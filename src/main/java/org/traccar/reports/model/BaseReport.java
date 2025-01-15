/*     */ package org.traccar.reports.model;
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
/*     */ public class BaseReport
/*     */ {
/*     */   private long deviceId;
/*     */   private String deviceName;
/*     */   private double distance;
/*     */   private double averageSpeed;
/*     */   private double maxSpeed;
/*     */   private double spentFuel;
/*     */   private double startOdometer;
/*     */   private double endOdometer;
/*     */   
/*     */   public long getDeviceId() {
/*  24 */     return this.deviceId;
/*     */   }
/*     */   
/*     */   public void setDeviceId(long deviceId) {
/*  28 */     this.deviceId = deviceId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDeviceName() {
/*  34 */     return this.deviceName;
/*     */   }
/*     */   
/*     */   public void setDeviceName(String deviceName) {
/*  38 */     this.deviceName = deviceName;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getDistance() {
/*  44 */     return this.distance;
/*     */   }
/*     */   
/*     */   public void setDistance(double distance) {
/*  48 */     this.distance = distance;
/*     */   }
/*     */   
/*     */   public void addDistance(double distance) {
/*  52 */     this.distance += distance;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getAverageSpeed() {
/*  58 */     return this.averageSpeed;
/*     */   }
/*     */   
/*     */   public void setAverageSpeed(Double averageSpeed) {
/*  62 */     this.averageSpeed = averageSpeed.doubleValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getMaxSpeed() {
/*  68 */     return this.maxSpeed;
/*     */   }
/*     */   
/*     */   public void setMaxSpeed(double maxSpeed) {
/*  72 */     if (maxSpeed > this.maxSpeed) {
/*  73 */       this.maxSpeed = maxSpeed;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getSpentFuel() {
/*  80 */     return this.spentFuel;
/*     */   }
/*     */   
/*     */   public void setSpentFuel(double spentFuel) {
/*  84 */     this.spentFuel = spentFuel;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getStartOdometer() {
/*  90 */     return this.startOdometer;
/*     */   }
/*     */   
/*     */   public void setStartOdometer(double startOdometer) {
/*  94 */     this.startOdometer = startOdometer;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getEndOdometer() {
/*  99 */     return this.endOdometer;
/*     */   }
/*     */   
/*     */   public void setEndOdometer(double endOdometer) {
/* 103 */     this.endOdometer = endOdometer;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\reports\model\BaseReport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */