/*     */ package org.traccar.reports.model;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StopReport
/*     */   extends BaseReport
/*     */ {
/*     */   private long positionId;
/*     */   private double latitude;
/*     */   private double longitude;
/*     */   private Date startTime;
/*     */   private Date endTime;
/*     */   private String address;
/*     */   private long duration;
/*     */   private long engineHours;
/*     */   
/*     */   public long getPositionId() {
/*  26 */     return this.positionId;
/*     */   }
/*     */   
/*     */   public void setPositionId(long positionId) {
/*  30 */     this.positionId = positionId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getLatitude() {
/*  36 */     return this.latitude;
/*     */   }
/*     */   
/*     */   public void setLatitude(double latitude) {
/*  40 */     this.latitude = latitude;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getLongitude() {
/*  46 */     return this.longitude;
/*     */   }
/*     */   
/*     */   public void setLongitude(double longitude) {
/*  50 */     this.longitude = longitude;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getStartTime() {
/*  56 */     return this.startTime;
/*     */   }
/*     */   
/*     */   public void setStartTime(Date startTime) {
/*  60 */     this.startTime = startTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getEndTime() {
/*  66 */     return this.endTime;
/*     */   }
/*     */   
/*     */   public void setEndTime(Date endTime) {
/*  70 */     this.endTime = endTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAddress() {
/*  76 */     return this.address;
/*     */   }
/*     */   
/*     */   public void setAddress(String address) {
/*  80 */     this.address = address;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getDuration() {
/*  86 */     return this.duration;
/*     */   }
/*     */   
/*     */   public void setDuration(long duration) {
/*  90 */     this.duration = duration;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getEngineHours() {
/*  96 */     return this.engineHours;
/*     */   }
/*     */   
/*     */   public void setEngineHours(long engineHours) {
/* 100 */     this.engineHours = engineHours;
/*     */   }
/*     */   
/*     */   public void addEngineHours(long engineHours) {
/* 104 */     this.engineHours += engineHours;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\reports\model\StopReport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */