/*    */ package org.traccar.helper;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.buffer.ByteBufUtil;
/*    */ import io.netty.buffer.Unpooled;
/*    */ import java.nio.charset.StandardCharsets;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class BufferUtil
/*    */ {
/*    */   public static int readSignedMagnitudeInt(ByteBuf buffer) {
/* 31 */     long value = buffer.readUnsignedInt();
/* 32 */     int result = (int)BitUtil.to(value, 31);
/* 33 */     return BitUtil.check(value, 31) ? -result : result;
/*    */   }
/*    */   
/*    */   public static int indexOf(ByteBuf buffer, int fromIndex, int toIndex, byte value, int count) {
/* 37 */     int startIndex = fromIndex;
/* 38 */     for (int i = 0; i < count; i++) {
/* 39 */       int result = buffer.indexOf(startIndex, toIndex, value);
/* 40 */       if (result < 0 || i == count - 1) {
/* 41 */         return result;
/*    */       }
/* 43 */       startIndex = result + 1;
/*    */     } 
/* 45 */     return -1;
/*    */   }
/*    */   
/*    */   public static int indexOf(String needle, ByteBuf haystack) {
/* 49 */     return indexOf(needle, haystack, haystack.readerIndex(), haystack.writerIndex());
/*    */   }
/*    */   
/*    */   public static int indexOf(String needle, ByteBuf haystack, int startIndex, int endIndex) {
/* 53 */     ByteBuf wrappedNeedle = Unpooled.wrappedBuffer(needle.getBytes(StandardCharsets.US_ASCII));
/*    */     try {
/* 55 */       return indexOf(wrappedNeedle, haystack, startIndex, endIndex);
/*    */     } finally {
/* 57 */       wrappedNeedle.release();
/*    */     } 
/*    */   }
/*    */   
/*    */   public static int indexOf(ByteBuf needle, ByteBuf haystack, int startIndex, int endIndex) {
/*    */     ByteBuf wrappedHaystack;
/* 63 */     if (startIndex == haystack.readerIndex() && endIndex == haystack.writerIndex()) {
/* 64 */       wrappedHaystack = haystack;
/*    */     } else {
/* 66 */       wrappedHaystack = Unpooled.wrappedBuffer(haystack);
/* 67 */       wrappedHaystack.readerIndex(startIndex - haystack.readerIndex());
/* 68 */       wrappedHaystack.writerIndex(endIndex - haystack.readerIndex());
/*    */     } 
/* 70 */     int result = ByteBufUtil.indexOf(needle, wrappedHaystack);
/* 71 */     return (result < 0) ? result : (startIndex + result);
/*    */   }
/*    */   
/*    */   public static String readString(ByteBuf buf, int length) {
/* 75 */     return buf.readCharSequence(length, StandardCharsets.US_ASCII).toString();
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\helper\BufferUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */