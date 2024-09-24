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
/*     */ public final class SourceContext
/*     */   extends GeneratedMessageV3
/*     */   implements SourceContextOrBuilder
/*     */ {
/*     */   private static final long serialVersionUID = 0L;
/*     */   public static final int FILE_NAME_FIELD_NUMBER = 1;
/*     */   private volatile Object fileName_;
/*     */   private byte memoizedIsInitialized;
/*     */   
/*     */   private SourceContext(GeneratedMessageV3.Builder<?> builder) {
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
/*     */ 
/*     */     
/* 143 */     this.memoizedIsInitialized = -1; } protected Object newInstance(UnusedPrivateParameter unused) { return new SourceContext(); } public final UnknownFieldSet getUnknownFields() { return this.unknownFields; } private SourceContext() { this.memoizedIsInitialized = -1; this.fileName_ = ""; }
/*     */   private SourceContext(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException { this(); if (extensionRegistry == null)
/*     */       throw new NullPointerException();  UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(); try { boolean done = false; while (!done) { String s; int tag = input.readTag(); switch (tag) { case 0: done = true; continue;case 10: s = input.readStringRequireUtf8(); this.fileName_ = s; continue; }  if (!parseUnknownField(input, unknownFields, extensionRegistry, tag))
/* 146 */           done = true;  }  } catch (InvalidProtocolBufferException e) { throw e.setUnfinishedMessage(this); } catch (IOException e) { throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(this); } finally { this.unknownFields = unknownFields.build(); makeExtensionsImmutable(); }  } public final boolean isInitialized() { byte isInitialized = this.memoizedIsInitialized;
/* 147 */     if (isInitialized == 1) return true; 
/* 148 */     if (isInitialized == 0) return false;
/*     */     
/* 150 */     this.memoizedIsInitialized = 1;
/* 151 */     return true; } public static final Descriptors.Descriptor getDescriptor() { return SourceContextProto.internal_static_google_protobuf_SourceContext_descriptor; }
/*     */   protected FieldAccessorTable internalGetFieldAccessorTable() { return SourceContextProto.internal_static_google_protobuf_SourceContext_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)SourceContext.class, (Class)Builder.class); }
/*     */   public String getFileName() { Object ref = this.fileName_; if (ref instanceof String)
/*     */       return (String)ref;  ByteString bs = (ByteString)ref; String s = bs.toStringUtf8(); this.fileName_ = s; return s; }
/*     */   public ByteString getFileNameBytes() { Object ref = this.fileName_; if (ref instanceof String) { ByteString b = ByteString.copyFromUtf8((String)ref); this.fileName_ = b; return b; }
/*     */      return (ByteString)ref; }
/* 157 */   public void writeTo(CodedOutputStream output) throws IOException { if (!getFileNameBytes().isEmpty()) {
/* 158 */       GeneratedMessageV3.writeString(output, 1, this.fileName_);
/*     */     }
/* 160 */     this.unknownFields.writeTo(output); }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSerializedSize() {
/* 165 */     int size = this.memoizedSize;
/* 166 */     if (size != -1) return size;
/*     */     
/* 168 */     size = 0;
/* 169 */     if (!getFileNameBytes().isEmpty()) {
/* 170 */       size += GeneratedMessageV3.computeStringSize(1, this.fileName_);
/*     */     }
/* 172 */     size += this.unknownFields.getSerializedSize();
/* 173 */     this.memoizedSize = size;
/* 174 */     return size;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 179 */     if (obj == this) {
/* 180 */       return true;
/*     */     }
/* 182 */     if (!(obj instanceof SourceContext)) {
/* 183 */       return super.equals(obj);
/*     */     }
/* 185 */     SourceContext other = (SourceContext)obj;
/*     */ 
/*     */     
/* 188 */     if (!getFileName().equals(other.getFileName())) return false; 
/* 189 */     if (!this.unknownFields.equals(other.unknownFields)) return false; 
/* 190 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 195 */     if (this.memoizedHashCode != 0) {
/* 196 */       return this.memoizedHashCode;
/*     */     }
/* 198 */     int hash = 41;
/* 199 */     hash = 19 * hash + getDescriptor().hashCode();
/* 200 */     hash = 37 * hash + 1;
/* 201 */     hash = 53 * hash + getFileName().hashCode();
/* 202 */     hash = 29 * hash + this.unknownFields.hashCode();
/* 203 */     this.memoizedHashCode = hash;
/* 204 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static SourceContext parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
/* 210 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static SourceContext parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 216 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public static SourceContext parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 221 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static SourceContext parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 227 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static SourceContext parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 231 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static SourceContext parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 237 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static SourceContext parseFrom(InputStream input) throws IOException {
/* 241 */     return 
/* 242 */       GeneratedMessageV3.<SourceContext>parseWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static SourceContext parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 248 */     return 
/* 249 */       GeneratedMessageV3.<SourceContext>parseWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static SourceContext parseDelimitedFrom(InputStream input) throws IOException {
/* 253 */     return 
/* 254 */       GeneratedMessageV3.<SourceContext>parseDelimitedWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static SourceContext parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 260 */     return 
/* 261 */       GeneratedMessageV3.<SourceContext>parseDelimitedWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public static SourceContext parseFrom(CodedInputStream input) throws IOException {
/* 266 */     return 
/* 267 */       GeneratedMessageV3.<SourceContext>parseWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static SourceContext parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 273 */     return 
/* 274 */       GeneratedMessageV3.<SourceContext>parseWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */   
/*     */   public Builder newBuilderForType() {
/* 278 */     return newBuilder();
/*     */   } public static Builder newBuilder() {
/* 280 */     return DEFAULT_INSTANCE.toBuilder();
/*     */   }
/*     */   public static Builder newBuilder(SourceContext prototype) {
/* 283 */     return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
/*     */   }
/*     */   
/*     */   public Builder toBuilder() {
/* 287 */     return (this == DEFAULT_INSTANCE) ? new Builder() : (new Builder())
/* 288 */       .mergeFrom(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Builder newBuilderForType(BuilderParent parent) {
/* 294 */     Builder builder = new Builder(parent);
/* 295 */     return builder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final class Builder
/*     */     extends GeneratedMessageV3.Builder<Builder>
/*     */     implements SourceContextOrBuilder
/*     */   {
/*     */     private Object fileName_;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static final Descriptors.Descriptor getDescriptor() {
/* 311 */       return SourceContextProto.internal_static_google_protobuf_SourceContext_descriptor;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected FieldAccessorTable internalGetFieldAccessorTable() {
/* 317 */       return SourceContextProto.internal_static_google_protobuf_SourceContext_fieldAccessorTable
/* 318 */         .ensureFieldAccessorsInitialized((Class)SourceContext.class, (Class)Builder.class);
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
/* 450 */       this.fileName_ = ""; maybeForceBuilderInitialization(); } private Builder(BuilderParent parent) { super(parent); this.fileName_ = ""; maybeForceBuilderInitialization(); } private void maybeForceBuilderInitialization() { if (GeneratedMessageV3.alwaysUseFieldBuilders); }
/*     */     public Builder clear() { super.clear(); this.fileName_ = ""; return this; }
/*     */     public Descriptors.Descriptor getDescriptorForType() { return SourceContextProto.internal_static_google_protobuf_SourceContext_descriptor; }
/*     */     public SourceContext getDefaultInstanceForType() { return SourceContext.getDefaultInstance(); }
/*     */     public SourceContext build() { SourceContext result = buildPartial(); if (!result.isInitialized())
/*     */         throw newUninitializedMessageException(result);  return result; }
/*     */     public SourceContext buildPartial() { SourceContext result = new SourceContext(this); result.fileName_ = this.fileName_;
/*     */       onBuilt();
/*     */       return result; }
/*     */     public Builder clone() { return super.clone(); }
/*     */     public Builder setField(Descriptors.FieldDescriptor field, Object value) { return super.setField(field, value); }
/* 461 */     public String getFileName() { Object ref = this.fileName_;
/* 462 */       if (!(ref instanceof String)) {
/* 463 */         ByteString bs = (ByteString)ref;
/*     */         
/* 465 */         String s = bs.toStringUtf8();
/* 466 */         this.fileName_ = s;
/* 467 */         return s;
/*     */       } 
/* 469 */       return (String)ref; }
/*     */     
/*     */     public Builder clearField(Descriptors.FieldDescriptor field) {
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
/* 483 */     public ByteString getFileNameBytes() { Object ref = this.fileName_;
/* 484 */       if (ref instanceof String) {
/*     */         
/* 486 */         ByteString b = ByteString.copyFromUtf8((String)ref);
/*     */         
/* 488 */         this.fileName_ = b;
/* 489 */         return b;
/*     */       } 
/* 491 */       return (ByteString)ref; } public Builder mergeFrom(Message other) { if (other instanceof SourceContext)
/*     */         return mergeFrom((SourceContext)other);  super.mergeFrom(other); return this; }
/*     */     public Builder mergeFrom(SourceContext other) { if (other == SourceContext.getDefaultInstance())
/*     */         return this;  if (!other.getFileName().isEmpty()) {
/*     */         this.fileName_ = other.fileName_; onChanged();
/*     */       }  mergeUnknownFields(other.unknownFields); onChanged(); return this; }
/*     */     public final boolean isInitialized() { return true; }
/*     */     public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException { SourceContext parsedMessage = null; try {
/*     */         parsedMessage = SourceContext.PARSER.parsePartialFrom(input, extensionRegistry);
/*     */       } catch (InvalidProtocolBufferException e) {
/*     */         parsedMessage = (SourceContext)e.getUnfinishedMessage(); throw e.unwrapIOException();
/*     */       } finally {
/*     */         if (parsedMessage != null)
/*     */           mergeFrom(parsedMessage); 
/*     */       }  return this; }
/* 506 */     public Builder setFileName(String value) { if (value == null) {
/* 507 */         throw new NullPointerException();
/*     */       }
/*     */       
/* 510 */       this.fileName_ = value;
/* 511 */       onChanged();
/* 512 */       return this; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder clearFileName() {
/* 525 */       this.fileName_ = SourceContext.getDefaultInstance().getFileName();
/* 526 */       onChanged();
/* 527 */       return this;
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
/*     */     public Builder setFileNameBytes(ByteString value) {
/* 541 */       if (value == null) {
/* 542 */         throw new NullPointerException();
/*     */       }
/* 544 */       AbstractMessageLite.checkByteStringIsUtf8(value);
/*     */       
/* 546 */       this.fileName_ = value;
/* 547 */       onChanged();
/* 548 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
/* 553 */       return super.setUnknownFields(unknownFields);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
/* 559 */       return super.mergeUnknownFields(unknownFields);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 569 */   private static final SourceContext DEFAULT_INSTANCE = new SourceContext();
/*     */ 
/*     */   
/*     */   public static SourceContext getDefaultInstance() {
/* 573 */     return DEFAULT_INSTANCE;
/*     */   }
/*     */ 
/*     */   
/* 577 */   private static final Parser<SourceContext> PARSER = new AbstractParser<SourceContext>()
/*     */     {
/*     */ 
/*     */       
/*     */       public SourceContext parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException
/*     */       {
/* 583 */         return new SourceContext(input, extensionRegistry);
/*     */       }
/*     */     };
/*     */   
/*     */   public static Parser<SourceContext> parser() {
/* 588 */     return PARSER;
/*     */   }
/*     */ 
/*     */   
/*     */   public Parser<SourceContext> getParserForType() {
/* 593 */     return PARSER;
/*     */   }
/*     */ 
/*     */   
/*     */   public SourceContext getDefaultInstanceForType() {
/* 598 */     return DEFAULT_INSTANCE;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\SourceContext.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */