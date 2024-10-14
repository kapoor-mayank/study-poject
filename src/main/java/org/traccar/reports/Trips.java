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
/*    */ import org.traccar.reports.model.TripReport;
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
/*    */ public final class Trips
/*    */ {
/*    */   private static Collection<TripReport> detectTrips(long deviceId, Date from, Date to) throws SQLException {
/* 45 */     boolean ignoreOdometer = Context.getDeviceManager().lookupAttributeBoolean(deviceId, "report.ignoreOdometer", false, true);
/*    */     
/* 47 */     IdentityManager identityManager = (IdentityManager)Main.getInjector().getInstance(IdentityManager.class);
/* 48 */     DeviceManager deviceManager = (DeviceManager)Main.getInjector().getInstance(DeviceManager.class);
/*    */     
/* 50 */     return ReportUtils.detectTripsAndStops(identityManager, deviceManager, 
/* 51 */         Context.getDataManager().getPositions(deviceId, from, to), 
/* 52 */         Context.getTripsConfig(), ignoreOdometer, TripReport.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public static Collection<TripReport> getObjects(long userId, Collection<Long> deviceIds, Collection<Long> groupIds, Date from, Date to) throws SQLException {
/* 57 */     ReportUtils.checkPeriodLimit(from, to);
/* 58 */     ArrayList<TripReport> result = new ArrayList<>();
/* 59 */     for (Iterator<Long> iterator = ReportUtils.getDeviceList(deviceIds, groupIds).iterator(); iterator.hasNext(); ) { long deviceId = ((Long)iterator.next()).longValue();
/* 60 */       Context.getPermissionsManager().checkDevice(userId, deviceId);
/* 61 */       result.addAll(detectTrips(deviceId, from, to)); }
/*    */     
/* 63 */     return result;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void getExcel(OutputStream outputStream, long userId, Collection<Long> deviceIds, Collection<Long> groupIds, Date from, Date to) throws SQLException, IOException {
/* 69 */     ReportUtils.checkPeriodLimit(from, to);
/* 70 */     ArrayList<DeviceReport> devicesTrips = new ArrayList<>();
/* 71 */     ArrayList<String> sheetNames = new ArrayList<>();
/* 72 */     for (Iterator<Long> iterator = ReportUtils.getDeviceList(deviceIds, groupIds).iterator(); iterator.hasNext(); ) { long deviceId = ((Long)iterator.next()).longValue();
/* 73 */       Context.getPermissionsManager().checkDevice(userId, deviceId);
/* 74 */       Collection<TripReport> trips = detectTrips(deviceId, from, to);
/* 75 */       DeviceReport deviceTrips = new DeviceReport();
/* 76 */       Device device = Context.getIdentityManager().getById(deviceId);
/* 77 */       deviceTrips.setDeviceName(device.getName());
/* 78 */       sheetNames.add(WorkbookUtil.createSafeSheetName(deviceTrips.getDeviceName()));
/* 79 */       if (device.getGroupId() != 0L) {
/* 80 */         Group group = (Group)Context.getGroupsManager().getById(device.getGroupId());
/* 81 */         if (group != null) {
/* 82 */           deviceTrips.setGroupName(group.getName());
/*    */         }
/*    */       } 
/* 85 */       deviceTrips.setObjects(trips);
/* 86 */       devicesTrips.add(deviceTrips); }
/*    */     
/* 88 */     String templatePath = Context.getConfig().getString("report.templatesPath", "templates/export/");
/*    */     
/* 90 */     try (InputStream inputStream = new FileInputStream(templatePath + "/trips.xlsx")) {
/* 91 */       org.jxls.common.Context jxlsContext = ReportUtils.initializeContext(userId);
/* 92 */       jxlsContext.putVar("devices", devicesTrips);
/* 93 */       jxlsContext.putVar("sheetNames", sheetNames);
/* 94 */       jxlsContext.putVar("from", from);
/* 95 */       jxlsContext.putVar("to", to);
/* 96 */       ReportUtils.processTemplateWithSheets(inputStream, outputStream, jxlsContext);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\reports\Trips.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */