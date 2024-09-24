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
/*     */ public final class UInt32Value
/*     */   extends GeneratedMessageV3
/*     */   implements UInt32ValueOrBuilder
/*     */ {
/*     */   private static final long serialVersionUID = 0L;
/*     */   public static final int VALUE_FIELD_NUMBER = 1;
/*     */   private int value_;
/*     */   private byte memoizedIsInitialized;
/*     */   
/*     */   private UInt32Value(GeneratedMessageV3.Builder<?> builder) {
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
/* 108 */     this.memoizedIsInitialized = -1; } protected Object newInstance(UnusedPrivateParameter unused) { return new UInt32Value(); } public final UnknownFieldSet getUnknownFields() { return this.unknownFields; } private UInt32Value() { this.memoizedIsInitialized = -1; }
/*     */   private UInt32Value(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException { this(); if (extensionRegistry == null)
/*     */       throw new NullPointerException();  UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(); try { boolean done = false; while (!done) { int tag = input.readTag(); switch (tag) { case 0: done = true; continue;case 8: this.value_ = input.readUInt32(); continue; }  if (!parseUnknownField(input, unknownFields, extensionRegistry, tag))
/* 111 */           done = true;  }  } catch (InvalidProtocolBufferException e) { throw e.setUnfinishedMessage(this); } catch (IOException e) { throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(this); } finally { this.unknownFields = unknownFields.build(); makeExtensionsImmutable(); }  } public final boolean isInitialized() { byte isInitialized = this.memoizedIsInitialized;
/* 112 */     if (isInitialized == 1) return true; 
/* 113 */     if (isInitialized == 0) return false;
/*     */     
/* 115 */     this.memoizedIsInitialized = 1;
/* 116 */     return true; } public static final Descriptors.Descriptor getDescriptor() { return WrappersProto.internal_static_google_protobuf_UInt32Value_descriptor; } protected FieldAccessorTable internalGetFieldAccessorTable() {
/*     */     return WrappersProto.internal_static_google_protobuf_UInt32Value_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)UInt32Value.class, (Class)Builder.class);
/*     */   } public int getValue() {
/*     */     return this.value_;
/*     */   }
/*     */   public void writeTo(CodedOutputStream output) throws IOException {
/* 122 */     if (this.value_ != 0) {
/* 123 */       output.writeUInt32(1, this.value_);
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
/* 134 */     if (this.value_ != 0) {
/* 135 */       size += 
/* 136 */         CodedOutputStream.computeUInt32Size(1, this.value_);
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
/* 148 */     if (!(obj instanceof UInt32Value)) {
/* 149 */       return super.equals(obj);
/*     */     }
/* 151 */     UInt32Value other = (UInt32Value)obj;
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
/* 167 */     hash = 53 * hash + getValue();
/* 168 */     hash = 29 * hash + this.unknownFields.hashCode();
/* 169 */     this.memoizedHashCode = hash;
/* 170 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static UInt32Value parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
/* 176 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static UInt32Value parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 182 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public static UInt32Value parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 187 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static UInt32Value parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 193 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static UInt32Value parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 197 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static UInt32Value parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 203 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static UInt32Value parseFrom(InputStream input) throws IOException {
/* 207 */     return 
/* 208 */       GeneratedMessageV3.<UInt32Value>parseWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static UInt32Value parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 214 */     return 
/* 215 */       GeneratedMessageV3.<UInt32Value>parseWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static UInt32Value parseDelimitedFrom(InputStream input) throws IOException {
/* 219 */     return 
/* 220 */       GeneratedMessageV3.<UInt32Value>parseDelimitedWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static UInt32Value parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 226 */     return 
/* 227 */       GeneratedMessageV3.<UInt32Value>parseDelimitedWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public static UInt32Value parseFrom(CodedInputStream input) throws IOException {
/* 232 */     return 
/* 233 */       GeneratedMessageV3.<UInt32Value>parseWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static UInt32Value parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 239 */     return 
/* 240 */       GeneratedMessageV3.<UInt32Value>parseWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */   
/*     */   public Builder newBuilderForType() {
/* 244 */     return newBuilder();
/*     */   } public static Builder newBuilder() {
/* 246 */     return DEFAULT_INSTANCE.toBuilder();
/*     */   }
/*     */   public static Builder newBuilder(UInt32Value prototype) {
/* 249 */     return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
/*     */   }
/*     */   
/*     */   public Builder toBuilder() {
/* 253 */     return (this == DEFAULT_INSTANCE) ? new Builder() : (new Builder())
/* 254 */       .mergeFrom(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Builder newBuilderForType(BuilderParent parent) {
/* 260 */     Builder builder = new Builder(parent);
/* 261 */     return builder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final class Builder
/*     */     extends GeneratedMessageV3.Builder<Builder>
/*     */     implements UInt32ValueOrBuilder
/*     */   {
/*     */     private int value_;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static final Descriptors.Descriptor getDescriptor() {
/* 277 */       return WrappersProto.internal_static_google_protobuf_UInt32Value_descriptor;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected FieldAccessorTable internalGetFieldAccessorTable() {
/* 283 */       return WrappersProto.internal_static_google_protobuf_UInt32Value_fieldAccessorTable
/* 284 */         .ensureFieldAccessorsInitialized((Class)UInt32Value.class, (Class)Builder.class);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private Builder() {
/* 290 */       maybeForceBuilderInitialization();
/*     */     }
/*     */ 
/*     */     
/*     */     private Builder(BuilderParent parent) {
/* 295 */       super(parent);
/* 296 */       maybeForceBuilderInitialization();
/*     */     }
/*     */     private void maybeForceBuilderInitialization() {
/* 299 */       if (GeneratedMessageV3.alwaysUseFieldBuilders);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder clear() {
/* 305 */       super.clear();
/* 306 */       this.value_ = 0;
/*     */       
/* 308 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Descriptors.Descriptor getDescriptorForType() {
/* 314 */       return WrappersProto.internal_static_google_protobuf_UInt32Value_descriptor;
/*     */     }
/*     */ 
/*     */     
/*     */     public UInt32Value getDefaultInstanceForType() {
/* 319 */       return UInt32Value.getDefaultInstance();
/*     */     }
/*     */ 
/*     */     
/*     */     public UInt32Value build() {
/* 324 */       UInt32Value result = buildPartial();
/* 325 */       if (!result.isInitialized()) {
/* 326 */         throw newUninitializedMessageException(result);
/*     */       }
/* 328 */       return result;
/*     */     }
/*     */ 
/*     */     
/*     */     public UInt32Value buildPartial() {
/* 333 */       UInt32Value result = new UInt32Value(this);
/* 334 */       result.value_ = this.value_;
/* 335 */       onBuilt();
/* 336 */       return result;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder clone() {
/* 341 */       return super.clone();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setField(Descriptors.FieldDescriptor field, Object value) {
/* 347 */       return super.setField(field, value);
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder clearField(Descriptors.FieldDescriptor field) {
/* 352 */       return super.clearField(field);
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
/* 357 */       return super.clearOneof(oneof);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
/* 363 */       return super.setRepeatedField(field, index, value);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
/* 369 */       return super.addRepeatedField(field, value);
/*     */     }
/*     */     
/*     */     public Builder mergeFrom(Message other) {
/* 373 */       if (other instanceof UInt32Value) {
/* 374 */         return mergeFrom((UInt32Value)other);
/*     */       }
/* 376 */       super.mergeFrom(other);
/* 377 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder mergeFrom(UInt32Value other) {
/* 382 */       if (other == UInt32Value.getDefaultInstance()) return this; 
/* 383 */       if (other.getValue() != 0) {
/* 384 */         setValue(other.getValue());
/*     */       }
/* 386 */       mergeUnknownFields(other.unknownFields);
/* 387 */       onChanged();
/* 388 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public final boolean isInitialized() {
/* 393 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 401 */       UInt32Value parsedMessage = null;
/*     */       try {
/* 403 */         parsedMessage = UInt32Value.PARSER.parsePartialFrom(input, extensionRegistry);
/* 404 */       } catch (InvalidProtocolBufferException e) {
/* 405 */         parsedMessage = (UInt32Value)e.getUnfinishedMessage();
/* 406 */         throw e.unwrapIOException();
/*     */       } finally {
/* 408 */         if (parsedMessage != null) {
/* 409 */           mergeFrom(parsedMessage);
/*     */         }
/*     */       } 
/* 412 */       return this;
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
/*     */     public int getValue() {
/* 426 */       return this.value_;
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
/*     */     public Builder setValue(int value) {
/* 439 */       this.value_ = value;
/* 440 */       onChanged();
/* 441 */       return this;
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
/* 453 */       this.value_ = 0;
/* 454 */       onChanged();
/* 455 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
/* 460 */       return super.setUnknownFields(unknownFields);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
/* 466 */       return super.mergeUnknownFields(unknownFields);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 476 */   private static final UInt32Value DEFAULT_INSTANCE = new UInt32Value();
/*     */ 
/*     */   
/*     */   public static UInt32Value getDefaultInstance() {
/* 480 */     return DEFAULT_INSTANCE;
/*     */   }
/*     */   
/*     */   public static UInt32Value of(int value) {
/* 484 */     return newBuilder().setValue(value).build();
/*     */   }
/*     */ 
/*     */   
/* 488 */   private static final Parser<UInt32Value> PARSER = new AbstractParser<UInt32Value>()
/*     */     {
/*     */ 
/*     */       
/*     */       public UInt32Value parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException
/*     */       {
/* 494 */         return new UInt32Value(input, extensionRegistry);
/*     */       }
/*     */     };
/*     */   
/*     */   public static Parser<UInt32Value> parser() {
/* 499 */     return PARSER;
/*     */   }
/*     */ 
/*     */   
/*     */   public Parser<UInt32Value> getParserForType() {
/* 504 */     return PARSER;
/*     */   }
/*     */ 
/*     */   
/*     */   public UInt32Value getDefaultInstanceForType() {
/* 509 */     return DEFAULT_INSTANCE;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\UInt32Value.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */