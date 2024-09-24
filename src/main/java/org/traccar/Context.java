/*     */ package org.traccar;
/*     */ 
/*     */ import com.fasterxml.jackson.databind.Module;
/*     */ import com.fasterxml.jackson.databind.ObjectMapper;
/*     */ import com.fasterxml.jackson.databind.SerializationFeature;
/*     */ import com.fasterxml.jackson.datatype.jsr353.JSR353Module;
/*     */ import java.net.InetAddress;
/*     */ import java.net.UnknownHostException;
/*     */ import java.util.Properties;
/*     */ import javax.ws.rs.client.Client;
/*     */ import javax.ws.rs.client.ClientBuilder;
/*     */ import javax.ws.rs.ext.ContextResolver;
/*     */ import org.apache.velocity.app.VelocityEngine;
/*     */ import org.eclipse.jetty.util.URIUtil;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.traccar.config.Config;
/*     */ import org.traccar.database.AttributesManager;
/*     */ import org.traccar.database.BaseObjectManager;
/*     */ import org.traccar.database.CalendarManager;
/*     */ import org.traccar.database.CommandsManager;
/*     */ import org.traccar.database.ConnectionManager;
/*     */ import org.traccar.database.DataManager;
/*     */ import org.traccar.database.DeviceManager;
/*     */ import org.traccar.database.DriversManager;
/*     */ import org.traccar.database.GeofenceManager;
/*     */ import org.traccar.database.GroupsManager;
/*     */ import org.traccar.database.IdentityManager;
/*     */ import org.traccar.database.LdapProvider;
/*     */ import org.traccar.database.MailManager;
/*     */ import org.traccar.database.MaintenancesManager;
/*     */ import org.traccar.database.MediaManager;
/*     */ import org.traccar.database.NotificationManager;
/*     */ import org.traccar.database.PermissionsManager;
/*     */ import org.traccar.database.RedisManager;
/*     */ import org.traccar.database.UsersManager;
/*     */ import org.traccar.geocoder.Geocoder;
/*     */ import org.traccar.helper.Log;
/*     */ import org.traccar.helper.SanitizerModule;
/*     */ import org.traccar.model.Attribute;
/*     */ import org.traccar.model.Calendar;
/*     */ import org.traccar.model.Command;
/*     */ import org.traccar.model.Device;
/*     */ import org.traccar.model.Driver;
/*     */ import org.traccar.model.Geofence;
/*     */ import org.traccar.model.Group;
/*     */ import org.traccar.model.Maintenance;
/*     */ import org.traccar.model.Notification;
/*     */ import org.traccar.model.User;
/*     */ import org.traccar.notification.EventForwarder;
/*     */ import org.traccar.notification.JsonTypeEventForwarder;
/*     */ import org.traccar.notification.NotificatorManager;
/*     */ import org.traccar.reports.model.TripsConfig;
/*     */ import org.traccar.sms.SmsManager;
/*     */ import org.traccar.web.WebServer;
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
/*     */ public final class Context
/*     */ {
/*     */   private static Config config;
/*     */   private static DataForwarder dataForwarder;
/*     */   private static ObjectMapper objectMapper;
/*     */   private static IdentityManager identityManager;
/*     */   private static DataManager dataManager;
/*  75 */   private static final Logger LOGGER = LoggerFactory.getLogger(Context.class); private static LdapProvider ldapProvider; private static MailManager mailManager; private static MediaManager mediaManager;
/*     */   private static RedisManager redisManager;
/*     */   private static UsersManager usersManager;
/*     */   private static GroupsManager groupsManager;
/*     */   private static DeviceManager deviceManager;
/*     */   private static ConnectionManager connectionManager;
/*     */   
/*     */   public static Config getConfig() {
/*  83 */     return config;
/*     */   }
/*     */   private static PermissionsManager permissionsManager;
/*     */   private static WebServer webServer;
/*     */   
/*     */   public static DataForwarder getDataForwarder() {
/*  89 */     return dataForwarder;
/*     */   }
/*     */   private static ServerManager serverManager; private static GeofenceManager geofenceManager;
/*     */   private static CalendarManager calendarManager;
/*     */   
/*     */   public static ObjectMapper getObjectMapper() {
/*  95 */     return objectMapper;
/*     */   }
/*     */   private static NotificationManager notificationManager; private static NotificatorManager notificatorManager;
/*     */   private static VelocityEngine velocityEngine;
/*     */   
/*     */   public static IdentityManager getIdentityManager() {
/* 101 */     return identityManager;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static DataManager getDataManager() {
/* 107 */     return dataManager;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static LdapProvider getLdapProvider() {
/* 113 */     return ldapProvider;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static MailManager getMailManager() {
/* 119 */     return mailManager;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static MediaManager getMediaManager() {
/* 125 */     return mediaManager;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static RedisManager getRedisManager() {
/* 131 */     return redisManager;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static UsersManager getUsersManager() {
/* 137 */     return usersManager;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static GroupsManager getGroupsManager() {
/* 143 */     return groupsManager;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static DeviceManager getDeviceManager() {
/* 149 */     return deviceManager;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ConnectionManager getConnectionManager() {
/* 155 */     return connectionManager;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static PermissionsManager getPermissionsManager() {
/* 161 */     return permissionsManager;
/*     */   }
/*     */   
/*     */   public static Geocoder getGeocoder() {
/* 165 */     return (Main.getInjector() != null) ? (Geocoder)Main.getInjector().getInstance(Geocoder.class) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static WebServer getWebServer() {
/* 171 */     return webServer;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ServerManager getServerManager() {
/* 177 */     return serverManager;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static GeofenceManager getGeofenceManager() {
/* 183 */     return geofenceManager;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static CalendarManager getCalendarManager() {
/* 189 */     return calendarManager;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static NotificationManager getNotificationManager() {
/* 195 */     return notificationManager;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static NotificatorManager getNotificatorManager() {
/* 201 */     return notificatorManager;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static VelocityEngine getVelocityEngine() {
/* 207 */     return velocityEngine;
/*     */   }
/*     */   
/* 210 */   private static Client client = ClientBuilder.newClient(); private static EventForwarder eventForwarder; private static AttributesManager attributesManager; private static DriversManager driversManager;
/*     */   
/*     */   public static Client getClient() {
/* 213 */     return client;
/*     */   }
/*     */   private static CommandsManager commandsManager; private static MaintenancesManager maintenancesManager; private static SmsManager smsManager;
/*     */   private static TripsConfig tripsConfig;
/*     */   
/*     */   public static EventForwarder getEventForwarder() {
/* 219 */     return eventForwarder;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static AttributesManager getAttributesManager() {
/* 225 */     return attributesManager;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static DriversManager getDriversManager() {
/* 231 */     return driversManager;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static CommandsManager getCommandsManager() {
/* 237 */     return commandsManager;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static MaintenancesManager getMaintenancesManager() {
/* 243 */     return maintenancesManager;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static SmsManager getSmsManager() {
/* 249 */     return smsManager;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static TripsConfig getTripsConfig() {
/* 255 */     return tripsConfig;
/*     */   }
/*     */   
/*     */   public static TripsConfig initTripsConfig() {
/* 259 */     return new TripsConfig(config
/* 260 */         .getLong("report.trip.minimalTripDistance", 500L), config
/* 261 */         .getLong("report.trip.minimalTripDuration", 300L) * 1000L, config
/* 262 */         .getLong("report.trip.minimalParkingDuration", 300L) * 1000L, config
/* 263 */         .getLong("report.trip.minimalNoDataDuration", 3600L) * 1000L, config
/* 264 */         .getBoolean("report.trip.useIgnition"), config
/* 265 */         .getBoolean("event.motion.processInvalidPositions"), config
/* 266 */         .getDouble("event.motion.speedThreshold", 0.01D));
/*     */   }
/*     */   
/*     */   private static class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {
/*     */     private ObjectMapperContextResolver() {}
/*     */     
/*     */     public ObjectMapper getContext(Class<?> clazz) {
/* 273 */       return Context.objectMapper;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void init(String configFile) throws Exception {
/*     */     try {
/* 281 */       config = new Config(configFile);
/* 282 */     } catch (Exception e) {
/* 283 */       config = new Config();
/* 284 */       Log.setupDefaultLogger();
/* 285 */       throw e;
/*     */     } 
/*     */     
/* 288 */     if (config.getBoolean("logger.enable")) {
/* 289 */       Log.setupLogger(config);
/*     */     }
/*     */     
/* 292 */     objectMapper = new ObjectMapper();
/* 293 */     objectMapper.registerModule((Module)new SanitizerModule());
/* 294 */     objectMapper.registerModule((Module)new JSR353Module());
/* 295 */     objectMapper.setConfig(objectMapper
/* 296 */         .getSerializationConfig().without(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS));
/* 297 */     if (getConfig().getBoolean("mapper.prettyPrintedJson")) {
/* 298 */       objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
/*     */     }
/*     */     
/* 301 */     client = (Client)ClientBuilder.newClient().register(new ObjectMapperContextResolver());
/*     */     
/* 303 */     if (config.hasKey("database.url")) {
/* 304 */       dataManager = new DataManager(config);
/*     */     }
/*     */     
/* 307 */     if (config.getBoolean("ldap.enable")) {
/* 308 */       ldapProvider = new LdapProvider(config);
/*     */     }
/*     */     
/* 311 */     mailManager = new MailManager();
/*     */     
/* 313 */     mediaManager = new MediaManager(config.getString("media.path"));
/*     */     
/* 315 */     if (config.getBoolean("redis.enable")) {
/* 316 */       redisManager = new RedisManager();
/*     */     }
/*     */     
/* 319 */     if (dataManager != null) {
/* 320 */       usersManager = new UsersManager(dataManager);
/* 321 */       groupsManager = new GroupsManager(dataManager);
/* 322 */       deviceManager = new DeviceManager(dataManager);
/*     */     } 
/*     */     
/* 325 */     identityManager = (IdentityManager)deviceManager;
/*     */     
/* 327 */     dataForwarder = new DataForwarder();
/*     */     
/* 329 */     if (config.getBoolean("web.enable")) {
/* 330 */       webServer = new WebServer(config);
/*     */     }
/*     */     
/* 333 */     permissionsManager = new PermissionsManager(dataManager, usersManager);
/*     */     
/* 335 */     connectionManager = new ConnectionManager();
/*     */     
/* 337 */     tripsConfig = initTripsConfig();
/*     */     
/* 339 */     initEventsModule();
/*     */     
/* 341 */     serverManager = new ServerManager();
/*     */     
/* 343 */     if (config.getBoolean("event.forward.enable")) {
/* 344 */       eventForwarder = (EventForwarder)new JsonTypeEventForwarder();
/*     */     }
/*     */     
/* 347 */     attributesManager = new AttributesManager(dataManager);
/*     */     
/* 349 */     driversManager = new DriversManager(dataManager);
/*     */     
/* 351 */     commandsManager = new CommandsManager(dataManager, config.getBoolean("commands.queueing"));
/*     */   }
/*     */ 
/*     */   
/*     */   private static void initEventsModule() {
/*     */     String address;
/* 357 */     if (config.getBoolean("event.enable")) {
/* 358 */       geofenceManager = new GeofenceManager(dataManager);
/* 359 */       calendarManager = new CalendarManager(dataManager);
/*     */     } 
/*     */     
/* 362 */     maintenancesManager = new MaintenancesManager(dataManager);
/* 363 */     notificationManager = new NotificationManager(dataManager);
/* 364 */     notificatorManager = new NotificatorManager();
/* 365 */     Properties velocityProperties = new Properties();
/* 366 */     velocityProperties.setProperty("file.resource.loader.path", 
/* 367 */         getConfig().getString("templates.rootPath", "templates") + "/");
/* 368 */     velocityProperties.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogChute");
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 373 */       address = config.getString("web.address", InetAddress.getLocalHost().getHostAddress());
/* 374 */     } catch (UnknownHostException e) {
/* 375 */       address = "localhost";
/*     */     } 
/*     */     
/* 378 */     String webUrl = URIUtil.newURI("http", address, config.getInteger("web.port", 8082), "", "");
/* 379 */     webUrl = getConfig().getString("web.url", webUrl);
/* 380 */     velocityProperties.setProperty("web.url", webUrl);
/*     */     
/* 382 */     velocityEngine = new VelocityEngine();
/* 383 */     velocityEngine.init(velocityProperties);
/*     */   }
/*     */   
/*     */   public static void init(IdentityManager testIdentityManager, MediaManager testMediaManager) {
/* 387 */     config = new Config();
/* 388 */     objectMapper = new ObjectMapper();
/* 389 */     objectMapper.registerModule((Module)new JSR353Module());
/* 390 */     client = (Client)ClientBuilder.newClient().register(new ObjectMapperContextResolver());
/* 391 */     identityManager = testIdentityManager;
/* 392 */     mediaManager = testMediaManager;
/*     */   }
/*     */   
/*     */   public static <T extends org.traccar.model.BaseModel> BaseObjectManager<T> getManager(Class<T> clazz) {
/* 396 */     if (clazz.equals(Device.class))
/* 397 */       return (BaseObjectManager<T>)deviceManager; 
/* 398 */     if (clazz.equals(Group.class))
/* 399 */       return (BaseObjectManager<T>)groupsManager; 
/* 400 */     if (clazz.equals(User.class))
/* 401 */       return (BaseObjectManager<T>)usersManager; 
/* 402 */     if (clazz.equals(Calendar.class))
/* 403 */       return (BaseObjectManager<T>)calendarManager; 
/* 404 */     if (clazz.equals(Attribute.class))
/* 405 */       return (BaseObjectManager<T>)attributesManager; 
/* 406 */     if (clazz.equals(Geofence.class))
/* 407 */       return (BaseObjectManager<T>)geofenceManager; 
/* 408 */     if (clazz.equals(Driver.class))
/* 409 */       return (BaseObjectManager<T>)driversManager; 
/* 410 */     if (clazz.equals(Command.class))
/* 411 */       return (BaseObjectManager<T>)commandsManager; 
/* 412 */     if (clazz.equals(Maintenance.class))
/* 413 */       return (BaseObjectManager<T>)maintenancesManager; 
/* 414 */     if (clazz.equals(Notification.class)) {
/* 415 */       return (BaseObjectManager<T>)notificationManager;
/*     */     }
/* 417 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\Context.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */