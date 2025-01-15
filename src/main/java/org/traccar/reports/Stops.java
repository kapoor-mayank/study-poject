/*    */ package org.traccar.reports;
/*    */ 
/*    */ import java.io.FileInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.OutputStream;
/*    */ import java.sql.SQLException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.Date;
/*    */ import java.util.Iterator;
/*    */ import org.apache.poi.ss.util.WorkbookUtil;
/*    */ //import org.jxls.common.Context;
/*    */ import org.traccar.Context;
/*    */ import org.traccar.Main;
/*    */ import org.traccar.database.DeviceManager;
/*    */ import org.traccar.database.IdentityManager;
/*    */ import org.traccar.model.Device;
/*    */ import org.traccar.model.Group;
/*    */ import org.traccar.reports.model.DeviceReport;
/*    */ import org.traccar.reports.model.StopReport;
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
/*    */ public final class Stops
/*    */ {
/*    */   private static Collection<StopReport> detectStops(long deviceId, Date from, Date to) throws SQLException {
/* 46 */     boolean ignoreOdometer = Context.getDeviceManager().lookupAttributeBoolean(deviceId, "report.ignoreOdometer", false, true);
/*    */     
/* 48 */     IdentityManager identityManager = (IdentityManager)Main.getInjector().getInstance(IdentityManager.class);
/* 49 */     DeviceManager deviceManager = (DeviceManager)Main.getInjector().getInstance(DeviceManager.class);
/*    */     
/* 51 */     return ReportUtils.detectTripsAndStops(identityManager, deviceManager, 
/* 52 */         Context.getDataManager().getPositions(deviceId, from, to), 
/* 53 */         Context.getTripsConfig(), ignoreOdometer, StopReport.class);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static Collection<StopReport> getObjects(long userId, Collection<Long> deviceIds, Collection<Long> groupIds, Date from, Date to) throws SQLException {
/* 59 */     ReportUtils.checkPeriodLimit(from, to);
/* 60 */     ArrayList<StopReport> result = new ArrayList<>();
/* 61 */     for (Iterator<Long> iterator = ReportUtils.getDeviceList(deviceIds, groupIds).iterator(); iterator.hasNext(); ) { long deviceId = ((Long)iterator.next()).longValue();
/* 62 */       Context.getPermissionsManager().checkDevice(userId, deviceId);
/* 63 */       result.addAll(detectStops(deviceId, from, to)); }
/*    */     
/* 65 */     return result;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void getExcel(OutputStream outputStream, long userId, Collection<Long> deviceIds, Collection<Long> groupIds, Date from, Date to) throws SQLException, IOException {
/* 71 */     ReportUtils.checkPeriodLimit(from, to);
/* 72 */     ArrayList<DeviceReport> devicesStops = new ArrayList<>();
/* 73 */     ArrayList<String> sheetNames = new ArrayList<>();
/* 74 */     for (Iterator<Long> iterator = ReportUtils.getDeviceList(deviceIds, groupIds).iterator(); iterator.hasNext(); ) { long deviceId = ((Long)iterator.next()).longValue();
/* 75 */       Context.getPermissionsManager().checkDevice(userId, deviceId);
/* 76 */       Collection<StopReport> stops = detectStops(deviceId, from, to);
/* 77 */       DeviceReport deviceStops = new DeviceReport();
/* 78 */       Device device = Context.getIdentityManager().getById(deviceId);
/* 79 */       deviceStops.setDeviceName(device.getName());
/* 80 */       sheetNames.add(WorkbookUtil.createSafeSheetName(deviceStops.getDeviceName()));
/* 81 */       if (device.getGroupId() != 0L) {
/* 82 */         Group group = (Group)Context.getGroupsManager().getById(device.getGroupId());
/* 83 */         if (group != null) {
/* 84 */           deviceStops.setGroupName(group.getName());
/*    */         }
/*    */       } 
/* 87 */       deviceStops.setObjects(stops);
/* 88 */       devicesStops.add(deviceStops); }
/*    */     
/* 90 */     String templatePath = Context.getConfig().getString("report.templatesPath", "templates/export/");
/*    */     
/* 92 */     try (InputStream inputStream = new FileInputStream(templatePath + "/stops.xlsx")) {
/* 93 */       org.jxls.common.Context jxlsContext = ReportUtils.initializeContext(userId);
/* 94 */       jxlsContext.putVar("devices", devicesStops);
/* 95 */       jxlsContext.putVar("sheetNames", sheetNames);
/* 96 */       jxlsContext.putVar("from", from);
/* 97 */       jxlsContext.putVar("to", to);
/* 98 */       ReportUtils.processTemplateWithSheets(inputStream, outputStream, jxlsContext);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\reports\Stops.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */