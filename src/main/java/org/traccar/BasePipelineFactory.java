/*     */ package org.traccar;
/*     */ 
/*     */ import io.netty.channel.Channel;
/*     */ import io.netty.channel.ChannelHandler;
/*     */ import io.netty.channel.ChannelInboundHandler;
/*     */ import io.netty.channel.ChannelInitializer;
/*     */ import io.netty.channel.ChannelOutboundHandler;
/*     */ import io.netty.channel.ChannelPipeline;
/*     */ import io.netty.handler.timeout.IdleStateHandler;
/*     */ import java.util.Map;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.traccar.config.Keys;
/*     */ import org.traccar.handler.ComputedAttributesHandler;
/*     */ import org.traccar.handler.CopyAttributesHandler;
/*     */ import org.traccar.handler.DefaultDataHandler;
/*     */ import org.traccar.handler.DistanceHandler;
/*     */ import org.traccar.handler.EngineHoursHandler;
/*     */ import org.traccar.handler.FilterHandler;
/*     */ import org.traccar.handler.ForwarderHandler;
/*     */ import org.traccar.handler.GeocoderHandler;
/*     */ import org.traccar.handler.GeolocationHandler;
/*     */ import org.traccar.handler.HemisphereHandler;
/*     */ import org.traccar.handler.MotionHandler;
/*     */ import org.traccar.handler.NetworkMessageHandler;
/*     */ import org.traccar.handler.OpenChannelHandler;
/*     */ import org.traccar.handler.RemoteAddressHandler;
/*     */ import org.traccar.handler.StandardLoggingHandler;
/*     */ import org.traccar.handler.TimeHandler;
/*     */ import org.traccar.handler.events.AlertEventHandler;
/*     */ import org.traccar.handler.events.CommandResultEventHandler;
/*     */ import org.traccar.handler.events.DriverEventHandler;
/*     */ import org.traccar.handler.events.FuelDropEventHandler;
/*     */ import org.traccar.handler.events.GeofenceEventHandler;
/*     */ import org.traccar.handler.events.IgnitionEventHandler;
/*     */ import org.traccar.handler.events.MaintenanceEventHandler;
/*     */ import org.traccar.handler.events.MotionEventHandler;
/*     */ import org.traccar.handler.events.OverspeedEventHandler;
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
/*     */ public abstract class BasePipelineFactory
/*     */   extends ChannelInitializer<Channel>
/*     */ {
/*  58 */   private static final Logger LOGGER = LoggerFactory.getLogger(BasePipelineFactory.class);
/*     */   
/*     */   private final TrackerConnector connector;
/*     */   private final String protocol;
/*     */   private boolean eventsEnabled;
/*     */   private int timeout;
/*     */   
/*     */   public BasePipelineFactory(TrackerConnector connector, String protocol) {
/*  66 */     this.connector = connector;
/*  67 */     this.protocol = protocol;
/*  68 */     this.eventsEnabled = Context.getConfig().getBoolean(Keys.EVENT_ENABLE);
/*  69 */     this.timeout = Context.getConfig().getInteger(Keys.PROTOCOL_TIMEOUT.withPrefix(protocol));
/*  70 */     if (this.timeout == 0) {
/*  71 */       this.timeout = Context.getConfig().getInteger(Keys.SERVER_TIMEOUT);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addHandlers(ChannelPipeline pipeline, Class<? extends ChannelHandler>... handlerClasses) {
/*  80 */     for (Class<? extends ChannelHandler> handlerClass : handlerClasses) {
/*  81 */       if (handlerClass != null) {
/*  82 */         pipeline.addLast(new ChannelHandler[] { (ChannelHandler)Main.getInjector().getInstance(handlerClass) });
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static <T extends ChannelHandler> T getHandler(ChannelPipeline pipeline, Class<T> clazz) {
/*  88 */     for (Map.Entry<String, ChannelHandler> handlerEntry : (Iterable<Map.Entry<String, ChannelHandler>>)pipeline) {
/*  89 */       ChannelInboundHandler channelInboundHandler; ChannelOutboundHandler channelOutboundHandler; ChannelHandler handler = handlerEntry.getValue();
/*  90 */       if (handler instanceof WrapperInboundHandler) {
/*  91 */         channelInboundHandler = ((WrapperInboundHandler)handler).getWrappedHandler();
/*  92 */       } else if (channelInboundHandler instanceof WrapperOutboundHandler) {
/*  93 */         channelOutboundHandler = ((WrapperOutboundHandler)channelInboundHandler).getWrappedHandler();
/*     */       } 
/*  95 */       if (clazz.isAssignableFrom(channelOutboundHandler.getClass())) {
/*  96 */         return (T)channelOutboundHandler;
/*     */       }
/*     */     } 
/*  99 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initChannel(Channel channel) {
/* 104 */     ChannelPipeline pipeline = channel.pipeline();
/*     */     
/* 106 */     addTransportHandlers(xva$0 -> rec$.addLast(new ChannelHandler[] { xva$0 }));
/*     */     
/* 108 */     pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new ForwarderHandler() });
/* 109 */     if (this.timeout > 0 && !this.connector.isDatagram()) {
/* 110 */       pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new IdleStateHandler(this.timeout, 0, 0) });
/*     */     }
/* 112 */     pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new OpenChannelHandler(this.connector) });
/* 113 */     pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new NetworkMessageHandler() });
/* 114 */     pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new StandardLoggingHandler() });
/*     */     
/* 116 */     addProtocolHandlers(handler -> {
/*     */           WrapperOutboundHandler wrapperOutboundHandler; if (!(handler instanceof BaseProtocolDecoder) && !(handler instanceof BaseProtocolEncoder)) {
/*     */             WrapperInboundHandler wrapperInboundHandler;
/*     */             if (handler instanceof ChannelInboundHandler) {
/*     */               wrapperInboundHandler = new WrapperInboundHandler((ChannelInboundHandler)handler);
/*     */             } else {
/*     */               wrapperOutboundHandler = new WrapperOutboundHandler((ChannelOutboundHandler)wrapperInboundHandler);
/*     */             } 
/*     */           } 
/*     */           pipeline.addLast(new ChannelHandler[] { (ChannelHandler)wrapperOutboundHandler });
/*     */         });
/* 127 */     addHandlers(pipeline, (Class<? extends ChannelHandler>[])new Class[] { TimeHandler.class, GeolocationHandler.class, HemisphereHandler.class, DistanceHandler.class, RemoteAddressHandler.class });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 135 */     addDynamicHandlers(pipeline);
/*     */     
/* 137 */     addHandlers(pipeline, (Class<? extends ChannelHandler>[])new Class[] { FilterHandler.class, GeocoderHandler.class, MotionHandler.class, EngineHoursHandler.class, CopyAttributesHandler.class, ComputedAttributesHandler.class, WebDataHandler.class, DefaultDataHandler.class });
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
/* 148 */     if (Context.getRedisManager() != null) {
/* 149 */       pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new RedisHandler() });
/*     */     }
/*     */     
/* 152 */     if (this.eventsEnabled) {
/* 153 */       addHandlers(pipeline, (Class<? extends ChannelHandler>[])new Class[] { CommandResultEventHandler.class, OverspeedEventHandler.class, FuelDropEventHandler.class, MotionEventHandler.class, GeofenceEventHandler.class, AlertEventHandler.class, IgnitionEventHandler.class, MaintenanceEventHandler.class, DriverEventHandler.class });
/*     */     }
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
/* 166 */     pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new MainEventHandler() });
/*     */   }
/*     */   
/*     */   private void addDynamicHandlers(ChannelPipeline pipeline) {
/* 170 */     String handlers = Context.getConfig().getString(Keys.EXTRA_HANDLERS);
/* 171 */     if (handlers != null)
/* 172 */       for (String handler : handlers.split(",")) {
/*     */         try {
/* 174 */           pipeline.addLast(new ChannelHandler[] { Class.forName(handler).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]) });
/* 175 */         } catch (ReflectiveOperationException error) {
/* 176 */           LOGGER.warn("Dynamic handler error", error);
/*     */         } 
/*     */       }  
/*     */   }
/*     */   
/*     */   protected abstract void addTransportHandlers(PipelineBuilder paramPipelineBuilder);
/*     */   
/*     */   protected abstract void addProtocolHandlers(PipelineBuilder paramPipelineBuilder);
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\BasePipelineFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */