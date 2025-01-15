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
/*    */ public final class StringUtil
/*    */ {
/*    */   public static boolean containsHex(String value) {
/* 24 */     for (char c : value.toCharArray()) {
/* 25 */       if ((c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F')) {
/* 26 */         return true;
/*    */       }
/*    */     } 
/* 29 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\helper\StringUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */