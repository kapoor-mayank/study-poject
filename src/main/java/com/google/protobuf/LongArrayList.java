/*     */ package com.google.protobuf;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.RandomAccess;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class LongArrayList
/*     */   extends AbstractProtobufList<Long>
/*     */   implements Internal.LongList, RandomAccess, PrimitiveNonBoxingCollection
/*     */ {
/*  48 */   private static final LongArrayList EMPTY_LIST = new LongArrayList(new long[0], 0); private long[] array;
/*     */   static {
/*  50 */     EMPTY_LIST.makeImmutable();
/*     */   }
/*     */   private int size;
/*     */   public static LongArrayList emptyList() {
/*  54 */     return EMPTY_LIST;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   LongArrayList() {
/*  68 */     this(new long[10], 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private LongArrayList(long[] other, int size) {
/*  75 */     this.array = other;
/*  76 */     this.size = size;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void removeRange(int fromIndex, int toIndex) {
/*  81 */     ensureIsMutable();
/*  82 */     if (toIndex < fromIndex) {
/*  83 */       throw new IndexOutOfBoundsException("toIndex < fromIndex");
/*     */     }
/*     */     
/*  86 */     System.arraycopy(this.array, toIndex, this.array, fromIndex, this.size - toIndex);
/*  87 */     this.size -= toIndex - fromIndex;
/*  88 */     this.modCount++;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/*  93 */     if (this == o) {
/*  94 */       return true;
/*     */     }
/*  96 */     if (!(o instanceof LongArrayList)) {
/*  97 */       return super.equals(o);
/*     */     }
/*  99 */     LongArrayList other = (LongArrayList)o;
/* 100 */     if (this.size != other.size) {
/* 101 */       return false;
/*     */     }
/*     */     
/* 104 */     long[] arr = other.array;
/* 105 */     for (int i = 0; i < this.size; i++) {
/* 106 */       if (this.array[i] != arr[i]) {
/* 107 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 111 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 116 */     int result = 1;
/* 117 */     for (int i = 0; i < this.size; i++) {
/* 118 */       result = 31 * result + Internal.hashLong(this.array[i]);
/*     */     }
/* 120 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public Internal.LongList mutableCopyWithCapacity(int capacity) {
/* 125 */     if (capacity < this.size) {
/* 126 */       throw new IllegalArgumentException();
/*     */     }
/* 128 */     return new LongArrayList(Arrays.copyOf(this.array, capacity), this.size);
/*     */   }
/*     */ 
/*     */   
/*     */   public Long get(int index) {
/* 133 */     return Long.valueOf(getLong(index));
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLong(int index) {
/* 138 */     ensureIndexInRange(index);
/* 139 */     return this.array[index];
/*     */   }
/*     */ 
/*     */   
/*     */   public int indexOf(Object element) {
/* 144 */     if (!(element instanceof Long)) {
/* 145 */       return -1;
/*     */     }
/* 147 */     long unboxedElement = ((Long)element).longValue();
/* 148 */     int numElems = size();
/* 149 */     for (int i = 0; i < numElems; i++) {
/* 150 */       if (this.array[i] == unboxedElement) {
/* 151 */         return i;
/*     */       }
/*     */     } 
/* 154 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(Object element) {
/* 159 */     return (indexOf(element) != -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/* 164 */     return this.size;
/*     */   }
/*     */ 
/*     */   
/*     */   public Long set(int index, Long element) {
/* 169 */     return Long.valueOf(setLong(index, element.longValue()));
/*     */   }
/*     */ 
/*     */   
/*     */   public long setLong(int index, long element) {
/* 174 */     ensureIsMutable();
/* 175 */     ensureIndexInRange(index);
/* 176 */     long previousValue = this.array[index];
/* 177 */     this.array[index] = element;
/* 178 */     return previousValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(Long element) {
/* 183 */     addLong(element.longValue());
/* 184 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void add(int index, Long element) {
/* 189 */     addLong(index, element.longValue());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addLong(long element) {
/* 195 */     ensureIsMutable();
/* 196 */     if (this.size == this.array.length) {
/*     */       
/* 198 */       int length = this.size * 3 / 2 + 1;
/* 199 */       long[] newArray = new long[length];
/*     */       
/* 201 */       System.arraycopy(this.array, 0, newArray, 0, this.size);
/* 202 */       this.array = newArray;
/*     */     } 
/*     */     
/* 205 */     this.array[this.size++] = element;
/*     */   }
/*     */ 
/*     */   
/*     */   private void addLong(int index, long element) {
/* 210 */     ensureIsMutable();
/* 211 */     if (index < 0 || index > this.size) {
/* 212 */       throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(index));
/*     */     }
/*     */     
/* 215 */     if (this.size < this.array.length) {
/*     */       
/* 217 */       System.arraycopy(this.array, index, this.array, index + 1, this.size - index);
/*     */     } else {
/*     */       
/* 220 */       int length = this.size * 3 / 2 + 1;
/* 221 */       long[] newArray = new long[length];
/*     */ 
/*     */       
/* 224 */       System.arraycopy(this.array, 0, newArray, 0, index);
/*     */ 
/*     */       
/* 227 */       System.arraycopy(this.array, index, newArray, index + 1, this.size - index);
/* 228 */       this.array = newArray;
/*     */     } 
/*     */     
/* 231 */     this.array[index] = element;
/* 232 */     this.size++;
/* 233 */     this.modCount++;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addAll(Collection<? extends Long> collection) {
/* 238 */     ensureIsMutable();
/*     */     
/* 240 */     Internal.checkNotNull(collection);
/*     */ 
/*     */     
/* 243 */     if (!(collection instanceof LongArrayList)) {
/* 244 */       return super.addAll(collection);
/*     */     }
/*     */     
/* 247 */     LongArrayList list = (LongArrayList)collection;
/* 248 */     if (list.size == 0) {
/* 249 */       return false;
/*     */     }
/*     */     
/* 252 */     int overflow = Integer.MAX_VALUE - this.size;
/* 253 */     if (overflow < list.size)
/*     */     {
/* 255 */       throw new OutOfMemoryError();
/*     */     }
/*     */     
/* 258 */     int newSize = this.size + list.size;
/* 259 */     if (newSize > this.array.length) {
/* 260 */       this.array = Arrays.copyOf(this.array, newSize);
/*     */     }
/*     */     
/* 263 */     System.arraycopy(list.array, 0, this.array, this.size, list.size);
/* 264 */     this.size = newSize;
/* 265 */     this.modCount++;
/* 266 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Long remove(int index) {
/* 271 */     ensureIsMutable();
/* 272 */     ensureIndexInRange(index);
/* 273 */     long value = this.array[index];
/* 274 */     if (index < this.size - 1) {
/* 275 */       System.arraycopy(this.array, index + 1, this.array, index, this.size - index - 1);
/*     */     }
/* 277 */     this.size--;
/* 278 */     this.modCount++;
/* 279 */     return Long.valueOf(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void ensureIndexInRange(int index) {
/* 289 */     if (index < 0 || index >= this.size) {
/* 290 */       throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(index));
/*     */     }
/*     */   }
/*     */   
/*     */   private String makeOutOfBoundsExceptionMessage(int index) {
/* 295 */     return "Index:" + index + ", Size:" + this.size;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\LongArrayList.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */