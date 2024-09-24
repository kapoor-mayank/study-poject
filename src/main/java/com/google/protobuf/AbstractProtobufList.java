/*     */ package com.google.protobuf;
/*     */ 
/*     */ import java.util.AbstractList;
/*     */ import java.util.Collection;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ abstract class AbstractProtobufList<E>
/*     */   extends AbstractList<E>
/*     */   implements Internal.ProtobufList<E>
/*     */ {
/*     */   protected static final int DEFAULT_CAPACITY = 10;
/*     */   private boolean isMutable = true;
/*     */   
/*     */   public boolean equals(Object o) {
/*  60 */     if (o == this) {
/*  61 */       return true;
/*     */     }
/*  63 */     if (!(o instanceof List)) {
/*  64 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  69 */     if (!(o instanceof java.util.RandomAccess)) {
/*  70 */       return super.equals(o);
/*     */     }
/*     */     
/*  73 */     List<?> other = (List)o;
/*  74 */     int size = size();
/*  75 */     if (size != other.size()) {
/*  76 */       return false;
/*     */     }
/*  78 */     for (int i = 0; i < size; i++) {
/*  79 */       if (!get(i).equals(other.get(i))) {
/*  80 */         return false;
/*     */       }
/*     */     } 
/*  83 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  88 */     int size = size();
/*  89 */     int hashCode = 1;
/*  90 */     for (int i = 0; i < size; i++) {
/*  91 */       hashCode = 31 * hashCode + get(i).hashCode();
/*     */     }
/*  93 */     return hashCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(E e) {
/*  98 */     ensureIsMutable();
/*  99 */     return super.add(e);
/*     */   }
/*     */ 
/*     */   
/*     */   public void add(int index, E element) {
/* 104 */     ensureIsMutable();
/* 105 */     super.add(index, element);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addAll(Collection<? extends E> c) {
/* 110 */     ensureIsMutable();
/* 111 */     return super.addAll(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addAll(int index, Collection<? extends E> c) {
/* 116 */     ensureIsMutable();
/* 117 */     return super.addAll(index, c);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 122 */     ensureIsMutable();
/* 123 */     super.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isModifiable() {
/* 128 */     return this.isMutable;
/*     */   }
/*     */ 
/*     */   
/*     */   public final void makeImmutable() {
/* 133 */     this.isMutable = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public E remove(int index) {
/* 138 */     ensureIsMutable();
/* 139 */     return super.remove(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean remove(Object o) {
/* 144 */     ensureIsMutable();
/* 145 */     int index = indexOf(o);
/* 146 */     if (index == -1) {
/* 147 */       return false;
/*     */     }
/* 149 */     remove(index);
/* 150 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean removeAll(Collection<?> c) {
/* 155 */     ensureIsMutable();
/* 156 */     return super.removeAll(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean retainAll(Collection<?> c) {
/* 161 */     ensureIsMutable();
/* 162 */     return super.retainAll(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public E set(int index, E element) {
/* 167 */     ensureIsMutable();
/* 168 */     return super.set(index, element);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void ensureIsMutable() {
/* 176 */     if (!this.isMutable)
/* 177 */       throw new UnsupportedOperationException(); 
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\AbstractProtobufList.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */