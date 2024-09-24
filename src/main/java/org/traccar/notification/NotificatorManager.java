/*    */ package org.traccar.notification;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.HashSet;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.traccar.Context;
/*    */ import org.traccar.model.Typed;
/*    */ import org.traccar.notificators.Notificator;
/*    */ import org.traccar.notificators.NotificatorFirebase;
/*    */ import org.traccar.notificators.NotificatorMail;
/*    */ import org.traccar.notificators.NotificatorNull;
/*    */ import org.traccar.notificators.NotificatorSms;
/*    */ import org.traccar.notificators.NotificatorWeb;
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
/*    */ public final class NotificatorManager
/*    */ {
/* 37 */   private static final Logger LOGGER = LoggerFactory.getLogger(NotificatorManager.class);
/*    */   
/* 39 */   private static final Notificator NULL_NOTIFICATOR = (Notificator)new NotificatorNull();
/*    */   
/* 41 */   private final Map<String, Notificator> notificators = new HashMap<>();
/*    */   
/*    */   public NotificatorManager() {
/* 44 */     String[] types = Context.getConfig().getString("notificator.types", "").split(",");
/* 45 */     for (String type : types) {
/* 46 */       String defaultNotificator = "";
/* 47 */       switch (type) {
/*    */         case "web":
/* 49 */           defaultNotificator = NotificatorWeb.class.getCanonicalName();
/*    */           break;
/*    */         case "mail":
/* 52 */           defaultNotificator = NotificatorMail.class.getCanonicalName();
/*    */           break;
/*    */         case "sms":
/* 55 */           defaultNotificator = NotificatorSms.class.getCanonicalName();
/*    */           break;
/*    */         case "firebase":
/* 58 */           defaultNotificator = NotificatorFirebase.class.getCanonicalName();
/*    */           break;
/*    */       } 
/*    */ 
/*    */ 
/*    */       
/* 64 */       String className = Context.getConfig().getString("notificator." + type + ".class", defaultNotificator);
/*    */       try {
/* 66 */         this.notificators.put(type, (Notificator)Class.forName(className).newInstance());
/* 67 */       } catch (ClassNotFoundException|InstantiationException|IllegalAccessException e) {
/* 68 */         LOGGER.warn("Unable to load notificator class for " + type + " " + className + " " + e.getMessage());
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public Notificator getNotificator(String type) {
/* 74 */     Notificator notificator = this.notificators.get(type);
/* 75 */     if (notificator == null) {
/* 76 */       LOGGER.warn("No notificator configured for type : " + type);
/* 77 */       return NULL_NOTIFICATOR;
/*    */     } 
/* 79 */     return notificator;
/*    */   }
/*    */   
/*    */   public Set<Typed> getAllNotificatorTypes() {
/* 83 */     Set<Typed> result = new HashSet<>();
/* 84 */     for (String notificator : this.notificators.keySet()) {
/* 85 */       result.add(new Typed(notificator));
/*    */     }
/* 87 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\notification\NotificatorManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */