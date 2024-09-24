/*    */ package com.google.protobuf;
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
/*    */ 
/*    */ final class NewInstanceSchemaLite
/*    */   implements NewInstanceSchema
/*    */ {
/*    */   public Object newInstance(Object defaultInstance) {
/* 36 */     return ((GeneratedMessageLite)defaultInstance)
/* 37 */       .dynamicMethod(GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE);
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\NewInstanceSchemaLite.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */