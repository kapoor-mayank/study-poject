/*     */ package com.google.protobuf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ConcurrentMap;
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
/*     */ final class Protobuf
/*     */ {
/*  45 */   private static final Protobuf INSTANCE = new Protobuf();
/*     */ 
/*     */   
/*     */   private final SchemaFactory schemaFactory;
/*     */   
/*  50 */   private final ConcurrentMap<Class<?>, Schema<?>> schemaCache = new ConcurrentHashMap<>();
/*     */ 
/*     */ 
/*     */   
/*     */   public static Protobuf getInstance() {
/*  55 */     return INSTANCE;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> void writeTo(T message, Writer writer) throws IOException {
/*  60 */     schemaFor(message).writeTo(message, writer);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> void mergeFrom(T message, Reader reader) throws IOException {
/*  65 */     mergeFrom(message, reader, ExtensionRegistryLite.getEmptyRegistry());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> void mergeFrom(T message, Reader reader, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  71 */     schemaFor(message).mergeFrom(message, reader, extensionRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> void makeImmutable(T message) {
/*  76 */     schemaFor(message).makeImmutable(message);
/*     */   }
/*     */ 
/*     */   
/*     */   <T> boolean isInitialized(T message) {
/*  81 */     return schemaFor(message).isInitialized(message);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> Schema<T> schemaFor(Class<T> messageType) {
/*  86 */     Internal.checkNotNull(messageType, "messageType");
/*     */     
/*  88 */     Schema<T> schema = (Schema<T>)this.schemaCache.get(messageType);
/*  89 */     if (schema == null) {
/*  90 */       schema = this.schemaFactory.createSchema(messageType);
/*     */       
/*  92 */       Schema<T> previous = (Schema)registerSchema(messageType, schema);
/*  93 */       if (previous != null)
/*     */       {
/*  95 */         schema = previous;
/*     */       }
/*     */     } 
/*  98 */     return schema;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> Schema<T> schemaFor(T message) {
/* 104 */     return schemaFor((Class)message.getClass());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Schema<?> registerSchema(Class<?> messageType, Schema<?> schema) {
/* 116 */     Internal.checkNotNull(messageType, "messageType");
/* 117 */     Internal.checkNotNull(schema, "schema");
/* 118 */     return this.schemaCache.putIfAbsent(messageType, schema);
/*     */   }
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
/*     */   public Schema<?> registerSchemaOverride(Class<?> messageType, Schema<?> schema) {
/* 131 */     Internal.checkNotNull(messageType, "messageType");
/* 132 */     Internal.checkNotNull(schema, "schema");
/* 133 */     return this.schemaCache.put(messageType, schema);
/*     */   }
/*     */   
/*     */   private Protobuf() {
/* 137 */     this.schemaFactory = new ManifestSchemaFactory();
/*     */   }
/*     */   
/*     */   int getTotalSchemaSize() {
/* 141 */     int result = 0;
/* 142 */     for (Schema<?> schema : this.schemaCache.values()) {
/* 143 */       if (schema instanceof MessageSchema) {
/* 144 */         result += ((MessageSchema)schema).getSchemaSize();
/*     */       }
/*     */     } 
/* 147 */     return result;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\Protobuf.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */