/*     */ package org.traccar.model;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Statistics
/*     */   extends ExtendedModel
/*     */ {
/*     */   private Date captureTime;
/*     */   private int activeUsers;
/*     */   private int activeDevices;
/*     */   private int requests;
/*     */   private int messagesReceived;
/*     */   private int messagesStored;
/*     */   private int mailSent;
/*     */   private int smsSent;
/*     */   private int geocoderRequests;
/*     */   private int geolocationRequests;
/*     */   
/*     */   public Date getCaptureTime() {
/*  25 */     return this.captureTime;
/*     */   }
/*     */   
/*     */   public void setCaptureTime(Date captureTime) {
/*  29 */     this.captureTime = captureTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getActiveUsers() {
/*  35 */     return this.activeUsers;
/*     */   }
/*     */   
/*     */   public void setActiveUsers(int activeUsers) {
/*  39 */     this.activeUsers = activeUsers;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getActiveDevices() {
/*  45 */     return this.activeDevices;
/*     */   }
/*     */   
/*     */   public void setActiveDevices(int activeDevices) {
/*  49 */     this.activeDevices = activeDevices;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequests() {
/*  55 */     return this.requests;
/*     */   }
/*     */   
/*     */   public void setRequests(int requests) {
/*  59 */     this.requests = requests;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMessagesReceived() {
/*  65 */     return this.messagesReceived;
/*     */   }
/*     */   
/*     */   public void setMessagesReceived(int messagesReceived) {
/*  69 */     this.messagesReceived = messagesReceived;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMessagesStored() {
/*  75 */     return this.messagesStored;
/*     */   }
/*     */   
/*     */   public void setMessagesStored(int messagesStored) {
/*  79 */     this.messagesStored = messagesStored;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMailSent() {
/*  85 */     return this.mailSent;
/*     */   }
/*     */   
/*     */   public void setMailSent(int mailSent) {
/*  89 */     this.mailSent = mailSent;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSmsSent() {
/*  95 */     return this.smsSent;
/*     */   }
/*     */   
/*     */   public void setSmsSent(int smsSent) {
/*  99 */     this.smsSent = smsSent;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGeocoderRequests() {
/* 105 */     return this.geocoderRequests;
/*     */   }
/*     */   
/*     */   public void setGeocoderRequests(int geocoderRequests) {
/* 109 */     this.geocoderRequests = geocoderRequests;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGeolocationRequests() {
/* 115 */     return this.geolocationRequests;
/*     */   }
/*     */   
/*     */   public void setGeolocationRequests(int geolocationRequests) {
/* 119 */     this.geolocationRequests = geolocationRequests;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\model\Statistics.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */