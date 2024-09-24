/*    */ package org.traccar.handler.events;
/*    */ 
/*    */ import io.netty.channel.ChannelHandler.Sharable;
/*    */ import java.util.Collections;
/*    */ import java.util.Map;
/*    */ import org.traccar.database.IdentityManager;
/*    */ import org.traccar.model.Event;
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
/*    */ 
/*    */ @Sharable
/*    */ public class DriverEventHandler
/*    */   extends BaseEventHandler
/*    */ {
/*    */   private final IdentityManager identityManager;
/*    */   
/*    */   public DriverEventHandler(IdentityManager identityManager) {
/* 33 */     this.identityManager = identityManager;
/*    */   }
/*    */ 
/*    */   
/*    */   protected Map<Event, Position> analyzePosition(Position position) {
/* 38 */     if (!this.identityManager.isLatestPosition(position)) {
/* 39 */       return null;
/*    */     }
/* 41 */     String driverUniqueId = position.getString("driverUniqueId");
/* 42 */     if (driverUniqueId != null) {
/* 43 */       String oldDriverUniqueId = null;
/* 44 */       Position lastPosition = this.identityManager.getLastPosition(position.getDeviceId());
/* 45 */       if (lastPosition != null) {
/* 46 */         oldDriverUniqueId = lastPosition.getString("driverUniqueId");
/*    */       }
/* 48 */       if (!driverUniqueId.equals(oldDriverUniqueId)) {
/* 49 */         Event event = new Event("driverChanged", position.getDeviceId(), position.getId());
/* 50 */         event.set("driverUniqueId", driverUniqueId);
/* 51 */         return Collections.singletonMap(event, position);
/*    */       } 
/*    */     } 
/* 54 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\handler\events\DriverEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */