/*     */ package org.traccar.model;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonInclude;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
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
/*     */ @JsonInclude(JsonInclude.Include.NON_NULL)
/*     */ public class Network
/*     */ {
/*     */   private Integer homeMobileCountryCode;
/*     */   private Integer homeMobileNetworkCode;
/*     */   
/*     */   public Network() {}
/*     */   
/*     */   public Network(CellTower cellTower) {
/*  30 */     addCellTower(cellTower);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getHomeMobileCountryCode() {
/*  36 */     return this.homeMobileCountryCode;
/*     */   }
/*     */   
/*     */   public void setHomeMobileCountryCode(Integer homeMobileCountryCode) {
/*  40 */     this.homeMobileCountryCode = homeMobileCountryCode;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getHomeMobileNetworkCode() {
/*  46 */     return this.homeMobileNetworkCode;
/*     */   }
/*     */   
/*     */   public void setHomeMobileNetworkCode(Integer homeMobileNetworkCode) {
/*  50 */     this.homeMobileNetworkCode = homeMobileNetworkCode;
/*     */   }
/*     */   
/*  53 */   private String radioType = "gsm"; private String carrier;
/*     */   
/*     */   public String getRadioType() {
/*  56 */     return this.radioType;
/*     */   }
/*     */   
/*     */   public void setRadioType(String radioType) {
/*  60 */     this.radioType = radioType;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCarrier() {
/*  66 */     return this.carrier;
/*     */   }
/*     */   
/*     */   public void setCarrier(String carrier) {
/*  70 */     this.carrier = carrier;
/*     */   }
/*     */   
/*  73 */   private Boolean considerIp = Boolean.valueOf(false); private Collection<CellTower> cellTowers;
/*     */   
/*     */   public Boolean getConsiderIp() {
/*  76 */     return this.considerIp;
/*     */   }
/*     */   private Collection<WifiAccessPoint> wifiAccessPoints;
/*     */   public void setConsiderIp(Boolean considerIp) {
/*  80 */     this.considerIp = considerIp;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<CellTower> getCellTowers() {
/*  86 */     return this.cellTowers;
/*     */   }
/*     */   
/*     */   public void setCellTowers(Collection<CellTower> cellTowers) {
/*  90 */     this.cellTowers = cellTowers;
/*     */   }
/*     */   
/*     */   public void addCellTower(CellTower cellTower) {
/*  94 */     if (this.cellTowers == null) {
/*  95 */       this.cellTowers = new ArrayList<>();
/*     */     }
/*  97 */     this.cellTowers.add(cellTower);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<WifiAccessPoint> getWifiAccessPoints() {
/* 103 */     return this.wifiAccessPoints;
/*     */   }
/*     */   
/*     */   public void setWifiAccessPoints(Collection<WifiAccessPoint> wifiAccessPoints) {
/* 107 */     this.wifiAccessPoints = wifiAccessPoints;
/*     */   }
/*     */   
/*     */   public void addWifiAccessPoint(WifiAccessPoint wifiAccessPoint) {
/* 111 */     if (this.wifiAccessPoints == null) {
/* 112 */       this.wifiAccessPoints = new ArrayList<>();
/*     */     }
/* 114 */     this.wifiAccessPoints.add(wifiAccessPoint);
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\model\Network.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */