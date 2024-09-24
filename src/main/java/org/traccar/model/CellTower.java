/*     */ package org.traccar.model;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonInclude;
/*     */ import org.traccar.Context;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @JsonInclude(JsonInclude.Include.NON_NULL)
/*     */ public class CellTower
/*     */ {
/*     */   private String radioType;
/*     */   private Long cellId;
/*     */   private Integer locationAreaCode;
/*     */   private Integer mobileCountryCode;
/*     */   private Integer mobileNetworkCode;
/*     */   private Integer signalStrength;
/*     */   
/*     */   public static CellTower from(int mcc, int mnc, int lac, long cid) {
/*  25 */     CellTower cellTower = new CellTower();
/*  26 */     cellTower.setMobileCountryCode(Integer.valueOf(mcc));
/*  27 */     cellTower.setMobileNetworkCode(Integer.valueOf(mnc));
/*  28 */     cellTower.setLocationAreaCode(Integer.valueOf(lac));
/*  29 */     cellTower.setCellId(Long.valueOf(cid));
/*  30 */     return cellTower;
/*     */   }
/*     */   
/*     */   public static CellTower from(int mcc, int mnc, int lac, long cid, int rssi) {
/*  34 */     CellTower cellTower = from(mcc, mnc, lac, cid);
/*  35 */     cellTower.setSignalStrength(Integer.valueOf(rssi));
/*  36 */     return cellTower;
/*     */   }
/*     */   
/*     */   public static CellTower fromLacCid(int lac, long cid) {
/*  40 */     return from(
/*  41 */         Context.getConfig().getInteger("geolocation.mcc"), 
/*  42 */         Context.getConfig().getInteger("geolocation.mnc"), lac, cid);
/*     */   }
/*     */   
/*     */   public static CellTower fromCidLac(long cid, int lac) {
/*  46 */     return fromLacCid(lac, cid);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRadioType() {
/*  52 */     return this.radioType;
/*     */   }
/*     */   
/*     */   public void setRadioType(String radioType) {
/*  56 */     this.radioType = radioType;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Long getCellId() {
/*  62 */     return this.cellId;
/*     */   }
/*     */   
/*     */   public void setCellId(Long cellId) {
/*  66 */     this.cellId = cellId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getLocationAreaCode() {
/*  72 */     return this.locationAreaCode;
/*     */   }
/*     */   
/*     */   public void setLocationAreaCode(Integer locationAreaCode) {
/*  76 */     this.locationAreaCode = locationAreaCode;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getMobileCountryCode() {
/*  82 */     return this.mobileCountryCode;
/*     */   }
/*     */   
/*     */   public void setMobileCountryCode(Integer mobileCountryCode) {
/*  86 */     this.mobileCountryCode = mobileCountryCode;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getMobileNetworkCode() {
/*  92 */     return this.mobileNetworkCode;
/*     */   }
/*     */   
/*     */   public void setMobileNetworkCode(Integer mobileNetworkCode) {
/*  96 */     this.mobileNetworkCode = mobileNetworkCode;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getSignalStrength() {
/* 102 */     return this.signalStrength;
/*     */   }
/*     */   
/*     */   public void setSignalStrength(Integer signalStrength) {
/* 106 */     this.signalStrength = signalStrength;
/*     */   }
/*     */   
/*     */   public void setOperator(long operator) {
/* 110 */     String operatorString = String.valueOf(operator);
/* 111 */     this.mobileCountryCode = Integer.valueOf(Integer.parseInt(operatorString.substring(0, 3)));
/* 112 */     this.mobileNetworkCode = Integer.valueOf(Integer.parseInt(operatorString.substring(3)));
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\model\CellTower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */