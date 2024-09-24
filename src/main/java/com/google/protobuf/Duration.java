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
/*     */ public final class Duration
/*     */   extends GeneratedMessageV3
/*     */   implements DurationOrBuilder
/*     */ {
/*     */   private static final long serialVersionUID = 0L;
/*     */   public static final int SECONDS_FIELD_NUMBER = 1;
/*     */   private long seconds_;
/*     */   public static final int NANOS_FIELD_NUMBER = 2;
/*     */   private int nanos_;
/*     */   private byte memoizedIsInitialized;
/*     */   
/*     */   private Duration(GeneratedMessageV3.Builder<?> builder) {
/*  64 */     super(builder);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 178 */     this.memoizedIsInitialized = -1; } protected Object newInstance(UnusedPrivateParameter unused) { return new Duration(); } public final UnknownFieldSet getUnknownFields() { return this.unknownFields; } private Duration() { this.memoizedIsInitialized = -1; }
/*     */   private Duration(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException { this(); if (extensionRegistry == null)
/*     */       throw new NullPointerException();  UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(); try { boolean done = false; while (!done) { int tag = input.readTag(); switch (tag) { case 0: done = true; continue;case 8: this.seconds_ = input.readInt64(); continue;case 16: this.nanos_ = input.readInt32(); continue; }  if (!parseUnknownField(input, unknownFields, extensionRegistry, tag))
/* 181 */           done = true;  }  } catch (InvalidProtocolBufferException e) { throw e.setUnfinishedMessage(this); } catch (IOException e) { throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(this); } finally { this.unknownFields = unknownFields.build(); makeExtensionsImmutable(); }  } public final boolean isInitialized() { byte isInitialized = this.memoizedIsInitialized;
/* 182 */     if (isInitialized == 1) return true; 
/* 183 */     if (isInitialized == 0) return false;
/*     */     
/* 185 */     this.memoizedIsInitialized = 1;
/* 186 */     return true; } public static final Descriptors.Descriptor getDescriptor() { return DurationProto.internal_static_google_protobuf_Duration_descriptor; } protected FieldAccessorTable internalGetFieldAccessorTable() { return DurationProto.internal_static_google_protobuf_Duration_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)Duration.class, (Class)Builder.class); }
/*     */   public long getSeconds() { return this.seconds_; }
/*     */   public int getNanos() {
/*     */     return this.nanos_;
/*     */   }
/*     */   public void writeTo(CodedOutputStream output) throws IOException {
/* 192 */     if (this.seconds_ != 0L) {
/* 193 */       output.writeInt64(1, this.seconds_);
/*     */     }
/* 195 */     if (this.nanos_ != 0) {
/* 196 */       output.writeInt32(2, this.nanos_);
/*     */     }
/* 198 */     this.unknownFields.writeTo(output);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSerializedSize() {
/* 203 */     int size = this.memoizedSize;
/* 204 */     if (size != -1) return size;
/*     */     
/* 206 */     size = 0;
/* 207 */     if (this.seconds_ != 0L) {
/* 208 */       size += 
/* 209 */         CodedOutputStream.computeInt64Size(1, this.seconds_);
/*     */     }
/* 211 */     if (this.nanos_ != 0) {
/* 212 */       size += 
/* 213 */         CodedOutputStream.computeInt32Size(2, this.nanos_);
/*     */     }
/* 215 */     size += this.unknownFields.getSerializedSize();
/* 216 */     this.memoizedSize = size;
/* 217 */     return size;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 222 */     if (obj == this) {
/* 223 */       return true;
/*     */     }
/* 225 */     if (!(obj instanceof Duration)) {
/* 226 */       return super.equals(obj);
/*     */     }
/* 228 */     Duration other = (Duration)obj;
/*     */     
/* 230 */     if (getSeconds() != other
/* 231 */       .getSeconds()) return false; 
/* 232 */     if (getNanos() != other
/* 233 */       .getNanos()) return false; 
/* 234 */     if (!this.unknownFields.equals(other.unknownFields)) return false; 
/* 235 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 240 */     if (this.memoizedHashCode != 0) {
/* 241 */       return this.memoizedHashCode;
/*     */     }
/* 243 */     int hash = 41;
/* 244 */     hash = 19 * hash + getDescriptor().hashCode();
/* 245 */     hash = 37 * hash + 1;
/* 246 */     hash = 53 * hash + Internal.hashLong(
/* 247 */         getSeconds());
/* 248 */     hash = 37 * hash + 2;
/* 249 */     hash = 53 * hash + getNanos();
/* 250 */     hash = 29 * hash + this.unknownFields.hashCode();
/* 251 */     this.memoizedHashCode = hash;
/* 252 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Duration parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
/* 258 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Duration parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 264 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Duration parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 269 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Duration parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 275 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static Duration parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 279 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Duration parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 285 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static Duration parseFrom(InputStream input) throws IOException {
/* 289 */     return 
/* 290 */       GeneratedMessageV3.<Duration>parseWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Duration parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 296 */     return 
/* 297 */       GeneratedMessageV3.<Duration>parseWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static Duration parseDelimitedFrom(InputStream input) throws IOException {
/* 301 */     return 
/* 302 */       GeneratedMessageV3.<Duration>parseDelimitedWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Duration parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 308 */     return 
/* 309 */       GeneratedMessageV3.<Duration>parseDelimitedWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Duration parseFrom(CodedInputStream input) throws IOException {
/* 314 */     return 
/* 315 */       GeneratedMessageV3.<Duration>parseWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Duration parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 321 */     return 
/* 322 */       GeneratedMessageV3.<Duration>parseWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */   
/*     */   public Builder newBuilderForType() {
/* 326 */     return newBuilder();
/*     */   } public static Builder newBuilder() {
/* 328 */     return DEFAULT_INSTANCE.toBuilder();
/*     */   }
/*     */   public static Builder newBuilder(Duration prototype) {
/* 331 */     return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
/*     */   }
/*     */   
/*     */   public Builder toBuilder() {
/* 335 */     return (this == DEFAULT_INSTANCE) ? new Builder() : (new Builder())
/* 336 */       .mergeFrom(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Builder newBuilderForType(BuilderParent parent) {
/* 342 */     Builder builder = new Builder(parent);
/* 343 */     return builder;
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
/*     */   public static final class Builder
/*     */     extends GeneratedMessageV3.Builder<Builder>
/*     */     implements DurationOrBuilder
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
/*     */     public static final Descriptors.Descriptor getDescriptor() {
/* 402 */       return DurationProto.internal_static_google_protobuf_Duration_descriptor;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected FieldAccessorTable internalGetFieldAccessorTable() {
/* 408 */       return DurationProto.internal_static_google_protobuf_Duration_fieldAccessorTable
/* 409 */         .ensureFieldAccessorsInitialized((Class)Duration.class, (Class)Builder.class);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private Builder() {
/* 415 */       maybeForceBuilderInitialization();
/*     */     }
/*     */ 
/*     */     
/*     */     private Builder(BuilderParent parent) {
/* 420 */       super(parent);
/* 421 */       maybeForceBuilderInitialization();
/*     */     }
/*     */     private void maybeForceBuilderInitialization() {
/* 424 */       if (GeneratedMessageV3.alwaysUseFieldBuilders);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder clear() {
/* 430 */       super.clear();
/* 431 */       this.seconds_ = 0L;
/*     */       
/* 433 */       this.nanos_ = 0;
/*     */       
/* 435 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Descriptors.Descriptor getDescriptorForType() {
/* 441 */       return DurationProto.internal_static_google_protobuf_Duration_descriptor;
/*     */     }
/*     */ 
/*     */     
/*     */     public Duration getDefaultInstanceForType() {
/* 446 */       return Duration.getDefaultInstance();
/*     */     }
/*     */ 
/*     */     
/*     */     public Duration build() {
/* 451 */       Duration result = buildPartial();
/* 452 */       if (!result.isInitialized()) {
/* 453 */         throw newUninitializedMessageException(result);
/*     */       }
/* 455 */       return result;
/*     */     }
/*     */ 
/*     */     
/*     */     public Duration buildPartial() {
/* 460 */       Duration result = new Duration(this);
/* 461 */       result.seconds_ = this.seconds_;
/* 462 */       result.nanos_ = this.nanos_;
/* 463 */       onBuilt();
/* 464 */       return result;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder clone() {
/* 469 */       return super.clone();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setField(Descriptors.FieldDescriptor field, Object value) {
/* 475 */       return super.setField(field, value);
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder clearField(Descriptors.FieldDescriptor field) {
/* 480 */       return super.clearField(field);
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
/* 485 */       return super.clearOneof(oneof);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
/* 491 */       return super.setRepeatedField(field, index, value);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
/* 497 */       return super.addRepeatedField(field, value);
/*     */     }
/*     */     
/*     */     public Builder mergeFrom(Message other) {
/* 501 */       if (other instanceof Duration) {
/* 502 */         return mergeFrom((Duration)other);
/*     */       }
/* 504 */       super.mergeFrom(other);
/* 505 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder mergeFrom(Duration other) {
/* 510 */       if (other == Duration.getDefaultInstance()) return this; 
/* 511 */       if (other.getSeconds() != 0L) {
/* 512 */         setSeconds(other.getSeconds());
/*     */       }
/* 514 */       if (other.getNanos() != 0) {
/* 515 */         setNanos(other.getNanos());
/*     */       }
/* 517 */       mergeUnknownFields(other.unknownFields);
/* 518 */       onChanged();
/* 519 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public final boolean isInitialized() {
/* 524 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 532 */       Duration parsedMessage = null;
/*     */       try {
/* 534 */         parsedMessage = Duration.PARSER.parsePartialFrom(input, extensionRegistry);
/* 535 */       } catch (InvalidProtocolBufferException e) {
/* 536 */         parsedMessage = (Duration)e.getUnfinishedMessage();
/* 537 */         throw e.unwrapIOException();
/*     */       } finally {
/* 539 */         if (parsedMessage != null) {
/* 540 */           mergeFrom(parsedMessage);
/*     */         }
/*     */       } 
/* 543 */       return this;
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
/* 559 */       return this.seconds_;
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
/* 574 */       this.seconds_ = value;
/* 575 */       onChanged();
/* 576 */       return this;
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
/* 590 */       this.seconds_ = 0L;
/* 591 */       onChanged();
/* 592 */       return this;
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
/*     */     public int getNanos() {
/* 611 */       return this.nanos_;
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
/*     */     public Builder setNanos(int value) {
/* 629 */       this.nanos_ = value;
/* 630 */       onChanged();
/* 631 */       return this;
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
/*     */     public Builder clearNanos() {
/* 648 */       this.nanos_ = 0;
/* 649 */       onChanged();
/* 650 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
/* 655 */       return super.setUnknownFields(unknownFields);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
/* 661 */       return super.mergeUnknownFields(unknownFields);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 671 */   private static final Duration DEFAULT_INSTANCE = new Duration();
/*     */ 
/*     */   
/*     */   public static Duration getDefaultInstance() {
/* 675 */     return DEFAULT_INSTANCE;
/*     */   }
/*     */ 
/*     */   
/* 679 */   private static final Parser<Duration> PARSER = new AbstractParser<Duration>()
/*     */     {
/*     */ 
/*     */       
/*     */       public Duration parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException
/*     */       {
/* 685 */         return new Duration(input, extensionRegistry);
/*     */       }
/*     */     };
/*     */   
/*     */   public static Parser<Duration> parser() {
/* 690 */     return PARSER;
/*     */   }
/*     */ 
/*     */   
/*     */   public Parser<Duration> getParserForType() {
/* 695 */     return PARSER;
/*     */   }
/*     */ 
/*     */   
/*     */   public Duration getDefaultInstanceForType() {
/* 700 */     return DEFAULT_INSTANCE;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\Duration.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */