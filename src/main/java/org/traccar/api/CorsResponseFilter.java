/*    */ package org.traccar.api;
/*    */ 
/*    */ import io.netty.handler.codec.http.HttpHeaderNames;
/*    */ import java.io.IOException;
/*    */ import javax.ws.rs.container.ContainerRequestContext;
/*    */ import javax.ws.rs.container.ContainerResponseContext;
/*    */ import javax.ws.rs.container.ContainerResponseFilter;
/*    */ import org.traccar.Context;
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
/*    */ public class CorsResponseFilter
/*    */   implements ContainerResponseFilter
/*    */ {
/*    */   private static final String ORIGIN_ALL = "*";
/*    */   private static final String HEADERS_ALL = "origin, content-type, accept, authorization";
/*    */   private static final String METHODS_ALL = "GET, POST, PUT, DELETE, OPTIONS";
/*    */   
/*    */   public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
/* 34 */     if (!response.getHeaders().containsKey(HttpHeaderNames.ACCESS_CONTROL_ALLOW_HEADERS.toString())) {
/* 35 */       response.getHeaders().add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_HEADERS.toString(), "origin, content-type, accept, authorization");
/*    */     }
/*    */     
/* 38 */     if (!response.getHeaders().containsKey(HttpHeaderNames.ACCESS_CONTROL_ALLOW_CREDENTIALS.toString())) {
/* 39 */       response.getHeaders().add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_CREDENTIALS.toString(), Boolean.valueOf(true));
/*    */     }
/*    */     
/* 42 */     if (!response.getHeaders().containsKey(HttpHeaderNames.ACCESS_CONTROL_ALLOW_METHODS.toString())) {
/* 43 */       response.getHeaders().add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_METHODS.toString(), "GET, POST, PUT, DELETE, OPTIONS");
/*    */     }
/*    */     
/* 46 */     if (!response.getHeaders().containsKey(HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN.toString())) {
/* 47 */       String origin = request.getHeaderString(HttpHeaderNames.ORIGIN.toString());
/* 48 */       String allowed = Context.getConfig().getString("web.origin");
/*    */       
/* 50 */       if (origin == null) {
/* 51 */         response.getHeaders().add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN.toString(), "*");
/* 52 */       } else if (allowed == null || allowed.equals("*") || allowed.contains(origin)) {
/* 53 */         response.getHeaders().add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN.toString(), origin);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\api\CorsResponseFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */