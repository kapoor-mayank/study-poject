/*      */ package com.google.protobuf;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.lang.reflect.Field;
/*      */ import java.util.Arrays;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import sun.misc.Unsafe;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ final class MessageSchema<T>
/*      */   implements Schema<T>
/*      */ {
/*      */   private static final int INTS_PER_FIELD = 3;
/*      */   private static final int OFFSET_BITS = 20;
/*      */   private static final int OFFSET_MASK = 1048575;
/*      */   private static final int FIELD_TYPE_MASK = 267386880;
/*      */   private static final int REQUIRED_MASK = 268435456;
/*      */   private static final int ENFORCE_UTF8_MASK = 536870912;
/*      */   private static final int NO_PRESENCE_SENTINEL = 1048575;
/*   93 */   private static final int[] EMPTY_INT_ARRAY = new int[0];
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final int ONEOF_TYPE_OFFSET = 51;
/*      */ 
/*      */ 
/*      */   
/*  102 */   private static final Unsafe UNSAFE = UnsafeUtil.getUnsafe();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final int[] buffer;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final Object[] objects;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final int minFieldNumber;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final int maxFieldNumber;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final MessageLite defaultInstance;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final boolean hasExtensions;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final boolean lite;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final boolean proto3;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final boolean useCachedSizeField;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final int[] intArray;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final int checkInitializedCount;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final int repeatedFieldOffsetStart;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final NewInstanceSchema newInstanceSchema;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final ListFieldSchema listFieldSchema;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final UnknownFieldSchema<?, ?> unknownFieldSchema;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final ExtensionSchema<?> extensionSchema;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final MapFieldSchema mapFieldSchema;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private MessageSchema(int[] buffer, Object[] objects, int minFieldNumber, int maxFieldNumber, MessageLite defaultInstance, boolean proto3, boolean useCachedSizeField, int[] intArray, int checkInitialized, int mapFieldPositions, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
/*  196 */     this.buffer = buffer;
/*  197 */     this.objects = objects;
/*  198 */     this.minFieldNumber = minFieldNumber;
/*  199 */     this.maxFieldNumber = maxFieldNumber;
/*      */     
/*  201 */     this.lite = defaultInstance instanceof GeneratedMessageLite;
/*  202 */     this.proto3 = proto3;
/*  203 */     this.hasExtensions = (extensionSchema != null && extensionSchema.hasExtensions(defaultInstance));
/*  204 */     this.useCachedSizeField = useCachedSizeField;
/*      */     
/*  206 */     this.intArray = intArray;
/*  207 */     this.checkInitializedCount = checkInitialized;
/*  208 */     this.repeatedFieldOffsetStart = mapFieldPositions;
/*      */     
/*  210 */     this.newInstanceSchema = newInstanceSchema;
/*  211 */     this.listFieldSchema = listFieldSchema;
/*  212 */     this.unknownFieldSchema = unknownFieldSchema;
/*  213 */     this.extensionSchema = extensionSchema;
/*  214 */     this.defaultInstance = defaultInstance;
/*  215 */     this.mapFieldSchema = mapFieldSchema;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static <T> MessageSchema<T> newSchema(Class<T> messageClass, MessageInfo messageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
/*  226 */     if (messageInfo instanceof RawMessageInfo) {
/*  227 */       return newSchemaForRawMessageInfo((RawMessageInfo)messageInfo, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  236 */     return newSchemaForMessageInfo((StructuralMessageInfo)messageInfo, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static <T> MessageSchema<T> newSchemaForRawMessageInfo(RawMessageInfo messageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
/*      */     int oneofCount, minFieldNumber, maxFieldNumber, numEntries, mapFieldCount, checkInitialized, intArray[], objectsPosition;
/*  253 */     boolean isProto3 = (messageInfo.getSyntax() == ProtoSyntax.PROTO3);
/*      */     
/*  255 */     String info = messageInfo.getStringInfo();
/*  256 */     int length = info.length();
/*  257 */     int i = 0;
/*      */     
/*  259 */     int next = info.charAt(i++);
/*  260 */     if (next >= 55296) {
/*  261 */       int result = next & 0x1FFF;
/*  262 */       int shift = 13;
/*  263 */       while ((next = info.charAt(i++)) >= 55296) {
/*  264 */         result |= (next & 0x1FFF) << shift;
/*  265 */         shift += 13;
/*      */       } 
/*  267 */       next = result | next << shift;
/*      */     } 
/*  269 */     int unusedFlags = next;
/*      */     
/*  271 */     next = info.charAt(i++);
/*  272 */     if (next >= 55296) {
/*  273 */       int result = next & 0x1FFF;
/*  274 */       int shift = 13;
/*  275 */       while ((next = info.charAt(i++)) >= 55296) {
/*  276 */         result |= (next & 0x1FFF) << shift;
/*  277 */         shift += 13;
/*      */       } 
/*  279 */       next = result | next << shift;
/*      */     } 
/*  281 */     int fieldCount = next;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  293 */     if (fieldCount == 0) {
/*  294 */       oneofCount = 0;
/*  295 */       int hasBitsCount = 0;
/*  296 */       minFieldNumber = 0;
/*  297 */       maxFieldNumber = 0;
/*  298 */       numEntries = 0;
/*  299 */       mapFieldCount = 0;
/*  300 */       int repeatedFieldCount = 0;
/*  301 */       checkInitialized = 0;
/*  302 */       intArray = EMPTY_INT_ARRAY;
/*  303 */       objectsPosition = 0;
/*      */     } else {
/*  305 */       next = info.charAt(i++);
/*  306 */       if (next >= 55296) {
/*  307 */         int result = next & 0x1FFF;
/*  308 */         int shift = 13;
/*  309 */         while ((next = info.charAt(i++)) >= 55296) {
/*  310 */           result |= (next & 0x1FFF) << shift;
/*  311 */           shift += 13;
/*      */         } 
/*  313 */         next = result | next << shift;
/*      */       } 
/*  315 */       oneofCount = next;
/*      */       
/*  317 */       next = info.charAt(i++);
/*  318 */       if (next >= 55296) {
/*  319 */         int result = next & 0x1FFF;
/*  320 */         int shift = 13;
/*  321 */         while ((next = info.charAt(i++)) >= 55296) {
/*  322 */           result |= (next & 0x1FFF) << shift;
/*  323 */           shift += 13;
/*      */         } 
/*  325 */         next = result | next << shift;
/*      */       } 
/*  327 */       int hasBitsCount = next;
/*      */       
/*  329 */       next = info.charAt(i++);
/*  330 */       if (next >= 55296) {
/*  331 */         int result = next & 0x1FFF;
/*  332 */         int shift = 13;
/*  333 */         while ((next = info.charAt(i++)) >= 55296) {
/*  334 */           result |= (next & 0x1FFF) << shift;
/*  335 */           shift += 13;
/*      */         } 
/*  337 */         next = result | next << shift;
/*      */       } 
/*  339 */       minFieldNumber = next;
/*      */       
/*  341 */       next = info.charAt(i++);
/*  342 */       if (next >= 55296) {
/*  343 */         int result = next & 0x1FFF;
/*  344 */         int shift = 13;
/*  345 */         while ((next = info.charAt(i++)) >= 55296) {
/*  346 */           result |= (next & 0x1FFF) << shift;
/*  347 */           shift += 13;
/*      */         } 
/*  349 */         next = result | next << shift;
/*      */       } 
/*  351 */       maxFieldNumber = next;
/*      */       
/*  353 */       next = info.charAt(i++);
/*  354 */       if (next >= 55296) {
/*  355 */         int result = next & 0x1FFF;
/*  356 */         int shift = 13;
/*  357 */         while ((next = info.charAt(i++)) >= 55296) {
/*  358 */           result |= (next & 0x1FFF) << shift;
/*  359 */           shift += 13;
/*      */         } 
/*  361 */         next = result | next << shift;
/*      */       } 
/*  363 */       numEntries = next;
/*      */       
/*  365 */       next = info.charAt(i++);
/*  366 */       if (next >= 55296) {
/*  367 */         int result = next & 0x1FFF;
/*  368 */         int shift = 13;
/*  369 */         while ((next = info.charAt(i++)) >= 55296) {
/*  370 */           result |= (next & 0x1FFF) << shift;
/*  371 */           shift += 13;
/*      */         } 
/*  373 */         next = result | next << shift;
/*      */       } 
/*  375 */       mapFieldCount = next;
/*      */       
/*  377 */       next = info.charAt(i++);
/*  378 */       if (next >= 55296) {
/*  379 */         int result = next & 0x1FFF;
/*  380 */         int shift = 13;
/*  381 */         while ((next = info.charAt(i++)) >= 55296) {
/*  382 */           result |= (next & 0x1FFF) << shift;
/*  383 */           shift += 13;
/*      */         } 
/*  385 */         next = result | next << shift;
/*      */       } 
/*  387 */       int repeatedFieldCount = next;
/*      */       
/*  389 */       next = info.charAt(i++);
/*  390 */       if (next >= 55296) {
/*  391 */         int result = next & 0x1FFF;
/*  392 */         int shift = 13;
/*  393 */         while ((next = info.charAt(i++)) >= 55296) {
/*  394 */           result |= (next & 0x1FFF) << shift;
/*  395 */           shift += 13;
/*      */         } 
/*  397 */         next = result | next << shift;
/*      */       } 
/*  399 */       checkInitialized = next;
/*  400 */       intArray = new int[checkInitialized + mapFieldCount + repeatedFieldCount];
/*      */       
/*  402 */       objectsPosition = oneofCount * 2 + hasBitsCount;
/*      */     } 
/*      */     
/*  405 */     Unsafe unsafe = UNSAFE;
/*  406 */     Object[] messageInfoObjects = messageInfo.getObjects();
/*  407 */     int checkInitializedPosition = 0;
/*  408 */     Class<?> messageClass = messageInfo.getDefaultInstance().getClass();
/*  409 */     int[] buffer = new int[numEntries * 3];
/*  410 */     Object[] objects = new Object[numEntries * 2];
/*      */     
/*  412 */     int mapFieldIndex = checkInitialized;
/*  413 */     int repeatedFieldIndex = checkInitialized + mapFieldCount;
/*      */     
/*  415 */     int bufferIndex = 0;
/*  416 */     while (i < length) {
/*      */       int fieldOffset, presenceMaskShift, presenceFieldOffset;
/*      */ 
/*      */ 
/*      */       
/*  421 */       next = info.charAt(i++);
/*  422 */       if (next >= 55296) {
/*  423 */         int result = next & 0x1FFF;
/*  424 */         int shift = 13;
/*  425 */         while ((next = info.charAt(i++)) >= 55296) {
/*  426 */           result |= (next & 0x1FFF) << shift;
/*  427 */           shift += 13;
/*      */         } 
/*  429 */         next = result | next << shift;
/*      */       } 
/*  431 */       int fieldNumber = next;
/*      */       
/*  433 */       next = info.charAt(i++);
/*  434 */       if (next >= 55296) {
/*  435 */         int result = next & 0x1FFF;
/*  436 */         int shift = 13;
/*  437 */         while ((next = info.charAt(i++)) >= 55296) {
/*  438 */           result |= (next & 0x1FFF) << shift;
/*  439 */           shift += 13;
/*      */         } 
/*  441 */         next = result | next << shift;
/*      */       } 
/*  443 */       int fieldTypeWithExtraBits = next;
/*  444 */       int fieldType = fieldTypeWithExtraBits & 0xFF;
/*      */       
/*  446 */       if ((fieldTypeWithExtraBits & 0x400) != 0) {
/*  447 */         intArray[checkInitializedPosition++] = bufferIndex;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  455 */       if (fieldType >= 51) {
/*  456 */         Field oneofField, oneofCaseField; next = info.charAt(i++);
/*  457 */         if (next >= 55296) {
/*  458 */           int result = next & 0x1FFF;
/*  459 */           int shift = 13;
/*  460 */           while ((next = info.charAt(i++)) >= 55296) {
/*  461 */             result |= (next & 0x1FFF) << shift;
/*  462 */             shift += 13;
/*      */           } 
/*  464 */           next = result | next << shift;
/*      */         } 
/*  466 */         int oneofIndex = next;
/*      */         
/*  468 */         int oneofFieldType = fieldType - 51;
/*  469 */         if (oneofFieldType == 9 || oneofFieldType == 17) {
/*      */           
/*  471 */           objects[bufferIndex / 3 * 2 + 1] = messageInfoObjects[objectsPosition++];
/*  472 */         } else if (oneofFieldType == 12 && 
/*  473 */           !isProto3) {
/*  474 */           objects[bufferIndex / 3 * 2 + 1] = messageInfoObjects[objectsPosition++];
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  479 */         int index = oneofIndex * 2;
/*  480 */         Object o = messageInfoObjects[index];
/*  481 */         if (o instanceof Field) {
/*  482 */           oneofField = (Field)o;
/*      */         } else {
/*  484 */           oneofField = reflectField(messageClass, (String)o);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  489 */           messageInfoObjects[index] = oneofField;
/*      */         } 
/*      */         
/*  492 */         fieldOffset = (int)unsafe.objectFieldOffset(oneofField);
/*      */ 
/*      */         
/*  495 */         index++;
/*  496 */         o = messageInfoObjects[index];
/*  497 */         if (o instanceof Field) {
/*  498 */           oneofCaseField = (Field)o;
/*      */         } else {
/*  500 */           oneofCaseField = reflectField(messageClass, (String)o);
/*  501 */           messageInfoObjects[index] = oneofCaseField;
/*      */         } 
/*      */         
/*  504 */         presenceFieldOffset = (int)unsafe.objectFieldOffset(oneofCaseField);
/*  505 */         presenceMaskShift = 0;
/*      */       } else {
/*  507 */         Field field = reflectField(messageClass, (String)messageInfoObjects[objectsPosition++]);
/*  508 */         if (fieldType == 9 || fieldType == 17) {
/*  509 */           objects[bufferIndex / 3 * 2 + 1] = field.getType();
/*  510 */         } else if (fieldType == 27 || fieldType == 49) {
/*      */           
/*  512 */           objects[bufferIndex / 3 * 2 + 1] = messageInfoObjects[objectsPosition++];
/*  513 */         } else if (fieldType == 12 || fieldType == 30 || fieldType == 44) {
/*      */ 
/*      */           
/*  516 */           if (!isProto3) {
/*  517 */             objects[bufferIndex / 3 * 2 + 1] = messageInfoObjects[objectsPosition++];
/*      */           }
/*  519 */         } else if (fieldType == 50) {
/*  520 */           intArray[mapFieldIndex++] = bufferIndex;
/*  521 */           objects[bufferIndex / 3 * 2] = messageInfoObjects[objectsPosition++];
/*  522 */           if ((fieldTypeWithExtraBits & 0x800) != 0) {
/*  523 */             objects[bufferIndex / 3 * 2 + 1] = messageInfoObjects[objectsPosition++];
/*      */           }
/*      */         } 
/*      */         
/*  527 */         fieldOffset = (int)unsafe.objectFieldOffset(field);
/*  528 */         boolean hasHasBit = ((fieldTypeWithExtraBits & 0x1000) == 4096);
/*  529 */         if (hasHasBit && fieldType <= 17) {
/*  530 */           Field hasBitsField; next = info.charAt(i++);
/*  531 */           if (next >= 55296) {
/*  532 */             int result = next & 0x1FFF;
/*  533 */             int shift = 13;
/*  534 */             while ((next = info.charAt(i++)) >= 55296) {
/*  535 */               result |= (next & 0x1FFF) << shift;
/*  536 */               shift += 13;
/*      */             } 
/*  538 */             next = result | next << shift;
/*      */           } 
/*  540 */           int hasBitsIndex = next;
/*      */ 
/*      */           
/*  543 */           int index = oneofCount * 2 + hasBitsIndex / 32;
/*  544 */           Object o = messageInfoObjects[index];
/*  545 */           if (o instanceof Field) {
/*  546 */             hasBitsField = (Field)o;
/*      */           } else {
/*  548 */             hasBitsField = reflectField(messageClass, (String)o);
/*  549 */             messageInfoObjects[index] = hasBitsField;
/*      */           } 
/*      */           
/*  552 */           presenceFieldOffset = (int)unsafe.objectFieldOffset(hasBitsField);
/*  553 */           presenceMaskShift = hasBitsIndex % 32;
/*      */         } else {
/*  555 */           presenceFieldOffset = 1048575;
/*  556 */           presenceMaskShift = 0;
/*      */         } 
/*      */         
/*  559 */         if (fieldType >= 18 && fieldType <= 49)
/*      */         {
/*      */           
/*  562 */           intArray[repeatedFieldIndex++] = fieldOffset;
/*      */         }
/*      */       } 
/*      */       
/*  566 */       buffer[bufferIndex++] = fieldNumber;
/*  567 */       buffer[bufferIndex++] = (((fieldTypeWithExtraBits & 0x200) != 0) ? true : false) | (((fieldTypeWithExtraBits & 0x100) != 0) ? true : false) | fieldType << 20 | fieldOffset;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  572 */       buffer[bufferIndex++] = presenceMaskShift << 20 | presenceFieldOffset;
/*      */     } 
/*      */     
/*  575 */     return new MessageSchema<>(buffer, objects, minFieldNumber, maxFieldNumber, messageInfo
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  580 */         .getDefaultInstance(), isProto3, false, intArray, checkInitialized, checkInitialized + mapFieldCount, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Field reflectField(Class<?> messageClass, String fieldName) {
/*      */     try {
/*  595 */       return messageClass.getDeclaredField(fieldName);
/*  596 */     } catch (NoSuchFieldException e) {
/*      */ 
/*      */       
/*  599 */       Field[] fields = messageClass.getDeclaredFields();
/*  600 */       for (Field field : fields) {
/*  601 */         if (fieldName.equals(field.getName())) {
/*  602 */           return field;
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  608 */       throw new RuntimeException("Field " + fieldName + " for " + messageClass
/*      */ 
/*      */ 
/*      */           
/*  612 */           .getName() + " not found. Known fields are " + 
/*      */           
/*  614 */           Arrays.toString(fields));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static <T> MessageSchema<T> newSchemaForMessageInfo(StructuralMessageInfo messageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
/*      */     int minFieldNumber, maxFieldNumber;
/*  625 */     boolean isProto3 = (messageInfo.getSyntax() == ProtoSyntax.PROTO3);
/*  626 */     FieldInfo[] fis = messageInfo.getFields();
/*      */ 
/*      */     
/*  629 */     if (fis.length == 0) {
/*  630 */       minFieldNumber = 0;
/*  631 */       maxFieldNumber = 0;
/*      */     } else {
/*  633 */       minFieldNumber = fis[0].getFieldNumber();
/*  634 */       maxFieldNumber = fis[fis.length - 1].getFieldNumber();
/*      */     } 
/*      */     
/*  637 */     int numEntries = fis.length;
/*      */     
/*  639 */     int[] buffer = new int[numEntries * 3];
/*  640 */     Object[] objects = new Object[numEntries * 2];
/*      */     
/*  642 */     int mapFieldCount = 0;
/*  643 */     int repeatedFieldCount = 0;
/*  644 */     for (FieldInfo fi : fis) {
/*  645 */       if (fi.getType() == FieldType.MAP) {
/*  646 */         mapFieldCount++;
/*  647 */       } else if (fi.getType().id() >= 18 && fi.getType().id() <= 49) {
/*      */ 
/*      */         
/*  650 */         repeatedFieldCount++;
/*      */       } 
/*      */     } 
/*  653 */     int[] mapFieldPositions = (mapFieldCount > 0) ? new int[mapFieldCount] : null;
/*  654 */     int[] repeatedFieldOffsets = (repeatedFieldCount > 0) ? new int[repeatedFieldCount] : null;
/*  655 */     mapFieldCount = 0;
/*  656 */     repeatedFieldCount = 0;
/*      */     
/*  658 */     int[] checkInitialized = messageInfo.getCheckInitialized();
/*  659 */     if (checkInitialized == null) {
/*  660 */       checkInitialized = EMPTY_INT_ARRAY;
/*      */     }
/*  662 */     int checkInitializedIndex = 0;
/*      */     
/*  664 */     int fieldIndex = 0;
/*  665 */     for (int bufferIndex = 0; fieldIndex < fis.length; bufferIndex += 3) {
/*  666 */       FieldInfo fi = fis[fieldIndex];
/*  667 */       int fieldNumber = fi.getFieldNumber();
/*      */ 
/*      */ 
/*      */       
/*  671 */       storeFieldData(fi, buffer, bufferIndex, objects);
/*      */ 
/*      */       
/*  674 */       if (checkInitializedIndex < checkInitialized.length && checkInitialized[checkInitializedIndex] == fieldNumber)
/*      */       {
/*  676 */         checkInitialized[checkInitializedIndex++] = bufferIndex;
/*      */       }
/*      */       
/*  679 */       if (fi.getType() == FieldType.MAP) {
/*  680 */         mapFieldPositions[mapFieldCount++] = bufferIndex;
/*  681 */       } else if (fi.getType().id() >= 18 && fi.getType().id() <= 49) {
/*      */ 
/*      */         
/*  684 */         repeatedFieldOffsets[repeatedFieldCount++] = 
/*  685 */           (int)UnsafeUtil.objectFieldOffset(fi.getField());
/*      */       } 
/*      */       
/*  688 */       fieldIndex++;
/*      */     } 
/*      */     
/*  691 */     if (mapFieldPositions == null) {
/*  692 */       mapFieldPositions = EMPTY_INT_ARRAY;
/*      */     }
/*  694 */     if (repeatedFieldOffsets == null) {
/*  695 */       repeatedFieldOffsets = EMPTY_INT_ARRAY;
/*      */     }
/*  697 */     int[] combined = new int[checkInitialized.length + mapFieldPositions.length + repeatedFieldOffsets.length];
/*      */     
/*  699 */     System.arraycopy(checkInitialized, 0, combined, 0, checkInitialized.length);
/*  700 */     System.arraycopy(mapFieldPositions, 0, combined, checkInitialized.length, mapFieldPositions.length);
/*      */     
/*  702 */     System.arraycopy(repeatedFieldOffsets, 0, combined, checkInitialized.length + mapFieldPositions.length, repeatedFieldOffsets.length);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  709 */     return new MessageSchema<>(buffer, objects, minFieldNumber, maxFieldNumber, messageInfo
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  714 */         .getDefaultInstance(), isProto3, true, combined, checkInitialized.length, checkInitialized.length + mapFieldPositions.length, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void storeFieldData(FieldInfo fi, int[] buffer, int bufferIndex, Object[] objects) {
/*      */     int fieldOffset, typeId, presenceMaskShift, presenceFieldOffset;
/*  734 */     OneofInfo oneof = fi.getOneof();
/*  735 */     if (oneof != null) {
/*  736 */       typeId = fi.getType().id() + 51;
/*  737 */       fieldOffset = (int)UnsafeUtil.objectFieldOffset(oneof.getValueField());
/*  738 */       presenceFieldOffset = (int)UnsafeUtil.objectFieldOffset(oneof.getCaseField());
/*  739 */       presenceMaskShift = 0;
/*      */     } else {
/*  741 */       FieldType type = fi.getType();
/*  742 */       fieldOffset = (int)UnsafeUtil.objectFieldOffset(fi.getField());
/*  743 */       typeId = type.id();
/*  744 */       if (!type.isList() && !type.isMap()) {
/*  745 */         Field presenceField = fi.getPresenceField();
/*  746 */         if (presenceField == null) {
/*  747 */           presenceFieldOffset = 1048575;
/*      */         } else {
/*  749 */           presenceFieldOffset = (int)UnsafeUtil.objectFieldOffset(presenceField);
/*      */         } 
/*  751 */         presenceMaskShift = Integer.numberOfTrailingZeros(fi.getPresenceMask());
/*      */       }
/*  753 */       else if (fi.getCachedSizeField() == null) {
/*  754 */         presenceFieldOffset = 0;
/*  755 */         presenceMaskShift = 0;
/*      */       } else {
/*  757 */         presenceFieldOffset = (int)UnsafeUtil.objectFieldOffset(fi.getCachedSizeField());
/*  758 */         presenceMaskShift = 0;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  763 */     buffer[bufferIndex] = fi.getFieldNumber();
/*  764 */     buffer[bufferIndex + 1] = (
/*  765 */       fi.isEnforceUtf8() ? true : false) | (
/*  766 */       fi.isRequired() ? true : false) | typeId << 20 | fieldOffset;
/*      */ 
/*      */     
/*  769 */     buffer[bufferIndex + 2] = presenceMaskShift << 20 | presenceFieldOffset;
/*      */     
/*  771 */     Object<?> messageFieldClass = (Object<?>)fi.getMessageFieldClass();
/*  772 */     if (fi.getMapDefaultEntry() != null) {
/*  773 */       objects[bufferIndex / 3 * 2] = fi.getMapDefaultEntry();
/*  774 */       if (messageFieldClass != null) {
/*  775 */         objects[bufferIndex / 3 * 2 + 1] = messageFieldClass;
/*  776 */       } else if (fi.getEnumVerifier() != null) {
/*  777 */         objects[bufferIndex / 3 * 2 + 1] = fi.getEnumVerifier();
/*      */       }
/*      */     
/*  780 */     } else if (messageFieldClass != null) {
/*  781 */       objects[bufferIndex / 3 * 2 + 1] = messageFieldClass;
/*  782 */     } else if (fi.getEnumVerifier() != null) {
/*  783 */       objects[bufferIndex / 3 * 2 + 1] = fi.getEnumVerifier();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public T newInstance() {
/*  791 */     return (T)this.newInstanceSchema.newInstance(this.defaultInstance);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean equals(T message, T other) {
/*  796 */     int bufferLength = this.buffer.length;
/*  797 */     for (int pos = 0; pos < bufferLength; pos += 3) {
/*  798 */       if (!equals(message, other, pos)) {
/*  799 */         return false;
/*      */       }
/*      */     } 
/*      */     
/*  803 */     Object messageUnknown = this.unknownFieldSchema.getFromMessage(message);
/*  804 */     Object otherUnknown = this.unknownFieldSchema.getFromMessage(other);
/*  805 */     if (!messageUnknown.equals(otherUnknown)) {
/*  806 */       return false;
/*      */     }
/*      */     
/*  809 */     if (this.hasExtensions) {
/*  810 */       FieldSet<?> messageExtensions = this.extensionSchema.getExtensions(message);
/*  811 */       FieldSet<?> otherExtensions = this.extensionSchema.getExtensions(other);
/*  812 */       return messageExtensions.equals(otherExtensions);
/*      */     } 
/*  814 */     return true;
/*      */   }
/*      */   
/*      */   private boolean equals(T message, T other, int pos) {
/*  818 */     int typeAndOffset = typeAndOffsetAt(pos);
/*  819 */     long offset = offset(typeAndOffset);
/*      */     
/*  821 */     switch (type(typeAndOffset)) {
/*      */       case 0:
/*  823 */         return (arePresentForEquals(message, other, pos) && 
/*  824 */           Double.doubleToLongBits(UnsafeUtil.getDouble(message, offset)) == 
/*  825 */           Double.doubleToLongBits(UnsafeUtil.getDouble(other, offset)));
/*      */       case 1:
/*  827 */         return (arePresentForEquals(message, other, pos) && 
/*  828 */           Float.floatToIntBits(UnsafeUtil.getFloat(message, offset)) == 
/*  829 */           Float.floatToIntBits(UnsafeUtil.getFloat(other, offset)));
/*      */       case 2:
/*  831 */         return (arePresentForEquals(message, other, pos) && 
/*  832 */           UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset));
/*      */       case 3:
/*  834 */         return (arePresentForEquals(message, other, pos) && 
/*  835 */           UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset));
/*      */       case 4:
/*  837 */         return (arePresentForEquals(message, other, pos) && 
/*  838 */           UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset));
/*      */       case 5:
/*  840 */         return (arePresentForEquals(message, other, pos) && 
/*  841 */           UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset));
/*      */       case 6:
/*  843 */         return (arePresentForEquals(message, other, pos) && 
/*  844 */           UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset));
/*      */       case 7:
/*  846 */         return (arePresentForEquals(message, other, pos) && 
/*  847 */           UnsafeUtil.getBoolean(message, offset) == UnsafeUtil.getBoolean(other, offset));
/*      */       case 8:
/*  849 */         return (arePresentForEquals(message, other, pos) && 
/*  850 */           SchemaUtil.safeEquals(
/*  851 */             UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset)));
/*      */       case 9:
/*  853 */         return (arePresentForEquals(message, other, pos) && 
/*  854 */           SchemaUtil.safeEquals(
/*  855 */             UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset)));
/*      */       case 10:
/*  857 */         return (arePresentForEquals(message, other, pos) && 
/*  858 */           SchemaUtil.safeEquals(
/*  859 */             UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset)));
/*      */       case 11:
/*  861 */         return (arePresentForEquals(message, other, pos) && 
/*  862 */           UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset));
/*      */       case 12:
/*  864 */         return (arePresentForEquals(message, other, pos) && 
/*  865 */           UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset));
/*      */       case 13:
/*  867 */         return (arePresentForEquals(message, other, pos) && 
/*  868 */           UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset));
/*      */       case 14:
/*  870 */         return (arePresentForEquals(message, other, pos) && 
/*  871 */           UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset));
/*      */       case 15:
/*  873 */         return (arePresentForEquals(message, other, pos) && 
/*  874 */           UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset));
/*      */       case 16:
/*  876 */         return (arePresentForEquals(message, other, pos) && 
/*  877 */           UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset));
/*      */       case 17:
/*  879 */         return (arePresentForEquals(message, other, pos) && 
/*  880 */           SchemaUtil.safeEquals(
/*  881 */             UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset)));
/*      */       
/*      */       case 18:
/*      */       case 19:
/*      */       case 20:
/*      */       case 21:
/*      */       case 22:
/*      */       case 23:
/*      */       case 24:
/*      */       case 25:
/*      */       case 26:
/*      */       case 27:
/*      */       case 28:
/*      */       case 29:
/*      */       case 30:
/*      */       case 31:
/*      */       case 32:
/*      */       case 33:
/*      */       case 34:
/*      */       case 35:
/*      */       case 36:
/*      */       case 37:
/*      */       case 38:
/*      */       case 39:
/*      */       case 40:
/*      */       case 41:
/*      */       case 42:
/*      */       case 43:
/*      */       case 44:
/*      */       case 45:
/*      */       case 46:
/*      */       case 47:
/*      */       case 48:
/*      */       case 49:
/*  915 */         return SchemaUtil.safeEquals(
/*  916 */             UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset));
/*      */       case 50:
/*  918 */         return SchemaUtil.safeEquals(
/*  919 */             UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset));
/*      */       case 51:
/*      */       case 52:
/*      */       case 53:
/*      */       case 54:
/*      */       case 55:
/*      */       case 56:
/*      */       case 57:
/*      */       case 58:
/*      */       case 59:
/*      */       case 60:
/*      */       case 61:
/*      */       case 62:
/*      */       case 63:
/*      */       case 64:
/*      */       case 65:
/*      */       case 66:
/*      */       case 67:
/*      */       case 68:
/*  938 */         return (isOneofCaseEqual(message, other, pos) && 
/*  939 */           SchemaUtil.safeEquals(
/*  940 */             UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset)));
/*      */     } 
/*      */     
/*  943 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode(T message) {
/*  949 */     int hashCode = 0;
/*  950 */     int bufferLength = this.buffer.length;
/*  951 */     for (int pos = 0; pos < bufferLength; pos += 3) {
/*  952 */       int protoHash; Object submessage; int typeAndOffset = typeAndOffsetAt(pos);
/*  953 */       int entryNumber = numberAt(pos);
/*      */       
/*  955 */       long offset = offset(typeAndOffset);
/*      */       
/*  957 */       switch (type(typeAndOffset)) {
/*      */ 
/*      */         
/*      */         case 0:
/*  961 */           hashCode = hashCode * 53 + Internal.hashLong(
/*  962 */               Double.doubleToLongBits(UnsafeUtil.getDouble(message, offset)));
/*      */           break;
/*      */         case 1:
/*  965 */           hashCode = hashCode * 53 + Float.floatToIntBits(UnsafeUtil.getFloat(message, offset));
/*      */           break;
/*      */         case 2:
/*  968 */           hashCode = hashCode * 53 + Internal.hashLong(UnsafeUtil.getLong(message, offset));
/*      */           break;
/*      */         case 3:
/*  971 */           hashCode = hashCode * 53 + Internal.hashLong(UnsafeUtil.getLong(message, offset));
/*      */           break;
/*      */         case 4:
/*  974 */           hashCode = hashCode * 53 + UnsafeUtil.getInt(message, offset);
/*      */           break;
/*      */         case 5:
/*  977 */           hashCode = hashCode * 53 + Internal.hashLong(UnsafeUtil.getLong(message, offset));
/*      */           break;
/*      */         case 6:
/*  980 */           hashCode = hashCode * 53 + UnsafeUtil.getInt(message, offset);
/*      */           break;
/*      */         case 7:
/*  983 */           hashCode = hashCode * 53 + Internal.hashBoolean(UnsafeUtil.getBoolean(message, offset));
/*      */           break;
/*      */         case 8:
/*  986 */           hashCode = hashCode * 53 + ((String)UnsafeUtil.getObject(message, offset)).hashCode();
/*      */           break;
/*      */         
/*      */         case 9:
/*  990 */           protoHash = 37;
/*  991 */           submessage = UnsafeUtil.getObject(message, offset);
/*  992 */           if (submessage != null) {
/*  993 */             protoHash = submessage.hashCode();
/*      */           }
/*  995 */           hashCode = 53 * hashCode + protoHash;
/*      */           break;
/*      */         
/*      */         case 10:
/*  999 */           hashCode = hashCode * 53 + UnsafeUtil.getObject(message, offset).hashCode();
/*      */           break;
/*      */         case 11:
/* 1002 */           hashCode = hashCode * 53 + UnsafeUtil.getInt(message, offset);
/*      */           break;
/*      */         case 12:
/* 1005 */           hashCode = hashCode * 53 + UnsafeUtil.getInt(message, offset);
/*      */           break;
/*      */         case 13:
/* 1008 */           hashCode = hashCode * 53 + UnsafeUtil.getInt(message, offset);
/*      */           break;
/*      */         case 14:
/* 1011 */           hashCode = hashCode * 53 + Internal.hashLong(UnsafeUtil.getLong(message, offset));
/*      */           break;
/*      */         case 15:
/* 1014 */           hashCode = hashCode * 53 + UnsafeUtil.getInt(message, offset);
/*      */           break;
/*      */         case 16:
/* 1017 */           hashCode = hashCode * 53 + Internal.hashLong(UnsafeUtil.getLong(message, offset));
/*      */           break;
/*      */ 
/*      */         
/*      */         case 17:
/* 1022 */           protoHash = 37;
/* 1023 */           submessage = UnsafeUtil.getObject(message, offset);
/* 1024 */           if (submessage != null) {
/* 1025 */             protoHash = submessage.hashCode();
/*      */           }
/* 1027 */           hashCode = 53 * hashCode + protoHash;
/*      */           break;
/*      */         
/*      */         case 18:
/*      */         case 19:
/*      */         case 20:
/*      */         case 21:
/*      */         case 22:
/*      */         case 23:
/*      */         case 24:
/*      */         case 25:
/*      */         case 26:
/*      */         case 27:
/*      */         case 28:
/*      */         case 29:
/*      */         case 30:
/*      */         case 31:
/*      */         case 32:
/*      */         case 33:
/*      */         case 34:
/*      */         case 35:
/*      */         case 36:
/*      */         case 37:
/*      */         case 38:
/*      */         case 39:
/*      */         case 40:
/*      */         case 41:
/*      */         case 42:
/*      */         case 43:
/*      */         case 44:
/*      */         case 45:
/*      */         case 46:
/*      */         case 47:
/*      */         case 48:
/*      */         case 49:
/* 1062 */           hashCode = hashCode * 53 + UnsafeUtil.getObject(message, offset).hashCode();
/*      */           break;
/*      */         case 50:
/* 1065 */           hashCode = hashCode * 53 + UnsafeUtil.getObject(message, offset).hashCode();
/*      */           break;
/*      */         case 51:
/* 1068 */           if (isOneofPresent(message, entryNumber, pos))
/*      */           {
/*      */             
/* 1071 */             hashCode = hashCode * 53 + Internal.hashLong(Double.doubleToLongBits(oneofDoubleAt(message, offset)));
/*      */           }
/*      */           break;
/*      */         case 52:
/* 1075 */           if (isOneofPresent(message, entryNumber, pos)) {
/* 1076 */             hashCode = hashCode * 53 + Float.floatToIntBits(oneofFloatAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 53:
/* 1080 */           if (isOneofPresent(message, entryNumber, pos)) {
/* 1081 */             hashCode = hashCode * 53 + Internal.hashLong(oneofLongAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 54:
/* 1085 */           if (isOneofPresent(message, entryNumber, pos)) {
/* 1086 */             hashCode = hashCode * 53 + Internal.hashLong(oneofLongAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 55:
/* 1090 */           if (isOneofPresent(message, entryNumber, pos)) {
/* 1091 */             hashCode = hashCode * 53 + oneofIntAt(message, offset);
/*      */           }
/*      */           break;
/*      */         case 56:
/* 1095 */           if (isOneofPresent(message, entryNumber, pos)) {
/* 1096 */             hashCode = hashCode * 53 + Internal.hashLong(oneofLongAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 57:
/* 1100 */           if (isOneofPresent(message, entryNumber, pos)) {
/* 1101 */             hashCode = hashCode * 53 + oneofIntAt(message, offset);
/*      */           }
/*      */           break;
/*      */         case 58:
/* 1105 */           if (isOneofPresent(message, entryNumber, pos)) {
/* 1106 */             hashCode = hashCode * 53 + Internal.hashBoolean(oneofBooleanAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 59:
/* 1110 */           if (isOneofPresent(message, entryNumber, pos))
/*      */           {
/* 1112 */             hashCode = hashCode * 53 + ((String)UnsafeUtil.getObject(message, offset)).hashCode();
/*      */           }
/*      */           break;
/*      */         case 60:
/* 1116 */           if (isOneofPresent(message, entryNumber, pos)) {
/* 1117 */             Object object = UnsafeUtil.getObject(message, offset);
/* 1118 */             hashCode = 53 * hashCode + object.hashCode();
/*      */           } 
/*      */           break;
/*      */         case 61:
/* 1122 */           if (isOneofPresent(message, entryNumber, pos)) {
/* 1123 */             hashCode = hashCode * 53 + UnsafeUtil.getObject(message, offset).hashCode();
/*      */           }
/*      */           break;
/*      */         case 62:
/* 1127 */           if (isOneofPresent(message, entryNumber, pos)) {
/* 1128 */             hashCode = hashCode * 53 + oneofIntAt(message, offset);
/*      */           }
/*      */           break;
/*      */         case 63:
/* 1132 */           if (isOneofPresent(message, entryNumber, pos)) {
/* 1133 */             hashCode = hashCode * 53 + oneofIntAt(message, offset);
/*      */           }
/*      */           break;
/*      */         case 64:
/* 1137 */           if (isOneofPresent(message, entryNumber, pos)) {
/* 1138 */             hashCode = hashCode * 53 + oneofIntAt(message, offset);
/*      */           }
/*      */           break;
/*      */         case 65:
/* 1142 */           if (isOneofPresent(message, entryNumber, pos)) {
/* 1143 */             hashCode = hashCode * 53 + Internal.hashLong(oneofLongAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 66:
/* 1147 */           if (isOneofPresent(message, entryNumber, pos)) {
/* 1148 */             hashCode = hashCode * 53 + oneofIntAt(message, offset);
/*      */           }
/*      */           break;
/*      */         case 67:
/* 1152 */           if (isOneofPresent(message, entryNumber, pos)) {
/* 1153 */             hashCode = hashCode * 53 + Internal.hashLong(oneofLongAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 68:
/* 1157 */           if (isOneofPresent(message, entryNumber, pos)) {
/* 1158 */             Object object = UnsafeUtil.getObject(message, offset);
/* 1159 */             hashCode = 53 * hashCode + object.hashCode();
/*      */           } 
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     } 
/* 1168 */     hashCode = hashCode * 53 + this.unknownFieldSchema.getFromMessage(message).hashCode();
/*      */     
/* 1170 */     if (this.hasExtensions) {
/* 1171 */       hashCode = hashCode * 53 + this.extensionSchema.getExtensions(message).hashCode();
/*      */     }
/*      */     
/* 1174 */     return hashCode;
/*      */   }
/*      */ 
/*      */   
/*      */   public void mergeFrom(T message, T other) {
/* 1179 */     if (other == null) {
/* 1180 */       throw new NullPointerException();
/*      */     }
/* 1182 */     for (int i = 0; i < this.buffer.length; i += 3)
/*      */     {
/* 1184 */       mergeSingleField(message, other, i);
/*      */     }
/*      */     
/* 1187 */     SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, message, other);
/*      */     
/* 1189 */     if (this.hasExtensions) {
/* 1190 */       SchemaUtil.mergeExtensions(this.extensionSchema, message, other);
/*      */     }
/*      */   }
/*      */   
/*      */   private void mergeSingleField(T message, T other, int pos) {
/* 1195 */     int typeAndOffset = typeAndOffsetAt(pos);
/* 1196 */     long offset = offset(typeAndOffset);
/* 1197 */     int number = numberAt(pos);
/*      */     
/* 1199 */     switch (type(typeAndOffset)) {
/*      */       case 0:
/* 1201 */         if (isFieldPresent(other, pos)) {
/* 1202 */           UnsafeUtil.putDouble(message, offset, UnsafeUtil.getDouble(other, offset));
/* 1203 */           setFieldPresent(message, pos);
/*      */         } 
/*      */         break;
/*      */       case 1:
/* 1207 */         if (isFieldPresent(other, pos)) {
/* 1208 */           UnsafeUtil.putFloat(message, offset, UnsafeUtil.getFloat(other, offset));
/* 1209 */           setFieldPresent(message, pos);
/*      */         } 
/*      */         break;
/*      */       case 2:
/* 1213 */         if (isFieldPresent(other, pos)) {
/* 1214 */           UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
/* 1215 */           setFieldPresent(message, pos);
/*      */         } 
/*      */         break;
/*      */       case 3:
/* 1219 */         if (isFieldPresent(other, pos)) {
/* 1220 */           UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
/* 1221 */           setFieldPresent(message, pos);
/*      */         } 
/*      */         break;
/*      */       case 4:
/* 1225 */         if (isFieldPresent(other, pos)) {
/* 1226 */           UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
/* 1227 */           setFieldPresent(message, pos);
/*      */         } 
/*      */         break;
/*      */       case 5:
/* 1231 */         if (isFieldPresent(other, pos)) {
/* 1232 */           UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
/* 1233 */           setFieldPresent(message, pos);
/*      */         } 
/*      */         break;
/*      */       case 6:
/* 1237 */         if (isFieldPresent(other, pos)) {
/* 1238 */           UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
/* 1239 */           setFieldPresent(message, pos);
/*      */         } 
/*      */         break;
/*      */       case 7:
/* 1243 */         if (isFieldPresent(other, pos)) {
/* 1244 */           UnsafeUtil.putBoolean(message, offset, UnsafeUtil.getBoolean(other, offset));
/* 1245 */           setFieldPresent(message, pos);
/*      */         } 
/*      */         break;
/*      */       case 8:
/* 1249 */         if (isFieldPresent(other, pos)) {
/* 1250 */           UnsafeUtil.putObject(message, offset, UnsafeUtil.getObject(other, offset));
/* 1251 */           setFieldPresent(message, pos);
/*      */         } 
/*      */         break;
/*      */       case 9:
/* 1255 */         mergeMessage(message, other, pos);
/*      */         break;
/*      */       case 10:
/* 1258 */         if (isFieldPresent(other, pos)) {
/* 1259 */           UnsafeUtil.putObject(message, offset, UnsafeUtil.getObject(other, offset));
/* 1260 */           setFieldPresent(message, pos);
/*      */         } 
/*      */         break;
/*      */       case 11:
/* 1264 */         if (isFieldPresent(other, pos)) {
/* 1265 */           UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
/* 1266 */           setFieldPresent(message, pos);
/*      */         } 
/*      */         break;
/*      */       case 12:
/* 1270 */         if (isFieldPresent(other, pos)) {
/* 1271 */           UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
/* 1272 */           setFieldPresent(message, pos);
/*      */         } 
/*      */         break;
/*      */       case 13:
/* 1276 */         if (isFieldPresent(other, pos)) {
/* 1277 */           UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
/* 1278 */           setFieldPresent(message, pos);
/*      */         } 
/*      */         break;
/*      */       case 14:
/* 1282 */         if (isFieldPresent(other, pos)) {
/* 1283 */           UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
/* 1284 */           setFieldPresent(message, pos);
/*      */         } 
/*      */         break;
/*      */       case 15:
/* 1288 */         if (isFieldPresent(other, pos)) {
/* 1289 */           UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
/* 1290 */           setFieldPresent(message, pos);
/*      */         } 
/*      */         break;
/*      */       case 16:
/* 1294 */         if (isFieldPresent(other, pos)) {
/* 1295 */           UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
/* 1296 */           setFieldPresent(message, pos);
/*      */         } 
/*      */         break;
/*      */       case 17:
/* 1300 */         mergeMessage(message, other, pos);
/*      */         break;
/*      */       case 18:
/*      */       case 19:
/*      */       case 20:
/*      */       case 21:
/*      */       case 22:
/*      */       case 23:
/*      */       case 24:
/*      */       case 25:
/*      */       case 26:
/*      */       case 27:
/*      */       case 28:
/*      */       case 29:
/*      */       case 30:
/*      */       case 31:
/*      */       case 32:
/*      */       case 33:
/*      */       case 34:
/*      */       case 35:
/*      */       case 36:
/*      */       case 37:
/*      */       case 38:
/*      */       case 39:
/*      */       case 40:
/*      */       case 41:
/*      */       case 42:
/*      */       case 43:
/*      */       case 44:
/*      */       case 45:
/*      */       case 46:
/*      */       case 47:
/*      */       case 48:
/*      */       case 49:
/* 1334 */         this.listFieldSchema.mergeListsAt(message, other, offset);
/*      */         break;
/*      */       case 50:
/* 1337 */         SchemaUtil.mergeMap(this.mapFieldSchema, message, other, offset);
/*      */         break;
/*      */       case 51:
/*      */       case 52:
/*      */       case 53:
/*      */       case 54:
/*      */       case 55:
/*      */       case 56:
/*      */       case 57:
/*      */       case 58:
/*      */       case 59:
/* 1348 */         if (isOneofPresent(other, number, pos)) {
/* 1349 */           UnsafeUtil.putObject(message, offset, UnsafeUtil.getObject(other, offset));
/* 1350 */           setOneofPresent(message, number, pos);
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 60:
/* 1355 */         mergeOneofMessage(message, other, pos);
/*      */         break;
/*      */       case 61:
/*      */       case 62:
/*      */       case 63:
/*      */       case 64:
/*      */       case 65:
/*      */       case 66:
/*      */       case 67:
/* 1364 */         if (isOneofPresent(other, number, pos)) {
/* 1365 */           UnsafeUtil.putObject(message, offset, UnsafeUtil.getObject(other, offset));
/* 1366 */           setOneofPresent(message, number, pos);
/*      */         } 
/*      */         break;
/*      */       case 68:
/* 1370 */         mergeOneofMessage(message, other, pos);
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void mergeMessage(T message, T other, int pos) {
/* 1378 */     int typeAndOffset = typeAndOffsetAt(pos);
/* 1379 */     long offset = offset(typeAndOffset);
/*      */     
/* 1381 */     if (!isFieldPresent(other, pos)) {
/*      */       return;
/*      */     }
/*      */     
/* 1385 */     Object mine = UnsafeUtil.getObject(message, offset);
/* 1386 */     Object theirs = UnsafeUtil.getObject(other, offset);
/* 1387 */     if (mine != null && theirs != null) {
/* 1388 */       Object merged = Internal.mergeMessage(mine, theirs);
/* 1389 */       UnsafeUtil.putObject(message, offset, merged);
/* 1390 */       setFieldPresent(message, pos);
/* 1391 */     } else if (theirs != null) {
/* 1392 */       UnsafeUtil.putObject(message, offset, theirs);
/* 1393 */       setFieldPresent(message, pos);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void mergeOneofMessage(T message, T other, int pos) {
/* 1398 */     int typeAndOffset = typeAndOffsetAt(pos);
/* 1399 */     int number = numberAt(pos);
/* 1400 */     long offset = offset(typeAndOffset);
/*      */     
/* 1402 */     if (!isOneofPresent(other, number, pos)) {
/*      */       return;
/*      */     }
/* 1405 */     Object mine = null;
/* 1406 */     if (isOneofPresent(message, number, pos)) {
/* 1407 */       mine = UnsafeUtil.getObject(message, offset);
/*      */     }
/* 1409 */     Object theirs = UnsafeUtil.getObject(other, offset);
/* 1410 */     if (mine != null && theirs != null) {
/* 1411 */       Object merged = Internal.mergeMessage(mine, theirs);
/* 1412 */       UnsafeUtil.putObject(message, offset, merged);
/* 1413 */       setOneofPresent(message, number, pos);
/* 1414 */     } else if (theirs != null) {
/* 1415 */       UnsafeUtil.putObject(message, offset, theirs);
/* 1416 */       setOneofPresent(message, number, pos);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public int getSerializedSize(T message) {
/* 1422 */     return this.proto3 ? getSerializedSizeProto3(message) : getSerializedSizeProto2(message);
/*      */   }
/*      */ 
/*      */   
/*      */   private int getSerializedSizeProto2(T message) {
/* 1427 */     int size = 0;
/*      */     
/* 1429 */     Unsafe unsafe = UNSAFE;
/* 1430 */     int currentPresenceFieldOffset = 1048575;
/* 1431 */     int currentPresenceField = 0;
/* 1432 */     for (int i = 0; i < this.buffer.length; i += 3) {
/* 1433 */       int fieldSize, typeAndOffset = typeAndOffsetAt(i);
/* 1434 */       int number = numberAt(i);
/*      */       
/* 1436 */       int fieldType = type(typeAndOffset);
/* 1437 */       int presenceMaskAndOffset = 0;
/* 1438 */       int presenceMask = 0;
/* 1439 */       if (fieldType <= 17) {
/* 1440 */         presenceMaskAndOffset = this.buffer[i + 2];
/* 1441 */         int presenceFieldOffset = presenceMaskAndOffset & 0xFFFFF;
/* 1442 */         presenceMask = 1 << presenceMaskAndOffset >>> 20;
/* 1443 */         if (presenceFieldOffset != currentPresenceFieldOffset) {
/* 1444 */           currentPresenceFieldOffset = presenceFieldOffset;
/* 1445 */           currentPresenceField = unsafe.getInt(message, presenceFieldOffset);
/*      */         } 
/* 1447 */       } else if (this.useCachedSizeField && fieldType >= FieldType.DOUBLE_LIST_PACKED
/* 1448 */         .id() && fieldType <= FieldType.SINT64_LIST_PACKED
/* 1449 */         .id()) {
/* 1450 */         presenceMaskAndOffset = this.buffer[i + 2] & 0xFFFFF;
/*      */       } 
/*      */       
/* 1453 */       long offset = offset(typeAndOffset);
/*      */       
/* 1455 */       switch (fieldType) {
/*      */         case 0:
/* 1457 */           if ((currentPresenceField & presenceMask) != 0) {
/* 1458 */             size += CodedOutputStream.computeDoubleSize(number, 0.0D);
/*      */           }
/*      */           break;
/*      */         case 1:
/* 1462 */           if ((currentPresenceField & presenceMask) != 0) {
/* 1463 */             size += CodedOutputStream.computeFloatSize(number, 0.0F);
/*      */           }
/*      */           break;
/*      */         case 2:
/* 1467 */           if ((currentPresenceField & presenceMask) != 0) {
/* 1468 */             size += CodedOutputStream.computeInt64Size(number, unsafe.getLong(message, offset));
/*      */           }
/*      */           break;
/*      */         case 3:
/* 1472 */           if ((currentPresenceField & presenceMask) != 0) {
/* 1473 */             size += CodedOutputStream.computeUInt64Size(number, unsafe.getLong(message, offset));
/*      */           }
/*      */           break;
/*      */         case 4:
/* 1477 */           if ((currentPresenceField & presenceMask) != 0) {
/* 1478 */             size += CodedOutputStream.computeInt32Size(number, unsafe.getInt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 5:
/* 1482 */           if ((currentPresenceField & presenceMask) != 0) {
/* 1483 */             size += CodedOutputStream.computeFixed64Size(number, 0L);
/*      */           }
/*      */           break;
/*      */         case 6:
/* 1487 */           if ((currentPresenceField & presenceMask) != 0) {
/* 1488 */             size += CodedOutputStream.computeFixed32Size(number, 0);
/*      */           }
/*      */           break;
/*      */         case 7:
/* 1492 */           if ((currentPresenceField & presenceMask) != 0) {
/* 1493 */             size += CodedOutputStream.computeBoolSize(number, true);
/*      */           }
/*      */           break;
/*      */         case 8:
/* 1497 */           if ((currentPresenceField & presenceMask) != 0) {
/* 1498 */             Object value = unsafe.getObject(message, offset);
/* 1499 */             if (value instanceof ByteString) {
/* 1500 */               size += CodedOutputStream.computeBytesSize(number, (ByteString)value); break;
/*      */             } 
/* 1502 */             size += CodedOutputStream.computeStringSize(number, (String)value);
/*      */           } 
/*      */           break;
/*      */         
/*      */         case 9:
/* 1507 */           if ((currentPresenceField & presenceMask) != 0) {
/* 1508 */             Object value = unsafe.getObject(message, offset);
/* 1509 */             size += SchemaUtil.computeSizeMessage(number, value, getMessageFieldSchema(i));
/*      */           } 
/*      */           break;
/*      */         case 10:
/* 1513 */           if ((currentPresenceField & presenceMask) != 0) {
/* 1514 */             ByteString value = (ByteString)unsafe.getObject(message, offset);
/* 1515 */             size += CodedOutputStream.computeBytesSize(number, value);
/*      */           } 
/*      */           break;
/*      */         case 11:
/* 1519 */           if ((currentPresenceField & presenceMask) != 0) {
/* 1520 */             size += CodedOutputStream.computeUInt32Size(number, unsafe.getInt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 12:
/* 1524 */           if ((currentPresenceField & presenceMask) != 0) {
/* 1525 */             size += CodedOutputStream.computeEnumSize(number, unsafe.getInt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 13:
/* 1529 */           if ((currentPresenceField & presenceMask) != 0) {
/* 1530 */             size += CodedOutputStream.computeSFixed32Size(number, 0);
/*      */           }
/*      */           break;
/*      */         case 14:
/* 1534 */           if ((currentPresenceField & presenceMask) != 0) {
/* 1535 */             size += CodedOutputStream.computeSFixed64Size(number, 0L);
/*      */           }
/*      */           break;
/*      */         case 15:
/* 1539 */           if ((currentPresenceField & presenceMask) != 0) {
/* 1540 */             size += CodedOutputStream.computeSInt32Size(number, unsafe.getInt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 16:
/* 1544 */           if ((currentPresenceField & presenceMask) != 0) {
/* 1545 */             size += CodedOutputStream.computeSInt64Size(number, unsafe.getLong(message, offset));
/*      */           }
/*      */           break;
/*      */         case 17:
/* 1549 */           if ((currentPresenceField & presenceMask) != 0) {
/* 1550 */             size += 
/* 1551 */               CodedOutputStream.computeGroupSize(number, (MessageLite)unsafe
/*      */                 
/* 1553 */                 .getObject(message, offset), 
/* 1554 */                 getMessageFieldSchema(i));
/*      */           }
/*      */           break;
/*      */         case 18:
/* 1558 */           size += 
/* 1559 */             SchemaUtil.computeSizeFixed64List(number, (List)unsafe
/* 1560 */               .getObject(message, offset), false);
/*      */           break;
/*      */         case 19:
/* 1563 */           size += 
/* 1564 */             SchemaUtil.computeSizeFixed32List(number, (List)unsafe
/* 1565 */               .getObject(message, offset), false);
/*      */           break;
/*      */         case 20:
/* 1568 */           size += 
/* 1569 */             SchemaUtil.computeSizeInt64List(number, (List<Long>)unsafe
/* 1570 */               .getObject(message, offset), false);
/*      */           break;
/*      */         case 21:
/* 1573 */           size += 
/* 1574 */             SchemaUtil.computeSizeUInt64List(number, (List<Long>)unsafe
/* 1575 */               .getObject(message, offset), false);
/*      */           break;
/*      */         case 22:
/* 1578 */           size += 
/* 1579 */             SchemaUtil.computeSizeInt32List(number, (List<Integer>)unsafe
/* 1580 */               .getObject(message, offset), false);
/*      */           break;
/*      */         case 23:
/* 1583 */           size += 
/* 1584 */             SchemaUtil.computeSizeFixed64List(number, (List)unsafe
/* 1585 */               .getObject(message, offset), false);
/*      */           break;
/*      */         case 24:
/* 1588 */           size += 
/* 1589 */             SchemaUtil.computeSizeFixed32List(number, (List)unsafe
/* 1590 */               .getObject(message, offset), false);
/*      */           break;
/*      */         case 25:
/* 1593 */           size += 
/* 1594 */             SchemaUtil.computeSizeBoolList(number, (List)unsafe
/* 1595 */               .getObject(message, offset), false);
/*      */           break;
/*      */         case 26:
/* 1598 */           size += 
/* 1599 */             SchemaUtil.computeSizeStringList(number, (List)unsafe.getObject(message, offset));
/*      */           break;
/*      */         case 27:
/* 1602 */           size += 
/* 1603 */             SchemaUtil.computeSizeMessageList(number, (List)unsafe
/* 1604 */               .getObject(message, offset), getMessageFieldSchema(i));
/*      */           break;
/*      */         case 28:
/* 1607 */           size += 
/* 1608 */             SchemaUtil.computeSizeByteStringList(number, (List<ByteString>)unsafe
/* 1609 */               .getObject(message, offset));
/*      */           break;
/*      */         case 29:
/* 1612 */           size += 
/* 1613 */             SchemaUtil.computeSizeUInt32List(number, (List<Integer>)unsafe
/* 1614 */               .getObject(message, offset), false);
/*      */           break;
/*      */         case 30:
/* 1617 */           size += 
/* 1618 */             SchemaUtil.computeSizeEnumList(number, (List<Integer>)unsafe
/* 1619 */               .getObject(message, offset), false);
/*      */           break;
/*      */         case 31:
/* 1622 */           size += 
/* 1623 */             SchemaUtil.computeSizeFixed32List(number, (List)unsafe
/* 1624 */               .getObject(message, offset), false);
/*      */           break;
/*      */         case 32:
/* 1627 */           size += 
/* 1628 */             SchemaUtil.computeSizeFixed64List(number, (List)unsafe
/* 1629 */               .getObject(message, offset), false);
/*      */           break;
/*      */         case 33:
/* 1632 */           size += 
/* 1633 */             SchemaUtil.computeSizeSInt32List(number, (List<Integer>)unsafe
/* 1634 */               .getObject(message, offset), false);
/*      */           break;
/*      */         case 34:
/* 1637 */           size += 
/* 1638 */             SchemaUtil.computeSizeSInt64List(number, (List<Long>)unsafe
/* 1639 */               .getObject(message, offset), false);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 35:
/* 1644 */           fieldSize = SchemaUtil.computeSizeFixed64ListNoTag((List)unsafe
/* 1645 */               .getObject(message, offset));
/* 1646 */           if (fieldSize > 0) {
/* 1647 */             if (this.useCachedSizeField) {
/* 1648 */               unsafe.putInt(message, presenceMaskAndOffset, fieldSize);
/*      */             }
/* 1650 */             size += 
/* 1651 */               CodedOutputStream.computeTagSize(number) + 
/* 1652 */               CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 36:
/* 1660 */           fieldSize = SchemaUtil.computeSizeFixed32ListNoTag((List)unsafe
/* 1661 */               .getObject(message, offset));
/* 1662 */           if (fieldSize > 0) {
/* 1663 */             if (this.useCachedSizeField) {
/* 1664 */               unsafe.putInt(message, presenceMaskAndOffset, fieldSize);
/*      */             }
/* 1666 */             size += 
/* 1667 */               CodedOutputStream.computeTagSize(number) + 
/* 1668 */               CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 37:
/* 1676 */           fieldSize = SchemaUtil.computeSizeInt64ListNoTag((List<Long>)unsafe
/* 1677 */               .getObject(message, offset));
/* 1678 */           if (fieldSize > 0) {
/* 1679 */             if (this.useCachedSizeField) {
/* 1680 */               unsafe.putInt(message, presenceMaskAndOffset, fieldSize);
/*      */             }
/* 1682 */             size += 
/* 1683 */               CodedOutputStream.computeTagSize(number) + 
/* 1684 */               CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 38:
/* 1692 */           fieldSize = SchemaUtil.computeSizeUInt64ListNoTag((List<Long>)unsafe
/* 1693 */               .getObject(message, offset));
/* 1694 */           if (fieldSize > 0) {
/* 1695 */             if (this.useCachedSizeField) {
/* 1696 */               unsafe.putInt(message, presenceMaskAndOffset, fieldSize);
/*      */             }
/* 1698 */             size += 
/* 1699 */               CodedOutputStream.computeTagSize(number) + 
/* 1700 */               CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 39:
/* 1708 */           fieldSize = SchemaUtil.computeSizeInt32ListNoTag((List<Integer>)unsafe
/* 1709 */               .getObject(message, offset));
/* 1710 */           if (fieldSize > 0) {
/* 1711 */             if (this.useCachedSizeField) {
/* 1712 */               unsafe.putInt(message, presenceMaskAndOffset, fieldSize);
/*      */             }
/* 1714 */             size += 
/* 1715 */               CodedOutputStream.computeTagSize(number) + 
/* 1716 */               CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 40:
/* 1724 */           fieldSize = SchemaUtil.computeSizeFixed64ListNoTag((List)unsafe
/* 1725 */               .getObject(message, offset));
/* 1726 */           if (fieldSize > 0) {
/* 1727 */             if (this.useCachedSizeField) {
/* 1728 */               unsafe.putInt(message, presenceMaskAndOffset, fieldSize);
/*      */             }
/* 1730 */             size += 
/* 1731 */               CodedOutputStream.computeTagSize(number) + 
/* 1732 */               CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 41:
/* 1740 */           fieldSize = SchemaUtil.computeSizeFixed32ListNoTag((List)unsafe
/* 1741 */               .getObject(message, offset));
/* 1742 */           if (fieldSize > 0) {
/* 1743 */             if (this.useCachedSizeField) {
/* 1744 */               unsafe.putInt(message, presenceMaskAndOffset, fieldSize);
/*      */             }
/* 1746 */             size += 
/* 1747 */               CodedOutputStream.computeTagSize(number) + 
/* 1748 */               CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 42:
/* 1756 */           fieldSize = SchemaUtil.computeSizeBoolListNoTag((List)unsafe
/* 1757 */               .getObject(message, offset));
/* 1758 */           if (fieldSize > 0) {
/* 1759 */             if (this.useCachedSizeField) {
/* 1760 */               unsafe.putInt(message, presenceMaskAndOffset, fieldSize);
/*      */             }
/* 1762 */             size += 
/* 1763 */               CodedOutputStream.computeTagSize(number) + 
/* 1764 */               CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 43:
/* 1772 */           fieldSize = SchemaUtil.computeSizeUInt32ListNoTag((List<Integer>)unsafe
/* 1773 */               .getObject(message, offset));
/* 1774 */           if (fieldSize > 0) {
/* 1775 */             if (this.useCachedSizeField) {
/* 1776 */               unsafe.putInt(message, presenceMaskAndOffset, fieldSize);
/*      */             }
/* 1778 */             size += 
/* 1779 */               CodedOutputStream.computeTagSize(number) + 
/* 1780 */               CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 44:
/* 1788 */           fieldSize = SchemaUtil.computeSizeEnumListNoTag((List<Integer>)unsafe
/* 1789 */               .getObject(message, offset));
/* 1790 */           if (fieldSize > 0) {
/* 1791 */             if (this.useCachedSizeField) {
/* 1792 */               unsafe.putInt(message, presenceMaskAndOffset, fieldSize);
/*      */             }
/* 1794 */             size += 
/* 1795 */               CodedOutputStream.computeTagSize(number) + 
/* 1796 */               CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 45:
/* 1804 */           fieldSize = SchemaUtil.computeSizeFixed32ListNoTag((List)unsafe
/* 1805 */               .getObject(message, offset));
/* 1806 */           if (fieldSize > 0) {
/* 1807 */             if (this.useCachedSizeField) {
/* 1808 */               unsafe.putInt(message, presenceMaskAndOffset, fieldSize);
/*      */             }
/* 1810 */             size += 
/* 1811 */               CodedOutputStream.computeTagSize(number) + 
/* 1812 */               CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 46:
/* 1820 */           fieldSize = SchemaUtil.computeSizeFixed64ListNoTag((List)unsafe
/* 1821 */               .getObject(message, offset));
/* 1822 */           if (fieldSize > 0) {
/* 1823 */             if (this.useCachedSizeField) {
/* 1824 */               unsafe.putInt(message, presenceMaskAndOffset, fieldSize);
/*      */             }
/* 1826 */             size += 
/* 1827 */               CodedOutputStream.computeTagSize(number) + 
/* 1828 */               CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 47:
/* 1836 */           fieldSize = SchemaUtil.computeSizeSInt32ListNoTag((List<Integer>)unsafe
/* 1837 */               .getObject(message, offset));
/* 1838 */           if (fieldSize > 0) {
/* 1839 */             if (this.useCachedSizeField) {
/* 1840 */               unsafe.putInt(message, presenceMaskAndOffset, fieldSize);
/*      */             }
/* 1842 */             size += 
/* 1843 */               CodedOutputStream.computeTagSize(number) + 
/* 1844 */               CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 48:
/* 1852 */           fieldSize = SchemaUtil.computeSizeSInt64ListNoTag((List<Long>)unsafe
/* 1853 */               .getObject(message, offset));
/* 1854 */           if (fieldSize > 0) {
/* 1855 */             if (this.useCachedSizeField) {
/* 1856 */               unsafe.putInt(message, presenceMaskAndOffset, fieldSize);
/*      */             }
/* 1858 */             size += 
/* 1859 */               CodedOutputStream.computeTagSize(number) + 
/* 1860 */               CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
/*      */           } 
/*      */           break;
/*      */ 
/*      */         
/*      */         case 49:
/* 1866 */           size += 
/* 1867 */             SchemaUtil.computeSizeGroupList(number, (List<MessageLite>)unsafe
/*      */               
/* 1869 */               .getObject(message, offset), 
/* 1870 */               getMessageFieldSchema(i));
/*      */           break;
/*      */         
/*      */         case 50:
/* 1874 */           size += this.mapFieldSchema
/* 1875 */             .getSerializedSize(number, unsafe
/* 1876 */               .getObject(message, offset), getMapFieldDefaultEntry(i));
/*      */           break;
/*      */         case 51:
/* 1879 */           if (isOneofPresent(message, number, i)) {
/* 1880 */             size += CodedOutputStream.computeDoubleSize(number, 0.0D);
/*      */           }
/*      */           break;
/*      */         case 52:
/* 1884 */           if (isOneofPresent(message, number, i)) {
/* 1885 */             size += CodedOutputStream.computeFloatSize(number, 0.0F);
/*      */           }
/*      */           break;
/*      */         case 53:
/* 1889 */           if (isOneofPresent(message, number, i)) {
/* 1890 */             size += CodedOutputStream.computeInt64Size(number, oneofLongAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 54:
/* 1894 */           if (isOneofPresent(message, number, i)) {
/* 1895 */             size += CodedOutputStream.computeUInt64Size(number, oneofLongAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 55:
/* 1899 */           if (isOneofPresent(message, number, i)) {
/* 1900 */             size += CodedOutputStream.computeInt32Size(number, oneofIntAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 56:
/* 1904 */           if (isOneofPresent(message, number, i)) {
/* 1905 */             size += CodedOutputStream.computeFixed64Size(number, 0L);
/*      */           }
/*      */           break;
/*      */         case 57:
/* 1909 */           if (isOneofPresent(message, number, i)) {
/* 1910 */             size += CodedOutputStream.computeFixed32Size(number, 0);
/*      */           }
/*      */           break;
/*      */         case 58:
/* 1914 */           if (isOneofPresent(message, number, i)) {
/* 1915 */             size += CodedOutputStream.computeBoolSize(number, true);
/*      */           }
/*      */           break;
/*      */         case 59:
/* 1919 */           if (isOneofPresent(message, number, i)) {
/* 1920 */             Object value = unsafe.getObject(message, offset);
/* 1921 */             if (value instanceof ByteString) {
/* 1922 */               size += CodedOutputStream.computeBytesSize(number, (ByteString)value); break;
/*      */             } 
/* 1924 */             size += CodedOutputStream.computeStringSize(number, (String)value);
/*      */           } 
/*      */           break;
/*      */         
/*      */         case 60:
/* 1929 */           if (isOneofPresent(message, number, i)) {
/* 1930 */             Object value = unsafe.getObject(message, offset);
/* 1931 */             size += SchemaUtil.computeSizeMessage(number, value, getMessageFieldSchema(i));
/*      */           } 
/*      */           break;
/*      */         case 61:
/* 1935 */           if (isOneofPresent(message, number, i)) {
/* 1936 */             size += 
/* 1937 */               CodedOutputStream.computeBytesSize(number, (ByteString)unsafe
/* 1938 */                 .getObject(message, offset));
/*      */           }
/*      */           break;
/*      */         case 62:
/* 1942 */           if (isOneofPresent(message, number, i)) {
/* 1943 */             size += CodedOutputStream.computeUInt32Size(number, oneofIntAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 63:
/* 1947 */           if (isOneofPresent(message, number, i)) {
/* 1948 */             size += CodedOutputStream.computeEnumSize(number, oneofIntAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 64:
/* 1952 */           if (isOneofPresent(message, number, i)) {
/* 1953 */             size += CodedOutputStream.computeSFixed32Size(number, 0);
/*      */           }
/*      */           break;
/*      */         case 65:
/* 1957 */           if (isOneofPresent(message, number, i)) {
/* 1958 */             size += CodedOutputStream.computeSFixed64Size(number, 0L);
/*      */           }
/*      */           break;
/*      */         case 66:
/* 1962 */           if (isOneofPresent(message, number, i)) {
/* 1963 */             size += CodedOutputStream.computeSInt32Size(number, oneofIntAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 67:
/* 1967 */           if (isOneofPresent(message, number, i)) {
/* 1968 */             size += CodedOutputStream.computeSInt64Size(number, oneofLongAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 68:
/* 1972 */           if (isOneofPresent(message, number, i)) {
/* 1973 */             size += 
/* 1974 */               CodedOutputStream.computeGroupSize(number, (MessageLite)unsafe
/*      */                 
/* 1976 */                 .getObject(message, offset), 
/* 1977 */                 getMessageFieldSchema(i));
/*      */           }
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */     
/*      */     } 
/* 1985 */     size += getUnknownFieldsSerializedSize(this.unknownFieldSchema, message);
/*      */     
/* 1987 */     if (this.hasExtensions) {
/* 1988 */       size += this.extensionSchema.getExtensions(message).getSerializedSize();
/*      */     }
/*      */     
/* 1991 */     return size;
/*      */   }
/*      */   
/*      */   private int getSerializedSizeProto3(T message) {
/* 1995 */     Unsafe unsafe = UNSAFE;
/* 1996 */     int size = 0;
/* 1997 */     for (int i = 0; i < this.buffer.length; i += 3) {
/* 1998 */       int fieldSize, typeAndOffset = typeAndOffsetAt(i);
/* 1999 */       int fieldType = type(typeAndOffset);
/* 2000 */       int number = numberAt(i);
/*      */       
/* 2002 */       long offset = offset(typeAndOffset);
/*      */ 
/*      */       
/* 2005 */       int cachedSizeOffset = (fieldType >= FieldType.DOUBLE_LIST_PACKED.id() && fieldType <= FieldType.SINT64_LIST_PACKED.id()) ? (this.buffer[i + 2] & 0xFFFFF) : 0;
/*      */ 
/*      */ 
/*      */       
/* 2009 */       switch (fieldType) {
/*      */         case 0:
/* 2011 */           if (isFieldPresent(message, i)) {
/* 2012 */             size += CodedOutputStream.computeDoubleSize(number, 0.0D);
/*      */           }
/*      */           break;
/*      */         case 1:
/* 2016 */           if (isFieldPresent(message, i)) {
/* 2017 */             size += CodedOutputStream.computeFloatSize(number, 0.0F);
/*      */           }
/*      */           break;
/*      */         case 2:
/* 2021 */           if (isFieldPresent(message, i)) {
/* 2022 */             size += CodedOutputStream.computeInt64Size(number, UnsafeUtil.getLong(message, offset));
/*      */           }
/*      */           break;
/*      */         case 3:
/* 2026 */           if (isFieldPresent(message, i)) {
/* 2027 */             size += 
/* 2028 */               CodedOutputStream.computeUInt64Size(number, UnsafeUtil.getLong(message, offset));
/*      */           }
/*      */           break;
/*      */         case 4:
/* 2032 */           if (isFieldPresent(message, i)) {
/* 2033 */             size += CodedOutputStream.computeInt32Size(number, UnsafeUtil.getInt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 5:
/* 2037 */           if (isFieldPresent(message, i)) {
/* 2038 */             size += CodedOutputStream.computeFixed64Size(number, 0L);
/*      */           }
/*      */           break;
/*      */         case 6:
/* 2042 */           if (isFieldPresent(message, i)) {
/* 2043 */             size += CodedOutputStream.computeFixed32Size(number, 0);
/*      */           }
/*      */           break;
/*      */         case 7:
/* 2047 */           if (isFieldPresent(message, i)) {
/* 2048 */             size += CodedOutputStream.computeBoolSize(number, true);
/*      */           }
/*      */           break;
/*      */         case 8:
/* 2052 */           if (isFieldPresent(message, i)) {
/* 2053 */             Object value = UnsafeUtil.getObject(message, offset);
/* 2054 */             if (value instanceof ByteString) {
/* 2055 */               size += CodedOutputStream.computeBytesSize(number, (ByteString)value); break;
/*      */             } 
/* 2057 */             size += CodedOutputStream.computeStringSize(number, (String)value);
/*      */           } 
/*      */           break;
/*      */         
/*      */         case 9:
/* 2062 */           if (isFieldPresent(message, i)) {
/* 2063 */             Object value = UnsafeUtil.getObject(message, offset);
/* 2064 */             size += SchemaUtil.computeSizeMessage(number, value, getMessageFieldSchema(i));
/*      */           } 
/*      */           break;
/*      */         case 10:
/* 2068 */           if (isFieldPresent(message, i)) {
/* 2069 */             ByteString value = (ByteString)UnsafeUtil.getObject(message, offset);
/* 2070 */             size += CodedOutputStream.computeBytesSize(number, value);
/*      */           } 
/*      */           break;
/*      */         case 11:
/* 2074 */           if (isFieldPresent(message, i)) {
/* 2075 */             size += CodedOutputStream.computeUInt32Size(number, UnsafeUtil.getInt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 12:
/* 2079 */           if (isFieldPresent(message, i)) {
/* 2080 */             size += CodedOutputStream.computeEnumSize(number, UnsafeUtil.getInt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 13:
/* 2084 */           if (isFieldPresent(message, i)) {
/* 2085 */             size += CodedOutputStream.computeSFixed32Size(number, 0);
/*      */           }
/*      */           break;
/*      */         case 14:
/* 2089 */           if (isFieldPresent(message, i)) {
/* 2090 */             size += CodedOutputStream.computeSFixed64Size(number, 0L);
/*      */           }
/*      */           break;
/*      */         case 15:
/* 2094 */           if (isFieldPresent(message, i)) {
/* 2095 */             size += CodedOutputStream.computeSInt32Size(number, UnsafeUtil.getInt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 16:
/* 2099 */           if (isFieldPresent(message, i)) {
/* 2100 */             size += 
/* 2101 */               CodedOutputStream.computeSInt64Size(number, UnsafeUtil.getLong(message, offset));
/*      */           }
/*      */           break;
/*      */         case 17:
/* 2105 */           if (isFieldPresent(message, i)) {
/* 2106 */             size += 
/* 2107 */               CodedOutputStream.computeGroupSize(number, 
/*      */                 
/* 2109 */                 (MessageLite)UnsafeUtil.getObject(message, offset), 
/* 2110 */                 getMessageFieldSchema(i));
/*      */           }
/*      */           break;
/*      */         case 18:
/* 2114 */           size += SchemaUtil.computeSizeFixed64List(number, listAt(message, offset), false);
/*      */           break;
/*      */         case 19:
/* 2117 */           size += SchemaUtil.computeSizeFixed32List(number, listAt(message, offset), false);
/*      */           break;
/*      */         case 20:
/* 2120 */           size += 
/* 2121 */             SchemaUtil.computeSizeInt64List(number, (List)listAt(message, offset), false);
/*      */           break;
/*      */         case 21:
/* 2124 */           size += 
/* 2125 */             SchemaUtil.computeSizeUInt64List(number, (List)listAt(message, offset), false);
/*      */           break;
/*      */         case 22:
/* 2128 */           size += 
/* 2129 */             SchemaUtil.computeSizeInt32List(number, 
/* 2130 */               (List)listAt(message, offset), false);
/*      */           break;
/*      */         case 23:
/* 2133 */           size += SchemaUtil.computeSizeFixed64List(number, listAt(message, offset), false);
/*      */           break;
/*      */         case 24:
/* 2136 */           size += SchemaUtil.computeSizeFixed32List(number, listAt(message, offset), false);
/*      */           break;
/*      */         case 25:
/* 2139 */           size += SchemaUtil.computeSizeBoolList(number, listAt(message, offset), false);
/*      */           break;
/*      */         case 26:
/* 2142 */           size += SchemaUtil.computeSizeStringList(number, listAt(message, offset));
/*      */           break;
/*      */         case 27:
/* 2145 */           size += 
/* 2146 */             SchemaUtil.computeSizeMessageList(number, 
/* 2147 */               listAt(message, offset), getMessageFieldSchema(i));
/*      */           break;
/*      */         case 28:
/* 2150 */           size += 
/* 2151 */             SchemaUtil.computeSizeByteStringList(number, 
/* 2152 */               (List)listAt(message, offset));
/*      */           break;
/*      */         case 29:
/* 2155 */           size += 
/* 2156 */             SchemaUtil.computeSizeUInt32List(number, 
/* 2157 */               (List)listAt(message, offset), false);
/*      */           break;
/*      */         case 30:
/* 2160 */           size += 
/* 2161 */             SchemaUtil.computeSizeEnumList(number, 
/* 2162 */               (List)listAt(message, offset), false);
/*      */           break;
/*      */         case 31:
/* 2165 */           size += SchemaUtil.computeSizeFixed32List(number, listAt(message, offset), false);
/*      */           break;
/*      */         case 32:
/* 2168 */           size += SchemaUtil.computeSizeFixed64List(number, listAt(message, offset), false);
/*      */           break;
/*      */         case 33:
/* 2171 */           size += 
/* 2172 */             SchemaUtil.computeSizeSInt32List(number, 
/* 2173 */               (List)listAt(message, offset), false);
/*      */           break;
/*      */         case 34:
/* 2176 */           size += 
/* 2177 */             SchemaUtil.computeSizeSInt64List(number, (List)listAt(message, offset), false);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 35:
/* 2182 */           fieldSize = SchemaUtil.computeSizeFixed64ListNoTag((List)unsafe
/* 2183 */               .getObject(message, offset));
/* 2184 */           if (fieldSize > 0) {
/* 2185 */             if (this.useCachedSizeField) {
/* 2186 */               unsafe.putInt(message, cachedSizeOffset, fieldSize);
/*      */             }
/* 2188 */             size += 
/* 2189 */               CodedOutputStream.computeTagSize(number) + 
/* 2190 */               CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 36:
/* 2198 */           fieldSize = SchemaUtil.computeSizeFixed32ListNoTag((List)unsafe
/* 2199 */               .getObject(message, offset));
/* 2200 */           if (fieldSize > 0) {
/* 2201 */             if (this.useCachedSizeField) {
/* 2202 */               unsafe.putInt(message, cachedSizeOffset, fieldSize);
/*      */             }
/* 2204 */             size += 
/* 2205 */               CodedOutputStream.computeTagSize(number) + 
/* 2206 */               CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 37:
/* 2214 */           fieldSize = SchemaUtil.computeSizeInt64ListNoTag((List<Long>)unsafe
/* 2215 */               .getObject(message, offset));
/* 2216 */           if (fieldSize > 0) {
/* 2217 */             if (this.useCachedSizeField) {
/* 2218 */               unsafe.putInt(message, cachedSizeOffset, fieldSize);
/*      */             }
/* 2220 */             size += 
/* 2221 */               CodedOutputStream.computeTagSize(number) + 
/* 2222 */               CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 38:
/* 2230 */           fieldSize = SchemaUtil.computeSizeUInt64ListNoTag((List<Long>)unsafe
/* 2231 */               .getObject(message, offset));
/* 2232 */           if (fieldSize > 0) {
/* 2233 */             if (this.useCachedSizeField) {
/* 2234 */               unsafe.putInt(message, cachedSizeOffset, fieldSize);
/*      */             }
/* 2236 */             size += 
/* 2237 */               CodedOutputStream.computeTagSize(number) + 
/* 2238 */               CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 39:
/* 2246 */           fieldSize = SchemaUtil.computeSizeInt32ListNoTag((List<Integer>)unsafe
/* 2247 */               .getObject(message, offset));
/* 2248 */           if (fieldSize > 0) {
/* 2249 */             if (this.useCachedSizeField) {
/* 2250 */               unsafe.putInt(message, cachedSizeOffset, fieldSize);
/*      */             }
/* 2252 */             size += 
/* 2253 */               CodedOutputStream.computeTagSize(number) + 
/* 2254 */               CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 40:
/* 2262 */           fieldSize = SchemaUtil.computeSizeFixed64ListNoTag((List)unsafe
/* 2263 */               .getObject(message, offset));
/* 2264 */           if (fieldSize > 0) {
/* 2265 */             if (this.useCachedSizeField) {
/* 2266 */               unsafe.putInt(message, cachedSizeOffset, fieldSize);
/*      */             }
/* 2268 */             size += 
/* 2269 */               CodedOutputStream.computeTagSize(number) + 
/* 2270 */               CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 41:
/* 2278 */           fieldSize = SchemaUtil.computeSizeFixed32ListNoTag((List)unsafe
/* 2279 */               .getObject(message, offset));
/* 2280 */           if (fieldSize > 0) {
/* 2281 */             if (this.useCachedSizeField) {
/* 2282 */               unsafe.putInt(message, cachedSizeOffset, fieldSize);
/*      */             }
/* 2284 */             size += 
/* 2285 */               CodedOutputStream.computeTagSize(number) + 
/* 2286 */               CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 42:
/* 2294 */           fieldSize = SchemaUtil.computeSizeBoolListNoTag((List)unsafe
/* 2295 */               .getObject(message, offset));
/* 2296 */           if (fieldSize > 0) {
/* 2297 */             if (this.useCachedSizeField) {
/* 2298 */               unsafe.putInt(message, cachedSizeOffset, fieldSize);
/*      */             }
/* 2300 */             size += 
/* 2301 */               CodedOutputStream.computeTagSize(number) + 
/* 2302 */               CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 43:
/* 2310 */           fieldSize = SchemaUtil.computeSizeUInt32ListNoTag((List<Integer>)unsafe
/* 2311 */               .getObject(message, offset));
/* 2312 */           if (fieldSize > 0) {
/* 2313 */             if (this.useCachedSizeField) {
/* 2314 */               unsafe.putInt(message, cachedSizeOffset, fieldSize);
/*      */             }
/* 2316 */             size += 
/* 2317 */               CodedOutputStream.computeTagSize(number) + 
/* 2318 */               CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 44:
/* 2326 */           fieldSize = SchemaUtil.computeSizeEnumListNoTag((List<Integer>)unsafe
/* 2327 */               .getObject(message, offset));
/* 2328 */           if (fieldSize > 0) {
/* 2329 */             if (this.useCachedSizeField) {
/* 2330 */               unsafe.putInt(message, cachedSizeOffset, fieldSize);
/*      */             }
/* 2332 */             size += 
/* 2333 */               CodedOutputStream.computeTagSize(number) + 
/* 2334 */               CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 45:
/* 2342 */           fieldSize = SchemaUtil.computeSizeFixed32ListNoTag((List)unsafe
/* 2343 */               .getObject(message, offset));
/* 2344 */           if (fieldSize > 0) {
/* 2345 */             if (this.useCachedSizeField) {
/* 2346 */               unsafe.putInt(message, cachedSizeOffset, fieldSize);
/*      */             }
/* 2348 */             size += 
/* 2349 */               CodedOutputStream.computeTagSize(number) + 
/* 2350 */               CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 46:
/* 2358 */           fieldSize = SchemaUtil.computeSizeFixed64ListNoTag((List)unsafe
/* 2359 */               .getObject(message, offset));
/* 2360 */           if (fieldSize > 0) {
/* 2361 */             if (this.useCachedSizeField) {
/* 2362 */               unsafe.putInt(message, cachedSizeOffset, fieldSize);
/*      */             }
/* 2364 */             size += 
/* 2365 */               CodedOutputStream.computeTagSize(number) + 
/* 2366 */               CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 47:
/* 2374 */           fieldSize = SchemaUtil.computeSizeSInt32ListNoTag((List<Integer>)unsafe
/* 2375 */               .getObject(message, offset));
/* 2376 */           if (fieldSize > 0) {
/* 2377 */             if (this.useCachedSizeField) {
/* 2378 */               unsafe.putInt(message, cachedSizeOffset, fieldSize);
/*      */             }
/* 2380 */             size += 
/* 2381 */               CodedOutputStream.computeTagSize(number) + 
/* 2382 */               CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 48:
/* 2390 */           fieldSize = SchemaUtil.computeSizeSInt64ListNoTag((List<Long>)unsafe
/* 2391 */               .getObject(message, offset));
/* 2392 */           if (fieldSize > 0) {
/* 2393 */             if (this.useCachedSizeField) {
/* 2394 */               unsafe.putInt(message, cachedSizeOffset, fieldSize);
/*      */             }
/* 2396 */             size += 
/* 2397 */               CodedOutputStream.computeTagSize(number) + 
/* 2398 */               CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
/*      */           } 
/*      */           break;
/*      */ 
/*      */         
/*      */         case 49:
/* 2404 */           size += 
/* 2405 */             SchemaUtil.computeSizeGroupList(number, 
/* 2406 */               (List)listAt(message, offset), getMessageFieldSchema(i));
/*      */           break;
/*      */         
/*      */         case 50:
/* 2410 */           size += this.mapFieldSchema
/* 2411 */             .getSerializedSize(number, 
/* 2412 */               UnsafeUtil.getObject(message, offset), getMapFieldDefaultEntry(i));
/*      */           break;
/*      */         case 51:
/* 2415 */           if (isOneofPresent(message, number, i)) {
/* 2416 */             size += CodedOutputStream.computeDoubleSize(number, 0.0D);
/*      */           }
/*      */           break;
/*      */         case 52:
/* 2420 */           if (isOneofPresent(message, number, i)) {
/* 2421 */             size += CodedOutputStream.computeFloatSize(number, 0.0F);
/*      */           }
/*      */           break;
/*      */         case 53:
/* 2425 */           if (isOneofPresent(message, number, i)) {
/* 2426 */             size += CodedOutputStream.computeInt64Size(number, oneofLongAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 54:
/* 2430 */           if (isOneofPresent(message, number, i)) {
/* 2431 */             size += CodedOutputStream.computeUInt64Size(number, oneofLongAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 55:
/* 2435 */           if (isOneofPresent(message, number, i)) {
/* 2436 */             size += CodedOutputStream.computeInt32Size(number, oneofIntAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 56:
/* 2440 */           if (isOneofPresent(message, number, i)) {
/* 2441 */             size += CodedOutputStream.computeFixed64Size(number, 0L);
/*      */           }
/*      */           break;
/*      */         case 57:
/* 2445 */           if (isOneofPresent(message, number, i)) {
/* 2446 */             size += CodedOutputStream.computeFixed32Size(number, 0);
/*      */           }
/*      */           break;
/*      */         case 58:
/* 2450 */           if (isOneofPresent(message, number, i)) {
/* 2451 */             size += CodedOutputStream.computeBoolSize(number, true);
/*      */           }
/*      */           break;
/*      */         case 59:
/* 2455 */           if (isOneofPresent(message, number, i)) {
/* 2456 */             Object value = UnsafeUtil.getObject(message, offset);
/* 2457 */             if (value instanceof ByteString) {
/* 2458 */               size += CodedOutputStream.computeBytesSize(number, (ByteString)value); break;
/*      */             } 
/* 2460 */             size += CodedOutputStream.computeStringSize(number, (String)value);
/*      */           } 
/*      */           break;
/*      */         
/*      */         case 60:
/* 2465 */           if (isOneofPresent(message, number, i)) {
/* 2466 */             Object value = UnsafeUtil.getObject(message, offset);
/* 2467 */             size += SchemaUtil.computeSizeMessage(number, value, getMessageFieldSchema(i));
/*      */           } 
/*      */           break;
/*      */         case 61:
/* 2471 */           if (isOneofPresent(message, number, i)) {
/* 2472 */             size += 
/* 2473 */               CodedOutputStream.computeBytesSize(number, 
/* 2474 */                 (ByteString)UnsafeUtil.getObject(message, offset));
/*      */           }
/*      */           break;
/*      */         case 62:
/* 2478 */           if (isOneofPresent(message, number, i)) {
/* 2479 */             size += CodedOutputStream.computeUInt32Size(number, oneofIntAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 63:
/* 2483 */           if (isOneofPresent(message, number, i)) {
/* 2484 */             size += CodedOutputStream.computeEnumSize(number, oneofIntAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 64:
/* 2488 */           if (isOneofPresent(message, number, i)) {
/* 2489 */             size += CodedOutputStream.computeSFixed32Size(number, 0);
/*      */           }
/*      */           break;
/*      */         case 65:
/* 2493 */           if (isOneofPresent(message, number, i)) {
/* 2494 */             size += CodedOutputStream.computeSFixed64Size(number, 0L);
/*      */           }
/*      */           break;
/*      */         case 66:
/* 2498 */           if (isOneofPresent(message, number, i)) {
/* 2499 */             size += CodedOutputStream.computeSInt32Size(number, oneofIntAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 67:
/* 2503 */           if (isOneofPresent(message, number, i)) {
/* 2504 */             size += CodedOutputStream.computeSInt64Size(number, oneofLongAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 68:
/* 2508 */           if (isOneofPresent(message, number, i)) {
/* 2509 */             size += 
/* 2510 */               CodedOutputStream.computeGroupSize(number, 
/*      */                 
/* 2512 */                 (MessageLite)UnsafeUtil.getObject(message, offset), 
/* 2513 */                 getMessageFieldSchema(i));
/*      */           }
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */     
/*      */     } 
/* 2521 */     size += getUnknownFieldsSerializedSize(this.unknownFieldSchema, message);
/*      */     
/* 2523 */     return size;
/*      */   }
/*      */ 
/*      */   
/*      */   private <UT, UB> int getUnknownFieldsSerializedSize(UnknownFieldSchema<UT, UB> schema, T message) {
/* 2528 */     UT unknowns = schema.getFromMessage(message);
/* 2529 */     return schema.getSerializedSize(unknowns);
/*      */   }
/*      */   
/*      */   private static List<?> listAt(Object message, long offset) {
/* 2533 */     return (List)UnsafeUtil.getObject(message, offset);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeTo(T message, Writer writer) throws IOException {
/* 2542 */     if (writer.fieldOrder() == Writer.FieldOrder.DESCENDING) {
/* 2543 */       writeFieldsInDescendingOrder(message, writer);
/*      */     }
/* 2545 */     else if (this.proto3) {
/* 2546 */       writeFieldsInAscendingOrderProto3(message, writer);
/*      */     } else {
/* 2548 */       writeFieldsInAscendingOrderProto2(message, writer);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeFieldsInAscendingOrderProto2(T message, Writer writer) throws IOException {
/* 2555 */     Iterator<? extends Map.Entry<?, ?>> extensionIterator = null;
/* 2556 */     Map.Entry<?, ?> nextExtension = null;
/* 2557 */     if (this.hasExtensions) {
/* 2558 */       FieldSet<?> extensions = this.extensionSchema.getExtensions(message);
/* 2559 */       if (!extensions.isEmpty()) {
/* 2560 */         extensionIterator = extensions.iterator();
/* 2561 */         nextExtension = extensionIterator.next();
/*      */       } 
/*      */     } 
/* 2564 */     int currentPresenceFieldOffset = 1048575;
/* 2565 */     int currentPresenceField = 0;
/* 2566 */     int bufferLength = this.buffer.length;
/* 2567 */     Unsafe unsafe = UNSAFE;
/* 2568 */     for (int pos = 0; pos < bufferLength; pos += 3) {
/* 2569 */       int typeAndOffset = typeAndOffsetAt(pos);
/* 2570 */       int number = numberAt(pos);
/* 2571 */       int fieldType = type(typeAndOffset);
/*      */       
/* 2573 */       int presenceMaskAndOffset = 0;
/* 2574 */       int presenceMask = 0;
/* 2575 */       if (fieldType <= 17) {
/* 2576 */         presenceMaskAndOffset = this.buffer[pos + 2];
/* 2577 */         int presenceFieldOffset = presenceMaskAndOffset & 0xFFFFF;
/* 2578 */         if (presenceFieldOffset != currentPresenceFieldOffset) {
/* 2579 */           currentPresenceFieldOffset = presenceFieldOffset;
/* 2580 */           currentPresenceField = unsafe.getInt(message, presenceFieldOffset);
/*      */         } 
/* 2582 */         presenceMask = 1 << presenceMaskAndOffset >>> 20;
/*      */       } 
/*      */ 
/*      */       
/* 2586 */       while (nextExtension != null && this.extensionSchema.extensionNumber(nextExtension) <= number) {
/* 2587 */         this.extensionSchema.serializeExtension(writer, nextExtension);
/* 2588 */         nextExtension = extensionIterator.hasNext() ? extensionIterator.next() : null;
/*      */       } 
/* 2590 */       long offset = offset(typeAndOffset);
/*      */       
/* 2592 */       switch (fieldType) {
/*      */         case 0:
/* 2594 */           if ((currentPresenceField & presenceMask) != 0) {
/* 2595 */             writer.writeDouble(number, doubleAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 1:
/* 2599 */           if ((currentPresenceField & presenceMask) != 0) {
/* 2600 */             writer.writeFloat(number, floatAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 2:
/* 2604 */           if ((currentPresenceField & presenceMask) != 0) {
/* 2605 */             writer.writeInt64(number, unsafe.getLong(message, offset));
/*      */           }
/*      */           break;
/*      */         case 3:
/* 2609 */           if ((currentPresenceField & presenceMask) != 0) {
/* 2610 */             writer.writeUInt64(number, unsafe.getLong(message, offset));
/*      */           }
/*      */           break;
/*      */         case 4:
/* 2614 */           if ((currentPresenceField & presenceMask) != 0) {
/* 2615 */             writer.writeInt32(number, unsafe.getInt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 5:
/* 2619 */           if ((currentPresenceField & presenceMask) != 0) {
/* 2620 */             writer.writeFixed64(number, unsafe.getLong(message, offset));
/*      */           }
/*      */           break;
/*      */         case 6:
/* 2624 */           if ((currentPresenceField & presenceMask) != 0) {
/* 2625 */             writer.writeFixed32(number, unsafe.getInt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 7:
/* 2629 */           if ((currentPresenceField & presenceMask) != 0) {
/* 2630 */             writer.writeBool(number, booleanAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 8:
/* 2634 */           if ((currentPresenceField & presenceMask) != 0) {
/* 2635 */             writeString(number, unsafe.getObject(message, offset), writer);
/*      */           }
/*      */           break;
/*      */         case 9:
/* 2639 */           if ((currentPresenceField & presenceMask) != 0) {
/* 2640 */             Object value = unsafe.getObject(message, offset);
/* 2641 */             writer.writeMessage(number, value, getMessageFieldSchema(pos));
/*      */           } 
/*      */           break;
/*      */         case 10:
/* 2645 */           if ((currentPresenceField & presenceMask) != 0) {
/* 2646 */             writer.writeBytes(number, (ByteString)unsafe.getObject(message, offset));
/*      */           }
/*      */           break;
/*      */         case 11:
/* 2650 */           if ((currentPresenceField & presenceMask) != 0) {
/* 2651 */             writer.writeUInt32(number, unsafe.getInt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 12:
/* 2655 */           if ((currentPresenceField & presenceMask) != 0) {
/* 2656 */             writer.writeEnum(number, unsafe.getInt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 13:
/* 2660 */           if ((currentPresenceField & presenceMask) != 0) {
/* 2661 */             writer.writeSFixed32(number, unsafe.getInt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 14:
/* 2665 */           if ((currentPresenceField & presenceMask) != 0) {
/* 2666 */             writer.writeSFixed64(number, unsafe.getLong(message, offset));
/*      */           }
/*      */           break;
/*      */         case 15:
/* 2670 */           if ((currentPresenceField & presenceMask) != 0) {
/* 2671 */             writer.writeSInt32(number, unsafe.getInt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 16:
/* 2675 */           if ((currentPresenceField & presenceMask) != 0) {
/* 2676 */             writer.writeSInt64(number, unsafe.getLong(message, offset));
/*      */           }
/*      */           break;
/*      */         case 17:
/* 2680 */           if ((currentPresenceField & presenceMask) != 0) {
/* 2681 */             writer.writeGroup(number, unsafe
/* 2682 */                 .getObject(message, offset), getMessageFieldSchema(pos));
/*      */           }
/*      */           break;
/*      */         case 18:
/* 2686 */           SchemaUtil.writeDoubleList(
/* 2687 */               numberAt(pos), (List<Double>)unsafe.getObject(message, offset), writer, false);
/*      */           break;
/*      */         case 19:
/* 2690 */           SchemaUtil.writeFloatList(
/* 2691 */               numberAt(pos), (List<Float>)unsafe.getObject(message, offset), writer, false);
/*      */           break;
/*      */         case 20:
/* 2694 */           SchemaUtil.writeInt64List(
/* 2695 */               numberAt(pos), (List<Long>)unsafe.getObject(message, offset), writer, false);
/*      */           break;
/*      */         case 21:
/* 2698 */           SchemaUtil.writeUInt64List(
/* 2699 */               numberAt(pos), (List<Long>)unsafe.getObject(message, offset), writer, false);
/*      */           break;
/*      */         case 22:
/* 2702 */           SchemaUtil.writeInt32List(
/* 2703 */               numberAt(pos), (List<Integer>)unsafe.getObject(message, offset), writer, false);
/*      */           break;
/*      */         case 23:
/* 2706 */           SchemaUtil.writeFixed64List(
/* 2707 */               numberAt(pos), (List<Long>)unsafe.getObject(message, offset), writer, false);
/*      */           break;
/*      */         case 24:
/* 2710 */           SchemaUtil.writeFixed32List(
/* 2711 */               numberAt(pos), (List<Integer>)unsafe.getObject(message, offset), writer, false);
/*      */           break;
/*      */         case 25:
/* 2714 */           SchemaUtil.writeBoolList(
/* 2715 */               numberAt(pos), (List<Boolean>)unsafe.getObject(message, offset), writer, false);
/*      */           break;
/*      */         case 26:
/* 2718 */           SchemaUtil.writeStringList(
/* 2719 */               numberAt(pos), (List<String>)unsafe.getObject(message, offset), writer);
/*      */           break;
/*      */         case 27:
/* 2722 */           SchemaUtil.writeMessageList(
/* 2723 */               numberAt(pos), (List)unsafe
/* 2724 */               .getObject(message, offset), writer, 
/*      */               
/* 2726 */               getMessageFieldSchema(pos));
/*      */           break;
/*      */         case 28:
/* 2729 */           SchemaUtil.writeBytesList(
/* 2730 */               numberAt(pos), (List<ByteString>)unsafe.getObject(message, offset), writer);
/*      */           break;
/*      */         case 29:
/* 2733 */           SchemaUtil.writeUInt32List(
/* 2734 */               numberAt(pos), (List<Integer>)unsafe.getObject(message, offset), writer, false);
/*      */           break;
/*      */         case 30:
/* 2737 */           SchemaUtil.writeEnumList(
/* 2738 */               numberAt(pos), (List<Integer>)unsafe.getObject(message, offset), writer, false);
/*      */           break;
/*      */         case 31:
/* 2741 */           SchemaUtil.writeSFixed32List(
/* 2742 */               numberAt(pos), (List<Integer>)unsafe.getObject(message, offset), writer, false);
/*      */           break;
/*      */         case 32:
/* 2745 */           SchemaUtil.writeSFixed64List(
/* 2746 */               numberAt(pos), (List<Long>)unsafe.getObject(message, offset), writer, false);
/*      */           break;
/*      */         case 33:
/* 2749 */           SchemaUtil.writeSInt32List(
/* 2750 */               numberAt(pos), (List<Integer>)unsafe.getObject(message, offset), writer, false);
/*      */           break;
/*      */         case 34:
/* 2753 */           SchemaUtil.writeSInt64List(
/* 2754 */               numberAt(pos), (List<Long>)unsafe.getObject(message, offset), writer, false);
/*      */           break;
/*      */         
/*      */         case 35:
/* 2758 */           SchemaUtil.writeDoubleList(
/* 2759 */               numberAt(pos), (List<Double>)unsafe.getObject(message, offset), writer, true);
/*      */           break;
/*      */         case 36:
/* 2762 */           SchemaUtil.writeFloatList(
/* 2763 */               numberAt(pos), (List<Float>)unsafe.getObject(message, offset), writer, true);
/*      */           break;
/*      */         case 37:
/* 2766 */           SchemaUtil.writeInt64List(
/* 2767 */               numberAt(pos), (List<Long>)unsafe.getObject(message, offset), writer, true);
/*      */           break;
/*      */         case 38:
/* 2770 */           SchemaUtil.writeUInt64List(
/* 2771 */               numberAt(pos), (List<Long>)unsafe.getObject(message, offset), writer, true);
/*      */           break;
/*      */         case 39:
/* 2774 */           SchemaUtil.writeInt32List(
/* 2775 */               numberAt(pos), (List<Integer>)unsafe.getObject(message, offset), writer, true);
/*      */           break;
/*      */         case 40:
/* 2778 */           SchemaUtil.writeFixed64List(
/* 2779 */               numberAt(pos), (List<Long>)unsafe.getObject(message, offset), writer, true);
/*      */           break;
/*      */         case 41:
/* 2782 */           SchemaUtil.writeFixed32List(
/* 2783 */               numberAt(pos), (List<Integer>)unsafe.getObject(message, offset), writer, true);
/*      */           break;
/*      */         
/*      */         case 42:
/* 2787 */           SchemaUtil.writeBoolList(
/* 2788 */               numberAt(pos), (List<Boolean>)unsafe.getObject(message, offset), writer, true);
/*      */           break;
/*      */         case 43:
/* 2791 */           SchemaUtil.writeUInt32List(
/* 2792 */               numberAt(pos), (List<Integer>)unsafe.getObject(message, offset), writer, true);
/*      */           break;
/*      */         case 44:
/* 2795 */           SchemaUtil.writeEnumList(
/* 2796 */               numberAt(pos), (List<Integer>)unsafe.getObject(message, offset), writer, true);
/*      */           break;
/*      */         case 45:
/* 2799 */           SchemaUtil.writeSFixed32List(
/* 2800 */               numberAt(pos), (List<Integer>)unsafe.getObject(message, offset), writer, true);
/*      */           break;
/*      */         case 46:
/* 2803 */           SchemaUtil.writeSFixed64List(
/* 2804 */               numberAt(pos), (List<Long>)unsafe.getObject(message, offset), writer, true);
/*      */           break;
/*      */         case 47:
/* 2807 */           SchemaUtil.writeSInt32List(
/* 2808 */               numberAt(pos), (List<Integer>)unsafe.getObject(message, offset), writer, true);
/*      */           break;
/*      */         case 48:
/* 2811 */           SchemaUtil.writeSInt64List(
/* 2812 */               numberAt(pos), (List<Long>)unsafe.getObject(message, offset), writer, true);
/*      */           break;
/*      */         case 49:
/* 2815 */           SchemaUtil.writeGroupList(
/* 2816 */               numberAt(pos), (List)unsafe
/* 2817 */               .getObject(message, offset), writer, 
/*      */               
/* 2819 */               getMessageFieldSchema(pos));
/*      */           break;
/*      */         
/*      */         case 50:
/* 2823 */           writeMapHelper(writer, number, unsafe.getObject(message, offset), pos);
/*      */           break;
/*      */         case 51:
/* 2826 */           if (isOneofPresent(message, number, pos)) {
/* 2827 */             writer.writeDouble(number, oneofDoubleAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 52:
/* 2831 */           if (isOneofPresent(message, number, pos)) {
/* 2832 */             writer.writeFloat(number, oneofFloatAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 53:
/* 2836 */           if (isOneofPresent(message, number, pos)) {
/* 2837 */             writer.writeInt64(number, oneofLongAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 54:
/* 2841 */           if (isOneofPresent(message, number, pos)) {
/* 2842 */             writer.writeUInt64(number, oneofLongAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 55:
/* 2846 */           if (isOneofPresent(message, number, pos)) {
/* 2847 */             writer.writeInt32(number, oneofIntAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 56:
/* 2851 */           if (isOneofPresent(message, number, pos)) {
/* 2852 */             writer.writeFixed64(number, oneofLongAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 57:
/* 2856 */           if (isOneofPresent(message, number, pos)) {
/* 2857 */             writer.writeFixed32(number, oneofIntAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 58:
/* 2861 */           if (isOneofPresent(message, number, pos)) {
/* 2862 */             writer.writeBool(number, oneofBooleanAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 59:
/* 2866 */           if (isOneofPresent(message, number, pos)) {
/* 2867 */             writeString(number, unsafe.getObject(message, offset), writer);
/*      */           }
/*      */           break;
/*      */         case 60:
/* 2871 */           if (isOneofPresent(message, number, pos)) {
/* 2872 */             Object value = unsafe.getObject(message, offset);
/* 2873 */             writer.writeMessage(number, value, getMessageFieldSchema(pos));
/*      */           } 
/*      */           break;
/*      */         case 61:
/* 2877 */           if (isOneofPresent(message, number, pos)) {
/* 2878 */             writer.writeBytes(number, (ByteString)unsafe.getObject(message, offset));
/*      */           }
/*      */           break;
/*      */         case 62:
/* 2882 */           if (isOneofPresent(message, number, pos)) {
/* 2883 */             writer.writeUInt32(number, oneofIntAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 63:
/* 2887 */           if (isOneofPresent(message, number, pos)) {
/* 2888 */             writer.writeEnum(number, oneofIntAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 64:
/* 2892 */           if (isOneofPresent(message, number, pos)) {
/* 2893 */             writer.writeSFixed32(number, oneofIntAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 65:
/* 2897 */           if (isOneofPresent(message, number, pos)) {
/* 2898 */             writer.writeSFixed64(number, oneofLongAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 66:
/* 2902 */           if (isOneofPresent(message, number, pos)) {
/* 2903 */             writer.writeSInt32(number, oneofIntAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 67:
/* 2907 */           if (isOneofPresent(message, number, pos)) {
/* 2908 */             writer.writeSInt64(number, oneofLongAt(message, offset));
/*      */           }
/*      */           break;
/*      */         case 68:
/* 2912 */           if (isOneofPresent(message, number, pos)) {
/* 2913 */             writer.writeGroup(number, unsafe
/* 2914 */                 .getObject(message, offset), getMessageFieldSchema(pos));
/*      */           }
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */     
/*      */     } 
/* 2922 */     while (nextExtension != null) {
/* 2923 */       this.extensionSchema.serializeExtension(writer, nextExtension);
/* 2924 */       nextExtension = extensionIterator.hasNext() ? extensionIterator.next() : null;
/*      */     } 
/* 2926 */     writeUnknownInMessageTo(this.unknownFieldSchema, message, writer);
/*      */   }
/*      */ 
/*      */   
/*      */   private void writeFieldsInAscendingOrderProto3(T message, Writer writer) throws IOException {
/* 2931 */     Iterator<? extends Map.Entry<?, ?>> extensionIterator = null;
/* 2932 */     Map.Entry<?, ?> nextExtension = null;
/* 2933 */     if (this.hasExtensions) {
/* 2934 */       FieldSet<?> extensions = this.extensionSchema.getExtensions(message);
/* 2935 */       if (!extensions.isEmpty()) {
/* 2936 */         extensionIterator = extensions.iterator();
/* 2937 */         nextExtension = extensionIterator.next();
/*      */       } 
/*      */     } 
/* 2940 */     int bufferLength = this.buffer.length;
/* 2941 */     for (int pos = 0; pos < bufferLength; pos += 3) {
/* 2942 */       int typeAndOffset = typeAndOffsetAt(pos);
/* 2943 */       int number = numberAt(pos);
/*      */ 
/*      */       
/* 2946 */       while (nextExtension != null && this.extensionSchema.extensionNumber(nextExtension) <= number) {
/* 2947 */         this.extensionSchema.serializeExtension(writer, nextExtension);
/* 2948 */         nextExtension = extensionIterator.hasNext() ? extensionIterator.next() : null;
/*      */       } 
/*      */       
/* 2951 */       switch (type(typeAndOffset)) {
/*      */         case 0:
/* 2953 */           if (isFieldPresent(message, pos)) {
/* 2954 */             writer.writeDouble(number, doubleAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 1:
/* 2958 */           if (isFieldPresent(message, pos)) {
/* 2959 */             writer.writeFloat(number, floatAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 2:
/* 2963 */           if (isFieldPresent(message, pos)) {
/* 2964 */             writer.writeInt64(number, longAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 3:
/* 2968 */           if (isFieldPresent(message, pos)) {
/* 2969 */             writer.writeUInt64(number, longAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 4:
/* 2973 */           if (isFieldPresent(message, pos)) {
/* 2974 */             writer.writeInt32(number, intAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 5:
/* 2978 */           if (isFieldPresent(message, pos)) {
/* 2979 */             writer.writeFixed64(number, longAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 6:
/* 2983 */           if (isFieldPresent(message, pos)) {
/* 2984 */             writer.writeFixed32(number, intAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 7:
/* 2988 */           if (isFieldPresent(message, pos)) {
/* 2989 */             writer.writeBool(number, booleanAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 8:
/* 2993 */           if (isFieldPresent(message, pos)) {
/* 2994 */             writeString(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
/*      */           }
/*      */           break;
/*      */         case 9:
/* 2998 */           if (isFieldPresent(message, pos)) {
/* 2999 */             Object value = UnsafeUtil.getObject(message, offset(typeAndOffset));
/* 3000 */             writer.writeMessage(number, value, getMessageFieldSchema(pos));
/*      */           } 
/*      */           break;
/*      */         case 10:
/* 3004 */           if (isFieldPresent(message, pos)) {
/* 3005 */             writer.writeBytes(number, 
/* 3006 */                 (ByteString)UnsafeUtil.getObject(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 11:
/* 3010 */           if (isFieldPresent(message, pos)) {
/* 3011 */             writer.writeUInt32(number, intAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 12:
/* 3015 */           if (isFieldPresent(message, pos)) {
/* 3016 */             writer.writeEnum(number, intAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 13:
/* 3020 */           if (isFieldPresent(message, pos)) {
/* 3021 */             writer.writeSFixed32(number, intAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 14:
/* 3025 */           if (isFieldPresent(message, pos)) {
/* 3026 */             writer.writeSFixed64(number, longAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 15:
/* 3030 */           if (isFieldPresent(message, pos)) {
/* 3031 */             writer.writeSInt32(number, intAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 16:
/* 3035 */           if (isFieldPresent(message, pos)) {
/* 3036 */             writer.writeSInt64(number, longAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 17:
/* 3040 */           if (isFieldPresent(message, pos)) {
/* 3041 */             writer.writeGroup(number, 
/*      */                 
/* 3043 */                 UnsafeUtil.getObject(message, offset(typeAndOffset)), 
/* 3044 */                 getMessageFieldSchema(pos));
/*      */           }
/*      */           break;
/*      */         case 18:
/* 3048 */           SchemaUtil.writeDoubleList(
/* 3049 */               numberAt(pos), 
/* 3050 */               (List<Double>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 19:
/* 3055 */           SchemaUtil.writeFloatList(
/* 3056 */               numberAt(pos), 
/* 3057 */               (List<Float>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 20:
/* 3062 */           SchemaUtil.writeInt64List(
/* 3063 */               numberAt(pos), 
/* 3064 */               (List<Long>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 21:
/* 3069 */           SchemaUtil.writeUInt64List(
/* 3070 */               numberAt(pos), 
/* 3071 */               (List<Long>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 22:
/* 3076 */           SchemaUtil.writeInt32List(
/* 3077 */               numberAt(pos), 
/* 3078 */               (List<Integer>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 23:
/* 3083 */           SchemaUtil.writeFixed64List(
/* 3084 */               numberAt(pos), 
/* 3085 */               (List<Long>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 24:
/* 3090 */           SchemaUtil.writeFixed32List(
/* 3091 */               numberAt(pos), 
/* 3092 */               (List<Integer>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 25:
/* 3097 */           SchemaUtil.writeBoolList(
/* 3098 */               numberAt(pos), 
/* 3099 */               (List<Boolean>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 26:
/* 3104 */           SchemaUtil.writeStringList(
/* 3105 */               numberAt(pos), 
/* 3106 */               (List<String>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
/*      */           break;
/*      */         
/*      */         case 27:
/* 3110 */           SchemaUtil.writeMessageList(
/* 3111 */               numberAt(pos), 
/* 3112 */               (List)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, 
/*      */               
/* 3114 */               getMessageFieldSchema(pos));
/*      */           break;
/*      */         case 28:
/* 3117 */           SchemaUtil.writeBytesList(
/* 3118 */               numberAt(pos), 
/* 3119 */               (List<ByteString>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
/*      */           break;
/*      */         
/*      */         case 29:
/* 3123 */           SchemaUtil.writeUInt32List(
/* 3124 */               numberAt(pos), 
/* 3125 */               (List<Integer>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 30:
/* 3130 */           SchemaUtil.writeEnumList(
/* 3131 */               numberAt(pos), 
/* 3132 */               (List<Integer>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 31:
/* 3137 */           SchemaUtil.writeSFixed32List(
/* 3138 */               numberAt(pos), 
/* 3139 */               (List<Integer>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 32:
/* 3144 */           SchemaUtil.writeSFixed64List(
/* 3145 */               numberAt(pos), 
/* 3146 */               (List<Long>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 33:
/* 3151 */           SchemaUtil.writeSInt32List(
/* 3152 */               numberAt(pos), 
/* 3153 */               (List<Integer>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 34:
/* 3158 */           SchemaUtil.writeSInt64List(
/* 3159 */               numberAt(pos), 
/* 3160 */               (List<Long>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case 35:
/* 3166 */           SchemaUtil.writeDoubleList(
/* 3167 */               numberAt(pos), 
/* 3168 */               (List<Double>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 36:
/* 3173 */           SchemaUtil.writeFloatList(
/* 3174 */               numberAt(pos), 
/* 3175 */               (List<Float>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 37:
/* 3180 */           SchemaUtil.writeInt64List(
/* 3181 */               numberAt(pos), 
/* 3182 */               (List<Long>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 38:
/* 3187 */           SchemaUtil.writeUInt64List(
/* 3188 */               numberAt(pos), 
/* 3189 */               (List<Long>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 39:
/* 3194 */           SchemaUtil.writeInt32List(
/* 3195 */               numberAt(pos), 
/* 3196 */               (List<Integer>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 40:
/* 3201 */           SchemaUtil.writeFixed64List(
/* 3202 */               numberAt(pos), 
/* 3203 */               (List<Long>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 41:
/* 3208 */           SchemaUtil.writeFixed32List(
/* 3209 */               numberAt(pos), 
/* 3210 */               (List<Integer>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case 42:
/* 3216 */           SchemaUtil.writeBoolList(
/* 3217 */               numberAt(pos), 
/* 3218 */               (List<Boolean>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 43:
/* 3223 */           SchemaUtil.writeUInt32List(
/* 3224 */               numberAt(pos), 
/* 3225 */               (List<Integer>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 44:
/* 3230 */           SchemaUtil.writeEnumList(
/* 3231 */               numberAt(pos), 
/* 3232 */               (List<Integer>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 45:
/* 3237 */           SchemaUtil.writeSFixed32List(
/* 3238 */               numberAt(pos), 
/* 3239 */               (List<Integer>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 46:
/* 3244 */           SchemaUtil.writeSFixed64List(
/* 3245 */               numberAt(pos), 
/* 3246 */               (List<Long>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 47:
/* 3251 */           SchemaUtil.writeSInt32List(
/* 3252 */               numberAt(pos), 
/* 3253 */               (List<Integer>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 48:
/* 3258 */           SchemaUtil.writeSInt64List(
/* 3259 */               numberAt(pos), 
/* 3260 */               (List<Long>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 49:
/* 3265 */           SchemaUtil.writeGroupList(
/* 3266 */               numberAt(pos), 
/* 3267 */               (List)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, 
/*      */               
/* 3269 */               getMessageFieldSchema(pos));
/*      */           break;
/*      */         
/*      */         case 50:
/* 3273 */           writeMapHelper(writer, number, UnsafeUtil.getObject(message, offset(typeAndOffset)), pos);
/*      */           break;
/*      */         case 51:
/* 3276 */           if (isOneofPresent(message, number, pos)) {
/* 3277 */             writer.writeDouble(number, oneofDoubleAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 52:
/* 3281 */           if (isOneofPresent(message, number, pos)) {
/* 3282 */             writer.writeFloat(number, oneofFloatAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 53:
/* 3286 */           if (isOneofPresent(message, number, pos)) {
/* 3287 */             writer.writeInt64(number, oneofLongAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 54:
/* 3291 */           if (isOneofPresent(message, number, pos)) {
/* 3292 */             writer.writeUInt64(number, oneofLongAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 55:
/* 3296 */           if (isOneofPresent(message, number, pos)) {
/* 3297 */             writer.writeInt32(number, oneofIntAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 56:
/* 3301 */           if (isOneofPresent(message, number, pos)) {
/* 3302 */             writer.writeFixed64(number, oneofLongAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 57:
/* 3306 */           if (isOneofPresent(message, number, pos)) {
/* 3307 */             writer.writeFixed32(number, oneofIntAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 58:
/* 3311 */           if (isOneofPresent(message, number, pos)) {
/* 3312 */             writer.writeBool(number, oneofBooleanAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 59:
/* 3316 */           if (isOneofPresent(message, number, pos)) {
/* 3317 */             writeString(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
/*      */           }
/*      */           break;
/*      */         case 60:
/* 3321 */           if (isOneofPresent(message, number, pos)) {
/* 3322 */             Object value = UnsafeUtil.getObject(message, offset(typeAndOffset));
/* 3323 */             writer.writeMessage(number, value, getMessageFieldSchema(pos));
/*      */           } 
/*      */           break;
/*      */         case 61:
/* 3327 */           if (isOneofPresent(message, number, pos)) {
/* 3328 */             writer.writeBytes(number, 
/* 3329 */                 (ByteString)UnsafeUtil.getObject(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 62:
/* 3333 */           if (isOneofPresent(message, number, pos)) {
/* 3334 */             writer.writeUInt32(number, oneofIntAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 63:
/* 3338 */           if (isOneofPresent(message, number, pos)) {
/* 3339 */             writer.writeEnum(number, oneofIntAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 64:
/* 3343 */           if (isOneofPresent(message, number, pos)) {
/* 3344 */             writer.writeSFixed32(number, oneofIntAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 65:
/* 3348 */           if (isOneofPresent(message, number, pos)) {
/* 3349 */             writer.writeSFixed64(number, oneofLongAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 66:
/* 3353 */           if (isOneofPresent(message, number, pos)) {
/* 3354 */             writer.writeSInt32(number, oneofIntAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 67:
/* 3358 */           if (isOneofPresent(message, number, pos)) {
/* 3359 */             writer.writeSInt64(number, oneofLongAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 68:
/* 3363 */           if (isOneofPresent(message, number, pos)) {
/* 3364 */             writer.writeGroup(number, 
/*      */                 
/* 3366 */                 UnsafeUtil.getObject(message, offset(typeAndOffset)), 
/* 3367 */                 getMessageFieldSchema(pos));
/*      */           }
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */     
/*      */     } 
/* 3375 */     while (nextExtension != null) {
/* 3376 */       this.extensionSchema.serializeExtension(writer, nextExtension);
/* 3377 */       nextExtension = extensionIterator.hasNext() ? extensionIterator.next() : null;
/*      */     } 
/* 3379 */     writeUnknownInMessageTo(this.unknownFieldSchema, message, writer);
/*      */   }
/*      */ 
/*      */   
/*      */   private void writeFieldsInDescendingOrder(T message, Writer writer) throws IOException {
/* 3384 */     writeUnknownInMessageTo(this.unknownFieldSchema, message, writer);
/*      */     
/* 3386 */     Iterator<? extends Map.Entry<?, ?>> extensionIterator = null;
/* 3387 */     Map.Entry<?, ?> nextExtension = null;
/* 3388 */     if (this.hasExtensions) {
/* 3389 */       FieldSet<?> extensions = this.extensionSchema.getExtensions(message);
/* 3390 */       if (!extensions.isEmpty()) {
/* 3391 */         extensionIterator = extensions.descendingIterator();
/* 3392 */         nextExtension = extensionIterator.next();
/*      */       } 
/*      */     } 
/*      */     
/* 3396 */     for (int pos = this.buffer.length - 3; pos >= 0; pos -= 3) {
/* 3397 */       int typeAndOffset = typeAndOffsetAt(pos);
/* 3398 */       int number = numberAt(pos);
/*      */ 
/*      */       
/* 3401 */       while (nextExtension != null && this.extensionSchema.extensionNumber(nextExtension) > number) {
/* 3402 */         this.extensionSchema.serializeExtension(writer, nextExtension);
/* 3403 */         nextExtension = extensionIterator.hasNext() ? extensionIterator.next() : null;
/*      */       } 
/*      */       
/* 3406 */       switch (type(typeAndOffset)) {
/*      */         case 0:
/* 3408 */           if (isFieldPresent(message, pos)) {
/* 3409 */             writer.writeDouble(number, doubleAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 1:
/* 3413 */           if (isFieldPresent(message, pos)) {
/* 3414 */             writer.writeFloat(number, floatAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 2:
/* 3418 */           if (isFieldPresent(message, pos)) {
/* 3419 */             writer.writeInt64(number, longAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 3:
/* 3423 */           if (isFieldPresent(message, pos)) {
/* 3424 */             writer.writeUInt64(number, longAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 4:
/* 3428 */           if (isFieldPresent(message, pos)) {
/* 3429 */             writer.writeInt32(number, intAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 5:
/* 3433 */           if (isFieldPresent(message, pos)) {
/* 3434 */             writer.writeFixed64(number, longAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 6:
/* 3438 */           if (isFieldPresent(message, pos)) {
/* 3439 */             writer.writeFixed32(number, intAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 7:
/* 3443 */           if (isFieldPresent(message, pos)) {
/* 3444 */             writer.writeBool(number, booleanAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 8:
/* 3448 */           if (isFieldPresent(message, pos)) {
/* 3449 */             writeString(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
/*      */           }
/*      */           break;
/*      */         case 9:
/* 3453 */           if (isFieldPresent(message, pos)) {
/* 3454 */             Object value = UnsafeUtil.getObject(message, offset(typeAndOffset));
/* 3455 */             writer.writeMessage(number, value, getMessageFieldSchema(pos));
/*      */           } 
/*      */           break;
/*      */         case 10:
/* 3459 */           if (isFieldPresent(message, pos)) {
/* 3460 */             writer.writeBytes(number, 
/* 3461 */                 (ByteString)UnsafeUtil.getObject(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 11:
/* 3465 */           if (isFieldPresent(message, pos)) {
/* 3466 */             writer.writeUInt32(number, intAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 12:
/* 3470 */           if (isFieldPresent(message, pos)) {
/* 3471 */             writer.writeEnum(number, intAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 13:
/* 3475 */           if (isFieldPresent(message, pos)) {
/* 3476 */             writer.writeSFixed32(number, intAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 14:
/* 3480 */           if (isFieldPresent(message, pos)) {
/* 3481 */             writer.writeSFixed64(number, longAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 15:
/* 3485 */           if (isFieldPresent(message, pos)) {
/* 3486 */             writer.writeSInt32(number, intAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 16:
/* 3490 */           if (isFieldPresent(message, pos)) {
/* 3491 */             writer.writeSInt64(number, longAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 17:
/* 3495 */           if (isFieldPresent(message, pos)) {
/* 3496 */             writer.writeGroup(number, 
/*      */                 
/* 3498 */                 UnsafeUtil.getObject(message, offset(typeAndOffset)), 
/* 3499 */                 getMessageFieldSchema(pos));
/*      */           }
/*      */           break;
/*      */         case 18:
/* 3503 */           SchemaUtil.writeDoubleList(
/* 3504 */               numberAt(pos), 
/* 3505 */               (List<Double>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 19:
/* 3510 */           SchemaUtil.writeFloatList(
/* 3511 */               numberAt(pos), 
/* 3512 */               (List<Float>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 20:
/* 3517 */           SchemaUtil.writeInt64List(
/* 3518 */               numberAt(pos), 
/* 3519 */               (List<Long>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 21:
/* 3524 */           SchemaUtil.writeUInt64List(
/* 3525 */               numberAt(pos), 
/* 3526 */               (List<Long>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 22:
/* 3531 */           SchemaUtil.writeInt32List(
/* 3532 */               numberAt(pos), 
/* 3533 */               (List<Integer>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 23:
/* 3538 */           SchemaUtil.writeFixed64List(
/* 3539 */               numberAt(pos), 
/* 3540 */               (List<Long>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 24:
/* 3545 */           SchemaUtil.writeFixed32List(
/* 3546 */               numberAt(pos), 
/* 3547 */               (List<Integer>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 25:
/* 3552 */           SchemaUtil.writeBoolList(
/* 3553 */               numberAt(pos), 
/* 3554 */               (List<Boolean>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 26:
/* 3559 */           SchemaUtil.writeStringList(
/* 3560 */               numberAt(pos), 
/* 3561 */               (List<String>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
/*      */           break;
/*      */         
/*      */         case 27:
/* 3565 */           SchemaUtil.writeMessageList(
/* 3566 */               numberAt(pos), 
/* 3567 */               (List)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, 
/*      */               
/* 3569 */               getMessageFieldSchema(pos));
/*      */           break;
/*      */         case 28:
/* 3572 */           SchemaUtil.writeBytesList(
/* 3573 */               numberAt(pos), 
/* 3574 */               (List<ByteString>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
/*      */           break;
/*      */         
/*      */         case 29:
/* 3578 */           SchemaUtil.writeUInt32List(
/* 3579 */               numberAt(pos), 
/* 3580 */               (List<Integer>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 30:
/* 3585 */           SchemaUtil.writeEnumList(
/* 3586 */               numberAt(pos), 
/* 3587 */               (List<Integer>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 31:
/* 3592 */           SchemaUtil.writeSFixed32List(
/* 3593 */               numberAt(pos), 
/* 3594 */               (List<Integer>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 32:
/* 3599 */           SchemaUtil.writeSFixed64List(
/* 3600 */               numberAt(pos), 
/* 3601 */               (List<Long>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 33:
/* 3606 */           SchemaUtil.writeSInt32List(
/* 3607 */               numberAt(pos), 
/* 3608 */               (List<Integer>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 34:
/* 3613 */           SchemaUtil.writeSInt64List(
/* 3614 */               numberAt(pos), 
/* 3615 */               (List<Long>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 35:
/* 3620 */           SchemaUtil.writeDoubleList(
/* 3621 */               numberAt(pos), 
/* 3622 */               (List<Double>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 36:
/* 3627 */           SchemaUtil.writeFloatList(
/* 3628 */               numberAt(pos), 
/* 3629 */               (List<Float>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 37:
/* 3634 */           SchemaUtil.writeInt64List(
/* 3635 */               numberAt(pos), 
/* 3636 */               (List<Long>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 38:
/* 3641 */           SchemaUtil.writeUInt64List(
/* 3642 */               numberAt(pos), 
/* 3643 */               (List<Long>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 39:
/* 3648 */           SchemaUtil.writeInt32List(
/* 3649 */               numberAt(pos), 
/* 3650 */               (List<Integer>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 40:
/* 3655 */           SchemaUtil.writeFixed64List(
/* 3656 */               numberAt(pos), 
/* 3657 */               (List<Long>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 41:
/* 3662 */           SchemaUtil.writeFixed32List(
/* 3663 */               numberAt(pos), 
/* 3664 */               (List<Integer>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case 42:
/* 3670 */           SchemaUtil.writeBoolList(
/* 3671 */               numberAt(pos), 
/* 3672 */               (List<Boolean>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 43:
/* 3677 */           SchemaUtil.writeUInt32List(
/* 3678 */               numberAt(pos), 
/* 3679 */               (List<Integer>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 44:
/* 3684 */           SchemaUtil.writeEnumList(
/* 3685 */               numberAt(pos), 
/* 3686 */               (List<Integer>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 45:
/* 3691 */           SchemaUtil.writeSFixed32List(
/* 3692 */               numberAt(pos), 
/* 3693 */               (List<Integer>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 46:
/* 3698 */           SchemaUtil.writeSFixed64List(
/* 3699 */               numberAt(pos), 
/* 3700 */               (List<Long>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 47:
/* 3705 */           SchemaUtil.writeSInt32List(
/* 3706 */               numberAt(pos), 
/* 3707 */               (List<Integer>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 48:
/* 3712 */           SchemaUtil.writeSInt64List(
/* 3713 */               numberAt(pos), 
/* 3714 */               (List<Long>)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 49:
/* 3719 */           SchemaUtil.writeGroupList(
/* 3720 */               numberAt(pos), 
/* 3721 */               (List)UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, 
/*      */               
/* 3723 */               getMessageFieldSchema(pos));
/*      */           break;
/*      */         
/*      */         case 50:
/* 3727 */           writeMapHelper(writer, number, UnsafeUtil.getObject(message, offset(typeAndOffset)), pos);
/*      */           break;
/*      */         case 51:
/* 3730 */           if (isOneofPresent(message, number, pos)) {
/* 3731 */             writer.writeDouble(number, oneofDoubleAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 52:
/* 3735 */           if (isOneofPresent(message, number, pos)) {
/* 3736 */             writer.writeFloat(number, oneofFloatAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 53:
/* 3740 */           if (isOneofPresent(message, number, pos)) {
/* 3741 */             writer.writeInt64(number, oneofLongAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 54:
/* 3745 */           if (isOneofPresent(message, number, pos)) {
/* 3746 */             writer.writeUInt64(number, oneofLongAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 55:
/* 3750 */           if (isOneofPresent(message, number, pos)) {
/* 3751 */             writer.writeInt32(number, oneofIntAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 56:
/* 3755 */           if (isOneofPresent(message, number, pos)) {
/* 3756 */             writer.writeFixed64(number, oneofLongAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 57:
/* 3760 */           if (isOneofPresent(message, number, pos)) {
/* 3761 */             writer.writeFixed32(number, oneofIntAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 58:
/* 3765 */           if (isOneofPresent(message, number, pos)) {
/* 3766 */             writer.writeBool(number, oneofBooleanAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 59:
/* 3770 */           if (isOneofPresent(message, number, pos)) {
/* 3771 */             writeString(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
/*      */           }
/*      */           break;
/*      */         case 60:
/* 3775 */           if (isOneofPresent(message, number, pos)) {
/* 3776 */             Object value = UnsafeUtil.getObject(message, offset(typeAndOffset));
/* 3777 */             writer.writeMessage(number, value, getMessageFieldSchema(pos));
/*      */           } 
/*      */           break;
/*      */         case 61:
/* 3781 */           if (isOneofPresent(message, number, pos)) {
/* 3782 */             writer.writeBytes(number, 
/* 3783 */                 (ByteString)UnsafeUtil.getObject(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 62:
/* 3787 */           if (isOneofPresent(message, number, pos)) {
/* 3788 */             writer.writeUInt32(number, oneofIntAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 63:
/* 3792 */           if (isOneofPresent(message, number, pos)) {
/* 3793 */             writer.writeEnum(number, oneofIntAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 64:
/* 3797 */           if (isOneofPresent(message, number, pos)) {
/* 3798 */             writer.writeSFixed32(number, oneofIntAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 65:
/* 3802 */           if (isOneofPresent(message, number, pos)) {
/* 3803 */             writer.writeSFixed64(number, oneofLongAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 66:
/* 3807 */           if (isOneofPresent(message, number, pos)) {
/* 3808 */             writer.writeSInt32(number, oneofIntAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 67:
/* 3812 */           if (isOneofPresent(message, number, pos)) {
/* 3813 */             writer.writeSInt64(number, oneofLongAt(message, offset(typeAndOffset)));
/*      */           }
/*      */           break;
/*      */         case 68:
/* 3817 */           if (isOneofPresent(message, number, pos)) {
/* 3818 */             writer.writeGroup(number, 
/*      */                 
/* 3820 */                 UnsafeUtil.getObject(message, offset(typeAndOffset)), 
/* 3821 */                 getMessageFieldSchema(pos));
/*      */           }
/*      */           break;
/*      */       } 
/*      */ 
/*      */     
/*      */     } 
/* 3828 */     while (nextExtension != null) {
/* 3829 */       this.extensionSchema.serializeExtension(writer, nextExtension);
/* 3830 */       nextExtension = extensionIterator.hasNext() ? extensionIterator.next() : null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private <K, V> void writeMapHelper(Writer writer, int number, Object mapField, int pos) throws IOException {
/* 3837 */     if (mapField != null) {
/* 3838 */       writer.writeMap(number, this.mapFieldSchema
/*      */           
/* 3840 */           .forMapMetadata(getMapFieldDefaultEntry(pos)), this.mapFieldSchema
/* 3841 */           .forMapData(mapField));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private <UT, UB> void writeUnknownInMessageTo(UnknownFieldSchema<UT, UB> schema, T message, Writer writer) throws IOException {
/* 3847 */     schema.writeTo(schema.getFromMessage(message), writer);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void mergeFrom(T message, Reader reader, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 3853 */     if (extensionRegistry == null) {
/* 3854 */       throw new NullPointerException();
/*      */     }
/* 3856 */     mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, message, reader, extensionRegistry);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private <UT, UB, ET extends FieldSet.FieldDescriptorLite<ET>> void mergeFromHelper(UnknownFieldSchema<UT, UB> unknownFieldSchema, ExtensionSchema<ET> extensionSchema, T message, Reader reader, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 3870 */     UB unknownFields = null;
/* 3871 */     FieldSet<ET> extensions = null;
/*      */     try {
/*      */       while (true) {
/* 3874 */         int number = reader.getFieldNumber();
/* 3875 */         int pos = positionForFieldNumber(number);
/* 3876 */         if (pos < 0) {
/* 3877 */           if (number == Integer.MAX_VALUE) {
/*      */             return;
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 3884 */           Object extension = !this.hasExtensions ? null : extensionSchema.findExtensionByNumber(extensionRegistry, this.defaultInstance, number);
/*      */           
/* 3886 */           if (extension != null) {
/* 3887 */             if (extensions == null) {
/* 3888 */               extensions = extensionSchema.getMutableExtensions(message);
/*      */             }
/*      */             
/* 3891 */             unknownFields = extensionSchema.parseExtension(reader, extension, extensionRegistry, extensions, unknownFields, unknownFieldSchema);
/*      */ 
/*      */ 
/*      */             
/*      */             continue;
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 3900 */           if (unknownFieldSchema.shouldDiscardUnknownFields(reader)) {
/* 3901 */             if (reader.skipField()) {
/*      */               continue;
/*      */             }
/*      */           } else {
/* 3905 */             if (unknownFields == null) {
/* 3906 */               unknownFields = unknownFieldSchema.getBuilderFromMessage(message);
/*      */             }
/*      */             
/* 3909 */             if (unknownFieldSchema.mergeOneFieldFrom(unknownFields, reader)) {
/*      */               continue;
/*      */             }
/*      */           } 
/*      */           
/*      */           return;
/*      */         } 
/* 3916 */         int typeAndOffset = typeAndOffsetAt(pos); try {
/*      */           int j; List<Integer> enumList; int enumValue;
/*      */           Internal.EnumVerifier enumVerifier;
/* 3919 */           switch (type(typeAndOffset)) {
/*      */             case 0:
/* 3921 */               UnsafeUtil.putDouble(message, offset(typeAndOffset), reader.readDouble());
/* 3922 */               setFieldPresent(message, pos);
/*      */               continue;
/*      */             case 1:
/* 3925 */               UnsafeUtil.putFloat(message, offset(typeAndOffset), reader.readFloat());
/* 3926 */               setFieldPresent(message, pos);
/*      */               continue;
/*      */             case 2:
/* 3929 */               UnsafeUtil.putLong(message, offset(typeAndOffset), reader.readInt64());
/* 3930 */               setFieldPresent(message, pos);
/*      */               continue;
/*      */             case 3:
/* 3933 */               UnsafeUtil.putLong(message, offset(typeAndOffset), reader.readUInt64());
/* 3934 */               setFieldPresent(message, pos);
/*      */               continue;
/*      */             case 4:
/* 3937 */               UnsafeUtil.putInt(message, offset(typeAndOffset), reader.readInt32());
/* 3938 */               setFieldPresent(message, pos);
/*      */               continue;
/*      */             case 5:
/* 3941 */               UnsafeUtil.putLong(message, offset(typeAndOffset), reader.readFixed64());
/* 3942 */               setFieldPresent(message, pos);
/*      */               continue;
/*      */             case 6:
/* 3945 */               UnsafeUtil.putInt(message, offset(typeAndOffset), reader.readFixed32());
/* 3946 */               setFieldPresent(message, pos);
/*      */               continue;
/*      */             case 7:
/* 3949 */               UnsafeUtil.putBoolean(message, offset(typeAndOffset), reader.readBool());
/* 3950 */               setFieldPresent(message, pos);
/*      */               continue;
/*      */             case 8:
/* 3953 */               readString(message, typeAndOffset, reader);
/* 3954 */               setFieldPresent(message, pos);
/*      */               continue;
/*      */             
/*      */             case 9:
/* 3958 */               if (isFieldPresent(message, pos)) {
/*      */                 
/* 3960 */                 Object mergedResult = Internal.mergeMessage(
/* 3961 */                     UnsafeUtil.getObject(message, offset(typeAndOffset)), reader
/* 3962 */                     .readMessageBySchemaWithCheck(
/* 3963 */                       getMessageFieldSchema(pos), extensionRegistry));
/* 3964 */                 UnsafeUtil.putObject(message, offset(typeAndOffset), mergedResult); continue;
/*      */               } 
/* 3966 */               UnsafeUtil.putObject(message, 
/*      */                   
/* 3968 */                   offset(typeAndOffset), reader
/* 3969 */                   .readMessageBySchemaWithCheck(
/* 3970 */                     getMessageFieldSchema(pos), extensionRegistry));
/* 3971 */               setFieldPresent(message, pos);
/*      */               continue;
/*      */ 
/*      */             
/*      */             case 10:
/* 3976 */               UnsafeUtil.putObject(message, offset(typeAndOffset), reader.readBytes());
/* 3977 */               setFieldPresent(message, pos);
/*      */               continue;
/*      */             case 11:
/* 3980 */               UnsafeUtil.putInt(message, offset(typeAndOffset), reader.readUInt32());
/* 3981 */               setFieldPresent(message, pos);
/*      */               continue;
/*      */             
/*      */             case 12:
/* 3985 */               j = reader.readEnum();
/* 3986 */               enumVerifier = getEnumFieldVerifier(pos);
/* 3987 */               if (enumVerifier == null || enumVerifier.isInRange(j)) {
/* 3988 */                 UnsafeUtil.putInt(message, offset(typeAndOffset), j);
/* 3989 */                 setFieldPresent(message, pos);
/*      */                 continue;
/*      */               } 
/* 3992 */               unknownFields = SchemaUtil.storeUnknownEnum(number, j, unknownFields, unknownFieldSchema);
/*      */               continue;
/*      */ 
/*      */ 
/*      */             
/*      */             case 13:
/* 3998 */               UnsafeUtil.putInt(message, offset(typeAndOffset), reader.readSFixed32());
/* 3999 */               setFieldPresent(message, pos);
/*      */               continue;
/*      */             case 14:
/* 4002 */               UnsafeUtil.putLong(message, offset(typeAndOffset), reader.readSFixed64());
/* 4003 */               setFieldPresent(message, pos);
/*      */               continue;
/*      */             case 15:
/* 4006 */               UnsafeUtil.putInt(message, offset(typeAndOffset), reader.readSInt32());
/* 4007 */               setFieldPresent(message, pos);
/*      */               continue;
/*      */             case 16:
/* 4010 */               UnsafeUtil.putLong(message, offset(typeAndOffset), reader.readSInt64());
/* 4011 */               setFieldPresent(message, pos);
/*      */               continue;
/*      */             
/*      */             case 17:
/* 4015 */               if (isFieldPresent(message, pos)) {
/*      */                 
/* 4017 */                 Object mergedResult = Internal.mergeMessage(
/* 4018 */                     UnsafeUtil.getObject(message, offset(typeAndOffset)), reader
/* 4019 */                     .readGroupBySchemaWithCheck(
/* 4020 */                       getMessageFieldSchema(pos), extensionRegistry));
/* 4021 */                 UnsafeUtil.putObject(message, offset(typeAndOffset), mergedResult); continue;
/*      */               } 
/* 4023 */               UnsafeUtil.putObject(message, 
/*      */                   
/* 4025 */                   offset(typeAndOffset), reader
/* 4026 */                   .readGroupBySchemaWithCheck(
/* 4027 */                     getMessageFieldSchema(pos), extensionRegistry));
/* 4028 */               setFieldPresent(message, pos);
/*      */               continue;
/*      */ 
/*      */             
/*      */             case 18:
/* 4033 */               reader.readDoubleList(this.listFieldSchema
/* 4034 */                   .mutableListAt(message, offset(typeAndOffset)));
/*      */               continue;
/*      */             case 19:
/* 4037 */               reader.readFloatList(this.listFieldSchema
/* 4038 */                   .mutableListAt(message, offset(typeAndOffset)));
/*      */               continue;
/*      */             case 20:
/* 4041 */               reader.readInt64List(this.listFieldSchema
/* 4042 */                   .mutableListAt(message, offset(typeAndOffset)));
/*      */               continue;
/*      */             case 21:
/* 4045 */               reader.readUInt64List(this.listFieldSchema
/* 4046 */                   .mutableListAt(message, offset(typeAndOffset)));
/*      */               continue;
/*      */             case 22:
/* 4049 */               reader.readInt32List(this.listFieldSchema
/* 4050 */                   .mutableListAt(message, offset(typeAndOffset)));
/*      */               continue;
/*      */             case 23:
/* 4053 */               reader.readFixed64List(this.listFieldSchema
/* 4054 */                   .mutableListAt(message, offset(typeAndOffset)));
/*      */               continue;
/*      */             case 24:
/* 4057 */               reader.readFixed32List(this.listFieldSchema
/* 4058 */                   .mutableListAt(message, offset(typeAndOffset)));
/*      */               continue;
/*      */             case 25:
/* 4061 */               reader.readBoolList(this.listFieldSchema
/* 4062 */                   .mutableListAt(message, offset(typeAndOffset)));
/*      */               continue;
/*      */             case 26:
/* 4065 */               readStringList(message, typeAndOffset, reader);
/*      */               continue;
/*      */             
/*      */             case 27:
/* 4069 */               readMessageList(message, typeAndOffset, reader, 
/*      */ 
/*      */ 
/*      */                   
/* 4073 */                   getMessageFieldSchema(pos), extensionRegistry);
/*      */               continue;
/*      */ 
/*      */             
/*      */             case 28:
/* 4078 */               reader.readBytesList(this.listFieldSchema
/* 4079 */                   .mutableListAt(message, offset(typeAndOffset)));
/*      */               continue;
/*      */             case 29:
/* 4082 */               reader.readUInt32List(this.listFieldSchema
/* 4083 */                   .mutableListAt(message, offset(typeAndOffset)));
/*      */               continue;
/*      */ 
/*      */             
/*      */             case 30:
/* 4088 */               enumList = this.listFieldSchema.mutableListAt(message, offset(typeAndOffset));
/* 4089 */               reader.readEnumList(enumList);
/*      */               
/* 4091 */               unknownFields = SchemaUtil.filterUnknownEnumList(number, enumList, 
/*      */ 
/*      */                   
/* 4094 */                   getEnumFieldVerifier(pos), unknownFields, unknownFieldSchema);
/*      */               continue;
/*      */ 
/*      */ 
/*      */             
/*      */             case 31:
/* 4100 */               reader.readSFixed32List(this.listFieldSchema
/* 4101 */                   .mutableListAt(message, offset(typeAndOffset)));
/*      */               continue;
/*      */             case 32:
/* 4104 */               reader.readSFixed64List(this.listFieldSchema
/* 4105 */                   .mutableListAt(message, offset(typeAndOffset)));
/*      */               continue;
/*      */             case 33:
/* 4108 */               reader.readSInt32List(this.listFieldSchema
/* 4109 */                   .mutableListAt(message, offset(typeAndOffset)));
/*      */               continue;
/*      */             case 34:
/* 4112 */               reader.readSInt64List(this.listFieldSchema
/* 4113 */                   .mutableListAt(message, offset(typeAndOffset)));
/*      */               continue;
/*      */             case 35:
/* 4116 */               reader.readDoubleList(this.listFieldSchema
/* 4117 */                   .mutableListAt(message, offset(typeAndOffset)));
/*      */               continue;
/*      */             case 36:
/* 4120 */               reader.readFloatList(this.listFieldSchema
/* 4121 */                   .mutableListAt(message, offset(typeAndOffset)));
/*      */               continue;
/*      */             case 37:
/* 4124 */               reader.readInt64List(this.listFieldSchema
/* 4125 */                   .mutableListAt(message, offset(typeAndOffset)));
/*      */               continue;
/*      */             case 38:
/* 4128 */               reader.readUInt64List(this.listFieldSchema
/* 4129 */                   .mutableListAt(message, offset(typeAndOffset)));
/*      */               continue;
/*      */             case 39:
/* 4132 */               reader.readInt32List(this.listFieldSchema
/* 4133 */                   .mutableListAt(message, offset(typeAndOffset)));
/*      */               continue;
/*      */             case 40:
/* 4136 */               reader.readFixed64List(this.listFieldSchema
/* 4137 */                   .mutableListAt(message, offset(typeAndOffset)));
/*      */               continue;
/*      */             case 41:
/* 4140 */               reader.readFixed32List(this.listFieldSchema
/* 4141 */                   .mutableListAt(message, offset(typeAndOffset)));
/*      */               continue;
/*      */             case 42:
/* 4144 */               reader.readBoolList(this.listFieldSchema
/* 4145 */                   .mutableListAt(message, offset(typeAndOffset)));
/*      */               continue;
/*      */             case 43:
/* 4148 */               reader.readUInt32List(this.listFieldSchema
/* 4149 */                   .mutableListAt(message, offset(typeAndOffset)));
/*      */               continue;
/*      */ 
/*      */             
/*      */             case 44:
/* 4154 */               enumList = this.listFieldSchema.mutableListAt(message, offset(typeAndOffset));
/* 4155 */               reader.readEnumList(enumList);
/*      */               
/* 4157 */               unknownFields = SchemaUtil.filterUnknownEnumList(number, enumList, 
/*      */ 
/*      */                   
/* 4160 */                   getEnumFieldVerifier(pos), unknownFields, unknownFieldSchema);
/*      */               continue;
/*      */ 
/*      */ 
/*      */             
/*      */             case 45:
/* 4166 */               reader.readSFixed32List(this.listFieldSchema
/* 4167 */                   .mutableListAt(message, offset(typeAndOffset)));
/*      */               continue;
/*      */             case 46:
/* 4170 */               reader.readSFixed64List(this.listFieldSchema
/* 4171 */                   .mutableListAt(message, offset(typeAndOffset)));
/*      */               continue;
/*      */             case 47:
/* 4174 */               reader.readSInt32List(this.listFieldSchema
/* 4175 */                   .mutableListAt(message, offset(typeAndOffset)));
/*      */               continue;
/*      */             case 48:
/* 4178 */               reader.readSInt64List(this.listFieldSchema
/* 4179 */                   .mutableListAt(message, offset(typeAndOffset)));
/*      */               continue;
/*      */             
/*      */             case 49:
/* 4183 */               readGroupList(message, 
/*      */                   
/* 4185 */                   offset(typeAndOffset), reader, 
/*      */                   
/* 4187 */                   getMessageFieldSchema(pos), extensionRegistry);
/*      */               continue;
/*      */ 
/*      */             
/*      */             case 50:
/* 4192 */               mergeMap(message, pos, getMapFieldDefaultEntry(pos), extensionRegistry, reader);
/*      */               continue;
/*      */             case 51:
/* 4195 */               UnsafeUtil.putObject(message, 
/* 4196 */                   offset(typeAndOffset), Double.valueOf(reader.readDouble()));
/* 4197 */               setOneofPresent(message, number, pos);
/*      */               continue;
/*      */             case 52:
/* 4200 */               UnsafeUtil.putObject(message, 
/* 4201 */                   offset(typeAndOffset), Float.valueOf(reader.readFloat()));
/* 4202 */               setOneofPresent(message, number, pos);
/*      */               continue;
/*      */             case 53:
/* 4205 */               UnsafeUtil.putObject(message, 
/* 4206 */                   offset(typeAndOffset), Long.valueOf(reader.readInt64()));
/* 4207 */               setOneofPresent(message, number, pos);
/*      */               continue;
/*      */             case 54:
/* 4210 */               UnsafeUtil.putObject(message, 
/* 4211 */                   offset(typeAndOffset), Long.valueOf(reader.readUInt64()));
/* 4212 */               setOneofPresent(message, number, pos);
/*      */               continue;
/*      */             case 55:
/* 4215 */               UnsafeUtil.putObject(message, 
/* 4216 */                   offset(typeAndOffset), Integer.valueOf(reader.readInt32()));
/* 4217 */               setOneofPresent(message, number, pos);
/*      */               continue;
/*      */             case 56:
/* 4220 */               UnsafeUtil.putObject(message, 
/* 4221 */                   offset(typeAndOffset), Long.valueOf(reader.readFixed64()));
/* 4222 */               setOneofPresent(message, number, pos);
/*      */               continue;
/*      */             case 57:
/* 4225 */               UnsafeUtil.putObject(message, 
/* 4226 */                   offset(typeAndOffset), Integer.valueOf(reader.readFixed32()));
/* 4227 */               setOneofPresent(message, number, pos);
/*      */               continue;
/*      */             case 58:
/* 4230 */               UnsafeUtil.putObject(message, 
/* 4231 */                   offset(typeAndOffset), Boolean.valueOf(reader.readBool()));
/* 4232 */               setOneofPresent(message, number, pos);
/*      */               continue;
/*      */             case 59:
/* 4235 */               readString(message, typeAndOffset, reader);
/* 4236 */               setOneofPresent(message, number, pos);
/*      */               continue;
/*      */             case 60:
/* 4239 */               if (isOneofPresent(message, number, pos)) {
/*      */                 
/* 4241 */                 Object mergedResult = Internal.mergeMessage(
/* 4242 */                     UnsafeUtil.getObject(message, offset(typeAndOffset)), reader
/* 4243 */                     .readMessageBySchemaWithCheck(
/* 4244 */                       getMessageFieldSchema(pos), extensionRegistry));
/* 4245 */                 UnsafeUtil.putObject(message, offset(typeAndOffset), mergedResult);
/*      */               } else {
/* 4247 */                 UnsafeUtil.putObject(message, 
/*      */                     
/* 4249 */                     offset(typeAndOffset), reader
/* 4250 */                     .readMessageBySchemaWithCheck(
/* 4251 */                       getMessageFieldSchema(pos), extensionRegistry));
/* 4252 */                 setFieldPresent(message, pos);
/*      */               } 
/* 4254 */               setOneofPresent(message, number, pos);
/*      */               continue;
/*      */             case 61:
/* 4257 */               UnsafeUtil.putObject(message, offset(typeAndOffset), reader.readBytes());
/* 4258 */               setOneofPresent(message, number, pos);
/*      */               continue;
/*      */             case 62:
/* 4261 */               UnsafeUtil.putObject(message, 
/* 4262 */                   offset(typeAndOffset), Integer.valueOf(reader.readUInt32()));
/* 4263 */               setOneofPresent(message, number, pos);
/*      */               continue;
/*      */             
/*      */             case 63:
/* 4267 */               enumValue = reader.readEnum();
/* 4268 */               enumVerifier = getEnumFieldVerifier(pos);
/* 4269 */               if (enumVerifier == null || enumVerifier.isInRange(enumValue)) {
/* 4270 */                 UnsafeUtil.putObject(message, offset(typeAndOffset), Integer.valueOf(enumValue));
/* 4271 */                 setOneofPresent(message, number, pos);
/*      */                 continue;
/*      */               } 
/* 4274 */               unknownFields = SchemaUtil.storeUnknownEnum(number, enumValue, unknownFields, unknownFieldSchema);
/*      */               continue;
/*      */ 
/*      */ 
/*      */             
/*      */             case 64:
/* 4280 */               UnsafeUtil.putObject(message, 
/* 4281 */                   offset(typeAndOffset), Integer.valueOf(reader.readSFixed32()));
/* 4282 */               setOneofPresent(message, number, pos);
/*      */               continue;
/*      */             case 65:
/* 4285 */               UnsafeUtil.putObject(message, 
/* 4286 */                   offset(typeAndOffset), Long.valueOf(reader.readSFixed64()));
/* 4287 */               setOneofPresent(message, number, pos);
/*      */               continue;
/*      */             case 66:
/* 4290 */               UnsafeUtil.putObject(message, 
/* 4291 */                   offset(typeAndOffset), Integer.valueOf(reader.readSInt32()));
/* 4292 */               setOneofPresent(message, number, pos);
/*      */               continue;
/*      */             case 67:
/* 4295 */               UnsafeUtil.putObject(message, 
/* 4296 */                   offset(typeAndOffset), Long.valueOf(reader.readSInt64()));
/* 4297 */               setOneofPresent(message, number, pos);
/*      */               continue;
/*      */             case 68:
/* 4300 */               UnsafeUtil.putObject(message, 
/*      */                   
/* 4302 */                   offset(typeAndOffset), reader
/* 4303 */                   .readGroupBySchemaWithCheck(getMessageFieldSchema(pos), extensionRegistry));
/* 4304 */               setOneofPresent(message, number, pos);
/*      */               continue;
/*      */           } 
/*      */           
/* 4308 */           if (unknownFields == null) {
/* 4309 */             unknownFields = unknownFieldSchema.newBuilder();
/*      */           }
/* 4311 */           if (!unknownFieldSchema.mergeOneFieldFrom(unknownFields, reader))
/*      */           {
/*      */             return;
/*      */           }
/*      */         }
/* 4316 */         catch (InvalidWireTypeException e) {
/*      */ 
/*      */           
/* 4319 */           if (unknownFieldSchema.shouldDiscardUnknownFields(reader)) {
/* 4320 */             if (!reader.skipField())
/*      */               return; 
/*      */             continue;
/*      */           } 
/* 4324 */           if (unknownFields == null) {
/* 4325 */             unknownFields = unknownFieldSchema.getBuilderFromMessage(message);
/*      */           }
/* 4327 */           if (!unknownFieldSchema.mergeOneFieldFrom(unknownFields, reader)) {
/*      */             break;
/*      */           }
/*      */         } 
/*      */       } 
/*      */       return;
/*      */     } finally {
/* 4334 */       for (int i = this.checkInitializedCount; i < this.repeatedFieldOffsetStart; i++)
/*      */       {
/* 4336 */         unknownFields = filterMapUnknownEnumValues(message, this.intArray[i], unknownFields, unknownFieldSchema);
/*      */       }
/* 4338 */       if (unknownFields != null) {
/* 4339 */         unknownFieldSchema.setBuilderToMessage(message, unknownFields);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   static UnknownFieldSetLite getMutableUnknownFields(Object message) {
/* 4346 */     UnknownFieldSetLite unknownFields = ((GeneratedMessageLite)message).unknownFields;
/* 4347 */     if (unknownFields == UnknownFieldSetLite.getDefaultInstance()) {
/* 4348 */       unknownFields = UnknownFieldSetLite.newInstance();
/* 4349 */       ((GeneratedMessageLite)message).unknownFields = unknownFields;
/*      */     } 
/* 4351 */     return unknownFields;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int decodeMapEntryValue(byte[] data, int position, int limit, WireFormat.FieldType fieldType, Class<?> messageType, ArrayDecoders.Registers registers) throws IOException {
/* 4363 */     switch (fieldType) {
/*      */       case BOOL:
/* 4365 */         position = ArrayDecoders.decodeVarint64(data, position, registers);
/* 4366 */         registers.object1 = Boolean.valueOf((registers.long1 != 0L));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4419 */         return position;case BYTES: position = ArrayDecoders.decodeBytes(data, position, registers); return position;case DOUBLE: registers.object1 = Double.valueOf(ArrayDecoders.decodeDouble(data, position)); position += 8; return position;case FIXED32: case SFIXED32: registers.object1 = Integer.valueOf(ArrayDecoders.decodeFixed32(data, position)); position += 4; return position;case FIXED64: case SFIXED64: registers.object1 = Long.valueOf(ArrayDecoders.decodeFixed64(data, position)); position += 8; return position;case FLOAT: registers.object1 = Float.valueOf(ArrayDecoders.decodeFloat(data, position)); position += 4; return position;case ENUM: case INT32: case UINT32: position = ArrayDecoders.decodeVarint32(data, position, registers); registers.object1 = Integer.valueOf(registers.int1); return position;case INT64: case UINT64: position = ArrayDecoders.decodeVarint64(data, position, registers); registers.object1 = Long.valueOf(registers.long1); return position;case MESSAGE: position = ArrayDecoders.decodeMessageField(Protobuf.getInstance().schemaFor(messageType), data, position, limit, registers); return position;case SINT32: position = ArrayDecoders.decodeVarint32(data, position, registers); registers.object1 = Integer.valueOf(CodedInputStream.decodeZigZag32(registers.int1)); return position;case SINT64: position = ArrayDecoders.decodeVarint64(data, position, registers); registers.object1 = Long.valueOf(CodedInputStream.decodeZigZag64(registers.long1)); return position;case STRING: position = ArrayDecoders.decodeStringRequireUtf8(data, position, registers); return position;
/*      */     } 
/*      */     throw new RuntimeException("unsupported field type.");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private <K, V> int decodeMapEntry(byte[] data, int position, int limit, MapEntryLite.Metadata<K, V> metadata, Map<K, V> target, ArrayDecoders.Registers registers) throws IOException {
/* 4431 */     position = ArrayDecoders.decodeVarint32(data, position, registers);
/* 4432 */     int length = registers.int1;
/* 4433 */     if (length < 0 || length > limit - position) {
/* 4434 */       throw InvalidProtocolBufferException.truncatedMessage();
/*      */     }
/* 4436 */     int end = position + length;
/* 4437 */     K key = metadata.defaultKey;
/* 4438 */     V value = metadata.defaultValue;
/* 4439 */     while (position < end) {
/* 4440 */       int tag = data[position++];
/* 4441 */       if (tag < 0) {
/* 4442 */         position = ArrayDecoders.decodeVarint32(tag, data, position, registers);
/* 4443 */         tag = registers.int1;
/*      */       } 
/* 4445 */       int fieldNumber = tag >>> 3;
/* 4446 */       int wireType = tag & 0x7;
/* 4447 */       switch (fieldNumber) {
/*      */         case 1:
/* 4449 */           if (wireType == metadata.keyType.getWireType()) {
/*      */             
/* 4451 */             position = decodeMapEntryValue(data, position, limit, metadata.keyType, null, registers);
/* 4452 */             key = (K)registers.object1;
/*      */             continue;
/*      */           } 
/*      */           break;
/*      */         case 2:
/* 4457 */           if (wireType == metadata.valueType.getWireType()) {
/*      */             
/* 4459 */             position = decodeMapEntryValue(data, position, limit, metadata.valueType, metadata.defaultValue
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/* 4464 */                 .getClass(), registers);
/*      */             
/* 4466 */             value = (V)registers.object1;
/*      */             continue;
/*      */           } 
/*      */           break;
/*      */       } 
/*      */ 
/*      */       
/* 4473 */       position = ArrayDecoders.skipField(tag, data, position, limit, registers);
/*      */     } 
/* 4475 */     if (position != end) {
/* 4476 */       throw InvalidProtocolBufferException.parseFailure();
/*      */     }
/* 4478 */     target.put(key, value);
/* 4479 */     return end;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int parseRepeatedField(T message, byte[] data, int position, int limit, int tag, int number, int wireType, int bufferPosition, long typeAndOffset, int fieldType, long fieldOffset, ArrayDecoders.Registers registers) throws IOException {
/*      */     UnknownFieldSetLite unknownFields;
/* 4497 */     Internal.ProtobufList<?> list = (Internal.ProtobufList)UNSAFE.getObject(message, fieldOffset);
/* 4498 */     if (!list.isModifiable()) {
/* 4499 */       int size = list.size();
/*      */       
/* 4501 */       list = list.mutableCopyWithCapacity((size == 0) ? 10 : (size * 2));
/*      */       
/* 4503 */       UNSAFE.putObject(message, fieldOffset, list);
/*      */     } 
/* 4505 */     switch (fieldType) {
/*      */       case 18:
/*      */       case 35:
/* 4508 */         if (wireType == 2) {
/* 4509 */           position = ArrayDecoders.decodePackedDoubleList(data, position, list, registers); break;
/* 4510 */         }  if (wireType == 1) {
/* 4511 */           position = ArrayDecoders.decodeDoubleList(tag, data, position, limit, list, registers);
/*      */         }
/*      */         break;
/*      */       case 19:
/*      */       case 36:
/* 4516 */         if (wireType == 2) {
/* 4517 */           position = ArrayDecoders.decodePackedFloatList(data, position, list, registers); break;
/* 4518 */         }  if (wireType == 5) {
/* 4519 */           position = ArrayDecoders.decodeFloatList(tag, data, position, limit, list, registers);
/*      */         }
/*      */         break;
/*      */       case 20:
/*      */       case 21:
/*      */       case 37:
/*      */       case 38:
/* 4526 */         if (wireType == 2) {
/* 4527 */           position = ArrayDecoders.decodePackedVarint64List(data, position, list, registers); break;
/* 4528 */         }  if (wireType == 0) {
/* 4529 */           position = ArrayDecoders.decodeVarint64List(tag, data, position, limit, list, registers);
/*      */         }
/*      */         break;
/*      */       case 22:
/*      */       case 29:
/*      */       case 39:
/*      */       case 43:
/* 4536 */         if (wireType == 2) {
/* 4537 */           position = ArrayDecoders.decodePackedVarint32List(data, position, list, registers); break;
/* 4538 */         }  if (wireType == 0) {
/* 4539 */           position = ArrayDecoders.decodeVarint32List(tag, data, position, limit, list, registers);
/*      */         }
/*      */         break;
/*      */       case 23:
/*      */       case 32:
/*      */       case 40:
/*      */       case 46:
/* 4546 */         if (wireType == 2) {
/* 4547 */           position = ArrayDecoders.decodePackedFixed64List(data, position, list, registers); break;
/* 4548 */         }  if (wireType == 1) {
/* 4549 */           position = ArrayDecoders.decodeFixed64List(tag, data, position, limit, list, registers);
/*      */         }
/*      */         break;
/*      */       case 24:
/*      */       case 31:
/*      */       case 41:
/*      */       case 45:
/* 4556 */         if (wireType == 2) {
/* 4557 */           position = ArrayDecoders.decodePackedFixed32List(data, position, list, registers); break;
/* 4558 */         }  if (wireType == 5) {
/* 4559 */           position = ArrayDecoders.decodeFixed32List(tag, data, position, limit, list, registers);
/*      */         }
/*      */         break;
/*      */       case 25:
/*      */       case 42:
/* 4564 */         if (wireType == 2) {
/* 4565 */           position = ArrayDecoders.decodePackedBoolList(data, position, list, registers); break;
/* 4566 */         }  if (wireType == 0) {
/* 4567 */           position = ArrayDecoders.decodeBoolList(tag, data, position, limit, list, registers);
/*      */         }
/*      */         break;
/*      */       case 26:
/* 4571 */         if (wireType == 2) {
/* 4572 */           if ((typeAndOffset & 0x20000000L) == 0L) {
/* 4573 */             position = ArrayDecoders.decodeStringList(tag, data, position, limit, list, registers); break;
/*      */           } 
/* 4575 */           position = ArrayDecoders.decodeStringListRequireUtf8(tag, data, position, limit, list, registers);
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 27:
/* 4580 */         if (wireType == 2)
/*      */         {
/* 4582 */           position = ArrayDecoders.decodeMessageList(
/* 4583 */               getMessageFieldSchema(bufferPosition), tag, data, position, limit, list, registers);
/*      */         }
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 28:
/* 4593 */         if (wireType == 2) {
/* 4594 */           position = ArrayDecoders.decodeBytesList(tag, data, position, limit, list, registers);
/*      */         }
/*      */         break;
/*      */       case 30:
/*      */       case 44:
/* 4599 */         if (wireType == 2) {
/* 4600 */           position = ArrayDecoders.decodePackedVarint32List(data, position, list, registers);
/* 4601 */         } else if (wireType == 0) {
/* 4602 */           position = ArrayDecoders.decodeVarint32List(tag, data, position, limit, list, registers);
/*      */         } else {
/*      */           break;
/*      */         } 
/* 4606 */         unknownFields = ((GeneratedMessageLite)message).unknownFields;
/* 4607 */         if (unknownFields == UnknownFieldSetLite.getDefaultInstance())
/*      */         {
/*      */ 
/*      */ 
/*      */           
/* 4612 */           unknownFields = null;
/*      */         }
/*      */         
/* 4615 */         unknownFields = (UnknownFieldSetLite)SchemaUtil.filterUnknownEnumList(number, (List)list, 
/*      */ 
/*      */             
/* 4618 */             getEnumFieldVerifier(bufferPosition), unknownFields, this.unknownFieldSchema);
/*      */ 
/*      */         
/* 4621 */         if (unknownFields != null) {
/* 4622 */           ((GeneratedMessageLite)message).unknownFields = unknownFields;
/*      */         }
/*      */         break;
/*      */       case 33:
/*      */       case 47:
/* 4627 */         if (wireType == 2) {
/* 4628 */           position = ArrayDecoders.decodePackedSInt32List(data, position, list, registers); break;
/* 4629 */         }  if (wireType == 0) {
/* 4630 */           position = ArrayDecoders.decodeSInt32List(tag, data, position, limit, list, registers);
/*      */         }
/*      */         break;
/*      */       case 34:
/*      */       case 48:
/* 4635 */         if (wireType == 2) {
/* 4636 */           position = ArrayDecoders.decodePackedSInt64List(data, position, list, registers); break;
/* 4637 */         }  if (wireType == 0) {
/* 4638 */           position = ArrayDecoders.decodeSInt64List(tag, data, position, limit, list, registers);
/*      */         }
/*      */         break;
/*      */       case 49:
/* 4642 */         if (wireType == 3)
/*      */         {
/* 4644 */           position = ArrayDecoders.decodeGroupList(
/* 4645 */               getMessageFieldSchema(bufferPosition), tag, data, position, limit, list, registers);
/*      */         }
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4657 */     return position;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private <K, V> int parseMapField(T message, byte[] data, int position, int limit, int bufferPosition, long fieldOffset, ArrayDecoders.Registers registers) throws IOException {
/* 4669 */     Unsafe unsafe = UNSAFE;
/* 4670 */     Object mapDefaultEntry = getMapFieldDefaultEntry(bufferPosition);
/* 4671 */     Object mapField = unsafe.getObject(message, fieldOffset);
/* 4672 */     if (this.mapFieldSchema.isImmutable(mapField)) {
/* 4673 */       Object oldMapField = mapField;
/* 4674 */       mapField = this.mapFieldSchema.newMapField(mapDefaultEntry);
/* 4675 */       this.mapFieldSchema.mergeFrom(mapField, oldMapField);
/* 4676 */       unsafe.putObject(message, fieldOffset, mapField);
/*      */     } 
/* 4678 */     return decodeMapEntry(data, position, limit, this.mapFieldSchema
/*      */ 
/*      */ 
/*      */         
/* 4682 */         .forMapMetadata(mapDefaultEntry), this.mapFieldSchema
/* 4683 */         .forMutableMapData(mapField), registers);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int parseOneofField(T message, byte[] data, int position, int limit, int tag, int number, int wireType, int typeAndOffset, int fieldType, long fieldOffset, int bufferPosition, ArrayDecoders.Registers registers) throws IOException {
/* 4701 */     Unsafe unsafe = UNSAFE;
/* 4702 */     long oneofCaseOffset = (this.buffer[bufferPosition + 2] & 0xFFFFF);
/* 4703 */     switch (fieldType) {
/*      */       case 51:
/* 4705 */         if (wireType == 1) {
/* 4706 */           unsafe.putObject(message, fieldOffset, Double.valueOf(ArrayDecoders.decodeDouble(data, position)));
/* 4707 */           position += 8;
/* 4708 */           unsafe.putInt(message, oneofCaseOffset, number);
/*      */         } 
/*      */         break;
/*      */       case 52:
/* 4712 */         if (wireType == 5) {
/* 4713 */           unsafe.putObject(message, fieldOffset, Float.valueOf(ArrayDecoders.decodeFloat(data, position)));
/* 4714 */           position += 4;
/* 4715 */           unsafe.putInt(message, oneofCaseOffset, number);
/*      */         } 
/*      */         break;
/*      */       case 53:
/*      */       case 54:
/* 4720 */         if (wireType == 0) {
/* 4721 */           position = ArrayDecoders.decodeVarint64(data, position, registers);
/* 4722 */           unsafe.putObject(message, fieldOffset, Long.valueOf(registers.long1));
/* 4723 */           unsafe.putInt(message, oneofCaseOffset, number);
/*      */         } 
/*      */         break;
/*      */       case 55:
/*      */       case 62:
/* 4728 */         if (wireType == 0) {
/* 4729 */           position = ArrayDecoders.decodeVarint32(data, position, registers);
/* 4730 */           unsafe.putObject(message, fieldOffset, Integer.valueOf(registers.int1));
/* 4731 */           unsafe.putInt(message, oneofCaseOffset, number);
/*      */         } 
/*      */         break;
/*      */       case 56:
/*      */       case 65:
/* 4736 */         if (wireType == 1) {
/* 4737 */           unsafe.putObject(message, fieldOffset, Long.valueOf(ArrayDecoders.decodeFixed64(data, position)));
/* 4738 */           position += 8;
/* 4739 */           unsafe.putInt(message, oneofCaseOffset, number);
/*      */         } 
/*      */         break;
/*      */       case 57:
/*      */       case 64:
/* 4744 */         if (wireType == 5) {
/* 4745 */           unsafe.putObject(message, fieldOffset, Integer.valueOf(ArrayDecoders.decodeFixed32(data, position)));
/* 4746 */           position += 4;
/* 4747 */           unsafe.putInt(message, oneofCaseOffset, number);
/*      */         } 
/*      */         break;
/*      */       case 58:
/* 4751 */         if (wireType == 0) {
/* 4752 */           position = ArrayDecoders.decodeVarint64(data, position, registers);
/* 4753 */           unsafe.putObject(message, fieldOffset, Boolean.valueOf((registers.long1 != 0L)));
/* 4754 */           unsafe.putInt(message, oneofCaseOffset, number);
/*      */         } 
/*      */         break;
/*      */       case 59:
/* 4758 */         if (wireType == 2) {
/* 4759 */           position = ArrayDecoders.decodeVarint32(data, position, registers);
/* 4760 */           int length = registers.int1;
/* 4761 */           if (length == 0) {
/* 4762 */             unsafe.putObject(message, fieldOffset, "");
/*      */           } else {
/* 4764 */             if ((typeAndOffset & 0x20000000) != 0 && 
/* 4765 */               !Utf8.isValidUtf8(data, position, position + length)) {
/* 4766 */               throw InvalidProtocolBufferException.invalidUtf8();
/*      */             }
/* 4768 */             String value = new String(data, position, length, Internal.UTF_8);
/* 4769 */             unsafe.putObject(message, fieldOffset, value);
/* 4770 */             position += length;
/*      */           } 
/* 4772 */           unsafe.putInt(message, oneofCaseOffset, number);
/*      */         } 
/*      */         break;
/*      */       case 60:
/* 4776 */         if (wireType == 2) {
/*      */           
/* 4778 */           position = ArrayDecoders.decodeMessageField(
/* 4779 */               getMessageFieldSchema(bufferPosition), data, position, limit, registers);
/*      */ 
/*      */           
/* 4782 */           Object oldValue = (unsafe.getInt(message, oneofCaseOffset) == number) ? unsafe.getObject(message, fieldOffset) : null;
/*      */           
/* 4784 */           if (oldValue == null) {
/* 4785 */             unsafe.putObject(message, fieldOffset, registers.object1);
/*      */           } else {
/* 4787 */             unsafe.putObject(message, fieldOffset, 
/* 4788 */                 Internal.mergeMessage(oldValue, registers.object1));
/*      */           } 
/* 4790 */           unsafe.putInt(message, oneofCaseOffset, number);
/*      */         } 
/*      */         break;
/*      */       case 61:
/* 4794 */         if (wireType == 2) {
/* 4795 */           position = ArrayDecoders.decodeBytes(data, position, registers);
/* 4796 */           unsafe.putObject(message, fieldOffset, registers.object1);
/* 4797 */           unsafe.putInt(message, oneofCaseOffset, number);
/*      */         } 
/*      */         break;
/*      */       case 63:
/* 4801 */         if (wireType == 0) {
/* 4802 */           position = ArrayDecoders.decodeVarint32(data, position, registers);
/* 4803 */           int enumValue = registers.int1;
/* 4804 */           Internal.EnumVerifier enumVerifier = getEnumFieldVerifier(bufferPosition);
/* 4805 */           if (enumVerifier == null || enumVerifier.isInRange(enumValue)) {
/* 4806 */             unsafe.putObject(message, fieldOffset, Integer.valueOf(enumValue));
/* 4807 */             unsafe.putInt(message, oneofCaseOffset, number);
/*      */             break;
/*      */           } 
/* 4810 */           getMutableUnknownFields(message).storeField(tag, Long.valueOf(enumValue));
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 66:
/* 4815 */         if (wireType == 0) {
/* 4816 */           position = ArrayDecoders.decodeVarint32(data, position, registers);
/* 4817 */           unsafe.putObject(message, fieldOffset, Integer.valueOf(CodedInputStream.decodeZigZag32(registers.int1)));
/* 4818 */           unsafe.putInt(message, oneofCaseOffset, number);
/*      */         } 
/*      */         break;
/*      */       case 67:
/* 4822 */         if (wireType == 0) {
/* 4823 */           position = ArrayDecoders.decodeVarint64(data, position, registers);
/* 4824 */           unsafe.putObject(message, fieldOffset, Long.valueOf(CodedInputStream.decodeZigZag64(registers.long1)));
/* 4825 */           unsafe.putInt(message, oneofCaseOffset, number);
/*      */         } 
/*      */         break;
/*      */       case 68:
/* 4829 */         if (wireType == 3) {
/* 4830 */           int endTag = tag & 0xFFFFFFF8 | 0x4;
/*      */           
/* 4832 */           position = ArrayDecoders.decodeGroupField(
/* 4833 */               getMessageFieldSchema(bufferPosition), data, position, limit, endTag, registers);
/*      */ 
/*      */           
/* 4836 */           Object oldValue = (unsafe.getInt(message, oneofCaseOffset) == number) ? unsafe.getObject(message, fieldOffset) : null;
/*      */           
/* 4838 */           if (oldValue == null) {
/* 4839 */             unsafe.putObject(message, fieldOffset, registers.object1);
/*      */           } else {
/* 4841 */             unsafe.putObject(message, fieldOffset, 
/* 4842 */                 Internal.mergeMessage(oldValue, registers.object1));
/*      */           } 
/* 4844 */           unsafe.putInt(message, oneofCaseOffset, number);
/*      */         } 
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/* 4850 */     return position;
/*      */   }
/*      */   
/*      */   private Schema getMessageFieldSchema(int pos) {
/* 4854 */     int index = pos / 3 * 2;
/* 4855 */     Schema<?> schema = (Schema)this.objects[index];
/* 4856 */     if (schema != null) {
/* 4857 */       return schema;
/*      */     }
/* 4859 */     schema = Protobuf.getInstance().schemaFor((Class)this.objects[index + 1]);
/* 4860 */     this.objects[index] = schema;
/* 4861 */     return schema;
/*      */   }
/*      */   
/*      */   private Object getMapFieldDefaultEntry(int pos) {
/* 4865 */     return this.objects[pos / 3 * 2];
/*      */   }
/*      */   
/*      */   private Internal.EnumVerifier getEnumFieldVerifier(int pos) {
/* 4869 */     return (Internal.EnumVerifier)this.objects[pos / 3 * 2 + 1];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int parseProto2Message(T message, byte[] data, int position, int limit, int endGroup, ArrayDecoders.Registers registers) throws IOException {
/* 4881 */     Unsafe unsafe = UNSAFE;
/* 4882 */     int currentPresenceFieldOffset = 1048575;
/* 4883 */     int currentPresenceField = 0;
/* 4884 */     int tag = 0;
/* 4885 */     int oldNumber = -1;
/* 4886 */     int pos = 0;
/* 4887 */     while (position < limit) {
/* 4888 */       tag = data[position++];
/* 4889 */       if (tag < 0) {
/* 4890 */         position = ArrayDecoders.decodeVarint32(tag, data, position, registers);
/* 4891 */         tag = registers.int1;
/*      */       } 
/* 4893 */       int number = tag >>> 3;
/* 4894 */       int wireType = tag & 0x7;
/* 4895 */       if (number > oldNumber) {
/* 4896 */         pos = positionForFieldNumber(number, pos / 3);
/*      */       } else {
/* 4898 */         pos = positionForFieldNumber(number);
/*      */       } 
/* 4900 */       oldNumber = number;
/* 4901 */       if (pos == -1) {
/*      */         
/* 4903 */         pos = 0;
/*      */       } else {
/* 4905 */         int typeAndOffset = this.buffer[pos + 1];
/* 4906 */         int fieldType = type(typeAndOffset);
/* 4907 */         long fieldOffset = offset(typeAndOffset);
/* 4908 */         if (fieldType <= 17) {
/*      */           
/* 4910 */           int presenceMaskAndOffset = this.buffer[pos + 2];
/* 4911 */           int presenceMask = 1 << presenceMaskAndOffset >>> 20;
/* 4912 */           int presenceFieldOffset = presenceMaskAndOffset & 0xFFFFF;
/*      */ 
/*      */           
/* 4915 */           if (presenceFieldOffset != currentPresenceFieldOffset) {
/* 4916 */             if (currentPresenceFieldOffset != 1048575) {
/* 4917 */               unsafe.putInt(message, currentPresenceFieldOffset, currentPresenceField);
/*      */             }
/* 4919 */             currentPresenceFieldOffset = presenceFieldOffset;
/* 4920 */             currentPresenceField = unsafe.getInt(message, presenceFieldOffset);
/*      */           } 
/* 4922 */           switch (fieldType) {
/*      */             case 0:
/* 4924 */               if (wireType == 1) {
/* 4925 */                 UnsafeUtil.putDouble(message, fieldOffset, ArrayDecoders.decodeDouble(data, position));
/* 4926 */                 position += 8;
/* 4927 */                 currentPresenceField |= presenceMask;
/*      */                 continue;
/*      */               } 
/*      */               break;
/*      */             case 1:
/* 4932 */               if (wireType == 5) {
/* 4933 */                 UnsafeUtil.putFloat(message, fieldOffset, ArrayDecoders.decodeFloat(data, position));
/* 4934 */                 position += 4;
/* 4935 */                 currentPresenceField |= presenceMask;
/*      */                 continue;
/*      */               } 
/*      */               break;
/*      */             case 2:
/*      */             case 3:
/* 4941 */               if (wireType == 0) {
/* 4942 */                 position = ArrayDecoders.decodeVarint64(data, position, registers);
/* 4943 */                 unsafe.putLong(message, fieldOffset, registers.long1);
/* 4944 */                 currentPresenceField |= presenceMask;
/*      */                 continue;
/*      */               } 
/*      */               break;
/*      */             case 4:
/*      */             case 11:
/* 4950 */               if (wireType == 0) {
/* 4951 */                 position = ArrayDecoders.decodeVarint32(data, position, registers);
/* 4952 */                 unsafe.putInt(message, fieldOffset, registers.int1);
/* 4953 */                 currentPresenceField |= presenceMask;
/*      */                 continue;
/*      */               } 
/*      */               break;
/*      */             case 5:
/*      */             case 14:
/* 4959 */               if (wireType == 1) {
/* 4960 */                 unsafe.putLong(message, fieldOffset, ArrayDecoders.decodeFixed64(data, position));
/* 4961 */                 position += 8;
/* 4962 */                 currentPresenceField |= presenceMask;
/*      */                 continue;
/*      */               } 
/*      */               break;
/*      */             case 6:
/*      */             case 13:
/* 4968 */               if (wireType == 5) {
/* 4969 */                 unsafe.putInt(message, fieldOffset, ArrayDecoders.decodeFixed32(data, position));
/* 4970 */                 position += 4;
/* 4971 */                 currentPresenceField |= presenceMask;
/*      */                 continue;
/*      */               } 
/*      */               break;
/*      */             case 7:
/* 4976 */               if (wireType == 0) {
/* 4977 */                 position = ArrayDecoders.decodeVarint64(data, position, registers);
/* 4978 */                 UnsafeUtil.putBoolean(message, fieldOffset, (registers.long1 != 0L));
/* 4979 */                 currentPresenceField |= presenceMask;
/*      */                 continue;
/*      */               } 
/*      */               break;
/*      */             case 8:
/* 4984 */               if (wireType == 2) {
/* 4985 */                 if ((typeAndOffset & 0x20000000) == 0) {
/* 4986 */                   position = ArrayDecoders.decodeString(data, position, registers);
/*      */                 } else {
/* 4988 */                   position = ArrayDecoders.decodeStringRequireUtf8(data, position, registers);
/*      */                 } 
/* 4990 */                 unsafe.putObject(message, fieldOffset, registers.object1);
/* 4991 */                 currentPresenceField |= presenceMask;
/*      */                 continue;
/*      */               } 
/*      */               break;
/*      */             case 9:
/* 4996 */               if (wireType == 2) {
/*      */                 
/* 4998 */                 position = ArrayDecoders.decodeMessageField(
/* 4999 */                     getMessageFieldSchema(pos), data, position, limit, registers);
/* 5000 */                 if ((currentPresenceField & presenceMask) == 0) {
/* 5001 */                   unsafe.putObject(message, fieldOffset, registers.object1);
/*      */                 } else {
/* 5003 */                   unsafe.putObject(message, fieldOffset, 
/*      */ 
/*      */                       
/* 5006 */                       Internal.mergeMessage(unsafe
/* 5007 */                         .getObject(message, fieldOffset), registers.object1));
/*      */                 } 
/* 5009 */                 currentPresenceField |= presenceMask;
/*      */                 continue;
/*      */               } 
/*      */               break;
/*      */             case 10:
/* 5014 */               if (wireType == 2) {
/* 5015 */                 position = ArrayDecoders.decodeBytes(data, position, registers);
/* 5016 */                 unsafe.putObject(message, fieldOffset, registers.object1);
/* 5017 */                 currentPresenceField |= presenceMask;
/*      */                 continue;
/*      */               } 
/*      */               break;
/*      */             case 12:
/* 5022 */               if (wireType == 0) {
/* 5023 */                 position = ArrayDecoders.decodeVarint32(data, position, registers);
/* 5024 */                 int enumValue = registers.int1;
/* 5025 */                 Internal.EnumVerifier enumVerifier = getEnumFieldVerifier(pos);
/* 5026 */                 if (enumVerifier == null || enumVerifier.isInRange(enumValue)) {
/* 5027 */                   unsafe.putInt(message, fieldOffset, enumValue);
/* 5028 */                   currentPresenceField |= presenceMask;
/*      */                   continue;
/*      */                 } 
/* 5031 */                 getMutableUnknownFields(message).storeField(tag, Long.valueOf(enumValue));
/*      */                 continue;
/*      */               } 
/*      */               break;
/*      */             
/*      */             case 15:
/* 5037 */               if (wireType == 0) {
/* 5038 */                 position = ArrayDecoders.decodeVarint32(data, position, registers);
/* 5039 */                 unsafe.putInt(message, fieldOffset, 
/* 5040 */                     CodedInputStream.decodeZigZag32(registers.int1));
/* 5041 */                 currentPresenceField |= presenceMask;
/*      */                 continue;
/*      */               } 
/*      */               break;
/*      */             case 16:
/* 5046 */               if (wireType == 0) {
/* 5047 */                 position = ArrayDecoders.decodeVarint64(data, position, registers);
/* 5048 */                 unsafe.putLong(message, fieldOffset, 
/* 5049 */                     CodedInputStream.decodeZigZag64(registers.long1));
/*      */                 
/* 5051 */                 currentPresenceField |= presenceMask;
/*      */                 continue;
/*      */               } 
/*      */               break;
/*      */             case 17:
/* 5056 */               if (wireType == 3) {
/* 5057 */                 int endTag = number << 3 | 0x4;
/*      */                 
/* 5059 */                 position = ArrayDecoders.decodeGroupField(
/* 5060 */                     getMessageFieldSchema(pos), data, position, limit, endTag, registers);
/* 5061 */                 if ((currentPresenceField & presenceMask) == 0) {
/* 5062 */                   unsafe.putObject(message, fieldOffset, registers.object1);
/*      */                 } else {
/* 5064 */                   unsafe.putObject(message, fieldOffset, 
/*      */ 
/*      */                       
/* 5067 */                       Internal.mergeMessage(unsafe
/* 5068 */                         .getObject(message, fieldOffset), registers.object1));
/*      */                 } 
/*      */                 
/* 5071 */                 currentPresenceField |= presenceMask;
/*      */                 continue;
/*      */               } 
/*      */               break;
/*      */           } 
/*      */ 
/*      */         
/* 5078 */         } else if (fieldType == 27) {
/*      */           
/* 5080 */           if (wireType == 2) {
/* 5081 */             Internal.ProtobufList<?> list = (Internal.ProtobufList)unsafe.getObject(message, fieldOffset);
/* 5082 */             if (!list.isModifiable()) {
/* 5083 */               int size = list.size();
/*      */               
/* 5085 */               list = list.mutableCopyWithCapacity((size == 0) ? 10 : (size * 2));
/*      */               
/* 5087 */               unsafe.putObject(message, fieldOffset, list);
/*      */             } 
/*      */             
/* 5090 */             position = ArrayDecoders.decodeMessageList(
/* 5091 */                 getMessageFieldSchema(pos), tag, data, position, limit, list, registers);
/*      */             continue;
/*      */           } 
/* 5094 */         } else if (fieldType <= 49) {
/*      */           
/* 5096 */           int oldPosition = position;
/*      */           
/* 5098 */           position = parseRepeatedField(message, data, position, limit, tag, number, wireType, pos, typeAndOffset, fieldType, fieldOffset, registers);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 5111 */           if (position != oldPosition) {
/*      */             continue;
/*      */           }
/* 5114 */         } else if (fieldType == 50) {
/* 5115 */           if (wireType == 2) {
/* 5116 */             int oldPosition = position;
/* 5117 */             position = parseMapField(message, data, position, limit, pos, fieldOffset, registers);
/* 5118 */             if (position != oldPosition) {
/*      */               continue;
/*      */             }
/*      */           } 
/*      */         } else {
/* 5123 */           int oldPosition = position;
/*      */           
/* 5125 */           position = parseOneofField(message, data, position, limit, tag, number, wireType, typeAndOffset, fieldType, fieldOffset, pos, registers);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 5138 */           if (position != oldPosition) {
/*      */             continue;
/*      */           }
/*      */         } 
/*      */       } 
/* 5143 */       if (tag == endGroup && endGroup != 0) {
/*      */         break;
/*      */       }
/*      */       
/* 5147 */       if (this.hasExtensions && registers.extensionRegistry != 
/* 5148 */         ExtensionRegistryLite.getEmptyRegistry()) {
/* 5149 */         position = ArrayDecoders.decodeExtensionOrUnknownField(tag, data, position, limit, message, this.defaultInstance, (UnknownFieldSchema)this.unknownFieldSchema, registers);
/*      */         
/*      */         continue;
/*      */       } 
/*      */       
/* 5154 */       position = ArrayDecoders.decodeUnknownField(tag, data, position, limit, 
/* 5155 */           getMutableUnknownFields(message), registers);
/*      */     } 
/*      */     
/* 5158 */     if (currentPresenceFieldOffset != 1048575) {
/* 5159 */       unsafe.putInt(message, currentPresenceFieldOffset, currentPresenceField);
/*      */     }
/* 5161 */     UnknownFieldSetLite unknownFields = null;
/* 5162 */     for (int i = this.checkInitializedCount; i < this.repeatedFieldOffsetStart; i++)
/*      */     {
/* 5164 */       unknownFields = (UnknownFieldSetLite)filterMapUnknownEnumValues(message, this.intArray[i], unknownFields, this.unknownFieldSchema);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5170 */     if (unknownFields != null) {
/* 5171 */       this.unknownFieldSchema
/* 5172 */         .setBuilderToMessage(message, unknownFields);
/*      */     }
/* 5174 */     if (endGroup == 0) {
/* 5175 */       if (position != limit) {
/* 5176 */         throw InvalidProtocolBufferException.parseFailure();
/*      */       }
/*      */     }
/* 5179 */     else if (position > limit || tag != endGroup) {
/* 5180 */       throw InvalidProtocolBufferException.parseFailure();
/*      */     } 
/*      */     
/* 5183 */     return position;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private int parseProto3Message(T message, byte[] data, int position, int limit, ArrayDecoders.Registers registers) throws IOException {
/* 5189 */     Unsafe unsafe = UNSAFE;
/* 5190 */     int currentPresenceFieldOffset = 1048575;
/* 5191 */     int currentPresenceField = 0;
/* 5192 */     int tag = 0;
/* 5193 */     int oldNumber = -1;
/* 5194 */     int pos = 0;
/* 5195 */     while (position < limit) {
/* 5196 */       tag = data[position++];
/* 5197 */       if (tag < 0) {
/* 5198 */         position = ArrayDecoders.decodeVarint32(tag, data, position, registers);
/* 5199 */         tag = registers.int1;
/*      */       } 
/* 5201 */       int number = tag >>> 3;
/* 5202 */       int wireType = tag & 0x7;
/* 5203 */       if (number > oldNumber) {
/* 5204 */         pos = positionForFieldNumber(number, pos / 3);
/*      */       } else {
/* 5206 */         pos = positionForFieldNumber(number);
/*      */       } 
/* 5208 */       oldNumber = number;
/* 5209 */       if (pos == -1) {
/*      */         
/* 5211 */         pos = 0;
/*      */       } else {
/* 5213 */         int typeAndOffset = this.buffer[pos + 1];
/* 5214 */         int fieldType = type(typeAndOffset);
/* 5215 */         long fieldOffset = offset(typeAndOffset);
/* 5216 */         if (fieldType <= 17) {
/*      */           
/* 5218 */           int presenceMaskAndOffset = this.buffer[pos + 2];
/* 5219 */           int presenceMask = 1 << presenceMaskAndOffset >>> 20;
/* 5220 */           int presenceFieldOffset = presenceMaskAndOffset & 0xFFFFF;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 5226 */           if (presenceFieldOffset != currentPresenceFieldOffset) {
/* 5227 */             if (currentPresenceFieldOffset != 1048575) {
/* 5228 */               unsafe.putInt(message, currentPresenceFieldOffset, currentPresenceField);
/*      */             }
/* 5230 */             if (presenceFieldOffset != 1048575) {
/* 5231 */               currentPresenceField = unsafe.getInt(message, presenceFieldOffset);
/*      */             }
/* 5233 */             currentPresenceFieldOffset = presenceFieldOffset;
/*      */           } 
/* 5235 */           switch (fieldType) {
/*      */             case 0:
/* 5237 */               if (wireType == 1) {
/* 5238 */                 UnsafeUtil.putDouble(message, fieldOffset, ArrayDecoders.decodeDouble(data, position));
/* 5239 */                 position += 8;
/* 5240 */                 currentPresenceField |= presenceMask;
/*      */                 continue;
/*      */               } 
/*      */               break;
/*      */             case 1:
/* 5245 */               if (wireType == 5) {
/* 5246 */                 UnsafeUtil.putFloat(message, fieldOffset, ArrayDecoders.decodeFloat(data, position));
/* 5247 */                 position += 4;
/* 5248 */                 currentPresenceField |= presenceMask;
/*      */                 continue;
/*      */               } 
/*      */               break;
/*      */             case 2:
/*      */             case 3:
/* 5254 */               if (wireType == 0) {
/* 5255 */                 position = ArrayDecoders.decodeVarint64(data, position, registers);
/* 5256 */                 unsafe.putLong(message, fieldOffset, registers.long1);
/* 5257 */                 currentPresenceField |= presenceMask;
/*      */                 continue;
/*      */               } 
/*      */               break;
/*      */             case 4:
/*      */             case 11:
/* 5263 */               if (wireType == 0) {
/* 5264 */                 position = ArrayDecoders.decodeVarint32(data, position, registers);
/* 5265 */                 unsafe.putInt(message, fieldOffset, registers.int1);
/* 5266 */                 currentPresenceField |= presenceMask;
/*      */                 continue;
/*      */               } 
/*      */               break;
/*      */             case 5:
/*      */             case 14:
/* 5272 */               if (wireType == 1) {
/* 5273 */                 unsafe.putLong(message, fieldOffset, ArrayDecoders.decodeFixed64(data, position));
/* 5274 */                 position += 8;
/* 5275 */                 currentPresenceField |= presenceMask;
/*      */                 continue;
/*      */               } 
/*      */               break;
/*      */             case 6:
/*      */             case 13:
/* 5281 */               if (wireType == 5) {
/* 5282 */                 unsafe.putInt(message, fieldOffset, ArrayDecoders.decodeFixed32(data, position));
/* 5283 */                 position += 4;
/* 5284 */                 currentPresenceField |= presenceMask;
/*      */                 continue;
/*      */               } 
/*      */               break;
/*      */             case 7:
/* 5289 */               if (wireType == 0) {
/* 5290 */                 position = ArrayDecoders.decodeVarint64(data, position, registers);
/* 5291 */                 UnsafeUtil.putBoolean(message, fieldOffset, (registers.long1 != 0L));
/* 5292 */                 currentPresenceField |= presenceMask;
/*      */                 continue;
/*      */               } 
/*      */               break;
/*      */             case 8:
/* 5297 */               if (wireType == 2) {
/* 5298 */                 if ((typeAndOffset & 0x20000000) == 0) {
/* 5299 */                   position = ArrayDecoders.decodeString(data, position, registers);
/*      */                 } else {
/* 5301 */                   position = ArrayDecoders.decodeStringRequireUtf8(data, position, registers);
/*      */                 } 
/* 5303 */                 unsafe.putObject(message, fieldOffset, registers.object1);
/* 5304 */                 currentPresenceField |= presenceMask;
/*      */                 continue;
/*      */               } 
/*      */               break;
/*      */             case 9:
/* 5309 */               if (wireType == 2) {
/*      */                 
/* 5311 */                 position = ArrayDecoders.decodeMessageField(
/* 5312 */                     getMessageFieldSchema(pos), data, position, limit, registers);
/* 5313 */                 Object oldValue = unsafe.getObject(message, fieldOffset);
/* 5314 */                 if (oldValue == null) {
/* 5315 */                   unsafe.putObject(message, fieldOffset, registers.object1);
/*      */                 } else {
/* 5317 */                   unsafe.putObject(message, fieldOffset, 
/* 5318 */                       Internal.mergeMessage(oldValue, registers.object1));
/*      */                 } 
/* 5320 */                 currentPresenceField |= presenceMask;
/*      */                 continue;
/*      */               } 
/*      */               break;
/*      */             case 10:
/* 5325 */               if (wireType == 2) {
/* 5326 */                 position = ArrayDecoders.decodeBytes(data, position, registers);
/* 5327 */                 unsafe.putObject(message, fieldOffset, registers.object1);
/* 5328 */                 currentPresenceField |= presenceMask;
/*      */                 continue;
/*      */               } 
/*      */               break;
/*      */             case 12:
/* 5333 */               if (wireType == 0) {
/* 5334 */                 position = ArrayDecoders.decodeVarint32(data, position, registers);
/* 5335 */                 unsafe.putInt(message, fieldOffset, registers.int1);
/* 5336 */                 currentPresenceField |= presenceMask;
/*      */                 continue;
/*      */               } 
/*      */               break;
/*      */             case 15:
/* 5341 */               if (wireType == 0) {
/* 5342 */                 position = ArrayDecoders.decodeVarint32(data, position, registers);
/* 5343 */                 unsafe.putInt(message, fieldOffset, 
/* 5344 */                     CodedInputStream.decodeZigZag32(registers.int1));
/* 5345 */                 currentPresenceField |= presenceMask;
/*      */                 continue;
/*      */               } 
/*      */               break;
/*      */             case 16:
/* 5350 */               if (wireType == 0) {
/* 5351 */                 position = ArrayDecoders.decodeVarint64(data, position, registers);
/* 5352 */                 unsafe.putLong(message, fieldOffset, 
/* 5353 */                     CodedInputStream.decodeZigZag64(registers.long1));
/* 5354 */                 currentPresenceField |= presenceMask;
/*      */                 continue;
/*      */               } 
/*      */               break;
/*      */           } 
/*      */ 
/*      */         
/* 5361 */         } else if (fieldType == 27) {
/*      */           
/* 5363 */           if (wireType == 2) {
/* 5364 */             Internal.ProtobufList<?> list = (Internal.ProtobufList)unsafe.getObject(message, fieldOffset);
/* 5365 */             if (!list.isModifiable()) {
/* 5366 */               int size = list.size();
/*      */               
/* 5368 */               list = list.mutableCopyWithCapacity((size == 0) ? 10 : (size * 2));
/*      */               
/* 5370 */               unsafe.putObject(message, fieldOffset, list);
/*      */             } 
/*      */             
/* 5373 */             position = ArrayDecoders.decodeMessageList(
/* 5374 */                 getMessageFieldSchema(pos), tag, data, position, limit, list, registers);
/*      */             continue;
/*      */           } 
/* 5377 */         } else if (fieldType <= 49) {
/*      */           
/* 5379 */           int oldPosition = position;
/*      */           
/* 5381 */           position = parseRepeatedField(message, data, position, limit, tag, number, wireType, pos, typeAndOffset, fieldType, fieldOffset, registers);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 5394 */           if (position != oldPosition) {
/*      */             continue;
/*      */           }
/* 5397 */         } else if (fieldType == 50) {
/* 5398 */           if (wireType == 2) {
/* 5399 */             int oldPosition = position;
/* 5400 */             position = parseMapField(message, data, position, limit, pos, fieldOffset, registers);
/* 5401 */             if (position != oldPosition) {
/*      */               continue;
/*      */             }
/*      */           } 
/*      */         } else {
/* 5406 */           int oldPosition = position;
/*      */           
/* 5408 */           position = parseOneofField(message, data, position, limit, tag, number, wireType, typeAndOffset, fieldType, fieldOffset, pos, registers);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 5421 */           if (position != oldPosition) {
/*      */             continue;
/*      */           }
/*      */         } 
/*      */       } 
/* 5426 */       position = ArrayDecoders.decodeUnknownField(tag, data, position, limit, 
/* 5427 */           getMutableUnknownFields(message), registers);
/*      */     } 
/* 5429 */     if (currentPresenceFieldOffset != 1048575) {
/* 5430 */       unsafe.putInt(message, currentPresenceFieldOffset, currentPresenceField);
/*      */     }
/* 5432 */     if (position != limit) {
/* 5433 */       throw InvalidProtocolBufferException.parseFailure();
/*      */     }
/* 5435 */     return position;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void mergeFrom(T message, byte[] data, int position, int limit, ArrayDecoders.Registers registers) throws IOException {
/* 5441 */     if (this.proto3) {
/* 5442 */       parseProto3Message(message, data, position, limit, registers);
/*      */     } else {
/* 5444 */       parseProto2Message(message, data, position, limit, 0, registers);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void makeImmutable(T message) {
/* 5451 */     for (int i = this.checkInitializedCount; i < this.repeatedFieldOffsetStart; i++) {
/* 5452 */       long offset = offset(typeAndOffsetAt(this.intArray[i]));
/* 5453 */       Object mapField = UnsafeUtil.getObject(message, offset);
/* 5454 */       if (mapField != null)
/*      */       {
/*      */         
/* 5457 */         UnsafeUtil.putObject(message, offset, this.mapFieldSchema.toImmutable(mapField)); } 
/*      */     } 
/* 5459 */     int length = this.intArray.length;
/* 5460 */     for (int j = this.repeatedFieldOffsetStart; j < length; j++) {
/* 5461 */       this.listFieldSchema.makeImmutableListAt(message, this.intArray[j]);
/*      */     }
/* 5463 */     this.unknownFieldSchema.makeImmutable(message);
/* 5464 */     if (this.hasExtensions) {
/* 5465 */       this.extensionSchema.makeImmutable(message);
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
/*      */   private final <K, V> void mergeMap(Object message, int pos, Object mapDefaultEntry, ExtensionRegistryLite extensionRegistry, Reader reader) throws IOException {
/* 5477 */     long offset = offset(typeAndOffsetAt(pos));
/* 5478 */     Object mapField = UnsafeUtil.getObject(message, offset);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5483 */     if (mapField == null) {
/* 5484 */       mapField = this.mapFieldSchema.newMapField(mapDefaultEntry);
/* 5485 */       UnsafeUtil.putObject(message, offset, mapField);
/* 5486 */     } else if (this.mapFieldSchema.isImmutable(mapField)) {
/* 5487 */       Object oldMapField = mapField;
/* 5488 */       mapField = this.mapFieldSchema.newMapField(mapDefaultEntry);
/* 5489 */       this.mapFieldSchema.mergeFrom(mapField, oldMapField);
/* 5490 */       UnsafeUtil.putObject(message, offset, mapField);
/*      */     } 
/* 5492 */     reader.readMap(this.mapFieldSchema
/* 5493 */         .forMutableMapData(mapField), this.mapFieldSchema
/* 5494 */         .forMapMetadata(mapDefaultEntry), extensionRegistry);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private final <UT, UB> UB filterMapUnknownEnumValues(Object message, int pos, UB unknownFields, UnknownFieldSchema<UT, UB> unknownFieldSchema) {
/* 5500 */     int fieldNumber = numberAt(pos);
/* 5501 */     long offset = offset(typeAndOffsetAt(pos));
/* 5502 */     Object mapField = UnsafeUtil.getObject(message, offset);
/* 5503 */     if (mapField == null) {
/* 5504 */       return unknownFields;
/*      */     }
/* 5506 */     Internal.EnumVerifier enumVerifier = getEnumFieldVerifier(pos);
/* 5507 */     if (enumVerifier == null) {
/* 5508 */       return unknownFields;
/*      */     }
/* 5510 */     Map<?, ?> mapData = this.mapFieldSchema.forMutableMapData(mapField);
/*      */ 
/*      */     
/* 5513 */     unknownFields = filterUnknownEnumMap(pos, fieldNumber, mapData, enumVerifier, unknownFields, unknownFieldSchema);
/*      */     
/* 5515 */     return unknownFields;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final <K, V, UT, UB> UB filterUnknownEnumMap(int pos, int number, Map<K, V> mapData, Internal.EnumVerifier enumVerifier, UB unknownFields, UnknownFieldSchema<UT, UB> unknownFieldSchema) {
/* 5527 */     MapEntryLite.Metadata<K, V> metadata = (MapEntryLite.Metadata)this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(pos));
/* 5528 */     for (Iterator<Map.Entry<K, V>> it = mapData.entrySet().iterator(); it.hasNext(); ) {
/* 5529 */       Map.Entry<K, V> entry = it.next();
/* 5530 */       if (!enumVerifier.isInRange(((Integer)entry.getValue()).intValue())) {
/* 5531 */         if (unknownFields == null) {
/* 5532 */           unknownFields = unknownFieldSchema.newBuilder();
/*      */         }
/*      */         
/* 5535 */         int entrySize = MapEntryLite.computeSerializedSize(metadata, entry.getKey(), entry.getValue());
/* 5536 */         ByteString.CodedBuilder codedBuilder = ByteString.newCodedBuilder(entrySize);
/* 5537 */         CodedOutputStream codedOutput = codedBuilder.getCodedOutput();
/*      */         try {
/* 5539 */           MapEntryLite.writeTo(codedOutput, metadata, entry.getKey(), entry.getValue());
/* 5540 */         } catch (IOException e) {
/*      */           
/* 5542 */           throw new RuntimeException(e);
/*      */         } 
/* 5544 */         unknownFieldSchema.addLengthDelimited(unknownFields, number, codedBuilder.build());
/* 5545 */         it.remove();
/*      */       } 
/*      */     } 
/* 5548 */     return unknownFields;
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean isInitialized(T message) {
/* 5553 */     int currentPresenceFieldOffset = 1048575;
/* 5554 */     int currentPresenceField = 0;
/* 5555 */     for (int i = 0; i < this.checkInitializedCount; i++) {
/* 5556 */       int pos = this.intArray[i];
/* 5557 */       int number = numberAt(pos);
/* 5558 */       int typeAndOffset = typeAndOffsetAt(pos);
/*      */       
/* 5560 */       int presenceMaskAndOffset = this.buffer[pos + 2];
/* 5561 */       int presenceFieldOffset = presenceMaskAndOffset & 0xFFFFF;
/* 5562 */       int presenceMask = 1 << presenceMaskAndOffset >>> 20;
/* 5563 */       if (presenceFieldOffset != currentPresenceFieldOffset) {
/* 5564 */         currentPresenceFieldOffset = presenceFieldOffset;
/* 5565 */         if (currentPresenceFieldOffset != 1048575) {
/* 5566 */           currentPresenceField = UNSAFE.getInt(message, presenceFieldOffset);
/*      */         }
/*      */       } 
/*      */       
/* 5570 */       if (isRequired(typeAndOffset) && 
/* 5571 */         !isFieldPresent(message, pos, currentPresenceFieldOffset, currentPresenceField, presenceMask))
/*      */       {
/* 5573 */         return false;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5580 */       switch (type(typeAndOffset)) {
/*      */         case 9:
/*      */         case 17:
/* 5583 */           if (isFieldPresent(message, pos, currentPresenceFieldOffset, currentPresenceField, presenceMask) && 
/*      */             
/* 5585 */             !isInitialized(message, typeAndOffset, getMessageFieldSchema(pos))) {
/* 5586 */             return false;
/*      */           }
/*      */           break;
/*      */         case 27:
/*      */         case 49:
/* 5591 */           if (!isListInitialized(message, typeAndOffset, pos)) {
/* 5592 */             return false;
/*      */           }
/*      */           break;
/*      */         case 60:
/*      */         case 68:
/* 5597 */           if (isOneofPresent(message, number, pos) && 
/* 5598 */             !isInitialized(message, typeAndOffset, getMessageFieldSchema(pos))) {
/* 5599 */             return false;
/*      */           }
/*      */           break;
/*      */         case 50:
/* 5603 */           if (!isMapInitialized(message, typeAndOffset, pos)) {
/* 5604 */             return false;
/*      */           }
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */     
/*      */     } 
/* 5612 */     if (this.hasExtensions && 
/* 5613 */       !this.extensionSchema.getExtensions(message).isInitialized()) {
/* 5614 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 5618 */     return true;
/*      */   }
/*      */   
/*      */   private static boolean isInitialized(Object message, int typeAndOffset, Schema<Object> schema) {
/* 5622 */     Object nested = UnsafeUtil.getObject(message, offset(typeAndOffset));
/* 5623 */     return schema.isInitialized(nested);
/*      */   }
/*      */ 
/*      */   
/*      */   private <N> boolean isListInitialized(Object message, int typeAndOffset, int pos) {
/* 5628 */     List<N> list = (List<N>)UnsafeUtil.getObject(message, offset(typeAndOffset));
/* 5629 */     if (list.isEmpty()) {
/* 5630 */       return true;
/*      */     }
/*      */     
/* 5633 */     Schema<N> schema = getMessageFieldSchema(pos);
/* 5634 */     for (int i = 0; i < list.size(); i++) {
/* 5635 */       N nested = list.get(i);
/* 5636 */       if (!schema.isInitialized(nested)) {
/* 5637 */         return false;
/*      */       }
/*      */     } 
/* 5640 */     return true;
/*      */   }
/*      */   
/*      */   private boolean isMapInitialized(T message, int typeAndOffset, int pos) {
/* 5644 */     Map<?, ?> map = this.mapFieldSchema.forMapData(UnsafeUtil.getObject(message, offset(typeAndOffset)));
/* 5645 */     if (map.isEmpty()) {
/* 5646 */       return true;
/*      */     }
/* 5648 */     Object mapDefaultEntry = getMapFieldDefaultEntry(pos);
/* 5649 */     MapEntryLite.Metadata<?, ?> metadata = this.mapFieldSchema.forMapMetadata(mapDefaultEntry);
/* 5650 */     if (metadata.valueType.getJavaType() != WireFormat.JavaType.MESSAGE) {
/* 5651 */       return true;
/*      */     }
/*      */     
/* 5654 */     Schema<?> schema = null;
/* 5655 */     for (Object nested : map.values()) {
/* 5656 */       if (schema == null) {
/* 5657 */         schema = Protobuf.getInstance().schemaFor(nested.getClass());
/*      */       }
/* 5659 */       if (!schema.isInitialized(nested)) {
/* 5660 */         return false;
/*      */       }
/*      */     } 
/* 5663 */     return true;
/*      */   }
/*      */   
/*      */   private void writeString(int fieldNumber, Object value, Writer writer) throws IOException {
/* 5667 */     if (value instanceof String) {
/* 5668 */       writer.writeString(fieldNumber, (String)value);
/*      */     } else {
/* 5670 */       writer.writeBytes(fieldNumber, (ByteString)value);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void readString(Object message, int typeAndOffset, Reader reader) throws IOException {
/* 5675 */     if (isEnforceUtf8(typeAndOffset)) {
/*      */       
/* 5677 */       UnsafeUtil.putObject(message, offset(typeAndOffset), reader.readStringRequireUtf8());
/* 5678 */     } else if (this.lite) {
/*      */ 
/*      */       
/* 5681 */       UnsafeUtil.putObject(message, offset(typeAndOffset), reader.readString());
/*      */     }
/*      */     else {
/*      */       
/* 5685 */       UnsafeUtil.putObject(message, offset(typeAndOffset), reader.readBytes());
/*      */     } 
/*      */   }
/*      */   
/*      */   private void readStringList(Object message, int typeAndOffset, Reader reader) throws IOException {
/* 5690 */     if (isEnforceUtf8(typeAndOffset)) {
/* 5691 */       reader.readStringListRequireUtf8(this.listFieldSchema
/* 5692 */           .mutableListAt(message, offset(typeAndOffset)));
/*      */     } else {
/* 5694 */       reader.readStringList(this.listFieldSchema.mutableListAt(message, offset(typeAndOffset)));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private <E> void readMessageList(Object message, int typeAndOffset, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 5705 */     long offset = offset(typeAndOffset);
/* 5706 */     reader.readMessageList(this.listFieldSchema
/* 5707 */         .mutableListAt(message, offset), schema, extensionRegistry);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private <E> void readGroupList(Object message, long offset, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 5717 */     reader.readGroupList(this.listFieldSchema
/* 5718 */         .mutableListAt(message, offset), schema, extensionRegistry);
/*      */   }
/*      */   
/*      */   private int numberAt(int pos) {
/* 5722 */     return this.buffer[pos];
/*      */   }
/*      */   
/*      */   private int typeAndOffsetAt(int pos) {
/* 5726 */     return this.buffer[pos + 1];
/*      */   }
/*      */   
/*      */   private int presenceMaskAndOffsetAt(int pos) {
/* 5730 */     return this.buffer[pos + 2];
/*      */   }
/*      */   
/*      */   private static int type(int value) {
/* 5734 */     return (value & 0xFF00000) >>> 20;
/*      */   }
/*      */   
/*      */   private static boolean isRequired(int value) {
/* 5738 */     return ((value & 0x10000000) != 0);
/*      */   }
/*      */   
/*      */   private static boolean isEnforceUtf8(int value) {
/* 5742 */     return ((value & 0x20000000) != 0);
/*      */   }
/*      */   
/*      */   private static long offset(int value) {
/* 5746 */     return (value & 0xFFFFF);
/*      */   }
/*      */   
/*      */   private static <T> double doubleAt(T message, long offset) {
/* 5750 */     return UnsafeUtil.getDouble(message, offset);
/*      */   }
/*      */   
/*      */   private static <T> float floatAt(T message, long offset) {
/* 5754 */     return UnsafeUtil.getFloat(message, offset);
/*      */   }
/*      */   
/*      */   private static <T> int intAt(T message, long offset) {
/* 5758 */     return UnsafeUtil.getInt(message, offset);
/*      */   }
/*      */   
/*      */   private static <T> long longAt(T message, long offset) {
/* 5762 */     return UnsafeUtil.getLong(message, offset);
/*      */   }
/*      */   
/*      */   private static <T> boolean booleanAt(T message, long offset) {
/* 5766 */     return UnsafeUtil.getBoolean(message, offset);
/*      */   }
/*      */   
/*      */   private static <T> double oneofDoubleAt(T message, long offset) {
/* 5770 */     return ((Double)UnsafeUtil.getObject(message, offset)).doubleValue();
/*      */   }
/*      */   
/*      */   private static <T> float oneofFloatAt(T message, long offset) {
/* 5774 */     return ((Float)UnsafeUtil.getObject(message, offset)).floatValue();
/*      */   }
/*      */   
/*      */   private static <T> int oneofIntAt(T message, long offset) {
/* 5778 */     return ((Integer)UnsafeUtil.getObject(message, offset)).intValue();
/*      */   }
/*      */   
/*      */   private static <T> long oneofLongAt(T message, long offset) {
/* 5782 */     return ((Long)UnsafeUtil.getObject(message, offset)).longValue();
/*      */   }
/*      */   
/*      */   private static <T> boolean oneofBooleanAt(T message, long offset) {
/* 5786 */     return ((Boolean)UnsafeUtil.getObject(message, offset)).booleanValue();
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean arePresentForEquals(T message, T other, int pos) {
/* 5791 */     return (isFieldPresent(message, pos) == isFieldPresent(other, pos));
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean isFieldPresent(T message, int pos, int presenceFieldOffset, int presenceField, int presenceMask) {
/* 5796 */     if (presenceFieldOffset == 1048575) {
/* 5797 */       return isFieldPresent(message, pos);
/*      */     }
/* 5799 */     return ((presenceField & presenceMask) != 0);
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean isFieldPresent(T message, int pos) {
/* 5804 */     int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
/* 5805 */     long presenceFieldOffset = (presenceMaskAndOffset & 0xFFFFF);
/* 5806 */     if (presenceFieldOffset == 1048575L) {
/* 5807 */       Object value; int typeAndOffset = typeAndOffsetAt(pos);
/* 5808 */       long offset = offset(typeAndOffset);
/* 5809 */       switch (type(typeAndOffset)) {
/*      */         case 0:
/* 5811 */           return (UnsafeUtil.getDouble(message, offset) != 0.0D);
/*      */         case 1:
/* 5813 */           return (UnsafeUtil.getFloat(message, offset) != 0.0F);
/*      */         case 2:
/* 5815 */           return (UnsafeUtil.getLong(message, offset) != 0L);
/*      */         case 3:
/* 5817 */           return (UnsafeUtil.getLong(message, offset) != 0L);
/*      */         case 4:
/* 5819 */           return (UnsafeUtil.getInt(message, offset) != 0);
/*      */         case 5:
/* 5821 */           return (UnsafeUtil.getLong(message, offset) != 0L);
/*      */         case 6:
/* 5823 */           return (UnsafeUtil.getInt(message, offset) != 0);
/*      */         case 7:
/* 5825 */           return UnsafeUtil.getBoolean(message, offset);
/*      */         case 8:
/* 5827 */           value = UnsafeUtil.getObject(message, offset);
/* 5828 */           if (value instanceof String)
/* 5829 */             return !((String)value).isEmpty(); 
/* 5830 */           if (value instanceof ByteString) {
/* 5831 */             return !ByteString.EMPTY.equals(value);
/*      */           }
/* 5833 */           throw new IllegalArgumentException();
/*      */         
/*      */         case 9:
/* 5836 */           return (UnsafeUtil.getObject(message, offset) != null);
/*      */         case 10:
/* 5838 */           return !ByteString.EMPTY.equals(UnsafeUtil.getObject(message, offset));
/*      */         case 11:
/* 5840 */           return (UnsafeUtil.getInt(message, offset) != 0);
/*      */         case 12:
/* 5842 */           return (UnsafeUtil.getInt(message, offset) != 0);
/*      */         case 13:
/* 5844 */           return (UnsafeUtil.getInt(message, offset) != 0);
/*      */         case 14:
/* 5846 */           return (UnsafeUtil.getLong(message, offset) != 0L);
/*      */         case 15:
/* 5848 */           return (UnsafeUtil.getInt(message, offset) != 0);
/*      */         case 16:
/* 5850 */           return (UnsafeUtil.getLong(message, offset) != 0L);
/*      */         case 17:
/* 5852 */           return (UnsafeUtil.getObject(message, offset) != null);
/*      */       } 
/* 5854 */       throw new IllegalArgumentException();
/*      */     } 
/*      */     
/* 5857 */     int presenceMask = 1 << presenceMaskAndOffset >>> 20;
/* 5858 */     return ((UnsafeUtil.getInt(message, (presenceMaskAndOffset & 0xFFFFF)) & presenceMask) != 0);
/*      */   }
/*      */ 
/*      */   
/*      */   private void setFieldPresent(T message, int pos) {
/* 5863 */     int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
/* 5864 */     long presenceFieldOffset = (presenceMaskAndOffset & 0xFFFFF);
/* 5865 */     if (presenceFieldOffset == 1048575L) {
/*      */       return;
/*      */     }
/* 5868 */     int presenceMask = 1 << presenceMaskAndOffset >>> 20;
/* 5869 */     UnsafeUtil.putInt(message, presenceFieldOffset, 
/*      */ 
/*      */         
/* 5872 */         UnsafeUtil.getInt(message, presenceFieldOffset) | presenceMask);
/*      */   }
/*      */   
/*      */   private boolean isOneofPresent(T message, int fieldNumber, int pos) {
/* 5876 */     int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
/* 5877 */     return (UnsafeUtil.getInt(message, (presenceMaskAndOffset & 0xFFFFF)) == fieldNumber);
/*      */   }
/*      */   
/*      */   private boolean isOneofCaseEqual(T message, T other, int pos) {
/* 5881 */     int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
/* 5882 */     return 
/* 5883 */       (UnsafeUtil.getInt(message, (presenceMaskAndOffset & 0xFFFFF)) == UnsafeUtil.getInt(other, (presenceMaskAndOffset & 0xFFFFF)));
/*      */   }
/*      */   
/*      */   private void setOneofPresent(T message, int fieldNumber, int pos) {
/* 5887 */     int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
/* 5888 */     UnsafeUtil.putInt(message, (presenceMaskAndOffset & 0xFFFFF), fieldNumber);
/*      */   }
/*      */   
/*      */   private int positionForFieldNumber(int number) {
/* 5892 */     if (number >= this.minFieldNumber && number <= this.maxFieldNumber) {
/* 5893 */       return slowPositionForFieldNumber(number, 0);
/*      */     }
/* 5895 */     return -1;
/*      */   }
/*      */   
/*      */   private int positionForFieldNumber(int number, int min) {
/* 5899 */     if (number >= this.minFieldNumber && number <= this.maxFieldNumber) {
/* 5900 */       return slowPositionForFieldNumber(number, min);
/*      */     }
/* 5902 */     return -1;
/*      */   }
/*      */   
/*      */   private int slowPositionForFieldNumber(int number, int min) {
/* 5906 */     int max = this.buffer.length / 3 - 1;
/* 5907 */     while (min <= max) {
/*      */       
/* 5909 */       int mid = max + min >>> 1;
/* 5910 */       int pos = mid * 3;
/* 5911 */       int midFieldNumber = numberAt(pos);
/* 5912 */       if (number == midFieldNumber)
/*      */       {
/* 5914 */         return pos;
/*      */       }
/* 5916 */       if (number < midFieldNumber) {
/*      */         
/* 5918 */         max = mid - 1;
/*      */         continue;
/*      */       } 
/* 5921 */       min = mid + 1;
/*      */     } 
/*      */     
/* 5924 */     return -1;
/*      */   }
/*      */   
/*      */   int getSchemaSize() {
/* 5928 */     return this.buffer.length * 3;
/*      */   }
/*      */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\MessageSchema.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */