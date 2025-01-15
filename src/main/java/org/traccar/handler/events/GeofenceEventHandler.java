/*    */ package org.traccar.handler.events;
/*    */ 
/*    */ import io.netty.channel.ChannelHandler.Sharable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.traccar.database.CalendarManager;
/*    */ import org.traccar.database.GeofenceManager;
/*    */ import org.traccar.database.IdentityManager;
/*    */ import org.traccar.model.Calendar;
/*    */ import org.traccar.model.Device;
/*    */ import org.traccar.model.Event;
/*    */ import org.traccar.model.Geofence;
/*    */ import org.traccar.model.Position;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Sharable
/*    */ public class GeofenceEventHandler
/*    */   extends BaseEventHandler
/*    */ {
/*    */   private final IdentityManager identityManager;
/*    */   private final GeofenceManager geofenceManager;
/*    */   private final CalendarManager calendarManager;
/*    */   
/*    */   public GeofenceEventHandler(IdentityManager identityManager, GeofenceManager geofenceManager, CalendarManager calendarManager) {
/* 41 */     this.identityManager = identityManager;
/* 42 */     this.geofenceManager = geofenceManager;
/* 43 */     this.calendarManager = calendarManager;
/*    */   }
/*    */ 
/*    */   
/*    */   protected Map<Event, Position> analyzePosition(Position position) {
/* 48 */     Device device = this.identityManager.getById(position.getDeviceId());
/* 49 */     if (device == null) {
/* 50 */       return null;
/*    */     }
/* 52 */     if (!this.identityManager.isLatestPosition(position) || !position.getValid()) {
/* 53 */       return null;
/*    */     }
/*    */     
/* 56 */     List<Long> currentGeofences = this.geofenceManager.getCurrentDeviceGeofences(position);
/* 57 */     List<Long> oldGeofences = new ArrayList<>();
/* 58 */     if (device.getGeofenceIds() != null) {
/* 59 */       oldGeofences.addAll(device.getGeofenceIds());
/*    */     }
/* 61 */     List<Long> newGeofences = new ArrayList<>(currentGeofences);
/* 62 */     newGeofences.removeAll(oldGeofences);
/* 63 */     oldGeofences.removeAll(currentGeofences);
/*    */     
/* 65 */     device.setGeofenceIds(currentGeofences);
/*    */     
/* 67 */     Map<Event, Position> events = new HashMap<>(); Iterator<Long> iterator;
/* 68 */     for (iterator = oldGeofences.iterator(); iterator.hasNext(); ) { long geofenceId = ((Long)iterator.next()).longValue();
/* 69 */       long calendarId = ((Geofence)this.geofenceManager.getById(geofenceId)).getCalendarId();
/* 70 */       Calendar calendar = (calendarId != 0L) ? (Calendar)this.calendarManager.getById(calendarId) : null;
/* 71 */       if (calendar == null || calendar.checkMoment(position.getFixTime())) {
/* 72 */         Event event = new Event("geofenceExit", position.getDeviceId(), position.getId());
/* 73 */         event.setGeofenceId(geofenceId);
/* 74 */         events.put(event, position);
/*    */       }  }
/*    */     
/* 77 */     for (iterator = newGeofences.iterator(); iterator.hasNext(); ) { long geofenceId = ((Long)iterator.next()).longValue();
/* 78 */       long calendarId = ((Geofence)this.geofenceManager.getById(geofenceId)).getCalendarId();
/* 79 */       Calendar calendar = (calendarId != 0L) ? (Calendar)this.calendarManager.getById(calendarId) : null;
/* 80 */       if (calendar == null || calendar.checkMoment(position.getFixTime())) {
/* 81 */         Event event = new Event("geofenceEnter", position.getDeviceId(), position.getId());
/* 82 */         event.setGeofenceId(geofenceId);
/* 83 */         events.put(event, position);
/*    */       }  }
/*    */     
/* 86 */     return events;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\handler\events\GeofenceEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */