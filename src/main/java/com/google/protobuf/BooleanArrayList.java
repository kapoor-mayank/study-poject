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
/*     */ final class BooleanArrayList
/*     */   extends AbstractProtobufList<Boolean>
/*     */   implements Internal.BooleanList, RandomAccess, PrimitiveNonBoxingCollection
/*     */ {
/*  48 */   private static final BooleanArrayList EMPTY_LIST = new BooleanArrayList(new boolean[0], 0); private boolean[] array;
/*     */   static {
/*  50 */     EMPTY_LIST.makeImmutable();
/*     */   }
/*     */   private int size;
/*     */   public static BooleanArrayList emptyList() {
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
/*     */   BooleanArrayList() {
/*  68 */     this(new boolean[10], 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private BooleanArrayList(boolean[] other, int size) {
/*  76 */     this.array = other;
/*  77 */     this.size = size;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void removeRange(int fromIndex, int toIndex) {
/*  82 */     ensureIsMutable();
/*  83 */     if (toIndex < fromIndex) {
/*  84 */       throw new IndexOutOfBoundsException("toIndex < fromIndex");
/*     */     }
/*     */     
/*  87 */     System.arraycopy(this.array, toIndex, this.array, fromIndex, this.size - toIndex);
/*  88 */     this.size -= toIndex - fromIndex;
/*  89 */     this.modCount++;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/*  94 */     if (this == o) {
/*  95 */       return true;
/*     */     }
/*  97 */     if (!(o instanceof BooleanArrayList)) {
/*  98 */       return super.equals(o);
/*     */     }
/* 100 */     BooleanArrayList other = (BooleanArrayList)o;
/* 101 */     if (this.size != other.size) {
/* 102 */       return false;
/*     */     }
/*     */     
/* 105 */     boolean[] arr = other.array;
/* 106 */     for (int i = 0; i < this.size; i++) {
/* 107 */       if (this.array[i] != arr[i]) {
/* 108 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 112 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 117 */     int result = 1;
/* 118 */     for (int i = 0; i < this.size; i++) {
/* 119 */       result = 31 * result + Internal.hashBoolean(this.array[i]);
/*     */     }
/* 121 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public Internal.BooleanList mutableCopyWithCapacity(int capacity) {
/* 126 */     if (capacity < this.size) {
/* 127 */       throw new IllegalArgumentException();
/*     */     }
/* 129 */     return new BooleanArrayList(Arrays.copyOf(this.array, capacity), this.size);
/*     */   }
/*     */ 
/*     */   
/*     */   public Boolean get(int index) {
/* 134 */     return Boolean.valueOf(getBoolean(index));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getBoolean(int index) {
/* 139 */     ensureIndexInRange(index);
/* 140 */     return this.array[index];
/*     */   }
/*     */ 
/*     */   
/*     */   public int indexOf(Object element) {
/* 145 */     if (!(element instanceof Boolean)) {
/* 146 */       return -1;
/*     */     }
/* 148 */     boolean unboxedElement = ((Boolean)element).booleanValue();
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
/*     */   public Boolean set(int index, Boolean element) {
/* 170 */     return Boolean.valueOf(setBoolean(index, element.booleanValue()));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setBoolean(int index, boolean element) {
/* 175 */     ensureIsMutable();
/* 176 */     ensureIndexInRange(index);
/* 177 */     boolean previousValue = this.array[index];
/* 178 */     this.array[index] = element;
/* 179 */     return previousValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(Boolean element) {
/* 184 */     addBoolean(element.booleanValue());
/* 185 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void add(int index, Boolean element) {
/* 190 */     addBoolean(index, element.booleanValue());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addBoolean(boolean element) {
/* 196 */     ensureIsMutable();
/* 197 */     if (this.size == this.array.length) {
/*     */       
/* 199 */       int length = this.size * 3 / 2 + 1;
/* 200 */       boolean[] newArray = new boolean[length];
/*     */       
/* 202 */       System.arraycopy(this.array, 0, newArray, 0, this.size);
/* 203 */       this.array = newArray;
/*     */     } 
/*     */     
/* 206 */     this.array[this.size++] = element;
/*     */   }
/*     */ 
/*     */   
/*     */   private void addBoolean(int index, boolean element) {
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
/* 222 */       boolean[] newArray = new boolean[length];
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
/*     */   public boolean addAll(Collection<? extends Boolean> collection) {
/* 239 */     ensureIsMutable();
/*     */     
/* 241 */     Internal.checkNotNull(collection);
/*     */ 
/*     */     
/* 244 */     if (!(collection instanceof BooleanArrayList)) {
/* 245 */       return super.addAll(collection);
/*     */     }
/*     */     
/* 248 */     BooleanArrayList list = (BooleanArrayList)collection;
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
/*     */   public Boolean remove(int index) {
/* 272 */     ensureIsMutable();
/* 273 */     ensureIndexInRange(index);
/* 274 */     boolean value = this.array[index];
/* 275 */     if (index < this.size - 1) {
/* 276 */       System.arraycopy(this.array, index + 1, this.array, index, this.size - index - 1);
/*     */     }
/* 278 */     this.size--;
/* 279 */     this.modCount++;
/* 280 */     return Boolean.valueOf(value);
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


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\BooleanArrayList.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */