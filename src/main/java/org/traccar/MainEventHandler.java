/*     */ package org.traccar;
/*     */ 
/*     */ import io.netty.channel.Channel;
/*     */ import io.netty.channel.ChannelHandlerContext;
/*     */ import io.netty.channel.ChannelInboundHandlerAdapter;
/*     */ import io.netty.handler.codec.http.HttpRequestDecoder;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.sql.SQLException;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Set;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.traccar.database.StatisticsManager;
/*     */ import org.traccar.helper.DateUtil;
/*     */ import org.traccar.model.Position;
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
/*     */ public class MainEventHandler
/*     */   extends ChannelInboundHandlerAdapter
/*     */ {
/*  39 */   private static final Logger LOGGER = LoggerFactory.getLogger(MainEventHandler.class);
/*     */   
/*     */   private static final String DEFAULT_LOGGER_ATTRIBUTES = "time,position,speed,course,accuracy,result";
/*     */   
/*  43 */   private final Set<String> connectionlessProtocols = new HashSet<>();
/*  44 */   private final Set<String> logAttributes = new LinkedHashSet<>();
/*     */   
/*     */   public MainEventHandler() {
/*  47 */     String connectionlessProtocolList = Context.getConfig().getString("status.ignoreOffline");
/*  48 */     if (connectionlessProtocolList != null) {
/*  49 */       this.connectionlessProtocols.addAll(Arrays.asList(connectionlessProtocolList.split("[, ]")));
/*     */     }
/*  51 */     this.logAttributes.addAll(Arrays.asList(
/*  52 */           Context.getConfig().getString("logger.attributes", "time,position,speed,course,accuracy,result").split("[, ]")));
/*     */   }
/*     */ 
/*     */   
/*     */   public void channelRead(ChannelHandlerContext ctx, Object msg) {
/*  57 */     if (msg instanceof Position) {
/*     */       
/*  59 */       Position position = (Position)msg;
/*     */       try {
/*  61 */         Context.getDeviceManager().updateLatestPosition(position);
/*  62 */       } catch (SQLException error) {
/*  63 */         LOGGER.warn("[{}] Failed to update latest position", ctx.channel().id().asShortText(), error);
/*     */       } 
/*     */       
/*  66 */       String uniqueId = Context.getIdentityManager().getById(position.getDeviceId()).getUniqueId();
/*     */       
/*  68 */       StringBuilder builder = new StringBuilder();
/*  69 */       builder.append(formatDetails(ctx.channel())).append(" ");
/*  70 */       builder.append("id: ").append(uniqueId);
/*  71 */       for (String attribute : this.logAttributes) {
/*  72 */         switch (attribute) {
/*     */           case "time":
/*  74 */             builder.append(", time: ").append(DateUtil.formatDate(position.getFixTime(), false));
/*     */             continue;
/*     */           case "position":
/*  77 */             builder.append(", lat: ").append(String.format("%.5f", new Object[] { Double.valueOf(position.getLatitude()) }));
/*  78 */             builder.append(", lon: ").append(String.format("%.5f", new Object[] { Double.valueOf(position.getLongitude()) }));
/*     */             continue;
/*     */           case "speed":
/*  81 */             if (position.getSpeed() > 0.0D) {
/*  82 */               builder.append(", speed: ").append(String.format("%.1f", new Object[] { Double.valueOf(position.getSpeed()) }));
/*     */             }
/*     */             continue;
/*     */           case "course":
/*  86 */             builder.append(", course: ").append(String.format("%.1f", new Object[] { Double.valueOf(position.getCourse()) }));
/*     */             continue;
/*     */           case "accuracy":
/*  89 */             if (position.getAccuracy() > 0.0D) {
/*  90 */               builder.append(", accuracy: ").append(String.format("%.1f", new Object[] { Double.valueOf(position.getAccuracy()) }));
/*     */             }
/*     */             continue;
/*     */           case "outdated":
/*  94 */             if (position.getOutdated()) {
/*  95 */               builder.append(", outdated");
/*     */             }
/*     */             continue;
/*     */           case "invalid":
/*  99 */             if (!position.getValid()) {
/* 100 */               builder.append(", invalid");
/*     */             }
/*     */             continue;
/*     */         } 
/* 104 */         Object value = position.getAttributes().get(attribute);
/* 105 */         if (value != null) {
/* 106 */           builder.append(", ").append(attribute).append(": ").append(value);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 111 */       LOGGER.info(builder.toString());
/*     */       
/* 113 */       ((StatisticsManager)Main.getInjector().getInstance(StatisticsManager.class)).registerMessageStored(position.getDeviceId());
/*     */     } 
/*     */   }
/*     */   
/*     */   private static String formatDetails(Channel channel) {
/* 118 */     String remote = "-";
/*     */     
/* 120 */     if (channel.remoteAddress() instanceof InetSocketAddress) {
/* 121 */       InetSocketAddress socketAddress = (InetSocketAddress)channel.remoteAddress();
/* 122 */       remote = String.format("%s:%d", new Object[] { socketAddress.getHostString(), Integer.valueOf(socketAddress.getPort()) });
/*     */     } 
/*     */     
/* 125 */     return String.format("[%s: %d] %s -", new Object[] { channel.id().asShortText(), Integer.valueOf(((InetSocketAddress)channel.localAddress()).getPort()), remote });
/*     */   }
/*     */ 
/*     */   
/*     */   public void channelActive(ChannelHandlerContext ctx) {
/* 130 */     if (!(ctx.channel() instanceof io.netty.channel.socket.DatagramChannel)) {
/* 131 */       LOGGER.info(formatDetails(ctx.channel()) + " connected");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void channelInactive(ChannelHandlerContext ctx) {
/* 137 */     LOGGER.info(formatDetails(ctx.channel()) + " disconnected");
/* 138 */     closeChannel(ctx.channel());
/*     */     
/* 140 */     if (BasePipelineFactory.getHandler(ctx.pipeline(), HttpRequestDecoder.class) == null && 
/* 141 */       !this.connectionlessProtocols.contains(((BaseProtocolDecoder)ctx.pipeline().get(BaseProtocolDecoder.class)).getProtocolName())) {
/* 142 */       Context.getConnectionManager().removeActiveDevice(ctx.channel());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
/* 148 */     while (cause.getCause() != null && cause.getCause() != cause) {
/* 149 */       cause = cause.getCause();
/*     */     }
/* 151 */     LOGGER.warn(formatDetails(ctx.channel()) + " error", cause);
/* 152 */     closeChannel(ctx.channel());
/*     */   }
/*     */ 
/*     */   
/*     */   public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
/* 157 */     if (evt instanceof io.netty.handler.timeout.IdleStateEvent) {
/* 158 */       LOGGER.info(formatDetails(ctx.channel()) + " timed out");
/* 159 */       closeChannel(ctx.channel());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void closeChannel(Channel channel) {
/* 164 */     if (!(channel instanceof io.netty.channel.socket.DatagramChannel))
/* 165 */       channel.close(); 
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\MainEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */