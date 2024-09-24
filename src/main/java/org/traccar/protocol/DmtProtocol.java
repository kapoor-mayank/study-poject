/*    */ package org.traccar.protocol;
/*    */ 
/*    */ import io.netty.channel.ChannelHandler;
/*    */ import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
/*    */ import java.nio.ByteOrder;
/*    */ import org.traccar.BaseProtocol;
/*    */ import org.traccar.PipelineBuilder;
/*    */ import org.traccar.Protocol;
/*    */ import org.traccar.TrackerServer;
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
/*    */ public class DmtProtocol
/*    */   extends BaseProtocol
/*    */ {
/*    */   public DmtProtocol() {
/* 27 */     addServer(new TrackerServer(false, getName())
/*    */         {
/*    */           protected void addProtocolHandlers(PipelineBuilder pipeline) {
/* 30 */             pipeline.addLast((ChannelHandler)new LengthFieldBasedFrameDecoder(ByteOrder.LITTLE_ENDIAN, 1024, 3, 2, 0, 0, true));
/* 31 */             pipeline.addLast((ChannelHandler)new DmtProtocolDecoder((Protocol)DmtProtocol.this));
/*    */           }
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\protocol\DmtProtocol.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */