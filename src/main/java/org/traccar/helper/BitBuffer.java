/*    */ package org.traccar.helper;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.buffer.Unpooled;
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
/*    */ public class BitBuffer
/*    */ {
/*    */   private final ByteBuf buffer;
/*    */   private int writeByte;
/*    */   private int writeCount;
/*    */   private int readByte;
/*    */   private int readCount;
/*    */   
/*    */   public BitBuffer() {
/* 32 */     this.buffer = Unpooled.buffer();
/*    */   }
/*    */   
/*    */   public BitBuffer(ByteBuf buffer) {
/* 36 */     this.buffer = buffer;
/*    */   }
/*    */   
/*    */   public void writeEncoded(byte[] bytes) {
/* 40 */     for (byte b : bytes) {
/* 41 */       b = (byte)(b - 48);
/* 42 */       if (b > 40) {
/* 43 */         b = (byte)(b - 8);
/*    */       }
/* 45 */       write(b);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void write(int b) {
/* 50 */     if (this.writeCount == 0) {
/* 51 */       this.writeByte |= b;
/* 52 */       this.writeCount = 6;
/*    */     } else {
/* 54 */       int remaining = 8 - this.writeCount;
/* 55 */       this.writeByte <<= remaining;
/* 56 */       this.writeByte |= b >> 6 - remaining;
/* 57 */       this.buffer.writeByte(this.writeByte);
/* 58 */       this.writeByte = b & (1 << 6 - remaining) - 1;
/* 59 */       this.writeCount = 6 - remaining;
/*    */     } 
/*    */   }
/*    */   
/*    */   public int readUnsigned(int length) {
/* 64 */     int result = 0;
/*    */     
/* 66 */     while (length > 0) {
/* 67 */       if (this.readCount == 0) {
/* 68 */         this.readByte = this.buffer.readUnsignedByte();
/* 69 */         this.readCount = 8;
/*    */       } 
/* 71 */       if (this.readCount >= length) {
/* 72 */         result <<= length;
/* 73 */         result |= this.readByte >> this.readCount - length;
/* 74 */         this.readByte &= (1 << this.readCount - length) - 1;
/* 75 */         this.readCount -= length;
/* 76 */         length = 0; continue;
/*    */       } 
/* 78 */       result <<= this.readCount;
/* 79 */       result |= this.readByte;
/* 80 */       length -= this.readCount;
/* 81 */       this.readByte = 0;
/* 82 */       this.readCount = 0;
/*    */     } 
/*    */ 
/*    */     
/* 86 */     return result;
/*    */   }
/*    */   
/*    */   public int readSigned(int length) {
/* 90 */     int result = readUnsigned(length);
/* 91 */     int signBit = 1 << length - 1;
/* 92 */     if ((result & signBit) == 0) {
/* 93 */       return result;
/*    */     }
/* 95 */     result &= signBit - 1;
/* 96 */     result += signBit - 1 ^ 0xFFFFFFFF;
/* 97 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\helper\BitBuffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */