/*    */ package org.traccar.helper;
/*    */ 
/*    */ import org.apache.commons.codec.DecoderException;
/*    */ import org.apache.commons.codec.binary.Base64;
/*    */ import org.apache.commons.codec.binary.Hex;
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
/*    */ public final class DataConverter
/*    */ {
/*    */   public static byte[] parseHex(String string) {
/*    */     try {
/* 29 */       return Hex.decodeHex(string);
/* 30 */     } catch (DecoderException e) {
/* 31 */       throw new RuntimeException(e);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static String printHex(byte[] data) {
/* 36 */     return Hex.encodeHexString(data);
/*    */   }
/*    */   
/*    */   public static byte[] parseBase64(String string) {
/* 40 */     return Base64.decodeBase64(string);
/*    */   }
/*    */   
/*    */   public static String printBase64(byte[] data) {
/* 44 */     return Base64.encodeBase64String(data);
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\helper\DataConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */