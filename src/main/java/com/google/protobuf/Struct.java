/*     */ package com.google.protobuf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Struct
/*     */   extends GeneratedMessageV3
/*     */   implements StructOrBuilder
/*     */ {
/*     */   private static final long serialVersionUID = 0L;
/*     */   public static final int FIELDS_FIELD_NUMBER = 1;
/*     */   private MapField<String, Value> fields_;
/*     */   private byte memoizedIsInitialized;
/*     */   
/*     */   private Struct(GeneratedMessageV3.Builder<?> builder) {
/*  26 */     super(builder);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 216 */     this.memoizedIsInitialized = -1; } protected Object newInstance(UnusedPrivateParameter unused) { return new Struct(); } public final UnknownFieldSet getUnknownFields() { return this.unknownFields; } private Struct(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException { this(); if (extensionRegistry == null) throw new NullPointerException();  int mutable_bitField0_ = 0; UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(); try { boolean done = false; while (!done) { MapEntry<String, Value> fields__; int tag = input.readTag(); switch (tag) { case 0: done = true; continue;case 10: if ((mutable_bitField0_ & 0x1) == 0) { this.fields_ = MapField.newMapField(FieldsDefaultEntryHolder.defaultEntry); mutable_bitField0_ |= 0x1; }  fields__ = input.<MapEntry<String, Value>>readMessage(FieldsDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry); this.fields_.getMutableMap().put(fields__.getKey(), fields__.getValue()); continue; }  if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) done = true;  }  } catch (InvalidProtocolBufferException e) { throw e.setUnfinishedMessage(this); } catch (IOException e) { throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(this); } finally { this.unknownFields = unknownFields.build(); makeExtensionsImmutable(); }  } public static final Descriptors.Descriptor getDescriptor() { return StructProto.internal_static_google_protobuf_Struct_descriptor; } private Struct() { this.memoizedIsInitialized = -1; }
/*     */   protected MapField internalGetMapField(int number) { switch (number) { case 1: return internalGetFields(); }  throw new RuntimeException("Invalid map field number: " + number); }
/*     */   protected FieldAccessorTable internalGetFieldAccessorTable() { return StructProto.internal_static_google_protobuf_Struct_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)Struct.class, (Class)Builder.class); } private static final class FieldsDefaultEntryHolder {
/* 219 */     static final MapEntry<String, Value> defaultEntry = MapEntry.newDefaultInstance(StructProto.internal_static_google_protobuf_Struct_FieldsEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.MESSAGE, Value.getDefaultInstance()); } public final boolean isInitialized() { byte isInitialized = this.memoizedIsInitialized;
/* 220 */     if (isInitialized == 1) return true; 
/* 221 */     if (isInitialized == 0) return false;
/*     */     
/* 223 */     this.memoizedIsInitialized = 1;
/* 224 */     return true; } private MapField<String, Value> internalGetFields() { if (this.fields_ == null) return MapField.emptyMapField(FieldsDefaultEntryHolder.defaultEntry);  return this.fields_; }
/*     */   public int getFieldsCount() { return internalGetFields().getMap().size(); }
/*     */   public boolean containsFields(String key) { if (key == null) throw new NullPointerException();  return internalGetFields().getMap().containsKey(key); }
/*     */   @Deprecated public Map<String, Value> getFields() { return getFieldsMap(); }
/*     */   public Map<String, Value> getFieldsMap() { return internalGetFields().getMap(); }
/*     */   public Value getFieldsOrDefault(String key, Value defaultValue) { if (key == null) throw new NullPointerException();  Map<String, Value> map = internalGetFields().getMap(); return map.containsKey(key) ? map.get(key) : defaultValue; }
/*     */   public Value getFieldsOrThrow(String key) { if (key == null) throw new NullPointerException();  Map<String, Value> map = internalGetFields().getMap(); if (!map.containsKey(key)) throw new IllegalArgumentException();  return map.get(key); }
/* 231 */   public void writeTo(CodedOutputStream output) throws IOException { GeneratedMessageV3.serializeStringMapTo(output, 
/*     */         
/* 233 */         internalGetFields(), FieldsDefaultEntryHolder.defaultEntry, 1);
/*     */ 
/*     */     
/* 236 */     this.unknownFields.writeTo(output); }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSerializedSize() {
/* 241 */     int size = this.memoizedSize;
/* 242 */     if (size != -1) return size;
/*     */     
/* 244 */     size = 0;
/*     */     
/* 246 */     for (Map.Entry<String, Value> entry : (Iterable<Map.Entry<String, Value>>)internalGetFields().getMap().entrySet()) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 251 */       MapEntry<String, Value> fields__ = FieldsDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build();
/* 252 */       size += 
/* 253 */         CodedOutputStream.computeMessageSize(1, fields__);
/*     */     } 
/* 255 */     size += this.unknownFields.getSerializedSize();
/* 256 */     this.memoizedSize = size;
/* 257 */     return size;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 262 */     if (obj == this) {
/* 263 */       return true;
/*     */     }
/* 265 */     if (!(obj instanceof Struct)) {
/* 266 */       return super.equals(obj);
/*     */     }
/* 268 */     Struct other = (Struct)obj;
/*     */     
/* 270 */     if (!internalGetFields().equals(other
/* 271 */         .internalGetFields())) return false; 
/* 272 */     if (!this.unknownFields.equals(other.unknownFields)) return false; 
/* 273 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 278 */     if (this.memoizedHashCode != 0) {
/* 279 */       return this.memoizedHashCode;
/*     */     }
/* 281 */     int hash = 41;
/* 282 */     hash = 19 * hash + getDescriptor().hashCode();
/* 283 */     if (!internalGetFields().getMap().isEmpty()) {
/* 284 */       hash = 37 * hash + 1;
/* 285 */       hash = 53 * hash + internalGetFields().hashCode();
/*     */     } 
/* 287 */     hash = 29 * hash + this.unknownFields.hashCode();
/* 288 */     this.memoizedHashCode = hash;
/* 289 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Struct parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
/* 295 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Struct parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 301 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Struct parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 306 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Struct parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 312 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static Struct parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 316 */     return PARSER.parseFrom(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Struct parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 322 */     return PARSER.parseFrom(data, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static Struct parseFrom(InputStream input) throws IOException {
/* 326 */     return 
/* 327 */       GeneratedMessageV3.<Struct>parseWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Struct parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 333 */     return 
/* 334 */       GeneratedMessageV3.<Struct>parseWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */   
/*     */   public static Struct parseDelimitedFrom(InputStream input) throws IOException {
/* 338 */     return 
/* 339 */       GeneratedMessageV3.<Struct>parseDelimitedWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Struct parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 345 */     return 
/* 346 */       GeneratedMessageV3.<Struct>parseDelimitedWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Struct parseFrom(CodedInputStream input) throws IOException {
/* 351 */     return 
/* 352 */       GeneratedMessageV3.<Struct>parseWithIOException(PARSER, input);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Struct parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 358 */     return 
/* 359 */       GeneratedMessageV3.<Struct>parseWithIOException(PARSER, input, extensionRegistry);
/*     */   }
/*     */   
/*     */   public Builder newBuilderForType() {
/* 363 */     return newBuilder();
/*     */   } public static Builder newBuilder() {
/* 365 */     return DEFAULT_INSTANCE.toBuilder();
/*     */   }
/*     */   public static Builder newBuilder(Struct prototype) {
/* 368 */     return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
/*     */   }
/*     */   
/*     */   public Builder toBuilder() {
/* 372 */     return (this == DEFAULT_INSTANCE) ? new Builder() : (new Builder())
/* 373 */       .mergeFrom(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Builder newBuilderForType(BuilderParent parent) {
/* 379 */     Builder builder = new Builder(parent);
/* 380 */     return builder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final class Builder
/*     */     extends GeneratedMessageV3.Builder<Builder>
/*     */     implements StructOrBuilder
/*     */   {
/*     */     private int bitField0_;
/*     */ 
/*     */ 
/*     */     
/*     */     private MapField<String, Value> fields_;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static final Descriptors.Descriptor getDescriptor() {
/* 401 */       return StructProto.internal_static_google_protobuf_Struct_descriptor;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected MapField internalGetMapField(int number) {
/* 407 */       switch (number) {
/*     */         case 1:
/* 409 */           return internalGetFields();
/*     */       } 
/* 411 */       throw new RuntimeException("Invalid map field number: " + number);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected MapField internalGetMutableMapField(int number) {
/* 418 */       switch (number) {
/*     */         case 1:
/* 420 */           return internalGetMutableFields();
/*     */       } 
/* 422 */       throw new RuntimeException("Invalid map field number: " + number);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected FieldAccessorTable internalGetFieldAccessorTable() {
/* 429 */       return StructProto.internal_static_google_protobuf_Struct_fieldAccessorTable
/* 430 */         .ensureFieldAccessorsInitialized((Class)Struct.class, (Class)Builder.class);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private Builder() {
/* 436 */       maybeForceBuilderInitialization();
/*     */     }
/*     */ 
/*     */     
/*     */     private Builder(BuilderParent parent) {
/* 441 */       super(parent);
/* 442 */       maybeForceBuilderInitialization();
/*     */     }
/*     */     private void maybeForceBuilderInitialization() {
/* 445 */       if (GeneratedMessageV3.alwaysUseFieldBuilders);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder clear() {
/* 451 */       super.clear();
/* 452 */       internalGetMutableFields().clear();
/* 453 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Descriptors.Descriptor getDescriptorForType() {
/* 459 */       return StructProto.internal_static_google_protobuf_Struct_descriptor;
/*     */     }
/*     */ 
/*     */     
/*     */     public Struct getDefaultInstanceForType() {
/* 464 */       return Struct.getDefaultInstance();
/*     */     }
/*     */ 
/*     */     
/*     */     public Struct build() {
/* 469 */       Struct result = buildPartial();
/* 470 */       if (!result.isInitialized()) {
/* 471 */         throw newUninitializedMessageException(result);
/*     */       }
/* 473 */       return result;
/*     */     }
/*     */ 
/*     */     
/*     */     public Struct buildPartial() {
/* 478 */       Struct result = new Struct(this);
/* 479 */       int from_bitField0_ = this.bitField0_;
/* 480 */       result.fields_ = internalGetFields();
/* 481 */       result.fields_.makeImmutable();
/* 482 */       onBuilt();
/* 483 */       return result;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder clone() {
/* 488 */       return super.clone();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setField(Descriptors.FieldDescriptor field, Object value) {
/* 494 */       return super.setField(field, value);
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder clearField(Descriptors.FieldDescriptor field) {
/* 499 */       return super.clearField(field);
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
/* 504 */       return super.clearOneof(oneof);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
/* 510 */       return super.setRepeatedField(field, index, value);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
/* 516 */       return super.addRepeatedField(field, value);
/*     */     }
/*     */     
/*     */     public Builder mergeFrom(Message other) {
/* 520 */       if (other instanceof Struct) {
/* 521 */         return mergeFrom((Struct)other);
/*     */       }
/* 523 */       super.mergeFrom(other);
/* 524 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder mergeFrom(Struct other) {
/* 529 */       if (other == Struct.getDefaultInstance()) return this; 
/* 530 */       internalGetMutableFields().mergeFrom(other
/* 531 */           .internalGetFields());
/* 532 */       mergeUnknownFields(other.unknownFields);
/* 533 */       onChanged();
/* 534 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public final boolean isInitialized() {
/* 539 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 547 */       Struct parsedMessage = null;
/*     */       try {
/* 549 */         parsedMessage = Struct.PARSER.parsePartialFrom(input, extensionRegistry);
/* 550 */       } catch (InvalidProtocolBufferException e) {
/* 551 */         parsedMessage = (Struct)e.getUnfinishedMessage();
/* 552 */         throw e.unwrapIOException();
/*     */       } finally {
/* 554 */         if (parsedMessage != null) {
/* 555 */           mergeFrom(parsedMessage);
/*     */         }
/*     */       } 
/* 558 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private MapField<String, Value> internalGetFields() {
/* 566 */       if (this.fields_ == null) {
/* 567 */         return MapField.emptyMapField(FieldsDefaultEntryHolder.defaultEntry);
/*     */       }
/*     */       
/* 570 */       return this.fields_;
/*     */     }
/*     */     
/*     */     private MapField<String, Value> internalGetMutableFields() {
/* 574 */       onChanged();
/* 575 */       if (this.fields_ == null) {
/* 576 */         this.fields_ = MapField.newMapField(FieldsDefaultEntryHolder.defaultEntry);
/*     */       }
/*     */       
/* 579 */       if (!this.fields_.isMutable()) {
/* 580 */         this.fields_ = this.fields_.copy();
/*     */       }
/* 582 */       return this.fields_;
/*     */     }
/*     */     
/*     */     public int getFieldsCount() {
/* 586 */       return internalGetFields().getMap().size();
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
/*     */     public boolean containsFields(String key) {
/* 599 */       if (key == null) throw new NullPointerException(); 
/* 600 */       return internalGetFields().getMap().containsKey(key);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @Deprecated
/*     */     public Map<String, Value> getFields() {
/* 608 */       return getFieldsMap();
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
/*     */     public Map<String, Value> getFieldsMap() {
/* 620 */       return internalGetFields().getMap();
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
/*     */     public Value getFieldsOrDefault(String key, Value defaultValue) {
/* 634 */       if (key == null) throw new NullPointerException();
/*     */       
/* 636 */       Map<String, Value> map = internalGetFields().getMap();
/* 637 */       return map.containsKey(key) ? map.get(key) : defaultValue;
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
/*     */     public Value getFieldsOrThrow(String key) {
/* 650 */       if (key == null) throw new NullPointerException();
/*     */       
/* 652 */       Map<String, Value> map = internalGetFields().getMap();
/* 653 */       if (!map.containsKey(key)) {
/* 654 */         throw new IllegalArgumentException();
/*     */       }
/* 656 */       return map.get(key);
/*     */     }
/*     */     
/*     */     public Builder clearFields() {
/* 660 */       internalGetMutableFields().getMutableMap()
/* 661 */         .clear();
/* 662 */       return this;
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
/*     */     public Builder removeFields(String key) {
/* 674 */       if (key == null) throw new NullPointerException(); 
/* 675 */       internalGetMutableFields().getMutableMap()
/* 676 */         .remove(key);
/* 677 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @Deprecated
/*     */     public Map<String, Value> getMutableFields() {
/* 685 */       return internalGetMutableFields().getMutableMap();
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
/*     */     public Builder putFields(String key, Value value) {
/* 697 */       if (key == null) throw new NullPointerException(); 
/* 698 */       if (value == null) throw new NullPointerException(); 
/* 699 */       internalGetMutableFields().getMutableMap()
/* 700 */         .put(key, value);
/* 701 */       return this;
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
/*     */     public Builder putAllFields(Map<String, Value> values) {
/* 713 */       internalGetMutableFields().getMutableMap()
/* 714 */         .putAll(values);
/* 715 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
/* 720 */       return super.setUnknownFields(unknownFields);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
/* 726 */       return super.mergeUnknownFields(unknownFields);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 736 */   private static final Struct DEFAULT_INSTANCE = new Struct();
/*     */ 
/*     */   
/*     */   public static Struct getDefaultInstance() {
/* 740 */     return DEFAULT_INSTANCE;
/*     */   }
/*     */ 
/*     */   
/* 744 */   private static final Parser<Struct> PARSER = new AbstractParser<Struct>()
/*     */     {
/*     */ 
/*     */       
/*     */       public Struct parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException
/*     */       {
/* 750 */         return new Struct(input, extensionRegistry);
/*     */       }
/*     */     };
/*     */   
/*     */   public static Parser<Struct> parser() {
/* 755 */     return PARSER;
/*     */   }
/*     */ 
/*     */   
/*     */   public Parser<Struct> getParserForType() {
/* 760 */     return PARSER;
/*     */   }
/*     */ 
/*     */   
/*     */   public Struct getDefaultInstanceForType() {
/* 765 */     return DEFAULT_INSTANCE;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\Struct.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */