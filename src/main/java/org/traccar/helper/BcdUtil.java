/*    */ package org.traccar.helper;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
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
/*    */ public final class BcdUtil
/*    */ {
/*    */   public static int readInteger(ByteBuf buf, int digits) {
/* 26 */     int result = 0;
/*    */     
/* 28 */     for (int i = 0; i < digits / 2; i++) {
/* 29 */       int b = buf.readUnsignedByte();
/* 30 */       result *= 10;
/* 31 */       result += b >>> 4;
/* 32 */       result *= 10;
/* 33 */       result += b & 0xF;
/*    */     } 
/*    */     
/* 36 */     if (digits % 2 != 0) {
/* 37 */       int b = buf.getUnsignedByte(buf.readerIndex());
/* 38 */       result *= 10;
/* 39 */       result += b >>> 4;
/*    */     } 
/*    */     
/* 42 */     return result;
/*    */   }
/*    */   
/*    */   public static double readCoordinate(ByteBuf buf) {
/* 46 */     int b1 = buf.readUnsignedByte();
/* 47 */     int b2 = buf.readUnsignedByte();
/* 48 */     int b3 = buf.readUnsignedByte();
/* 49 */     int b4 = buf.readUnsignedByte();
/*    */     
/* 51 */     double value = ((b2 & 0xF) * 10 + (b3 >> 4));
/* 52 */     value += (((b3 & 0xF) * 10 + (b4 >> 4)) * 10 + (b4 & 0xF)) / 1000.0D;
/* 53 */     value /= 60.0D;
/* 54 */     value += (((b1 >> 4 & 0x7) * 10 + (b1 & 0xF)) * 10 + (b2 >> 4));
/*    */     
/* 56 */     if ((b1 & 0x80) != 0) {
/* 57 */       value = -value;
/*    */     }
/*    */     
/* 60 */     return value;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\helper\BcdUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */