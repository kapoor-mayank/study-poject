/*     */ package org.traccar.handler.events;
/*     */ 
/*     */ import io.netty.channel.ChannelHandler.Sharable;
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import org.traccar.database.DeviceManager;
/*     */ import org.traccar.database.IdentityManager;
/*     */ import org.traccar.model.Device;
/*     */ import org.traccar.model.DeviceState;
/*     */ import org.traccar.model.Event;
/*     */ import org.traccar.model.Position;
/*     */ import org.traccar.reports.ReportUtils;
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
/*     */ @Sharable
/*     */ public class MotionEventHandler
/*     */   extends BaseEventHandler
/*     */ {
/*     */   private final IdentityManager identityManager;
/*     */   private final DeviceManager deviceManager;
/*     */   private final TripsConfig tripsConfig;
/*     */   
/*     */   public MotionEventHandler(IdentityManager identityManager, DeviceManager deviceManager, TripsConfig tripsConfig) {
/*  40 */     this.identityManager = identityManager;
/*  41 */     this.deviceManager = deviceManager;
/*  42 */     this.tripsConfig = tripsConfig;
/*     */   }
/*     */   
/*     */   private Map<Event, Position> newEvent(DeviceState deviceState, boolean newMotion) {
/*  46 */     String eventType = newMotion ? "deviceMoving" : "deviceStopped";
/*  47 */     Position position = deviceState.getMotionPosition();
/*  48 */     Event event = new Event(eventType, position.getDeviceId(), position.getId());
/*  49 */     deviceState.setMotionState(newMotion);
/*  50 */     deviceState.setMotionPosition(null);
/*  51 */     return Collections.singletonMap(event, position);
/*     */   }
/*     */   
/*     */   public Map<Event, Position> updateMotionState(DeviceState deviceState) {
/*  55 */     Map<Event, Position> result = null;
/*  56 */     if (deviceState.getMotionState() != null && deviceState.getMotionPosition() != null) {
/*  57 */       boolean newMotion = !deviceState.getMotionState().booleanValue();
/*  58 */       Position motionPosition = deviceState.getMotionPosition();
/*  59 */       long currentTime = System.currentTimeMillis();
/*     */       
/*  61 */       long motionTime = motionPosition.getFixTime().getTime() + (newMotion ? this.tripsConfig.getMinimalTripDuration() : this.tripsConfig.getMinimalParkingDuration());
/*  62 */       if (motionTime <= currentTime) {
/*  63 */         result = newEvent(deviceState, newMotion);
/*     */       }
/*     */     } 
/*  66 */     return result;
/*     */   }
/*     */   
/*     */   public Map<Event, Position> updateMotionState(DeviceState deviceState, Position position) {
/*  70 */     return updateMotionState(deviceState, position, position.getBoolean("motion"));
/*     */   }
/*     */   
/*     */   public Map<Event, Position> updateMotionState(DeviceState deviceState, Position position, boolean newMotion) {
/*  74 */     Map<Event, Position> result = null;
/*  75 */     Boolean oldMotion = deviceState.getMotionState();
/*     */     
/*  77 */     long currentTime = position.getFixTime().getTime();
/*  78 */     if (newMotion != oldMotion.booleanValue()) {
/*  79 */       if (deviceState.getMotionPosition() == null) {
/*  80 */         deviceState.setMotionPosition(position);
/*     */       }
/*     */     } else {
/*  83 */       deviceState.setMotionPosition(null);
/*     */     } 
/*     */     
/*  86 */     Position motionPosition = deviceState.getMotionPosition();
/*  87 */     if (motionPosition != null) {
/*  88 */       long motionTime = motionPosition.getFixTime().getTime();
/*  89 */       double distance = ReportUtils.calculateDistance(motionPosition, position, false);
/*  90 */       Boolean ignition = null;
/*  91 */       if (this.tripsConfig.getUseIgnition() && position
/*  92 */         .getAttributes().containsKey("ignition")) {
/*  93 */         ignition = Boolean.valueOf(position.getBoolean("ignition"));
/*     */       }
/*  95 */       if (newMotion) {
/*  96 */         if (motionTime + this.tripsConfig.getMinimalTripDuration() <= currentTime || distance >= this.tripsConfig
/*  97 */           .getMinimalTripDistance()) {
/*  98 */           result = newEvent(deviceState, newMotion);
/*     */         }
/*     */       }
/* 101 */       else if (motionTime + this.tripsConfig.getMinimalParkingDuration() <= currentTime || (ignition != null && 
/* 102 */         !ignition.booleanValue())) {
/* 103 */         result = newEvent(deviceState, newMotion);
/*     */       } 
/*     */     } 
/*     */     
/* 107 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Map<Event, Position> analyzePosition(Position position) {
/* 113 */     long deviceId = position.getDeviceId();
/* 114 */     Device device = this.identityManager.getById(deviceId);
/* 115 */     if (device == null) {
/* 116 */       return null;
/*     */     }
/* 118 */     if (!this.identityManager.isLatestPosition(position) || (
/* 119 */       !this.tripsConfig.getProcessInvalidPositions() && !position.getValid())) {
/* 120 */       return null;
/*     */     }
/*     */     
/* 123 */     Map<Event, Position> result = null;
/* 124 */     DeviceState deviceState = this.deviceManager.getDeviceState(deviceId);
/*     */     
/* 126 */     if (deviceState.getMotionState() == null) {
/* 127 */       deviceState.setMotionState(position.getBoolean("motion"));
/*     */     } else {
/* 129 */       result = updateMotionState(deviceState, position);
/*     */     } 
/* 131 */     this.deviceManager.setDeviceState(deviceId, deviceState);
/* 132 */     return result;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\handler\events\MotionEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */