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
/*     */ 
/*     */ public class TripsConfig
/*     */ {
/*     */   private double minimalTripDistance;
/*     */   private long minimalTripDuration;
/*     */   private long minimalParkingDuration;
/*     */   private long minimalNoDataDuration;
/*     */   private boolean useIgnition;
/*     */   private boolean processInvalidPositions;
/*     */   private double speedThreshold;
/*     */   
/*     */   public TripsConfig() {}
/*     */   
/*     */   public TripsConfig(double minimalTripDistance, long minimalTripDuration, long minimalParkingDuration, long minimalNoDataDuration, boolean useIgnition, boolean processInvalidPositions, double speedThreshold) {
/*  26 */     this.minimalTripDistance = minimalTripDistance;
/*  27 */     this.minimalTripDuration = minimalTripDuration;
/*  28 */     this.minimalParkingDuration = minimalParkingDuration;
/*  29 */     this.minimalNoDataDuration = minimalNoDataDuration;
/*  30 */     this.useIgnition = useIgnition;
/*  31 */     this.processInvalidPositions = processInvalidPositions;
/*  32 */     this.speedThreshold = speedThreshold;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getMinimalTripDistance() {
/*  38 */     return this.minimalTripDistance;
/*     */   }
/*     */   
/*     */   public void setMinimalTripDistance(double minimalTripDistance) {
/*  42 */     this.minimalTripDistance = minimalTripDistance;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getMinimalTripDuration() {
/*  48 */     return this.minimalTripDuration;
/*     */   }
/*     */   
/*     */   public void setMinimalTripDuration(long minimalTripDuration) {
/*  52 */     this.minimalTripDuration = minimalTripDuration;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getMinimalParkingDuration() {
/*  58 */     return this.minimalParkingDuration;
/*     */   }
/*     */   
/*     */   public void setMinimalParkingDuration(long minimalParkingDuration) {
/*  62 */     this.minimalParkingDuration = minimalParkingDuration;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getMinimalNoDataDuration() {
/*  68 */     return this.minimalNoDataDuration;
/*     */   }
/*     */   
/*     */   public void setMinimalNoDataDuration(long minimalNoDataDuration) {
/*  72 */     this.minimalNoDataDuration = minimalNoDataDuration;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getUseIgnition() {
/*  78 */     return this.useIgnition;
/*     */   }
/*     */   
/*     */   public void setUseIgnition(boolean useIgnition) {
/*  82 */     this.useIgnition = useIgnition;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getProcessInvalidPositions() {
/*  88 */     return this.processInvalidPositions;
/*     */   }
/*     */   
/*     */   public void setProcessInvalidPositions(boolean processInvalidPositions) {
/*  92 */     this.processInvalidPositions = processInvalidPositions;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getSpeedThreshold() {
/*  98 */     return this.speedThreshold;
/*     */   }
/*     */   
/*     */   public void setSpeedThreshold(double speedThreshold) {
/* 102 */     this.speedThreshold = speedThreshold;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\reports\model\TripsConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */