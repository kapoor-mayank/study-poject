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
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import org.apache.poi.ss.util.WorkbookUtil;
/*     */ import org.jxls.common.Context;
/*     */ import org.traccar.Context;
/*     */ import org.traccar.model.Device;
/*     */ import org.traccar.model.Event;
/*     */ import org.traccar.model.Geofence;
/*     */ import org.traccar.model.Group;
/*     */ import org.traccar.model.Maintenance;
/*     */ import org.traccar.reports.model.DeviceReport;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Events
/*     */ {
/*     */   public static Collection<Event> getObjects(long userId, Collection<Long> deviceIds, Collection<Long> groupIds, Collection<String> types, Date from, Date to) throws SQLException {
/*     */     // Byte code:
/*     */     //   0: aload #5
/*     */     //   2: aload #6
/*     */     //   4: invokestatic checkPeriodLimit : (Ljava/util/Date;Ljava/util/Date;)V
/*     */     //   7: new java/util/ArrayList
/*     */     //   10: dup
/*     */     //   11: invokespecial <init> : ()V
/*     */     //   14: astore #7
/*     */     //   16: aload_2
/*     */     //   17: aload_3
/*     */     //   18: invokestatic getDeviceList : (Ljava/util/Collection;Ljava/util/Collection;)Ljava/util/Collection;
/*     */     //   21: invokeinterface iterator : ()Ljava/util/Iterator;
/*     */     //   26: astore #8
/*     */     //   28: aload #8
/*     */     //   30: invokeinterface hasNext : ()Z
/*     */     //   35: ifeq -> 222
/*     */     //   38: aload #8
/*     */     //   40: invokeinterface next : ()Ljava/lang/Object;
/*     */     //   45: checkcast java/lang/Long
/*     */     //   48: invokevirtual longValue : ()J
/*     */     //   51: lstore #9
/*     */     //   53: invokestatic getPermissionsManager : ()Lorg/traccar/database/PermissionsManager;
/*     */     //   56: lload_0
/*     */     //   57: lload #9
/*     */     //   59: invokevirtual checkDevice : (JJ)V
/*     */     //   62: invokestatic getDataManager : ()Lorg/traccar/database/DataManager;
/*     */     //   65: lload #9
/*     */     //   67: aload #5
/*     */     //   69: aload #6
/*     */     //   71: invokevirtual getEvents : (JLjava/util/Date;Ljava/util/Date;)Ljava/util/Collection;
/*     */     //   74: astore #11
/*     */     //   76: aload #4
/*     */     //   78: invokeinterface isEmpty : ()Z
/*     */     //   83: ifne -> 98
/*     */     //   86: aload #4
/*     */     //   88: ldc 'allEvents'
/*     */     //   90: invokeinterface contains : (Ljava/lang/Object;)Z
/*     */     //   95: ifeq -> 102
/*     */     //   98: iconst_1
/*     */     //   99: goto -> 103
/*     */     //   102: iconst_0
/*     */     //   103: istore #12
/*     */     //   105: aload #11
/*     */     //   107: invokeinterface iterator : ()Ljava/util/Iterator;
/*     */     //   112: astore #13
/*     */     //   114: aload #13
/*     */     //   116: invokeinterface hasNext : ()Z
/*     */     //   121: ifeq -> 219
/*     */     //   124: aload #13
/*     */     //   126: invokeinterface next : ()Ljava/lang/Object;
/*     */     //   131: checkcast org/traccar/model/Event
/*     */     //   134: astore #14
/*     */     //   136: iload #12
/*     */     //   138: ifne -> 156
/*     */     //   141: aload #4
/*     */     //   143: aload #14
/*     */     //   145: invokevirtual getType : ()Ljava/lang/String;
/*     */     //   148: invokeinterface contains : (Ljava/lang/Object;)Z
/*     */     //   153: ifeq -> 216
/*     */     //   156: aload #14
/*     */     //   158: invokevirtual getGeofenceId : ()J
/*     */     //   161: lstore #15
/*     */     //   163: aload #14
/*     */     //   165: invokevirtual getMaintenanceId : ()J
/*     */     //   168: lstore #17
/*     */     //   170: lload #15
/*     */     //   172: lconst_0
/*     */     //   173: lcmp
/*     */     //   174: ifeq -> 189
/*     */     //   177: invokestatic getGeofenceManager : ()Lorg/traccar/database/GeofenceManager;
/*     */     //   180: lload_0
/*     */     //   181: lload #15
/*     */     //   183: invokevirtual checkItemPermission : (JJ)Z
/*     */     //   186: ifeq -> 216
/*     */     //   189: lload #17
/*     */     //   191: lconst_0
/*     */     //   192: lcmp
/*     */     //   193: ifeq -> 208
/*     */     //   196: invokestatic getMaintenancesManager : ()Lorg/traccar/database/MaintenancesManager;
/*     */     //   199: lload_0
/*     */     //   200: lload #17
/*     */     //   202: invokevirtual checkItemPermission : (JJ)Z
/*     */     //   205: ifeq -> 216
/*     */     //   208: aload #7
/*     */     //   210: aload #14
/*     */     //   212: invokevirtual add : (Ljava/lang/Object;)Z
/*     */     //   215: pop
/*     */     //   216: goto -> 114
/*     */     //   219: goto -> 28
/*     */     //   222: aload #7
/*     */     //   224: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #46	-> 0
/*     */     //   #47	-> 7
/*     */     //   #48	-> 16
/*     */     //   #49	-> 53
/*     */     //   #50	-> 62
/*     */     //   #51	-> 76
/*     */     //   #52	-> 105
/*     */     //   #53	-> 136
/*     */     //   #54	-> 156
/*     */     //   #55	-> 163
/*     */     //   #56	-> 170
/*     */     //   #58	-> 196
/*     */     //   #59	-> 208
/*     */     //   #62	-> 216
/*     */     //   #63	-> 219
/*     */     //   #64	-> 222
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   163	53	15	geofenceId	J
/*     */     //   170	46	17	maintenanceId	J
/*     */     //   136	80	14	event	Lorg/traccar/model/Event;
/*     */     //   76	143	11	events	Ljava/util/Collection;
/*     */     //   105	114	12	all	Z
/*     */     //   53	166	9	deviceId	J
/*     */     //   0	225	0	userId	J
/*     */     //   0	225	2	deviceIds	Ljava/util/Collection;
/*     */     //   0	225	3	groupIds	Ljava/util/Collection;
/*     */     //   0	225	4	types	Ljava/util/Collection;
/*     */     //   0	225	5	from	Ljava/util/Date;
/*     */     //   0	225	6	to	Ljava/util/Date;
/*     */     //   16	209	7	result	Ljava/util/ArrayList;
/*     */     // Local variable type table:
/*     */     //   start	length	slot	name	signature
/*     */     //   76	143	11	events	Ljava/util/Collection<Lorg/traccar/model/Event;>;
/*     */     //   0	225	2	deviceIds	Ljava/util/Collection<Ljava/lang/Long;>;
/*     */     //   0	225	3	groupIds	Ljava/util/Collection<Ljava/lang/Long;>;
/*     */     //   0	225	4	types	Ljava/util/Collection<Ljava/lang/String;>;
/*     */     //   16	209	7	result	Ljava/util/ArrayList<Lorg/traccar/model/Event;>;
/*     */   }
/*     */   
/*     */   public static void getExcel(OutputStream outputStream, long userId, Collection<Long> deviceIds, Collection<Long> groupIds, Collection<String> types, Date from, Date to) throws SQLException, IOException {
/*  70 */     ReportUtils.checkPeriodLimit(from, to);
/*  71 */     ArrayList<DeviceReport> devicesEvents = new ArrayList<>();
/*  72 */     ArrayList<String> sheetNames = new ArrayList<>();
/*  73 */     HashMap<Long, String> geofenceNames = new HashMap<>();
/*  74 */     HashMap<Long, String> maintenanceNames = new HashMap<>();
/*  75 */     for (Iterator<Long> iterator = ReportUtils.getDeviceList(deviceIds, groupIds).iterator(); iterator.hasNext(); ) { long deviceId = ((Long)iterator.next()).longValue();
/*  76 */       Context.getPermissionsManager().checkDevice(userId, deviceId);
/*  77 */       Collection<Event> events = Context.getDataManager().getEvents(deviceId, from, to);
/*  78 */       boolean all = (types.isEmpty() || types.contains("allEvents"));
/*  79 */       for (Iterator<Event> iterator1 = events.iterator(); iterator1.hasNext(); ) {
/*  80 */         Event event = iterator1.next();
/*  81 */         if (all || types.contains(event.getType())) {
/*  82 */           long geofenceId = event.getGeofenceId();
/*  83 */           long maintenanceId = event.getMaintenanceId();
/*  84 */           if (geofenceId != 0L) {
/*  85 */             if (Context.getGeofenceManager().checkItemPermission(userId, geofenceId)) {
/*  86 */               Geofence geofence = (Geofence)Context.getGeofenceManager().getById(geofenceId);
/*  87 */               if (geofence != null)
/*  88 */                 geofenceNames.put(Long.valueOf(geofenceId), geofence.getName()); 
/*     */               continue;
/*     */             } 
/*  91 */             iterator1.remove(); continue;
/*     */           } 
/*  93 */           if (maintenanceId != 0L) {
/*  94 */             if (Context.getMaintenancesManager().checkItemPermission(userId, maintenanceId)) {
/*  95 */               Maintenance maintenance = (Maintenance)Context.getMaintenancesManager().getById(maintenanceId);
/*  96 */               if (maintenance != null)
/*  97 */                 maintenanceNames.put(Long.valueOf(maintenanceId), maintenance.getName()); 
/*     */               continue;
/*     */             } 
/* 100 */             iterator1.remove();
/*     */           } 
/*     */           continue;
/*     */         } 
/* 104 */         iterator1.remove();
/*     */       } 
/*     */       
/* 107 */       DeviceReport deviceEvents = new DeviceReport();
/* 108 */       Device device = Context.getIdentityManager().getById(deviceId);
/* 109 */       deviceEvents.setDeviceName(device.getName());
/* 110 */       sheetNames.add(WorkbookUtil.createSafeSheetName(deviceEvents.getDeviceName()));
/* 111 */       if (device.getGroupId() != 0L) {
/* 112 */         Group group = (Group)Context.getGroupsManager().getById(device.getGroupId());
/* 113 */         if (group != null) {
/* 114 */           deviceEvents.setGroupName(group.getName());
/*     */         }
/*     */       } 
/* 117 */       deviceEvents.setObjects(events);
/* 118 */       devicesEvents.add(deviceEvents); }
/*     */     
/* 120 */     String templatePath = Context.getConfig().getString("report.templatesPath", "templates/export/");
/*     */     
/* 122 */     try (InputStream inputStream = new FileInputStream(templatePath + "/events.xlsx")) {
/* 123 */       Context jxlsContext = ReportUtils.initializeContext(userId);
/* 124 */       jxlsContext.putVar("devices", devicesEvents);
/* 125 */       jxlsContext.putVar("sheetNames", sheetNames);
/* 126 */       jxlsContext.putVar("geofenceNames", geofenceNames);
/* 127 */       jxlsContext.putVar("maintenanceNames", maintenanceNames);
/* 128 */       jxlsContext.putVar("from", from);
/* 129 */       jxlsContext.putVar("to", to);
/* 130 */       ReportUtils.processTemplateWithSheets(inputStream, outputStream, jxlsContext);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\reports\Events.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */