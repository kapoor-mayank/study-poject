/*     */ package org.traccar.database;
/*     */ 
/*     */ import java.sql.SQLException;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.traccar.Context;
/*     */ import org.traccar.model.Attribute;
/*     */ import org.traccar.model.BaseModel;
/*     */ import org.traccar.model.Calendar;
/*     */ import org.traccar.model.Command;
/*     */ import org.traccar.model.Device;
/*     */ import org.traccar.model.Driver;
/*     */ import org.traccar.model.Geofence;
/*     */ import org.traccar.model.Group;
/*     */ import org.traccar.model.Maintenance;
/*     */ import org.traccar.model.ManagedUser;
/*     */ import org.traccar.model.Notification;
/*     */ import org.traccar.model.Permission;
/*     */ import org.traccar.model.Server;
/*     */ import org.traccar.model.User;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PermissionsManager
/*     */ {
/*  44 */   private static final Logger LOGGER = LoggerFactory.getLogger(PermissionsManager.class);
/*     */   
/*     */   private final DataManager dataManager;
/*     */   
/*     */   private final UsersManager usersManager;
/*     */   
/*     */   private volatile Server server;
/*  51 */   private final Map<Long, Set<Long>> groupPermissions = new HashMap<>();
/*  52 */   private final Map<Long, Set<Long>> devicePermissions = new HashMap<>();
/*  53 */   private final Map<Long, Set<Long>> deviceUsers = new HashMap<>();
/*  54 */   private final Map<Long, Set<Long>> groupDevices = new HashMap<>();
/*     */   
/*     */   public PermissionsManager(DataManager dataManager, UsersManager usersManager) {
/*  57 */     this.dataManager = dataManager;
/*  58 */     this.usersManager = usersManager;
/*  59 */     refreshServer();
/*  60 */     refreshDeviceAndGroupPermissions();
/*     */   }
/*     */   
/*     */   public User getUser(long userId) {
/*  64 */     return this.usersManager.getById(userId);
/*     */   }
/*     */   
/*     */   public Set<Long> getGroupPermissions(long userId) {
/*  68 */     if (!this.groupPermissions.containsKey(Long.valueOf(userId))) {
/*  69 */       this.groupPermissions.put(Long.valueOf(userId), new HashSet<>());
/*     */     }
/*  71 */     return this.groupPermissions.get(Long.valueOf(userId));
/*     */   }
/*     */   
/*     */   public Set<Long> getDevicePermissions(long userId) {
/*  75 */     if (!this.devicePermissions.containsKey(Long.valueOf(userId))) {
/*  76 */       this.devicePermissions.put(Long.valueOf(userId), new HashSet<>());
/*     */     }
/*  78 */     return this.devicePermissions.get(Long.valueOf(userId));
/*     */   }
/*     */   
/*     */   private Set<Long> getAllDeviceUsers(long deviceId) {
/*  82 */     if (!this.deviceUsers.containsKey(Long.valueOf(deviceId))) {
/*  83 */       this.deviceUsers.put(Long.valueOf(deviceId), new HashSet<>());
/*     */     }
/*  85 */     return this.deviceUsers.get(Long.valueOf(deviceId));
/*     */   }
/*     */   
/*     */   public Set<Long> getDeviceUsers(long deviceId) {
/*  89 */     Device device = Context.getIdentityManager().getById(deviceId);
/*  90 */     if (device != null && !device.getDisabled()) {
/*  91 */       return getAllDeviceUsers(deviceId);
/*     */     }
/*  93 */     Set<Long> result = new HashSet<>();
/*  94 */     for (Iterator<Long> iterator = getAllDeviceUsers(deviceId).iterator(); iterator.hasNext(); ) { long userId = ((Long)iterator.next()).longValue();
/*  95 */       if (getUserAdmin(userId)) {
/*  96 */         result.add(Long.valueOf(userId));
/*     */       } }
/*     */     
/*  99 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<Long> getGroupDevices(long groupId) {
/* 104 */     if (!this.groupDevices.containsKey(Long.valueOf(groupId))) {
/* 105 */       this.groupDevices.put(Long.valueOf(groupId), new HashSet<>());
/*     */     }
/* 107 */     return this.groupDevices.get(Long.valueOf(groupId));
/*     */   }
/*     */   
/*     */   public void refreshServer() {
/*     */     try {
/* 112 */       this.server = this.dataManager.getServer();
/* 113 */     } catch (SQLException error) {
/* 114 */       LOGGER.warn("Refresh server config error", error);
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void refreshDeviceAndGroupPermissions() {
/* 119 */     this.groupPermissions.clear();
/* 120 */     this.devicePermissions.clear();
/*     */ 
/*     */     
/*     */     try {
/* 124 */       GroupTree groupTree = new GroupTree(Context.getGroupsManager().getItems(Context.getGroupsManager().getAllItems()), Context.getDeviceManager().getAllDevices());
/* 125 */       for (Permission groupPermission : this.dataManager.getPermissions((Class)User.class, (Class)Group.class)) {
/* 126 */         Set<Long> userGroupPermissions = getGroupPermissions(groupPermission.getOwnerId());
/* 127 */         Set<Long> userDevicePermissions = getDevicePermissions(groupPermission.getOwnerId());
/* 128 */         userGroupPermissions.add(Long.valueOf(groupPermission.getPropertyId()));
/* 129 */         for (Group group : groupTree.getGroups(groupPermission.getPropertyId())) {
/* 130 */           userGroupPermissions.add(Long.valueOf(group.getId()));
/*     */         }
/* 132 */         for (Device device : groupTree.getDevices(groupPermission.getPropertyId())) {
/* 133 */           userDevicePermissions.add(Long.valueOf(device.getId()));
/*     */         }
/*     */       } 
/*     */       
/* 137 */       for (Permission devicePermission : this.dataManager.getPermissions((Class)User.class, (Class)Device.class)) {
/* 138 */         getDevicePermissions(devicePermission.getOwnerId()).add(Long.valueOf(devicePermission.getPropertyId()));
/*     */       }
/*     */       
/* 141 */       this.groupDevices.clear();
/* 142 */       for (Iterator<Long> iterator = Context.getGroupsManager().getAllItems().iterator(); iterator.hasNext(); ) { long groupId = ((Long)iterator.next()).longValue();
/* 143 */         for (Device device : groupTree.getDevices(groupId)) {
/* 144 */           getGroupDevices(groupId).add(Long.valueOf(device.getId()));
/*     */         } }
/*     */ 
/*     */     
/* 148 */     } catch (SQLException|ClassNotFoundException error) {
/* 149 */       LOGGER.warn("Refresh device permissions error", error);
/*     */     } 
/*     */     
/* 152 */     this.deviceUsers.clear();
/* 153 */     for (Map.Entry<Long, Set<Long>> entry : this.devicePermissions.entrySet()) {
/* 154 */       for (Iterator<Long> iterator = ((Set)entry.getValue()).iterator(); iterator.hasNext(); ) { long deviceId = ((Long)iterator.next()).longValue();
/* 155 */         getAllDeviceUsers(deviceId).add(entry.getKey()); }
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean getUserAdmin(long userId) {
/* 161 */     User user = getUser(userId);
/* 162 */     return (user != null && user.getAdministrator());
/*     */   }
/*     */   
/*     */   public void checkAdmin(long userId) throws SecurityException {
/* 166 */     if (!getUserAdmin(userId)) {
/* 167 */       throw new SecurityException("Admin access required");
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean getUserManager(long userId) {
/* 172 */     User user = getUser(userId);
/* 173 */     return (user != null && user.getUserLimit() != 0);
/*     */   }
/*     */   
/*     */   public void checkManager(long userId) throws SecurityException {
/* 177 */     if (!getUserManager(userId)) {
/* 178 */       throw new SecurityException("Manager access required");
/*     */     }
/*     */   }
/*     */   
/*     */   public void checkManager(long userId, long managedUserId) throws SecurityException {
/* 183 */     checkManager(userId);
/* 184 */     if (!this.usersManager.getUserItems(userId).contains(Long.valueOf(managedUserId))) {
/* 185 */       throw new SecurityException("User access denied");
/*     */     }
/*     */   }
/*     */   
/*     */   public void checkUserLimit(long userId) throws SecurityException {
/* 190 */     int userLimit = getUser(userId).getUserLimit();
/* 191 */     if (userLimit != -1 && this.usersManager.getUserItems(userId).size() >= userLimit) {
/* 192 */       throw new SecurityException("Manager user limit reached");
/*     */     }
/*     */   }
/*     */   
/*     */   public void checkDeviceLimit(long userId) throws SecurityException {
/* 197 */     int deviceLimit = getUser(userId).getDeviceLimit();
/* 198 */     if (deviceLimit != -1) {
/* 199 */       int deviceCount = 0;
/* 200 */       if (getUserManager(userId)) {
/* 201 */         deviceCount = Context.getDeviceManager().getAllManagedItems(userId).size();
/*     */       } else {
/* 203 */         deviceCount = Context.getDeviceManager().getAllUserItems(userId).size();
/*     */       } 
/* 205 */       if (deviceCount >= deviceLimit) {
/* 206 */         throw new SecurityException("User device limit reached");
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean getUserReadonly(long userId) {
/* 212 */     User user = getUser(userId);
/* 213 */     return (user != null && user.getReadonly());
/*     */   }
/*     */   
/*     */   public boolean getUserDeviceReadonly(long userId) {
/* 217 */     User user = getUser(userId);
/* 218 */     return (user != null && user.getDeviceReadonly());
/*     */   }
/*     */   
/*     */   public boolean getUserLimitCommands(long userId) {
/* 222 */     User user = getUser(userId);
/* 223 */     return (user != null && user.getLimitCommands());
/*     */   }
/*     */   
/*     */   public void checkReadonly(long userId) throws SecurityException {
/* 227 */     if (!getUserAdmin(userId) && (this.server.getReadonly() || getUserReadonly(userId))) {
/* 228 */       throw new SecurityException("Account is readonly");
/*     */     }
/*     */   }
/*     */   
/*     */   public void checkDeviceReadonly(long userId) throws SecurityException {
/* 233 */     if (!getUserAdmin(userId) && (this.server.getDeviceReadonly() || getUserDeviceReadonly(userId))) {
/* 234 */       throw new SecurityException("Account is device readonly");
/*     */     }
/*     */   }
/*     */   
/*     */   public void checkLimitCommands(long userId) throws SecurityException {
/* 239 */     if (!getUserAdmin(userId) && (this.server.getLimitCommands() || getUserLimitCommands(userId))) {
/* 240 */       throw new SecurityException("Account has limit sending commands");
/*     */     }
/*     */   }
/*     */   
/*     */   public void checkUserDeviceCommand(long userId, long deviceId, long commandId) throws SecurityException {
/* 245 */     if (!getUserAdmin(userId) && Context.getCommandsManager().checkDeviceCommand(deviceId, commandId)) {
/* 246 */       throw new SecurityException("Command can not be sent to this device");
/*     */     }
/*     */   }
/*     */   
/*     */   public void checkUserEnabled(long userId) throws SecurityException {
/* 251 */     User user = getUser(userId);
/* 252 */     if (user == null) {
/* 253 */       throw new SecurityException("Unknown account");
/*     */     }
/* 255 */     if (user.getDisabled()) {
/* 256 */       throw new SecurityException("Account is disabled");
/*     */     }
/* 258 */     if (user.getExpirationTime() != null && System.currentTimeMillis() > user.getExpirationTime().getTime()) {
/* 259 */       throw new SecurityException("Account has expired");
/*     */     }
/*     */   }
/*     */   
/*     */   public void checkUserUpdate(long userId, User before, User after) throws SecurityException {
/* 264 */     if (before.getAdministrator() != after.getAdministrator() || before
/* 265 */       .getDeviceLimit() != after.getDeviceLimit() || before
/* 266 */       .getUserLimit() != after.getUserLimit()) {
/* 267 */       checkAdmin(userId);
/*     */     }
/* 269 */     User user = getUser(userId);
/* 270 */     if (user != null && user.getExpirationTime() != null && (after
/* 271 */       .getExpirationTime() == null || user
/* 272 */       .getExpirationTime().compareTo(after.getExpirationTime()) < 0)) {
/* 273 */       checkAdmin(userId);
/*     */     }
/* 275 */     if (before.getReadonly() != after.getReadonly() || before
/* 276 */       .getDeviceReadonly() != after.getDeviceReadonly() || before
/* 277 */       .getDisabled() != after.getDisabled() || before
/* 278 */       .getLimitCommands() != after.getLimitCommands()) {
/* 279 */       if (userId == after.getId()) {
/* 280 */         checkAdmin(userId);
/*     */       }
/* 282 */       if (!getUserAdmin(userId)) {
/* 283 */         checkManager(userId);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void checkUser(long userId, long managedUserId) throws SecurityException {
/* 289 */     if (userId != managedUserId && !getUserAdmin(userId)) {
/* 290 */       checkManager(userId, managedUserId);
/*     */     }
/*     */   }
/*     */   
/*     */   public void checkGroup(long userId, long groupId) throws SecurityException {
/* 295 */     if (!getGroupPermissions(userId).contains(Long.valueOf(groupId)) && !getUserAdmin(userId)) {
/* 296 */       checkManager(userId);
/* 297 */       for (Iterator<Long> iterator = this.usersManager.getUserItems(userId).iterator(); iterator.hasNext(); ) { long managedUserId = ((Long)iterator.next()).longValue();
/* 298 */         if (getGroupPermissions(managedUserId).contains(Long.valueOf(groupId))) {
/*     */           return;
/*     */         } }
/*     */       
/* 302 */       throw new SecurityException("Group access denied");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void checkDevice(long userId, long deviceId) throws SecurityException {
/* 307 */     if (!Context.getDeviceManager().getUserItems(userId).contains(Long.valueOf(deviceId)) && !getUserAdmin(userId)) {
/* 308 */       checkManager(userId);
/* 309 */       for (Iterator<Long> iterator = this.usersManager.getUserItems(userId).iterator(); iterator.hasNext(); ) { long managedUserId = ((Long)iterator.next()).longValue();
/* 310 */         if (Context.getDeviceManager().getUserItems(managedUserId).contains(Long.valueOf(deviceId))) {
/*     */           return;
/*     */         } }
/*     */       
/* 314 */       throw new SecurityException("Device access denied");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void checkRegistration(long userId) {
/* 319 */     if (!this.server.getRegistration() && !getUserAdmin(userId)) {
/* 320 */       throw new SecurityException("Registration disabled");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void checkPermission(Class<?> object, long userId, long objectId) throws SecurityException {
/* 326 */     SimpleObjectManager<? extends BaseModel> manager = null;
/*     */     
/* 328 */     if (object.equals(Device.class)) {
/* 329 */       checkDevice(userId, objectId);
/* 330 */     } else if (object.equals(Group.class)) {
/* 331 */       checkGroup(userId, objectId);
/* 332 */     } else if (object.equals(User.class) || object.equals(ManagedUser.class)) {
/* 333 */       checkUser(userId, objectId);
/* 334 */     } else if (object.equals(Geofence.class)) {
/* 335 */       manager = Context.getGeofenceManager();
/* 336 */     } else if (object.equals(Attribute.class)) {
/* 337 */       manager = Context.getAttributesManager();
/* 338 */     } else if (object.equals(Driver.class)) {
/* 339 */       manager = Context.getDriversManager();
/* 340 */     } else if (object.equals(Calendar.class)) {
/* 341 */       manager = Context.getCalendarManager();
/* 342 */     } else if (object.equals(Command.class)) {
/* 343 */       manager = Context.getCommandsManager();
/* 344 */     } else if (object.equals(Maintenance.class)) {
/* 345 */       manager = Context.getMaintenancesManager();
/* 346 */     } else if (object.equals(Notification.class)) {
/* 347 */       manager = Context.getNotificationManager();
/*     */     } else {
/* 349 */       throw new IllegalArgumentException("Unknown object type");
/*     */     } 
/*     */     
/* 352 */     if (manager != null && !manager.checkItemPermission(userId, objectId) && !getUserAdmin(userId)) {
/* 353 */       checkManager(userId);
/* 354 */       for (Iterator<Long> iterator = this.usersManager.getManagedItems(userId).iterator(); iterator.hasNext(); ) { long managedUserId = ((Long)iterator.next()).longValue();
/* 355 */         if (manager.checkItemPermission(managedUserId, objectId)) {
/*     */           return;
/*     */         } }
/*     */       
/* 359 */       throw new SecurityException("Type " + object + " access denied");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void refreshAllUsersPermissions() {
/* 364 */     if (Context.getGeofenceManager() != null) {
/* 365 */       Context.getGeofenceManager().refreshUserItems();
/*     */     }
/* 367 */     Context.getCalendarManager().refreshUserItems();
/* 368 */     Context.getDriversManager().refreshUserItems();
/* 369 */     Context.getAttributesManager().refreshUserItems();
/* 370 */     Context.getCommandsManager().refreshUserItems();
/* 371 */     Context.getMaintenancesManager().refreshUserItems();
/* 372 */     if (Context.getNotificationManager() != null) {
/* 373 */       Context.getNotificationManager().refreshUserItems();
/*     */     }
/*     */   }
/*     */   
/*     */   public void refreshAllExtendedPermissions() {
/* 378 */     if (Context.getGeofenceManager() != null) {
/* 379 */       Context.getGeofenceManager().refreshExtendedPermissions();
/*     */     }
/* 381 */     Context.getDriversManager().refreshExtendedPermissions();
/* 382 */     Context.getAttributesManager().refreshExtendedPermissions();
/* 383 */     Context.getCommandsManager().refreshExtendedPermissions();
/* 384 */     Context.getMaintenancesManager().refreshExtendedPermissions();
/*     */   }
/*     */   
/*     */   public void refreshPermissions(Permission permission) {
/* 388 */     if (permission.getOwnerClass().equals(User.class)) {
/* 389 */       if (permission.getPropertyClass().equals(Device.class) || permission
/* 390 */         .getPropertyClass().equals(Group.class)) {
/* 391 */         refreshDeviceAndGroupPermissions();
/* 392 */         refreshAllExtendedPermissions();
/* 393 */       } else if (permission.getPropertyClass().equals(ManagedUser.class)) {
/* 394 */         this.usersManager.refreshUserItems();
/* 395 */       } else if (permission.getPropertyClass().equals(Geofence.class) && Context.getGeofenceManager() != null) {
/* 396 */         Context.getGeofenceManager().refreshUserItems();
/* 397 */       } else if (permission.getPropertyClass().equals(Driver.class)) {
/* 398 */         Context.getDriversManager().refreshUserItems();
/* 399 */       } else if (permission.getPropertyClass().equals(Attribute.class)) {
/* 400 */         Context.getAttributesManager().refreshUserItems();
/* 401 */       } else if (permission.getPropertyClass().equals(Calendar.class)) {
/* 402 */         Context.getCalendarManager().refreshUserItems();
/* 403 */       } else if (permission.getPropertyClass().equals(Command.class)) {
/* 404 */         Context.getCommandsManager().refreshUserItems();
/* 405 */       } else if (permission.getPropertyClass().equals(Maintenance.class)) {
/* 406 */         Context.getMaintenancesManager().refreshUserItems();
/* 407 */       } else if (permission.getPropertyClass().equals(Notification.class) && 
/* 408 */         Context.getNotificationManager() != null) {
/* 409 */         Context.getNotificationManager().refreshUserItems();
/*     */       } 
/* 411 */     } else if (permission.getOwnerClass().equals(Device.class) || permission.getOwnerClass().equals(Group.class)) {
/* 412 */       if (permission.getPropertyClass().equals(Geofence.class) && Context.getGeofenceManager() != null) {
/* 413 */         Context.getGeofenceManager().refreshExtendedPermissions();
/* 414 */       } else if (permission.getPropertyClass().equals(Driver.class)) {
/* 415 */         Context.getDriversManager().refreshExtendedPermissions();
/* 416 */       } else if (permission.getPropertyClass().equals(Attribute.class)) {
/* 417 */         Context.getAttributesManager().refreshExtendedPermissions();
/* 418 */       } else if (permission.getPropertyClass().equals(Command.class)) {
/* 419 */         Context.getCommandsManager().refreshExtendedPermissions();
/* 420 */       } else if (permission.getPropertyClass().equals(Maintenance.class)) {
/* 421 */         Context.getMaintenancesManager().refreshExtendedPermissions();
/* 422 */       } else if (permission.getPropertyClass().equals(Notification.class) && 
/* 423 */         Context.getNotificationManager() != null) {
/* 424 */         Context.getNotificationManager().refreshExtendedPermissions();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Server getServer() {
/* 430 */     return this.server;
/*     */   }
/*     */   
/*     */   public void updateServer(Server server) throws SQLException {
/* 434 */     this.dataManager.updateObject((BaseModel)server);
/* 435 */     this.server = server;
/*     */   }
/*     */   
/*     */   public User login(String email, String password) throws SQLException {
/* 439 */     User user = this.dataManager.login(email, password);
/* 440 */     if (user != null) {
/* 441 */       checkUserEnabled(user.getId());
/* 442 */       return getUser(user.getId());
/*     */     } 
/* 444 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object lookupAttribute(long userId, String key, Object defaultValue) {
/* 449 */     Object preference, serverPreference = this.server.getAttributes().get(key);
/* 450 */     Object userPreference = getUser(userId).getAttributes().get(key);
/* 451 */     if (this.server.getForceSettings()) {
/* 452 */       preference = (serverPreference != null) ? serverPreference : userPreference;
/*     */     } else {
/* 454 */       preference = (userPreference != null) ? userPreference : serverPreference;
/*     */     } 
/* 456 */     return (preference != null) ? preference : defaultValue;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\database\PermissionsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */