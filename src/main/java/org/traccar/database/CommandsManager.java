/*     */ package org.traccar.database;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Queue;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ConcurrentLinkedQueue;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.traccar.BaseProtocol;
/*     */ import org.traccar.Context;
/*     */ import org.traccar.model.Command;
/*     */ import org.traccar.model.Position;
/*     */ import org.traccar.model.Typed;
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
/*     */ public class CommandsManager
/*     */   extends ExtendedObjectManager<Command>
/*     */ {
/*  40 */   private static final Logger LOGGER = LoggerFactory.getLogger(CommandsManager.class);
/*     */   
/*  42 */   private final Map<Long, Queue<Command>> deviceQueues = new ConcurrentHashMap<>();
/*     */   
/*     */   private boolean queueing;
/*     */   
/*     */   public CommandsManager(DataManager dataManager, boolean queueing) {
/*  47 */     super(dataManager, Command.class);
/*  48 */     this.queueing = queueing;
/*     */   }
/*     */   
/*     */   public boolean checkDeviceCommand(long deviceId, long commandId) {
/*  52 */     return !getAllDeviceItems(deviceId).contains(Long.valueOf(commandId));
/*     */   }
/*     */   
/*     */   public boolean sendCommand(Command command) throws Exception {
/*  56 */     long deviceId = command.getDeviceId();
/*  57 */     if (command.getId() != 0L) {
/*  58 */       command = getById(command.getId()).clone();
/*  59 */       command.setDeviceId(deviceId);
/*     */     } 
/*  61 */     if (command.getTextChannel()) {
/*  62 */       Position lastPosition = Context.getIdentityManager().getLastPosition(deviceId);
/*  63 */       String phone = Context.getIdentityManager().getById(deviceId).getPhone();
/*  64 */       if (lastPosition != null) {
/*  65 */         BaseProtocol protocol = Context.getServerManager().getProtocol(lastPosition.getProtocol());
/*  66 */         protocol.sendTextCommand(phone, command);
/*  67 */       } else if (command.getType().equals("custom")) {
/*  68 */         if (Context.getSmsManager() != null) {
/*  69 */           Context.getSmsManager().sendMessageSync(phone, command.getString("data"), true);
/*     */         } else {
/*  71 */           throw new RuntimeException("SMS is not enabled");
/*     */         } 
/*     */       } else {
/*  74 */         throw new RuntimeException("Command " + command.getType() + " is not supported");
/*     */       } 
/*     */     } else {
/*  77 */       ActiveDevice activeDevice = Context.getConnectionManager().getActiveDevice(deviceId);
/*  78 */       if (activeDevice != null)
/*  79 */       { activeDevice.sendCommand(command); }
/*  80 */       else { if (!this.queueing) {
/*  81 */           throw new RuntimeException("Device is not online");
/*     */         }
/*  83 */         getDeviceQueue(deviceId).add(command);
/*  84 */         return false; }
/*     */     
/*     */     } 
/*  87 */     return true;
/*     */   }
/*     */   
/*     */   public Collection<Long> getSupportedCommands(long deviceId) {
/*  91 */     List<Long> result = new ArrayList<>();
/*  92 */     Position lastPosition = Context.getIdentityManager().getLastPosition(deviceId);
/*  93 */     for (Iterator<Long> iterator = getAllDeviceItems(deviceId).iterator(); iterator.hasNext(); ) { long commandId = ((Long)iterator.next()).longValue();
/*  94 */       Command command = getById(commandId);
/*  95 */       if (lastPosition != null) {
/*  96 */         BaseProtocol protocol = Context.getServerManager().getProtocol(lastPosition.getProtocol());
/*  97 */         if ((command.getTextChannel() && protocol.getSupportedTextCommands().contains(command.getType())) || (
/*  98 */           !command.getTextChannel() && protocol
/*  99 */           .getSupportedDataCommands().contains(command.getType())))
/* 100 */           result.add(Long.valueOf(commandId));  continue;
/*     */       } 
/* 102 */       if (command.getType().equals("custom")) {
/* 103 */         result.add(Long.valueOf(commandId));
/*     */       } }
/*     */     
/* 106 */     return result;
/*     */   }
/*     */   
/*     */   public Collection<Typed> getCommandTypes(long deviceId, boolean textChannel) {
/* 110 */     Position lastPosition = Context.getIdentityManager().getLastPosition(deviceId);
/* 111 */     if (lastPosition != null) {
/* 112 */       return getCommandTypes(lastPosition.getProtocol(), textChannel);
/*     */     }
/* 114 */     return Collections.singletonList(new Typed("custom"));
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<Typed> getCommandTypes(String protocolName, boolean textChannel) {
/* 119 */     List<Typed> result = new ArrayList<>();
/* 120 */     BaseProtocol protocol = Context.getServerManager().getProtocol(protocolName);
/*     */     
/* 122 */     Collection<String> commands = textChannel ? protocol.getSupportedTextCommands() : protocol.getSupportedDataCommands();
/* 123 */     for (String commandKey : commands) {
/* 124 */       result.add(new Typed(commandKey));
/*     */     }
/* 126 */     return result;
/*     */   }
/*     */   
/*     */   public Collection<Typed> getAllCommandTypes() {
/* 130 */     List<Typed> result = new ArrayList<>();
/* 131 */     Field[] fields = Command.class.getDeclaredFields();
/* 132 */     for (Field field : fields) {
/* 133 */       if (Modifier.isStatic(field.getModifiers()) && field.getName().startsWith("TYPE_")) {
/*     */         try {
/* 135 */           result.add(new Typed(field.get(null).toString()));
/* 136 */         } catch (IllegalArgumentException|IllegalAccessException error) {
/* 137 */           LOGGER.warn("Get command types error", error);
/*     */         } 
/*     */       }
/*     */     } 
/* 141 */     return result;
/*     */   }
/*     */   
/*     */   private Queue<Command> getDeviceQueue(long deviceId) {
/* 145 */     if (!this.deviceQueues.containsKey(Long.valueOf(deviceId))) {
/* 146 */       this.deviceQueues.put(Long.valueOf(deviceId), new ConcurrentLinkedQueue<>());
/*     */     }
/* 148 */     return this.deviceQueues.get(Long.valueOf(deviceId));
/*     */   }
/*     */   
/*     */   public void sendQueuedCommands(ActiveDevice activeDevice) {
/* 152 */     Queue<Command> deviceQueue = this.deviceQueues.get(Long.valueOf(activeDevice.getDeviceId()));
/* 153 */     if (deviceQueue != null) {
/* 154 */       Command command = deviceQueue.poll();
/* 155 */       while (command != null) {
/* 156 */         activeDevice.sendCommand(command);
/* 157 */         command = deviceQueue.poll();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\database\CommandsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */