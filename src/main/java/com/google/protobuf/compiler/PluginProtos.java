/*      */ package com.google.protobuf.compiler;public final class PluginProtos { private static final Descriptors.Descriptor internal_static_google_protobuf_compiler_Version_descriptor;
/*      */   private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_compiler_Version_fieldAccessorTable;
/*      */   private static final Descriptors.Descriptor internal_static_google_protobuf_compiler_CodeGeneratorRequest_descriptor;
/*      */   private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_compiler_CodeGeneratorRequest_fieldAccessorTable;
/*      */   private static final Descriptors.Descriptor internal_static_google_protobuf_compiler_CodeGeneratorResponse_descriptor;
/*      */   private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_compiler_CodeGeneratorResponse_fieldAccessorTable;
/*      */   private static final Descriptors.Descriptor internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_descriptor;
/*      */   private static final GeneratedMessageV3.FieldAccessorTable internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_fieldAccessorTable;
/*      */   private static Descriptors.FileDescriptor descriptor;
/*      */   
/*      */   public static void registerAllExtensions(ExtensionRegistryLite registry) {}
/*      */   
/*      */   public static void registerAllExtensions(ExtensionRegistry registry) {
/*   14 */     registerAllExtensions((ExtensionRegistryLite)registry);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static interface VersionOrBuilder
/*      */     extends MessageOrBuilder
/*      */   {
/*      */     boolean hasMajor();
/*      */ 
/*      */ 
/*      */     
/*      */     int getMajor();
/*      */ 
/*      */ 
/*      */     
/*      */     boolean hasMinor();
/*      */ 
/*      */ 
/*      */     
/*      */     int getMinor();
/*      */ 
/*      */ 
/*      */     
/*      */     boolean hasPatch();
/*      */ 
/*      */ 
/*      */     
/*      */     int getPatch();
/*      */ 
/*      */ 
/*      */     
/*      */     boolean hasSuffix();
/*      */ 
/*      */ 
/*      */     
/*      */     String getSuffix();
/*      */ 
/*      */ 
/*      */     
/*      */     ByteString getSuffixBytes();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static final class Version
/*      */     extends GeneratedMessageV3
/*      */     implements VersionOrBuilder
/*      */   {
/*      */     private static final long serialVersionUID = 0L;
/*      */ 
/*      */     
/*      */     private int bitField0_;
/*      */ 
/*      */     
/*      */     public static final int MAJOR_FIELD_NUMBER = 1;
/*      */ 
/*      */     
/*      */     private int major_;
/*      */ 
/*      */     
/*      */     public static final int MINOR_FIELD_NUMBER = 2;
/*      */ 
/*      */     
/*      */     private int minor_;
/*      */ 
/*      */     
/*      */     public static final int PATCH_FIELD_NUMBER = 3;
/*      */ 
/*      */     
/*      */     private int patch_;
/*      */ 
/*      */     
/*      */     public static final int SUFFIX_FIELD_NUMBER = 4;
/*      */ 
/*      */     
/*      */     private volatile Object suffix_;
/*      */ 
/*      */     
/*      */     private byte memoizedIsInitialized;
/*      */ 
/*      */ 
/*      */     
/*      */     private Version(GeneratedMessageV3.Builder<?> builder)
/*      */     {
/*  100 */       super(builder);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  311 */       this.memoizedIsInitialized = -1; } private Version() { this.memoizedIsInitialized = -1; this.suffix_ = ""; } protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) { return new Version(); } public final UnknownFieldSet getUnknownFields() { return this.unknownFields; } private Version(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException { this(); if (extensionRegistry == null) throw new NullPointerException();  int mutable_bitField0_ = 0; UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(); try { boolean done = false; while (!done) { ByteString bs; int tag = input.readTag(); switch (tag) { case 0: done = true; continue;case 8: this.bitField0_ |= 0x1; this.major_ = input.readInt32(); continue;case 16: this.bitField0_ |= 0x2; this.minor_ = input.readInt32(); continue;case 24: this.bitField0_ |= 0x4; this.patch_ = input.readInt32(); continue;case 34: bs = input.readBytes(); this.bitField0_ |= 0x8; this.suffix_ = bs; continue; }  if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) done = true;  }  } catch (InvalidProtocolBufferException e) { throw e.setUnfinishedMessage(this); } catch (IOException e) { throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(this); } finally { this.unknownFields = unknownFields.build(); makeExtensionsImmutable(); }  } public static final Descriptors.Descriptor getDescriptor() { return PluginProtos.internal_static_google_protobuf_compiler_Version_descriptor; } protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() { return PluginProtos.internal_static_google_protobuf_compiler_Version_fieldAccessorTable.ensureFieldAccessorsInitialized(Version.class, Builder.class); } public boolean hasMajor() { return ((this.bitField0_ & 0x1) != 0); } public int getMajor() { return this.major_; } public boolean hasMinor() { return ((this.bitField0_ & 0x2) != 0); } public int getMinor() { return this.minor_; } public boolean hasPatch() { return ((this.bitField0_ & 0x4) != 0); } public int getPatch() { return this.patch_; } public boolean hasSuffix() { return ((this.bitField0_ & 0x8) != 0); }
/*      */     public String getSuffix() { Object ref = this.suffix_; if (ref instanceof String) return (String)ref;  ByteString bs = (ByteString)ref; String s = bs.toStringUtf8(); if (bs.isValidUtf8()) this.suffix_ = s;  return s; }
/*      */     public ByteString getSuffixBytes() { Object ref = this.suffix_; if (ref instanceof String) { ByteString b = ByteString.copyFromUtf8((String)ref); this.suffix_ = b; return b; }  return (ByteString)ref; }
/*  314 */     public final boolean isInitialized() { byte isInitialized = this.memoizedIsInitialized;
/*  315 */       if (isInitialized == 1) return true; 
/*  316 */       if (isInitialized == 0) return false;
/*      */       
/*  318 */       this.memoizedIsInitialized = 1;
/*  319 */       return true; }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void writeTo(CodedOutputStream output) throws IOException {
/*  325 */       if ((this.bitField0_ & 0x1) != 0) {
/*  326 */         output.writeInt32(1, this.major_);
/*      */       }
/*  328 */       if ((this.bitField0_ & 0x2) != 0) {
/*  329 */         output.writeInt32(2, this.minor_);
/*      */       }
/*  331 */       if ((this.bitField0_ & 0x4) != 0) {
/*  332 */         output.writeInt32(3, this.patch_);
/*      */       }
/*  334 */       if ((this.bitField0_ & 0x8) != 0) {
/*  335 */         GeneratedMessageV3.writeString(output, 4, this.suffix_);
/*      */       }
/*  337 */       this.unknownFields.writeTo(output);
/*      */     }
/*      */ 
/*      */     
/*      */     public int getSerializedSize() {
/*  342 */       int size = this.memoizedSize;
/*  343 */       if (size != -1) return size;
/*      */       
/*  345 */       size = 0;
/*  346 */       if ((this.bitField0_ & 0x1) != 0) {
/*  347 */         size += 
/*  348 */           CodedOutputStream.computeInt32Size(1, this.major_);
/*      */       }
/*  350 */       if ((this.bitField0_ & 0x2) != 0) {
/*  351 */         size += 
/*  352 */           CodedOutputStream.computeInt32Size(2, this.minor_);
/*      */       }
/*  354 */       if ((this.bitField0_ & 0x4) != 0) {
/*  355 */         size += 
/*  356 */           CodedOutputStream.computeInt32Size(3, this.patch_);
/*      */       }
/*  358 */       if ((this.bitField0_ & 0x8) != 0) {
/*  359 */         size += GeneratedMessageV3.computeStringSize(4, this.suffix_);
/*      */       }
/*  361 */       size += this.unknownFields.getSerializedSize();
/*  362 */       this.memoizedSize = size;
/*  363 */       return size;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean equals(Object obj) {
/*  368 */       if (obj == this) {
/*  369 */         return true;
/*      */       }
/*  371 */       if (!(obj instanceof Version)) {
/*  372 */         return super.equals(obj);
/*      */       }
/*  374 */       Version other = (Version)obj;
/*      */       
/*  376 */       if (hasMajor() != other.hasMajor()) return false; 
/*  377 */       if (hasMajor() && 
/*  378 */         getMajor() != other
/*  379 */         .getMajor()) return false;
/*      */       
/*  381 */       if (hasMinor() != other.hasMinor()) return false; 
/*  382 */       if (hasMinor() && 
/*  383 */         getMinor() != other
/*  384 */         .getMinor()) return false;
/*      */       
/*  386 */       if (hasPatch() != other.hasPatch()) return false; 
/*  387 */       if (hasPatch() && 
/*  388 */         getPatch() != other
/*  389 */         .getPatch()) return false;
/*      */       
/*  391 */       if (hasSuffix() != other.hasSuffix()) return false; 
/*  392 */       if (hasSuffix() && 
/*      */         
/*  394 */         !getSuffix().equals(other.getSuffix())) return false;
/*      */       
/*  396 */       if (!this.unknownFields.equals(other.unknownFields)) return false; 
/*  397 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public int hashCode() {
/*  402 */       if (this.memoizedHashCode != 0) {
/*  403 */         return this.memoizedHashCode;
/*      */       }
/*  405 */       int hash = 41;
/*  406 */       hash = 19 * hash + getDescriptor().hashCode();
/*  407 */       if (hasMajor()) {
/*  408 */         hash = 37 * hash + 1;
/*  409 */         hash = 53 * hash + getMajor();
/*      */       } 
/*  411 */       if (hasMinor()) {
/*  412 */         hash = 37 * hash + 2;
/*  413 */         hash = 53 * hash + getMinor();
/*      */       } 
/*  415 */       if (hasPatch()) {
/*  416 */         hash = 37 * hash + 3;
/*  417 */         hash = 53 * hash + getPatch();
/*      */       } 
/*  419 */       if (hasSuffix()) {
/*  420 */         hash = 37 * hash + 4;
/*  421 */         hash = 53 * hash + getSuffix().hashCode();
/*      */       } 
/*  423 */       hash = 29 * hash + this.unknownFields.hashCode();
/*  424 */       this.memoizedHashCode = hash;
/*  425 */       return hash;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static Version parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
/*  431 */       return (Version)PARSER.parseFrom(data);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static Version parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  437 */       return (Version)PARSER.parseFrom(data, extensionRegistry);
/*      */     }
/*      */ 
/*      */     
/*      */     public static Version parseFrom(ByteString data) throws InvalidProtocolBufferException {
/*  442 */       return (Version)PARSER.parseFrom(data);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static Version parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  448 */       return (Version)PARSER.parseFrom(data, extensionRegistry);
/*      */     }
/*      */     
/*      */     public static Version parseFrom(byte[] data) throws InvalidProtocolBufferException {
/*  452 */       return (Version)PARSER.parseFrom(data);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static Version parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  458 */       return (Version)PARSER.parseFrom(data, extensionRegistry);
/*      */     }
/*      */     
/*      */     public static Version parseFrom(InputStream input) throws IOException {
/*  462 */       return 
/*  463 */         (Version)GeneratedMessageV3.parseWithIOException(PARSER, input);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static Version parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  469 */       return 
/*  470 */         (Version)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
/*      */     }
/*      */     
/*      */     public static Version parseDelimitedFrom(InputStream input) throws IOException {
/*  474 */       return 
/*  475 */         (Version)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static Version parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  481 */       return 
/*  482 */         (Version)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
/*      */     }
/*      */ 
/*      */     
/*      */     public static Version parseFrom(CodedInputStream input) throws IOException {
/*  487 */       return 
/*  488 */         (Version)GeneratedMessageV3.parseWithIOException(PARSER, input);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static Version parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  494 */       return 
/*  495 */         (Version)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
/*      */     }
/*      */     
/*      */     public Builder newBuilderForType() {
/*  499 */       return newBuilder();
/*      */     } public static Builder newBuilder() {
/*  501 */       return DEFAULT_INSTANCE.toBuilder();
/*      */     }
/*      */     public static Builder newBuilder(Version prototype) {
/*  504 */       return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
/*      */     }
/*      */     
/*      */     public Builder toBuilder() {
/*  508 */       return (this == DEFAULT_INSTANCE) ? new Builder() : (new Builder())
/*  509 */         .mergeFrom(this);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
/*  515 */       Builder builder = new Builder(parent);
/*  516 */       return builder;
/*      */     }
/*      */ 
/*      */     
/*      */     public static final class Builder
/*      */       extends GeneratedMessageV3.Builder<Builder>
/*      */       implements VersionOrBuilder
/*      */     {
/*      */       private int bitField0_;
/*      */       private int major_;
/*      */       private int minor_;
/*      */       private int patch_;
/*      */       private Object suffix_;
/*      */       
/*      */       public static final Descriptors.Descriptor getDescriptor() {
/*  531 */         return PluginProtos.internal_static_google_protobuf_compiler_Version_descriptor;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
/*  537 */         return PluginProtos.internal_static_google_protobuf_compiler_Version_fieldAccessorTable
/*  538 */           .ensureFieldAccessorsInitialized(Version.class, Builder.class);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       private Builder()
/*      */       {
/*  822 */         this.suffix_ = ""; maybeForceBuilderInitialization(); } private Builder(GeneratedMessageV3.BuilderParent parent) { super(parent); this.suffix_ = ""; maybeForceBuilderInitialization(); } private void maybeForceBuilderInitialization() { if (Version.alwaysUseFieldBuilders); } public Builder clear() { super.clear(); this.major_ = 0; this.bitField0_ &= 0xFFFFFFFE; this.minor_ = 0; this.bitField0_ &= 0xFFFFFFFD; this.patch_ = 0; this.bitField0_ &= 0xFFFFFFFB; this.suffix_ = ""; this.bitField0_ &= 0xFFFFFFF7; return this; } public Descriptors.Descriptor getDescriptorForType() { return PluginProtos.internal_static_google_protobuf_compiler_Version_descriptor; } public Version getDefaultInstanceForType() { return Version.getDefaultInstance(); } public Version build() { Version result = buildPartial(); if (!result.isInitialized()) throw newUninitializedMessageException(result);  return result; } public Version buildPartial() { Version result = new Version(this); int from_bitField0_ = this.bitField0_; int to_bitField0_ = 0; if ((from_bitField0_ & 0x1) != 0) { result.major_ = this.major_; to_bitField0_ |= 0x1; }  if ((from_bitField0_ & 0x2) != 0) { result.minor_ = this.minor_; to_bitField0_ |= 0x2; }  if ((from_bitField0_ & 0x4) != 0) { result.patch_ = this.patch_; to_bitField0_ |= 0x4; }  if ((from_bitField0_ & 0x8) != 0) to_bitField0_ |= 0x8;  result.suffix_ = this.suffix_; result.bitField0_ = to_bitField0_; onBuilt(); return result; } public Builder clone() { return (Builder)super.clone(); } public Builder setField(Descriptors.FieldDescriptor field, Object value) { return (Builder)super.setField(field, value); } public Builder clearField(Descriptors.FieldDescriptor field) { return (Builder)super.clearField(field); } public Builder clearOneof(Descriptors.OneofDescriptor oneof) { return (Builder)super.clearOneof(oneof); } public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) { return (Builder)super.setRepeatedField(field, index, value); } public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) { return (Builder)super.addRepeatedField(field, value); } public Builder mergeFrom(Message other) { if (other instanceof Version) return mergeFrom((Version)other);  super.mergeFrom(other); return this; } public Builder mergeFrom(Version other) { if (other == Version.getDefaultInstance()) return this;  if (other.hasMajor()) setMajor(other.getMajor());  if (other.hasMinor()) setMinor(other.getMinor());  if (other.hasPatch()) setPatch(other.getPatch());  if (other.hasSuffix()) { this.bitField0_ |= 0x8; this.suffix_ = other.suffix_; onChanged(); }  mergeUnknownFields(other.unknownFields); onChanged(); return this; } public final boolean isInitialized() { return true; } public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException { Version parsedMessage = null; try { parsedMessage = (Version) Version.PARSER.parsePartialFrom(input, extensionRegistry); } catch (InvalidProtocolBufferException e) { parsedMessage = (Version)e.getUnfinishedMessage(); throw e.unwrapIOException(); } finally { if (parsedMessage != null) mergeFrom(parsedMessage);  }  return this; } public boolean hasMajor() { return ((this.bitField0_ & 0x1) != 0); } public int getMajor() { return this.major_; }
/*      */       public Builder setMajor(int value) { this.bitField0_ |= 0x1; this.major_ = value; onChanged(); return this; }
/*      */       public Builder clearMajor() { this.bitField0_ &= 0xFFFFFFFE; this.major_ = 0; onChanged(); return this; }
/*      */       public boolean hasMinor() { return ((this.bitField0_ & 0x2) != 0); }
/*      */       public int getMinor() { return this.minor_; }
/*      */       public Builder setMinor(int value) { this.bitField0_ |= 0x2; this.minor_ = value; onChanged(); return this; }
/*      */       public Builder clearMinor() { this.bitField0_ &= 0xFFFFFFFD; this.minor_ = 0; onChanged(); return this; }
/*      */       public boolean hasPatch() { return ((this.bitField0_ & 0x4) != 0); }
/*      */       public int getPatch() { return this.patch_; }
/*      */       public Builder setPatch(int value) { this.bitField0_ |= 0x4; this.patch_ = value; onChanged(); return this; }
/*      */       public Builder clearPatch() { this.bitField0_ &= 0xFFFFFFFB; this.patch_ = 0; onChanged(); return this; }
/*  833 */       public boolean hasSuffix() { return ((this.bitField0_ & 0x8) != 0); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public String getSuffix() {
/*  845 */         Object ref = this.suffix_;
/*  846 */         if (!(ref instanceof String)) {
/*  847 */           ByteString bs = (ByteString)ref;
/*      */           
/*  849 */           String s = bs.toStringUtf8();
/*  850 */           if (bs.isValidUtf8()) {
/*  851 */             this.suffix_ = s;
/*      */           }
/*  853 */           return s;
/*      */         } 
/*  855 */         return (String)ref;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public ByteString getSuffixBytes() {
/*  869 */         Object ref = this.suffix_;
/*  870 */         if (ref instanceof String) {
/*      */           
/*  872 */           ByteString b = ByteString.copyFromUtf8((String)ref);
/*      */           
/*  874 */           this.suffix_ = b;
/*  875 */           return b;
/*      */         } 
/*  877 */         return (ByteString)ref;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Builder setSuffix(String value) {
/*  892 */         if (value == null) {
/*  893 */           throw new NullPointerException();
/*      */         }
/*  895 */         this.bitField0_ |= 0x8;
/*  896 */         this.suffix_ = value;
/*  897 */         onChanged();
/*  898 */         return this;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Builder clearSuffix() {
/*  910 */         this.bitField0_ &= 0xFFFFFFF7;
/*  911 */         this.suffix_ = Version.getDefaultInstance().getSuffix();
/*  912 */         onChanged();
/*  913 */         return this;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Builder setSuffixBytes(ByteString value) {
/*  927 */         if (value == null) {
/*  928 */           throw new NullPointerException();
/*      */         }
/*  930 */         this.bitField0_ |= 0x8;
/*  931 */         this.suffix_ = value;
/*  932 */         onChanged();
/*  933 */         return this;
/*      */       }
/*      */ 
/*      */       
/*      */       public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
/*  938 */         return (Builder)super.setUnknownFields(unknownFields);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
/*  944 */         return (Builder)super.mergeUnknownFields(unknownFields);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  954 */     private static final Version DEFAULT_INSTANCE = new Version();
/*      */ 
/*      */     
/*      */     public static Version getDefaultInstance() {
/*  958 */       return DEFAULT_INSTANCE;
/*      */     }
/*      */     
/*      */     @Deprecated
/*  962 */     public static final Parser<Version> PARSER = (Parser<Version>)new AbstractParser<Version>()
/*      */       {
/*      */ 
/*      */         
/*      */         public Version parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException
/*      */         {
/*  968 */           return new Version(input, extensionRegistry);
/*      */         }
/*      */       };
/*      */     
/*      */     public static Parser<Version> parser() {
/*  973 */       return PARSER;
/*      */     }
/*      */ 
/*      */     
/*      */     public Parser<Version> getParserForType() {
/*  978 */       return PARSER;
/*      */     }
/*      */ 
/*      */     
/*      */     public Version getDefaultInstanceForType() {
/*  983 */       return DEFAULT_INSTANCE;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static interface CodeGeneratorRequestOrBuilder
/*      */     extends MessageOrBuilder
/*      */   {
/*      */     List<String> getFileToGenerateList();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     int getFileToGenerateCount();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     String getFileToGenerate(int param1Int);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     ByteString getFileToGenerateBytes(int param1Int);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     boolean hasParameter();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     String getParameter();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     ByteString getParameterBytes();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     List<DescriptorProtos.FileDescriptorProto> getProtoFileList();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     DescriptorProtos.FileDescriptorProto getProtoFile(int param1Int);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     int getProtoFileCount();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     List<? extends DescriptorProtos.FileDescriptorProtoOrBuilder> getProtoFileOrBuilderList();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     DescriptorProtos.FileDescriptorProtoOrBuilder getProtoFileOrBuilder(int param1Int);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     boolean hasCompilerVersion();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     Version getCompilerVersion();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     VersionOrBuilder getCompilerVersionOrBuilder();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final class CodeGeneratorRequest
/*      */     extends GeneratedMessageV3
/*      */     implements CodeGeneratorRequestOrBuilder
/*      */   {
/*      */     private static final long serialVersionUID = 0L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private int bitField0_;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static final int FILE_TO_GENERATE_FIELD_NUMBER = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private LazyStringList fileToGenerate_;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static final int PARAMETER_FIELD_NUMBER = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private volatile Object parameter_;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static final int PROTO_FILE_FIELD_NUMBER = 15;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private List<DescriptorProtos.FileDescriptorProto> protoFile_;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static final int COMPILER_VERSION_FIELD_NUMBER = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private Version compilerVersion_;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private byte memoizedIsInitialized;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private CodeGeneratorRequest(GeneratedMessageV3.Builder<?> builder)
/*      */     {
/* 1210 */       super(builder);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1597 */       this.memoizedIsInitialized = -1; } private CodeGeneratorRequest() { this.memoizedIsInitialized = -1; this.fileToGenerate_ = LazyStringArrayList.EMPTY; this.parameter_ = ""; this.protoFile_ = Collections.emptyList(); } protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) { return new CodeGeneratorRequest(); } public final UnknownFieldSet getUnknownFields() { return this.unknownFields; } private CodeGeneratorRequest(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException { this(); if (extensionRegistry == null) throw new NullPointerException();  int mutable_bitField0_ = 0; UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(); try { boolean done = false; while (!done) { ByteString bs; Version.Builder subBuilder; int tag = input.readTag(); switch (tag) { case 0: done = true; continue;case 10: bs = input.readBytes(); if ((mutable_bitField0_ & 0x1) == 0) { this.fileToGenerate_ = (LazyStringList)new LazyStringArrayList(); mutable_bitField0_ |= 0x1; }  this.fileToGenerate_.add(bs); continue;case 18: bs = input.readBytes(); this.bitField0_ |= 0x1; this.parameter_ = bs; continue;case 26: subBuilder = null; if ((this.bitField0_ & 0x2) != 0) subBuilder = this.compilerVersion_.toBuilder();  this.compilerVersion_ = (Version)input.readMessage(Version.PARSER, extensionRegistry); if (subBuilder != null) { subBuilder.mergeFrom(this.compilerVersion_); this.compilerVersion_ = subBuilder.buildPartial(); }  this.bitField0_ |= 0x2; continue;case 122: if ((mutable_bitField0_ & 0x4) == 0) { this.protoFile_ = new ArrayList<>(); mutable_bitField0_ |= 0x4; }  this.protoFile_.add(input.readMessage(DescriptorProtos.FileDescriptorProto.PARSER, extensionRegistry)); continue; }  if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) done = true;  }  } catch (InvalidProtocolBufferException e) { throw e.setUnfinishedMessage(this); } catch (IOException e) { throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(this); } finally { if ((mutable_bitField0_ & 0x1) != 0) this.fileToGenerate_ = this.fileToGenerate_.getUnmodifiableView();  if ((mutable_bitField0_ & 0x4) != 0) this.protoFile_ = Collections.unmodifiableList(this.protoFile_);  this.unknownFields = unknownFields.build(); makeExtensionsImmutable(); }  } public static final Descriptors.Descriptor getDescriptor() { return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorRequest_descriptor; } protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() { return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(CodeGeneratorRequest.class, Builder.class); } public ProtocolStringList getFileToGenerateList() { return (ProtocolStringList)this.fileToGenerate_; } public int getFileToGenerateCount() { return this.fileToGenerate_.size(); } public String getFileToGenerate(int index) { return (String)this.fileToGenerate_.get(index); } public ByteString getFileToGenerateBytes(int index) { return this.fileToGenerate_.getByteString(index); } public boolean hasParameter() { return ((this.bitField0_ & 0x1) != 0); } public String getParameter() { Object ref = this.parameter_; if (ref instanceof String) return (String)ref;  ByteString bs = (ByteString)ref; String s = bs.toStringUtf8(); if (bs.isValidUtf8()) this.parameter_ = s;  return s; } public ByteString getParameterBytes() { Object ref = this.parameter_; if (ref instanceof String) { ByteString b = ByteString.copyFromUtf8((String)ref); this.parameter_ = b; return b; }  return (ByteString)ref; } public List<DescriptorProtos.FileDescriptorProto> getProtoFileList() { return this.protoFile_; } public List<? extends DescriptorProtos.FileDescriptorProtoOrBuilder> getProtoFileOrBuilderList() { return (List)this.protoFile_; } public int getProtoFileCount() { return this.protoFile_.size(); } public DescriptorProtos.FileDescriptorProto getProtoFile(int index) { return this.protoFile_.get(index); } public DescriptorProtos.FileDescriptorProtoOrBuilder getProtoFileOrBuilder(int index) { return (DescriptorProtos.FileDescriptorProtoOrBuilder)this.protoFile_.get(index); } public boolean hasCompilerVersion() { return ((this.bitField0_ & 0x2) != 0); }
/*      */     public Version getCompilerVersion() { return (this.compilerVersion_ == null) ? Version.getDefaultInstance() : this.compilerVersion_; }
/*      */     public VersionOrBuilder getCompilerVersionOrBuilder() { return (this.compilerVersion_ == null) ? Version.getDefaultInstance() : this.compilerVersion_; }
/* 1600 */     public final boolean isInitialized() { byte isInitialized = this.memoizedIsInitialized;
/* 1601 */       if (isInitialized == 1) return true; 
/* 1602 */       if (isInitialized == 0) return false;
/*      */       
/* 1604 */       for (int i = 0; i < getProtoFileCount(); i++) {
/* 1605 */         if (!getProtoFile(i).isInitialized()) {
/* 1606 */           this.memoizedIsInitialized = 0;
/* 1607 */           return false;
/*      */         } 
/*      */       } 
/* 1610 */       this.memoizedIsInitialized = 1;
/* 1611 */       return true; }
/*      */ 
/*      */ 
/*      */     
/*      */     public void writeTo(CodedOutputStream output) throws IOException {
/*      */       int i;
/* 1617 */       for (i = 0; i < this.fileToGenerate_.size(); i++) {
/* 1618 */         GeneratedMessageV3.writeString(output, 1, this.fileToGenerate_.getRaw(i));
/*      */       }
/* 1620 */       if ((this.bitField0_ & 0x1) != 0) {
/* 1621 */         GeneratedMessageV3.writeString(output, 2, this.parameter_);
/*      */       }
/* 1623 */       if ((this.bitField0_ & 0x2) != 0) {
/* 1624 */         output.writeMessage(3, (MessageLite)getCompilerVersion());
/*      */       }
/* 1626 */       for (i = 0; i < this.protoFile_.size(); i++) {
/* 1627 */         output.writeMessage(15, (MessageLite)this.protoFile_.get(i));
/*      */       }
/* 1629 */       this.unknownFields.writeTo(output);
/*      */     }
/*      */ 
/*      */     
/*      */     public int getSerializedSize() {
/* 1634 */       int size = this.memoizedSize;
/* 1635 */       if (size != -1) return size;
/*      */       
/* 1637 */       size = 0;
/*      */       
/* 1639 */       int dataSize = 0;
/* 1640 */       for (int j = 0; j < this.fileToGenerate_.size(); j++) {
/* 1641 */         dataSize += computeStringSizeNoTag(this.fileToGenerate_.getRaw(j));
/*      */       }
/* 1643 */       size += dataSize;
/* 1644 */       size += 1 * getFileToGenerateList().size();
/*      */       
/* 1646 */       if ((this.bitField0_ & 0x1) != 0) {
/* 1647 */         size += GeneratedMessageV3.computeStringSize(2, this.parameter_);
/*      */       }
/* 1649 */       if ((this.bitField0_ & 0x2) != 0) {
/* 1650 */         size += 
/* 1651 */           CodedOutputStream.computeMessageSize(3, (MessageLite)getCompilerVersion());
/*      */       }
/* 1653 */       for (int i = 0; i < this.protoFile_.size(); i++) {
/* 1654 */         size += 
/* 1655 */           CodedOutputStream.computeMessageSize(15, (MessageLite)this.protoFile_.get(i));
/*      */       }
/* 1657 */       size += this.unknownFields.getSerializedSize();
/* 1658 */       this.memoizedSize = size;
/* 1659 */       return size;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean equals(Object obj) {
/* 1664 */       if (obj == this) {
/* 1665 */         return true;
/*      */       }
/* 1667 */       if (!(obj instanceof CodeGeneratorRequest)) {
/* 1668 */         return super.equals(obj);
/*      */       }
/* 1670 */       CodeGeneratorRequest other = (CodeGeneratorRequest)obj;
/*      */ 
/*      */       
/* 1673 */       if (!getFileToGenerateList().equals(other.getFileToGenerateList())) return false; 
/* 1674 */       if (hasParameter() != other.hasParameter()) return false; 
/* 1675 */       if (hasParameter() && 
/*      */         
/* 1677 */         !getParameter().equals(other.getParameter())) return false;
/*      */ 
/*      */       
/* 1680 */       if (!getProtoFileList().equals(other.getProtoFileList())) return false; 
/* 1681 */       if (hasCompilerVersion() != other.hasCompilerVersion()) return false; 
/* 1682 */       if (hasCompilerVersion() && 
/*      */         
/* 1684 */         !getCompilerVersion().equals(other.getCompilerVersion())) return false;
/*      */       
/* 1686 */       if (!this.unknownFields.equals(other.unknownFields)) return false; 
/* 1687 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public int hashCode() {
/* 1692 */       if (this.memoizedHashCode != 0) {
/* 1693 */         return this.memoizedHashCode;
/*      */       }
/* 1695 */       int hash = 41;
/* 1696 */       hash = 19 * hash + getDescriptor().hashCode();
/* 1697 */       if (getFileToGenerateCount() > 0) {
/* 1698 */         hash = 37 * hash + 1;
/* 1699 */         hash = 53 * hash + getFileToGenerateList().hashCode();
/*      */       } 
/* 1701 */       if (hasParameter()) {
/* 1702 */         hash = 37 * hash + 2;
/* 1703 */         hash = 53 * hash + getParameter().hashCode();
/*      */       } 
/* 1705 */       if (getProtoFileCount() > 0) {
/* 1706 */         hash = 37 * hash + 15;
/* 1707 */         hash = 53 * hash + getProtoFileList().hashCode();
/*      */       } 
/* 1709 */       if (hasCompilerVersion()) {
/* 1710 */         hash = 37 * hash + 3;
/* 1711 */         hash = 53 * hash + getCompilerVersion().hashCode();
/*      */       } 
/* 1713 */       hash = 29 * hash + this.unknownFields.hashCode();
/* 1714 */       this.memoizedHashCode = hash;
/* 1715 */       return hash;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static CodeGeneratorRequest parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
/* 1721 */       return (CodeGeneratorRequest)PARSER.parseFrom(data);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static CodeGeneratorRequest parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 1727 */       return (CodeGeneratorRequest)PARSER.parseFrom(data, extensionRegistry);
/*      */     }
/*      */ 
/*      */     
/*      */     public static CodeGeneratorRequest parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 1732 */       return (CodeGeneratorRequest)PARSER.parseFrom(data);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static CodeGeneratorRequest parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 1738 */       return (CodeGeneratorRequest)PARSER.parseFrom(data, extensionRegistry);
/*      */     }
/*      */     
/*      */     public static CodeGeneratorRequest parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 1742 */       return (CodeGeneratorRequest)PARSER.parseFrom(data);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static CodeGeneratorRequest parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 1748 */       return (CodeGeneratorRequest)PARSER.parseFrom(data, extensionRegistry);
/*      */     }
/*      */     
/*      */     public static CodeGeneratorRequest parseFrom(InputStream input) throws IOException {
/* 1752 */       return 
/* 1753 */         (CodeGeneratorRequest)GeneratedMessageV3.parseWithIOException(PARSER, input);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static CodeGeneratorRequest parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 1759 */       return 
/* 1760 */         (CodeGeneratorRequest)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
/*      */     }
/*      */     
/*      */     public static CodeGeneratorRequest parseDelimitedFrom(InputStream input) throws IOException {
/* 1764 */       return 
/* 1765 */         (CodeGeneratorRequest)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static CodeGeneratorRequest parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 1771 */       return 
/* 1772 */         (CodeGeneratorRequest)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
/*      */     }
/*      */ 
/*      */     
/*      */     public static CodeGeneratorRequest parseFrom(CodedInputStream input) throws IOException {
/* 1777 */       return 
/* 1778 */         (CodeGeneratorRequest)GeneratedMessageV3.parseWithIOException(PARSER, input);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static CodeGeneratorRequest parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 1784 */       return 
/* 1785 */         (CodeGeneratorRequest)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
/*      */     }
/*      */     
/*      */     public Builder newBuilderForType() {
/* 1789 */       return newBuilder();
/*      */     } public static Builder newBuilder() {
/* 1791 */       return DEFAULT_INSTANCE.toBuilder();
/*      */     }
/*      */     public static Builder newBuilder(CodeGeneratorRequest prototype) {
/* 1794 */       return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
/*      */     }
/*      */     
/*      */     public Builder toBuilder() {
/* 1798 */       return (this == DEFAULT_INSTANCE) ? new Builder() : (new Builder())
/* 1799 */         .mergeFrom(this);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
/* 1805 */       Builder builder = new Builder(parent);
/* 1806 */       return builder;
/*      */     }
/*      */     
/*      */     public static final class Builder
/*      */       extends GeneratedMessageV3.Builder<Builder>
/*      */       implements CodeGeneratorRequestOrBuilder {
/*      */       private int bitField0_;
/*      */       private LazyStringList fileToGenerate_;
/*      */       private Object parameter_;
/*      */       private List<DescriptorProtos.FileDescriptorProto> protoFile_;
/*      */       private RepeatedFieldBuilderV3<DescriptorProtos.FileDescriptorProto, DescriptorProtos.FileDescriptorProto.Builder, DescriptorProtos.FileDescriptorProtoOrBuilder> protoFileBuilder_;
/*      */       private Version compilerVersion_;
/*      */       private SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> compilerVersionBuilder_;
/*      */       
/*      */       public static final Descriptors.Descriptor getDescriptor() {
/* 1821 */         return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorRequest_descriptor;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
/* 1827 */         return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorRequest_fieldAccessorTable
/* 1828 */           .ensureFieldAccessorsInitialized(CodeGeneratorRequest.class, Builder.class);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       private Builder()
/*      */       {
/* 2050 */         this.fileToGenerate_ = LazyStringArrayList.EMPTY;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2213 */         this.parameter_ = "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2321 */         this
/* 2322 */           .protoFile_ = Collections.emptyList(); maybeForceBuilderInitialization(); } private Builder(GeneratedMessageV3.BuilderParent parent) { super(parent); this.fileToGenerate_ = LazyStringArrayList.EMPTY; this.parameter_ = ""; this.protoFile_ = Collections.emptyList(); maybeForceBuilderInitialization(); } private void maybeForceBuilderInitialization() { if (CodeGeneratorRequest.alwaysUseFieldBuilders) { getProtoFileFieldBuilder(); getCompilerVersionFieldBuilder(); }  } public Builder clear() { super.clear(); this.fileToGenerate_ = LazyStringArrayList.EMPTY; this.bitField0_ &= 0xFFFFFFFE; this.parameter_ = ""; this.bitField0_ &= 0xFFFFFFFD; if (this.protoFileBuilder_ == null) { this.protoFile_ = Collections.emptyList(); this.bitField0_ &= 0xFFFFFFFB; } else { this.protoFileBuilder_.clear(); }  if (this.compilerVersionBuilder_ == null) { this.compilerVersion_ = null; } else { this.compilerVersionBuilder_.clear(); }  this.bitField0_ &= 0xFFFFFFF7; return this; } public Descriptors.Descriptor getDescriptorForType() { return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorRequest_descriptor; } public CodeGeneratorRequest getDefaultInstanceForType() { return CodeGeneratorRequest.getDefaultInstance(); } public CodeGeneratorRequest build() { CodeGeneratorRequest result = buildPartial(); if (!result.isInitialized()) throw newUninitializedMessageException(result);  return result; } public CodeGeneratorRequest buildPartial() { CodeGeneratorRequest result = new CodeGeneratorRequest(this); int from_bitField0_ = this.bitField0_; int to_bitField0_ = 0; if ((this.bitField0_ & 0x1) != 0) { this.fileToGenerate_ = this.fileToGenerate_.getUnmodifiableView(); this.bitField0_ &= 0xFFFFFFFE; }  result.fileToGenerate_ = this.fileToGenerate_; if ((from_bitField0_ & 0x2) != 0) to_bitField0_ |= 0x1;  result.parameter_ = this.parameter_; if (this.protoFileBuilder_ == null) { if ((this.bitField0_ & 0x4) != 0) { this.protoFile_ = Collections.unmodifiableList(this.protoFile_); this.bitField0_ &= 0xFFFFFFFB; }  result.protoFile_ = this.protoFile_; } else { result.protoFile_ = this.protoFileBuilder_.build(); }  if ((from_bitField0_ & 0x8) != 0) { if (this.compilerVersionBuilder_ == null) { result.compilerVersion_ = this.compilerVersion_; } else { result.compilerVersion_ = (Version)this.compilerVersionBuilder_.build(); }  to_bitField0_ |= 0x2; }  result.bitField0_ = to_bitField0_; onBuilt(); return result; } public Builder clone() { return (Builder)super.clone(); } public Builder setField(Descriptors.FieldDescriptor field, Object value) { return (Builder)super.setField(field, value); } public Builder clearField(Descriptors.FieldDescriptor field) { return (Builder)super.clearField(field); } public Builder clearOneof(Descriptors.OneofDescriptor oneof) { return (Builder)super.clearOneof(oneof); } public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) { return (Builder)super.setRepeatedField(field, index, value); } public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) { return (Builder)super.addRepeatedField(field, value); } public Builder mergeFrom(Message other) { if (other instanceof CodeGeneratorRequest) return mergeFrom((CodeGeneratorRequest)other);  super.mergeFrom(other); return this; } public Builder mergeFrom(CodeGeneratorRequest other) { if (other == CodeGeneratorRequest.getDefaultInstance()) return this;  if (!other.fileToGenerate_.isEmpty()) { if (this.fileToGenerate_.isEmpty()) { this.fileToGenerate_ = other.fileToGenerate_; this.bitField0_ &= 0xFFFFFFFE; } else { ensureFileToGenerateIsMutable(); this.fileToGenerate_.addAll((Collection)other.fileToGenerate_); }  onChanged(); }  if (other.hasParameter()) { this.bitField0_ |= 0x2; this.parameter_ = other.parameter_; onChanged(); }  if (this.protoFileBuilder_ == null) { if (!other.protoFile_.isEmpty()) { if (this.protoFile_.isEmpty()) { this.protoFile_ = other.protoFile_; this.bitField0_ &= 0xFFFFFFFB; } else { ensureProtoFileIsMutable(); this.protoFile_.addAll(other.protoFile_); }  onChanged(); }  } else if (!other.protoFile_.isEmpty()) { if (this.protoFileBuilder_.isEmpty()) { this.protoFileBuilder_.dispose(); this.protoFileBuilder_ = null; this.protoFile_ = other.protoFile_; this.bitField0_ &= 0xFFFFFFFB; this.protoFileBuilder_ = CodeGeneratorRequest.alwaysUseFieldBuilders ? getProtoFileFieldBuilder() : null; } else { this.protoFileBuilder_.addAllMessages(other.protoFile_); }  }  if (other.hasCompilerVersion()) mergeCompilerVersion(other.getCompilerVersion());  mergeUnknownFields(other.unknownFields); onChanged(); return this; } public final boolean isInitialized() { for (int i = 0; i < getProtoFileCount(); i++) { if (!getProtoFile(i).isInitialized()) return false;  }  return true; } public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException { CodeGeneratorRequest parsedMessage = null; try { parsedMessage = (CodeGeneratorRequest) CodeGeneratorRequest.PARSER.parsePartialFrom(input, extensionRegistry); } catch (InvalidProtocolBufferException e) { parsedMessage = (CodeGeneratorRequest)e.getUnfinishedMessage(); throw e.unwrapIOException(); } finally { if (parsedMessage != null) mergeFrom(parsedMessage);  }  return this; } private void ensureFileToGenerateIsMutable() { if ((this.bitField0_ & 0x1) == 0) { this.fileToGenerate_ = (LazyStringList)new LazyStringArrayList(this.fileToGenerate_); this.bitField0_ |= 0x1; }  } public ProtocolStringList getFileToGenerateList() { return (ProtocolStringList)this.fileToGenerate_.getUnmodifiableView(); } public int getFileToGenerateCount() { return this.fileToGenerate_.size(); } public String getFileToGenerate(int index) { return (String)this.fileToGenerate_.get(index); } public ByteString getFileToGenerateBytes(int index) { return this.fileToGenerate_.getByteString(index); } public Builder setFileToGenerate(int index, String value) { if (value == null) throw new NullPointerException();  ensureFileToGenerateIsMutable(); this.fileToGenerate_.set(index, value); onChanged(); return this; } public Builder addFileToGenerate(String value) { if (value == null) throw new NullPointerException();  ensureFileToGenerateIsMutable(); this.fileToGenerate_.add(value); onChanged(); return this; } public Builder addAllFileToGenerate(Iterable<String> values) { ensureFileToGenerateIsMutable(); AbstractMessageLite.Builder.addAll(values, (List)this.fileToGenerate_); onChanged(); return this; } public Builder clearFileToGenerate() { this.fileToGenerate_ = LazyStringArrayList.EMPTY; this.bitField0_ &= 0xFFFFFFFE; onChanged(); return this; } public Builder addFileToGenerateBytes(ByteString value) { if (value == null) throw new NullPointerException();  ensureFileToGenerateIsMutable(); this.fileToGenerate_.add(value); onChanged(); return this; } public boolean hasParameter() { return ((this.bitField0_ & 0x2) != 0); } public String getParameter() { Object ref = this.parameter_; if (!(ref instanceof String)) { ByteString bs = (ByteString)ref; String s = bs.toStringUtf8(); if (bs.isValidUtf8()) this.parameter_ = s;  return s; }  return (String)ref; } public ByteString getParameterBytes() { Object ref = this.parameter_; if (ref instanceof String) { ByteString b = ByteString.copyFromUtf8((String)ref); this.parameter_ = b; return b; }  return (ByteString)ref; } public Builder setParameter(String value) { if (value == null) throw new NullPointerException();  this.bitField0_ |= 0x2; this.parameter_ = value; onChanged(); return this; } public Builder clearParameter() { this.bitField0_ &= 0xFFFFFFFD; this.parameter_ = CodeGeneratorRequest.getDefaultInstance().getParameter(); onChanged(); return this; }
/*      */       public Builder setParameterBytes(ByteString value) { if (value == null) throw new NullPointerException();  this.bitField0_ |= 0x2; this.parameter_ = value; onChanged(); return this; }
/* 2324 */       private void ensureProtoFileIsMutable() { if ((this.bitField0_ & 0x4) == 0) {
/* 2325 */           this.protoFile_ = new ArrayList<>(this.protoFile_);
/* 2326 */           this.bitField0_ |= 0x4;
/*      */         }  }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public List<DescriptorProtos.FileDescriptorProto> getProtoFileList() {
/* 2352 */         if (this.protoFileBuilder_ == null) {
/* 2353 */           return Collections.unmodifiableList(this.protoFile_);
/*      */         }
/* 2355 */         return this.protoFileBuilder_.getMessageList();
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public int getProtoFileCount() {
/* 2377 */         if (this.protoFileBuilder_ == null) {
/* 2378 */           return this.protoFile_.size();
/*      */         }
/* 2380 */         return this.protoFileBuilder_.getCount();
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public DescriptorProtos.FileDescriptorProto getProtoFile(int index) {
/* 2402 */         if (this.protoFileBuilder_ == null) {
/* 2403 */           return this.protoFile_.get(index);
/*      */         }
/* 2405 */         return (DescriptorProtos.FileDescriptorProto)this.protoFileBuilder_.getMessage(index);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Builder setProtoFile(int index, DescriptorProtos.FileDescriptorProto value) {
/* 2428 */         if (this.protoFileBuilder_ == null) {
/* 2429 */           if (value == null) {
/* 2430 */             throw new NullPointerException();
/*      */           }
/* 2432 */           ensureProtoFileIsMutable();
/* 2433 */           this.protoFile_.set(index, value);
/* 2434 */           onChanged();
/*      */         } else {
/* 2436 */           this.protoFileBuilder_.setMessage(index, (AbstractMessage)value);
/*      */         } 
/* 2438 */         return this;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Builder setProtoFile(int index, DescriptorProtos.FileDescriptorProto.Builder builderForValue) {
/* 2460 */         if (this.protoFileBuilder_ == null) {
/* 2461 */           ensureProtoFileIsMutable();
/* 2462 */           this.protoFile_.set(index, builderForValue.build());
/* 2463 */           onChanged();
/*      */         } else {
/* 2465 */           this.protoFileBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
/*      */         } 
/* 2467 */         return this;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Builder addProtoFile(DescriptorProtos.FileDescriptorProto value) {
/* 2488 */         if (this.protoFileBuilder_ == null) {
/* 2489 */           if (value == null) {
/* 2490 */             throw new NullPointerException();
/*      */           }
/* 2492 */           ensureProtoFileIsMutable();
/* 2493 */           this.protoFile_.add(value);
/* 2494 */           onChanged();
/*      */         } else {
/* 2496 */           this.protoFileBuilder_.addMessage((AbstractMessage)value);
/*      */         } 
/* 2498 */         return this;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Builder addProtoFile(int index, DescriptorProtos.FileDescriptorProto value) {
/* 2520 */         if (this.protoFileBuilder_ == null) {
/* 2521 */           if (value == null) {
/* 2522 */             throw new NullPointerException();
/*      */           }
/* 2524 */           ensureProtoFileIsMutable();
/* 2525 */           this.protoFile_.add(index, value);
/* 2526 */           onChanged();
/*      */         } else {
/* 2528 */           this.protoFileBuilder_.addMessage(index, (AbstractMessage)value);
/*      */         } 
/* 2530 */         return this;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Builder addProtoFile(DescriptorProtos.FileDescriptorProto.Builder builderForValue) {
/* 2552 */         if (this.protoFileBuilder_ == null) {
/* 2553 */           ensureProtoFileIsMutable();
/* 2554 */           this.protoFile_.add(builderForValue.build());
/* 2555 */           onChanged();
/*      */         } else {
/* 2557 */           this.protoFileBuilder_.addMessage((AbstractMessage)builderForValue.build());
/*      */         } 
/* 2559 */         return this;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Builder addProtoFile(int index, DescriptorProtos.FileDescriptorProto.Builder builderForValue) {
/* 2581 */         if (this.protoFileBuilder_ == null) {
/* 2582 */           ensureProtoFileIsMutable();
/* 2583 */           this.protoFile_.add(index, builderForValue.build());
/* 2584 */           onChanged();
/*      */         } else {
/* 2586 */           this.protoFileBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
/*      */         } 
/* 2588 */         return this;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Builder addAllProtoFile(Iterable<? extends DescriptorProtos.FileDescriptorProto> values) {
/* 2610 */         if (this.protoFileBuilder_ == null) {
/* 2611 */           ensureProtoFileIsMutable();
/* 2612 */           AbstractMessageLite.Builder.addAll(values, this.protoFile_);
/*      */           
/* 2614 */           onChanged();
/*      */         } else {
/* 2616 */           this.protoFileBuilder_.addAllMessages(values);
/*      */         } 
/* 2618 */         return this;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Builder clearProtoFile() {
/* 2639 */         if (this.protoFileBuilder_ == null) {
/* 2640 */           this.protoFile_ = Collections.emptyList();
/* 2641 */           this.bitField0_ &= 0xFFFFFFFB;
/* 2642 */           onChanged();
/*      */         } else {
/* 2644 */           this.protoFileBuilder_.clear();
/*      */         } 
/* 2646 */         return this;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Builder removeProtoFile(int index) {
/* 2667 */         if (this.protoFileBuilder_ == null) {
/* 2668 */           ensureProtoFileIsMutable();
/* 2669 */           this.protoFile_.remove(index);
/* 2670 */           onChanged();
/*      */         } else {
/* 2672 */           this.protoFileBuilder_.remove(index);
/*      */         } 
/* 2674 */         return this;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public DescriptorProtos.FileDescriptorProto.Builder getProtoFileBuilder(int index) {
/* 2696 */         return (DescriptorProtos.FileDescriptorProto.Builder)getProtoFileFieldBuilder().getBuilder(index);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public DescriptorProtos.FileDescriptorProtoOrBuilder getProtoFileOrBuilder(int index) {
/* 2718 */         if (this.protoFileBuilder_ == null)
/* 2719 */           return (DescriptorProtos.FileDescriptorProtoOrBuilder)this.protoFile_.get(index); 
/* 2720 */         return (DescriptorProtos.FileDescriptorProtoOrBuilder)this.protoFileBuilder_.getMessageOrBuilder(index);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public List<? extends DescriptorProtos.FileDescriptorProtoOrBuilder> getProtoFileOrBuilderList() {
/* 2743 */         if (this.protoFileBuilder_ != null) {
/* 2744 */           return this.protoFileBuilder_.getMessageOrBuilderList();
/*      */         }
/* 2746 */         return (List)Collections.unmodifiableList(this.protoFile_);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public DescriptorProtos.FileDescriptorProto.Builder addProtoFileBuilder() {
/* 2768 */         return (DescriptorProtos.FileDescriptorProto.Builder)getProtoFileFieldBuilder().addBuilder(
/* 2769 */             (AbstractMessage)DescriptorProtos.FileDescriptorProto.getDefaultInstance());
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public DescriptorProtos.FileDescriptorProto.Builder addProtoFileBuilder(int index) {
/* 2791 */         return (DescriptorProtos.FileDescriptorProto.Builder)getProtoFileFieldBuilder().addBuilder(index, 
/* 2792 */             (AbstractMessage)DescriptorProtos.FileDescriptorProto.getDefaultInstance());
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public List<DescriptorProtos.FileDescriptorProto.Builder> getProtoFileBuilderList() {
/* 2814 */         return getProtoFileFieldBuilder().getBuilderList();
/*      */       }
/*      */ 
/*      */       
/*      */       private RepeatedFieldBuilderV3<DescriptorProtos.FileDescriptorProto, DescriptorProtos.FileDescriptorProto.Builder, DescriptorProtos.FileDescriptorProtoOrBuilder> getProtoFileFieldBuilder() {
/* 2819 */         if (this.protoFileBuilder_ == null) {
/* 2820 */           this
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 2825 */             .protoFileBuilder_ = new RepeatedFieldBuilderV3(this.protoFile_, ((this.bitField0_ & 0x4) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
/* 2826 */           this.protoFile_ = null;
/*      */         } 
/* 2828 */         return this.protoFileBuilder_;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public boolean hasCompilerVersion() {
/* 2843 */         return ((this.bitField0_ & 0x8) != 0);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Version getCompilerVersion() {
/* 2854 */         if (this.compilerVersionBuilder_ == null) {
/* 2855 */           return (this.compilerVersion_ == null) ? Version.getDefaultInstance() : this.compilerVersion_;
/*      */         }
/* 2857 */         return (Version)this.compilerVersionBuilder_.getMessage();
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Builder setCompilerVersion(Version value) {
/* 2868 */         if (this.compilerVersionBuilder_ == null) {
/* 2869 */           if (value == null) {
/* 2870 */             throw new NullPointerException();
/*      */           }
/* 2872 */           this.compilerVersion_ = value;
/* 2873 */           onChanged();
/*      */         } else {
/* 2875 */           this.compilerVersionBuilder_.setMessage((AbstractMessage)value);
/*      */         } 
/* 2877 */         this.bitField0_ |= 0x8;
/* 2878 */         return this;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Builder setCompilerVersion(Version.Builder builderForValue) {
/* 2889 */         if (this.compilerVersionBuilder_ == null) {
/* 2890 */           this.compilerVersion_ = builderForValue.build();
/* 2891 */           onChanged();
/*      */         } else {
/* 2893 */           this.compilerVersionBuilder_.setMessage((AbstractMessage)builderForValue.build());
/*      */         } 
/* 2895 */         this.bitField0_ |= 0x8;
/* 2896 */         return this;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Builder mergeCompilerVersion(Version value) {
/* 2906 */         if (this.compilerVersionBuilder_ == null) {
/* 2907 */           if ((this.bitField0_ & 0x8) != 0 && this.compilerVersion_ != null && this.compilerVersion_ != 
/*      */             
/* 2909 */             Version.getDefaultInstance()) {
/* 2910 */             this
/* 2911 */               .compilerVersion_ = Version.newBuilder(this.compilerVersion_).mergeFrom(value).buildPartial();
/*      */           } else {
/* 2913 */             this.compilerVersion_ = value;
/*      */           } 
/* 2915 */           onChanged();
/*      */         } else {
/* 2917 */           this.compilerVersionBuilder_.mergeFrom((AbstractMessage)value);
/*      */         } 
/* 2919 */         this.bitField0_ |= 0x8;
/* 2920 */         return this;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Builder clearCompilerVersion() {
/* 2930 */         if (this.compilerVersionBuilder_ == null) {
/* 2931 */           this.compilerVersion_ = null;
/* 2932 */           onChanged();
/*      */         } else {
/* 2934 */           this.compilerVersionBuilder_.clear();
/*      */         } 
/* 2936 */         this.bitField0_ &= 0xFFFFFFF7;
/* 2937 */         return this;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Version.Builder getCompilerVersionBuilder() {
/* 2947 */         this.bitField0_ |= 0x8;
/* 2948 */         onChanged();
/* 2949 */         return (Version.Builder)getCompilerVersionFieldBuilder().getBuilder();
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public VersionOrBuilder getCompilerVersionOrBuilder() {
/* 2959 */         if (this.compilerVersionBuilder_ != null) {
/* 2960 */           return (VersionOrBuilder)this.compilerVersionBuilder_.getMessageOrBuilder();
/*      */         }
/* 2962 */         return (this.compilerVersion_ == null) ? 
/* 2963 */           Version.getDefaultInstance() : this.compilerVersion_;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       private SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> getCompilerVersionFieldBuilder() {
/* 2976 */         if (this.compilerVersionBuilder_ == null) {
/* 2977 */           this
/*      */ 
/*      */ 
/*      */             
/* 2981 */             .compilerVersionBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getCompilerVersion(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
/* 2982 */           this.compilerVersion_ = null;
/*      */         } 
/* 2984 */         return this.compilerVersionBuilder_;
/*      */       }
/*      */ 
/*      */       
/*      */       public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
/* 2989 */         return (Builder)super.setUnknownFields(unknownFields);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
/* 2995 */         return (Builder)super.mergeUnknownFields(unknownFields);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3005 */     private static final CodeGeneratorRequest DEFAULT_INSTANCE = new CodeGeneratorRequest();
/*      */ 
/*      */     
/*      */     public static CodeGeneratorRequest getDefaultInstance() {
/* 3009 */       return DEFAULT_INSTANCE;
/*      */     }
/*      */     
/*      */     @Deprecated
/* 3013 */     public static final Parser<CodeGeneratorRequest> PARSER = (Parser<CodeGeneratorRequest>)new AbstractParser<CodeGeneratorRequest>()
/*      */       {
/*      */ 
/*      */         
/*      */         public CodeGeneratorRequest parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException
/*      */         {
/* 3019 */           return new CodeGeneratorRequest(input, extensionRegistry);
/*      */         }
/*      */       };
/*      */     
/*      */     public static Parser<CodeGeneratorRequest> parser() {
/* 3024 */       return PARSER;
/*      */     }
/*      */ 
/*      */     
/*      */     public Parser<CodeGeneratorRequest> getParserForType() {
/* 3029 */       return PARSER;
/*      */     }
/*      */ 
/*      */     
/*      */     public CodeGeneratorRequest getDefaultInstanceForType() {
/* 3034 */       return DEFAULT_INSTANCE;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static interface CodeGeneratorResponseOrBuilder
/*      */     extends MessageOrBuilder
/*      */   {
/*      */     boolean hasError();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     String getError();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     ByteString getErrorBytes();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     boolean hasSupportedFeatures();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     long getSupportedFeatures();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     List<CodeGeneratorResponse.File> getFileList();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     CodeGeneratorResponse.File getFile(int param1Int);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     int getFileCount();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     List<? extends CodeGeneratorResponse.FileOrBuilder> getFileOrBuilderList();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     CodeGeneratorResponse.FileOrBuilder getFileOrBuilder(int param1Int);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final class CodeGeneratorResponse
/*      */     extends GeneratedMessageV3
/*      */     implements CodeGeneratorResponseOrBuilder
/*      */   {
/*      */     private static final long serialVersionUID = 0L;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private int bitField0_;
/*      */ 
/*      */ 
/*      */     
/*      */     public static final int ERROR_FIELD_NUMBER = 1;
/*      */ 
/*      */ 
/*      */     
/*      */     private volatile Object error_;
/*      */ 
/*      */ 
/*      */     
/*      */     public static final int SUPPORTED_FEATURES_FIELD_NUMBER = 2;
/*      */ 
/*      */ 
/*      */     
/*      */     private long supportedFeatures_;
/*      */ 
/*      */ 
/*      */     
/*      */     public static final int FILE_FIELD_NUMBER = 15;
/*      */ 
/*      */ 
/*      */     
/*      */     private List<File> file_;
/*      */ 
/*      */ 
/*      */     
/*      */     private byte memoizedIsInitialized;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private CodeGeneratorResponse(GeneratedMessageV3.Builder<?> builder) {
/* 3149 */       super(builder);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5402 */       this.memoizedIsInitialized = -1; } protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) { return new CodeGeneratorResponse(); } public final UnknownFieldSet getUnknownFields() { return this.unknownFields; } private CodeGeneratorResponse(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException { this(); if (extensionRegistry == null) throw new NullPointerException();  int mutable_bitField0_ = 0; UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(); try { boolean done = false; while (!done) { ByteString bs; int tag = input.readTag(); switch (tag) { case 0: done = true; continue;case 10: bs = input.readBytes(); this.bitField0_ |= 0x1; this.error_ = bs; continue;case 16: this.bitField0_ |= 0x2; this.supportedFeatures_ = input.readUInt64(); continue;case 122: if ((mutable_bitField0_ & 0x4) == 0) { this.file_ = new ArrayList<>(); mutable_bitField0_ |= 0x4; }  this.file_.add(input.readMessage(File.PARSER, extensionRegistry)); continue; }  if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) done = true;  }  } catch (InvalidProtocolBufferException e) { throw e.setUnfinishedMessage(this); } catch (IOException e) { throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(this); } finally { if ((mutable_bitField0_ & 0x4) != 0) this.file_ = Collections.unmodifiableList(this.file_);  this.unknownFields = unknownFields.build(); makeExtensionsImmutable(); }  } public static final Descriptors.Descriptor getDescriptor() { return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_descriptor; } private CodeGeneratorResponse() { this.memoizedIsInitialized = -1; this.error_ = ""; this.file_ = Collections.emptyList(); } protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() { return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(CodeGeneratorResponse.class, Builder.class); } public enum Feature implements ProtocolMessageEnum {
/*      */       FEATURE_NONE(0), FEATURE_PROTO3_OPTIONAL(1); public static final int FEATURE_NONE_VALUE = 0; public static final int FEATURE_PROTO3_OPTIONAL_VALUE = 1; private static final Internal.EnumLiteMap<Feature> internalValueMap = new Internal.EnumLiteMap<Feature>() { public Feature findValueByNumber(int number) { return Feature.forNumber(number); } }; private static final Feature[] VALUES = values(); private final int value; public final int getNumber() { return this.value; } public static Feature forNumber(int value) { switch (value) { case 0: return FEATURE_NONE;case 1: return FEATURE_PROTO3_OPTIONAL; }  return null; } public static Internal.EnumLiteMap<Feature> internalGetValueMap() { return internalValueMap; } static {  } public final Descriptors.EnumValueDescriptor getValueDescriptor() { return getDescriptor().getValues().get(ordinal()); } public final Descriptors.EnumDescriptor getDescriptorForType() { return getDescriptor(); } public static final Descriptors.EnumDescriptor getDescriptor() { return CodeGeneratorResponse.getDescriptor().getEnumTypes().get(0); } Feature(int value) { this.value = value; } } public static final class File extends GeneratedMessageV3 implements FileOrBuilder {
/*      */       private static final long serialVersionUID = 0L; private int bitField0_; public static final int NAME_FIELD_NUMBER = 1; private volatile Object name_; public static final int INSERTION_POINT_FIELD_NUMBER = 2; private volatile Object insertionPoint_; public static final int CONTENT_FIELD_NUMBER = 15; private volatile Object content_; public static final int GENERATED_CODE_INFO_FIELD_NUMBER = 16; private DescriptorProtos.GeneratedCodeInfo generatedCodeInfo_; private byte memoizedIsInitialized; private File(GeneratedMessageV3.Builder<?> builder) { super(builder); this.memoizedIsInitialized = -1; } private File() { this.memoizedIsInitialized = -1; this.name_ = ""; this.insertionPoint_ = ""; this.content_ = ""; } protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) { return new File(); } public final UnknownFieldSet getUnknownFields() { return this.unknownFields; } private File(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException { this(); if (extensionRegistry == null) throw new NullPointerException();  int mutable_bitField0_ = 0; UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(); try { boolean done = false; while (!done) { ByteString bs; DescriptorProtos.GeneratedCodeInfo.Builder subBuilder; int tag = input.readTag(); switch (tag) { case 0: done = true; continue;case 10: bs = input.readBytes(); this.bitField0_ |= 0x1; this.name_ = bs; continue;case 18: bs = input.readBytes(); this.bitField0_ |= 0x2; this.insertionPoint_ = bs; continue;case 122: bs = input.readBytes(); this.bitField0_ |= 0x4; this.content_ = bs; continue;case 130: subBuilder = null; if ((this.bitField0_ & 0x8) != 0) subBuilder = this.generatedCodeInfo_.toBuilder();  this.generatedCodeInfo_ = (DescriptorProtos.GeneratedCodeInfo)input.readMessage(DescriptorProtos.GeneratedCodeInfo.PARSER, extensionRegistry); if (subBuilder != null) { subBuilder.mergeFrom(this.generatedCodeInfo_); this.generatedCodeInfo_ = subBuilder.buildPartial(); }  this.bitField0_ |= 0x8; continue; }  if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) done = true;  }  } catch (InvalidProtocolBufferException e) { throw e.setUnfinishedMessage(this); } catch (IOException e) { throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(this); } finally { this.unknownFields = unknownFields.build(); makeExtensionsImmutable(); }  } public static final Descriptors.Descriptor getDescriptor() { return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_descriptor; } protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() { return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_fieldAccessorTable.ensureFieldAccessorsInitialized(File.class, Builder.class); } public boolean hasName() { return ((this.bitField0_ & 0x1) != 0); } public String getName() { Object ref = this.name_; if (ref instanceof String) return (String)ref;  ByteString bs = (ByteString)ref; String s = bs.toStringUtf8(); if (bs.isValidUtf8()) this.name_ = s;  return s; } public ByteString getNameBytes() { Object ref = this.name_; if (ref instanceof String) { ByteString b = ByteString.copyFromUtf8((String)ref); this.name_ = b; return b; }  return (ByteString)ref; } public boolean hasInsertionPoint() { return ((this.bitField0_ & 0x2) != 0); } public String getInsertionPoint() { Object ref = this.insertionPoint_; if (ref instanceof String) return (String)ref;  ByteString bs = (ByteString)ref; String s = bs.toStringUtf8(); if (bs.isValidUtf8()) this.insertionPoint_ = s;  return s; } public ByteString getInsertionPointBytes() { Object ref = this.insertionPoint_; if (ref instanceof String) { ByteString b = ByteString.copyFromUtf8((String)ref); this.insertionPoint_ = b; return b; }  return (ByteString)ref; } public boolean hasContent() { return ((this.bitField0_ & 0x4) != 0); } public String getContent() { Object ref = this.content_; if (ref instanceof String) return (String)ref;  ByteString bs = (ByteString)ref; String s = bs.toStringUtf8(); if (bs.isValidUtf8()) this.content_ = s;  return s; } public ByteString getContentBytes() { Object ref = this.content_; if (ref instanceof String) { ByteString b = ByteString.copyFromUtf8((String)ref); this.content_ = b; return b; }  return (ByteString)ref; } public boolean hasGeneratedCodeInfo() { return ((this.bitField0_ & 0x8) != 0); } public DescriptorProtos.GeneratedCodeInfo getGeneratedCodeInfo() { return (this.generatedCodeInfo_ == null) ? DescriptorProtos.GeneratedCodeInfo.getDefaultInstance() : this.generatedCodeInfo_; } public DescriptorProtos.GeneratedCodeInfoOrBuilder getGeneratedCodeInfoOrBuilder() { return (this.generatedCodeInfo_ == null) ? (DescriptorProtos.GeneratedCodeInfoOrBuilder)DescriptorProtos.GeneratedCodeInfo.getDefaultInstance() : (DescriptorProtos.GeneratedCodeInfoOrBuilder)this.generatedCodeInfo_; } public final boolean isInitialized() { byte isInitialized = this.memoizedIsInitialized; if (isInitialized == 1) return true;  if (isInitialized == 0) return false;  this.memoizedIsInitialized = 1; return true; } public void writeTo(CodedOutputStream output) throws IOException { if ((this.bitField0_ & 0x1) != 0) GeneratedMessageV3.writeString(output, 1, this.name_);  if ((this.bitField0_ & 0x2) != 0) GeneratedMessageV3.writeString(output, 2, this.insertionPoint_);  if ((this.bitField0_ & 0x4) != 0) GeneratedMessageV3.writeString(output, 15, this.content_);  if ((this.bitField0_ & 0x8) != 0) output.writeMessage(16, (MessageLite)getGeneratedCodeInfo());  this.unknownFields.writeTo(output); } public int getSerializedSize() { int size = this.memoizedSize; if (size != -1) return size;  size = 0; if ((this.bitField0_ & 0x1) != 0) size += GeneratedMessageV3.computeStringSize(1, this.name_);  if ((this.bitField0_ & 0x2) != 0) size += GeneratedMessageV3.computeStringSize(2, this.insertionPoint_);  if ((this.bitField0_ & 0x4) != 0) size += GeneratedMessageV3.computeStringSize(15, this.content_);  if ((this.bitField0_ & 0x8) != 0) size += CodedOutputStream.computeMessageSize(16, (MessageLite)getGeneratedCodeInfo());  size += this.unknownFields.getSerializedSize(); this.memoizedSize = size; return size; } public boolean equals(Object obj) { if (obj == this) return true;  if (!(obj instanceof File)) return super.equals(obj);  File other = (File)obj; if (hasName() != other.hasName()) return false;  if (hasName() && !getName().equals(other.getName())) return false;  if (hasInsertionPoint() != other.hasInsertionPoint()) return false;  if (hasInsertionPoint() && !getInsertionPoint().equals(other.getInsertionPoint())) return false;  if (hasContent() != other.hasContent()) return false;  if (hasContent() && !getContent().equals(other.getContent())) return false;  if (hasGeneratedCodeInfo() != other.hasGeneratedCodeInfo()) return false;  if (hasGeneratedCodeInfo() && !getGeneratedCodeInfo().equals(other.getGeneratedCodeInfo())) return false;  if (!this.unknownFields.equals(other.unknownFields)) return false;  return true; } public int hashCode() { if (this.memoizedHashCode != 0) return this.memoizedHashCode;  int hash = 41; hash = 19 * hash + getDescriptor().hashCode(); if (hasName()) { hash = 37 * hash + 1; hash = 53 * hash + getName().hashCode(); }  if (hasInsertionPoint()) { hash = 37 * hash + 2; hash = 53 * hash + getInsertionPoint().hashCode(); }  if (hasContent()) { hash = 37 * hash + 15; hash = 53 * hash + getContent().hashCode(); }  if (hasGeneratedCodeInfo()) { hash = 37 * hash + 16; hash = 53 * hash + getGeneratedCodeInfo().hashCode(); }  hash = 29 * hash + this.unknownFields.hashCode(); this.memoizedHashCode = hash; return hash; } public static File parseFrom(ByteBuffer data) throws InvalidProtocolBufferException { return (File)PARSER.parseFrom(data); } public static File parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException { return (File)PARSER.parseFrom(data, extensionRegistry); } public static File parseFrom(ByteString data) throws InvalidProtocolBufferException { return (File)PARSER.parseFrom(data); } public static File parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException { return (File)PARSER.parseFrom(data, extensionRegistry); } public static File parseFrom(byte[] data) throws InvalidProtocolBufferException { return (File)PARSER.parseFrom(data); } public static File parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException { return (File)PARSER.parseFrom(data, extensionRegistry); } public static File parseFrom(InputStream input) throws IOException { return (File)GeneratedMessageV3.parseWithIOException(PARSER, input); } public static File parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException { return (File)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry); } public static File parseDelimitedFrom(InputStream input) throws IOException { return (File)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input); } public static File parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException { return (File)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry); } public static File parseFrom(CodedInputStream input) throws IOException { return (File)GeneratedMessageV3.parseWithIOException(PARSER, input); } public static File parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException { return (File)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry); } public Builder newBuilderForType() { return newBuilder(); } public static Builder newBuilder() { return DEFAULT_INSTANCE.toBuilder(); } public static Builder newBuilder(File prototype) { return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype); } public Builder toBuilder() { return (this == DEFAULT_INSTANCE) ? new Builder() : (new Builder()).mergeFrom(this); } protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) { Builder builder = new Builder(parent); return builder; } public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements FileOrBuilder {
/* 5405 */         private int bitField0_; private Object name_; private Object insertionPoint_; private Object content_; private DescriptorProtos.GeneratedCodeInfo generatedCodeInfo_; private SingleFieldBuilderV3<DescriptorProtos.GeneratedCodeInfo, DescriptorProtos.GeneratedCodeInfo.Builder, DescriptorProtos.GeneratedCodeInfoOrBuilder> generatedCodeInfoBuilder_; public static final Descriptors.Descriptor getDescriptor() { return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_descriptor; } protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() { return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_fieldAccessorTable.ensureFieldAccessorsInitialized(File.class, Builder.class); } private Builder() { this.name_ = ""; this.insertionPoint_ = ""; this.content_ = ""; maybeForceBuilderInitialization(); } private Builder(GeneratedMessageV3.BuilderParent parent) { super(parent); this.name_ = ""; this.insertionPoint_ = ""; this.content_ = ""; maybeForceBuilderInitialization(); } private void maybeForceBuilderInitialization() { if (File.alwaysUseFieldBuilders) getGeneratedCodeInfoFieldBuilder();  } public Builder clear() { super.clear(); this.name_ = ""; this.bitField0_ &= 0xFFFFFFFE; this.insertionPoint_ = ""; this.bitField0_ &= 0xFFFFFFFD; this.content_ = ""; this.bitField0_ &= 0xFFFFFFFB; if (this.generatedCodeInfoBuilder_ == null) { this.generatedCodeInfo_ = null; } else { this.generatedCodeInfoBuilder_.clear(); }  this.bitField0_ &= 0xFFFFFFF7; return this; } public Descriptors.Descriptor getDescriptorForType() { return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_descriptor; } public File getDefaultInstanceForType() { return File.getDefaultInstance(); } public File build() { File result = buildPartial(); if (!result.isInitialized()) throw newUninitializedMessageException(result);  return result; } public File buildPartial() { File result = new File(this); int from_bitField0_ = this.bitField0_; int to_bitField0_ = 0; if ((from_bitField0_ & 0x1) != 0) to_bitField0_ |= 0x1;  result.name_ = this.name_; if ((from_bitField0_ & 0x2) != 0) to_bitField0_ |= 0x2;  result.insertionPoint_ = this.insertionPoint_; if ((from_bitField0_ & 0x4) != 0) to_bitField0_ |= 0x4;  result.content_ = this.content_; if ((from_bitField0_ & 0x8) != 0) { if (this.generatedCodeInfoBuilder_ == null) { result.generatedCodeInfo_ = this.generatedCodeInfo_; } else { result.generatedCodeInfo_ = (DescriptorProtos.GeneratedCodeInfo)this.generatedCodeInfoBuilder_.build(); }  to_bitField0_ |= 0x8; }  result.bitField0_ = to_bitField0_; onBuilt(); return result; } public Builder clone() { return (Builder)super.clone(); } public Builder setField(Descriptors.FieldDescriptor field, Object value) { return (Builder)super.setField(field, value); } public Builder clearField(Descriptors.FieldDescriptor field) { return (Builder)super.clearField(field); } public Builder clearOneof(Descriptors.OneofDescriptor oneof) { return (Builder)super.clearOneof(oneof); } public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) { return (Builder)super.setRepeatedField(field, index, value); } public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) { return (Builder)super.addRepeatedField(field, value); } public Builder mergeFrom(Message other) { if (other instanceof File) return mergeFrom((File)other);  super.mergeFrom(other); return this; } public Builder mergeFrom(File other) { if (other == File.getDefaultInstance()) return this;  if (other.hasName()) { this.bitField0_ |= 0x1; this.name_ = other.name_; onChanged(); }  if (other.hasInsertionPoint()) { this.bitField0_ |= 0x2; this.insertionPoint_ = other.insertionPoint_; onChanged(); }  if (other.hasContent()) { this.bitField0_ |= 0x4; this.content_ = other.content_; onChanged(); }  if (other.hasGeneratedCodeInfo()) mergeGeneratedCodeInfo(other.getGeneratedCodeInfo());  mergeUnknownFields(other.unknownFields); onChanged(); return this; } public final boolean isInitialized() { return true; } public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException { File parsedMessage = null; try { parsedMessage = (File) File.PARSER.parsePartialFrom(input, extensionRegistry); } catch (InvalidProtocolBufferException e) { parsedMessage = (File)e.getUnfinishedMessage(); throw e.unwrapIOException(); } finally { if (parsedMessage != null) mergeFrom(parsedMessage);  }  return this; } public boolean hasName() { return ((this.bitField0_ & 0x1) != 0); } public String getName() { Object ref = this.name_; if (!(ref instanceof String)) { ByteString bs = (ByteString)ref; String s = bs.toStringUtf8(); if (bs.isValidUtf8()) this.name_ = s;  return s; }  return (String)ref; } public ByteString getNameBytes() { Object ref = this.name_; if (ref instanceof String) { ByteString b = ByteString.copyFromUtf8((String)ref); this.name_ = b; return b; }  return (ByteString)ref; } public Builder setName(String value) { if (value == null) throw new NullPointerException();  this.bitField0_ |= 0x1; this.name_ = value; onChanged(); return this; } public Builder clearName() { this.bitField0_ &= 0xFFFFFFFE; this.name_ = File.getDefaultInstance().getName(); onChanged(); return this; } public Builder setNameBytes(ByteString value) { if (value == null) throw new NullPointerException();  this.bitField0_ |= 0x1; this.name_ = value; onChanged(); return this; } public boolean hasInsertionPoint() { return ((this.bitField0_ & 0x2) != 0); } public String getInsertionPoint() { Object ref = this.insertionPoint_; if (!(ref instanceof String)) { ByteString bs = (ByteString)ref; String s = bs.toStringUtf8(); if (bs.isValidUtf8()) this.insertionPoint_ = s;  return s; }  return (String)ref; } public ByteString getInsertionPointBytes() { Object ref = this.insertionPoint_; if (ref instanceof String) { ByteString b = ByteString.copyFromUtf8((String)ref); this.insertionPoint_ = b; return b; }  return (ByteString)ref; } public Builder setInsertionPoint(String value) { if (value == null) throw new NullPointerException();  this.bitField0_ |= 0x2; this.insertionPoint_ = value; onChanged(); return this; } public Builder clearInsertionPoint() { this.bitField0_ &= 0xFFFFFFFD; this.insertionPoint_ = File.getDefaultInstance().getInsertionPoint(); onChanged(); return this; } public Builder setInsertionPointBytes(ByteString value) { if (value == null) throw new NullPointerException();  this.bitField0_ |= 0x2; this.insertionPoint_ = value; onChanged(); return this; } public boolean hasContent() { return ((this.bitField0_ & 0x4) != 0); } public String getContent() { Object ref = this.content_; if (!(ref instanceof String)) { ByteString bs = (ByteString)ref; String s = bs.toStringUtf8(); if (bs.isValidUtf8()) this.content_ = s;  return s; }  return (String)ref; } public ByteString getContentBytes() { Object ref = this.content_; if (ref instanceof String) { ByteString b = ByteString.copyFromUtf8((String)ref); this.content_ = b; return b; }  return (ByteString)ref; } public Builder setContent(String value) { if (value == null) throw new NullPointerException();  this.bitField0_ |= 0x4; this.content_ = value; onChanged(); return this; } public Builder clearContent() { this.bitField0_ &= 0xFFFFFFFB; this.content_ = File.getDefaultInstance().getContent(); onChanged(); return this; } public Builder setContentBytes(ByteString value) { if (value == null) throw new NullPointerException();  this.bitField0_ |= 0x4; this.content_ = value; onChanged(); return this; } public boolean hasGeneratedCodeInfo() { return ((this.bitField0_ & 0x8) != 0); } public DescriptorProtos.GeneratedCodeInfo getGeneratedCodeInfo() { if (this.generatedCodeInfoBuilder_ == null) return (this.generatedCodeInfo_ == null) ? DescriptorProtos.GeneratedCodeInfo.getDefaultInstance() : this.generatedCodeInfo_;  return (DescriptorProtos.GeneratedCodeInfo)this.generatedCodeInfoBuilder_.getMessage(); } public Builder setGeneratedCodeInfo(DescriptorProtos.GeneratedCodeInfo value) { if (this.generatedCodeInfoBuilder_ == null) { if (value == null) throw new NullPointerException();  this.generatedCodeInfo_ = value; onChanged(); } else { this.generatedCodeInfoBuilder_.setMessage((AbstractMessage)value); }  this.bitField0_ |= 0x8; return this; } public Builder setGeneratedCodeInfo(DescriptorProtos.GeneratedCodeInfo.Builder builderForValue) { if (this.generatedCodeInfoBuilder_ == null) { this.generatedCodeInfo_ = builderForValue.build(); onChanged(); } else { this.generatedCodeInfoBuilder_.setMessage((AbstractMessage)builderForValue.build()); }  this.bitField0_ |= 0x8; return this; } public Builder mergeGeneratedCodeInfo(DescriptorProtos.GeneratedCodeInfo value) { if (this.generatedCodeInfoBuilder_ == null) { if ((this.bitField0_ & 0x8) != 0 && this.generatedCodeInfo_ != null && this.generatedCodeInfo_ != DescriptorProtos.GeneratedCodeInfo.getDefaultInstance()) { this.generatedCodeInfo_ = DescriptorProtos.GeneratedCodeInfo.newBuilder(this.generatedCodeInfo_).mergeFrom(value).buildPartial(); } else { this.generatedCodeInfo_ = value; }  onChanged(); } else { this.generatedCodeInfoBuilder_.mergeFrom((AbstractMessage)value); }  this.bitField0_ |= 0x8; return this; } public Builder clearGeneratedCodeInfo() { if (this.generatedCodeInfoBuilder_ == null) { this.generatedCodeInfo_ = null; onChanged(); } else { this.generatedCodeInfoBuilder_.clear(); }  this.bitField0_ &= 0xFFFFFFF7; return this; } public DescriptorProtos.GeneratedCodeInfo.Builder getGeneratedCodeInfoBuilder() { this.bitField0_ |= 0x8; onChanged(); return (DescriptorProtos.GeneratedCodeInfo.Builder)getGeneratedCodeInfoFieldBuilder().getBuilder(); } public DescriptorProtos.GeneratedCodeInfoOrBuilder getGeneratedCodeInfoOrBuilder() { if (this.generatedCodeInfoBuilder_ != null) return (DescriptorProtos.GeneratedCodeInfoOrBuilder)this.generatedCodeInfoBuilder_.getMessageOrBuilder();  return (this.generatedCodeInfo_ == null) ? (DescriptorProtos.GeneratedCodeInfoOrBuilder)DescriptorProtos.GeneratedCodeInfo.getDefaultInstance() : (DescriptorProtos.GeneratedCodeInfoOrBuilder)this.generatedCodeInfo_; } private SingleFieldBuilderV3<DescriptorProtos.GeneratedCodeInfo, DescriptorProtos.GeneratedCodeInfo.Builder, DescriptorProtos.GeneratedCodeInfoOrBuilder> getGeneratedCodeInfoFieldBuilder() { if (this.generatedCodeInfoBuilder_ == null) { this.generatedCodeInfoBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getGeneratedCodeInfo(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean()); this.generatedCodeInfo_ = null; }  return this.generatedCodeInfoBuilder_; } public final Builder setUnknownFields(UnknownFieldSet unknownFields) { return (Builder)super.setUnknownFields(unknownFields); } public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) { return (Builder)super.mergeUnknownFields(unknownFields); } } private static final File DEFAULT_INSTANCE = new File(); public static File getDefaultInstance() { return DEFAULT_INSTANCE; } @Deprecated public static final Parser<File> PARSER = (Parser<File>)new AbstractParser<File>() { public File parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException { return new File(input, extensionRegistry); } }; public static Parser<File> parser() { return PARSER; } public Parser<File> getParserForType() { return PARSER; } public File getDefaultInstanceForType() { return DEFAULT_INSTANCE; } } public boolean hasError() { return ((this.bitField0_ & 0x1) != 0); } public final boolean isInitialized() { byte isInitialized = this.memoizedIsInitialized;
/* 5406 */       if (isInitialized == 1) return true; 
/* 5407 */       if (isInitialized == 0) return false;
/*      */       
/* 5409 */       this.memoizedIsInitialized = 1;
/* 5410 */       return true; } public String getError() { Object ref = this.error_; if (ref instanceof String) return (String)ref;  ByteString bs = (ByteString)ref; String s = bs.toStringUtf8(); if (bs.isValidUtf8()) this.error_ = s;  return s; } public ByteString getErrorBytes() { Object ref = this.error_; if (ref instanceof String) { ByteString b = ByteString.copyFromUtf8((String)ref); this.error_ = b; return b; }  return (ByteString)ref; } public boolean hasSupportedFeatures() { return ((this.bitField0_ & 0x2) != 0); } public long getSupportedFeatures() { return this.supportedFeatures_; }
/*      */     public List<File> getFileList() { return this.file_; }
/*      */     public List<? extends FileOrBuilder> getFileOrBuilderList() { return (List)this.file_; }
/*      */     public int getFileCount() { return this.file_.size(); }
/*      */     public File getFile(int index) { return this.file_.get(index); }
/*      */     public FileOrBuilder getFileOrBuilder(int index) { return this.file_.get(index); }
/* 5416 */     public void writeTo(CodedOutputStream output) throws IOException { if ((this.bitField0_ & 0x1) != 0) {
/* 5417 */         GeneratedMessageV3.writeString(output, 1, this.error_);
/*      */       }
/* 5419 */       if ((this.bitField0_ & 0x2) != 0) {
/* 5420 */         output.writeUInt64(2, this.supportedFeatures_);
/*      */       }
/* 5422 */       for (int i = 0; i < this.file_.size(); i++) {
/* 5423 */         output.writeMessage(15, (MessageLite)this.file_.get(i));
/*      */       }
/* 5425 */       this.unknownFields.writeTo(output); }
/*      */ 
/*      */ 
/*      */     
/*      */     public int getSerializedSize() {
/* 5430 */       int size = this.memoizedSize;
/* 5431 */       if (size != -1) return size;
/*      */       
/* 5433 */       size = 0;
/* 5434 */       if ((this.bitField0_ & 0x1) != 0) {
/* 5435 */         size += GeneratedMessageV3.computeStringSize(1, this.error_);
/*      */       }
/* 5437 */       if ((this.bitField0_ & 0x2) != 0) {
/* 5438 */         size += 
/* 5439 */           CodedOutputStream.computeUInt64Size(2, this.supportedFeatures_);
/*      */       }
/* 5441 */       for (int i = 0; i < this.file_.size(); i++) {
/* 5442 */         size += 
/* 5443 */           CodedOutputStream.computeMessageSize(15, (MessageLite)this.file_.get(i));
/*      */       }
/* 5445 */       size += this.unknownFields.getSerializedSize();
/* 5446 */       this.memoizedSize = size;
/* 5447 */       return size;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean equals(Object obj) {
/* 5452 */       if (obj == this) {
/* 5453 */         return true;
/*      */       }
/* 5455 */       if (!(obj instanceof CodeGeneratorResponse)) {
/* 5456 */         return super.equals(obj);
/*      */       }
/* 5458 */       CodeGeneratorResponse other = (CodeGeneratorResponse)obj;
/*      */       
/* 5460 */       if (hasError() != other.hasError()) return false; 
/* 5461 */       if (hasError() && 
/*      */         
/* 5463 */         !getError().equals(other.getError())) return false;
/*      */       
/* 5465 */       if (hasSupportedFeatures() != other.hasSupportedFeatures()) return false; 
/* 5466 */       if (hasSupportedFeatures() && 
/* 5467 */         getSupportedFeatures() != other
/* 5468 */         .getSupportedFeatures()) return false;
/*      */ 
/*      */       
/* 5471 */       if (!getFileList().equals(other.getFileList())) return false; 
/* 5472 */       if (!this.unknownFields.equals(other.unknownFields)) return false; 
/* 5473 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public int hashCode() {
/* 5478 */       if (this.memoizedHashCode != 0) {
/* 5479 */         return this.memoizedHashCode;
/*      */       }
/* 5481 */       int hash = 41;
/* 5482 */       hash = 19 * hash + getDescriptor().hashCode();
/* 5483 */       if (hasError()) {
/* 5484 */         hash = 37 * hash + 1;
/* 5485 */         hash = 53 * hash + getError().hashCode();
/*      */       } 
/* 5487 */       if (hasSupportedFeatures()) {
/* 5488 */         hash = 37 * hash + 2;
/* 5489 */         hash = 53 * hash + Internal.hashLong(
/* 5490 */             getSupportedFeatures());
/*      */       } 
/* 5492 */       if (getFileCount() > 0) {
/* 5493 */         hash = 37 * hash + 15;
/* 5494 */         hash = 53 * hash + getFileList().hashCode();
/*      */       } 
/* 5496 */       hash = 29 * hash + this.unknownFields.hashCode();
/* 5497 */       this.memoizedHashCode = hash;
/* 5498 */       return hash;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static CodeGeneratorResponse parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
/* 5504 */       return (CodeGeneratorResponse)PARSER.parseFrom(data);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static CodeGeneratorResponse parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 5510 */       return (CodeGeneratorResponse)PARSER.parseFrom(data, extensionRegistry);
/*      */     }
/*      */ 
/*      */     
/*      */     public static CodeGeneratorResponse parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 5515 */       return (CodeGeneratorResponse)PARSER.parseFrom(data);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static CodeGeneratorResponse parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 5521 */       return (CodeGeneratorResponse)PARSER.parseFrom(data, extensionRegistry);
/*      */     }
/*      */     
/*      */     public static CodeGeneratorResponse parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 5525 */       return (CodeGeneratorResponse)PARSER.parseFrom(data);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static CodeGeneratorResponse parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 5531 */       return (CodeGeneratorResponse)PARSER.parseFrom(data, extensionRegistry);
/*      */     }
/*      */     
/*      */     public static CodeGeneratorResponse parseFrom(InputStream input) throws IOException {
/* 5535 */       return 
/* 5536 */         (CodeGeneratorResponse)GeneratedMessageV3.parseWithIOException(PARSER, input);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static CodeGeneratorResponse parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 5542 */       return 
/* 5543 */         (CodeGeneratorResponse)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
/*      */     }
/*      */     
/*      */     public static CodeGeneratorResponse parseDelimitedFrom(InputStream input) throws IOException {
/* 5547 */       return 
/* 5548 */         (CodeGeneratorResponse)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static CodeGeneratorResponse parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 5554 */       return 
/* 5555 */         (CodeGeneratorResponse)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
/*      */     }
/*      */ 
/*      */     
/*      */     public static CodeGeneratorResponse parseFrom(CodedInputStream input) throws IOException {
/* 5560 */       return 
/* 5561 */         (CodeGeneratorResponse)GeneratedMessageV3.parseWithIOException(PARSER, input);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static CodeGeneratorResponse parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 5567 */       return 
/* 5568 */         (CodeGeneratorResponse)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
/*      */     }
/*      */     
/*      */     public Builder newBuilderForType() {
/* 5572 */       return newBuilder();
/*      */     } public static Builder newBuilder() {
/* 5574 */       return DEFAULT_INSTANCE.toBuilder();
/*      */     }
/*      */     public static Builder newBuilder(CodeGeneratorResponse prototype) {
/* 5577 */       return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
/*      */     }
/*      */     
/*      */     public Builder toBuilder() {
/* 5581 */       return (this == DEFAULT_INSTANCE) ? new Builder() : (new Builder())
/* 5582 */         .mergeFrom(this);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
/* 5588 */       Builder builder = new Builder(parent);
/* 5589 */       return builder;
/*      */     }
/*      */ 
/*      */     
/*      */     public static final class Builder
/*      */       extends GeneratedMessageV3.Builder<Builder>
/*      */       implements CodeGeneratorResponseOrBuilder
/*      */     {
/*      */       private int bitField0_;
/*      */       private Object error_;
/*      */       private long supportedFeatures_;
/*      */       private List<File> file_;
/*      */       private RepeatedFieldBuilderV3<File, File.Builder, FileOrBuilder> fileBuilder_;
/*      */       
/*      */       public static final Descriptors.Descriptor getDescriptor() {
/* 5604 */         return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_descriptor;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
/* 5610 */         return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_fieldAccessorTable
/* 5611 */           .ensureFieldAccessorsInitialized(CodeGeneratorResponse.class, Builder.class);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       private Builder()
/*      */       {
/* 5802 */         this.error_ = "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 6005 */         this
/* 6006 */           .file_ = Collections.emptyList(); maybeForceBuilderInitialization(); } private Builder(GeneratedMessageV3.BuilderParent parent) { super(parent); this.error_ = ""; this.file_ = Collections.emptyList(); maybeForceBuilderInitialization(); } private void maybeForceBuilderInitialization() { if (CodeGeneratorResponse.alwaysUseFieldBuilders) getFileFieldBuilder();  } public Builder clear() { super.clear(); this.error_ = ""; this.bitField0_ &= 0xFFFFFFFE; this.supportedFeatures_ = 0L; this.bitField0_ &= 0xFFFFFFFD; if (this.fileBuilder_ == null) { this.file_ = Collections.emptyList(); this.bitField0_ &= 0xFFFFFFFB; } else { this.fileBuilder_.clear(); }  return this; } public Descriptors.Descriptor getDescriptorForType() { return PluginProtos.internal_static_google_protobuf_compiler_CodeGeneratorResponse_descriptor; } public CodeGeneratorResponse getDefaultInstanceForType() { return CodeGeneratorResponse.getDefaultInstance(); } public CodeGeneratorResponse build() { CodeGeneratorResponse result = buildPartial(); if (!result.isInitialized()) throw newUninitializedMessageException(result);  return result; } public CodeGeneratorResponse buildPartial() { CodeGeneratorResponse result = new CodeGeneratorResponse(this); int from_bitField0_ = this.bitField0_; int to_bitField0_ = 0; if ((from_bitField0_ & 0x1) != 0) to_bitField0_ |= 0x1;  result.error_ = this.error_; if ((from_bitField0_ & 0x2) != 0) { result.supportedFeatures_ = this.supportedFeatures_; to_bitField0_ |= 0x2; }  if (this.fileBuilder_ == null) { if ((this.bitField0_ & 0x4) != 0) { this.file_ = Collections.unmodifiableList(this.file_); this.bitField0_ &= 0xFFFFFFFB; }  result.file_ = this.file_; } else { result.file_ = this.fileBuilder_.build(); }  result.bitField0_ = to_bitField0_; onBuilt(); return result; } public Builder clone() { return (Builder)super.clone(); } public Builder setField(Descriptors.FieldDescriptor field, Object value) { return (Builder)super.setField(field, value); } public Builder clearField(Descriptors.FieldDescriptor field) { return (Builder)super.clearField(field); } public Builder clearOneof(Descriptors.OneofDescriptor oneof) { return (Builder)super.clearOneof(oneof); } public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) { return (Builder)super.setRepeatedField(field, index, value); } public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) { return (Builder)super.addRepeatedField(field, value); } public Builder mergeFrom(Message other) { if (other instanceof CodeGeneratorResponse) return mergeFrom((CodeGeneratorResponse)other);  super.mergeFrom(other); return this; } public Builder mergeFrom(CodeGeneratorResponse other) { if (other == CodeGeneratorResponse.getDefaultInstance()) return this;  if (other.hasError()) { this.bitField0_ |= 0x1; this.error_ = other.error_; onChanged(); }  if (other.hasSupportedFeatures()) setSupportedFeatures(other.getSupportedFeatures());  if (this.fileBuilder_ == null) { if (!other.file_.isEmpty()) { if (this.file_.isEmpty()) { this.file_ = other.file_; this.bitField0_ &= 0xFFFFFFFB; } else { ensureFileIsMutable(); this.file_.addAll(other.file_); }  onChanged(); }  } else if (!other.file_.isEmpty()) { if (this.fileBuilder_.isEmpty()) { this.fileBuilder_.dispose(); this.fileBuilder_ = null; this.file_ = other.file_; this.bitField0_ &= 0xFFFFFFFB; this.fileBuilder_ = CodeGeneratorResponse.alwaysUseFieldBuilders ? getFileFieldBuilder() : null; } else { this.fileBuilder_.addAllMessages(other.file_); }  }  mergeUnknownFields(other.unknownFields); onChanged(); return this; } public final boolean isInitialized() { return true; } public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException { CodeGeneratorResponse parsedMessage = null; try { parsedMessage = (CodeGeneratorResponse) CodeGeneratorResponse.PARSER.parsePartialFrom(input, extensionRegistry); } catch (InvalidProtocolBufferException e) { parsedMessage = (CodeGeneratorResponse)e.getUnfinishedMessage(); throw e.unwrapIOException(); } finally { if (parsedMessage != null) mergeFrom(parsedMessage);  }  return this; } public boolean hasError() { return ((this.bitField0_ & 0x1) != 0); } public String getError() { Object ref = this.error_; if (!(ref instanceof String)) { ByteString bs = (ByteString)ref; String s = bs.toStringUtf8(); if (bs.isValidUtf8()) this.error_ = s;  return s; }  return (String)ref; } public ByteString getErrorBytes() { Object ref = this.error_; if (ref instanceof String) { ByteString b = ByteString.copyFromUtf8((String)ref); this.error_ = b; return b; }  return (ByteString)ref; } public Builder setError(String value) { if (value == null) throw new NullPointerException();  this.bitField0_ |= 0x1; this.error_ = value; onChanged(); return this; } public Builder clearError() { this.bitField0_ &= 0xFFFFFFFE; this.error_ = CodeGeneratorResponse.getDefaultInstance().getError(); onChanged(); return this; } public Builder setErrorBytes(ByteString value) { if (value == null) throw new NullPointerException();  this.bitField0_ |= 0x1; this.error_ = value; onChanged(); return this; } public boolean hasSupportedFeatures() { return ((this.bitField0_ & 0x2) != 0); } public long getSupportedFeatures() { return this.supportedFeatures_; } public Builder setSupportedFeatures(long value) { this.bitField0_ |= 0x2; this.supportedFeatures_ = value; onChanged(); return this; }
/*      */       public Builder clearSupportedFeatures() { this.bitField0_ &= 0xFFFFFFFD; this.supportedFeatures_ = 0L; onChanged(); return this; }
/* 6008 */       private void ensureFileIsMutable() { if ((this.bitField0_ & 0x4) == 0) {
/* 6009 */           this.file_ = new ArrayList<>(this.file_);
/* 6010 */           this.bitField0_ |= 0x4;
/*      */         }  }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public List<File> getFileList() {
/* 6021 */         if (this.fileBuilder_ == null) {
/* 6022 */           return Collections.unmodifiableList(this.file_);
/*      */         }
/* 6024 */         return this.fileBuilder_.getMessageList();
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public int getFileCount() {
/* 6031 */         if (this.fileBuilder_ == null) {
/* 6032 */           return this.file_.size();
/*      */         }
/* 6034 */         return this.fileBuilder_.getCount();
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public File getFile(int index) {
/* 6041 */         if (this.fileBuilder_ == null) {
/* 6042 */           return this.file_.get(index);
/*      */         }
/* 6044 */         return (File)this.fileBuilder_.getMessage(index);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Builder setFile(int index, File value) {
/* 6052 */         if (this.fileBuilder_ == null) {
/* 6053 */           if (value == null) {
/* 6054 */             throw new NullPointerException();
/*      */           }
/* 6056 */           ensureFileIsMutable();
/* 6057 */           this.file_.set(index, value);
/* 6058 */           onChanged();
/*      */         } else {
/* 6060 */           this.fileBuilder_.setMessage(index, (AbstractMessage)value);
/*      */         } 
/* 6062 */         return this;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Builder setFile(int index, File.Builder builderForValue) {
/* 6069 */         if (this.fileBuilder_ == null) {
/* 6070 */           ensureFileIsMutable();
/* 6071 */           this.file_.set(index, builderForValue.build());
/* 6072 */           onChanged();
/*      */         } else {
/* 6074 */           this.fileBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
/*      */         } 
/* 6076 */         return this;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public Builder addFile(File value) {
/* 6082 */         if (this.fileBuilder_ == null) {
/* 6083 */           if (value == null) {
/* 6084 */             throw new NullPointerException();
/*      */           }
/* 6086 */           ensureFileIsMutable();
/* 6087 */           this.file_.add(value);
/* 6088 */           onChanged();
/*      */         } else {
/* 6090 */           this.fileBuilder_.addMessage((AbstractMessage)value);
/*      */         } 
/* 6092 */         return this;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Builder addFile(int index, File value) {
/* 6099 */         if (this.fileBuilder_ == null) {
/* 6100 */           if (value == null) {
/* 6101 */             throw new NullPointerException();
/*      */           }
/* 6103 */           ensureFileIsMutable();
/* 6104 */           this.file_.add(index, value);
/* 6105 */           onChanged();
/*      */         } else {
/* 6107 */           this.fileBuilder_.addMessage(index, (AbstractMessage)value);
/*      */         } 
/* 6109 */         return this;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Builder addFile(File.Builder builderForValue) {
/* 6116 */         if (this.fileBuilder_ == null) {
/* 6117 */           ensureFileIsMutable();
/* 6118 */           this.file_.add(builderForValue.build());
/* 6119 */           onChanged();
/*      */         } else {
/* 6121 */           this.fileBuilder_.addMessage((AbstractMessage)builderForValue.build());
/*      */         } 
/* 6123 */         return this;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Builder addFile(int index, File.Builder builderForValue) {
/* 6130 */         if (this.fileBuilder_ == null) {
/* 6131 */           ensureFileIsMutable();
/* 6132 */           this.file_.add(index, builderForValue.build());
/* 6133 */           onChanged();
/*      */         } else {
/* 6135 */           this.fileBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
/*      */         } 
/* 6137 */         return this;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Builder addAllFile(Iterable<? extends File> values) {
/* 6144 */         if (this.fileBuilder_ == null) {
/* 6145 */           ensureFileIsMutable();
/* 6146 */           AbstractMessageLite.Builder.addAll(values, this.file_);
/*      */           
/* 6148 */           onChanged();
/*      */         } else {
/* 6150 */           this.fileBuilder_.addAllMessages(values);
/*      */         } 
/* 6152 */         return this;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public Builder clearFile() {
/* 6158 */         if (this.fileBuilder_ == null) {
/* 6159 */           this.file_ = Collections.emptyList();
/* 6160 */           this.bitField0_ &= 0xFFFFFFFB;
/* 6161 */           onChanged();
/*      */         } else {
/* 6163 */           this.fileBuilder_.clear();
/*      */         } 
/* 6165 */         return this;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public Builder removeFile(int index) {
/* 6171 */         if (this.fileBuilder_ == null) {
/* 6172 */           ensureFileIsMutable();
/* 6173 */           this.file_.remove(index);
/* 6174 */           onChanged();
/*      */         } else {
/* 6176 */           this.fileBuilder_.remove(index);
/*      */         } 
/* 6178 */         return this;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public File.Builder getFileBuilder(int index) {
/* 6185 */         return (File.Builder)getFileFieldBuilder().getBuilder(index);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public FileOrBuilder getFileOrBuilder(int index) {
/* 6192 */         if (this.fileBuilder_ == null)
/* 6193 */           return this.file_.get(index); 
/* 6194 */         return (FileOrBuilder)this.fileBuilder_.getMessageOrBuilder(index);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public List<? extends FileOrBuilder> getFileOrBuilderList() {
/* 6202 */         if (this.fileBuilder_ != null) {
/* 6203 */           return this.fileBuilder_.getMessageOrBuilderList();
/*      */         }
/* 6205 */         return Collections.unmodifiableList((List)this.file_);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public File.Builder addFileBuilder() {
/* 6212 */         return (File.Builder)getFileFieldBuilder().addBuilder(
/* 6213 */             (AbstractMessage) File.getDefaultInstance());
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public File.Builder addFileBuilder(int index) {
/* 6220 */         return (File.Builder)getFileFieldBuilder().addBuilder(index,
/* 6221 */             (AbstractMessage) File.getDefaultInstance());
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public List<File.Builder> getFileBuilderList() {
/* 6228 */         return getFileFieldBuilder().getBuilderList();
/*      */       }
/*      */ 
/*      */       
/*      */       private RepeatedFieldBuilderV3<File, File.Builder, FileOrBuilder> getFileFieldBuilder() {
/* 6233 */         if (this.fileBuilder_ == null) {
/* 6234 */           this
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 6239 */             .fileBuilder_ = new RepeatedFieldBuilderV3(this.file_, ((this.bitField0_ & 0x4) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
/* 6240 */           this.file_ = null;
/*      */         } 
/* 6242 */         return this.fileBuilder_;
/*      */       }
/*      */ 
/*      */       
/*      */       public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
/* 6247 */         return (Builder)super.setUnknownFields(unknownFields);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
/* 6253 */         return (Builder)super.mergeUnknownFields(unknownFields);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 6263 */     private static final CodeGeneratorResponse DEFAULT_INSTANCE = new CodeGeneratorResponse();
/*      */ 
/*      */     
/*      */     public static CodeGeneratorResponse getDefaultInstance() {
/* 6267 */       return DEFAULT_INSTANCE;
/*      */     }
/*      */     
/*      */     @Deprecated
/* 6271 */     public static final Parser<CodeGeneratorResponse> PARSER = (Parser<CodeGeneratorResponse>)new AbstractParser<CodeGeneratorResponse>()
/*      */       {
/*      */ 
/*      */         
/*      */         public CodeGeneratorResponse parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException
/*      */         {
/* 6277 */           return new CodeGeneratorResponse(input, extensionRegistry);
/*      */         }
/*      */       };
/*      */     
/*      */     public static Parser<CodeGeneratorResponse> parser() {
/* 6282 */       return PARSER;
/*      */     }
/*      */ 
/*      */     
/*      */     public Parser<CodeGeneratorResponse> getParserForType() {
/* 6287 */       return PARSER;
/*      */     }
/*      */ 
/*      */     
/*      */     public CodeGeneratorResponse getDefaultInstanceForType() {
/* 6292 */       return DEFAULT_INSTANCE;
/*      */     }
/*      */     
/*      */     public static interface FileOrBuilder extends MessageOrBuilder { boolean hasName();
/*      */       
/*      */       String getName();
/*      */       
/*      */       ByteString getNameBytes();
/*      */       
/*      */       boolean hasInsertionPoint();
/*      */       
/*      */       String getInsertionPoint();
/*      */       
/*      */       ByteString getInsertionPointBytes();
/*      */       
/*      */       boolean hasContent();
/*      */       
/*      */       String getContent();
/*      */       
/*      */       ByteString getContentBytes();
/*      */       
/*      */       boolean hasGeneratedCodeInfo();
/*      */       
/*      */       DescriptorProtos.GeneratedCodeInfo getGeneratedCodeInfo();
/*      */       
/*      */       DescriptorProtos.GeneratedCodeInfoOrBuilder getGeneratedCodeInfoOrBuilder(); } }
/*      */   
/*      */   public static Descriptors.FileDescriptor getDescriptor() {
/* 6320 */     return descriptor;
/*      */   }
/*      */ 
/*      */   
/*      */   static {
/* 6325 */     String[] descriptorData = { "\n%google/protobuf/compiler/plugin.proto\022\030google.protobuf.compiler\032 google/protobuf/descriptor.proto\"F\n\007Version\022\r\n\005major\030\001 \001(\005\022\r\n\005minor\030\002 \001(\005\022\r\n\005patch\030\003 \001(\005\022\016\n\006suffix\030\004 \001(\t\"º\001\n\024CodeGeneratorRequest\022\030\n\020file_to_generate\030\001 \003(\t\022\021\n\tparameter\030\002 \001(\t\0228\n\nproto_file\030\017 \003(\0132$.google.protobuf.FileDescriptorProto\022;\n\020compiler_version\030\003 \001(\0132!.google.protobuf.compiler.Version\"Á\002\n\025CodeGeneratorResponse\022\r\n\005error\030\001 \001(\t\022\032\n\022supported_features\030\002 \001(\004\022B\n\004file\030\017 \003(\01324.google.protobuf.compiler.CodeGeneratorResponse.File\032\n\004File\022\f\n\004name\030\001 \001(\t\022\027\n\017insertion_point\030\002 \001(\t\022\017\n\007content\030\017 \001(\t\022?\n\023generated_code_info\030\020 \001(\0132\".google.protobuf.GeneratedCodeInfo\"8\n\007Feature\022\020\n\fFEATURE_NONE\020\000\022\033\n\027FEATURE_PROTO3_OPTIONAL\020\001BW\n\034com.google.protobuf.compilerB\fPluginProtosZ)google.golang.org/protobuf/types/pluginpb" };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 6348 */     descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[] {
/*      */           
/* 6350 */           DescriptorProtos.getDescriptor()
/*      */         });
/*      */     
/* 6353 */     internal_static_google_protobuf_compiler_Version_descriptor = getDescriptor().getMessageTypes().get(0);
/* 6354 */     internal_static_google_protobuf_compiler_Version_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_protobuf_compiler_Version_descriptor, new String[] { "Major", "Minor", "Patch", "Suffix" });
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 6359 */     internal_static_google_protobuf_compiler_CodeGeneratorRequest_descriptor = getDescriptor().getMessageTypes().get(1);
/* 6360 */     internal_static_google_protobuf_compiler_CodeGeneratorRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_protobuf_compiler_CodeGeneratorRequest_descriptor, new String[] { "FileToGenerate", "Parameter", "ProtoFile", "CompilerVersion" });
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 6365 */     internal_static_google_protobuf_compiler_CodeGeneratorResponse_descriptor = getDescriptor().getMessageTypes().get(2);
/* 6366 */     internal_static_google_protobuf_compiler_CodeGeneratorResponse_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_protobuf_compiler_CodeGeneratorResponse_descriptor, new String[] { "Error", "SupportedFeatures", "File" });
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 6371 */     internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_descriptor = internal_static_google_protobuf_compiler_CodeGeneratorResponse_descriptor.getNestedTypes().get(0);
/* 6372 */     internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_protobuf_compiler_CodeGeneratorResponse_File_descriptor, new String[] { "Name", "InsertionPoint", "Content", "GeneratedCodeInfo" });
/*      */ 
/*      */ 
/*      */     
/* 6376 */     DescriptorProtos.getDescriptor();
/*      */   } }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\compiler\PluginProtos.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */