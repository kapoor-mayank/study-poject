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
/*    */ final class ExtensionRegistryFactory
/*    */ {
/*    */   static final String FULL_REGISTRY_CLASS_NAME = "com.google.protobuf.ExtensionRegistry";
/* 47 */   static final Class<?> EXTENSION_REGISTRY_CLASS = reflectExtensionRegistry();
/*    */   
/*    */   static Class<?> reflectExtensionRegistry() {
/*    */     try {
/* 51 */       return Class.forName("com.google.protobuf.ExtensionRegistry");
/* 52 */     } catch (ClassNotFoundException e) {
/*    */ 
/*    */       
/* 55 */       return null;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static ExtensionRegistryLite create() {
/* 61 */     ExtensionRegistryLite result = invokeSubclassFactory("newInstance");
/*    */     
/* 63 */     return (result != null) ? result : new ExtensionRegistryLite();
/*    */   }
/*    */ 
/*    */   
/*    */   public static ExtensionRegistryLite createEmpty() {
/* 68 */     ExtensionRegistryLite result = invokeSubclassFactory("getEmptyRegistry");
/*    */     
/* 70 */     return (result != null) ? result : ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
/*    */   }
/*    */ 
/*    */   
/*    */   static boolean isFullRegistry(ExtensionRegistryLite registry) {
/* 75 */     return (EXTENSION_REGISTRY_CLASS != null && EXTENSION_REGISTRY_CLASS
/* 76 */       .isAssignableFrom(registry.getClass()));
/*    */   }
/*    */   
/*    */   private static final ExtensionRegistryLite invokeSubclassFactory(String methodName) {
/* 80 */     if (EXTENSION_REGISTRY_CLASS == null) {
/* 81 */       return null;
/*    */     }
/*    */     
/*    */     try {
/* 85 */       return (ExtensionRegistryLite)EXTENSION_REGISTRY_CLASS
/* 86 */         .getDeclaredMethod(methodName, new Class[0]).invoke(null, new Object[0]);
/* 87 */     } catch (Exception e) {
/* 88 */       return null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\ExtensionRegistryFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */