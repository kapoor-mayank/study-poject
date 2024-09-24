/*      */ package com.google.protobuf;
/*      */ 
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class CodedInputStream
/*      */ {
/*      */   private static final int DEFAULT_BUFFER_SIZE = 4096;
/*      */   private static final int DEFAULT_RECURSION_LIMIT = 100;
/*      */   private static final int DEFAULT_SIZE_LIMIT = 2147483647;
/*      */   int recursionDepth;
/*   71 */   int recursionLimit = 100;
/*      */ 
/*      */   
/*   74 */   int sizeLimit = Integer.MAX_VALUE;
/*      */   
/*      */   CodedInputStreamReader wrapper;
/*      */   
/*      */   private boolean shouldDiscardUnknownFields;
/*      */   
/*      */   public static CodedInputStream newInstance(InputStream input) {
/*   81 */     return newInstance(input, 4096);
/*      */   }
/*      */ 
/*      */   
/*      */   public static CodedInputStream newInstance(InputStream input, int bufferSize) {
/*   86 */     if (bufferSize <= 0) {
/*   87 */       throw new IllegalArgumentException("bufferSize must be > 0");
/*      */     }
/*   89 */     if (input == null)
/*      */     {
/*   91 */       return newInstance(Internal.EMPTY_BYTE_ARRAY);
/*      */     }
/*   93 */     return new StreamDecoder(input, bufferSize);
/*      */   }
/*      */ 
/*      */   
/*      */   public static CodedInputStream newInstance(Iterable<ByteBuffer> input) {
/*   98 */     if (!UnsafeDirectNioDecoder.isSupported()) {
/*   99 */       return newInstance(new IterableByteBufferInputStream(input));
/*      */     }
/*  101 */     return newInstance(input, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static CodedInputStream newInstance(Iterable<ByteBuffer> bufs, boolean bufferIsImmutable) {
/*  112 */     int flag = 0;
/*      */     
/*  114 */     int totalSize = 0;
/*  115 */     for (ByteBuffer buf : bufs) {
/*  116 */       totalSize += buf.remaining();
/*  117 */       if (buf.hasArray()) {
/*  118 */         flag |= 0x1; continue;
/*  119 */       }  if (buf.isDirect()) {
/*  120 */         flag |= 0x2; continue;
/*      */       } 
/*  122 */       flag |= 0x4;
/*      */     } 
/*      */     
/*  125 */     if (flag == 2) {
/*  126 */       return new IterableDirectByteBufferDecoder(bufs, totalSize, bufferIsImmutable);
/*      */     }
/*      */     
/*  129 */     return newInstance(new IterableByteBufferInputStream(bufs));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static CodedInputStream newInstance(byte[] buf) {
/*  135 */     return newInstance(buf, 0, buf.length);
/*      */   }
/*      */ 
/*      */   
/*      */   public static CodedInputStream newInstance(byte[] buf, int off, int len) {
/*  140 */     return newInstance(buf, off, len, false);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static CodedInputStream newInstance(byte[] buf, int off, int len, boolean bufferIsImmutable) {
/*  146 */     ArrayDecoder result = new ArrayDecoder(buf, off, len, bufferIsImmutable);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  153 */       result.pushLimit(len);
/*  154 */     } catch (InvalidProtocolBufferException ex) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  162 */       throw new IllegalArgumentException(ex);
/*      */     } 
/*  164 */     return result;
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
/*      */   public static CodedInputStream newInstance(ByteBuffer buf) {
/*  176 */     return newInstance(buf, false);
/*      */   }
/*      */ 
/*      */   
/*      */   static CodedInputStream newInstance(ByteBuffer buf, boolean bufferIsImmutable) {
/*  181 */     if (buf.hasArray()) {
/*  182 */       return newInstance(buf
/*  183 */           .array(), buf.arrayOffset() + buf.position(), buf.remaining(), bufferIsImmutable);
/*      */     }
/*      */     
/*  186 */     if (buf.isDirect() && UnsafeDirectNioDecoder.isSupported()) {
/*  187 */       return new UnsafeDirectNioDecoder(buf, bufferIsImmutable);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  193 */     byte[] buffer = new byte[buf.remaining()];
/*  194 */     buf.duplicate().get(buffer);
/*  195 */     return newInstance(buffer, 0, buffer.length, true);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int setRecursionLimit(int limit) {
/*  389 */     if (limit < 0) {
/*  390 */       throw new IllegalArgumentException("Recursion limit cannot be negative: " + limit);
/*      */     }
/*  392 */     int oldLimit = this.recursionLimit;
/*  393 */     this.recursionLimit = limit;
/*  394 */     return oldLimit;
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
/*      */   public final int setSizeLimit(int limit) {
/*  412 */     if (limit < 0) {
/*  413 */       throw new IllegalArgumentException("Size limit cannot be negative: " + limit);
/*      */     }
/*  415 */     int oldLimit = this.sizeLimit;
/*  416 */     this.sizeLimit = limit;
/*  417 */     return oldLimit;
/*      */   }
/*      */   private CodedInputStream() {
/*  420 */     this.shouldDiscardUnknownFields = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void discardUnknownFields() {
/*  431 */     this.shouldDiscardUnknownFields = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void unsetDiscardUnknownFields() {
/*  439 */     this.shouldDiscardUnknownFields = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final boolean shouldDiscardUnknownFields() {
/*  447 */     return this.shouldDiscardUnknownFields;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int decodeZigZag32(int n) {
/*  530 */     return n >>> 1 ^ -(n & 0x1);
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
/*      */   public static long decodeZigZag64(long n) {
/*  543 */     return n >>> 1L ^ -(n & 0x1L);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int readRawVarint32(int firstByte, InputStream input) throws IOException {
/*  552 */     if ((firstByte & 0x80) == 0) {
/*  553 */       return firstByte;
/*      */     }
/*      */     
/*  556 */     int result = firstByte & 0x7F;
/*  557 */     int offset = 7;
/*  558 */     for (; offset < 32; offset += 7) {
/*  559 */       int b = input.read();
/*  560 */       if (b == -1) {
/*  561 */         throw InvalidProtocolBufferException.truncatedMessage();
/*      */       }
/*  563 */       result |= (b & 0x7F) << offset;
/*  564 */       if ((b & 0x80) == 0) {
/*  565 */         return result;
/*      */       }
/*      */     } 
/*      */     
/*  569 */     for (; offset < 64; offset += 7) {
/*  570 */       int b = input.read();
/*  571 */       if (b == -1) {
/*  572 */         throw InvalidProtocolBufferException.truncatedMessage();
/*      */       }
/*  574 */       if ((b & 0x80) == 0) {
/*  575 */         return result;
/*      */       }
/*      */     } 
/*  578 */     throw InvalidProtocolBufferException.malformedVarint();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static int readRawVarint32(InputStream input) throws IOException {
/*  588 */     int firstByte = input.read();
/*  589 */     if (firstByte == -1) {
/*  590 */       throw InvalidProtocolBufferException.truncatedMessage();
/*      */     }
/*  592 */     return readRawVarint32(firstByte, input);
/*      */   } public abstract int readTag() throws IOException; public abstract void checkLastTagWas(int paramInt) throws InvalidProtocolBufferException; public abstract int getLastTag(); public abstract boolean skipField(int paramInt) throws IOException; @Deprecated
/*      */   public abstract boolean skipField(int paramInt, CodedOutputStream paramCodedOutputStream) throws IOException; public abstract void skipMessage() throws IOException; public abstract void skipMessage(CodedOutputStream paramCodedOutputStream) throws IOException; public abstract double readDouble() throws IOException; public abstract float readFloat() throws IOException; public abstract long readUInt64() throws IOException; public abstract long readInt64() throws IOException; public abstract int readInt32() throws IOException; public abstract long readFixed64() throws IOException; public abstract int readFixed32() throws IOException; public abstract boolean readBool() throws IOException; public abstract String readString() throws IOException; public abstract String readStringRequireUtf8() throws IOException; public abstract void readGroup(int paramInt, MessageLite.Builder paramBuilder, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException; public abstract <T extends MessageLite> T readGroup(int paramInt, Parser<T> paramParser, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException; @Deprecated
/*      */   public abstract void readUnknownGroup(int paramInt, MessageLite.Builder paramBuilder) throws IOException; public abstract void readMessage(MessageLite.Builder paramBuilder, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException; public abstract <T extends MessageLite> T readMessage(Parser<T> paramParser, ExtensionRegistryLite paramExtensionRegistryLite) throws IOException; public abstract ByteString readBytes() throws IOException; public abstract byte[] readByteArray() throws IOException; public abstract ByteBuffer readByteBuffer() throws IOException; public abstract int readUInt32() throws IOException; public abstract int readEnum() throws IOException; public abstract int readSFixed32() throws IOException; public abstract long readSFixed64() throws IOException; public abstract int readSInt32() throws IOException; public abstract long readSInt64() throws IOException; public abstract int readRawVarint32() throws IOException; public abstract long readRawVarint64() throws IOException; abstract long readRawVarint64SlowPath() throws IOException; public abstract int readRawLittleEndian32() throws IOException;
/*      */   public abstract long readRawLittleEndian64() throws IOException;
/*      */   public abstract void enableAliasing(boolean paramBoolean);
/*      */   public abstract void resetSizeCounter();
/*      */   public abstract int pushLimit(int paramInt) throws InvalidProtocolBufferException;
/*      */   public abstract void popLimit(int paramInt);
/*      */   public abstract int getBytesUntilLimit();
/*      */   public abstract boolean isAtEnd() throws IOException;
/*      */   public abstract int getTotalBytesRead();
/*      */   public abstract byte readRawByte() throws IOException;
/*      */   public abstract byte[] readRawBytes(int paramInt) throws IOException;
/*      */   public abstract void skipRawBytes(int paramInt) throws IOException;
/*  607 */   private static final class ArrayDecoder extends CodedInputStream { private final byte[] buffer; private final boolean immutable; private int limit; private int bufferSizeAfterLimit; private int currentLimit = Integer.MAX_VALUE; private int pos; private int startPos; private int lastTag; private boolean enableAliasing;
/*      */     
/*      */     private ArrayDecoder(byte[] buffer, int offset, int len, boolean immutable) {
/*  610 */       this.buffer = buffer;
/*  611 */       this.limit = offset + len;
/*  612 */       this.pos = offset;
/*  613 */       this.startPos = this.pos;
/*  614 */       this.immutable = immutable;
/*      */     }
/*      */ 
/*      */     
/*      */     public int readTag() throws IOException {
/*  619 */       if (isAtEnd()) {
/*  620 */         this.lastTag = 0;
/*  621 */         return 0;
/*      */       } 
/*      */       
/*  624 */       this.lastTag = readRawVarint32();
/*  625 */       if (WireFormat.getTagFieldNumber(this.lastTag) == 0)
/*      */       {
/*      */         
/*  628 */         throw InvalidProtocolBufferException.invalidTag();
/*      */       }
/*  630 */       return this.lastTag;
/*      */     }
/*      */ 
/*      */     
/*      */     public void checkLastTagWas(int value) throws InvalidProtocolBufferException {
/*  635 */       if (this.lastTag != value) {
/*  636 */         throw InvalidProtocolBufferException.invalidEndTag();
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public int getLastTag() {
/*  642 */       return this.lastTag;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean skipField(int tag) throws IOException {
/*  647 */       switch (WireFormat.getTagWireType(tag)) {
/*      */         case 0:
/*  649 */           skipRawVarint();
/*  650 */           return true;
/*      */         case 1:
/*  652 */           skipRawBytes(8);
/*  653 */           return true;
/*      */         case 2:
/*  655 */           skipRawBytes(readRawVarint32());
/*  656 */           return true;
/*      */         case 3:
/*  658 */           skipMessage();
/*  659 */           checkLastTagWas(
/*  660 */               WireFormat.makeTag(WireFormat.getTagFieldNumber(tag), 4));
/*  661 */           return true;
/*      */         case 4:
/*  663 */           return false;
/*      */         case 5:
/*  665 */           skipRawBytes(4);
/*  666 */           return true;
/*      */       } 
/*  668 */       throw InvalidProtocolBufferException.invalidWireType();
/*      */     } public boolean skipField(int tag, CodedOutputStream output) throws IOException {
/*      */       long l;
/*      */       ByteString byteString;
/*      */       int endtag;
/*      */       int value;
/*  674 */       switch (WireFormat.getTagWireType(tag)) {
/*      */         
/*      */         case 0:
/*  677 */           l = readInt64();
/*  678 */           output.writeRawVarint32(tag);
/*  679 */           output.writeUInt64NoTag(l);
/*  680 */           return true;
/*      */ 
/*      */         
/*      */         case 1:
/*  684 */           l = readRawLittleEndian64();
/*  685 */           output.writeRawVarint32(tag);
/*  686 */           output.writeFixed64NoTag(l);
/*  687 */           return true;
/*      */ 
/*      */         
/*      */         case 2:
/*  691 */           byteString = readBytes();
/*  692 */           output.writeRawVarint32(tag);
/*  693 */           output.writeBytesNoTag(byteString);
/*  694 */           return true;
/*      */ 
/*      */         
/*      */         case 3:
/*  698 */           output.writeRawVarint32(tag);
/*  699 */           skipMessage(output);
/*      */           
/*  701 */           endtag = WireFormat.makeTag(
/*  702 */               WireFormat.getTagFieldNumber(tag), 4);
/*  703 */           checkLastTagWas(endtag);
/*  704 */           output.writeRawVarint32(endtag);
/*  705 */           return true;
/*      */ 
/*      */         
/*      */         case 4:
/*  709 */           return false;
/*      */ 
/*      */         
/*      */         case 5:
/*  713 */           value = readRawLittleEndian32();
/*  714 */           output.writeRawVarint32(tag);
/*  715 */           output.writeFixed32NoTag(value);
/*  716 */           return true;
/*      */       } 
/*      */       
/*  719 */       throw InvalidProtocolBufferException.invalidWireType();
/*      */     }
/*      */ 
/*      */     
/*      */     public void skipMessage() throws IOException {
/*      */       int tag;
/*      */       do {
/*  726 */         tag = readTag();
/*  727 */       } while (tag != 0 && skipField(tag));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void skipMessage(CodedOutputStream output) throws IOException {
/*      */       int tag;
/*      */       do {
/*  736 */         tag = readTag();
/*  737 */       } while (tag != 0 && skipField(tag, output));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public double readDouble() throws IOException {
/*  748 */       return Double.longBitsToDouble(readRawLittleEndian64());
/*      */     }
/*      */ 
/*      */     
/*      */     public float readFloat() throws IOException {
/*  753 */       return Float.intBitsToFloat(readRawLittleEndian32());
/*      */     }
/*      */ 
/*      */     
/*      */     public long readUInt64() throws IOException {
/*  758 */       return readRawVarint64();
/*      */     }
/*      */ 
/*      */     
/*      */     public long readInt64() throws IOException {
/*  763 */       return readRawVarint64();
/*      */     }
/*      */ 
/*      */     
/*      */     public int readInt32() throws IOException {
/*  768 */       return readRawVarint32();
/*      */     }
/*      */ 
/*      */     
/*      */     public long readFixed64() throws IOException {
/*  773 */       return readRawLittleEndian64();
/*      */     }
/*      */ 
/*      */     
/*      */     public int readFixed32() throws IOException {
/*  778 */       return readRawLittleEndian32();
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean readBool() throws IOException {
/*  783 */       return (readRawVarint64() != 0L);
/*      */     }
/*      */ 
/*      */     
/*      */     public String readString() throws IOException {
/*  788 */       int size = readRawVarint32();
/*  789 */       if (size > 0 && size <= this.limit - this.pos) {
/*      */ 
/*      */         
/*  792 */         String result = new String(this.buffer, this.pos, size, Internal.UTF_8);
/*  793 */         this.pos += size;
/*  794 */         return result;
/*      */       } 
/*      */       
/*  797 */       if (size == 0) {
/*  798 */         return "";
/*      */       }
/*  800 */       if (size < 0) {
/*  801 */         throw InvalidProtocolBufferException.negativeSize();
/*      */       }
/*  803 */       throw InvalidProtocolBufferException.truncatedMessage();
/*      */     }
/*      */ 
/*      */     
/*      */     public String readStringRequireUtf8() throws IOException {
/*  808 */       int size = readRawVarint32();
/*  809 */       if (size > 0 && size <= this.limit - this.pos) {
/*  810 */         String result = Utf8.decodeUtf8(this.buffer, this.pos, size);
/*  811 */         this.pos += size;
/*  812 */         return result;
/*      */       } 
/*      */       
/*  815 */       if (size == 0) {
/*  816 */         return "";
/*      */       }
/*  818 */       if (size <= 0) {
/*  819 */         throw InvalidProtocolBufferException.negativeSize();
/*      */       }
/*  821 */       throw InvalidProtocolBufferException.truncatedMessage();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void readGroup(int fieldNumber, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  830 */       if (this.recursionDepth >= this.recursionLimit) {
/*  831 */         throw InvalidProtocolBufferException.recursionLimitExceeded();
/*      */       }
/*  833 */       this.recursionDepth++;
/*  834 */       builder.mergeFrom(this, extensionRegistry);
/*  835 */       checkLastTagWas(WireFormat.makeTag(fieldNumber, 4));
/*  836 */       this.recursionDepth--;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public <T extends MessageLite> T readGroup(int fieldNumber, Parser<T> parser, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  846 */       if (this.recursionDepth >= this.recursionLimit) {
/*  847 */         throw InvalidProtocolBufferException.recursionLimitExceeded();
/*      */       }
/*  849 */       this.recursionDepth++;
/*  850 */       MessageLite messageLite = (MessageLite)parser.parsePartialFrom(this, extensionRegistry);
/*  851 */       checkLastTagWas(WireFormat.makeTag(fieldNumber, 4));
/*  852 */       this.recursionDepth--;
/*  853 */       return (T)messageLite;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     @Deprecated
/*      */     public void readUnknownGroup(int fieldNumber, MessageLite.Builder builder) throws IOException {
/*  860 */       readGroup(fieldNumber, builder, ExtensionRegistryLite.getEmptyRegistry());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  867 */       int length = readRawVarint32();
/*  868 */       if (this.recursionDepth >= this.recursionLimit) {
/*  869 */         throw InvalidProtocolBufferException.recursionLimitExceeded();
/*      */       }
/*  871 */       int oldLimit = pushLimit(length);
/*  872 */       this.recursionDepth++;
/*  873 */       builder.mergeFrom(this, extensionRegistry);
/*  874 */       checkLastTagWas(0);
/*  875 */       this.recursionDepth--;
/*  876 */       popLimit(oldLimit);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  883 */       int length = readRawVarint32();
/*  884 */       if (this.recursionDepth >= this.recursionLimit) {
/*  885 */         throw InvalidProtocolBufferException.recursionLimitExceeded();
/*      */       }
/*  887 */       int oldLimit = pushLimit(length);
/*  888 */       this.recursionDepth++;
/*  889 */       MessageLite messageLite = (MessageLite)parser.parsePartialFrom(this, extensionRegistry);
/*  890 */       checkLastTagWas(0);
/*  891 */       this.recursionDepth--;
/*  892 */       popLimit(oldLimit);
/*  893 */       return (T)messageLite;
/*      */     }
/*      */ 
/*      */     
/*      */     public ByteString readBytes() throws IOException {
/*  898 */       int size = readRawVarint32();
/*  899 */       if (size > 0 && size <= this.limit - this.pos) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  905 */         ByteString result = (this.immutable && this.enableAliasing) ? ByteString.wrap(this.buffer, this.pos, size) : ByteString.copyFrom(this.buffer, this.pos, size);
/*  906 */         this.pos += size;
/*  907 */         return result;
/*      */       } 
/*  909 */       if (size == 0) {
/*  910 */         return ByteString.EMPTY;
/*      */       }
/*      */       
/*  913 */       return ByteString.wrap(readRawBytes(size));
/*      */     }
/*      */ 
/*      */     
/*      */     public byte[] readByteArray() throws IOException {
/*  918 */       int size = readRawVarint32();
/*  919 */       return readRawBytes(size);
/*      */     }
/*      */ 
/*      */     
/*      */     public ByteBuffer readByteBuffer() throws IOException {
/*  924 */       int size = readRawVarint32();
/*  925 */       if (size > 0 && size <= this.limit - this.pos) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  934 */         ByteBuffer result = (!this.immutable && this.enableAliasing) ? ByteBuffer.wrap(this.buffer, this.pos, size).slice() : ByteBuffer.wrap(Arrays.copyOfRange(this.buffer, this.pos, this.pos + size));
/*  935 */         this.pos += size;
/*      */         
/*  937 */         return result;
/*      */       } 
/*      */       
/*  940 */       if (size == 0) {
/*  941 */         return Internal.EMPTY_BYTE_BUFFER;
/*      */       }
/*  943 */       if (size < 0) {
/*  944 */         throw InvalidProtocolBufferException.negativeSize();
/*      */       }
/*  946 */       throw InvalidProtocolBufferException.truncatedMessage();
/*      */     }
/*      */ 
/*      */     
/*      */     public int readUInt32() throws IOException {
/*  951 */       return readRawVarint32();
/*      */     }
/*      */ 
/*      */     
/*      */     public int readEnum() throws IOException {
/*  956 */       return readRawVarint32();
/*      */     }
/*      */ 
/*      */     
/*      */     public int readSFixed32() throws IOException {
/*  961 */       return readRawLittleEndian32();
/*      */     }
/*      */ 
/*      */     
/*      */     public long readSFixed64() throws IOException {
/*  966 */       return readRawLittleEndian64();
/*      */     }
/*      */ 
/*      */     
/*      */     public int readSInt32() throws IOException {
/*  971 */       return decodeZigZag32(readRawVarint32());
/*      */     }
/*      */ 
/*      */     
/*      */     public long readSInt64() throws IOException {
/*  976 */       return decodeZigZag64(readRawVarint64());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int readRawVarint32() throws IOException {
/*  986 */       int tempPos = this.pos;
/*      */       
/*  988 */       if (this.limit != tempPos)
/*      */       
/*      */       { 
/*      */         
/*  992 */         byte[] buffer = this.buffer;
/*      */         int x;
/*  994 */         if ((x = buffer[tempPos++]) >= 0) {
/*  995 */           this.pos = tempPos;
/*  996 */           return x;
/*  997 */         }  if (this.limit - tempPos >= 9)
/*      */         
/*  999 */         { if ((x ^= buffer[tempPos++] << 7) < 0)
/* 1000 */           { x ^= 0xFFFFFF80; }
/* 1001 */           else if ((x ^= buffer[tempPos++] << 14) >= 0)
/* 1002 */           { x ^= 0x3F80; }
/* 1003 */           else if ((x ^= buffer[tempPos++] << 21) < 0)
/* 1004 */           { x ^= 0xFFE03F80; }
/*      */           else
/* 1006 */           { int y = buffer[tempPos++];
/* 1007 */             x ^= y << 28;
/* 1008 */             x ^= 0xFE03F80;
/* 1009 */             if (y < 0 && buffer[tempPos++] < 0 && buffer[tempPos++] < 0 && buffer[tempPos++] < 0 && buffer[tempPos++] < 0 && buffer[tempPos++] < 0)
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1021 */               return (int)readRawVarint64SlowPath(); }  }  this.pos = tempPos; return x; }  }  return (int)readRawVarint64SlowPath();
/*      */     }
/*      */     
/*      */     private void skipRawVarint() throws IOException {
/* 1025 */       if (this.limit - this.pos >= 10) {
/* 1026 */         skipRawVarintFastPath();
/*      */       } else {
/* 1028 */         skipRawVarintSlowPath();
/*      */       } 
/*      */     }
/*      */     
/*      */     private void skipRawVarintFastPath() throws IOException {
/* 1033 */       for (int i = 0; i < 10; i++) {
/* 1034 */         if (this.buffer[this.pos++] >= 0) {
/*      */           return;
/*      */         }
/*      */       } 
/* 1038 */       throw InvalidProtocolBufferException.malformedVarint();
/*      */     }
/*      */     
/*      */     private void skipRawVarintSlowPath() throws IOException {
/* 1042 */       for (int i = 0; i < 10; i++) {
/* 1043 */         if (readRawByte() >= 0) {
/*      */           return;
/*      */         }
/*      */       } 
/* 1047 */       throw InvalidProtocolBufferException.malformedVarint();
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
/*      */     public long readRawVarint64() throws IOException {
/* 1065 */       int tempPos = this.pos;
/*      */       
/* 1067 */       if (this.limit != tempPos)
/*      */       
/*      */       { 
/*      */         
/* 1071 */         byte[] buffer = this.buffer;
/*      */         
/*      */         int y;
/* 1074 */         if ((y = buffer[tempPos++]) >= 0) {
/* 1075 */           this.pos = tempPos;
/* 1076 */           return y;
/* 1077 */         }  if (this.limit - tempPos >= 9)
/*      */         { long x;
/* 1079 */           if ((y ^= buffer[tempPos++] << 7) < 0)
/* 1080 */           { x = (y ^ 0xFFFFFF80); }
/* 1081 */           else if ((y ^= buffer[tempPos++] << 14) >= 0)
/* 1082 */           { x = (y ^ 0x3F80); }
/* 1083 */           else if ((y ^= buffer[tempPos++] << 21) < 0)
/* 1084 */           { x = (y ^ 0xFFE03F80); }
/* 1085 */           else if ((x = y ^ buffer[tempPos++] << 28L) >= 0L)
/* 1086 */           { x ^= 0xFE03F80L; }
/* 1087 */           else if ((x ^= buffer[tempPos++] << 35L) < 0L)
/* 1088 */           { x ^= 0xFFFFFFF80FE03F80L; }
/* 1089 */           else if ((x ^= buffer[tempPos++] << 42L) >= 0L)
/* 1090 */           { x ^= 0x3F80FE03F80L; }
/* 1091 */           else if ((x ^= buffer[tempPos++] << 49L) < 0L)
/* 1092 */           { x ^= 0xFFFE03F80FE03F80L;
/*      */ 
/*      */             
/*      */              }
/*      */           
/*      */           else
/*      */           
/*      */           { 
/*      */             
/* 1101 */             x ^= buffer[tempPos++] << 56L;
/* 1102 */             x ^= 0xFE03F80FE03F80L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1111 */             if (x < 0L && 
/* 1112 */               buffer[tempPos++] < 0L)
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1120 */               return readRawVarint64SlowPath(); }  }  this.pos = tempPos; return x; }  }  return readRawVarint64SlowPath();
/*      */     }
/*      */ 
/*      */     
/*      */     long readRawVarint64SlowPath() throws IOException {
/* 1125 */       long result = 0L;
/* 1126 */       for (int shift = 0; shift < 64; shift += 7) {
/* 1127 */         byte b = readRawByte();
/* 1128 */         result |= (b & Byte.MAX_VALUE) << shift;
/* 1129 */         if ((b & 0x80) == 0) {
/* 1130 */           return result;
/*      */         }
/*      */       } 
/* 1133 */       throw InvalidProtocolBufferException.malformedVarint();
/*      */     }
/*      */ 
/*      */     
/*      */     public int readRawLittleEndian32() throws IOException {
/* 1138 */       int tempPos = this.pos;
/*      */       
/* 1140 */       if (this.limit - tempPos < 4) {
/* 1141 */         throw InvalidProtocolBufferException.truncatedMessage();
/*      */       }
/*      */       
/* 1144 */       byte[] buffer = this.buffer;
/* 1145 */       this.pos = tempPos + 4;
/* 1146 */       return buffer[tempPos] & 0xFF | (buffer[tempPos + 1] & 0xFF) << 8 | (buffer[tempPos + 2] & 0xFF) << 16 | (buffer[tempPos + 3] & 0xFF) << 24;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public long readRawLittleEndian64() throws IOException {
/* 1154 */       int tempPos = this.pos;
/*      */       
/* 1156 */       if (this.limit - tempPos < 8) {
/* 1157 */         throw InvalidProtocolBufferException.truncatedMessage();
/*      */       }
/*      */       
/* 1160 */       byte[] buffer = this.buffer;
/* 1161 */       this.pos = tempPos + 8;
/* 1162 */       return buffer[tempPos] & 0xFFL | (buffer[tempPos + 1] & 0xFFL) << 8L | (buffer[tempPos + 2] & 0xFFL) << 16L | (buffer[tempPos + 3] & 0xFFL) << 24L | (buffer[tempPos + 4] & 0xFFL) << 32L | (buffer[tempPos + 5] & 0xFFL) << 40L | (buffer[tempPos + 6] & 0xFFL) << 48L | (buffer[tempPos + 7] & 0xFFL) << 56L;
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
/*      */     public void enableAliasing(boolean enabled) {
/* 1174 */       this.enableAliasing = enabled;
/*      */     }
/*      */ 
/*      */     
/*      */     public void resetSizeCounter() {
/* 1179 */       this.startPos = this.pos;
/*      */     }
/*      */ 
/*      */     
/*      */     public int pushLimit(int byteLimit) throws InvalidProtocolBufferException {
/* 1184 */       if (byteLimit < 0) {
/* 1185 */         throw InvalidProtocolBufferException.negativeSize();
/*      */       }
/* 1187 */       byteLimit += getTotalBytesRead();
/* 1188 */       int oldLimit = this.currentLimit;
/* 1189 */       if (byteLimit > oldLimit) {
/* 1190 */         throw InvalidProtocolBufferException.truncatedMessage();
/*      */       }
/* 1192 */       this.currentLimit = byteLimit;
/*      */       
/* 1194 */       recomputeBufferSizeAfterLimit();
/*      */       
/* 1196 */       return oldLimit;
/*      */     }
/*      */     
/*      */     private void recomputeBufferSizeAfterLimit() {
/* 1200 */       this.limit += this.bufferSizeAfterLimit;
/* 1201 */       int bufferEnd = this.limit - this.startPos;
/* 1202 */       if (bufferEnd > this.currentLimit) {
/*      */         
/* 1204 */         this.bufferSizeAfterLimit = bufferEnd - this.currentLimit;
/* 1205 */         this.limit -= this.bufferSizeAfterLimit;
/*      */       } else {
/* 1207 */         this.bufferSizeAfterLimit = 0;
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void popLimit(int oldLimit) {
/* 1213 */       this.currentLimit = oldLimit;
/* 1214 */       recomputeBufferSizeAfterLimit();
/*      */     }
/*      */ 
/*      */     
/*      */     public int getBytesUntilLimit() {
/* 1219 */       if (this.currentLimit == Integer.MAX_VALUE) {
/* 1220 */         return -1;
/*      */       }
/*      */       
/* 1223 */       return this.currentLimit - getTotalBytesRead();
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isAtEnd() throws IOException {
/* 1228 */       return (this.pos == this.limit);
/*      */     }
/*      */ 
/*      */     
/*      */     public int getTotalBytesRead() {
/* 1233 */       return this.pos - this.startPos;
/*      */     }
/*      */ 
/*      */     
/*      */     public byte readRawByte() throws IOException {
/* 1238 */       if (this.pos == this.limit) {
/* 1239 */         throw InvalidProtocolBufferException.truncatedMessage();
/*      */       }
/* 1241 */       return this.buffer[this.pos++];
/*      */     }
/*      */ 
/*      */     
/*      */     public byte[] readRawBytes(int length) throws IOException {
/* 1246 */       if (length > 0 && length <= this.limit - this.pos) {
/* 1247 */         int tempPos = this.pos;
/* 1248 */         this.pos += length;
/* 1249 */         return Arrays.copyOfRange(this.buffer, tempPos, this.pos);
/*      */       } 
/*      */       
/* 1252 */       if (length <= 0) {
/* 1253 */         if (length == 0) {
/* 1254 */           return Internal.EMPTY_BYTE_ARRAY;
/*      */         }
/* 1256 */         throw InvalidProtocolBufferException.negativeSize();
/*      */       } 
/*      */       
/* 1259 */       throw InvalidProtocolBufferException.truncatedMessage();
/*      */     }
/*      */ 
/*      */     
/*      */     public void skipRawBytes(int length) throws IOException {
/* 1264 */       if (length >= 0 && length <= this.limit - this.pos) {
/*      */         
/* 1266 */         this.pos += length;
/*      */         
/*      */         return;
/*      */       } 
/* 1270 */       if (length < 0) {
/* 1271 */         throw InvalidProtocolBufferException.negativeSize();
/*      */       }
/* 1273 */       throw InvalidProtocolBufferException.truncatedMessage();
/*      */     } }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final class UnsafeDirectNioDecoder
/*      */     extends CodedInputStream
/*      */   {
/*      */     private final ByteBuffer buffer;
/*      */ 
/*      */ 
/*      */     
/*      */     private final boolean immutable;
/*      */ 
/*      */ 
/*      */     
/*      */     private final long address;
/*      */ 
/*      */ 
/*      */     
/*      */     private long limit;
/*      */ 
/*      */ 
/*      */     
/*      */     private long pos;
/*      */ 
/*      */ 
/*      */     
/*      */     private long startPos;
/*      */ 
/*      */ 
/*      */     
/*      */     private int bufferSizeAfterLimit;
/*      */ 
/*      */     
/*      */     private int lastTag;
/*      */ 
/*      */     
/*      */     private boolean enableAliasing;
/*      */ 
/*      */     
/* 1316 */     private int currentLimit = Integer.MAX_VALUE;
/*      */     
/*      */     static boolean isSupported() {
/* 1319 */       return UnsafeUtil.hasUnsafeByteBufferOperations();
/*      */     }
/*      */     
/*      */     private UnsafeDirectNioDecoder(ByteBuffer buffer, boolean immutable) {
/* 1323 */       this.buffer = buffer;
/* 1324 */       this.address = UnsafeUtil.addressOffset(buffer);
/* 1325 */       this.limit = this.address + buffer.limit();
/* 1326 */       this.pos = this.address + buffer.position();
/* 1327 */       this.startPos = this.pos;
/* 1328 */       this.immutable = immutable;
/*      */     }
/*      */ 
/*      */     
/*      */     public int readTag() throws IOException {
/* 1333 */       if (isAtEnd()) {
/* 1334 */         this.lastTag = 0;
/* 1335 */         return 0;
/*      */       } 
/*      */       
/* 1338 */       this.lastTag = readRawVarint32();
/* 1339 */       if (WireFormat.getTagFieldNumber(this.lastTag) == 0)
/*      */       {
/*      */         
/* 1342 */         throw InvalidProtocolBufferException.invalidTag();
/*      */       }
/* 1344 */       return this.lastTag;
/*      */     }
/*      */ 
/*      */     
/*      */     public void checkLastTagWas(int value) throws InvalidProtocolBufferException {
/* 1349 */       if (this.lastTag != value) {
/* 1350 */         throw InvalidProtocolBufferException.invalidEndTag();
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public int getLastTag() {
/* 1356 */       return this.lastTag;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean skipField(int tag) throws IOException {
/* 1361 */       switch (WireFormat.getTagWireType(tag)) {
/*      */         case 0:
/* 1363 */           skipRawVarint();
/* 1364 */           return true;
/*      */         case 1:
/* 1366 */           skipRawBytes(8);
/* 1367 */           return true;
/*      */         case 2:
/* 1369 */           skipRawBytes(readRawVarint32());
/* 1370 */           return true;
/*      */         case 3:
/* 1372 */           skipMessage();
/* 1373 */           checkLastTagWas(
/* 1374 */               WireFormat.makeTag(WireFormat.getTagFieldNumber(tag), 4));
/* 1375 */           return true;
/*      */         case 4:
/* 1377 */           return false;
/*      */         case 5:
/* 1379 */           skipRawBytes(4);
/* 1380 */           return true;
/*      */       } 
/* 1382 */       throw InvalidProtocolBufferException.invalidWireType();
/*      */     } public boolean skipField(int tag, CodedOutputStream output) throws IOException {
/*      */       long l;
/*      */       ByteString byteString;
/*      */       int endtag;
/*      */       int value;
/* 1388 */       switch (WireFormat.getTagWireType(tag)) {
/*      */         
/*      */         case 0:
/* 1391 */           l = readInt64();
/* 1392 */           output.writeRawVarint32(tag);
/* 1393 */           output.writeUInt64NoTag(l);
/* 1394 */           return true;
/*      */ 
/*      */         
/*      */         case 1:
/* 1398 */           l = readRawLittleEndian64();
/* 1399 */           output.writeRawVarint32(tag);
/* 1400 */           output.writeFixed64NoTag(l);
/* 1401 */           return true;
/*      */ 
/*      */         
/*      */         case 2:
/* 1405 */           byteString = readBytes();
/* 1406 */           output.writeRawVarint32(tag);
/* 1407 */           output.writeBytesNoTag(byteString);
/* 1408 */           return true;
/*      */ 
/*      */         
/*      */         case 3:
/* 1412 */           output.writeRawVarint32(tag);
/* 1413 */           skipMessage(output);
/*      */           
/* 1415 */           endtag = WireFormat.makeTag(
/* 1416 */               WireFormat.getTagFieldNumber(tag), 4);
/* 1417 */           checkLastTagWas(endtag);
/* 1418 */           output.writeRawVarint32(endtag);
/* 1419 */           return true;
/*      */ 
/*      */         
/*      */         case 4:
/* 1423 */           return false;
/*      */ 
/*      */         
/*      */         case 5:
/* 1427 */           value = readRawLittleEndian32();
/* 1428 */           output.writeRawVarint32(tag);
/* 1429 */           output.writeFixed32NoTag(value);
/* 1430 */           return true;
/*      */       } 
/*      */       
/* 1433 */       throw InvalidProtocolBufferException.invalidWireType();
/*      */     }
/*      */ 
/*      */     
/*      */     public void skipMessage() throws IOException {
/*      */       int tag;
/*      */       do {
/* 1440 */         tag = readTag();
/* 1441 */       } while (tag != 0 && skipField(tag));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void skipMessage(CodedOutputStream output) throws IOException {
/*      */       int tag;
/*      */       do {
/* 1450 */         tag = readTag();
/* 1451 */       } while (tag != 0 && skipField(tag, output));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public double readDouble() throws IOException {
/* 1462 */       return Double.longBitsToDouble(readRawLittleEndian64());
/*      */     }
/*      */ 
/*      */     
/*      */     public float readFloat() throws IOException {
/* 1467 */       return Float.intBitsToFloat(readRawLittleEndian32());
/*      */     }
/*      */ 
/*      */     
/*      */     public long readUInt64() throws IOException {
/* 1472 */       return readRawVarint64();
/*      */     }
/*      */ 
/*      */     
/*      */     public long readInt64() throws IOException {
/* 1477 */       return readRawVarint64();
/*      */     }
/*      */ 
/*      */     
/*      */     public int readInt32() throws IOException {
/* 1482 */       return readRawVarint32();
/*      */     }
/*      */ 
/*      */     
/*      */     public long readFixed64() throws IOException {
/* 1487 */       return readRawLittleEndian64();
/*      */     }
/*      */ 
/*      */     
/*      */     public int readFixed32() throws IOException {
/* 1492 */       return readRawLittleEndian32();
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean readBool() throws IOException {
/* 1497 */       return (readRawVarint64() != 0L);
/*      */     }
/*      */ 
/*      */     
/*      */     public String readString() throws IOException {
/* 1502 */       int size = readRawVarint32();
/* 1503 */       if (size > 0 && size <= remaining()) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1508 */         byte[] bytes = new byte[size];
/* 1509 */         UnsafeUtil.copyMemory(this.pos, bytes, 0L, size);
/* 1510 */         String result = new String(bytes, Internal.UTF_8);
/* 1511 */         this.pos += size;
/* 1512 */         return result;
/*      */       } 
/*      */       
/* 1515 */       if (size == 0) {
/* 1516 */         return "";
/*      */       }
/* 1518 */       if (size < 0) {
/* 1519 */         throw InvalidProtocolBufferException.negativeSize();
/*      */       }
/* 1521 */       throw InvalidProtocolBufferException.truncatedMessage();
/*      */     }
/*      */ 
/*      */     
/*      */     public String readStringRequireUtf8() throws IOException {
/* 1526 */       int size = readRawVarint32();
/* 1527 */       if (size > 0 && size <= remaining()) {
/* 1528 */         int bufferPos = bufferPos(this.pos);
/* 1529 */         String result = Utf8.decodeUtf8(this.buffer, bufferPos, size);
/* 1530 */         this.pos += size;
/* 1531 */         return result;
/*      */       } 
/*      */       
/* 1534 */       if (size == 0) {
/* 1535 */         return "";
/*      */       }
/* 1537 */       if (size <= 0) {
/* 1538 */         throw InvalidProtocolBufferException.negativeSize();
/*      */       }
/* 1540 */       throw InvalidProtocolBufferException.truncatedMessage();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void readGroup(int fieldNumber, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 1549 */       if (this.recursionDepth >= this.recursionLimit) {
/* 1550 */         throw InvalidProtocolBufferException.recursionLimitExceeded();
/*      */       }
/* 1552 */       this.recursionDepth++;
/* 1553 */       builder.mergeFrom(this, extensionRegistry);
/* 1554 */       checkLastTagWas(WireFormat.makeTag(fieldNumber, 4));
/* 1555 */       this.recursionDepth--;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public <T extends MessageLite> T readGroup(int fieldNumber, Parser<T> parser, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 1565 */       if (this.recursionDepth >= this.recursionLimit) {
/* 1566 */         throw InvalidProtocolBufferException.recursionLimitExceeded();
/*      */       }
/* 1568 */       this.recursionDepth++;
/* 1569 */       MessageLite messageLite = (MessageLite)parser.parsePartialFrom(this, extensionRegistry);
/* 1570 */       checkLastTagWas(WireFormat.makeTag(fieldNumber, 4));
/* 1571 */       this.recursionDepth--;
/* 1572 */       return (T)messageLite;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     @Deprecated
/*      */     public void readUnknownGroup(int fieldNumber, MessageLite.Builder builder) throws IOException {
/* 1579 */       readGroup(fieldNumber, builder, ExtensionRegistryLite.getEmptyRegistry());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 1586 */       int length = readRawVarint32();
/* 1587 */       if (this.recursionDepth >= this.recursionLimit) {
/* 1588 */         throw InvalidProtocolBufferException.recursionLimitExceeded();
/*      */       }
/* 1590 */       int oldLimit = pushLimit(length);
/* 1591 */       this.recursionDepth++;
/* 1592 */       builder.mergeFrom(this, extensionRegistry);
/* 1593 */       checkLastTagWas(0);
/* 1594 */       this.recursionDepth--;
/* 1595 */       popLimit(oldLimit);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 1602 */       int length = readRawVarint32();
/* 1603 */       if (this.recursionDepth >= this.recursionLimit) {
/* 1604 */         throw InvalidProtocolBufferException.recursionLimitExceeded();
/*      */       }
/* 1606 */       int oldLimit = pushLimit(length);
/* 1607 */       this.recursionDepth++;
/* 1608 */       MessageLite messageLite = (MessageLite)parser.parsePartialFrom(this, extensionRegistry);
/* 1609 */       checkLastTagWas(0);
/* 1610 */       this.recursionDepth--;
/* 1611 */       popLimit(oldLimit);
/* 1612 */       return (T)messageLite;
/*      */     }
/*      */ 
/*      */     
/*      */     public ByteString readBytes() throws IOException {
/* 1617 */       int size = readRawVarint32();
/* 1618 */       if (size > 0 && size <= remaining()) {
/* 1619 */         if (this.immutable && this.enableAliasing) {
/* 1620 */           ByteBuffer result = slice(this.pos, this.pos + size);
/* 1621 */           this.pos += size;
/* 1622 */           return ByteString.wrap(result);
/*      */         } 
/*      */         
/* 1625 */         byte[] bytes = new byte[size];
/* 1626 */         UnsafeUtil.copyMemory(this.pos, bytes, 0L, size);
/* 1627 */         this.pos += size;
/* 1628 */         return ByteString.wrap(bytes);
/*      */       } 
/*      */ 
/*      */       
/* 1632 */       if (size == 0) {
/* 1633 */         return ByteString.EMPTY;
/*      */       }
/* 1635 */       if (size < 0) {
/* 1636 */         throw InvalidProtocolBufferException.negativeSize();
/*      */       }
/* 1638 */       throw InvalidProtocolBufferException.truncatedMessage();
/*      */     }
/*      */ 
/*      */     
/*      */     public byte[] readByteArray() throws IOException {
/* 1643 */       return readRawBytes(readRawVarint32());
/*      */     }
/*      */ 
/*      */     
/*      */     public ByteBuffer readByteBuffer() throws IOException {
/* 1648 */       int size = readRawVarint32();
/* 1649 */       if (size > 0 && size <= remaining()) {
/*      */ 
/*      */ 
/*      */         
/* 1653 */         if (!this.immutable && this.enableAliasing) {
/* 1654 */           ByteBuffer result = slice(this.pos, this.pos + size);
/* 1655 */           this.pos += size;
/* 1656 */           return result;
/*      */         } 
/*      */         
/* 1659 */         byte[] bytes = new byte[size];
/* 1660 */         UnsafeUtil.copyMemory(this.pos, bytes, 0L, size);
/* 1661 */         this.pos += size;
/* 1662 */         return ByteBuffer.wrap(bytes);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1667 */       if (size == 0) {
/* 1668 */         return Internal.EMPTY_BYTE_BUFFER;
/*      */       }
/* 1670 */       if (size < 0) {
/* 1671 */         throw InvalidProtocolBufferException.negativeSize();
/*      */       }
/* 1673 */       throw InvalidProtocolBufferException.truncatedMessage();
/*      */     }
/*      */ 
/*      */     
/*      */     public int readUInt32() throws IOException {
/* 1678 */       return readRawVarint32();
/*      */     }
/*      */ 
/*      */     
/*      */     public int readEnum() throws IOException {
/* 1683 */       return readRawVarint32();
/*      */     }
/*      */ 
/*      */     
/*      */     public int readSFixed32() throws IOException {
/* 1688 */       return readRawLittleEndian32();
/*      */     }
/*      */ 
/*      */     
/*      */     public long readSFixed64() throws IOException {
/* 1693 */       return readRawLittleEndian64();
/*      */     }
/*      */ 
/*      */     
/*      */     public int readSInt32() throws IOException {
/* 1698 */       return decodeZigZag32(readRawVarint32());
/*      */     }
/*      */ 
/*      */     
/*      */     public long readSInt64() throws IOException {
/* 1703 */       return decodeZigZag64(readRawVarint64());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int readRawVarint32() throws IOException {
/* 1713 */       long tempPos = this.pos;
/*      */       
/* 1715 */       if (this.limit != tempPos)
/*      */       { int x;
/*      */ 
/*      */ 
/*      */         
/* 1720 */         if ((x = UnsafeUtil.getByte(tempPos++)) >= 0) {
/* 1721 */           this.pos = tempPos;
/* 1722 */           return x;
/* 1723 */         }  if (this.limit - tempPos >= 9L)
/*      */         
/* 1725 */         { if ((x ^= UnsafeUtil.getByte(tempPos++) << 7) < 0)
/* 1726 */           { x ^= 0xFFFFFF80; }
/* 1727 */           else if ((x ^= UnsafeUtil.getByte(tempPos++) << 14) >= 0)
/* 1728 */           { x ^= 0x3F80; }
/* 1729 */           else if ((x ^= UnsafeUtil.getByte(tempPos++) << 21) < 0)
/* 1730 */           { x ^= 0xFFE03F80; }
/*      */           else
/* 1732 */           { int y = UnsafeUtil.getByte(tempPos++);
/* 1733 */             x ^= y << 28;
/* 1734 */             x ^= 0xFE03F80;
/* 1735 */             if (y < 0 && 
/* 1736 */               UnsafeUtil.getByte(tempPos++) < 0 && 
/* 1737 */               UnsafeUtil.getByte(tempPos++) < 0 && 
/* 1738 */               UnsafeUtil.getByte(tempPos++) < 0 && 
/* 1739 */               UnsafeUtil.getByte(tempPos++) < 0 && 
/* 1740 */               UnsafeUtil.getByte(tempPos++) < 0)
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1747 */               return (int)readRawVarint64SlowPath(); }  }  this.pos = tempPos; return x; }  }  return (int)readRawVarint64SlowPath();
/*      */     }
/*      */     
/*      */     private void skipRawVarint() throws IOException {
/* 1751 */       if (remaining() >= 10) {
/* 1752 */         skipRawVarintFastPath();
/*      */       } else {
/* 1754 */         skipRawVarintSlowPath();
/*      */       } 
/*      */     }
/*      */     
/*      */     private void skipRawVarintFastPath() throws IOException {
/* 1759 */       for (int i = 0; i < 10; i++) {
/* 1760 */         if (UnsafeUtil.getByte(this.pos++) >= 0) {
/*      */           return;
/*      */         }
/*      */       } 
/* 1764 */       throw InvalidProtocolBufferException.malformedVarint();
/*      */     }
/*      */     
/*      */     private void skipRawVarintSlowPath() throws IOException {
/* 1768 */       for (int i = 0; i < 10; i++) {
/* 1769 */         if (readRawByte() >= 0) {
/*      */           return;
/*      */         }
/*      */       } 
/* 1773 */       throw InvalidProtocolBufferException.malformedVarint();
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
/*      */     public long readRawVarint64() throws IOException {
/* 1791 */       long tempPos = this.pos;
/*      */       
/* 1793 */       if (this.limit != tempPos)
/*      */       { int y;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1799 */         if ((y = UnsafeUtil.getByte(tempPos++)) >= 0) {
/* 1800 */           this.pos = tempPos;
/* 1801 */           return y;
/* 1802 */         }  if (this.limit - tempPos >= 9L)
/*      */         { long x;
/* 1804 */           if ((y ^= UnsafeUtil.getByte(tempPos++) << 7) < 0)
/* 1805 */           { x = (y ^ 0xFFFFFF80); }
/* 1806 */           else if ((y ^= UnsafeUtil.getByte(tempPos++) << 14) >= 0)
/* 1807 */           { x = (y ^ 0x3F80); }
/* 1808 */           else if ((y ^= UnsafeUtil.getByte(tempPos++) << 21) < 0)
/* 1809 */           { x = (y ^ 0xFFE03F80); }
/* 1810 */           else if ((x = y ^ UnsafeUtil.getByte(tempPos++) << 28L) >= 0L)
/* 1811 */           { x ^= 0xFE03F80L; }
/* 1812 */           else if ((x ^= UnsafeUtil.getByte(tempPos++) << 35L) < 0L)
/* 1813 */           { x ^= 0xFFFFFFF80FE03F80L; }
/* 1814 */           else if ((x ^= UnsafeUtil.getByte(tempPos++) << 42L) >= 0L)
/* 1815 */           { x ^= 0x3F80FE03F80L; }
/* 1816 */           else if ((x ^= UnsafeUtil.getByte(tempPos++) << 49L) < 0L)
/* 1817 */           { x ^= 0xFFFE03F80FE03F80L;
/*      */ 
/*      */             
/*      */              }
/*      */           
/*      */           else
/*      */           
/*      */           { 
/*      */             
/* 1826 */             x ^= UnsafeUtil.getByte(tempPos++) << 56L;
/* 1827 */             x ^= 0xFE03F80FE03F80L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1836 */             if (x < 0L && 
/* 1837 */               UnsafeUtil.getByte(tempPos++) < 0L)
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1845 */               return readRawVarint64SlowPath(); }  }  this.pos = tempPos; return x; }  }  return readRawVarint64SlowPath();
/*      */     }
/*      */ 
/*      */     
/*      */     long readRawVarint64SlowPath() throws IOException {
/* 1850 */       long result = 0L;
/* 1851 */       for (int shift = 0; shift < 64; shift += 7) {
/* 1852 */         byte b = readRawByte();
/* 1853 */         result |= (b & Byte.MAX_VALUE) << shift;
/* 1854 */         if ((b & 0x80) == 0) {
/* 1855 */           return result;
/*      */         }
/*      */       } 
/* 1858 */       throw InvalidProtocolBufferException.malformedVarint();
/*      */     }
/*      */ 
/*      */     
/*      */     public int readRawLittleEndian32() throws IOException {
/* 1863 */       long tempPos = this.pos;
/*      */       
/* 1865 */       if (this.limit - tempPos < 4L) {
/* 1866 */         throw InvalidProtocolBufferException.truncatedMessage();
/*      */       }
/*      */       
/* 1869 */       this.pos = tempPos + 4L;
/* 1870 */       return UnsafeUtil.getByte(tempPos) & 0xFF | (
/* 1871 */         UnsafeUtil.getByte(tempPos + 1L) & 0xFF) << 8 | (
/* 1872 */         UnsafeUtil.getByte(tempPos + 2L) & 0xFF) << 16 | (
/* 1873 */         UnsafeUtil.getByte(tempPos + 3L) & 0xFF) << 24;
/*      */     }
/*      */ 
/*      */     
/*      */     public long readRawLittleEndian64() throws IOException {
/* 1878 */       long tempPos = this.pos;
/*      */       
/* 1880 */       if (this.limit - tempPos < 8L) {
/* 1881 */         throw InvalidProtocolBufferException.truncatedMessage();
/*      */       }
/*      */       
/* 1884 */       this.pos = tempPos + 8L;
/* 1885 */       return UnsafeUtil.getByte(tempPos) & 0xFFL | (
/* 1886 */         UnsafeUtil.getByte(tempPos + 1L) & 0xFFL) << 8L | (
/* 1887 */         UnsafeUtil.getByte(tempPos + 2L) & 0xFFL) << 16L | (
/* 1888 */         UnsafeUtil.getByte(tempPos + 3L) & 0xFFL) << 24L | (
/* 1889 */         UnsafeUtil.getByte(tempPos + 4L) & 0xFFL) << 32L | (
/* 1890 */         UnsafeUtil.getByte(tempPos + 5L) & 0xFFL) << 40L | (
/* 1891 */         UnsafeUtil.getByte(tempPos + 6L) & 0xFFL) << 48L | (
/* 1892 */         UnsafeUtil.getByte(tempPos + 7L) & 0xFFL) << 56L;
/*      */     }
/*      */ 
/*      */     
/*      */     public void enableAliasing(boolean enabled) {
/* 1897 */       this.enableAliasing = enabled;
/*      */     }
/*      */ 
/*      */     
/*      */     public void resetSizeCounter() {
/* 1902 */       this.startPos = this.pos;
/*      */     }
/*      */ 
/*      */     
/*      */     public int pushLimit(int byteLimit) throws InvalidProtocolBufferException {
/* 1907 */       if (byteLimit < 0) {
/* 1908 */         throw InvalidProtocolBufferException.negativeSize();
/*      */       }
/* 1910 */       byteLimit += getTotalBytesRead();
/* 1911 */       int oldLimit = this.currentLimit;
/* 1912 */       if (byteLimit > oldLimit) {
/* 1913 */         throw InvalidProtocolBufferException.truncatedMessage();
/*      */       }
/* 1915 */       this.currentLimit = byteLimit;
/*      */       
/* 1917 */       recomputeBufferSizeAfterLimit();
/*      */       
/* 1919 */       return oldLimit;
/*      */     }
/*      */ 
/*      */     
/*      */     public void popLimit(int oldLimit) {
/* 1924 */       this.currentLimit = oldLimit;
/* 1925 */       recomputeBufferSizeAfterLimit();
/*      */     }
/*      */ 
/*      */     
/*      */     public int getBytesUntilLimit() {
/* 1930 */       if (this.currentLimit == Integer.MAX_VALUE) {
/* 1931 */         return -1;
/*      */       }
/*      */       
/* 1934 */       return this.currentLimit - getTotalBytesRead();
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isAtEnd() throws IOException {
/* 1939 */       return (this.pos == this.limit);
/*      */     }
/*      */ 
/*      */     
/*      */     public int getTotalBytesRead() {
/* 1944 */       return (int)(this.pos - this.startPos);
/*      */     }
/*      */ 
/*      */     
/*      */     public byte readRawByte() throws IOException {
/* 1949 */       if (this.pos == this.limit) {
/* 1950 */         throw InvalidProtocolBufferException.truncatedMessage();
/*      */       }
/* 1952 */       return UnsafeUtil.getByte(this.pos++);
/*      */     }
/*      */ 
/*      */     
/*      */     public byte[] readRawBytes(int length) throws IOException {
/* 1957 */       if (length >= 0 && length <= remaining()) {
/* 1958 */         byte[] bytes = new byte[length];
/* 1959 */         slice(this.pos, this.pos + length).get(bytes);
/* 1960 */         this.pos += length;
/* 1961 */         return bytes;
/*      */       } 
/*      */       
/* 1964 */       if (length <= 0) {
/* 1965 */         if (length == 0) {
/* 1966 */           return Internal.EMPTY_BYTE_ARRAY;
/*      */         }
/* 1968 */         throw InvalidProtocolBufferException.negativeSize();
/*      */       } 
/*      */ 
/*      */       
/* 1972 */       throw InvalidProtocolBufferException.truncatedMessage();
/*      */     }
/*      */ 
/*      */     
/*      */     public void skipRawBytes(int length) throws IOException {
/* 1977 */       if (length >= 0 && length <= remaining()) {
/*      */         
/* 1979 */         this.pos += length;
/*      */         
/*      */         return;
/*      */       } 
/* 1983 */       if (length < 0) {
/* 1984 */         throw InvalidProtocolBufferException.negativeSize();
/*      */       }
/* 1986 */       throw InvalidProtocolBufferException.truncatedMessage();
/*      */     }
/*      */     
/*      */     private void recomputeBufferSizeAfterLimit() {
/* 1990 */       this.limit += this.bufferSizeAfterLimit;
/* 1991 */       int bufferEnd = (int)(this.limit - this.startPos);
/* 1992 */       if (bufferEnd > this.currentLimit) {
/*      */         
/* 1994 */         this.bufferSizeAfterLimit = bufferEnd - this.currentLimit;
/* 1995 */         this.limit -= this.bufferSizeAfterLimit;
/*      */       } else {
/* 1997 */         this.bufferSizeAfterLimit = 0;
/*      */       } 
/*      */     }
/*      */     
/*      */     private int remaining() {
/* 2002 */       return (int)(this.limit - this.pos);
/*      */     }
/*      */     
/*      */     private int bufferPos(long pos) {
/* 2006 */       return (int)(pos - this.address);
/*      */     }
/*      */     
/*      */     private ByteBuffer slice(long begin, long end) throws IOException {
/* 2010 */       int prevPos = this.buffer.position();
/* 2011 */       int prevLimit = this.buffer.limit();
/*      */       try {
/* 2013 */         this.buffer.position(bufferPos(begin));
/* 2014 */         this.buffer.limit(bufferPos(end));
/* 2015 */         return this.buffer.slice();
/* 2016 */       } catch (IllegalArgumentException e) {
/* 2017 */         throw InvalidProtocolBufferException.truncatedMessage();
/*      */       } finally {
/* 2019 */         this.buffer.position(prevPos);
/* 2020 */         this.buffer.limit(prevLimit);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static final class StreamDecoder
/*      */     extends CodedInputStream
/*      */   {
/*      */     private final InputStream input;
/*      */ 
/*      */     
/*      */     private final byte[] buffer;
/*      */ 
/*      */     
/*      */     private int bufferSize;
/*      */ 
/*      */     
/*      */     private int bufferSizeAfterLimit;
/*      */     
/*      */     private int pos;
/*      */     
/*      */     private int lastTag;
/*      */     
/*      */     private int totalBytesRetired;
/*      */     
/* 2047 */     private int currentLimit = Integer.MAX_VALUE;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private RefillCallback refillCallback;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int readTag() throws IOException {
/* 2060 */       if (isAtEnd()) {
/* 2061 */         this.lastTag = 0;
/* 2062 */         return 0;
/*      */       } 
/*      */       
/* 2065 */       this.lastTag = readRawVarint32();
/* 2066 */       if (WireFormat.getTagFieldNumber(this.lastTag) == 0)
/*      */       {
/*      */         
/* 2069 */         throw InvalidProtocolBufferException.invalidTag();
/*      */       }
/* 2071 */       return this.lastTag;
/*      */     }
/*      */ 
/*      */     
/*      */     public void checkLastTagWas(int value) throws InvalidProtocolBufferException {
/* 2076 */       if (this.lastTag != value) {
/* 2077 */         throw InvalidProtocolBufferException.invalidEndTag();
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public int getLastTag() {
/* 2083 */       return this.lastTag;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean skipField(int tag) throws IOException {
/* 2088 */       switch (WireFormat.getTagWireType(tag)) {
/*      */         case 0:
/* 2090 */           skipRawVarint();
/* 2091 */           return true;
/*      */         case 1:
/* 2093 */           skipRawBytes(8);
/* 2094 */           return true;
/*      */         case 2:
/* 2096 */           skipRawBytes(readRawVarint32());
/* 2097 */           return true;
/*      */         case 3:
/* 2099 */           skipMessage();
/* 2100 */           checkLastTagWas(
/* 2101 */               WireFormat.makeTag(WireFormat.getTagFieldNumber(tag), 4));
/* 2102 */           return true;
/*      */         case 4:
/* 2104 */           return false;
/*      */         case 5:
/* 2106 */           skipRawBytes(4);
/* 2107 */           return true;
/*      */       } 
/* 2109 */       throw InvalidProtocolBufferException.invalidWireType();
/*      */     } public boolean skipField(int tag, CodedOutputStream output) throws IOException {
/*      */       long l;
/*      */       ByteString byteString;
/*      */       int endtag;
/*      */       int value;
/* 2115 */       switch (WireFormat.getTagWireType(tag)) {
/*      */         
/*      */         case 0:
/* 2118 */           l = readInt64();
/* 2119 */           output.writeRawVarint32(tag);
/* 2120 */           output.writeUInt64NoTag(l);
/* 2121 */           return true;
/*      */ 
/*      */         
/*      */         case 1:
/* 2125 */           l = readRawLittleEndian64();
/* 2126 */           output.writeRawVarint32(tag);
/* 2127 */           output.writeFixed64NoTag(l);
/* 2128 */           return true;
/*      */ 
/*      */         
/*      */         case 2:
/* 2132 */           byteString = readBytes();
/* 2133 */           output.writeRawVarint32(tag);
/* 2134 */           output.writeBytesNoTag(byteString);
/* 2135 */           return true;
/*      */ 
/*      */         
/*      */         case 3:
/* 2139 */           output.writeRawVarint32(tag);
/* 2140 */           skipMessage(output);
/*      */           
/* 2142 */           endtag = WireFormat.makeTag(
/* 2143 */               WireFormat.getTagFieldNumber(tag), 4);
/* 2144 */           checkLastTagWas(endtag);
/* 2145 */           output.writeRawVarint32(endtag);
/* 2146 */           return true;
/*      */ 
/*      */         
/*      */         case 4:
/* 2150 */           return false;
/*      */ 
/*      */         
/*      */         case 5:
/* 2154 */           value = readRawLittleEndian32();
/* 2155 */           output.writeRawVarint32(tag);
/* 2156 */           output.writeFixed32NoTag(value);
/* 2157 */           return true;
/*      */       } 
/*      */       
/* 2160 */       throw InvalidProtocolBufferException.invalidWireType();
/*      */     }
/*      */ 
/*      */     
/*      */     public void skipMessage() throws IOException {
/*      */       int tag;
/*      */       do {
/* 2167 */         tag = readTag();
/* 2168 */       } while (tag != 0 && skipField(tag));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void skipMessage(CodedOutputStream output) throws IOException {
/*      */       int tag;
/*      */       do {
/* 2177 */         tag = readTag();
/* 2178 */       } while (tag != 0 && skipField(tag, output));
/*      */     }
/*      */     
/*      */     private static interface RefillCallback {
/*      */       void onRefill();
/*      */     }
/*      */     
/*      */     private class SkippedDataSink implements RefillCallback {
/* 2186 */       private int lastPos = StreamDecoder.this.pos;
/*      */       
/*      */       private ByteArrayOutputStream byteArrayStream;
/*      */       
/*      */       public void onRefill() {
/* 2191 */         if (this.byteArrayStream == null) {
/* 2192 */           this.byteArrayStream = new ByteArrayOutputStream();
/*      */         }
/* 2194 */         this.byteArrayStream.write(StreamDecoder.this.buffer, this.lastPos, StreamDecoder.this.pos - this.lastPos);
/* 2195 */         this.lastPos = 0;
/*      */       }
/*      */ 
/*      */       
/*      */       ByteBuffer getSkippedData() {
/* 2200 */         if (this.byteArrayStream == null) {
/* 2201 */           return ByteBuffer.wrap(StreamDecoder.this.buffer, this.lastPos, StreamDecoder.this.pos - this.lastPos);
/*      */         }
/* 2203 */         this.byteArrayStream.write(StreamDecoder.this.buffer, this.lastPos, StreamDecoder.this.pos);
/* 2204 */         return ByteBuffer.wrap(this.byteArrayStream.toByteArray());
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public double readDouble() throws IOException {
/* 2214 */       return Double.longBitsToDouble(readRawLittleEndian64());
/*      */     }
/*      */ 
/*      */     
/*      */     public float readFloat() throws IOException {
/* 2219 */       return Float.intBitsToFloat(readRawLittleEndian32());
/*      */     }
/*      */ 
/*      */     
/*      */     public long readUInt64() throws IOException {
/* 2224 */       return readRawVarint64();
/*      */     }
/*      */ 
/*      */     
/*      */     public long readInt64() throws IOException {
/* 2229 */       return readRawVarint64();
/*      */     }
/*      */ 
/*      */     
/*      */     public int readInt32() throws IOException {
/* 2234 */       return readRawVarint32();
/*      */     }
/*      */ 
/*      */     
/*      */     public long readFixed64() throws IOException {
/* 2239 */       return readRawLittleEndian64();
/*      */     }
/*      */ 
/*      */     
/*      */     public int readFixed32() throws IOException {
/* 2244 */       return readRawLittleEndian32();
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean readBool() throws IOException {
/* 2249 */       return (readRawVarint64() != 0L);
/*      */     }
/*      */ 
/*      */     
/*      */     public String readString() throws IOException {
/* 2254 */       int size = readRawVarint32();
/* 2255 */       if (size > 0 && size <= this.bufferSize - this.pos) {
/*      */ 
/*      */         
/* 2258 */         String result = new String(this.buffer, this.pos, size, Internal.UTF_8);
/* 2259 */         this.pos += size;
/* 2260 */         return result;
/*      */       } 
/* 2262 */       if (size == 0) {
/* 2263 */         return "";
/*      */       }
/* 2265 */       if (size <= this.bufferSize) {
/* 2266 */         refillBuffer(size);
/* 2267 */         String result = new String(this.buffer, this.pos, size, Internal.UTF_8);
/* 2268 */         this.pos += size;
/* 2269 */         return result;
/*      */       } 
/*      */       
/* 2272 */       return new String(readRawBytesSlowPath(size, false), Internal.UTF_8);
/*      */     }
/*      */     
/*      */     public String readStringRequireUtf8() throws IOException {
/*      */       byte[] bytes;
/* 2277 */       int tempPos, size = readRawVarint32();
/*      */       
/* 2279 */       int oldPos = this.pos;
/*      */       
/* 2281 */       if (size <= this.bufferSize - oldPos && size > 0)
/*      */       
/*      */       { 
/* 2284 */         bytes = this.buffer;
/* 2285 */         this.pos = oldPos + size;
/* 2286 */         tempPos = oldPos; }
/* 2287 */       else { if (size == 0)
/* 2288 */           return ""; 
/* 2289 */         if (size <= this.bufferSize) {
/* 2290 */           refillBuffer(size);
/* 2291 */           bytes = this.buffer;
/* 2292 */           tempPos = 0;
/* 2293 */           this.pos = tempPos + size;
/*      */         } else {
/*      */           
/* 2296 */           bytes = readRawBytesSlowPath(size, false);
/* 2297 */           tempPos = 0;
/*      */         }  }
/* 2299 */        return Utf8.decodeUtf8(bytes, tempPos, size);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void readGroup(int fieldNumber, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 2308 */       if (this.recursionDepth >= this.recursionLimit) {
/* 2309 */         throw InvalidProtocolBufferException.recursionLimitExceeded();
/*      */       }
/* 2311 */       this.recursionDepth++;
/* 2312 */       builder.mergeFrom(this, extensionRegistry);
/* 2313 */       checkLastTagWas(WireFormat.makeTag(fieldNumber, 4));
/* 2314 */       this.recursionDepth--;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public <T extends MessageLite> T readGroup(int fieldNumber, Parser<T> parser, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 2324 */       if (this.recursionDepth >= this.recursionLimit) {
/* 2325 */         throw InvalidProtocolBufferException.recursionLimitExceeded();
/*      */       }
/* 2327 */       this.recursionDepth++;
/* 2328 */       MessageLite messageLite = (MessageLite)parser.parsePartialFrom(this, extensionRegistry);
/* 2329 */       checkLastTagWas(WireFormat.makeTag(fieldNumber, 4));
/* 2330 */       this.recursionDepth--;
/* 2331 */       return (T)messageLite;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     @Deprecated
/*      */     public void readUnknownGroup(int fieldNumber, MessageLite.Builder builder) throws IOException {
/* 2338 */       readGroup(fieldNumber, builder, ExtensionRegistryLite.getEmptyRegistry());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 2345 */       int length = readRawVarint32();
/* 2346 */       if (this.recursionDepth >= this.recursionLimit) {
/* 2347 */         throw InvalidProtocolBufferException.recursionLimitExceeded();
/*      */       }
/* 2349 */       int oldLimit = pushLimit(length);
/* 2350 */       this.recursionDepth++;
/* 2351 */       builder.mergeFrom(this, extensionRegistry);
/* 2352 */       checkLastTagWas(0);
/* 2353 */       this.recursionDepth--;
/* 2354 */       popLimit(oldLimit);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 2361 */       int length = readRawVarint32();
/* 2362 */       if (this.recursionDepth >= this.recursionLimit) {
/* 2363 */         throw InvalidProtocolBufferException.recursionLimitExceeded();
/*      */       }
/* 2365 */       int oldLimit = pushLimit(length);
/* 2366 */       this.recursionDepth++;
/* 2367 */       MessageLite messageLite = (MessageLite)parser.parsePartialFrom(this, extensionRegistry);
/* 2368 */       checkLastTagWas(0);
/* 2369 */       this.recursionDepth--;
/* 2370 */       popLimit(oldLimit);
/* 2371 */       return (T)messageLite;
/*      */     }
/*      */ 
/*      */     
/*      */     public ByteString readBytes() throws IOException {
/* 2376 */       int size = readRawVarint32();
/* 2377 */       if (size <= this.bufferSize - this.pos && size > 0) {
/*      */ 
/*      */         
/* 2380 */         ByteString result = ByteString.copyFrom(this.buffer, this.pos, size);
/* 2381 */         this.pos += size;
/* 2382 */         return result;
/*      */       } 
/* 2384 */       if (size == 0) {
/* 2385 */         return ByteString.EMPTY;
/*      */       }
/* 2387 */       return readBytesSlowPath(size);
/*      */     }
/*      */ 
/*      */     
/*      */     public byte[] readByteArray() throws IOException {
/* 2392 */       int size = readRawVarint32();
/* 2393 */       if (size <= this.bufferSize - this.pos && size > 0) {
/*      */ 
/*      */         
/* 2396 */         byte[] result = Arrays.copyOfRange(this.buffer, this.pos, this.pos + size);
/* 2397 */         this.pos += size;
/* 2398 */         return result;
/*      */       } 
/*      */ 
/*      */       
/* 2402 */       return readRawBytesSlowPath(size, false);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public ByteBuffer readByteBuffer() throws IOException {
/* 2408 */       int size = readRawVarint32();
/* 2409 */       if (size <= this.bufferSize - this.pos && size > 0) {
/*      */         
/* 2411 */         ByteBuffer result = ByteBuffer.wrap(Arrays.copyOfRange(this.buffer, this.pos, this.pos + size));
/* 2412 */         this.pos += size;
/* 2413 */         return result;
/*      */       } 
/* 2415 */       if (size == 0) {
/* 2416 */         return Internal.EMPTY_BYTE_BUFFER;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2422 */       return ByteBuffer.wrap(readRawBytesSlowPath(size, true));
/*      */     }
/*      */ 
/*      */     
/*      */     public int readUInt32() throws IOException {
/* 2427 */       return readRawVarint32();
/*      */     }
/*      */ 
/*      */     
/*      */     public int readEnum() throws IOException {
/* 2432 */       return readRawVarint32();
/*      */     }
/*      */ 
/*      */     
/*      */     public int readSFixed32() throws IOException {
/* 2437 */       return readRawLittleEndian32();
/*      */     }
/*      */ 
/*      */     
/*      */     public long readSFixed64() throws IOException {
/* 2442 */       return readRawLittleEndian64();
/*      */     }
/*      */ 
/*      */     
/*      */     public int readSInt32() throws IOException {
/* 2447 */       return decodeZigZag32(readRawVarint32());
/*      */     }
/*      */ 
/*      */     
/*      */     public long readSInt64() throws IOException {
/* 2452 */       return decodeZigZag64(readRawVarint64());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int readRawVarint32() throws IOException {
/* 2462 */       int tempPos = this.pos;
/*      */       
/* 2464 */       if (this.bufferSize != tempPos)
/*      */       
/*      */       { 
/*      */         
/* 2468 */         byte[] buffer = this.buffer;
/*      */         int x;
/* 2470 */         if ((x = buffer[tempPos++]) >= 0) {
/* 2471 */           this.pos = tempPos;
/* 2472 */           return x;
/* 2473 */         }  if (this.bufferSize - tempPos >= 9)
/*      */         
/* 2475 */         { if ((x ^= buffer[tempPos++] << 7) < 0)
/* 2476 */           { x ^= 0xFFFFFF80; }
/* 2477 */           else if ((x ^= buffer[tempPos++] << 14) >= 0)
/* 2478 */           { x ^= 0x3F80; }
/* 2479 */           else if ((x ^= buffer[tempPos++] << 21) < 0)
/* 2480 */           { x ^= 0xFFE03F80; }
/*      */           else
/* 2482 */           { int y = buffer[tempPos++];
/* 2483 */             x ^= y << 28;
/* 2484 */             x ^= 0xFE03F80;
/* 2485 */             if (y < 0 && buffer[tempPos++] < 0 && buffer[tempPos++] < 0 && buffer[tempPos++] < 0 && buffer[tempPos++] < 0 && buffer[tempPos++] < 0)
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 2497 */               return (int)readRawVarint64SlowPath(); }  }  this.pos = tempPos; return x; }  }  return (int)readRawVarint64SlowPath();
/*      */     }
/*      */     
/*      */     private void skipRawVarint() throws IOException {
/* 2501 */       if (this.bufferSize - this.pos >= 10) {
/* 2502 */         skipRawVarintFastPath();
/*      */       } else {
/* 2504 */         skipRawVarintSlowPath();
/*      */       } 
/*      */     }
/*      */     
/*      */     private void skipRawVarintFastPath() throws IOException {
/* 2509 */       for (int i = 0; i < 10; i++) {
/* 2510 */         if (this.buffer[this.pos++] >= 0) {
/*      */           return;
/*      */         }
/*      */       } 
/* 2514 */       throw InvalidProtocolBufferException.malformedVarint();
/*      */     }
/*      */     
/*      */     private void skipRawVarintSlowPath() throws IOException {
/* 2518 */       for (int i = 0; i < 10; i++) {
/* 2519 */         if (readRawByte() >= 0) {
/*      */           return;
/*      */         }
/*      */       } 
/* 2523 */       throw InvalidProtocolBufferException.malformedVarint();
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
/*      */     public long readRawVarint64() throws IOException {
/* 2541 */       int tempPos = this.pos;
/*      */       
/* 2543 */       if (this.bufferSize != tempPos)
/*      */       
/*      */       { 
/*      */         
/* 2547 */         byte[] buffer = this.buffer;
/*      */         
/*      */         int y;
/* 2550 */         if ((y = buffer[tempPos++]) >= 0) {
/* 2551 */           this.pos = tempPos;
/* 2552 */           return y;
/* 2553 */         }  if (this.bufferSize - tempPos >= 9)
/*      */         { long x;
/* 2555 */           if ((y ^= buffer[tempPos++] << 7) < 0)
/* 2556 */           { x = (y ^ 0xFFFFFF80); }
/* 2557 */           else if ((y ^= buffer[tempPos++] << 14) >= 0)
/* 2558 */           { x = (y ^ 0x3F80); }
/* 2559 */           else if ((y ^= buffer[tempPos++] << 21) < 0)
/* 2560 */           { x = (y ^ 0xFFE03F80); }
/* 2561 */           else if ((x = y ^ buffer[tempPos++] << 28L) >= 0L)
/* 2562 */           { x ^= 0xFE03F80L; }
/* 2563 */           else if ((x ^= buffer[tempPos++] << 35L) < 0L)
/* 2564 */           { x ^= 0xFFFFFFF80FE03F80L; }
/* 2565 */           else if ((x ^= buffer[tempPos++] << 42L) >= 0L)
/* 2566 */           { x ^= 0x3F80FE03F80L; }
/* 2567 */           else if ((x ^= buffer[tempPos++] << 49L) < 0L)
/* 2568 */           { x ^= 0xFFFE03F80FE03F80L;
/*      */ 
/*      */             
/*      */              }
/*      */           
/*      */           else
/*      */           
/*      */           { 
/*      */             
/* 2577 */             x ^= buffer[tempPos++] << 56L;
/* 2578 */             x ^= 0xFE03F80FE03F80L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 2587 */             if (x < 0L && 
/* 2588 */               buffer[tempPos++] < 0L)
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 2596 */               return readRawVarint64SlowPath(); }  }  this.pos = tempPos; return x; }  }  return readRawVarint64SlowPath();
/*      */     }
/*      */ 
/*      */     
/*      */     long readRawVarint64SlowPath() throws IOException {
/* 2601 */       long result = 0L;
/* 2602 */       for (int shift = 0; shift < 64; shift += 7) {
/* 2603 */         byte b = readRawByte();
/* 2604 */         result |= (b & Byte.MAX_VALUE) << shift;
/* 2605 */         if ((b & 0x80) == 0) {
/* 2606 */           return result;
/*      */         }
/*      */       } 
/* 2609 */       throw InvalidProtocolBufferException.malformedVarint();
/*      */     }
/*      */ 
/*      */     
/*      */     public int readRawLittleEndian32() throws IOException {
/* 2614 */       int tempPos = this.pos;
/*      */       
/* 2616 */       if (this.bufferSize - tempPos < 4) {
/* 2617 */         refillBuffer(4);
/* 2618 */         tempPos = this.pos;
/*      */       } 
/*      */       
/* 2621 */       byte[] buffer = this.buffer;
/* 2622 */       this.pos = tempPos + 4;
/* 2623 */       return buffer[tempPos] & 0xFF | (buffer[tempPos + 1] & 0xFF) << 8 | (buffer[tempPos + 2] & 0xFF) << 16 | (buffer[tempPos + 3] & 0xFF) << 24;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public long readRawLittleEndian64() throws IOException {
/* 2631 */       int tempPos = this.pos;
/*      */       
/* 2633 */       if (this.bufferSize - tempPos < 8) {
/* 2634 */         refillBuffer(8);
/* 2635 */         tempPos = this.pos;
/*      */       } 
/*      */       
/* 2638 */       byte[] buffer = this.buffer;
/* 2639 */       this.pos = tempPos + 8;
/* 2640 */       return buffer[tempPos] & 0xFFL | (buffer[tempPos + 1] & 0xFFL) << 8L | (buffer[tempPos + 2] & 0xFFL) << 16L | (buffer[tempPos + 3] & 0xFFL) << 24L | (buffer[tempPos + 4] & 0xFFL) << 32L | (buffer[tempPos + 5] & 0xFFL) << 40L | (buffer[tempPos + 6] & 0xFFL) << 48L | (buffer[tempPos + 7] & 0xFFL) << 56L;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void enableAliasing(boolean enabled) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void resetSizeCounter() {
/* 2659 */       this.totalBytesRetired = -this.pos;
/*      */     }
/*      */ 
/*      */     
/*      */     public int pushLimit(int byteLimit) throws InvalidProtocolBufferException {
/* 2664 */       if (byteLimit < 0) {
/* 2665 */         throw InvalidProtocolBufferException.negativeSize();
/*      */       }
/* 2667 */       byteLimit += this.totalBytesRetired + this.pos;
/* 2668 */       int oldLimit = this.currentLimit;
/* 2669 */       if (byteLimit > oldLimit) {
/* 2670 */         throw InvalidProtocolBufferException.truncatedMessage();
/*      */       }
/* 2672 */       this.currentLimit = byteLimit;
/*      */       
/* 2674 */       recomputeBufferSizeAfterLimit();
/*      */       
/* 2676 */       return oldLimit;
/*      */     }
/*      */     
/*      */     private void recomputeBufferSizeAfterLimit() {
/* 2680 */       this.bufferSize += this.bufferSizeAfterLimit;
/* 2681 */       int bufferEnd = this.totalBytesRetired + this.bufferSize;
/* 2682 */       if (bufferEnd > this.currentLimit) {
/*      */         
/* 2684 */         this.bufferSizeAfterLimit = bufferEnd - this.currentLimit;
/* 2685 */         this.bufferSize -= this.bufferSizeAfterLimit;
/*      */       } else {
/* 2687 */         this.bufferSizeAfterLimit = 0;
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void popLimit(int oldLimit) {
/* 2693 */       this.currentLimit = oldLimit;
/* 2694 */       recomputeBufferSizeAfterLimit();
/*      */     }
/*      */ 
/*      */     
/*      */     public int getBytesUntilLimit() {
/* 2699 */       if (this.currentLimit == Integer.MAX_VALUE) {
/* 2700 */         return -1;
/*      */       }
/*      */       
/* 2703 */       int currentAbsolutePosition = this.totalBytesRetired + this.pos;
/* 2704 */       return this.currentLimit - currentAbsolutePosition;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isAtEnd() throws IOException {
/* 2709 */       return (this.pos == this.bufferSize && !tryRefillBuffer(1));
/*      */     }
/*      */ 
/*      */     
/*      */     public int getTotalBytesRead() {
/* 2714 */       return this.totalBytesRetired + this.pos;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private StreamDecoder(InputStream input, int bufferSize) {
/* 2721 */       this.refillCallback = null;
/*      */       Internal.checkNotNull(input, "input");
/*      */       this.input = input;
/*      */       this.buffer = new byte[bufferSize];
/*      */       this.bufferSize = 0;
/*      */       this.pos = 0;
/*      */       this.totalBytesRetired = 0;
/*      */     }
/*      */ 
/*      */     
/*      */     private void refillBuffer(int n) throws IOException {
/* 2732 */       if (!tryRefillBuffer(n)) {
/*      */ 
/*      */         
/* 2735 */         if (n > this.sizeLimit - this.totalBytesRetired - this.pos) {
/* 2736 */           throw InvalidProtocolBufferException.sizeLimitExceeded();
/*      */         }
/* 2738 */         throw InvalidProtocolBufferException.truncatedMessage();
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
/*      */     private boolean tryRefillBuffer(int n) throws IOException {
/* 2752 */       if (this.pos + n <= this.bufferSize) {
/* 2753 */         throw new IllegalStateException("refillBuffer() called when " + n + " bytes were already available in buffer");
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2760 */       if (n > this.sizeLimit - this.totalBytesRetired - this.pos) {
/* 2761 */         return false;
/*      */       }
/*      */ 
/*      */       
/* 2765 */       if (this.totalBytesRetired + this.pos + n > this.currentLimit)
/*      */       {
/* 2767 */         return false;
/*      */       }
/*      */       
/* 2770 */       if (this.refillCallback != null) {
/* 2771 */         this.refillCallback.onRefill();
/*      */       }
/*      */       
/* 2774 */       int tempPos = this.pos;
/* 2775 */       if (tempPos > 0) {
/* 2776 */         if (this.bufferSize > tempPos) {
/* 2777 */           System.arraycopy(this.buffer, tempPos, this.buffer, 0, this.bufferSize - tempPos);
/*      */         }
/* 2779 */         this.totalBytesRetired += tempPos;
/* 2780 */         this.bufferSize -= tempPos;
/* 2781 */         this.pos = 0;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2786 */       int bytesRead = this.input.read(this.buffer, this.bufferSize, 
/*      */ 
/*      */           
/* 2789 */           Math.min(this.buffer.length - this.bufferSize, this.sizeLimit - this.totalBytesRetired - this.bufferSize));
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2794 */       if (bytesRead == 0 || bytesRead < -1 || bytesRead > this.buffer.length) {
/* 2795 */         throw new IllegalStateException(this.input
/* 2796 */             .getClass() + "#read(byte[]) returned invalid result: " + bytesRead + "\nThe InputStream implementation is buggy.");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2801 */       if (bytesRead > 0) {
/* 2802 */         this.bufferSize += bytesRead;
/* 2803 */         recomputeBufferSizeAfterLimit();
/* 2804 */         return (this.bufferSize >= n) ? true : tryRefillBuffer(n);
/*      */       } 
/*      */       
/* 2807 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public byte readRawByte() throws IOException {
/* 2812 */       if (this.pos == this.bufferSize) {
/* 2813 */         refillBuffer(1);
/*      */       }
/* 2815 */       return this.buffer[this.pos++];
/*      */     }
/*      */ 
/*      */     
/*      */     public byte[] readRawBytes(int size) throws IOException {
/* 2820 */       int tempPos = this.pos;
/* 2821 */       if (size <= this.bufferSize - tempPos && size > 0) {
/* 2822 */         this.pos = tempPos + size;
/* 2823 */         return Arrays.copyOfRange(this.buffer, tempPos, tempPos + size);
/*      */       } 
/*      */       
/* 2826 */       return readRawBytesSlowPath(size, false);
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
/*      */     private byte[] readRawBytesSlowPath(int size, boolean ensureNoLeakedReferences) throws IOException {
/* 2840 */       byte[] result = readRawBytesSlowPathOneChunk(size);
/* 2841 */       if (result != null) {
/* 2842 */         return ensureNoLeakedReferences ? (byte[])result.clone() : result;
/*      */       }
/*      */       
/* 2845 */       int originalBufferPos = this.pos;
/* 2846 */       int bufferedBytes = this.bufferSize - this.pos;
/*      */ 
/*      */       
/* 2849 */       this.totalBytesRetired += this.bufferSize;
/* 2850 */       this.pos = 0;
/* 2851 */       this.bufferSize = 0;
/*      */ 
/*      */       
/* 2854 */       int sizeLeft = size - bufferedBytes;
/*      */ 
/*      */ 
/*      */       
/* 2858 */       List<byte[]> chunks = readRawBytesSlowPathRemainingChunks(sizeLeft);
/*      */ 
/*      */       
/* 2861 */       byte[] bytes = new byte[size];
/*      */ 
/*      */       
/* 2864 */       System.arraycopy(this.buffer, originalBufferPos, bytes, 0, bufferedBytes);
/*      */ 
/*      */       
/* 2867 */       int tempPos = bufferedBytes;
/* 2868 */       for (byte[] chunk : chunks) {
/* 2869 */         System.arraycopy(chunk, 0, bytes, tempPos, chunk.length);
/* 2870 */         tempPos += chunk.length;
/*      */       } 
/*      */ 
/*      */       
/* 2874 */       return bytes;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private byte[] readRawBytesSlowPathOneChunk(int size) throws IOException {
/* 2884 */       if (size == 0) {
/* 2885 */         return Internal.EMPTY_BYTE_ARRAY;
/*      */       }
/* 2887 */       if (size < 0) {
/* 2888 */         throw InvalidProtocolBufferException.negativeSize();
/*      */       }
/*      */ 
/*      */       
/* 2892 */       int currentMessageSize = this.totalBytesRetired + this.pos + size;
/* 2893 */       if (currentMessageSize - this.sizeLimit > 0) {
/* 2894 */         throw InvalidProtocolBufferException.sizeLimitExceeded();
/*      */       }
/*      */ 
/*      */       
/* 2898 */       if (currentMessageSize > this.currentLimit) {
/*      */         
/* 2900 */         skipRawBytes(this.currentLimit - this.totalBytesRetired - this.pos);
/* 2901 */         throw InvalidProtocolBufferException.truncatedMessage();
/*      */       } 
/*      */       
/* 2904 */       int bufferedBytes = this.bufferSize - this.pos;
/*      */       
/* 2906 */       int sizeLeft = size - bufferedBytes;
/*      */       
/* 2908 */       if (sizeLeft < 4096 || sizeLeft <= this.input.available()) {
/*      */ 
/*      */         
/* 2911 */         byte[] bytes = new byte[size];
/*      */ 
/*      */         
/* 2914 */         System.arraycopy(this.buffer, this.pos, bytes, 0, bufferedBytes);
/* 2915 */         this.totalBytesRetired += this.bufferSize;
/* 2916 */         this.pos = 0;
/* 2917 */         this.bufferSize = 0;
/*      */ 
/*      */         
/* 2920 */         int tempPos = bufferedBytes;
/* 2921 */         while (tempPos < bytes.length) {
/* 2922 */           int n = this.input.read(bytes, tempPos, size - tempPos);
/* 2923 */           if (n == -1) {
/* 2924 */             throw InvalidProtocolBufferException.truncatedMessage();
/*      */           }
/* 2926 */           this.totalBytesRetired += n;
/* 2927 */           tempPos += n;
/*      */         } 
/*      */         
/* 2930 */         return bytes;
/*      */       } 
/*      */       
/* 2933 */       return null;
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
/*      */     private List<byte[]> readRawBytesSlowPathRemainingChunks(int sizeLeft) throws IOException {
/* 2949 */       List<byte[]> chunks = (List)new ArrayList<>();
/*      */       
/* 2951 */       while (sizeLeft > 0) {
/*      */         
/* 2953 */         byte[] chunk = new byte[Math.min(sizeLeft, 4096)];
/* 2954 */         int tempPos = 0;
/* 2955 */         while (tempPos < chunk.length) {
/* 2956 */           int n = this.input.read(chunk, tempPos, chunk.length - tempPos);
/* 2957 */           if (n == -1) {
/* 2958 */             throw InvalidProtocolBufferException.truncatedMessage();
/*      */           }
/* 2960 */           this.totalBytesRetired += n;
/* 2961 */           tempPos += n;
/*      */         } 
/* 2963 */         sizeLeft -= chunk.length;
/* 2964 */         chunks.add(chunk);
/*      */       } 
/*      */       
/* 2967 */       return chunks;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private ByteString readBytesSlowPath(int size) throws IOException {
/* 2975 */       byte[] result = readRawBytesSlowPathOneChunk(size);
/* 2976 */       if (result != null)
/*      */       {
/*      */         
/* 2979 */         return ByteString.copyFrom(result);
/*      */       }
/*      */       
/* 2982 */       int originalBufferPos = this.pos;
/* 2983 */       int bufferedBytes = this.bufferSize - this.pos;
/*      */ 
/*      */       
/* 2986 */       this.totalBytesRetired += this.bufferSize;
/* 2987 */       this.pos = 0;
/* 2988 */       this.bufferSize = 0;
/*      */ 
/*      */       
/* 2991 */       int sizeLeft = size - bufferedBytes;
/*      */ 
/*      */ 
/*      */       
/* 2995 */       List<byte[]> chunks = readRawBytesSlowPathRemainingChunks(sizeLeft);
/*      */ 
/*      */       
/* 2998 */       byte[] bytes = new byte[size];
/*      */ 
/*      */       
/* 3001 */       System.arraycopy(this.buffer, originalBufferPos, bytes, 0, bufferedBytes);
/*      */ 
/*      */       
/* 3004 */       int tempPos = bufferedBytes;
/* 3005 */       for (byte[] chunk : chunks) {
/* 3006 */         System.arraycopy(chunk, 0, bytes, tempPos, chunk.length);
/* 3007 */         tempPos += chunk.length;
/*      */       } 
/*      */       
/* 3010 */       return ByteString.wrap(bytes);
/*      */     }
/*      */ 
/*      */     
/*      */     public void skipRawBytes(int size) throws IOException {
/* 3015 */       if (size <= this.bufferSize - this.pos && size >= 0) {
/*      */         
/* 3017 */         this.pos += size;
/*      */       } else {
/* 3019 */         skipRawBytesSlowPath(size);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void skipRawBytesSlowPath(int size) throws IOException {
/* 3028 */       if (size < 0) {
/* 3029 */         throw InvalidProtocolBufferException.negativeSize();
/*      */       }
/*      */       
/* 3032 */       if (this.totalBytesRetired + this.pos + size > this.currentLimit) {
/*      */         
/* 3034 */         skipRawBytes(this.currentLimit - this.totalBytesRetired - this.pos);
/*      */         
/* 3036 */         throw InvalidProtocolBufferException.truncatedMessage();
/*      */       } 
/*      */       
/* 3039 */       int totalSkipped = 0;
/* 3040 */       if (this.refillCallback == null) {
/*      */         
/* 3042 */         this.totalBytesRetired += this.pos;
/* 3043 */         totalSkipped = this.bufferSize - this.pos;
/* 3044 */         this.bufferSize = 0;
/* 3045 */         this.pos = 0;
/*      */         
/*      */         try {
/* 3048 */           while (totalSkipped < size) {
/* 3049 */             int toSkip = size - totalSkipped;
/* 3050 */             long skipped = this.input.skip(toSkip);
/* 3051 */             if (skipped < 0L || skipped > toSkip) {
/* 3052 */               throw new IllegalStateException(this.input
/* 3053 */                   .getClass() + "#skip returned invalid result: " + skipped + "\nThe InputStream implementation is buggy.");
/*      */             }
/*      */ 
/*      */             
/* 3057 */             if (skipped == 0L) {
/*      */               break;
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 3064 */             totalSkipped += (int)skipped;
/*      */           } 
/*      */         } finally {
/* 3067 */           this.totalBytesRetired += totalSkipped;
/* 3068 */           recomputeBufferSizeAfterLimit();
/*      */         } 
/*      */       } 
/* 3071 */       if (totalSkipped < size) {
/*      */         
/* 3073 */         int tempPos = this.bufferSize - this.pos;
/* 3074 */         this.pos = this.bufferSize;
/*      */ 
/*      */ 
/*      */         
/* 3078 */         refillBuffer(1);
/* 3079 */         while (size - tempPos > this.bufferSize) {
/* 3080 */           tempPos += this.bufferSize;
/* 3081 */           this.pos = this.bufferSize;
/* 3082 */           refillBuffer(1);
/*      */         } 
/*      */         
/* 3085 */         this.pos = size - tempPos;
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static final class IterableDirectByteBufferDecoder
/*      */     extends CodedInputStream
/*      */   {
/*      */     private Iterable<ByteBuffer> input;
/*      */ 
/*      */     
/*      */     private Iterator<ByteBuffer> iterator;
/*      */ 
/*      */     
/*      */     private ByteBuffer currentByteBuffer;
/*      */ 
/*      */     
/*      */     private boolean immutable;
/*      */ 
/*      */     
/*      */     private boolean enableAliasing;
/*      */ 
/*      */     
/*      */     private int totalBufferSize;
/*      */ 
/*      */     
/*      */     private int bufferSizeAfterCurrentLimit;
/*      */ 
/*      */     
/* 3116 */     private int currentLimit = Integer.MAX_VALUE;
/*      */ 
/*      */ 
/*      */     
/*      */     private int lastTag;
/*      */ 
/*      */ 
/*      */     
/*      */     private int totalBytesRead;
/*      */ 
/*      */     
/*      */     private int startOffset;
/*      */ 
/*      */     
/*      */     private long currentByteBufferPos;
/*      */ 
/*      */     
/*      */     private long currentByteBufferStartPos;
/*      */ 
/*      */     
/*      */     private long currentAddress;
/*      */ 
/*      */     
/*      */     private long currentByteBufferLimit;
/*      */ 
/*      */ 
/*      */     
/*      */     private IterableDirectByteBufferDecoder(Iterable<ByteBuffer> inputBufs, int size, boolean immutableFlag) {
/* 3144 */       this.totalBufferSize = size;
/* 3145 */       this.input = inputBufs;
/* 3146 */       this.iterator = this.input.iterator();
/* 3147 */       this.immutable = immutableFlag;
/* 3148 */       this.startOffset = this.totalBytesRead = 0;
/* 3149 */       if (size == 0) {
/* 3150 */         this.currentByteBuffer = Internal.EMPTY_BYTE_BUFFER;
/* 3151 */         this.currentByteBufferPos = 0L;
/* 3152 */         this.currentByteBufferStartPos = 0L;
/* 3153 */         this.currentByteBufferLimit = 0L;
/* 3154 */         this.currentAddress = 0L;
/*      */       } else {
/* 3156 */         tryGetNextByteBuffer();
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     private void getNextByteBuffer() throws InvalidProtocolBufferException {
/* 3162 */       if (!this.iterator.hasNext()) {
/* 3163 */         throw InvalidProtocolBufferException.truncatedMessage();
/*      */       }
/* 3165 */       tryGetNextByteBuffer();
/*      */     }
/*      */     
/*      */     private void tryGetNextByteBuffer() {
/* 3169 */       this.currentByteBuffer = this.iterator.next();
/* 3170 */       this.totalBytesRead += (int)(this.currentByteBufferPos - this.currentByteBufferStartPos);
/* 3171 */       this.currentByteBufferPos = this.currentByteBuffer.position();
/* 3172 */       this.currentByteBufferStartPos = this.currentByteBufferPos;
/* 3173 */       this.currentByteBufferLimit = this.currentByteBuffer.limit();
/* 3174 */       this.currentAddress = UnsafeUtil.addressOffset(this.currentByteBuffer);
/* 3175 */       this.currentByteBufferPos += this.currentAddress;
/* 3176 */       this.currentByteBufferStartPos += this.currentAddress;
/* 3177 */       this.currentByteBufferLimit += this.currentAddress;
/*      */     }
/*      */ 
/*      */     
/*      */     public int readTag() throws IOException {
/* 3182 */       if (isAtEnd()) {
/* 3183 */         this.lastTag = 0;
/* 3184 */         return 0;
/*      */       } 
/*      */       
/* 3187 */       this.lastTag = readRawVarint32();
/* 3188 */       if (WireFormat.getTagFieldNumber(this.lastTag) == 0)
/*      */       {
/*      */         
/* 3191 */         throw InvalidProtocolBufferException.invalidTag();
/*      */       }
/* 3193 */       return this.lastTag;
/*      */     }
/*      */ 
/*      */     
/*      */     public void checkLastTagWas(int value) throws InvalidProtocolBufferException {
/* 3198 */       if (this.lastTag != value) {
/* 3199 */         throw InvalidProtocolBufferException.invalidEndTag();
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public int getLastTag() {
/* 3205 */       return this.lastTag;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean skipField(int tag) throws IOException {
/* 3210 */       switch (WireFormat.getTagWireType(tag)) {
/*      */         case 0:
/* 3212 */           skipRawVarint();
/* 3213 */           return true;
/*      */         case 1:
/* 3215 */           skipRawBytes(8);
/* 3216 */           return true;
/*      */         case 2:
/* 3218 */           skipRawBytes(readRawVarint32());
/* 3219 */           return true;
/*      */         case 3:
/* 3221 */           skipMessage();
/* 3222 */           checkLastTagWas(
/* 3223 */               WireFormat.makeTag(WireFormat.getTagFieldNumber(tag), 4));
/* 3224 */           return true;
/*      */         case 4:
/* 3226 */           return false;
/*      */         case 5:
/* 3228 */           skipRawBytes(4);
/* 3229 */           return true;
/*      */       } 
/* 3231 */       throw InvalidProtocolBufferException.invalidWireType();
/*      */     } public boolean skipField(int tag, CodedOutputStream output) throws IOException {
/*      */       long l;
/*      */       ByteString byteString;
/*      */       int endtag;
/*      */       int value;
/* 3237 */       switch (WireFormat.getTagWireType(tag)) {
/*      */         
/*      */         case 0:
/* 3240 */           l = readInt64();
/* 3241 */           output.writeRawVarint32(tag);
/* 3242 */           output.writeUInt64NoTag(l);
/* 3243 */           return true;
/*      */ 
/*      */         
/*      */         case 1:
/* 3247 */           l = readRawLittleEndian64();
/* 3248 */           output.writeRawVarint32(tag);
/* 3249 */           output.writeFixed64NoTag(l);
/* 3250 */           return true;
/*      */ 
/*      */         
/*      */         case 2:
/* 3254 */           byteString = readBytes();
/* 3255 */           output.writeRawVarint32(tag);
/* 3256 */           output.writeBytesNoTag(byteString);
/* 3257 */           return true;
/*      */ 
/*      */         
/*      */         case 3:
/* 3261 */           output.writeRawVarint32(tag);
/* 3262 */           skipMessage(output);
/*      */           
/* 3264 */           endtag = WireFormat.makeTag(
/* 3265 */               WireFormat.getTagFieldNumber(tag), 4);
/* 3266 */           checkLastTagWas(endtag);
/* 3267 */           output.writeRawVarint32(endtag);
/* 3268 */           return true;
/*      */ 
/*      */         
/*      */         case 4:
/* 3272 */           return false;
/*      */ 
/*      */         
/*      */         case 5:
/* 3276 */           value = readRawLittleEndian32();
/* 3277 */           output.writeRawVarint32(tag);
/* 3278 */           output.writeFixed32NoTag(value);
/* 3279 */           return true;
/*      */       } 
/*      */       
/* 3282 */       throw InvalidProtocolBufferException.invalidWireType();
/*      */     }
/*      */ 
/*      */     
/*      */     public void skipMessage() throws IOException {
/*      */       int tag;
/*      */       do {
/* 3289 */         tag = readTag();
/* 3290 */       } while (tag != 0 && skipField(tag));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void skipMessage(CodedOutputStream output) throws IOException {
/*      */       int tag;
/*      */       do {
/* 3299 */         tag = readTag();
/* 3300 */       } while (tag != 0 && skipField(tag, output));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public double readDouble() throws IOException {
/* 3310 */       return Double.longBitsToDouble(readRawLittleEndian64());
/*      */     }
/*      */ 
/*      */     
/*      */     public float readFloat() throws IOException {
/* 3315 */       return Float.intBitsToFloat(readRawLittleEndian32());
/*      */     }
/*      */ 
/*      */     
/*      */     public long readUInt64() throws IOException {
/* 3320 */       return readRawVarint64();
/*      */     }
/*      */ 
/*      */     
/*      */     public long readInt64() throws IOException {
/* 3325 */       return readRawVarint64();
/*      */     }
/*      */ 
/*      */     
/*      */     public int readInt32() throws IOException {
/* 3330 */       return readRawVarint32();
/*      */     }
/*      */ 
/*      */     
/*      */     public long readFixed64() throws IOException {
/* 3335 */       return readRawLittleEndian64();
/*      */     }
/*      */ 
/*      */     
/*      */     public int readFixed32() throws IOException {
/* 3340 */       return readRawLittleEndian32();
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean readBool() throws IOException {
/* 3345 */       return (readRawVarint64() != 0L);
/*      */     }
/*      */ 
/*      */     
/*      */     public String readString() throws IOException {
/* 3350 */       int size = readRawVarint32();
/* 3351 */       if (size > 0 && size <= this.currentByteBufferLimit - this.currentByteBufferPos) {
/* 3352 */         byte[] bytes = new byte[size];
/* 3353 */         UnsafeUtil.copyMemory(this.currentByteBufferPos, bytes, 0L, size);
/* 3354 */         String result = new String(bytes, Internal.UTF_8);
/* 3355 */         this.currentByteBufferPos += size;
/* 3356 */         return result;
/* 3357 */       }  if (size > 0 && size <= remaining()) {
/*      */         
/* 3359 */         byte[] bytes = new byte[size];
/* 3360 */         readRawBytesTo(bytes, 0, size);
/* 3361 */         String result = new String(bytes, Internal.UTF_8);
/* 3362 */         return result;
/*      */       } 
/*      */       
/* 3365 */       if (size == 0) {
/* 3366 */         return "";
/*      */       }
/* 3368 */       if (size < 0) {
/* 3369 */         throw InvalidProtocolBufferException.negativeSize();
/*      */       }
/* 3371 */       throw InvalidProtocolBufferException.truncatedMessage();
/*      */     }
/*      */ 
/*      */     
/*      */     public String readStringRequireUtf8() throws IOException {
/* 3376 */       int size = readRawVarint32();
/* 3377 */       if (size > 0 && size <= this.currentByteBufferLimit - this.currentByteBufferPos) {
/* 3378 */         int bufferPos = (int)(this.currentByteBufferPos - this.currentByteBufferStartPos);
/* 3379 */         String result = Utf8.decodeUtf8(this.currentByteBuffer, bufferPos, size);
/* 3380 */         this.currentByteBufferPos += size;
/* 3381 */         return result;
/*      */       } 
/* 3383 */       if (size >= 0 && size <= remaining()) {
/* 3384 */         byte[] bytes = new byte[size];
/* 3385 */         readRawBytesTo(bytes, 0, size);
/* 3386 */         return Utf8.decodeUtf8(bytes, 0, size);
/*      */       } 
/*      */       
/* 3389 */       if (size == 0) {
/* 3390 */         return "";
/*      */       }
/* 3392 */       if (size <= 0) {
/* 3393 */         throw InvalidProtocolBufferException.negativeSize();
/*      */       }
/* 3395 */       throw InvalidProtocolBufferException.truncatedMessage();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void readGroup(int fieldNumber, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 3404 */       if (this.recursionDepth >= this.recursionLimit) {
/* 3405 */         throw InvalidProtocolBufferException.recursionLimitExceeded();
/*      */       }
/* 3407 */       this.recursionDepth++;
/* 3408 */       builder.mergeFrom(this, extensionRegistry);
/* 3409 */       checkLastTagWas(WireFormat.makeTag(fieldNumber, 4));
/* 3410 */       this.recursionDepth--;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public <T extends MessageLite> T readGroup(int fieldNumber, Parser<T> parser, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 3420 */       if (this.recursionDepth >= this.recursionLimit) {
/* 3421 */         throw InvalidProtocolBufferException.recursionLimitExceeded();
/*      */       }
/* 3423 */       this.recursionDepth++;
/* 3424 */       MessageLite messageLite = (MessageLite)parser.parsePartialFrom(this, extensionRegistry);
/* 3425 */       checkLastTagWas(WireFormat.makeTag(fieldNumber, 4));
/* 3426 */       this.recursionDepth--;
/* 3427 */       return (T)messageLite;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     @Deprecated
/*      */     public void readUnknownGroup(int fieldNumber, MessageLite.Builder builder) throws IOException {
/* 3434 */       readGroup(fieldNumber, builder, ExtensionRegistryLite.getEmptyRegistry());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 3441 */       int length = readRawVarint32();
/* 3442 */       if (this.recursionDepth >= this.recursionLimit) {
/* 3443 */         throw InvalidProtocolBufferException.recursionLimitExceeded();
/*      */       }
/* 3445 */       int oldLimit = pushLimit(length);
/* 3446 */       this.recursionDepth++;
/* 3447 */       builder.mergeFrom(this, extensionRegistry);
/* 3448 */       checkLastTagWas(0);
/* 3449 */       this.recursionDepth--;
/* 3450 */       popLimit(oldLimit);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 3457 */       int length = readRawVarint32();
/* 3458 */       if (this.recursionDepth >= this.recursionLimit) {
/* 3459 */         throw InvalidProtocolBufferException.recursionLimitExceeded();
/*      */       }
/* 3461 */       int oldLimit = pushLimit(length);
/* 3462 */       this.recursionDepth++;
/* 3463 */       MessageLite messageLite = (MessageLite)parser.parsePartialFrom(this, extensionRegistry);
/* 3464 */       checkLastTagWas(0);
/* 3465 */       this.recursionDepth--;
/* 3466 */       popLimit(oldLimit);
/* 3467 */       return (T)messageLite;
/*      */     }
/*      */ 
/*      */     
/*      */     public ByteString readBytes() throws IOException {
/* 3472 */       int size = readRawVarint32();
/* 3473 */       if (size > 0 && size <= this.currentByteBufferLimit - this.currentByteBufferPos) {
/* 3474 */         if (this.immutable && this.enableAliasing) {
/* 3475 */           int idx = (int)(this.currentByteBufferPos - this.currentAddress);
/* 3476 */           ByteString result = ByteString.wrap(slice(idx, idx + size));
/* 3477 */           this.currentByteBufferPos += size;
/* 3478 */           return result;
/*      */         } 
/*      */         
/* 3481 */         byte[] bytes = new byte[size];
/* 3482 */         UnsafeUtil.copyMemory(this.currentByteBufferPos, bytes, 0L, size);
/* 3483 */         this.currentByteBufferPos += size;
/* 3484 */         return ByteString.wrap(bytes);
/*      */       } 
/* 3486 */       if (size > 0 && size <= remaining()) {
/* 3487 */         byte[] temp = new byte[size];
/* 3488 */         readRawBytesTo(temp, 0, size);
/* 3489 */         return ByteString.wrap(temp);
/*      */       } 
/*      */       
/* 3492 */       if (size == 0) {
/* 3493 */         return ByteString.EMPTY;
/*      */       }
/* 3495 */       if (size < 0) {
/* 3496 */         throw InvalidProtocolBufferException.negativeSize();
/*      */       }
/* 3498 */       throw InvalidProtocolBufferException.truncatedMessage();
/*      */     }
/*      */ 
/*      */     
/*      */     public byte[] readByteArray() throws IOException {
/* 3503 */       return readRawBytes(readRawVarint32());
/*      */     }
/*      */ 
/*      */     
/*      */     public ByteBuffer readByteBuffer() throws IOException {
/* 3508 */       int size = readRawVarint32();
/* 3509 */       if (size > 0 && size <= currentRemaining()) {
/* 3510 */         if (!this.immutable && this.enableAliasing) {
/* 3511 */           this.currentByteBufferPos += size;
/* 3512 */           return slice((int)(this.currentByteBufferPos - this.currentAddress - size), (int)(this.currentByteBufferPos - this.currentAddress));
/*      */         } 
/*      */ 
/*      */         
/* 3516 */         byte[] bytes = new byte[size];
/* 3517 */         UnsafeUtil.copyMemory(this.currentByteBufferPos, bytes, 0L, size);
/* 3518 */         this.currentByteBufferPos += size;
/* 3519 */         return ByteBuffer.wrap(bytes);
/*      */       } 
/* 3521 */       if (size > 0 && size <= remaining()) {
/* 3522 */         byte[] temp = new byte[size];
/* 3523 */         readRawBytesTo(temp, 0, size);
/* 3524 */         return ByteBuffer.wrap(temp);
/*      */       } 
/*      */       
/* 3527 */       if (size == 0) {
/* 3528 */         return Internal.EMPTY_BYTE_BUFFER;
/*      */       }
/* 3530 */       if (size < 0) {
/* 3531 */         throw InvalidProtocolBufferException.negativeSize();
/*      */       }
/* 3533 */       throw InvalidProtocolBufferException.truncatedMessage();
/*      */     }
/*      */ 
/*      */     
/*      */     public int readUInt32() throws IOException {
/* 3538 */       return readRawVarint32();
/*      */     }
/*      */ 
/*      */     
/*      */     public int readEnum() throws IOException {
/* 3543 */       return readRawVarint32();
/*      */     }
/*      */ 
/*      */     
/*      */     public int readSFixed32() throws IOException {
/* 3548 */       return readRawLittleEndian32();
/*      */     }
/*      */ 
/*      */     
/*      */     public long readSFixed64() throws IOException {
/* 3553 */       return readRawLittleEndian64();
/*      */     }
/*      */ 
/*      */     
/*      */     public int readSInt32() throws IOException {
/* 3558 */       return decodeZigZag32(readRawVarint32());
/*      */     }
/*      */ 
/*      */     
/*      */     public long readSInt64() throws IOException {
/* 3563 */       return decodeZigZag64(readRawVarint64());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int readRawVarint32() throws IOException {
/* 3570 */       long tempPos = this.currentByteBufferPos;
/*      */       
/* 3572 */       if (this.currentByteBufferLimit != this.currentByteBufferPos)
/*      */       { int x;
/*      */ 
/*      */ 
/*      */         
/* 3577 */         if ((x = UnsafeUtil.getByte(tempPos++)) >= 0) {
/* 3578 */           this.currentByteBufferPos++;
/* 3579 */           return x;
/* 3580 */         }  if (this.currentByteBufferLimit - this.currentByteBufferPos >= 10L)
/*      */         
/* 3582 */         { if ((x ^= UnsafeUtil.getByte(tempPos++) << 7) < 0)
/* 3583 */           { x ^= 0xFFFFFF80; }
/* 3584 */           else if ((x ^= UnsafeUtil.getByte(tempPos++) << 14) >= 0)
/* 3585 */           { x ^= 0x3F80; }
/* 3586 */           else if ((x ^= UnsafeUtil.getByte(tempPos++) << 21) < 0)
/* 3587 */           { x ^= 0xFFE03F80; }
/*      */           else
/* 3589 */           { int y = UnsafeUtil.getByte(tempPos++);
/* 3590 */             x ^= y << 28;
/* 3591 */             x ^= 0xFE03F80;
/* 3592 */             if (y < 0 && 
/* 3593 */               UnsafeUtil.getByte(tempPos++) < 0 && 
/* 3594 */               UnsafeUtil.getByte(tempPos++) < 0 && 
/* 3595 */               UnsafeUtil.getByte(tempPos++) < 0 && 
/* 3596 */               UnsafeUtil.getByte(tempPos++) < 0 && 
/* 3597 */               UnsafeUtil.getByte(tempPos++) < 0)
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 3604 */               return (int)readRawVarint64SlowPath(); }  }  this.currentByteBufferPos = tempPos; return x; }  }  return (int)readRawVarint64SlowPath();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public long readRawVarint64() throws IOException {
/* 3611 */       long tempPos = this.currentByteBufferPos;
/*      */       
/* 3613 */       if (this.currentByteBufferLimit != this.currentByteBufferPos)
/*      */       { int y;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3619 */         if ((y = UnsafeUtil.getByte(tempPos++)) >= 0) {
/* 3620 */           this.currentByteBufferPos++;
/* 3621 */           return y;
/* 3622 */         }  if (this.currentByteBufferLimit - this.currentByteBufferPos >= 10L)
/*      */         { long x;
/* 3624 */           if ((y ^= UnsafeUtil.getByte(tempPos++) << 7) < 0)
/* 3625 */           { x = (y ^ 0xFFFFFF80); }
/* 3626 */           else if ((y ^= UnsafeUtil.getByte(tempPos++) << 14) >= 0)
/* 3627 */           { x = (y ^ 0x3F80); }
/* 3628 */           else if ((y ^= UnsafeUtil.getByte(tempPos++) << 21) < 0)
/* 3629 */           { x = (y ^ 0xFFE03F80); }
/* 3630 */           else if ((x = y ^ UnsafeUtil.getByte(tempPos++) << 28L) >= 0L)
/* 3631 */           { x ^= 0xFE03F80L; }
/* 3632 */           else if ((x ^= UnsafeUtil.getByte(tempPos++) << 35L) < 0L)
/* 3633 */           { x ^= 0xFFFFFFF80FE03F80L; }
/* 3634 */           else if ((x ^= UnsafeUtil.getByte(tempPos++) << 42L) >= 0L)
/* 3635 */           { x ^= 0x3F80FE03F80L; }
/* 3636 */           else if ((x ^= UnsafeUtil.getByte(tempPos++) << 49L) < 0L)
/* 3637 */           { x ^= 0xFFFE03F80FE03F80L;
/*      */ 
/*      */             
/*      */              }
/*      */           
/*      */           else
/*      */           
/*      */           { 
/*      */             
/* 3646 */             x ^= UnsafeUtil.getByte(tempPos++) << 56L;
/* 3647 */             x ^= 0xFE03F80FE03F80L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 3656 */             if (x < 0L && 
/* 3657 */               UnsafeUtil.getByte(tempPos++) < 0L)
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 3665 */               return readRawVarint64SlowPath(); }  }  this.currentByteBufferPos = tempPos; return x; }  }  return readRawVarint64SlowPath();
/*      */     }
/*      */ 
/*      */     
/*      */     long readRawVarint64SlowPath() throws IOException {
/* 3670 */       long result = 0L;
/* 3671 */       for (int shift = 0; shift < 64; shift += 7) {
/* 3672 */         byte b = readRawByte();
/* 3673 */         result |= (b & Byte.MAX_VALUE) << shift;
/* 3674 */         if ((b & 0x80) == 0) {
/* 3675 */           return result;
/*      */         }
/*      */       } 
/* 3678 */       throw InvalidProtocolBufferException.malformedVarint();
/*      */     }
/*      */ 
/*      */     
/*      */     public int readRawLittleEndian32() throws IOException {
/* 3683 */       if (currentRemaining() >= 4L) {
/* 3684 */         long tempPos = this.currentByteBufferPos;
/* 3685 */         this.currentByteBufferPos += 4L;
/* 3686 */         return UnsafeUtil.getByte(tempPos) & 0xFF | (
/* 3687 */           UnsafeUtil.getByte(tempPos + 1L) & 0xFF) << 8 | (
/* 3688 */           UnsafeUtil.getByte(tempPos + 2L) & 0xFF) << 16 | (
/* 3689 */           UnsafeUtil.getByte(tempPos + 3L) & 0xFF) << 24;
/*      */       } 
/* 3691 */       return readRawByte() & 0xFF | (
/* 3692 */         readRawByte() & 0xFF) << 8 | (
/* 3693 */         readRawByte() & 0xFF) << 16 | (
/* 3694 */         readRawByte() & 0xFF) << 24;
/*      */     }
/*      */ 
/*      */     
/*      */     public long readRawLittleEndian64() throws IOException {
/* 3699 */       if (currentRemaining() >= 8L) {
/* 3700 */         long tempPos = this.currentByteBufferPos;
/* 3701 */         this.currentByteBufferPos += 8L;
/* 3702 */         return UnsafeUtil.getByte(tempPos) & 0xFFL | (
/* 3703 */           UnsafeUtil.getByte(tempPos + 1L) & 0xFFL) << 8L | (
/* 3704 */           UnsafeUtil.getByte(tempPos + 2L) & 0xFFL) << 16L | (
/* 3705 */           UnsafeUtil.getByte(tempPos + 3L) & 0xFFL) << 24L | (
/* 3706 */           UnsafeUtil.getByte(tempPos + 4L) & 0xFFL) << 32L | (
/* 3707 */           UnsafeUtil.getByte(tempPos + 5L) & 0xFFL) << 40L | (
/* 3708 */           UnsafeUtil.getByte(tempPos + 6L) & 0xFFL) << 48L | (
/* 3709 */           UnsafeUtil.getByte(tempPos + 7L) & 0xFFL) << 56L;
/*      */       } 
/* 3711 */       return readRawByte() & 0xFFL | (
/* 3712 */         readRawByte() & 0xFFL) << 8L | (
/* 3713 */         readRawByte() & 0xFFL) << 16L | (
/* 3714 */         readRawByte() & 0xFFL) << 24L | (
/* 3715 */         readRawByte() & 0xFFL) << 32L | (
/* 3716 */         readRawByte() & 0xFFL) << 40L | (
/* 3717 */         readRawByte() & 0xFFL) << 48L | (
/* 3718 */         readRawByte() & 0xFFL) << 56L;
/*      */     }
/*      */ 
/*      */     
/*      */     public void enableAliasing(boolean enabled) {
/* 3723 */       this.enableAliasing = enabled;
/*      */     }
/*      */ 
/*      */     
/*      */     public void resetSizeCounter() {
/* 3728 */       this.startOffset = (int)(this.totalBytesRead + this.currentByteBufferPos - this.currentByteBufferStartPos);
/*      */     }
/*      */ 
/*      */     
/*      */     public int pushLimit(int byteLimit) throws InvalidProtocolBufferException {
/* 3733 */       if (byteLimit < 0) {
/* 3734 */         throw InvalidProtocolBufferException.negativeSize();
/*      */       }
/* 3736 */       byteLimit += getTotalBytesRead();
/* 3737 */       int oldLimit = this.currentLimit;
/* 3738 */       if (byteLimit > oldLimit) {
/* 3739 */         throw InvalidProtocolBufferException.truncatedMessage();
/*      */       }
/* 3741 */       this.currentLimit = byteLimit;
/*      */       
/* 3743 */       recomputeBufferSizeAfterLimit();
/*      */       
/* 3745 */       return oldLimit;
/*      */     }
/*      */     
/*      */     private void recomputeBufferSizeAfterLimit() {
/* 3749 */       this.totalBufferSize += this.bufferSizeAfterCurrentLimit;
/* 3750 */       int bufferEnd = this.totalBufferSize - this.startOffset;
/* 3751 */       if (bufferEnd > this.currentLimit) {
/*      */         
/* 3753 */         this.bufferSizeAfterCurrentLimit = bufferEnd - this.currentLimit;
/* 3754 */         this.totalBufferSize -= this.bufferSizeAfterCurrentLimit;
/*      */       } else {
/* 3756 */         this.bufferSizeAfterCurrentLimit = 0;
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void popLimit(int oldLimit) {
/* 3762 */       this.currentLimit = oldLimit;
/* 3763 */       recomputeBufferSizeAfterLimit();
/*      */     }
/*      */ 
/*      */     
/*      */     public int getBytesUntilLimit() {
/* 3768 */       if (this.currentLimit == Integer.MAX_VALUE) {
/* 3769 */         return -1;
/*      */       }
/*      */       
/* 3772 */       return this.currentLimit - getTotalBytesRead();
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isAtEnd() throws IOException {
/* 3777 */       return (this.totalBytesRead + this.currentByteBufferPos - this.currentByteBufferStartPos == this.totalBufferSize);
/*      */     }
/*      */ 
/*      */     
/*      */     public int getTotalBytesRead() {
/* 3782 */       return (int)((this.totalBytesRead - this.startOffset) + this.currentByteBufferPos - this.currentByteBufferStartPos);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public byte readRawByte() throws IOException {
/* 3788 */       if (currentRemaining() == 0L) {
/* 3789 */         getNextByteBuffer();
/*      */       }
/* 3791 */       return UnsafeUtil.getByte(this.currentByteBufferPos++);
/*      */     }
/*      */ 
/*      */     
/*      */     public byte[] readRawBytes(int length) throws IOException {
/* 3796 */       if (length >= 0 && length <= currentRemaining()) {
/* 3797 */         byte[] bytes = new byte[length];
/* 3798 */         UnsafeUtil.copyMemory(this.currentByteBufferPos, bytes, 0L, length);
/* 3799 */         this.currentByteBufferPos += length;
/* 3800 */         return bytes;
/*      */       } 
/* 3802 */       if (length >= 0 && length <= remaining()) {
/* 3803 */         byte[] bytes = new byte[length];
/* 3804 */         readRawBytesTo(bytes, 0, length);
/* 3805 */         return bytes;
/*      */       } 
/*      */       
/* 3808 */       if (length <= 0) {
/* 3809 */         if (length == 0) {
/* 3810 */           return Internal.EMPTY_BYTE_ARRAY;
/*      */         }
/* 3812 */         throw InvalidProtocolBufferException.negativeSize();
/*      */       } 
/*      */ 
/*      */       
/* 3816 */       throw InvalidProtocolBufferException.truncatedMessage();
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
/*      */     private void readRawBytesTo(byte[] bytes, int offset, int length) throws IOException {
/* 3830 */       if (length >= 0 && length <= remaining()) {
/* 3831 */         int l = length;
/* 3832 */         while (l > 0) {
/* 3833 */           if (currentRemaining() == 0L) {
/* 3834 */             getNextByteBuffer();
/*      */           }
/* 3836 */           int bytesToCopy = Math.min(l, (int)currentRemaining());
/* 3837 */           UnsafeUtil.copyMemory(this.currentByteBufferPos, bytes, (length - l + offset), bytesToCopy);
/* 3838 */           l -= bytesToCopy;
/* 3839 */           this.currentByteBufferPos += bytesToCopy;
/*      */         } 
/*      */         
/*      */         return;
/*      */       } 
/* 3844 */       if (length <= 0) {
/* 3845 */         if (length == 0) {
/*      */           return;
/*      */         }
/* 3848 */         throw InvalidProtocolBufferException.negativeSize();
/*      */       } 
/*      */       
/* 3851 */       throw InvalidProtocolBufferException.truncatedMessage();
/*      */     }
/*      */ 
/*      */     
/*      */     public void skipRawBytes(int length) throws IOException {
/* 3856 */       if (length >= 0 && length <= (this.totalBufferSize - this.totalBytesRead) - this.currentByteBufferPos + this.currentByteBufferStartPos) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3863 */         int l = length;
/* 3864 */         while (l > 0) {
/* 3865 */           if (currentRemaining() == 0L) {
/* 3866 */             getNextByteBuffer();
/*      */           }
/* 3868 */           int rl = Math.min(l, (int)currentRemaining());
/* 3869 */           l -= rl;
/* 3870 */           this.currentByteBufferPos += rl;
/*      */         } 
/*      */         
/*      */         return;
/*      */       } 
/* 3875 */       if (length < 0) {
/* 3876 */         throw InvalidProtocolBufferException.negativeSize();
/*      */       }
/* 3878 */       throw InvalidProtocolBufferException.truncatedMessage();
/*      */     }
/*      */ 
/*      */     
/*      */     private void skipRawVarint() throws IOException {
/* 3883 */       for (int i = 0; i < 10; i++) {
/* 3884 */         if (readRawByte() >= 0) {
/*      */           return;
/*      */         }
/*      */       } 
/* 3888 */       throw InvalidProtocolBufferException.malformedVarint();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private int remaining() {
/* 3897 */       return (int)((this.totalBufferSize - this.totalBytesRead) - this.currentByteBufferPos + this.currentByteBufferStartPos);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private long currentRemaining() {
/* 3907 */       return this.currentByteBufferLimit - this.currentByteBufferPos;
/*      */     }
/*      */     
/*      */     private ByteBuffer slice(int begin, int end) throws IOException {
/* 3911 */       int prevPos = this.currentByteBuffer.position();
/* 3912 */       int prevLimit = this.currentByteBuffer.limit();
/*      */       try {
/* 3914 */         this.currentByteBuffer.position(begin);
/* 3915 */         this.currentByteBuffer.limit(end);
/* 3916 */         return this.currentByteBuffer.slice();
/* 3917 */       } catch (IllegalArgumentException e) {
/* 3918 */         throw InvalidProtocolBufferException.truncatedMessage();
/*      */       } finally {
/* 3920 */         this.currentByteBuffer.position(prevPos);
/* 3921 */         this.currentByteBuffer.limit(prevLimit);
/*      */       } 
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\CodedInputStream.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */