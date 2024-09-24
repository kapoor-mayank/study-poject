/*    */ package org.traccar.handler;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.buffer.ByteBufUtil;
/*    */ import io.netty.channel.ChannelDuplexHandler;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.channel.ChannelPromise;
/*    */ import java.net.InetSocketAddress;
/*    */ import java.net.SocketAddress;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
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
/*    */ public class StandardLoggingHandler
/*    */   extends ChannelDuplexHandler
/*    */ {
/* 33 */   private static final Logger LOGGER = LoggerFactory.getLogger(StandardLoggingHandler.class);
/*    */ 
/*    */   
/*    */   public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
/* 37 */     log(ctx, false, msg);
/* 38 */     super.channelRead(ctx, msg);
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
/* 43 */     log(ctx, true, msg);
/* 44 */     super.write(ctx, msg, promise);
/*    */   }
/*    */   
/*    */   public void log(ChannelHandlerContext ctx, boolean downstream, Object o) {
/* 48 */     if (o instanceof NetworkMessage) {
/* 49 */       NetworkMessage networkMessage = (NetworkMessage)o;
/* 50 */       if (networkMessage.getMessage() instanceof ByteBuf) {
/* 51 */         log(ctx, downstream, networkMessage.getRemoteAddress(), (ByteBuf)networkMessage.getMessage());
/*    */       }
/* 53 */     } else if (o instanceof ByteBuf) {
/* 54 */       log(ctx, downstream, ctx.channel().remoteAddress(), (ByteBuf)o);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void log(ChannelHandlerContext ctx, boolean downstream, SocketAddress remoteAddress, ByteBuf buf) {
/* 59 */     StringBuilder message = new StringBuilder();
/*    */     
/* 61 */     message.append("[").append(ctx.channel().id().asShortText()).append(": ");
/* 62 */     message.append(((InetSocketAddress)ctx.channel().localAddress()).getPort());
/* 63 */     if (downstream) {
/* 64 */       message.append(" > ");
/*    */     } else {
/* 66 */       message.append(" < ");
/*    */     } 
/*    */     
/* 69 */     if (remoteAddress instanceof InetSocketAddress) {
/* 70 */       InetSocketAddress remote = (InetSocketAddress)remoteAddress;
/* 71 */       message.append(remote.getHostString()).append(":").append(remote.getPort());
/*    */     } else {
/* 73 */       message.append("unknown");
/*    */     } 
/* 75 */     message.append("]");
/* 76 */     message.append(" [").append((ctx.channel() instanceof io.netty.channel.socket.DatagramChannel) ? "UDP" : "TCP").append("]");
/* 77 */     message.append(" HEX: ");
/* 78 */     message.append(ByteBufUtil.hexDump(buf));
/*    */     
/* 80 */     LOGGER.info(message.toString());
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\handler\StandardLoggingHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */