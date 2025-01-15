/*     */ package org.traccar.model;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonIgnore;
/*     */ import java.util.Date;
/*     */ import org.traccar.database.QueryExtended;
/*     */ import org.traccar.database.QueryIgnore;
/*     */ import org.traccar.helper.Hashing;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class User
/*     */   extends ExtendedModel
/*     */ {
/*     */   private String name;
/*     */   private String login;
/*     */   private String email;
/*     */   private String phone;
/*     */   private boolean readonly;
/*     */   private boolean administrator;
/*     */   private String map;
/*     */   private double latitude;
/*     */   private double longitude;
/*     */   private int zoom;
/*     */   private boolean twelveHourFormat;
/*     */   
/*     */   public String getName() {
/*  31 */     return this.name;
/*     */   }
/*     */   private String coordinateFormat; private boolean disabled; private Date expirationTime; private int deviceLimit; private int userLimit; private boolean deviceReadonly; private String token; private boolean limitCommands; private String poiLayer; private String hashedPassword; private String salt;
/*     */   public void setName(String name) {
/*  35 */     this.name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLogin() {
/*  41 */     return this.login;
/*     */   }
/*     */   
/*     */   public void setLogin(String login) {
/*  45 */     this.login = login;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEmail() {
/*  51 */     return this.email;
/*     */   }
/*     */   
/*     */   public void setEmail(String email) {
/*  55 */     this.email = email.trim();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPhone() {
/*  61 */     return this.phone;
/*     */   }
/*     */   
/*     */   public void setPhone(String phone) {
/*  65 */     this.phone = phone;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getReadonly() {
/*  71 */     return this.readonly;
/*     */   }
/*     */   
/*     */   public void setReadonly(boolean readonly) {
/*  75 */     this.readonly = readonly;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getAdministrator() {
/*  81 */     return this.administrator;
/*     */   }
/*     */   
/*     */   public void setAdministrator(boolean administrator) {
/*  85 */     this.administrator = administrator;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMap() {
/*  91 */     return this.map;
/*     */   }
/*     */   
/*     */   public void setMap(String map) {
/*  95 */     this.map = map;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getLatitude() {
/* 101 */     return this.latitude;
/*     */   }
/*     */   
/*     */   public void setLatitude(double latitude) {
/* 105 */     this.latitude = latitude;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getLongitude() {
/* 111 */     return this.longitude;
/*     */   }
/*     */   
/*     */   public void setLongitude(double longitude) {
/* 115 */     this.longitude = longitude;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getZoom() {
/* 121 */     return this.zoom;
/*     */   }
/*     */   
/*     */   public void setZoom(int zoom) {
/* 125 */     this.zoom = zoom;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getTwelveHourFormat() {
/* 131 */     return this.twelveHourFormat;
/*     */   }
/*     */   
/*     */   public void setTwelveHourFormat(boolean twelveHourFormat) {
/* 135 */     this.twelveHourFormat = twelveHourFormat;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCoordinateFormat() {
/* 141 */     return this.coordinateFormat;
/*     */   }
/*     */   
/*     */   public void setCoordinateFormat(String coordinateFormat) {
/* 145 */     this.coordinateFormat = coordinateFormat;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getDisabled() {
/* 151 */     return this.disabled;
/*     */   }
/*     */   
/*     */   public void setDisabled(boolean disabled) {
/* 155 */     this.disabled = disabled;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getExpirationTime() {
/* 161 */     return this.expirationTime;
/*     */   }
/*     */   
/*     */   public void setExpirationTime(Date expirationTime) {
/* 165 */     this.expirationTime = expirationTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDeviceLimit() {
/* 171 */     return this.deviceLimit;
/*     */   }
/*     */   
/*     */   public void setDeviceLimit(int deviceLimit) {
/* 175 */     this.deviceLimit = deviceLimit;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getUserLimit() {
/* 181 */     return this.userLimit;
/*     */   }
/*     */   
/*     */   public void setUserLimit(int userLimit) {
/* 185 */     this.userLimit = userLimit;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getDeviceReadonly() {
/* 191 */     return this.deviceReadonly;
/*     */   }
/*     */   
/*     */   public void setDeviceReadonly(boolean deviceReadonly) {
/* 195 */     this.deviceReadonly = deviceReadonly;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getToken() {
/* 201 */     return this.token;
/*     */   }
/*     */   
/*     */   public void setToken(String token) {
/* 205 */     if (token != null && !token.isEmpty()) {
/* 206 */       if (!token.matches("^[a-zA-Z0-9-]{16,}$")) {
/* 207 */         throw new IllegalArgumentException("Illegal token");
/*     */       }
/* 209 */       this.token = token;
/*     */     } else {
/* 211 */       this.token = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getLimitCommands() {
/* 218 */     return this.limitCommands;
/*     */   }
/*     */   
/*     */   public void setLimitCommands(boolean limitCommands) {
/* 222 */     this.limitCommands = limitCommands;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPoiLayer() {
/* 228 */     return this.poiLayer;
/*     */   }
/*     */   
/*     */   public void setPoiLayer(String poiLayer) {
/* 232 */     this.poiLayer = poiLayer;
/*     */   }
/*     */   
/*     */   @QueryIgnore
/*     */   public String getPassword() {
/* 237 */     return null;
/*     */   }
/*     */   
/*     */   public void setPassword(String password) {
/* 241 */     if (password != null && !password.isEmpty()) {
/* 242 */       Hashing.HashingResult hashingResult = Hashing.createHash(password);
/* 243 */       this.hashedPassword = hashingResult.getHash();
/* 244 */       this.salt = hashingResult.getSalt();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @JsonIgnore
/*     */   @QueryExtended
/*     */   public String getHashedPassword() {
/* 253 */     return this.hashedPassword;
/*     */   }
/*     */   
/*     */   public void setHashedPassword(String hashedPassword) {
/* 257 */     this.hashedPassword = hashedPassword;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @JsonIgnore
/*     */   @QueryExtended
/*     */   public String getSalt() {
/* 265 */     return this.salt;
/*     */   }
/*     */   
/*     */   public void setSalt(String salt) {
/* 269 */     this.salt = salt;
/*     */   }
/*     */   
/*     */   public boolean isPasswordValid(String password) {
/* 273 */     return Hashing.validatePassword(password, this.hashedPassword, this.salt);
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\model\User.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */