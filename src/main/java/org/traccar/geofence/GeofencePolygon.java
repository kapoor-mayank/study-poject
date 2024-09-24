/*     */ package org.traccar.geofence;
/*     */ 
/*     */ import java.text.ParseException;
/*     */ import java.util.ArrayList;
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
/*     */ public class GeofencePolygon
/*     */   extends GeofenceGeometry
/*     */ {
/*     */   private ArrayList<GeofenceGeometry.Coordinate> coordinates;
/*     */   private double[] constant;
/*     */   private double[] multiple;
/*     */   
/*     */   public GeofencePolygon() {}
/*     */   
/*     */   public GeofencePolygon(String wkt) throws ParseException {
/*  27 */     fromWkt(wkt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean needNormalize = false;
/*     */ 
/*     */ 
/*     */   
/*     */   private void precalc() {
/*  38 */     if (this.coordinates == null) {
/*     */       return;
/*     */     }
/*     */     
/*  42 */     int polyCorners = this.coordinates.size();
/*     */     
/*  44 */     int j = polyCorners - 1;
/*     */     
/*  46 */     if (this.constant != null) {
/*  47 */       this.constant = null;
/*     */     }
/*  49 */     if (this.multiple != null) {
/*  50 */       this.multiple = null;
/*     */     }
/*     */     
/*  53 */     this.constant = new double[polyCorners];
/*  54 */     this.multiple = new double[polyCorners];
/*     */     
/*  56 */     boolean hasNegative = false;
/*  57 */     boolean hasPositive = false; int i;
/*  58 */     for (i = 0; i < polyCorners; i++) {
/*  59 */       if (((GeofenceGeometry.Coordinate)this.coordinates.get(i)).getLon() > 90.0D) {
/*  60 */         hasPositive = true;
/*  61 */       } else if (((GeofenceGeometry.Coordinate)this.coordinates.get(i)).getLon() < -90.0D) {
/*  62 */         hasNegative = true;
/*     */       } 
/*     */     } 
/*  65 */     this.needNormalize = (hasPositive && hasNegative);
/*     */     
/*  67 */     for (i = 0; i < polyCorners; j = i++) {
/*  68 */       if (normalizeLon(((GeofenceGeometry.Coordinate)this.coordinates.get(j)).getLon()) == normalizeLon(((GeofenceGeometry.Coordinate)this.coordinates.get(i)).getLon())) {
/*  69 */         this.constant[i] = ((GeofenceGeometry.Coordinate)this.coordinates.get(i)).getLat();
/*  70 */         this.multiple[i] = 0.0D;
/*     */       } else {
/*  72 */         this.constant[i] = ((GeofenceGeometry.Coordinate)this.coordinates.get(i)).getLat() - 
/*  73 */           normalizeLon(((GeofenceGeometry.Coordinate)this.coordinates.get(i)).getLon()) * ((GeofenceGeometry.Coordinate)this.coordinates.get(j)).getLat() / (
/*  74 */           normalizeLon(((GeofenceGeometry.Coordinate)this.coordinates.get(j)).getLon()) - normalizeLon(((GeofenceGeometry.Coordinate)this.coordinates.get(i)).getLon())) + 
/*  75 */           normalizeLon(((GeofenceGeometry.Coordinate)this.coordinates.get(i)).getLon()) * ((GeofenceGeometry.Coordinate)this.coordinates.get(i)).getLat() / (
/*  76 */           normalizeLon(((GeofenceGeometry.Coordinate)this.coordinates.get(j)).getLon()) - normalizeLon(((GeofenceGeometry.Coordinate)this.coordinates.get(i)).getLon()));
/*  77 */         this.multiple[i] = (((GeofenceGeometry.Coordinate)this.coordinates.get(j)).getLat() - ((GeofenceGeometry.Coordinate)this.coordinates.get(i)).getLat()) / (
/*  78 */           normalizeLon(((GeofenceGeometry.Coordinate)this.coordinates.get(j)).getLon()) - normalizeLon(((GeofenceGeometry.Coordinate)this.coordinates.get(i)).getLon()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private double normalizeLon(double lon) {
/*  84 */     if (this.needNormalize && lon < -90.0D) {
/*  85 */       return lon + 360.0D;
/*     */     }
/*  87 */     return lon;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsPoint(double latitude, double longitude) {
/*  93 */     int k, polyCorners = this.coordinates.size();
/*     */     
/*  95 */     int j = polyCorners - 1;
/*  96 */     double longitudeNorm = normalizeLon(longitude);
/*  97 */     boolean oddNodes = false;
/*     */     
/*  99 */     for (int i = 0; i < polyCorners; j = i++) {
/* 100 */       if ((normalizeLon(((GeofenceGeometry.Coordinate)this.coordinates.get(i)).getLon()) < longitudeNorm && 
/* 101 */         normalizeLon(((GeofenceGeometry.Coordinate)this.coordinates.get(j)).getLon()) >= longitudeNorm) || (
/* 102 */         normalizeLon(((GeofenceGeometry.Coordinate)this.coordinates.get(j)).getLon()) < longitudeNorm && 
/* 103 */         normalizeLon(((GeofenceGeometry.Coordinate)this.coordinates.get(i)).getLon()) >= longitudeNorm)) {
/* 104 */         k = oddNodes ^ ((longitudeNorm * this.multiple[i] + this.constant[i] < latitude) ? 1 : 0);
/*     */       }
/*     */     } 
/* 107 */     return k;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toWkt() {
/* 112 */     StringBuilder buf = new StringBuilder();
/* 113 */     buf.append("POLYGON ((");
/* 114 */     for (GeofenceGeometry.Coordinate coordinate : this.coordinates) {
/* 115 */       buf.append(String.valueOf(coordinate.getLat()));
/* 116 */       buf.append(" ");
/* 117 */       buf.append(String.valueOf(coordinate.getLon()));
/* 118 */       buf.append(", ");
/*     */     } 
/* 120 */     return buf.substring(0, buf.length() - 2) + "))";
/*     */   }
/*     */ 
/*     */   
/*     */   public void fromWkt(String wkt) throws ParseException {
/* 125 */     if (this.coordinates == null) {
/* 126 */       this.coordinates = new ArrayList<>();
/*     */     } else {
/* 128 */       this.coordinates.clear();
/*     */     } 
/*     */     
/* 131 */     if (!wkt.startsWith("POLYGON")) {
/* 132 */       throw new ParseException("Mismatch geometry type", 0);
/*     */     }
/* 134 */     String content = wkt.substring(wkt.indexOf("((") + 2, wkt.indexOf("))"));
/* 135 */     if (content.isEmpty()) {
/* 136 */       throw new ParseException("No content", 0);
/*     */     }
/* 138 */     String[] commaTokens = content.split(",");
/* 139 */     if (commaTokens.length < 3) {
/* 140 */       throw new ParseException("Not valid content", 0);
/*     */     }
/*     */     
/* 143 */     for (String commaToken : commaTokens) {
/* 144 */       String[] tokens = commaToken.trim().split("\\s");
/* 145 */       if (tokens.length != 2) {
/* 146 */         throw new ParseException("Here must be two coordinates: " + commaToken, 0);
/*     */       }
/* 148 */       GeofenceGeometry.Coordinate coordinate = new GeofenceGeometry.Coordinate();
/*     */       try {
/* 150 */         coordinate.setLat(Double.parseDouble(tokens[0]));
/* 151 */       } catch (NumberFormatException e) {
/* 152 */         throw new ParseException(tokens[0] + " is not a double", 0);
/*     */       } 
/*     */       try {
/* 155 */         coordinate.setLon(Double.parseDouble(tokens[1]));
/* 156 */       } catch (NumberFormatException e) {
/* 157 */         throw new ParseException(tokens[1] + " is not a double", 0);
/*     */       } 
/* 159 */       this.coordinates.add(coordinate);
/*     */     } 
/* 161 */     precalc();
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\geofence\GeofencePolygon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */