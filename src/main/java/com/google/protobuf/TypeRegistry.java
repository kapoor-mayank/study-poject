/*     */ package com.google.protobuf;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Logger;
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
/*     */ public class TypeRegistry
/*     */ {
/*     */   private final Map<String, Descriptors.Descriptor> types;
/*  47 */   private static final Logger logger = Logger.getLogger(TypeRegistry.class.getName());
/*     */   
/*     */   private static class EmptyTypeRegistryHolder {
/*  50 */     private static final TypeRegistry EMPTY = new TypeRegistry(
/*  51 */         Collections.emptyMap());
/*     */   }
/*     */   
/*     */   public static TypeRegistry getEmptyTypeRegistry() {
/*  55 */     return EmptyTypeRegistryHolder.EMPTY;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Builder newBuilder() {
/*  60 */     return new Builder();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Descriptors.Descriptor find(String name) {
/*  67 */     return this.types.get(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final Descriptors.Descriptor getDescriptorForTypeUrl(String typeUrl) throws InvalidProtocolBufferException {
/*  75 */     return find(getTypeName(typeUrl));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   TypeRegistry(Map<String, Descriptors.Descriptor> types) {
/*  81 */     this.types = types;
/*     */   }
/*     */   
/*     */   private static String getTypeName(String typeUrl) throws InvalidProtocolBufferException {
/*  85 */     String[] parts = typeUrl.split("/");
/*  86 */     if (parts.length == 1) {
/*  87 */       throw new InvalidProtocolBufferException("Invalid type url found: " + typeUrl);
/*     */     }
/*  89 */     return parts[parts.length - 1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final class Builder
/*     */   {
/*     */     public Builder add(Descriptors.Descriptor messageType) {
/* 101 */       if (this.types == null) {
/* 102 */         throw new IllegalStateException("A TypeRegistry.Builder can only be used once.");
/*     */       }
/* 104 */       addFile(messageType.getFile());
/* 105 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder add(Iterable<Descriptors.Descriptor> messageTypes) {
/* 113 */       if (this.types == null) {
/* 114 */         throw new IllegalStateException("A TypeRegistry.Builder can only be used once.");
/*     */       }
/* 116 */       for (Descriptors.Descriptor type : messageTypes) {
/* 117 */         addFile(type.getFile());
/*     */       }
/* 119 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public TypeRegistry build() {
/* 124 */       TypeRegistry result = new TypeRegistry(this.types);
/*     */       
/* 126 */       this.types = null;
/* 127 */       return result;
/*     */     }
/*     */ 
/*     */     
/*     */     private void addFile(Descriptors.FileDescriptor file) {
/* 132 */       if (!this.files.add(file.getFullName())) {
/*     */         return;
/*     */       }
/* 135 */       for (Descriptors.FileDescriptor dependency : file.getDependencies()) {
/* 136 */         addFile(dependency);
/*     */       }
/* 138 */       for (Descriptors.Descriptor message : file.getMessageTypes()) {
/* 139 */         addMessage(message);
/*     */       }
/*     */     }
/*     */     
/*     */     private void addMessage(Descriptors.Descriptor message) {
/* 144 */       for (Descriptors.Descriptor nestedType : message.getNestedTypes()) {
/* 145 */         addMessage(nestedType);
/*     */       }
/*     */       
/* 148 */       if (this.types.containsKey(message.getFullName())) {
/* 149 */         TypeRegistry.logger.warning("Type " + message.getFullName() + " is added multiple times.");
/*     */         
/*     */         return;
/*     */       } 
/* 153 */       this.types.put(message.getFullName(), message);
/*     */     }
/*     */     
/* 156 */     private final Set<String> files = new HashSet<>();
/* 157 */     private Map<String, Descriptors.Descriptor> types = new HashMap<>();
/*     */     
/*     */     private Builder() {}
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\TypeRegistry.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */