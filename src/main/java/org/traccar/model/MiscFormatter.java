/*    */ package org.traccar.model;
/*    */ 
/*    */ import java.text.DecimalFormat;
/*    */ import java.util.Map;
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
/*    */ public final class MiscFormatter
/*    */ {
/*    */   private static final String XML_ROOT_NODE = "info";
/* 29 */   private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");
/*    */   
/*    */   private static String format(Object value) {
/* 32 */     if (value instanceof Double || value instanceof Float) {
/* 33 */       return DECIMAL_FORMAT.format(value);
/*    */     }
/* 35 */     return value.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public static String toXmlString(Map<String, Object> attributes) {
/* 40 */     StringBuilder result = new StringBuilder();
/*    */     
/* 42 */     result.append("<").append("info").append(">");
/*    */     
/* 44 */     for (Map.Entry<String, Object> entry : attributes.entrySet()) {
/*    */       
/* 46 */       result.append("<").append(entry.getKey()).append(">");
/* 47 */       result.append(format(entry.getValue()));
/* 48 */       result.append("</").append(entry.getKey()).append(">");
/*    */     } 
/*    */     
/* 51 */     result.append("</").append("info").append(">");
/*    */     
/* 53 */     return result.toString();
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\model\MiscFormatter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */