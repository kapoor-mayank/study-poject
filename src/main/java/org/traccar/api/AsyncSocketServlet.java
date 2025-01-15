/*    */ package org.traccar.api;
/*    */ 
/*    */ import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
/*    */ import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
/*    */ import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
/*    */ import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
/*    */ import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
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
/*    */ public class AsyncSocketServlet
/*    */   extends WebSocketServlet
/*    */ {
/*    */   private static final long ASYNC_TIMEOUT = 600000L;
/*    */   
/*    */   public void configure(WebSocketServletFactory factory) {
/* 32 */     factory.getPolicy().setIdleTimeout(Context.getConfig().getLong("web.timeout", 600000L));
/* 33 */     factory.setCreator(new WebSocketCreator()
/*    */         {
/*    */           public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp) {
/* 36 */             if (req.getSession() != null) {
/* 37 */               long userId = ((Long)req.getSession().getAttribute("userId")).longValue();
/* 38 */               return new AsyncSocket(userId);
/*    */             } 
/* 40 */             return null;
/*    */           }
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\api\AsyncSocketServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */