/*     */ package org.traccar;
/*     */ 
/*     */ import io.netty.buffer.ByteBufAllocator;
/*     */ import io.netty.channel.Channel;
/*     */ import io.netty.channel.ChannelFuture;
/*     */ import io.netty.channel.ChannelHandler;
/*     */ import io.netty.channel.ChannelHandlerContext;
/*     */ import io.netty.channel.ChannelInboundInvoker;
/*     */ import io.netty.channel.ChannelOutboundInvoker;
/*     */ import io.netty.channel.ChannelPipeline;
/*     */ import io.netty.channel.ChannelProgressivePromise;
/*     */ import io.netty.channel.ChannelPromise;
/*     */ import io.netty.util.Attribute;
/*     */ import io.netty.util.AttributeKey;
/*     */ import io.netty.util.concurrent.EventExecutor;
/*     */ import java.net.SocketAddress;
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
/*     */ public class WrapperContext
/*     */   implements ChannelHandlerContext
/*     */ {
/*     */   private ChannelHandlerContext context;
/*     */   private SocketAddress remoteAddress;
/*     */   
/*     */   public WrapperContext(ChannelHandlerContext context, SocketAddress remoteAddress) {
/*  38 */     this.context = context;
/*  39 */     this.remoteAddress = remoteAddress;
/*     */   }
/*     */ 
/*     */   
/*     */   public Channel channel() {
/*  44 */     return this.context.channel();
/*     */   }
/*     */ 
/*     */   
/*     */   public EventExecutor executor() {
/*  49 */     return this.context.executor();
/*     */   }
/*     */ 
/*     */   
/*     */   public String name() {
/*  54 */     return this.context.name();
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelHandler handler() {
/*  59 */     return this.context.handler();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRemoved() {
/*  64 */     return this.context.isRemoved();
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelHandlerContext fireChannelRegistered() {
/*  69 */     return this.context.fireChannelRegistered();
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelHandlerContext fireChannelUnregistered() {
/*  74 */     return this.context.fireChannelUnregistered();
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelHandlerContext fireChannelActive() {
/*  79 */     return this.context.fireChannelActive();
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelHandlerContext fireChannelInactive() {
/*  84 */     return this.context.fireChannelInactive();
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelHandlerContext fireExceptionCaught(Throwable cause) {
/*  89 */     return this.context.fireExceptionCaught(cause);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelHandlerContext fireUserEventTriggered(Object evt) {
/*  94 */     return this.context.fireUserEventTriggered(evt);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelHandlerContext fireChannelRead(Object msg) {
/*  99 */     if (!(msg instanceof NetworkMessage)) {
/* 100 */       msg = new NetworkMessage(msg, this.remoteAddress);
/*     */     }
/* 102 */     return this.context.fireChannelRead(msg);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelHandlerContext fireChannelReadComplete() {
/* 107 */     return this.context.fireChannelReadComplete();
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelHandlerContext fireChannelWritabilityChanged() {
/* 112 */     return this.context.fireChannelWritabilityChanged();
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelFuture bind(SocketAddress localAddress) {
/* 117 */     return this.context.bind(localAddress);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelFuture connect(SocketAddress remoteAddress) {
/* 122 */     return this.context.connect(remoteAddress);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelFuture connect(SocketAddress remoteAddress, SocketAddress localAddress) {
/* 127 */     return this.context.connect(remoteAddress, localAddress);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelFuture disconnect() {
/* 132 */     return this.context.disconnect();
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelFuture close() {
/* 137 */     return this.context.close();
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelFuture deregister() {
/* 142 */     return this.context.deregister();
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelFuture bind(SocketAddress localAddress, ChannelPromise promise) {
/* 147 */     return this.context.bind(localAddress, promise);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelFuture connect(SocketAddress remoteAddress, ChannelPromise promise) {
/* 152 */     return this.context.connect(remoteAddress, promise);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelFuture connect(SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) {
/* 157 */     return this.context.connect(remoteAddress, localAddress, promise);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelFuture disconnect(ChannelPromise promise) {
/* 162 */     return this.context.disconnect(promise);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelFuture close(ChannelPromise promise) {
/* 167 */     return this.context.close(promise);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelFuture deregister(ChannelPromise promise) {
/* 172 */     return this.context.deregister(promise);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelHandlerContext read() {
/* 177 */     return this.context.read();
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelFuture write(Object msg) {
/* 182 */     return this.context.write(msg);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelFuture write(Object msg, ChannelPromise promise) {
/* 187 */     if (!(msg instanceof NetworkMessage)) {
/* 188 */       msg = new NetworkMessage(msg, this.remoteAddress);
/*     */     }
/* 190 */     return this.context.write(msg, promise);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelHandlerContext flush() {
/* 195 */     return this.context.flush();
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelFuture writeAndFlush(Object msg, ChannelPromise promise) {
/* 200 */     return this.context.writeAndFlush(msg, promise);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelFuture writeAndFlush(Object msg) {
/* 205 */     return this.context.writeAndFlush(msg);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelPromise newPromise() {
/* 210 */     return this.context.newPromise();
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelProgressivePromise newProgressivePromise() {
/* 215 */     return this.context.newProgressivePromise();
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelFuture newSucceededFuture() {
/* 220 */     return this.context.newSucceededFuture();
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelFuture newFailedFuture(Throwable cause) {
/* 225 */     return this.context.newFailedFuture(cause);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelPromise voidPromise() {
/* 230 */     return this.context.voidPromise();
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelPipeline pipeline() {
/* 235 */     return this.context.pipeline();
/*     */   }
/*     */ 
/*     */   
/*     */   public ByteBufAllocator alloc() {
/* 240 */     return this.context.alloc();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> Attribute<T> attr(AttributeKey<T> key) {
/* 246 */     return this.context.attr(key);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> boolean hasAttr(AttributeKey<T> key) {
/* 252 */     return this.context.hasAttr(key);
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\WrapperContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */