/*     */ package com.google.protobuf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InvalidObjectException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.InvalidMarkException;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class NioByteString
/*     */   extends ByteString.LeafByteString
/*     */ {
/*     */   private final ByteBuffer buffer;
/*     */   
/*     */   NioByteString(ByteBuffer buffer) {
/*  53 */     Internal.checkNotNull(buffer, "buffer");
/*     */ 
/*     */     
/*  56 */     this.buffer = buffer.slice().order(ByteOrder.nativeOrder());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Object writeReplace() {
/*  64 */     return ByteString.copyFrom(this.buffer.slice());
/*     */   }
/*     */ 
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException {
/*  69 */     throw new InvalidObjectException("NioByteString instances are not to be serialized directly");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte byteAt(int index) {
/*     */     try {
/*  77 */       return this.buffer.get(index);
/*  78 */     } catch (ArrayIndexOutOfBoundsException e) {
/*  79 */       throw e;
/*  80 */     } catch (IndexOutOfBoundsException e) {
/*  81 */       throw new ArrayIndexOutOfBoundsException(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte internalByteAt(int index) {
/*  89 */     return byteAt(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/*  94 */     return this.buffer.remaining();
/*     */   }
/*     */ 
/*     */   
/*     */   public ByteString substring(int beginIndex, int endIndex) {
/*     */     try {
/* 100 */       ByteBuffer slice = slice(beginIndex, endIndex);
/* 101 */       return new NioByteString(slice);
/* 102 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 103 */       throw e;
/* 104 */     } catch (IndexOutOfBoundsException e) {
/* 105 */       throw new ArrayIndexOutOfBoundsException(e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void copyToInternal(byte[] target, int sourceOffset, int targetOffset, int numberToCopy) {
/* 112 */     ByteBuffer slice = this.buffer.slice();
/* 113 */     slice.position(sourceOffset);
/* 114 */     slice.get(target, targetOffset, numberToCopy);
/*     */   }
/*     */ 
/*     */   
/*     */   public void copyTo(ByteBuffer target) {
/* 119 */     target.put(this.buffer.slice());
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeTo(OutputStream out) throws IOException {
/* 124 */     out.write(toByteArray());
/*     */   }
/*     */ 
/*     */   
/*     */   boolean equalsRange(ByteString other, int offset, int length) {
/* 129 */     return substring(0, length).equals(other.substring(offset, offset + length));
/*     */   }
/*     */ 
/*     */   
/*     */   void writeToInternal(OutputStream out, int sourceOffset, int numberToWrite) throws IOException {
/* 134 */     if (this.buffer.hasArray()) {
/*     */ 
/*     */       
/* 137 */       int bufferOffset = this.buffer.arrayOffset() + this.buffer.position() + sourceOffset;
/* 138 */       out.write(this.buffer.array(), bufferOffset, numberToWrite);
/*     */       
/*     */       return;
/*     */     } 
/* 142 */     ByteBufferWriter.write(slice(sourceOffset, sourceOffset + numberToWrite), out);
/*     */   }
/*     */ 
/*     */   
/*     */   void writeTo(ByteOutput output) throws IOException {
/* 147 */     output.writeLazy(this.buffer.slice());
/*     */   }
/*     */ 
/*     */   
/*     */   public ByteBuffer asReadOnlyByteBuffer() {
/* 152 */     return this.buffer.asReadOnlyBuffer();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<ByteBuffer> asReadOnlyByteBufferList() {
/* 157 */     return Collections.singletonList(asReadOnlyByteBuffer());
/*     */   }
/*     */ 
/*     */   
/*     */   protected String toStringInternal(Charset charset) {
/*     */     byte[] bytes;
/*     */     int offset;
/*     */     int length;
/* 165 */     if (this.buffer.hasArray()) {
/* 166 */       bytes = this.buffer.array();
/* 167 */       offset = this.buffer.arrayOffset() + this.buffer.position();
/* 168 */       length = this.buffer.remaining();
/*     */     } else {
/*     */       
/* 171 */       bytes = toByteArray();
/* 172 */       offset = 0;
/* 173 */       length = bytes.length;
/*     */     } 
/* 175 */     return new String(bytes, offset, length, charset);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidUtf8() {
/* 180 */     return Utf8.isValidUtf8(this.buffer);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int partialIsValidUtf8(int state, int offset, int length) {
/* 185 */     return Utf8.partialIsValidUtf8(state, this.buffer, offset, offset + length);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object other) {
/* 190 */     if (other == this) {
/* 191 */       return true;
/*     */     }
/* 193 */     if (!(other instanceof ByteString)) {
/* 194 */       return false;
/*     */     }
/* 196 */     ByteString otherString = (ByteString)other;
/* 197 */     if (size() != otherString.size()) {
/* 198 */       return false;
/*     */     }
/* 200 */     if (size() == 0) {
/* 201 */       return true;
/*     */     }
/* 203 */     if (other instanceof NioByteString) {
/* 204 */       return this.buffer.equals(((NioByteString)other).buffer);
/*     */     }
/* 206 */     if (other instanceof RopeByteString) {
/* 207 */       return other.equals(this);
/*     */     }
/* 209 */     return this.buffer.equals(otherString.asReadOnlyByteBuffer());
/*     */   }
/*     */ 
/*     */   
/*     */   protected int partialHash(int h, int offset, int length) {
/* 214 */     for (int i = offset; i < offset + length; i++) {
/* 215 */       h = h * 31 + this.buffer.get(i);
/*     */     }
/* 217 */     return h;
/*     */   }
/*     */ 
/*     */   
/*     */   public InputStream newInput() {
/* 222 */     return new InputStream() {
/* 223 */         private final ByteBuffer buf = NioByteString.this.buffer.slice();
/*     */ 
/*     */         
/*     */         public void mark(int readlimit) {
/* 227 */           this.buf.mark();
/*     */         }
/*     */ 
/*     */         
/*     */         public boolean markSupported() {
/* 232 */           return true;
/*     */         }
/*     */ 
/*     */         
/*     */         public void reset() throws IOException {
/*     */           try {
/* 238 */             this.buf.reset();
/* 239 */           } catch (InvalidMarkException e) {
/* 240 */             throw new IOException(e);
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/*     */         public int available() throws IOException {
/* 246 */           return this.buf.remaining();
/*     */         }
/*     */ 
/*     */         
/*     */         public int read() throws IOException {
/* 251 */           if (!this.buf.hasRemaining()) {
/* 252 */             return -1;
/*     */           }
/* 254 */           return this.buf.get() & 0xFF;
/*     */         }
/*     */ 
/*     */         
/*     */         public int read(byte[] bytes, int off, int len) throws IOException {
/* 259 */           if (!this.buf.hasRemaining()) {
/* 260 */             return -1;
/*     */           }
/*     */           
/* 263 */           len = Math.min(len, this.buf.remaining());
/* 264 */           this.buf.get(bytes, off, len);
/* 265 */           return len;
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   public CodedInputStream newCodedInput() {
/* 272 */     return CodedInputStream.newInstance(this.buffer, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ByteBuffer slice(int beginIndex, int endIndex) {
/* 283 */     if (beginIndex < this.buffer.position() || endIndex > this.buffer.limit() || beginIndex > endIndex) {
/* 284 */       throw new IllegalArgumentException(
/* 285 */           String.format("Invalid indices [%d, %d]", new Object[] { Integer.valueOf(beginIndex), Integer.valueOf(endIndex) }));
/*     */     }
/*     */     
/* 288 */     ByteBuffer slice = this.buffer.slice();
/* 289 */     slice.position(beginIndex - this.buffer.position());
/* 290 */     slice.limit(endIndex - this.buffer.position());
/* 291 */     return slice;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\NioByteString.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */