/*    */ package org.traccar.sms.smpp;
/*    */ 
/*    */ import com.cloudhopper.commons.charset.CharsetUtil;
/*    */ import com.cloudhopper.smpp.impl.DefaultSmppSessionHandler;
/*    */ import com.cloudhopper.smpp.pdu.DeliverSm;
/*    */ import com.cloudhopper.smpp.pdu.PduRequest;
/*    */ import com.cloudhopper.smpp.pdu.PduResponse;
/*    */ import com.cloudhopper.smpp.util.SmppUtil;
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
/*    */ public class ClientSmppSessionHandler
/*    */   extends DefaultSmppSessionHandler
/*    */ {
/* 32 */   private static final Logger LOGGER = LoggerFactory.getLogger(ClientSmppSessionHandler.class);
/*    */   
/*    */   private SmppClient smppClient;
/*    */   
/*    */   public ClientSmppSessionHandler(SmppClient smppClient) {
/* 37 */     this.smppClient = smppClient;
/*    */   }
/*    */ 
/*    */   
/*    */   public void firePduRequestExpired(PduRequest pduRequest) {
/* 42 */     LOGGER.warn("PDU request expired: " + pduRequest);
/*    */   }
/*    */ 
/*    */   
/*    */   public PduResponse firePduRequestReceived(PduRequest request) {
/*    */     PduResponse response;
/*    */     try {
/* 49 */       if (request instanceof DeliverSm) {
/* 50 */         boolean isDeliveryReceipt; String sourceAddress = ((DeliverSm)request).getSourceAddress().getAddress();
/* 51 */         String message = CharsetUtil.decode(((DeliverSm)request).getShortMessage(), this.smppClient
/* 52 */             .mapDataCodingToCharset(((DeliverSm)request).getDataCoding()));
/* 53 */         LOGGER.info("SMS Message Received: " + message.trim() + ", Source Address: " + sourceAddress);
/*    */ 
/*    */         
/* 56 */         if (this.smppClient.getDetectDlrByOpts()) {
/* 57 */           isDeliveryReceipt = (request.getOptionalParameters() != null);
/*    */         } else {
/* 59 */           isDeliveryReceipt = SmppUtil.isMessageTypeAnyDeliveryReceipt(((DeliverSm)request).getEsmClass());
/*    */         } 
/*    */         
/* 62 */         if (!isDeliveryReceipt) {
/* 63 */           TextMessageEventHandler.handleTextMessage(sourceAddress, message);
/*    */         }
/*    */       } 
/* 66 */       response = request.createResponse();
/* 67 */     } catch (Exception error) {
/* 68 */       LOGGER.warn("SMS receiving error", error);
/* 69 */       response = request.createResponse();
/* 70 */       response.setResultMessage(error.getMessage());
/* 71 */       response.setCommandStatus(255);
/*    */     } 
/* 73 */     return response;
/*    */   }
/*    */ 
/*    */   
/*    */   public void fireChannelUnexpectedlyClosed() {
/* 78 */     LOGGER.warn("SMPP session channel unexpectedly closed");
/* 79 */     this.smppClient.scheduleReconnect();
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\sms\smpp\ClientSmppSessionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */