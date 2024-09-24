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
/*     */ public final class StringValue
/*     */   extends GeneratedMessageV3
/*     */   implements StringValueOrBuilder
/*     */ {
/*     */   private static final long serialVersionUID = 0L;
/*     */   public static final int VALUE_FIELD_NUMBER = 1;
/*     */   private volatile Object value_;
/*     */   private byte memoizedIsInitialized;
/*     */   
/*     */   private StringValue(GeneratedMessageV3.Builder<?> builder) {
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 141 */     this.memoizedIsInitialized = -1; } protected Object newInstance(UnusedPrivateParameter unused) { return new StringValue(); } public final UnknownFieldSet getUnknownFields() { return this.unknownFields; } private StringValue() { this.memoizedIsInitialized = -1; this.value_ = ""; }
/*     */   private StringValue(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException { this(); if (extensionRegistry == null)
/*     */       throw new NullPointerException();  UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(); try { boolean done = false; while (!done) { String s; int tag = input.readTag(); switch (tag) { case 0: done = true; continue;case 10: s = input.readStringRequireUtf8(); this.value_ = s; continue; }  if (!parseUnknownField(input, unknownFields, extensionRegistry, tag))
/* 144 */           done = true;  }  } catch (InvalidProtocolBufferException e) { throw e.setUnfinishedMessage(this); } catch (IOException e) { throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(this); } finally { this.unknownFields = unknownFields.build(); makeExtensionsImmutable(); }  } public final boolean isInitialized() { byte isInitialized = this.memoizedIsInitialized;
/* 145 */     if (isInitialized == 1) return true; 
/* 146 */     if (isInitialized == 0) return false;
/*     */     
/* 148 */     this.memoizedIsInitialized = 1;
/* 149 */     return true; } public static final Descriptors.Descriptor getDescriptor() { return WrappersProto.internal_static_google_protobuf_StringValue_descriptor; }
/*     */   protected FieldAccessorTable internalGetFieldAccessorTable() { return WrappersProto.internal_static_google_protobuf_StringValue_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)StringValue.class, (Class)Builder.class); }
/*     */   public String getValue() { Object ref = this.value_; if (ref instanceof String)
/*     */       return (String)ref;  ByteString bs = (ByteString)ref; String s = bs.toStringUtf8(); this.value_ = s; return s; }
/*     */   public ByteString getValueBytes() { Object ref = this.value_; if (ref instanceof String) { ByteString b = ByteString.copyFromUtf8((String)ref); this.value_ = b; return b; }
/*     */      return (ByteString)ref; }
/* 155 */   public void writeTo(CodedOutputStream output) throws IOException { if (!getValueBytes().isEmpty()) {
/* 156 */       GeneratedMessageV3.writeString(output, 1, this.value_);
/*     */     }
/* 158 */     this.unknownFields.writeTo(output); }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSerializedSize() {
/* 163 */     int size = this.memoizedSize;
/* 164 */     if (size != -1) return size;
/*     */     
/* 166 */     size = 0;
/* 167 */     if (!getValueBytes().isEmpty()) {
/* 168 */       size += GeneratedMessageV3.computeStringSize(1, this.value_);
/*     */     }
/* 170 */     size += this.unknownFields.getSerializedSize();
/* 171 */     this.memoizedSize = size;
/* 172 */     return size;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 177 */     if (obj == this) {
/* 178 */       return true;
/*     */     }
/* 180 */     if (!(obj instanceof StringValue)) {
/* 181 */       return super.equals(obj);
/*     */     }
/* 183 */     StringValue other = (StringValue)obj;
/*     */ 
/*     */     
/* 186 */     if (!getValue().equals(other.getValue())) return false; 
/* 187 */     if (!this.unknownFields.equals(other.unknownFields)) return false; 
/* 188 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 193 */     if (this.memoizedHashCode != 0) {
/* 194 */       return this.memoizedHashCode;
/*     */     }
/* 196 */     int hash = 41;
/* 197 */     hash = 19 * hash + getDescriptor().hashCode();
/* 198 */     hash = 37 * hash + 1;
/* 199 */     hash = 53 * hash + getValue().hashCode();
/* 200 */     hash = 29 * hash + this.unknownFields.hashCode();
/* 201 */     this.memoizedHashCode = hash;
/* 202 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static StringValue parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
/* 208 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static StringValue parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 214 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public static StringValue parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 219 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static StringValue parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 225 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static StringValue parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 229 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static StringValue parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 235 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static StringValue parseFrom(InputStream input) throws IOException {
/* 239 */     return 
/* 240 */       GeneratedMessageV3.<StringValue>parseWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static StringValue parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 246 */     return 
/* 247 */       GeneratedMessageV3.<StringValue>parseWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static StringValue parseDelimitedFrom(InputStream input) throws IOException {
/* 251 */     return 
/* 252 */       GeneratedMessageV3.<StringValue>parseDelimitedWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static StringValue parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 258 */     return 
/* 259 */       GeneratedMessageV3.<StringValue>parseDelimitedWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public static StringValue parseFrom(CodedInputStream input) throws IOException {
/* 264 */     return 
/* 265 */       GeneratedMessageV3.<StringValue>parseWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static StringValue parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 271 */     return 
/* 272 */       GeneratedMessageV3.<StringValue>parseWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */   
/*     */   public Builder newBuilderForType() {
/* 276 */     return newBuilder();
/*     */   } public static Builder newBuilder() {
/* 278 */     return DEFAULT_INSTANCE.toBuilder();
/*     */   }
/*     */   public static Builder newBuilder(StringValue prototype) {
/* 281 */     return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
/*     */   }
/*     */   
/*     */   public Builder toBuilder() {
/* 285 */     return (this == DEFAULT_INSTANCE) ? new Builder() : (new Builder())
/* 286 */       .mergeFrom(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Builder newBuilderForType(BuilderParent parent) {
/* 292 */     Builder builder = new Builder(parent);
/* 293 */     return builder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final class Builder
/*     */     extends GeneratedMessageV3.Builder<Builder>
/*     */     implements StringValueOrBuilder
/*     */   {
/*     */     private Object value_;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static final Descriptors.Descriptor getDescriptor() {
/* 309 */       return WrappersProto.internal_static_google_protobuf_StringValue_descriptor;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected FieldAccessorTable internalGetFieldAccessorTable() {
/* 315 */       return WrappersProto.internal_static_google_protobuf_StringValue_fieldAccessorTable
/* 316 */         .ensureFieldAccessorsInitialized((Class)StringValue.class, (Class)Builder.class);
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
/*     */     
/*     */     private Builder()
/*     */     {
/* 448 */       this.value_ = ""; maybeForceBuilderInitialization(); } private Builder(BuilderParent parent) { super(parent); this.value_ = ""; maybeForceBuilderInitialization(); } private void maybeForceBuilderInitialization() { if (GeneratedMessageV3.alwaysUseFieldBuilders); }
/*     */     public Builder clear() { super.clear(); this.value_ = ""; return this; }
/*     */     public Descriptors.Descriptor getDescriptorForType() { return WrappersProto.internal_static_google_protobuf_StringValue_descriptor; }
/*     */     public StringValue getDefaultInstanceForType() { return StringValue.getDefaultInstance(); }
/*     */     public StringValue build() { StringValue result = buildPartial(); if (!result.isInitialized())
/*     */         throw newUninitializedMessageException(result);  return result; }
/*     */     public StringValue buildPartial() { StringValue result = new StringValue(this); result.value_ = this.value_; onBuilt();
/*     */       return result; }
/*     */     public Builder clone() { return super.clone(); }
/*     */     public Builder setField(Descriptors.FieldDescriptor field, Object value) { return super.setField(field, value); }
/* 458 */     public String getValue() { Object ref = this.value_;
/* 459 */       if (!(ref instanceof String)) {
/* 460 */         ByteString bs = (ByteString)ref;
/*     */         
/* 462 */         String s = bs.toStringUtf8();
/* 463 */         this.value_ = s;
/* 464 */         return s;
/*     */       } 
/* 466 */       return (String)ref; }
/*     */      public Builder clearField(Descriptors.FieldDescriptor field) {
/*     */       return super.clearField(field);
/*     */     }
/*     */     public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
/*     */       return super.clearOneof(oneof);
/*     */     }
/*     */     public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
/*     */       return super.setRepeatedField(field, index, value);
/*     */     }
/*     */     public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
/*     */       return super.addRepeatedField(field, value);
/*     */     }
/* 479 */     public ByteString getValueBytes() { Object ref = this.value_;
/* 480 */       if (ref instanceof String) {
/*     */         
/* 482 */         ByteString b = ByteString.copyFromUtf8((String)ref);
/*     */         
/* 484 */         this.value_ = b;
/* 485 */         return b;
/*     */       } 
/* 487 */       return (ByteString)ref; } public Builder mergeFrom(Message other) { if (other instanceof StringValue)
/*     */         return mergeFrom((StringValue)other);  super.mergeFrom(other); return this; }
/*     */     public Builder mergeFrom(StringValue other) { if (other == StringValue.getDefaultInstance())
/*     */         return this;  if (!other.getValue().isEmpty()) { this.value_ = other.value_; onChanged(); }
/*     */        mergeUnknownFields(other.unknownFields); onChanged(); return this; }
/*     */     public final boolean isInitialized() { return true; }
/*     */     public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException { StringValue parsedMessage = null; try {
/*     */         parsedMessage = StringValue.PARSER.parsePartialFrom(input, extensionRegistry);
/*     */       } catch (InvalidProtocolBufferException e) {
/*     */         parsedMessage = (StringValue)e.getUnfinishedMessage(); throw e.unwrapIOException();
/*     */       } finally {
/*     */         if (parsedMessage != null)
/*     */           mergeFrom(parsedMessage); 
/*     */       }  return this; }
/* 501 */     public Builder setValue(String value) { if (value == null) {
/* 502 */         throw new NullPointerException();
/*     */       }
/*     */       
/* 505 */       this.value_ = value;
/* 506 */       onChanged();
/* 507 */       return this; }
/*     */ 
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
/* 519 */       this.value_ = StringValue.getDefaultInstance().getValue();
/* 520 */       onChanged();
/* 521 */       return this;
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
/*     */     public Builder setValueBytes(ByteString value) {
/* 534 */       if (value == null) {
/* 535 */         throw new NullPointerException();
/*     */       }
/* 537 */       AbstractMessageLite.checkByteStringIsUtf8(value);
/*     */       
/* 539 */       this.value_ = value;
/* 540 */       onChanged();
/* 541 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
/* 546 */       return super.setUnknownFields(unknownFields);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
/* 552 */       return super.mergeUnknownFields(unknownFields);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 562 */   private static final StringValue DEFAULT_INSTANCE = new StringValue();
/*     */ 
/*     */   
/*     */   public static StringValue getDefaultInstance() {
/* 566 */     return DEFAULT_INSTANCE;
/*     */   }
/*     */   
/*     */   public static StringValue of(String value) {
/* 570 */     return newBuilder().setValue(value).build();
/*     */   }
/*     */ 
/*     */   
/* 574 */   private static final Parser<StringValue> PARSER = new AbstractParser<StringValue>()
/*     */     {
/*     */ 
/*     */       
/*     */       public StringValue parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException
/*     */       {
/* 580 */         return new StringValue(input, extensionRegistry);
/*     */       }
/*     */     };
/*     */   
/*     */   public static Parser<StringValue> parser() {
/* 585 */     return PARSER;
/*     */   }
/*     */ 
/*     */   
/*     */   public Parser<StringValue> getParserForType() {
/* 590 */     return PARSER;
/*     */   }
/*     */ 
/*     */   
/*     */   public StringValue getDefaultInstanceForType() {
/* 595 */     return DEFAULT_INSTANCE;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\StringValue.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */