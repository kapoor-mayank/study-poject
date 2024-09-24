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
/*     */ public final class Mixin
/*     */   extends GeneratedMessageV3
/*     */   implements MixinOrBuilder
/*     */ {
/*     */   private static final long serialVersionUID = 0L;
/*     */   public static final int NAME_FIELD_NUMBER = 1;
/*     */   private volatile Object name_;
/*     */   public static final int ROOT_FIELD_NUMBER = 2;
/*     */   private volatile Object root_;
/*     */   private byte memoizedIsInitialized;
/*     */   
/*     */   private Mixin(GeneratedMessageV3.Builder<?> builder) {
/*  81 */     super(builder);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 256 */     this.memoizedIsInitialized = -1; } protected Object newInstance(UnusedPrivateParameter unused) { return new Mixin(); } public final UnknownFieldSet getUnknownFields() { return this.unknownFields; } private Mixin() { this.memoizedIsInitialized = -1; this.name_ = ""; this.root_ = ""; }
/*     */   private Mixin(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException { this(); if (extensionRegistry == null) throw new NullPointerException();  UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(); try { boolean done = false; while (!done) { String s; int tag = input.readTag(); switch (tag) { case 0: done = true; continue;case 10: s = input.readStringRequireUtf8(); this.name_ = s; continue;case 18: s = input.readStringRequireUtf8(); this.root_ = s; continue; }  if (!parseUnknownField(input, unknownFields, extensionRegistry, tag))
/*     */           done = true;  }  } catch (InvalidProtocolBufferException e) { throw e.setUnfinishedMessage(this); } catch (IOException e) { throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(this); } finally { this.unknownFields = unknownFields.build(); makeExtensionsImmutable(); }  }
/* 259 */   public static final Descriptors.Descriptor getDescriptor() { return ApiProto.internal_static_google_protobuf_Mixin_descriptor; } public final boolean isInitialized() { byte isInitialized = this.memoizedIsInitialized;
/* 260 */     if (isInitialized == 1) return true; 
/* 261 */     if (isInitialized == 0) return false;
/*     */     
/* 263 */     this.memoizedIsInitialized = 1;
/* 264 */     return true; } protected FieldAccessorTable internalGetFieldAccessorTable() { return ApiProto.internal_static_google_protobuf_Mixin_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)Mixin.class, (Class)Builder.class); }
/*     */   public String getName() { Object ref = this.name_; if (ref instanceof String) return (String)ref;  ByteString bs = (ByteString)ref; String s = bs.toStringUtf8(); this.name_ = s; return s; }
/*     */   public ByteString getNameBytes() { Object ref = this.name_; if (ref instanceof String) { ByteString b = ByteString.copyFromUtf8((String)ref); this.name_ = b; return b; }  return (ByteString)ref; }
/*     */   public String getRoot() { Object ref = this.root_; if (ref instanceof String)
/*     */       return (String)ref;  ByteString bs = (ByteString)ref; String s = bs.toStringUtf8(); this.root_ = s; return s; }
/*     */   public ByteString getRootBytes() { Object ref = this.root_; if (ref instanceof String) { ByteString b = ByteString.copyFromUtf8((String)ref); this.root_ = b; return b; }  return (ByteString)ref; }
/* 270 */   public void writeTo(CodedOutputStream output) throws IOException { if (!getNameBytes().isEmpty()) {
/* 271 */       GeneratedMessageV3.writeString(output, 1, this.name_);
/*     */     }
/* 273 */     if (!getRootBytes().isEmpty()) {
/* 274 */       GeneratedMessageV3.writeString(output, 2, this.root_);
/*     */     }
/* 276 */     this.unknownFields.writeTo(output); }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSerializedSize() {
/* 281 */     int size = this.memoizedSize;
/* 282 */     if (size != -1) return size;
/*     */     
/* 284 */     size = 0;
/* 285 */     if (!getNameBytes().isEmpty()) {
/* 286 */       size += GeneratedMessageV3.computeStringSize(1, this.name_);
/*     */     }
/* 288 */     if (!getRootBytes().isEmpty()) {
/* 289 */       size += GeneratedMessageV3.computeStringSize(2, this.root_);
/*     */     }
/* 291 */     size += this.unknownFields.getSerializedSize();
/* 292 */     this.memoizedSize = size;
/* 293 */     return size;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 298 */     if (obj == this) {
/* 299 */       return true;
/*     */     }
/* 301 */     if (!(obj instanceof Mixin)) {
/* 302 */       return super.equals(obj);
/*     */     }
/* 304 */     Mixin other = (Mixin)obj;
/*     */ 
/*     */     
/* 307 */     if (!getName().equals(other.getName())) return false;
/*     */     
/* 309 */     if (!getRoot().equals(other.getRoot())) return false; 
/* 310 */     if (!this.unknownFields.equals(other.unknownFields)) return false; 
/* 311 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 316 */     if (this.memoizedHashCode != 0) {
/* 317 */       return this.memoizedHashCode;
/*     */     }
/* 319 */     int hash = 41;
/* 320 */     hash = 19 * hash + getDescriptor().hashCode();
/* 321 */     hash = 37 * hash + 1;
/* 322 */     hash = 53 * hash + getName().hashCode();
/* 323 */     hash = 37 * hash + 2;
/* 324 */     hash = 53 * hash + getRoot().hashCode();
/* 325 */     hash = 29 * hash + this.unknownFields.hashCode();
/* 326 */     this.memoizedHashCode = hash;
/* 327 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Mixin parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
/* 333 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Mixin parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 339 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Mixin parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 344 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Mixin parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 350 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static Mixin parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 354 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Mixin parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 360 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static Mixin parseFrom(InputStream input) throws IOException {
/* 364 */     return 
/* 365 */       GeneratedMessageV3.<Mixin>parseWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Mixin parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 371 */     return 
/* 372 */       GeneratedMessageV3.<Mixin>parseWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static Mixin parseDelimitedFrom(InputStream input) throws IOException {
/* 376 */     return 
/* 377 */       GeneratedMessageV3.<Mixin>parseDelimitedWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Mixin parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 383 */     return 
/* 384 */       GeneratedMessageV3.<Mixin>parseDelimitedWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Mixin parseFrom(CodedInputStream input) throws IOException {
/* 389 */     return 
/* 390 */       GeneratedMessageV3.<Mixin>parseWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Mixin parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 396 */     return 
/* 397 */       GeneratedMessageV3.<Mixin>parseWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */   
/*     */   public Builder newBuilderForType() {
/* 401 */     return newBuilder();
/*     */   } public static Builder newBuilder() {
/* 403 */     return DEFAULT_INSTANCE.toBuilder();
/*     */   }
/*     */   public static Builder newBuilder(Mixin prototype) {
/* 406 */     return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
/*     */   }
/*     */   
/*     */   public Builder toBuilder() {
/* 410 */     return (this == DEFAULT_INSTANCE) ? new Builder() : (new Builder())
/* 411 */       .mergeFrom(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Builder newBuilderForType(BuilderParent parent) {
/* 417 */     Builder builder = new Builder(parent);
/* 418 */     return builder;
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
/*     */   public static final class Builder
/*     */     extends GeneratedMessageV3.Builder<Builder>
/*     */     implements MixinOrBuilder
/*     */   {
/*     */     private Object name_;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private Object root_;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 494 */       return ApiProto.internal_static_google_protobuf_Mixin_descriptor;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected FieldAccessorTable internalGetFieldAccessorTable() {
/* 500 */       return ApiProto.internal_static_google_protobuf_Mixin_fieldAccessorTable
/* 501 */         .ensureFieldAccessorsInitialized((Class)Mixin.class, (Class)Builder.class);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private Builder()
/*     */     {
/* 640 */       this.name_ = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 736 */       this.root_ = ""; maybeForceBuilderInitialization(); } private Builder(BuilderParent parent) { super(parent); this.name_ = ""; this.root_ = ""; maybeForceBuilderInitialization(); } private void maybeForceBuilderInitialization() { if (GeneratedMessageV3.alwaysUseFieldBuilders); }
/*     */     public Builder clear() { super.clear(); this.name_ = ""; this.root_ = ""; return this; }
/*     */     public Descriptors.Descriptor getDescriptorForType() { return ApiProto.internal_static_google_protobuf_Mixin_descriptor; }
/*     */     public Mixin getDefaultInstanceForType() { return Mixin.getDefaultInstance(); }
/*     */     public Mixin build() { Mixin result = buildPartial(); if (!result.isInitialized())
/*     */         throw newUninitializedMessageException(result);  return result; }
/*     */     public Mixin buildPartial() { Mixin result = new Mixin(this); result.name_ = this.name_; result.root_ = this.root_; onBuilt(); return result; }
/*     */     public Builder clone() { return super.clone(); }
/*     */     public Builder setField(Descriptors.FieldDescriptor field, Object value) { return super.setField(field, value); }
/*     */     public Builder clearField(Descriptors.FieldDescriptor field) { return super.clearField(field); }
/*     */     public Builder clearOneof(Descriptors.OneofDescriptor oneof) { return super.clearOneof(oneof); }
/* 747 */     public String getRoot() { Object ref = this.root_;
/* 748 */       if (!(ref instanceof String)) {
/* 749 */         ByteString bs = (ByteString)ref;
/*     */         
/* 751 */         String s = bs.toStringUtf8();
/* 752 */         this.root_ = s;
/* 753 */         return s;
/*     */       } 
/* 755 */       return (String)ref; } public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) { return super.setRepeatedField(field, index, value); }
/*     */     public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) { return super.addRepeatedField(field, value); }
/*     */     public Builder mergeFrom(Message other) { if (other instanceof Mixin)
/*     */         return mergeFrom((Mixin)other);  super.mergeFrom(other); return this; }
/*     */     public Builder mergeFrom(Mixin other) { if (other == Mixin.getDefaultInstance())
/*     */         return this;  if (!other.getName().isEmpty()) {
/*     */         this.name_ = other.name_; onChanged();
/*     */       }  if (!other.getRoot().isEmpty()) {
/*     */         this.root_ = other.root_; onChanged();
/*     */       } 
/*     */       mergeUnknownFields(other.unknownFields);
/*     */       onChanged();
/*     */       return this; }
/*     */     public final boolean isInitialized() { return true; }
/* 769 */     public ByteString getRootBytes() { Object ref = this.root_;
/* 770 */       if (ref instanceof String) {
/*     */         
/* 772 */         ByteString b = ByteString.copyFromUtf8((String)ref);
/*     */         
/* 774 */         this.root_ = b;
/* 775 */         return b;
/*     */       } 
/* 777 */       return (ByteString)ref; } public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException { Mixin parsedMessage = null; try { parsedMessage = Mixin.PARSER.parsePartialFrom(input, extensionRegistry); }
/*     */       catch (InvalidProtocolBufferException e) { parsedMessage = (Mixin)e.getUnfinishedMessage(); throw e.unwrapIOException(); }
/*     */       finally { if (parsedMessage != null)
/*     */           mergeFrom(parsedMessage);  }
/*     */        return this; }
/*     */     public String getName() { Object ref = this.name_; if (!(ref instanceof String)) { ByteString bs = (ByteString)ref; String s = bs.toStringUtf8(); this.name_ = s; return s; }
/*     */        return (String)ref; }
/*     */     public ByteString getNameBytes() { Object ref = this.name_; if (ref instanceof String) {
/*     */         ByteString b = ByteString.copyFromUtf8((String)ref); this.name_ = b; return b;
/*     */       }  return (ByteString)ref; }
/*     */     public Builder setName(String value) { if (value == null)
/*     */         throw new NullPointerException();  this.name_ = value; onChanged(); return this; }
/*     */     public Builder clearName() { this.name_ = Mixin.getDefaultInstance().getName(); onChanged(); return this; }
/*     */     public Builder setNameBytes(ByteString value) { if (value == null)
/*     */         throw new NullPointerException();  AbstractMessageLite.checkByteStringIsUtf8(value); this.name_ = value; onChanged(); return this; }
/* 792 */     public Builder setRoot(String value) { if (value == null) {
/* 793 */         throw new NullPointerException();
/*     */       }
/*     */       
/* 796 */       this.root_ = value;
/* 797 */       onChanged();
/* 798 */       return this; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder clearRoot() {
/* 811 */       this.root_ = Mixin.getDefaultInstance().getRoot();
/* 812 */       onChanged();
/* 813 */       return this;
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
/*     */     public Builder setRootBytes(ByteString value) {
/* 827 */       if (value == null) {
/* 828 */         throw new NullPointerException();
/*     */       }
/* 830 */       AbstractMessageLite.checkByteStringIsUtf8(value);
/*     */       
/* 832 */       this.root_ = value;
/* 833 */       onChanged();
/* 834 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
/* 839 */       return super.setUnknownFields(unknownFields);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
/* 845 */       return super.mergeUnknownFields(unknownFields);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 855 */   private static final Mixin DEFAULT_INSTANCE = new Mixin();
/*     */ 
/*     */   
/*     */   public static Mixin getDefaultInstance() {
/* 859 */     return DEFAULT_INSTANCE;
/*     */   }
/*     */ 
/*     */   
/* 863 */   private static final Parser<Mixin> PARSER = new AbstractParser<Mixin>()
/*     */     {
/*     */ 
/*     */       
/*     */       public Mixin parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException
/*     */       {
/* 869 */         return new Mixin(input, extensionRegistry);
/*     */       }
/*     */     };
/*     */   
/*     */   public static Parser<Mixin> parser() {
/* 874 */     return PARSER;
/*     */   }
/*     */ 
/*     */   
/*     */   public Parser<Mixin> getParserForType() {
/* 879 */     return PARSER;
/*     */   }
/*     */ 
/*     */   
/*     */   public Mixin getDefaultInstanceForType() {
/* 884 */     return DEFAULT_INSTANCE;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\Mixin.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */