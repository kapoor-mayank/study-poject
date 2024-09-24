/*     */ package com.google.protobuf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class DynamicMessage
/*     */   extends AbstractMessage
/*     */ {
/*     */   private final Descriptors.Descriptor type;
/*     */   private final FieldSet<Descriptors.FieldDescriptor> fields;
/*     */   private final Descriptors.FieldDescriptor[] oneofCases;
/*     */   private final UnknownFieldSet unknownFields;
/*  56 */   private int memoizedSize = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   DynamicMessage(Descriptors.Descriptor type, FieldSet<Descriptors.FieldDescriptor> fields, Descriptors.FieldDescriptor[] oneofCases, UnknownFieldSet unknownFields) {
/*  71 */     this.type = type;
/*  72 */     this.fields = fields;
/*  73 */     this.oneofCases = oneofCases;
/*  74 */     this.unknownFields = unknownFields;
/*     */   }
/*     */ 
/*     */   
/*     */   public static DynamicMessage getDefaultInstance(Descriptors.Descriptor type) {
/*  79 */     int oneofDeclCount = type.toProto().getOneofDeclCount();
/*  80 */     Descriptors.FieldDescriptor[] oneofCases = new Descriptors.FieldDescriptor[oneofDeclCount];
/*  81 */     return new DynamicMessage(type, 
/*     */         
/*  83 */         FieldSet.emptySet(), oneofCases, 
/*     */         
/*  85 */         UnknownFieldSet.getDefaultInstance());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DynamicMessage parseFrom(Descriptors.Descriptor type, CodedInputStream input) throws IOException {
/*  92 */     return newBuilder(type).mergeFrom(input).buildParsed();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DynamicMessage parseFrom(Descriptors.Descriptor type, CodedInputStream input, ExtensionRegistry extensionRegistry) throws IOException {
/*  99 */     return newBuilder(type).mergeFrom(input, extensionRegistry).buildParsed();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static DynamicMessage parseFrom(Descriptors.Descriptor type, ByteString data) throws InvalidProtocolBufferException {
/* 105 */     return newBuilder(type).mergeFrom(data).buildParsed();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DynamicMessage parseFrom(Descriptors.Descriptor type, ByteString data, ExtensionRegistry extensionRegistry) throws InvalidProtocolBufferException {
/* 112 */     return newBuilder(type).mergeFrom(data, extensionRegistry).buildParsed();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static DynamicMessage parseFrom(Descriptors.Descriptor type, byte[] data) throws InvalidProtocolBufferException {
/* 118 */     return newBuilder(type).mergeFrom(data).buildParsed();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DynamicMessage parseFrom(Descriptors.Descriptor type, byte[] data, ExtensionRegistry extensionRegistry) throws InvalidProtocolBufferException {
/* 125 */     return newBuilder(type).mergeFrom(data, extensionRegistry).buildParsed();
/*     */   }
/*     */ 
/*     */   
/*     */   public static DynamicMessage parseFrom(Descriptors.Descriptor type, InputStream input) throws IOException {
/* 130 */     return newBuilder(type).mergeFrom(input).buildParsed();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static DynamicMessage parseFrom(Descriptors.Descriptor type, InputStream input, ExtensionRegistry extensionRegistry) throws IOException {
/* 136 */     return newBuilder(type).mergeFrom(input, extensionRegistry).buildParsed();
/*     */   }
/*     */ 
/*     */   
/*     */   public static Builder newBuilder(Descriptors.Descriptor type) {
/* 141 */     return new Builder(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Builder newBuilder(Message prototype) {
/* 149 */     return (new Builder(prototype.getDescriptorForType())).mergeFrom(prototype);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Descriptors.Descriptor getDescriptorForType() {
/* 157 */     return this.type;
/*     */   }
/*     */ 
/*     */   
/*     */   public DynamicMessage getDefaultInstanceForType() {
/* 162 */     return getDefaultInstance(this.type);
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
/* 167 */     return this.fields.getAllFields();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasOneof(Descriptors.OneofDescriptor oneof) {
/* 172 */     verifyOneofContainingType(oneof);
/* 173 */     Descriptors.FieldDescriptor field = this.oneofCases[oneof.getIndex()];
/* 174 */     if (field == null) {
/* 175 */       return false;
/*     */     }
/* 177 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneof) {
/* 182 */     verifyOneofContainingType(oneof);
/* 183 */     return this.oneofCases[oneof.getIndex()];
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasField(Descriptors.FieldDescriptor field) {
/* 188 */     verifyContainingType(field);
/* 189 */     return this.fields.hasField(field);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getField(Descriptors.FieldDescriptor field) {
/* 194 */     verifyContainingType(field);
/* 195 */     Object<?> result = (Object<?>)this.fields.getField(field);
/* 196 */     if (result == null) {
/* 197 */       if (field.isRepeated()) {
/* 198 */         result = Collections.emptyList();
/* 199 */       } else if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
/* 200 */         result = (Object<?>)getDefaultInstance(field.getMessageType());
/*     */       } else {
/* 202 */         result = (Object<?>)field.getDefaultValue();
/*     */       } 
/*     */     }
/* 205 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRepeatedFieldCount(Descriptors.FieldDescriptor field) {
/* 210 */     verifyContainingType(field);
/* 211 */     return this.fields.getRepeatedFieldCount(field);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getRepeatedField(Descriptors.FieldDescriptor field, int index) {
/* 216 */     verifyContainingType(field);
/* 217 */     return this.fields.getRepeatedField(field, index);
/*     */   }
/*     */ 
/*     */   
/*     */   public UnknownFieldSet getUnknownFields() {
/* 222 */     return this.unknownFields;
/*     */   }
/*     */ 
/*     */   
/*     */   static boolean isInitialized(Descriptors.Descriptor type, FieldSet<Descriptors.FieldDescriptor> fields) {
/* 227 */     for (Descriptors.FieldDescriptor field : type.getFields()) {
/* 228 */       if (field.isRequired() && 
/* 229 */         !fields.hasField(field)) {
/* 230 */         return false;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 236 */     return fields.isInitialized();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInitialized() {
/* 241 */     return isInitialized(this.type, this.fields);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeTo(CodedOutputStream output) throws IOException {
/* 246 */     if (this.type.getOptions().getMessageSetWireFormat()) {
/* 247 */       this.fields.writeMessageSetTo(output);
/* 248 */       this.unknownFields.writeAsMessageSetTo(output);
/*     */     } else {
/* 250 */       this.fields.writeTo(output);
/* 251 */       this.unknownFields.writeTo(output);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSerializedSize() {
/* 257 */     int size = this.memoizedSize;
/* 258 */     if (size != -1) return size;
/*     */     
/* 260 */     if (this.type.getOptions().getMessageSetWireFormat()) {
/* 261 */       size = this.fields.getMessageSetSerializedSize();
/* 262 */       size += this.unknownFields.getSerializedSizeAsMessageSet();
/*     */     } else {
/* 264 */       size = this.fields.getSerializedSize();
/* 265 */       size += this.unknownFields.getSerializedSize();
/*     */     } 
/*     */     
/* 268 */     this.memoizedSize = size;
/* 269 */     return size;
/*     */   }
/*     */ 
/*     */   
/*     */   public Builder newBuilderForType() {
/* 274 */     return new Builder(this.type);
/*     */   }
/*     */ 
/*     */   
/*     */   public Builder toBuilder() {
/* 279 */     return newBuilderForType().mergeFrom(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public Parser<DynamicMessage> getParserForType() {
/* 284 */     return new AbstractParser<DynamicMessage>()
/*     */       {
/*     */         
/*     */         public DynamicMessage parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException
/*     */         {
/* 289 */           Builder builder = DynamicMessage.newBuilder(DynamicMessage.this.type);
/*     */           try {
/* 291 */             builder.mergeFrom(input, extensionRegistry);
/* 292 */           } catch (InvalidProtocolBufferException e) {
/* 293 */             throw e.setUnfinishedMessage(builder.buildPartial());
/* 294 */           } catch (IOException e) {
/* 295 */             throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(builder.buildPartial());
/*     */           } 
/* 297 */           return builder.buildPartial();
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   private void verifyContainingType(Descriptors.FieldDescriptor field) {
/* 304 */     if (field.getContainingType() != this.type) {
/* 305 */       throw new IllegalArgumentException("FieldDescriptor does not match message type.");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void verifyOneofContainingType(Descriptors.OneofDescriptor oneof) {
/* 311 */     if (oneof.getContainingType() != this.type) {
/* 312 */       throw new IllegalArgumentException("OneofDescriptor does not match message type.");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static final class Builder
/*     */     extends AbstractMessage.Builder<Builder>
/*     */   {
/*     */     private final Descriptors.Descriptor type;
/*     */     
/*     */     private FieldSet<Descriptors.FieldDescriptor> fields;
/*     */     private final Descriptors.FieldDescriptor[] oneofCases;
/*     */     private UnknownFieldSet unknownFields;
/*     */     
/*     */     private Builder(Descriptors.Descriptor type) {
/* 327 */       this.type = type;
/* 328 */       this.fields = FieldSet.newFieldSet();
/* 329 */       this.unknownFields = UnknownFieldSet.getDefaultInstance();
/* 330 */       this.oneofCases = new Descriptors.FieldDescriptor[type.toProto().getOneofDeclCount()];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder clear() {
/* 338 */       if (this.fields.isImmutable()) {
/* 339 */         this.fields = FieldSet.newFieldSet();
/*     */       } else {
/* 341 */         this.fields.clear();
/*     */       } 
/* 343 */       this.unknownFields = UnknownFieldSet.getDefaultInstance();
/* 344 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder mergeFrom(Message other) {
/* 349 */       if (other instanceof DynamicMessage) {
/*     */         
/* 351 */         DynamicMessage otherDynamicMessage = (DynamicMessage)other;
/* 352 */         if (otherDynamicMessage.type != this.type) {
/* 353 */           throw new IllegalArgumentException("mergeFrom(Message) can only merge messages of the same type.");
/*     */         }
/*     */         
/* 356 */         ensureIsMutable();
/* 357 */         this.fields.mergeFrom(otherDynamicMessage.fields);
/* 358 */         mergeUnknownFields(otherDynamicMessage.unknownFields);
/* 359 */         for (int i = 0; i < this.oneofCases.length; i++) {
/* 360 */           if (this.oneofCases[i] == null) {
/* 361 */             this.oneofCases[i] = otherDynamicMessage.oneofCases[i];
/*     */           }
/* 363 */           else if (otherDynamicMessage.oneofCases[i] != null && this.oneofCases[i] != otherDynamicMessage
/* 364 */             .oneofCases[i]) {
/* 365 */             this.fields.clearField(this.oneofCases[i]);
/* 366 */             this.oneofCases[i] = otherDynamicMessage.oneofCases[i];
/*     */           } 
/*     */         } 
/*     */         
/* 370 */         return this;
/*     */       } 
/* 372 */       return super.mergeFrom(other);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public DynamicMessage build() {
/* 378 */       if (!isInitialized()) {
/* 379 */         throw newUninitializedMessageException(new DynamicMessage(this.type, this.fields, 
/*     */ 
/*     */ 
/*     */               
/* 383 */               (Descriptors.FieldDescriptor[])Arrays.copyOf(this.oneofCases, this.oneofCases.length), this.unknownFields));
/*     */       }
/*     */       
/* 386 */       return buildPartial();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private DynamicMessage buildParsed() throws InvalidProtocolBufferException {
/* 394 */       if (!isInitialized()) {
/* 395 */         throw newUninitializedMessageException(new DynamicMessage(this.type, this.fields, 
/*     */ 
/*     */ 
/*     */               
/* 399 */               (Descriptors.FieldDescriptor[])Arrays.copyOf(this.oneofCases, this.oneofCases.length), this.unknownFields))
/*     */           
/* 401 */           .asInvalidProtocolBufferException();
/*     */       }
/* 403 */       return buildPartial();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public DynamicMessage buildPartial() {
/* 409 */       if (this.type.getOptions().getMapEntry()) {
/* 410 */         for (Descriptors.FieldDescriptor field : this.type.getFields()) {
/* 411 */           if (field.isOptional() && !this.fields.hasField(field)) {
/* 412 */             if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
/* 413 */               this.fields.setField(field, DynamicMessage.getDefaultInstance(field.getMessageType())); continue;
/*     */             } 
/* 415 */             this.fields.setField(field, field.getDefaultValue());
/*     */           } 
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/* 421 */       this.fields.makeImmutable();
/*     */ 
/*     */       
/* 424 */       DynamicMessage result = new DynamicMessage(this.type, this.fields, Arrays.<Descriptors.FieldDescriptor>copyOf(this.oneofCases, this.oneofCases.length), this.unknownFields);
/* 425 */       return result;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder clone() {
/* 430 */       Builder result = new Builder(this.type);
/* 431 */       result.fields.mergeFrom(this.fields);
/* 432 */       result.mergeUnknownFields(this.unknownFields);
/* 433 */       System.arraycopy(this.oneofCases, 0, result.oneofCases, 0, this.oneofCases.length);
/* 434 */       return result;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isInitialized() {
/* 439 */       return DynamicMessage.isInitialized(this.type, this.fields);
/*     */     }
/*     */ 
/*     */     
/*     */     public Descriptors.Descriptor getDescriptorForType() {
/* 444 */       return this.type;
/*     */     }
/*     */ 
/*     */     
/*     */     public DynamicMessage getDefaultInstanceForType() {
/* 449 */       return DynamicMessage.getDefaultInstance(this.type);
/*     */     }
/*     */ 
/*     */     
/*     */     public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
/* 454 */       return this.fields.getAllFields();
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder newBuilderForField(Descriptors.FieldDescriptor field) {
/* 459 */       verifyContainingType(field);
/*     */       
/* 461 */       if (field.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
/* 462 */         throw new IllegalArgumentException("newBuilderForField is only valid for fields with message type.");
/*     */       }
/*     */ 
/*     */       
/* 466 */       return new Builder(field.getMessageType());
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean hasOneof(Descriptors.OneofDescriptor oneof) {
/* 471 */       verifyOneofContainingType(oneof);
/* 472 */       Descriptors.FieldDescriptor field = this.oneofCases[oneof.getIndex()];
/* 473 */       if (field == null) {
/* 474 */         return false;
/*     */       }
/* 476 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneof) {
/* 481 */       verifyOneofContainingType(oneof);
/* 482 */       return this.oneofCases[oneof.getIndex()];
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
/* 487 */       verifyOneofContainingType(oneof);
/* 488 */       Descriptors.FieldDescriptor field = this.oneofCases[oneof.getIndex()];
/* 489 */       if (field != null) {
/* 490 */         clearField(field);
/*     */       }
/* 492 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean hasField(Descriptors.FieldDescriptor field) {
/* 497 */       verifyContainingType(field);
/* 498 */       return this.fields.hasField(field);
/*     */     }
/*     */ 
/*     */     
/*     */     public Object getField(Descriptors.FieldDescriptor field) {
/* 503 */       verifyContainingType(field);
/* 504 */       Object<?> result = (Object<?>)this.fields.getField(field);
/* 505 */       if (result == null) {
/* 506 */         if (field.isRepeated()) {
/* 507 */           result = Collections.emptyList();
/* 508 */         } else if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
/* 509 */           result = (Object<?>)DynamicMessage.getDefaultInstance(field.getMessageType());
/*     */         } else {
/* 511 */           result = (Object<?>)field.getDefaultValue();
/*     */         } 
/*     */       }
/* 514 */       return result;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder setField(Descriptors.FieldDescriptor field, Object value) {
/* 519 */       verifyContainingType(field);
/* 520 */       ensureIsMutable();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 526 */       if (field.getType() == Descriptors.FieldDescriptor.Type.ENUM) {
/* 527 */         ensureEnumValueDescriptor(field, value);
/*     */       }
/* 529 */       Descriptors.OneofDescriptor oneofDescriptor = field.getContainingOneof();
/* 530 */       if (oneofDescriptor != null) {
/* 531 */         int index = oneofDescriptor.getIndex();
/* 532 */         Descriptors.FieldDescriptor oldField = this.oneofCases[index];
/* 533 */         if (oldField != null && oldField != field) {
/* 534 */           this.fields.clearField(oldField);
/*     */         }
/* 536 */         this.oneofCases[index] = field;
/* 537 */       } else if (field.getFile().getSyntax() == Descriptors.FileDescriptor.Syntax.PROTO3 && 
/* 538 */         !field.isRepeated() && field
/* 539 */         .getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE && value
/* 540 */         .equals(field.getDefaultValue())) {
/*     */         
/* 542 */         this.fields.clearField(field);
/* 543 */         return this;
/*     */       } 
/*     */       
/* 546 */       this.fields.setField(field, value);
/* 547 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder clearField(Descriptors.FieldDescriptor field) {
/* 552 */       verifyContainingType(field);
/* 553 */       ensureIsMutable();
/* 554 */       Descriptors.OneofDescriptor oneofDescriptor = field.getContainingOneof();
/* 555 */       if (oneofDescriptor != null) {
/* 556 */         int index = oneofDescriptor.getIndex();
/* 557 */         if (this.oneofCases[index] == field) {
/* 558 */           this.oneofCases[index] = null;
/*     */         }
/*     */       } 
/* 561 */       this.fields.clearField(field);
/* 562 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getRepeatedFieldCount(Descriptors.FieldDescriptor field) {
/* 567 */       verifyContainingType(field);
/* 568 */       return this.fields.getRepeatedFieldCount(field);
/*     */     }
/*     */ 
/*     */     
/*     */     public Object getRepeatedField(Descriptors.FieldDescriptor field, int index) {
/* 573 */       verifyContainingType(field);
/* 574 */       return this.fields.getRepeatedField(field, index);
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
/* 579 */       verifyContainingType(field);
/* 580 */       ensureIsMutable();
/* 581 */       this.fields.setRepeatedField(field, index, value);
/* 582 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
/* 587 */       verifyContainingType(field);
/* 588 */       ensureIsMutable();
/* 589 */       this.fields.addRepeatedField(field, value);
/* 590 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public UnknownFieldSet getUnknownFields() {
/* 595 */       return this.unknownFields;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder setUnknownFields(UnknownFieldSet unknownFields) {
/* 600 */       this.unknownFields = unknownFields;
/* 601 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
/* 606 */       this
/* 607 */         .unknownFields = UnknownFieldSet.newBuilder(this.unknownFields).mergeFrom(unknownFields).build();
/* 608 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     private void verifyContainingType(Descriptors.FieldDescriptor field) {
/* 613 */       if (field.getContainingType() != this.type) {
/* 614 */         throw new IllegalArgumentException("FieldDescriptor does not match message type.");
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     private void verifyOneofContainingType(Descriptors.OneofDescriptor oneof) {
/* 620 */       if (oneof.getContainingType() != this.type) {
/* 621 */         throw new IllegalArgumentException("OneofDescriptor does not match message type.");
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     private void ensureSingularEnumValueDescriptor(Descriptors.FieldDescriptor field, Object value) {
/* 627 */       Internal.checkNotNull(value);
/* 628 */       if (!(value instanceof Descriptors.EnumValueDescriptor)) {
/* 629 */         throw new IllegalArgumentException("DynamicMessage should use EnumValueDescriptor to set Enum Value.");
/*     */       }
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
/*     */     private void ensureEnumValueDescriptor(Descriptors.FieldDescriptor field, Object value) {
/* 645 */       if (field.isRepeated()) {
/* 646 */         for (Object item : value) {
/* 647 */           ensureSingularEnumValueDescriptor(field, item);
/*     */         }
/*     */       } else {
/* 650 */         ensureSingularEnumValueDescriptor(field, value);
/*     */       } 
/*     */     }
/*     */     
/*     */     private void ensureIsMutable() {
/* 655 */       if (this.fields.isImmutable()) {
/* 656 */         this.fields = this.fields.clone();
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Message.Builder getFieldBuilder(Descriptors.FieldDescriptor field) {
/* 663 */       throw new UnsupportedOperationException("getFieldBuilder() called on a dynamic message type.");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Message.Builder getRepeatedFieldBuilder(Descriptors.FieldDescriptor field, int index) {
/* 670 */       throw new UnsupportedOperationException("getRepeatedFieldBuilder() called on a dynamic message type.");
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\DynamicMessage.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */