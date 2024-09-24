/*    */ package org.traccar.sms.smpp;
/*    */ 
/*    */ import org.traccar.Context;
/*    */ import org.traccar.model.Device;
/*    */ import org.traccar.model.Event;
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
/*    */ public final class TextMessageEventHandler
/*    */ {
/*    */   public static void handleTextMessage(String phone, String message) {
/* 29 */     Device device = Context.getDeviceManager().getDeviceByPhone(phone);
/* 30 */     if (device != null && Context.getNotificationManager() != null) {
/* 31 */       Event event = new Event("textMessage", device.getId());
/* 32 */       event.set("message", message);
/* 33 */       Context.getNotificationManager().updateEvent(event, null);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\sms\smpp\TextMessageEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */