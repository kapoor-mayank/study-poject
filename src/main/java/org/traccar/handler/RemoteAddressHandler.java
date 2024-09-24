/*    */ package org.traccar.handler;
/*    */ 
/*    */ import io.netty.channel.ChannelHandler.Sharable;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.channel.ChannelInboundHandlerAdapter;
/*    */ import java.net.InetSocketAddress;
/*    */ import org.traccar.model.Position;
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
/*    */ @Sharable
/*    */ public class RemoteAddressHandler
/*    */   extends ChannelInboundHandlerAdapter
/*    */ {
/*    */   public void channelRead(ChannelHandlerContext ctx, Object msg) {
/* 31 */     InetSocketAddress remoteAddress = (InetSocketAddress)ctx.channel().remoteAddress();
/* 32 */     String hostAddress = (remoteAddress != null) ? remoteAddress.getAddress().getHostAddress() : null;
/*    */     
/* 34 */     if (msg instanceof Position) {
/* 35 */       Position position = (Position)msg;
/* 36 */       position.set("ip", hostAddress);
/*    */     } 
/*    */     
/* 39 */     ctx.fireChannelRead(msg);
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\handler\RemoteAddressHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */