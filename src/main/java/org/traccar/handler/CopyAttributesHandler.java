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
/*    */ public class CopyAttributesHandler
/*    */   extends BaseDataHandler
/*    */ {
/*    */   private IdentityManager identityManager;
/*    */   
/*    */   public CopyAttributesHandler(IdentityManager identityManager) {
/* 30 */     this.identityManager = identityManager;
/*    */   }
/*    */ 
/*    */   
/*    */   protected Position handlePosition(Position position) {
/* 35 */     String attributesString = this.identityManager.lookupAttributeString(position
/* 36 */         .getDeviceId(), "processing.copyAttributes", "", true);
/* 37 */     if (attributesString.isEmpty()) {
/* 38 */       attributesString = "driverUniqueId";
/*    */     } else {
/* 40 */       attributesString = attributesString + ",driverUniqueId";
/*    */     } 
/* 42 */     Position last = this.identityManager.getLastPosition(position.getDeviceId());
/* 43 */     if (last != null) {
/* 44 */       for (String attribute : attributesString.split("[ ,]")) {
/* 45 */         if (last.getAttributes().containsKey(attribute) && !position.getAttributes().containsKey(attribute)) {
/* 46 */           position.getAttributes().put(attribute, last.getAttributes().get(attribute));
/*    */         }
/*    */       } 
/*    */     }
/* 50 */     return position;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\handler\CopyAttributesHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */