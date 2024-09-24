/*    */ package org.traccar.handler;
/*    */ 
/*    */ import io.netty.channel.ChannelHandler.Sharable;
/*    */ import org.traccar.BaseDataHandler;
/*    */ import org.traccar.config.Config;
/*    */ import org.traccar.config.Keys;
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
/*    */ public class HemisphereHandler
/*    */   extends BaseDataHandler
/*    */ {
/*    */   private int latitudeFactor;
/*    */   private int longitudeFactor;
/*    */   
/*    */   public HemisphereHandler(Config config) {
/* 31 */     String latitudeHemisphere = config.getString(Keys.LOCATION_LATITUDE_HEMISPHERE);
/* 32 */     if (latitudeHemisphere != null) {
/* 33 */       if (latitudeHemisphere.equalsIgnoreCase("N")) {
/* 34 */         this.latitudeFactor = 1;
/* 35 */       } else if (latitudeHemisphere.equalsIgnoreCase("S")) {
/* 36 */         this.latitudeFactor = -1;
/*    */       } 
/*    */     }
/* 39 */     String longitudeHemisphere = config.getString(Keys.LOCATION_LATITUDE_HEMISPHERE);
/* 40 */     if (longitudeHemisphere != null) {
/* 41 */       if (longitudeHemisphere.equalsIgnoreCase("E")) {
/* 42 */         this.longitudeFactor = 1;
/* 43 */       } else if (longitudeHemisphere.equalsIgnoreCase("W")) {
/* 44 */         this.longitudeFactor = -1;
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected Position handlePosition(Position position) {
/* 51 */     if (this.latitudeFactor != 0) {
/* 52 */       position.setLatitude(Math.abs(position.getLatitude()) * this.latitudeFactor);
/*    */     }
/* 54 */     if (this.longitudeFactor != 0) {
/* 55 */       position.setLongitude(Math.abs(position.getLongitude()) * this.longitudeFactor);
/*    */     }
/* 57 */     return position;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\handler\HemisphereHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */