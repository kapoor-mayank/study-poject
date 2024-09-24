/*    */ package org.traccar.helper;
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
/*    */ public final class BitUtil
/*    */ {
/*    */   public static boolean check(long number, int index) {
/* 24 */     return ((number & 1L << index) != 0L);
/*    */   }
/*    */   
/*    */   public static int between(int number, int from, int to) {
/* 28 */     return number >> from & (1 << to - from) - 1;
/*    */   }
/*    */   
/*    */   public static int from(int number, int from) {
/* 32 */     return number >> from;
/*    */   }
/*    */   
/*    */   public static int to(int number, int to) {
/* 36 */     return between(number, 0, to);
/*    */   }
/*    */   
/*    */   public static long between(long number, int from, int to) {
/* 40 */     return number >> from & (1L << to - from) - 1L;
/*    */   }
/*    */   
/*    */   public static long from(long number, int from) {
/* 44 */     return number >> from;
/*    */   }
/*    */   
/*    */   public static long to(long number, int to) {
/* 48 */     return between(number, 0, to);
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\helper\BitUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */