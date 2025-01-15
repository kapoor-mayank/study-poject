/*     */ package org.traccar.sms.smpp;
/*     */ 
/*     */ import com.cloudhopper.commons.charset.CharsetUtil;
/*     */ import com.cloudhopper.smpp.SmppBindType;
/*     */ import com.cloudhopper.smpp.SmppSession;
/*     */ import com.cloudhopper.smpp.SmppSessionConfiguration;
/*     */ import com.cloudhopper.smpp.SmppSessionHandler;
/*     */ import com.cloudhopper.smpp.impl.DefaultSmppClient;
/*     */ import com.cloudhopper.smpp.impl.DefaultSmppSessionHandler;
/*     */ import com.cloudhopper.smpp.pdu.SubmitSm;
/*     */ import com.cloudhopper.smpp.pdu.SubmitSmResp;
/*     */ import com.cloudhopper.smpp.tlv.Tlv;
/*     */ import com.cloudhopper.smpp.type.Address;
/*     */ import com.cloudhopper.smpp.type.SmppChannelException;
/*     */ import com.cloudhopper.smpp.type.SmppTimeoutException;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.ScheduledExecutorService;
/*     */ import java.util.concurrent.ScheduledFuture;
/*     */ import java.util.concurrent.ThreadFactory;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.traccar.Context;
/*     */ import org.traccar.notification.MessageException;
/*     */ import org.traccar.sms.SmsManager;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SmppClient
/*     */   implements SmsManager
/*     */ {
/*  50 */   private static final Logger LOGGER = LoggerFactory.getLogger(SmppClient.class);
/*     */   
/*  52 */   private SmppSessionConfiguration sessionConfig = new SmppSessionConfiguration();
/*     */   private SmppSession smppSession;
/*  54 */   private DefaultSmppSessionHandler sessionHandler = new ClientSmppSessionHandler(this);
/*  55 */   private ExecutorService executorService = Executors.newCachedThreadPool();
/*  56 */   private DefaultSmppClient clientBootstrap = new DefaultSmppClient();
/*     */   
/*     */   private ScheduledExecutorService enquireLinkExecutor;
/*     */   
/*     */   private ScheduledFuture<?> enquireLinkTask;
/*     */   
/*     */   private Integer enquireLinkPeriod;
/*     */   
/*     */   private Integer enquireLinkTimeout;
/*     */   
/*     */   private ScheduledExecutorService reconnectionExecutor;
/*     */   private ScheduledFuture<?> reconnectionTask;
/*     */   private Integer reconnectionDelay;
/*     */   private String sourceAddress;
/*     */   private String commandSourceAddress;
/*     */   private int submitTimeout;
/*     */   private boolean requestDlr;
/*     */   private boolean detectDlrByOpts;
/*     */   private String notificationsCharsetName;
/*     */   private byte notificationsDataCoding;
/*     */   private String commandsCharsetName;
/*     */   private byte commandsDataCoding;
/*     */   private byte sourceTon;
/*     */   private byte sourceNpi;
/*     */   private byte commandSourceTon;
/*     */   private byte commandSourceNpi;
/*     */   private byte destTon;
/*     */   private byte destNpi;
/*     */   
/*     */   public SmppClient() {
/*  86 */     this.sessionConfig.setName("Traccar.smppSession");
/*  87 */     this.sessionConfig.setInterfaceVersion(
/*  88 */         (byte)Context.getConfig().getInteger("sms.smpp.version", 52));
/*  89 */     this.sessionConfig.setType(SmppBindType.TRANSCEIVER);
/*  90 */     this.sessionConfig.setHost(Context.getConfig().getString("sms.smpp.host", "localhost"));
/*  91 */     this.sessionConfig.setPort(Context.getConfig().getInteger("sms.smpp.port", 2775));
/*  92 */     this.sessionConfig.setSystemId(Context.getConfig().getString("sms.smpp.username", "user"));
/*  93 */     this.sessionConfig.setSystemType(Context.getConfig().getString("sms.smpp.systemType", null));
/*  94 */     this.sessionConfig.setPassword(Context.getConfig().getString("sms.smpp.password", "password"));
/*  95 */     this.sessionConfig.getLoggingOptions().setLogBytes(false);
/*  96 */     this.sessionConfig.getLoggingOptions().setLogPdu(Context.getConfig().getBoolean("sms.smpp.logPdu"));
/*     */     
/*  98 */     this.sourceAddress = Context.getConfig().getString("sms.smpp.sourceAddress", "");
/*  99 */     this.commandSourceAddress = Context.getConfig().getString("sms.smpp.commandSourceAddress", this.sourceAddress);
/* 100 */     this.submitTimeout = Context.getConfig().getInteger("sms.smpp.submitTimeout", 10000);
/*     */     
/* 102 */     this.requestDlr = Context.getConfig().getBoolean("sms.smpp.requestDlr");
/* 103 */     this.detectDlrByOpts = Context.getConfig().getBoolean("sms.smpp.detectDlrByOpts");
/*     */     
/* 105 */     this.notificationsCharsetName = Context.getConfig().getString("sms.smpp.notificationsCharset", "UCS-2");
/*     */     
/* 107 */     this.notificationsDataCoding = (byte)Context.getConfig().getInteger("sms.smpp.notificationsDataCoding", 8);
/*     */     
/* 109 */     this.commandsCharsetName = Context.getConfig().getString("sms.smpp.commandsCharset", "GSM");
/*     */     
/* 111 */     this.commandsDataCoding = (byte)Context.getConfig().getInteger("sms.smpp.commandsDataCoding", 0);
/*     */ 
/*     */ 
/*     */     
/* 115 */     this.sourceTon = (byte)Context.getConfig().getInteger("sms.smpp.sourceTon", 5);
/* 116 */     this.commandSourceTon = (byte)Context.getConfig().getInteger("sms.smpp.commandSourceTon", this.sourceTon);
/* 117 */     this.sourceNpi = (byte)Context.getConfig().getInteger("sms.smpp.sourceNpi", 0);
/* 118 */     this.commandSourceNpi = (byte)Context.getConfig().getInteger("sms.smpp.commandSourceNpi", this.sourceNpi);
/*     */     
/* 120 */     this.destTon = (byte)Context.getConfig().getInteger("sms.smpp.destTon", 1);
/* 121 */     this.destNpi = (byte)Context.getConfig().getInteger("sms.smpp.destNpi", 1);
/*     */     
/* 123 */     this.enquireLinkPeriod = Integer.valueOf(Context.getConfig().getInteger("sms.smpp.enquireLinkPeriod", 60000));
/* 124 */     this.enquireLinkTimeout = Integer.valueOf(Context.getConfig().getInteger("sms.smpp.enquireLinkTimeout", 10000));
/* 125 */     this.enquireLinkExecutor = Executors.newScheduledThreadPool(1, new ThreadFactory()
/*     */         {
/*     */           public Thread newThread(Runnable runnable) {
/* 128 */             Thread thread = new Thread(runnable);
/* 129 */             String name = SmppClient.this.sessionConfig.getName();
/* 130 */             thread.setName("EnquireLink-" + name);
/* 131 */             return thread;
/*     */           }
/*     */         });
/*     */     
/* 135 */     this.reconnectionDelay = Integer.valueOf(Context.getConfig().getInteger("sms.smpp.reconnectionDelay", 10000));
/* 136 */     this.reconnectionExecutor = Executors.newSingleThreadScheduledExecutor(new ThreadFactory()
/*     */         {
/*     */           public Thread newThread(Runnable runnable) {
/* 139 */             Thread thread = new Thread(runnable);
/* 140 */             String name = SmppClient.this.sessionConfig.getName();
/* 141 */             thread.setName("Reconnection-" + name);
/* 142 */             return thread;
/*     */           }
/*     */         });
/*     */     
/* 146 */     scheduleReconnect();
/*     */   }
/*     */   
/*     */   public synchronized SmppSession getSession() {
/* 150 */     return this.smppSession;
/*     */   }
/*     */   
/*     */   public String mapDataCodingToCharset(byte dataCoding) {
/* 154 */     switch (dataCoding) {
/*     */       case 3:
/* 156 */         return "ISO-8859-1";
/*     */       case 8:
/* 158 */         return "UCS-2";
/*     */     } 
/* 160 */     return "GSM";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getDetectDlrByOpts() {
/* 165 */     return this.detectDlrByOpts;
/*     */   }
/*     */   
/*     */   protected synchronized void reconnect() {
/*     */     try {
/* 170 */       disconnect();
/* 171 */       this.smppSession = this.clientBootstrap.bind(this.sessionConfig, (SmppSessionHandler)this.sessionHandler);
/* 172 */       stopReconnectionkTask();
/* 173 */       runEnquireLinkTask();
/* 174 */       LOGGER.info("SMPP session connected");
/* 175 */     } catch (SmppTimeoutException|SmppChannelException|com.cloudhopper.smpp.type.UnrecoverablePduException|InterruptedException error) {
/*     */       
/* 177 */       LOGGER.warn("Unable to connect to SMPP server: ", error);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void scheduleReconnect() {
/* 182 */     if (this.reconnectionTask == null || this.reconnectionTask.isDone()) {
/* 183 */       this.reconnectionTask = this.reconnectionExecutor.scheduleWithFixedDelay(new ReconnectionTask(this), this.reconnectionDelay
/*     */           
/* 185 */           .intValue(), this.reconnectionDelay.intValue(), TimeUnit.MILLISECONDS);
/*     */     }
/*     */   }
/*     */   
/*     */   private void stopReconnectionkTask() {
/* 190 */     if (this.reconnectionTask != null) {
/* 191 */       this.reconnectionTask.cancel(false);
/*     */     }
/*     */   }
/*     */   
/*     */   private void disconnect() {
/* 196 */     stopEnquireLinkTask();
/* 197 */     destroySession();
/*     */   }
/*     */   
/*     */   private void runEnquireLinkTask() {
/* 201 */     this.enquireLinkTask = this.enquireLinkExecutor.scheduleWithFixedDelay(new EnquireLinkTask(this, this.enquireLinkTimeout), this.enquireLinkPeriod
/*     */         
/* 203 */         .intValue(), this.enquireLinkPeriod.intValue(), TimeUnit.MILLISECONDS);
/*     */   }
/*     */   
/*     */   private void stopEnquireLinkTask() {
/* 207 */     if (this.enquireLinkTask != null) {
/* 208 */       this.enquireLinkTask.cancel(true);
/*     */     }
/*     */   }
/*     */   
/*     */   private void destroySession() {
/* 213 */     if (this.smppSession != null) {
/* 214 */       LOGGER.info("Cleaning up SMPP session... ");
/* 215 */       this.smppSession.destroy();
/* 216 */       this.smppSession = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void sendMessageSync(String destAddress, String message, boolean command) throws MessageException, InterruptedException, IllegalStateException {
/* 223 */     if (getSession() != null && getSession().isBound()) {
/*     */       try {
/* 225 */         SubmitSm submit = new SubmitSm();
/*     */         
/* 227 */         byte[] textBytes = CharsetUtil.encode(message, command ? this.commandsCharsetName : this.notificationsCharsetName);
/* 228 */         submit.setDataCoding(command ? this.commandsDataCoding : this.notificationsDataCoding);
/* 229 */         if (this.requestDlr) {
/* 230 */           submit.setRegisteredDelivery((byte)1);
/*     */         }
/*     */         
/* 233 */         if (textBytes != null && textBytes.length > 255) {
/* 234 */           submit.addOptionalParameter(new Tlv((short)1060, textBytes, "message_payload"));
/*     */         } else {
/*     */           
/* 237 */           submit.setShortMessage(textBytes);
/*     */         } 
/*     */         
/* 240 */         submit.setSourceAddress(command ? new Address(this.commandSourceTon, this.commandSourceNpi, this.commandSourceAddress) : new Address(this.sourceTon, this.sourceNpi, this.sourceAddress));
/*     */         
/* 242 */         submit.setDestAddress(new Address(this.destTon, this.destNpi, destAddress));
/* 243 */         SubmitSmResp submitResponce = getSession().submit(submit, this.submitTimeout);
/* 244 */         if (submitResponce.getCommandStatus() == 0) {
/* 245 */           LOGGER.info("SMS submitted, message id: " + submitResponce.getMessageId());
/*     */         } else {
/* 247 */           throw new IllegalStateException(submitResponce.getResultMessage());
/*     */         } 
/* 249 */       } catch (SmppChannelException|com.cloudhopper.smpp.type.RecoverablePduException|SmppTimeoutException|com.cloudhopper.smpp.type.UnrecoverablePduException error) {
/*     */         
/* 251 */         throw new MessageException(error);
/*     */       } 
/*     */     } else {
/* 254 */       throw new MessageException(new SmppChannelException("SMPP session is not connected"));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendMessageAsync(final String destAddress, final String message, final boolean command) {
/* 260 */     this.executorService.execute(new Runnable()
/*     */         {
/*     */           public void run() {
/*     */             try {
/* 264 */               SmppClient.this.sendMessageSync(destAddress, message, command);
/* 265 */             } catch (MessageException|InterruptedException|IllegalStateException error) {
/* 266 */               SmppClient.LOGGER.warn("SMS sending error", error);
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\sms\smpp\SmppClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */