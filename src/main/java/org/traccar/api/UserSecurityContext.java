/*    */ package org.traccar.api;
/*    */ 
/*    */ import java.security.Principal;
/*    */ import javax.ws.rs.core.SecurityContext;
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
/*    */ public class UserSecurityContext
/*    */   implements SecurityContext
/*    */ {
/*    */   private UserPrincipal principal;
/*    */   
/*    */   public UserSecurityContext(UserPrincipal principal) {
/* 26 */     this.principal = principal;
/*    */   }
/*    */ 
/*    */   
/*    */   public Principal getUserPrincipal() {
/* 31 */     return this.principal;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isUserInRole(String role) {
/* 36 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isSecure() {
/* 41 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getAuthenticationScheme() {
/* 46 */     return "BASIC";
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\api\UserSecurityContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */