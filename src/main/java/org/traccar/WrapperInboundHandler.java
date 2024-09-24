/*    */ package org.traccar;
/*    */ 
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.channel.ChannelInboundHandler;
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
/*    */ public class WrapperInboundHandler
/*    */   implements ChannelInboundHandler
/*    */ {
/*    */   private ChannelInboundHandler handler;
/*    */   
/*    */   public ChannelInboundHandler getWrappedHandler() {
/* 26 */     return this.handler;
/*    */   }
/*    */   
/*    */   public WrapperInboundHandler(ChannelInboundHandler handler) {
/* 30 */     this.handler = handler;
/*    */   }
/*    */ 
/*    */   
/*    */   public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
/* 35 */     this.handler.channelRegistered(ctx);
/*    */   }
/*    */ 
/*    */   
/*    */   public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
/* 40 */     this.handler.channelUnregistered(ctx);
/*    */   }
/*    */ 
/*    */   
/*    */   public void channelActive(ChannelHandlerContext ctx) throws Exception {
/* 45 */     this.handler.channelActive(ctx);
/*    */   }
/*    */ 
/*    */   
/*    */   public void channelInactive(ChannelHandlerContext ctx) throws Exception {
/* 50 */     this.handler.channelInactive(ctx);
/*    */   }
/*    */ 
/*    */   
/*    */   public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
/* 55 */     if (msg instanceof NetworkMessage) {
/* 56 */       NetworkMessage nm = (NetworkMessage)msg;
/* 57 */       this.handler.channelRead(new WrapperContext(ctx, nm.getRemoteAddress()), nm.getMessage());
/*    */     } else {
/* 59 */       this.handler.channelRead(ctx, msg);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
/* 65 */     this.handler.channelReadComplete(ctx);
/*    */   }
/*    */ 
/*    */   
/*    */   public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
/* 70 */     this.handler.userEventTriggered(ctx, evt);
/*    */   }
/*    */ 
/*    */   
/*    */   public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
/* 75 */     this.handler.channelWritabilityChanged(ctx);
/*    */   }
/*    */ 
/*    */   
/*    */   public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
/* 80 */     this.handler.handlerAdded(ctx);
/*    */   }
/*    */ 
/*    */   
/*    */   public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
/* 85 */     this.handler.handlerRemoved(ctx);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
/* 91 */     this.handler.exceptionCaught(ctx, cause);
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\WrapperInboundHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */