/*    */ package org.traccar.protocol;
/*    */ 
/*    */ import io.netty.channel.ChannelHandler;
/*    */ import io.netty.handler.codec.string.StringEncoder;
/*    */ import org.traccar.BaseProtocol;
/*    */ import org.traccar.Context;
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
/*    */ 
/*    */ public class H02Protocol
/*    */   extends BaseProtocol
/*    */ {
/*    */   public H02Protocol() {
/* 28 */     setSupportedDataCommands(new String[] { "alarmArm", "alarmDisarm", "engineStop", "engineResume", "positionPeriodic" });
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 35 */     addServer(new TrackerServer(false, getName())
/*    */         {
/*    */           protected void addProtocolHandlers(PipelineBuilder pipeline) {
/* 38 */             int messageLength = Context.getConfig().getInteger(H02Protocol.this.getName() + ".messageLength");
/* 39 */             pipeline.addLast((ChannelHandler)new H02FrameDecoder(messageLength));
/* 40 */             pipeline.addLast((ChannelHandler)new StringEncoder());
/* 41 */             pipeline.addLast((ChannelHandler)new H02ProtocolEncoder());
/* 42 */             pipeline.addLast((ChannelHandler)new H02ProtocolDecoder((Protocol)H02Protocol.this));
/*    */           }
/*    */         });
/* 45 */     addServer(new TrackerServer(true, getName())
/*    */         {
/*    */           protected void addProtocolHandlers(PipelineBuilder pipeline) {
/* 48 */             pipeline.addLast((ChannelHandler)new StringEncoder());
/* 49 */             pipeline.addLast((ChannelHandler)new H02ProtocolEncoder());
/* 50 */             pipeline.addLast((ChannelHandler)new H02ProtocolDecoder((Protocol)H02Protocol.this));
/*    */           }
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\protocol\H02Protocol.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */