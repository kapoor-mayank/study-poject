/*    */ package org.traccar.handler;
/*    */ 
/*    */ import io.netty.channel.ChannelHandler.Sharable;
/*    */ import java.math.BigDecimal;
/*    */ import java.math.RoundingMode;
/*    */ import org.traccar.BaseDataHandler;
/*    */ import org.traccar.config.Config;
/*    */ import org.traccar.config.Keys;
/*    */ import org.traccar.database.IdentityManager;
/*    */ import org.traccar.helper.DistanceCalculator;
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
/*    */ 
/*    */ @Sharable
/*    */ public class DistanceHandler
/*    */   extends BaseDataHandler
/*    */ {
/*    */   private final IdentityManager identityManager;
/*    */   private final boolean filter;
/*    */   private final int coordinatesMinError;
/*    */   private final int coordinatesMaxError;
/*    */   
/*    */   public DistanceHandler(Config config, IdentityManager identityManager) {
/* 40 */     this.identityManager = identityManager;
/* 41 */     this.filter = config.getBoolean(Keys.COORDINATES_FILTER);
/* 42 */     this.coordinatesMinError = config.getInteger(Keys.COORDINATES_MIN_ERROR);
/* 43 */     this.coordinatesMaxError = config.getInteger(Keys.COORDINATES_MAX_ERROR);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Position handlePosition(Position position) {
/* 49 */     double distance = 0.0D;
/* 50 */     if (position.getAttributes().containsKey("distance")) {
/* 51 */       distance = position.getDouble("distance");
/*    */     }
/* 53 */     double totalDistance = 0.0D;
/*    */     
/* 55 */     Position last = (this.identityManager != null) ? this.identityManager.getLastPosition(position.getDeviceId()) : null;
/* 56 */     if (last != null) {
/* 57 */       totalDistance = last.getDouble("totalDistance");
/* 58 */       if (!position.getAttributes().containsKey("distance")) {
/* 59 */         distance = DistanceCalculator.distance(position
/* 60 */             .getLatitude(), position.getLongitude(), last
/* 61 */             .getLatitude(), last.getLongitude());
/* 62 */         distance = BigDecimal.valueOf(distance).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
/*    */       } 
/* 64 */       if (this.filter && last.getValid() && last.getLatitude() != 0.0D && last.getLongitude() != 0.0D) {
/* 65 */         boolean satisfiesMin = (this.coordinatesMinError == 0 || distance > this.coordinatesMinError);
/*    */         
/* 67 */         boolean satisfiesMax = (this.coordinatesMaxError == 0 || distance < this.coordinatesMaxError || position.getValid());
/* 68 */         if (!satisfiesMin || !satisfiesMax) {
/* 69 */           position.setLatitude(last.getLatitude());
/* 70 */           position.setLongitude(last.getLongitude());
/* 71 */           distance = 0.0D;
/*    */         } 
/*    */       } 
/*    */     } 
/* 75 */     position.set("distance", Double.valueOf(distance));
/* 76 */     totalDistance = BigDecimal.valueOf(totalDistance + distance).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
/* 77 */     position.set("totalDistance", Double.valueOf(totalDistance));
/*    */     
/* 79 */     return position;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\handler\DistanceHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */