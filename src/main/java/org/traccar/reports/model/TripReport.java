/*     */ package org.traccar.reports.model;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TripReport
/*     */   extends BaseReport
/*     */ {
/*     */   private long startPositionId;
/*     */   private long endPositionId;
/*     */   private double startLat;
/*     */   private double startLon;
/*     */   private double endLat;
/*     */   private double endLon;
/*     */   private Date startTime;
/*     */   private String startAddress;
/*     */   private Date endTime;
/*     */   private String endAddress;
/*     */   private long duration;
/*     */   private String driverUniqueId;
/*     */   private String driverName;
/*     */   
/*     */   public long getStartPositionId() {
/*  26 */     return this.startPositionId;
/*     */   }
/*     */   
/*     */   public void setStartPositionId(long startPositionId) {
/*  30 */     this.startPositionId = startPositionId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getEndPositionId() {
/*  36 */     return this.endPositionId;
/*     */   }
/*     */   
/*     */   public void setEndPositionId(long endPositionId) {
/*  40 */     this.endPositionId = endPositionId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getStartLat() {
/*  46 */     return this.startLat;
/*     */   }
/*     */   
/*     */   public void setStartLat(double startLat) {
/*  50 */     this.startLat = startLat;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getStartLon() {
/*  56 */     return this.startLon;
/*     */   }
/*     */   
/*     */   public void setStartLon(double startLon) {
/*  60 */     this.startLon = startLon;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getEndLat() {
/*  66 */     return this.endLat;
/*     */   }
/*     */   
/*     */   public void setEndLat(double endLat) {
/*  70 */     this.endLat = endLat;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getEndLon() {
/*  76 */     return this.endLon;
/*     */   }
/*     */   
/*     */   public void setEndLon(double endLon) {
/*  80 */     this.endLon = endLon;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getStartTime() {
/*  86 */     return this.startTime;
/*     */   }
/*     */   
/*     */   public void setStartTime(Date startTime) {
/*  90 */     this.startTime = startTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStartAddress() {
/*  96 */     return this.startAddress;
/*     */   }
/*     */   
/*     */   public void setStartAddress(String address) {
/* 100 */     this.startAddress = address;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getEndTime() {
/* 106 */     return this.endTime;
/*     */   }
/*     */   
/*     */   public void setEndTime(Date endTime) {
/* 110 */     this.endTime = endTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEndAddress() {
/* 116 */     return this.endAddress;
/*     */   }
/*     */   
/*     */   public void setEndAddress(String address) {
/* 120 */     this.endAddress = address;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getDuration() {
/* 126 */     return this.duration;
/*     */   }
/*     */   
/*     */   public void setDuration(long duration) {
/* 130 */     this.duration = duration;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDriverUniqueId() {
/* 136 */     return this.driverUniqueId;
/*     */   }
/*     */   
/*     */   public void setDriverUniqueId(String driverUniqueId) {
/* 140 */     this.driverUniqueId = driverUniqueId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDriverName() {
/* 146 */     return this.driverName;
/*     */   }
/*     */   
/*     */   public void setDriverName(String driverName) {
/* 150 */     this.driverName = driverName;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\reports\model\TripReport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */