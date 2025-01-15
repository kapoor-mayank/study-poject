/*    */ package org.traccar.handler;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelDuplexHandler;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.channel.ChannelPromise;
/*    */ import io.netty.channel.socket.DatagramPacket;
/*    */ import java.net.InetSocketAddress;
/*    */ import org.traccar.NetworkMessage;
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
/*    */ 
/*    */ public class NetworkMessageHandler
/*    */   extends ChannelDuplexHandler
/*    */ {
/*    */   public void channelRead(ChannelHandlerContext ctx, Object msg) {
/* 32 */     if (ctx.channel() instanceof io.netty.channel.socket.DatagramChannel) {
/* 33 */       DatagramPacket packet = (DatagramPacket)msg;
/* 34 */       ctx.fireChannelRead(new NetworkMessage(packet.content(), packet.sender()));
/* 35 */     } else if (msg instanceof ByteBuf) {
/* 36 */       ByteBuf buffer = (ByteBuf)msg;
/* 37 */       ctx.fireChannelRead(new NetworkMessage(buffer, ctx.channel().remoteAddress()));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
/* 43 */     if (msg instanceof NetworkMessage) {
/* 44 */       NetworkMessage message = (NetworkMessage)msg;
/* 45 */       if (ctx.channel() instanceof io.netty.channel.socket.DatagramChannel) {
/* 46 */         InetSocketAddress recipient = (InetSocketAddress)message.getRemoteAddress();
/* 47 */         InetSocketAddress sender = (InetSocketAddress)ctx.channel().localAddress();
/* 48 */         ctx.write(new DatagramPacket((ByteBuf)message.getMessage(), recipient, sender), promise);
/*    */       } else {
/* 50 */         ctx.write(message.getMessage(), promise);
/*    */       } 
/*    */     } else {
/* 53 */       ctx.write(msg, promise);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\handler\NetworkMessageHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */