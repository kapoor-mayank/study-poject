/*     */ package org.traccar.api;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.sql.SQLException;
/*     */ import javax.annotation.security.PermitAll;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.ws.rs.WebApplicationException;
/*     */ import javax.ws.rs.container.ContainerRequestContext;
/*     */ import javax.ws.rs.container.ContainerRequestFilter;
/*     */ import javax.ws.rs.container.ResourceInfo;
/*     */ //import javax.ws.rs.core.Context;
/*     */ import javax.ws.rs.core.Response;
/*     */ import javax.ws.rs.core.SecurityContext;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.traccar.Context;
/*     */ import org.traccar.Main;
/*     */ import org.traccar.database.StatisticsManager;
/*     */ import org.traccar.helper.DataConverter;
/*     */ import org.traccar.model.User;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SecurityRequestFilter
/*     */   implements ContainerRequestFilter
/*     */ {
/*  41 */   private static final Logger LOGGER = LoggerFactory.getLogger(SecurityRequestFilter.class);
/*     */   
/*     */   public static final String AUTHORIZATION_HEADER = "Authorization";
/*     */   
/*     */   public static final String WWW_AUTHENTICATE = "WWW-Authenticate";
/*     */   
/*     */   public static final String BASIC_REALM = "Basic realm=\"api\"";
/*     */   
/*     */   public static String[] decodeBasicAuth(String auth) {
/*  50 */     auth = auth.replaceFirst("[B|b]asic ", "");
/*  51 */     byte[] decodedBytes = DataConverter.parseBase64(auth);
/*  52 */     if (decodedBytes != null && decodedBytes.length > 0) {
/*  53 */       return (new String(decodedBytes, StandardCharsets.US_ASCII)).split(":", 2);
/*     */     }
/*  55 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static final String X_REQUESTED_WITH = "X-Requested-With";
/*     */   public static final String XML_HTTP_REQUEST = "XMLHttpRequest";
/*     */   @javax.ws.rs.core.Context
/*     */   private HttpServletRequest request;
/*     */   @javax.ws.rs.core.Context
/*     */   private ResourceInfo resourceInfo;
/*     */   
/*     */   public void filter(ContainerRequestContext requestContext) {
/*  67 */     if (requestContext.getMethod().equals("OPTIONS")) {
/*     */       return;
/*     */     }
/*     */     
/*  71 */     SecurityContext securityContext = null;
/*     */ 
/*     */     
/*     */     try {
/*  75 */       String authHeader = requestContext.getHeaderString("Authorization");
/*  76 */       if (authHeader != null) {
/*     */         
/*     */         try {
/*  79 */           String[] auth = decodeBasicAuth(authHeader);
/*  80 */           User user = Context.getPermissionsManager().login(auth[0], auth[1]);
/*  81 */           if (user != null) {
/*  82 */             ((StatisticsManager)Main.getInjector().getInstance(StatisticsManager.class)).registerRequest(user.getId());
/*  83 */             securityContext = new UserSecurityContext(new UserPrincipal(user.getId()));
/*     */           } 
/*  85 */         } catch (SQLException e) {
/*  86 */           throw new WebApplicationException(e);
/*     */         }
/*     */       
/*  89 */       } else if (this.request.getSession() != null) {
/*     */         
/*  91 */         Long userId = (Long)this.request.getSession().getAttribute("userId");
/*  92 */         if (userId != null) {
/*  93 */           Context.getPermissionsManager().checkUserEnabled(userId.longValue());
/*  94 */           ((StatisticsManager)Main.getInjector().getInstance(StatisticsManager.class)).registerRequest(userId.longValue());
/*  95 */           securityContext = new UserSecurityContext(new UserPrincipal(userId.longValue()));
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 100 */     } catch (SecurityException e) {
/* 101 */       LOGGER.warn("Authentication error", e);
/*     */     } 
/*     */     
/* 104 */     if (securityContext != null) {
/* 105 */       requestContext.setSecurityContext(securityContext);
/*     */     } else {
/* 107 */       Method method = this.resourceInfo.getResourceMethod();
/* 108 */       if (!method.isAnnotationPresent((Class)PermitAll.class)) {
/* 109 */         Response.ResponseBuilder responseBuilder = Response.status(Response.Status.UNAUTHORIZED);
/* 110 */         if (!"XMLHttpRequest".equals(this.request.getHeader("X-Requested-With"))) {
/* 111 */           responseBuilder.header("WWW-Authenticate", "Basic realm=\"api\"");
/*     */         }
/* 113 */         throw new WebApplicationException(responseBuilder.build());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\api\SecurityRequestFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */