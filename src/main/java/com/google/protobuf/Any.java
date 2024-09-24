/*      */ package com.google.protobuf;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.nio.ByteBuffer;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class Any
/*      */   extends GeneratedMessageV3
/*      */   implements AnyOrBuilder
/*      */ {
/*      */   private static final long serialVersionUID = 0L;
/*      */   private volatile Message cachedUnpackValue;
/*      */   public static final int TYPE_URL_FIELD_NUMBER = 1;
/*      */   private volatile Object typeUrl_;
/*      */   public static final int VALUE_FIELD_NUMBER = 2;
/*      */   private ByteString value_;
/*      */   private byte memoizedIsInitialized;
/*      */   
/*      */   private Any(GeneratedMessageV3.Builder<?> builder) {
/*   85 */     super(builder);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  340 */     this.memoizedIsInitialized = -1; } protected Object newInstance(UnusedPrivateParameter unused) { return new Any(); } public final UnknownFieldSet getUnknownFields() { return this.unknownFields; } private Any(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException { this(); if (extensionRegistry == null) throw new NullPointerException();  UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(); try { boolean done = false; while (!done) { String s; int tag = input.readTag(); switch (tag) { case 0: done = true; continue;case 10: s = input.readStringRequireUtf8(); this.typeUrl_ = s; continue;case 18: this.value_ = input.readBytes(); continue; }  if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) done = true;  }  } catch (InvalidProtocolBufferException e) { throw e.setUnfinishedMessage(this); } catch (IOException e) { throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(this); } finally { this.unknownFields = unknownFields.build(); makeExtensionsImmutable(); }  } public static final Descriptors.Descriptor getDescriptor() { return AnyProto.internal_static_google_protobuf_Any_descriptor; } private Any() { this.memoizedIsInitialized = -1; this.typeUrl_ = ""; this.value_ = ByteString.EMPTY; }
/*      */   protected FieldAccessorTable internalGetFieldAccessorTable() { return AnyProto.internal_static_google_protobuf_Any_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)Any.class, (Class)Builder.class); }
/*      */   private static String getTypeUrl(String typeUrlPrefix, Descriptors.Descriptor descriptor) { return typeUrlPrefix.endsWith("/") ? (typeUrlPrefix + descriptor.getFullName()) : (typeUrlPrefix + "/" + descriptor.getFullName()); }
/*  343 */   private static String getTypeNameFromTypeUrl(String typeUrl) { int pos = typeUrl.lastIndexOf('/'); return (pos == -1) ? "" : typeUrl.substring(pos + 1); } public final boolean isInitialized() { byte isInitialized = this.memoizedIsInitialized;
/*  344 */     if (isInitialized == 1) return true; 
/*  345 */     if (isInitialized == 0) return false;
/*      */     
/*  347 */     this.memoizedIsInitialized = 1;
/*  348 */     return true; } public static <T extends Message> Any pack(T message) { return newBuilder().setTypeUrl(getTypeUrl("type.googleapis.com", message.getDescriptorForType())).setValue(message.toByteString()).build(); } public static <T extends Message> Any pack(T message, String typeUrlPrefix) { return newBuilder().setTypeUrl(getTypeUrl(typeUrlPrefix, message.getDescriptorForType())).setValue(message.toByteString()).build(); }
/*      */   public <T extends Message> boolean is(Class<T> clazz) { Message message = Internal.<Message>getDefaultInstance(clazz); return getTypeNameFromTypeUrl(getTypeUrl()).equals(message.getDescriptorForType().getFullName()); }
/*      */   public <T extends Message> T unpack(Class<T> clazz) throws InvalidProtocolBufferException { boolean invalidClazz = false; if (this.cachedUnpackValue != null) { if (this.cachedUnpackValue.getClass() == clazz) return (T)this.cachedUnpackValue;  invalidClazz = true; }  if (invalidClazz || !is(clazz)) throw new InvalidProtocolBufferException("Type of the Any message does not match the given class.");  Message message1 = Internal.<Message>getDefaultInstance(clazz); Message message2 = message1.getParserForType().parseFrom(getValue()); this.cachedUnpackValue = message2; return (T)message2; }
/*      */   public String getTypeUrl() { Object ref = this.typeUrl_; if (ref instanceof String) return (String)ref;  ByteString bs = (ByteString)ref; String s = bs.toStringUtf8(); this.typeUrl_ = s; return s; }
/*      */   public ByteString getTypeUrlBytes() { Object ref = this.typeUrl_; if (ref instanceof String) { ByteString b = ByteString.copyFromUtf8((String)ref); this.typeUrl_ = b; return b; }  return (ByteString)ref; }
/*      */   public ByteString getValue() { return this.value_; }
/*  354 */   public void writeTo(CodedOutputStream output) throws IOException { if (!getTypeUrlBytes().isEmpty()) {
/*  355 */       GeneratedMessageV3.writeString(output, 1, this.typeUrl_);
/*      */     }
/*  357 */     if (!this.value_.isEmpty()) {
/*  358 */       output.writeBytes(2, this.value_);
/*      */     }
/*  360 */     this.unknownFields.writeTo(output); }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSerializedSize() {
/*  365 */     int size = this.memoizedSize;
/*  366 */     if (size != -1) return size;
/*      */     
/*  368 */     size = 0;
/*  369 */     if (!getTypeUrlBytes().isEmpty()) {
/*  370 */       size += GeneratedMessageV3.computeStringSize(1, this.typeUrl_);
/*      */     }
/*  372 */     if (!this.value_.isEmpty()) {
/*  373 */       size += 
/*  374 */         CodedOutputStream.computeBytesSize(2, this.value_);
/*      */     }
/*  376 */     size += this.unknownFields.getSerializedSize();
/*  377 */     this.memoizedSize = size;
/*  378 */     return size;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean equals(Object obj) {
/*  383 */     if (obj == this) {
/*  384 */       return true;
/*      */     }
/*  386 */     if (!(obj instanceof Any)) {
/*  387 */       return super.equals(obj);
/*      */     }
/*  389 */     Any other = (Any)obj;
/*      */ 
/*      */     
/*  392 */     if (!getTypeUrl().equals(other.getTypeUrl())) return false;
/*      */     
/*  394 */     if (!getValue().equals(other.getValue())) return false; 
/*  395 */     if (!this.unknownFields.equals(other.unknownFields)) return false; 
/*  396 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public int hashCode() {
/*  401 */     if (this.memoizedHashCode != 0) {
/*  402 */       return this.memoizedHashCode;
/*      */     }
/*  404 */     int hash = 41;
/*  405 */     hash = 19 * hash + getDescriptor().hashCode();
/*  406 */     hash = 37 * hash + 1;
/*  407 */     hash = 53 * hash + getTypeUrl().hashCode();
/*  408 */     hash = 37 * hash + 2;
/*  409 */     hash = 53 * hash + getValue().hashCode();
/*  410 */     hash = 29 * hash + this.unknownFields.hashCode();
/*  411 */     this.memoizedHashCode = hash;
/*  412 */     return hash;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Any parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
/*  418 */     return PARSER.parseFrom(data);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Any parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  424 */     return PARSER.parseFrom(data, extensionRegistry);
/*      */   }
/*      */ 
/*      */   
/*      */   public static Any parseFrom(ByteString data) throws InvalidProtocolBufferException {
/*  429 */     return PARSER.parseFrom(data);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Any parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  435 */     return PARSER.parseFrom(data, extensionRegistry);
/*      */   }
/*      */   
/*      */   public static Any parseFrom(byte[] data) throws InvalidProtocolBufferException {
/*  439 */     return PARSER.parseFrom(data);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Any parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  445 */     return PARSER.parseFrom(data, extensionRegistry);
/*      */   }
/*      */   
/*      */   public static Any parseFrom(InputStream input) throws IOException {
/*  449 */     return 
/*  450 */       GeneratedMessageV3.<Any>parseWithIOException(PARSER, input);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Any parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  456 */     return 
/*  457 */       GeneratedMessageV3.<Any>parseWithIOException(PARSER, input, extensionRegistry);
/*      */   }
/*      */   
/*      */   public static Any parseDelimitedFrom(InputStream input) throws IOException {
/*  461 */     return 
/*  462 */       GeneratedMessageV3.<Any>parseDelimitedWithIOException(PARSER, input);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Any parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  468 */     return 
/*  469 */       GeneratedMessageV3.<Any>parseDelimitedWithIOException(PARSER, input, extensionRegistry);
/*      */   }
/*      */ 
/*      */   
/*      */   public static Any parseFrom(CodedInputStream input) throws IOException {
/*  474 */     return 
/*  475 */       GeneratedMessageV3.<Any>parseWithIOException(PARSER, input);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Any parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  481 */     return 
/*  482 */       GeneratedMessageV3.<Any>parseWithIOException(PARSER, input, extensionRegistry);
/*      */   }
/*      */   
/*      */   public Builder newBuilderForType() {
/*  486 */     return newBuilder();
/*      */   } public static Builder newBuilder() {
/*  488 */     return DEFAULT_INSTANCE.toBuilder();
/*      */   }
/*      */   public static Builder newBuilder(Any prototype) {
/*  491 */     return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
/*      */   }
/*      */   
/*      */   public Builder toBuilder() {
/*  495 */     return (this == DEFAULT_INSTANCE) ? new Builder() : (new Builder())
/*  496 */       .mergeFrom(this);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected Builder newBuilderForType(BuilderParent parent) {
/*  502 */     Builder builder = new Builder(parent);
/*  503 */     return builder;
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
/*      */   public static final class Builder
/*      */     extends GeneratedMessageV3.Builder<Builder>
/*      */     implements AnyOrBuilder
/*      */   {
/*      */     private Object typeUrl_;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private ByteString value_;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static final Descriptors.Descriptor getDescriptor() {
/*  583 */       return AnyProto.internal_static_google_protobuf_Any_descriptor;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected FieldAccessorTable internalGetFieldAccessorTable() {
/*  589 */       return AnyProto.internal_static_google_protobuf_Any_fieldAccessorTable
/*  590 */         .ensureFieldAccessorsInitialized((Class)Any.class, (Class)Builder.class);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private Builder()
/*      */     {
/*  728 */       this.typeUrl_ = "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  934 */       this.value_ = ByteString.EMPTY; maybeForceBuilderInitialization(); } private Builder(BuilderParent parent) { super(parent); this.typeUrl_ = ""; this.value_ = ByteString.EMPTY; maybeForceBuilderInitialization(); } private void maybeForceBuilderInitialization() { if (GeneratedMessageV3.alwaysUseFieldBuilders); }
/*      */     public Builder clear() { super.clear(); this.typeUrl_ = ""; this.value_ = ByteString.EMPTY; return this; }
/*      */     public Descriptors.Descriptor getDescriptorForType() { return AnyProto.internal_static_google_protobuf_Any_descriptor; }
/*      */     public Any getDefaultInstanceForType() { return Any.getDefaultInstance(); }
/*      */     public Any build() { Any result = buildPartial(); if (!result.isInitialized())
/*      */         throw newUninitializedMessageException(result);  return result; }
/*      */     public Any buildPartial() { Any result = new Any(this); result.typeUrl_ = this.typeUrl_; result.value_ = this.value_; onBuilt(); return result; }
/*      */     public Builder clone() { return super.clone(); }
/*      */     public Builder setField(Descriptors.FieldDescriptor field, Object value) { return super.setField(field, value); }
/*      */     public Builder clearField(Descriptors.FieldDescriptor field) { return super.clearField(field); }
/*      */     public Builder clearOneof(Descriptors.OneofDescriptor oneof) { return super.clearOneof(oneof); }
/*  945 */     public ByteString getValue() { return this.value_; } public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) { return super.setRepeatedField(field, index, value); }
/*      */     public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) { return super.addRepeatedField(field, value); }
/*      */     public Builder mergeFrom(Message other) { if (other instanceof Any)
/*      */         return mergeFrom((Any)other);  super.mergeFrom(other); return this; }
/*      */     public Builder mergeFrom(Any other) { if (other == Any.getDefaultInstance())
/*      */         return this;  if (!other.getTypeUrl().isEmpty()) {
/*      */         this.typeUrl_ = other.typeUrl_; onChanged();
/*      */       }  if (other.getValue() != ByteString.EMPTY)
/*      */         setValue(other.getValue());  mergeUnknownFields(other.unknownFields);
/*      */       onChanged();
/*      */       return this; }
/*      */     public final boolean isInitialized() { return true; }
/*  957 */     public Builder setValue(ByteString value) { if (value == null) {
/*  958 */         throw new NullPointerException();
/*      */       }
/*      */       
/*  961 */       this.value_ = value;
/*  962 */       onChanged();
/*  963 */       return this; } public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException { Any parsedMessage = null; try { parsedMessage = Any.PARSER.parsePartialFrom(input, extensionRegistry); } catch (InvalidProtocolBufferException e) { parsedMessage = (Any)e.getUnfinishedMessage(); throw e.unwrapIOException(); } finally { if (parsedMessage != null)
/*      */           mergeFrom(parsedMessage);  }
/*      */        return this; }
/*      */     public String getTypeUrl() { Object ref = this.typeUrl_; if (!(ref instanceof String)) { ByteString bs = (ByteString)ref; String s = bs.toStringUtf8(); this.typeUrl_ = s; return s; }
/*      */        return (String)ref; }
/*      */     public ByteString getTypeUrlBytes() { Object ref = this.typeUrl_; if (ref instanceof String) { ByteString b = ByteString.copyFromUtf8((String)ref); this.typeUrl_ = b; return b; }
/*      */        return (ByteString)ref; }
/*      */     public Builder setTypeUrl(String value) { if (value == null)
/*      */         throw new NullPointerException();  this.typeUrl_ = value; onChanged(); return this; }
/*      */     public Builder clearTypeUrl() { this.typeUrl_ = Any.getDefaultInstance().getTypeUrl(); onChanged(); return this; }
/*      */     public Builder setTypeUrlBytes(ByteString value) { if (value == null)
/*      */         throw new NullPointerException();  AbstractMessageLite.checkByteStringIsUtf8(value); this.typeUrl_ = value; onChanged(); return this; }
/*  975 */     public Builder clearValue() { this.value_ = Any.getDefaultInstance().getValue();
/*  976 */       onChanged();
/*  977 */       return this; }
/*      */ 
/*      */ 
/*      */     
/*      */     public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
/*  982 */       return super.setUnknownFields(unknownFields);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
/*  988 */       return super.mergeUnknownFields(unknownFields);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  998 */   private static final Any DEFAULT_INSTANCE = new Any();
/*      */ 
/*      */   
/*      */   public static Any getDefaultInstance() {
/* 1002 */     return DEFAULT_INSTANCE;
/*      */   }
/*      */ 
/*      */   
/* 1006 */   private static final Parser<Any> PARSER = new AbstractParser<Any>()
/*      */     {
/*      */ 
/*      */       
/*      */       public Any parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException
/*      */       {
/* 1012 */         return new Any(input, extensionRegistry);
/*      */       }
/*      */     };
/*      */   
/*      */   public static Parser<Any> parser() {
/* 1017 */     return PARSER;
/*      */   }
/*      */ 
/*      */   
/*      */   public Parser<Any> getParserForType() {
/* 1022 */     return PARSER;
/*      */   }
/*      */ 
/*      */   
/*      */   public Any getDefaultInstanceForType() {
/* 1027 */     return DEFAULT_INSTANCE;
/*      */   }
/*      */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\Any.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */