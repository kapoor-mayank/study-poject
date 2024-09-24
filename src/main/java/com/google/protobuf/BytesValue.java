/*     */ package com.google.protobuf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.ByteBuffer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class BytesValue
/*     */   extends GeneratedMessageV3
/*     */   implements BytesValueOrBuilder
/*     */ {
/*     */   private static final long serialVersionUID = 0L;
/*     */   public static final int VALUE_FIELD_NUMBER = 1;
/*     */   private ByteString value_;
/*     */   private byte memoizedIsInitialized;
/*     */   
/*     */   private BytesValue(GeneratedMessageV3.Builder<?> builder) {
/*  21 */     super(builder);
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
/* 109 */     this.memoizedIsInitialized = -1; } protected Object newInstance(UnusedPrivateParameter unused) { return new BytesValue(); } public final UnknownFieldSet getUnknownFields() { return this.unknownFields; } private BytesValue() { this.memoizedIsInitialized = -1; this.value_ = ByteString.EMPTY; }
/*     */   private BytesValue(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException { this(); if (extensionRegistry == null)
/*     */       throw new NullPointerException();  UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(); try { boolean done = false; while (!done) { int tag = input.readTag(); switch (tag) { case 0: done = true; continue;case 10: this.value_ = input.readBytes(); continue; }  if (!parseUnknownField(input, unknownFields, extensionRegistry, tag))
/* 112 */           done = true;  }  } catch (InvalidProtocolBufferException e) { throw e.setUnfinishedMessage(this); } catch (IOException e) { throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(this); } finally { this.unknownFields = unknownFields.build(); makeExtensionsImmutable(); }  } public final boolean isInitialized() { byte isInitialized = this.memoizedIsInitialized;
/* 113 */     if (isInitialized == 1) return true; 
/* 114 */     if (isInitialized == 0) return false;
/*     */     
/* 116 */     this.memoizedIsInitialized = 1;
/* 117 */     return true; } public static final Descriptors.Descriptor getDescriptor() { return WrappersProto.internal_static_google_protobuf_BytesValue_descriptor; } protected FieldAccessorTable internalGetFieldAccessorTable() {
/*     */     return WrappersProto.internal_static_google_protobuf_BytesValue_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)BytesValue.class, (Class)Builder.class);
/*     */   } public ByteString getValue() {
/*     */     return this.value_;
/*     */   }
/*     */   public void writeTo(CodedOutputStream output) throws IOException {
/* 123 */     if (!this.value_.isEmpty()) {
/* 124 */       output.writeBytes(1, this.value_);
/*     */     }
/* 126 */     this.unknownFields.writeTo(output);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSerializedSize() {
/* 131 */     int size = this.memoizedSize;
/* 132 */     if (size != -1) return size;
/*     */     
/* 134 */     size = 0;
/* 135 */     if (!this.value_.isEmpty()) {
/* 136 */       size += 
/* 137 */         CodedOutputStream.computeBytesSize(1, this.value_);
/*     */     }
/* 139 */     size += this.unknownFields.getSerializedSize();
/* 140 */     this.memoizedSize = size;
/* 141 */     return size;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 146 */     if (obj == this) {
/* 147 */       return true;
/*     */     }
/* 149 */     if (!(obj instanceof BytesValue)) {
/* 150 */       return super.equals(obj);
/*     */     }
/* 152 */     BytesValue other = (BytesValue)obj;
/*     */ 
/*     */     
/* 155 */     if (!getValue().equals(other.getValue())) return false; 
/* 156 */     if (!this.unknownFields.equals(other.unknownFields)) return false; 
/* 157 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 162 */     if (this.memoizedHashCode != 0) {
/* 163 */       return this.memoizedHashCode;
/*     */     }
/* 165 */     int hash = 41;
/* 166 */     hash = 19 * hash + getDescriptor().hashCode();
/* 167 */     hash = 37 * hash + 1;
/* 168 */     hash = 53 * hash + getValue().hashCode();
/* 169 */     hash = 29 * hash + this.unknownFields.hashCode();
/* 170 */     this.memoizedHashCode = hash;
/* 171 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static BytesValue parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
/* 177 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static BytesValue parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 183 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public static BytesValue parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 188 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static BytesValue parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 194 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static BytesValue parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 198 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static BytesValue parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 204 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static BytesValue parseFrom(InputStream input) throws IOException {
/* 208 */     return 
/* 209 */       GeneratedMessageV3.<BytesValue>parseWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static BytesValue parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 215 */     return 
/* 216 */       GeneratedMessageV3.<BytesValue>parseWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static BytesValue parseDelimitedFrom(InputStream input) throws IOException {
/* 220 */     return 
/* 221 */       GeneratedMessageV3.<BytesValue>parseDelimitedWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static BytesValue parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 227 */     return 
/* 228 */       GeneratedMessageV3.<BytesValue>parseDelimitedWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public static BytesValue parseFrom(CodedInputStream input) throws IOException {
/* 233 */     return 
/* 234 */       GeneratedMessageV3.<BytesValue>parseWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static BytesValue parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 240 */     return 
/* 241 */       GeneratedMessageV3.<BytesValue>parseWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */   
/*     */   public Builder newBuilderForType() {
/* 245 */     return newBuilder();
/*     */   } public static Builder newBuilder() {
/* 247 */     return DEFAULT_INSTANCE.toBuilder();
/*     */   }
/*     */   public static Builder newBuilder(BytesValue prototype) {
/* 250 */     return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
/*     */   }
/*     */   
/*     */   public Builder toBuilder() {
/* 254 */     return (this == DEFAULT_INSTANCE) ? new Builder() : (new Builder())
/* 255 */       .mergeFrom(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Builder newBuilderForType(BuilderParent parent) {
/* 261 */     Builder builder = new Builder(parent);
/* 262 */     return builder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final class Builder
/*     */     extends GeneratedMessageV3.Builder<Builder>
/*     */     implements BytesValueOrBuilder
/*     */   {
/*     */     private ByteString value_;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static final Descriptors.Descriptor getDescriptor() {
/* 278 */       return WrappersProto.internal_static_google_protobuf_BytesValue_descriptor;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected FieldAccessorTable internalGetFieldAccessorTable() {
/* 284 */       return WrappersProto.internal_static_google_protobuf_BytesValue_fieldAccessorTable
/* 285 */         .ensureFieldAccessorsInitialized((Class)BytesValue.class, (Class)Builder.class);
/*     */     }
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
/*     */     private Builder()
/*     */     {
/* 416 */       this.value_ = ByteString.EMPTY; maybeForceBuilderInitialization(); } private Builder(BuilderParent parent) { super(parent); this.value_ = ByteString.EMPTY; maybeForceBuilderInitialization(); } private void maybeForceBuilderInitialization() { if (GeneratedMessageV3.alwaysUseFieldBuilders); }
/*     */     public Builder clear() { super.clear(); this.value_ = ByteString.EMPTY; return this; }
/*     */     public Descriptors.Descriptor getDescriptorForType() { return WrappersProto.internal_static_google_protobuf_BytesValue_descriptor; }
/*     */     public BytesValue getDefaultInstanceForType() { return BytesValue.getDefaultInstance(); }
/*     */     public BytesValue build() { BytesValue result = buildPartial(); if (!result.isInitialized())
/*     */         throw newUninitializedMessageException(result);  return result; }
/*     */     public BytesValue buildPartial() { BytesValue result = new BytesValue(this); result.value_ = this.value_;
/*     */       onBuilt();
/*     */       return result; }
/*     */     public Builder clone() { return super.clone(); }
/*     */     public Builder setField(Descriptors.FieldDescriptor field, Object value) { return super.setField(field, value); }
/* 427 */     public ByteString getValue() { return this.value_; }
/*     */      public Builder clearField(Descriptors.FieldDescriptor field) {
/*     */       return super.clearField(field);
/*     */     } public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
/*     */       return super.clearOneof(oneof);
/*     */     }
/*     */     public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
/*     */       return super.setRepeatedField(field, index, value);
/*     */     }
/*     */     public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
/*     */       return super.addRepeatedField(field, value);
/*     */     }
/* 439 */     public Builder setValue(ByteString value) { if (value == null) {
/* 440 */         throw new NullPointerException();
/*     */       }
/*     */       
/* 443 */       this.value_ = value;
/* 444 */       onChanged();
/* 445 */       return this; } public Builder mergeFrom(Message other) { if (other instanceof BytesValue)
/*     */         return mergeFrom((BytesValue)other);  super.mergeFrom(other); return this; }
/*     */     public Builder mergeFrom(BytesValue other) { if (other == BytesValue.getDefaultInstance())
/*     */         return this;  if (other.getValue() != ByteString.EMPTY)
/*     */         setValue(other.getValue());  mergeUnknownFields(other.unknownFields); onChanged(); return this; }
/*     */     public final boolean isInitialized() { return true; }
/*     */     public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException { BytesValue parsedMessage = null; try { parsedMessage = BytesValue.PARSER.parsePartialFrom(input, extensionRegistry); }
/*     */       catch (InvalidProtocolBufferException e) { parsedMessage = (BytesValue)e.getUnfinishedMessage(); throw e.unwrapIOException(); }
/*     */       finally
/*     */       { if (parsedMessage != null)
/*     */           mergeFrom(parsedMessage);  }
/*     */        return this; }
/* 457 */     public Builder clearValue() { this.value_ = BytesValue.getDefaultInstance().getValue();
/* 458 */       onChanged();
/* 459 */       return this; }
/*     */ 
/*     */ 
/*     */     
/*     */     public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
/* 464 */       return super.setUnknownFields(unknownFields);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
/* 470 */       return super.mergeUnknownFields(unknownFields);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 480 */   private static final BytesValue DEFAULT_INSTANCE = new BytesValue();
/*     */ 
/*     */   
/*     */   public static BytesValue getDefaultInstance() {
/* 484 */     return DEFAULT_INSTANCE;
/*     */   }
/*     */   
/*     */   public static BytesValue of(ByteString value) {
/* 488 */     return newBuilder().setValue(value).build();
/*     */   }
/*     */ 
/*     */   
/* 492 */   private static final Parser<BytesValue> PARSER = new AbstractParser<BytesValue>()
/*     */     {
/*     */ 
/*     */       
/*     */       public BytesValue parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException
/*     */       {
/* 498 */         return new BytesValue(input, extensionRegistry);
/*     */       }
/*     */     };
/*     */   
/*     */   public static Parser<BytesValue> parser() {
/* 503 */     return PARSER;
/*     */   }
/*     */ 
/*     */   
/*     */   public Parser<BytesValue> getParserForType() {
/* 508 */     return PARSER;
/*     */   }
/*     */ 
/*     */   
/*     */   public BytesValue getDefaultInstanceForType() {
/* 513 */     return DEFAULT_INSTANCE;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\BytesValue.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */