/*     */ package org.traccar.helper;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.zip.CRC32;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Checksum
/*     */ {
/*     */   public static class Algorithm
/*     */   {
/*     */     private int poly;
/*     */     private int init;
/*     */     private boolean refIn;
/*     */     private boolean refOut;
/*     */     private int xorOut;
/*     */     private int[] table;
/*     */     
/*     */     public Algorithm(int bits, int poly, int init, boolean refIn, boolean refOut, int xorOut) {
/*  37 */       this.poly = poly;
/*  38 */       this.init = init;
/*  39 */       this.refIn = refIn;
/*  40 */       this.refOut = refOut;
/*  41 */       this.xorOut = xorOut;
/*  42 */       this.table = (bits == 8) ? initTable8() : initTable16();
/*     */     }
/*     */     
/*     */     private int[] initTable8() {
/*  46 */       int[] table = new int[256];
/*     */       
/*  48 */       for (int i = 0; i < 256; i++) {
/*  49 */         int crc = i;
/*  50 */         for (int j = 0; j < 8; j++) {
/*  51 */           boolean bit = ((crc & 0x80) != 0);
/*  52 */           crc <<= 1;
/*  53 */           if (bit) {
/*  54 */             crc ^= this.poly;
/*     */           }
/*     */         } 
/*  57 */         table[i] = crc & 0xFF;
/*     */       } 
/*  59 */       return table;
/*     */     }
/*     */     
/*     */     private int[] initTable16() {
/*  63 */       int[] table = new int[256];
/*     */       
/*  65 */       for (int i = 0; i < 256; i++) {
/*  66 */         int crc = i << 8;
/*  67 */         for (int j = 0; j < 8; j++) {
/*  68 */           boolean bit = ((crc & 0x8000) != 0);
/*  69 */           crc <<= 1;
/*  70 */           if (bit) {
/*  71 */             crc ^= this.poly;
/*     */           }
/*     */         } 
/*  74 */         table[i] = crc & 0xFFFF;
/*     */       } 
/*  76 */       return table;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static int reverse(int value, int bits) {
/*  82 */     int result = 0;
/*  83 */     for (int i = 0; i < bits; i++) {
/*  84 */       result = result << 1 | value & 0x1;
/*  85 */       value >>= 1;
/*     */     } 
/*  87 */     return result;
/*     */   }
/*     */   
/*     */   public static int crc8(Algorithm algorithm, ByteBuffer buf) {
/*  91 */     int crc = algorithm.init;
/*  92 */     while (buf.hasRemaining()) {
/*  93 */       int b = buf.get() & 0xFF;
/*  94 */       if (algorithm.refIn) {
/*  95 */         b = reverse(b, 8);
/*     */       }
/*  97 */       crc = algorithm.table[crc & 0xFF ^ b];
/*     */     } 
/*  99 */     if (algorithm.refOut) {
/* 100 */       crc = reverse(crc, 8);
/*     */     }
/* 102 */     return (crc ^ algorithm.xorOut) & 0xFF;
/*     */   }
/*     */   
/*     */   public static int crc16(Algorithm algorithm, ByteBuffer buf) {
/* 106 */     int crc = algorithm.init;
/* 107 */     while (buf.hasRemaining()) {
/* 108 */       int b = buf.get() & 0xFF;
/* 109 */       if (algorithm.refIn) {
/* 110 */         b = reverse(b, 8);
/*     */       }
/* 112 */       crc = crc << 8 ^ algorithm.table[crc >> 8 & 0xFF ^ b];
/*     */     } 
/* 114 */     if (algorithm.refOut) {
/* 115 */       crc = reverse(crc, 16);
/*     */     }
/* 117 */     return (crc ^ algorithm.xorOut) & 0xFFFF;
/*     */   }
/*     */   
/* 120 */   public static final Algorithm CRC8_EGTS = new Algorithm(8, 49, 255, false, false, 0);
/* 121 */   public static final Algorithm CRC8_ROHC = new Algorithm(8, 7, 255, true, true, 0);
/*     */   
/* 123 */   public static final Algorithm CRC16_IBM = new Algorithm(16, 32773, 0, true, true, 0);
/* 124 */   public static final Algorithm CRC16_X25 = new Algorithm(16, 4129, 65535, true, true, 65535);
/* 125 */   public static final Algorithm CRC16_MODBUS = new Algorithm(16, 32773, 65535, true, true, 0);
/* 126 */   public static final Algorithm CRC16_CCITT_FALSE = new Algorithm(16, 4129, 65535, false, false, 0);
/* 127 */   public static final Algorithm CRC16_KERMIT = new Algorithm(16, 4129, 0, true, true, 0);
/* 128 */   public static final Algorithm CRC16_XMODEM = new Algorithm(16, 4129, 0, false, false, 0);
/*     */   
/*     */   public static int crc32(ByteBuffer buf) {
/* 131 */     CRC32 checksum = new CRC32();
/* 132 */     while (buf.hasRemaining()) {
/* 133 */       checksum.update(buf.get());
/*     */     }
/* 135 */     return (int)checksum.getValue();
/*     */   }
/*     */   
/*     */   public static int xor(ByteBuffer buf) {
/* 139 */     int checksum = 0;
/* 140 */     while (buf.hasRemaining()) {
/* 141 */       checksum ^= buf.get();
/*     */     }
/* 143 */     return checksum;
/*     */   }
/*     */   
/*     */   public static int xor(String string) {
/* 147 */     byte checksum = 0;
/* 148 */     for (byte b : string.getBytes(StandardCharsets.US_ASCII)) {
/* 149 */       checksum = (byte)(checksum ^ b);
/*     */     }
/* 151 */     return checksum;
/*     */   }
/*     */   
/*     */   public static String nmea(String msg) {
/* 155 */     int checksum = 0;
/* 156 */     byte[] bytes = msg.getBytes(StandardCharsets.US_ASCII);
/* 157 */     for (int i = 1; i < bytes.length; i++) {
/* 158 */       checksum ^= bytes[i];
/*     */     }
/* 160 */     return String.format("*%02x", new Object[] { Integer.valueOf(checksum) }).toUpperCase();
/*     */   }
/*     */   
/*     */   public static int sum(ByteBuffer buf) {
/* 164 */     byte checksum = 0;
/* 165 */     while (buf.hasRemaining()) {
/* 166 */       checksum = (byte)(checksum + buf.get());
/*     */     }
/* 168 */     return checksum;
/*     */   }
/*     */   
/*     */   public static int modulo256(ByteBuffer buf) {
/* 172 */     int checksum = 0;
/* 173 */     while (buf.hasRemaining()) {
/* 174 */       checksum = checksum + buf.get() & 0xFF;
/*     */     }
/* 176 */     return checksum;
/*     */   }
/*     */   
/*     */   public static String sum(String msg) {
/* 180 */     byte checksum = 0;
/* 181 */     for (byte b : msg.getBytes(StandardCharsets.US_ASCII)) {
/* 182 */       checksum = (byte)(checksum + b);
/*     */     }
/* 184 */     return String.format("%02X", new Object[] { Byte.valueOf(checksum) }).toUpperCase();
/*     */   }
/*     */   
/*     */   public static long luhn(long imei) {
/* 188 */     long checksum = 0L;
/* 189 */     long remain = imei;
/*     */     
/* 191 */     for (int i = 0; remain != 0L; i++) {
/* 192 */       long digit = remain % 10L;
/*     */       
/* 194 */       if (i % 2 == 0) {
/* 195 */         digit *= 2L;
/* 196 */         if (digit >= 10L) {
/* 197 */           digit = 1L + digit % 10L;
/*     */         }
/*     */       } 
/*     */       
/* 201 */       checksum += digit;
/* 202 */       remain /= 10L;
/*     */     } 
/*     */     
/* 205 */     return (10L - checksum % 10L) % 10L;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\helper\Checksum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */