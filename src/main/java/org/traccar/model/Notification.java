/*    */ package org.traccar.model;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonIgnore;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import org.traccar.database.QueryIgnore;
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
/*    */ public class Notification
/*    */   extends ScheduledModel
/*    */ {
/*    */   private boolean always;
/*    */   private String type;
/*    */   private String notificators;
/*    */   
/*    */   public boolean getAlways() {
/* 30 */     return this.always;
/*    */   }
/*    */   
/*    */   public void setAlways(boolean always) {
/* 34 */     this.always = always;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getType() {
/* 40 */     return this.type;
/*    */   }
/*    */   
/*    */   public void setType(String type) {
/* 44 */     this.type = type;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getNotificators() {
/* 51 */     return this.notificators;
/*    */   }
/*    */   
/*    */   public void setNotificators(String transports) {
/* 55 */     this.notificators = transports;
/*    */   }
/*    */ 
/*    */   
/*    */   @JsonIgnore
/*    */   @QueryIgnore
/*    */   public Set<String> getNotificatorsTypes() {
/* 62 */     Set<String> result = new HashSet<>();
/* 63 */     if (this.notificators != null) {
/* 64 */       String[] transportsList = this.notificators.split(",");
/* 65 */       for (String transport : transportsList) {
/* 66 */         result.add(transport.trim());
/*    */       }
/*    */     } 
/* 69 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\model\Notification.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */