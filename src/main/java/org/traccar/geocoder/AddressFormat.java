/*    */ package org.traccar.geocoder;
/*    */ 
/*    */ import java.text.FieldPosition;
/*    */ import java.text.Format;
/*    */ import java.text.ParsePosition;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AddressFormat
/*    */   extends Format
/*    */ {
/*    */   private final String format;
/*    */   
/*    */   public AddressFormat() {
/* 41 */     this("%h %r, %t, %s, %c");
/*    */   }
/*    */   
/*    */   public AddressFormat(String format) {
/* 45 */     this.format = format;
/*    */   }
/*    */   
/*    */   private static String replace(String s, String key, String value) {
/* 49 */     if (value != null) {
/* 50 */       s = s.replace(key, value);
/*    */     } else {
/* 52 */       s = s.replaceAll("[, ]*" + key, "");
/*    */     } 
/* 54 */     return s;
/*    */   }
/*    */ 
/*    */   
/*    */   public StringBuffer format(Object o, StringBuffer stringBuffer, FieldPosition fieldPosition) {
/* 59 */     Address address = (Address)o;
/* 60 */     String result = this.format;
/*    */     
/* 62 */     result = replace(result, "%p", address.getPostcode());
/* 63 */     result = replace(result, "%c", address.getCountry());
/* 64 */     result = replace(result, "%s", address.getState());
/* 65 */     result = replace(result, "%d", address.getDistrict());
/* 66 */     result = replace(result, "%t", address.getSettlement());
/* 67 */     result = replace(result, "%u", address.getSuburb());
/* 68 */     result = replace(result, "%r", address.getStreet());
/* 69 */     result = replace(result, "%h", address.getHouse());
/* 70 */     result = replace(result, "%f", address.getFormattedAddress());
/*    */     
/* 72 */     result = result.replaceAll("^[, ]*", "");
/*    */     
/* 74 */     return stringBuffer.append(result);
/*    */   }
/*    */ 
/*    */   
/*    */   public Address parseObject(String s, ParsePosition parsePosition) {
/* 79 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\geocoder\AddressFormat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */