///*    */ package org.traccar.protocol;
///*    */
///*    */ import io.netty.channel.ChannelHandler;
///*    */ import org.traccar.BaseProtocol;
///*    */ import org.traccar.PipelineBuilder;
///*    */ import org.traccar.Protocol;
///*    */ import org.traccar.TrackerServer;
///*    */
///*    */
///*    */
///*    */
///*    */
///*    */
///*    */
///*    */
///*    */
///*    */
///*    */
///*    */
///*    */
///*    */
///*    */ public class BceProtocol
///*    */   extends BaseProtocol
///*    */ {
///*    */   public BceProtocol() {
///* 26 */     setSupportedDataCommands(new String[] { "outputControl" });
///*    */
///* 28 */     addServer(new TrackerServer(false, getName())
///*    */         {
///*    */           protected void addProtocolHandlers(PipelineBuilder pipeline) {
///* 31 */             pipeline.addLast((ChannelHandler)new BceFrameDecoder());
///* 32 */             pipeline.addLast((ChannelHandler)new BceProtocolEncoder());
///* 33 */             pipeline.addLast((ChannelHandler)new BceProtocolDecoder((Protocol)BceProtocol.this));
///*    */           }
///*    */         });
///*    */   }
///*    */ }
//
//
///* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\protocol\BceProtocol.class
// * Java compiler version: 8 (52.0)
// * JD-Core Version:       1.1.3
// */