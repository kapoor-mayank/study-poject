/*    */ package org.traccar.handler;
/*    */ 
/*    */ import io.netty.channel.ChannelHandler.Sharable;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.channel.ChannelInboundHandlerAdapter;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.traccar.Context;
/*    */ import org.traccar.config.Config;
/*    */ import org.traccar.config.Keys;
/*    */ import org.traccar.database.IdentityManager;
/*    */ import org.traccar.database.StatisticsManager;
/*    */ import org.traccar.geocoder.Geocoder;
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
/*    */ @Sharable
/*    */ public class GeocoderHandler
/*    */   extends ChannelInboundHandlerAdapter
/*    */ {
/* 34 */   private static final Logger LOGGER = LoggerFactory.getLogger(GeocoderHandler.class);
/*    */   
/*    */   private final Geocoder geocoder;
/*    */   
/*    */   private final IdentityManager identityManager;
/*    */   private final StatisticsManager statisticsManager;
/*    */   private final boolean ignorePositions;
/*    */   private final boolean processInvalidPositions;
/*    */   private final int geocoderReuseDistance;
/*    */   
/*    */   public GeocoderHandler(Config config, Geocoder geocoder, IdentityManager identityManager, StatisticsManager statisticsManager) {
/* 45 */     this.geocoder = geocoder;
/* 46 */     this.identityManager = identityManager;
/* 47 */     this.statisticsManager = statisticsManager;
/* 48 */     this.ignorePositions = Context.getConfig().getBoolean(Keys.GEOCODER_IGNORE_POSITIONS);
/* 49 */     this.processInvalidPositions = config.getBoolean(Keys.GEOCODER_PROCESS_INVALID_POSITIONS);
/* 50 */     this.geocoderReuseDistance = config.getInteger(Keys.GEOCODER_REUSE_DISTANCE, 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void channelRead(final ChannelHandlerContext ctx, Object message) {
/* 55 */     if (message instanceof Position && !this.ignorePositions) {
/* 56 */       final Position position = (Position)message;
/* 57 */       if (this.processInvalidPositions || position.getValid()) {
/* 58 */         if (this.geocoderReuseDistance != 0) {
/* 59 */           Position lastPosition = this.identityManager.getLastPosition(position.getDeviceId());
/* 60 */           if (lastPosition != null && lastPosition.getAddress() != null && position
/* 61 */             .getDouble("distance") <= this.geocoderReuseDistance) {
/* 62 */             position.setAddress(lastPosition.getAddress());
/* 63 */             ctx.fireChannelRead(position);
/*    */             
/*    */             return;
/*    */           } 
/*    */         } 
/* 68 */         if (this.statisticsManager != null) {
/* 69 */           this.statisticsManager.registerGeocoderRequest();
/*    */         }
/*    */         
/* 72 */         this.geocoder.getAddress(position.getLatitude(), position.getLongitude(), new Geocoder.ReverseGeocoderCallback()
/*    */             {
/*    */               public void onSuccess(String address)
/*    */               {
/* 76 */                 position.setAddress(address);
/* 77 */                 ctx.fireChannelRead(position);
/*    */               }
/*    */ 
/*    */               
/*    */               public void onFailure(Throwable e) {
/* 82 */                 GeocoderHandler.LOGGER.warn("Geocoding failed", e);
/* 83 */                 ctx.fireChannelRead(position);
/*    */               }
/*    */             });
/*    */       } else {
/* 87 */         ctx.fireChannelRead(position);
/*    */       } 
/*    */     } else {
/* 90 */       ctx.fireChannelRead(message);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\handler\GeocoderHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */