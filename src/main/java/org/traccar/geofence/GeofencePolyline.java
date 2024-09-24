/*     */ package org.traccar.geofence;
/*     */ 
/*     */ import java.text.ParseException;
/*     */ import java.util.ArrayList;
/*     */ import org.traccar.helper.DistanceCalculator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GeofencePolyline
/*     */   extends GeofenceGeometry
/*     */ {
/*     */   private ArrayList<GeofenceGeometry.Coordinate> coordinates;
/*     */   private double distance;
/*     */   
/*     */   public GeofencePolyline() {}
/*     */   
/*     */   public GeofencePolyline(String wkt, double distance) throws ParseException {
/*  33 */     fromWkt(wkt);
/*  34 */     this.distance = distance;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsPoint(double latitude, double longitude) {
/*  39 */     for (int i = 1; i < this.coordinates.size(); i++) {
/*  40 */       if (DistanceCalculator.distanceToLine(latitude, longitude, ((GeofenceGeometry.Coordinate)this.coordinates
/*  41 */           .get(i - 1)).getLat(), ((GeofenceGeometry.Coordinate)this.coordinates.get(i - 1)).getLon(), ((GeofenceGeometry.Coordinate)this.coordinates
/*  42 */           .get(i)).getLat(), ((GeofenceGeometry.Coordinate)this.coordinates.get(i)).getLon()) <= this.distance) {
/*  43 */         return true;
/*     */       }
/*     */     } 
/*  46 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toWkt() {
/*  51 */     StringBuilder buf = new StringBuilder();
/*  52 */     buf.append("LINESTRING (");
/*  53 */     for (GeofenceGeometry.Coordinate coordinate : this.coordinates) {
/*  54 */       buf.append(String.valueOf(coordinate.getLat()));
/*  55 */       buf.append(" ");
/*  56 */       buf.append(String.valueOf(coordinate.getLon()));
/*  57 */       buf.append(", ");
/*     */     } 
/*  59 */     return buf.substring(0, buf.length() - 2) + ")";
/*     */   }
/*     */ 
/*     */   
/*     */   public void fromWkt(String wkt) throws ParseException {
/*  64 */     if (this.coordinates == null) {
/*  65 */       this.coordinates = new ArrayList<>();
/*     */     } else {
/*  67 */       this.coordinates.clear();
/*     */     } 
/*     */     
/*  70 */     if (!wkt.startsWith("LINESTRING")) {
/*  71 */       throw new ParseException("Mismatch geometry type", 0);
/*     */     }
/*  73 */     String content = wkt.substring(wkt.indexOf("(") + 1, wkt.indexOf(")"));
/*  74 */     if (content.isEmpty()) {
/*  75 */       throw new ParseException("No content", 0);
/*     */     }
/*  77 */     String[] commaTokens = content.split(",");
/*  78 */     if (commaTokens.length < 2) {
/*  79 */       throw new ParseException("Not valid content", 0);
/*     */     }
/*     */     
/*  82 */     for (String commaToken : commaTokens) {
/*  83 */       String[] tokens = commaToken.trim().split("\\s");
/*  84 */       if (tokens.length != 2) {
/*  85 */         throw new ParseException("Here must be two coordinates: " + commaToken, 0);
/*     */       }
/*  87 */       GeofenceGeometry.Coordinate coordinate = new GeofenceGeometry.Coordinate();
/*     */       try {
/*  89 */         coordinate.setLat(Double.parseDouble(tokens[0]));
/*  90 */       } catch (NumberFormatException e) {
/*  91 */         throw new ParseException(tokens[0] + " is not a double", 0);
/*     */       } 
/*     */       try {
/*  94 */         coordinate.setLon(Double.parseDouble(tokens[1]));
/*  95 */       } catch (NumberFormatException e) {
/*  96 */         throw new ParseException(tokens[1] + " is not a double", 0);
/*     */       } 
/*  98 */       this.coordinates.add(coordinate);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDistance(double distance) {
/* 104 */     this.distance = distance;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\geofence\GeofencePolyline.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */