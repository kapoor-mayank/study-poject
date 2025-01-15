/*     */ package org.traccar.reports;
/*     */ 
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ //import org.jxls.common.Context;
/*     */ import org.jxls.util.JxlsHelper;
/*     */ import org.traccar.Context;
/*     */ import org.traccar.model.Position;
/*     */ import org.traccar.reports.model.SummaryReport;
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
/*     */ public final class Summary
/*     */ {
/*     */   private static SummaryReport calculateSummaryResult(long deviceId, Date from, Date to) throws SQLException {
/*  39 */     SummaryReport result = new SummaryReport();
/*  40 */     result.setDeviceId(deviceId);
/*  41 */     result.setDeviceName(Context.getIdentityManager().getById(deviceId).getName());
/*  42 */     Collection<Position> positions = Context.getDataManager().getPositions(deviceId, from, to);
/*  43 */     if (positions != null && !positions.isEmpty()) {
/*  44 */       Position firstPosition = null;
/*  45 */       Position previousPosition = null;
/*  46 */       double speedSum = 0.0D;
/*  47 */       boolean engineHoursEnabled = Context.getConfig().getBoolean("processing.engineHours.enable");
/*  48 */       for (Position position : positions) {
/*  49 */         if (firstPosition == null) {
/*  50 */           firstPosition = position;
/*     */         }
/*  52 */         if (engineHoursEnabled && previousPosition != null && position
/*  53 */           .getBoolean("ignition") && previousPosition
/*  54 */           .getBoolean("ignition"))
/*     */         {
/*  56 */           result.addEngineHours(position.getFixTime().getTime() - previousPosition
/*  57 */               .getFixTime().getTime());
/*     */         }
/*  59 */         previousPosition = position;
/*  60 */         speedSum += position.getSpeed();
/*  61 */         result.setMaxSpeed(position.getSpeed());
/*     */       } 
/*     */       
/*  64 */       boolean ignoreOdometer = Context.getDeviceManager().lookupAttributeBoolean(deviceId, "report.ignoreOdometer", false, true);
/*  65 */       result.setDistance(ReportUtils.calculateDistance(firstPosition, previousPosition, !ignoreOdometer));
/*  66 */       result.setAverageSpeed(Double.valueOf(speedSum / positions.size()));
/*  67 */       result.setSpentFuel(ReportUtils.calculateFuel(firstPosition, previousPosition));
/*     */       
/*  69 */       if (engineHoursEnabled && firstPosition
/*  70 */         .getAttributes().containsKey("hours") && previousPosition
/*  71 */         .getAttributes().containsKey("hours")) {
/*  72 */         result.setEngineHours(previousPosition
/*  73 */             .getLong("hours") - firstPosition.getLong("hours"));
/*     */       }
/*     */       
/*  76 */       if (!ignoreOdometer && firstPosition
/*  77 */         .getDouble("odometer") != 0.0D && previousPosition
/*  78 */         .getDouble("odometer") != 0.0D) {
/*  79 */         result.setStartOdometer(firstPosition.getDouble("odometer"));
/*  80 */         result.setEndOdometer(previousPosition.getDouble("odometer"));
/*     */       } else {
/*  82 */         result.setStartOdometer(firstPosition.getDouble("totalDistance"));
/*  83 */         result.setEndOdometer(previousPosition.getDouble("totalDistance"));
/*     */       } 
/*     */     } 
/*     */     
/*  87 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Collection<SummaryReport> getObjects(long userId, Collection<Long> deviceIds, Collection<Long> groupIds, Date from, Date to) throws SQLException {
/*  92 */     ReportUtils.checkPeriodLimit(from, to);
/*  93 */     ArrayList<SummaryReport> result = new ArrayList<>();
/*  94 */     for (Iterator<Long> iterator = ReportUtils.getDeviceList(deviceIds, groupIds).iterator(); iterator.hasNext(); ) { long deviceId = ((Long)iterator.next()).longValue();
/*  95 */       Context.getPermissionsManager().checkDevice(userId, deviceId);
/*  96 */       result.add(calculateSummaryResult(deviceId, from, to)); }
/*     */     
/*  98 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void getExcel(OutputStream outputStream, long userId, Collection<Long> deviceIds, Collection<Long> groupIds, Date from, Date to) throws SQLException, IOException {
/* 104 */     ReportUtils.checkPeriodLimit(from, to);
/* 105 */     Collection<SummaryReport> summaries = getObjects(userId, deviceIds, groupIds, from, to);
/* 106 */     String templatePath = Context.getConfig().getString("report.templatesPath", "templates/export/");
/*     */     
/* 108 */     try (InputStream inputStream = new FileInputStream(templatePath + "/summary.xlsx")) {
/* 109 */       org.jxls.common.Context jxlsContext = ReportUtils.initializeContext(userId);
/* 110 */       jxlsContext.putVar("summaries", summaries);
/* 111 */       jxlsContext.putVar("from", from);
/* 112 */       jxlsContext.putVar("to", to);
/* 113 */       JxlsHelper.getInstance().setUseFastFormulaProcessor(false)
/* 114 */         .processTemplate(inputStream, outputStream, jxlsContext);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\reports\Summary.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */