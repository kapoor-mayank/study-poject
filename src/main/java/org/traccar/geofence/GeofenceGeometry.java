/*    */ package org.traccar.geofence;
/*    */ 
/*    */ import java.text.ParseException;
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
/*    */ public abstract class GeofenceGeometry
/*    */ {
/*    */   public abstract boolean containsPoint(double paramDouble1, double paramDouble2);
/*    */   
/*    */   public abstract String toWkt();
/*    */   
/*    */   public abstract void fromWkt(String paramString) throws ParseException;
/*    */   
/*    */   public static class Coordinate
/*    */   {
/*    */     private double lat;
/*    */     private double lon;
/*    */     
/*    */     public double getLat() {
/* 34 */       return this.lat;
/*    */     }
/*    */     
/*    */     public void setLat(double lat) {
/* 38 */       this.lat = lat;
/*    */     }
/*    */     
/*    */     public double getLon() {
/* 42 */       return this.lon;
/*    */     }
/*    */     
/*    */     public void setLon(double lon) {
/* 46 */       this.lon = lon;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\geofence\GeofenceGeometry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */