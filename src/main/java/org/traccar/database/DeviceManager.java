/*     */ package org.traccar.database;
/*     */ 
/*     */ import java.sql.SQLException;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.atomic.AtomicLong;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.traccar.Context;
/*     */ import org.traccar.config.Config;
/*     */ import org.traccar.model.BaseModel;
/*     */ import org.traccar.model.Device;
/*     */ import org.traccar.model.DeviceAccumulators;
/*     */ import org.traccar.model.DeviceState;
/*     */ import org.traccar.model.Group;
/*     */ import org.traccar.model.Position;
/*     */ import org.traccar.model.Server;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DeviceManager
/*     */   extends BaseObjectManager<Device>
/*     */   implements IdentityManager, ManagableObjects
/*     */ {
/*  41 */   private static final Logger LOGGER = LoggerFactory.getLogger(DeviceManager.class);
/*     */   
/*     */   public static final long DEFAULT_REFRESH_DELAY = 300L;
/*     */   
/*     */   private final Config config;
/*     */   
/*     */   private final long dataRefreshDelay;
/*     */   private boolean lookupGroupsAttribute;
/*     */   private Map<String, Device> devicesByUniqueId;
/*     */   private Map<String, Device> devicesByPhone;
/*  51 */   private AtomicLong devicesLastUpdate = new AtomicLong();
/*     */   
/*  53 */   private final Map<Long, Position> positions = new ConcurrentHashMap<>();
/*     */   
/*  55 */   private final Map<Long, DeviceState> deviceStates = new ConcurrentHashMap<>();
/*     */   
/*     */   public DeviceManager(DataManager dataManager) {
/*  58 */     super(dataManager, Device.class);
/*  59 */     this.config = Context.getConfig();
/*  60 */     if (this.devicesByPhone == null) {
/*  61 */       this.devicesByPhone = new ConcurrentHashMap<>();
/*     */     }
/*  63 */     if (this.devicesByUniqueId == null) {
/*  64 */       this.devicesByUniqueId = new ConcurrentHashMap<>();
/*     */     }
/*  66 */     this.dataRefreshDelay = this.config.getLong("database.refreshDelay", 300L) * 1000L;
/*  67 */     this.lookupGroupsAttribute = this.config.getBoolean("deviceManager.lookupGroupsAttribute");
/*  68 */     refreshLastPositions();
/*     */   }
/*     */ 
/*     */   
/*     */   public long addUnknownDevice(String uniqueId) {
/*  73 */     Device device = new Device();
/*  74 */     device.setName(uniqueId);
/*  75 */     device.setUniqueId(uniqueId);
/*  76 */     device.setCategory(Context.getConfig().getString("database.registerUnknown.defaultCategory"));
/*     */     
/*  78 */     long defaultGroupId = Context.getConfig().getLong("database.registerUnknown.defaultGroupId");
/*  79 */     if (defaultGroupId != 0L) {
/*  80 */       device.setGroupId(defaultGroupId);
/*     */     }
/*     */     
/*     */     try {
/*  84 */       addItem(device);
/*     */       
/*  86 */       LOGGER.info("Automatically registered device " + uniqueId);
/*     */       
/*  88 */       if (defaultGroupId != 0L) {
/*  89 */         Context.getPermissionsManager().refreshDeviceAndGroupPermissions();
/*  90 */         Context.getPermissionsManager().refreshAllExtendedPermissions();
/*     */       } 
/*     */       
/*  93 */       return device.getId();
/*  94 */     } catch (SQLException e) {
/*  95 */       LOGGER.warn("Automatic device registration error", e);
/*  96 */       return 0L;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void updateDeviceCache(boolean force) throws SQLException {
/* 101 */     long lastUpdate = this.devicesLastUpdate.get();
/* 102 */     if ((force || System.currentTimeMillis() - lastUpdate > this.dataRefreshDelay) && this.devicesLastUpdate
/* 103 */       .compareAndSet(lastUpdate, System.currentTimeMillis())) {
/* 104 */       refreshItems();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Device getByUniqueId(String uniqueId) throws SQLException {
/* 110 */     return this.devicesByUniqueId.get(uniqueId);
/*     */   }
/*     */   
/*     */   public Device getDeviceByPhone(String phone) {
/* 114 */     return this.devicesByPhone.get(phone);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<Long> getAllItems() {
/* 119 */     Set<Long> result = super.getAllItems();
/* 120 */     if (result.isEmpty()) {
/*     */       try {
/* 122 */         updateDeviceCache(true);
/* 123 */       } catch (SQLException e) {
/* 124 */         LOGGER.warn("Update device cache error", e);
/*     */       } 
/* 126 */       result = super.getAllItems();
/*     */     } 
/* 128 */     return result;
/*     */   }
/*     */   
/*     */   public Collection<Device> getAllDevices() {
/* 132 */     return getItems(getAllItems());
/*     */   }
/*     */   
/*     */   public Set<Long> getAllUserItems(long userId) {
/* 136 */     return Context.getPermissionsManager().getDevicePermissions(userId);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<Long> getUserItems(long userId) {
/* 141 */     if (Context.getPermissionsManager() != null) {
/* 142 */       Set<Long> result = new HashSet<>();
/* 143 */       for (Iterator<Long> iterator = Context.getPermissionsManager().getDevicePermissions(userId).iterator(); iterator.hasNext(); ) { long deviceId = ((Long)iterator.next()).longValue();
/* 144 */         Device device = getById(deviceId);
/* 145 */         if (device != null && !device.getDisabled()) {
/* 146 */           result.add(Long.valueOf(deviceId));
/*     */         } }
/*     */       
/* 149 */       return result;
/*     */     } 
/* 151 */     return new HashSet<>();
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<Long> getAllManagedItems(long userId) {
/* 156 */     Set<Long> result = new HashSet<>();
/* 157 */     result.addAll(getAllUserItems(userId));
/* 158 */     for (Iterator<Long> iterator = Context.getUsersManager().getUserItems(userId).iterator(); iterator.hasNext(); ) { long managedUserId = ((Long)iterator.next()).longValue();
/* 159 */       result.addAll(getAllUserItems(managedUserId)); }
/*     */     
/* 161 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<Long> getManagedItems(long userId) {
/* 166 */     Set<Long> result = new HashSet<>();
/* 167 */     result.addAll(getUserItems(userId));
/* 168 */     for (Iterator<Long> iterator = Context.getUsersManager().getUserItems(userId).iterator(); iterator.hasNext(); ) { long managedUserId = ((Long)iterator.next()).longValue();
/* 169 */       result.addAll(getUserItems(managedUserId)); }
/*     */     
/* 171 */     return result;
/*     */   }
/*     */   
/*     */   private void putUniqueDeviceId(Device device) {
/* 175 */     if (this.devicesByUniqueId == null) {
/* 176 */       this.devicesByUniqueId = new ConcurrentHashMap<>(getAllItems().size());
/*     */     }
/* 178 */     this.devicesByUniqueId.put(device.getUniqueId(), device);
/*     */   }
/*     */   
/*     */   private void putPhone(Device device) {
/* 182 */     if (this.devicesByPhone == null) {
/* 183 */       this.devicesByPhone = new ConcurrentHashMap<>(getAllItems().size());
/*     */     }
/* 185 */     this.devicesByPhone.put(device.getPhone(), device);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addNewItem(Device device) {
/* 190 */     super.addNewItem(device);
/* 191 */     putUniqueDeviceId(device);
/* 192 */     if (device.getPhone() != null && !device.getPhone().isEmpty()) {
/* 193 */       putPhone(device);
/*     */     }
/* 195 */     if (Context.getGeofenceManager() != null) {
/* 196 */       Position lastPosition = getLastPosition(device.getId());
/* 197 */       if (lastPosition != null) {
/* 198 */         device.setGeofenceIds(Context.getGeofenceManager().getCurrentDeviceGeofences(lastPosition));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updateCachedItem(Device device) {
/* 205 */     Device cachedDevice = getById(device.getId());
/* 206 */     cachedDevice.setName(device.getName());
/* 207 */     cachedDevice.setGroupId(device.getGroupId());
/* 208 */     cachedDevice.setCategory(device.getCategory());
/* 209 */     cachedDevice.setContact(device.getContact());
/* 210 */     cachedDevice.setModel(device.getModel());
/* 211 */     cachedDevice.setDisabled(device.getDisabled());
/* 212 */     cachedDevice.setAttributes(device.getAttributes());
/* 213 */     if (!device.getUniqueId().equals(cachedDevice.getUniqueId())) {
/* 214 */       this.devicesByUniqueId.remove(cachedDevice.getUniqueId());
/* 215 */       cachedDevice.setUniqueId(device.getUniqueId());
/* 216 */       putUniqueDeviceId(cachedDevice);
/*     */     } 
/* 218 */     if (device.getPhone() != null && !device.getPhone().isEmpty() && 
/* 219 */       !device.getPhone().equals(cachedDevice.getPhone())) {
/* 220 */       String phone = cachedDevice.getPhone();
/* 221 */       if (phone != null && !phone.isEmpty()) {
/* 222 */         this.devicesByPhone.remove(phone);
/*     */       }
/* 224 */       cachedDevice.setPhone(device.getPhone());
/* 225 */       putPhone(cachedDevice);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void removeCachedItem(long deviceId) {
/* 231 */     Device cachedDevice = getById(deviceId);
/* 232 */     if (cachedDevice != null) {
/* 233 */       String deviceUniqueId = cachedDevice.getUniqueId();
/* 234 */       String phone = cachedDevice.getPhone();
/* 235 */       super.removeCachedItem(deviceId);
/* 236 */       this.devicesByUniqueId.remove(deviceUniqueId);
/* 237 */       if (phone != null && !phone.isEmpty()) {
/* 238 */         this.devicesByPhone.remove(phone);
/*     */       }
/*     */     } 
/* 241 */     this.positions.remove(Long.valueOf(deviceId));
/*     */   }
/*     */   
/*     */   public void updateDeviceStatus(Device device) throws SQLException {
/* 245 */     getDataManager().updateDeviceStatus(device);
/* 246 */     Device cachedDevice = getById(device.getId());
/* 247 */     if (cachedDevice != null) {
/* 248 */       cachedDevice.setStatus(device.getStatus());
/*     */     }
/*     */   }
/*     */   
/*     */   private void refreshLastPositions() {
/* 253 */     if (getDataManager() != null) {
/*     */       try {
/* 255 */         for (Position position : getDataManager().getLatestPositions()) {
/* 256 */           this.positions.put(Long.valueOf(position.getDeviceId()), position);
/*     */         }
/* 258 */       } catch (SQLException error) {
/* 259 */         LOGGER.warn("Load latest positions error", error);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isLatestPosition(Position position) {
/* 265 */     Position lastPosition = getLastPosition(position.getDeviceId());
/* 266 */     return (lastPosition == null || position.getFixTime().compareTo(lastPosition.getFixTime()) >= 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateLatestPosition(Position position) throws SQLException {
/* 271 */     if (isLatestPosition(position)) {
/*     */       
/* 273 */       getDataManager().updateLatestPosition(position);
/*     */       
/* 275 */       Device device = getById(position.getDeviceId());
/* 276 */       if (device != null) {
/* 277 */         device.setPositionId(position.getId());
/*     */       }
/*     */       
/* 280 */       this.positions.put(Long.valueOf(position.getDeviceId()), position);
/*     */       
/* 282 */       if (Context.getConnectionManager() != null) {
/* 283 */         Context.getConnectionManager().updatePosition(position);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Position getLastPosition(long deviceId) {
/* 290 */     return this.positions.get(Long.valueOf(deviceId));
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<Position> getInitialState(long userId) {
/* 295 */     List<Position> result = new LinkedList<>();
/*     */     
/* 297 */     if (Context.getPermissionsManager() != null) {
/* 298 */       for (Iterator<Long> iterator = (Context.getPermissionsManager().getUserAdmin(userId) ? 
/* 299 */         getAllUserItems(userId) : getUserItems(userId)).iterator(); iterator.hasNext(); ) {
/* 300 */         long deviceId = ((Long)iterator.next()).longValue(); if (this.positions.containsKey(Long.valueOf(deviceId))) {
/* 301 */           result.add(this.positions.get(Long.valueOf(deviceId)));
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 306 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean lookupAttributeBoolean(long deviceId, String attributeName, boolean defaultValue, boolean lookupConfig) {
/* 312 */     Object result = lookupAttribute(deviceId, attributeName, lookupConfig);
/* 313 */     if (result != null) {
/* 314 */       return (result instanceof String) ? Boolean.parseBoolean((String)result) : ((Boolean)result).booleanValue();
/*     */     }
/* 316 */     return defaultValue;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String lookupAttributeString(long deviceId, String attributeName, String defaultValue, boolean lookupConfig) {
/* 322 */     Object result = lookupAttribute(deviceId, attributeName, lookupConfig);
/* 323 */     return (result != null) ? (String)result : defaultValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public int lookupAttributeInteger(long deviceId, String attributeName, int defaultValue, boolean lookupConfig) {
/* 328 */     Object result = lookupAttribute(deviceId, attributeName, lookupConfig);
/* 329 */     if (result != null) {
/* 330 */       return (result instanceof String) ? Integer.parseInt((String)result) : ((Number)result).intValue();
/*     */     }
/* 332 */     return defaultValue;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long lookupAttributeLong(long deviceId, String attributeName, long defaultValue, boolean lookupConfig) {
/* 338 */     Object result = lookupAttribute(deviceId, attributeName, lookupConfig);
/* 339 */     if (result != null) {
/* 340 */       return (result instanceof String) ? Long.parseLong((String)result) : ((Number)result).longValue();
/*     */     }
/* 342 */     return defaultValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public double lookupAttributeDouble(long deviceId, String attributeName, double defaultValue, boolean lookupConfig) {
/* 347 */     Object result = lookupAttribute(deviceId, attributeName, lookupConfig);
/* 348 */     if (result != null) {
/* 349 */       return (result instanceof String) ? Double.parseDouble((String)result) : ((Number)result).doubleValue();
/*     */     }
/* 351 */     return defaultValue;
/*     */   }
/*     */   
/*     */   private Object lookupAttribute(long deviceId, String attributeName, boolean lookupConfig) {
/* 355 */     Object result = null;
/* 356 */     Device device = getById(deviceId);
/* 357 */     if (device != null) {
/* 358 */       result = device.getAttributes().get(attributeName);
/* 359 */       if (result == null && this.lookupGroupsAttribute) {
/* 360 */         long groupId = device.getGroupId();
/* 361 */         while (groupId != 0L) {
/* 362 */           Group group = Context.getGroupsManager().getById(groupId);
/* 363 */           if (group != null) {
/* 364 */             result = group.getAttributes().get(attributeName);
/* 365 */             if (result != null) {
/*     */               break;
/*     */             }
/* 368 */             groupId = group.getGroupId(); continue;
/*     */           } 
/* 370 */           groupId = 0L;
/*     */         } 
/*     */       } 
/*     */       
/* 374 */       if (result == null) {
/* 375 */         if (lookupConfig) {
/* 376 */           result = Context.getConfig().getString(attributeName);
/*     */         } else {
/* 378 */           Server server = Context.getPermissionsManager().getServer();
/* 379 */           result = server.getAttributes().get(attributeName);
/*     */         } 
/*     */       }
/*     */     } 
/* 383 */     return result;
/*     */   }
/*     */   
/*     */   public void resetDeviceAccumulators(DeviceAccumulators deviceAccumulators) throws SQLException {
/* 387 */     Position last = this.positions.get(Long.valueOf(deviceAccumulators.getDeviceId()));
/* 388 */     if (last != null) {
/* 389 */       if (deviceAccumulators.getTotalDistance() != null) {
/* 390 */         last.getAttributes().put("totalDistance", deviceAccumulators.getTotalDistance());
/*     */       }
/* 392 */       if (deviceAccumulators.getHours() != null) {
/* 393 */         last.getAttributes().put("hours", deviceAccumulators.getHours());
/*     */       }
/* 395 */       getDataManager().addObject((BaseModel)last);
/* 396 */       updateLatestPosition(last);
/*     */     } else {
/* 398 */       throw new IllegalArgumentException();
/*     */     } 
/*     */   }
/*     */   
/*     */   public DeviceState getDeviceState(long deviceId) {
/* 403 */     DeviceState deviceState = this.deviceStates.get(Long.valueOf(deviceId));
/* 404 */     if (deviceState == null) {
/* 405 */       deviceState = new DeviceState();
/* 406 */       this.deviceStates.put(Long.valueOf(deviceId), deviceState);
/*     */     } 
/* 408 */     return deviceState;
/*     */   }
/*     */   
/*     */   public void setDeviceState(long deviceId, DeviceState deviceState) {
/* 412 */     this.deviceStates.put(Long.valueOf(deviceId), deviceState);
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\database\DeviceManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */