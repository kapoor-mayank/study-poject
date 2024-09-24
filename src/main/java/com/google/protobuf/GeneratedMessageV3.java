/*      */ package com.google.protobuf;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.ObjectStreamException;
/*      */ import java.io.Serializable;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collections;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.TreeMap;
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
/*      */ public abstract class GeneratedMessageV3
/*      */   extends AbstractMessage
/*      */   implements Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 1L;
/*      */   protected static boolean alwaysUseFieldBuilders = false;
/*      */   protected UnknownFieldSet unknownFields;
/*      */   
/*      */   protected GeneratedMessageV3() {
/*   94 */     this.unknownFields = UnknownFieldSet.getDefaultInstance();
/*      */   }
/*      */   
/*      */   protected GeneratedMessageV3(Builder<?> builder) {
/*   98 */     this.unknownFields = builder.getUnknownFields();
/*      */   }
/*      */ 
/*      */   
/*      */   public Parser<? extends GeneratedMessageV3> getParserForType() {
/*  103 */     throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static void enableAlwaysUseFieldBuildersForTesting() {
/*  111 */     setAlwaysUseFieldBuildersForTesting(true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static void setAlwaysUseFieldBuildersForTesting(boolean useBuilders) {
/*  121 */     alwaysUseFieldBuilders = useBuilders;
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
/*      */   public Descriptors.Descriptor getDescriptorForType() {
/*  133 */     return (internalGetFieldAccessorTable()).descriptor;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void mergeFromAndMakeImmutableInternal(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  140 */     Schema<GeneratedMessageV3> schema = Protobuf.getInstance().schemaFor(this);
/*      */     try {
/*  142 */       schema.mergeFrom(this, CodedInputStreamReader.forCodedInput(input), extensionRegistry);
/*  143 */     } catch (InvalidProtocolBufferException e) {
/*  144 */       throw e.setUnfinishedMessage(this);
/*  145 */     } catch (IOException e) {
/*  146 */       throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(this);
/*      */     } 
/*  148 */     schema.makeImmutable(this);
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
/*      */   private Map<Descriptors.FieldDescriptor, Object> getAllFieldsMutable(boolean getBytesForString) {
/*  160 */     TreeMap<Descriptors.FieldDescriptor, Object> result = new TreeMap<>();
/*      */     
/*  162 */     Descriptors.Descriptor descriptor = (internalGetFieldAccessorTable()).descriptor;
/*  163 */     List<Descriptors.FieldDescriptor> fields = descriptor.getFields();
/*      */     
/*  165 */     for (int i = 0; i < fields.size(); i++) {
/*  166 */       Descriptors.FieldDescriptor field = fields.get(i);
/*  167 */       Descriptors.OneofDescriptor oneofDescriptor = field.getContainingOneof();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  173 */       if (oneofDescriptor != null) {
/*      */         
/*  175 */         i += oneofDescriptor.getFieldCount() - 1;
/*  176 */         if (!hasOneof(oneofDescriptor)) {
/*      */           continue;
/*      */         }
/*      */ 
/*      */         
/*  181 */         field = getOneofFieldDescriptor(oneofDescriptor);
/*      */       } else {
/*      */         
/*  184 */         if (field.isRepeated()) {
/*  185 */           List<?> value = (List)getField(field);
/*  186 */           if (!value.isEmpty()) {
/*  187 */             result.put(field, value);
/*      */           }
/*      */           continue;
/*      */         } 
/*  191 */         if (!hasField(field)) {
/*      */           continue;
/*      */         }
/*      */       } 
/*      */       
/*  196 */       if (getBytesForString && field.getJavaType() == Descriptors.FieldDescriptor.JavaType.STRING) {
/*  197 */         result.put(field, getFieldRaw(field));
/*      */       } else {
/*  199 */         result.put(field, getField(field));
/*      */       }  continue;
/*      */     } 
/*  202 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isInitialized() {
/*  207 */     for (Descriptors.FieldDescriptor field : getDescriptorForType().getFields()) {
/*      */       
/*  209 */       if (field.isRequired() && 
/*  210 */         !hasField(field)) {
/*  211 */         return false;
/*      */       }
/*      */ 
/*      */       
/*  215 */       if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
/*  216 */         if (field.isRepeated()) {
/*      */           
/*  218 */           List<Message> messageList = (List<Message>)getField(field);
/*  219 */           for (Message element : messageList) {
/*  220 */             if (!element.isInitialized())
/*  221 */               return false; 
/*      */           } 
/*      */           continue;
/*      */         } 
/*  225 */         if (hasField(field) && !((Message)getField(field)).isInitialized()) {
/*  226 */           return false;
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  232 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
/*  237 */     return Collections.unmodifiableMap(
/*  238 */         getAllFieldsMutable(false));
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
/*      */   Map<Descriptors.FieldDescriptor, Object> getAllFieldsRaw() {
/*  252 */     return Collections.unmodifiableMap(
/*  253 */         getAllFieldsMutable(true));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasOneof(Descriptors.OneofDescriptor oneof) {
/*  258 */     return internalGetFieldAccessorTable().getOneof(oneof).has(this);
/*      */   }
/*      */ 
/*      */   
/*      */   public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneof) {
/*  263 */     return internalGetFieldAccessorTable().getOneof(oneof).get(this);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasField(Descriptors.FieldDescriptor field) {
/*  268 */     return internalGetFieldAccessorTable().getField(field).has(this);
/*      */   }
/*      */ 
/*      */   
/*      */   public Object getField(Descriptors.FieldDescriptor field) {
/*  273 */     return internalGetFieldAccessorTable().getField(field).get(this);
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
/*      */   Object getFieldRaw(Descriptors.FieldDescriptor field) {
/*  285 */     return internalGetFieldAccessorTable().getField(field).getRaw(this);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getRepeatedFieldCount(Descriptors.FieldDescriptor field) {
/*  290 */     return internalGetFieldAccessorTable().getField(field)
/*  291 */       .getRepeatedCount(this);
/*      */   }
/*      */ 
/*      */   
/*      */   public Object getRepeatedField(Descriptors.FieldDescriptor field, int index) {
/*  296 */     return internalGetFieldAccessorTable().getField(field)
/*  297 */       .getRepeated(this, index);
/*      */   }
/*      */ 
/*      */   
/*      */   public UnknownFieldSet getUnknownFields() {
/*  302 */     throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
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
/*      */   protected boolean parseUnknownField(CodedInputStream input, UnknownFieldSet.Builder unknownFields, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
/*  317 */     if (input.shouldDiscardUnknownFields()) {
/*  318 */       return input.skipField(tag);
/*      */     }
/*  320 */     return unknownFields.mergeFieldFrom(tag, input);
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
/*      */   protected boolean parseUnknownFieldProto3(CodedInputStream input, UnknownFieldSet.Builder unknownFields, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
/*  333 */     return parseUnknownField(input, unknownFields, extensionRegistry, tag);
/*      */   }
/*      */ 
/*      */   
/*      */   protected static <M extends Message> M parseWithIOException(Parser<M> parser, InputStream input) throws IOException {
/*      */     try {
/*  339 */       return parser.parseFrom(input);
/*  340 */     } catch (InvalidProtocolBufferException e) {
/*  341 */       throw e.unwrapIOException();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected static <M extends Message> M parseWithIOException(Parser<M> parser, InputStream input, ExtensionRegistryLite extensions) throws IOException {
/*      */     try {
/*  348 */       return parser.parseFrom(input, extensions);
/*  349 */     } catch (InvalidProtocolBufferException e) {
/*  350 */       throw e.unwrapIOException();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected static <M extends Message> M parseWithIOException(Parser<M> parser, CodedInputStream input) throws IOException {
/*      */     try {
/*  357 */       return parser.parseFrom(input);
/*  358 */     } catch (InvalidProtocolBufferException e) {
/*  359 */       throw e.unwrapIOException();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected static <M extends Message> M parseWithIOException(Parser<M> parser, CodedInputStream input, ExtensionRegistryLite extensions) throws IOException {
/*      */     try {
/*  366 */       return parser.parseFrom(input, extensions);
/*  367 */     } catch (InvalidProtocolBufferException e) {
/*  368 */       throw e.unwrapIOException();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected static <M extends Message> M parseDelimitedWithIOException(Parser<M> parser, InputStream input) throws IOException {
/*      */     try {
/*  375 */       return parser.parseDelimitedFrom(input);
/*  376 */     } catch (InvalidProtocolBufferException e) {
/*  377 */       throw e.unwrapIOException();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected static <M extends Message> M parseDelimitedWithIOException(Parser<M> parser, InputStream input, ExtensionRegistryLite extensions) throws IOException {
/*      */     try {
/*  384 */       return parser.parseDelimitedFrom(input, extensions);
/*  385 */     } catch (InvalidProtocolBufferException e) {
/*  386 */       throw e.unwrapIOException();
/*      */     } 
/*      */   }
/*      */   
/*      */   protected static boolean canUseUnsafe() {
/*  391 */     return (UnsafeUtil.hasUnsafeArrayOperations() && UnsafeUtil.hasUnsafeByteBufferOperations());
/*      */   }
/*      */   
/*      */   protected static Internal.IntList emptyIntList() {
/*  395 */     return IntArrayList.emptyList();
/*      */   }
/*      */   
/*      */   protected static Internal.IntList newIntList() {
/*  399 */     return new IntArrayList();
/*      */   }
/*      */   
/*      */   protected static Internal.IntList mutableCopy(Internal.IntList list) {
/*  403 */     int size = list.size();
/*  404 */     return list.mutableCopyWithCapacity((size == 0) ? 10 : (size * 2));
/*      */   }
/*      */ 
/*      */   
/*      */   protected static Internal.LongList emptyLongList() {
/*  409 */     return LongArrayList.emptyList();
/*      */   }
/*      */   
/*      */   protected static Internal.LongList newLongList() {
/*  413 */     return new LongArrayList();
/*      */   }
/*      */   
/*      */   protected static Internal.LongList mutableCopy(Internal.LongList list) {
/*  417 */     int size = list.size();
/*  418 */     return list.mutableCopyWithCapacity((size == 0) ? 10 : (size * 2));
/*      */   }
/*      */ 
/*      */   
/*      */   protected static Internal.FloatList emptyFloatList() {
/*  423 */     return FloatArrayList.emptyList();
/*      */   }
/*      */   
/*      */   protected static Internal.FloatList newFloatList() {
/*  427 */     return new FloatArrayList();
/*      */   }
/*      */   
/*      */   protected static Internal.FloatList mutableCopy(Internal.FloatList list) {
/*  431 */     int size = list.size();
/*  432 */     return list.mutableCopyWithCapacity((size == 0) ? 10 : (size * 2));
/*      */   }
/*      */ 
/*      */   
/*      */   protected static Internal.DoubleList emptyDoubleList() {
/*  437 */     return DoubleArrayList.emptyList();
/*      */   }
/*      */   
/*      */   protected static Internal.DoubleList newDoubleList() {
/*  441 */     return new DoubleArrayList();
/*      */   }
/*      */   
/*      */   protected static Internal.DoubleList mutableCopy(Internal.DoubleList list) {
/*  445 */     int size = list.size();
/*  446 */     return list.mutableCopyWithCapacity((size == 0) ? 10 : (size * 2));
/*      */   }
/*      */ 
/*      */   
/*      */   protected static Internal.BooleanList emptyBooleanList() {
/*  451 */     return BooleanArrayList.emptyList();
/*      */   }
/*      */   
/*      */   protected static Internal.BooleanList newBooleanList() {
/*  455 */     return new BooleanArrayList();
/*      */   }
/*      */   
/*      */   protected static Internal.BooleanList mutableCopy(Internal.BooleanList list) {
/*  459 */     int size = list.size();
/*  460 */     return list.mutableCopyWithCapacity((size == 0) ? 10 : (size * 2));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeTo(CodedOutputStream output) throws IOException {
/*  467 */     MessageReflection.writeMessageTo(this, getAllFieldsRaw(), output, false);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getSerializedSize() {
/*  472 */     int size = this.memoizedSize;
/*  473 */     if (size != -1) {
/*  474 */       return size;
/*      */     }
/*      */     
/*  477 */     this.memoizedSize = MessageReflection.getSerializedSize(this, 
/*  478 */         getAllFieldsRaw());
/*  479 */     return this.memoizedSize;
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
/*      */   protected static final class UnusedPrivateParameter
/*      */   {
/*  493 */     static final UnusedPrivateParameter INSTANCE = new UnusedPrivateParameter();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Object newInstance(UnusedPrivateParameter unused) {
/*  504 */     throw new UnsupportedOperationException("This method must be overridden by the subclass.");
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
/*      */   protected void makeExtensionsImmutable() {}
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
/*      */   protected Message.Builder newBuilderForType(final AbstractMessage.BuilderParent parent) {
/*  529 */     return newBuilderForType(new BuilderParent()
/*      */         {
/*      */           public void markDirty() {
/*  532 */             parent.markDirty();
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static abstract class Builder<BuilderType extends Builder<BuilderType>>
/*      */     extends AbstractMessage.Builder<BuilderType>
/*      */   {
/*      */     private BuilderParent builderParent;
/*      */ 
/*      */     
/*      */     private BuilderParentImpl meAsParent;
/*      */ 
/*      */     
/*      */     private boolean isClean;
/*      */ 
/*      */     
/*  551 */     private UnknownFieldSet unknownFields = UnknownFieldSet.getDefaultInstance();
/*      */     
/*      */     protected Builder() {
/*  554 */       this((BuilderParent)null);
/*      */     }
/*      */     
/*      */     protected Builder(BuilderParent builderParent) {
/*  558 */       this.builderParent = builderParent;
/*      */     }
/*      */ 
/*      */     
/*      */     void dispose() {
/*  563 */       this.builderParent = null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void onBuilt() {
/*  570 */       if (this.builderParent != null) {
/*  571 */         markClean();
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void markClean() {
/*  581 */       this.isClean = true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean isClean() {
/*  590 */       return this.isClean;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public BuilderType clone() {
/*  596 */       Builder builder = (Builder)getDefaultInstanceForType().newBuilderForType();
/*  597 */       builder.mergeFrom(buildPartial());
/*  598 */       return (BuilderType)builder;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public BuilderType clear() {
/*  607 */       this.unknownFields = UnknownFieldSet.getDefaultInstance();
/*  608 */       onChanged();
/*  609 */       return (BuilderType)this;
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
/*      */     public Descriptors.Descriptor getDescriptorForType() {
/*  621 */       return (internalGetFieldAccessorTable()).descriptor;
/*      */     }
/*      */ 
/*      */     
/*      */     public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
/*  626 */       return Collections.unmodifiableMap(getAllFieldsMutable());
/*      */     }
/*      */ 
/*      */     
/*      */     private Map<Descriptors.FieldDescriptor, Object> getAllFieldsMutable() {
/*  631 */       TreeMap<Descriptors.FieldDescriptor, Object> result = new TreeMap<>();
/*      */       
/*  633 */       Descriptors.Descriptor descriptor = (internalGetFieldAccessorTable()).descriptor;
/*  634 */       List<Descriptors.FieldDescriptor> fields = descriptor.getFields();
/*      */       
/*  636 */       for (int i = 0; i < fields.size(); i++) {
/*  637 */         Descriptors.FieldDescriptor field = fields.get(i);
/*  638 */         Descriptors.OneofDescriptor oneofDescriptor = field.getContainingOneof();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  644 */         if (oneofDescriptor != null) {
/*      */           
/*  646 */           i += oneofDescriptor.getFieldCount() - 1;
/*  647 */           if (!hasOneof(oneofDescriptor)) {
/*      */             continue;
/*      */           }
/*      */ 
/*      */           
/*  652 */           field = getOneofFieldDescriptor(oneofDescriptor);
/*      */         } else {
/*      */           
/*  655 */           if (field.isRepeated()) {
/*  656 */             List<?> value = (List)getField(field);
/*  657 */             if (!value.isEmpty()) {
/*  658 */               result.put(field, value);
/*      */             }
/*      */             continue;
/*      */           } 
/*  662 */           if (!hasField(field)) {
/*      */             continue;
/*      */           }
/*      */         } 
/*      */         
/*  667 */         result.put(field, getField(field)); continue;
/*      */       } 
/*  669 */       return result;
/*      */     }
/*      */ 
/*      */     
/*      */     public Message.Builder newBuilderForField(Descriptors.FieldDescriptor field) {
/*  674 */       return internalGetFieldAccessorTable().getField(field).newBuilder();
/*      */     }
/*      */ 
/*      */     
/*      */     public Message.Builder getFieldBuilder(Descriptors.FieldDescriptor field) {
/*  679 */       return internalGetFieldAccessorTable().getField(field).getBuilder(this);
/*      */     }
/*      */ 
/*      */     
/*      */     public Message.Builder getRepeatedFieldBuilder(Descriptors.FieldDescriptor field, int index) {
/*  684 */       return internalGetFieldAccessorTable().getField(field).getRepeatedBuilder(this, index);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean hasOneof(Descriptors.OneofDescriptor oneof) {
/*  690 */       return internalGetFieldAccessorTable().getOneof(oneof).has(this);
/*      */     }
/*      */ 
/*      */     
/*      */     public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneof) {
/*  695 */       return internalGetFieldAccessorTable().getOneof(oneof).get(this);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean hasField(Descriptors.FieldDescriptor field) {
/*  700 */       return internalGetFieldAccessorTable().getField(field).has(this);
/*      */     }
/*      */ 
/*      */     
/*      */     public Object getField(Descriptors.FieldDescriptor field) {
/*  705 */       Object object = internalGetFieldAccessorTable().getField(field).get(this);
/*  706 */       if (field.isRepeated())
/*      */       {
/*      */         
/*  709 */         return Collections.unmodifiableList((List)object);
/*      */       }
/*  711 */       return object;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public BuilderType setField(Descriptors.FieldDescriptor field, Object value) {
/*  717 */       internalGetFieldAccessorTable().getField(field).set(this, value);
/*  718 */       return (BuilderType)this;
/*      */     }
/*      */ 
/*      */     
/*      */     public BuilderType clearField(Descriptors.FieldDescriptor field) {
/*  723 */       internalGetFieldAccessorTable().getField(field).clear(this);
/*  724 */       return (BuilderType)this;
/*      */     }
/*      */ 
/*      */     
/*      */     public BuilderType clearOneof(Descriptors.OneofDescriptor oneof) {
/*  729 */       internalGetFieldAccessorTable().getOneof(oneof).clear(this);
/*  730 */       return (BuilderType)this;
/*      */     }
/*      */ 
/*      */     
/*      */     public int getRepeatedFieldCount(Descriptors.FieldDescriptor field) {
/*  735 */       return internalGetFieldAccessorTable().getField(field)
/*  736 */         .getRepeatedCount(this);
/*      */     }
/*      */ 
/*      */     
/*      */     public Object getRepeatedField(Descriptors.FieldDescriptor field, int index) {
/*  741 */       return internalGetFieldAccessorTable().getField(field)
/*  742 */         .getRepeated(this, index);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public BuilderType setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
/*  748 */       internalGetFieldAccessorTable().getField(field)
/*  749 */         .setRepeated(this, index, value);
/*  750 */       return (BuilderType)this;
/*      */     }
/*      */ 
/*      */     
/*      */     public BuilderType addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
/*  755 */       internalGetFieldAccessorTable().getField(field).addRepeated(this, value);
/*  756 */       return (BuilderType)this;
/*      */     }
/*      */     
/*      */     private BuilderType setUnknownFieldsInternal(UnknownFieldSet unknownFields) {
/*  760 */       this.unknownFields = unknownFields;
/*  761 */       onChanged();
/*  762 */       return (BuilderType)this;
/*      */     }
/*      */ 
/*      */     
/*      */     public BuilderType setUnknownFields(UnknownFieldSet unknownFields) {
/*  767 */       return setUnknownFieldsInternal(unknownFields);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected BuilderType setUnknownFieldsProto3(UnknownFieldSet unknownFields) {
/*  775 */       return setUnknownFieldsInternal(unknownFields);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public BuilderType mergeUnknownFields(UnknownFieldSet unknownFields) {
/*  781 */       return setUnknownFields(
/*  782 */           UnknownFieldSet.newBuilder(this.unknownFields)
/*  783 */           .mergeFrom(unknownFields)
/*  784 */           .build());
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isInitialized() {
/*  790 */       for (Descriptors.FieldDescriptor field : getDescriptorForType().getFields()) {
/*      */         
/*  792 */         if (field.isRequired() && 
/*  793 */           !hasField(field)) {
/*  794 */           return false;
/*      */         }
/*      */ 
/*      */         
/*  798 */         if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
/*  799 */           if (field.isRepeated()) {
/*      */             
/*  801 */             List<Message> messageList = (List<Message>)getField(field);
/*  802 */             for (Message element : messageList) {
/*  803 */               if (!element.isInitialized())
/*  804 */                 return false; 
/*      */             } 
/*      */             continue;
/*      */           } 
/*  808 */           if (hasField(field) && 
/*  809 */             !((Message)getField(field)).isInitialized()) {
/*  810 */             return false;
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/*  815 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public final UnknownFieldSet getUnknownFields() {
/*  820 */       return this.unknownFields;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private class BuilderParentImpl
/*      */       implements BuilderParent
/*      */     {
/*      */       private BuilderParentImpl() {}
/*      */ 
/*      */       
/*      */       public void markDirty() {
/*  832 */         Builder.this.onChanged();
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected BuilderParent getParentForChildren() {
/*  841 */       if (this.meAsParent == null) {
/*  842 */         this.meAsParent = new BuilderParentImpl();
/*      */       }
/*  844 */       return this.meAsParent;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected final void onChanged() {
/*  852 */       if (this.isClean && this.builderParent != null) {
/*  853 */         this.builderParent.markDirty();
/*      */ 
/*      */         
/*  856 */         this.isClean = false;
/*      */       } 
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
/*      */     protected MapField internalGetMapField(int fieldNumber) {
/*  875 */       throw new RuntimeException("No map fields found in " + 
/*  876 */           getClass().getName());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected MapField internalGetMutableMapField(int fieldNumber) {
/*  884 */       throw new RuntimeException("No map fields found in " + 
/*  885 */           getClass().getName());
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
/*      */     protected abstract FieldAccessorTable internalGetFieldAccessorTable();
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
/*      */   public static abstract class ExtendableMessage<MessageType extends ExtendableMessage>
/*      */     extends GeneratedMessageV3
/*      */     implements ExtendableMessageOrBuilder<MessageType>
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
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
/*      */     private final FieldSet<Descriptors.FieldDescriptor> extensions;
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
/*      */     protected ExtendableMessage() {
/*  988 */       this.extensions = FieldSet.newFieldSet();
/*      */     }
/*      */ 
/*      */     
/*      */     protected ExtendableMessage(ExtendableBuilder<MessageType, ?> builder) {
/*  993 */       super(builder);
/*  994 */       this.extensions = builder.buildExtensions();
/*      */     }
/*      */ 
/*      */     
/*      */     private void verifyExtensionContainingType(Extension<MessageType, ?> extension) {
/*  999 */       if (extension.getDescriptor().getContainingType() != 
/* 1000 */         getDescriptorForType())
/*      */       {
/* 1002 */         throw new IllegalArgumentException("Extension is for type \"" + extension
/*      */             
/* 1004 */             .getDescriptor().getContainingType().getFullName() + "\" which does not match message type \"" + 
/*      */             
/* 1006 */             getDescriptorForType().getFullName() + "\".");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> boolean hasExtension(ExtensionLite<MessageType, Type> extensionLite) {
/* 1014 */       Extension<MessageType, Type> extension = GeneratedMessageV3.checkNotLite(extensionLite);
/*      */       
/* 1016 */       verifyExtensionContainingType(extension);
/* 1017 */       return this.extensions.hasField(extension.getDescriptor());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> int getExtensionCount(ExtensionLite<MessageType, List<Type>> extensionLite) {
/* 1025 */       Extension<MessageType, List<Type>> extension = (Extension)GeneratedMessageV3.checkNotLite((ExtensionLite)extensionLite);
/*      */       
/* 1027 */       verifyExtensionContainingType(extension);
/* 1028 */       Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
/* 1029 */       return this.extensions.getRepeatedFieldCount(descriptor);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> Type getExtension(ExtensionLite<MessageType, Type> extensionLite) {
/* 1036 */       Extension<MessageType, Type> extension = GeneratedMessageV3.checkNotLite(extensionLite);
/*      */       
/* 1038 */       verifyExtensionContainingType(extension);
/* 1039 */       Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
/* 1040 */       Object value = this.extensions.getField(descriptor);
/* 1041 */       if (value == null) {
/* 1042 */         if (descriptor.isRepeated())
/* 1043 */           return (Type)Collections.emptyList(); 
/* 1044 */         if (descriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
/*      */         {
/* 1046 */           return (Type)extension.getMessageDefaultInstance();
/*      */         }
/* 1048 */         return (Type)extension.fromReflectionType(descriptor
/* 1049 */             .getDefaultValue());
/*      */       } 
/*      */       
/* 1052 */       return (Type)extension.fromReflectionType(value);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> Type getExtension(ExtensionLite<MessageType, List<Type>> extensionLite, int index) {
/* 1061 */       Extension<MessageType, List<Type>> extension = (Extension)GeneratedMessageV3.checkNotLite((ExtensionLite)extensionLite);
/*      */       
/* 1063 */       verifyExtensionContainingType(extension);
/* 1064 */       Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
/* 1065 */       return (Type)extension.singularFromReflectionType(this.extensions
/* 1066 */           .getRepeatedField(descriptor, index));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> boolean hasExtension(Extension<MessageType, Type> extension) {
/* 1072 */       return hasExtension(extension);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> boolean hasExtension(GeneratedMessage.GeneratedExtension<MessageType, Type> extension) {
/* 1078 */       return hasExtension(extension);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> int getExtensionCount(Extension<MessageType, List<Type>> extension) {
/* 1084 */       return getExtensionCount(extension);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> int getExtensionCount(GeneratedMessage.GeneratedExtension<MessageType, List<Type>> extension) {
/* 1090 */       return getExtensionCount(extension);
/*      */     }
/*      */ 
/*      */     
/*      */     public final <Type> Type getExtension(Extension<MessageType, Type> extension) {
/* 1095 */       return getExtension(extension);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> Type getExtension(GeneratedMessage.GeneratedExtension<MessageType, Type> extension) {
/* 1101 */       return getExtension(extension);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> Type getExtension(Extension<MessageType, List<Type>> extension, int index) {
/* 1107 */       return getExtension(extension, index);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> Type getExtension(GeneratedMessage.GeneratedExtension<MessageType, List<Type>> extension, int index) {
/* 1113 */       return getExtension(extension, index);
/*      */     }
/*      */ 
/*      */     
/*      */     protected boolean extensionsAreInitialized() {
/* 1118 */       return this.extensions.isInitialized();
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isInitialized() {
/* 1123 */       return (super.isInitialized() && extensionsAreInitialized());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean parseUnknownField(CodedInputStream input, UnknownFieldSet.Builder unknownFields, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
/* 1132 */       return MessageReflection.mergeFieldFrom(input, 
/* 1133 */           input.shouldDiscardUnknownFields() ? null : unknownFields, extensionRegistry, 
/* 1134 */           getDescriptorForType(), new MessageReflection.ExtensionAdapter(this.extensions), tag);
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
/*      */     protected boolean parseUnknownFieldProto3(CodedInputStream input, UnknownFieldSet.Builder unknownFields, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
/* 1147 */       return parseUnknownField(input, unknownFields, extensionRegistry, tag);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void makeExtensionsImmutable() {
/* 1156 */       this.extensions.makeImmutable();
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
/*      */     protected class ExtensionWriter
/*      */     {
/* 1169 */       private final Iterator<Map.Entry<Descriptors.FieldDescriptor, Object>> iter = ExtendableMessage.this
/* 1170 */         .extensions.iterator();
/*      */       private Map.Entry<Descriptors.FieldDescriptor, Object> next;
/*      */       private final boolean messageSetWireFormat;
/*      */       
/*      */       private ExtensionWriter(boolean messageSetWireFormat) {
/* 1175 */         if (this.iter.hasNext()) {
/* 1176 */           this.next = this.iter.next();
/*      */         }
/* 1178 */         this.messageSetWireFormat = messageSetWireFormat;
/*      */       }
/*      */ 
/*      */       
/*      */       public void writeUntil(int end, CodedOutputStream output) throws IOException {
/* 1183 */         while (this.next != null && ((Descriptors.FieldDescriptor)this.next.getKey()).getNumber() < end) {
/* 1184 */           Descriptors.FieldDescriptor descriptor = this.next.getKey();
/* 1185 */           if (this.messageSetWireFormat && descriptor.getLiteJavaType() == WireFormat.JavaType.MESSAGE && 
/*      */             
/* 1187 */             !descriptor.isRepeated()) {
/* 1188 */             if (this.next instanceof LazyField.LazyEntry) {
/* 1189 */               output.writeRawMessageSetExtension(descriptor.getNumber(), ((LazyField.LazyEntry)this.next)
/* 1190 */                   .getField().toByteString());
/*      */             } else {
/* 1192 */               output.writeMessageSetExtension(descriptor.getNumber(), (Message)this.next
/* 1193 */                   .getValue());
/*      */ 
/*      */             
/*      */             }
/*      */ 
/*      */           
/*      */           }
/*      */           else {
/*      */ 
/*      */             
/* 1203 */             FieldSet.writeField(descriptor, this.next.getValue(), output);
/*      */           } 
/* 1205 */           if (this.iter.hasNext()) {
/* 1206 */             this.next = this.iter.next(); continue;
/*      */           } 
/* 1208 */           this.next = null;
/*      */         } 
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     protected ExtensionWriter newExtensionWriter() {
/* 1215 */       return new ExtensionWriter(false);
/*      */     }
/*      */     protected ExtensionWriter newMessageSetExtensionWriter() {
/* 1218 */       return new ExtensionWriter(true);
/*      */     }
/*      */ 
/*      */     
/*      */     protected int extensionsSerializedSize() {
/* 1223 */       return this.extensions.getSerializedSize();
/*      */     }
/*      */     protected int extensionsSerializedSizeAsMessageSet() {
/* 1226 */       return this.extensions.getMessageSetSerializedSize();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Map<Descriptors.FieldDescriptor, Object> getExtensionFields() {
/* 1233 */       return this.extensions.getAllFields();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
/* 1239 */       Map<Descriptors.FieldDescriptor, Object> result = getAllFieldsMutable(false);
/* 1240 */       result.putAll(getExtensionFields());
/* 1241 */       return Collections.unmodifiableMap(result);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public Map<Descriptors.FieldDescriptor, Object> getAllFieldsRaw() {
/* 1247 */       Map<Descriptors.FieldDescriptor, Object> result = getAllFieldsMutable(false);
/* 1248 */       result.putAll(getExtensionFields());
/* 1249 */       return Collections.unmodifiableMap(result);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean hasField(Descriptors.FieldDescriptor field) {
/* 1254 */       if (field.isExtension()) {
/* 1255 */         verifyContainingType(field);
/* 1256 */         return this.extensions.hasField(field);
/*      */       } 
/* 1258 */       return super.hasField(field);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public Object getField(Descriptors.FieldDescriptor field) {
/* 1264 */       if (field.isExtension()) {
/* 1265 */         verifyContainingType(field);
/* 1266 */         Object value = this.extensions.getField(field);
/* 1267 */         if (value == null) {
/* 1268 */           if (field.isRepeated())
/* 1269 */             return Collections.emptyList(); 
/* 1270 */           if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
/*      */           {
/*      */             
/* 1273 */             return DynamicMessage.getDefaultInstance(field.getMessageType());
/*      */           }
/* 1275 */           return field.getDefaultValue();
/*      */         } 
/*      */         
/* 1278 */         return value;
/*      */       } 
/*      */       
/* 1281 */       return super.getField(field);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public int getRepeatedFieldCount(Descriptors.FieldDescriptor field) {
/* 1287 */       if (field.isExtension()) {
/* 1288 */         verifyContainingType(field);
/* 1289 */         return this.extensions.getRepeatedFieldCount(field);
/*      */       } 
/* 1291 */       return super.getRepeatedFieldCount(field);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Object getRepeatedField(Descriptors.FieldDescriptor field, int index) {
/* 1298 */       if (field.isExtension()) {
/* 1299 */         verifyContainingType(field);
/* 1300 */         return this.extensions.getRepeatedField(field, index);
/*      */       } 
/* 1302 */       return super.getRepeatedField(field, index);
/*      */     }
/*      */ 
/*      */     
/*      */     private void verifyContainingType(Descriptors.FieldDescriptor field) {
/* 1307 */       if (field.getContainingType() != getDescriptorForType()) {
/* 1308 */         throw new IllegalArgumentException("FieldDescriptor does not match message type.");
/*      */       }
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
/*      */   public static abstract class ExtendableBuilder<MessageType extends ExtendableMessage, BuilderType extends ExtendableBuilder<MessageType, BuilderType>>
/*      */     extends Builder<BuilderType>
/*      */     implements ExtendableMessageOrBuilder<MessageType>
/*      */   {
/*      */     private FieldSet.Builder<Descriptors.FieldDescriptor> extensions;
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
/*      */     protected ExtendableBuilder() {}
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
/*      */     protected ExtendableBuilder(BuilderParent parent) {
/* 1364 */       super(parent);
/*      */     }
/*      */ 
/*      */     
/*      */     void internalSetExtensionSet(FieldSet<Descriptors.FieldDescriptor> extensions) {
/* 1369 */       this.extensions = FieldSet.Builder.fromFieldSet(extensions);
/*      */     }
/*      */ 
/*      */     
/*      */     public BuilderType clear() {
/* 1374 */       this.extensions = null;
/* 1375 */       return super.clear();
/*      */     }
/*      */     
/*      */     private void ensureExtensionsIsMutable() {
/* 1379 */       if (this.extensions == null) {
/* 1380 */         this.extensions = FieldSet.newBuilder();
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     private void verifyExtensionContainingType(Extension<MessageType, ?> extension) {
/* 1386 */       if (extension.getDescriptor().getContainingType() != 
/* 1387 */         getDescriptorForType())
/*      */       {
/* 1389 */         throw new IllegalArgumentException("Extension is for type \"" + extension
/*      */             
/* 1391 */             .getDescriptor().getContainingType().getFullName() + "\" which does not match message type \"" + 
/*      */             
/* 1393 */             getDescriptorForType().getFullName() + "\".");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> boolean hasExtension(ExtensionLite<MessageType, Type> extensionLite) {
/* 1400 */       Extension<MessageType, Type> extension = GeneratedMessageV3.checkNotLite(extensionLite);
/*      */       
/* 1402 */       verifyExtensionContainingType(extension);
/* 1403 */       return (this.extensions == null) ? false : this.extensions.hasField(extension.getDescriptor());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> int getExtensionCount(ExtensionLite<MessageType, List<Type>> extensionLite) {
/* 1410 */       Extension<MessageType, List<Type>> extension = (Extension)GeneratedMessageV3.checkNotLite((ExtensionLite)extensionLite);
/*      */       
/* 1412 */       verifyExtensionContainingType(extension);
/* 1413 */       Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
/* 1414 */       return (this.extensions == null) ? 0 : this.extensions.getRepeatedFieldCount(descriptor);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> Type getExtension(ExtensionLite<MessageType, Type> extensionLite) {
/* 1420 */       Extension<MessageType, Type> extension = GeneratedMessageV3.checkNotLite(extensionLite);
/*      */       
/* 1422 */       verifyExtensionContainingType(extension);
/* 1423 */       Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
/* 1424 */       Object value = (this.extensions == null) ? null : this.extensions.getField(descriptor);
/* 1425 */       if (value == null) {
/* 1426 */         if (descriptor.isRepeated())
/* 1427 */           return (Type)Collections.emptyList(); 
/* 1428 */         if (descriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
/*      */         {
/* 1430 */           return (Type)extension.getMessageDefaultInstance();
/*      */         }
/* 1432 */         return (Type)extension.fromReflectionType(descriptor
/* 1433 */             .getDefaultValue());
/*      */       } 
/*      */       
/* 1436 */       return (Type)extension.fromReflectionType(value);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> Type getExtension(ExtensionLite<MessageType, List<Type>> extensionLite, int index) {
/* 1444 */       Extension<MessageType, List<Type>> extension = (Extension)GeneratedMessageV3.checkNotLite((ExtensionLite)extensionLite);
/*      */       
/* 1446 */       verifyExtensionContainingType(extension);
/* 1447 */       Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
/* 1448 */       if (this.extensions == null) {
/* 1449 */         throw new IndexOutOfBoundsException();
/*      */       }
/* 1451 */       return (Type)extension
/* 1452 */         .singularFromReflectionType(this.extensions.getRepeatedField(descriptor, index));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> BuilderType setExtension(ExtensionLite<MessageType, Type> extensionLite, Type value) {
/* 1459 */       Extension<MessageType, Type> extension = GeneratedMessageV3.checkNotLite(extensionLite);
/*      */       
/* 1461 */       verifyExtensionContainingType(extension);
/* 1462 */       ensureExtensionsIsMutable();
/* 1463 */       Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
/* 1464 */       this.extensions.setField(descriptor, extension.toReflectionType(value));
/* 1465 */       onChanged();
/* 1466 */       return (BuilderType)this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> BuilderType setExtension(ExtensionLite<MessageType, List<Type>> extensionLite, int index, Type value) {
/* 1473 */       Extension<MessageType, List<Type>> extension = (Extension)GeneratedMessageV3.checkNotLite((ExtensionLite)extensionLite);
/*      */       
/* 1475 */       verifyExtensionContainingType(extension);
/* 1476 */       ensureExtensionsIsMutable();
/* 1477 */       Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
/* 1478 */       this.extensions.setRepeatedField(descriptor, index, extension
/*      */           
/* 1480 */           .singularToReflectionType(value));
/* 1481 */       onChanged();
/* 1482 */       return (BuilderType)this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> BuilderType addExtension(ExtensionLite<MessageType, List<Type>> extensionLite, Type value) {
/* 1489 */       Extension<MessageType, List<Type>> extension = (Extension)GeneratedMessageV3.checkNotLite((ExtensionLite)extensionLite);
/*      */       
/* 1491 */       verifyExtensionContainingType(extension);
/* 1492 */       ensureExtensionsIsMutable();
/* 1493 */       Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
/* 1494 */       this.extensions.addRepeatedField(descriptor, extension
/* 1495 */           .singularToReflectionType(value));
/* 1496 */       onChanged();
/* 1497 */       return (BuilderType)this;
/*      */     }
/*      */ 
/*      */     
/*      */     public final BuilderType clearExtension(ExtensionLite<MessageType, ?> extensionLite) {
/* 1502 */       Extension<MessageType, ?> extension = GeneratedMessageV3.checkNotLite((ExtensionLite)extensionLite);
/*      */       
/* 1504 */       verifyExtensionContainingType(extension);
/* 1505 */       ensureExtensionsIsMutable();
/* 1506 */       this.extensions.clearField(extension.getDescriptor());
/* 1507 */       onChanged();
/* 1508 */       return (BuilderType)this;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> boolean hasExtension(Extension<MessageType, Type> extension) {
/* 1514 */       return hasExtension(extension);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> boolean hasExtension(GeneratedMessage.GeneratedExtension<MessageType, Type> extension) {
/* 1520 */       return hasExtension(extension);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> int getExtensionCount(Extension<MessageType, List<Type>> extension) {
/* 1526 */       return getExtensionCount(extension);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> int getExtensionCount(GeneratedMessage.GeneratedExtension<MessageType, List<Type>> extension) {
/* 1532 */       return getExtensionCount(extension);
/*      */     }
/*      */ 
/*      */     
/*      */     public final <Type> Type getExtension(Extension<MessageType, Type> extension) {
/* 1537 */       return getExtension(extension);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> Type getExtension(GeneratedMessage.GeneratedExtension<MessageType, Type> extension) {
/* 1543 */       return getExtension(extension);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> Type getExtension(Extension<MessageType, List<Type>> extension, int index) {
/* 1549 */       return getExtension(extension, index);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> Type getExtension(GeneratedMessage.GeneratedExtension<MessageType, List<Type>> extension, int index) {
/* 1555 */       return getExtension(extension, index);
/*      */     }
/*      */ 
/*      */     
/*      */     public final <Type> BuilderType setExtension(Extension<MessageType, Type> extension, Type value) {
/* 1560 */       return setExtension(extension, value);
/*      */     }
/*      */ 
/*      */     
/*      */     public <Type> BuilderType setExtension(GeneratedMessage.GeneratedExtension<MessageType, Type> extension, Type value) {
/* 1565 */       return setExtension(extension, value);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public final <Type> BuilderType setExtension(Extension<MessageType, List<Type>> extension, int index, Type value) {
/* 1571 */       return setExtension(extension, index, value);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public <Type> BuilderType setExtension(GeneratedMessage.GeneratedExtension<MessageType, List<Type>> extension, int index, Type value) {
/* 1577 */       return setExtension(extension, index, value);
/*      */     }
/*      */ 
/*      */     
/*      */     public final <Type> BuilderType addExtension(Extension<MessageType, List<Type>> extension, Type value) {
/* 1582 */       return addExtension(extension, value);
/*      */     }
/*      */ 
/*      */     
/*      */     public <Type> BuilderType addExtension(GeneratedMessage.GeneratedExtension<MessageType, List<Type>> extension, Type value) {
/* 1587 */       return addExtension(extension, value);
/*      */     }
/*      */ 
/*      */     
/*      */     public final <Type> BuilderType clearExtension(Extension<MessageType, ?> extension) {
/* 1592 */       return clearExtension(extension);
/*      */     }
/*      */ 
/*      */     
/*      */     public <Type> BuilderType clearExtension(GeneratedMessage.GeneratedExtension<MessageType, ?> extension) {
/* 1597 */       return clearExtension(extension);
/*      */     }
/*      */ 
/*      */     
/*      */     protected boolean extensionsAreInitialized() {
/* 1602 */       return (this.extensions == null) ? true : this.extensions.isInitialized();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private FieldSet<Descriptors.FieldDescriptor> buildExtensions() {
/* 1610 */       return (this.extensions == null) ? 
/* 1611 */         FieldSet.<Descriptors.FieldDescriptor>emptySet() : this.extensions
/* 1612 */         .build();
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isInitialized() {
/* 1617 */       return (super.isInitialized() && extensionsAreInitialized());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
/* 1625 */       Map<Descriptors.FieldDescriptor, Object> result = getAllFieldsMutable();
/* 1626 */       if (this.extensions != null) {
/* 1627 */         result.putAll(this.extensions.getAllFields());
/*      */       }
/* 1629 */       return Collections.unmodifiableMap(result);
/*      */     }
/*      */ 
/*      */     
/*      */     public Object getField(Descriptors.FieldDescriptor field) {
/* 1634 */       if (field.isExtension()) {
/* 1635 */         verifyContainingType(field);
/* 1636 */         Object value = (this.extensions == null) ? null : this.extensions.getField(field);
/* 1637 */         if (value == null) {
/* 1638 */           if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
/*      */           {
/*      */             
/* 1641 */             return DynamicMessage.getDefaultInstance(field.getMessageType());
/*      */           }
/* 1643 */           return field.getDefaultValue();
/*      */         } 
/*      */         
/* 1646 */         return value;
/*      */       } 
/*      */       
/* 1649 */       return super.getField(field);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public Message.Builder getFieldBuilder(Descriptors.FieldDescriptor field) {
/* 1655 */       if (field.isExtension()) {
/* 1656 */         verifyContainingType(field);
/* 1657 */         if (field.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
/* 1658 */           throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
/*      */         }
/*      */         
/* 1661 */         ensureExtensionsIsMutable();
/* 1662 */         Object value = this.extensions.getFieldAllowBuilders(field);
/* 1663 */         if (value == null) {
/* 1664 */           Message.Builder builder = DynamicMessage.newBuilder(field.getMessageType());
/* 1665 */           this.extensions.setField(field, builder);
/* 1666 */           onChanged();
/* 1667 */           return builder;
/*      */         } 
/* 1669 */         if (value instanceof Message.Builder)
/* 1670 */           return (Message.Builder)value; 
/* 1671 */         if (value instanceof Message) {
/* 1672 */           Message.Builder builder = ((Message)value).toBuilder();
/* 1673 */           this.extensions.setField(field, builder);
/* 1674 */           onChanged();
/* 1675 */           return builder;
/*      */         } 
/* 1677 */         throw new UnsupportedOperationException("getRepeatedFieldBuilder() called on a non-Message type.");
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1682 */       return super.getFieldBuilder(field);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public int getRepeatedFieldCount(Descriptors.FieldDescriptor field) {
/* 1688 */       if (field.isExtension()) {
/* 1689 */         verifyContainingType(field);
/* 1690 */         return (this.extensions == null) ? 0 : this.extensions.getRepeatedFieldCount(field);
/*      */       } 
/* 1692 */       return super.getRepeatedFieldCount(field);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Object getRepeatedField(Descriptors.FieldDescriptor field, int index) {
/* 1699 */       if (field.isExtension()) {
/* 1700 */         verifyContainingType(field);
/* 1701 */         if (this.extensions == null) {
/* 1702 */           throw new IndexOutOfBoundsException();
/*      */         }
/* 1704 */         return this.extensions.getRepeatedField(field, index);
/*      */       } 
/* 1706 */       return super.getRepeatedField(field, index);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public Message.Builder getRepeatedFieldBuilder(Descriptors.FieldDescriptor field, int index) {
/* 1712 */       if (field.isExtension()) {
/* 1713 */         verifyContainingType(field);
/* 1714 */         ensureExtensionsIsMutable();
/* 1715 */         if (field.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
/* 1716 */           throw new UnsupportedOperationException("getRepeatedFieldBuilder() called on a non-Message type.");
/*      */         }
/*      */         
/* 1719 */         Object value = this.extensions.getRepeatedFieldAllowBuilders(field, index);
/* 1720 */         if (value instanceof Message.Builder)
/* 1721 */           return (Message.Builder)value; 
/* 1722 */         if (value instanceof Message) {
/* 1723 */           Message.Builder builder = ((Message)value).toBuilder();
/* 1724 */           this.extensions.setRepeatedField(field, index, builder);
/* 1725 */           onChanged();
/* 1726 */           return builder;
/*      */         } 
/* 1728 */         throw new UnsupportedOperationException("getRepeatedFieldBuilder() called on a non-Message type.");
/*      */       } 
/*      */ 
/*      */       
/* 1732 */       return super.getRepeatedFieldBuilder(field, index);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean hasField(Descriptors.FieldDescriptor field) {
/* 1738 */       if (field.isExtension()) {
/* 1739 */         verifyContainingType(field);
/* 1740 */         return (this.extensions == null) ? false : this.extensions.hasField(field);
/*      */       } 
/* 1742 */       return super.hasField(field);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public BuilderType setField(Descriptors.FieldDescriptor field, Object value) {
/* 1749 */       if (field.isExtension()) {
/* 1750 */         verifyContainingType(field);
/* 1751 */         ensureExtensionsIsMutable();
/* 1752 */         this.extensions.setField(field, value);
/* 1753 */         onChanged();
/* 1754 */         return (BuilderType)this;
/*      */       } 
/* 1756 */       return super.setField(field, value);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public BuilderType clearField(Descriptors.FieldDescriptor field) {
/* 1762 */       if (field.isExtension()) {
/* 1763 */         verifyContainingType(field);
/* 1764 */         ensureExtensionsIsMutable();
/* 1765 */         this.extensions.clearField(field);
/* 1766 */         onChanged();
/* 1767 */         return (BuilderType)this;
/*      */       } 
/* 1769 */       return super.clearField(field);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public BuilderType setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
/* 1776 */       if (field.isExtension()) {
/* 1777 */         verifyContainingType(field);
/* 1778 */         ensureExtensionsIsMutable();
/* 1779 */         this.extensions.setRepeatedField(field, index, value);
/* 1780 */         onChanged();
/* 1781 */         return (BuilderType)this;
/*      */       } 
/* 1783 */       return super.setRepeatedField(field, index, value);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public BuilderType addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
/* 1790 */       if (field.isExtension()) {
/* 1791 */         verifyContainingType(field);
/* 1792 */         ensureExtensionsIsMutable();
/* 1793 */         this.extensions.addRepeatedField(field, value);
/* 1794 */         onChanged();
/* 1795 */         return (BuilderType)this;
/*      */       } 
/* 1797 */       return super.addRepeatedField(field, value);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public Message.Builder newBuilderForField(Descriptors.FieldDescriptor field) {
/* 1803 */       if (field.isExtension()) {
/* 1804 */         return DynamicMessage.newBuilder(field.getMessageType());
/*      */       }
/* 1806 */       return super.newBuilderForField(field);
/*      */     }
/*      */ 
/*      */     
/*      */     protected final void mergeExtensionFields(ExtendableMessage other) {
/* 1811 */       if (other.extensions != null) {
/* 1812 */         ensureExtensionsIsMutable();
/* 1813 */         this.extensions.mergeFrom(other.extensions);
/* 1814 */         onChanged();
/*      */       } 
/*      */     }
/*      */     
/*      */     private void verifyContainingType(Descriptors.FieldDescriptor field) {
/* 1819 */       if (field.getContainingType() != getDescriptorForType()) {
/* 1820 */         throw new IllegalArgumentException("FieldDescriptor does not match message type.");
/*      */       }
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
/*      */   private static Method getMethodOrDie(Class clazz, String name, Class... params) {
/*      */     try {
/* 1844 */       return clazz.getMethod(name, params);
/* 1845 */     } catch (NoSuchMethodException e) {
/* 1846 */       throw new RuntimeException("Generated message class \"" + clazz
/* 1847 */           .getName() + "\" missing method \"" + name + "\".", e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Object invokeOrDie(Method method, Object object, Object... params) {
/*      */     try {
/* 1856 */       return method.invoke(object, params);
/* 1857 */     } catch (IllegalAccessException e) {
/* 1858 */       throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
/*      */     
/*      */     }
/* 1861 */     catch (InvocationTargetException e) {
/* 1862 */       Throwable cause = e.getCause();
/* 1863 */       if (cause instanceof RuntimeException)
/* 1864 */         throw (RuntimeException)cause; 
/* 1865 */       if (cause instanceof Error) {
/* 1866 */         throw (Error)cause;
/*      */       }
/* 1868 */       throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
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
/*      */   protected MapField internalGetMapField(int fieldNumber) {
/* 1887 */     throw new RuntimeException("No map fields found in " + 
/* 1888 */         getClass().getName());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static final class FieldAccessorTable
/*      */   {
/*      */     private final Descriptors.Descriptor descriptor;
/*      */ 
/*      */     
/*      */     private final FieldAccessor[] fields;
/*      */ 
/*      */     
/*      */     private String[] camelCaseNames;
/*      */ 
/*      */     
/*      */     private final OneofAccessor[] oneofs;
/*      */ 
/*      */     
/*      */     private volatile boolean initialized;
/*      */ 
/*      */ 
/*      */     
/*      */     public FieldAccessorTable(Descriptors.Descriptor descriptor, String[] camelCaseNames, Class<? extends GeneratedMessageV3> messageClass, Class<? extends Builder> builderClass) {
/* 1912 */       this(descriptor, camelCaseNames);
/* 1913 */       ensureFieldAccessorsInitialized(messageClass, builderClass);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public FieldAccessorTable(Descriptors.Descriptor descriptor, String[] camelCaseNames) {
/* 1923 */       this.descriptor = descriptor;
/* 1924 */       this.camelCaseNames = camelCaseNames;
/* 1925 */       this.fields = new FieldAccessor[descriptor.getFields().size()];
/* 1926 */       this.oneofs = new OneofAccessor[descriptor.getOneofs().size()];
/* 1927 */       this.initialized = false;
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
/*      */     public FieldAccessorTable ensureFieldAccessorsInitialized(Class<? extends GeneratedMessageV3> messageClass, Class<? extends Builder> builderClass) {
/* 1940 */       if (this.initialized) return this; 
/* 1941 */       synchronized (this) {
/* 1942 */         if (this.initialized) return this; 
/* 1943 */         int fieldsSize = this.fields.length;
/* 1944 */         for (int i = 0; i < fieldsSize; i++) {
/* 1945 */           Descriptors.FieldDescriptor field = this.descriptor.getFields().get(i);
/* 1946 */           String containingOneofCamelCaseName = null;
/* 1947 */           if (field.getContainingOneof() != null)
/*      */           {
/* 1949 */             containingOneofCamelCaseName = this.camelCaseNames[fieldsSize + field.getContainingOneof().getIndex()];
/*      */           }
/* 1951 */           if (field.isRepeated()) {
/* 1952 */             if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
/* 1953 */               if (field.isMapField()) {
/* 1954 */                 this.fields[i] = new MapFieldAccessor(field, this.camelCaseNames[i], messageClass, builderClass);
/*      */               } else {
/*      */                 
/* 1957 */                 this.fields[i] = new RepeatedMessageFieldAccessor(field, this.camelCaseNames[i], messageClass, builderClass);
/*      */               }
/*      */             
/* 1960 */             } else if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM) {
/* 1961 */               this.fields[i] = new RepeatedEnumFieldAccessor(field, this.camelCaseNames[i], messageClass, builderClass);
/*      */             } else {
/*      */               
/* 1964 */               this.fields[i] = new RepeatedFieldAccessor(field, this.camelCaseNames[i], messageClass, builderClass);
/*      */             }
/*      */           
/*      */           }
/* 1968 */           else if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
/* 1969 */             this.fields[i] = new SingularMessageFieldAccessor(field, this.camelCaseNames[i], messageClass, builderClass, containingOneofCamelCaseName);
/*      */           
/*      */           }
/* 1972 */           else if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM) {
/* 1973 */             this.fields[i] = new SingularEnumFieldAccessor(field, this.camelCaseNames[i], messageClass, builderClass, containingOneofCamelCaseName);
/*      */           
/*      */           }
/* 1976 */           else if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.STRING) {
/* 1977 */             this.fields[i] = new SingularStringFieldAccessor(field, this.camelCaseNames[i], messageClass, builderClass, containingOneofCamelCaseName);
/*      */           }
/*      */           else {
/*      */             
/* 1981 */             this.fields[i] = new SingularFieldAccessor(field, this.camelCaseNames[i], messageClass, builderClass, containingOneofCamelCaseName);
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1988 */         int oneofsSize = this.oneofs.length;
/* 1989 */         for (int j = 0; j < oneofsSize; j++) {
/* 1990 */           this.oneofs[j] = new OneofAccessor(this.descriptor, j, this.camelCaseNames[j + fieldsSize], messageClass, builderClass);
/*      */         }
/*      */ 
/*      */         
/* 1994 */         this.initialized = true;
/* 1995 */         this.camelCaseNames = null;
/* 1996 */         return this;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private FieldAccessor getField(Descriptors.FieldDescriptor field) {
/* 2008 */       if (field.getContainingType() != this.descriptor) {
/* 2009 */         throw new IllegalArgumentException("FieldDescriptor does not match message type.");
/*      */       }
/* 2011 */       if (field.isExtension())
/*      */       {
/*      */         
/* 2014 */         throw new IllegalArgumentException("This type does not have extensions.");
/*      */       }
/*      */       
/* 2017 */       return this.fields[field.getIndex()];
/*      */     }
/*      */ 
/*      */     
/*      */     private OneofAccessor getOneof(Descriptors.OneofDescriptor oneof) {
/* 2022 */       if (oneof.getContainingType() != this.descriptor) {
/* 2023 */         throw new IllegalArgumentException("OneofDescriptor does not match message type.");
/*      */       }
/*      */       
/* 2026 */       return this.oneofs[oneof.getIndex()];
/*      */     }
/*      */     private static interface FieldAccessor {
/*      */       Object get(GeneratedMessageV3 param2GeneratedMessageV3);
/*      */       Object get(Builder param2Builder);
/*      */       Object getRaw(GeneratedMessageV3 param2GeneratedMessageV3);
/*      */       Object getRaw(Builder param2Builder);
/*      */       void set(Builder param2Builder, Object param2Object);
/*      */       Object getRepeated(GeneratedMessageV3 param2GeneratedMessageV3, int param2Int);
/*      */       Object getRepeated(Builder param2Builder, int param2Int);
/*      */       Object getRepeatedRaw(GeneratedMessageV3 param2GeneratedMessageV3, int param2Int);
/*      */       Object getRepeatedRaw(Builder param2Builder, int param2Int);
/*      */       void setRepeated(Builder param2Builder, int param2Int, Object param2Object);
/*      */       
/*      */       void addRepeated(Builder param2Builder, Object param2Object);
/*      */       
/*      */       boolean has(GeneratedMessageV3 param2GeneratedMessageV3);
/*      */       
/*      */       boolean has(Builder param2Builder);
/*      */       
/*      */       int getRepeatedCount(GeneratedMessageV3 param2GeneratedMessageV3);
/*      */       
/*      */       int getRepeatedCount(Builder param2Builder);
/*      */       
/*      */       void clear(Builder param2Builder);
/*      */       
/*      */       Message.Builder newBuilder();
/*      */       
/*      */       Message.Builder getBuilder(Builder param2Builder);
/*      */       
/*      */       Message.Builder getRepeatedBuilder(Builder param2Builder, int param2Int); }
/*      */     
/*      */     private static class OneofAccessor { private final Descriptors.Descriptor descriptor;
/*      */       private final Method caseMethod;
/*      */       private final Method caseMethodBuilder;
/*      */       private final Method clearMethod;
/*      */       private final Descriptors.FieldDescriptor fieldDescriptor;
/*      */       
/*      */       OneofAccessor(Descriptors.Descriptor descriptor, int oneofIndex, String camelCaseName, Class<? extends GeneratedMessageV3> messageClass, Class<? extends Builder> builderClass) {
/* 2065 */         this.descriptor = descriptor;
/* 2066 */         Descriptors.OneofDescriptor oneofDescriptor = descriptor.getOneofs().get(oneofIndex);
/* 2067 */         if (oneofDescriptor.isSynthetic()) {
/* 2068 */           this.caseMethod = null;
/* 2069 */           this.caseMethodBuilder = null;
/* 2070 */           this.fieldDescriptor = oneofDescriptor.getFields().get(0);
/*      */         } else {
/* 2072 */           this.caseMethod = GeneratedMessageV3.getMethodOrDie(messageClass, "get" + camelCaseName + "Case", new Class[0]);
/* 2073 */           this.caseMethodBuilder = GeneratedMessageV3.getMethodOrDie(builderClass, "get" + camelCaseName + "Case", new Class[0]);
/* 2074 */           this.fieldDescriptor = null;
/*      */         } 
/* 2076 */         this.clearMethod = GeneratedMessageV3.getMethodOrDie(builderClass, "clear" + camelCaseName, new Class[0]);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public boolean has(GeneratedMessageV3 message) {
/* 2086 */         if (this.fieldDescriptor != null) {
/* 2087 */           return message.hasField(this.fieldDescriptor);
/*      */         }
/* 2089 */         if (((Internal.EnumLite)GeneratedMessageV3.invokeOrDie(this.caseMethod, message, new Object[0])).getNumber() == 0) {
/* 2090 */           return false;
/*      */         }
/*      */         
/* 2093 */         return true;
/*      */       }
/*      */       
/*      */       public boolean has(Builder builder) {
/* 2097 */         if (this.fieldDescriptor != null) {
/* 2098 */           return builder.hasField(this.fieldDescriptor);
/*      */         }
/* 2100 */         if (((Internal.EnumLite)GeneratedMessageV3.invokeOrDie(this.caseMethodBuilder, builder, new Object[0])).getNumber() == 0) {
/* 2101 */           return false;
/*      */         }
/*      */         
/* 2104 */         return true;
/*      */       }
/*      */       
/*      */       public Descriptors.FieldDescriptor get(GeneratedMessageV3 message) {
/* 2108 */         if (this.fieldDescriptor != null) {
/* 2109 */           return message.hasField(this.fieldDescriptor) ? this.fieldDescriptor : null;
/*      */         }
/* 2111 */         int fieldNumber = ((Internal.EnumLite)GeneratedMessageV3.invokeOrDie(this.caseMethod, message, new Object[0])).getNumber();
/* 2112 */         if (fieldNumber > 0) {
/* 2113 */           return this.descriptor.findFieldByNumber(fieldNumber);
/*      */         }
/*      */         
/* 2116 */         return null;
/*      */       }
/*      */       
/*      */       public Descriptors.FieldDescriptor get(Builder builder) {
/* 2120 */         if (this.fieldDescriptor != null) {
/* 2121 */           return builder.hasField(this.fieldDescriptor) ? this.fieldDescriptor : null;
/*      */         }
/*      */         
/* 2124 */         int fieldNumber = ((Internal.EnumLite)GeneratedMessageV3.invokeOrDie(this.caseMethodBuilder, builder, new Object[0])).getNumber();
/* 2125 */         if (fieldNumber > 0) {
/* 2126 */           return this.descriptor.findFieldByNumber(fieldNumber);
/*      */         }
/*      */         
/* 2129 */         return null;
/*      */       }
/*      */       
/*      */       public void clear(Builder builder) {
/* 2133 */         GeneratedMessageV3.invokeOrDie(this.clearMethod, builder, new Object[0]);
/*      */       } }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static class SingularFieldAccessor
/*      */       implements FieldAccessor
/*      */     {
/*      */       protected final Class<?> type;
/*      */ 
/*      */       
/*      */       protected final Descriptors.FieldDescriptor field;
/*      */ 
/*      */       
/*      */       protected final boolean isOneofField;
/*      */       
/*      */       protected final boolean hasHasMethod;
/*      */       
/*      */       protected final MethodInvoker invoker;
/*      */ 
/*      */       
/*      */       private static final class ReflectionInvoker
/*      */         implements MethodInvoker
/*      */       {
/*      */         protected final Method getMethod;
/*      */         
/*      */         protected final Method getMethodBuilder;
/*      */         
/*      */         protected final Method setMethod;
/*      */         
/*      */         protected final Method hasMethod;
/*      */         
/*      */         protected final Method hasMethodBuilder;
/*      */         
/*      */         protected final Method clearMethod;
/*      */         
/*      */         protected final Method caseMethod;
/*      */         
/*      */         protected final Method caseMethodBuilder;
/*      */ 
/*      */         
/*      */         ReflectionInvoker(Descriptors.FieldDescriptor descriptor, String camelCaseName, Class<? extends GeneratedMessageV3> messageClass, Class<? extends Builder> builderClass, String containingOneofCamelCaseName, boolean isOneofField, boolean hasHasMethod) {
/* 2176 */           this.getMethod = GeneratedMessageV3.getMethodOrDie(messageClass, "get" + camelCaseName, new Class[0]);
/* 2177 */           this.getMethodBuilder = GeneratedMessageV3.getMethodOrDie(builderClass, "get" + camelCaseName, new Class[0]);
/* 2178 */           Class<?> type = this.getMethod.getReturnType();
/* 2179 */           this.setMethod = GeneratedMessageV3.getMethodOrDie(builderClass, "set" + camelCaseName, new Class[] { type });
/* 2180 */           this.hasMethod = hasHasMethod ? GeneratedMessageV3.getMethodOrDie(messageClass, "has" + camelCaseName, new Class[0]) : null;
/* 2181 */           this
/* 2182 */             .hasMethodBuilder = hasHasMethod ? GeneratedMessageV3.getMethodOrDie(builderClass, "has" + camelCaseName, new Class[0]) : null;
/* 2183 */           this.clearMethod = GeneratedMessageV3.getMethodOrDie(builderClass, "clear" + camelCaseName, new Class[0]);
/* 2184 */           this
/*      */             
/* 2186 */             .caseMethod = isOneofField ? GeneratedMessageV3.getMethodOrDie(messageClass, "get" + containingOneofCamelCaseName + "Case", new Class[0]) : null;
/*      */           
/* 2188 */           this
/*      */             
/* 2190 */             .caseMethodBuilder = isOneofField ? GeneratedMessageV3.getMethodOrDie(builderClass, "get" + containingOneofCamelCaseName + "Case", new Class[0]) : null;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public Object get(GeneratedMessageV3 message) {
/* 2196 */           return GeneratedMessageV3.invokeOrDie(this.getMethod, message, new Object[0]);
/*      */         }
/*      */ 
/*      */         
/*      */         public Object get(Builder<?> builder) {
/* 2201 */           return GeneratedMessageV3.invokeOrDie(this.getMethodBuilder, builder, new Object[0]);
/*      */         }
/*      */ 
/*      */         
/*      */         public int getOneofFieldNumber(GeneratedMessageV3 message) {
/* 2206 */           return ((Internal.EnumLite)GeneratedMessageV3.invokeOrDie(this.caseMethod, message, new Object[0])).getNumber();
/*      */         }
/*      */ 
/*      */         
/*      */         public int getOneofFieldNumber(Builder<?> builder) {
/* 2211 */           return ((Internal.EnumLite)GeneratedMessageV3.invokeOrDie(this.caseMethodBuilder, builder, new Object[0])).getNumber();
/*      */         }
/*      */ 
/*      */         
/*      */         public void set(Builder<?> builder, Object value) {
/* 2216 */           GeneratedMessageV3.invokeOrDie(this.setMethod, builder, new Object[] { value });
/*      */         }
/*      */ 
/*      */         
/*      */         public boolean has(GeneratedMessageV3 message) {
/* 2221 */           return ((Boolean)GeneratedMessageV3.invokeOrDie(this.hasMethod, message, new Object[0])).booleanValue();
/*      */         }
/*      */ 
/*      */         
/*      */         public boolean has(Builder<?> builder) {
/* 2226 */           return ((Boolean)GeneratedMessageV3.invokeOrDie(this.hasMethodBuilder, builder, new Object[0])).booleanValue();
/*      */         }
/*      */ 
/*      */         
/*      */         public void clear(Builder<?> builder) {
/* 2231 */           GeneratedMessageV3.invokeOrDie(this.clearMethod, builder, new Object[0]);
/*      */         }
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       SingularFieldAccessor(Descriptors.FieldDescriptor descriptor, String camelCaseName, Class<? extends GeneratedMessageV3> messageClass, Class<? extends Builder> builderClass, String containingOneofCamelCaseName) {
/* 2241 */         this
/*      */           
/* 2243 */           .isOneofField = (descriptor.getContainingOneof() != null && !descriptor.getContainingOneof().isSynthetic());
/* 2244 */         this
/*      */ 
/*      */           
/* 2247 */           .hasHasMethod = (descriptor.getFile().getSyntax() == Descriptors.FileDescriptor.Syntax.PROTO2 || descriptor.hasOptionalKeyword() || (!this.isOneofField && descriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE));
/* 2248 */         ReflectionInvoker reflectionInvoker = new ReflectionInvoker(descriptor, camelCaseName, messageClass, builderClass, containingOneofCamelCaseName, this.isOneofField, this.hasHasMethod);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2257 */         this.field = descriptor;
/* 2258 */         this.type = reflectionInvoker.getMethod.getReturnType();
/* 2259 */         this.invoker = getMethodInvoker(reflectionInvoker);
/*      */       }
/*      */       
/*      */       static MethodInvoker getMethodInvoker(ReflectionInvoker accessor) {
/* 2263 */         return accessor;
/*      */       }
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
/*      */       public Object get(GeneratedMessageV3 message) {
/* 2277 */         return this.invoker.get(message);
/*      */       }
/*      */       
/*      */       public Object get(Builder<?> builder) {
/* 2281 */         return this.invoker.get(builder);
/*      */       }
/*      */       
/*      */       public Object getRaw(GeneratedMessageV3 message) {
/* 2285 */         return get(message);
/*      */       }
/*      */       
/*      */       public Object getRaw(Builder builder) {
/* 2289 */         return get(builder);
/*      */       }
/*      */       
/*      */       public void set(Builder<?> builder, Object value) {
/* 2293 */         this.invoker.set(builder, value);
/*      */       }
/*      */       
/*      */       public Object getRepeated(GeneratedMessageV3 message, int index) {
/* 2297 */         throw new UnsupportedOperationException("getRepeatedField() called on a singular field.");
/*      */       }
/*      */       
/*      */       public Object getRepeatedRaw(GeneratedMessageV3 message, int index) {
/* 2301 */         throw new UnsupportedOperationException("getRepeatedFieldRaw() called on a singular field.");
/*      */       }
/*      */ 
/*      */       
/*      */       public Object getRepeated(Builder builder, int index) {
/* 2306 */         throw new UnsupportedOperationException("getRepeatedField() called on a singular field.");
/*      */       }
/*      */       
/*      */       public Object getRepeatedRaw(Builder builder, int index) {
/* 2310 */         throw new UnsupportedOperationException("getRepeatedFieldRaw() called on a singular field.");
/*      */       }
/*      */ 
/*      */       
/*      */       public void setRepeated(Builder builder, int index, Object value) {
/* 2315 */         throw new UnsupportedOperationException("setRepeatedField() called on a singular field.");
/*      */       }
/*      */       
/*      */       public void addRepeated(Builder builder, Object value) {
/* 2319 */         throw new UnsupportedOperationException("addRepeatedField() called on a singular field.");
/*      */       }
/*      */       
/*      */       public boolean has(GeneratedMessageV3 message) {
/* 2323 */         if (!this.hasHasMethod) {
/* 2324 */           if (this.isOneofField) {
/* 2325 */             return (this.invoker.getOneofFieldNumber(message) == this.field.getNumber());
/*      */           }
/* 2327 */           return !get(message).equals(this.field.getDefaultValue());
/*      */         } 
/* 2329 */         return this.invoker.has(message);
/*      */       }
/*      */       
/*      */       public boolean has(Builder<?> builder) {
/* 2333 */         if (!this.hasHasMethod) {
/* 2334 */           if (this.isOneofField) {
/* 2335 */             return (this.invoker.getOneofFieldNumber(builder) == this.field.getNumber());
/*      */           }
/* 2337 */           return !get(builder).equals(this.field.getDefaultValue());
/*      */         } 
/* 2339 */         return this.invoker.has(builder);
/*      */       }
/*      */       
/*      */       public int getRepeatedCount(GeneratedMessageV3 message) {
/* 2343 */         throw new UnsupportedOperationException("getRepeatedFieldSize() called on a singular field.");
/*      */       }
/*      */ 
/*      */       
/*      */       public int getRepeatedCount(Builder builder) {
/* 2348 */         throw new UnsupportedOperationException("getRepeatedFieldSize() called on a singular field.");
/*      */       }
/*      */ 
/*      */       
/*      */       public void clear(Builder<?> builder) {
/* 2353 */         this.invoker.clear(builder);
/*      */       }
/*      */       
/*      */       public Message.Builder newBuilder() {
/* 2357 */         throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
/*      */       }
/*      */ 
/*      */       
/*      */       public Message.Builder getBuilder(Builder builder) {
/* 2362 */         throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
/*      */       }
/*      */       
/*      */       public Message.Builder getRepeatedBuilder(Builder builder, int index) {
/* 2366 */         throw new UnsupportedOperationException("getRepeatedFieldBuilder() called on a non-Message type.");
/*      */       }
/*      */       
/*      */       private static interface MethodInvoker
/*      */       {
/*      */         Object get(GeneratedMessageV3 param3GeneratedMessageV3);
/*      */         
/*      */         Object get(Builder<?> param3Builder);
/*      */         
/*      */         int getOneofFieldNumber(GeneratedMessageV3 param3GeneratedMessageV3);
/*      */         
/*      */         int getOneofFieldNumber(Builder<?> param3Builder);
/*      */         
/*      */         void set(Builder<?> param3Builder, Object param3Object);
/*      */         
/*      */         boolean has(GeneratedMessageV3 param3GeneratedMessageV3);
/*      */         
/*      */         boolean has(Builder<?> param3Builder);
/*      */         
/*      */         void clear(Builder<?> param3Builder);
/*      */       }
/*      */     }
/*      */     
/*      */     private static class RepeatedFieldAccessor
/*      */       implements FieldAccessor
/*      */     {
/*      */       protected final Class type;
/*      */       protected final MethodInvoker invoker;
/*      */       
/*      */       private static final class ReflectionInvoker
/*      */         implements MethodInvoker
/*      */       {
/*      */         protected final Method getMethod;
/*      */         protected final Method getMethodBuilder;
/*      */         protected final Method getRepeatedMethod;
/*      */         protected final Method getRepeatedMethodBuilder;
/*      */         protected final Method setRepeatedMethod;
/*      */         protected final Method addRepeatedMethod;
/*      */         protected final Method getCountMethod;
/*      */         protected final Method getCountMethodBuilder;
/*      */         protected final Method clearMethod;
/*      */         
/*      */         ReflectionInvoker(Descriptors.FieldDescriptor descriptor, String camelCaseName, Class<? extends GeneratedMessageV3> messageClass, Class<? extends Builder> builderClass) {
/* 2409 */           this.getMethod = GeneratedMessageV3.getMethodOrDie(messageClass, "get" + camelCaseName + "List", new Class[0]);
/* 2410 */           this.getMethodBuilder = GeneratedMessageV3.getMethodOrDie(builderClass, "get" + camelCaseName + "List", new Class[0]);
/* 2411 */           this.getRepeatedMethod = GeneratedMessageV3.getMethodOrDie(messageClass, "get" + camelCaseName, new Class[] { int.class });
/* 2412 */           this
/* 2413 */             .getRepeatedMethodBuilder = GeneratedMessageV3.getMethodOrDie(builderClass, "get" + camelCaseName, new Class[] { int.class });
/* 2414 */           Class<?> type = this.getRepeatedMethod.getReturnType();
/* 2415 */           this
/* 2416 */             .setRepeatedMethod = GeneratedMessageV3.getMethodOrDie(builderClass, "set" + camelCaseName, new Class[] { int.class, type });
/* 2417 */           this.addRepeatedMethod = GeneratedMessageV3.getMethodOrDie(builderClass, "add" + camelCaseName, new Class[] { type });
/* 2418 */           this.getCountMethod = GeneratedMessageV3.getMethodOrDie(messageClass, "get" + camelCaseName + "Count", new Class[0]);
/* 2419 */           this.getCountMethodBuilder = GeneratedMessageV3.getMethodOrDie(builderClass, "get" + camelCaseName + "Count", new Class[0]);
/* 2420 */           this.clearMethod = GeneratedMessageV3.getMethodOrDie(builderClass, "clear" + camelCaseName, new Class[0]);
/*      */         }
/*      */ 
/*      */         
/*      */         public Object get(GeneratedMessageV3 message) {
/* 2425 */           return GeneratedMessageV3.invokeOrDie(this.getMethod, message, new Object[0]);
/*      */         }
/*      */ 
/*      */         
/*      */         public Object get(Builder<?> builder) {
/* 2430 */           return GeneratedMessageV3.invokeOrDie(this.getMethodBuilder, builder, new Object[0]);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public Object getRepeated(GeneratedMessageV3 message, int index) {
/* 2436 */           return GeneratedMessageV3.invokeOrDie(this.getRepeatedMethod, message, new Object[] { Integer.valueOf(index) });
/*      */         }
/*      */ 
/*      */         
/*      */         public Object getRepeated(Builder<?> builder, int index) {
/* 2441 */           return GeneratedMessageV3.invokeOrDie(this.getRepeatedMethodBuilder, builder, new Object[] { Integer.valueOf(index) });
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void setRepeated(Builder<?> builder, int index, Object value) {
/* 2447 */           GeneratedMessageV3.invokeOrDie(this.setRepeatedMethod, builder, new Object[] { Integer.valueOf(index), value });
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void addRepeated(Builder<?> builder, Object value) {
/* 2453 */           GeneratedMessageV3.invokeOrDie(this.addRepeatedMethod, builder, new Object[] { value });
/*      */         }
/*      */ 
/*      */         
/*      */         public int getRepeatedCount(GeneratedMessageV3 message) {
/* 2458 */           return ((Integer)GeneratedMessageV3.invokeOrDie(this.getCountMethod, message, new Object[0])).intValue();
/*      */         }
/*      */ 
/*      */         
/*      */         public int getRepeatedCount(Builder<?> builder) {
/* 2463 */           return ((Integer)GeneratedMessageV3.invokeOrDie(this.getCountMethodBuilder, builder, new Object[0])).intValue();
/*      */         }
/*      */ 
/*      */         
/*      */         public void clear(Builder<?> builder) {
/* 2468 */           GeneratedMessageV3.invokeOrDie(this.clearMethod, builder, new Object[0]);
/*      */         }
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       RepeatedFieldAccessor(Descriptors.FieldDescriptor descriptor, String camelCaseName, Class<? extends GeneratedMessageV3> messageClass, Class<? extends Builder> builderClass) {
/* 2479 */         ReflectionInvoker reflectionInvoker = new ReflectionInvoker(descriptor, camelCaseName, messageClass, builderClass);
/*      */         
/* 2481 */         this.type = reflectionInvoker.getRepeatedMethod.getReturnType();
/* 2482 */         this.invoker = getMethodInvoker(reflectionInvoker);
/*      */       }
/*      */       
/*      */       static MethodInvoker getMethodInvoker(ReflectionInvoker accessor) {
/* 2486 */         return accessor;
/*      */       }
/*      */ 
/*      */       
/*      */       public Object get(GeneratedMessageV3 message) {
/* 2491 */         return this.invoker.get(message);
/*      */       }
/*      */       
/*      */       public Object get(Builder<?> builder) {
/* 2495 */         return this.invoker.get(builder);
/*      */       }
/*      */       
/*      */       public Object getRaw(GeneratedMessageV3 message) {
/* 2499 */         return get(message);
/*      */       }
/*      */       
/*      */       public Object getRaw(Builder builder) {
/* 2503 */         return get(builder);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public void set(Builder builder, Object value) {
/* 2511 */         clear(builder);
/* 2512 */         for (Object element : value) {
/* 2513 */           addRepeated(builder, element);
/*      */         }
/*      */       }
/*      */       
/*      */       public Object getRepeated(GeneratedMessageV3 message, int index) {
/* 2518 */         return this.invoker.getRepeated(message, index);
/*      */       }
/*      */       
/*      */       public Object getRepeated(Builder<?> builder, int index) {
/* 2522 */         return this.invoker.getRepeated(builder, index);
/*      */       }
/*      */       
/*      */       public Object getRepeatedRaw(GeneratedMessageV3 message, int index) {
/* 2526 */         return getRepeated(message, index);
/*      */       }
/*      */       
/*      */       public Object getRepeatedRaw(Builder builder, int index) {
/* 2530 */         return getRepeated(builder, index);
/*      */       }
/*      */       
/*      */       public void setRepeated(Builder<?> builder, int index, Object value) {
/* 2534 */         this.invoker.setRepeated(builder, index, value);
/*      */       }
/*      */       
/*      */       public void addRepeated(Builder<?> builder, Object value) {
/* 2538 */         this.invoker.addRepeated(builder, value);
/*      */       }
/*      */       
/*      */       public boolean has(GeneratedMessageV3 message) {
/* 2542 */         throw new UnsupportedOperationException("hasField() called on a repeated field.");
/*      */       }
/*      */       
/*      */       public boolean has(Builder builder) {
/* 2546 */         throw new UnsupportedOperationException("hasField() called on a repeated field.");
/*      */       }
/*      */       
/*      */       public int getRepeatedCount(GeneratedMessageV3 message) {
/* 2550 */         return this.invoker.getRepeatedCount(message);
/*      */       }
/*      */       
/*      */       public int getRepeatedCount(Builder<?> builder) {
/* 2554 */         return this.invoker.getRepeatedCount(builder);
/*      */       }
/*      */       
/*      */       public void clear(Builder<?> builder) {
/* 2558 */         this.invoker.clear(builder);
/*      */       }
/*      */       
/*      */       public Message.Builder newBuilder() {
/* 2562 */         throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
/*      */       }
/*      */ 
/*      */       
/*      */       public Message.Builder getBuilder(Builder builder) {
/* 2567 */         throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
/*      */       }
/*      */       
/*      */       public Message.Builder getRepeatedBuilder(Builder builder, int index) {
/* 2571 */         throw new UnsupportedOperationException("getRepeatedFieldBuilder() called on a non-Message type.");
/*      */       } static interface MethodInvoker { Object get(GeneratedMessageV3 param3GeneratedMessageV3); Object get(Builder<?> param3Builder); Object getRepeated(GeneratedMessageV3 param3GeneratedMessageV3, int param3Int);
/*      */         Object getRepeated(Builder<?> param3Builder, int param3Int);
/*      */         void setRepeated(Builder<?> param3Builder, int param3Int, Object param3Object);
/*      */         void addRepeated(Builder<?> param3Builder, Object param3Object);
/*      */         int getRepeatedCount(GeneratedMessageV3 param3GeneratedMessageV3);
/*      */         int getRepeatedCount(Builder<?> param3Builder);
/*      */         void clear(Builder<?> param3Builder); } }
/*      */     private static class MapFieldAccessor implements FieldAccessor { private final Descriptors.FieldDescriptor field; private final Message mapEntryMessageDefaultInstance;
/*      */       MapFieldAccessor(Descriptors.FieldDescriptor descriptor, String camelCaseName, Class<? extends GeneratedMessageV3> messageClass, Class<? extends Builder> builderClass) {
/* 2581 */         this.field = descriptor;
/*      */         
/* 2583 */         Method getDefaultInstanceMethod = GeneratedMessageV3.getMethodOrDie(messageClass, "getDefaultInstance", new Class[0]);
/* 2584 */         MapField<?, ?> defaultMapField = getMapField(
/* 2585 */             (GeneratedMessageV3)GeneratedMessageV3.invokeOrDie(getDefaultInstanceMethod, null, new Object[0]));
/* 2586 */         this
/* 2587 */           .mapEntryMessageDefaultInstance = defaultMapField.getMapEntryMessageDefaultInstance();
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       private MapField<?, ?> getMapField(GeneratedMessageV3 message) {
/* 2594 */         return message.internalGetMapField(this.field.getNumber());
/*      */       }
/*      */       
/*      */       private MapField<?, ?> getMapField(Builder builder) {
/* 2598 */         return builder.internalGetMapField(this.field.getNumber());
/*      */       }
/*      */ 
/*      */       
/*      */       private MapField<?, ?> getMutableMapField(Builder builder) {
/* 2603 */         return builder.internalGetMutableMapField(this.field
/* 2604 */             .getNumber());
/*      */       }
/*      */       
/*      */       private Message coerceType(Message value) {
/* 2608 */         if (value == null) {
/* 2609 */           return null;
/*      */         }
/* 2611 */         if (this.mapEntryMessageDefaultInstance.getClass().isInstance(value)) {
/* 2612 */           return value;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2618 */         return this.mapEntryMessageDefaultInstance.toBuilder().mergeFrom(value).build();
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public Object get(GeneratedMessageV3 message) {
/* 2624 */         List<Object> result = new ArrayList();
/* 2625 */         for (int i = 0; i < getRepeatedCount(message); i++) {
/* 2626 */           result.add(getRepeated(message, i));
/*      */         }
/* 2628 */         return Collections.unmodifiableList(result);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public Object get(Builder builder) {
/* 2634 */         List<Object> result = new ArrayList();
/* 2635 */         for (int i = 0; i < getRepeatedCount(builder); i++) {
/* 2636 */           result.add(getRepeated(builder, i));
/*      */         }
/* 2638 */         return Collections.unmodifiableList(result);
/*      */       }
/*      */ 
/*      */       
/*      */       public Object getRaw(GeneratedMessageV3 message) {
/* 2643 */         return get(message);
/*      */       }
/*      */ 
/*      */       
/*      */       public Object getRaw(Builder builder) {
/* 2648 */         return get(builder);
/*      */       }
/*      */ 
/*      */       
/*      */       public void set(Builder builder, Object value) {
/* 2653 */         clear(builder);
/* 2654 */         for (Object entry : value) {
/* 2655 */           addRepeated(builder, entry);
/*      */         }
/*      */       }
/*      */ 
/*      */       
/*      */       public Object getRepeated(GeneratedMessageV3 message, int index) {
/* 2661 */         return getMapField(message).getList().get(index);
/*      */       }
/*      */ 
/*      */       
/*      */       public Object getRepeated(Builder builder, int index) {
/* 2666 */         return getMapField(builder).getList().get(index);
/*      */       }
/*      */ 
/*      */       
/*      */       public Object getRepeatedRaw(GeneratedMessageV3 message, int index) {
/* 2671 */         return getRepeated(message, index);
/*      */       }
/*      */ 
/*      */       
/*      */       public Object getRepeatedRaw(Builder builder, int index) {
/* 2676 */         return getRepeated(builder, index);
/*      */       }
/*      */ 
/*      */       
/*      */       public void setRepeated(Builder builder, int index, Object value) {
/* 2681 */         getMutableMapField(builder).getMutableList().set(index, coerceType((Message)value));
/*      */       }
/*      */ 
/*      */       
/*      */       public void addRepeated(Builder builder, Object value) {
/* 2686 */         getMutableMapField(builder).getMutableList().add(coerceType((Message)value));
/*      */       }
/*      */ 
/*      */       
/*      */       public boolean has(GeneratedMessageV3 message) {
/* 2691 */         throw new UnsupportedOperationException("hasField() is not supported for repeated fields.");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public boolean has(Builder builder) {
/* 2697 */         throw new UnsupportedOperationException("hasField() is not supported for repeated fields.");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public int getRepeatedCount(GeneratedMessageV3 message) {
/* 2703 */         return getMapField(message).getList().size();
/*      */       }
/*      */ 
/*      */       
/*      */       public int getRepeatedCount(Builder builder) {
/* 2708 */         return getMapField(builder).getList().size();
/*      */       }
/*      */ 
/*      */       
/*      */       public void clear(Builder builder) {
/* 2713 */         getMutableMapField(builder).getMutableList().clear();
/*      */       }
/*      */ 
/*      */       
/*      */       public Message.Builder newBuilder() {
/* 2718 */         return this.mapEntryMessageDefaultInstance.newBuilderForType();
/*      */       }
/*      */ 
/*      */       
/*      */       public Message.Builder getBuilder(Builder builder) {
/* 2723 */         throw new UnsupportedOperationException("Nested builder not supported for map fields.");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public Message.Builder getRepeatedBuilder(Builder builder, int index) {
/* 2729 */         throw new UnsupportedOperationException("Nested builder not supported for map fields.");
/*      */       } }
/*      */ 
/*      */     
/*      */     private static final class SingularEnumFieldAccessor extends SingularFieldAccessor {
/*      */       private Descriptors.EnumDescriptor enumDescriptor;
/*      */       private Method valueOfMethod;
/*      */       private Method getValueDescriptorMethod;
/*      */       private boolean supportUnknownEnumValue;
/*      */       private Method getValueMethod;
/*      */       private Method getValueMethodBuilder;
/*      */       private Method setValueMethod;
/*      */       
/*      */       SingularEnumFieldAccessor(Descriptors.FieldDescriptor descriptor, String camelCaseName, Class<? extends GeneratedMessageV3> messageClass, Class<? extends Builder> builderClass, String containingOneofCamelCaseName) {
/* 2743 */         super(descriptor, camelCaseName, messageClass, builderClass, containingOneofCamelCaseName);
/*      */         
/* 2745 */         this.enumDescriptor = descriptor.getEnumType();
/*      */         
/* 2747 */         this.valueOfMethod = GeneratedMessageV3.getMethodOrDie(this.type, "valueOf", new Class[] { Descriptors.EnumValueDescriptor.class });
/* 2748 */         this.getValueDescriptorMethod = GeneratedMessageV3.getMethodOrDie(this.type, "getValueDescriptor", new Class[0]);
/*      */         
/* 2750 */         this.supportUnknownEnumValue = descriptor.getFile().supportsUnknownEnumValue();
/* 2751 */         if (this.supportUnknownEnumValue) {
/* 2752 */           this
/* 2753 */             .getValueMethod = GeneratedMessageV3.getMethodOrDie(messageClass, "get" + camelCaseName + "Value", new Class[0]);
/* 2754 */           this
/* 2755 */             .getValueMethodBuilder = GeneratedMessageV3.getMethodOrDie(builderClass, "get" + camelCaseName + "Value", new Class[0]);
/* 2756 */           this
/* 2757 */             .setValueMethod = GeneratedMessageV3.getMethodOrDie(builderClass, "set" + camelCaseName + "Value", new Class[] { int.class });
/*      */         } 
/*      */       }
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
/*      */       public Object get(GeneratedMessageV3 message) {
/* 2773 */         if (this.supportUnknownEnumValue) {
/* 2774 */           int value = ((Integer)GeneratedMessageV3.invokeOrDie(this.getValueMethod, message, new Object[0])).intValue();
/* 2775 */           return this.enumDescriptor.findValueByNumberCreatingIfUnknown(value);
/*      */         } 
/* 2777 */         return GeneratedMessageV3.invokeOrDie(this.getValueDescriptorMethod, super.get(message), new Object[0]);
/*      */       }
/*      */ 
/*      */       
/*      */       public Object get(Builder builder) {
/* 2782 */         if (this.supportUnknownEnumValue) {
/* 2783 */           int value = ((Integer)GeneratedMessageV3.invokeOrDie(this.getValueMethodBuilder, builder, new Object[0])).intValue();
/* 2784 */           return this.enumDescriptor.findValueByNumberCreatingIfUnknown(value);
/*      */         } 
/* 2786 */         return GeneratedMessageV3.invokeOrDie(this.getValueDescriptorMethod, super.get(builder), new Object[0]);
/*      */       }
/*      */ 
/*      */       
/*      */       public void set(Builder builder, Object value) {
/* 2791 */         if (this.supportUnknownEnumValue) {
/* 2792 */           GeneratedMessageV3.invokeOrDie(this.setValueMethod, builder, new Object[] {
/* 2793 */                 Integer.valueOf(((Descriptors.EnumValueDescriptor)value).getNumber()) });
/*      */           return;
/*      */         } 
/* 2796 */         super.set(builder, GeneratedMessageV3.invokeOrDie(this.valueOfMethod, null, new Object[] { value }));
/*      */       } }
/*      */     private static final class RepeatedEnumFieldAccessor extends RepeatedFieldAccessor { private Descriptors.EnumDescriptor enumDescriptor; private final Method valueOfMethod; private final Method getValueDescriptorMethod;
/*      */       private boolean supportUnknownEnumValue;
/*      */       private Method getRepeatedValueMethod;
/*      */       private Method getRepeatedValueMethodBuilder;
/*      */       private Method setRepeatedValueMethod;
/*      */       private Method addRepeatedValueMethod;
/*      */       
/*      */       RepeatedEnumFieldAccessor(Descriptors.FieldDescriptor descriptor, String camelCaseName, Class<? extends GeneratedMessageV3> messageClass, Class<? extends Builder> builderClass) {
/* 2806 */         super(descriptor, camelCaseName, messageClass, builderClass);
/*      */         
/* 2808 */         this.enumDescriptor = descriptor.getEnumType();
/*      */         
/* 2810 */         this.valueOfMethod = GeneratedMessageV3.getMethodOrDie(this.type, "valueOf", new Class[] { Descriptors.EnumValueDescriptor.class });
/* 2811 */         this.getValueDescriptorMethod = GeneratedMessageV3.getMethodOrDie(this.type, "getValueDescriptor", new Class[0]);
/*      */         
/* 2813 */         this.supportUnknownEnumValue = descriptor.getFile().supportsUnknownEnumValue();
/* 2814 */         if (this.supportUnknownEnumValue) {
/* 2815 */           this
/* 2816 */             .getRepeatedValueMethod = GeneratedMessageV3.getMethodOrDie(messageClass, "get" + camelCaseName + "Value", new Class[] { int.class });
/* 2817 */           this
/* 2818 */             .getRepeatedValueMethodBuilder = GeneratedMessageV3.getMethodOrDie(builderClass, "get" + camelCaseName + "Value", new Class[] { int.class });
/* 2819 */           this
/* 2820 */             .setRepeatedValueMethod = GeneratedMessageV3.getMethodOrDie(builderClass, "set" + camelCaseName + "Value", new Class[] { int.class, int.class });
/* 2821 */           this
/* 2822 */             .addRepeatedValueMethod = GeneratedMessageV3.getMethodOrDie(builderClass, "add" + camelCaseName + "Value", new Class[] { int.class });
/*      */         } 
/*      */       }
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
/*      */       public Object get(GeneratedMessageV3 message) {
/* 2839 */         List<Object> newList = new ArrayList();
/* 2840 */         int size = getRepeatedCount(message);
/* 2841 */         for (int i = 0; i < size; i++) {
/* 2842 */           newList.add(getRepeated(message, i));
/*      */         }
/* 2844 */         return Collections.unmodifiableList(newList);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public Object get(Builder builder) {
/* 2850 */         List<Object> newList = new ArrayList();
/* 2851 */         int size = getRepeatedCount(builder);
/* 2852 */         for (int i = 0; i < size; i++) {
/* 2853 */           newList.add(getRepeated(builder, i));
/*      */         }
/* 2855 */         return Collections.unmodifiableList(newList);
/*      */       }
/*      */ 
/*      */       
/*      */       public Object getRepeated(GeneratedMessageV3 message, int index) {
/* 2860 */         if (this.supportUnknownEnumValue) {
/* 2861 */           int value = ((Integer)GeneratedMessageV3.invokeOrDie(this.getRepeatedValueMethod, message, new Object[] { Integer.valueOf(index) })).intValue();
/* 2862 */           return this.enumDescriptor.findValueByNumberCreatingIfUnknown(value);
/*      */         } 
/* 2864 */         return GeneratedMessageV3.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(message, index), new Object[0]);
/*      */       }
/*      */ 
/*      */       
/*      */       public Object getRepeated(Builder builder, int index) {
/* 2869 */         if (this.supportUnknownEnumValue) {
/* 2870 */           int value = ((Integer)GeneratedMessageV3.invokeOrDie(this.getRepeatedValueMethodBuilder, builder, new Object[] { Integer.valueOf(index) })).intValue();
/* 2871 */           return this.enumDescriptor.findValueByNumberCreatingIfUnknown(value);
/*      */         } 
/* 2873 */         return GeneratedMessageV3.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(builder, index), new Object[0]);
/*      */       }
/*      */ 
/*      */       
/*      */       public void setRepeated(Builder builder, int index, Object value) {
/* 2878 */         if (this.supportUnknownEnumValue) {
/* 2879 */           GeneratedMessageV3.invokeOrDie(this.setRepeatedValueMethod, builder, new Object[] { Integer.valueOf(index), 
/* 2880 */                 Integer.valueOf(((Descriptors.EnumValueDescriptor)value).getNumber()) });
/*      */           return;
/*      */         } 
/* 2883 */         super.setRepeated(builder, index, GeneratedMessageV3.invokeOrDie(this.valueOfMethod, null, new Object[] { value }));
/*      */       }
/*      */       
/*      */       public void addRepeated(Builder builder, Object value) {
/* 2887 */         if (this.supportUnknownEnumValue) {
/* 2888 */           GeneratedMessageV3.invokeOrDie(this.addRepeatedValueMethod, builder, new Object[] {
/* 2889 */                 Integer.valueOf(((Descriptors.EnumValueDescriptor)value).getNumber()) });
/*      */           return;
/*      */         } 
/* 2892 */         super.addRepeated(builder, GeneratedMessageV3.invokeOrDie(this.valueOfMethod, null, new Object[] { value }));
/*      */       } }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static final class SingularStringFieldAccessor
/*      */       extends SingularFieldAccessor
/*      */     {
/*      */       private final Method getBytesMethod;
/*      */ 
/*      */ 
/*      */       
/*      */       private final Method getBytesMethodBuilder;
/*      */ 
/*      */ 
/*      */       
/*      */       private final Method setBytesMethodBuilder;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       SingularStringFieldAccessor(Descriptors.FieldDescriptor descriptor, String camelCaseName, Class<? extends GeneratedMessageV3> messageClass, Class<? extends Builder> builderClass, String containingOneofCamelCaseName) {
/* 2916 */         super(descriptor, camelCaseName, messageClass, builderClass, containingOneofCamelCaseName);
/*      */         
/* 2918 */         this.getBytesMethod = GeneratedMessageV3.getMethodOrDie(messageClass, "get" + camelCaseName + "Bytes", new Class[0]);
/*      */         
/* 2920 */         this.getBytesMethodBuilder = GeneratedMessageV3.getMethodOrDie(builderClass, "get" + camelCaseName + "Bytes", new Class[0]);
/*      */         
/* 2922 */         this.setBytesMethodBuilder = GeneratedMessageV3.getMethodOrDie(builderClass, "set" + camelCaseName + "Bytes", new Class[] { ByteString.class });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Object getRaw(GeneratedMessageV3 message) {
/* 2932 */         return GeneratedMessageV3.invokeOrDie(this.getBytesMethod, message, new Object[0]);
/*      */       }
/*      */ 
/*      */       
/*      */       public Object getRaw(Builder builder) {
/* 2937 */         return GeneratedMessageV3.invokeOrDie(this.getBytesMethodBuilder, builder, new Object[0]);
/*      */       }
/*      */ 
/*      */       
/*      */       public void set(Builder builder, Object value) {
/* 2942 */         if (value instanceof ByteString) {
/* 2943 */           GeneratedMessageV3.invokeOrDie(this.setBytesMethodBuilder, builder, new Object[] { value });
/*      */         } else {
/* 2945 */           super.set(builder, value);
/*      */         } 
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     private static final class SingularMessageFieldAccessor
/*      */       extends SingularFieldAccessor
/*      */     {
/*      */       private final Method newBuilderMethod;
/*      */       
/*      */       private final Method getBuilderMethodBuilder;
/*      */       
/*      */       SingularMessageFieldAccessor(Descriptors.FieldDescriptor descriptor, String camelCaseName, Class<? extends GeneratedMessageV3> messageClass, Class<? extends Builder> builderClass, String containingOneofCamelCaseName) {
/* 2959 */         super(descriptor, camelCaseName, messageClass, builderClass, containingOneofCamelCaseName);
/*      */ 
/*      */         
/* 2962 */         this.newBuilderMethod = GeneratedMessageV3.getMethodOrDie(this.type, "newBuilder", new Class[0]);
/* 2963 */         this
/* 2964 */           .getBuilderMethodBuilder = GeneratedMessageV3.getMethodOrDie(builderClass, "get" + camelCaseName + "Builder", new Class[0]);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       private Object coerceType(Object value) {
/* 2971 */         if (this.type.isInstance(value)) {
/* 2972 */           return value;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2978 */         return ((Message.Builder)GeneratedMessageV3.invokeOrDie(this.newBuilderMethod, null, new Object[0]))
/* 2979 */           .mergeFrom((Message)value)
/* 2980 */           .buildPartial();
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public void set(Builder builder, Object value) {
/* 2986 */         super.set(builder, coerceType(value));
/*      */       }
/*      */       
/*      */       public Message.Builder newBuilder() {
/* 2990 */         return (Message.Builder)GeneratedMessageV3.invokeOrDie(this.newBuilderMethod, null, new Object[0]);
/*      */       }
/*      */       
/*      */       public Message.Builder getBuilder(Builder builder) {
/* 2994 */         return (Message.Builder)GeneratedMessageV3.invokeOrDie(this.getBuilderMethodBuilder, builder, new Object[0]);
/*      */       }
/*      */     }
/*      */     
/*      */     private static final class RepeatedMessageFieldAccessor
/*      */       extends RepeatedFieldAccessor {
/*      */       private final Method newBuilderMethod;
/*      */       private final Method getBuilderMethodBuilder;
/*      */       
/*      */       RepeatedMessageFieldAccessor(Descriptors.FieldDescriptor descriptor, String camelCaseName, Class<? extends GeneratedMessageV3> messageClass, Class<? extends Builder> builderClass) {
/* 3004 */         super(descriptor, camelCaseName, messageClass, builderClass);
/*      */         
/* 3006 */         this.newBuilderMethod = GeneratedMessageV3.getMethodOrDie(this.type, "newBuilder", new Class[0]);
/* 3007 */         this.getBuilderMethodBuilder = GeneratedMessageV3.getMethodOrDie(builderClass, "get" + camelCaseName + "Builder", new Class[] { int.class });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       private Object coerceType(Object value) {
/* 3015 */         if (this.type.isInstance(value)) {
/* 3016 */           return value;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3022 */         return ((Message.Builder)GeneratedMessageV3.invokeOrDie(this.newBuilderMethod, null, new Object[0]))
/* 3023 */           .mergeFrom((Message)value)
/* 3024 */           .build();
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public void setRepeated(Builder builder, int index, Object value) {
/* 3030 */         super.setRepeated(builder, index, coerceType(value));
/*      */       }
/*      */       
/*      */       public void addRepeated(Builder builder, Object value) {
/* 3034 */         super.addRepeated(builder, coerceType(value));
/*      */       }
/*      */       
/*      */       public Message.Builder newBuilder() {
/* 3038 */         return (Message.Builder)GeneratedMessageV3.invokeOrDie(this.newBuilderMethod, null, new Object[0]);
/*      */       }
/*      */       
/*      */       public Message.Builder getRepeatedBuilder(Builder builder, int index)
/*      */       {
/* 3043 */         return (Message.Builder)GeneratedMessageV3.invokeOrDie(this.getBuilderMethodBuilder, builder, new Object[] {
/* 3044 */               Integer.valueOf(index) });
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Object writeReplace() throws ObjectStreamException {
/* 3056 */     return new GeneratedMessageLite.SerializedForm(this);
/*      */   }
/*      */   private static class OneofAccessor {
/*      */     private final Descriptors.Descriptor descriptor;
/*      */     private final Method caseMethod;
/*      */     private final Method caseMethodBuilder;
/*      */     private final Method clearMethod;
/*      */     private final Descriptors.FieldDescriptor fieldDescriptor; OneofAccessor(Descriptors.Descriptor descriptor, int oneofIndex, String camelCaseName, Class<? extends GeneratedMessageV3> messageClass, Class<? extends Builder> builderClass) { this.descriptor = descriptor; Descriptors.OneofDescriptor oneofDescriptor = descriptor.getOneofs().get(oneofIndex); if (oneofDescriptor.isSynthetic()) { this.caseMethod = null; this.caseMethodBuilder = null; this.fieldDescriptor = oneofDescriptor.getFields().get(0); } else { this.caseMethod = GeneratedMessageV3.getMethodOrDie(messageClass, "get" + camelCaseName + "Case", new Class[0]); this.caseMethodBuilder = GeneratedMessageV3.getMethodOrDie(builderClass, "get" + camelCaseName + "Case", new Class[0]); this.fieldDescriptor = null; }  this.clearMethod = GeneratedMessageV3.getMethodOrDie(builderClass, "clear" + camelCaseName, new Class[0]); } public boolean has(GeneratedMessageV3 message) { if (this.fieldDescriptor != null) return message.hasField(this.fieldDescriptor);  if (((Internal.EnumLite)GeneratedMessageV3.invokeOrDie(this.caseMethod, message, new Object[0])).getNumber() == 0) return false;  return true; } public boolean has(Builder builder) { if (this.fieldDescriptor != null) return builder.hasField(this.fieldDescriptor);  if (((Internal.EnumLite)GeneratedMessageV3.invokeOrDie(this.caseMethodBuilder, builder, new Object[0])).getNumber() == 0) return false;  return true; } public Descriptors.FieldDescriptor get(GeneratedMessageV3 message) { if (this.fieldDescriptor != null) return message.hasField(this.fieldDescriptor) ? this.fieldDescriptor : null;  int fieldNumber = ((Internal.EnumLite)GeneratedMessageV3.invokeOrDie(this.caseMethod, message, new Object[0])).getNumber(); if (fieldNumber > 0) return this.descriptor.findFieldByNumber(fieldNumber);  return null; } public Descriptors.FieldDescriptor get(Builder builder) { if (this.fieldDescriptor != null) return builder.hasField(this.fieldDescriptor) ? this.fieldDescriptor : null;  int fieldNumber = ((Internal.EnumLite)GeneratedMessageV3.invokeOrDie(this.caseMethodBuilder, builder, new Object[0])).getNumber(); if (fieldNumber > 0)
/* 3064 */         return this.descriptor.findFieldByNumber(fieldNumber);  return null; } public void clear(Builder builder) { GeneratedMessageV3.invokeOrDie(this.clearMethod, builder, new Object[0]); } } private static <MessageType extends ExtendableMessage<MessageType>, T> Extension<MessageType, T> checkNotLite(ExtensionLite<MessageType, T> extension) { if (extension.isLite()) {
/* 3065 */       throw new IllegalArgumentException("Expected non-lite extension.");
/*      */     }
/*      */     
/* 3068 */     return (Extension<MessageType, T>)extension; }
/*      */ 
/*      */   
/*      */   protected static int computeStringSize(int fieldNumber, Object value) {
/* 3072 */     if (value instanceof String) {
/* 3073 */       return CodedOutputStream.computeStringSize(fieldNumber, (String)value);
/*      */     }
/* 3075 */     return CodedOutputStream.computeBytesSize(fieldNumber, (ByteString)value);
/*      */   }
/*      */ 
/*      */   
/*      */   protected static int computeStringSizeNoTag(Object value) {
/* 3080 */     if (value instanceof String) {
/* 3081 */       return CodedOutputStream.computeStringSizeNoTag((String)value);
/*      */     }
/* 3083 */     return CodedOutputStream.computeBytesSizeNoTag((ByteString)value);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected static void writeString(CodedOutputStream output, int fieldNumber, Object value) throws IOException {
/* 3089 */     if (value instanceof String) {
/* 3090 */       output.writeString(fieldNumber, (String)value);
/*      */     } else {
/* 3092 */       output.writeBytes(fieldNumber, (ByteString)value);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected static void writeStringNoTag(CodedOutputStream output, Object value) throws IOException {
/* 3098 */     if (value instanceof String) {
/* 3099 */       output.writeStringNoTag((String)value);
/*      */     } else {
/* 3101 */       output.writeBytesNoTag((ByteString)value);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static <V> void serializeIntegerMapTo(CodedOutputStream out, MapField<Integer, V> field, MapEntry<Integer, V> defaultEntry, int fieldNumber) throws IOException {
/* 3110 */     Map<Integer, V> m = field.getMap();
/* 3111 */     if (!out.isSerializationDeterministic()) {
/* 3112 */       serializeMapTo(out, m, defaultEntry, fieldNumber);
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 3117 */     int[] keys = new int[m.size()];
/* 3118 */     int index = 0;
/* 3119 */     for (Iterator<Integer> iterator = m.keySet().iterator(); iterator.hasNext(); ) { int k = ((Integer)iterator.next()).intValue();
/* 3120 */       keys[index++] = k; }
/*      */     
/* 3122 */     Arrays.sort(keys);
/* 3123 */     for (int key : keys) {
/* 3124 */       out.writeMessage(fieldNumber, defaultEntry
/* 3125 */           .newBuilderForType()
/* 3126 */           .setKey(Integer.valueOf(key))
/* 3127 */           .setValue(m.get(Integer.valueOf(key)))
/* 3128 */           .build());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static <V> void serializeLongMapTo(CodedOutputStream out, MapField<Long, V> field, MapEntry<Long, V> defaultEntry, int fieldNumber) throws IOException {
/* 3138 */     Map<Long, V> m = field.getMap();
/* 3139 */     if (!out.isSerializationDeterministic()) {
/* 3140 */       serializeMapTo(out, m, defaultEntry, fieldNumber);
/*      */       
/*      */       return;
/*      */     } 
/* 3144 */     long[] keys = new long[m.size()];
/* 3145 */     int index = 0;
/* 3146 */     for (Iterator<Long> iterator = m.keySet().iterator(); iterator.hasNext(); ) { long k = ((Long)iterator.next()).longValue();
/* 3147 */       keys[index++] = k; }
/*      */     
/* 3149 */     Arrays.sort(keys);
/* 3150 */     for (long key : keys) {
/* 3151 */       out.writeMessage(fieldNumber, defaultEntry
/* 3152 */           .newBuilderForType()
/* 3153 */           .setKey(Long.valueOf(key))
/* 3154 */           .setValue(m.get(Long.valueOf(key)))
/* 3155 */           .build());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static <V> void serializeStringMapTo(CodedOutputStream out, MapField<String, V> field, MapEntry<String, V> defaultEntry, int fieldNumber) throws IOException {
/* 3165 */     Map<String, V> m = field.getMap();
/* 3166 */     if (!out.isSerializationDeterministic()) {
/* 3167 */       serializeMapTo(out, m, defaultEntry, fieldNumber);
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 3173 */     String[] keys = new String[m.size()];
/* 3174 */     keys = (String[])m.keySet().toArray((Object[])keys);
/* 3175 */     Arrays.sort((Object[])keys);
/* 3176 */     for (String key : keys) {
/* 3177 */       out.writeMessage(fieldNumber, defaultEntry
/* 3178 */           .newBuilderForType()
/* 3179 */           .setKey(key)
/* 3180 */           .setValue(m.get(key))
/* 3181 */           .build());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static <V> void serializeBooleanMapTo(CodedOutputStream out, MapField<Boolean, V> field, MapEntry<Boolean, V> defaultEntry, int fieldNumber) throws IOException {
/* 3191 */     Map<Boolean, V> m = field.getMap();
/* 3192 */     if (!out.isSerializationDeterministic()) {
/* 3193 */       serializeMapTo(out, m, defaultEntry, fieldNumber);
/*      */       return;
/*      */     } 
/* 3196 */     maybeSerializeBooleanEntryTo(out, m, defaultEntry, fieldNumber, false);
/* 3197 */     maybeSerializeBooleanEntryTo(out, m, defaultEntry, fieldNumber, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static <V> void maybeSerializeBooleanEntryTo(CodedOutputStream out, Map<Boolean, V> m, MapEntry<Boolean, V> defaultEntry, int fieldNumber, boolean key) throws IOException {
/* 3207 */     if (m.containsKey(Boolean.valueOf(key))) {
/* 3208 */       out.writeMessage(fieldNumber, defaultEntry
/* 3209 */           .newBuilderForType()
/* 3210 */           .setKey(Boolean.valueOf(key))
/* 3211 */           .setValue(m.get(Boolean.valueOf(key)))
/* 3212 */           .build());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static <K, V> void serializeMapTo(CodedOutputStream out, Map<K, V> m, MapEntry<K, V> defaultEntry, int fieldNumber) throws IOException {
/* 3223 */     for (Map.Entry<K, V> entry : m.entrySet())
/* 3224 */       out.writeMessage(fieldNumber, defaultEntry
/* 3225 */           .newBuilderForType()
/* 3226 */           .setKey(entry.getKey())
/* 3227 */           .setValue(entry.getValue())
/* 3228 */           .build()); 
/*      */   }
/*      */   
/*      */   protected abstract FieldAccessorTable internalGetFieldAccessorTable();
/*      */   
/*      */   protected abstract Message.Builder newBuilderForType(BuilderParent paramBuilderParent);
/*      */   
/*      */   static interface ExtensionDescriptorRetriever {
/*      */     Descriptors.FieldDescriptor getDescriptor();
/*      */   }
/*      */   
/*      */   public static interface ExtendableMessageOrBuilder<MessageType extends ExtendableMessage> extends MessageOrBuilder {
/*      */     Message getDefaultInstanceForType();
/*      */     
/*      */     <Type> boolean hasExtension(ExtensionLite<MessageType, Type> param1ExtensionLite);
/*      */     
/*      */     <Type> int getExtensionCount(ExtensionLite<MessageType, List<Type>> param1ExtensionLite);
/*      */     
/*      */     <Type> Type getExtension(ExtensionLite<MessageType, Type> param1ExtensionLite);
/*      */     
/*      */     <Type> Type getExtension(ExtensionLite<MessageType, List<Type>> param1ExtensionLite, int param1Int);
/*      */     
/*      */     <Type> boolean hasExtension(Extension<MessageType, Type> param1Extension);
/*      */     
/*      */     <Type> boolean hasExtension(GeneratedMessage.GeneratedExtension<MessageType, Type> param1GeneratedExtension);
/*      */     
/*      */     <Type> int getExtensionCount(Extension<MessageType, List<Type>> param1Extension);
/*      */     
/*      */     <Type> int getExtensionCount(GeneratedMessage.GeneratedExtension<MessageType, List<Type>> param1GeneratedExtension);
/*      */     
/*      */     <Type> Type getExtension(Extension<MessageType, Type> param1Extension);
/*      */     
/*      */     <Type> Type getExtension(GeneratedMessage.GeneratedExtension<MessageType, Type> param1GeneratedExtension);
/*      */     
/*      */     <Type> Type getExtension(Extension<MessageType, List<Type>> param1Extension, int param1Int);
/*      */     
/*      */     <Type> Type getExtension(GeneratedMessage.GeneratedExtension<MessageType, List<Type>> param1GeneratedExtension, int param1Int);
/*      */   }
/*      */   
/*      */   protected static interface BuilderParent extends AbstractMessage.BuilderParent {}
/*      */   
/*      */   private static interface FieldAccessor {
/*      */     Object get(GeneratedMessageV3 param1GeneratedMessageV3);
/*      */     
/*      */     Object get(Builder param1Builder);
/*      */     
/*      */     Object getRaw(GeneratedMessageV3 param1GeneratedMessageV3);
/*      */     
/*      */     Object getRaw(Builder param1Builder);
/*      */     
/*      */     void set(Builder param1Builder, Object param1Object);
/*      */     
/*      */     Object getRepeated(GeneratedMessageV3 param1GeneratedMessageV3, int param1Int);
/*      */     
/*      */     Object getRepeated(Builder param1Builder, int param1Int);
/*      */     
/*      */     Object getRepeatedRaw(GeneratedMessageV3 param1GeneratedMessageV3, int param1Int);
/*      */     
/*      */     Object getRepeatedRaw(Builder param1Builder, int param1Int);
/*      */     
/*      */     void setRepeated(Builder param1Builder, int param1Int, Object param1Object);
/*      */     
/*      */     void addRepeated(Builder param1Builder, Object param1Object);
/*      */     
/*      */     boolean has(GeneratedMessageV3 param1GeneratedMessageV3);
/*      */     
/*      */     boolean has(Builder param1Builder);
/*      */     
/*      */     int getRepeatedCount(GeneratedMessageV3 param1GeneratedMessageV3);
/*      */     
/*      */     int getRepeatedCount(Builder param1Builder);
/*      */     
/*      */     void clear(Builder param1Builder);
/*      */     
/*      */     Message.Builder newBuilder();
/*      */     
/*      */     Message.Builder getBuilder(Builder param1Builder);
/*      */     
/*      */     Message.Builder getRepeatedBuilder(Builder param1Builder, int param1Int);
/*      */   }
/*      */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\GeneratedMessageV3.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */