/*     */ package com.google.protobuf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.ByteBuffer;
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Option
/*     */   extends GeneratedMessageV3
/*     */   implements OptionOrBuilder
/*     */ {
/*     */   private static final long serialVersionUID = 0L;
/*     */   public static final int NAME_FIELD_NUMBER = 1;
/*     */   private volatile Object name_;
/*     */   public static final int VALUE_FIELD_NUMBER = 2;
/*     */   private Any value_;
/*     */   private byte memoizedIsInitialized;
/*     */   
/*     */   private Option(GeneratedMessageV3.Builder<?> builder) {
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 207 */     this.memoizedIsInitialized = -1; } protected Object newInstance(UnusedPrivateParameter unused) { return new Option(); } public final UnknownFieldSet getUnknownFields() { return this.unknownFields; } private Option(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException { this(); if (extensionRegistry == null) throw new NullPointerException();  UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(); try { boolean done = false; while (!done) { String s; Any.Builder subBuilder; int tag = input.readTag(); switch (tag) { case 0: done = true; continue;case 10: s = input.readStringRequireUtf8(); this.name_ = s; continue;case 18: subBuilder = null; if (this.value_ != null) subBuilder = this.value_.toBuilder();  this.value_ = input.<Any>readMessage(Any.parser(), extensionRegistry); if (subBuilder != null) { subBuilder.mergeFrom(this.value_); this.value_ = subBuilder.buildPartial(); }  continue; }  if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) done = true;  }  } catch (InvalidProtocolBufferException e) { throw e.setUnfinishedMessage(this); } catch (IOException e) { throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(this); } finally { this.unknownFields = unknownFields.build(); makeExtensionsImmutable(); }  } private Option() { this.memoizedIsInitialized = -1;
/*     */     this.name_ = ""; }
/*     */   public static final Descriptors.Descriptor getDescriptor() { return TypeProto.internal_static_google_protobuf_Option_descriptor; }
/* 210 */   protected FieldAccessorTable internalGetFieldAccessorTable() { return TypeProto.internal_static_google_protobuf_Option_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)Option.class, (Class)Builder.class); } public final boolean isInitialized() { byte isInitialized = this.memoizedIsInitialized;
/* 211 */     if (isInitialized == 1) return true; 
/* 212 */     if (isInitialized == 0) return false;
/*     */     
/* 214 */     this.memoizedIsInitialized = 1;
/* 215 */     return true; } public String getName() { Object ref = this.name_; if (ref instanceof String)
/*     */       return (String)ref;  ByteString bs = (ByteString)ref; String s = bs.toStringUtf8(); this.name_ = s; return s; }
/*     */   public ByteString getNameBytes() { Object ref = this.name_; if (ref instanceof String) { ByteString b = ByteString.copyFromUtf8((String)ref); this.name_ = b; return b; }  return (ByteString)ref; }
/*     */   public boolean hasValue() { return (this.value_ != null); }
/*     */   public Any getValue() { return (this.value_ == null) ? Any.getDefaultInstance() : this.value_; }
/*     */   public AnyOrBuilder getValueOrBuilder() { return getValue(); }
/* 221 */   public void writeTo(CodedOutputStream output) throws IOException { if (!getNameBytes().isEmpty()) {
/* 222 */       GeneratedMessageV3.writeString(output, 1, this.name_);
/*     */     }
/* 224 */     if (this.value_ != null) {
/* 225 */       output.writeMessage(2, getValue());
/*     */     }
/* 227 */     this.unknownFields.writeTo(output); }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSerializedSize() {
/* 232 */     int size = this.memoizedSize;
/* 233 */     if (size != -1) return size;
/*     */     
/* 235 */     size = 0;
/* 236 */     if (!getNameBytes().isEmpty()) {
/* 237 */       size += GeneratedMessageV3.computeStringSize(1, this.name_);
/*     */     }
/* 239 */     if (this.value_ != null) {
/* 240 */       size += 
/* 241 */         CodedOutputStream.computeMessageSize(2, getValue());
/*     */     }
/* 243 */     size += this.unknownFields.getSerializedSize();
/* 244 */     this.memoizedSize = size;
/* 245 */     return size;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 250 */     if (obj == this) {
/* 251 */       return true;
/*     */     }
/* 253 */     if (!(obj instanceof Option)) {
/* 254 */       return super.equals(obj);
/*     */     }
/* 256 */     Option other = (Option)obj;
/*     */ 
/*     */     
/* 259 */     if (!getName().equals(other.getName())) return false; 
/* 260 */     if (hasValue() != other.hasValue()) return false; 
/* 261 */     if (hasValue() && 
/*     */       
/* 263 */       !getValue().equals(other.getValue())) return false;
/*     */     
/* 265 */     if (!this.unknownFields.equals(other.unknownFields)) return false; 
/* 266 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 271 */     if (this.memoizedHashCode != 0) {
/* 272 */       return this.memoizedHashCode;
/*     */     }
/* 274 */     int hash = 41;
/* 275 */     hash = 19 * hash + getDescriptor().hashCode();
/* 276 */     hash = 37 * hash + 1;
/* 277 */     hash = 53 * hash + getName().hashCode();
/* 278 */     if (hasValue()) {
/* 279 */       hash = 37 * hash + 2;
/* 280 */       hash = 53 * hash + getValue().hashCode();
/*     */     } 
/* 282 */     hash = 29 * hash + this.unknownFields.hashCode();
/* 283 */     this.memoizedHashCode = hash;
/* 284 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Option parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
/* 290 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Option parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 296 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Option parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 301 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Option parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 307 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static Option parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 311 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Option parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 317 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static Option parseFrom(InputStream input) throws IOException {
/* 321 */     return 
/* 322 */       GeneratedMessageV3.<Option>parseWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Option parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 328 */     return 
/* 329 */       GeneratedMessageV3.<Option>parseWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static Option parseDelimitedFrom(InputStream input) throws IOException {
/* 333 */     return 
/* 334 */       GeneratedMessageV3.<Option>parseDelimitedWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Option parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 340 */     return 
/* 341 */       GeneratedMessageV3.<Option>parseDelimitedWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Option parseFrom(CodedInputStream input) throws IOException {
/* 346 */     return 
/* 347 */       GeneratedMessageV3.<Option>parseWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Option parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 353 */     return 
/* 354 */       GeneratedMessageV3.<Option>parseWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */   
/*     */   public Builder newBuilderForType() {
/* 358 */     return newBuilder();
/*     */   } public static Builder newBuilder() {
/* 360 */     return DEFAULT_INSTANCE.toBuilder();
/*     */   }
/*     */   public static Builder newBuilder(Option prototype) {
/* 363 */     return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
/*     */   }
/*     */   
/*     */   public Builder toBuilder() {
/* 367 */     return (this == DEFAULT_INSTANCE) ? new Builder() : (new Builder())
/* 368 */       .mergeFrom(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Builder newBuilderForType(BuilderParent parent) {
/* 374 */     Builder builder = new Builder(parent);
/* 375 */     return builder;
/*     */   }
/*     */ 
/*     */   
/*     */   public static final class Builder
/*     */     extends GeneratedMessageV3.Builder<Builder>
/*     */     implements OptionOrBuilder
/*     */   {
/*     */     private Object name_;
/*     */     
/*     */     private Any value_;
/*     */     
/*     */     private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> valueBuilder_;
/*     */ 
/*     */     
/*     */     public static final Descriptors.Descriptor getDescriptor() {
/* 391 */       return TypeProto.internal_static_google_protobuf_Option_descriptor;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected FieldAccessorTable internalGetFieldAccessorTable() {
/* 397 */       return TypeProto.internal_static_google_protobuf_Option_fieldAccessorTable
/* 398 */         .ensureFieldAccessorsInitialized((Class)Option.class, (Class)Builder.class);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private Builder()
/*     */     {
/* 544 */       this.name_ = ""; maybeForceBuilderInitialization(); } private Builder(BuilderParent parent) { super(parent); this.name_ = ""; maybeForceBuilderInitialization(); } private void maybeForceBuilderInitialization() { if (GeneratedMessageV3.alwaysUseFieldBuilders); }
/*     */     public Builder clear() { super.clear(); this.name_ = ""; if (this.valueBuilder_ == null) { this.value_ = null; }
/*     */       else { this.value_ = null; this.valueBuilder_ = null; }
/*     */        return this; }
/*     */     public Descriptors.Descriptor getDescriptorForType() { return TypeProto.internal_static_google_protobuf_Option_descriptor; }
/*     */     public Option getDefaultInstanceForType() { return Option.getDefaultInstance(); }
/*     */     public Option build() { Option result = buildPartial(); if (!result.isInitialized())
/*     */         throw newUninitializedMessageException(result);  return result; }
/*     */     public Option buildPartial() { Option result = new Option(this); result.name_ = this.name_; if (this.valueBuilder_ == null) { result.value_ = this.value_; }
/*     */       else { result.value_ = this.valueBuilder_.build(); }
/*     */        onBuilt(); return result; }
/*     */     public Builder clone() { return super.clone(); }
/*     */     public Builder setField(Descriptors.FieldDescriptor field, Object value) { return super.setField(field, value); }
/* 557 */     public String getName() { Object ref = this.name_;
/* 558 */       if (!(ref instanceof String)) {
/* 559 */         ByteString bs = (ByteString)ref;
/*     */         
/* 561 */         String s = bs.toStringUtf8();
/* 562 */         this.name_ = s;
/* 563 */         return s;
/*     */       } 
/* 565 */       return (String)ref; } public Builder clearField(Descriptors.FieldDescriptor field) { return super.clearField(field); }
/*     */     public Builder clearOneof(Descriptors.OneofDescriptor oneof) { return super.clearOneof(oneof); }
/*     */     public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) { return super.setRepeatedField(field, index, value); }
/*     */     public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) { return super.addRepeatedField(field, value); }
/*     */     public Builder mergeFrom(Message other) { if (other instanceof Option)
/*     */         return mergeFrom((Option)other);  super.mergeFrom(other); return this; }
/*     */     public Builder mergeFrom(Option other) { if (other == Option.getDefaultInstance())
/*     */         return this;  if (!other.getName().isEmpty()) { this.name_ = other.name_; onChanged(); }
/*     */        if (other.hasValue())
/*     */         mergeValue(other.getValue());  mergeUnknownFields(other.unknownFields); onChanged(); return this; }
/*     */     public final boolean isInitialized() { return true; }
/*     */     public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException { Option parsedMessage = null; try { parsedMessage = Option.PARSER.parsePartialFrom(input, extensionRegistry); }
/*     */       catch (InvalidProtocolBufferException e) { parsedMessage = (Option)e.getUnfinishedMessage(); throw e.unwrapIOException(); }
/*     */       finally { if (parsedMessage != null)
/*     */           mergeFrom(parsedMessage);  }
/*     */        return this; }
/* 581 */     public ByteString getNameBytes() { Object ref = this.name_;
/* 582 */       if (ref instanceof String) {
/*     */         
/* 584 */         ByteString b = ByteString.copyFromUtf8((String)ref);
/*     */         
/* 586 */         this.name_ = b;
/* 587 */         return b;
/*     */       } 
/* 589 */       return (ByteString)ref; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setName(String value) {
/* 606 */       if (value == null) {
/* 607 */         throw new NullPointerException();
/*     */       }
/*     */       
/* 610 */       this.name_ = value;
/* 611 */       onChanged();
/* 612 */       return this;
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
/*     */     public Builder clearName() {
/* 627 */       this.name_ = Option.getDefaultInstance().getName();
/* 628 */       onChanged();
/* 629 */       return this;
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
/*     */     public Builder setNameBytes(ByteString value) {
/* 645 */       if (value == null) {
/* 646 */         throw new NullPointerException();
/*     */       }
/* 648 */       AbstractMessageLite.checkByteStringIsUtf8(value);
/*     */       
/* 650 */       this.name_ = value;
/* 651 */       onChanged();
/* 652 */       return this;
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
/*     */     public boolean hasValue() {
/* 670 */       return (this.valueBuilder_ != null || this.value_ != null);
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
/*     */     public Any getValue() {
/* 684 */       if (this.valueBuilder_ == null) {
/* 685 */         return (this.value_ == null) ? Any.getDefaultInstance() : this.value_;
/*     */       }
/* 687 */       return this.valueBuilder_.getMessage();
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
/*     */     public Builder setValue(Any value) {
/* 701 */       if (this.valueBuilder_ == null) {
/* 702 */         if (value == null) {
/* 703 */           throw new NullPointerException();
/*     */         }
/* 705 */         this.value_ = value;
/* 706 */         onChanged();
/*     */       } else {
/* 708 */         this.valueBuilder_.setMessage(value);
/*     */       } 
/*     */       
/* 711 */       return this;
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
/*     */     public Builder setValue(Any.Builder builderForValue) {
/* 725 */       if (this.valueBuilder_ == null) {
/* 726 */         this.value_ = builderForValue.build();
/* 727 */         onChanged();
/*     */       } else {
/* 729 */         this.valueBuilder_.setMessage(builderForValue.build());
/*     */       } 
/*     */       
/* 732 */       return this;
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
/*     */     public Builder mergeValue(Any value) {
/* 745 */       if (this.valueBuilder_ == null) {
/* 746 */         if (this.value_ != null) {
/* 747 */           this
/* 748 */             .value_ = Any.newBuilder(this.value_).mergeFrom(value).buildPartial();
/*     */         } else {
/* 750 */           this.value_ = value;
/*     */         } 
/* 752 */         onChanged();
/*     */       } else {
/* 754 */         this.valueBuilder_.mergeFrom(value);
/*     */       } 
/*     */       
/* 757 */       return this;
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
/*     */     public Builder clearValue() {
/* 770 */       if (this.valueBuilder_ == null) {
/* 771 */         this.value_ = null;
/* 772 */         onChanged();
/*     */       } else {
/* 774 */         this.value_ = null;
/* 775 */         this.valueBuilder_ = null;
/*     */       } 
/*     */       
/* 778 */       return this;
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
/*     */     public Any.Builder getValueBuilder() {
/* 792 */       onChanged();
/* 793 */       return getValueFieldBuilder().getBuilder();
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
/*     */     public AnyOrBuilder getValueOrBuilder() {
/* 806 */       if (this.valueBuilder_ != null) {
/* 807 */         return this.valueBuilder_.getMessageOrBuilder();
/*     */       }
/* 809 */       return (this.value_ == null) ? 
/* 810 */         Any.getDefaultInstance() : this.value_;
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
/*     */     private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getValueFieldBuilder() {
/* 826 */       if (this.valueBuilder_ == null) {
/* 827 */         this
/*     */ 
/*     */ 
/*     */           
/* 831 */           .valueBuilder_ = new SingleFieldBuilderV3<>(getValue(), getParentForChildren(), isClean());
/* 832 */         this.value_ = null;
/*     */       } 
/* 834 */       return this.valueBuilder_;
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
/* 855 */   private static final Option DEFAULT_INSTANCE = new Option();
/*     */ 
/*     */   
/*     */   public static Option getDefaultInstance() {
/* 859 */     return DEFAULT_INSTANCE;
/*     */   }
/*     */ 
/*     */   
/* 863 */   private static final Parser<Option> PARSER = new AbstractParser<Option>()
/*     */     {
/*     */ 
/*     */       
/*     */       public Option parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException
/*     */       {
/* 869 */         return new Option(input, extensionRegistry);
/*     */       }
/*     */     };
/*     */   
/*     */   public static Parser<Option> parser() {
/* 874 */     return PARSER;
/*     */   }
/*     */ 
/*     */   
/*     */   public Parser<Option> getParserForType() {
/* 879 */     return PARSER;
/*     */   }
/*     */ 
/*     */   
/*     */   public Option getDefaultInstanceForType() {
/* 884 */     return DEFAULT_INSTANCE;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\Option.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */