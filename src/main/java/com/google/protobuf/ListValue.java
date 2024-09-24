/*     */ package com.google.protobuf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ public final class ListValue
/*     */   extends GeneratedMessageV3
/*     */   implements ListValueOrBuilder
/*     */ {
/*     */   private static final long serialVersionUID = 0L;
/*     */   public static final int VALUES_FIELD_NUMBER = 1;
/*     */   private List<Value> values_;
/*     */   private byte memoizedIsInitialized;
/*     */   
/*     */   private ListValue(GeneratedMessageV3.Builder<?> builder) {
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
/* 162 */     this.memoizedIsInitialized = -1; } private ListValue() { this.memoizedIsInitialized = -1; this.values_ = Collections.emptyList(); } protected Object newInstance(UnusedPrivateParameter unused) { return new ListValue(); } public final UnknownFieldSet getUnknownFields() { return this.unknownFields; } private ListValue(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException { this(); if (extensionRegistry == null) throw new NullPointerException();  int mutable_bitField0_ = 0; UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(); try { boolean done = false; while (!done) { int tag = input.readTag(); switch (tag) { case 0: done = true; continue;case 10: if ((mutable_bitField0_ & 0x1) == 0) { this.values_ = new ArrayList<>(); mutable_bitField0_ |= 0x1; }  this.values_.add(input.readMessage(Value.parser(), extensionRegistry)); continue; }  if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) done = true;  }  } catch (InvalidProtocolBufferException e) { throw e.setUnfinishedMessage(this); } catch (IOException e) { throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(this); } finally { if ((mutable_bitField0_ & 0x1) != 0) this.values_ = Collections.unmodifiableList(this.values_);  this.unknownFields = unknownFields.build(); makeExtensionsImmutable(); }  }
/*     */   public static final Descriptors.Descriptor getDescriptor() { return StructProto.internal_static_google_protobuf_ListValue_descriptor; }
/*     */   protected FieldAccessorTable internalGetFieldAccessorTable() { return StructProto.internal_static_google_protobuf_ListValue_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)ListValue.class, (Class)Builder.class); }
/* 165 */   public final boolean isInitialized() { byte isInitialized = this.memoizedIsInitialized;
/* 166 */     if (isInitialized == 1) return true; 
/* 167 */     if (isInitialized == 0) return false;
/*     */     
/* 169 */     this.memoizedIsInitialized = 1;
/* 170 */     return true; } public List<Value> getValuesList() { return this.values_; }
/*     */   public List<? extends ValueOrBuilder> getValuesOrBuilderList() { return (List)this.values_; }
/*     */   public int getValuesCount() { return this.values_.size(); }
/*     */   public Value getValues(int index) { return this.values_.get(index); }
/*     */   public ValueOrBuilder getValuesOrBuilder(int index) { return this.values_.get(index); }
/*     */   public void writeTo(CodedOutputStream output) throws IOException {
/* 176 */     for (int i = 0; i < this.values_.size(); i++) {
/* 177 */       output.writeMessage(1, this.values_.get(i));
/*     */     }
/* 179 */     this.unknownFields.writeTo(output);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSerializedSize() {
/* 184 */     int size = this.memoizedSize;
/* 185 */     if (size != -1) return size;
/*     */     
/* 187 */     size = 0;
/* 188 */     for (int i = 0; i < this.values_.size(); i++) {
/* 189 */       size += 
/* 190 */         CodedOutputStream.computeMessageSize(1, this.values_.get(i));
/*     */     }
/* 192 */     size += this.unknownFields.getSerializedSize();
/* 193 */     this.memoizedSize = size;
/* 194 */     return size;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 199 */     if (obj == this) {
/* 200 */       return true;
/*     */     }
/* 202 */     if (!(obj instanceof ListValue)) {
/* 203 */       return super.equals(obj);
/*     */     }
/* 205 */     ListValue other = (ListValue)obj;
/*     */ 
/*     */     
/* 208 */     if (!getValuesList().equals(other.getValuesList())) return false; 
/* 209 */     if (!this.unknownFields.equals(other.unknownFields)) return false; 
/* 210 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 215 */     if (this.memoizedHashCode != 0) {
/* 216 */       return this.memoizedHashCode;
/*     */     }
/* 218 */     int hash = 41;
/* 219 */     hash = 19 * hash + getDescriptor().hashCode();
/* 220 */     if (getValuesCount() > 0) {
/* 221 */       hash = 37 * hash + 1;
/* 222 */       hash = 53 * hash + getValuesList().hashCode();
/*     */     } 
/* 224 */     hash = 29 * hash + this.unknownFields.hashCode();
/* 225 */     this.memoizedHashCode = hash;
/* 226 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ListValue parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
/* 232 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ListValue parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 238 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public static ListValue parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 243 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ListValue parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 249 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static ListValue parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 253 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ListValue parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 259 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static ListValue parseFrom(InputStream input) throws IOException {
/* 263 */     return 
/* 264 */       GeneratedMessageV3.<ListValue>parseWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ListValue parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 270 */     return 
/* 271 */       GeneratedMessageV3.<ListValue>parseWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static ListValue parseDelimitedFrom(InputStream input) throws IOException {
/* 275 */     return 
/* 276 */       GeneratedMessageV3.<ListValue>parseDelimitedWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ListValue parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 282 */     return 
/* 283 */       GeneratedMessageV3.<ListValue>parseDelimitedWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public static ListValue parseFrom(CodedInputStream input) throws IOException {
/* 288 */     return 
/* 289 */       GeneratedMessageV3.<ListValue>parseWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ListValue parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 295 */     return 
/* 296 */       GeneratedMessageV3.<ListValue>parseWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */   
/*     */   public Builder newBuilderForType() {
/* 300 */     return newBuilder();
/*     */   } public static Builder newBuilder() {
/* 302 */     return DEFAULT_INSTANCE.toBuilder();
/*     */   }
/*     */   public static Builder newBuilder(ListValue prototype) {
/* 305 */     return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
/*     */   }
/*     */   
/*     */   public Builder toBuilder() {
/* 309 */     return (this == DEFAULT_INSTANCE) ? new Builder() : (new Builder())
/* 310 */       .mergeFrom(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Builder newBuilderForType(BuilderParent parent) {
/* 316 */     Builder builder = new Builder(parent);
/* 317 */     return builder;
/*     */   }
/*     */ 
/*     */   
/*     */   public static final class Builder
/*     */     extends GeneratedMessageV3.Builder<Builder>
/*     */     implements ListValueOrBuilder
/*     */   {
/*     */     private int bitField0_;
/*     */     
/*     */     private List<Value> values_;
/*     */     
/*     */     private RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> valuesBuilder_;
/*     */ 
/*     */     
/*     */     public static final Descriptors.Descriptor getDescriptor() {
/* 333 */       return StructProto.internal_static_google_protobuf_ListValue_descriptor;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected FieldAccessorTable internalGetFieldAccessorTable() {
/* 339 */       return StructProto.internal_static_google_protobuf_ListValue_fieldAccessorTable
/* 340 */         .ensureFieldAccessorsInitialized((Class)ListValue.class, (Class)Builder.class);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 509 */       this
/* 510 */         .values_ = Collections.emptyList(); maybeForceBuilderInitialization(); } private Builder(BuilderParent parent) { super(parent); this.values_ = Collections.emptyList(); maybeForceBuilderInitialization(); }
/*     */     private void maybeForceBuilderInitialization() { if (GeneratedMessageV3.alwaysUseFieldBuilders) getValuesFieldBuilder();  }
/* 512 */     public Builder clear() { super.clear(); if (this.valuesBuilder_ == null) { this.values_ = Collections.emptyList(); this.bitField0_ &= 0xFFFFFFFE; } else { this.valuesBuilder_.clear(); }  return this; } public Descriptors.Descriptor getDescriptorForType() { return StructProto.internal_static_google_protobuf_ListValue_descriptor; } public ListValue getDefaultInstanceForType() { return ListValue.getDefaultInstance(); } private void ensureValuesIsMutable() { if ((this.bitField0_ & 0x1) == 0) {
/* 513 */         this.values_ = new ArrayList<>(this.values_);
/* 514 */         this.bitField0_ |= 0x1;
/*     */       }  }
/*     */     public ListValue build() { ListValue result = buildPartial(); if (!result.isInitialized())
/*     */         throw newUninitializedMessageException(result);  return result; }
/*     */     public ListValue buildPartial() { ListValue result = new ListValue(this); int from_bitField0_ = this.bitField0_; if (this.valuesBuilder_ == null) { if ((this.bitField0_ & 0x1) != 0) {
/*     */           this.values_ = Collections.unmodifiableList(this.values_); this.bitField0_ &= 0xFFFFFFFE;
/*     */         }  result.values_ = this.values_; }
/*     */       else
/*     */       { result.values_ = this.valuesBuilder_.build(); }
/*     */        onBuilt(); return result; }
/*     */     public Builder clone() { return super.clone(); }
/*     */     public Builder setField(Descriptors.FieldDescriptor field, Object value) { return super.setField(field, value); }
/*     */     public Builder clearField(Descriptors.FieldDescriptor field) { return super.clearField(field); }
/*     */     public Builder clearOneof(Descriptors.OneofDescriptor oneof) { return super.clearOneof(oneof); }
/*     */     public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) { return super.setRepeatedField(field, index, value); }
/* 529 */     public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) { return super.addRepeatedField(field, value); } public List<Value> getValuesList() { if (this.valuesBuilder_ == null) {
/* 530 */         return Collections.unmodifiableList(this.values_);
/*     */       }
/* 532 */       return this.valuesBuilder_.getMessageList(); } public Builder mergeFrom(Message other) { if (other instanceof ListValue)
/*     */         return mergeFrom((ListValue)other);  super.mergeFrom(other); return this; }
/*     */     public Builder mergeFrom(ListValue other) { if (other == ListValue.getDefaultInstance())
/*     */         return this;  if (this.valuesBuilder_ == null) { if (!other.values_.isEmpty()) { if (this.values_.isEmpty()) { this.values_ = other.values_; this.bitField0_ &= 0xFFFFFFFE; } else { ensureValuesIsMutable(); this.values_.addAll(other.values_); }  onChanged(); }  } else if (!other.values_.isEmpty()) { if (this.valuesBuilder_.isEmpty()) { this.valuesBuilder_.dispose(); this.valuesBuilder_ = null; this.values_ = other.values_; this.bitField0_ &= 0xFFFFFFFE; this.valuesBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getValuesFieldBuilder() : null; } else { this.valuesBuilder_.addAllMessages(other.values_); }  }
/*     */        mergeUnknownFields(other.unknownFields); onChanged(); return this; }
/*     */     public final boolean isInitialized() { return true; }
/*     */     public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException { ListValue parsedMessage = null; try { parsedMessage = ListValue.PARSER.parsePartialFrom(input, extensionRegistry); }
/*     */       catch (InvalidProtocolBufferException e) { parsedMessage = (ListValue)e.getUnfinishedMessage(); throw e.unwrapIOException(); }
/*     */       finally { if (parsedMessage != null)
/*     */           mergeFrom(parsedMessage);  }
/*     */        return this; }
/* 543 */     public int getValuesCount() { if (this.valuesBuilder_ == null) {
/* 544 */         return this.values_.size();
/*     */       }
/* 546 */       return this.valuesBuilder_.getCount(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Value getValues(int index) {
/* 557 */       if (this.valuesBuilder_ == null) {
/* 558 */         return this.values_.get(index);
/*     */       }
/* 560 */       return this.valuesBuilder_.getMessage(index);
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
/*     */     public Builder setValues(int index, Value value) {
/* 572 */       if (this.valuesBuilder_ == null) {
/* 573 */         if (value == null) {
/* 574 */           throw new NullPointerException();
/*     */         }
/* 576 */         ensureValuesIsMutable();
/* 577 */         this.values_.set(index, value);
/* 578 */         onChanged();
/*     */       } else {
/* 580 */         this.valuesBuilder_.setMessage(index, value);
/*     */       } 
/* 582 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setValues(int index, Value.Builder builderForValue) {
/* 593 */       if (this.valuesBuilder_ == null) {
/* 594 */         ensureValuesIsMutable();
/* 595 */         this.values_.set(index, builderForValue.build());
/* 596 */         onChanged();
/*     */       } else {
/* 598 */         this.valuesBuilder_.setMessage(index, builderForValue.build());
/*     */       } 
/* 600 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder addValues(Value value) {
/* 610 */       if (this.valuesBuilder_ == null) {
/* 611 */         if (value == null) {
/* 612 */           throw new NullPointerException();
/*     */         }
/* 614 */         ensureValuesIsMutable();
/* 615 */         this.values_.add(value);
/* 616 */         onChanged();
/*     */       } else {
/* 618 */         this.valuesBuilder_.addMessage(value);
/*     */       } 
/* 620 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder addValues(int index, Value value) {
/* 631 */       if (this.valuesBuilder_ == null) {
/* 632 */         if (value == null) {
/* 633 */           throw new NullPointerException();
/*     */         }
/* 635 */         ensureValuesIsMutable();
/* 636 */         this.values_.add(index, value);
/* 637 */         onChanged();
/*     */       } else {
/* 639 */         this.valuesBuilder_.addMessage(index, value);
/*     */       } 
/* 641 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder addValues(Value.Builder builderForValue) {
/* 652 */       if (this.valuesBuilder_ == null) {
/* 653 */         ensureValuesIsMutable();
/* 654 */         this.values_.add(builderForValue.build());
/* 655 */         onChanged();
/*     */       } else {
/* 657 */         this.valuesBuilder_.addMessage(builderForValue.build());
/*     */       } 
/* 659 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder addValues(int index, Value.Builder builderForValue) {
/* 670 */       if (this.valuesBuilder_ == null) {
/* 671 */         ensureValuesIsMutable();
/* 672 */         this.values_.add(index, builderForValue.build());
/* 673 */         onChanged();
/*     */       } else {
/* 675 */         this.valuesBuilder_.addMessage(index, builderForValue.build());
/*     */       } 
/* 677 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder addAllValues(Iterable<? extends Value> values) {
/* 688 */       if (this.valuesBuilder_ == null) {
/* 689 */         ensureValuesIsMutable();
/* 690 */         AbstractMessageLite.Builder.addAll(values, this.values_);
/*     */         
/* 692 */         onChanged();
/*     */       } else {
/* 694 */         this.valuesBuilder_.addAllMessages(values);
/*     */       } 
/* 696 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder clearValues() {
/* 706 */       if (this.valuesBuilder_ == null) {
/* 707 */         this.values_ = Collections.emptyList();
/* 708 */         this.bitField0_ &= 0xFFFFFFFE;
/* 709 */         onChanged();
/*     */       } else {
/* 711 */         this.valuesBuilder_.clear();
/*     */       } 
/* 713 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder removeValues(int index) {
/* 723 */       if (this.valuesBuilder_ == null) {
/* 724 */         ensureValuesIsMutable();
/* 725 */         this.values_.remove(index);
/* 726 */         onChanged();
/*     */       } else {
/* 728 */         this.valuesBuilder_.remove(index);
/*     */       } 
/* 730 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Value.Builder getValuesBuilder(int index) {
/* 741 */       return getValuesFieldBuilder().getBuilder(index);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ValueOrBuilder getValuesOrBuilder(int index) {
/* 752 */       if (this.valuesBuilder_ == null)
/* 753 */         return this.values_.get(index); 
/* 754 */       return this.valuesBuilder_.getMessageOrBuilder(index);
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
/*     */     public List<? extends ValueOrBuilder> getValuesOrBuilderList() {
/* 766 */       if (this.valuesBuilder_ != null) {
/* 767 */         return this.valuesBuilder_.getMessageOrBuilderList();
/*     */       }
/* 769 */       return Collections.unmodifiableList((List)this.values_);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Value.Builder addValuesBuilder() {
/* 780 */       return getValuesFieldBuilder().addBuilder(
/* 781 */           Value.getDefaultInstance());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Value.Builder addValuesBuilder(int index) {
/* 792 */       return getValuesFieldBuilder().addBuilder(index, 
/* 793 */           Value.getDefaultInstance());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public List<Value.Builder> getValuesBuilderList() {
/* 804 */       return getValuesFieldBuilder().getBuilderList();
/*     */     }
/*     */ 
/*     */     
/*     */     private RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> getValuesFieldBuilder() {
/* 809 */       if (this.valuesBuilder_ == null) {
/* 810 */         this
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 815 */           .valuesBuilder_ = new RepeatedFieldBuilderV3<>(this.values_, ((this.bitField0_ & 0x1) != 0), getParentForChildren(), isClean());
/* 816 */         this.values_ = null;
/*     */       } 
/* 818 */       return this.valuesBuilder_;
/*     */     }
/*     */ 
/*     */     
/*     */     public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
/* 823 */       return super.setUnknownFields(unknownFields);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
/* 829 */       return super.mergeUnknownFields(unknownFields);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 839 */   private static final ListValue DEFAULT_INSTANCE = new ListValue();
/*     */ 
/*     */   
/*     */   public static ListValue getDefaultInstance() {
/* 843 */     return DEFAULT_INSTANCE;
/*     */   }
/*     */ 
/*     */   
/* 847 */   private static final Parser<ListValue> PARSER = new AbstractParser<ListValue>()
/*     */     {
/*     */ 
/*     */       
/*     */       public ListValue parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException
/*     */       {
/* 853 */         return new ListValue(input, extensionRegistry);
/*     */       }
/*     */     };
/*     */   
/*     */   public static Parser<ListValue> parser() {
/* 858 */     return PARSER;
/*     */   }
/*     */ 
/*     */   
/*     */   public Parser<ListValue> getParserForType() {
/* 863 */     return PARSER;
/*     */   }
/*     */ 
/*     */   
/*     */   public ListValue getDefaultInstanceForType() {
/* 868 */     return DEFAULT_INSTANCE;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\ListValue.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */