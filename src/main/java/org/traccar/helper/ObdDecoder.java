/*     */ package org.traccar.helper;
/*     */ 
/*     */ import java.util.AbstractMap;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ 
/*     */ public final class ObdDecoder
/*     */ {
/*     */   private static final int MODE_CURRENT = 1;
/*     */   private static final int MODE_FREEZE_FRAME = 2;
/*     */   private static final int MODE_CODES = 3;
/*     */   
/*     */   public static Map.Entry<String, Object> decode(int mode, String value) {
/*  33 */     switch (mode) {
/*     */       case 1:
/*     */       case 2:
/*  36 */         return decodeData(
/*  37 */             Integer.parseInt(value.substring(0, 2), 16), 
/*  38 */             Integer.parseInt(value.substring(2), 16), true);
/*     */       case 3:
/*  40 */         return decodeCodes(value);
/*     */     } 
/*  42 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private static Map.Entry<String, Object> createEntry(String key, Object value) {
/*  47 */     return new AbstractMap.SimpleEntry<>(key, value);
/*     */   }
/*     */   
/*     */   public static Map.Entry<String, Object> decodeCodes(String value) {
/*  51 */     StringBuilder codes = new StringBuilder();
/*  52 */     for (int i = 0; i < value.length() / 4; i++) {
/*  53 */       int numValue = Integer.parseInt(value.substring(i * 4, (i + 1) * 4), 16);
/*  54 */       codes.append(' ');
/*  55 */       switch (numValue >> 14) {
/*     */         case 1:
/*  57 */           codes.append('C');
/*     */           break;
/*     */         case 2:
/*  60 */           codes.append('B');
/*     */           break;
/*     */         case 3:
/*  63 */           codes.append('U');
/*     */           break;
/*     */         default:
/*  66 */           codes.append('P');
/*     */           break;
/*     */       } 
/*  69 */       codes.append(String.format("%04X", new Object[] { Integer.valueOf(numValue & 0x3FFF) }));
/*     */     } 
/*  71 */     if (codes.length() > 0) {
/*  72 */       return createEntry("dtcs", codes.toString().trim());
/*     */     }
/*  74 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Map.Entry<String, Object> decodeData(int pid, int value, boolean convert) {
/*  79 */     switch (pid) {
/*     */       case 4:
/*  81 */         return createEntry("engineLoad", Integer.valueOf(convert ? (value * 100 / 255) : value));
/*     */       case 5:
/*  83 */         return createEntry("coolantTemp", Integer.valueOf(convert ? (value - 40) : value));
/*     */       case 11:
/*  85 */         return createEntry("mapIntake", Integer.valueOf(value));
/*     */       case 12:
/*  87 */         return createEntry("rpm", Integer.valueOf(convert ? (value / 4) : value));
/*     */       case 13:
/*  89 */         return createEntry("obdSpeed", Integer.valueOf(value));
/*     */       case 15:
/*  91 */         return createEntry("intakeTemp", Integer.valueOf(convert ? (value - 40) : value));
/*     */       case 17:
/*  93 */         return createEntry("throttle", Integer.valueOf(convert ? (value * 100 / 255) : value));
/*     */       case 33:
/*  95 */         return createEntry("milDistance", Integer.valueOf(value));
/*     */       case 47:
/*  97 */         return createEntry("fuel", Integer.valueOf(convert ? (value * 100 / 255) : value));
/*     */       case 49:
/*  99 */         return createEntry("clearedDistance", Integer.valueOf(value));
/*     */     } 
/* 101 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\helper\ObdDecoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */