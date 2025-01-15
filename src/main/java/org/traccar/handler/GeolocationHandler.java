/*    */ package org.traccar.handler;
/*    */ 
/*    */ import io.netty.channel.ChannelHandler.Sharable;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.channel.ChannelInboundHandlerAdapter;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.traccar.config.Config;
/*    */ import org.traccar.config.Keys;
/*    */ import org.traccar.database.StatisticsManager;
/*    */ import org.traccar.geolocation.GeolocationProvider;
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
/*    */ public class GeolocationHandler
/*    */   extends ChannelInboundHandlerAdapter
/*    */ {
/* 32 */   private static final Logger LOGGER = LoggerFactory.getLogger(GeolocationHandler.class);
/*    */   
/*    */   private final GeolocationProvider geolocationProvider;
/*    */   
/*    */   private final StatisticsManager statisticsManager;
/*    */   private final boolean processInvalidPositions;
/*    */   
/*    */   public GeolocationHandler(Config config, GeolocationProvider geolocationProvider, StatisticsManager statisticsManager) {
/* 40 */     this.geolocationProvider = geolocationProvider;
/* 41 */     this.statisticsManager = statisticsManager;
/* 42 */     this.processInvalidPositions = config.getBoolean(Keys.GEOLOCATION_PROCESS_INVALID_POSITIONS);
/*    */   }
/*    */ 
/*    */   
/*    */   public void channelRead(final ChannelHandlerContext ctx, Object message) {
/* 47 */     if (message instanceof Position) {
/* 48 */       final Position position = (Position)message;
/* 49 */       if ((position.getOutdated() || (this.processInvalidPositions && !position.getValid())) && position
/* 50 */         .getNetwork() != null) {
/* 51 */         if (this.statisticsManager != null) {
/* 52 */           this.statisticsManager.registerGeolocationRequest();
/*    */         }
/*    */         
/* 55 */         this.geolocationProvider.getLocation(position.getNetwork(), new GeolocationProvider.LocationProviderCallback()
/*    */             {
/*    */               public void onSuccess(double latitude, double longitude, double accuracy)
/*    */               {
/* 59 */                 position.set("approximate", Boolean.valueOf(true));
/* 60 */                 position.setValid(true);
/* 61 */                 position.setFixTime(position.getDeviceTime());
/* 62 */                 position.setLatitude(latitude);
/* 63 */                 position.setLongitude(longitude);
/* 64 */                 position.setAccuracy(accuracy);
/* 65 */                 position.setAltitude(0.0D);
/* 66 */                 position.setSpeed(0.0D);
/* 67 */                 position.setCourse(0.0D);
/* 68 */                 position.set("rssi", Integer.valueOf(0));
/* 69 */                 ctx.fireChannelRead(position);
/*    */               }
/*    */ 
/*    */               
/*    */               public void onFailure(Throwable e) {
/* 74 */                 GeolocationHandler.LOGGER.warn("Geolocation network error", e);
/* 75 */                 ctx.fireChannelRead(position);
/*    */               }
/*    */             });
/*    */       } else {
/* 79 */         ctx.fireChannelRead(position);
/*    */       } 
/*    */     } else {
/* 82 */       ctx.fireChannelRead(message);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\handler\GeolocationHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */