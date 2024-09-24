/*    */ package org.traccar.model;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonIgnore;
/*    */ import java.text.ParseException;
/*    */ import org.traccar.Context;
/*    */ import org.traccar.database.QueryIgnore;
/*    */ import org.traccar.geofence.GeofenceCircle;
/*    */ import org.traccar.geofence.GeofenceGeometry;
/*    */ import org.traccar.geofence.GeofencePolygon;
/*    */ import org.traccar.geofence.GeofencePolyline;
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
/*    */ public class Geofence
/*    */   extends ScheduledModel
/*    */ {
/*    */   public static final String TYPE_GEOFENCE_CILCLE = "geofenceCircle";
/*    */   public static final String TYPE_GEOFENCE_POLYGON = "geofencePolygon";
/*    */   public static final String TYPE_GEOFENCE_POLYLINE = "geofencePolyline";
/*    */   private String name;
/*    */   private String description;
/*    */   private String area;
/*    */   private GeofenceGeometry geometry;
/*    */   
/*    */   public String getName() {
/* 38 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 42 */     this.name = name;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 48 */     return this.description;
/*    */   }
/*    */   
/*    */   public void setDescription(String description) {
/* 52 */     this.description = description;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getArea() {
/* 58 */     return this.area;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setArea(String area) throws ParseException {
/* 63 */     if (area.startsWith("CIRCLE")) {
/* 64 */       this.geometry = (GeofenceGeometry)new GeofenceCircle(area);
/* 65 */     } else if (area.startsWith("POLYGON")) {
/* 66 */       this.geometry = (GeofenceGeometry)new GeofencePolygon(area);
/* 67 */     } else if (area.startsWith("LINESTRING")) {
/* 68 */       double distance = getDouble("polylineDistance");
/* 69 */       this
/* 70 */         .geometry = (GeofenceGeometry)new GeofencePolyline(area, (distance > 0.0D) ? distance : Context.getConfig().getDouble("geofence.polylineDistance", 25.0D));
/*    */     } else {
/* 72 */       throw new ParseException("Unknown geometry type", 0);
/*    */     } 
/*    */     
/* 75 */     this.area = area;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @QueryIgnore
/*    */   @JsonIgnore
/*    */   public GeofenceGeometry getGeometry() {
/* 83 */     return this.geometry;
/*    */   }
/*    */   
/*    */   @QueryIgnore
/*    */   @JsonIgnore
/*    */   public void setGeometry(GeofenceGeometry geometry) {
/* 89 */     this.area = geometry.toWkt();
/* 90 */     this.geometry = geometry;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\model\Geofence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */