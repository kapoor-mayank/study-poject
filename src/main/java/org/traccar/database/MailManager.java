/*     */ package org.traccar.database;
/*     */ 
/*     */ import java.util.Date;
/*     */ import java.util.Properties;
/*     */ import javax.mail.Address;
/*     */ import javax.mail.BodyPart;
/*     */ import javax.mail.Message;
/*     */ import javax.mail.MessagingException;
/*     */ import javax.mail.Multipart;
/*     */ import javax.mail.Session;
/*     */ import javax.mail.Transport;
/*     */ import javax.mail.internet.InternetAddress;
/*     */ import javax.mail.internet.MimeBodyPart;
/*     */ import javax.mail.internet.MimeMessage;
/*     */ import javax.mail.internet.MimeMultipart;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.traccar.Context;
/*     */ import org.traccar.Main;
/*     */ import org.traccar.model.ExtendedModel;
/*     */ import org.traccar.model.User;
/*     */ import org.traccar.notification.PropertiesProvider;
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
/*     */ public final class MailManager
/*     */ {
/*  41 */   private static final Logger LOGGER = LoggerFactory.getLogger(MailManager.class);
/*     */   
/*     */   private static Properties getProperties(PropertiesProvider provider) {
/*  44 */     Properties properties = new Properties();
/*  45 */     String host = provider.getString("mail.smtp.host");
/*  46 */     if (host != null) {
/*  47 */       properties.put("mail.transport.protocol", provider.getString("mail.transport.protocol", "smtp"));
/*  48 */       properties.put("mail.smtp.host", host);
/*  49 */       properties.put("mail.smtp.port", String.valueOf(provider.getInteger("mail.smtp.port", 25)));
/*     */       
/*  51 */       Boolean starttlsEnable = provider.getBoolean("mail.smtp.starttls.enable");
/*  52 */       if (starttlsEnable != null) {
/*  53 */         properties.put("mail.smtp.starttls.enable", String.valueOf(starttlsEnable));
/*     */       }
/*  55 */       Boolean starttlsRequired = provider.getBoolean("mail.smtp.starttls.required");
/*  56 */       if (starttlsRequired != null) {
/*  57 */         properties.put("mail.smtp.starttls.required", String.valueOf(starttlsRequired));
/*     */       }
/*     */       
/*  60 */       Boolean sslEnable = provider.getBoolean("mail.smtp.ssl.enable");
/*  61 */       if (sslEnable != null) {
/*  62 */         properties.put("mail.smtp.ssl.enable", String.valueOf(sslEnable));
/*     */       }
/*  64 */       String sslTrust = provider.getString("mail.smtp.ssl.trust");
/*  65 */       if (sslTrust != null) {
/*  66 */         properties.put("mail.smtp.ssl.trust", sslTrust);
/*     */       }
/*     */       
/*  69 */       String sslProtocols = provider.getString("mail.smtp.ssl.protocols");
/*  70 */       if (sslProtocols != null) {
/*  71 */         properties.put("mail.smtp.ssl.protocols", sslProtocols);
/*     */       }
/*     */       
/*  74 */       String username = provider.getString("mail.smtp.username");
/*  75 */       if (username != null) {
/*  76 */         properties.put("mail.smtp.username", username);
/*     */       }
/*  78 */       String password = provider.getString("mail.smtp.password");
/*  79 */       if (password != null) {
/*  80 */         properties.put("mail.smtp.password", password);
/*     */       }
/*  82 */       String from = provider.getString("mail.smtp.from");
/*  83 */       if (from != null) {
/*  84 */         properties.put("mail.smtp.from", from);
/*     */       }
/*     */     } 
/*  87 */     return properties;
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendMessage(long userId, String subject, String body) throws MessagingException {
/*  92 */     sendMessage(userId, subject, body, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendMessage(long userId, String subject, String body, MimeBodyPart attachment) throws MessagingException {
/*  97 */     User user = Context.getPermissionsManager().getUser(userId);
/*     */     
/*  99 */     Properties properties = null;
/* 100 */     if (!Context.getConfig().getBoolean("mail.smtp.ignoreUserConfig")) {
/* 101 */       properties = getProperties(new PropertiesProvider((ExtendedModel)user));
/*     */     }
/* 103 */     if (properties == null || !properties.containsKey("mail.smtp.host")) {
/* 104 */       properties = getProperties(new PropertiesProvider(Context.getConfig()));
/*     */     }
/* 106 */     if (!properties.containsKey("mail.smtp.host")) {
/* 107 */       LOGGER.warn("No SMTP configuration found");
/*     */       
/*     */       return;
/*     */     } 
/* 111 */     Session session = Session.getInstance(properties);
/*     */     
/* 113 */     MimeMessage message = new MimeMessage(session);
/*     */     
/* 115 */     String from = properties.getProperty("mail.smtp.from");
/* 116 */     if (from != null) {
/* 117 */       message.setFrom((Address)new InternetAddress(from));
/*     */     }
/*     */     
/* 120 */     message.addRecipient(Message.RecipientType.TO, (Address)new InternetAddress(user.getEmail()));
/* 121 */     message.setSubject(subject);
/* 122 */     message.setSentDate(new Date());
/*     */     
/* 124 */     if (attachment != null) {
/* 125 */       MimeMultipart mimeMultipart = new MimeMultipart();
/*     */       
/* 127 */       MimeBodyPart mimeBodyPart = new MimeBodyPart();
/* 128 */       mimeBodyPart.setContent(body, "text/html; charset=utf-8");
/* 129 */       mimeMultipart.addBodyPart((BodyPart)mimeBodyPart);
/* 130 */       mimeMultipart.addBodyPart((BodyPart)attachment);
/*     */       
/* 132 */       message.setContent((Multipart)mimeMultipart);
/*     */     } else {
/* 134 */       message.setContent(body, "text/html; charset=utf-8");
/*     */     } 
/*     */     
/* 137 */     try (Transport transport = session.getTransport()) {
/* 138 */       ((StatisticsManager)Main.getInjector().getInstance(StatisticsManager.class)).registerMail();
/* 139 */       transport.connect(properties
/* 140 */           .getProperty("mail.smtp.host"), properties
/* 141 */           .getProperty("mail.smtp.username"), properties
/* 142 */           .getProperty("mail.smtp.password"));
/* 143 */       transport.sendMessage((Message)message, message.getAllRecipients());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\database\MailManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */