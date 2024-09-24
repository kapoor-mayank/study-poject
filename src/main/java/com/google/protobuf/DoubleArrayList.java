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
/*     */ final class DoubleArrayList
/*     */   extends AbstractProtobufList<Double>
/*     */   implements Internal.DoubleList, RandomAccess, PrimitiveNonBoxingCollection
/*     */ {
/*  48 */   private static final DoubleArrayList EMPTY_LIST = new DoubleArrayList(new double[0], 0); private double[] array;
/*     */   static {
/*  50 */     EMPTY_LIST.makeImmutable();
/*     */   }
/*     */   private int size;
/*     */   public static DoubleArrayList emptyList() {
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
/*     */   DoubleArrayList() {
/*  68 */     this(new double[10], 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private DoubleArrayList(double[] other, int size) {
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
/*  96 */     if (!(o instanceof DoubleArrayList)) {
/*  97 */       return super.equals(o);
/*     */     }
/*  99 */     DoubleArrayList other = (DoubleArrayList)o;
/* 100 */     if (this.size != other.size) {
/* 101 */       return false;
/*     */     }
/*     */     
/* 104 */     double[] arr = other.array;
/* 105 */     for (int i = 0; i < this.size; i++) {
/* 106 */       if (Double.doubleToLongBits(this.array[i]) != Double.doubleToLongBits(arr[i])) {
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
/* 118 */       long bits = Double.doubleToLongBits(this.array[i]);
/* 119 */       result = 31 * result + Internal.hashLong(bits);
/*     */     } 
/* 121 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public Internal.DoubleList mutableCopyWithCapacity(int capacity) {
/* 126 */     if (capacity < this.size) {
/* 127 */       throw new IllegalArgumentException();
/*     */     }
/* 129 */     return new DoubleArrayList(Arrays.copyOf(this.array, capacity), this.size);
/*     */   }
/*     */ 
/*     */   
/*     */   public Double get(int index) {
/* 134 */     return Double.valueOf(getDouble(index));
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDouble(int index) {
/* 139 */     ensureIndexInRange(index);
/* 140 */     return this.array[index];
/*     */   }
/*     */ 
/*     */   
/*     */   public int indexOf(Object element) {
/* 145 */     if (!(element instanceof Double)) {
/* 146 */       return -1;
/*     */     }
/* 148 */     double unboxedElement = ((Double)element).doubleValue();
/* 149 */     int numElems = size();
/* 150 */     for (int i = 0; i < numElems; i++) {
/* 151 */       if (this.array[i] == unboxedElement) {
/* 152 */         return i;
/*     */       }
/*     */     } 
/* 155 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(Object element) {
/* 160 */     return (indexOf(element) != -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/* 165 */     return this.size;
/*     */   }
/*     */ 
/*     */   
/*     */   public Double set(int index, Double element) {
/* 170 */     return Double.valueOf(setDouble(index, element.doubleValue()));
/*     */   }
/*     */ 
/*     */   
/*     */   public double setDouble(int index, double element) {
/* 175 */     ensureIsMutable();
/* 176 */     ensureIndexInRange(index);
/* 177 */     double previousValue = this.array[index];
/* 178 */     this.array[index] = element;
/* 179 */     return previousValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(Double element) {
/* 184 */     addDouble(element.doubleValue());
/* 185 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void add(int index, Double element) {
/* 190 */     addDouble(index, element.doubleValue());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addDouble(double element) {
/* 196 */     ensureIsMutable();
/* 197 */     if (this.size == this.array.length) {
/*     */       
/* 199 */       int length = this.size * 3 / 2 + 1;
/* 200 */       double[] newArray = new double[length];
/*     */       
/* 202 */       System.arraycopy(this.array, 0, newArray, 0, this.size);
/* 203 */       this.array = newArray;
/*     */     } 
/*     */     
/* 206 */     this.array[this.size++] = element;
/*     */   }
/*     */ 
/*     */   
/*     */   private void addDouble(int index, double element) {
/* 211 */     ensureIsMutable();
/* 212 */     if (index < 0 || index > this.size) {
/* 213 */       throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(index));
/*     */     }
/*     */     
/* 216 */     if (this.size < this.array.length) {
/*     */       
/* 218 */       System.arraycopy(this.array, index, this.array, index + 1, this.size - index);
/*     */     } else {
/*     */       
/* 221 */       int length = this.size * 3 / 2 + 1;
/* 222 */       double[] newArray = new double[length];
/*     */ 
/*     */       
/* 225 */       System.arraycopy(this.array, 0, newArray, 0, index);
/*     */ 
/*     */       
/* 228 */       System.arraycopy(this.array, index, newArray, index + 1, this.size - index);
/* 229 */       this.array = newArray;
/*     */     } 
/*     */     
/* 232 */     this.array[index] = element;
/* 233 */     this.size++;
/* 234 */     this.modCount++;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addAll(Collection<? extends Double> collection) {
/* 239 */     ensureIsMutable();
/*     */     
/* 241 */     Internal.checkNotNull(collection);
/*     */ 
/*     */     
/* 244 */     if (!(collection instanceof DoubleArrayList)) {
/* 245 */       return super.addAll(collection);
/*     */     }
/*     */     
/* 248 */     DoubleArrayList list = (DoubleArrayList)collection;
/* 249 */     if (list.size == 0) {
/* 250 */       return false;
/*     */     }
/*     */     
/* 253 */     int overflow = Integer.MAX_VALUE - this.size;
/* 254 */     if (overflow < list.size)
/*     */     {
/* 256 */       throw new OutOfMemoryError();
/*     */     }
/*     */     
/* 259 */     int newSize = this.size + list.size;
/* 260 */     if (newSize > this.array.length) {
/* 261 */       this.array = Arrays.copyOf(this.array, newSize);
/*     */     }
/*     */     
/* 264 */     System.arraycopy(list.array, 0, this.array, this.size, list.size);
/* 265 */     this.size = newSize;
/* 266 */     this.modCount++;
/* 267 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Double remove(int index) {
/* 272 */     ensureIsMutable();
/* 273 */     ensureIndexInRange(index);
/* 274 */     double value = this.array[index];
/* 275 */     if (index < this.size - 1) {
/* 276 */       System.arraycopy(this.array, index + 1, this.array, index, this.size - index - 1);
/*     */     }
/* 278 */     this.size--;
/* 279 */     this.modCount++;
/* 280 */     return Double.valueOf(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void ensureIndexInRange(int index) {
/* 290 */     if (index < 0 || index >= this.size) {
/* 291 */       throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(index));
/*     */     }
/*     */   }
/*     */   
/*     */   private String makeOutOfBoundsExceptionMessage(int index) {
/* 296 */     return "Index:" + index + ", Size:" + this.size;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\DoubleArrayList.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */