/*    */ package org.traccar.sms.smpp;
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
/*    */ public class ReconnectionTask
/*    */   implements Runnable
/*    */ {
/*    */   private final SmppClient smppClient;
/*    */   
/*    */   protected ReconnectionTask(SmppClient smppClient) {
/* 24 */     this.smppClient = smppClient;
/*    */   }
/*    */ 
/*    */   
/*    */   public void run() {
/* 29 */     this.smppClient.reconnect();
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\sms\smpp\ReconnectionTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */