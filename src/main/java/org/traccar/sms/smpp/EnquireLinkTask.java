/*    */ package org.traccar.sms.smpp;
/*    */ 
/*    */ import com.cloudhopper.smpp.SmppSession;
/*    */ import com.cloudhopper.smpp.pdu.EnquireLink;
/*    */ import com.cloudhopper.smpp.type.SmppTimeoutException;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnquireLinkTask
/*    */   implements Runnable
/*    */ {
/* 31 */   private static final Logger LOGGER = LoggerFactory.getLogger(EnquireLinkTask.class);
/*    */   
/*    */   private SmppClient smppClient;
/*    */   private Integer enquireLinkTimeout;
/*    */   
/*    */   public EnquireLinkTask(SmppClient smppClient, Integer enquireLinkTimeout) {
/* 37 */     this.smppClient = smppClient;
/* 38 */     this.enquireLinkTimeout = enquireLinkTimeout;
/*    */   }
/*    */ 
/*    */   
/*    */   public void run() {
/* 43 */     SmppSession smppSession = this.smppClient.getSession();
/* 44 */     if (smppSession != null && smppSession.isBound()) {
/*    */       try {
/* 46 */         smppSession.enquireLink(new EnquireLink(), this.enquireLinkTimeout.intValue());
/* 47 */       } catch (SmppTimeoutException|com.cloudhopper.smpp.type.SmppChannelException|com.cloudhopper.smpp.type.RecoverablePduException|com.cloudhopper.smpp.type.UnrecoverablePduException error) {
/*    */         
/* 49 */         LOGGER.warn("Enquire link failed, executing reconnect: ", error);
/* 50 */         this.smppClient.scheduleReconnect();
/* 51 */       } catch (InterruptedException error) {
/* 52 */         LOGGER.info("Enquire link interrupted, probably killed by reconnecting");
/*    */       } 
/*    */     } else {
/* 55 */       LOGGER.warn("Enquire link running while session is not connected");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\sms\smpp\EnquireLinkTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */