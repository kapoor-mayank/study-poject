/*    */ package org.traccar.handler;
/*    */ 
/*    */ import io.netty.channel.ChannelHandler.Sharable;
/*    */ import org.traccar.BaseDataHandler;
/*    */ import org.traccar.database.IdentityManager;
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
/*    */ public class EngineHoursHandler
/*    */   extends BaseDataHandler
/*    */ {
/*    */   private final IdentityManager identityManager;
/*    */   
/*    */   public EngineHoursHandler(IdentityManager identityManager) {
/* 30 */     this.identityManager = identityManager;
/*    */   }
/*    */ 
/*    */   
/*    */   protected Position handlePosition(Position position) {
/* 35 */     if (!position.getAttributes().containsKey("hours")) {
/* 36 */       Position last = this.identityManager.getLastPosition(position.getDeviceId());
/* 37 */       if (last != null) {
/* 38 */         long hours = last.getLong("hours");
/* 39 */         if (last.getBoolean("ignition") && position.getBoolean("ignition")) {
/* 40 */           hours += position.getFixTime().getTime() - last.getFixTime().getTime();
/*    */         }
/* 42 */         if (hours != 0L) {
/* 43 */           position.set("hours", Long.valueOf(hours));
/*    */         }
/*    */       } 
/*    */     } 
/* 47 */     return position;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\handler\EngineHoursHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */