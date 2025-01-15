/*    */ package org.traccar.api;
/*    */ 
/*    */ import java.security.Principal;
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
/*    */ public class UserPrincipal
/*    */   implements Principal
/*    */ {
/*    */   private long userId;
/*    */   
/*    */   public UserPrincipal(long userId) {
/* 25 */     this.userId = userId;
/*    */   }
/*    */   
/*    */   public Long getUserId() {
/* 29 */     return Long.valueOf(this.userId);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 34 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\api\UserPrincipal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */