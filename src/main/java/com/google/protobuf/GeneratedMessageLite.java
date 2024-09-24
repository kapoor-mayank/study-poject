/*      */ package com.google.protobuf;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.ObjectStreamException;
/*      */ import java.io.Serializable;
/*      */ import java.lang.reflect.Field;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.concurrent.ConcurrentHashMap;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class GeneratedMessageLite<MessageType extends GeneratedMessageLite<MessageType, BuilderType>, BuilderType extends GeneratedMessageLite.Builder<MessageType, BuilderType>>
/*      */   extends AbstractMessageLite<MessageType, BuilderType>
/*      */ {
/*   66 */   protected UnknownFieldSetLite unknownFields = UnknownFieldSetLite.getDefaultInstance();
/*      */ 
/*      */   
/*   69 */   protected int memoizedSerializedSize = -1;
/*      */ 
/*      */ 
/*      */   
/*      */   public final Parser<MessageType> getParserForType() {
/*   74 */     return (Parser<MessageType>)dynamicMethod(MethodToInvoke.GET_PARSER);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final MessageType getDefaultInstanceForType() {
/*   80 */     return (MessageType)dynamicMethod(MethodToInvoke.GET_DEFAULT_INSTANCE);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final BuilderType newBuilderForType() {
/*   86 */     return (BuilderType)dynamicMethod(MethodToInvoke.NEW_BUILDER);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/*  103 */     return MessageLiteToString.toString(this, super.toString());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode() {
/*  109 */     if (this.memoizedHashCode != 0) {
/*  110 */       return this.memoizedHashCode;
/*      */     }
/*  112 */     this.memoizedHashCode = Protobuf.getInstance().<GeneratedMessageLite<MessageType, BuilderType>>schemaFor(this).hashCode(this);
/*  113 */     return this.memoizedHashCode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean equals(Object other) {
/*  120 */     if (this == other) {
/*  121 */       return true;
/*      */     }
/*      */     
/*  124 */     if (other == null) {
/*  125 */       return false;
/*      */     }
/*      */     
/*  128 */     if (getClass() != other.getClass()) {
/*  129 */       return false;
/*      */     }
/*      */     
/*  132 */     return Protobuf.getInstance().<GeneratedMessageLite<MessageType, BuilderType>>schemaFor(this).equals(this, (GeneratedMessageLite<MessageType, BuilderType>)other);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void ensureUnknownFieldsInitialized() {
/*  141 */     if (this.unknownFields == UnknownFieldSetLite.getDefaultInstance()) {
/*  142 */       this.unknownFields = UnknownFieldSetLite.newInstance();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean parseUnknownField(int tag, CodedInputStream input) throws IOException {
/*  153 */     if (WireFormat.getTagWireType(tag) == 4) {
/*  154 */       return false;
/*      */     }
/*      */     
/*  157 */     ensureUnknownFieldsInitialized();
/*  158 */     return this.unknownFields.mergeFieldFrom(tag, input);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void mergeVarintField(int tag, int value) {
/*  163 */     ensureUnknownFieldsInitialized();
/*  164 */     this.unknownFields.mergeVarintField(tag, value);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void mergeLengthDelimitedField(int fieldNumber, ByteString value) {
/*  169 */     ensureUnknownFieldsInitialized();
/*  170 */     this.unknownFields.mergeLengthDelimitedField(fieldNumber, value);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void makeImmutable() {
/*  175 */     Protobuf.getInstance().<GeneratedMessageLite<MessageType, BuilderType>>schemaFor(this).makeImmutable(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final <MessageType extends GeneratedMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> BuilderType createBuilder() {
/*  182 */     return (BuilderType)dynamicMethod(MethodToInvoke.NEW_BUILDER);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final <MessageType extends GeneratedMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> BuilderType createBuilder(MessageType prototype) {
/*  189 */     return createBuilder().mergeFrom(prototype);
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean isInitialized() {
/*  194 */     return isInitialized(this, Boolean.TRUE.booleanValue());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final BuilderType toBuilder() {
/*  200 */     Builder builder = (Builder)dynamicMethod(MethodToInvoke.NEW_BUILDER);
/*  201 */     builder.mergeFrom(this);
/*  202 */     return (BuilderType)builder;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public enum MethodToInvoke
/*      */   {
/*  213 */     GET_MEMOIZED_IS_INITIALIZED,
/*  214 */     SET_MEMOIZED_IS_INITIALIZED,
/*      */ 
/*      */     
/*  217 */     BUILD_MESSAGE_INFO,
/*  218 */     NEW_MUTABLE_INSTANCE,
/*  219 */     NEW_BUILDER,
/*  220 */     GET_DEFAULT_INSTANCE,
/*  221 */     GET_PARSER;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Object dynamicMethod(MethodToInvoke method, Object arg0) {
/*  251 */     return dynamicMethod(method, arg0, (Object)null);
/*      */   }
/*      */ 
/*      */   
/*      */   protected Object dynamicMethod(MethodToInvoke method) {
/*  256 */     return dynamicMethod(method, (Object)null, (Object)null);
/*      */   }
/*      */ 
/*      */   
/*      */   int getMemoizedSerializedSize() {
/*  261 */     return this.memoizedSerializedSize;
/*      */   }
/*      */ 
/*      */   
/*      */   void setMemoizedSerializedSize(int size) {
/*  266 */     this.memoizedSerializedSize = size;
/*      */   }
/*      */   
/*      */   public void writeTo(CodedOutputStream output) throws IOException {
/*  270 */     Protobuf.getInstance()
/*  271 */       .<GeneratedMessageLite<MessageType, BuilderType>>schemaFor(this)
/*  272 */       .writeTo(this, CodedOutputStreamWriter.forCodedOutput(output));
/*      */   }
/*      */   
/*      */   public int getSerializedSize() {
/*  276 */     if (this.memoizedSerializedSize == -1) {
/*  277 */       this.memoizedSerializedSize = Protobuf.getInstance().<GeneratedMessageLite<MessageType, BuilderType>>schemaFor(this).getSerializedSize(this);
/*      */     }
/*  279 */     return this.memoizedSerializedSize;
/*      */   }
/*      */ 
/*      */   
/*      */   Object buildMessageInfo() throws Exception {
/*  284 */     return dynamicMethod(MethodToInvoke.BUILD_MESSAGE_INFO);
/*      */   }
/*      */   
/*  287 */   private static Map<Object, GeneratedMessageLite<?, ?>> defaultInstanceMap = new ConcurrentHashMap<>();
/*      */ 
/*      */ 
/*      */   
/*      */   static <T extends GeneratedMessageLite<?, ?>> T getDefaultInstance(Class<T> clazz) {
/*  292 */     GeneratedMessageLite<?, ?> generatedMessageLite = defaultInstanceMap.get(clazz);
/*  293 */     if (generatedMessageLite == null) {
/*      */ 
/*      */       
/*      */       try {
/*  297 */         Class.forName(clazz.getName(), true, clazz.getClassLoader());
/*  298 */       } catch (ClassNotFoundException e) {
/*  299 */         throw new IllegalStateException("Class initialization cannot fail.", e);
/*      */       } 
/*  301 */       generatedMessageLite = defaultInstanceMap.get(clazz);
/*      */     } 
/*  303 */     if (generatedMessageLite == null) {
/*      */ 
/*      */       
/*  306 */       generatedMessageLite = (GeneratedMessageLite<?, ?>)((GeneratedMessageLite)UnsafeUtil.<GeneratedMessageLite>allocateInstance(clazz)).getDefaultInstanceForType();
/*      */       
/*  308 */       if (generatedMessageLite == null) {
/*  309 */         throw new IllegalStateException();
/*      */       }
/*  311 */       defaultInstanceMap.put(clazz, generatedMessageLite);
/*      */     } 
/*  313 */     return (T)generatedMessageLite;
/*      */   }
/*      */ 
/*      */   
/*      */   protected static <T extends GeneratedMessageLite<?, ?>> void registerDefaultInstance(Class<T> clazz, T defaultInstance) {
/*  318 */     defaultInstanceMap.put(clazz, (GeneratedMessageLite<?, ?>)defaultInstance);
/*      */   }
/*      */ 
/*      */   
/*      */   protected static Object newMessageInfo(MessageLite defaultInstance, String info, Object[] objects) {
/*  323 */     return new RawMessageInfo(defaultInstance, info, objects);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void mergeUnknownFields(UnknownFieldSetLite unknownFields) {
/*  332 */     this.unknownFields = UnknownFieldSetLite.mutableCopyOf(this.unknownFields, unknownFields);
/*      */   }
/*      */ 
/*      */   
/*      */   public static abstract class Builder<MessageType extends GeneratedMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>>
/*      */     extends AbstractMessageLite.Builder<MessageType, BuilderType>
/*      */   {
/*      */     private final MessageType defaultInstance;
/*      */     
/*      */     protected MessageType instance;
/*      */     
/*      */     protected boolean isBuilt;
/*      */     
/*      */     protected Builder(MessageType defaultInstance) {
/*  346 */       this.defaultInstance = defaultInstance;
/*  347 */       this
/*  348 */         .instance = (MessageType)defaultInstance.dynamicMethod(MethodToInvoke.NEW_MUTABLE_INSTANCE);
/*  349 */       this.isBuilt = false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected final void copyOnWrite() {
/*  357 */       if (this.isBuilt) {
/*  358 */         copyOnWriteInternal();
/*  359 */         this.isBuilt = false;
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     protected void copyOnWriteInternal() {
/*  365 */       GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite)this.instance.dynamicMethod(MethodToInvoke.NEW_MUTABLE_INSTANCE);
/*  366 */       mergeFromInstance((MessageType)generatedMessageLite, this.instance);
/*  367 */       this.instance = (MessageType)generatedMessageLite;
/*      */     }
/*      */ 
/*      */     
/*      */     public final boolean isInitialized() {
/*  372 */       return GeneratedMessageLite.isInitialized(this.instance, false);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public final BuilderType clear() {
/*  378 */       this.instance = (MessageType)this.instance.dynamicMethod(MethodToInvoke.NEW_MUTABLE_INSTANCE);
/*  379 */       return (BuilderType)this;
/*      */     }
/*      */ 
/*      */     
/*      */     public BuilderType clone() {
/*  384 */       BuilderType builder = (BuilderType)getDefaultInstanceForType().newBuilderForType();
/*  385 */       builder.mergeFrom(buildPartial());
/*  386 */       return builder;
/*      */     }
/*      */ 
/*      */     
/*      */     public MessageType buildPartial() {
/*  391 */       if (this.isBuilt) {
/*  392 */         return this.instance;
/*      */       }
/*      */       
/*  395 */       this.instance.makeImmutable();
/*      */       
/*  397 */       this.isBuilt = true;
/*  398 */       return this.instance;
/*      */     }
/*      */ 
/*      */     
/*      */     public final MessageType build() {
/*  403 */       MessageType result = buildPartial();
/*  404 */       if (!result.isInitialized()) {
/*  405 */         throw newUninitializedMessageException(result);
/*      */       }
/*  407 */       return result;
/*      */     }
/*      */ 
/*      */     
/*      */     protected BuilderType internalMergeFrom(MessageType message) {
/*  412 */       return mergeFrom(message);
/*      */     }
/*      */ 
/*      */     
/*      */     public BuilderType mergeFrom(MessageType message) {
/*  417 */       copyOnWrite();
/*  418 */       mergeFromInstance(this.instance, message);
/*  419 */       return (BuilderType)this;
/*      */     }
/*      */     
/*      */     private void mergeFromInstance(MessageType dest, MessageType src) {
/*  423 */       Protobuf.getInstance().<MessageType>schemaFor(dest).mergeFrom(dest, src);
/*      */     }
/*      */ 
/*      */     
/*      */     public MessageType getDefaultInstanceForType() {
/*  428 */       return this.defaultInstance;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public BuilderType mergeFrom(byte[] input, int offset, int length, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  435 */       copyOnWrite();
/*      */       try {
/*  437 */         Protobuf.getInstance().<MessageType>schemaFor(this.instance).mergeFrom(this.instance, input, offset, offset + length, new ArrayDecoders.Registers(extensionRegistry));
/*      */       
/*      */       }
/*  440 */       catch (InvalidProtocolBufferException e) {
/*  441 */         throw e;
/*  442 */       } catch (IndexOutOfBoundsException e) {
/*  443 */         throw InvalidProtocolBufferException.truncatedMessage();
/*  444 */       } catch (IOException e) {
/*  445 */         throw new RuntimeException("Reading from byte array should not throw IOException.", e);
/*      */       } 
/*  447 */       return (BuilderType)this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public BuilderType mergeFrom(byte[] input, int offset, int length) throws InvalidProtocolBufferException {
/*  454 */       return mergeFrom(input, offset, length, ExtensionRegistryLite.getEmptyRegistry());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public BuilderType mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  462 */       copyOnWrite();
/*      */ 
/*      */       
/*      */       try {
/*  466 */         Protobuf.getInstance().<MessageType>schemaFor(this.instance).mergeFrom(this.instance, 
/*  467 */             CodedInputStreamReader.forCodedInput(input), extensionRegistry);
/*  468 */       } catch (RuntimeException e) {
/*  469 */         if (e.getCause() instanceof IOException) {
/*  470 */           throw (IOException)e.getCause();
/*      */         }
/*  472 */         throw e;
/*      */       } 
/*  474 */       return (BuilderType)this;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static abstract class ExtendableMessage<MessageType extends ExtendableMessage<MessageType, BuilderType>, BuilderType extends ExtendableBuilder<MessageType, BuilderType>>
/*      */     extends GeneratedMessageLite<MessageType, BuilderType>
/*      */     implements ExtendableMessageOrBuilder<MessageType, BuilderType>
/*      */   {
/*  509 */     protected FieldSet<ExtensionDescriptor> extensions = FieldSet.emptySet();
/*      */ 
/*      */     
/*      */     protected final void mergeExtensionFields(MessageType other) {
/*  513 */       if (this.extensions.isImmutable()) {
/*  514 */         this.extensions = this.extensions.clone();
/*      */       }
/*  516 */       this.extensions.mergeFrom(((ExtendableMessage)other).extensions);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected <MessageType extends MessageLite> boolean parseUnknownField(MessageType defaultInstance, CodedInputStream input, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
/*  532 */       int fieldNumber = WireFormat.getTagFieldNumber(tag);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  537 */       GeneratedExtension<MessageType, ?> extension = extensionRegistry.findLiteExtensionByNumber(defaultInstance, fieldNumber);
/*      */       
/*  539 */       return parseExtension(input, extensionRegistry, extension, tag, fieldNumber);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean parseExtension(CodedInputStream input, ExtensionRegistryLite extensionRegistry, GeneratedExtension<?, ?> extension, int tag, int fieldNumber) throws IOException {
/*  549 */       int wireType = WireFormat.getTagWireType(tag);
/*  550 */       boolean unknown = false;
/*  551 */       boolean packed = false;
/*  552 */       if (extension == null) {
/*  553 */         unknown = true;
/*  554 */       } else if (wireType == 
/*  555 */         FieldSet.getWireFormatForFieldType(extension.descriptor
/*  556 */           .getLiteType(), false)) {
/*  557 */         packed = false;
/*  558 */       } else if (extension.descriptor.isRepeated && extension.descriptor.type
/*  559 */         .isPackable() && wireType == 
/*      */         
/*  561 */         FieldSet.getWireFormatForFieldType(extension.descriptor
/*  562 */           .getLiteType(), true)) {
/*  563 */         packed = true;
/*      */       } else {
/*  565 */         unknown = true;
/*      */       } 
/*      */       
/*  568 */       if (unknown) {
/*  569 */         return parseUnknownField(tag, input);
/*      */       }
/*      */       
/*  572 */       ensureExtensionsAreMutable();
/*      */       
/*  574 */       if (packed) {
/*  575 */         int length = input.readRawVarint32();
/*  576 */         int limit = input.pushLimit(length);
/*  577 */         if (extension.descriptor.getLiteType() == WireFormat.FieldType.ENUM) {
/*  578 */           while (input.getBytesUntilLimit() > 0) {
/*  579 */             int rawValue = input.readEnum();
/*  580 */             Object value = extension.descriptor.getEnumType().findValueByNumber(rawValue);
/*  581 */             if (value == null)
/*      */             {
/*      */               
/*  584 */               return true;
/*      */             }
/*  586 */             this.extensions.addRepeatedField(extension.descriptor, extension
/*  587 */                 .singularToFieldSetType(value));
/*      */           } 
/*      */         } else {
/*  590 */           while (input.getBytesUntilLimit() > 0) {
/*      */             
/*  592 */             Object value = FieldSet.readPrimitiveField(input, extension.descriptor
/*  593 */                 .getLiteType(), false);
/*  594 */             this.extensions.addRepeatedField(extension.descriptor, value);
/*      */           } 
/*      */         } 
/*  597 */         input.popLimit(limit);
/*      */       } else {
/*      */         Object value; MessageLite.Builder subBuilder; int rawValue;
/*  600 */         switch (extension.descriptor.getLiteJavaType()) {
/*      */           
/*      */           case MESSAGE:
/*  603 */             subBuilder = null;
/*  604 */             if (!extension.descriptor.isRepeated()) {
/*  605 */               MessageLite existingValue = (MessageLite)this.extensions.getField(extension.descriptor);
/*  606 */               if (existingValue != null) {
/*  607 */                 subBuilder = existingValue.toBuilder();
/*      */               }
/*      */             } 
/*  610 */             if (subBuilder == null) {
/*  611 */               subBuilder = extension.getMessageDefaultInstance().newBuilderForType();
/*      */             }
/*  613 */             if (extension.descriptor.getLiteType() == WireFormat.FieldType.GROUP) {
/*  614 */               input.readGroup(extension.getNumber(), subBuilder, extensionRegistry);
/*      */             } else {
/*  616 */               input.readMessage(subBuilder, extensionRegistry);
/*      */             } 
/*  618 */             value = subBuilder.build();
/*      */             break;
/*      */           
/*      */           case ENUM:
/*  622 */             rawValue = input.readEnum();
/*  623 */             value = extension.descriptor.getEnumType().findValueByNumber(rawValue);
/*      */ 
/*      */             
/*  626 */             if (value == null) {
/*  627 */               mergeVarintField(fieldNumber, rawValue);
/*  628 */               return true;
/*      */             } 
/*      */             break;
/*      */           
/*      */           default:
/*  633 */             value = FieldSet.readPrimitiveField(input, extension.descriptor
/*  634 */                 .getLiteType(), false);
/*      */             break;
/*      */         } 
/*      */         
/*  638 */         if (extension.descriptor.isRepeated()) {
/*  639 */           this.extensions.addRepeatedField(extension.descriptor, extension
/*  640 */               .singularToFieldSetType(value));
/*      */         } else {
/*  642 */           this.extensions.setField(extension.descriptor, extension.singularToFieldSetType(value));
/*      */         } 
/*      */       } 
/*  645 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected <MessageType extends MessageLite> boolean parseUnknownFieldAsMessageSet(MessageType defaultInstance, CodedInputStream input, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
/*  662 */       if (tag == WireFormat.MESSAGE_SET_ITEM_TAG) {
/*  663 */         mergeMessageSetExtensionFromCodedStream(defaultInstance, input, extensionRegistry);
/*  664 */         return true;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  669 */       int wireType = WireFormat.getTagWireType(tag);
/*  670 */       if (wireType == 2) {
/*  671 */         return parseUnknownField(defaultInstance, input, extensionRegistry, tag);
/*      */       }
/*      */       
/*  674 */       return input.skipField(tag);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private <MessageType extends MessageLite> void mergeMessageSetExtensionFromCodedStream(MessageType defaultInstance, CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  706 */       int typeId = 0;
/*  707 */       ByteString rawBytes = null;
/*  708 */       GeneratedExtension<?, ?> extension = null;
/*      */ 
/*      */ 
/*      */       
/*      */       while (true) {
/*  713 */         int tag = input.readTag();
/*  714 */         if (tag == 0) {
/*      */           break;
/*      */         }
/*      */         
/*  718 */         if (tag == WireFormat.MESSAGE_SET_TYPE_ID_TAG) {
/*  719 */           typeId = input.readUInt32();
/*  720 */           if (typeId != 0)
/*  721 */             extension = extensionRegistry.findLiteExtensionByNumber(defaultInstance, typeId); 
/*      */           continue;
/*      */         } 
/*  724 */         if (tag == WireFormat.MESSAGE_SET_MESSAGE_TAG) {
/*  725 */           if (typeId != 0 && 
/*  726 */             extension != null) {
/*      */ 
/*      */             
/*  729 */             eagerlyMergeMessageSetExtension(input, extension, extensionRegistry, typeId);
/*  730 */             rawBytes = null;
/*      */             
/*      */             continue;
/*      */           } 
/*      */           
/*  735 */           rawBytes = input.readBytes();
/*      */           continue;
/*      */         } 
/*  738 */         if (!input.skipField(tag)) {
/*      */           break;
/*      */         }
/*      */       } 
/*      */       
/*  743 */       input.checkLastTagWas(WireFormat.MESSAGE_SET_ITEM_END_TAG);
/*      */ 
/*      */       
/*  746 */       if (rawBytes != null && typeId != 0) {
/*  747 */         if (extension != null) {
/*  748 */           mergeMessageSetExtensionFromBytes(rawBytes, extensionRegistry, extension);
/*      */         }
/*  750 */         else if (rawBytes != null) {
/*  751 */           mergeLengthDelimitedField(typeId, rawBytes);
/*      */         } 
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void eagerlyMergeMessageSetExtension(CodedInputStream input, GeneratedExtension<?, ?> extension, ExtensionRegistryLite extensionRegistry, int typeId) throws IOException {
/*  763 */       int fieldNumber = typeId;
/*  764 */       int tag = WireFormat.makeTag(typeId, 2);
/*  765 */       parseExtension(input, extensionRegistry, extension, tag, fieldNumber);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void mergeMessageSetExtensionFromBytes(ByteString rawBytes, ExtensionRegistryLite extensionRegistry, GeneratedExtension<?, ?> extension) throws IOException {
/*  773 */       MessageLite.Builder subBuilder = null;
/*  774 */       MessageLite existingValue = (MessageLite)this.extensions.getField(extension.descriptor);
/*  775 */       if (existingValue != null) {
/*  776 */         subBuilder = existingValue.toBuilder();
/*      */       }
/*  778 */       if (subBuilder == null) {
/*  779 */         subBuilder = extension.getMessageDefaultInstance().newBuilderForType();
/*      */       }
/*  781 */       subBuilder.mergeFrom(rawBytes, extensionRegistry);
/*  782 */       MessageLite value = subBuilder.build();
/*      */       
/*  784 */       ensureExtensionsAreMutable()
/*  785 */         .setField(extension.descriptor, extension.singularToFieldSetType(value));
/*      */     }
/*      */     
/*      */     FieldSet<ExtensionDescriptor> ensureExtensionsAreMutable() {
/*  789 */       if (this.extensions.isImmutable()) {
/*  790 */         this.extensions = this.extensions.clone();
/*      */       }
/*  792 */       return this.extensions;
/*      */     }
/*      */     
/*      */     private void verifyExtensionContainingType(GeneratedExtension<MessageType, ?> extension) {
/*  796 */       if (extension.getContainingTypeDefaultInstance() != getDefaultInstanceForType())
/*      */       {
/*  798 */         throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> boolean hasExtension(ExtensionLite<MessageType, Type> extension) {
/*  807 */       GeneratedExtension<MessageType, Type> extensionLite = GeneratedMessageLite.checkIsLite(extension);
/*      */       
/*  809 */       verifyExtensionContainingType(extensionLite);
/*  810 */       return this.extensions.hasField(extensionLite.descriptor);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> int getExtensionCount(ExtensionLite<MessageType, List<Type>> extension) {
/*  817 */       GeneratedExtension<MessageType, List<Type>> extensionLite = (GeneratedExtension)GeneratedMessageLite.checkIsLite((ExtensionLite)extension);
/*      */       
/*  819 */       verifyExtensionContainingType(extensionLite);
/*  820 */       return this.extensions.getRepeatedFieldCount(extensionLite.descriptor);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> Type getExtension(ExtensionLite<MessageType, Type> extension) {
/*  827 */       GeneratedExtension<MessageType, Type> extensionLite = GeneratedMessageLite.checkIsLite(extension);
/*      */       
/*  829 */       verifyExtensionContainingType(extensionLite);
/*  830 */       Object value = this.extensions.getField(extensionLite.descriptor);
/*  831 */       if (value == null) {
/*  832 */         return extensionLite.defaultValue;
/*      */       }
/*  834 */       return (Type)extensionLite.fromFieldSetType(value);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> Type getExtension(ExtensionLite<MessageType, List<Type>> extension, int index) {
/*  843 */       GeneratedExtension<MessageType, List<Type>> extensionLite = (GeneratedExtension)GeneratedMessageLite.checkIsLite((ExtensionLite)extension);
/*      */       
/*  845 */       verifyExtensionContainingType(extensionLite);
/*  846 */       return (Type)extensionLite
/*  847 */         .singularFromFieldSetType(this.extensions
/*  848 */           .getRepeatedField(extensionLite.descriptor, index));
/*      */     }
/*      */ 
/*      */     
/*      */     protected boolean extensionsAreInitialized() {
/*  853 */       return this.extensions.isInitialized();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected class ExtensionWriter
/*      */     {
/*  865 */       private final Iterator<Map.Entry<ExtensionDescriptor, Object>> iter = ExtendableMessage.this.extensions.iterator();
/*      */       private Map.Entry<ExtensionDescriptor, Object> next;
/*      */       private final boolean messageSetWireFormat;
/*      */       
/*      */       private ExtensionWriter(boolean messageSetWireFormat) {
/*  870 */         if (this.iter.hasNext()) {
/*  871 */           this.next = this.iter.next();
/*      */         }
/*  873 */         this.messageSetWireFormat = messageSetWireFormat;
/*      */       }
/*      */       
/*      */       public void writeUntil(int end, CodedOutputStream output) throws IOException {
/*  877 */         while (this.next != null && ((ExtensionDescriptor)this.next.getKey()).getNumber() < end) {
/*  878 */           ExtensionDescriptor extension = this.next.getKey();
/*  879 */           if (this.messageSetWireFormat && extension
/*  880 */             .getLiteJavaType() == WireFormat.JavaType.MESSAGE && 
/*  881 */             !extension.isRepeated()) {
/*  882 */             output.writeMessageSetExtension(extension.getNumber(), (MessageLite)this.next.getValue());
/*      */           } else {
/*  884 */             FieldSet.writeField(extension, this.next.getValue(), output);
/*      */           } 
/*  886 */           if (this.iter.hasNext()) {
/*  887 */             this.next = this.iter.next(); continue;
/*      */           } 
/*  889 */           this.next = null;
/*      */         } 
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     protected ExtensionWriter newExtensionWriter() {
/*  896 */       return new ExtensionWriter(false);
/*      */     }
/*      */     
/*      */     protected ExtensionWriter newMessageSetExtensionWriter() {
/*  900 */       return new ExtensionWriter(true);
/*      */     }
/*      */ 
/*      */     
/*      */     protected int extensionsSerializedSize() {
/*  905 */       return this.extensions.getSerializedSize();
/*      */     }
/*      */     
/*      */     protected int extensionsSerializedSizeAsMessageSet() {
/*  909 */       return this.extensions.getMessageSetSerializedSize();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static abstract class ExtendableBuilder<MessageType extends ExtendableMessage<MessageType, BuilderType>, BuilderType extends ExtendableBuilder<MessageType, BuilderType>>
/*      */     extends Builder<MessageType, BuilderType>
/*      */     implements ExtendableMessageOrBuilder<MessageType, BuilderType>
/*      */   {
/*      */     protected ExtendableBuilder(MessageType defaultInstance) {
/*  921 */       super(defaultInstance);
/*      */     }
/*      */ 
/*      */     
/*      */     void internalSetExtensionSet(FieldSet<ExtensionDescriptor> extensions) {
/*  926 */       copyOnWrite();
/*  927 */       ((ExtendableMessage)this.instance).extensions = extensions;
/*      */     }
/*      */ 
/*      */     
/*      */     protected void copyOnWriteInternal() {
/*  932 */       super.copyOnWriteInternal();
/*  933 */       ((ExtendableMessage)this.instance).extensions = ((ExtendableMessage)this.instance).extensions.clone();
/*      */     }
/*      */     
/*      */     private FieldSet<ExtensionDescriptor> ensureExtensionsAreMutable() {
/*  937 */       FieldSet<ExtensionDescriptor> extensions = ((ExtendableMessage)this.instance).extensions;
/*  938 */       if (extensions.isImmutable()) {
/*  939 */         extensions = extensions.clone();
/*  940 */         ((ExtendableMessage)this.instance).extensions = extensions;
/*      */       } 
/*  942 */       return extensions;
/*      */     }
/*      */ 
/*      */     
/*      */     public final MessageType buildPartial() {
/*  947 */       if (this.isBuilt) {
/*  948 */         return this.instance;
/*      */       }
/*      */       
/*  951 */       ((ExtendableMessage)this.instance).extensions.makeImmutable();
/*  952 */       return super.buildPartial();
/*      */     }
/*      */     
/*      */     private void verifyExtensionContainingType(GeneratedExtension<MessageType, ?> extension) {
/*  956 */       if (extension.getContainingTypeDefaultInstance() != getDefaultInstanceForType())
/*      */       {
/*  958 */         throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> boolean hasExtension(ExtensionLite<MessageType, Type> extension) {
/*  967 */       return ((ExtendableMessage)this.instance).hasExtension(extension);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> int getExtensionCount(ExtensionLite<MessageType, List<Type>> extension) {
/*  974 */       return ((ExtendableMessage)this.instance).getExtensionCount(extension);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> Type getExtension(ExtensionLite<MessageType, Type> extension) {
/*  981 */       return (Type)((ExtendableMessage)this.instance).getExtension(extension);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> Type getExtension(ExtensionLite<MessageType, List<Type>> extension, int index) {
/*  989 */       return (Type)((ExtendableMessage)this.instance).getExtension(extension, index);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> BuilderType setExtension(ExtensionLite<MessageType, Type> extension, Type value) {
/*  995 */       GeneratedExtension<MessageType, Type> extensionLite = GeneratedMessageLite.checkIsLite(extension);
/*      */       
/*  997 */       verifyExtensionContainingType(extensionLite);
/*  998 */       copyOnWrite();
/*  999 */       ensureExtensionsAreMutable()
/* 1000 */         .setField(extensionLite.descriptor, extensionLite.toFieldSetType(value));
/* 1001 */       return (BuilderType)this;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> BuilderType setExtension(ExtensionLite<MessageType, List<Type>> extension, int index, Type value) {
/* 1007 */       GeneratedExtension<MessageType, List<Type>> extensionLite = (GeneratedExtension)GeneratedMessageLite.checkIsLite((ExtensionLite)extension);
/*      */       
/* 1009 */       verifyExtensionContainingType(extensionLite);
/* 1010 */       copyOnWrite();
/* 1011 */       ensureExtensionsAreMutable()
/* 1012 */         .setRepeatedField(extensionLite.descriptor, index, extensionLite
/* 1013 */           .singularToFieldSetType(value));
/* 1014 */       return (BuilderType)this;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> BuilderType addExtension(ExtensionLite<MessageType, List<Type>> extension, Type value) {
/* 1020 */       GeneratedExtension<MessageType, List<Type>> extensionLite = (GeneratedExtension)GeneratedMessageLite.checkIsLite((ExtensionLite)extension);
/*      */       
/* 1022 */       verifyExtensionContainingType(extensionLite);
/* 1023 */       copyOnWrite();
/* 1024 */       ensureExtensionsAreMutable()
/* 1025 */         .addRepeatedField(extensionLite.descriptor, extensionLite.singularToFieldSetType(value));
/* 1026 */       return (BuilderType)this;
/*      */     }
/*      */ 
/*      */     
/*      */     public final BuilderType clearExtension(ExtensionLite<MessageType, ?> extension) {
/* 1031 */       GeneratedExtension<MessageType, ?> extensionLite = GeneratedMessageLite.checkIsLite((ExtensionLite)extension);
/*      */       
/* 1033 */       verifyExtensionContainingType(extensionLite);
/* 1034 */       copyOnWrite();
/* 1035 */       ensureExtensionsAreMutable().clearField(extensionLite.descriptor);
/* 1036 */       return (BuilderType)this;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static <ContainingType extends MessageLite, Type> GeneratedExtension<ContainingType, Type> newSingularGeneratedExtension(ContainingType containingTypeDefaultInstance, Type defaultValue, MessageLite messageDefaultInstance, Internal.EnumLiteMap<?> enumTypeMap, int number, WireFormat.FieldType type, Class singularType) {
/* 1052 */     return new GeneratedExtension<>(containingTypeDefaultInstance, defaultValue, messageDefaultInstance, new ExtensionDescriptor(enumTypeMap, number, type, false, false), singularType);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static <ContainingType extends MessageLite, Type> GeneratedExtension<ContainingType, Type> newRepeatedGeneratedExtension(ContainingType containingTypeDefaultInstance, MessageLite messageDefaultInstance, Internal.EnumLiteMap<?> enumTypeMap, int number, WireFormat.FieldType type, boolean isPacked, Class singularType) {
/* 1072 */     List<?> list = Collections.emptyList();
/* 1073 */     return new GeneratedExtension<>(containingTypeDefaultInstance, (Type)list, messageDefaultInstance, new ExtensionDescriptor(enumTypeMap, number, type, true, isPacked), singularType);
/*      */   }
/*      */ 
/*      */   
/*      */   static final class ExtensionDescriptor
/*      */     implements FieldSet.FieldDescriptorLite<ExtensionDescriptor>
/*      */   {
/*      */     final Internal.EnumLiteMap<?> enumTypeMap;
/*      */     
/*      */     final int number;
/*      */     
/*      */     final WireFormat.FieldType type;
/*      */     final boolean isRepeated;
/*      */     final boolean isPacked;
/*      */     
/*      */     ExtensionDescriptor(Internal.EnumLiteMap<?> enumTypeMap, int number, WireFormat.FieldType type, boolean isRepeated, boolean isPacked) {
/* 1089 */       this.enumTypeMap = enumTypeMap;
/* 1090 */       this.number = number;
/* 1091 */       this.type = type;
/* 1092 */       this.isRepeated = isRepeated;
/* 1093 */       this.isPacked = isPacked;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getNumber() {
/* 1104 */       return this.number;
/*      */     }
/*      */ 
/*      */     
/*      */     public WireFormat.FieldType getLiteType() {
/* 1109 */       return this.type;
/*      */     }
/*      */ 
/*      */     
/*      */     public WireFormat.JavaType getLiteJavaType() {
/* 1114 */       return this.type.getJavaType();
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isRepeated() {
/* 1119 */       return this.isRepeated;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isPacked() {
/* 1124 */       return this.isPacked;
/*      */     }
/*      */ 
/*      */     
/*      */     public Internal.EnumLiteMap<?> getEnumType() {
/* 1129 */       return this.enumTypeMap;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public MessageLite.Builder internalMergeFrom(MessageLite.Builder to, MessageLite from) {
/* 1135 */       return ((Builder<GeneratedMessageLite, MessageLite.Builder>)to).mergeFrom((GeneratedMessageLite)from);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public int compareTo(ExtensionDescriptor other) {
/* 1141 */       return this.number - other.number;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static Method getMethodOrDie(Class clazz, String name, Class... params) {
/*      */     try {
/* 1151 */       return clazz.getMethod(name, params);
/* 1152 */     } catch (NoSuchMethodException e) {
/* 1153 */       throw new RuntimeException("Generated message class \"" + clazz
/* 1154 */           .getName() + "\" missing method \"" + name + "\".", e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static Object invokeOrDie(Method method, Object object, Object... params) {
/*      */     try {
/* 1162 */       return method.invoke(object, params);
/* 1163 */     } catch (IllegalAccessException e) {
/* 1164 */       throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
/*      */     }
/* 1166 */     catch (InvocationTargetException e) {
/* 1167 */       Throwable cause = e.getCause();
/* 1168 */       if (cause instanceof RuntimeException)
/* 1169 */         throw (RuntimeException)cause; 
/* 1170 */       if (cause instanceof Error) {
/* 1171 */         throw (Error)cause;
/*      */       }
/* 1173 */       throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class GeneratedExtension<ContainingType extends MessageLite, Type>
/*      */     extends ExtensionLite<ContainingType, Type>
/*      */   {
/*      */     final ContainingType containingTypeDefaultInstance;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     final Type defaultValue;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     final MessageLite messageDefaultInstance;
/*      */ 
/*      */ 
/*      */     
/*      */     final ExtensionDescriptor descriptor;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     GeneratedExtension(ContainingType containingTypeDefaultInstance, Type defaultValue, MessageLite messageDefaultInstance, ExtensionDescriptor descriptor, Class singularType) {
/* 1204 */       if (containingTypeDefaultInstance == null) {
/* 1205 */         throw new IllegalArgumentException("Null containingTypeDefaultInstance");
/*      */       }
/* 1207 */       if (descriptor.getLiteType() == WireFormat.FieldType.MESSAGE && messageDefaultInstance == null)
/*      */       {
/* 1209 */         throw new IllegalArgumentException("Null messageDefaultInstance");
/*      */       }
/* 1211 */       this.containingTypeDefaultInstance = containingTypeDefaultInstance;
/* 1212 */       this.defaultValue = defaultValue;
/* 1213 */       this.messageDefaultInstance = messageDefaultInstance;
/* 1214 */       this.descriptor = descriptor;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ContainingType getContainingTypeDefaultInstance() {
/* 1224 */       return this.containingTypeDefaultInstance;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public int getNumber() {
/* 1230 */       return this.descriptor.getNumber();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public MessageLite getMessageDefaultInstance() {
/* 1239 */       return this.messageDefaultInstance;
/*      */     }
/*      */ 
/*      */     
/*      */     Object fromFieldSetType(Object value) {
/* 1244 */       if (this.descriptor.isRepeated()) {
/* 1245 */         if (this.descriptor.getLiteJavaType() == WireFormat.JavaType.ENUM) {
/* 1246 */           List<Object> result = new ArrayList();
/* 1247 */           for (Object element : value) {
/* 1248 */             result.add(singularFromFieldSetType(element));
/*      */           }
/* 1250 */           return result;
/*      */         } 
/* 1252 */         return value;
/*      */       } 
/*      */       
/* 1255 */       return singularFromFieldSetType(value);
/*      */     }
/*      */ 
/*      */     
/*      */     Object singularFromFieldSetType(Object value) {
/* 1260 */       if (this.descriptor.getLiteJavaType() == WireFormat.JavaType.ENUM) {
/* 1261 */         return this.descriptor.enumTypeMap.findValueByNumber(((Integer)value).intValue());
/*      */       }
/* 1263 */       return value;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     Object toFieldSetType(Object value) {
/* 1269 */       if (this.descriptor.isRepeated()) {
/* 1270 */         if (this.descriptor.getLiteJavaType() == WireFormat.JavaType.ENUM) {
/* 1271 */           List<Object> result = new ArrayList();
/* 1272 */           for (Object element : value) {
/* 1273 */             result.add(singularToFieldSetType(element));
/*      */           }
/* 1275 */           return result;
/*      */         } 
/* 1277 */         return value;
/*      */       } 
/*      */       
/* 1280 */       return singularToFieldSetType(value);
/*      */     }
/*      */ 
/*      */     
/*      */     Object singularToFieldSetType(Object value) {
/* 1285 */       if (this.descriptor.getLiteJavaType() == WireFormat.JavaType.ENUM) {
/* 1286 */         return Integer.valueOf(((Internal.EnumLite)value).getNumber());
/*      */       }
/* 1288 */       return value;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public WireFormat.FieldType getLiteType() {
/* 1294 */       return this.descriptor.getLiteType();
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isRepeated() {
/* 1299 */       return this.descriptor.isRepeated;
/*      */     }
/*      */ 
/*      */     
/*      */     public Type getDefaultValue() {
/* 1304 */       return this.defaultValue;
/*      */     }
/*      */   }
/*      */   
/*      */   protected static final class SerializedForm implements Serializable {
/*      */     private static final long serialVersionUID = 0L;
/*      */     private final Class<?> messageClass;
/*      */     private final String messageClassName;
/*      */     private final byte[] asBytes;
/*      */     
/*      */     public static SerializedForm of(MessageLite message) {
/* 1315 */       return new SerializedForm(message);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     SerializedForm(MessageLite regularForm) {
/* 1332 */       this.messageClass = regularForm.getClass();
/* 1333 */       this.messageClassName = this.messageClass.getName();
/* 1334 */       this.asBytes = regularForm.toByteArray();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Object readResolve() throws ObjectStreamException {
/*      */       try {
/* 1346 */         Class<?> messageClass = resolveMessageClass();
/*      */         
/* 1348 */         Field defaultInstanceField = messageClass.getDeclaredField("DEFAULT_INSTANCE");
/* 1349 */         defaultInstanceField.setAccessible(true);
/* 1350 */         MessageLite defaultInstance = (MessageLite)defaultInstanceField.get(null);
/* 1351 */         return defaultInstance.newBuilderForType().mergeFrom(this.asBytes).buildPartial();
/* 1352 */       } catch (ClassNotFoundException e) {
/* 1353 */         throw new RuntimeException("Unable to find proto buffer class: " + this.messageClassName, e);
/* 1354 */       } catch (NoSuchFieldException e) {
/* 1355 */         return readResolveFallback();
/* 1356 */       } catch (SecurityException e) {
/* 1357 */         throw new RuntimeException("Unable to call DEFAULT_INSTANCE in " + this.messageClassName, e);
/* 1358 */       } catch (IllegalAccessException e) {
/* 1359 */         throw new RuntimeException("Unable to call parsePartialFrom", e);
/* 1360 */       } catch (InvalidProtocolBufferException e) {
/* 1361 */         throw new RuntimeException("Unable to understand proto buffer", e);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     @Deprecated
/*      */     private Object readResolveFallback() throws ObjectStreamException {
/*      */       try {
/* 1371 */         Class<?> messageClass = resolveMessageClass();
/*      */         
/* 1373 */         Field defaultInstanceField = messageClass.getDeclaredField("defaultInstance");
/* 1374 */         defaultInstanceField.setAccessible(true);
/* 1375 */         MessageLite defaultInstance = (MessageLite)defaultInstanceField.get(null);
/* 1376 */         return defaultInstance.newBuilderForType()
/* 1377 */           .mergeFrom(this.asBytes)
/* 1378 */           .buildPartial();
/* 1379 */       } catch (ClassNotFoundException e) {
/* 1380 */         throw new RuntimeException("Unable to find proto buffer class: " + this.messageClassName, e);
/* 1381 */       } catch (NoSuchFieldException e) {
/* 1382 */         throw new RuntimeException("Unable to find defaultInstance in " + this.messageClassName, e);
/* 1383 */       } catch (SecurityException e) {
/* 1384 */         throw new RuntimeException("Unable to call defaultInstance in " + this.messageClassName, e);
/* 1385 */       } catch (IllegalAccessException e) {
/* 1386 */         throw new RuntimeException("Unable to call parsePartialFrom", e);
/* 1387 */       } catch (InvalidProtocolBufferException e) {
/* 1388 */         throw new RuntimeException("Unable to understand proto buffer", e);
/*      */       } 
/*      */     }
/*      */     
/*      */     private Class<?> resolveMessageClass() throws ClassNotFoundException {
/* 1393 */       return (this.messageClass != null) ? this.messageClass : Class.forName(this.messageClassName);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static <MessageType extends ExtendableMessage<MessageType, BuilderType>, BuilderType extends ExtendableBuilder<MessageType, BuilderType>, T> GeneratedExtension<MessageType, T> checkIsLite(ExtensionLite<MessageType, T> extension) {
/* 1403 */     if (!extension.isLite()) {
/* 1404 */       throw new IllegalArgumentException("Expected a lite extension.");
/*      */     }
/*      */     
/* 1407 */     return (GeneratedExtension<MessageType, T>)extension;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final <T extends GeneratedMessageLite<T, ?>> boolean isInitialized(T message, boolean shouldMemoize) {
/* 1418 */     byte memoizedIsInitialized = ((Byte)message.dynamicMethod(MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED)).byteValue();
/* 1419 */     if (memoizedIsInitialized == 1) {
/* 1420 */       return true;
/*      */     }
/* 1422 */     if (memoizedIsInitialized == 0) {
/* 1423 */       return false;
/*      */     }
/* 1425 */     boolean isInitialized = Protobuf.getInstance().<T>schemaFor(message).isInitialized(message);
/* 1426 */     if (shouldMemoize) {
/* 1427 */       message.dynamicMethod(MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED, isInitialized ? message : null);
/*      */     }
/*      */     
/* 1430 */     return isInitialized;
/*      */   }
/*      */   
/*      */   protected static Internal.IntList emptyIntList() {
/* 1434 */     return IntArrayList.emptyList();
/*      */   }
/*      */   
/*      */   protected static Internal.IntList mutableCopy(Internal.IntList list) {
/* 1438 */     int size = list.size();
/* 1439 */     return list.mutableCopyWithCapacity((size == 0) ? 10 : (size * 2));
/*      */   }
/*      */ 
/*      */   
/*      */   protected static Internal.LongList emptyLongList() {
/* 1444 */     return LongArrayList.emptyList();
/*      */   }
/*      */   
/*      */   protected static Internal.LongList mutableCopy(Internal.LongList list) {
/* 1448 */     int size = list.size();
/* 1449 */     return list.mutableCopyWithCapacity((size == 0) ? 10 : (size * 2));
/*      */   }
/*      */ 
/*      */   
/*      */   protected static Internal.FloatList emptyFloatList() {
/* 1454 */     return FloatArrayList.emptyList();
/*      */   }
/*      */   
/*      */   protected static Internal.FloatList mutableCopy(Internal.FloatList list) {
/* 1458 */     int size = list.size();
/* 1459 */     return list.mutableCopyWithCapacity((size == 0) ? 10 : (size * 2));
/*      */   }
/*      */ 
/*      */   
/*      */   protected static Internal.DoubleList emptyDoubleList() {
/* 1464 */     return DoubleArrayList.emptyList();
/*      */   }
/*      */   
/*      */   protected static Internal.DoubleList mutableCopy(Internal.DoubleList list) {
/* 1468 */     int size = list.size();
/* 1469 */     return list.mutableCopyWithCapacity((size == 0) ? 10 : (size * 2));
/*      */   }
/*      */ 
/*      */   
/*      */   protected static Internal.BooleanList emptyBooleanList() {
/* 1474 */     return BooleanArrayList.emptyList();
/*      */   }
/*      */   
/*      */   protected static Internal.BooleanList mutableCopy(Internal.BooleanList list) {
/* 1478 */     int size = list.size();
/* 1479 */     return list.mutableCopyWithCapacity((size == 0) ? 10 : (size * 2));
/*      */   }
/*      */ 
/*      */   
/*      */   protected static <E> Internal.ProtobufList<E> emptyProtobufList() {
/* 1484 */     return ProtobufArrayList.emptyList();
/*      */   }
/*      */   
/*      */   protected static <E> Internal.ProtobufList<E> mutableCopy(Internal.ProtobufList<E> list) {
/* 1488 */     int size = list.size();
/* 1489 */     return list.mutableCopyWithCapacity((size == 0) ? 10 : (size * 2));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class DefaultInstanceBasedParser<T extends GeneratedMessageLite<T, ?>>
/*      */     extends AbstractParser<T>
/*      */   {
/*      */     private final T defaultInstance;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public DefaultInstanceBasedParser(T defaultInstance) {
/* 1504 */       this.defaultInstance = defaultInstance;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public T parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 1510 */       return GeneratedMessageLite.parsePartialFrom(this.defaultInstance, input, extensionRegistry);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public T parsePartialFrom(byte[] input, int offset, int length, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 1517 */       return GeneratedMessageLite.parsePartialFrom(this.defaultInstance, input, offset, length, extensionRegistry);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static <T extends GeneratedMessageLite<T, ?>> T parsePartialFrom(T instance, CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 1531 */     GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite)instance.dynamicMethod(MethodToInvoke.NEW_MUTABLE_INSTANCE);
/*      */ 
/*      */     
/*      */     try {
/* 1535 */       Schema<T> schema = Protobuf.getInstance().schemaFor((T)generatedMessageLite);
/* 1536 */       schema.mergeFrom((T)generatedMessageLite, CodedInputStreamReader.forCodedInput(input), extensionRegistry);
/* 1537 */       schema.makeImmutable((T)generatedMessageLite);
/* 1538 */     } catch (IOException e) {
/* 1539 */       if (e.getCause() instanceof InvalidProtocolBufferException) {
/* 1540 */         throw (InvalidProtocolBufferException)e.getCause();
/*      */       }
/* 1542 */       throw (new InvalidProtocolBufferException(e.getMessage())).setUnfinishedMessage(generatedMessageLite);
/* 1543 */     } catch (RuntimeException e) {
/* 1544 */       if (e.getCause() instanceof InvalidProtocolBufferException) {
/* 1545 */         throw (InvalidProtocolBufferException)e.getCause();
/*      */       }
/* 1547 */       throw e;
/*      */     } 
/* 1549 */     return (T)generatedMessageLite;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static <T extends GeneratedMessageLite<T, ?>> T parsePartialFrom(T instance, byte[] input, int offset, int length, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 1557 */     GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite)instance.dynamicMethod(MethodToInvoke.NEW_MUTABLE_INSTANCE);
/*      */     try {
/* 1559 */       Schema<T> schema = Protobuf.getInstance().schemaFor((T)generatedMessageLite);
/* 1560 */       schema.mergeFrom((T)generatedMessageLite, input, offset, offset + length, new ArrayDecoders.Registers(extensionRegistry));
/*      */       
/* 1562 */       schema.makeImmutable((T)generatedMessageLite);
/* 1563 */       if (generatedMessageLite.memoizedHashCode != 0) {
/* 1564 */         throw new RuntimeException();
/*      */       }
/* 1566 */     } catch (IOException e) {
/* 1567 */       if (e.getCause() instanceof InvalidProtocolBufferException) {
/* 1568 */         throw (InvalidProtocolBufferException)e.getCause();
/*      */       }
/* 1570 */       throw (new InvalidProtocolBufferException(e.getMessage())).setUnfinishedMessage(generatedMessageLite);
/* 1571 */     } catch (IndexOutOfBoundsException e) {
/* 1572 */       throw InvalidProtocolBufferException.truncatedMessage().setUnfinishedMessage(generatedMessageLite);
/*      */     } 
/* 1574 */     return (T)generatedMessageLite;
/*      */   }
/*      */ 
/*      */   
/*      */   protected static <T extends GeneratedMessageLite<T, ?>> T parsePartialFrom(T defaultInstance, CodedInputStream input) throws InvalidProtocolBufferException {
/* 1579 */     return parsePartialFrom(defaultInstance, input, ExtensionRegistryLite.getEmptyRegistry());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static <T extends GeneratedMessageLite<T, ?>> T checkMessageInitialized(T message) throws InvalidProtocolBufferException {
/* 1590 */     if (message != null && !message.isInitialized()) {
/* 1591 */       throw message
/* 1592 */         .newUninitializedMessageException()
/* 1593 */         .asInvalidProtocolBufferException()
/* 1594 */         .setUnfinishedMessage(message);
/*      */     }
/* 1596 */     return message;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T defaultInstance, ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 1603 */     return checkMessageInitialized(
/* 1604 */         parseFrom(defaultInstance, CodedInputStream.newInstance(data), extensionRegistry));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T defaultInstance, ByteBuffer data) throws InvalidProtocolBufferException {
/* 1610 */     return parseFrom(defaultInstance, data, ExtensionRegistryLite.getEmptyRegistry());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T defaultInstance, ByteString data) throws InvalidProtocolBufferException {
/* 1616 */     return checkMessageInitialized(
/* 1617 */         parseFrom(defaultInstance, data, ExtensionRegistryLite.getEmptyRegistry()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T defaultInstance, ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 1624 */     return checkMessageInitialized(parsePartialFrom(defaultInstance, data, extensionRegistry));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static <T extends GeneratedMessageLite<T, ?>> T parsePartialFrom(T defaultInstance, ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*      */     try {
/* 1634 */       CodedInputStream input = data.newCodedInput();
/* 1635 */       T message = parsePartialFrom(defaultInstance, input, extensionRegistry);
/*      */       try {
/* 1637 */         input.checkLastTagWas(0);
/* 1638 */       } catch (InvalidProtocolBufferException e) {
/* 1639 */         throw e.setUnfinishedMessage(message);
/*      */       } 
/* 1641 */       return message;
/* 1642 */     } catch (InvalidProtocolBufferException e) {
/* 1643 */       throw e;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static <T extends GeneratedMessageLite<T, ?>> T parsePartialFrom(T defaultInstance, byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 1652 */     return checkMessageInitialized(
/* 1653 */         parsePartialFrom(defaultInstance, data, 0, data.length, extensionRegistry));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T defaultInstance, byte[] data) throws InvalidProtocolBufferException {
/* 1659 */     return checkMessageInitialized(parsePartialFrom(defaultInstance, data, 0, data.length, 
/* 1660 */           ExtensionRegistryLite.getEmptyRegistry()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T defaultInstance, byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 1667 */     return checkMessageInitialized(
/* 1668 */         parsePartialFrom(defaultInstance, data, 0, data.length, extensionRegistry));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T defaultInstance, InputStream input) throws InvalidProtocolBufferException {
/* 1674 */     return checkMessageInitialized(
/* 1675 */         parsePartialFrom(defaultInstance, 
/*      */           
/* 1677 */           CodedInputStream.newInstance(input), 
/* 1678 */           ExtensionRegistryLite.getEmptyRegistry()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T defaultInstance, InputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 1685 */     return checkMessageInitialized(
/* 1686 */         parsePartialFrom(defaultInstance, CodedInputStream.newInstance(input), extensionRegistry));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T defaultInstance, CodedInputStream input) throws InvalidProtocolBufferException {
/* 1692 */     return parseFrom(defaultInstance, input, ExtensionRegistryLite.getEmptyRegistry());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static <T extends GeneratedMessageLite<T, ?>> T parseFrom(T defaultInstance, CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 1699 */     return checkMessageInitialized(parsePartialFrom(defaultInstance, input, extensionRegistry));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected static <T extends GeneratedMessageLite<T, ?>> T parseDelimitedFrom(T defaultInstance, InputStream input) throws InvalidProtocolBufferException {
/* 1705 */     return checkMessageInitialized(
/* 1706 */         parsePartialDelimitedFrom(defaultInstance, input, 
/* 1707 */           ExtensionRegistryLite.getEmptyRegistry()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static <T extends GeneratedMessageLite<T, ?>> T parseDelimitedFrom(T defaultInstance, InputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 1714 */     return checkMessageInitialized(
/* 1715 */         parsePartialDelimitedFrom(defaultInstance, input, extensionRegistry));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static <T extends GeneratedMessageLite<T, ?>> T parsePartialDelimitedFrom(T defaultInstance, InputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*      */     int size;
/*      */     try {
/* 1723 */       int firstByte = input.read();
/* 1724 */       if (firstByte == -1) {
/* 1725 */         return null;
/*      */       }
/* 1727 */       size = CodedInputStream.readRawVarint32(firstByte, input);
/* 1728 */     } catch (IOException e) {
/* 1729 */       throw new InvalidProtocolBufferException(e.getMessage());
/*      */     } 
/* 1731 */     InputStream limitedInput = new AbstractMessageLite.Builder.LimitedInputStream(input, size);
/* 1732 */     CodedInputStream codedInput = CodedInputStream.newInstance(limitedInput);
/* 1733 */     T message = parsePartialFrom(defaultInstance, codedInput, extensionRegistry);
/*      */     try {
/* 1735 */       codedInput.checkLastTagWas(0);
/* 1736 */     } catch (InvalidProtocolBufferException e) {
/* 1737 */       throw e.setUnfinishedMessage(message);
/*      */     } 
/* 1739 */     return message;
/*      */   }
/*      */   
/*      */   protected abstract Object dynamicMethod(MethodToInvoke paramMethodToInvoke, Object paramObject1, Object paramObject2);
/*      */   
/*      */   public static interface ExtendableMessageOrBuilder<MessageType extends ExtendableMessage<MessageType, BuilderType>, BuilderType extends ExtendableBuilder<MessageType, BuilderType>> extends MessageLiteOrBuilder {
/*      */     <Type> boolean hasExtension(ExtensionLite<MessageType, Type> param1ExtensionLite);
/*      */     
/*      */     <Type> int getExtensionCount(ExtensionLite<MessageType, List<Type>> param1ExtensionLite);
/*      */     
/*      */     <Type> Type getExtension(ExtensionLite<MessageType, Type> param1ExtensionLite);
/*      */     
/*      */     <Type> Type getExtension(ExtensionLite<MessageType, List<Type>> param1ExtensionLite, int param1Int);
/*      */   }
/*      */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\GeneratedMessageLite.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */