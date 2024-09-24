/*     */ package com.google.protobuf;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
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
/*     */ public class ExtensionRegistryLite
/*     */ {
/*     */   private static volatile boolean eagerlyParseMessageSets = false;
/*     */   private static boolean doFullRuntimeInheritanceCheck = true;
/*     */   static final String EXTENSION_CLASS_NAME = "com.google.protobuf.Extension";
/*     */   private static volatile ExtensionRegistryLite emptyRegistry;
/*     */   
/*     */   private static class ExtensionClassHolder
/*     */   {
/*  87 */     static final Class<?> INSTANCE = resolveExtensionClass();
/*     */     
/*     */     static Class<?> resolveExtensionClass() {
/*     */       try {
/*  91 */         return Class.forName("com.google.protobuf.Extension");
/*  92 */       } catch (ClassNotFoundException e) {
/*     */         
/*  94 */         return null;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean isEagerlyParseMessageSets() {
/* 100 */     return eagerlyParseMessageSets;
/*     */   }
/*     */   
/*     */   public static void setEagerlyParseMessageSets(boolean isEagerlyParse) {
/* 104 */     eagerlyParseMessageSets = isEagerlyParse;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ExtensionRegistryLite newInstance() {
/* 114 */     return doFullRuntimeInheritanceCheck ? 
/* 115 */       ExtensionRegistryFactory.create() : new ExtensionRegistryLite();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ExtensionRegistryLite getEmptyRegistry() {
/* 126 */     ExtensionRegistryLite result = emptyRegistry;
/* 127 */     if (result == null) {
/* 128 */       synchronized (ExtensionRegistryLite.class) {
/* 129 */         result = emptyRegistry;
/* 130 */         if (result == null)
/*     */         {
/*     */ 
/*     */           
/* 134 */           result = emptyRegistry = doFullRuntimeInheritanceCheck ? ExtensionRegistryFactory.createEmpty() : EMPTY_REGISTRY_LITE;
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 139 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ExtensionRegistryLite getUnmodifiable() {
/* 145 */     return new ExtensionRegistryLite(this);
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
/*     */   public <ContainingType extends MessageLite> GeneratedMessageLite.GeneratedExtension<ContainingType, ?> findLiteExtensionByNumber(ContainingType containingTypeDefaultInstance, int fieldNumber) {
/* 157 */     return (GeneratedMessageLite.GeneratedExtension<ContainingType, ?>)this.extensionsByNumber
/* 158 */       .get(new ObjectIntPair(containingTypeDefaultInstance, fieldNumber));
/*     */   }
/*     */ 
/*     */   
/*     */   public final void add(GeneratedMessageLite.GeneratedExtension<?, ?> extension) {
/* 163 */     this.extensionsByNumber.put(new ObjectIntPair(extension
/* 164 */           .getContainingTypeDefaultInstance(), extension.getNumber()), extension);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void add(ExtensionLite<?, ?> extension) {
/* 173 */     if (GeneratedMessageLite.GeneratedExtension.class.isAssignableFrom(extension.getClass())) {
/* 174 */       add((GeneratedMessageLite.GeneratedExtension<?, ?>)extension);
/*     */     }
/* 176 */     if (doFullRuntimeInheritanceCheck && ExtensionRegistryFactory.isFullRegistry(this)) {
/*     */       try {
/* 178 */         getClass().getMethod("add", new Class[] { ExtensionClassHolder.INSTANCE }).invoke(this, new Object[] { extension });
/* 179 */       } catch (Exception e) {
/* 180 */         throw new IllegalArgumentException(
/* 181 */             String.format("Could not invoke ExtensionRegistry#add for %s", new Object[] { extension }), e);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ExtensionRegistryLite() {
/* 193 */     this.extensionsByNumber = new HashMap<>();
/*     */   }
/*     */ 
/*     */   
/* 197 */   static final ExtensionRegistryLite EMPTY_REGISTRY_LITE = new ExtensionRegistryLite(true);
/*     */   
/*     */   ExtensionRegistryLite(ExtensionRegistryLite other) {
/* 200 */     if (other == EMPTY_REGISTRY_LITE) {
/* 201 */       this.extensionsByNumber = Collections.emptyMap();
/*     */     } else {
/* 203 */       this.extensionsByNumber = Collections.unmodifiableMap(other.extensionsByNumber);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private final Map<ObjectIntPair, GeneratedMessageLite.GeneratedExtension<?, ?>> extensionsByNumber;
/*     */   
/*     */   ExtensionRegistryLite(boolean empty) {
/* 211 */     this.extensionsByNumber = Collections.emptyMap();
/*     */   }
/*     */   
/*     */   private static final class ObjectIntPair
/*     */   {
/*     */     private final Object object;
/*     */     private final int number;
/*     */     
/*     */     ObjectIntPair(Object object, int number) {
/* 220 */       this.object = object;
/* 221 */       this.number = number;
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 226 */       return System.identityHashCode(this.object) * 65535 + this.number;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object obj) {
/* 231 */       if (!(obj instanceof ObjectIntPair)) {
/* 232 */         return false;
/*     */       }
/* 234 */       ObjectIntPair other = (ObjectIntPair)obj;
/* 235 */       return (this.object == other.object && this.number == other.number);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\ExtensionRegistryLite.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */