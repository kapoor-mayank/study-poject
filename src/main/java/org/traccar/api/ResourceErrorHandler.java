/*    */ package org.traccar.api;
/*    */ 
/*    */ import javax.ws.rs.WebApplicationException;
/*    */ import javax.ws.rs.core.Response;
/*    */ import javax.ws.rs.ext.ExceptionMapper;
/*    */ import org.traccar.helper.Log;
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
/*    */ public class ResourceErrorHandler
/*    */   implements ExceptionMapper<Exception>
/*    */ {
/*    */   public Response toResponse(Exception e) {
/* 28 */     if (e instanceof WebApplicationException) {
/* 29 */       String message; WebApplicationException exception = (WebApplicationException)e;
/*    */       
/* 31 */       if (exception.getCause() != null) {
/* 32 */         message = Log.exceptionStack(exception.getCause());
/*    */       } else {
/* 34 */         message = Log.exceptionStack((Throwable)exception);
/*    */       } 
/* 36 */       return Response.fromResponse(exception.getResponse()).entity(message).build();
/*    */     } 
/* 38 */     return Response.status(Response.Status.BAD_REQUEST).entity(Log.exceptionStack(e)).build();
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\api\ResourceErrorHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */