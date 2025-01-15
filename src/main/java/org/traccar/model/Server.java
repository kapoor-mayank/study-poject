/*     */ package org.traccar.model;
/*     */ 
/*     */ import org.traccar.database.QueryIgnore;
/*     */ 
/*     */ public class Server
/*     */   extends ExtendedModel {
/*     */   private boolean registration;
/*     */   private boolean readonly;
/*     */   private boolean deviceReadonly;
/*     */   private String map;
/*     */   private String bingKey;
/*     */   private String mapUrl;
/*     */   private double latitude;
/*     */   private double longitude;
/*     */   private int zoom;
/*     */   private boolean twelveHourFormat;
/*     */   private boolean forceSettings;
/*     */   private String coordinateFormat;
/*     */   private boolean limitCommands;
/*     */   private String poiLayer;
/*     */   
/*     */   @QueryIgnore
/*     */   public String getVersion() {
/*  24 */     return getClass().getPackage().getImplementationVersion();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(String version) {}
/*     */ 
/*     */   
/*     */   public boolean getRegistration() {
/*  33 */     return this.registration;
/*     */   }
/*     */   
/*     */   public void setRegistration(boolean registration) {
/*  37 */     this.registration = registration;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getReadonly() {
/*  43 */     return this.readonly;
/*     */   }
/*     */   
/*     */   public void setReadonly(boolean readonly) {
/*  47 */     this.readonly = readonly;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getDeviceReadonly() {
/*  53 */     return this.deviceReadonly;
/*     */   }
/*     */   
/*     */   public void setDeviceReadonly(boolean deviceReadonly) {
/*  57 */     this.deviceReadonly = deviceReadonly;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMap() {
/*  63 */     return this.map;
/*     */   }
/*     */   
/*     */   public void setMap(String map) {
/*  67 */     this.map = map;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBingKey() {
/*  73 */     return this.bingKey;
/*     */   }
/*     */   
/*     */   public void setBingKey(String bingKey) {
/*  77 */     this.bingKey = bingKey;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMapUrl() {
/*  83 */     return this.mapUrl;
/*     */   }
/*     */   
/*     */   public void setMapUrl(String mapUrl) {
/*  87 */     this.mapUrl = mapUrl;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getLatitude() {
/*  93 */     return this.latitude;
/*     */   }
/*     */   
/*     */   public void setLatitude(double latitude) {
/*  97 */     this.latitude = latitude;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getLongitude() {
/* 103 */     return this.longitude;
/*     */   }
/*     */   
/*     */   public void setLongitude(double longitude) {
/* 107 */     this.longitude = longitude;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getZoom() {
/* 113 */     return this.zoom;
/*     */   }
/*     */   
/*     */   public void setZoom(int zoom) {
/* 117 */     this.zoom = zoom;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getTwelveHourFormat() {
/* 123 */     return this.twelveHourFormat;
/*     */   }
/*     */   
/*     */   public void setTwelveHourFormat(boolean twelveHourFormat) {
/* 127 */     this.twelveHourFormat = twelveHourFormat;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getForceSettings() {
/* 133 */     return this.forceSettings;
/*     */   }
/*     */   
/*     */   public void setForceSettings(boolean forceSettings) {
/* 137 */     this.forceSettings = forceSettings;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCoordinateFormat() {
/* 143 */     return this.coordinateFormat;
/*     */   }
/*     */   
/*     */   public void setCoordinateFormat(String coordinateFormat) {
/* 147 */     this.coordinateFormat = coordinateFormat;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getLimitCommands() {
/* 153 */     return this.limitCommands;
/*     */   }
/*     */   
/*     */   public void setLimitCommands(boolean limitCommands) {
/* 157 */     this.limitCommands = limitCommands;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPoiLayer() {
/* 163 */     return this.poiLayer;
/*     */   }
/*     */   
/*     */   public void setPoiLayer(String poiLayer) {
/* 167 */     this.poiLayer = poiLayer;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\model\Server.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */