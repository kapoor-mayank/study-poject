/*     */ package org.traccar.notification;
/*     */ 
/*     */ import java.io.StringWriter;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.nio.file.Paths;
/*     */ import java.util.Locale;
/*     */ import org.apache.velocity.Template;
/*     */ import org.apache.velocity.VelocityContext;
/*     */ import org.apache.velocity.context.Context;
/*     */ import org.apache.velocity.exception.ResourceNotFoundException;
/*     */ import org.apache.velocity.tools.generic.DateTool;
/*     */ import org.apache.velocity.tools.generic.NumberTool;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.traccar.Context;
/*     */ import org.traccar.model.Device;
/*     */ import org.traccar.model.Event;
/*     */ import org.traccar.model.Position;
/*     */ import org.traccar.model.User;
/*     */ import org.traccar.reports.ReportUtils;
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
/*     */ public final class NotificationFormatter
/*     */ {
/*  40 */   private static final Logger LOGGER = LoggerFactory.getLogger(NotificationFormatter.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static VelocityContext prepareContext(long userId, Event event, Position position) {
/*  47 */     User user = Context.getPermissionsManager().getUser(userId);
/*  48 */     Device device = Context.getIdentityManager().getById(event.getDeviceId());
/*     */     
/*  50 */     VelocityContext velocityContext = new VelocityContext();
/*  51 */     velocityContext.put("user", user);
/*  52 */     velocityContext.put("device", device);
/*  53 */     velocityContext.put("event", event);
/*  54 */     if (position != null) {
/*  55 */       velocityContext.put("position", position);
/*  56 */       velocityContext.put("speedUnit", ReportUtils.getSpeedUnit(userId));
/*  57 */       velocityContext.put("distanceUnit", ReportUtils.getDistanceUnit(userId));
/*  58 */       velocityContext.put("volumeUnit", ReportUtils.getVolumeUnit(userId));
/*     */     } 
/*  60 */     if (event.getGeofenceId() != 0L) {
/*  61 */       velocityContext.put("geofence", Context.getGeofenceManager().getById(event.getGeofenceId()));
/*     */     }
/*  63 */     if (event.getMaintenanceId() != 0L) {
/*  64 */       velocityContext.put("maintenance", Context.getMaintenancesManager().getById(event.getMaintenanceId()));
/*     */     }
/*  66 */     String driverUniqueId = event.getString("driverUniqueId");
/*  67 */     if (driverUniqueId != null) {
/*  68 */       velocityContext.put("driver", Context.getDriversManager().getDriverByUniqueId(driverUniqueId));
/*     */     }
/*  70 */     velocityContext.put("webUrl", Context.getVelocityEngine().getProperty("web.url"));
/*  71 */     velocityContext.put("dateTool", new DateTool());
/*  72 */     velocityContext.put("numberTool", new NumberTool());
/*  73 */     velocityContext.put("timezone", ReportUtils.getTimezone(userId));
/*  74 */     velocityContext.put("locale", Locale.getDefault());
/*  75 */     return velocityContext;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Template getTemplate(Event event, String path) {
/*     */     Template template;
/*     */     try {
/*  84 */       String templateFilePath = Paths.get(path, new String[] { event.getType() + ".vm" }).toString();
/*  85 */       template = Context.getVelocityEngine().getTemplate(templateFilePath, StandardCharsets.UTF_8.name());
/*  86 */     } catch (ResourceNotFoundException error) {
/*  87 */       LOGGER.warn("Notification template error", (Throwable)error);
/*  88 */       String templateFilePath = Paths.get(path, new String[] { "unknown.vm" }).toString();
/*  89 */       template = Context.getVelocityEngine().getTemplate(templateFilePath, StandardCharsets.UTF_8.name());
/*     */     } 
/*  91 */     return template;
/*     */   }
/*     */   
/*     */   public static FullMessage formatFullMessage(long userId, Event event, Position position) {
/*  95 */     VelocityContext velocityContext = prepareContext(userId, event, position);
/*  96 */     String formattedMessage = formatMessage(velocityContext, Long.valueOf(userId), event, position, "full");
/*     */     
/*  98 */     return new FullMessage((String)velocityContext.get("subject"), formattedMessage);
/*     */   }
/*     */   
/*     */   public static String formatShortMessage(long userId, Event event, Position position) {
/* 102 */     return formatMessage(null, Long.valueOf(userId), event, position, "short");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static String formatMessage(VelocityContext vc, Long userId, Event event, Position position, String templatePath) {
/* 108 */     VelocityContext velocityContext = vc;
/* 109 */     if (velocityContext == null) {
/* 110 */       velocityContext = prepareContext(userId.longValue(), event, position);
/*     */     }
/* 112 */     StringWriter writer = new StringWriter();
/* 113 */     getTemplate(event, templatePath).merge((Context)velocityContext, writer);
/*     */     
/* 115 */     return writer.toString();
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\notification\NotificationFormatter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */