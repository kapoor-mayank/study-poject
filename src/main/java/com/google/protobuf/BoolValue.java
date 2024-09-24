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
/*     */ public final class BoolValue
/*     */   extends GeneratedMessageV3
/*     */   implements BoolValueOrBuilder
/*     */ {
/*     */   private static final long serialVersionUID = 0L;
/*     */   public static final int VALUE_FIELD_NUMBER = 1;
/*     */   private boolean value_;
/*     */   private byte memoizedIsInitialized;
/*     */   
/*     */   private BoolValue(GeneratedMessageV3.Builder<?> builder) {
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
/* 108 */     this.memoizedIsInitialized = -1; } protected Object newInstance(UnusedPrivateParameter unused) { return new BoolValue(); } public final UnknownFieldSet getUnknownFields() { return this.unknownFields; } private BoolValue() { this.memoizedIsInitialized = -1; }
/*     */   private BoolValue(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException { this(); if (extensionRegistry == null)
/*     */       throw new NullPointerException();  UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(); try { boolean done = false; while (!done) { int tag = input.readTag(); switch (tag) { case 0: done = true; continue;case 8: this.value_ = input.readBool(); continue; }  if (!parseUnknownField(input, unknownFields, extensionRegistry, tag))
/* 111 */           done = true;  }  } catch (InvalidProtocolBufferException e) { throw e.setUnfinishedMessage(this); } catch (IOException e) { throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(this); } finally { this.unknownFields = unknownFields.build(); makeExtensionsImmutable(); }  } public final boolean isInitialized() { byte isInitialized = this.memoizedIsInitialized;
/* 112 */     if (isInitialized == 1) return true; 
/* 113 */     if (isInitialized == 0) return false;
/*     */     
/* 115 */     this.memoizedIsInitialized = 1;
/* 116 */     return true; } public static final Descriptors.Descriptor getDescriptor() { return WrappersProto.internal_static_google_protobuf_BoolValue_descriptor; } protected FieldAccessorTable internalGetFieldAccessorTable() {
/*     */     return WrappersProto.internal_static_google_protobuf_BoolValue_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)BoolValue.class, (Class)Builder.class);
/*     */   } public boolean getValue() {
/*     */     return this.value_;
/*     */   }
/*     */   public void writeTo(CodedOutputStream output) throws IOException {
/* 122 */     if (this.value_) {
/* 123 */       output.writeBool(1, this.value_);
/*     */     }
/* 125 */     this.unknownFields.writeTo(output);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSerializedSize() {
/* 130 */     int size = this.memoizedSize;
/* 131 */     if (size != -1) return size;
/*     */     
/* 133 */     size = 0;
/* 134 */     if (this.value_) {
/* 135 */       size += 
/* 136 */         CodedOutputStream.computeBoolSize(1, this.value_);
/*     */     }
/* 138 */     size += this.unknownFields.getSerializedSize();
/* 139 */     this.memoizedSize = size;
/* 140 */     return size;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 145 */     if (obj == this) {
/* 146 */       return true;
/*     */     }
/* 148 */     if (!(obj instanceof BoolValue)) {
/* 149 */       return super.equals(obj);
/*     */     }
/* 151 */     BoolValue other = (BoolValue)obj;
/*     */     
/* 153 */     if (getValue() != other
/* 154 */       .getValue()) return false; 
/* 155 */     if (!this.unknownFields.equals(other.unknownFields)) return false; 
/* 156 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 161 */     if (this.memoizedHashCode != 0) {
/* 162 */       return this.memoizedHashCode;
/*     */     }
/* 164 */     int hash = 41;
/* 165 */     hash = 19 * hash + getDescriptor().hashCode();
/* 166 */     hash = 37 * hash + 1;
/* 167 */     hash = 53 * hash + Internal.hashBoolean(
/* 168 */         getValue());
/* 169 */     hash = 29 * hash + this.unknownFields.hashCode();
/* 170 */     this.memoizedHashCode = hash;
/* 171 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static BoolValue parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
/* 177 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static BoolValue parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 183 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public static BoolValue parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 188 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static BoolValue parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 194 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static BoolValue parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 198 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static BoolValue parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 204 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static BoolValue parseFrom(InputStream input) throws IOException {
/* 208 */     return 
/* 209 */       GeneratedMessageV3.<BoolValue>parseWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static BoolValue parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 215 */     return 
/* 216 */       GeneratedMessageV3.<BoolValue>parseWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static BoolValue parseDelimitedFrom(InputStream input) throws IOException {
/* 220 */     return 
/* 221 */       GeneratedMessageV3.<BoolValue>parseDelimitedWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static BoolValue parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 227 */     return 
/* 228 */       GeneratedMessageV3.<BoolValue>parseDelimitedWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public static BoolValue parseFrom(CodedInputStream input) throws IOException {
/* 233 */     return 
/* 234 */       GeneratedMessageV3.<BoolValue>parseWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static BoolValue parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 240 */     return 
/* 241 */       GeneratedMessageV3.<BoolValue>parseWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */   
/*     */   public Builder newBuilderForType() {
/* 245 */     return newBuilder();
/*     */   } public static Builder newBuilder() {
/* 247 */     return DEFAULT_INSTANCE.toBuilder();
/*     */   }
/*     */   public static Builder newBuilder(BoolValue prototype) {
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
/*     */     implements BoolValueOrBuilder
/*     */   {
/*     */     private boolean value_;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static final Descriptors.Descriptor getDescriptor() {
/* 278 */       return WrappersProto.internal_static_google_protobuf_BoolValue_descriptor;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected FieldAccessorTable internalGetFieldAccessorTable() {
/* 284 */       return WrappersProto.internal_static_google_protobuf_BoolValue_fieldAccessorTable
/* 285 */         .ensureFieldAccessorsInitialized((Class)BoolValue.class, (Class)Builder.class);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private Builder() {
/* 291 */       maybeForceBuilderInitialization();
/*     */     }
/*     */ 
/*     */     
/*     */     private Builder(BuilderParent parent) {
/* 296 */       super(parent);
/* 297 */       maybeForceBuilderInitialization();
/*     */     }
/*     */     private void maybeForceBuilderInitialization() {
/* 300 */       if (GeneratedMessageV3.alwaysUseFieldBuilders);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder clear() {
/* 306 */       super.clear();
/* 307 */       this.value_ = false;
/*     */       
/* 309 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Descriptors.Descriptor getDescriptorForType() {
/* 315 */       return WrappersProto.internal_static_google_protobuf_BoolValue_descriptor;
/*     */     }
/*     */ 
/*     */     
/*     */     public BoolValue getDefaultInstanceForType() {
/* 320 */       return BoolValue.getDefaultInstance();
/*     */     }
/*     */ 
/*     */     
/*     */     public BoolValue build() {
/* 325 */       BoolValue result = buildPartial();
/* 326 */       if (!result.isInitialized()) {
/* 327 */         throw newUninitializedMessageException(result);
/*     */       }
/* 329 */       return result;
/*     */     }
/*     */ 
/*     */     
/*     */     public BoolValue buildPartial() {
/* 334 */       BoolValue result = new BoolValue(this);
/* 335 */       result.value_ = this.value_;
/* 336 */       onBuilt();
/* 337 */       return result;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder clone() {
/* 342 */       return super.clone();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setField(Descriptors.FieldDescriptor field, Object value) {
/* 348 */       return super.setField(field, value);
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder clearField(Descriptors.FieldDescriptor field) {
/* 353 */       return super.clearField(field);
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
/* 358 */       return super.clearOneof(oneof);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
/* 364 */       return super.setRepeatedField(field, index, value);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
/* 370 */       return super.addRepeatedField(field, value);
/*     */     }
/*     */     
/*     */     public Builder mergeFrom(Message other) {
/* 374 */       if (other instanceof BoolValue) {
/* 375 */         return mergeFrom((BoolValue)other);
/*     */       }
/* 377 */       super.mergeFrom(other);
/* 378 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder mergeFrom(BoolValue other) {
/* 383 */       if (other == BoolValue.getDefaultInstance()) return this; 
/* 384 */       if (other.getValue()) {
/* 385 */         setValue(other.getValue());
/*     */       }
/* 387 */       mergeUnknownFields(other.unknownFields);
/* 388 */       onChanged();
/* 389 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public final boolean isInitialized() {
/* 394 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 402 */       BoolValue parsedMessage = null;
/*     */       try {
/* 404 */         parsedMessage = BoolValue.PARSER.parsePartialFrom(input, extensionRegistry);
/* 405 */       } catch (InvalidProtocolBufferException e) {
/* 406 */         parsedMessage = (BoolValue)e.getUnfinishedMessage();
/* 407 */         throw e.unwrapIOException();
/*     */       } finally {
/* 409 */         if (parsedMessage != null) {
/* 410 */           mergeFrom(parsedMessage);
/*     */         }
/*     */       } 
/* 413 */       return this;
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
/*     */     public boolean getValue() {
/* 427 */       return this.value_;
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
/*     */     public Builder setValue(boolean value) {
/* 440 */       this.value_ = value;
/* 441 */       onChanged();
/* 442 */       return this;
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
/*     */     public Builder clearValue() {
/* 454 */       this.value_ = false;
/* 455 */       onChanged();
/* 456 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
/* 461 */       return super.setUnknownFields(unknownFields);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
/* 467 */       return super.mergeUnknownFields(unknownFields);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 477 */   private static final BoolValue DEFAULT_INSTANCE = new BoolValue();
/*     */ 
/*     */   
/*     */   public static BoolValue getDefaultInstance() {
/* 481 */     return DEFAULT_INSTANCE;
/*     */   }
/*     */   
/*     */   public static BoolValue of(boolean value) {
/* 485 */     return newBuilder().setValue(value).build();
/*     */   }
/*     */ 
/*     */   
/* 489 */   private static final Parser<BoolValue> PARSER = new AbstractParser<BoolValue>()
/*     */     {
/*     */ 
/*     */       
/*     */       public BoolValue parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException
/*     */       {
/* 495 */         return new BoolValue(input, extensionRegistry);
/*     */       }
/*     */     };
/*     */   
/*     */   public static Parser<BoolValue> parser() {
/* 500 */     return PARSER;
/*     */   }
/*     */ 
/*     */   
/*     */   public Parser<BoolValue> getParserForType() {
/* 505 */     return PARSER;
/*     */   }
/*     */ 
/*     */   
/*     */   public BoolValue getDefaultInstanceForType() {
/* 510 */     return DEFAULT_INSTANCE;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\BoolValue.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */