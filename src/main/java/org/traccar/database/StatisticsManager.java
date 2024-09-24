/*     */ package org.traccar.database;
/*     */ 
/*     */ import java.sql.SQLException;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import javax.inject.Inject;
/*     */ import javax.ws.rs.client.Client;
/*     */ import javax.ws.rs.client.Entity;
/*     */ import javax.ws.rs.core.Form;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.traccar.config.Config;
/*     */ import org.traccar.config.Keys;
/*     */ import org.traccar.helper.DateUtil;
/*     */ import org.traccar.model.BaseModel;
/*     */ import org.traccar.model.Statistics;
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
/*     */ 
/*     */ public class StatisticsManager
/*     */ {
/*  38 */   private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsManager.class);
/*     */   
/*     */   private static final int SPLIT_MODE = 5;
/*     */   
/*     */   private final Config config;
/*     */   
/*     */   private final DataManager dataManager;
/*     */   private final Client client;
/*  46 */   private AtomicInteger lastUpdate = new AtomicInteger(Calendar.getInstance().get(5));
/*     */   
/*  48 */   private Set<Long> users = new HashSet<>();
/*  49 */   private Set<Long> devices = new HashSet<>();
/*     */   
/*     */   private int requests;
/*     */   private int messagesReceived;
/*     */   private int messagesStored;
/*     */   private int mailSent;
/*     */   private int smsSent;
/*     */   private int geocoderRequests;
/*     */   private int geolocationRequests;
/*     */   
/*     */   @Inject
/*     */   public StatisticsManager(Config config, DataManager dataManager, Client client) {
/*  61 */     this.config = config;
/*  62 */     this.dataManager = dataManager;
/*  63 */     this.client = client;
/*     */   }
/*     */   
/*     */   private void checkSplit() {
/*  67 */     int currentUpdate = Calendar.getInstance().get(5);
/*  68 */     if (this.lastUpdate.getAndSet(currentUpdate) != currentUpdate) {
/*  69 */       Statistics statistics = new Statistics();
/*  70 */       statistics.setCaptureTime(new Date());
/*  71 */       statistics.setActiveUsers(this.users.size());
/*  72 */       statistics.setActiveDevices(this.devices.size());
/*  73 */       statistics.setRequests(this.requests);
/*  74 */       statistics.setMessagesReceived(this.messagesReceived);
/*  75 */       statistics.setMessagesStored(this.messagesStored);
/*  76 */       statistics.setMailSent(this.mailSent);
/*  77 */       statistics.setSmsSent(this.smsSent);
/*  78 */       statistics.setGeocoderRequests(this.geocoderRequests);
/*  79 */       statistics.setGeolocationRequests(this.geolocationRequests);
/*     */       
/*     */       try {
/*  82 */         this.dataManager.addObject((BaseModel)statistics);
/*  83 */       } catch (SQLException e) {
/*  84 */         LOGGER.warn("Error saving statistics", e);
/*     */       } 
/*     */       
/*  87 */       String url = this.config.getString(Keys.SERVER_STATISTICS);
/*  88 */       if (url != null) {
/*  89 */         String time = DateUtil.formatDate(statistics.getCaptureTime());
/*     */         
/*  91 */         Form form = new Form();
/*  92 */         form.param("version", getClass().getPackage().getImplementationVersion());
/*  93 */         form.param("captureTime", time);
/*  94 */         form.param("activeUsers", String.valueOf(statistics.getActiveUsers()));
/*  95 */         form.param("activeDevices", String.valueOf(statistics.getActiveDevices()));
/*  96 */         form.param("requests", String.valueOf(statistics.getRequests()));
/*  97 */         form.param("messagesReceived", String.valueOf(statistics.getMessagesReceived()));
/*  98 */         form.param("messagesStored", String.valueOf(statistics.getMessagesStored()));
/*  99 */         form.param("mailSent", String.valueOf(statistics.getMailSent()));
/* 100 */         form.param("smsSent", String.valueOf(statistics.getSmsSent()));
/* 101 */         form.param("geocoderRequests", String.valueOf(statistics.getGeocoderRequests()));
/* 102 */         form.param("geolocationRequests", String.valueOf(statistics.getGeolocationRequests()));
/*     */         
/* 104 */         this.client.target(url).request().async().post(Entity.form(form));
/*     */       } 
/*     */       
/* 107 */       this.users.clear();
/* 108 */       this.devices.clear();
/* 109 */       this.requests = 0;
/* 110 */       this.messagesReceived = 0;
/* 111 */       this.messagesStored = 0;
/* 112 */       this.mailSent = 0;
/* 113 */       this.smsSent = 0;
/* 114 */       this.geocoderRequests = 0;
/* 115 */       this.geolocationRequests = 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized void registerRequest(long userId) {
/* 120 */     checkSplit();
/* 121 */     this.requests++;
/* 122 */     if (userId != 0L) {
/* 123 */       this.users.add(Long.valueOf(userId));
/*     */     }
/*     */   }
/*     */   
/*     */   public synchronized void registerMessageReceived() {
/* 128 */     checkSplit();
/* 129 */     this.messagesReceived++;
/*     */   }
/*     */   
/*     */   public synchronized void registerMessageStored(long deviceId) {
/* 133 */     checkSplit();
/* 134 */     this.messagesStored++;
/* 135 */     if (deviceId != 0L) {
/* 136 */       this.devices.add(Long.valueOf(deviceId));
/*     */     }
/*     */   }
/*     */   
/*     */   public synchronized void registerMail() {
/* 141 */     checkSplit();
/* 142 */     this.mailSent++;
/*     */   }
/*     */   
/*     */   public synchronized void registerSms() {
/* 146 */     checkSplit();
/* 147 */     this.smsSent++;
/*     */   }
/*     */   
/*     */   public synchronized void registerGeocoderRequest() {
/* 151 */     checkSplit();
/* 152 */     this.geocoderRequests++;
/*     */   }
/*     */   
/*     */   public synchronized void registerGeolocationRequest() {
/* 156 */     checkSplit();
/* 157 */     this.geolocationRequests++;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\database\StatisticsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */