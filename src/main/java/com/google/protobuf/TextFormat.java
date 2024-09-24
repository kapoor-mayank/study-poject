/*      */ package com.google.protobuf;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.math.BigInteger;
/*      */ import java.nio.CharBuffer;
/*      */ import java.nio.charset.StandardCharsets;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.logging.Logger;
/*      */ import java.util.regex.Matcher;
/*      */ import java.util.regex.Pattern;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class TextFormat
/*      */ {
/*   62 */   private static final Logger logger = Logger.getLogger(TextFormat.class.getName());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static void print(MessageOrBuilder message, Appendable output) throws IOException {
/*   74 */     printer().print(message, output);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static void print(UnknownFieldSet fields, Appendable output) throws IOException {
/*   85 */     printer().print(fields, output);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static void printUnicode(MessageOrBuilder message, Appendable output) throws IOException {
/*   96 */     printer().escapingNonAscii(false).print(message, output);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static void printUnicode(UnknownFieldSet fields, Appendable output) throws IOException {
/*  107 */     printer().escapingNonAscii(false).print(fields, output);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String shortDebugString(MessageOrBuilder message) {
/*  116 */     return printer().shortDebugString(message);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static String shortDebugString(Descriptors.FieldDescriptor field, Object value) {
/*  127 */     return printer().shortDebugString(field, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static String shortDebugString(UnknownFieldSet fields) {
/*  138 */     return printer().shortDebugString(fields);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static String printToString(MessageOrBuilder message) {
/*  148 */     return printer().printToString(message);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static String printToString(UnknownFieldSet fields) {
/*  158 */     return printer().printToString(fields);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static String printToUnicodeString(MessageOrBuilder message) {
/*  169 */     return printer().escapingNonAscii(false).printToString(message);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static String printToUnicodeString(UnknownFieldSet fields) {
/*  180 */     return printer().escapingNonAscii(false).printToString(fields);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static void printField(Descriptors.FieldDescriptor field, Object value, Appendable output) throws IOException {
/*  188 */     printer().printField(field, value, output);
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static String printFieldToString(Descriptors.FieldDescriptor field, Object value) {
/*  194 */     return printer().printFieldToString(field, value);
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
/*      */   
/*      */   @Deprecated
/*      */   public static void printUnicodeFieldValue(Descriptors.FieldDescriptor field, Object value, Appendable output) throws IOException {
/*  215 */     printer().escapingNonAscii(false).printFieldValue(field, value, output);
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
/*      */   @Deprecated
/*      */   public static void printFieldValue(Descriptors.FieldDescriptor field, Object value, Appendable output) throws IOException {
/*  231 */     printer().printFieldValue(field, value, output);
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
/*      */   public static void printUnknownFieldValue(int tag, Object value, Appendable output) throws IOException {
/*  245 */     printUnknownFieldValue(tag, value, multiLineOutput(output));
/*      */   }
/*      */ 
/*      */   
/*      */   private static void printUnknownFieldValue(int tag, Object value, TextGenerator generator) throws IOException {
/*  250 */     switch (WireFormat.getTagWireType(tag)) {
/*      */       case 0:
/*  252 */         generator.print(unsignedToString(((Long)value).longValue()));
/*      */         return;
/*      */       case 5:
/*  255 */         generator.print(String.format((Locale)null, "0x%08x", new Object[] { value }));
/*      */         return;
/*      */       case 1:
/*  258 */         generator.print(String.format((Locale)null, "0x%016x", new Object[] { value }));
/*      */         return;
/*      */       
/*      */       case 2:
/*      */         try {
/*  263 */           UnknownFieldSet message = UnknownFieldSet.parseFrom((ByteString)value);
/*  264 */           generator.print("{");
/*  265 */           generator.eol();
/*  266 */           generator.indent();
/*  267 */           Printer.printUnknownFields(message, generator);
/*  268 */           generator.outdent();
/*  269 */           generator.print("}");
/*  270 */         } catch (InvalidProtocolBufferException e) {
/*      */           
/*  272 */           generator.print("\"");
/*  273 */           generator.print(escapeBytes((ByteString)value));
/*  274 */           generator.print("\"");
/*      */         } 
/*      */         return;
/*      */       case 3:
/*  278 */         Printer.printUnknownFields((UnknownFieldSet)value, generator);
/*      */         return;
/*      */     } 
/*  281 */     throw new IllegalArgumentException("Bad tag: " + tag);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static Printer printer() {
/*  287 */     return Printer.DEFAULT;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static final class Printer
/*      */   {
/*  294 */     private static final Printer DEFAULT = new Printer(true, TypeRegistry.getEmptyTypeRegistry());
/*      */     
/*      */     private final boolean escapeNonAscii;
/*      */     
/*      */     private final TypeRegistry typeRegistry;
/*      */ 
/*      */     
/*      */     private Printer(boolean escapeNonAscii, TypeRegistry typeRegistry) {
/*  302 */       this.escapeNonAscii = escapeNonAscii;
/*  303 */       this.typeRegistry = typeRegistry;
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
/*      */     public Printer escapingNonAscii(boolean escapeNonAscii) {
/*  316 */       return new Printer(escapeNonAscii, this.typeRegistry);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Printer usingTypeRegistry(TypeRegistry typeRegistry) {
/*  326 */       if (this.typeRegistry != TypeRegistry.getEmptyTypeRegistry()) {
/*  327 */         throw new IllegalArgumentException("Only one typeRegistry is allowed.");
/*      */       }
/*  329 */       return new Printer(this.escapeNonAscii, typeRegistry);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void print(MessageOrBuilder message, Appendable output) throws IOException {
/*  338 */       print(message, TextFormat.multiLineOutput(output));
/*      */     }
/*      */ 
/*      */     
/*      */     public void print(UnknownFieldSet fields, Appendable output) throws IOException {
/*  343 */       printUnknownFields(fields, TextFormat.multiLineOutput(output));
/*      */     }
/*      */ 
/*      */     
/*      */     private void print(MessageOrBuilder message, TextGenerator generator) throws IOException {
/*  348 */       if (message.getDescriptorForType().getFullName().equals("google.protobuf.Any") && 
/*  349 */         printAny(message, generator)) {
/*      */         return;
/*      */       }
/*  352 */       printMessage(message, generator);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean printAny(MessageOrBuilder message, TextGenerator generator) throws IOException {
/*  362 */       Descriptors.Descriptor messageType = message.getDescriptorForType();
/*  363 */       Descriptors.FieldDescriptor typeUrlField = messageType.findFieldByNumber(1);
/*  364 */       Descriptors.FieldDescriptor valueField = messageType.findFieldByNumber(2);
/*  365 */       if (typeUrlField == null || typeUrlField
/*  366 */         .getType() != Descriptors.FieldDescriptor.Type.STRING || valueField == null || valueField
/*      */         
/*  368 */         .getType() != Descriptors.FieldDescriptor.Type.BYTES)
/*      */       {
/*      */         
/*  371 */         return false;
/*      */       }
/*  373 */       String typeUrl = (String)message.getField(typeUrlField);
/*      */ 
/*      */       
/*  376 */       if (typeUrl.isEmpty()) {
/*  377 */         return false;
/*      */       }
/*  379 */       Object value = message.getField(valueField);
/*      */       
/*  381 */       Message.Builder contentBuilder = null;
/*      */       try {
/*  383 */         Descriptors.Descriptor contentType = this.typeRegistry.getDescriptorForTypeUrl(typeUrl);
/*  384 */         if (contentType == null) {
/*  385 */           return false;
/*      */         }
/*  387 */         contentBuilder = DynamicMessage.getDefaultInstance(contentType).newBuilderForType();
/*  388 */         contentBuilder.mergeFrom((ByteString)value);
/*  389 */       } catch (InvalidProtocolBufferException e) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  394 */         return false;
/*      */       } 
/*  396 */       generator.print("[");
/*  397 */       generator.print(typeUrl);
/*  398 */       generator.print("] {");
/*  399 */       generator.eol();
/*  400 */       generator.indent();
/*  401 */       print(contentBuilder, generator);
/*  402 */       generator.outdent();
/*  403 */       generator.print("}");
/*  404 */       generator.eol();
/*  405 */       return true;
/*      */     }
/*      */     
/*      */     public String printFieldToString(Descriptors.FieldDescriptor field, Object value) {
/*      */       try {
/*  410 */         StringBuilder text = new StringBuilder();
/*  411 */         printField(field, value, text);
/*  412 */         return text.toString();
/*  413 */       } catch (IOException e) {
/*  414 */         throw new IllegalStateException(e);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void printField(Descriptors.FieldDescriptor field, Object value, Appendable output) throws IOException {
/*  420 */       printField(field, value, TextFormat.multiLineOutput(output));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void printField(Descriptors.FieldDescriptor field, Object value, TextGenerator generator) throws IOException {
/*  427 */       if (field.isMapField()) {
/*  428 */         List<MapEntryAdapter> adapters = new ArrayList<>();
/*  429 */         for (Object entry : value) {
/*  430 */           adapters.add(new MapEntryAdapter(entry, field));
/*      */         }
/*  432 */         Collections.sort(adapters);
/*  433 */         for (MapEntryAdapter adapter : adapters) {
/*  434 */           printSingleField(field, adapter.getEntry(), generator);
/*      */         }
/*  436 */       } else if (field.isRepeated()) {
/*      */         
/*  438 */         for (Object element : value) {
/*  439 */           printSingleField(field, element, generator);
/*      */         }
/*      */       } else {
/*  442 */         printSingleField(field, value, generator);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private static class MapEntryAdapter
/*      */       implements Comparable<MapEntryAdapter>
/*      */     {
/*      */       private Object entry;
/*      */ 
/*      */       
/*      */       private MapEntry mapEntry;
/*      */ 
/*      */       
/*      */       private final Descriptors.FieldDescriptor.JavaType fieldType;
/*      */ 
/*      */       
/*      */       public MapEntryAdapter(Object entry, Descriptors.FieldDescriptor fieldDescriptor) {
/*  461 */         if (entry instanceof MapEntry) {
/*  462 */           this.mapEntry = (MapEntry)entry;
/*      */         } else {
/*  464 */           this.entry = entry;
/*      */         } 
/*  466 */         this.fieldType = extractFieldType(fieldDescriptor);
/*      */       }
/*      */       
/*      */       private static Descriptors.FieldDescriptor.JavaType extractFieldType(Descriptors.FieldDescriptor fieldDescriptor) {
/*  470 */         return ((Descriptors.FieldDescriptor)fieldDescriptor.getMessageType().getFields().get(0)).getJavaType();
/*      */       }
/*      */       
/*      */       public Object getKey() {
/*  474 */         if (this.mapEntry != null) {
/*  475 */           return this.mapEntry.getKey();
/*      */         }
/*  477 */         return null;
/*      */       }
/*      */       
/*      */       public Object getEntry() {
/*  481 */         if (this.mapEntry != null) {
/*  482 */           return this.mapEntry;
/*      */         }
/*  484 */         return this.entry;
/*      */       }
/*      */       public int compareTo(MapEntryAdapter b) {
/*      */         String aString;
/*      */         String bString;
/*  489 */         if (getKey() == null || b.getKey() == null) {
/*  490 */           TextFormat.logger.info("Invalid key for map field.");
/*  491 */           return -1;
/*      */         } 
/*  493 */         switch (this.fieldType) {
/*      */           case INT32:
/*  495 */             return Boolean.compare(((Boolean)getKey()).booleanValue(), ((Boolean)b.getKey()).booleanValue());
/*      */           case SINT32:
/*  497 */             return Long.compare(((Long)getKey()).longValue(), ((Long)b.getKey()).longValue());
/*      */           case SFIXED32:
/*  499 */             return Integer.compare(((Integer)getKey()).intValue(), ((Integer)b.getKey()).intValue());
/*      */           case INT64:
/*  501 */             aString = (String)getKey();
/*  502 */             bString = (String)b.getKey();
/*  503 */             if (aString == null && bString == null)
/*  504 */               return 0; 
/*  505 */             if (aString == null && bString != null)
/*  506 */               return -1; 
/*  507 */             if (aString != null && bString == null) {
/*  508 */               return 1;
/*      */             }
/*  510 */             return aString.compareTo(bString);
/*      */         } 
/*      */         
/*  513 */         return 0;
/*      */       }
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
/*      */     public void printFieldValue(Descriptors.FieldDescriptor field, Object value, Appendable output) throws IOException {
/*  530 */       printFieldValue(field, value, TextFormat.multiLineOutput(output));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private void printFieldValue(Descriptors.FieldDescriptor field, Object value, TextGenerator generator) throws IOException {
/*  536 */       switch (field.getType()) {
/*      */         case INT32:
/*      */         case SINT32:
/*      */         case SFIXED32:
/*  540 */           generator.print(((Integer)value).toString());
/*      */           break;
/*      */         
/*      */         case INT64:
/*      */         case SINT64:
/*      */         case SFIXED64:
/*  546 */           generator.print(((Long)value).toString());
/*      */           break;
/*      */         
/*      */         case BOOL:
/*  550 */           generator.print(((Boolean)value).toString());
/*      */           break;
/*      */         
/*      */         case FLOAT:
/*  554 */           generator.print(((Float)value).toString());
/*      */           break;
/*      */         
/*      */         case DOUBLE:
/*  558 */           generator.print(((Double)value).toString());
/*      */           break;
/*      */         
/*      */         case UINT32:
/*      */         case FIXED32:
/*  563 */           generator.print(TextFormat.unsignedToString(((Integer)value).intValue()));
/*      */           break;
/*      */         
/*      */         case UINT64:
/*      */         case FIXED64:
/*  568 */           generator.print(TextFormat.unsignedToString(((Long)value).longValue()));
/*      */           break;
/*      */         
/*      */         case STRING:
/*  572 */           generator.print("\"");
/*  573 */           generator.print(this.escapeNonAscii ? 
/*      */               
/*  575 */               TextFormatEscaper.escapeText((String)value) : 
/*  576 */               TextFormat.escapeDoubleQuotesAndBackslashes((String)value).replace("\n", "\\n"));
/*  577 */           generator.print("\"");
/*      */           break;
/*      */         
/*      */         case BYTES:
/*  581 */           generator.print("\"");
/*  582 */           if (value instanceof ByteString) {
/*  583 */             generator.print(TextFormat.escapeBytes((ByteString)value));
/*      */           } else {
/*  585 */             generator.print(TextFormat.escapeBytes((byte[])value));
/*      */           } 
/*  587 */           generator.print("\"");
/*      */           break;
/*      */         
/*      */         case ENUM:
/*  591 */           generator.print(((Descriptors.EnumValueDescriptor)value).getName());
/*      */           break;
/*      */         
/*      */         case MESSAGE:
/*      */         case GROUP:
/*  596 */           print((Message)value, generator);
/*      */           break;
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public String printToString(MessageOrBuilder message) {
/*      */       try {
/*  604 */         StringBuilder text = new StringBuilder();
/*  605 */         print(message, text);
/*  606 */         return text.toString();
/*  607 */       } catch (IOException e) {
/*  608 */         throw new IllegalStateException(e);
/*      */       } 
/*      */     }
/*      */     
/*      */     public String printToString(UnknownFieldSet fields) {
/*      */       try {
/*  614 */         StringBuilder text = new StringBuilder();
/*  615 */         print(fields, text);
/*  616 */         return text.toString();
/*  617 */       } catch (IOException e) {
/*  618 */         throw new IllegalStateException(e);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String shortDebugString(MessageOrBuilder message) {
/*      */       try {
/*  628 */         StringBuilder text = new StringBuilder();
/*  629 */         print(message, TextFormat.singleLineOutput(text));
/*  630 */         return text.toString();
/*  631 */       } catch (IOException e) {
/*  632 */         throw new IllegalStateException(e);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String shortDebugString(Descriptors.FieldDescriptor field, Object value) {
/*      */       try {
/*  642 */         StringBuilder text = new StringBuilder();
/*  643 */         printField(field, value, TextFormat.singleLineOutput(text));
/*  644 */         return text.toString();
/*  645 */       } catch (IOException e) {
/*  646 */         throw new IllegalStateException(e);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String shortDebugString(UnknownFieldSet fields) {
/*      */       try {
/*  656 */         StringBuilder text = new StringBuilder();
/*  657 */         printUnknownFields(fields, TextFormat.singleLineOutput(text));
/*  658 */         return text.toString();
/*  659 */       } catch (IOException e) {
/*  660 */         throw new IllegalStateException(e);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     private static void printUnknownFieldValue(int tag, Object value, TextGenerator generator) throws IOException {
/*  666 */       switch (WireFormat.getTagWireType(tag)) {
/*      */         case 0:
/*  668 */           generator.print(TextFormat.unsignedToString(((Long)value).longValue()));
/*      */           return;
/*      */         case 5:
/*  671 */           generator.print(String.format((Locale)null, "0x%08x", new Object[] { value }));
/*      */           return;
/*      */         case 1:
/*  674 */           generator.print(String.format((Locale)null, "0x%016x", new Object[] { value }));
/*      */           return;
/*      */         
/*      */         case 2:
/*      */           try {
/*  679 */             UnknownFieldSet message = UnknownFieldSet.parseFrom((ByteString)value);
/*  680 */             generator.print("{");
/*  681 */             generator.eol();
/*  682 */             generator.indent();
/*  683 */             printUnknownFields(message, generator);
/*  684 */             generator.outdent();
/*  685 */             generator.print("}");
/*  686 */           } catch (InvalidProtocolBufferException e) {
/*      */             
/*  688 */             generator.print("\"");
/*  689 */             generator.print(TextFormat.escapeBytes((ByteString)value));
/*  690 */             generator.print("\"");
/*      */           } 
/*      */           return;
/*      */         case 3:
/*  694 */           printUnknownFields((UnknownFieldSet)value, generator);
/*      */           return;
/*      */       } 
/*  697 */       throw new IllegalArgumentException("Bad tag: " + tag);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private void printMessage(MessageOrBuilder message, TextGenerator generator) throws IOException {
/*  703 */       for (Map.Entry<Descriptors.FieldDescriptor, Object> field : message.getAllFields().entrySet()) {
/*  704 */         printField(field.getKey(), field.getValue(), generator);
/*      */       }
/*  706 */       printUnknownFields(message.getUnknownFields(), generator);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private void printSingleField(Descriptors.FieldDescriptor field, Object value, TextGenerator generator) throws IOException {
/*  712 */       if (field.isExtension()) {
/*  713 */         generator.print("[");
/*      */         
/*  715 */         if (field.getContainingType().getOptions().getMessageSetWireFormat() && field
/*  716 */           .getType() == Descriptors.FieldDescriptor.Type.MESSAGE && field
/*  717 */           .isOptional() && field
/*      */           
/*  719 */           .getExtensionScope() == field.getMessageType()) {
/*  720 */           generator.print(field.getMessageType().getFullName());
/*      */         } else {
/*  722 */           generator.print(field.getFullName());
/*      */         } 
/*  724 */         generator.print("]");
/*      */       }
/*  726 */       else if (field.getType() == Descriptors.FieldDescriptor.Type.GROUP) {
/*      */         
/*  728 */         generator.print(field.getMessageType().getName());
/*      */       } else {
/*  730 */         generator.print(field.getName());
/*      */       } 
/*      */ 
/*      */       
/*  734 */       if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
/*  735 */         generator.print(" {");
/*  736 */         generator.eol();
/*  737 */         generator.indent();
/*      */       } else {
/*  739 */         generator.print(": ");
/*      */       } 
/*      */       
/*  742 */       printFieldValue(field, value, generator);
/*      */       
/*  744 */       if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
/*  745 */         generator.outdent();
/*  746 */         generator.print("}");
/*      */       } 
/*  748 */       generator.eol();
/*      */     }
/*      */ 
/*      */     
/*      */     private static void printUnknownFields(UnknownFieldSet unknownFields, TextGenerator generator) throws IOException {
/*  753 */       for (Map.Entry<Integer, UnknownFieldSet.Field> entry : unknownFields.asMap().entrySet()) {
/*  754 */         int number = ((Integer)entry.getKey()).intValue();
/*  755 */         UnknownFieldSet.Field field = entry.getValue();
/*  756 */         printUnknownField(number, 0, field.getVarintList(), generator);
/*  757 */         printUnknownField(number, 5, field.getFixed32List(), generator);
/*  758 */         printUnknownField(number, 1, field.getFixed64List(), generator);
/*  759 */         printUnknownField(number, 2, field
/*      */ 
/*      */             
/*  762 */             .getLengthDelimitedList(), generator);
/*      */         
/*  764 */         for (UnknownFieldSet value : field.getGroupList()) {
/*  765 */           generator.print(((Integer)entry.getKey()).toString());
/*  766 */           generator.print(" {");
/*  767 */           generator.eol();
/*  768 */           generator.indent();
/*  769 */           printUnknownFields(value, generator);
/*  770 */           generator.outdent();
/*  771 */           generator.print("}");
/*  772 */           generator.eol();
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private static void printUnknownField(int number, int wireType, List<?> values, TextGenerator generator) throws IOException {
/*  780 */       for (Object value : values) {
/*  781 */         generator.print(String.valueOf(number));
/*  782 */         generator.print(": ");
/*  783 */         printUnknownFieldValue(wireType, value, generator);
/*  784 */         generator.eol();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static String unsignedToString(int value) {
/*  791 */     if (value >= 0) {
/*  792 */       return Integer.toString(value);
/*      */     }
/*  794 */     return Long.toString(value & 0xFFFFFFFFL);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static String unsignedToString(long value) {
/*  800 */     if (value >= 0L) {
/*  801 */       return Long.toString(value);
/*      */     }
/*      */ 
/*      */     
/*  805 */     return BigInteger.valueOf(value & Long.MAX_VALUE).setBit(63).toString();
/*      */   }
/*      */ 
/*      */   
/*      */   private static TextGenerator multiLineOutput(Appendable output) {
/*  810 */     return new TextGenerator(output, false);
/*      */   }
/*      */   
/*      */   private static TextGenerator singleLineOutput(Appendable output) {
/*  814 */     return new TextGenerator(output, true);
/*      */   }
/*      */   
/*      */   private static final class TextGenerator
/*      */   {
/*      */     private final Appendable output;
/*  820 */     private final StringBuilder indent = new StringBuilder();
/*      */     
/*      */     private final boolean singleLineMode;
/*      */     
/*      */     private boolean atStartOfLine = false;
/*      */ 
/*      */     
/*      */     private TextGenerator(Appendable output, boolean singleLineMode) {
/*  828 */       this.output = output;
/*  829 */       this.singleLineMode = singleLineMode;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void indent() {
/*  838 */       this.indent.append("  ");
/*      */     }
/*      */ 
/*      */     
/*      */     public void outdent() {
/*  843 */       int length = this.indent.length();
/*  844 */       if (length == 0) {
/*  845 */         throw new IllegalArgumentException(" Outdent() without matching Indent().");
/*      */       }
/*  847 */       this.indent.setLength(length - 2);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void print(CharSequence text) throws IOException {
/*  855 */       if (this.atStartOfLine) {
/*  856 */         this.atStartOfLine = false;
/*  857 */         this.output.append(this.singleLineMode ? " " : this.indent);
/*      */       } 
/*  859 */       this.output.append(text);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void eol() throws IOException {
/*  868 */       if (!this.singleLineMode) {
/*  869 */         this.output.append("\n");
/*      */       }
/*  871 */       this.atStartOfLine = true;
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
/*      */   private static final class Tokenizer
/*      */   {
/*      */     private final CharSequence text;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private final Matcher matcher;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private String currentToken;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  908 */     private int pos = 0;
/*      */ 
/*      */     
/*  911 */     private int line = 0;
/*  912 */     private int column = 0;
/*      */ 
/*      */ 
/*      */     
/*  916 */     private int previousLine = 0;
/*  917 */     private int previousColumn = 0;
/*      */ 
/*      */ 
/*      */     
/*  921 */     private static final Pattern WHITESPACE = Pattern.compile("(\\s|(#.*$))++", 8);
/*      */     
/*  923 */     private static final Pattern TOKEN = Pattern.compile("[a-zA-Z_][0-9a-zA-Z_+-]*+|[.]?[0-9+-][0-9a-zA-Z_.+-]*+|\"([^\"\n\\\\]|\\\\.)*+(\"|\\\\?$)|'([^'\n\\\\]|\\\\.)*+('|\\\\?$)", 8);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  931 */     private static final Pattern DOUBLE_INFINITY = Pattern.compile("-?inf(inity)?", 2);
/*      */     
/*  933 */     private static final Pattern FLOAT_INFINITY = Pattern.compile("-?inf(inity)?f?", 2);
/*  934 */     private static final Pattern FLOAT_NAN = Pattern.compile("nanf?", 2);
/*      */ 
/*      */     
/*      */     private Tokenizer(CharSequence text) {
/*  938 */       this.text = text;
/*  939 */       this.matcher = WHITESPACE.matcher(text);
/*  940 */       skipWhitespace();
/*  941 */       nextToken();
/*      */     }
/*      */     
/*      */     int getPreviousLine() {
/*  945 */       return this.previousLine;
/*      */     }
/*      */     
/*      */     int getPreviousColumn() {
/*  949 */       return this.previousColumn;
/*      */     }
/*      */     
/*      */     int getLine() {
/*  953 */       return this.line;
/*      */     }
/*      */     
/*      */     int getColumn() {
/*  957 */       return this.column;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean atEnd() {
/*  962 */       return (this.currentToken.length() == 0);
/*      */     }
/*      */ 
/*      */     
/*      */     public void nextToken() {
/*  967 */       this.previousLine = this.line;
/*  968 */       this.previousColumn = this.column;
/*      */ 
/*      */       
/*  971 */       while (this.pos < this.matcher.regionStart()) {
/*  972 */         if (this.text.charAt(this.pos) == '\n') {
/*  973 */           this.line++;
/*  974 */           this.column = 0;
/*      */         } else {
/*  976 */           this.column++;
/*      */         } 
/*  978 */         this.pos++;
/*      */       } 
/*      */ 
/*      */       
/*  982 */       if (this.matcher.regionStart() == this.matcher.regionEnd()) {
/*      */         
/*  984 */         this.currentToken = "";
/*      */       } else {
/*  986 */         this.matcher.usePattern(TOKEN);
/*  987 */         if (this.matcher.lookingAt()) {
/*  988 */           this.currentToken = this.matcher.group();
/*  989 */           this.matcher.region(this.matcher.end(), this.matcher.regionEnd());
/*      */         } else {
/*      */           
/*  992 */           this.currentToken = String.valueOf(this.text.charAt(this.pos));
/*  993 */           this.matcher.region(this.pos + 1, this.matcher.regionEnd());
/*      */         } 
/*      */         
/*  996 */         skipWhitespace();
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     private void skipWhitespace() {
/* 1002 */       this.matcher.usePattern(WHITESPACE);
/* 1003 */       if (this.matcher.lookingAt()) {
/* 1004 */         this.matcher.region(this.matcher.end(), this.matcher.regionEnd());
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean tryConsume(String token) {
/* 1013 */       if (this.currentToken.equals(token)) {
/* 1014 */         nextToken();
/* 1015 */         return true;
/*      */       } 
/* 1017 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void consume(String token) throws ParseException {
/* 1026 */       if (!tryConsume(token)) {
/* 1027 */         throw parseException("Expected \"" + token + "\".");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean lookingAtInteger() {
/* 1033 */       if (this.currentToken.length() == 0) {
/* 1034 */         return false;
/*      */       }
/*      */       
/* 1037 */       char c = this.currentToken.charAt(0);
/* 1038 */       return (('0' <= c && c <= '9') || c == '-' || c == '+');
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean lookingAt(String text) {
/* 1043 */       return this.currentToken.equals(text);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String consumeIdentifier() throws ParseException {
/* 1051 */       for (int i = 0; i < this.currentToken.length(); ) {
/* 1052 */         char c = this.currentToken.charAt(i);
/* 1053 */         if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || ('0' <= c && c <= '9') || c == '_' || c == '.') {
/*      */           i++;
/*      */ 
/*      */           
/*      */           continue;
/*      */         } 
/*      */         
/* 1060 */         throw parseException("Expected identifier. Found '" + this.currentToken + "'");
/*      */       } 
/*      */ 
/*      */       
/* 1064 */       String result = this.currentToken;
/* 1065 */       nextToken();
/* 1066 */       return result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean tryConsumeIdentifier() {
/*      */       try {
/* 1075 */         consumeIdentifier();
/* 1076 */         return true;
/* 1077 */       } catch (ParseException e) {
/* 1078 */         return false;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int consumeInt32() throws ParseException {
/*      */       try {
/* 1088 */         int result = TextFormat.parseInt32(this.currentToken);
/* 1089 */         nextToken();
/* 1090 */         return result;
/* 1091 */       } catch (NumberFormatException e) {
/* 1092 */         throw integerParseException(e);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int consumeUInt32() throws ParseException {
/*      */       try {
/* 1102 */         int result = TextFormat.parseUInt32(this.currentToken);
/* 1103 */         nextToken();
/* 1104 */         return result;
/* 1105 */       } catch (NumberFormatException e) {
/* 1106 */         throw integerParseException(e);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public long consumeInt64() throws ParseException {
/*      */       try {
/* 1116 */         long result = TextFormat.parseInt64(this.currentToken);
/* 1117 */         nextToken();
/* 1118 */         return result;
/* 1119 */       } catch (NumberFormatException e) {
/* 1120 */         throw integerParseException(e);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean tryConsumeInt64() {
/*      */       try {
/* 1130 */         consumeInt64();
/* 1131 */         return true;
/* 1132 */       } catch (ParseException e) {
/* 1133 */         return false;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public long consumeUInt64() throws ParseException {
/*      */       try {
/* 1143 */         long result = TextFormat.parseUInt64(this.currentToken);
/* 1144 */         nextToken();
/* 1145 */         return result;
/* 1146 */       } catch (NumberFormatException e) {
/* 1147 */         throw integerParseException(e);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean tryConsumeUInt64() {
/*      */       try {
/* 1157 */         consumeUInt64();
/* 1158 */         return true;
/* 1159 */       } catch (ParseException e) {
/* 1160 */         return false;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public double consumeDouble() throws ParseException {
/* 1171 */       if (DOUBLE_INFINITY.matcher(this.currentToken).matches()) {
/* 1172 */         boolean negative = this.currentToken.startsWith("-");
/* 1173 */         nextToken();
/* 1174 */         return negative ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
/*      */       } 
/* 1176 */       if (this.currentToken.equalsIgnoreCase("nan")) {
/* 1177 */         nextToken();
/* 1178 */         return Double.NaN;
/*      */       } 
/*      */       try {
/* 1181 */         double result = Double.parseDouble(this.currentToken);
/* 1182 */         nextToken();
/* 1183 */         return result;
/* 1184 */       } catch (NumberFormatException e) {
/* 1185 */         throw floatParseException(e);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean tryConsumeDouble() {
/*      */       try {
/* 1195 */         consumeDouble();
/* 1196 */         return true;
/* 1197 */       } catch (ParseException e) {
/* 1198 */         return false;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public float consumeFloat() throws ParseException {
/* 1209 */       if (FLOAT_INFINITY.matcher(this.currentToken).matches()) {
/* 1210 */         boolean negative = this.currentToken.startsWith("-");
/* 1211 */         nextToken();
/* 1212 */         return negative ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY;
/*      */       } 
/* 1214 */       if (FLOAT_NAN.matcher(this.currentToken).matches()) {
/* 1215 */         nextToken();
/* 1216 */         return Float.NaN;
/*      */       } 
/*      */       try {
/* 1219 */         float result = Float.parseFloat(this.currentToken);
/* 1220 */         nextToken();
/* 1221 */         return result;
/* 1222 */       } catch (NumberFormatException e) {
/* 1223 */         throw floatParseException(e);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean tryConsumeFloat() {
/*      */       try {
/* 1233 */         consumeFloat();
/* 1234 */         return true;
/* 1235 */       } catch (ParseException e) {
/* 1236 */         return false;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean consumeBoolean() throws ParseException {
/* 1245 */       if (this.currentToken.equals("true") || this.currentToken
/* 1246 */         .equals("True") || this.currentToken
/* 1247 */         .equals("t") || this.currentToken
/* 1248 */         .equals("1")) {
/* 1249 */         nextToken();
/* 1250 */         return true;
/* 1251 */       }  if (this.currentToken.equals("false") || this.currentToken
/* 1252 */         .equals("False") || this.currentToken
/* 1253 */         .equals("f") || this.currentToken
/* 1254 */         .equals("0")) {
/* 1255 */         nextToken();
/* 1256 */         return false;
/*      */       } 
/* 1258 */       throw parseException("Expected \"true\" or \"false\". Found \"" + this.currentToken + "\".");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String consumeString() throws ParseException {
/* 1267 */       return consumeByteString().toStringUtf8();
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean tryConsumeString() {
/*      */       try {
/* 1273 */         consumeString();
/* 1274 */         return true;
/* 1275 */       } catch (ParseException e) {
/* 1276 */         return false;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ByteString consumeByteString() throws ParseException {
/* 1285 */       List<ByteString> list = new ArrayList<>();
/* 1286 */       consumeByteString(list);
/* 1287 */       while (this.currentToken.startsWith("'") || this.currentToken.startsWith("\"")) {
/* 1288 */         consumeByteString(list);
/*      */       }
/* 1290 */       return ByteString.copyFrom(list);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void consumeByteString(List<ByteString> list) throws ParseException {
/* 1299 */       char quote = (this.currentToken.length() > 0) ? this.currentToken.charAt(0) : Character.MIN_VALUE;
/* 1300 */       if (quote != '"' && quote != '\'') {
/* 1301 */         throw parseException("Expected string.");
/*      */       }
/*      */       
/* 1304 */       if (this.currentToken.length() < 2 || this.currentToken.charAt(this.currentToken.length() - 1) != quote) {
/* 1305 */         throw parseException("String missing ending quote.");
/*      */       }
/*      */       
/*      */       try {
/* 1309 */         String escaped = this.currentToken.substring(1, this.currentToken.length() - 1);
/* 1310 */         ByteString result = TextFormat.unescapeBytes(escaped);
/* 1311 */         nextToken();
/* 1312 */         list.add(result);
/* 1313 */       } catch (InvalidEscapeSequenceException e) {
/* 1314 */         throw parseException(e.getMessage());
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ParseException parseException(String description) {
/* 1324 */       return new ParseException(this.line + 1, this.column + 1, description);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ParseException parseExceptionPreviousToken(String description) {
/* 1333 */       return new ParseException(this.previousLine + 1, this.previousColumn + 1, description);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private ParseException integerParseException(NumberFormatException e) {
/* 1341 */       return parseException("Couldn't parse integer: " + e.getMessage());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private ParseException floatParseException(NumberFormatException e) {
/* 1349 */       return parseException("Couldn't parse number: " + e.getMessage());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public UnknownFieldParseException unknownFieldParseExceptionPreviousToken(String unknownField, String description) {
/* 1359 */       return new UnknownFieldParseException(this.previousLine + 1, this.previousColumn + 1, unknownField, description);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class ParseException
/*      */     extends IOException
/*      */   {
/*      */     private static final long serialVersionUID = 3196188060225107702L;
/*      */     
/*      */     private final int line;
/*      */     private final int column;
/*      */     
/*      */     public ParseException(String message) {
/* 1373 */       this(-1, -1, message);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ParseException(int line, int column, String message) {
/* 1383 */       super(Integer.toString(line) + ":" + column + ": " + message);
/* 1384 */       this.line = line;
/* 1385 */       this.column = column;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getLine() {
/* 1393 */       return this.line;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getColumn() {
/* 1401 */       return this.column;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class UnknownFieldParseException
/*      */     extends ParseException
/*      */   {
/*      */     private final String unknownField;
/*      */ 
/*      */     
/*      */     public UnknownFieldParseException(String message) {
/* 1414 */       this(-1, -1, "", message);
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
/*      */     public UnknownFieldParseException(int line, int column, String unknownField, String message) {
/* 1426 */       super(line, column, message);
/* 1427 */       this.unknownField = unknownField;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getUnknownField() {
/* 1434 */       return this.unknownField;
/*      */     }
/*      */   }
/*      */   
/* 1438 */   private static final Parser PARSER = Parser.newBuilder().build();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Parser getParser() {
/* 1445 */     return PARSER;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void merge(Readable input, Message.Builder builder) throws IOException {
/* 1450 */     PARSER.merge(input, builder);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void merge(CharSequence input, Message.Builder builder) throws ParseException {
/* 1456 */     PARSER.merge(input, builder);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static <T extends Message> T parse(CharSequence input, Class<T> protoClass) throws ParseException {
/* 1466 */     Message.Builder builder = ((Message)Internal.<Message>getDefaultInstance(protoClass)).newBuilderForType();
/* 1467 */     merge(input, builder);
/*      */     
/* 1469 */     return (T)builder.build();
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
/*      */   public static void merge(Readable input, ExtensionRegistry extensionRegistry, Message.Builder builder) throws IOException {
/* 1482 */     PARSER.merge(input, extensionRegistry, builder);
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
/*      */   public static void merge(CharSequence input, ExtensionRegistry extensionRegistry, Message.Builder builder) throws ParseException {
/* 1495 */     PARSER.merge(input, extensionRegistry, builder);
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
/*      */   public static <T extends Message> T parse(CharSequence input, ExtensionRegistry extensionRegistry, Class<T> protoClass) throws ParseException {
/* 1509 */     Message.Builder builder = ((Message)Internal.<Message>getDefaultInstance(protoClass)).newBuilderForType();
/* 1510 */     merge(input, extensionRegistry, builder);
/*      */     
/* 1512 */     return (T)builder.build();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class Parser
/*      */   {
/*      */     private final TypeRegistry typeRegistry;
/*      */ 
/*      */     
/*      */     private final boolean allowUnknownFields;
/*      */ 
/*      */     
/*      */     private final boolean allowUnknownEnumValues;
/*      */ 
/*      */     
/*      */     private final boolean allowUnknownExtensions;
/*      */ 
/*      */     
/*      */     private final SingularOverwritePolicy singularOverwritePolicy;
/*      */ 
/*      */     
/*      */     private TextFormatParseInfoTree.Builder parseInfoTreeBuilder;
/*      */     
/*      */     private static final int BUFFER_SIZE = 4096;
/*      */ 
/*      */     
/*      */     public enum SingularOverwritePolicy
/*      */     {
/* 1541 */       ALLOW_SINGULAR_OVERWRITES,
/*      */       
/* 1543 */       FORBID_SINGULAR_OVERWRITES;
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
/*      */     private Parser(TypeRegistry typeRegistry, boolean allowUnknownFields, boolean allowUnknownEnumValues, boolean allowUnknownExtensions, SingularOverwritePolicy singularOverwritePolicy, TextFormatParseInfoTree.Builder parseInfoTreeBuilder) {
/* 1560 */       this.typeRegistry = typeRegistry;
/* 1561 */       this.allowUnknownFields = allowUnknownFields;
/* 1562 */       this.allowUnknownEnumValues = allowUnknownEnumValues;
/* 1563 */       this.allowUnknownExtensions = allowUnknownExtensions;
/* 1564 */       this.singularOverwritePolicy = singularOverwritePolicy;
/* 1565 */       this.parseInfoTreeBuilder = parseInfoTreeBuilder;
/*      */     }
/*      */ 
/*      */     
/*      */     public static Builder newBuilder() {
/* 1570 */       return new Builder();
/*      */     }
/*      */     
/*      */     public static class Builder
/*      */     {
/*      */       private boolean allowUnknownFields = false;
/*      */       private boolean allowUnknownEnumValues = false;
/*      */       private boolean allowUnknownExtensions = false;
/* 1578 */       private SingularOverwritePolicy singularOverwritePolicy = SingularOverwritePolicy.ALLOW_SINGULAR_OVERWRITES;
/*      */       
/* 1580 */       private TextFormatParseInfoTree.Builder parseInfoTreeBuilder = null;
/* 1581 */       private TypeRegistry typeRegistry = TypeRegistry.getEmptyTypeRegistry();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Builder setTypeRegistry(TypeRegistry typeRegistry) {
/* 1590 */         this.typeRegistry = typeRegistry;
/* 1591 */         return this;
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
/*      */       public Builder setAllowUnknownFields(boolean allowUnknownFields) {
/* 1603 */         this.allowUnknownFields = allowUnknownFields;
/* 1604 */         return this;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Builder setAllowUnknownExtensions(boolean allowUnknownExtensions) {
/* 1614 */         this.allowUnknownExtensions = allowUnknownExtensions;
/* 1615 */         return this;
/*      */       }
/*      */ 
/*      */       
/*      */       public Builder setSingularOverwritePolicy(SingularOverwritePolicy p) {
/* 1620 */         this.singularOverwritePolicy = p;
/* 1621 */         return this;
/*      */       }
/*      */       
/*      */       public Builder setParseInfoTreeBuilder(TextFormatParseInfoTree.Builder parseInfoTreeBuilder) {
/* 1625 */         this.parseInfoTreeBuilder = parseInfoTreeBuilder;
/* 1626 */         return this;
/*      */       }
/*      */       
/*      */       public Parser build() {
/* 1630 */         return new Parser(this.typeRegistry, this.allowUnknownFields, this.allowUnknownEnumValues, this.allowUnknownExtensions, this.singularOverwritePolicy, this.parseInfoTreeBuilder);
/*      */       }
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
/*      */     public void merge(Readable input, Message.Builder builder) throws IOException {
/* 1644 */       merge(input, ExtensionRegistry.getEmptyRegistry(), builder);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void merge(CharSequence input, Message.Builder builder) throws ParseException {
/* 1652 */       merge(input, ExtensionRegistry.getEmptyRegistry(), builder);
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
/*      */     public void merge(Readable input, ExtensionRegistry extensionRegistry, Message.Builder builder) throws IOException {
/* 1672 */       merge(toStringBuilder(input), extensionRegistry, builder);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static StringBuilder toStringBuilder(Readable input) throws IOException {
/* 1681 */       StringBuilder text = new StringBuilder();
/* 1682 */       CharBuffer buffer = CharBuffer.allocate(4096);
/*      */       while (true) {
/* 1684 */         int n = input.read(buffer);
/* 1685 */         if (n == -1) {
/*      */           break;
/*      */         }
/* 1688 */         buffer.flip();
/* 1689 */         text.append(buffer, 0, n);
/*      */       } 
/* 1691 */       return text;
/*      */     }
/*      */     static final class UnknownField { final String message;
/*      */       final Type type;
/*      */       
/* 1696 */       enum Type { FIELD, EXTENSION; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       UnknownField(String message, Type type) {
/* 1703 */         this.message = message;
/* 1704 */         this.type = type;
/*      */       } }
/*      */ 
/*      */     
/*      */     enum Type { FIELD, EXTENSION; }
/*      */     
/*      */     private void checkUnknownFields(List<UnknownField> unknownFields) throws ParseException {
/* 1711 */       if (unknownFields.isEmpty()) {
/*      */         return;
/*      */       }
/*      */       
/* 1715 */       StringBuilder msg = new StringBuilder("Input contains unknown fields and/or extensions:");
/* 1716 */       for (UnknownField field : unknownFields) {
/* 1717 */         msg.append('\n').append(field.message);
/*      */       }
/*      */       
/* 1720 */       if (this.allowUnknownFields) {
/* 1721 */         TextFormat.logger.warning(msg.toString());
/*      */         
/*      */         return;
/*      */       } 
/* 1725 */       int firstErrorIndex = 0;
/* 1726 */       if (this.allowUnknownExtensions) {
/* 1727 */         boolean allUnknownExtensions = true;
/* 1728 */         for (UnknownField field : unknownFields) {
/* 1729 */           if (field.type == UnknownField.Type.FIELD) {
/* 1730 */             allUnknownExtensions = false;
/*      */             break;
/*      */           } 
/* 1733 */           firstErrorIndex++;
/*      */         } 
/* 1735 */         if (allUnknownExtensions) {
/* 1736 */           TextFormat.logger.warning(msg.toString());
/*      */           
/*      */           return;
/*      */         } 
/*      */       } 
/* 1741 */       String[] lineColumn = ((UnknownField)unknownFields.get(firstErrorIndex)).message.split(":");
/* 1742 */       throw new ParseException(
/* 1743 */           Integer.parseInt(lineColumn[0]), Integer.parseInt(lineColumn[1]), msg.toString());
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
/*      */     public void merge(CharSequence input, ExtensionRegistry extensionRegistry, Message.Builder builder) throws ParseException {
/* 1755 */       Tokenizer tokenizer = new Tokenizer(input);
/* 1756 */       MessageReflection.BuilderAdapter target = new MessageReflection.BuilderAdapter(builder);
/*      */       
/* 1758 */       List<UnknownField> unknownFields = new ArrayList<>();
/*      */       
/* 1760 */       while (!tokenizer.atEnd()) {
/* 1761 */         mergeField(tokenizer, extensionRegistry, target, unknownFields);
/*      */       }
/*      */       
/* 1764 */       checkUnknownFields(unknownFields);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void mergeField(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget target, List<UnknownField> unknownFields) throws ParseException {
/* 1775 */       mergeField(tokenizer, extensionRegistry, target, this.parseInfoTreeBuilder, unknownFields);
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
/*      */     private void mergeField(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget target, TextFormatParseInfoTree.Builder parseTreeBuilder, List<UnknownField> unknownFields) throws ParseException {
/* 1791 */       Descriptors.FieldDescriptor field = null;
/* 1792 */       int startLine = tokenizer.getLine();
/* 1793 */       int startColumn = tokenizer.getColumn();
/* 1794 */       Descriptors.Descriptor type = target.getDescriptorForType();
/* 1795 */       ExtensionRegistry.ExtensionInfo extension = null;
/*      */       
/* 1797 */       if ("google.protobuf.Any".equals(type.getFullName()) && tokenizer.tryConsume("[")) {
/* 1798 */         mergeAnyFieldValue(tokenizer, extensionRegistry, target, parseTreeBuilder, unknownFields, type);
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/* 1803 */       if (tokenizer.tryConsume("[")) {
/*      */         
/* 1805 */         StringBuilder name = new StringBuilder(tokenizer.consumeIdentifier());
/* 1806 */         while (tokenizer.tryConsume(".")) {
/* 1807 */           name.append('.');
/* 1808 */           name.append(tokenizer.consumeIdentifier());
/*      */         } 
/*      */         
/* 1811 */         extension = target.findExtensionByName(extensionRegistry, name.toString());
/*      */         
/* 1813 */         if (extension == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1819 */           String message = (tokenizer.getPreviousLine() + 1) + ":" + (tokenizer.getPreviousColumn() + 1) + ":\t" + type.getFullName() + ".[" + name + "]";
/*      */ 
/*      */ 
/*      */           
/* 1823 */           unknownFields.add(new UnknownField(message, UnknownField.Type.EXTENSION));
/*      */         } else {
/* 1825 */           if (extension.descriptor.getContainingType() != type) {
/* 1826 */             throw tokenizer.parseExceptionPreviousToken("Extension \"" + name + "\" does not extend message type \"" + type
/*      */ 
/*      */ 
/*      */                 
/* 1830 */                 .getFullName() + "\".");
/*      */           }
/*      */           
/* 1833 */           field = extension.descriptor;
/*      */         } 
/*      */         
/* 1836 */         tokenizer.consume("]");
/*      */       } else {
/* 1838 */         String name = tokenizer.consumeIdentifier();
/* 1839 */         field = type.findFieldByName(name);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1844 */         if (field == null) {
/*      */ 
/*      */           
/* 1847 */           String lowerName = name.toLowerCase(Locale.US);
/* 1848 */           field = type.findFieldByName(lowerName);
/*      */           
/* 1850 */           if (field != null && field.getType() != Descriptors.FieldDescriptor.Type.GROUP) {
/* 1851 */             field = null;
/*      */           }
/*      */         } 
/*      */         
/* 1855 */         if (field != null && field
/* 1856 */           .getType() == Descriptors.FieldDescriptor.Type.GROUP && 
/* 1857 */           !field.getMessageType().getName().equals(name)) {
/* 1858 */           field = null;
/*      */         }
/*      */         
/* 1861 */         if (field == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1866 */           String message = (tokenizer.getPreviousLine() + 1) + ":" + (tokenizer.getPreviousColumn() + 1) + ":\t" + type.getFullName() + "." + name;
/*      */ 
/*      */           
/* 1869 */           unknownFields.add(new UnknownField(message, UnknownField.Type.FIELD));
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1874 */       if (field == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1881 */         if (tokenizer.tryConsume(":") && !tokenizer.lookingAt("{") && !tokenizer.lookingAt("<")) {
/* 1882 */           skipFieldValue(tokenizer);
/*      */         } else {
/* 1884 */           skipFieldMessage(tokenizer);
/*      */         } 
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/* 1890 */       if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
/* 1891 */         tokenizer.tryConsume(":");
/* 1892 */         if (parseTreeBuilder != null)
/*      */         {
/* 1894 */           TextFormatParseInfoTree.Builder childParseTreeBuilder = parseTreeBuilder.getBuilderForSubMessageField(field);
/* 1895 */           consumeFieldValues(tokenizer, extensionRegistry, target, field, extension, childParseTreeBuilder, unknownFields);
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/*      */           
/* 1904 */           consumeFieldValues(tokenizer, extensionRegistry, target, field, extension, parseTreeBuilder, unknownFields);
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 1914 */         tokenizer.consume(":");
/* 1915 */         consumeFieldValues(tokenizer, extensionRegistry, target, field, extension, parseTreeBuilder, unknownFields);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1925 */       if (parseTreeBuilder != null) {
/* 1926 */         parseTreeBuilder.setLocation(field, TextFormatParseLocation.create(startLine, startColumn));
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1931 */       if (!tokenizer.tryConsume(";")) {
/* 1932 */         tokenizer.tryConsume(",");
/*      */       }
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
/*      */     private void consumeFieldValues(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget target, Descriptors.FieldDescriptor field, ExtensionRegistry.ExtensionInfo extension, TextFormatParseInfoTree.Builder parseTreeBuilder, List<UnknownField> unknownFields) throws ParseException {
/* 1950 */       if (field.isRepeated() && tokenizer.tryConsume("[")) {
/* 1951 */         if (!tokenizer.tryConsume("]")) {
/*      */           while (true) {
/* 1953 */             consumeFieldValue(tokenizer, extensionRegistry, target, field, extension, parseTreeBuilder, unknownFields);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1961 */             if (tokenizer.tryConsume("]")) {
/*      */               break;
/*      */             }
/*      */             
/* 1965 */             tokenizer.consume(",");
/*      */           } 
/*      */         }
/*      */       } else {
/* 1969 */         consumeFieldValue(tokenizer, extensionRegistry, target, field, extension, parseTreeBuilder, unknownFields);
/*      */       } 
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
/*      */     private void consumeFieldValue(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget target, Descriptors.FieldDescriptor field, ExtensionRegistry.ExtensionInfo extension, TextFormatParseInfoTree.Builder parseTreeBuilder, List<UnknownField> unknownFields) throws ParseException {
/* 1990 */       if (this.singularOverwritePolicy == SingularOverwritePolicy.FORBID_SINGULAR_OVERWRITES && 
/* 1991 */         !field.isRepeated()) {
/* 1992 */         if (target.hasField(field))
/* 1993 */           throw tokenizer.parseExceptionPreviousToken("Non-repeated field \"" + field
/* 1994 */               .getFullName() + "\" cannot be overwritten."); 
/* 1995 */         if (field.getContainingOneof() != null && target
/* 1996 */           .hasOneof(field.getContainingOneof())) {
/* 1997 */           Descriptors.OneofDescriptor oneof = field.getContainingOneof();
/* 1998 */           throw tokenizer.parseExceptionPreviousToken("Field \"" + field
/*      */               
/* 2000 */               .getFullName() + "\" is specified along with field \"" + target
/*      */               
/* 2002 */               .getOneofFieldDescriptor(oneof).getFullName() + "\", another member of oneof \"" + oneof
/*      */               
/* 2004 */               .getName() + "\".");
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 2009 */       Object value = null;
/*      */       
/* 2011 */       if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
/*      */         String endToken;
/* 2013 */         if (tokenizer.tryConsume("<")) {
/* 2014 */           endToken = ">";
/*      */         } else {
/* 2016 */           tokenizer.consume("{");
/* 2017 */           endToken = "}";
/*      */         } 
/*      */ 
/*      */         
/* 2021 */         if (field.getMessageType().getFullName().equals("google.protobuf.Any") && tokenizer
/* 2022 */           .tryConsume("[")) {
/*      */ 
/*      */           
/* 2025 */           Message anyBuilder = DynamicMessage.getDefaultInstance(field.getMessageType());
/* 2026 */           MessageReflection.MergeTarget anyField = target.newMergeTargetForField(field, anyBuilder);
/* 2027 */           mergeAnyFieldValue(tokenizer, extensionRegistry, anyField, parseTreeBuilder, unknownFields, field
/* 2028 */               .getMessageType());
/* 2029 */           value = anyField.finish();
/* 2030 */           tokenizer.consume(endToken);
/*      */         } else {
/* 2032 */           Message defaultInstance = (extension == null) ? null : extension.defaultInstance;
/*      */           
/* 2034 */           MessageReflection.MergeTarget subField = target.newMergeTargetForField(field, defaultInstance);
/*      */           
/* 2036 */           while (!tokenizer.tryConsume(endToken)) {
/* 2037 */             if (tokenizer.atEnd()) {
/* 2038 */               throw tokenizer.parseException("Expected \"" + endToken + "\".");
/*      */             }
/* 2040 */             mergeField(tokenizer, extensionRegistry, subField, parseTreeBuilder, unknownFields);
/*      */           } 
/*      */           
/* 2043 */           value = subField.finish();
/*      */         } 
/*      */       } else {
/*      */         Descriptors.EnumDescriptor enumType; String id;
/* 2047 */         switch (field.getType()) {
/*      */           case INT32:
/*      */           case SINT32:
/*      */           case SFIXED32:
/* 2051 */             value = Integer.valueOf(tokenizer.consumeInt32());
/*      */             break;
/*      */           
/*      */           case INT64:
/*      */           case SINT64:
/*      */           case SFIXED64:
/* 2057 */             value = Long.valueOf(tokenizer.consumeInt64());
/*      */             break;
/*      */           
/*      */           case UINT32:
/*      */           case FIXED32:
/* 2062 */             value = Integer.valueOf(tokenizer.consumeUInt32());
/*      */             break;
/*      */           
/*      */           case UINT64:
/*      */           case FIXED64:
/* 2067 */             value = Long.valueOf(tokenizer.consumeUInt64());
/*      */             break;
/*      */           
/*      */           case FLOAT:
/* 2071 */             value = Float.valueOf(tokenizer.consumeFloat());
/*      */             break;
/*      */           
/*      */           case DOUBLE:
/* 2075 */             value = Double.valueOf(tokenizer.consumeDouble());
/*      */             break;
/*      */           
/*      */           case BOOL:
/* 2079 */             value = Boolean.valueOf(tokenizer.consumeBoolean());
/*      */             break;
/*      */           
/*      */           case STRING:
/* 2083 */             value = tokenizer.consumeString();
/*      */             break;
/*      */           
/*      */           case BYTES:
/* 2087 */             value = tokenizer.consumeByteString();
/*      */             break;
/*      */           
/*      */           case ENUM:
/* 2091 */             enumType = field.getEnumType();
/*      */             
/* 2093 */             if (tokenizer.lookingAtInteger()) {
/* 2094 */               int number = tokenizer.consumeInt32();
/* 2095 */               value = enumType.findValueByNumber(number);
/* 2096 */               if (value == null) {
/*      */ 
/*      */                 
/* 2099 */                 String unknownValueMsg = "Enum type \"" + enumType.getFullName() + "\" has no value with number " + number + '.';
/*      */ 
/*      */ 
/*      */                 
/* 2103 */                 if (this.allowUnknownEnumValues) {
/* 2104 */                   TextFormat.logger.warning(unknownValueMsg);
/*      */                   return;
/*      */                 } 
/* 2107 */                 throw tokenizer.parseExceptionPreviousToken("Enum type \"" + enumType
/*      */                     
/* 2109 */                     .getFullName() + "\" has no value with number " + number + '.');
/*      */               } 
/*      */ 
/*      */               
/*      */               break;
/*      */             } 
/*      */             
/* 2116 */             id = tokenizer.consumeIdentifier();
/* 2117 */             value = enumType.findValueByName(id);
/* 2118 */             if (value == null) {
/*      */ 
/*      */               
/* 2121 */               String unknownValueMsg = "Enum type \"" + enumType.getFullName() + "\" has no value named \"" + id + "\".";
/*      */ 
/*      */ 
/*      */               
/* 2125 */               if (this.allowUnknownEnumValues) {
/* 2126 */                 TextFormat.logger.warning(unknownValueMsg);
/*      */                 return;
/*      */               } 
/* 2129 */               throw tokenizer.parseExceptionPreviousToken(unknownValueMsg);
/*      */             } 
/*      */             break;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case MESSAGE:
/*      */           case GROUP:
/* 2138 */             throw new RuntimeException("Can't get here.");
/*      */         } 
/*      */       
/*      */       } 
/* 2142 */       if (field.isRepeated()) {
/*      */ 
/*      */         
/* 2145 */         target.addRepeatedField(field, value);
/*      */       } else {
/* 2147 */         target.setField(field, value);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void mergeAnyFieldValue(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget target, TextFormatParseInfoTree.Builder parseTreeBuilder, List<UnknownField> unknownFields, Descriptors.Descriptor anyDescriptor) throws ParseException {
/*      */       String anyEndToken;
/* 2160 */       StringBuilder typeUrlBuilder = new StringBuilder();
/*      */       
/*      */       while (true) {
/* 2163 */         typeUrlBuilder.append(tokenizer.consumeIdentifier());
/* 2164 */         if (tokenizer.tryConsume("]")) {
/*      */           break;
/*      */         }
/* 2167 */         if (tokenizer.tryConsume("/")) {
/* 2168 */           typeUrlBuilder.append("/"); continue;
/* 2169 */         }  if (tokenizer.tryConsume(".")) {
/* 2170 */           typeUrlBuilder.append("."); continue;
/*      */         } 
/* 2172 */         throw tokenizer.parseExceptionPreviousToken("Expected a valid type URL.");
/*      */       } 
/*      */       
/* 2175 */       tokenizer.tryConsume(":");
/*      */       
/* 2177 */       if (tokenizer.tryConsume("<")) {
/* 2178 */         anyEndToken = ">";
/*      */       } else {
/* 2180 */         tokenizer.consume("{");
/* 2181 */         anyEndToken = "}";
/*      */       } 
/* 2183 */       String typeUrl = typeUrlBuilder.toString();
/* 2184 */       Descriptors.Descriptor contentType = null;
/*      */       try {
/* 2186 */         contentType = this.typeRegistry.getDescriptorForTypeUrl(typeUrl);
/* 2187 */       } catch (InvalidProtocolBufferException e) {
/* 2188 */         throw tokenizer.parseException("Invalid valid type URL. Found: " + typeUrl);
/*      */       } 
/* 2190 */       if (contentType == null) {
/* 2191 */         throw tokenizer.parseException("Unable to parse Any of type: " + typeUrl + ". Please make sure that the TypeRegistry contains the descriptors for the given types.");
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2198 */       Message.Builder contentBuilder = DynamicMessage.getDefaultInstance(contentType).newBuilderForType();
/* 2199 */       MessageReflection.BuilderAdapter contentTarget = new MessageReflection.BuilderAdapter(contentBuilder);
/*      */       
/* 2201 */       while (!tokenizer.tryConsume(anyEndToken)) {
/* 2202 */         mergeField(tokenizer, extensionRegistry, contentTarget, parseTreeBuilder, unknownFields);
/*      */       }
/*      */       
/* 2205 */       target.setField(anyDescriptor.findFieldByName("type_url"), typeUrlBuilder.toString());
/* 2206 */       target.setField(anyDescriptor
/* 2207 */           .findFieldByName("value"), contentBuilder.build().toByteString());
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
/*      */     private static void skipField(Tokenizer tokenizer) throws ParseException {
/*      */       // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: ldc '['
/*      */       //   3: invokevirtual tryConsume : (Ljava/lang/String;)Z
/*      */       //   6: ifeq -> 32
/*      */       //   9: aload_0
/*      */       //   10: invokevirtual consumeIdentifier : ()Ljava/lang/String;
/*      */       //   13: pop
/*      */       //   14: aload_0
/*      */       //   15: ldc '.'
/*      */       //   17: invokevirtual tryConsume : (Ljava/lang/String;)Z
/*      */       //   20: ifne -> 9
/*      */       //   23: aload_0
/*      */       //   24: ldc ']'
/*      */       //   26: invokevirtual consume : (Ljava/lang/String;)V
/*      */       //   29: goto -> 37
/*      */       //   32: aload_0
/*      */       //   33: invokevirtual consumeIdentifier : ()Ljava/lang/String;
/*      */       //   36: pop
/*      */       //   37: aload_0
/*      */       //   38: ldc ':'
/*      */       //   40: invokevirtual tryConsume : (Ljava/lang/String;)Z
/*      */       //   43: ifeq -> 71
/*      */       //   46: aload_0
/*      */       //   47: ldc '<'
/*      */       //   49: invokevirtual lookingAt : (Ljava/lang/String;)Z
/*      */       //   52: ifne -> 71
/*      */       //   55: aload_0
/*      */       //   56: ldc '{'
/*      */       //   58: invokevirtual lookingAt : (Ljava/lang/String;)Z
/*      */       //   61: ifne -> 71
/*      */       //   64: aload_0
/*      */       //   65: invokestatic skipFieldValue : (Lcom/google/protobuf/TextFormat$Tokenizer;)V
/*      */       //   68: goto -> 75
/*      */       //   71: aload_0
/*      */       //   72: invokestatic skipFieldMessage : (Lcom/google/protobuf/TextFormat$Tokenizer;)V
/*      */       //   75: aload_0
/*      */       //   76: ldc ';'
/*      */       //   78: invokevirtual tryConsume : (Ljava/lang/String;)Z
/*      */       //   81: ifne -> 91
/*      */       //   84: aload_0
/*      */       //   85: ldc ','
/*      */       //   87: invokevirtual tryConsume : (Ljava/lang/String;)Z
/*      */       //   90: pop
/*      */       //   91: return
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #2212	-> 0
/*      */       //   #2215	-> 9
/*      */       //   #2216	-> 14
/*      */       //   #2217	-> 23
/*      */       //   #2219	-> 32
/*      */       //   #2228	-> 37
/*      */       //   #2229	-> 64
/*      */       //   #2231	-> 71
/*      */       //   #2235	-> 75
/*      */       //   #2236	-> 84
/*      */       //   #2238	-> 91
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   0	92	0	tokenizer	Lcom/google/protobuf/TextFormat$Tokenizer;
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
/*      */     private static void skipFieldMessage(Tokenizer tokenizer) throws ParseException {
/*      */       String delimiter;
/* 2245 */       if (tokenizer.tryConsume("<")) {
/* 2246 */         delimiter = ">";
/*      */       } else {
/* 2248 */         tokenizer.consume("{");
/* 2249 */         delimiter = "}";
/*      */       } 
/* 2251 */       while (!tokenizer.lookingAt(">") && !tokenizer.lookingAt("}")) {
/* 2252 */         skipField(tokenizer);
/*      */       }
/* 2254 */       tokenizer.consume(delimiter);
/*      */     }
/*      */     
/*      */     private static void skipFieldValue(Tokenizer tokenizer) throws ParseException
/*      */     {
/* 2259 */       if (tokenizer.tryConsumeString()) {
/* 2260 */         while (tokenizer.tryConsumeString());
/*      */         return;
/*      */       } 
/* 2263 */       if (!tokenizer.tryConsumeIdentifier() && 
/* 2264 */         !tokenizer.tryConsumeInt64() && 
/* 2265 */         !tokenizer.tryConsumeUInt64() && 
/* 2266 */         !tokenizer.tryConsumeDouble() && 
/* 2267 */         !tokenizer.tryConsumeFloat()) {
/* 2268 */         throw tokenizer.parseException("Invalid field value: " + tokenizer.currentToken);
/*      */       }
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String escapeBytes(ByteString input) {
/* 2286 */     return TextFormatEscaper.escapeBytes(input);
/*      */   }
/*      */   public static class Builder {
/*      */     private boolean allowUnknownFields;
/*      */     private boolean allowUnknownEnumValues;
/* 2291 */     private boolean allowUnknownExtensions; private Parser.SingularOverwritePolicy singularOverwritePolicy; private TextFormatParseInfoTree.Builder parseInfoTreeBuilder; private TypeRegistry typeRegistry; public Builder() { this.allowUnknownFields = false; this.allowUnknownEnumValues = false; this.allowUnknownExtensions = false; this.singularOverwritePolicy = Parser.SingularOverwritePolicy.ALLOW_SINGULAR_OVERWRITES; this.parseInfoTreeBuilder = null; this.typeRegistry = TypeRegistry.getEmptyTypeRegistry(); } public Builder setTypeRegistry(TypeRegistry typeRegistry) { this.typeRegistry = typeRegistry; return this; } public Builder setAllowUnknownFields(boolean allowUnknownFields) { this.allowUnknownFields = allowUnknownFields; return this; } public Builder setAllowUnknownExtensions(boolean allowUnknownExtensions) { this.allowUnknownExtensions = allowUnknownExtensions; return this; } public Builder setSingularOverwritePolicy(Parser.SingularOverwritePolicy p) { this.singularOverwritePolicy = p; return this; } public Builder setParseInfoTreeBuilder(TextFormatParseInfoTree.Builder parseInfoTreeBuilder) { this.parseInfoTreeBuilder = parseInfoTreeBuilder; return this; } public Parser build() { return new Parser(this.typeRegistry, this.allowUnknownFields, this.allowUnknownEnumValues, this.allowUnknownExtensions, this.singularOverwritePolicy, this.parseInfoTreeBuilder); } } public static String escapeBytes(byte[] input) { return TextFormatEscaper.escapeBytes(input); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ByteString unescapeBytes(CharSequence charString) throws InvalidEscapeSequenceException {
/* 2301 */     ByteString input = ByteString.copyFromUtf8(charString.toString());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2309 */     byte[] result = new byte[input.size()];
/* 2310 */     int pos = 0;
/* 2311 */     for (int i = 0; i < input.size(); i++) {
/* 2312 */       byte c = input.byteAt(i);
/* 2313 */       if (c == 92) {
/* 2314 */         if (i + 1 < input.size()) {
/* 2315 */           i++;
/* 2316 */           c = input.byteAt(i);
/* 2317 */           if (isOctal(c)) {
/*      */             
/* 2319 */             int code = digitValue(c);
/* 2320 */             if (i + 1 < input.size() && isOctal(input.byteAt(i + 1))) {
/* 2321 */               i++;
/* 2322 */               code = code * 8 + digitValue(input.byteAt(i));
/*      */             } 
/* 2324 */             if (i + 1 < input.size() && isOctal(input.byteAt(i + 1))) {
/* 2325 */               i++;
/* 2326 */               code = code * 8 + digitValue(input.byteAt(i));
/*      */             } 
/*      */             
/* 2329 */             result[pos++] = (byte)code;
/*      */           } else {
/* 2331 */             int code; int codepoint; int offset; Character.UnicodeBlock unicodeBlock; int[] codepoints; byte[] chUtf8; switch (c) {
/*      */               case 97:
/* 2333 */                 result[pos++] = 7;
/*      */                 break;
/*      */               case 98:
/* 2336 */                 result[pos++] = 8;
/*      */                 break;
/*      */               case 102:
/* 2339 */                 result[pos++] = 12;
/*      */                 break;
/*      */               case 110:
/* 2342 */                 result[pos++] = 10;
/*      */                 break;
/*      */               case 114:
/* 2345 */                 result[pos++] = 13;
/*      */                 break;
/*      */               case 116:
/* 2348 */                 result[pos++] = 9;
/*      */                 break;
/*      */               case 118:
/* 2351 */                 result[pos++] = 11;
/*      */                 break;
/*      */               case 92:
/* 2354 */                 result[pos++] = 92;
/*      */                 break;
/*      */               case 39:
/* 2357 */                 result[pos++] = 39;
/*      */                 break;
/*      */               case 34:
/* 2360 */                 result[pos++] = 34;
/*      */                 break;
/*      */ 
/*      */               
/*      */               case 120:
/* 2365 */                 code = 0;
/* 2366 */                 if (i + 1 < input.size() && isHex(input.byteAt(i + 1))) {
/* 2367 */                   i++;
/* 2368 */                   code = digitValue(input.byteAt(i));
/*      */                 } else {
/* 2370 */                   throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\x' with no digits");
/*      */                 } 
/*      */                 
/* 2373 */                 if (i + 1 < input.size() && isHex(input.byteAt(i + 1))) {
/* 2374 */                   i++;
/* 2375 */                   code = code * 16 + digitValue(input.byteAt(i));
/*      */                 } 
/* 2377 */                 result[pos++] = (byte)code;
/*      */                 break;
/*      */ 
/*      */               
/*      */               case 117:
/* 2382 */                 i++;
/* 2383 */                 if (i + 3 < input.size() && 
/* 2384 */                   isHex(input.byteAt(i)) && 
/* 2385 */                   isHex(input.byteAt(i + 1)) && 
/* 2386 */                   isHex(input.byteAt(i + 2)) && 
/* 2387 */                   isHex(input.byteAt(i + 3))) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                   
/* 2393 */                   char ch = (char)(digitValue(input.byteAt(i)) << 12 | digitValue(input.byteAt(i + 1)) << 8 | digitValue(input.byteAt(i + 2)) << 4 | digitValue(input.byteAt(i + 3)));
/* 2394 */                   if (Character.isSurrogate(ch)) {
/* 2395 */                     throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\u' refers to a surrogate");
/*      */                   }
/*      */                   
/* 2398 */                   byte[] arrayOfByte = Character.toString(ch).getBytes(StandardCharsets.UTF_8);
/* 2399 */                   System.arraycopy(arrayOfByte, 0, result, pos, arrayOfByte.length);
/* 2400 */                   pos += arrayOfByte.length;
/* 2401 */                   i += 3; break;
/*      */                 } 
/* 2403 */                 throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\u' with too few hex chars");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*      */               case 85:
/* 2410 */                 i++;
/* 2411 */                 if (i + 7 >= input.size()) {
/* 2412 */                   throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\U' with too few hex chars");
/*      */                 }
/*      */                 
/* 2415 */                 codepoint = 0;
/* 2416 */                 for (offset = i; offset < i + 8; offset++) {
/* 2417 */                   byte b = input.byteAt(offset);
/* 2418 */                   if (!isHex(b)) {
/* 2419 */                     throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\U' with too few hex chars");
/*      */                   }
/*      */                   
/* 2422 */                   codepoint = codepoint << 4 | digitValue(b);
/*      */                 } 
/* 2424 */                 if (!Character.isValidCodePoint(codepoint)) {
/* 2425 */                   throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\U" + input
/*      */                       
/* 2427 */                       .substring(i, i + 8).toStringUtf8() + "' is not a valid code point value");
/*      */                 }
/*      */                 
/* 2430 */                 unicodeBlock = Character.UnicodeBlock.of(codepoint);
/* 2431 */                 if (unicodeBlock.equals(Character.UnicodeBlock.LOW_SURROGATES) || unicodeBlock
/* 2432 */                   .equals(Character.UnicodeBlock.HIGH_SURROGATES) || unicodeBlock
/* 2433 */                   .equals(Character.UnicodeBlock.HIGH_PRIVATE_USE_SURROGATES)) {
/* 2434 */                   throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\U" + input
/*      */                       
/* 2436 */                       .substring(i, i + 8).toStringUtf8() + "' refers to a surrogate code unit");
/*      */                 }
/*      */                 
/* 2439 */                 codepoints = new int[1];
/* 2440 */                 codepoints[0] = codepoint;
/* 2441 */                 chUtf8 = (new String(codepoints, 0, 1)).getBytes(StandardCharsets.UTF_8);
/* 2442 */                 System.arraycopy(chUtf8, 0, result, pos, chUtf8.length);
/* 2443 */                 pos += chUtf8.length;
/* 2444 */                 i += 7;
/*      */                 break;
/*      */               
/*      */               default:
/* 2448 */                 throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\" + (char)c + '\'');
/*      */             } 
/*      */           
/*      */           } 
/*      */         } else {
/* 2453 */           throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\' at end of string.");
/*      */         } 
/*      */       } else {
/*      */         
/* 2457 */         result[pos++] = c;
/*      */       } 
/*      */     } 
/*      */     
/* 2461 */     return (result.length == pos) ? 
/* 2462 */       ByteString.wrap(result) : 
/* 2463 */       ByteString.copyFrom(result, 0, pos);
/*      */   }
/*      */ 
/*      */   
/*      */   public static class InvalidEscapeSequenceException
/*      */     extends IOException
/*      */   {
/*      */     private static final long serialVersionUID = -8164033650142593304L;
/*      */ 
/*      */     
/*      */     InvalidEscapeSequenceException(String description) {
/* 2474 */       super(description);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static String escapeText(String input) {
/* 2484 */     return escapeBytes(ByteString.copyFromUtf8(input));
/*      */   }
/*      */ 
/*      */   
/*      */   public static String escapeDoubleQuotesAndBackslashes(String input) {
/* 2489 */     return TextFormatEscaper.escapeDoubleQuotesAndBackslashes(input);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static String unescapeText(String input) throws InvalidEscapeSequenceException {
/* 2497 */     return unescapeBytes(input).toStringUtf8();
/*      */   }
/*      */ 
/*      */   
/*      */   private static boolean isOctal(byte c) {
/* 2502 */     return (48 <= c && c <= 55);
/*      */   }
/*      */ 
/*      */   
/*      */   private static boolean isHex(byte c) {
/* 2507 */     return ((48 <= c && c <= 57) || (97 <= c && c <= 102) || (65 <= c && c <= 70));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int digitValue(byte c) {
/* 2515 */     if (48 <= c && c <= 57)
/* 2516 */       return c - 48; 
/* 2517 */     if (97 <= c && c <= 122) {
/* 2518 */       return c - 97 + 10;
/*      */     }
/* 2520 */     return c - 65 + 10;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static int parseInt32(String text) throws NumberFormatException {
/* 2530 */     return (int)parseInteger(text, true, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static int parseUInt32(String text) throws NumberFormatException {
/* 2540 */     return (int)parseInteger(text, false, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static long parseInt64(String text) throws NumberFormatException {
/* 2549 */     return parseInteger(text, true, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static long parseUInt64(String text) throws NumberFormatException {
/* 2559 */     return parseInteger(text, false, true);
/*      */   }
/*      */ 
/*      */   
/*      */   private static long parseInteger(String text, boolean isSigned, boolean isLong) throws NumberFormatException {
/* 2564 */     int pos = 0;
/*      */     
/* 2566 */     boolean negative = false;
/* 2567 */     if (text.startsWith("-", pos)) {
/* 2568 */       if (!isSigned) {
/* 2569 */         throw new NumberFormatException("Number must be positive: " + text);
/*      */       }
/* 2571 */       pos++;
/* 2572 */       negative = true;
/*      */     } 
/*      */     
/* 2575 */     int radix = 10;
/* 2576 */     if (text.startsWith("0x", pos)) {
/* 2577 */       pos += 2;
/* 2578 */       radix = 16;
/* 2579 */     } else if (text.startsWith("0", pos)) {
/* 2580 */       radix = 8;
/*      */     } 
/*      */     
/* 2583 */     String numberText = text.substring(pos);
/*      */     
/* 2585 */     long result = 0L;
/* 2586 */     if (numberText.length() < 16) {
/*      */       
/* 2588 */       result = Long.parseLong(numberText, radix);
/* 2589 */       if (negative) {
/* 2590 */         result = -result;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2596 */       if (!isLong) {
/* 2597 */         if (isSigned) {
/* 2598 */           if (result > 2147483647L || result < -2147483648L) {
/* 2599 */             throw new NumberFormatException("Number out of range for 32-bit signed integer: " + text);
/*      */           
/*      */           }
/*      */         }
/* 2603 */         else if (result >= 4294967296L || result < 0L) {
/* 2604 */           throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + text);
/*      */         }
/*      */       
/*      */       }
/*      */     } else {
/*      */       
/* 2610 */       BigInteger bigValue = new BigInteger(numberText, radix);
/* 2611 */       if (negative) {
/* 2612 */         bigValue = bigValue.negate();
/*      */       }
/*      */ 
/*      */       
/* 2616 */       if (!isLong) {
/* 2617 */         if (isSigned) {
/* 2618 */           if (bigValue.bitLength() > 31) {
/* 2619 */             throw new NumberFormatException("Number out of range for 32-bit signed integer: " + text);
/*      */           
/*      */           }
/*      */         }
/* 2623 */         else if (bigValue.bitLength() > 32) {
/* 2624 */           throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + text);
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 2629 */       else if (isSigned) {
/* 2630 */         if (bigValue.bitLength() > 63) {
/* 2631 */           throw new NumberFormatException("Number out of range for 64-bit signed integer: " + text);
/*      */         
/*      */         }
/*      */       }
/* 2635 */       else if (bigValue.bitLength() > 64) {
/* 2636 */         throw new NumberFormatException("Number out of range for 64-bit unsigned integer: " + text);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2642 */       result = bigValue.longValue();
/*      */     } 
/*      */     
/* 2645 */     return result;
/*      */   }
/*      */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\TextFormat.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */