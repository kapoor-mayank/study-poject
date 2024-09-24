/*    */ package org.traccar;
/*    */ 
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.channel.ChannelOutboundHandler;
/*    */ import io.netty.channel.ChannelPromise;
/*    */ import java.net.SocketAddress;
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
/*    */ public class WrapperOutboundHandler
/*    */   implements ChannelOutboundHandler
/*    */ {
/*    */   private ChannelOutboundHandler handler;
/*    */   
/*    */   public ChannelOutboundHandler getWrappedHandler() {
/* 29 */     return this.handler;
/*    */   }
/*    */   
/*    */   public WrapperOutboundHandler(ChannelOutboundHandler handler) {
/* 33 */     this.handler = handler;
/*    */   }
/*    */ 
/*    */   
/*    */   public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception {
/* 38 */     this.handler.bind(ctx, localAddress, promise);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
/* 45 */     this.handler.connect(ctx, remoteAddress, localAddress, promise);
/*    */   }
/*    */ 
/*    */   
/*    */   public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
/* 50 */     this.handler.disconnect(ctx, promise);
/*    */   }
/*    */ 
/*    */   
/*    */   public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
/* 55 */     this.handler.close(ctx, promise);
/*    */   }
/*    */ 
/*    */   
/*    */   public void deregister(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
/* 60 */     this.handler.deregister(ctx, promise);
/*    */   }
/*    */ 
/*    */   
/*    */   public void read(ChannelHandlerContext ctx) throws Exception {
/* 65 */     this.handler.read(ctx);
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
/* 70 */     if (msg instanceof NetworkMessage) {
/* 71 */       NetworkMessage nm = (NetworkMessage)msg;
/* 72 */       this.handler.write(new WrapperContext(ctx, nm.getRemoteAddress()), nm.getMessage(), promise);
/*    */     } else {
/* 74 */       this.handler.write(ctx, msg, promise);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void flush(ChannelHandlerContext ctx) throws Exception {
/* 80 */     this.handler.flush(ctx);
/*    */   }
/*    */ 
/*    */   
/*    */   public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
/* 85 */     this.handler.handlerAdded(ctx);
/*    */   }
/*    */ 
/*    */   
/*    */   public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
/* 90 */     this.handler.handlerRemoved(ctx);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
/* 96 */     this.handler.exceptionCaught(ctx, cause);
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\WrapperOutboundHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */