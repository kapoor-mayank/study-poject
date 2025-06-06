/*    */ package org.traccar.handler.events;
/*    */ 
/*    */ import io.netty.channel.ChannelHandler.Sharable;
/*    */ import java.util.Collections;
/*    */ import java.util.Map;
/*    */ import org.traccar.config.Config;
/*    */ import org.traccar.config.Keys;
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
/*    */ @Sharable
/*    */ public class AlertEventHandler
/*    */   extends BaseEventHandler
/*    */ {
/*    */   private final IdentityManager identityManager;
/*    */   private final boolean ignoreDuplicateAlerts;
/*    */   
/*    */   public AlertEventHandler(Config config, IdentityManager identityManager) {
/* 35 */     this.identityManager = identityManager;
/* 36 */     this.ignoreDuplicateAlerts = config.getBoolean(Keys.EVENT_IGNORE_DUPLICATE_ALERTS);
/*    */   }
/*    */ 
/*    */   
/*    */   protected Map<Event, Position> analyzePosition(Position position) {
/* 41 */     Object alarm = position.getAttributes().get("alarm");
/* 42 */     if (alarm != null) {
/* 43 */       boolean ignoreAlert = false;
/* 44 */       if (this.ignoreDuplicateAlerts) {
/* 45 */         Position lastPosition = this.identityManager.getLastPosition(position.getDeviceId());
/* 46 */         if (lastPosition != null && alarm.equals(lastPosition.getAttributes().get("alarm"))) {
/* 47 */           ignoreAlert = true;
/*    */         }
/*    */       } 
/* 50 */       if (!ignoreAlert) {
/* 51 */         Event event = new Event("alarm", position.getDeviceId(), position.getId());
/* 52 */         event.set("alarm", (String)alarm);
/* 53 */         return Collections.singletonMap(event, position);
/*    */       } 
/*    */     } 
/* 56 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\handler\events\AlertEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */