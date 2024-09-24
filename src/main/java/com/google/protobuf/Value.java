/*      */ package com.google.protobuf;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.nio.ByteBuffer;
/*      */ 
/*      */ 
/*      */ public final class Value
/*      */   extends GeneratedMessageV3
/*      */   implements ValueOrBuilder
/*      */ {
/*      */   private static final long serialVersionUID = 0L;
/*      */   private int kindCase_;
/*      */   private Object kind_;
/*      */   public static final int NULL_VALUE_FIELD_NUMBER = 1;
/*      */   public static final int NUMBER_VALUE_FIELD_NUMBER = 2;
/*      */   public static final int STRING_VALUE_FIELD_NUMBER = 3;
/*      */   public static final int BOOL_VALUE_FIELD_NUMBER = 4;
/*      */   public static final int STRUCT_VALUE_FIELD_NUMBER = 5;
/*      */   public static final int LIST_VALUE_FIELD_NUMBER = 6;
/*      */   private byte memoizedIsInitialized;
/*      */   
/*      */   private Value(GeneratedMessageV3.Builder<?> builder) {
/*   24 */     super(builder);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  141 */     this.kindCase_ = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  396 */     this.memoizedIsInitialized = -1; } protected Object newInstance(UnusedPrivateParameter unused) { return new Value(); } public final UnknownFieldSet getUnknownFields() { return this.unknownFields; } private Value(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException { this(); if (extensionRegistry == null) throw new NullPointerException();  UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(); try { boolean done = false; while (!done) { int rawValue; String s; Struct.Builder builder; ListValue.Builder subBuilder; int tag = input.readTag(); switch (tag) { case 0: done = true; continue;case 8: rawValue = input.readEnum(); this.kindCase_ = 1; this.kind_ = Integer.valueOf(rawValue); continue;case 17: this.kindCase_ = 2; this.kind_ = Double.valueOf(input.readDouble()); continue;case 26: s = input.readStringRequireUtf8(); this.kindCase_ = 3; this.kind_ = s; continue;case 32: this.kindCase_ = 4; this.kind_ = Boolean.valueOf(input.readBool()); continue;case 42: builder = null; if (this.kindCase_ == 5) builder = ((Struct)this.kind_).toBuilder();  this.kind_ = input.readMessage(Struct.parser(), extensionRegistry); if (builder != null) { builder.mergeFrom((Struct)this.kind_); this.kind_ = builder.buildPartial(); }  this.kindCase_ = 5; continue;case 50: subBuilder = null; if (this.kindCase_ == 6) subBuilder = ((ListValue)this.kind_).toBuilder();  this.kind_ = input.readMessage(ListValue.parser(), extensionRegistry); if (subBuilder != null) { subBuilder.mergeFrom((ListValue)this.kind_); this.kind_ = subBuilder.buildPartial(); }  this.kindCase_ = 6; continue; }  if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) done = true;  }  } catch (InvalidProtocolBufferException e) { throw e.setUnfinishedMessage(this); } catch (IOException e) { throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(this); } finally { this.unknownFields = unknownFields.build(); makeExtensionsImmutable(); }  } public static final Descriptors.Descriptor getDescriptor() { return StructProto.internal_static_google_protobuf_Value_descriptor; } protected FieldAccessorTable internalGetFieldAccessorTable() { return StructProto.internal_static_google_protobuf_Value_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)Value.class, (Class)Builder.class); } private Value() { this.kindCase_ = 0; this.memoizedIsInitialized = -1; }
/*      */   public enum KindCase implements Internal.EnumLite, InternalOneOfEnum {
/*      */     NULL_VALUE(1), NUMBER_VALUE(2), STRING_VALUE(3), BOOL_VALUE(4), STRUCT_VALUE(5), LIST_VALUE(6), KIND_NOT_SET(0);
/*  399 */     private final int value; KindCase(int value) { this.value = value; } public static KindCase forNumber(int value) { switch (value) { case 1: return NULL_VALUE;case 2: return NUMBER_VALUE;case 3: return STRING_VALUE;case 4: return BOOL_VALUE;case 5: return STRUCT_VALUE;case 6: return LIST_VALUE;case 0: return KIND_NOT_SET; }  return null; } public int getNumber() { return this.value; } } public KindCase getKindCase() { return KindCase.forNumber(this.kindCase_); } public int getNullValueValue() { if (this.kindCase_ == 1) return ((Integer)this.kind_).intValue();  return 0; } public NullValue getNullValue() { if (this.kindCase_ == 1) { NullValue result = NullValue.valueOf(((Integer)this.kind_).intValue()); return (result == null) ? NullValue.UNRECOGNIZED : result; }  return NullValue.NULL_VALUE; } public final boolean isInitialized() { byte isInitialized = this.memoizedIsInitialized;
/*  400 */     if (isInitialized == 1) return true; 
/*  401 */     if (isInitialized == 0) return false;
/*      */     
/*  403 */     this.memoizedIsInitialized = 1;
/*  404 */     return true; } public double getNumberValue() { if (this.kindCase_ == 2) return ((Double)this.kind_).doubleValue();  return 0.0D; } public String getStringValue() { Object ref = ""; if (this.kindCase_ == 3) ref = this.kind_;  if (ref instanceof String) return (String)ref;  ByteString bs = (ByteString)ref; String s = bs.toStringUtf8(); if (this.kindCase_ == 3) this.kind_ = s;  return s; } public ByteString getStringValueBytes() { Object ref = ""; if (this.kindCase_ == 3) ref = this.kind_;  if (ref instanceof String) { ByteString b = ByteString.copyFromUtf8((String)ref); if (this.kindCase_ == 3) this.kind_ = b;  return b; }  return (ByteString)ref; } public boolean getBoolValue() { if (this.kindCase_ == 4) return ((Boolean)this.kind_).booleanValue();  return false; } public boolean hasStructValue() { return (this.kindCase_ == 5); }
/*      */   public Struct getStructValue() { if (this.kindCase_ == 5) return (Struct)this.kind_;  return Struct.getDefaultInstance(); }
/*      */   public StructOrBuilder getStructValueOrBuilder() { if (this.kindCase_ == 5) return (Struct)this.kind_;  return Struct.getDefaultInstance(); }
/*      */   public boolean hasListValue() { return (this.kindCase_ == 6); }
/*      */   public ListValue getListValue() { if (this.kindCase_ == 6) return (ListValue)this.kind_;  return ListValue.getDefaultInstance(); }
/*      */   public ListValueOrBuilder getListValueOrBuilder() { if (this.kindCase_ == 6) return (ListValue)this.kind_;  return ListValue.getDefaultInstance(); }
/*  410 */   public void writeTo(CodedOutputStream output) throws IOException { if (this.kindCase_ == 1) {
/*  411 */       output.writeEnum(1, ((Integer)this.kind_).intValue());
/*      */     }
/*  413 */     if (this.kindCase_ == 2) {
/*  414 */       output.writeDouble(2, ((Double)this.kind_)
/*  415 */           .doubleValue());
/*      */     }
/*  417 */     if (this.kindCase_ == 3) {
/*  418 */       GeneratedMessageV3.writeString(output, 3, this.kind_);
/*      */     }
/*  420 */     if (this.kindCase_ == 4) {
/*  421 */       output.writeBool(4, ((Boolean)this.kind_)
/*  422 */           .booleanValue());
/*      */     }
/*  424 */     if (this.kindCase_ == 5) {
/*  425 */       output.writeMessage(5, (Struct)this.kind_);
/*      */     }
/*  427 */     if (this.kindCase_ == 6) {
/*  428 */       output.writeMessage(6, (ListValue)this.kind_);
/*      */     }
/*  430 */     this.unknownFields.writeTo(output); }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSerializedSize() {
/*  435 */     int size = this.memoizedSize;
/*  436 */     if (size != -1) return size;
/*      */     
/*  438 */     size = 0;
/*  439 */     if (this.kindCase_ == 1) {
/*  440 */       size += 
/*  441 */         CodedOutputStream.computeEnumSize(1, ((Integer)this.kind_).intValue());
/*      */     }
/*  443 */     if (this.kindCase_ == 2) {
/*  444 */       size += 
/*  445 */         CodedOutputStream.computeDoubleSize(2, ((Double)this.kind_)
/*  446 */           .doubleValue());
/*      */     }
/*  448 */     if (this.kindCase_ == 3) {
/*  449 */       size += GeneratedMessageV3.computeStringSize(3, this.kind_);
/*      */     }
/*  451 */     if (this.kindCase_ == 4) {
/*  452 */       size += 
/*  453 */         CodedOutputStream.computeBoolSize(4, ((Boolean)this.kind_)
/*  454 */           .booleanValue());
/*      */     }
/*  456 */     if (this.kindCase_ == 5) {
/*  457 */       size += 
/*  458 */         CodedOutputStream.computeMessageSize(5, (Struct)this.kind_);
/*      */     }
/*  460 */     if (this.kindCase_ == 6) {
/*  461 */       size += 
/*  462 */         CodedOutputStream.computeMessageSize(6, (ListValue)this.kind_);
/*      */     }
/*  464 */     size += this.unknownFields.getSerializedSize();
/*  465 */     this.memoizedSize = size;
/*  466 */     return size;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean equals(Object obj) {
/*  471 */     if (obj == this) {
/*  472 */       return true;
/*      */     }
/*  474 */     if (!(obj instanceof Value)) {
/*  475 */       return super.equals(obj);
/*      */     }
/*  477 */     Value other = (Value)obj;
/*      */     
/*  479 */     if (!getKindCase().equals(other.getKindCase())) return false; 
/*  480 */     switch (this.kindCase_) {
/*      */       case 1:
/*  482 */         if (getNullValueValue() != other
/*  483 */           .getNullValueValue()) return false; 
/*      */         break;
/*      */       case 2:
/*  486 */         if (Double.doubleToLongBits(getNumberValue()) != 
/*  487 */           Double.doubleToLongBits(other
/*  488 */             .getNumberValue())) return false;
/*      */         
/*      */         break;
/*      */       case 3:
/*  492 */         if (!getStringValue().equals(other.getStringValue())) return false; 
/*      */         break;
/*      */       case 4:
/*  495 */         if (getBoolValue() != other
/*  496 */           .getBoolValue()) return false;
/*      */         
/*      */         break;
/*      */       case 5:
/*  500 */         if (!getStructValue().equals(other.getStructValue())) return false;
/*      */         
/*      */         break;
/*      */       case 6:
/*  504 */         if (!getListValue().equals(other.getListValue())) return false;
/*      */         
/*      */         break;
/*      */     } 
/*      */     
/*  509 */     if (!this.unknownFields.equals(other.unknownFields)) return false; 
/*  510 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public int hashCode() {
/*  515 */     if (this.memoizedHashCode != 0) {
/*  516 */       return this.memoizedHashCode;
/*      */     }
/*  518 */     int hash = 41;
/*  519 */     hash = 19 * hash + getDescriptor().hashCode();
/*  520 */     switch (this.kindCase_) {
/*      */       case 1:
/*  522 */         hash = 37 * hash + 1;
/*  523 */         hash = 53 * hash + getNullValueValue();
/*      */         break;
/*      */       case 2:
/*  526 */         hash = 37 * hash + 2;
/*  527 */         hash = 53 * hash + Internal.hashLong(
/*  528 */             Double.doubleToLongBits(getNumberValue()));
/*      */         break;
/*      */       case 3:
/*  531 */         hash = 37 * hash + 3;
/*  532 */         hash = 53 * hash + getStringValue().hashCode();
/*      */         break;
/*      */       case 4:
/*  535 */         hash = 37 * hash + 4;
/*  536 */         hash = 53 * hash + Internal.hashBoolean(
/*  537 */             getBoolValue());
/*      */         break;
/*      */       case 5:
/*  540 */         hash = 37 * hash + 5;
/*  541 */         hash = 53 * hash + getStructValue().hashCode();
/*      */         break;
/*      */       case 6:
/*  544 */         hash = 37 * hash + 6;
/*  545 */         hash = 53 * hash + getListValue().hashCode();
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/*  550 */     hash = 29 * hash + this.unknownFields.hashCode();
/*  551 */     this.memoizedHashCode = hash;
/*  552 */     return hash;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Value parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
/*  558 */     return PARSER.parseFrom(data);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Value parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  564 */     return PARSER.parseFrom(data, extensionRegistry);
/*      */   }
/*      */ 
/*      */   
/*      */   public static Value parseFrom(ByteString data) throws InvalidProtocolBufferException {
/*  569 */     return PARSER.parseFrom(data);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Value parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  575 */     return PARSER.parseFrom(data, extensionRegistry);
/*      */   }
/*      */   
/*      */   public static Value parseFrom(byte[] data) throws InvalidProtocolBufferException {
/*  579 */     return PARSER.parseFrom(data);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Value parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  585 */     return PARSER.parseFrom(data, extensionRegistry);
/*      */   }
/*      */   
/*      */   public static Value parseFrom(InputStream input) throws IOException {
/*  589 */     return 
/*  590 */       GeneratedMessageV3.<Value>parseWithIOException(PARSER, input);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Value parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  596 */     return 
/*  597 */       GeneratedMessageV3.<Value>parseWithIOException(PARSER, input, extensionRegistry);
/*      */   }
/*      */   
/*      */   public static Value parseDelimitedFrom(InputStream input) throws IOException {
/*  601 */     return 
/*  602 */       GeneratedMessageV3.<Value>parseDelimitedWithIOException(PARSER, input);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Value parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  608 */     return 
/*  609 */       GeneratedMessageV3.<Value>parseDelimitedWithIOException(PARSER, input, extensionRegistry);
/*      */   }
/*      */ 
/*      */   
/*      */   public static Value parseFrom(CodedInputStream input) throws IOException {
/*  614 */     return 
/*  615 */       GeneratedMessageV3.<Value>parseWithIOException(PARSER, input);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Value parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  621 */     return 
/*  622 */       GeneratedMessageV3.<Value>parseWithIOException(PARSER, input, extensionRegistry);
/*      */   }
/*      */   
/*      */   public Builder newBuilderForType() {
/*  626 */     return newBuilder();
/*      */   } public static Builder newBuilder() {
/*  628 */     return DEFAULT_INSTANCE.toBuilder();
/*      */   }
/*      */   public static Builder newBuilder(Value prototype) {
/*  631 */     return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
/*      */   }
/*      */   
/*      */   public Builder toBuilder() {
/*  635 */     return (this == DEFAULT_INSTANCE) ? new Builder() : (new Builder())
/*  636 */       .mergeFrom(this);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected Builder newBuilderForType(BuilderParent parent) {
/*  642 */     Builder builder = new Builder(parent);
/*  643 */     return builder;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static final class Builder
/*      */     extends GeneratedMessageV3.Builder<Builder>
/*      */     implements ValueOrBuilder
/*      */   {
/*      */     private int kindCase_;
/*      */     
/*      */     private Object kind_;
/*      */     
/*      */     private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> structValueBuilder_;
/*      */     
/*      */     private SingleFieldBuilderV3<ListValue, ListValue.Builder, ListValueOrBuilder> listValueBuilder_;
/*      */ 
/*      */     
/*      */     public static final Descriptors.Descriptor getDescriptor() {
/*  662 */       return StructProto.internal_static_google_protobuf_Value_descriptor;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected FieldAccessorTable internalGetFieldAccessorTable() {
/*  668 */       return StructProto.internal_static_google_protobuf_Value_fieldAccessorTable
/*  669 */         .ensureFieldAccessorsInitialized((Class)Value.class, (Class)Builder.class);
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
/*      */ 
/*      */     
/*      */     private Builder()
/*      */     {
/*  853 */       this.kindCase_ = 0; maybeForceBuilderInitialization(); } private Builder(BuilderParent parent) { super(parent); this.kindCase_ = 0; maybeForceBuilderInitialization(); } private void maybeForceBuilderInitialization() { if (GeneratedMessageV3.alwaysUseFieldBuilders); } public Builder clear() { super.clear(); this.kindCase_ = 0; this.kind_ = null; return this; } public Descriptors.Descriptor getDescriptorForType() { return StructProto.internal_static_google_protobuf_Value_descriptor; } public Value getDefaultInstanceForType() { return Value.getDefaultInstance(); } public Value build() { Value result = buildPartial(); if (!result.isInitialized()) throw newUninitializedMessageException(result);  return result; } public Value buildPartial() { Value result = new Value(this); if (this.kindCase_ == 1) result.kind_ = this.kind_;  if (this.kindCase_ == 2) result.kind_ = this.kind_;  if (this.kindCase_ == 3) result.kind_ = this.kind_;  if (this.kindCase_ == 4) result.kind_ = this.kind_;  if (this.kindCase_ == 5) if (this.structValueBuilder_ == null) { result.kind_ = this.kind_; } else { result.kind_ = this.structValueBuilder_.build(); }   if (this.kindCase_ == 6) if (this.listValueBuilder_ == null) { result.kind_ = this.kind_; } else { result.kind_ = this.listValueBuilder_.build(); }   result.kindCase_ = this.kindCase_; onBuilt(); return result; } public Builder clone() { return super.clone(); } public Builder setField(Descriptors.FieldDescriptor field, Object value) { return super.setField(field, value); } public Builder clearField(Descriptors.FieldDescriptor field) { return super.clearField(field); } public Builder clearOneof(Descriptors.OneofDescriptor oneof) { return super.clearOneof(oneof); } public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) { return super.setRepeatedField(field, index, value); } public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) { return super.addRepeatedField(field, value); } public Builder mergeFrom(Message other) { if (other instanceof Value) return mergeFrom((Value)other);  super.mergeFrom(other); return this; }
/*      */     public Builder mergeFrom(Value other) { if (other == Value.getDefaultInstance()) return this;  switch (other.getKindCase()) { case NULL_VALUE: setNullValueValue(other.getNullValueValue()); break;case NUMBER_VALUE: setNumberValue(other.getNumberValue()); break;case STRING_VALUE: this.kindCase_ = 3; this.kind_ = other.kind_; onChanged(); break;case BOOL_VALUE: setBoolValue(other.getBoolValue()); break;case STRUCT_VALUE: mergeStructValue(other.getStructValue()); break;case LIST_VALUE: mergeListValue(other.getListValue()); break; }  mergeUnknownFields(other.unknownFields); onChanged(); return this; }
/*      */     public final boolean isInitialized() { return true; }
/*      */     public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException { Value parsedMessage = null; try { parsedMessage = Value.PARSER.parsePartialFrom(input, extensionRegistry); } catch (InvalidProtocolBufferException e) { parsedMessage = (Value)e.getUnfinishedMessage(); throw e.unwrapIOException(); } finally { if (parsedMessage != null) mergeFrom(parsedMessage);  }  return this; }
/*  857 */     public KindCase getKindCase() { return KindCase.forNumber(this.kindCase_); }
/*      */ 
/*      */ 
/*      */     
/*      */     public Builder clearKind() {
/*  862 */       this.kindCase_ = 0;
/*  863 */       this.kind_ = null;
/*  864 */       onChanged();
/*  865 */       return this;
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
/*      */     public int getNullValueValue() {
/*  879 */       if (this.kindCase_ == 1) {
/*  880 */         return ((Integer)this.kind_).intValue();
/*      */       }
/*  882 */       return 0;
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
/*      */     public Builder setNullValueValue(int value) {
/*  894 */       this.kindCase_ = 1;
/*  895 */       this.kind_ = Integer.valueOf(value);
/*  896 */       onChanged();
/*  897 */       return this;
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
/*      */     public NullValue getNullValue() {
/*  909 */       if (this.kindCase_ == 1) {
/*      */         
/*  911 */         NullValue result = NullValue.valueOf(((Integer)this.kind_)
/*  912 */             .intValue());
/*  913 */         return (result == null) ? NullValue.UNRECOGNIZED : result;
/*      */       } 
/*  915 */       return NullValue.NULL_VALUE;
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
/*      */     public Builder setNullValue(NullValue value) {
/*  927 */       if (value == null) {
/*  928 */         throw new NullPointerException();
/*      */       }
/*  930 */       this.kindCase_ = 1;
/*  931 */       this.kind_ = Integer.valueOf(value.getNumber());
/*  932 */       onChanged();
/*  933 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Builder clearNullValue() {
/*  944 */       if (this.kindCase_ == 1) {
/*  945 */         this.kindCase_ = 0;
/*  946 */         this.kind_ = null;
/*  947 */         onChanged();
/*      */       } 
/*  949 */       return this;
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
/*      */     public double getNumberValue() {
/*  961 */       if (this.kindCase_ == 2) {
/*  962 */         return ((Double)this.kind_).doubleValue();
/*      */       }
/*  964 */       return 0.0D;
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
/*      */     public Builder setNumberValue(double value) {
/*  976 */       this.kindCase_ = 2;
/*  977 */       this.kind_ = Double.valueOf(value);
/*  978 */       onChanged();
/*  979 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Builder clearNumberValue() {
/*  990 */       if (this.kindCase_ == 2) {
/*  991 */         this.kindCase_ = 0;
/*  992 */         this.kind_ = null;
/*  993 */         onChanged();
/*      */       } 
/*  995 */       return this;
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
/*      */     public String getStringValue() {
/* 1008 */       Object ref = "";
/* 1009 */       if (this.kindCase_ == 3) {
/* 1010 */         ref = this.kind_;
/*      */       }
/* 1012 */       if (!(ref instanceof String)) {
/* 1013 */         ByteString bs = (ByteString)ref;
/*      */         
/* 1015 */         String s = bs.toStringUtf8();
/* 1016 */         if (this.kindCase_ == 3) {
/* 1017 */           this.kind_ = s;
/*      */         }
/* 1019 */         return s;
/*      */       } 
/* 1021 */       return (String)ref;
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
/*      */     public ByteString getStringValueBytes() {
/* 1035 */       Object ref = "";
/* 1036 */       if (this.kindCase_ == 3) {
/* 1037 */         ref = this.kind_;
/*      */       }
/* 1039 */       if (ref instanceof String) {
/*      */         
/* 1041 */         ByteString b = ByteString.copyFromUtf8((String)ref);
/*      */         
/* 1043 */         if (this.kindCase_ == 3) {
/* 1044 */           this.kind_ = b;
/*      */         }
/* 1046 */         return b;
/*      */       } 
/* 1048 */       return (ByteString)ref;
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
/*      */     public Builder setStringValue(String value) {
/* 1062 */       if (value == null) {
/* 1063 */         throw new NullPointerException();
/*      */       }
/* 1065 */       this.kindCase_ = 3;
/* 1066 */       this.kind_ = value;
/* 1067 */       onChanged();
/* 1068 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Builder clearStringValue() {
/* 1079 */       if (this.kindCase_ == 3) {
/* 1080 */         this.kindCase_ = 0;
/* 1081 */         this.kind_ = null;
/* 1082 */         onChanged();
/*      */       } 
/* 1084 */       return this;
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
/*      */     public Builder setStringValueBytes(ByteString value) {
/* 1097 */       if (value == null) {
/* 1098 */         throw new NullPointerException();
/*      */       }
/* 1100 */       AbstractMessageLite.checkByteStringIsUtf8(value);
/* 1101 */       this.kindCase_ = 3;
/* 1102 */       this.kind_ = value;
/* 1103 */       onChanged();
/* 1104 */       return this;
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
/*      */     public boolean getBoolValue() {
/* 1116 */       if (this.kindCase_ == 4) {
/* 1117 */         return ((Boolean)this.kind_).booleanValue();
/*      */       }
/* 1119 */       return false;
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
/*      */     public Builder setBoolValue(boolean value) {
/* 1131 */       this.kindCase_ = 4;
/* 1132 */       this.kind_ = Boolean.valueOf(value);
/* 1133 */       onChanged();
/* 1134 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Builder clearBoolValue() {
/* 1145 */       if (this.kindCase_ == 4) {
/* 1146 */         this.kindCase_ = 0;
/* 1147 */         this.kind_ = null;
/* 1148 */         onChanged();
/*      */       } 
/* 1150 */       return this;
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
/*      */     public boolean hasStructValue() {
/* 1165 */       return (this.kindCase_ == 5);
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
/*      */     public Struct getStructValue() {
/* 1177 */       if (this.structValueBuilder_ == null) {
/* 1178 */         if (this.kindCase_ == 5) {
/* 1179 */           return (Struct)this.kind_;
/*      */         }
/* 1181 */         return Struct.getDefaultInstance();
/*      */       } 
/* 1183 */       if (this.kindCase_ == 5) {
/* 1184 */         return this.structValueBuilder_.getMessage();
/*      */       }
/* 1186 */       return Struct.getDefaultInstance();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Builder setStructValue(Struct value) {
/* 1197 */       if (this.structValueBuilder_ == null) {
/* 1198 */         if (value == null) {
/* 1199 */           throw new NullPointerException();
/*      */         }
/* 1201 */         this.kind_ = value;
/* 1202 */         onChanged();
/*      */       } else {
/* 1204 */         this.structValueBuilder_.setMessage(value);
/*      */       } 
/* 1206 */       this.kindCase_ = 5;
/* 1207 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Builder setStructValue(Struct.Builder builderForValue) {
/* 1218 */       if (this.structValueBuilder_ == null) {
/* 1219 */         this.kind_ = builderForValue.build();
/* 1220 */         onChanged();
/*      */       } else {
/* 1222 */         this.structValueBuilder_.setMessage(builderForValue.build());
/*      */       } 
/* 1224 */       this.kindCase_ = 5;
/* 1225 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Builder mergeStructValue(Struct value) {
/* 1235 */       if (this.structValueBuilder_ == null) {
/* 1236 */         if (this.kindCase_ == 5 && this.kind_ != 
/* 1237 */           Struct.getDefaultInstance()) {
/* 1238 */           this
/* 1239 */             .kind_ = Struct.newBuilder((Struct)this.kind_).mergeFrom(value).buildPartial();
/*      */         } else {
/* 1241 */           this.kind_ = value;
/*      */         } 
/* 1243 */         onChanged();
/*      */       } else {
/* 1245 */         if (this.kindCase_ == 5) {
/* 1246 */           this.structValueBuilder_.mergeFrom(value);
/*      */         }
/* 1248 */         this.structValueBuilder_.setMessage(value);
/*      */       } 
/* 1250 */       this.kindCase_ = 5;
/* 1251 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Builder clearStructValue() {
/* 1261 */       if (this.structValueBuilder_ == null) {
/* 1262 */         if (this.kindCase_ == 5) {
/* 1263 */           this.kindCase_ = 0;
/* 1264 */           this.kind_ = null;
/* 1265 */           onChanged();
/*      */         } 
/*      */       } else {
/* 1268 */         if (this.kindCase_ == 5) {
/* 1269 */           this.kindCase_ = 0;
/* 1270 */           this.kind_ = null;
/*      */         } 
/* 1272 */         this.structValueBuilder_.clear();
/*      */       } 
/* 1274 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Struct.Builder getStructValueBuilder() {
/* 1284 */       return getStructValueFieldBuilder().getBuilder();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public StructOrBuilder getStructValueOrBuilder() {
/* 1295 */       if (this.kindCase_ == 5 && this.structValueBuilder_ != null) {
/* 1296 */         return this.structValueBuilder_.getMessageOrBuilder();
/*      */       }
/* 1298 */       if (this.kindCase_ == 5) {
/* 1299 */         return (Struct)this.kind_;
/*      */       }
/* 1301 */       return Struct.getDefaultInstance();
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
/*      */     private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> getStructValueFieldBuilder() {
/* 1314 */       if (this.structValueBuilder_ == null) {
/* 1315 */         if (this.kindCase_ != 5) {
/* 1316 */           this.kind_ = Struct.getDefaultInstance();
/*      */         }
/* 1318 */         this
/*      */ 
/*      */ 
/*      */           
/* 1322 */           .structValueBuilder_ = new SingleFieldBuilderV3<>((Struct)this.kind_, getParentForChildren(), isClean());
/* 1323 */         this.kind_ = null;
/*      */       } 
/* 1325 */       this.kindCase_ = 5;
/* 1326 */       onChanged();
/* 1327 */       return this.structValueBuilder_;
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
/*      */     public boolean hasListValue() {
/* 1342 */       return (this.kindCase_ == 6);
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
/*      */     public ListValue getListValue() {
/* 1354 */       if (this.listValueBuilder_ == null) {
/* 1355 */         if (this.kindCase_ == 6) {
/* 1356 */           return (ListValue)this.kind_;
/*      */         }
/* 1358 */         return ListValue.getDefaultInstance();
/*      */       } 
/* 1360 */       if (this.kindCase_ == 6) {
/* 1361 */         return this.listValueBuilder_.getMessage();
/*      */       }
/* 1363 */       return ListValue.getDefaultInstance();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Builder setListValue(ListValue value) {
/* 1374 */       if (this.listValueBuilder_ == null) {
/* 1375 */         if (value == null) {
/* 1376 */           throw new NullPointerException();
/*      */         }
/* 1378 */         this.kind_ = value;
/* 1379 */         onChanged();
/*      */       } else {
/* 1381 */         this.listValueBuilder_.setMessage(value);
/*      */       } 
/* 1383 */       this.kindCase_ = 6;
/* 1384 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Builder setListValue(ListValue.Builder builderForValue) {
/* 1395 */       if (this.listValueBuilder_ == null) {
/* 1396 */         this.kind_ = builderForValue.build();
/* 1397 */         onChanged();
/*      */       } else {
/* 1399 */         this.listValueBuilder_.setMessage(builderForValue.build());
/*      */       } 
/* 1401 */       this.kindCase_ = 6;
/* 1402 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Builder mergeListValue(ListValue value) {
/* 1412 */       if (this.listValueBuilder_ == null) {
/* 1413 */         if (this.kindCase_ == 6 && this.kind_ != 
/* 1414 */           ListValue.getDefaultInstance()) {
/* 1415 */           this
/* 1416 */             .kind_ = ListValue.newBuilder((ListValue)this.kind_).mergeFrom(value).buildPartial();
/*      */         } else {
/* 1418 */           this.kind_ = value;
/*      */         } 
/* 1420 */         onChanged();
/*      */       } else {
/* 1422 */         if (this.kindCase_ == 6) {
/* 1423 */           this.listValueBuilder_.mergeFrom(value);
/*      */         }
/* 1425 */         this.listValueBuilder_.setMessage(value);
/*      */       } 
/* 1427 */       this.kindCase_ = 6;
/* 1428 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Builder clearListValue() {
/* 1438 */       if (this.listValueBuilder_ == null) {
/* 1439 */         if (this.kindCase_ == 6) {
/* 1440 */           this.kindCase_ = 0;
/* 1441 */           this.kind_ = null;
/* 1442 */           onChanged();
/*      */         } 
/*      */       } else {
/* 1445 */         if (this.kindCase_ == 6) {
/* 1446 */           this.kindCase_ = 0;
/* 1447 */           this.kind_ = null;
/*      */         } 
/* 1449 */         this.listValueBuilder_.clear();
/*      */       } 
/* 1451 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ListValue.Builder getListValueBuilder() {
/* 1461 */       return getListValueFieldBuilder().getBuilder();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ListValueOrBuilder getListValueOrBuilder() {
/* 1472 */       if (this.kindCase_ == 6 && this.listValueBuilder_ != null) {
/* 1473 */         return this.listValueBuilder_.getMessageOrBuilder();
/*      */       }
/* 1475 */       if (this.kindCase_ == 6) {
/* 1476 */         return (ListValue)this.kind_;
/*      */       }
/* 1478 */       return ListValue.getDefaultInstance();
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
/*      */     private SingleFieldBuilderV3<ListValue, ListValue.Builder, ListValueOrBuilder> getListValueFieldBuilder() {
/* 1491 */       if (this.listValueBuilder_ == null) {
/* 1492 */         if (this.kindCase_ != 6) {
/* 1493 */           this.kind_ = ListValue.getDefaultInstance();
/*      */         }
/* 1495 */         this
/*      */ 
/*      */ 
/*      */           
/* 1499 */           .listValueBuilder_ = new SingleFieldBuilderV3<>((ListValue)this.kind_, getParentForChildren(), isClean());
/* 1500 */         this.kind_ = null;
/*      */       } 
/* 1502 */       this.kindCase_ = 6;
/* 1503 */       onChanged();
/* 1504 */       return this.listValueBuilder_;
/*      */     }
/*      */ 
/*      */     
/*      */     public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
/* 1509 */       return super.setUnknownFields(unknownFields);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
/* 1515 */       return super.mergeUnknownFields(unknownFields);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1525 */   private static final Value DEFAULT_INSTANCE = new Value();
/*      */ 
/*      */   
/*      */   public static Value getDefaultInstance() {
/* 1529 */     return DEFAULT_INSTANCE;
/*      */   }
/*      */ 
/*      */   
/* 1533 */   private static final Parser<Value> PARSER = new AbstractParser<Value>()
/*      */     {
/*      */ 
/*      */       
/*      */       public Value parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException
/*      */       {
/* 1539 */         return new Value(input, extensionRegistry);
/*      */       }
/*      */     };
/*      */   
/*      */   public static Parser<Value> parser() {
/* 1544 */     return PARSER;
/*      */   }
/*      */ 
/*      */   
/*      */   public Parser<Value> getParserForType() {
/* 1549 */     return PARSER;
/*      */   }
/*      */ 
/*      */   
/*      */   public Value getDefaultInstanceForType() {
/* 1554 */     return DEFAULT_INSTANCE;
/*      */   }
/*      */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\Value.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */