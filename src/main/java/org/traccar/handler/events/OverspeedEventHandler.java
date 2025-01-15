/*     */ package org.traccar.handler.events;
/*     */ 
/*     */ import io.netty.channel.ChannelHandler.Sharable;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.traccar.config.Config;
/*     */ import org.traccar.config.Keys;
/*     */ import org.traccar.database.DeviceManager;
/*     */ import org.traccar.database.GeofenceManager;
/*     */ import org.traccar.model.Device;
/*     */ import org.traccar.model.DeviceState;
/*     */ import org.traccar.model.Event;
/*     */ import org.traccar.model.Geofence;
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
/*     */ 
/*     */ @Sharable
/*     */ public class OverspeedEventHandler
/*     */   extends BaseEventHandler
/*     */ {
/*     */   public static final String ATTRIBUTE_SPEED = "speed";
/*     */   public static final String ATTRIBUTE_SPEED_LIMIT = "speedLimit";
/*     */   private final DeviceManager deviceManager;
/*     */   private final GeofenceManager geofenceManager;
/*     */   private final boolean notRepeat;
/*     */   private final long minimalDuration;
/*     */   private final boolean preferLowest;
/*     */   
/*     */   public OverspeedEventHandler(Config config, DeviceManager deviceManager, GeofenceManager geofenceManager) {
/*  47 */     this.deviceManager = deviceManager;
/*  48 */     this.geofenceManager = geofenceManager;
/*  49 */     this.notRepeat = config.getBoolean(Keys.EVENT_OVERSPEED_NOT_REPEAT);
/*  50 */     this.minimalDuration = config.getLong(Keys.EVENT_OVERSPEED_MINIMAL_DURATION) * 1000L;
/*  51 */     this.preferLowest = config.getBoolean(Keys.EVENT_OVERSPEED_PREFER_LOWEST);
/*     */   }
/*     */   
/*     */   private Map<Event, Position> newEvent(DeviceState deviceState, double speedLimit) {
/*  55 */     Position position = deviceState.getOverspeedPosition();
/*  56 */     Event event = new Event("deviceOverspeed", position.getDeviceId(), position.getId());
/*  57 */     event.set("speed", Double.valueOf(deviceState.getOverspeedPosition().getSpeed()));
/*  58 */     event.set("speedLimit", Double.valueOf(speedLimit));
/*  59 */     event.setGeofenceId(deviceState.getOverspeedGeofenceId());
/*  60 */     deviceState.setOverspeedState(this.notRepeat);
/*  61 */     deviceState.setOverspeedPosition(null);
/*  62 */     deviceState.setOverspeedGeofenceId(0L);
/*  63 */     return Collections.singletonMap(event, position);
/*     */   }
/*     */   
/*     */   public Map<Event, Position> updateOverspeedState(DeviceState deviceState, double speedLimit) {
/*  67 */     Map<Event, Position> result = null;
/*  68 */     if (deviceState.getOverspeedState() != null && !deviceState.getOverspeedState().booleanValue() && deviceState
/*  69 */       .getOverspeedPosition() != null && speedLimit != 0.0D) {
/*  70 */       long currentTime = System.currentTimeMillis();
/*  71 */       Position overspeedPosition = deviceState.getOverspeedPosition();
/*  72 */       long overspeedTime = overspeedPosition.getFixTime().getTime();
/*  73 */       if (overspeedTime + this.minimalDuration <= currentTime) {
/*  74 */         result = newEvent(deviceState, speedLimit);
/*     */       }
/*     */     } 
/*  77 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<Event, Position> updateOverspeedState(DeviceState deviceState, Position position, double speedLimit, long geofenceId) {
/*  82 */     Map<Event, Position> result = null;
/*     */     
/*  84 */     Boolean oldOverspeed = deviceState.getOverspeedState();
/*     */     
/*  86 */     long currentTime = position.getFixTime().getTime();
/*  87 */     boolean newOverspeed = (position.getSpeed() > speedLimit);
/*  88 */     if (newOverspeed && !oldOverspeed.booleanValue()) {
/*  89 */       if (deviceState.getOverspeedPosition() == null) {
/*  90 */         deviceState.setOverspeedPosition(position);
/*  91 */         deviceState.setOverspeedGeofenceId(geofenceId);
/*     */       } 
/*  93 */     } else if (oldOverspeed.booleanValue() && !newOverspeed) {
/*  94 */       deviceState.setOverspeedState(false);
/*  95 */       deviceState.setOverspeedPosition(null);
/*  96 */       deviceState.setOverspeedGeofenceId(0L);
/*     */     } else {
/*  98 */       deviceState.setOverspeedPosition(null);
/*  99 */       deviceState.setOverspeedGeofenceId(0L);
/*     */     } 
/* 101 */     Position overspeedPosition = deviceState.getOverspeedPosition();
/* 102 */     if (overspeedPosition != null) {
/* 103 */       long overspeedTime = overspeedPosition.getFixTime().getTime();
/* 104 */       if (newOverspeed && overspeedTime + this.minimalDuration <= currentTime) {
/* 105 */         result = newEvent(deviceState, speedLimit);
/*     */       }
/*     */     } 
/* 108 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Map<Event, Position> analyzePosition(Position position) {
/* 114 */     long deviceId = position.getDeviceId();
/* 115 */     Device device = (Device)this.deviceManager.getById(deviceId);
/* 116 */     if (device == null) {
/* 117 */       return null;
/*     */     }
/* 119 */     if (!this.deviceManager.isLatestPosition(position) || !position.getValid()) {
/* 120 */       return null;
/*     */     }
/*     */     
/* 123 */     double speedLimit = this.deviceManager.lookupAttributeDouble(deviceId, "speedLimit", 0.0D, false);
/*     */     
/* 125 */     double geofenceSpeedLimit = 0.0D;
/* 126 */     long overspeedGeofenceId = 0L;
/*     */     
/* 128 */     if (this.geofenceManager != null && device.getGeofenceIds() != null) {
/* 129 */       for (Iterator<Long> iterator = device.getGeofenceIds().iterator(); iterator.hasNext(); ) { long geofenceId = ((Long)iterator.next()).longValue();
/* 130 */         Geofence geofence = (Geofence)this.geofenceManager.getById(geofenceId);
/* 131 */         if (geofence != null) {
/* 132 */           double currentSpeedLimit = geofence.getDouble("speedLimit");
/* 133 */           if ((currentSpeedLimit > 0.0D && geofenceSpeedLimit == 0.0D) || (this.preferLowest && currentSpeedLimit < geofenceSpeedLimit) || (!this.preferLowest && currentSpeedLimit > geofenceSpeedLimit)) {
/*     */ 
/*     */             
/* 136 */             geofenceSpeedLimit = currentSpeedLimit;
/* 137 */             overspeedGeofenceId = geofenceId;
/*     */           } 
/*     */         }  }
/*     */     
/*     */     }
/* 142 */     if (geofenceSpeedLimit > 0.0D) {
/* 143 */       speedLimit = geofenceSpeedLimit;
/*     */     }
/*     */     
/* 146 */     if (speedLimit == 0.0D) {
/* 147 */       return null;
/*     */     }
/*     */     
/* 150 */     Map<Event, Position> result = null;
/* 151 */     DeviceState deviceState = this.deviceManager.getDeviceState(deviceId);
/*     */     
/* 153 */     if (deviceState.getOverspeedState() == null) {
/* 154 */       deviceState.setOverspeedState((position.getSpeed() > speedLimit));
/* 155 */       deviceState.setOverspeedGeofenceId((position.getSpeed() > speedLimit) ? overspeedGeofenceId : 0L);
/*     */     } else {
/* 157 */       result = updateOverspeedState(deviceState, position, speedLimit, overspeedGeofenceId);
/*     */     } 
/*     */     
/* 160 */     this.deviceManager.setDeviceState(deviceId, deviceState);
/* 161 */     return result;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\handler\events\OverspeedEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */