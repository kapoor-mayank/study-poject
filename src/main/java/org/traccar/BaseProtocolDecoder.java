/*     */ package org.traccar;
/*     */ 
/*     */ import io.netty.channel.Channel;
/*     */ import io.netty.handler.codec.http.HttpRequestDecoder;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.net.SocketAddress;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.TimeZone;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.traccar.config.Config;
/*     */ import org.traccar.database.ConnectionManager;
/*     */ import org.traccar.database.IdentityManager;
/*     */ import org.traccar.database.StatisticsManager;
/*     */ import org.traccar.handler.ForwarderHandler;
/*     */ import org.traccar.helper.UnitsConverter;
/*     */ import org.traccar.model.Device;
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
/*     */ 
/*     */ 
/*     */ public abstract class BaseProtocolDecoder
/*     */   extends ExtendedObjectDecoder
/*     */ {
/*  42 */   private static final Logger LOGGER = LoggerFactory.getLogger(BaseProtocolDecoder.class);
/*     */   
/*     */   private static final String PROTOCOL_UNKNOWN = "unknown";
/*     */   
/*  46 */   private final Config config = Context.getConfig();
/*  47 */   private final IdentityManager identityManager = Context.getIdentityManager();
/*  48 */   private final ConnectionManager connectionManager = Context.getConnectionManager();
/*     */   
/*     */   private final StatisticsManager statisticsManager;
/*     */   
/*     */   private final Protocol protocol;
/*     */   
/*     */   private String modelOverride;
/*     */   
/*     */   private DeviceSession channelDeviceSession;
/*     */   private Map<SocketAddress, DeviceSession> addressDeviceSessions;
/*     */   
/*     */   public String getProtocolName() {
/*  60 */     return (this.protocol != null) ? this.protocol.getName() : "unknown";
/*     */   }
/*     */   
/*     */   public String getServer(Channel channel, char delimiter) {
/*  64 */     String server = this.config.getString(getProtocolName() + ".server");
/*  65 */     if (server == null && channel != null) {
/*  66 */       InetSocketAddress address = (InetSocketAddress)channel.localAddress();
/*  67 */       server = address.getAddress().getHostAddress() + ":" + address.getPort();
/*     */     } 
/*  69 */     return (server != null) ? server.replace(':', delimiter) : null;
/*     */   }
/*     */   
/*     */   protected double convertSpeed(double value, String defaultUnits) {
/*  73 */     switch (this.config.getString(getProtocolName() + ".speed", defaultUnits)) {
/*     */       case "kmh":
/*  75 */         return UnitsConverter.knotsFromKph(value);
/*     */       case "mps":
/*  77 */         return UnitsConverter.knotsFromMps(value);
/*     */       case "mph":
/*  79 */         return UnitsConverter.knotsFromMph(value);
/*     */     } 
/*     */     
/*  82 */     return value;
/*     */   }
/*     */ 
/*     */   
/*     */   protected TimeZone getTimeZone(long deviceId) {
/*  87 */     return getTimeZone(deviceId, "UTC");
/*     */   }
/*     */   
/*     */   protected TimeZone getTimeZone(long deviceId, String defaultTimeZone) {
/*  91 */     TimeZone result = TimeZone.getTimeZone(defaultTimeZone);
/*  92 */     String timeZoneName = this.identityManager.lookupAttributeString(deviceId, "decoder.timezone", null, true);
/*  93 */     if (timeZoneName != null) {
/*  94 */       result = TimeZone.getTimeZone(timeZoneName);
/*     */     } else {
/*  96 */       int timeZoneOffset = this.config.getInteger(getProtocolName() + ".timezone", 0);
/*  97 */       if (timeZoneOffset != 0) {
/*  98 */         result.setRawOffset(timeZoneOffset * 1000);
/*  99 */         LOGGER.warn("Config parameter " + getProtocolName() + ".timezone is deprecated");
/*     */       } 
/*     */     } 
/* 102 */     return result;
/*     */   }
/*     */   
/*     */   public BaseProtocolDecoder(Protocol protocol) {
/* 106 */     this.addressDeviceSessions = new HashMap<>();
/*     */     this.protocol = protocol;
/*     */     this.statisticsManager = (Main.getInjector() != null) ? (StatisticsManager)Main.getInjector().getInstance(StatisticsManager.class) : null; } private long findDeviceId(SocketAddress remoteAddress, String... uniqueIds) {
/* 109 */     if (uniqueIds.length > 0) {
/* 110 */       long deviceId = 0L;
/* 111 */       Device device = null;
/*     */       try {
/* 113 */         for (String uniqueId : uniqueIds) {
/* 114 */           if (uniqueId != null) {
/* 115 */             device = this.identityManager.getByUniqueId(uniqueId);
/* 116 */             if (device != null) {
/* 117 */               deviceId = device.getId();
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/* 122 */       } catch (Exception e) {
/* 123 */         LOGGER.warn("Find device error", e);
/*     */       } 
/* 125 */       if (deviceId == 0L && this.config.getBoolean("database.registerUnknown")) {
/* 126 */         return this.identityManager.addUnknownDevice(uniqueIds[0]);
/*     */       }
/* 128 */       if ((device != null && !device.getDisabled()) || this.config.getBoolean("database.storeDisabled")) {
/* 129 */         return deviceId;
/*     */       }
/* 131 */       StringBuilder message = new StringBuilder();
/* 132 */       if (deviceId == 0L) {
/* 133 */         message.append("Unknown device -");
/*     */       } else {
/* 135 */         message.append("Disabled device -");
/*     */       } 
/* 137 */       for (String uniqueId : uniqueIds) {
/* 138 */         message.append(" ").append(uniqueId);
/*     */       }
/* 140 */       if (remoteAddress != null) {
/* 141 */         message.append(" (").append(((InetSocketAddress)remoteAddress).getHostString()).append(")");
/*     */       }
/* 143 */       LOGGER.warn(message.toString());
/*     */     } 
/* 145 */     return 0L;
/*     */   }
/*     */   
/*     */   public DeviceSession getDeviceSession(Channel channel, SocketAddress remoteAddress, String... uniqueIds) {
/* 149 */     return getDeviceSession(channel, remoteAddress, false, uniqueIds);
/*     */   }
/*     */ 
/*     */   
/*     */   public DeviceSession getDeviceSession(Channel channel, SocketAddress remoteAddress, boolean ignoreCache, String... uniqueIds) {
/* 154 */     if ((channel != null && BasePipelineFactory.getHandler(channel.pipeline(), HttpRequestDecoder.class) != null) || ignoreCache || this.config
/* 155 */       .getBoolean(getProtocolName() + ".ignoreSessionCache")) {
/* 156 */       long deviceId = findDeviceId(remoteAddress, uniqueIds);
/* 157 */       if (deviceId != 0L) {
/* 158 */         if (channel != null && uniqueIds.length > 0) {
/* 159 */           ((ForwarderHandler)channel.pipeline().get(ForwarderHandler.class))
/* 160 */             .identify(uniqueIds[0], remoteAddress);
/*     */         }
/* 162 */         if (this.connectionManager != null) {
/* 163 */           this.connectionManager.addActiveDevice(deviceId, this.protocol, channel, remoteAddress);
/*     */         }
/* 165 */         return new DeviceSession(deviceId);
/*     */       } 
/* 167 */       return null;
/*     */     } 
/*     */     
/* 170 */     if (channel instanceof io.netty.channel.socket.DatagramChannel) {
/* 171 */       long deviceId = findDeviceId(remoteAddress, uniqueIds);
/* 172 */       DeviceSession deviceSession = this.addressDeviceSessions.get(remoteAddress);
/* 173 */       if (deviceSession != null && (deviceSession.getDeviceId() == deviceId || uniqueIds.length == 0))
/* 174 */         return deviceSession; 
/* 175 */       if (deviceId != 0L) {
/* 176 */         deviceSession = new DeviceSession(deviceId);
/* 177 */         this.addressDeviceSessions.put(remoteAddress, deviceSession);
/* 178 */         if (channel != null && uniqueIds.length > 0) {
/* 179 */           ((ForwarderHandler)channel.pipeline().get(ForwarderHandler.class))
/* 180 */             .identify(uniqueIds[0], remoteAddress);
/*     */         }
/* 182 */         if (this.connectionManager != null) {
/* 183 */           this.connectionManager.addActiveDevice(deviceId, this.protocol, channel, remoteAddress);
/*     */         }
/* 185 */         return deviceSession;
/*     */       } 
/* 187 */       return null;
/*     */     } 
/*     */     
/* 190 */     if (this.channelDeviceSession == null) {
/* 191 */       long deviceId = findDeviceId(remoteAddress, uniqueIds);
/* 192 */       if (deviceId != 0L) {
/* 193 */         this.channelDeviceSession = new DeviceSession(deviceId);
/* 194 */         if (channel != null && uniqueIds.length > 0) {
/* 195 */           ((ForwarderHandler)channel.pipeline().get(ForwarderHandler.class))
/* 196 */             .identify(uniqueIds[0], remoteAddress);
/*     */         }
/* 198 */         if (this.connectionManager != null) {
/* 199 */           this.connectionManager.addActiveDevice(deviceId, this.protocol, channel, remoteAddress);
/*     */         }
/*     */       } 
/*     */     } 
/* 203 */     return this.channelDeviceSession;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setModelOverride(String modelOverride) {
/* 208 */     this.modelOverride = modelOverride;
/*     */   }
/*     */   
/*     */   public String getDeviceModel(DeviceSession deviceSession) {
/*     */     String model;
/* 213 */     if (Context.getDeviceManager() != null) {
/* 214 */       model = ((Device)Context.getDeviceManager().getById(deviceSession.getDeviceId())).getModel();
/*     */     } else {
/* 216 */       model = null;
/*     */     } 
/* 218 */     return (this.modelOverride != null) ? this.modelOverride : model;
/*     */   }
/*     */   
/*     */   public void getLastLocation(Position position, Date deviceTime) {
/* 222 */     if (position.getDeviceId() != 0L) {
/* 223 */       position.setOutdated(true);
/*     */       
/* 225 */       Position last = this.identityManager.getLastPosition(position.getDeviceId());
/* 226 */       if (last != null) {
/* 227 */         position.setFixTime(last.getFixTime());
/* 228 */         position.setValid(last.getValid());
/* 229 */         position.setLatitude(last.getLatitude());
/* 230 */         position.setLongitude(last.getLongitude());
/* 231 */         position.setAltitude(last.getAltitude());
/* 232 */         position.setSpeed(last.getSpeed());
/* 233 */         position.setCourse(last.getCourse());
/* 234 */         position.setAccuracy(last.getAccuracy());
/*     */       } else {
/* 236 */         position.setFixTime(new Date(0L));
/*     */       } 
/*     */       
/* 239 */       position.setDeviceTime(deviceTime);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onMessageEvent(Channel channel, SocketAddress remoteAddress, Object originalMessage, Object decodedMessage) {
/* 246 */     if (this.statisticsManager != null) {
/* 247 */       this.statisticsManager.registerMessageReceived();
/*     */     }
/* 249 */     Position position = null;
/* 250 */     if (decodedMessage != null) {
/* 251 */       if (decodedMessage instanceof Position) {
/* 252 */         position = (Position)decodedMessage;
/* 253 */       } else if (decodedMessage instanceof Collection) {
/* 254 */         Collection<Position> positions = (Collection)decodedMessage;
/* 255 */         if (!positions.isEmpty()) {
/* 256 */           position = positions.iterator().next();
/*     */         }
/*     */       } 
/*     */     }
/* 260 */     if (position != null) {
/* 261 */       this.connectionManager.updateDevice(position
/* 262 */           .getDeviceId(), "online", new Date());
/*     */     } else {
/* 264 */       DeviceSession deviceSession = getDeviceSession(channel, remoteAddress, new String[0]);
/* 265 */       if (deviceSession != null) {
/* 266 */         this.connectionManager.updateDevice(deviceSession
/* 267 */             .getDeviceId(), "online", new Date());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object handleEmptyMessage(Channel channel, SocketAddress remoteAddress, Object msg) {
/* 274 */     DeviceSession deviceSession = getDeviceSession(channel, remoteAddress, new String[0]);
/* 275 */     if (this.config.getBoolean("database.saveEmpty") && deviceSession != null) {
/* 276 */       Position position = new Position(getProtocolName());
/* 277 */       position.setDeviceId(deviceSession.getDeviceId());
/* 278 */       getLastLocation(position, null);
/* 279 */       return position;
/*     */     } 
/* 281 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\BaseProtocolDecoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */