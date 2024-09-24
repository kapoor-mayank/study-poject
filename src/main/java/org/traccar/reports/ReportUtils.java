/*     */ package org.traccar.reports;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.RoundingMode;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.TimeZone;
/*     */ import org.apache.velocity.tools.generic.DateTool;
/*     */ import org.apache.velocity.tools.generic.NumberTool;
/*     */ import org.jxls.area.Area;
/*     */ import org.jxls.builder.xls.XlsCommentAreaBuilder;
/*     */ import org.jxls.common.CellRef;
/*     */ import org.jxls.common.Context;
/*     */ import org.jxls.formula.FormulaProcessor;
/*     */ import org.jxls.formula.StandardFormulaProcessor;
/*     */ import org.jxls.transform.Transformer;
/*     */ import org.jxls.transform.poi.PoiTransformer;
/*     */ import org.jxls.util.TransformerFactory;
/*     */ import org.traccar.Context;
/*     */ import org.traccar.database.DeviceManager;
/*     */ import org.traccar.database.IdentityManager;
/*     */ import org.traccar.handler.events.MotionEventHandler;
/*     */ import org.traccar.model.DeviceState;
/*     */ import org.traccar.model.Driver;
/*     */ import org.traccar.model.Event;
/*     */ import org.traccar.model.Position;
/*     */ import org.traccar.reports.model.StopReport;
/*     */ import org.traccar.reports.model.TripReport;
/*     */ import org.traccar.reports.model.TripsConfig;
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
/*     */ public final class ReportUtils
/*     */ {
/*     */   public static void checkPeriodLimit(Date from, Date to) {
/*  60 */     long limit = Context.getConfig().getLong("report.periodLimit") * 1000L;
/*  61 */     if (limit > 0L && to.getTime() - from.getTime() > limit) {
/*  62 */       throw new IllegalArgumentException("Time period exceeds the limit");
/*     */     }
/*     */   }
/*     */   
/*     */   public static String getDistanceUnit(long userId) {
/*  67 */     return (String)Context.getPermissionsManager().lookupAttribute(userId, "distanceUnit", "km");
/*     */   }
/*     */   
/*     */   public static String getSpeedUnit(long userId) {
/*  71 */     return (String)Context.getPermissionsManager().lookupAttribute(userId, "speedUnit", "kn");
/*     */   }
/*     */   
/*     */   public static String getVolumeUnit(long userId) {
/*  75 */     return (String)Context.getPermissionsManager().lookupAttribute(userId, "volumeUnit", "ltr");
/*     */   }
/*     */   
/*     */   public static TimeZone getTimezone(long userId) {
/*  79 */     String timezone = (String)Context.getPermissionsManager().lookupAttribute(userId, "timezone", null);
/*  80 */     return (timezone != null) ? TimeZone.getTimeZone(timezone) : TimeZone.getDefault();
/*     */   }
/*     */   
/*     */   public static Collection<Long> getDeviceList(Collection<Long> deviceIds, Collection<Long> groupIds) {
/*  84 */     Collection<Long> result = new ArrayList<>();
/*  85 */     result.addAll(deviceIds);
/*  86 */     for (Iterator<Long> iterator = groupIds.iterator(); iterator.hasNext(); ) { long groupId = ((Long)iterator.next()).longValue();
/*  87 */       result.addAll(Context.getPermissionsManager().getGroupDevices(groupId)); }
/*     */     
/*  89 */     return result;
/*     */   }
/*     */   
/*     */   public static double calculateDistance(Position firstPosition, Position lastPosition) {
/*  93 */     return calculateDistance(firstPosition, lastPosition, true);
/*     */   }
/*     */   
/*     */   public static double calculateDistance(Position firstPosition, Position lastPosition, boolean useOdometer) {
/*  97 */     double distance = 0.0D;
/*  98 */     double firstOdometer = firstPosition.getDouble("odometer");
/*  99 */     double lastOdometer = lastPosition.getDouble("odometer");
/*     */     
/* 101 */     if (useOdometer && (firstOdometer != 0.0D || lastOdometer != 0.0D)) {
/* 102 */       distance = lastOdometer - firstOdometer;
/* 103 */     } else if (firstPosition.getAttributes().containsKey("totalDistance") && lastPosition
/* 104 */       .getAttributes().containsKey("totalDistance")) {
/*     */       
/* 106 */       distance = lastPosition.getDouble("totalDistance") - firstPosition.getDouble("totalDistance");
/*     */     } 
/* 108 */     return distance;
/*     */   }
/*     */ 
/*     */   
/*     */   public static double calculateFuel(Position firstPosition, Position lastPosition) {
/* 113 */     if (firstPosition.getAttributes().get("fuel") != null && lastPosition
/* 114 */       .getAttributes().get("fuel") != null) {
/*     */ 
/*     */       
/* 117 */       BigDecimal value = new BigDecimal(firstPosition.getDouble("fuel") - lastPosition.getDouble("fuel"));
/* 118 */       return value.setScale(1, RoundingMode.HALF_EVEN).doubleValue();
/*     */     } 
/* 120 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public static String findDriver(Position firstPosition, Position lastPosition) {
/* 124 */     if (firstPosition.getAttributes().containsKey("driverUniqueId"))
/* 125 */       return firstPosition.getString("driverUniqueId"); 
/* 126 */     if (lastPosition.getAttributes().containsKey("driverUniqueId")) {
/* 127 */       return lastPosition.getString("driverUniqueId");
/*     */     }
/* 129 */     return null;
/*     */   }
/*     */   
/*     */   public static String findDriverName(String driverUniqueId) {
/* 133 */     if (driverUniqueId != null && Context.getDriversManager() != null) {
/* 134 */       Driver driver = Context.getDriversManager().getDriverByUniqueId(driverUniqueId);
/* 135 */       if (driver != null) {
/* 136 */         return driver.getName();
/*     */       }
/*     */     } 
/* 139 */     return null;
/*     */   }
/*     */   
/*     */   public static Context initializeContext(long userId) {
/* 143 */     Context jxlsContext = PoiTransformer.createInitialContext();
/* 144 */     jxlsContext.putVar("distanceUnit", getDistanceUnit(userId));
/* 145 */     jxlsContext.putVar("speedUnit", getSpeedUnit(userId));
/* 146 */     jxlsContext.putVar("volumeUnit", getVolumeUnit(userId));
/* 147 */     jxlsContext.putVar("webUrl", Context.getVelocityEngine().getProperty("web.url"));
/* 148 */     jxlsContext.putVar("dateTool", new DateTool());
/* 149 */     jxlsContext.putVar("numberTool", new NumberTool());
/* 150 */     jxlsContext.putVar("timezone", getTimezone(userId));
/* 151 */     jxlsContext.putVar("locale", Locale.getDefault());
/* 152 */     jxlsContext.putVar("bracketsRegex", "[\\{\\}\"]");
/* 153 */     return jxlsContext;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void processTemplateWithSheets(InputStream templateStream, OutputStream targetStream, Context jxlsContext) throws IOException {
/* 160 */     Transformer transformer = TransformerFactory.createTransformer(templateStream, targetStream);
/* 161 */     List<Area> xlsAreas = (new XlsCommentAreaBuilder(transformer)).build();
/* 162 */     for (Area xlsArea : xlsAreas) {
/* 163 */       xlsArea.applyAt(new CellRef(xlsArea.getStartCellRef().getCellName()), jxlsContext);
/* 164 */       xlsArea.setFormulaProcessor((FormulaProcessor)new StandardFormulaProcessor());
/* 165 */       xlsArea.processFormulas();
/*     */     } 
/* 167 */     transformer.deleteSheet(((Area)xlsAreas.get(0)).getStartCellRef().getSheetName());
/* 168 */     transformer.write();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static TripReport calculateTrip(ArrayList<Position> positions, int startIndex, int endIndex, boolean ignoreOdometer) {
/* 174 */     Position startTrip = positions.get(startIndex);
/* 175 */     Position endTrip = positions.get(endIndex);
/*     */     
/* 177 */     double speedMax = 0.0D;
/* 178 */     double speedSum = 0.0D;
/* 179 */     for (int i = startIndex; i <= endIndex; i++) {
/* 180 */       double speed = ((Position)positions.get(i)).getSpeed();
/* 181 */       speedSum += speed;
/* 182 */       if (speed > speedMax) {
/* 183 */         speedMax = speed;
/*     */       }
/*     */     } 
/*     */     
/* 187 */     TripReport trip = new TripReport();
/*     */     
/* 189 */     long tripDuration = endTrip.getFixTime().getTime() - startTrip.getFixTime().getTime();
/* 190 */     long deviceId = startTrip.getDeviceId();
/* 191 */     trip.setDeviceId(deviceId);
/* 192 */     trip.setDeviceName(Context.getIdentityManager().getById(deviceId).getName());
/*     */     
/* 194 */     trip.setStartPositionId(startTrip.getId());
/* 195 */     trip.setStartLat(startTrip.getLatitude());
/* 196 */     trip.setStartLon(startTrip.getLongitude());
/* 197 */     trip.setStartTime(startTrip.getFixTime());
/* 198 */     String startAddress = startTrip.getAddress();
/* 199 */     if (startAddress == null && Context.getGeocoder() != null && 
/* 200 */       Context.getConfig().getBoolean("geocoder.onRequest")) {
/* 201 */       startAddress = Context.getGeocoder().getAddress(startTrip.getLatitude(), startTrip.getLongitude(), null);
/*     */     }
/* 203 */     trip.setStartAddress(startAddress);
/*     */     
/* 205 */     trip.setEndPositionId(endTrip.getId());
/* 206 */     trip.setEndLat(endTrip.getLatitude());
/* 207 */     trip.setEndLon(endTrip.getLongitude());
/* 208 */     trip.setEndTime(endTrip.getFixTime());
/* 209 */     String endAddress = endTrip.getAddress();
/* 210 */     if (endAddress == null && Context.getGeocoder() != null && 
/* 211 */       Context.getConfig().getBoolean("geocoder.onRequest")) {
/* 212 */       endAddress = Context.getGeocoder().getAddress(endTrip.getLatitude(), endTrip.getLongitude(), null);
/*     */     }
/* 214 */     trip.setEndAddress(endAddress);
/*     */     
/* 216 */     trip.setDistance(calculateDistance(startTrip, endTrip, !ignoreOdometer));
/* 217 */     trip.setDuration(tripDuration);
/* 218 */     trip.setAverageSpeed(Double.valueOf(speedSum / (endIndex - startIndex)));
/* 219 */     trip.setMaxSpeed(speedMax);
/* 220 */     trip.setSpentFuel(calculateFuel(startTrip, endTrip));
/*     */     
/* 222 */     trip.setDriverUniqueId(findDriver(startTrip, endTrip));
/* 223 */     trip.setDriverName(findDriverName(trip.getDriverUniqueId()));
/*     */     
/* 225 */     if (!ignoreOdometer && startTrip
/* 226 */       .getDouble("odometer") != 0.0D && endTrip
/* 227 */       .getDouble("odometer") != 0.0D) {
/* 228 */       trip.setStartOdometer(startTrip.getDouble("odometer"));
/* 229 */       trip.setEndOdometer(endTrip.getDouble("odometer"));
/*     */     } else {
/* 231 */       trip.setStartOdometer(startTrip.getDouble("totalDistance"));
/* 232 */       trip.setEndOdometer(endTrip.getDouble("totalDistance"));
/*     */     } 
/*     */     
/* 235 */     return trip;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static StopReport calculateStop(ArrayList<Position> positions, int startIndex, int endIndex, boolean ignoreOdometer) {
/* 241 */     Position startStop = positions.get(startIndex);
/* 242 */     Position endStop = positions.get(endIndex);
/*     */     
/* 244 */     StopReport stop = new StopReport();
/*     */     
/* 246 */     long deviceId = startStop.getDeviceId();
/* 247 */     stop.setDeviceId(deviceId);
/* 248 */     stop.setDeviceName(Context.getIdentityManager().getById(deviceId).getName());
/*     */     
/* 250 */     stop.setPositionId(startStop.getId());
/* 251 */     stop.setLatitude(startStop.getLatitude());
/* 252 */     stop.setLongitude(startStop.getLongitude());
/* 253 */     stop.setStartTime(startStop.getFixTime());
/* 254 */     String address = startStop.getAddress();
/* 255 */     if (address == null && Context.getGeocoder() != null && 
/* 256 */       Context.getConfig().getBoolean("geocoder.onRequest")) {
/* 257 */       address = Context.getGeocoder().getAddress(stop.getLatitude(), stop.getLongitude(), null);
/*     */     }
/* 259 */     stop.setAddress(address);
/*     */     
/* 261 */     stop.setEndTime(endStop.getFixTime());
/*     */     
/* 263 */     long stopDuration = endStop.getFixTime().getTime() - startStop.getFixTime().getTime();
/* 264 */     stop.setDuration(stopDuration);
/* 265 */     stop.setSpentFuel(calculateFuel(startStop, endStop));
/*     */     
/* 267 */     long engineHours = 0L;
/* 268 */     if (startStop.getAttributes().containsKey("hours") && endStop
/* 269 */       .getAttributes().containsKey("hours")) {
/* 270 */       engineHours = endStop.getLong("hours") - startStop.getLong("hours");
/* 271 */     } else if (Context.getConfig().getBoolean("processing.engineHours.enable")) {
/*     */       
/* 273 */       for (int i = startIndex + 1; i <= endIndex; i++) {
/* 274 */         if (((Position)positions.get(i)).getBoolean("ignition") && ((Position)positions
/* 275 */           .get(i - 1)).getBoolean("ignition")) {
/* 276 */           engineHours += ((Position)positions.get(i)).getFixTime().getTime() - ((Position)positions
/* 277 */             .get(i - 1)).getFixTime().getTime();
/*     */         }
/*     */       } 
/*     */     } 
/* 281 */     stop.setEngineHours(engineHours);
/*     */     
/* 283 */     if (!ignoreOdometer && startStop
/* 284 */       .getDouble("odometer") != 0.0D && endStop
/* 285 */       .getDouble("odometer") != 0.0D) {
/* 286 */       stop.setStartOdometer(startStop.getDouble("odometer"));
/* 287 */       stop.setEndOdometer(endStop.getDouble("odometer"));
/*     */     } else {
/* 289 */       stop.setStartOdometer(startStop.getDouble("totalDistance"));
/* 290 */       stop.setEndOdometer(endStop.getDouble("totalDistance"));
/*     */     } 
/*     */     
/* 293 */     return stop;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static <T extends org.traccar.reports.model.BaseReport> T calculateTripOrStop(ArrayList<Position> positions, int startIndex, int endIndex, boolean ignoreOdometer, Class<T> reportClass) {
/* 300 */     if (reportClass.equals(TripReport.class)) {
/* 301 */       return (T)calculateTrip(positions, startIndex, endIndex, ignoreOdometer);
/*     */     }
/* 303 */     return (T)calculateStop(positions, startIndex, endIndex, ignoreOdometer);
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean isMoving(ArrayList<Position> positions, int index, TripsConfig tripsConfig) {
/* 308 */     if (tripsConfig.getMinimalNoDataDuration() > 0L) {
/*     */ 
/*     */       
/* 311 */       boolean beforeGap = (index < positions.size() - 1 && ((Position)positions.get(index + 1)).getFixTime().getTime() - ((Position)positions.get(index)).getFixTime().getTime() >= tripsConfig.getMinimalNoDataDuration());
/*     */ 
/*     */       
/* 314 */       boolean afterGap = (index > 0 && ((Position)positions.get(index)).getFixTime().getTime() - ((Position)positions.get(index - 1)).getFixTime().getTime() >= tripsConfig.getMinimalNoDataDuration());
/* 315 */       if (beforeGap || afterGap) {
/* 316 */         return false;
/*     */       }
/*     */     } 
/* 319 */     if (((Position)positions.get(index)).getAttributes().containsKey("motion") && ((Position)positions
/* 320 */       .get(index)).getAttributes().get("motion") instanceof Boolean) {
/* 321 */       return ((Position)positions.get(index)).getBoolean("motion");
/*     */     }
/* 323 */     return (((Position)positions.get(index)).getSpeed() > tripsConfig.getSpeedThreshold());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T extends org.traccar.reports.model.BaseReport> Collection<T> detectTripsAndStops(IdentityManager identityManager, DeviceManager deviceManager, Collection<Position> positionCollection, TripsConfig tripsConfig, boolean ignoreOdometer, Class<T> reportClass) {
/* 332 */     Collection<T> result = new ArrayList<>();
/*     */     
/* 334 */     ArrayList<Position> positions = new ArrayList<>(positionCollection);
/* 335 */     if (!positions.isEmpty()) {
/* 336 */       boolean trips = reportClass.equals(TripReport.class);
/* 337 */       MotionEventHandler motionHandler = new MotionEventHandler(identityManager, deviceManager, tripsConfig);
/* 338 */       DeviceState deviceState = new DeviceState();
/* 339 */       deviceState.setMotionState(isMoving(positions, 0, tripsConfig));
/* 340 */       int startEventIndex = (trips == deviceState.getMotionState().booleanValue()) ? 0 : -1;
/* 341 */       int startNoEventIndex = -1;
/* 342 */       for (int i = 0; i < positions.size(); i++) {
/* 343 */         Map<Event, Position> event = motionHandler.updateMotionState(deviceState, positions.get(i), 
/* 344 */             isMoving(positions, i, tripsConfig));
/* 345 */         if (startEventIndex == -1 && ((trips != deviceState
/* 346 */           .getMotionState().booleanValue() && deviceState.getMotionPosition() != null) || (trips == deviceState
/* 347 */           .getMotionState().booleanValue() && event != null))) {
/* 348 */           startEventIndex = i;
/* 349 */           startNoEventIndex = -1;
/* 350 */         } else if (trips != deviceState.getMotionState().booleanValue() && startEventIndex != -1 && deviceState
/* 351 */           .getMotionPosition() == null && event == null) {
/* 352 */           startEventIndex = -1;
/*     */         } 
/* 354 */         if (startNoEventIndex == -1 && ((trips == deviceState
/* 355 */           .getMotionState().booleanValue() && deviceState.getMotionPosition() != null) || (trips != deviceState
/* 356 */           .getMotionState().booleanValue() && event != null))) {
/* 357 */           startNoEventIndex = i;
/* 358 */         } else if (startNoEventIndex != -1 && deviceState.getMotionPosition() == null && event == null) {
/* 359 */           startNoEventIndex = -1;
/*     */         } 
/* 361 */         if (startEventIndex != -1 && startNoEventIndex != -1 && event != null && trips != deviceState
/* 362 */           .getMotionState().booleanValue()) {
/* 363 */           result.add(calculateTripOrStop(positions, startEventIndex, startNoEventIndex, ignoreOdometer, reportClass));
/*     */           
/* 365 */           startEventIndex = -1;
/*     */         } 
/*     */       } 
/* 368 */       if (startEventIndex != -1 && (startNoEventIndex != -1 || !trips)) {
/* 369 */         result.add(calculateTripOrStop(positions, startEventIndex, (startNoEventIndex != -1) ? startNoEventIndex : (positions
/* 370 */               .size() - 1), ignoreOdometer, reportClass));
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 375 */     return result;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\reports\ReportUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */