/*    */ package org.traccar.api;
/*    */ 
/*    */ import javax.ws.rs.core.Context;
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
/*    */ public class BaseResource
/*    */ {
/*    */   @Context
/*    */   private SecurityContext securityContext;
/*    */   
/*    */   protected long getUserId() {
/* 26 */     UserPrincipal principal = (UserPrincipal)this.securityContext.getUserPrincipal();
/* 27 */     if (principal != null) {
/* 28 */       return principal.getUserId().longValue();
/*    */     }
/* 30 */     return 0L;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\api\BaseResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */