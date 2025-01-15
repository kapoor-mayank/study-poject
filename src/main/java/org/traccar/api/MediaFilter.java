/*    */ package org.traccar.api;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.sql.SQLException;
/*    */ import javax.servlet.Filter;
/*    */ import javax.servlet.FilterChain;
/*    */ import javax.servlet.FilterConfig;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.ServletRequest;
/*    */ import javax.servlet.ServletResponse;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import javax.servlet.http.HttpSession;
/*    */ import org.traccar.Context;
/*    */ import org.traccar.Main;
/*    */ import org.traccar.database.StatisticsManager;
/*    */ import org.traccar.helper.Log;
/*    */ import org.traccar.model.Device;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MediaFilter
/*    */   implements Filter
/*    */ {
/*    */   public void init(FilterConfig filterConfig) throws ServletException {}
/*    */   
/*    */   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
/* 48 */     HttpServletResponse httpResponse = (HttpServletResponse)response;
/*    */     try {
/* 50 */       HttpSession session = ((HttpServletRequest)request).getSession(false);
/* 51 */       Long userId = null;
/* 52 */       if (session != null) {
/* 53 */         userId = (Long)session.getAttribute("userId");
/* 54 */         if (userId != null) {
/* 55 */           Context.getPermissionsManager().checkUserEnabled(userId.longValue());
/* 56 */           ((StatisticsManager)Main.getInjector().getInstance(StatisticsManager.class)).registerRequest(userId.longValue());
/*    */         } 
/*    */       } 
/* 59 */       if (userId == null) {
/* 60 */         httpResponse.sendError(401);
/*    */         
/*    */         return;
/*    */       } 
/* 64 */       String path = ((HttpServletRequest)request).getPathInfo();
/* 65 */       String[] parts = path.split("/");
/* 66 */       if (parts.length < 2 || (parts.length == 2 && !path.endsWith("/"))) {
/* 67 */         Context.getPermissionsManager().checkAdmin(userId.longValue());
/*    */       } else {
/* 69 */         Device device = Context.getDeviceManager().getByUniqueId(parts[1]);
/* 70 */         if (device != null) {
/* 71 */           Context.getPermissionsManager().checkDevice(userId.longValue(), device.getId());
/*    */         } else {
/* 73 */           httpResponse.sendError(404);
/*    */           
/*    */           return;
/*    */         } 
/*    */       } 
/* 78 */       chain.doFilter(request, response);
/* 79 */     } catch (SecurityException e) {
/* 80 */       httpResponse.setStatus(403);
/* 81 */       httpResponse.getWriter().println(Log.exceptionStack(e));
/* 82 */     } catch (SQLException e) {
/* 83 */       httpResponse.setStatus(400);
/* 84 */       httpResponse.getWriter().println(Log.exceptionStack(e));
/*    */     } 
/*    */   }
/*    */   
/*    */   public void destroy() {}
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\api\MediaFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */