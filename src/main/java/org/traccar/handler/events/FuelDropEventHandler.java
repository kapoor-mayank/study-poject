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
/*    */ public class FuelDropEventHandler
/*    */   extends BaseEventHandler
/*    */ {
/*    */   public static final String ATTRIBUTE_FUEL_DROP_THRESHOLD = "fuelDropThreshold";
/*    */   private final IdentityManager identityManager;
/*    */   
/*    */   public FuelDropEventHandler(IdentityManager identityManager) {
/* 35 */     this.identityManager = identityManager;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Map<Event, Position> analyzePosition(Position position) {
/* 41 */     Device device = this.identityManager.getById(position.getDeviceId());
/* 42 */     if (device == null) {
/* 43 */       return null;
/*    */     }
/* 45 */     if (!this.identityManager.isLatestPosition(position)) {
/* 46 */       return null;
/*    */     }
/*    */ 
/*    */     
/* 50 */     double fuelDropThreshold = this.identityManager.lookupAttributeDouble(device.getId(), "fuelDropThreshold", 0.0D, false);
/*    */     
/* 52 */     if (fuelDropThreshold > 0.0D) {
/* 53 */       Position lastPosition = this.identityManager.getLastPosition(position.getDeviceId());
/* 54 */       if (position.getAttributes().containsKey("fuel") && lastPosition != null && lastPosition
/* 55 */         .getAttributes().containsKey("fuel")) {
/*    */ 
/*    */         
/* 58 */         double drop = lastPosition.getDouble("fuel") - position.getDouble("fuel");
/* 59 */         if (drop >= fuelDropThreshold) {
/* 60 */           Event event = new Event("deviceFuelDrop", position.getDeviceId(), position.getId());
/* 61 */           event.set("fuelDropThreshold", Double.valueOf(fuelDropThreshold));
/* 62 */           return Collections.singletonMap(event, position);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 67 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\handler\events\FuelDropEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */