/*    */ package org.traccar;
/*    */ 
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.channel.ChannelInboundHandlerAdapter;
/*    */ import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.traccar.model.Position;
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
/*    */ public abstract class BaseDataHandler
/*    */   extends ChannelInboundHandlerAdapter
/*    */ {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseDataHandler.class);
/*    */   public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
/* 26 */     if (msg instanceof Position) {
/* 27 */       Position position = handlePosition((Position)msg);
/* 28 */       if (position != null) {
/* 29 */         ctx.fireChannelRead(position);
/*    */       }
/*    */     } else {
    LOGGER.info("BaseDataHandler channelRead(): "+ msg);
/* 32 */       super.channelRead(ctx, msg);
/*    */     } 
/*    */   }
/*    */   
/*    */   protected abstract Position handlePosition(Position paramPosition);
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\BaseDataHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */