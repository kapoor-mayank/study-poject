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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Timestamp
/*     */   extends GeneratedMessageV3
/*     */   implements TimestampOrBuilder
/*     */ {
/*     */   private static final long serialVersionUID = 0L;
/*     */   public static final int SECONDS_FIELD_NUMBER = 1;
/*     */   private long seconds_;
/*     */   public static final int NANOS_FIELD_NUMBER = 2;
/*     */   private int nanos_;
/*     */   private byte memoizedIsInitialized;
/*     */   
/*     */   private Timestamp(GeneratedMessageV3.Builder<?> builder) {
/*  85 */     super(builder);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 197 */     this.memoizedIsInitialized = -1; } protected Object newInstance(UnusedPrivateParameter unused) { return new Timestamp(); } public final UnknownFieldSet getUnknownFields() { return this.unknownFields; } private Timestamp() { this.memoizedIsInitialized = -1; }
/*     */   private Timestamp(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException { this(); if (extensionRegistry == null)
/*     */       throw new NullPointerException();  UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(); try { boolean done = false; while (!done) { int tag = input.readTag(); switch (tag) { case 0: done = true; continue;case 8: this.seconds_ = input.readInt64(); continue;case 16: this.nanos_ = input.readInt32(); continue; }  if (!parseUnknownField(input, unknownFields, extensionRegistry, tag))
/* 200 */           done = true;  }  } catch (InvalidProtocolBufferException e) { throw e.setUnfinishedMessage(this); } catch (IOException e) { throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(this); } finally { this.unknownFields = unknownFields.build(); makeExtensionsImmutable(); }  } public final boolean isInitialized() { byte isInitialized = this.memoizedIsInitialized;
/* 201 */     if (isInitialized == 1) return true; 
/* 202 */     if (isInitialized == 0) return false;
/*     */     
/* 204 */     this.memoizedIsInitialized = 1;
/* 205 */     return true; } public static final Descriptors.Descriptor getDescriptor() { return TimestampProto.internal_static_google_protobuf_Timestamp_descriptor; } protected FieldAccessorTable internalGetFieldAccessorTable() { return TimestampProto.internal_static_google_protobuf_Timestamp_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)Timestamp.class, (Class)Builder.class); }
/*     */   public long getSeconds() { return this.seconds_; }
/*     */   public int getNanos() {
/*     */     return this.nanos_;
/*     */   }
/*     */   public void writeTo(CodedOutputStream output) throws IOException {
/* 211 */     if (this.seconds_ != 0L) {
/* 212 */       output.writeInt64(1, this.seconds_);
/*     */     }
/* 214 */     if (this.nanos_ != 0) {
/* 215 */       output.writeInt32(2, this.nanos_);
/*     */     }
/* 217 */     this.unknownFields.writeTo(output);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSerializedSize() {
/* 222 */     int size = this.memoizedSize;
/* 223 */     if (size != -1) return size;
/*     */     
/* 225 */     size = 0;
/* 226 */     if (this.seconds_ != 0L) {
/* 227 */       size += 
/* 228 */         CodedOutputStream.computeInt64Size(1, this.seconds_);
/*     */     }
/* 230 */     if (this.nanos_ != 0) {
/* 231 */       size += 
/* 232 */         CodedOutputStream.computeInt32Size(2, this.nanos_);
/*     */     }
/* 234 */     size += this.unknownFields.getSerializedSize();
/* 235 */     this.memoizedSize = size;
/* 236 */     return size;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 241 */     if (obj == this) {
/* 242 */       return true;
/*     */     }
/* 244 */     if (!(obj instanceof Timestamp)) {
/* 245 */       return super.equals(obj);
/*     */     }
/* 247 */     Timestamp other = (Timestamp)obj;
/*     */     
/* 249 */     if (getSeconds() != other
/* 250 */       .getSeconds()) return false; 
/* 251 */     if (getNanos() != other
/* 252 */       .getNanos()) return false; 
/* 253 */     if (!this.unknownFields.equals(other.unknownFields)) return false; 
/* 254 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 259 */     if (this.memoizedHashCode != 0) {
/* 260 */       return this.memoizedHashCode;
/*     */     }
/* 262 */     int hash = 41;
/* 263 */     hash = 19 * hash + getDescriptor().hashCode();
/* 264 */     hash = 37 * hash + 1;
/* 265 */     hash = 53 * hash + Internal.hashLong(
/* 266 */         getSeconds());
/* 267 */     hash = 37 * hash + 2;
/* 268 */     hash = 53 * hash + getNanos();
/* 269 */     hash = 29 * hash + this.unknownFields.hashCode();
/* 270 */     this.memoizedHashCode = hash;
/* 271 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Timestamp parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
/* 277 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Timestamp parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 283 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Timestamp parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 288 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Timestamp parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 294 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static Timestamp parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 298 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Timestamp parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 304 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static Timestamp parseFrom(InputStream input) throws IOException {
/* 308 */     return 
/* 309 */       GeneratedMessageV3.<Timestamp>parseWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Timestamp parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 315 */     return 
/* 316 */       GeneratedMessageV3.<Timestamp>parseWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static Timestamp parseDelimitedFrom(InputStream input) throws IOException {
/* 320 */     return 
/* 321 */       GeneratedMessageV3.<Timestamp>parseDelimitedWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Timestamp parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 327 */     return 
/* 328 */       GeneratedMessageV3.<Timestamp>parseDelimitedWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Timestamp parseFrom(CodedInputStream input) throws IOException {
/* 333 */     return 
/* 334 */       GeneratedMessageV3.<Timestamp>parseWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Timestamp parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 340 */     return 
/* 341 */       GeneratedMessageV3.<Timestamp>parseWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */   
/*     */   public Builder newBuilderForType() {
/* 345 */     return newBuilder();
/*     */   } public static Builder newBuilder() {
/* 347 */     return DEFAULT_INSTANCE.toBuilder();
/*     */   }
/*     */   public static Builder newBuilder(Timestamp prototype) {
/* 350 */     return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
/*     */   }
/*     */   
/*     */   public Builder toBuilder() {
/* 354 */     return (this == DEFAULT_INSTANCE) ? new Builder() : (new Builder())
/* 355 */       .mergeFrom(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Builder newBuilderForType(BuilderParent parent) {
/* 361 */     Builder builder = new Builder(parent);
/* 362 */     return builder;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final class Builder
/*     */     extends GeneratedMessageV3.Builder<Builder>
/*     */     implements TimestampOrBuilder
/*     */   {
/*     */     private long seconds_;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int nanos_;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static final Descriptors.Descriptor getDescriptor() {
/* 442 */       return TimestampProto.internal_static_google_protobuf_Timestamp_descriptor;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected FieldAccessorTable internalGetFieldAccessorTable() {
/* 448 */       return TimestampProto.internal_static_google_protobuf_Timestamp_fieldAccessorTable
/* 449 */         .ensureFieldAccessorsInitialized((Class)Timestamp.class, (Class)Builder.class);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private Builder() {
/* 455 */       maybeForceBuilderInitialization();
/*     */     }
/*     */ 
/*     */     
/*     */     private Builder(BuilderParent parent) {
/* 460 */       super(parent);
/* 461 */       maybeForceBuilderInitialization();
/*     */     }
/*     */     private void maybeForceBuilderInitialization() {
/* 464 */       if (GeneratedMessageV3.alwaysUseFieldBuilders);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder clear() {
/* 470 */       super.clear();
/* 471 */       this.seconds_ = 0L;
/*     */       
/* 473 */       this.nanos_ = 0;
/*     */       
/* 475 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Descriptors.Descriptor getDescriptorForType() {
/* 481 */       return TimestampProto.internal_static_google_protobuf_Timestamp_descriptor;
/*     */     }
/*     */ 
/*     */     
/*     */     public Timestamp getDefaultInstanceForType() {
/* 486 */       return Timestamp.getDefaultInstance();
/*     */     }
/*     */ 
/*     */     
/*     */     public Timestamp build() {
/* 491 */       Timestamp result = buildPartial();
/* 492 */       if (!result.isInitialized()) {
/* 493 */         throw newUninitializedMessageException(result);
/*     */       }
/* 495 */       return result;
/*     */     }
/*     */ 
/*     */     
/*     */     public Timestamp buildPartial() {
/* 500 */       Timestamp result = new Timestamp(this);
/* 501 */       result.seconds_ = this.seconds_;
/* 502 */       result.nanos_ = this.nanos_;
/* 503 */       onBuilt();
/* 504 */       return result;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder clone() {
/* 509 */       return super.clone();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setField(Descriptors.FieldDescriptor field, Object value) {
/* 515 */       return super.setField(field, value);
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder clearField(Descriptors.FieldDescriptor field) {
/* 520 */       return super.clearField(field);
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
/* 525 */       return super.clearOneof(oneof);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
/* 531 */       return super.setRepeatedField(field, index, value);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
/* 537 */       return super.addRepeatedField(field, value);
/*     */     }
/*     */     
/*     */     public Builder mergeFrom(Message other) {
/* 541 */       if (other instanceof Timestamp) {
/* 542 */         return mergeFrom((Timestamp)other);
/*     */       }
/* 544 */       super.mergeFrom(other);
/* 545 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder mergeFrom(Timestamp other) {
/* 550 */       if (other == Timestamp.getDefaultInstance()) return this; 
/* 551 */       if (other.getSeconds() != 0L) {
/* 552 */         setSeconds(other.getSeconds());
/*     */       }
/* 554 */       if (other.getNanos() != 0) {
/* 555 */         setNanos(other.getNanos());
/*     */       }
/* 557 */       mergeUnknownFields(other.unknownFields);
/* 558 */       onChanged();
/* 559 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public final boolean isInitialized() {
/* 564 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 572 */       Timestamp parsedMessage = null;
/*     */       try {
/* 574 */         parsedMessage = Timestamp.PARSER.parsePartialFrom(input, extensionRegistry);
/* 575 */       } catch (InvalidProtocolBufferException e) {
/* 576 */         parsedMessage = (Timestamp)e.getUnfinishedMessage();
/* 577 */         throw e.unwrapIOException();
/*     */       } finally {
/* 579 */         if (parsedMessage != null) {
/* 580 */           mergeFrom(parsedMessage);
/*     */         }
/*     */       } 
/* 583 */       return this;
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
/*     */     public long getSeconds() {
/* 599 */       return this.seconds_;
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
/*     */     public Builder setSeconds(long value) {
/* 614 */       this.seconds_ = value;
/* 615 */       onChanged();
/* 616 */       return this;
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
/*     */     public Builder clearSeconds() {
/* 630 */       this.seconds_ = 0L;
/* 631 */       onChanged();
/* 632 */       return this;
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
/*     */     public int getNanos() {
/* 649 */       return this.nanos_;
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
/*     */     public Builder setNanos(int value) {
/* 665 */       this.nanos_ = value;
/* 666 */       onChanged();
/* 667 */       return this;
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
/*     */     public Builder clearNanos() {
/* 682 */       this.nanos_ = 0;
/* 683 */       onChanged();
/* 684 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
/* 689 */       return super.setUnknownFields(unknownFields);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
/* 695 */       return super.mergeUnknownFields(unknownFields);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 705 */   private static final Timestamp DEFAULT_INSTANCE = new Timestamp();
/*     */ 
/*     */   
/*     */   public static Timestamp getDefaultInstance() {
/* 709 */     return DEFAULT_INSTANCE;
/*     */   }
/*     */ 
/*     */   
/* 713 */   private static final Parser<Timestamp> PARSER = new AbstractParser<Timestamp>()
/*     */     {
/*     */ 
/*     */       
/*     */       public Timestamp parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException
/*     */       {
/* 719 */         return new Timestamp(input, extensionRegistry);
/*     */       }
/*     */     };
/*     */   
/*     */   public static Parser<Timestamp> parser() {
/* 724 */     return PARSER;
/*     */   }
/*     */ 
/*     */   
/*     */   public Parser<Timestamp> getParserForType() {
/* 729 */     return PARSER;
/*     */   }
/*     */ 
/*     */   
/*     */   public Timestamp getDefaultInstanceForType() {
/* 734 */     return DEFAULT_INSTANCE;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\Timestamp.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */