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
/*     */ public final class FloatValue
/*     */   extends GeneratedMessageV3
/*     */   implements FloatValueOrBuilder
/*     */ {
/*     */   private static final long serialVersionUID = 0L;
/*     */   public static final int VALUE_FIELD_NUMBER = 1;
/*     */   private float value_;
/*     */   private byte memoizedIsInitialized;
/*     */   
/*     */   private FloatValue(GeneratedMessageV3.Builder<?> builder) {
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
/* 108 */     this.memoizedIsInitialized = -1; } protected Object newInstance(UnusedPrivateParameter unused) { return new FloatValue(); } public final UnknownFieldSet getUnknownFields() { return this.unknownFields; } private FloatValue() { this.memoizedIsInitialized = -1; }
/*     */   private FloatValue(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException { this(); if (extensionRegistry == null)
/*     */       throw new NullPointerException();  UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(); try { boolean done = false; while (!done) { int tag = input.readTag(); switch (tag) { case 0: done = true; continue;case 13: this.value_ = input.readFloat(); continue; }  if (!parseUnknownField(input, unknownFields, extensionRegistry, tag))
/* 111 */           done = true;  }  } catch (InvalidProtocolBufferException e) { throw e.setUnfinishedMessage(this); } catch (IOException e) { throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(this); } finally { this.unknownFields = unknownFields.build(); makeExtensionsImmutable(); }  } public final boolean isInitialized() { byte isInitialized = this.memoizedIsInitialized;
/* 112 */     if (isInitialized == 1) return true; 
/* 113 */     if (isInitialized == 0) return false;
/*     */     
/* 115 */     this.memoizedIsInitialized = 1;
/* 116 */     return true; } public static final Descriptors.Descriptor getDescriptor() { return WrappersProto.internal_static_google_protobuf_FloatValue_descriptor; } protected FieldAccessorTable internalGetFieldAccessorTable() {
/*     */     return WrappersProto.internal_static_google_protobuf_FloatValue_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)FloatValue.class, (Class)Builder.class);
/*     */   } public float getValue() {
/*     */     return this.value_;
/*     */   }
/*     */   public void writeTo(CodedOutputStream output) throws IOException {
/* 122 */     if (this.value_ != 0.0F) {
/* 123 */       output.writeFloat(1, this.value_);
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
/* 134 */     if (this.value_ != 0.0F) {
/* 135 */       size += 
/* 136 */         CodedOutputStream.computeFloatSize(1, this.value_);
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
/* 148 */     if (!(obj instanceof FloatValue)) {
/* 149 */       return super.equals(obj);
/*     */     }
/* 151 */     FloatValue other = (FloatValue)obj;
/*     */     
/* 153 */     if (Float.floatToIntBits(getValue()) != 
/* 154 */       Float.floatToIntBits(other
/* 155 */         .getValue())) return false; 
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
/* 168 */     hash = 53 * hash + Float.floatToIntBits(
/* 169 */         getValue());
/* 170 */     hash = 29 * hash + this.unknownFields.hashCode();
/* 171 */     this.memoizedHashCode = hash;
/* 172 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static FloatValue parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
/* 178 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static FloatValue parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 184 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public static FloatValue parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 189 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static FloatValue parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 195 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static FloatValue parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 199 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static FloatValue parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 205 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static FloatValue parseFrom(InputStream input) throws IOException {
/* 209 */     return 
/* 210 */       GeneratedMessageV3.<FloatValue>parseWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static FloatValue parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 216 */     return 
/* 217 */       GeneratedMessageV3.<FloatValue>parseWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static FloatValue parseDelimitedFrom(InputStream input) throws IOException {
/* 221 */     return 
/* 222 */       GeneratedMessageV3.<FloatValue>parseDelimitedWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static FloatValue parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 228 */     return 
/* 229 */       GeneratedMessageV3.<FloatValue>parseDelimitedWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public static FloatValue parseFrom(CodedInputStream input) throws IOException {
/* 234 */     return 
/* 235 */       GeneratedMessageV3.<FloatValue>parseWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static FloatValue parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 241 */     return 
/* 242 */       GeneratedMessageV3.<FloatValue>parseWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */   
/*     */   public Builder newBuilderForType() {
/* 246 */     return newBuilder();
/*     */   } public static Builder newBuilder() {
/* 248 */     return DEFAULT_INSTANCE.toBuilder();
/*     */   }
/*     */   public static Builder newBuilder(FloatValue prototype) {
/* 251 */     return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
/*     */   }
/*     */   
/*     */   public Builder toBuilder() {
/* 255 */     return (this == DEFAULT_INSTANCE) ? new Builder() : (new Builder())
/* 256 */       .mergeFrom(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Builder newBuilderForType(BuilderParent parent) {
/* 262 */     Builder builder = new Builder(parent);
/* 263 */     return builder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final class Builder
/*     */     extends GeneratedMessageV3.Builder<Builder>
/*     */     implements FloatValueOrBuilder
/*     */   {
/*     */     private float value_;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static final Descriptors.Descriptor getDescriptor() {
/* 279 */       return WrappersProto.internal_static_google_protobuf_FloatValue_descriptor;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected FieldAccessorTable internalGetFieldAccessorTable() {
/* 285 */       return WrappersProto.internal_static_google_protobuf_FloatValue_fieldAccessorTable
/* 286 */         .ensureFieldAccessorsInitialized((Class)FloatValue.class, (Class)Builder.class);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private Builder() {
/* 292 */       maybeForceBuilderInitialization();
/*     */     }
/*     */ 
/*     */     
/*     */     private Builder(BuilderParent parent) {
/* 297 */       super(parent);
/* 298 */       maybeForceBuilderInitialization();
/*     */     }
/*     */     private void maybeForceBuilderInitialization() {
/* 301 */       if (GeneratedMessageV3.alwaysUseFieldBuilders);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder clear() {
/* 307 */       super.clear();
/* 308 */       this.value_ = 0.0F;
/*     */       
/* 310 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Descriptors.Descriptor getDescriptorForType() {
/* 316 */       return WrappersProto.internal_static_google_protobuf_FloatValue_descriptor;
/*     */     }
/*     */ 
/*     */     
/*     */     public FloatValue getDefaultInstanceForType() {
/* 321 */       return FloatValue.getDefaultInstance();
/*     */     }
/*     */ 
/*     */     
/*     */     public FloatValue build() {
/* 326 */       FloatValue result = buildPartial();
/* 327 */       if (!result.isInitialized()) {
/* 328 */         throw newUninitializedMessageException(result);
/*     */       }
/* 330 */       return result;
/*     */     }
/*     */ 
/*     */     
/*     */     public FloatValue buildPartial() {
/* 335 */       FloatValue result = new FloatValue(this);
/* 336 */       result.value_ = this.value_;
/* 337 */       onBuilt();
/* 338 */       return result;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder clone() {
/* 343 */       return super.clone();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setField(Descriptors.FieldDescriptor field, Object value) {
/* 349 */       return super.setField(field, value);
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder clearField(Descriptors.FieldDescriptor field) {
/* 354 */       return super.clearField(field);
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
/* 359 */       return super.clearOneof(oneof);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
/* 365 */       return super.setRepeatedField(field, index, value);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
/* 371 */       return super.addRepeatedField(field, value);
/*     */     }
/*     */     
/*     */     public Builder mergeFrom(Message other) {
/* 375 */       if (other instanceof FloatValue) {
/* 376 */         return mergeFrom((FloatValue)other);
/*     */       }
/* 378 */       super.mergeFrom(other);
/* 379 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder mergeFrom(FloatValue other) {
/* 384 */       if (other == FloatValue.getDefaultInstance()) return this; 
/* 385 */       if (other.getValue() != 0.0F) {
/* 386 */         setValue(other.getValue());
/*     */       }
/* 388 */       mergeUnknownFields(other.unknownFields);
/* 389 */       onChanged();
/* 390 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public final boolean isInitialized() {
/* 395 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 403 */       FloatValue parsedMessage = null;
/*     */       try {
/* 405 */         parsedMessage = FloatValue.PARSER.parsePartialFrom(input, extensionRegistry);
/* 406 */       } catch (InvalidProtocolBufferException e) {
/* 407 */         parsedMessage = (FloatValue)e.getUnfinishedMessage();
/* 408 */         throw e.unwrapIOException();
/*     */       } finally {
/* 410 */         if (parsedMessage != null) {
/* 411 */           mergeFrom(parsedMessage);
/*     */         }
/*     */       } 
/* 414 */       return this;
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
/*     */     public float getValue() {
/* 428 */       return this.value_;
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
/*     */     public Builder setValue(float value) {
/* 441 */       this.value_ = value;
/* 442 */       onChanged();
/* 443 */       return this;
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
/* 455 */       this.value_ = 0.0F;
/* 456 */       onChanged();
/* 457 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
/* 462 */       return super.setUnknownFields(unknownFields);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
/* 468 */       return super.mergeUnknownFields(unknownFields);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 478 */   private static final FloatValue DEFAULT_INSTANCE = new FloatValue();
/*     */ 
/*     */   
/*     */   public static FloatValue getDefaultInstance() {
/* 482 */     return DEFAULT_INSTANCE;
/*     */   }
/*     */   
/*     */   public static FloatValue of(float value) {
/* 486 */     return newBuilder().setValue(value).build();
/*     */   }
/*     */ 
/*     */   
/* 490 */   private static final Parser<FloatValue> PARSER = new AbstractParser<FloatValue>()
/*     */     {
/*     */ 
/*     */       
/*     */       public FloatValue parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException
/*     */       {
/* 496 */         return new FloatValue(input, extensionRegistry);
/*     */       }
/*     */     };
/*     */   
/*     */   public static Parser<FloatValue> parser() {
/* 501 */     return PARSER;
/*     */   }
/*     */ 
/*     */   
/*     */   public Parser<FloatValue> getParserForType() {
/* 506 */     return PARSER;
/*     */   }
/*     */ 
/*     */   
/*     */   public FloatValue getDefaultInstanceForType() {
/* 511 */     return DEFAULT_INSTANCE;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\FloatValue.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */