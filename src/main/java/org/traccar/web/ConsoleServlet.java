/*    */ package org.traccar.web;
/*    */ 
/*    */ import java.lang.reflect.Field;
/*    */ import java.lang.reflect.Method;
/*    */ import org.h2.server.web.ConnectionInfo;
/*    */ import org.h2.server.web.WebServer;
/*    */ import org.h2.server.web.WebServlet;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
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
/*    */ public class ConsoleServlet
/*    */   extends WebServlet
/*    */ {
/* 30 */   private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleServlet.class);
/*    */ 
/*    */   
/*    */   public void init() {
/* 34 */     super.init();
/*    */     
/*    */     try {
/* 37 */       Field field = WebServlet.class.getDeclaredField("server");
/* 38 */       field.setAccessible(true);
/* 39 */       WebServer server = (WebServer)field.get(this);
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 44 */       ConnectionInfo connectionInfo = new ConnectionInfo("Traccar|" + Context.getConfig().getString("database.driver") + "|" + Context.getConfig().getString("database.url") + "|" + Context.getConfig().getString("database.user"));
/*    */ 
/*    */ 
/*    */       
/* 48 */       Method method = WebServer.class.getDeclaredMethod("updateSetting", new Class[] { ConnectionInfo.class });
/* 49 */       method.setAccessible(true);
/* 50 */       method.invoke(server, new Object[] { connectionInfo });
/*    */       
/* 52 */       method = WebServer.class.getDeclaredMethod("setAllowOthers", new Class[] { boolean.class });
/* 53 */       method.setAccessible(true);
/* 54 */       method.invoke(server, new Object[] { Boolean.valueOf(true) });
/*    */     }
/* 56 */     catch (NoSuchFieldException|IllegalAccessException|NoSuchMethodException|java.lang.reflect.InvocationTargetException e) {
/* 57 */       LOGGER.warn("Console reflection error", e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\web\ConsoleServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */