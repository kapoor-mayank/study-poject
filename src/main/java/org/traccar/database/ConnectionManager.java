/*     */ package org.traccar.database;
/*     */ 
/*     */ import io.netty.channel.Channel;
/*     */ import io.netty.util.Timeout;
/*     */ import io.netty.util.TimerTask;
/*     */ import java.net.SocketAddress;
/*     */ import java.sql.SQLException;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.traccar.Context;
/*     */ import org.traccar.GlobalTimer;
/*     */ import org.traccar.Main;
/*     */ import org.traccar.Protocol;
/*     */ import org.traccar.handler.events.MotionEventHandler;
/*     */ import org.traccar.handler.events.OverspeedEventHandler;
/*     */ import org.traccar.model.Device;
/*     */ import org.traccar.model.DeviceState;
/*     */ import org.traccar.model.Event;
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
/*     */ public class ConnectionManager
/*     */ {
/*  46 */   private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionManager.class);
/*     */   
/*     */   private static final long DEFAULT_TIMEOUT = 600L;
/*     */   
/*     */   private final long deviceTimeout;
/*     */   
/*     */   private final boolean enableStatusEvents = true;
/*     */   private final boolean updateDeviceState;
/*  54 */   private final Map<Long, ActiveDevice> activeDevices = new ConcurrentHashMap<>();
/*  55 */   private final Map<Long, Set<UpdateListener>> listeners = new ConcurrentHashMap<>();
/*  56 */   private final Map<Long, Timeout> timeouts = new ConcurrentHashMap<>();
/*     */   
/*     */   public ConnectionManager() {
/*  59 */     this.deviceTimeout = Context.getConfig().getLong("status.timeout", 600L) * 1000L;
/*  60 */     this.updateDeviceState = Context.getConfig().getBoolean("status.updateDeviceState");
/*     */   }
/*     */   
/*     */   public void addActiveDevice(long deviceId, Protocol protocol, Channel channel, SocketAddress remoteAddress) {
/*  64 */     if (!"dualcam".equals(protocol.getName())) {
/*  65 */       this.activeDevices.put(Long.valueOf(deviceId), new ActiveDevice(deviceId, protocol, channel, remoteAddress));
/*     */     }
/*     */   }
/*     */   
/*     */   public void removeActiveDevice(Channel channel) {
/*  70 */     for (ActiveDevice activeDevice : this.activeDevices.values()) {
/*  71 */       if (activeDevice.getChannel() == channel) {
/*  72 */         updateDevice(activeDevice.getDeviceId(), "offline", null);
/*  73 */         this.activeDevices.remove(Long.valueOf(activeDevice.getDeviceId()));
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public ActiveDevice getActiveDevice(long deviceId) {
/*  80 */     return this.activeDevices.get(Long.valueOf(deviceId));
/*     */   }
/*     */   
/*     */   public void updateDevice(final long deviceId, String status, Date time) {
/*  84 */     Device device = Context.getIdentityManager().getById(deviceId);
/*  85 */     if (device == null) {
/*     */       return;
/*     */     }
/*     */     
/*  89 */     String oldStatus = device.getStatus();
/*  90 */     device.setStatus(status);
/*     */     
/*  92 */     if (!status.equals(oldStatus)) {
/*     */       String eventType;
/*  94 */       Map<Event, Position> events = new HashMap<>();
/*  95 */       switch (status) {
/*     */         case "online":
/*  97 */           if (Context.getRedisManager() != null) {
/*  98 */             Context.getRedisManager().addDevice(device);
/*     */           }
/* 100 */           eventType = "deviceOnline";
/*     */           break;
/*     */         case "unknown":
/* 103 */           if (Context.getRedisManager() != null) {
/* 104 */             Context.getRedisManager().removeDevice(device);
/*     */           }
/* 106 */           eventType = "deviceUnknown";
/* 107 */           if (this.updateDeviceState) {
/* 108 */             events.putAll(updateDeviceState(deviceId));
/*     */           }
/*     */           break;
/*     */         default:
/* 112 */           if (Context.getRedisManager() != null) {
/* 113 */             Context.getRedisManager().removeDevice(device);
/*     */           }
/* 115 */           eventType = "deviceOffline";
/* 116 */           if (this.updateDeviceState) {
/* 117 */             events.putAll(updateDeviceState(deviceId));
/*     */           }
/*     */           break;
/*     */       } 
/* 121 */       events.put(new Event(eventType, deviceId), null);
/* 122 */       Context.getNotificationManager().updateEvents(events);
/*     */     } 
/*     */     
/* 125 */     Timeout timeout = this.timeouts.remove(Long.valueOf(deviceId));
/* 126 */     if (timeout != null) {
/* 127 */       timeout.cancel();
/*     */     }
/*     */     
/* 130 */     if (time != null) {
/* 131 */       device.setLastUpdate(time);
/*     */     }
/*     */     
/* 134 */     if (status.equals("online")) {
/* 135 */       this.timeouts.put(Long.valueOf(deviceId), GlobalTimer.getTimer().newTimeout(new TimerTask()
/*     */             {
/*     */               public void run(Timeout timeout) {
/* 138 */                 if (!timeout.isCancelled()) {
/* 139 */                   ConnectionManager.this.updateDevice(deviceId, "unknown", null);
/*     */                 }
/*     */               }
/*     */             },this.deviceTimeout, TimeUnit.MILLISECONDS));
/*     */     }
/*     */     
/*     */     try {
/* 146 */       Context.getDeviceManager().updateDeviceStatus(device);
/* 147 */     } catch (SQLException error) {
/* 148 */       LOGGER.warn("Update device status error", error);
/*     */     } 
/*     */     
/* 151 */     updateDevice(device);
/*     */     
/* 153 */     if (status.equals("online") && !oldStatus.equals("online")) {
/* 154 */       Context.getCommandsManager().sendQueuedCommands(getActiveDevice(deviceId));
/*     */     }
/*     */   }
/*     */   
/*     */   public Map<Event, Position> updateDeviceState(long deviceId) {
/* 159 */     DeviceState deviceState = Context.getDeviceManager().getDeviceState(deviceId);
/* 160 */     Map<Event, Position> result = new HashMap<>();
/*     */ 
/*     */     
/* 163 */     Map<Event, Position> event = ((MotionEventHandler)Main.getInjector().getInstance(MotionEventHandler.class)).updateMotionState(deviceState);
/* 164 */     if (event != null) {
/* 165 */       result.putAll(event);
/*     */     }
/*     */ 
/*     */     
/* 169 */     event = ((OverspeedEventHandler)Main.getInjector().getInstance(OverspeedEventHandler.class)).updateOverspeedState(deviceState, Context.getDeviceManager()
/* 170 */         .lookupAttributeDouble(deviceId, "speedLimit", 0.0D, false));
/* 171 */     if (event != null) {
/* 172 */       result.putAll(event);
/*     */     }
/*     */     
/* 175 */     return result;
/*     */   } public static interface UpdateListener {
/*     */     void onUpdateDevice(Device param1Device); void onUpdatePosition(Position param1Position); void onUpdateEvent(Event param1Event); }
/*     */   public synchronized void updateDevice(Device device) {
/* 179 */     for (Iterator<Long> iterator = Context.getPermissionsManager().getDeviceUsers(device.getId()).iterator(); iterator.hasNext(); ) { long userId = ((Long)iterator.next()).longValue();
/* 180 */       if (this.listeners.containsKey(Long.valueOf(userId))) {
/* 181 */         for (UpdateListener listener : this.listeners.get(Long.valueOf(userId))) {
/* 182 */           listener.onUpdateDevice(device);
/*     */         }
/*     */       } }
/*     */   
/*     */   }
/*     */   
/*     */   public synchronized void updatePosition(Position position) {
/* 189 */     long deviceId = position.getDeviceId();
/*     */     
/* 191 */     for (Iterator<Long> iterator = Context.getPermissionsManager().getDeviceUsers(deviceId).iterator(); iterator.hasNext(); ) { long userId = ((Long)iterator.next()).longValue();
/* 192 */       if (this.listeners.containsKey(Long.valueOf(userId))) {
/* 193 */         for (UpdateListener listener : this.listeners.get(Long.valueOf(userId))) {
/* 194 */           listener.onUpdatePosition(position);
/*     */         }
/*     */       } }
/*     */   
/*     */   }
/*     */   
/*     */   public synchronized void updateEvent(long userId, Event event) {
/* 201 */     if (this.listeners.containsKey(Long.valueOf(userId))) {
/* 202 */       for (UpdateListener listener : this.listeners.get(Long.valueOf(userId))) {
/* 203 */         listener.onUpdateEvent(event);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void addListener(long userId, UpdateListener listener) {
/* 215 */     if (!this.listeners.containsKey(Long.valueOf(userId))) {
/* 216 */       this.listeners.put(Long.valueOf(userId), new HashSet<>());
/*     */     }
/* 218 */     ((Set<UpdateListener>)this.listeners.get(Long.valueOf(userId))).add(listener);
/*     */   }
/*     */   
/*     */   public synchronized void removeListener(long userId, UpdateListener listener) {
/* 222 */     if (!this.listeners.containsKey(Long.valueOf(userId))) {
/* 223 */       this.listeners.put(Long.valueOf(userId), new HashSet<>());
/*     */     }
/* 225 */     ((Set)this.listeners.get(Long.valueOf(userId))).remove(listener);
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\database\ConnectionManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */