/*    */ package org.traccar.database;
/*    */ 
/*    */ import io.netty.channel.Channel;
/*    */ import java.net.SocketAddress;
/*    */ import org.traccar.NetworkMessage;
/*    */ import org.traccar.Protocol;
/*    */ import org.traccar.model.Command;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ActiveDevice
/*    */ {
/*    */   private final long deviceId;
/*    */   private final Protocol protocol;
/*    */   private final Channel channel;
/*    */   private final SocketAddress remoteAddress;
/*    */   
/*    */   public ActiveDevice(long deviceId, Protocol protocol, Channel channel, SocketAddress remoteAddress) {
/* 33 */     this.deviceId = deviceId;
/* 34 */     this.protocol = protocol;
/* 35 */     this.channel = channel;
/* 36 */     this.remoteAddress = remoteAddress;
/*    */   }
/*    */   
/*    */   public Channel getChannel() {
/* 40 */     return this.channel;
/*    */   }
/*    */   
/*    */   public long getDeviceId() {
/* 44 */     return this.deviceId;
/*    */   }
/*    */   
/*    */   public void sendCommand(Command command) {
/* 48 */     this.protocol.sendDataCommand(this, command);
/*    */   }
/*    */   
/*    */   public void write(Object message) {
/* 52 */     this.channel.writeAndFlush(new NetworkMessage(message, this.remoteAddress));
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\database\ActiveDevice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */