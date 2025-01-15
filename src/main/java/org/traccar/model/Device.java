/*     */ package org.traccar.model;
/*     */ 
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import org.traccar.database.QueryExtended;
/*     */ import org.traccar.database.QueryIgnore;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Device
/*     */   extends GroupedModel
/*     */ {
/*     */   private String name;
/*     */   private String uniqueId;
/*     */   public static final String STATUS_UNKNOWN = "unknown";
/*     */   public static final String STATUS_ONLINE = "online";
/*     */   public static final String STATUS_OFFLINE = "offline";
/*     */   private String status;
/*     */   private Date lastUpdate;
/*     */   private long positionId;
/*     */   private List<Long> geofenceIds;
/*     */   private String phone;
/*     */   private String model;
/*     */   private String contact;
/*     */   private String category;
/*     */   private boolean disabled;
/*     */   
/*     */   public String getName() {
/*  29 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  33 */     this.name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUniqueId() {
/*  39 */     return this.uniqueId;
/*     */   }
/*     */   
/*     */   public void setUniqueId(String uniqueId) {
/*  43 */     this.uniqueId = uniqueId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @QueryIgnore
/*     */   public String getStatus() {
/*  54 */     return (this.status != null) ? this.status : "offline";
/*     */   }
/*     */   
/*     */   public void setStatus(String status) {
/*  58 */     this.status = status;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @QueryExtended
/*     */   public Date getLastUpdate() {
/*  65 */     if (this.lastUpdate != null) {
/*  66 */       return new Date(this.lastUpdate.getTime());
/*     */     }
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLastUpdate(Date lastUpdate) {
/*  73 */     if (lastUpdate != null) {
/*  74 */       this.lastUpdate = new Date(lastUpdate.getTime());
/*     */     } else {
/*  76 */       this.lastUpdate = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @QueryIgnore
/*     */   public long getPositionId() {
/*  84 */     return this.positionId;
/*     */   }
/*     */   
/*     */   public void setPositionId(long positionId) {
/*  88 */     this.positionId = positionId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @QueryIgnore
/*     */   public List<Long> getGeofenceIds() {
/*  95 */     return this.geofenceIds;
/*     */   }
/*     */   
/*     */   public void setGeofenceIds(List<Long> geofenceIds) {
/*  99 */     this.geofenceIds = geofenceIds;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPhone() {
/* 105 */     return this.phone;
/*     */   }
/*     */   
/*     */   public void setPhone(String phone) {
/* 109 */     this.phone = phone;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getModel() {
/* 115 */     return this.model;
/*     */   }
/*     */   
/*     */   public void setModel(String model) {
/* 119 */     this.model = model;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContact() {
/* 125 */     return this.contact;
/*     */   }
/*     */   
/*     */   public void setContact(String contact) {
/* 129 */     this.contact = contact;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCategory() {
/* 135 */     return this.category;
/*     */   }
/*     */   
/*     */   public void setCategory(String category) {
/* 139 */     this.category = category;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getDisabled() {
/* 145 */     return this.disabled;
/*     */   }
/*     */   
/*     */   public void setDisabled(boolean disabled) {
/* 149 */     this.disabled = disabled;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\model\Device.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */