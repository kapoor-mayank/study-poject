/*     */ package org.traccar.database;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.sql.SQLException;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.traccar.Context;
/*     */ import org.traccar.model.BaseModel;
/*     */ import org.traccar.model.Calendar;
/*     */ import org.traccar.model.Event;
/*     */ import org.traccar.model.Notification;
/*     */ import org.traccar.model.Position;
/*     */ import org.traccar.model.Typed;
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
/*     */ public class NotificationManager
/*     */   extends ExtendedObjectManager<Notification>
/*     */ {
/*  39 */   private static final Logger LOGGER = LoggerFactory.getLogger(NotificationManager.class);
/*     */   
/*     */   private boolean geocodeOnRequest;
/*     */   
/*     */   public NotificationManager(DataManager dataManager) {
/*  44 */     super(dataManager, Notification.class);
/*  45 */     this.geocodeOnRequest = Context.getConfig().getBoolean("geocoder.onRequest");
/*     */   }
/*     */   
/*     */   private Set<Long> getEffectiveNotifications(long userId, long deviceId, Date time) {
/*  49 */     Set<Long> result = new HashSet<>();
/*  50 */     Set<Long> deviceNotifications = getAllDeviceItems(deviceId);
/*  51 */     for (Iterator<Long> iterator = getUserItems(userId).iterator(); iterator.hasNext(); ) { long itemId = ((Long)iterator.next()).longValue();
/*  52 */       if (getById(itemId).getAlways() || deviceNotifications.contains(Long.valueOf(itemId))) {
/*  53 */         long calendarId = getById(itemId).getCalendarId();
/*  54 */         Calendar calendar = (calendarId != 0L) ? Context.getCalendarManager().getById(calendarId) : null;
/*  55 */         if (calendar == null || calendar.checkMoment(time)) {
/*  56 */           result.add(Long.valueOf(itemId));
/*     */         }
/*     */       }  }
/*     */     
/*  60 */     return result;
/*     */   }
/*     */   
/*     */   public void updateEvent(Event event, Position position) {
/*     */     try {
/*  65 */       getDataManager().addObject((BaseModel)event);
/*  66 */     } catch (SQLException error) {
/*  67 */       LOGGER.warn("Event save error", error);
/*     */     } 
/*     */     
/*  70 */     if (position != null && this.geocodeOnRequest && Context.getGeocoder() != null && position.getAddress() == null) {
/*  71 */       position.setAddress(Context.getGeocoder()
/*  72 */           .getAddress(position.getLatitude(), position.getLongitude(), null));
/*     */     }
/*     */     
/*  75 */     long deviceId = event.getDeviceId();
/*  76 */     Set<Long> users = Context.getPermissionsManager().getDeviceUsers(deviceId);
/*  77 */     Set<Long> usersToForward = null;
/*  78 */     if (Context.getEventForwarder() != null) {
/*  79 */       usersToForward = new HashSet<>();
/*     */     }
/*  81 */     for (Iterator<Long> iterator = users.iterator(); iterator.hasNext(); ) { long userId = ((Long)iterator.next()).longValue();
/*  82 */       if ((event.getGeofenceId() == 0L || 
/*  83 */         Context.getGeofenceManager().checkItemPermission(userId, event.getGeofenceId())) && (event
/*  84 */         .getMaintenanceId() == 0L || 
/*  85 */         Context.getMaintenancesManager().checkItemPermission(userId, event.getMaintenanceId()))) {
/*  86 */         if (usersToForward != null) {
/*  87 */           usersToForward.add(Long.valueOf(userId));
/*     */         }
/*  89 */         Set<String> notificators = new HashSet<>();
/*  90 */         for (null = getEffectiveNotifications(userId, deviceId, event.getServerTime()).iterator(); null.hasNext(); ) { long notificationId = ((Long)null.next()).longValue();
/*  91 */           Notification notification = getById(notificationId);
/*  92 */           if (getById(notificationId).getType().equals(event.getType())) {
/*  93 */             boolean filter = false;
/*  94 */             if (event.getType().equals("alarm")) {
/*  95 */               String alarms = notification.getString("alarms");
/*  96 */               if (alarms == null || !alarms.contains(event.getString("alarm"))) {
/*  97 */                 filter = true;
/*     */               }
/*     */             } 
/* 100 */             if (!filter) {
/* 101 */               notificators.addAll(notification.getNotificatorsTypes());
/*     */             }
/*     */           }  }
/*     */         
/* 105 */         for (String notificator : notificators) {
/* 106 */           Context.getNotificatorManager().getNotificator(notificator).sendAsync(userId, event, position);
/*     */         }
/*     */       }  }
/*     */     
/* 110 */     if (Context.getEventForwarder() != null) {
/* 111 */       Context.getEventForwarder().forwardEvent(event, position, usersToForward);
/*     */     }
/*     */   }
/*     */   
/*     */   public void updateEvents(Map<Event, Position> events) {
/* 116 */     for (Map.Entry<Event, Position> event : events.entrySet()) {
/* 117 */       updateEvent(event.getKey(), event.getValue());
/*     */     }
/*     */   }
/*     */   
/*     */   public Set<Typed> getAllNotificationTypes() {
/* 122 */     Set<Typed> types = new HashSet<>();
/* 123 */     Field[] fields = Event.class.getDeclaredFields();
/* 124 */     for (Field field : fields) {
/* 125 */       if (Modifier.isStatic(field.getModifiers()) && field.getName().startsWith("TYPE_")) {
/*     */         try {
/* 127 */           types.add(new Typed(field.get(null).toString()));
/* 128 */         } catch (IllegalArgumentException|IllegalAccessException error) {
/* 129 */           LOGGER.warn("Get event types error", error);
/*     */         } 
/*     */       }
/*     */     } 
/* 133 */     return types;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\database\NotificationManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */