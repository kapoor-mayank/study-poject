/*    */ package org.traccar.geofence;
/*    */ 
/*    */ import java.text.DecimalFormat;
/*    */ import java.text.ParseException;
/*    */ import org.traccar.helper.DistanceCalculator;
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
/*    */ public class GeofenceCircle
/*    */   extends GeofenceGeometry
/*    */ {
/*    */   private double centerLatitude;
/*    */   private double centerLongitude;
/*    */   private double radius;
/*    */   
/*    */   public GeofenceCircle() {}
/*    */   
/*    */   public GeofenceCircle(String wkt) throws ParseException {
/* 33 */     fromWkt(wkt);
/*    */   }
/*    */   
/*    */   public GeofenceCircle(double latitude, double longitude, double radius) {
/* 37 */     this.centerLatitude = latitude;
/* 38 */     this.centerLongitude = longitude;
/* 39 */     this.radius = radius;
/*    */   }
/*    */   
/*    */   public double distanceFromCenter(double latitude, double longitude) {
/* 43 */     return DistanceCalculator.distance(this.centerLatitude, this.centerLongitude, latitude, longitude);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean containsPoint(double latitude, double longitude) {
/* 48 */     return (distanceFromCenter(latitude, longitude) <= this.radius);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toWkt() {
/* 53 */     String wkt = "";
/* 54 */     wkt = "CIRCLE (";
/* 55 */     wkt = wkt + String.valueOf(this.centerLatitude);
/* 56 */     wkt = wkt + " ";
/* 57 */     wkt = wkt + String.valueOf(this.centerLongitude);
/* 58 */     wkt = wkt + ", ";
/* 59 */     DecimalFormat format = new DecimalFormat("0.#");
/* 60 */     wkt = wkt + format.format(this.radius);
/* 61 */     wkt = wkt + ")";
/* 62 */     return wkt;
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromWkt(String wkt) throws ParseException {
/* 67 */     if (!wkt.startsWith("CIRCLE")) {
/* 68 */       throw new ParseException("Mismatch geometry type", 0);
/*    */     }
/* 70 */     String content = wkt.substring(wkt.indexOf("(") + 1, wkt.indexOf(")"));
/* 71 */     if (content == null || content.equals("")) {
/* 72 */       throw new ParseException("No content", 0);
/*    */     }
/* 74 */     String[] commaTokens = content.split(",");
/* 75 */     if (commaTokens.length != 2) {
/* 76 */       throw new ParseException("Not valid content", 0);
/*    */     }
/* 78 */     String[] tokens = commaTokens[0].split("\\s");
/* 79 */     if (tokens.length != 2) {
/* 80 */       throw new ParseException("Too much or less coordinates", 0);
/*    */     }
/*    */     try {
/* 83 */       this.centerLatitude = Double.parseDouble(tokens[0]);
/* 84 */     } catch (NumberFormatException e) {
/* 85 */       throw new ParseException(tokens[0] + " is not a double", 0);
/*    */     } 
/*    */     try {
/* 88 */       this.centerLongitude = Double.parseDouble(tokens[1]);
/* 89 */     } catch (NumberFormatException e) {
/* 90 */       throw new ParseException(tokens[1] + " is not a double", 0);
/*    */     } 
/*    */     try {
/* 93 */       this.radius = Double.parseDouble(commaTokens[1]);
/* 94 */     } catch (NumberFormatException e) {
/* 95 */       throw new ParseException(commaTokens[1] + " is not a double", 0);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\geofence\GeofenceCircle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */