/*    */ package org.traccar.helper;
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
/*    */ 
/*    */ 
/*    */ public final class DistanceCalculator
/*    */ {
/*    */   private static final double EQUATORIAL_EARTH_RADIUS = 6378.137D;
/*    */   private static final double DEG_TO_RAD = 0.017453292519943295D;
/*    */   
/*    */   public static double distance(double lat1, double lon1, double lat2, double lon2) {
/* 28 */     double dlong = (lon2 - lon1) * 0.017453292519943295D;
/* 29 */     double dlat = (lat2 - lat1) * 0.017453292519943295D;
/*    */     
/* 31 */     double a = Math.pow(Math.sin(dlat / 2.0D), 2.0D) + Math.cos(lat1 * 0.017453292519943295D) * Math.cos(lat2 * 0.017453292519943295D) * Math.pow(Math.sin(dlong / 2.0D), 2.0D);
/* 32 */     double c = 2.0D * Math.atan2(Math.sqrt(a), Math.sqrt(1.0D - a));
/* 33 */     double d = 6378.137D * c;
/* 34 */     return d * 1000.0D;
/*    */   }
/*    */ 
/*    */   
/*    */   public static double distanceToLine(double pointLat, double pointLon, double lat1, double lon1, double lat2, double lon2) {
/* 39 */     double d0 = distance(pointLat, pointLon, lat1, lon1);
/* 40 */     double d1 = distance(lat1, lon1, lat2, lon2);
/* 41 */     double d2 = distance(lat2, lon2, pointLat, pointLon);
/* 42 */     if (Math.pow(d0, 2.0D) > Math.pow(d1, 2.0D) + Math.pow(d2, 2.0D)) {
/* 43 */       return d2;
/*    */     }
/* 45 */     if (Math.pow(d2, 2.0D) > Math.pow(d1, 2.0D) + Math.pow(d0, 2.0D)) {
/* 46 */       return d0;
/*    */     }
/* 48 */     double halfP = (d0 + d1 + d2) * 0.5D;
/* 49 */     double area = Math.sqrt(halfP * (halfP - d0) * (halfP - d1) * (halfP - d2));
/* 50 */     return 2.0D * area / d1;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\helper\DistanceCalculator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */