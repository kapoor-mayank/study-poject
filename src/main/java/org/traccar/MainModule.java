/*     */ package org.traccar;
/*     */ 
/*     */ import com.fasterxml.jackson.databind.ObjectMapper;
/*     */ import com.google.inject.AbstractModule;
/*     */ import com.google.inject.Provides;
/*     */ import com.google.inject.Singleton;
/*     */ import javax.annotation.Nullable;
/*     */ import javax.ws.rs.client.Client;
/*     */ import org.traccar.config.Config;
/*     */ import org.traccar.config.Keys;
/*     */ import org.traccar.database.AttributesManager;
/*     */ import org.traccar.database.CalendarManager;
/*     */ import org.traccar.database.DataManager;
/*     */ import org.traccar.database.DeviceManager;
/*     */ import org.traccar.database.GeofenceManager;
/*     */ import org.traccar.database.IdentityManager;
/*     */ import org.traccar.database.MaintenancesManager;
/*     */ import org.traccar.database.StatisticsManager;
/*     */ import org.traccar.geocoder.AddressFormat;
/*     */ import org.traccar.geocoder.BanGeocoder;
/*     */ import org.traccar.geocoder.BingMapsGeocoder;
/*     */ import org.traccar.geocoder.FactualGeocoder;
/*     */ import org.traccar.geocoder.GeocodeFarmGeocoder;
/*     */ import org.traccar.geocoder.GeocodeXyzGeocoder;
/*     */ import org.traccar.geocoder.Geocoder;
/*     */ import org.traccar.geocoder.GisgraphyGeocoder;
/*     */ import org.traccar.geocoder.GoogleGeocoder;
/*     */ import org.traccar.geocoder.HereGeocoder;
/*     */ import org.traccar.geocoder.MapQuestGeocoder;
/*     */ import org.traccar.geocoder.MapmyIndiaGeocoder;
/*     */ import org.traccar.geocoder.NominatimGeocoder;
/*     */ import org.traccar.geocoder.OpenCageGeocoder;
/*     */ import org.traccar.geolocation.GeolocationProvider;
/*     */ import org.traccar.geolocation.GoogleGeolocationProvider;
/*     */ import org.traccar.geolocation.MozillaGeolocationProvider;
/*     */ import org.traccar.geolocation.OpenCellIdGeolocationProvider;
/*     */ import org.traccar.geolocation.UnwiredGeolocationProvider;
/*     */ import org.traccar.handler.ComputedAttributesHandler;
/*     */ import org.traccar.handler.CopyAttributesHandler;
/*     */ import org.traccar.handler.DefaultDataHandler;
/*     */ import org.traccar.handler.DistanceHandler;
/*     */ import org.traccar.handler.EngineHoursHandler;
/*     */ import org.traccar.handler.FilterHandler;
/*     */ import org.traccar.handler.GeocoderHandler;
/*     */ import org.traccar.handler.GeolocationHandler;
/*     */ import org.traccar.handler.HemisphereHandler;
/*     */ import org.traccar.handler.MotionHandler;
/*     */ import org.traccar.handler.RemoteAddressHandler;
/*     */ import org.traccar.handler.TimeHandler;
/*     */ import org.traccar.handler.events.AlertEventHandler;
/*     */ import org.traccar.handler.events.CommandResultEventHandler;
/*     */ import org.traccar.handler.events.DriverEventHandler;
/*     */ import org.traccar.handler.events.FuelDropEventHandler;
/*     */ import org.traccar.handler.events.GeofenceEventHandler;
/*     */ import org.traccar.handler.events.IgnitionEventHandler;
/*     */ import org.traccar.handler.events.MaintenanceEventHandler;
/*     */ import org.traccar.handler.events.MotionEventHandler;
/*     */ import org.traccar.handler.events.OverspeedEventHandler;
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
/*     */ public class MainModule
/*     */   extends AbstractModule
/*     */ {
/*     */   @Provides
/*     */   public static ObjectMapper provideObjectMapper() {
/*  81 */     return Context.getObjectMapper();
/*     */   }
/*     */   
/*     */   @Provides
/*     */   public static Config provideConfig() {
/*  86 */     return Context.getConfig();
/*     */   }
/*     */   
/*     */   @Provides
/*     */   public static DataManager provideDataManager() {
/*  91 */     return Context.getDataManager();
/*     */   }
/*     */   
/*     */   @Provides
/*     */   public static IdentityManager provideIdentityManager() {
/*  96 */     return Context.getIdentityManager();
/*     */   }
/*     */   
/*     */   @Provides
/*     */   public static Client provideClient() {
/* 101 */     return Context.getClient();
/*     */   }
/*     */   
/*     */   @Provides
/*     */   public static TripsConfig provideTripsConfig() {
/* 106 */     return Context.getTripsConfig();
/*     */   }
/*     */   
/*     */   @Provides
/*     */   public static DeviceManager provideDeviceManager() {
/* 111 */     return Context.getDeviceManager();
/*     */   }
/*     */   
/*     */   @Provides
/*     */   public static GeofenceManager provideGeofenceManager() {
/* 116 */     return Context.getGeofenceManager();
/*     */   }
/*     */   
/*     */   @Provides
/*     */   public static CalendarManager provideCalendarManager() {
/* 121 */     return Context.getCalendarManager();
/*     */   }
/*     */   
/*     */   @Provides
/*     */   public static AttributesManager provideAttributesManager() {
/* 126 */     return Context.getAttributesManager();
/*     */   }
/*     */   
/*     */   @Provides
/*     */   public static MaintenancesManager provideMaintenancesManager() {
/* 131 */     return Context.getMaintenancesManager();
/*     */   }
/*     */   
/*     */   @Singleton
/*     */   @Provides
/*     */   public static StatisticsManager provideStatisticsManager(Config config, DataManager dataManager, Client client) {
/* 137 */     return new StatisticsManager(config, dataManager, client);
/*     */   }
/*     */   
/*     */   @Singleton
/*     */   @Provides
/*     */   public static Geocoder provideGeocoder(Config config) {
/* 143 */     if (config.getBoolean(Keys.GEOCODER_ENABLE)) {
/* 144 */       String type = config.getString(Keys.GEOCODER_TYPE, "google");
/* 145 */       String url = config.getString(Keys.GEOCODER_URL);
/* 146 */       String id = config.getString(Keys.GEOCODER_ID);
/* 147 */       String key = config.getString(Keys.GEOCODER_KEY);
/* 148 */       String language = config.getString(Keys.GEOCODER_LANGUAGE);
/* 149 */       String formatString = config.getString(Keys.GEOCODER_FORMAT);
/* 150 */       AddressFormat addressFormat = (formatString != null) ? new AddressFormat(formatString) : new AddressFormat();
/*     */       
/* 152 */       int cacheSize = config.getInteger(Keys.GEOCODER_CACHE_SIZE);
/* 153 */       switch (type) {
/*     */         case "nominatim":
/* 155 */           return (Geocoder)new NominatimGeocoder(url, key, language, cacheSize, addressFormat);
/*     */         case "gisgraphy":
/* 157 */           return (Geocoder)new GisgraphyGeocoder(url, cacheSize, addressFormat);
/*     */         case "mapquest":
/* 159 */           return (Geocoder)new MapQuestGeocoder(url, key, cacheSize, addressFormat);
/*     */         case "opencage":
/* 161 */           return (Geocoder)new OpenCageGeocoder(url, key, cacheSize, addressFormat);
/*     */         case "bingmaps":
/* 163 */           return (Geocoder)new BingMapsGeocoder(url, key, cacheSize, addressFormat);
/*     */         case "factual":
/* 165 */           return (Geocoder)new FactualGeocoder(url, key, cacheSize, addressFormat);
/*     */         case "geocodefarm":
/* 167 */           return (Geocoder)new GeocodeFarmGeocoder(key, language, cacheSize, addressFormat);
/*     */         case "geocodexyz":
/* 169 */           return (Geocoder)new GeocodeXyzGeocoder(key, cacheSize, addressFormat);
/*     */         case "ban":
/* 171 */           return (Geocoder)new BanGeocoder(cacheSize, addressFormat);
/*     */         case "here":
/* 173 */           return (Geocoder)new HereGeocoder(id, key, language, cacheSize, addressFormat);
/*     */         case "mapmyindia":
/* 175 */           return (Geocoder)new MapmyIndiaGeocoder(url, key, cacheSize, addressFormat);
/*     */       } 
/* 177 */       return (Geocoder)new GoogleGeocoder(key, language, cacheSize, addressFormat);
/*     */     } 
/*     */     
/* 180 */     return null;
/*     */   }
/*     */   
/*     */   @Singleton
/*     */   @Provides
/*     */   public static GeolocationProvider provideGeolocationProvider(Config config) {
/* 186 */     if (config.getBoolean(Keys.GEOLOCATION_ENABLE)) {
/* 187 */       String type = config.getString(Keys.GEOLOCATION_TYPE, "mozilla");
/* 188 */       String url = config.getString(Keys.GEOLOCATION_URL);
/* 189 */       String key = config.getString(Keys.GEOLOCATION_KEY);
/* 190 */       switch (type) {
/*     */         case "google":
/* 192 */           return (GeolocationProvider)new GoogleGeolocationProvider(key);
/*     */         case "opencellid":
/* 194 */           return (GeolocationProvider)new OpenCellIdGeolocationProvider(key);
/*     */         case "unwired":
/* 196 */           return (GeolocationProvider)new UnwiredGeolocationProvider(url, key);
/*     */       } 
/* 198 */       return (GeolocationProvider)new MozillaGeolocationProvider(key);
/*     */     } 
/*     */     
/* 201 */     return null;
/*     */   }
/*     */   
/*     */   @Singleton
/*     */   @Provides
/*     */   public static DistanceHandler provideDistanceHandler(Config config, IdentityManager identityManager) {
/* 207 */     return new DistanceHandler(config, identityManager);
/*     */   }
/*     */   
/*     */   @Singleton
/*     */   @Provides
/*     */   public static FilterHandler provideFilterHandler(Config config) {
/* 213 */     if (config.getBoolean(Keys.FILTER_ENABLE)) {
/* 214 */       return new FilterHandler(config);
/*     */     }
/* 216 */     return null;
/*     */   }
/*     */   
/*     */   @Singleton
/*     */   @Provides
/*     */   public static HemisphereHandler provideHemisphereHandler(Config config) {
/* 222 */     if (config.hasKey(Keys.LOCATION_LATITUDE_HEMISPHERE) || config.hasKey(Keys.LOCATION_LONGITUDE_HEMISPHERE)) {
/* 223 */       return new HemisphereHandler(config);
/*     */     }
/* 225 */     return null;
/*     */   }
/*     */   
/*     */   @Singleton
/*     */   @Provides
/*     */   public static RemoteAddressHandler provideRemoteAddressHandler(Config config) {
/* 231 */     if (config.getBoolean(Keys.PROCESSING_REMOTE_ADDRESS_ENABLE)) {
/* 232 */       return new RemoteAddressHandler();
/*     */     }
/* 234 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   @Singleton
/*     */   @Provides
/*     */   public static WebDataHandler provideWebDataHandler(Config config, IdentityManager identityManager, ObjectMapper objectMapper, Client client) {
/* 241 */     if (config.getBoolean(Keys.FORWARD_ENABLE)) {
/* 242 */       return new WebDataHandler(config, identityManager, objectMapper, client);
/*     */     }
/* 244 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   @Singleton
/*     */   @Provides
/*     */   public static GeolocationHandler provideGeolocationHandler(Config config, @Nullable GeolocationProvider geolocationProvider, StatisticsManager statisticsManager) {
/* 251 */     if (geolocationProvider != null) {
/* 252 */       return new GeolocationHandler(config, geolocationProvider, statisticsManager);
/*     */     }
/* 254 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Singleton
/*     */   @Provides
/*     */   public static GeocoderHandler provideGeocoderHandler(Config config, @Nullable Geocoder geocoder, IdentityManager identityManager, StatisticsManager statisticsManager) {
/* 262 */     if (geocoder != null) {
/* 263 */       return new GeocoderHandler(config, geocoder, identityManager, statisticsManager);
/*     */     }
/* 265 */     return null;
/*     */   }
/*     */   
/*     */   @Singleton
/*     */   @Provides
/*     */   public static MotionHandler provideMotionHandler(TripsConfig tripsConfig) {
/* 271 */     return new MotionHandler(tripsConfig.getSpeedThreshold());
/*     */   }
/*     */   
/*     */   @Singleton
/*     */   @Provides
/*     */   public static EngineHoursHandler provideEngineHoursHandler(Config config, IdentityManager identityManager) {
/* 277 */     if (config.getBoolean(Keys.PROCESSING_ENGINE_HOURS_ENABLE)) {
/* 278 */       return new EngineHoursHandler(identityManager);
/*     */     }
/* 280 */     return null;
/*     */   }
/*     */   
/*     */   @Singleton
/*     */   @Provides
/*     */   public static CopyAttributesHandler provideCopyAttributesHandler(Config config, IdentityManager identityManager) {
/* 286 */     if (config.getBoolean(Keys.PROCESSING_COPY_ATTRIBUTES_ENABLE)) {
/* 287 */       return new CopyAttributesHandler(identityManager);
/*     */     }
/* 289 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   @Singleton
/*     */   @Provides
/*     */   public static ComputedAttributesHandler provideComputedAttributesHandler(Config config, IdentityManager identityManager, AttributesManager attributesManager) {
/* 296 */     if (config.getBoolean(Keys.PROCESSING_COMPUTED_ATTRIBUTES_ENABLE)) {
/* 297 */       return new ComputedAttributesHandler(config, identityManager, attributesManager);
/*     */     }
/* 299 */     return null;
/*     */   }
/*     */   
/*     */   @Singleton
/*     */   @Provides
/*     */   public static TimeHandler provideTimeHandler(Config config) {
/* 305 */     if (config.hasKey(Keys.TIME_OVERRIDE)) {
/* 306 */       return new TimeHandler(config);
/*     */     }
/* 308 */     return null;
/*     */   }
/*     */   
/*     */   @Singleton
/*     */   @Provides
/*     */   public static DefaultDataHandler provideDefaultDataHandler(@Nullable DataManager dataManager) {
/* 314 */     if (dataManager != null) {
/* 315 */       return new DefaultDataHandler(dataManager);
/*     */     }
/* 317 */     return null;
/*     */   }
/*     */   
/*     */   @Singleton
/*     */   @Provides
/*     */   public static CommandResultEventHandler provideCommandResultEventHandler() {
/* 323 */     return new CommandResultEventHandler();
/*     */   }
/*     */ 
/*     */   
/*     */   @Singleton
/*     */   @Provides
/*     */   public static OverspeedEventHandler provideOverspeedEventHandler(Config config, DeviceManager deviceManager, GeofenceManager geofenceManager) {
/* 330 */     return new OverspeedEventHandler(config, deviceManager, geofenceManager);
/*     */   }
/*     */   
/*     */   @Singleton
/*     */   @Provides
/*     */   public static FuelDropEventHandler provideFuelDropEventHandler(IdentityManager identityManager) {
/* 336 */     return new FuelDropEventHandler(identityManager);
/*     */   }
/*     */ 
/*     */   
/*     */   @Singleton
/*     */   @Provides
/*     */   public static MotionEventHandler provideMotionEventHandler(IdentityManager identityManager, DeviceManager deviceManager, TripsConfig tripsConfig) {
/* 343 */     return new MotionEventHandler(identityManager, deviceManager, tripsConfig);
/*     */   }
/*     */ 
/*     */   
/*     */   @Singleton
/*     */   @Provides
/*     */   public static GeofenceEventHandler provideGeofenceEventHandler(IdentityManager identityManager, GeofenceManager geofenceManager, CalendarManager calendarManager) {
/* 350 */     return new GeofenceEventHandler(identityManager, geofenceManager, calendarManager);
/*     */   }
/*     */   
/*     */   @Singleton
/*     */   @Provides
/*     */   public static AlertEventHandler provideAlertEventHandler(Config config, IdentityManager identityManager) {
/* 356 */     return new AlertEventHandler(config, identityManager);
/*     */   }
/*     */   
/*     */   @Singleton
/*     */   @Provides
/*     */   public static IgnitionEventHandler provideIgnitionEventHandler(IdentityManager identityManager) {
/* 362 */     return new IgnitionEventHandler(identityManager);
/*     */   }
/*     */ 
/*     */   
/*     */   @Singleton
/*     */   @Provides
/*     */   public static MaintenanceEventHandler provideMaintenanceEventHandler(IdentityManager identityManager, MaintenancesManager maintenancesManager) {
/* 369 */     return new MaintenanceEventHandler(identityManager, maintenancesManager);
/*     */   }
/*     */   
/*     */   @Singleton
/*     */   @Provides
/*     */   public static DriverEventHandler provideDriverEventHandler(IdentityManager identityManager) {
/* 375 */     return new DriverEventHandler(identityManager);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void configure() {
/* 380 */     binder().requireExplicitBindings();
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\MainModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */