/*     */ package org.traccar.sms;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.URLEncoder;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import javax.ws.rs.client.Entity;
/*     */ import javax.ws.rs.client.Invocation;
/*     */ import javax.ws.rs.client.InvocationCallback;
/*     */ import javax.ws.rs.core.MediaType;
/*     */ import javax.ws.rs.core.Response;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.traccar.Context;
/*     */ import org.traccar.helper.DataConverter;
/*     */ import org.traccar.notification.MessageException;
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
/*     */ 
/*     */ public class HttpSmsClient
/*     */   implements SmsManager
/*     */ {
/*  38 */   private static final Logger LOGGER = LoggerFactory.getLogger(HttpSmsClient.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   private String url = Context.getConfig().getString("sms.http.url");
/*  49 */   private String authorizationHeader = Context.getConfig().getString("sms.http.authorizationHeader", "Authorization");
/*     */   
/*  51 */   private String authorization = Context.getConfig().getString("sms.http.authorization"); private String template; public HttpSmsClient() {
/*  52 */     if (this.authorization == null) {
/*  53 */       String user = Context.getConfig().getString("sms.http.user");
/*  54 */       String password = Context.getConfig().getString("sms.http.password");
/*  55 */       this
/*  56 */         .authorization = "Basic " + DataConverter.printBase64((user + ":" + password).getBytes(StandardCharsets.UTF_8));
/*     */     } 
/*  58 */     this.template = Context.getConfig().getString("sms.http.template").trim();
/*  59 */     if (this.template.charAt(0) == '{' || this.template.charAt(0) == '[') {
/*  60 */       this.encode = false;
/*  61 */       this.mediaType = MediaType.APPLICATION_JSON_TYPE;
/*     */     } else {
/*  63 */       this.encode = true;
/*  64 */       this.mediaType = MediaType.APPLICATION_FORM_URLENCODED_TYPE;
/*     */     } 
/*     */   }
/*     */   private boolean encode; private MediaType mediaType;
/*     */   private String prepareValue(String value) throws UnsupportedEncodingException {
/*  69 */     return this.encode ? URLEncoder.encode(value, StandardCharsets.UTF_8.name()) : value;
/*     */   }
/*     */   
/*     */   private String preparePayload(String destAddress, String message) {
/*     */     try {
/*  74 */       return this.template
/*  75 */         .replace("{phone}", prepareValue(destAddress))
/*  76 */         .replace("{message}", prepareValue(message.trim()));
/*  77 */     } catch (UnsupportedEncodingException e) {
/*  78 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private Invocation.Builder getRequestBuilder() {
/*  83 */     return Context.getClient().target(this.url).request()
/*  84 */       .header(this.authorizationHeader, this.authorization);
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendMessageSync(String destAddress, String message, boolean command) throws MessageException {
/*  89 */     Response response = getRequestBuilder().post(Entity.entity(preparePayload(destAddress, message), this.mediaType));
/*  90 */     if (response.getStatus() / 100 != 2) {
/*  91 */       throw new MessageException((String)response.readEntity(String.class));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendMessageAsync(String destAddress, String message, boolean command) {
/*  97 */     getRequestBuilder().async().post(
/*  98 */         Entity.entity(preparePayload(destAddress, message), this.mediaType), new InvocationCallback<String>()
/*     */         {
/*     */           public void completed(String s) {}
/*     */ 
/*     */ 
/*     */           
/*     */           public void failed(Throwable throwable) {
/* 105 */             HttpSmsClient.LOGGER.warn("SMS send failed", throwable);
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\sms\HttpSmsClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */