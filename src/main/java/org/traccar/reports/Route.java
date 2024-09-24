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
/*    */ import org.jxls.common.Context;
/*    */ import org.traccar.Context;
/*    */ import org.traccar.model.Device;
/*    */ import org.traccar.model.Group;
/*    */ import org.traccar.model.Position;
/*    */ import org.traccar.reports.model.DeviceReport;
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
/*    */ public final class Route
/*    */ {
/*    */   public static Collection<Position> getObjects(long userId, Collection<Long> deviceIds, Collection<Long> groupIds, Date from, Date to) throws SQLException {
/* 42 */     ReportUtils.checkPeriodLimit(from, to);
/* 43 */     ArrayList<Position> result = new ArrayList<>();
/* 44 */     for (Iterator<Long> iterator = ReportUtils.getDeviceList(deviceIds, groupIds).iterator(); iterator.hasNext(); ) { long deviceId = ((Long)iterator.next()).longValue();
/* 45 */       Context.getPermissionsManager().checkDevice(userId, deviceId);
/* 46 */       result.addAll(Context.getDataManager().getPositions(deviceId, from, to)); }
/*    */     
/* 48 */     return result;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void getExcel(OutputStream outputStream, long userId, Collection<Long> deviceIds, Collection<Long> groupIds, Date from, Date to) throws SQLException, IOException {
/* 54 */     ReportUtils.checkPeriodLimit(from, to);
/* 55 */     ArrayList<DeviceReport> devicesRoutes = new ArrayList<>();
/* 56 */     ArrayList<String> sheetNames = new ArrayList<>();
/* 57 */     for (Iterator<Long> iterator = ReportUtils.getDeviceList(deviceIds, groupIds).iterator(); iterator.hasNext(); ) { long deviceId = ((Long)iterator.next()).longValue();
/* 58 */       Context.getPermissionsManager().checkDevice(userId, deviceId);
/*    */       
/* 60 */       Collection<Position> positions = Context.getDataManager().getPositions(deviceId, from, to);
/* 61 */       DeviceReport deviceRoutes = new DeviceReport();
/* 62 */       Device device = Context.getIdentityManager().getById(deviceId);
/* 63 */       deviceRoutes.setDeviceName(device.getName());
/* 64 */       sheetNames.add(WorkbookUtil.createSafeSheetName(deviceRoutes.getDeviceName()));
/* 65 */       if (device.getGroupId() != 0L) {
/* 66 */         Group group = (Group)Context.getGroupsManager().getById(device.getGroupId());
/* 67 */         if (group != null) {
/* 68 */           deviceRoutes.setGroupName(group.getName());
/*    */         }
/*    */       } 
/* 71 */       deviceRoutes.setObjects(positions);
/* 72 */       devicesRoutes.add(deviceRoutes); }
/*    */     
/* 74 */     String templatePath = Context.getConfig().getString("report.templatesPath", "templates/export/");
/*    */     
/* 76 */     try (InputStream inputStream = new FileInputStream(templatePath + "/route.xlsx")) {
/* 77 */       Context jxlsContext = ReportUtils.initializeContext(userId);
/* 78 */       jxlsContext.putVar("devices", devicesRoutes);
/* 79 */       jxlsContext.putVar("sheetNames", sheetNames);
/* 80 */       jxlsContext.putVar("from", from);
/* 81 */       jxlsContext.putVar("to", to);
/* 82 */       ReportUtils.processTemplateWithSheets(inputStream, outputStream, jxlsContext);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\reports\Route.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */