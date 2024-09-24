/*    */ package org.traccar.handler;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandler;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.channel.ChannelInboundHandlerAdapter;
/*    */ import io.netty.channel.socket.DatagramPacket;
/*    */ import java.net.SocketAddress;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.traccar.Context;
/*    */ import org.traccar.protocol.GoSafeProtocolDecoder;
/*    */ import org.traccar.protocol.Gt06ProtocolDecoder;
/*    */ import org.traccar.protocol.H02ProtocolDecoder;
/*    */ 
/*    */ 
/*    */ public class ForwarderHandler
/*    */   extends ChannelInboundHandlerAdapter
/*    */ {
/* 22 */   private Map<SocketAddress, String> deviceIdMap = new HashMap<>();
/* 23 */   private Map<SocketAddress, List<byte[]>> bufferMap = new HashMap<>();
/*    */   
/*    */   public void identify(String uniqueId, SocketAddress remoteAddress) {
/* 26 */     this.deviceIdMap.put(remoteAddress, uniqueId);
/* 27 */     if (this.bufferMap.containsKey(remoteAddress)) {
/* 28 */       for (byte[] data : this.bufferMap.get(remoteAddress)) {
/* 29 */         Context.getDataForwarder().forward(uniqueId, data);
/*    */       }
/* 31 */       this.bufferMap.remove(remoteAddress);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean requirePrefix(ChannelHandlerContext context, String protocol, Class<? extends ChannelHandler> decoder) {
/* 37 */     return (context.channel().pipeline().get(decoder) != null && 
/* 38 */       Context.getConfig().getBoolean(protocol + ".forwardPrefix"));
/*    */   }
/*    */ 
/*    */   
/*    */   public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
/*    */     SocketAddress remoteAddress;
/*    */     ByteBuf buffer;
/* 45 */     if (ctx.channel() instanceof io.netty.channel.socket.DatagramChannel) {
/* 46 */       DatagramPacket message = (DatagramPacket)msg;
/* 47 */       remoteAddress = message.recipient();
/* 48 */       buffer = (ByteBuf)message.content();
/*    */     } else {
/* 50 */       remoteAddress = ctx.channel().remoteAddress();
/* 51 */       buffer = (ByteBuf)msg;
/*    */     } 
/*    */     
/* 54 */     byte[] data = new byte[buffer.readableBytes()];
/* 55 */     buffer.getBytes(buffer.readerIndex(), data);
/*    */     
/* 57 */     if (requirePrefix(ctx, "gt06", (Class)Gt06ProtocolDecoder.class) || 
/* 58 */       requirePrefix(ctx, "h02", (Class)H02ProtocolDecoder.class) || 
/* 59 */       requirePrefix(ctx, "gosafe", (Class)GoSafeProtocolDecoder.class)) {
/* 60 */       byte[] prefixedData = new byte[data.length + 3];
/* 61 */       prefixedData[0] = 81;
/* 62 */       prefixedData[1] = 90;
/* 63 */       prefixedData[2] = 69;
/* 64 */       System.arraycopy(data, 0, prefixedData, 3, data.length);
/* 65 */       data = prefixedData;
/*    */     } 
/*    */     
/* 68 */     if (this.deviceIdMap.containsKey(remoteAddress)) {
/* 69 */       Context.getDataForwarder().forward(this.deviceIdMap.get(remoteAddress), data);
/*    */     } else {
/* 71 */       if (!this.bufferMap.containsKey(remoteAddress)) {
/* 72 */         this.bufferMap.put(remoteAddress, (List)new ArrayList<>());
/*    */       }
/* 74 */       ((List<byte[]>)this.bufferMap.get(remoteAddress)).add(data);
/*    */     } 
/* 76 */     super.channelRead(ctx, msg);
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\handler\ForwarderHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */