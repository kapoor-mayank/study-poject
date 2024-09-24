/*     */ package org.traccar.model;
/*     */ 
/*     */ import java.util.Date;
/*     */ import org.traccar.Context;
/*     */ import org.traccar.database.QueryIgnore;
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
/*     */ public class Position
/*     */   extends Message
/*     */ {
/*     */   public static final String KEY_ORIGINAL = "raw";
/*     */   public static final String KEY_INDEX = "index";
/*     */   public static final String KEY_HDOP = "hdop";
/*     */   public static final String KEY_VDOP = "vdop";
/*     */   public static final String KEY_PDOP = "pdop";
/*     */   public static final String KEY_SATELLITES = "sat";
/*     */   public static final String KEY_SATELLITES_VISIBLE = "satVisible";
/*     */   public static final String KEY_RSSI = "rssi";
/*     */   public static final String KEY_GPS = "gps";
/*     */   public static final String KEY_ROAMING = "roaming";
/*     */   public static final String KEY_EVENT = "event";
/*     */   public static final String KEY_ALARM = "alarm";
/*     */   public static final String KEY_STATUS = "status";
/*     */   public static final String KEY_ODOMETER = "odometer";
/*     */   public static final String KEY_ODOMETER_SERVICE = "serviceOdometer";
/*     */   public static final String KEY_ODOMETER_TRIP = "tripOdometer";
/*     */   public static final String KEY_HOURS = "hours";
/*     */   public static final String KEY_STEPS = "steps";
/*     */   public static final String KEY_HEART_RATE = "heartRate";
/*     */   public static final String KEY_INPUT = "input";
/*     */   public static final String KEY_OUTPUT = "output";
/*     */   public static final String KEY_IMAGE = "image";
/*     */   public static final String KEY_VIDEO = "video";
/*     */   public static final String KEY_AUDIO = "audio";
/*     */   public static final String KEY_POWER = "power";
/*     */   public static final String KEY_BATTERY = "battery";
/*     */   public static final String KEY_BATTERY_LEVEL = "batteryLevel";
/*     */   public static final String KEY_FUEL_LEVEL = "fuel";
/*     */   public static final String KEY_FUEL_USED = "fuelUsed";
/*     */   public static final String KEY_FUEL_CONSUMPTION = "fuelConsumption";
/*     */   public static final String KEY_VERSION_FW = "versionFw";
/*     */   public static final String KEY_VERSION_HW = "versionHw";
/*     */   public static final String KEY_TYPE = "type";
/*     */   public static final String KEY_IMMOBILIZER = "immobilizer";
/*     */   public static final String KEY_GSM_SIGNAL = "GSMSignal";
/*     */   public static final String KEY_GPS_GLONASS_SIGNAL = "GPSGlonassSignal";
/*     */   public static final String KEY_EXTERNAL_VOLTAGE = "externalVoltage";
/*     */   public static final String KEY_IGNITION = "ignition";
/*     */   public static final String KEY_FLAGS = "flags";
/*     */   public static final String KEY_ANTENNA = "antenna";
/*     */   public static final String KEY_CHARGE = "charge";
/*     */   public static final String KEY_IP = "ip";
/*     */   public static final String KEY_ARCHIVE = "archive";
/*     */   public static final String KEY_DISTANCE = "distance";
/*     */   public static final String KEY_TOTAL_DISTANCE = "totalDistance";
/*     */   public static final String KEY_RPM = "rpm";
/*     */   public static final String KEY_VIN = "vin";
/*     */   public static final String KEY_APPROXIMATE = "approximate";
/*     */   public static final String KEY_THROTTLE = "throttle";
/*     */   public static final String KEY_MOTION = "motion";
/*     */   public static final String KEY_ARMED = "armed";
/*     */   public static final String KEY_GEOFENCE = "geofence";
/*     */   public static final String KEY_ACCELERATION = "acceleration";
/*     */   public static final String KEY_DEVICE_TEMP = "deviceTemp";
/*     */   public static final String KEY_COOLANT_TEMP = "coolantTemp";
/*     */   public static final String KEY_ENGINE_LOAD = "engineLoad";
/*     */   public static final String KEY_OPERATOR = "operator";
/*     */   public static final String KEY_COMMAND = "command";
/*     */   public static final String KEY_BLOCKED = "blocked";
/*     */   public static final String KEY_DOOR = "door";
/*     */   public static final String KEY_AXLE_WEIGHT = "axleWeight";
/*     */   public static final String KEY_G_SENSOR = "gSensor";
/*     */   public static final String KEY_ICCID = "iccid";
/*     */   public static final String KEY_PHONE = "phone";
/*     */   public static final String KEY_SPEED_LIMIT = "speedLimit";
/*     */   public static final String KEY_DRIVING_TIME = "drivingTime";
/*     */   public static final String KEY_DTCS = "dtcs";
/*     */   public static final String KEY_OBD_SPEED = "obdSpeed";
/*     */   public static final String KEY_OBD_ODOMETER = "obdOdometer";
/*     */   public static final String KEY_RESULT = "result";
/*     */   public static final String KEY_DRIVER_UNIQUE_ID = "driverUniqueId";
/*     */   public static final String KEY_CARD = "card";
/*     */   public static final String PREFIX_TEMP = "temp";
/*     */   public static final String PREFIX_ADC = "adc";
/*     */   public static final String PREFIX_IO = "io";
/*     */   public static final String PREFIX_COUNT = "count";
/*     */   public static final String PREFIX_IN = "in";
/*     */   public static final String PREFIX_OUT = "out";
/*     */   public static final String ALARM_GENERAL = "general";
/*     */   public static final String ALARM_SOS = "sos";
/*     */   public static final String ALARM_VIBRATION = "vibration";
/*     */   public static final String ALARM_MOVEMENT = "movement";
/*     */   public static final String ALARM_LOW_SPEED = "lowspeed";
/*     */   public static final String ALARM_OVERSPEED = "overspeed";
/*     */   public static final String ALARM_FALL_DOWN = "fallDown";
/*     */   public static final String ALARM_LOW_POWER = "lowPower";
/*     */   public static final String ALARM_LOW_BATTERY = "lowBattery";
/*     */   public static final String ALARM_FAULT = "fault";
/*     */   public static final String ALARM_POWER_OFF = "powerOff";
/*     */   public static final String ALARM_POWER_ON = "powerOn";
/*     */   public static final String ALARM_DOOR = "door";
/*     */   public static final String ALARM_LOCK = "lock";
/*     */   public static final String ALARM_UNLOCK = "unlock";
/*     */   public static final String ALARM_GEOFENCE = "geofence";
/*     */   public static final String ALARM_GEOFENCE_ENTER = "geofenceEnter";
/*     */   public static final String ALARM_GEOFENCE_EXIT = "geofenceExit";
/*     */   public static final String ALARM_GPS_ANTENNA_CUT = "gpsAntennaCut";
/*     */   public static final String ALARM_ACCIDENT = "accident";
/*     */   public static final String ALARM_TOW = "tow";
/*     */   public static final String ALARM_IDLE = "idle";
/*     */   public static final String ALARM_HIGH_RPM = "highRpm";
/*     */   public static final String ALARM_ACCELERATION = "hardAcceleration";
/*     */   public static final String ALARM_BRAKING = "hardBraking";
/*     */   public static final String ALARM_CORNERING = "hardCornering";
/*     */   public static final String ALARM_LANE_CHANGE = "laneChange";
/*     */   public static final String ALARM_FATIGUE_DRIVING = "fatigueDriving";
/*     */   public static final String ALARM_POWER_CUT = "powerCut";
/*     */   public static final String ALARM_POWER_RESTORED = "powerRestored";
/*     */   public static final String ALARM_JAMMING = "jamming";
/*     */   public static final String ALARM_TEMPERATURE = "temperature";
/*     */   public static final String ALARM_PARKING = "parking";
/*     */   public static final String ALARM_SHOCK = "shock";
/*     */   public static final String ALARM_BONNET = "bonnet";
/*     */   public static final String ALARM_FOOT_BRAKE = "footBrake";
/*     */   public static final String ALARM_FUEL_LEAK = "fuelLeak";
/*     */   public static final String ALARM_TAMPERING = "tampering";
/*     */   public static final String ALARM_REMOVING = "removing";
/*     */   public static final String ALARM_WATCH = "watchState";
/*     */   public static final String KEY_PACKET_TYPE = "packet_type";
/*     */   public static final String DEFAULT_PACKET_TYPE_SIMPLE = "simple";
/*     */   public static final String DEFAULT_PACKET_TYPE_DATA = "data";
/*     */   private String protocol;
/*     */   
/*     */   public Position() {}
/*     */   
/*     */   public Position(String protocol) {
/* 165 */     this.protocol = protocol;
/* 166 */     this.serverTime = new Date();
/*     */   }
/*     */   
/*     */   public String getUniqueId() {
/* 170 */     return ((Device)Context.getDeviceManager().getById(getDeviceId())).getUniqueId();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getProtocol() {
/* 176 */     return this.protocol;
/*     */   }
/*     */   
/*     */   public void setProtocol(String protocol) {
/* 180 */     this.protocol = protocol;
/*     */   }
/*     */   
/* 183 */   private Date serverTime = new Date(); private Date deviceTime; private Date fixTime; private boolean outdated; private boolean valid; private double latitude; private double longitude;
/*     */   
/*     */   public Date getServerTime() {
/* 186 */     return this.serverTime;
/*     */   }
/*     */   private double altitude; private double speed; private double course; private String address; private double accuracy; private Network network; private String packetType;
/*     */   public void setServerTime(Date serverTime) {
/* 190 */     this.serverTime = serverTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getDeviceTime() {
/* 196 */     return this.deviceTime;
/*     */   }
/*     */   
/*     */   public void setDeviceTime(Date deviceTime) {
/* 200 */     this.deviceTime = deviceTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getFixTime() {
/* 206 */     return this.fixTime;
/*     */   }
/*     */   
/*     */   public void setFixTime(Date fixTime) {
/* 210 */     this.fixTime = fixTime;
/*     */   }
/*     */   
/*     */   public void setTime(Date time) {
/* 214 */     setDeviceTime(time);
/* 215 */     setFixTime(time);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @QueryIgnore
/*     */   public boolean getOutdated() {
/* 222 */     return this.outdated;
/*     */   }
/*     */   
/*     */   public void setOutdated(boolean outdated) {
/* 226 */     this.outdated = outdated;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getValid() {
/* 232 */     return this.valid;
/*     */   }
/*     */   
/*     */   public void setValid(boolean valid) {
/* 236 */     this.valid = valid;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getLatitude() {
/* 242 */     return this.latitude;
/*     */   }
/*     */   
/*     */   public void setLatitude(double latitude) {
/* 246 */     this.latitude = latitude;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getLongitude() {
/* 252 */     return this.longitude;
/*     */   }
/*     */   
/*     */   public void setLongitude(double longitude) {
/* 256 */     this.longitude = longitude;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getAltitude() {
/* 262 */     return this.altitude;
/*     */   }
/*     */   
/*     */   public void setAltitude(double altitude) {
/* 266 */     this.altitude = altitude;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getSpeed() {
/* 272 */     return this.speed;
/*     */   }
/*     */   
/*     */   public void setSpeed(double speed) {
/* 276 */     this.speed = speed;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getCourse() {
/* 282 */     return this.course;
/*     */   }
/*     */   
/*     */   public void setCourse(double course) {
/* 286 */     this.course = course;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAddress() {
/* 292 */     return this.address;
/*     */   }
/*     */   
/*     */   public void setAddress(String address) {
/* 296 */     this.address = address;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getAccuracy() {
/* 302 */     return this.accuracy;
/*     */   }
/*     */   
/*     */   public void setAccuracy(double accuracy) {
/* 306 */     this.accuracy = accuracy;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Network getNetwork() {
/* 312 */     return this.network;
/*     */   }
/*     */   
/*     */   public void setNetwork(Network network) {
/* 316 */     this.network = network;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPacketType() {
/* 324 */     return this.packetType;
/*     */   }
/*     */   
/*     */   public void setPacketType(String packetType) {
/* 328 */     this.packetType = packetType;
/*     */   }
/*     */ 
/*     */   
/*     */   @QueryIgnore
/*     */   public String getType() {
/* 334 */     return super.getType();
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\model\Position.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */