/*     */ package org.traccar.handler;
/*     */ 
/*     */ import io.netty.channel.ChannelHandler.Sharable;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.traccar.BaseDataHandler;
/*     */ import org.traccar.Context;
/*     */ import org.traccar.config.Config;
/*     */ import org.traccar.config.Keys;
/*     */ import org.traccar.helper.UnitsConverter;
/*     */ import org.traccar.model.Position;
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
/*     */ @Sharable
/*     */ public class FilterHandler
/*     */   extends BaseDataHandler
/*     */ {
/*  31 */   private static final Logger LOGGER = LoggerFactory.getLogger(FilterHandler.class);
/*     */   
/*     */   private boolean filterInvalid;
/*     */   private boolean filterZero;
/*     */   private boolean filterDuplicate;
/*     */   private long filterFuture;
/*     */   private boolean filterApproximate;
/*     */   private int filterAccuracy;
/*     */   private boolean filterStatic;
/*     */   private int filterDistance;
/*     */   private int filterMaxDistance;
/*     */   private int filterMaxSpeed;
/*     */   private long filterMinPeriod;
/*     */   private long filterMinSatellites;
/*     */   private long filterMinAltitude;
/*     */   private long skipLimit;
/*     */   private boolean skipAttributes;
/*     */   
/*     */   public FilterHandler(Config config) {
/*  50 */     this.filterInvalid = config.getBoolean(Keys.FILTER_INVALID);
/*  51 */     this.filterZero = config.getBoolean(Keys.FILTER_ZERO);
/*  52 */     this.filterDuplicate = config.getBoolean(Keys.FILTER_DUPLICATE);
/*  53 */     this.filterFuture = config.getLong(Keys.FILTER_FUTURE) * 1000L;
/*  54 */     this.filterAccuracy = config.getInteger(Keys.FILTER_ACCURACY);
/*  55 */     this.filterApproximate = config.getBoolean(Keys.FILTER_APPROXIMATE);
/*  56 */     this.filterStatic = config.getBoolean(Keys.FILTER_STATIC);
/*  57 */     this.filterDistance = config.getInteger(Keys.FILTER_DISTANCE);
/*  58 */     this.filterMaxDistance = config.getInteger("filter.maxDistance");
/*  59 */     this.filterMaxSpeed = config.getInteger(Keys.FILTER_MAX_SPEED);
/*  60 */     this.filterMinPeriod = (config.getInteger(Keys.FILTER_MIN_PERIOD) * 1000);
/*  61 */     this.filterMinSatellites = config.getInteger("filter.minSatellites");
/*  62 */     this.filterMinAltitude = config.getInteger("filter.minAltitude");
/*  63 */     this.skipLimit = config.getLong(Keys.FILTER_SKIP_LIMIT) * 1000L;
/*  64 */     this.skipAttributes = config.getBoolean(Keys.FILTER_SKIP_ATTRIBUTES_ENABLE);
/*     */   }
/*     */   
/*     */   private boolean filterInvalid(Position position) {
/*  68 */     return (this.filterInvalid && (!position.getValid() || position
/*  69 */       .getLatitude() > 90.0D || position.getLongitude() > 180.0D || position
/*  70 */       .getLatitude() < -90.0D || position.getLongitude() < -180.0D));
/*     */   }
/*     */   
/*     */   private boolean filterZero(Position position) {
/*  74 */     return (this.filterZero && position.getLatitude() < 1.0D && position.getLongitude() < 1.0D);
/*     */   }
/*     */   
/*     */   private boolean filterDuplicate(Position position, Position last) {
/*  78 */     if (this.filterDuplicate && last != null && position.getFixTime().equals(last.getFixTime())) {
/*  79 */       for (String key : position.getAttributes().keySet()) {
/*  80 */         if (!last.getAttributes().containsKey(key)) {
/*  81 */           return false;
/*     */         }
/*     */       } 
/*  84 */       return true;
/*     */     } 
/*  86 */     return false;
/*     */   }
/*     */   
/*     */   private boolean filterFuture(Position position) {
/*  90 */     return (this.filterFuture != 0L && position.getFixTime().getTime() > System.currentTimeMillis() + this.filterFuture);
/*     */   }
/*     */   
/*     */   private boolean filterAccuracy(Position position) {
/*  94 */     return (this.filterAccuracy != 0 && position.getAccuracy() > this.filterAccuracy);
/*     */   }
/*     */   
/*     */   private boolean filterApproximate(Position position) {
/*  98 */     return (this.filterApproximate && position.getBoolean("approximate"));
/*     */   }
/*     */   
/*     */   private boolean filterStatic(Position position) {
/* 102 */     return (this.filterStatic && position.getSpeed() == 0.0D);
/*     */   }
/*     */   
/*     */   private boolean filterDistance(Position position, Position last) {
/* 106 */     double distance = position.getDouble("distance");
/* 107 */     return (last != null && ((this.filterDistance != 0 && distance < this.filterDistance) || (this.filterMaxDistance != 0 && distance > this.filterMaxDistance)));
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean filterMaxSpeed(Position position, Position last) {
/* 112 */     if (this.filterMaxSpeed != 0 && last != null) {
/* 113 */       double distance = position.getDouble("distance");
/* 114 */       double time = (position.getFixTime().getTime() - last.getFixTime().getTime());
/* 115 */       return (UnitsConverter.knotsFromMps(distance / time / 1000.0D) > this.filterMaxSpeed);
/*     */     } 
/* 117 */     return false;
/*     */   }
/*     */   
/*     */   private boolean filterMinPeriod(Position position, Position last) {
/* 121 */     if (this.filterMinPeriod != 0L && last != null) {
/* 122 */       long time = position.getFixTime().getTime() - last.getFixTime().getTime();
/* 123 */       return (time > 0L && time < this.filterMinPeriod);
/*     */     } 
/* 125 */     return false;
/*     */   }
/*     */   
/*     */   private boolean filterMinSatellites(Position position) {
/* 129 */     if (this.filterMinSatellites != 0L && position.getAttributes().containsKey("sat")) {
/* 130 */       return (position.getInteger("sat") < this.filterMinSatellites);
/*     */     }
/* 132 */     return false;
/*     */   }
/*     */   
/*     */   private boolean filterMinAltitude(Position position) {
/* 136 */     if (this.filterMinAltitude != 0L) {
/* 137 */       return (position.getAltitude() < this.filterMinAltitude);
/*     */     }
/* 139 */     return false;
/*     */   }
/*     */   
/*     */   private boolean skipLimit(Position position, Position last) {
/* 143 */     if (this.skipLimit != 0L && last != null) {
/* 144 */       return (position.getServerTime().getTime() - last.getServerTime().getTime() > this.skipLimit);
/*     */     }
/* 146 */     return false;
/*     */   }
/*     */   
/*     */   private boolean skipAttributes(Position position) {
/* 150 */     if (this.skipAttributes) {
/* 151 */       String attributesString = Context.getIdentityManager().lookupAttributeString(position
/* 152 */           .getDeviceId(), "filter.skipAttributes", "", true);
/* 153 */       for (String attribute : attributesString.split("[ ,]")) {
/* 154 */         if (position.getAttributes().containsKey(attribute)) {
/* 155 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/* 159 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean filter(Position position) {
/* 164 */     StringBuilder filterType = new StringBuilder();
/*     */     
/* 166 */     Position last = null;
/* 167 */     if (Context.getIdentityManager() != null) {
/* 168 */       last = Context.getIdentityManager().getLastPosition(position.getDeviceId());
/*     */     }
/*     */     
/* 171 */     if (filterInvalid(position)) {
/* 172 */       filterType.append("Invalid ");
/*     */     }
/* 174 */     if (filterZero(position)) {
/* 175 */       filterType.append("Zero ");
/*     */     }
/* 177 */     if (filterDuplicate(position, last) && !skipLimit(position, last) && !skipAttributes(position)) {
/* 178 */       filterType.append("Duplicate ");
/*     */     }
/* 180 */     if (filterFuture(position)) {
/* 181 */       filterType.append("Future ");
/*     */     }
/* 183 */     if (filterAccuracy(position)) {
/* 184 */       filterType.append("Accuracy ");
/*     */     }
/* 186 */     if (filterApproximate(position)) {
/* 187 */       filterType.append("Approximate ");
/*     */     }
/* 189 */     if (filterStatic(position) && !skipLimit(position, last) && !skipAttributes(position)) {
/* 190 */       filterType.append("Static ");
/*     */     }
/* 192 */     if (filterDistance(position, last) && !skipLimit(position, last) && !skipAttributes(position)) {
/* 193 */       filterType.append("Distance ");
/*     */     }
/* 195 */     if (filterMaxSpeed(position, last)) {
/* 196 */       filterType.append("MaxSpeed ");
/*     */     }
/* 198 */     if (filterMinPeriod(position, last)) {
/* 199 */       filterType.append("MinPeriod ");
/*     */     }
/* 201 */     if (filterMinSatellites(position)) {
/* 202 */       filterType.append("MinSatellites ");
/*     */     }
/* 204 */     if (filterMinAltitude(position)) {
/* 205 */       filterType.append("MinAltitude ");
/*     */     }
/*     */     
/* 208 */     if (filterType.length() > 0) {
/*     */       
/* 210 */       StringBuilder message = new StringBuilder();
/* 211 */       message.append("Position filtered by ");
/* 212 */       message.append(filterType.toString());
/* 213 */       message.append("filters from device: ");
/* 214 */       message.append(Context.getIdentityManager().getById(position.getDeviceId()).getUniqueId());
/*     */       
/* 216 */       LOGGER.info(message.toString());
/* 217 */       return true;
/*     */     } 
/*     */     
/* 220 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Position handlePosition(Position position) {
/* 225 */     if (filter(position)) {
/* 226 */       return null;
/*     */     }
/* 228 */     return position;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\handler\FilterHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */