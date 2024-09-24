/*      */ package com.google.protobuf;public final class Field extends GeneratedMessageV3 implements FieldOrBuilder { private static final long serialVersionUID = 0L; public static final int KIND_FIELD_NUMBER = 1; private int kind_; public static final int CARDINALITY_FIELD_NUMBER = 2; private int cardinality_;
/*      */   public static final int NUMBER_FIELD_NUMBER = 3;
/*      */   private int number_;
/*      */   public static final int NAME_FIELD_NUMBER = 4;
/*      */   private volatile Object name_;
/*      */   public static final int TYPE_URL_FIELD_NUMBER = 6;
/*      */   private volatile Object typeUrl_;
/*      */   public static final int ONEOF_INDEX_FIELD_NUMBER = 7;
/*      */   private int oneofIndex_;
/*      */   public static final int PACKED_FIELD_NUMBER = 8;
/*      */   private boolean packed_;
/*      */   public static final int OPTIONS_FIELD_NUMBER = 9;
/*      */   private List<Option> options_;
/*      */   public static final int JSON_NAME_FIELD_NUMBER = 10;
/*      */   private volatile Object jsonName_;
/*      */   public static final int DEFAULT_VALUE_FIELD_NUMBER = 11;
/*      */   private volatile Object defaultValue_;
/*      */   private byte memoizedIsInitialized;
/*      */   
/*   20 */   private Field(GeneratedMessageV3.Builder<?> builder) { super(builder);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1083 */     this.memoizedIsInitialized = -1; } protected Object newInstance(UnusedPrivateParameter unused) { return new Field(); } public final UnknownFieldSet getUnknownFields() { return this.unknownFields; } private Field(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException { this(); if (extensionRegistry == null) throw new NullPointerException();  int mutable_bitField0_ = 0; UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(); try { boolean done = false; while (!done) { int rawValue; String s; int tag = input.readTag(); switch (tag) { case 0: done = true; continue;case 8: rawValue = input.readEnum(); this.kind_ = rawValue; continue;case 16: rawValue = input.readEnum(); this.cardinality_ = rawValue; continue;case 24: this.number_ = input.readInt32(); continue;case 34: s = input.readStringRequireUtf8(); this.name_ = s; continue;case 50: s = input.readStringRequireUtf8(); this.typeUrl_ = s; continue;case 56: this.oneofIndex_ = input.readInt32(); continue;case 64: this.packed_ = input.readBool(); continue;case 74: if ((mutable_bitField0_ & 0x1) == 0) { this.options_ = new ArrayList<>(); mutable_bitField0_ |= 0x1; }  this.options_.add(input.readMessage(Option.parser(), extensionRegistry)); continue;case 82: s = input.readStringRequireUtf8(); this.jsonName_ = s; continue;case 90: s = input.readStringRequireUtf8(); this.defaultValue_ = s; continue; }  if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) done = true;  }  } catch (InvalidProtocolBufferException e) { throw e.setUnfinishedMessage(this); } catch (IOException e) { throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(this); } finally { if ((mutable_bitField0_ & 0x1) != 0) this.options_ = Collections.unmodifiableList(this.options_);  this.unknownFields = unknownFields.build(); makeExtensionsImmutable(); }  } public static final Descriptors.Descriptor getDescriptor() { return TypeProto.internal_static_google_protobuf_Field_descriptor; } protected FieldAccessorTable internalGetFieldAccessorTable() { return TypeProto.internal_static_google_protobuf_Field_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)Field.class, (Class)Builder.class); } public enum Kind implements ProtocolMessageEnum { TYPE_UNKNOWN(0), TYPE_DOUBLE(1), TYPE_FLOAT(2), TYPE_INT64(3), TYPE_UINT64(4), TYPE_INT32(5), TYPE_FIXED64(6), TYPE_FIXED32(7), TYPE_BOOL(8), TYPE_STRING(9), TYPE_GROUP(10), TYPE_MESSAGE(11), TYPE_BYTES(12), TYPE_UINT32(13), TYPE_ENUM(14), TYPE_SFIXED32(15), TYPE_SFIXED64(16), TYPE_SINT32(17), TYPE_SINT64(18), UNRECOGNIZED(-1); public static final int TYPE_UNKNOWN_VALUE = 0; public static final int TYPE_DOUBLE_VALUE = 1; public static final int TYPE_FLOAT_VALUE = 2; public static final int TYPE_INT64_VALUE = 3; public static final int TYPE_UINT64_VALUE = 4; public static final int TYPE_INT32_VALUE = 5; public static final int TYPE_FIXED64_VALUE = 6; public static final int TYPE_FIXED32_VALUE = 7; public static final int TYPE_BOOL_VALUE = 8; public static final int TYPE_STRING_VALUE = 9; public static final int TYPE_GROUP_VALUE = 10; public static final int TYPE_MESSAGE_VALUE = 11; public static final int TYPE_BYTES_VALUE = 12; public static final int TYPE_UINT32_VALUE = 13; public static final int TYPE_ENUM_VALUE = 14; public static final int TYPE_SFIXED32_VALUE = 15; public static final int TYPE_SFIXED64_VALUE = 16; public static final int TYPE_SINT32_VALUE = 17; public static final int TYPE_SINT64_VALUE = 18; private static final Internal.EnumLiteMap<Kind> internalValueMap = new Internal.EnumLiteMap<Kind>() { public Kind findValueByNumber(int number) { return Kind.forNumber(number); } }; private static final Kind[] VALUES = values(); private final int value; public final int getNumber() { if (this == UNRECOGNIZED) throw new IllegalArgumentException("Can't get the number of an unknown enum value.");  return this.value; } public static Kind forNumber(int value) { switch (value) { case 0: return TYPE_UNKNOWN;case 1: return TYPE_DOUBLE;case 2: return TYPE_FLOAT;case 3: return TYPE_INT64;case 4: return TYPE_UINT64;case 5: return TYPE_INT32;case 6: return TYPE_FIXED64;case 7: return TYPE_FIXED32;case 8: return TYPE_BOOL;case 9: return TYPE_STRING;case 10: return TYPE_GROUP;case 11: return TYPE_MESSAGE;case 12: return TYPE_BYTES;case 13: return TYPE_UINT32;case 14: return TYPE_ENUM;case 15: return TYPE_SFIXED32;case 16: return TYPE_SFIXED64;case 17: return TYPE_SINT32;case 18: return TYPE_SINT64; }  return null; } public static Internal.EnumLiteMap<Kind> internalGetValueMap() { return internalValueMap; } static {  } public final Descriptors.EnumValueDescriptor getValueDescriptor() { if (this == UNRECOGNIZED) throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");  return getDescriptor().getValues().get(ordinal()); } public final Descriptors.EnumDescriptor getDescriptorForType() { return getDescriptor(); } public static final Descriptors.EnumDescriptor getDescriptor() { return Field.getDescriptor().getEnumTypes().get(0); } Kind(int value) { this.value = value; } } public enum Cardinality implements ProtocolMessageEnum { CARDINALITY_UNKNOWN(0), CARDINALITY_OPTIONAL(1), CARDINALITY_REQUIRED(2), CARDINALITY_REPEATED(3), UNRECOGNIZED(-1); public static final int CARDINALITY_UNKNOWN_VALUE = 0; public static final int CARDINALITY_OPTIONAL_VALUE = 1; public static final int CARDINALITY_REQUIRED_VALUE = 2; public static final int CARDINALITY_REPEATED_VALUE = 3; private static final Internal.EnumLiteMap<Cardinality> internalValueMap = new Internal.EnumLiteMap<Cardinality>() { public Cardinality findValueByNumber(int number) { return Cardinality.forNumber(number); } }; private static final Cardinality[] VALUES = values(); private final int value; public final int getNumber() { if (this == UNRECOGNIZED) throw new IllegalArgumentException("Can't get the number of an unknown enum value.");  return this.value; } public static Cardinality forNumber(int value) { switch (value) { case 0: return CARDINALITY_UNKNOWN;case 1: return CARDINALITY_OPTIONAL;case 2: return CARDINALITY_REQUIRED;case 3: return CARDINALITY_REPEATED; }  return null; } public static Internal.EnumLiteMap<Cardinality> internalGetValueMap() { return internalValueMap; } static {  } public final Descriptors.EnumValueDescriptor getValueDescriptor() { if (this == UNRECOGNIZED) throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");  return getDescriptor().getValues().get(ordinal()); } public final Descriptors.EnumDescriptor getDescriptorForType() { return getDescriptor(); } public static final Descriptors.EnumDescriptor getDescriptor() { return Field.getDescriptor().getEnumTypes().get(1); } Cardinality(int value) { this.value = value; } } private Field() { this.memoizedIsInitialized = -1; this.kind_ = 0; this.cardinality_ = 0; this.name_ = ""; this.typeUrl_ = ""; this.options_ = Collections.emptyList(); this.jsonName_ = ""; this.defaultValue_ = ""; }
/*      */   public int getKindValue() { return this.kind_; }
/*      */   public Kind getKind() { Kind result = Kind.valueOf(this.kind_); return (result == null) ? Kind.UNRECOGNIZED : result; }
/* 1086 */   public int getCardinalityValue() { return this.cardinality_; } public Cardinality getCardinality() { Cardinality result = Cardinality.valueOf(this.cardinality_); return (result == null) ? Cardinality.UNRECOGNIZED : result; } public int getNumber() { return this.number_; } public String getName() { Object ref = this.name_; if (ref instanceof String) return (String)ref;  ByteString bs = (ByteString)ref; String s = bs.toStringUtf8(); this.name_ = s; return s; } public final boolean isInitialized() { byte isInitialized = this.memoizedIsInitialized;
/* 1087 */     if (isInitialized == 1) return true; 
/* 1088 */     if (isInitialized == 0) return false;
/*      */     
/* 1090 */     this.memoizedIsInitialized = 1;
/* 1091 */     return true; } public ByteString getNameBytes() { Object ref = this.name_; if (ref instanceof String) { ByteString b = ByteString.copyFromUtf8((String)ref); this.name_ = b; return b; }  return (ByteString)ref; } public String getTypeUrl() { Object ref = this.typeUrl_; if (ref instanceof String) return (String)ref;  ByteString bs = (ByteString)ref; String s = bs.toStringUtf8(); this.typeUrl_ = s; return s; } public ByteString getTypeUrlBytes() { Object ref = this.typeUrl_; if (ref instanceof String) { ByteString b = ByteString.copyFromUtf8((String)ref); this.typeUrl_ = b; return b; }  return (ByteString)ref; } public int getOneofIndex() { return this.oneofIndex_; } public boolean getPacked() { return this.packed_; } public List<Option> getOptionsList() { return this.options_; } public List<? extends OptionOrBuilder> getOptionsOrBuilderList() { return (List)this.options_; } public int getOptionsCount() { return this.options_.size(); } public Option getOptions(int index) { return this.options_.get(index); }
/*      */   public OptionOrBuilder getOptionsOrBuilder(int index) { return this.options_.get(index); }
/*      */   public String getJsonName() { Object ref = this.jsonName_; if (ref instanceof String) return (String)ref;  ByteString bs = (ByteString)ref; String s = bs.toStringUtf8(); this.jsonName_ = s; return s; }
/*      */   public ByteString getJsonNameBytes() { Object ref = this.jsonName_; if (ref instanceof String) { ByteString b = ByteString.copyFromUtf8((String)ref); this.jsonName_ = b; return b; }  return (ByteString)ref; }
/*      */   public String getDefaultValue() { Object ref = this.defaultValue_; if (ref instanceof String) return (String)ref;  ByteString bs = (ByteString)ref; String s = bs.toStringUtf8(); this.defaultValue_ = s; return s; }
/*      */   public ByteString getDefaultValueBytes() { Object ref = this.defaultValue_; if (ref instanceof String) { ByteString b = ByteString.copyFromUtf8((String)ref); this.defaultValue_ = b; return b; }  return (ByteString)ref; }
/* 1097 */   public void writeTo(CodedOutputStream output) throws IOException { if (this.kind_ != Kind.TYPE_UNKNOWN.getNumber()) {
/* 1098 */       output.writeEnum(1, this.kind_);
/*      */     }
/* 1100 */     if (this.cardinality_ != Cardinality.CARDINALITY_UNKNOWN.getNumber()) {
/* 1101 */       output.writeEnum(2, this.cardinality_);
/*      */     }
/* 1103 */     if (this.number_ != 0) {
/* 1104 */       output.writeInt32(3, this.number_);
/*      */     }
/* 1106 */     if (!getNameBytes().isEmpty()) {
/* 1107 */       GeneratedMessageV3.writeString(output, 4, this.name_);
/*      */     }
/* 1109 */     if (!getTypeUrlBytes().isEmpty()) {
/* 1110 */       GeneratedMessageV3.writeString(output, 6, this.typeUrl_);
/*      */     }
/* 1112 */     if (this.oneofIndex_ != 0) {
/* 1113 */       output.writeInt32(7, this.oneofIndex_);
/*      */     }
/* 1115 */     if (this.packed_) {
/* 1116 */       output.writeBool(8, this.packed_);
/*      */     }
/* 1118 */     for (int i = 0; i < this.options_.size(); i++) {
/* 1119 */       output.writeMessage(9, this.options_.get(i));
/*      */     }
/* 1121 */     if (!getJsonNameBytes().isEmpty()) {
/* 1122 */       GeneratedMessageV3.writeString(output, 10, this.jsonName_);
/*      */     }
/* 1124 */     if (!getDefaultValueBytes().isEmpty()) {
/* 1125 */       GeneratedMessageV3.writeString(output, 11, this.defaultValue_);
/*      */     }
/* 1127 */     this.unknownFields.writeTo(output); }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSerializedSize() {
/* 1132 */     int size = this.memoizedSize;
/* 1133 */     if (size != -1) return size;
/*      */     
/* 1135 */     size = 0;
/* 1136 */     if (this.kind_ != Kind.TYPE_UNKNOWN.getNumber()) {
/* 1137 */       size += 
/* 1138 */         CodedOutputStream.computeEnumSize(1, this.kind_);
/*      */     }
/* 1140 */     if (this.cardinality_ != Cardinality.CARDINALITY_UNKNOWN.getNumber()) {
/* 1141 */       size += 
/* 1142 */         CodedOutputStream.computeEnumSize(2, this.cardinality_);
/*      */     }
/* 1144 */     if (this.number_ != 0) {
/* 1145 */       size += 
/* 1146 */         CodedOutputStream.computeInt32Size(3, this.number_);
/*      */     }
/* 1148 */     if (!getNameBytes().isEmpty()) {
/* 1149 */       size += GeneratedMessageV3.computeStringSize(4, this.name_);
/*      */     }
/* 1151 */     if (!getTypeUrlBytes().isEmpty()) {
/* 1152 */       size += GeneratedMessageV3.computeStringSize(6, this.typeUrl_);
/*      */     }
/* 1154 */     if (this.oneofIndex_ != 0) {
/* 1155 */       size += 
/* 1156 */         CodedOutputStream.computeInt32Size(7, this.oneofIndex_);
/*      */     }
/* 1158 */     if (this.packed_) {
/* 1159 */       size += 
/* 1160 */         CodedOutputStream.computeBoolSize(8, this.packed_);
/*      */     }
/* 1162 */     for (int i = 0; i < this.options_.size(); i++) {
/* 1163 */       size += 
/* 1164 */         CodedOutputStream.computeMessageSize(9, this.options_.get(i));
/*      */     }
/* 1166 */     if (!getJsonNameBytes().isEmpty()) {
/* 1167 */       size += GeneratedMessageV3.computeStringSize(10, this.jsonName_);
/*      */     }
/* 1169 */     if (!getDefaultValueBytes().isEmpty()) {
/* 1170 */       size += GeneratedMessageV3.computeStringSize(11, this.defaultValue_);
/*      */     }
/* 1172 */     size += this.unknownFields.getSerializedSize();
/* 1173 */     this.memoizedSize = size;
/* 1174 */     return size;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean equals(Object obj) {
/* 1179 */     if (obj == this) {
/* 1180 */       return true;
/*      */     }
/* 1182 */     if (!(obj instanceof Field)) {
/* 1183 */       return super.equals(obj);
/*      */     }
/* 1185 */     Field other = (Field)obj;
/*      */     
/* 1187 */     if (this.kind_ != other.kind_) return false; 
/* 1188 */     if (this.cardinality_ != other.cardinality_) return false; 
/* 1189 */     if (getNumber() != other
/* 1190 */       .getNumber()) return false;
/*      */     
/* 1192 */     if (!getName().equals(other.getName())) return false;
/*      */     
/* 1194 */     if (!getTypeUrl().equals(other.getTypeUrl())) return false; 
/* 1195 */     if (getOneofIndex() != other
/* 1196 */       .getOneofIndex()) return false; 
/* 1197 */     if (getPacked() != other
/* 1198 */       .getPacked()) return false;
/*      */     
/* 1200 */     if (!getOptionsList().equals(other.getOptionsList())) return false;
/*      */     
/* 1202 */     if (!getJsonName().equals(other.getJsonName())) return false;
/*      */     
/* 1204 */     if (!getDefaultValue().equals(other.getDefaultValue())) return false; 
/* 1205 */     if (!this.unknownFields.equals(other.unknownFields)) return false; 
/* 1206 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public int hashCode() {
/* 1211 */     if (this.memoizedHashCode != 0) {
/* 1212 */       return this.memoizedHashCode;
/*      */     }
/* 1214 */     int hash = 41;
/* 1215 */     hash = 19 * hash + getDescriptor().hashCode();
/* 1216 */     hash = 37 * hash + 1;
/* 1217 */     hash = 53 * hash + this.kind_;
/* 1218 */     hash = 37 * hash + 2;
/* 1219 */     hash = 53 * hash + this.cardinality_;
/* 1220 */     hash = 37 * hash + 3;
/* 1221 */     hash = 53 * hash + getNumber();
/* 1222 */     hash = 37 * hash + 4;
/* 1223 */     hash = 53 * hash + getName().hashCode();
/* 1224 */     hash = 37 * hash + 6;
/* 1225 */     hash = 53 * hash + getTypeUrl().hashCode();
/* 1226 */     hash = 37 * hash + 7;
/* 1227 */     hash = 53 * hash + getOneofIndex();
/* 1228 */     hash = 37 * hash + 8;
/* 1229 */     hash = 53 * hash + Internal.hashBoolean(
/* 1230 */         getPacked());
/* 1231 */     if (getOptionsCount() > 0) {
/* 1232 */       hash = 37 * hash + 9;
/* 1233 */       hash = 53 * hash + getOptionsList().hashCode();
/*      */     } 
/* 1235 */     hash = 37 * hash + 10;
/* 1236 */     hash = 53 * hash + getJsonName().hashCode();
/* 1237 */     hash = 37 * hash + 11;
/* 1238 */     hash = 53 * hash + getDefaultValue().hashCode();
/* 1239 */     hash = 29 * hash + this.unknownFields.hashCode();
/* 1240 */     this.memoizedHashCode = hash;
/* 1241 */     return hash;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Field parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
/* 1247 */     return PARSER.parseFrom(data);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Field parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 1253 */     return PARSER.parseFrom(data, extensionRegistry);
/*      */   }
/*      */ 
/*      */   
/*      */   public static Field parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 1258 */     return PARSER.parseFrom(data);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Field parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 1264 */     return PARSER.parseFrom(data, extensionRegistry);
/*      */   }
/*      */   
/*      */   public static Field parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 1268 */     return PARSER.parseFrom(data);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Field parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 1274 */     return PARSER.parseFrom(data, extensionRegistry);
/*      */   }
/*      */   
/*      */   public static Field parseFrom(InputStream input) throws IOException {
/* 1278 */     return 
/* 1279 */       GeneratedMessageV3.<Field>parseWithIOException(PARSER, input);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Field parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 1285 */     return 
/* 1286 */       GeneratedMessageV3.<Field>parseWithIOException(PARSER, input, extensionRegistry);
/*      */   }
/*      */   
/*      */   public static Field parseDelimitedFrom(InputStream input) throws IOException {
/* 1290 */     return 
/* 1291 */       GeneratedMessageV3.<Field>parseDelimitedWithIOException(PARSER, input);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Field parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 1297 */     return 
/* 1298 */       GeneratedMessageV3.<Field>parseDelimitedWithIOException(PARSER, input, extensionRegistry);
/*      */   }
/*      */ 
/*      */   
/*      */   public static Field parseFrom(CodedInputStream input) throws IOException {
/* 1303 */     return 
/* 1304 */       GeneratedMessageV3.<Field>parseWithIOException(PARSER, input);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Field parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 1310 */     return 
/* 1311 */       GeneratedMessageV3.<Field>parseWithIOException(PARSER, input, extensionRegistry);
/*      */   }
/*      */   
/*      */   public Builder newBuilderForType() {
/* 1315 */     return newBuilder();
/*      */   } public static Builder newBuilder() {
/* 1317 */     return DEFAULT_INSTANCE.toBuilder();
/*      */   }
/*      */   public static Builder newBuilder(Field prototype) {
/* 1320 */     return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
/*      */   }
/*      */   
/*      */   public Builder toBuilder() {
/* 1324 */     return (this == DEFAULT_INSTANCE) ? new Builder() : (new Builder())
/* 1325 */       .mergeFrom(this);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected Builder newBuilderForType(BuilderParent parent) {
/* 1331 */     Builder builder = new Builder(parent);
/* 1332 */     return builder;
/*      */   }
/*      */   public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements FieldOrBuilder { private int bitField0_; private int kind_;
/*      */     private int cardinality_;
/*      */     private int number_;
/*      */     private Object name_;
/*      */     private Object typeUrl_;
/*      */     private int oneofIndex_;
/*      */     private boolean packed_;
/*      */     private List<Option> options_;
/*      */     private RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> optionsBuilder_;
/*      */     private Object jsonName_;
/*      */     private Object defaultValue_;
/*      */     
/*      */     public static final Descriptors.Descriptor getDescriptor() {
/* 1347 */       return TypeProto.internal_static_google_protobuf_Field_descriptor;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected FieldAccessorTable internalGetFieldAccessorTable() {
/* 1353 */       return TypeProto.internal_static_google_protobuf_Field_fieldAccessorTable
/* 1354 */         .ensureFieldAccessorsInitialized((Class)Field.class, (Class)Builder.class);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 1581 */       this.kind_ = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1655 */       this.cardinality_ = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1772 */       this.name_ = "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1868 */       this.typeUrl_ = "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2058 */       this
/* 2059 */         .options_ = Collections.emptyList();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2370 */       this.jsonName_ = "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2466 */       this.defaultValue_ = ""; maybeForceBuilderInitialization(); } private Builder(BuilderParent parent) { super(parent); this.kind_ = 0; this.cardinality_ = 0; this.name_ = ""; this.typeUrl_ = ""; this.options_ = Collections.emptyList(); this.jsonName_ = ""; this.defaultValue_ = ""; maybeForceBuilderInitialization(); } private void maybeForceBuilderInitialization() { if (GeneratedMessageV3.alwaysUseFieldBuilders) getOptionsFieldBuilder();  } public Builder clear() { super.clear(); this.kind_ = 0; this.cardinality_ = 0; this.number_ = 0; this.name_ = ""; this.typeUrl_ = ""; this.oneofIndex_ = 0; this.packed_ = false; if (this.optionsBuilder_ == null) { this.options_ = Collections.emptyList(); this.bitField0_ &= 0xFFFFFFFE; } else { this.optionsBuilder_.clear(); }  this.jsonName_ = ""; this.defaultValue_ = ""; return this; } public Descriptors.Descriptor getDescriptorForType() { return TypeProto.internal_static_google_protobuf_Field_descriptor; } public Field getDefaultInstanceForType() { return Field.getDefaultInstance(); } public Field build() { Field result = buildPartial(); if (!result.isInitialized()) throw newUninitializedMessageException(result);  return result; } public Field buildPartial() { Field result = new Field(this); int from_bitField0_ = this.bitField0_; result.kind_ = this.kind_; result.cardinality_ = this.cardinality_; result.number_ = this.number_; result.name_ = this.name_; result.typeUrl_ = this.typeUrl_; result.oneofIndex_ = this.oneofIndex_; result.packed_ = this.packed_; if (this.optionsBuilder_ == null) { if ((this.bitField0_ & 0x1) != 0) { this.options_ = Collections.unmodifiableList(this.options_); this.bitField0_ &= 0xFFFFFFFE; }  result.options_ = this.options_; } else { result.options_ = this.optionsBuilder_.build(); }  result.jsonName_ = this.jsonName_; result.defaultValue_ = this.defaultValue_; onBuilt(); return result; } public Builder clone() { return super.clone(); } public Builder setField(Descriptors.FieldDescriptor field, Object value) { return super.setField(field, value); } public Builder clearField(Descriptors.FieldDescriptor field) { return super.clearField(field); } public Builder clearOneof(Descriptors.OneofDescriptor oneof) { return super.clearOneof(oneof); } public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) { return super.setRepeatedField(field, index, value); } public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) { return super.addRepeatedField(field, value); } public Builder mergeFrom(Message other) { if (other instanceof Field) return mergeFrom((Field)other);  super.mergeFrom(other); return this; } public Builder mergeFrom(Field other) { if (other == Field.getDefaultInstance()) return this;  if (other.kind_ != 0) setKindValue(other.getKindValue());  if (other.cardinality_ != 0) setCardinalityValue(other.getCardinalityValue());  if (other.getNumber() != 0) setNumber(other.getNumber());  if (!other.getName().isEmpty()) { this.name_ = other.name_; onChanged(); }  if (!other.getTypeUrl().isEmpty()) { this.typeUrl_ = other.typeUrl_; onChanged(); }  if (other.getOneofIndex() != 0) setOneofIndex(other.getOneofIndex());  if (other.getPacked()) setPacked(other.getPacked());  if (this.optionsBuilder_ == null) { if (!other.options_.isEmpty()) { if (this.options_.isEmpty()) { this.options_ = other.options_; this.bitField0_ &= 0xFFFFFFFE; } else { ensureOptionsIsMutable(); this.options_.addAll(other.options_); }  onChanged(); }  } else if (!other.options_.isEmpty()) { if (this.optionsBuilder_.isEmpty()) { this.optionsBuilder_.dispose(); this.optionsBuilder_ = null; this.options_ = other.options_; this.bitField0_ &= 0xFFFFFFFE; this.optionsBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getOptionsFieldBuilder() : null; } else { this.optionsBuilder_.addAllMessages(other.options_); }  }  if (!other.getJsonName().isEmpty()) { this.jsonName_ = other.jsonName_; onChanged(); }  if (!other.getDefaultValue().isEmpty()) { this.defaultValue_ = other.defaultValue_; onChanged(); }  mergeUnknownFields(other.unknownFields); onChanged(); return this; } public final boolean isInitialized() { return true; } public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException { Field parsedMessage = null; try { parsedMessage = Field.PARSER.parsePartialFrom(input, extensionRegistry); } catch (InvalidProtocolBufferException e) { parsedMessage = (Field)e.getUnfinishedMessage(); throw e.unwrapIOException(); } finally { if (parsedMessage != null) mergeFrom(parsedMessage);  }  return this; } public int getKindValue() { return this.kind_; } public Builder setKindValue(int value) { this.kind_ = value; onChanged(); return this; } public Kind getKind() { Kind result = Kind.valueOf(this.kind_); return (result == null) ? Kind.UNRECOGNIZED : result; } public Builder setKind(Kind value) { if (value == null) throw new NullPointerException();  this.kind_ = value.getNumber(); onChanged(); return this; } public Builder clearKind() { this.kind_ = 0; onChanged(); return this; } public int getCardinalityValue() { return this.cardinality_; } public Builder setCardinalityValue(int value) { this.cardinality_ = value; onChanged(); return this; } public Cardinality getCardinality() { Cardinality result = Cardinality.valueOf(this.cardinality_); return (result == null) ? Cardinality.UNRECOGNIZED : result; } public Builder setCardinality(Cardinality value) { if (value == null) throw new NullPointerException();  this.cardinality_ = value.getNumber(); onChanged(); return this; } public Builder clearCardinality() { this.cardinality_ = 0; onChanged(); return this; }
/*      */     public int getNumber() { return this.number_; }
/*      */     public Builder setNumber(int value) { this.number_ = value; onChanged(); return this; }
/*      */     public Builder clearNumber() { this.number_ = 0; onChanged(); return this; }
/*      */     public String getName() { Object ref = this.name_; if (!(ref instanceof String)) { ByteString bs = (ByteString)ref; String s = bs.toStringUtf8(); this.name_ = s; return s; }  return (String)ref; }
/*      */     public ByteString getNameBytes() { Object ref = this.name_; if (ref instanceof String) { ByteString b = ByteString.copyFromUtf8((String)ref); this.name_ = b; return b; }  return (ByteString)ref; }
/*      */     public Builder setName(String value) { if (value == null) throw new NullPointerException();  this.name_ = value; onChanged(); return this; }
/*      */     public Builder clearName() { this.name_ = Field.getDefaultInstance().getName(); onChanged(); return this; }
/*      */     public Builder setNameBytes(ByteString value) { if (value == null) throw new NullPointerException();  AbstractMessageLite.checkByteStringIsUtf8(value); this.name_ = value; onChanged(); return this; }
/*      */     public String getTypeUrl() { Object ref = this.typeUrl_; if (!(ref instanceof String)) { ByteString bs = (ByteString)ref; String s = bs.toStringUtf8(); this.typeUrl_ = s; return s; }  return (String)ref; }
/* 2476 */     public String getDefaultValue() { Object ref = this.defaultValue_;
/* 2477 */       if (!(ref instanceof String)) {
/* 2478 */         ByteString bs = (ByteString)ref;
/*      */         
/* 2480 */         String s = bs.toStringUtf8();
/* 2481 */         this.defaultValue_ = s;
/* 2482 */         return s;
/*      */       } 
/* 2484 */       return (String)ref; } public ByteString getTypeUrlBytes() { Object ref = this.typeUrl_; if (ref instanceof String) { ByteString b = ByteString.copyFromUtf8((String)ref); this.typeUrl_ = b; return b; }  return (ByteString)ref; } public Builder setTypeUrl(String value) { if (value == null) throw new NullPointerException();  this.typeUrl_ = value; onChanged(); return this; } public Builder clearTypeUrl() { this.typeUrl_ = Field.getDefaultInstance().getTypeUrl(); onChanged(); return this; } public Builder setTypeUrlBytes(ByteString value) { if (value == null) throw new NullPointerException();  AbstractMessageLite.checkByteStringIsUtf8(value); this.typeUrl_ = value; onChanged(); return this; } public int getOneofIndex() { return this.oneofIndex_; } public Builder setOneofIndex(int value) { this.oneofIndex_ = value; onChanged(); return this; } public Builder clearOneofIndex() { this.oneofIndex_ = 0; onChanged(); return this; } public boolean getPacked() { return this.packed_; } public Builder setPacked(boolean value) { this.packed_ = value; onChanged(); return this; } public Builder clearPacked() { this.packed_ = false; onChanged(); return this; } private void ensureOptionsIsMutable() { if ((this.bitField0_ & 0x1) == 0) { this.options_ = new ArrayList<>(this.options_); this.bitField0_ |= 0x1; }  } public List<Option> getOptionsList() { if (this.optionsBuilder_ == null) return Collections.unmodifiableList(this.options_);  return this.optionsBuilder_.getMessageList(); } public int getOptionsCount() { if (this.optionsBuilder_ == null) return this.options_.size();  return this.optionsBuilder_.getCount(); } public Option getOptions(int index) { if (this.optionsBuilder_ == null) return this.options_.get(index);  return this.optionsBuilder_.getMessage(index); } public Builder setOptions(int index, Option value) { if (this.optionsBuilder_ == null) { if (value == null) throw new NullPointerException();  ensureOptionsIsMutable(); this.options_.set(index, value); onChanged(); } else { this.optionsBuilder_.setMessage(index, value); }  return this; } public Builder setOptions(int index, Option.Builder builderForValue) { if (this.optionsBuilder_ == null) { ensureOptionsIsMutable(); this.options_.set(index, builderForValue.build()); onChanged(); } else { this.optionsBuilder_.setMessage(index, builderForValue.build()); }  return this; } public Builder addOptions(Option value) { if (this.optionsBuilder_ == null) { if (value == null) throw new NullPointerException();  ensureOptionsIsMutable(); this.options_.add(value); onChanged(); } else { this.optionsBuilder_.addMessage(value); }  return this; } public Builder addOptions(int index, Option value) { if (this.optionsBuilder_ == null) { if (value == null) throw new NullPointerException();  ensureOptionsIsMutable(); this.options_.add(index, value); onChanged(); } else { this.optionsBuilder_.addMessage(index, value); }  return this; } public Builder addOptions(Option.Builder builderForValue) { if (this.optionsBuilder_ == null) { ensureOptionsIsMutable(); this.options_.add(builderForValue.build()); onChanged(); } else { this.optionsBuilder_.addMessage(builderForValue.build()); }  return this; } public Builder addOptions(int index, Option.Builder builderForValue) { if (this.optionsBuilder_ == null) { ensureOptionsIsMutable(); this.options_.add(index, builderForValue.build()); onChanged(); } else { this.optionsBuilder_.addMessage(index, builderForValue.build()); }  return this; } public Builder addAllOptions(Iterable<? extends Option> values) { if (this.optionsBuilder_ == null) { ensureOptionsIsMutable(); AbstractMessageLite.Builder.addAll(values, this.options_); onChanged(); } else { this.optionsBuilder_.addAllMessages(values); }  return this; } public Builder clearOptions() { if (this.optionsBuilder_ == null) { this.options_ = Collections.emptyList(); this.bitField0_ &= 0xFFFFFFFE; onChanged(); } else { this.optionsBuilder_.clear(); }  return this; } public Builder removeOptions(int index) { if (this.optionsBuilder_ == null) { ensureOptionsIsMutable(); this.options_.remove(index); onChanged(); } else { this.optionsBuilder_.remove(index); }  return this; }
/*      */     public Option.Builder getOptionsBuilder(int index) { return getOptionsFieldBuilder().getBuilder(index); }
/*      */     public OptionOrBuilder getOptionsOrBuilder(int index) { if (this.optionsBuilder_ == null) return this.options_.get(index);  return this.optionsBuilder_.getMessageOrBuilder(index); }
/*      */     public List<? extends OptionOrBuilder> getOptionsOrBuilderList() { if (this.optionsBuilder_ != null) return this.optionsBuilder_.getMessageOrBuilderList();  return Collections.unmodifiableList((List)this.options_); }
/*      */     public Option.Builder addOptionsBuilder() { return getOptionsFieldBuilder().addBuilder(Option.getDefaultInstance()); }
/*      */     public Option.Builder addOptionsBuilder(int index) { return getOptionsFieldBuilder().addBuilder(index, Option.getDefaultInstance()); }
/*      */     public List<Option.Builder> getOptionsBuilderList() { return getOptionsFieldBuilder().getBuilderList(); }
/*      */     private RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> getOptionsFieldBuilder() { if (this.optionsBuilder_ == null) { this.optionsBuilder_ = new RepeatedFieldBuilderV3<>(this.options_, ((this.bitField0_ & 0x1) != 0), getParentForChildren(), isClean()); this.options_ = null; }  return this.optionsBuilder_; }
/*      */     public String getJsonName() { Object ref = this.jsonName_; if (!(ref instanceof String)) { ByteString bs = (ByteString)ref; String s = bs.toStringUtf8(); this.jsonName_ = s; return s; }  return (String)ref; }
/*      */     public ByteString getJsonNameBytes() { Object ref = this.jsonName_; if (ref instanceof String) { ByteString b = ByteString.copyFromUtf8((String)ref); this.jsonName_ = b; return b; }  return (ByteString)ref; }
/*      */     public Builder setJsonName(String value) { if (value == null) throw new NullPointerException();  this.jsonName_ = value; onChanged(); return this; }
/*      */     public Builder clearJsonName() { this.jsonName_ = Field.getDefaultInstance().getJsonName(); onChanged(); return this; }
/*      */     public Builder setJsonNameBytes(ByteString value) { if (value == null) throw new NullPointerException();  AbstractMessageLite.checkByteStringIsUtf8(value); this.jsonName_ = value; onChanged(); return this; }
/* 2497 */     public ByteString getDefaultValueBytes() { Object ref = this.defaultValue_;
/* 2498 */       if (ref instanceof String) {
/*      */         
/* 2500 */         ByteString b = ByteString.copyFromUtf8((String)ref);
/*      */         
/* 2502 */         this.defaultValue_ = b;
/* 2503 */         return b;
/*      */       } 
/* 2505 */       return (ByteString)ref; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Builder setDefaultValue(String value) {
/* 2519 */       if (value == null) {
/* 2520 */         throw new NullPointerException();
/*      */       }
/*      */       
/* 2523 */       this.defaultValue_ = value;
/* 2524 */       onChanged();
/* 2525 */       return this;
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
/*      */     public Builder clearDefaultValue() {
/* 2537 */       this.defaultValue_ = Field.getDefaultInstance().getDefaultValue();
/* 2538 */       onChanged();
/* 2539 */       return this;
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
/*      */     public Builder setDefaultValueBytes(ByteString value) {
/* 2552 */       if (value == null) {
/* 2553 */         throw new NullPointerException();
/*      */       }
/* 2555 */       AbstractMessageLite.checkByteStringIsUtf8(value);
/*      */       
/* 2557 */       this.defaultValue_ = value;
/* 2558 */       onChanged();
/* 2559 */       return this;
/*      */     }
/*      */ 
/*      */     
/*      */     public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
/* 2564 */       return super.setUnknownFields(unknownFields);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
/* 2570 */       return super.mergeUnknownFields(unknownFields);
/*      */     } }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2580 */   private static final Field DEFAULT_INSTANCE = new Field();
/*      */ 
/*      */   
/*      */   public static Field getDefaultInstance() {
/* 2584 */     return DEFAULT_INSTANCE;
/*      */   }
/*      */ 
/*      */   
/* 2588 */   private static final Parser<Field> PARSER = new AbstractParser<Field>()
/*      */     {
/*      */ 
/*      */       
/*      */       public Field parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException
/*      */       {
/* 2594 */         return new Field(input, extensionRegistry);
/*      */       }
/*      */     };
/*      */   
/*      */   public static Parser<Field> parser() {
/* 2599 */     return PARSER;
/*      */   }
/*      */ 
/*      */   
/*      */   public Parser<Field> getParserForType() {
/* 2604 */     return PARSER;
/*      */   }
/*      */ 
/*      */   
/*      */   public Field getDefaultInstanceForType() {
/* 2609 */     return DEFAULT_INSTANCE;
/*      */   } }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\Field.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */