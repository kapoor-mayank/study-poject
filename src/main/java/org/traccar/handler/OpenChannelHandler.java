/*    */ package org.traccar.handler;
/*    */ 
/*    */ import io.netty.channel.ChannelDuplexHandler;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import org.traccar.TrackerConnector;
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
/*    */ public class OpenChannelHandler
/*    */   extends ChannelDuplexHandler
/*    */ {
/*    */   private final TrackerConnector connector;
/*    */   
/*    */   public OpenChannelHandler(TrackerConnector connector) {
/* 27 */     this.connector = connector;
/*    */   }
/*    */ 
/*    */   
/*    */   public void channelActive(ChannelHandlerContext ctx) throws Exception {
/* 32 */     super.channelActive(ctx);
/* 33 */     this.connector.getChannelGroup().add(ctx.channel());
/*    */   }
/*    */ 
/*    */   
/*    */   public void channelInactive(ChannelHandlerContext ctx) throws Exception {
/* 38 */     super.channelInactive(ctx);
/* 39 */     this.connector.getChannelGroup().remove(ctx.channel());
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\handler\OpenChannelHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */