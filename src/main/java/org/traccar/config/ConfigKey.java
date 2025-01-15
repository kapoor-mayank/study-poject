/*    */ package org.traccar.config;
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
/*    */ public class ConfigKey
/*    */ {
/*    */   private final String key;
/*    */   private final Class clazz;
/*    */   
/*    */   ConfigKey(String key, Class clazz) {
/* 24 */     this.key = key;
/* 25 */     this.clazz = clazz;
/*    */   }
/*    */   
/*    */   String getKey() {
/* 29 */     return this.key;
/*    */   }
/*    */   
/*    */   Class getValueClass() {
/* 33 */     return this.clazz;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\config\ConfigKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */