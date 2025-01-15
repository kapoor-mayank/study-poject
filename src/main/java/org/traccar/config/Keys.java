/*     */ package org.traccar.config;
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
/*     */ public final class Keys
/*     */ {
/*  26 */   public static final ConfigSuffix PROTOCOL_TIMEOUT = new ConfigSuffix(".timeout", Integer.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  32 */   public static final ConfigSuffix PROTOCOL_PREFIX = new ConfigSuffix(".prefix", Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  38 */   public static final ConfigKey SERVER_TIMEOUT = new ConfigKey("server.timeout", Integer.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   public static final ConfigKey SERVER_STATISTICS = new ConfigKey("server.statistics", Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   public static final ConfigKey EVENT_ENABLE = new ConfigKey("event.enable", Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   public static final ConfigKey EVENT_OVERSPEED_NOT_REPEAT = new ConfigKey("event.overspeed.notRepeat", Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   public static final ConfigKey EVENT_OVERSPEED_MINIMAL_DURATION = new ConfigKey("event.overspeed.minimalDuration", Long.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  69 */   public static final ConfigKey EVENT_OVERSPEED_PREFER_LOWEST = new ConfigKey("event.overspeed.preferLowest", Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   public static final ConfigKey EVENT_IGNORE_DUPLICATE_ALERTS = new ConfigKey("event.ignoreDuplicateAlerts", Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   public static final ConfigKey EXTRA_HANDLERS = new ConfigKey("extra.handlers", String.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   public static final ConfigKey FORWARD_ENABLE = new ConfigKey("forward.enable", Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   public static final ConfigKey FORWARD_URL = new ConfigKey("forward.url", String.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   public static final ConfigKey FORWARD_HEADER = new ConfigKey("forward.header", String.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 106 */   public static final ConfigKey FORWARD_JSON = new ConfigKey("forward.json", Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   public static final ConfigKey FILTER_ENABLE = new ConfigKey("filter.enable", Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 118 */   public static final ConfigKey FILTER_INVALID = new ConfigKey("filter.invalid", Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 125 */   public static final ConfigKey FILTER_ZERO = new ConfigKey("filter.zero", Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 131 */   public static final ConfigKey FILTER_DUPLICATE = new ConfigKey("filter.duplicate", Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public static final ConfigKey FILTER_FUTURE = new ConfigKey("filter.future", Long.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 144 */   public static final ConfigKey FILTER_ACCURACY = new ConfigKey("filter.accuracy", Integer.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 150 */   public static final ConfigKey FILTER_APPROXIMATE = new ConfigKey("filter.approximate", Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 156 */   public static final ConfigKey FILTER_STATIC = new ConfigKey("filter.static", Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 163 */   public static final ConfigKey FILTER_DISTANCE = new ConfigKey("filter.distance", Integer.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 170 */   public static final ConfigKey FILTER_MAX_SPEED = new ConfigKey("filter.maxSpeed", Integer.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 176 */   public static final ConfigKey FILTER_MIN_PERIOD = new ConfigKey("filter.minPeriod", Integer.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 183 */   public static final ConfigKey FILTER_SKIP_LIMIT = new ConfigKey("filter.skipLimit", Long.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 189 */   public static final ConfigKey FILTER_SKIP_ATTRIBUTES_ENABLE = new ConfigKey("filter.skipAttributes.enable", Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 195 */   public static final ConfigKey TIME_OVERRIDE = new ConfigKey("time.override", String.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 202 */   public static final ConfigKey TIME_PROTOCOLS = new ConfigKey("time.protocols", String.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 209 */   public static final ConfigKey COORDINATES_FILTER = new ConfigKey("coordinates.filter", Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 215 */   public static final ConfigKey COORDINATES_MIN_ERROR = new ConfigKey("coordinates.minError", Integer.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 222 */   public static final ConfigKey COORDINATES_MAX_ERROR = new ConfigKey("filter.maxError", Integer.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 228 */   public static final ConfigKey PROCESSING_REMOTE_ADDRESS_ENABLE = new ConfigKey("processing.remoteAddress.enable", Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 234 */   public static final ConfigKey PROCESSING_ENGINE_HOURS_ENABLE = new ConfigKey("processing.engineHours.enable", Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 241 */   public static final ConfigKey PROCESSING_COPY_ATTRIBUTES_ENABLE = new ConfigKey("processing.copyAttributes.enable", Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 247 */   public static final ConfigKey PROCESSING_COMPUTED_ATTRIBUTES_ENABLE = new ConfigKey("processing.computedAttributes.enable", Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 253 */   public static final ConfigKey PROCESSING_COMPUTED_ATTRIBUTES_DEVICE_ATTRIBUTES = new ConfigKey("processing.computedAttributes.deviceAttributes", Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 259 */   public static final ConfigKey GEOCODER_ENABLE = new ConfigKey("geocoder.enable", Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 266 */   public static final ConfigKey GEOCODER_TYPE = new ConfigKey("geocoder.type", String.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 272 */   public static final ConfigKey GEOCODER_URL = new ConfigKey("geocoder.url", String.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 278 */   public static final ConfigKey GEOCODER_ID = new ConfigKey("geocoder.id", String.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 284 */   public static final ConfigKey GEOCODER_KEY = new ConfigKey("geocoder.key", String.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 290 */   public static final ConfigKey GEOCODER_LANGUAGE = new ConfigKey("geocoder.language", String.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 296 */   public static final ConfigKey GEOCODER_FORMAT = new ConfigKey("geocoder.format", String.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 302 */   public static final ConfigKey GEOCODER_CACHE_SIZE = new ConfigKey("geocoder.cacheSize", Integer.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 308 */   public static final ConfigKey GEOCODER_IGNORE_POSITIONS = new ConfigKey("geocoder.ignorePositions", Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 314 */   public static final ConfigKey GEOCODER_PROCESS_INVALID_POSITIONS = new ConfigKey("geocoder.processInvalidPositions", Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 321 */   public static final ConfigKey GEOCODER_REUSE_DISTANCE = new ConfigKey("geocoder.reuseDistance", Integer.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 329 */   public static final ConfigKey GEOLOCATION_ENABLE = new ConfigKey("geolocation.enable", Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 337 */   public static final ConfigKey GEOLOCATION_TYPE = new ConfigKey("geolocation.type", String.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 343 */   public static final ConfigKey GEOLOCATION_URL = new ConfigKey("geolocation.url", String.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 349 */   public static final ConfigKey GEOLOCATION_KEY = new ConfigKey("geolocation.key", String.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 355 */   public static final ConfigKey GEOLOCATION_PROCESS_INVALID_POSITIONS = new ConfigKey("geolocation.processInvalidPositions", Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 362 */   public static final ConfigKey LOCATION_LATITUDE_HEMISPHERE = new ConfigKey("location.latitudeHemisphere", Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 369 */   public static final ConfigKey LOCATION_LONGITUDE_HEMISPHERE = new ConfigKey("location.longitudeHemisphere", Boolean.class);
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\config\Keys.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */