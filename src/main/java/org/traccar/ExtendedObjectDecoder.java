/*    */ package org.traccar;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.buffer.ByteBufUtil;
/*    */ import io.netty.channel.Channel;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.channel.ChannelInboundHandlerAdapter;
/*    */ import io.netty.util.ReferenceCountUtil;
/*    */ import java.net.SocketAddress;
/*    */ import java.nio.charset.StandardCharsets;
/*    */ import org.traccar.helper.DataConverter;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ExtendedObjectDecoder
/*    */   extends ChannelInboundHandlerAdapter
/*    */ {
/*    */   private long sequence;
/*    */   
/*    */   private void saveOriginal(Object decodedMessage, Object originalMessage) {
/* 37 */     Position position = (Position)decodedMessage;
/* 38 */     position.set("sequence", Long.valueOf(++this.sequence));
/* 39 */     if (decodedMessage instanceof Position && Context.getConfig().getBoolean("database.saveOriginal")) if (originalMessage instanceof ByteBuf) {
/* 40 */         ByteBuf buf = (ByteBuf)originalMessage;
/* 41 */         position.set("raw", ByteBufUtil.hexDump(buf));
/* 42 */       } else if (originalMessage instanceof String) {
/* 43 */         position.set("raw", DataConverter.printHex(((String)originalMessage)
/* 44 */               .getBytes(StandardCharsets.US_ASCII)));
/*    */       } 
/*    */   
/*    */   }
/*    */ 
/*    */   
/*    */   public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
/* 51 */     NetworkMessage networkMessage = (NetworkMessage)msg;
/* 52 */     Object originalMessage = networkMessage.getMessage();
/*    */     try {
/* 54 */       Object decodedMessage = decode(ctx.channel(), networkMessage.getRemoteAddress(), originalMessage);
/* 55 */       onMessageEvent(ctx.channel(), networkMessage.getRemoteAddress(), originalMessage, decodedMessage);
/* 56 */       if (decodedMessage == null) {
/* 57 */         decodedMessage = handleEmptyMessage(ctx.channel(), networkMessage.getRemoteAddress(), originalMessage);
/*    */       }
/* 59 */       if (decodedMessage != null) {
/* 60 */         if (decodedMessage instanceof java.util.Collection) {
/* 61 */           for (Object o : decodedMessage) {
/* 62 */             saveOriginal(o, originalMessage);
/* 63 */             ctx.fireChannelRead(o);
/*    */           } 
/*    */         } else {
/* 66 */           saveOriginal(decodedMessage, originalMessage);
/* 67 */           ctx.fireChannelRead(decodedMessage);
/*    */         } 
/*    */       }
/*    */     } finally {
/* 71 */       ReferenceCountUtil.release(originalMessage);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected void onMessageEvent(Channel channel, SocketAddress remoteAddress, Object originalMessage, Object decodedMessage) {}
/*    */ 
/*    */   
/*    */   protected Object handleEmptyMessage(Channel channel, SocketAddress remoteAddress, Object msg) {
/* 80 */     return null;
/*    */   }
/*    */   
/*    */   protected abstract Object decode(Channel paramChannel, SocketAddress paramSocketAddress, Object paramObject) throws Exception;
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\ExtendedObjectDecoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */