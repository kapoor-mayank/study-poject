/*    */ package org.traccar.handler.events;
/*    */ 
/*    */ import io.netty.channel.ChannelHandler.Sharable;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import org.traccar.database.IdentityManager;
/*    */ import org.traccar.database.MaintenancesManager;
/*    */ import org.traccar.model.Event;
/*    */ import org.traccar.model.Maintenance;
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
/*    */ 
/*    */ @Sharable
/*    */ public class MaintenanceEventHandler
/*    */   extends BaseEventHandler
/*    */ {
/*    */   private final IdentityManager identityManager;
/*    */   private final MaintenancesManager maintenancesManager;
/*    */   
/*    */   public MaintenanceEventHandler(IdentityManager identityManager, MaintenancesManager maintenancesManager) {
/* 36 */     this.identityManager = identityManager;
/* 37 */     this.maintenancesManager = maintenancesManager;
/*    */   }
/*    */ 
/*    */   
/*    */   protected Map<Event, Position> analyzePosition(Position position) {
/* 42 */     if (this.identityManager.getById(position.getDeviceId()) == null || 
/* 43 */       !this.identityManager.isLatestPosition(position)) {
/* 44 */       return null;
/*    */     }
/*    */     
/* 47 */     Position lastPosition = this.identityManager.getLastPosition(position.getDeviceId());
/* 48 */     if (lastPosition == null) {
/* 49 */       return null;
/*    */     }
/*    */     
/* 52 */     Map<Event, Position> events = new HashMap<>();
/* 53 */     for (Iterator<Long> iterator = this.maintenancesManager.getAllDeviceItems(position.getDeviceId()).iterator(); iterator.hasNext(); ) { long maintenanceId = ((Long)iterator.next()).longValue();
/* 54 */       Maintenance maintenance = (Maintenance)this.maintenancesManager.getById(maintenanceId);
/* 55 */       if (maintenance.getPeriod() != 0.0D) {
/* 56 */         double oldValue = lastPosition.getDouble(maintenance.getType());
/* 57 */         double newValue = position.getDouble(maintenance.getType());
/* 58 */         if (oldValue != 0.0D && newValue != 0.0D && 
/* 59 */           (long)((oldValue - maintenance.getStart()) / maintenance.getPeriod()) < 
/* 60 */           (long)((newValue - maintenance.getStart()) / maintenance.getPeriod())) {
/* 61 */           Event event = new Event("maintenance", position.getDeviceId(), position.getId());
/* 62 */           event.setMaintenanceId(maintenanceId);
/* 63 */           event.set(maintenance.getType(), Double.valueOf(newValue));
/* 64 */           events.put(event, position);
/*    */         } 
/*    */       }  }
/*    */ 
/*    */     
/* 69 */     return events;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\handler\events\MaintenanceEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */