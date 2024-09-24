/*     */ package org.traccar;
/*     */ 
/*     */ import io.netty.bootstrap.AbstractBootstrap;
/*     */ import io.netty.bootstrap.Bootstrap;
/*     */ import io.netty.bootstrap.ServerBootstrap;
/*     */ import io.netty.channel.Channel;
/*     */ import io.netty.channel.ChannelHandler;
/*     */ import io.netty.channel.group.ChannelGroup;
/*     */ import io.netty.channel.group.DefaultChannelGroup;
/*     */ import io.netty.channel.socket.nio.NioDatagramChannel;
/*     */ import io.netty.channel.socket.nio.NioServerSocketChannel;
/*     */ import io.netty.handler.ssl.SslHandler;
/*     */ import io.netty.util.concurrent.EventExecutor;
/*     */ import io.netty.util.concurrent.GlobalEventExecutor;
/*     */ import java.net.InetSocketAddress;
/*     */ import javax.net.ssl.SSLContext;
/*     */ import javax.net.ssl.SSLEngine;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class TrackerServer
/*     */   implements TrackerConnector
/*     */ {
/*     */   private final boolean datagram;
/*     */   private final boolean secure;
/*     */   private final AbstractBootstrap bootstrap;
/*     */   private final int port;
/*     */   private final String address;
/*  45 */   private final ChannelGroup channelGroup = (ChannelGroup)new DefaultChannelGroup((EventExecutor)GlobalEventExecutor.INSTANCE);
/*     */ 
/*     */   
/*     */   public boolean isDatagram() {
/*  49 */     return this.datagram;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSecure() {
/*  54 */     return this.secure;
/*     */   }
/*     */   
/*     */   public TrackerServer(boolean datagram, String protocol) {
/*  58 */     this.datagram = datagram;
/*     */     
/*  60 */     this.secure = Context.getConfig().getBoolean(protocol + ".ssl");
/*  61 */     this.address = Context.getConfig().getString(protocol + ".address");
/*  62 */     this.port = Context.getConfig().getInteger(protocol + ".port");
/*     */     
/*  64 */     BasePipelineFactory pipelineFactory = new BasePipelineFactory(this, protocol)
/*     */       {
/*     */         protected void addTransportHandlers(PipelineBuilder pipeline) {
/*     */           try {
/*  68 */             if (TrackerServer.this.isSecure()) {
/*  69 */               SSLEngine engine = SSLContext.getDefault().createSSLEngine();
/*  70 */               pipeline.addLast((ChannelHandler)new SslHandler(engine));
/*     */             } 
/*  72 */           } catch (Exception e) {
/*  73 */             throw new RuntimeException(e);
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/*     */         protected void addProtocolHandlers(PipelineBuilder pipeline) {
/*  79 */           TrackerServer.this.addProtocolHandlers(pipeline);
/*     */         }
/*     */       };
/*     */     
/*  83 */     if (datagram) {
/*     */       
/*  85 */       this
/*     */ 
/*     */         
/*  88 */         .bootstrap = ((Bootstrap)((Bootstrap)(new Bootstrap()).group(EventLoopGroupFactory.getWorkerGroup())).channel(NioDatagramChannel.class)).handler((ChannelHandler)pipelineFactory);
/*     */     }
/*     */     else {
/*     */       
/*  92 */       this
/*     */ 
/*     */         
/*  95 */         .bootstrap = (AbstractBootstrap)((ServerBootstrap)(new ServerBootstrap()).group(EventLoopGroupFactory.getBossGroup(), EventLoopGroupFactory.getWorkerGroup()).channel(NioServerSocketChannel.class)).childHandler((ChannelHandler)pipelineFactory);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract void addProtocolHandlers(PipelineBuilder paramPipelineBuilder);
/*     */   
/*     */   public int getPort() {
/* 103 */     return this.port;
/*     */   }
/*     */   
/*     */   public String getAddress() {
/* 107 */     return this.address;
/*     */   }
/*     */ 
/*     */   
/*     */   public ChannelGroup getChannelGroup() {
/* 112 */     return this.channelGroup;
/*     */   }
/*     */ 
/*     */   
/*     */   public void start() throws Exception {
/*     */     InetSocketAddress endpoint;
/* 118 */     if (this.address == null) {
/* 119 */       endpoint = new InetSocketAddress(this.port);
/*     */     } else {
/* 121 */       endpoint = new InetSocketAddress(this.address, this.port);
/*     */     } 
/*     */     
/* 124 */     Channel channel = this.bootstrap.bind(endpoint).syncUninterruptibly().channel();
/* 125 */     if (channel != null) {
/* 126 */       getChannelGroup().add(channel);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void stop() {
/* 132 */     this.channelGroup.close().awaitUninterruptibly();
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\TrackerServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */