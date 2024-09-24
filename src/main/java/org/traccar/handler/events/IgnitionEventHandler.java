/*    */ package org.traccar.handler.events;
/*    */ 
/*    */ import io.netty.channel.ChannelHandler.Sharable;
/*    */ import java.util.Collections;
/*    */ import java.util.Map;
/*    */ import org.traccar.database.IdentityManager;
/*    */ import org.traccar.model.Device;
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
/*    */ public class IgnitionEventHandler
/*    */   extends BaseEventHandler
/*    */ {
/*    */   private final IdentityManager identityManager;
/*    */   
/*    */   public IgnitionEventHandler(IdentityManager identityManager) {
/* 34 */     this.identityManager = identityManager;
/*    */   }
/*    */ 
/*    */   
/*    */   protected Map<Event, Position> analyzePosition(Position position) {
/* 39 */     Device device = this.identityManager.getById(position.getDeviceId());
/* 40 */     if (device == null || !this.identityManager.isLatestPosition(position)) {
/* 41 */       return null;
/*    */     }
/*    */     
/* 44 */     Map<Event, Position> result = null;
/*    */     
/* 46 */     if (position.getAttributes().containsKey("ignition")) {
/* 47 */       boolean ignition = position.getBoolean("ignition");
/*    */       
/* 49 */       Position lastPosition = this.identityManager.getLastPosition(position.getDeviceId());
/* 50 */       if (lastPosition != null && lastPosition.getAttributes().containsKey("ignition")) {
/* 51 */         boolean oldIgnition = lastPosition.getBoolean("ignition");
/*    */         
/* 53 */         if (ignition && !oldIgnition) {
/* 54 */           result = Collections.singletonMap(new Event("ignitionOn", position
/* 55 */                 .getDeviceId(), position.getId()), position);
/* 56 */         } else if (!ignition && oldIgnition) {
/* 57 */           result = Collections.singletonMap(new Event("ignitionOff", position
/* 58 */                 .getDeviceId(), position.getId()), position);
/*    */         } 
/*    */       } 
/*    */     } 
/* 62 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\handler\events\IgnitionEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */