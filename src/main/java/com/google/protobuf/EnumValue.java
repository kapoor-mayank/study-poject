/*      */ package com.google.protobuf;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.List;
/*      */ 
/*      */ public final class EnumValue extends GeneratedMessageV3 implements EnumValueOrBuilder {
/*      */   private static final long serialVersionUID = 0L;
/*      */   public static final int NAME_FIELD_NUMBER = 1;
/*      */   private volatile Object name_;
/*      */   public static final int NUMBER_FIELD_NUMBER = 2;
/*      */   private int number_;
/*      */   public static final int OPTIONS_FIELD_NUMBER = 3;
/*      */   private List<Option> options_;
/*      */   private byte memoizedIsInitialized;
/*      */   
/*   20 */   private EnumValue(GeneratedMessageV3.Builder<?> builder) { super(builder);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  234 */     this.memoizedIsInitialized = -1; } private EnumValue() { this.memoizedIsInitialized = -1; this.name_ = ""; this.options_ = Collections.emptyList(); } protected Object newInstance(UnusedPrivateParameter unused) { return new EnumValue(); } public final UnknownFieldSet getUnknownFields() { return this.unknownFields; } private EnumValue(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException { this(); if (extensionRegistry == null) throw new NullPointerException();  int mutable_bitField0_ = 0; UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(); try { boolean done = false; while (!done) { String s; int tag = input.readTag(); switch (tag) { case 0: done = true; continue;case 10: s = input.readStringRequireUtf8(); this.name_ = s; continue;case 16: this.number_ = input.readInt32(); continue;case 26: if ((mutable_bitField0_ & 0x1) == 0) { this.options_ = new ArrayList<>(); mutable_bitField0_ |= 0x1; }  this.options_.add(input.readMessage(Option.parser(), extensionRegistry)); continue; }  if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) done = true;  }  } catch (InvalidProtocolBufferException e) { throw e.setUnfinishedMessage(this); } catch (IOException e) { throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(this); } finally { if ((mutable_bitField0_ & 0x1) != 0) this.options_ = Collections.unmodifiableList(this.options_);  this.unknownFields = unknownFields.build(); makeExtensionsImmutable(); }  } public static final Descriptors.Descriptor getDescriptor() { return TypeProto.internal_static_google_protobuf_EnumValue_descriptor; }
/*      */   protected FieldAccessorTable internalGetFieldAccessorTable() { return TypeProto.internal_static_google_protobuf_EnumValue_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)EnumValue.class, (Class)Builder.class); }
/*      */   public String getName() { Object ref = this.name_; if (ref instanceof String) return (String)ref;  ByteString bs = (ByteString)ref; String s = bs.toStringUtf8(); this.name_ = s; return s; }
/*  237 */   public final boolean isInitialized() { byte isInitialized = this.memoizedIsInitialized;
/*  238 */     if (isInitialized == 1) return true; 
/*  239 */     if (isInitialized == 0) return false;
/*      */     
/*  241 */     this.memoizedIsInitialized = 1;
/*  242 */     return true; } public ByteString getNameBytes() { Object ref = this.name_; if (ref instanceof String) { ByteString b = ByteString.copyFromUtf8((String)ref); this.name_ = b; return b; }  return (ByteString)ref; } public int getNumber() { return this.number_; }
/*      */   public List<Option> getOptionsList() { return this.options_; }
/*      */   public List<? extends OptionOrBuilder> getOptionsOrBuilderList() { return (List)this.options_; }
/*      */   public int getOptionsCount() { return this.options_.size(); }
/*      */   public Option getOptions(int index) { return this.options_.get(index); }
/*      */   public OptionOrBuilder getOptionsOrBuilder(int index) { return this.options_.get(index); }
/*  248 */   public void writeTo(CodedOutputStream output) throws IOException { if (!getNameBytes().isEmpty()) {
/*  249 */       GeneratedMessageV3.writeString(output, 1, this.name_);
/*      */     }
/*  251 */     if (this.number_ != 0) {
/*  252 */       output.writeInt32(2, this.number_);
/*      */     }
/*  254 */     for (int i = 0; i < this.options_.size(); i++) {
/*  255 */       output.writeMessage(3, this.options_.get(i));
/*      */     }
/*  257 */     this.unknownFields.writeTo(output); }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSerializedSize() {
/*  262 */     int size = this.memoizedSize;
/*  263 */     if (size != -1) return size;
/*      */     
/*  265 */     size = 0;
/*  266 */     if (!getNameBytes().isEmpty()) {
/*  267 */       size += GeneratedMessageV3.computeStringSize(1, this.name_);
/*      */     }
/*  269 */     if (this.number_ != 0) {
/*  270 */       size += 
/*  271 */         CodedOutputStream.computeInt32Size(2, this.number_);
/*      */     }
/*  273 */     for (int i = 0; i < this.options_.size(); i++) {
/*  274 */       size += 
/*  275 */         CodedOutputStream.computeMessageSize(3, this.options_.get(i));
/*      */     }
/*  277 */     size += this.unknownFields.getSerializedSize();
/*  278 */     this.memoizedSize = size;
/*  279 */     return size;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean equals(Object obj) {
/*  284 */     if (obj == this) {
/*  285 */       return true;
/*      */     }
/*  287 */     if (!(obj instanceof EnumValue)) {
/*  288 */       return super.equals(obj);
/*      */     }
/*  290 */     EnumValue other = (EnumValue)obj;
/*      */ 
/*      */     
/*  293 */     if (!getName().equals(other.getName())) return false; 
/*  294 */     if (getNumber() != other
/*  295 */       .getNumber()) return false;
/*      */     
/*  297 */     if (!getOptionsList().equals(other.getOptionsList())) return false; 
/*  298 */     if (!this.unknownFields.equals(other.unknownFields)) return false; 
/*  299 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public int hashCode() {
/*  304 */     if (this.memoizedHashCode != 0) {
/*  305 */       return this.memoizedHashCode;
/*      */     }
/*  307 */     int hash = 41;
/*  308 */     hash = 19 * hash + getDescriptor().hashCode();
/*  309 */     hash = 37 * hash + 1;
/*  310 */     hash = 53 * hash + getName().hashCode();
/*  311 */     hash = 37 * hash + 2;
/*  312 */     hash = 53 * hash + getNumber();
/*  313 */     if (getOptionsCount() > 0) {
/*  314 */       hash = 37 * hash + 3;
/*  315 */       hash = 53 * hash + getOptionsList().hashCode();
/*      */     } 
/*  317 */     hash = 29 * hash + this.unknownFields.hashCode();
/*  318 */     this.memoizedHashCode = hash;
/*  319 */     return hash;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static EnumValue parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
/*  325 */     return PARSER.parseFrom(data);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static EnumValue parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  331 */     return PARSER.parseFrom(data, extensionRegistry);
/*      */   }
/*      */ 
/*      */   
/*      */   public static EnumValue parseFrom(ByteString data) throws InvalidProtocolBufferException {
/*  336 */     return PARSER.parseFrom(data);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static EnumValue parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  342 */     return PARSER.parseFrom(data, extensionRegistry);
/*      */   }
/*      */   
/*      */   public static EnumValue parseFrom(byte[] data) throws InvalidProtocolBufferException {
/*  346 */     return PARSER.parseFrom(data);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static EnumValue parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  352 */     return PARSER.parseFrom(data, extensionRegistry);
/*      */   }
/*      */   
/*      */   public static EnumValue parseFrom(InputStream input) throws IOException {
/*  356 */     return 
/*  357 */       GeneratedMessageV3.<EnumValue>parseWithIOException(PARSER, input);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static EnumValue parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  363 */     return 
/*  364 */       GeneratedMessageV3.<EnumValue>parseWithIOException(PARSER, input, extensionRegistry);
/*      */   }
/*      */   
/*      */   public static EnumValue parseDelimitedFrom(InputStream input) throws IOException {
/*  368 */     return 
/*  369 */       GeneratedMessageV3.<EnumValue>parseDelimitedWithIOException(PARSER, input);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static EnumValue parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  375 */     return 
/*  376 */       GeneratedMessageV3.<EnumValue>parseDelimitedWithIOException(PARSER, input, extensionRegistry);
/*      */   }
/*      */ 
/*      */   
/*      */   public static EnumValue parseFrom(CodedInputStream input) throws IOException {
/*  381 */     return 
/*  382 */       GeneratedMessageV3.<EnumValue>parseWithIOException(PARSER, input);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static EnumValue parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  388 */     return 
/*  389 */       GeneratedMessageV3.<EnumValue>parseWithIOException(PARSER, input, extensionRegistry);
/*      */   }
/*      */   
/*      */   public Builder newBuilderForType() {
/*  393 */     return newBuilder();
/*      */   } public static Builder newBuilder() {
/*  395 */     return DEFAULT_INSTANCE.toBuilder();
/*      */   }
/*      */   public static Builder newBuilder(EnumValue prototype) {
/*  398 */     return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
/*      */   }
/*      */   
/*      */   public Builder toBuilder() {
/*  402 */     return (this == DEFAULT_INSTANCE) ? new Builder() : (new Builder())
/*  403 */       .mergeFrom(this);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected Builder newBuilderForType(BuilderParent parent) {
/*  409 */     Builder builder = new Builder(parent);
/*  410 */     return builder;
/*      */   }
/*      */ 
/*      */   
/*      */   public static final class Builder
/*      */     extends GeneratedMessageV3.Builder<Builder>
/*      */     implements EnumValueOrBuilder
/*      */   {
/*      */     private int bitField0_;
/*      */     private Object name_;
/*      */     private int number_;
/*      */     private List<Option> options_;
/*      */     private RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> optionsBuilder_;
/*      */     
/*      */     public static final Descriptors.Descriptor getDescriptor() {
/*  425 */       return TypeProto.internal_static_google_protobuf_EnumValue_descriptor;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected FieldAccessorTable internalGetFieldAccessorTable() {
/*  431 */       return TypeProto.internal_static_google_protobuf_EnumValue_fieldAccessorTable
/*  432 */         .ensureFieldAccessorsInitialized((Class)EnumValue.class, (Class)Builder.class);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private Builder()
/*      */     {
/*  614 */       this.name_ = "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  753 */       this
/*  754 */         .options_ = Collections.emptyList(); maybeForceBuilderInitialization(); } private void maybeForceBuilderInitialization() { if (GeneratedMessageV3.alwaysUseFieldBuilders) getOptionsFieldBuilder();  } public Builder clear() { super.clear(); this.name_ = ""; this.number_ = 0; if (this.optionsBuilder_ == null) { this.options_ = Collections.emptyList(); this.bitField0_ &= 0xFFFFFFFE; } else { this.optionsBuilder_.clear(); }  return this; } public Descriptors.Descriptor getDescriptorForType() { return TypeProto.internal_static_google_protobuf_EnumValue_descriptor; } public EnumValue getDefaultInstanceForType() { return EnumValue.getDefaultInstance(); } public EnumValue build() { EnumValue result = buildPartial(); if (!result.isInitialized()) throw newUninitializedMessageException(result);  return result; } public EnumValue buildPartial() { EnumValue result = new EnumValue(this); int from_bitField0_ = this.bitField0_; result.name_ = this.name_; result.number_ = this.number_; if (this.optionsBuilder_ == null) { if ((this.bitField0_ & 0x1) != 0) { this.options_ = Collections.unmodifiableList(this.options_); this.bitField0_ &= 0xFFFFFFFE; }  result.options_ = this.options_; } else { result.options_ = this.optionsBuilder_.build(); }  onBuilt(); return result; } private Builder(BuilderParent parent) { super(parent); this.name_ = ""; this.options_ = Collections.emptyList(); maybeForceBuilderInitialization(); }
/*      */     public Builder clone() { return super.clone(); }
/*  756 */     public Builder setField(Descriptors.FieldDescriptor field, Object value) { return super.setField(field, value); } public Builder clearField(Descriptors.FieldDescriptor field) { return super.clearField(field); } public Builder clearOneof(Descriptors.OneofDescriptor oneof) { return super.clearOneof(oneof); } public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) { return super.setRepeatedField(field, index, value); } public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) { return super.addRepeatedField(field, value); } private void ensureOptionsIsMutable() { if ((this.bitField0_ & 0x1) == 0)
/*  757 */       { this.options_ = new ArrayList<>(this.options_);
/*  758 */         this.bitField0_ |= 0x1; }  } public Builder mergeFrom(Message other) { if (other instanceof EnumValue)
/*      */         return mergeFrom((EnumValue)other);  super.mergeFrom(other); return this; }
/*      */     public Builder mergeFrom(EnumValue other) { if (other == EnumValue.getDefaultInstance())
/*      */         return this;  if (!other.getName().isEmpty()) { this.name_ = other.name_; onChanged(); }  if (other.getNumber() != 0)
/*      */         setNumber(other.getNumber());  if (this.optionsBuilder_ == null) { if (!other.options_.isEmpty()) { if (this.options_.isEmpty()) { this.options_ = other.options_; this.bitField0_ &= 0xFFFFFFFE; } else { ensureOptionsIsMutable(); this.options_.addAll(other.options_); }  onChanged(); }  } else if (!other.options_.isEmpty()) { if (this.optionsBuilder_.isEmpty()) { this.optionsBuilder_.dispose(); this.optionsBuilder_ = null; this.options_ = other.options_; this.bitField0_ &= 0xFFFFFFFE; this.optionsBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getOptionsFieldBuilder() : null; } else { this.optionsBuilder_.addAllMessages(other.options_); }  }  mergeUnknownFields(other.unknownFields); onChanged(); return this; }
/*      */     public final boolean isInitialized() { return true; }
/*      */     public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException { EnumValue parsedMessage = null; try { parsedMessage = EnumValue.PARSER.parsePartialFrom(input, extensionRegistry); }
/*      */       catch (InvalidProtocolBufferException e) { parsedMessage = (EnumValue)e.getUnfinishedMessage(); throw e.unwrapIOException(); }
/*      */       finally { if (parsedMessage != null)
/*      */           mergeFrom(parsedMessage);  }
/*      */        return this; }
/*      */     public String getName() { Object ref = this.name_; if (!(ref instanceof String)) { ByteString bs = (ByteString)ref; String s = bs.toStringUtf8(); this.name_ = s; return s; }
/*      */        return (String)ref; }
/*      */     public ByteString getNameBytes() { Object ref = this.name_; if (ref instanceof String) { ByteString b = ByteString.copyFromUtf8((String)ref); this.name_ = b; return b; }
/*      */        return (ByteString)ref; }
/*  773 */     public List<Option> getOptionsList() { if (this.optionsBuilder_ == null) {
/*  774 */         return Collections.unmodifiableList(this.options_);
/*      */       }
/*  776 */       return this.optionsBuilder_.getMessageList(); } public Builder setName(String value) { if (value == null)
/*      */         throw new NullPointerException();  this.name_ = value; onChanged(); return this; }
/*      */     public Builder clearName() { this.name_ = EnumValue.getDefaultInstance().getName(); onChanged(); return this; }
/*      */     public Builder setNameBytes(ByteString value) { if (value == null)
/*      */         throw new NullPointerException();  AbstractMessageLite.checkByteStringIsUtf8(value); this.name_ = value; onChanged(); return this; }
/*      */     public int getNumber() { return this.number_; }
/*      */     public Builder setNumber(int value) { this.number_ = value; onChanged();
/*      */       return this; }
/*      */     public Builder clearNumber() { this.number_ = 0;
/*      */       onChanged();
/*      */       return this; }
/*  787 */     public int getOptionsCount() { if (this.optionsBuilder_ == null) {
/*  788 */         return this.options_.size();
/*      */       }
/*  790 */       return this.optionsBuilder_.getCount(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Option getOptions(int index) {
/*  801 */       if (this.optionsBuilder_ == null) {
/*  802 */         return this.options_.get(index);
/*      */       }
/*  804 */       return this.optionsBuilder_.getMessage(index);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Builder setOptions(int index, Option value) {
/*  816 */       if (this.optionsBuilder_ == null) {
/*  817 */         if (value == null) {
/*  818 */           throw new NullPointerException();
/*      */         }
/*  820 */         ensureOptionsIsMutable();
/*  821 */         this.options_.set(index, value);
/*  822 */         onChanged();
/*      */       } else {
/*  824 */         this.optionsBuilder_.setMessage(index, value);
/*      */       } 
/*  826 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Builder setOptions(int index, Option.Builder builderForValue) {
/*  837 */       if (this.optionsBuilder_ == null) {
/*  838 */         ensureOptionsIsMutable();
/*  839 */         this.options_.set(index, builderForValue.build());
/*  840 */         onChanged();
/*      */       } else {
/*  842 */         this.optionsBuilder_.setMessage(index, builderForValue.build());
/*      */       } 
/*  844 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Builder addOptions(Option value) {
/*  854 */       if (this.optionsBuilder_ == null) {
/*  855 */         if (value == null) {
/*  856 */           throw new NullPointerException();
/*      */         }
/*  858 */         ensureOptionsIsMutable();
/*  859 */         this.options_.add(value);
/*  860 */         onChanged();
/*      */       } else {
/*  862 */         this.optionsBuilder_.addMessage(value);
/*      */       } 
/*  864 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Builder addOptions(int index, Option value) {
/*  875 */       if (this.optionsBuilder_ == null) {
/*  876 */         if (value == null) {
/*  877 */           throw new NullPointerException();
/*      */         }
/*  879 */         ensureOptionsIsMutable();
/*  880 */         this.options_.add(index, value);
/*  881 */         onChanged();
/*      */       } else {
/*  883 */         this.optionsBuilder_.addMessage(index, value);
/*      */       } 
/*  885 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Builder addOptions(Option.Builder builderForValue) {
/*  896 */       if (this.optionsBuilder_ == null) {
/*  897 */         ensureOptionsIsMutable();
/*  898 */         this.options_.add(builderForValue.build());
/*  899 */         onChanged();
/*      */       } else {
/*  901 */         this.optionsBuilder_.addMessage(builderForValue.build());
/*      */       } 
/*  903 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Builder addOptions(int index, Option.Builder builderForValue) {
/*  914 */       if (this.optionsBuilder_ == null) {
/*  915 */         ensureOptionsIsMutable();
/*  916 */         this.options_.add(index, builderForValue.build());
/*  917 */         onChanged();
/*      */       } else {
/*  919 */         this.optionsBuilder_.addMessage(index, builderForValue.build());
/*      */       } 
/*  921 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Builder addAllOptions(Iterable<? extends Option> values) {
/*  932 */       if (this.optionsBuilder_ == null) {
/*  933 */         ensureOptionsIsMutable();
/*  934 */         AbstractMessageLite.Builder.addAll(values, this.options_);
/*      */         
/*  936 */         onChanged();
/*      */       } else {
/*  938 */         this.optionsBuilder_.addAllMessages(values);
/*      */       } 
/*  940 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Builder clearOptions() {
/*  950 */       if (this.optionsBuilder_ == null) {
/*  951 */         this.options_ = Collections.emptyList();
/*  952 */         this.bitField0_ &= 0xFFFFFFFE;
/*  953 */         onChanged();
/*      */       } else {
/*  955 */         this.optionsBuilder_.clear();
/*      */       } 
/*  957 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Builder removeOptions(int index) {
/*  967 */       if (this.optionsBuilder_ == null) {
/*  968 */         ensureOptionsIsMutable();
/*  969 */         this.options_.remove(index);
/*  970 */         onChanged();
/*      */       } else {
/*  972 */         this.optionsBuilder_.remove(index);
/*      */       } 
/*  974 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Option.Builder getOptionsBuilder(int index) {
/*  985 */       return getOptionsFieldBuilder().getBuilder(index);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public OptionOrBuilder getOptionsOrBuilder(int index) {
/*  996 */       if (this.optionsBuilder_ == null)
/*  997 */         return this.options_.get(index); 
/*  998 */       return this.optionsBuilder_.getMessageOrBuilder(index);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public List<? extends OptionOrBuilder> getOptionsOrBuilderList() {
/* 1010 */       if (this.optionsBuilder_ != null) {
/* 1011 */         return this.optionsBuilder_.getMessageOrBuilderList();
/*      */       }
/* 1013 */       return Collections.unmodifiableList((List)this.options_);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Option.Builder addOptionsBuilder() {
/* 1024 */       return getOptionsFieldBuilder().addBuilder(
/* 1025 */           Option.getDefaultInstance());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Option.Builder addOptionsBuilder(int index) {
/* 1036 */       return getOptionsFieldBuilder().addBuilder(index, 
/* 1037 */           Option.getDefaultInstance());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public List<Option.Builder> getOptionsBuilderList() {
/* 1048 */       return getOptionsFieldBuilder().getBuilderList();
/*      */     }
/*      */ 
/*      */     
/*      */     private RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> getOptionsFieldBuilder() {
/* 1053 */       if (this.optionsBuilder_ == null) {
/* 1054 */         this
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1059 */           .optionsBuilder_ = new RepeatedFieldBuilderV3<>(this.options_, ((this.bitField0_ & 0x1) != 0), getParentForChildren(), isClean());
/* 1060 */         this.options_ = null;
/*      */       } 
/* 1062 */       return this.optionsBuilder_;
/*      */     }
/*      */ 
/*      */     
/*      */     public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
/* 1067 */       return super.setUnknownFields(unknownFields);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
/* 1073 */       return super.mergeUnknownFields(unknownFields);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1083 */   private static final EnumValue DEFAULT_INSTANCE = new EnumValue();
/*      */ 
/*      */   
/*      */   public static EnumValue getDefaultInstance() {
/* 1087 */     return DEFAULT_INSTANCE;
/*      */   }
/*      */ 
/*      */   
/* 1091 */   private static final Parser<EnumValue> PARSER = new AbstractParser<EnumValue>()
/*      */     {
/*      */ 
/*      */       
/*      */       public EnumValue parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException
/*      */       {
/* 1097 */         return new EnumValue(input, extensionRegistry);
/*      */       }
/*      */     };
/*      */   
/*      */   public static Parser<EnumValue> parser() {
/* 1102 */     return PARSER;
/*      */   }
/*      */ 
/*      */   
/*      */   public Parser<EnumValue> getParserForType() {
/* 1107 */     return PARSER;
/*      */   }
/*      */ 
/*      */   
/*      */   public EnumValue getDefaultInstanceForType() {
/* 1112 */     return DEFAULT_INSTANCE;
/*      */   }
/*      */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\EnumValue.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */