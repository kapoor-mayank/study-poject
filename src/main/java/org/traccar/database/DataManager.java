/*     */ package org.traccar.database;
/*     */ 
/*     */ import java.sql.SQLException;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.traccar.Context;
/*     */ import org.traccar.config.Config;
/*     */ import org.traccar.model.Attribute;
/*     */ import org.traccar.model.BaseModel;
/*     */ import org.traccar.model.Calendar;
/*     */ import org.traccar.model.Command;
/*     */ import org.traccar.model.Device;
/*     */ import org.traccar.model.Driver;
/*     */ import org.traccar.model.Event;
/*     */ import org.traccar.model.Geofence;
/*     */ import org.traccar.model.Group;
/*     */ import org.traccar.model.Maintenance;
/*     */ import org.traccar.model.ManagedUser;
/*     */ import org.traccar.model.Notification;
/*     */ import org.traccar.model.Permission;
/*     */ import org.traccar.model.Position;
/*     */ import org.traccar.model.Server;
/*     */ import org.traccar.model.Statistics;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DataManager
/*     */ {
/*  52 */   private static final Logger LOGGER = LoggerFactory.getLogger(DataManager.class);
/*     */   
/*     */   public static final String ACTION_SELECT_ALL = "selectAll";
/*     */   
/*     */   public static final String ACTION_SELECT = "select";
/*     */   
/*     */   public static final String ACTION_INSERT = "insert";
/*     */   public static final String ACTION_UPDATE = "update";
/*     */   public static final String ACTION_DELETE = "delete";
/*     */   private Server server;
/*     */   private User user;
/*     */   private Group group;
/*     */   private long deviceIncrement;
/*  65 */   private Map<Long, Device> devices = new HashMap<>();
/*     */   
/*     */   private boolean forceLdap;
/*     */ 
/*     */   
/*     */   public DataManager(Config config) throws Exception {
/*  71 */     this.server = new Server();
/*  72 */     this.server.setId(1L);
/*     */     
/*  74 */     this.user = new User();
/*  75 */     this.user.setId(1L);
/*  76 */     this.user.setAdministrator(true);
/*     */     
/*  78 */     this.group = new Group();
/*  79 */     this.group.setId(1L);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String constructObjectQuery(String action, Class<?> clazz, boolean extended) {
/*  84 */     return null;
/*     */   }
/*     */   
/*     */   public static String constructPermissionQuery(String action, Class<?> owner, Class<?> property) {
/*  88 */     return null;
/*     */   }
/*     */   
/*     */   public User login(String email, String password) throws SQLException {
/*  92 */     if (email.equals("admin") && password.equals(Context.getConfig().getString("user.password", "admin"))) {
/*  93 */       return this.user;
/*     */     }
/*  95 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateUser(User user) throws SQLException {}
/*     */ 
/*     */   
/*     */   public void updateDeviceStatus(Device device) throws SQLException {}
/*     */   
/*     */   public Collection<Position> getPositions(long deviceId, Date from, Date to) throws SQLException {
/* 105 */     return Collections.emptyList();
/*     */   }
/*     */   
/*     */   public Position getPosition(long positionId) throws SQLException {
/* 109 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addPosition(Position position) throws SQLException {}
/*     */ 
/*     */   
/*     */   public void updateLatestPosition(Position position) throws SQLException {}
/*     */   
/*     */   public Collection<Position> getLatestPositions() throws SQLException {
/* 119 */     return Collections.emptyList();
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearHistory() throws SQLException {}
/*     */   
/*     */   public Server getServer() throws SQLException {
/* 126 */     return this.server;
/*     */   }
/*     */   
/*     */   public Event getEvent(long eventId) throws SQLException {
/* 130 */     return null;
/*     */   }
/*     */   
/*     */   public Collection<Event> getEvents(long deviceId, Date from, Date to) throws SQLException {
/* 134 */     return Collections.emptyList();
/*     */   }
/*     */   
/*     */   public Collection<Statistics> getStatistics(Date from, Date to) throws SQLException {
/* 138 */     return Collections.emptyList();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addStatistics(Statistics statistics) throws SQLException {}
/*     */   
/*     */   public static Class<?> getClassByName(String name) throws ClassNotFoundException {
/* 145 */     switch (name.toLowerCase().replace("id", "")) {
/*     */       case "device":
/* 147 */         return Device.class;
/*     */       case "group":
/* 149 */         return Group.class;
/*     */       case "user":
/* 151 */         return User.class;
/*     */       case "manageduser":
/* 153 */         return ManagedUser.class;
/*     */       case "geofence":
/* 155 */         return Geofence.class;
/*     */       case "driver":
/* 157 */         return Driver.class;
/*     */       case "attribute":
/* 159 */         return Attribute.class;
/*     */       case "calendar":
/* 161 */         return Calendar.class;
/*     */       case "command":
/* 163 */         return Command.class;
/*     */       case "maintenance":
/* 165 */         return Maintenance.class;
/*     */       case "notification":
/* 167 */         return Notification.class;
/*     */     } 
/* 169 */     throw new ClassNotFoundException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void linkObject(Class<?> owner, long ownerId, Class<?> property, long propertyId, boolean link) throws SQLException {}
/*     */ 
/*     */   
/*     */   public <T extends BaseModel> T getObject(Class<T> clazz, long entityId) throws SQLException {
/* 178 */     return null;
/*     */   }
/*     */   
/*     */   public <T> Collection<T> getObjects(Class<T> clazz) throws SQLException {
/* 182 */     if (clazz.equals(Server.class))
/* 183 */       return Collections.singleton((T)this.server); 
/* 184 */     if (clazz.equals(User.class))
/* 185 */       return Collections.singleton((T)this.user); 
/* 186 */     if (clazz.equals(Group.class))
/* 187 */       return Collections.singleton((T)this.group); 
/* 188 */     if (clazz.equals(Device.class)) {
/* 189 */       return (Collection)this.devices.values();
/*     */     }
/* 191 */     return Collections.emptyList();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<Permission> getPermissions(Class<? extends BaseModel> owner, Class<? extends BaseModel> property) throws SQLException, ClassNotFoundException {
/* 197 */     if (owner.equals(User.class) && property.equals(Group.class)) {
/* 198 */       LinkedHashMap<String, Long> permission = new LinkedHashMap<>();
/* 199 */       permission.put("userId", Long.valueOf(this.user.getId()));
/* 200 */       permission.put("groupId", Long.valueOf(this.group.getId()));
/* 201 */       return Collections.singleton(new Permission(permission));
/*     */     } 
/* 203 */     return Collections.emptyList();
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void addObject(BaseModel entity) throws SQLException {
/* 208 */     if (entity instanceof Device) {
/* 209 */       Device device = (Device)entity;
/* 210 */       device.setId(++this.deviceIncrement);
/* 211 */       device.setGroupId(this.group.getId());
/* 212 */       if (Context.getConfig().getBoolean("redis.deviceModels")) {
/* 213 */         device.setModel(Context.getRedisManager().getDeviceModel(device.getUniqueId()));
/*     */       }
/* 215 */       this.devices.put(Long.valueOf(device.getId()), device);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void updateObject(BaseModel entity) throws SQLException {}
/*     */   
/*     */   public void removeObject(Class<? extends BaseModel> clazz, long entityId) throws SQLException {}
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\database\DataManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */