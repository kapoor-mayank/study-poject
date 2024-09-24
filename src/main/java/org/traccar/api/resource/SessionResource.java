/*     */ package org.traccar.api.resource;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.URLDecoder;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.sql.SQLException;
/*     */ import javax.annotation.security.PermitAll;
/*     */ import javax.servlet.http.Cookie;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.ws.rs.Consumes;
/*     */ import javax.ws.rs.DELETE;
/*     */ import javax.ws.rs.FormParam;
/*     */ import javax.ws.rs.GET;
/*     */ import javax.ws.rs.POST;
/*     */ import javax.ws.rs.Path;
/*     */ import javax.ws.rs.Produces;
/*     */ import javax.ws.rs.QueryParam;
/*     */ import javax.ws.rs.WebApplicationException;
/*     */ import javax.ws.rs.core.Context;
/*     */ import javax.ws.rs.core.Response;
/*     */ import org.traccar.Context;
/*     */ import org.traccar.api.BaseResource;
/*     */ import org.traccar.helper.DataConverter;
/*     */ import org.traccar.helper.LogAction;
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
/*     */ 
/*     */ 
/*     */ @Path("session")
/*     */ @Produces({"application/json"})
/*     */ @Consumes({"application/x-www-form-urlencoded"})
/*     */ public class SessionResource
/*     */   extends BaseResource
/*     */ {
/*     */   public static final String USER_ID_KEY = "userId";
/*     */   public static final String USER_COOKIE_KEY = "user";
/*     */   public static final String PASS_COOKIE_KEY = "password";
/*     */   @Context
/*     */   private HttpServletRequest request;
/*     */   
/*     */   @PermitAll
/*     */   @GET
/*     */   public User get(@QueryParam("token") String token) throws SQLException, UnsupportedEncodingException {
/*  59 */     Long userId = (Long)this.request.getSession().getAttribute("userId");
/*  60 */     if (userId == null) {
/*  61 */       Cookie[] cookies = this.request.getCookies();
/*  62 */       String email = null, password = null;
/*  63 */       if (cookies != null) {
/*  64 */         for (Cookie cookie : cookies) {
/*  65 */           if (cookie.getName().equals("user")) {
/*  66 */             byte[] emailBytes = DataConverter.parseBase64(
/*  67 */                 URLDecoder.decode(cookie.getValue(), StandardCharsets.US_ASCII.name()));
/*  68 */             email = new String(emailBytes, StandardCharsets.UTF_8);
/*  69 */           } else if (cookie.getName().equals("password")) {
/*  70 */             byte[] passwordBytes = DataConverter.parseBase64(
/*  71 */                 URLDecoder.decode(cookie.getValue(), StandardCharsets.US_ASCII.name()));
/*  72 */             password = new String(passwordBytes, StandardCharsets.UTF_8);
/*     */           } 
/*     */         } 
/*     */       }
/*  76 */       if (email != null && password != null) {
/*  77 */         User user = Context.getPermissionsManager().login(email, password);
/*  78 */         if (user != null) {
/*  79 */           userId = Long.valueOf(user.getId());
/*  80 */           this.request.getSession().setAttribute("userId", userId);
/*     */         } 
/*  82 */       } else if (token != null) {
/*  83 */         User user = Context.getUsersManager().getUserByToken(token);
/*  84 */         if (user != null) {
/*  85 */           userId = Long.valueOf(user.getId());
/*  86 */           this.request.getSession().setAttribute("userId", userId);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  91 */     if (userId != null) {
/*  92 */       Context.getPermissionsManager().checkUserEnabled(userId.longValue());
/*  93 */       return Context.getPermissionsManager().getUser(userId.longValue());
/*     */     } 
/*  95 */     throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).build());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @PermitAll
/*     */   @POST
/*     */   public User add(@FormParam("email") String email, @FormParam("password") String password) throws SQLException {
/* 103 */     User user = Context.getPermissionsManager().login(email, password);
/* 104 */     if (user != null) {
/* 105 */       this.request.getSession().setAttribute("userId", Long.valueOf(user.getId()));
/* 106 */       LogAction.login(user.getId());
/* 107 */       return user;
/*     */     } 
/* 109 */     throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED).build());
/*     */   }
/*     */ 
/*     */   
/*     */   @DELETE
/*     */   public Response remove() {
/* 115 */     LogAction.logout(getUserId());
/* 116 */     this.request.getSession().removeAttribute("userId");
/* 117 */     return Response.noContent().build();
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\api\resource\SessionResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */