/*     */ package com.google.protobuf;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.AbstractList;
/*     */ import java.util.AbstractMap;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.RandomAccess;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Internal
/*     */ {
/*  57 */   static final Charset UTF_8 = Charset.forName("UTF-8");
/*  58 */   static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
/*     */   private static final int DEFAULT_BUFFER_SIZE = 4096;
/*     */   
/*     */   static <T> T checkNotNull(T obj) {
/*  62 */     if (obj == null) {
/*  63 */       throw new NullPointerException();
/*     */     }
/*  65 */     return obj;
/*     */   }
/*     */ 
/*     */   
/*     */   static <T> T checkNotNull(T obj, String message) {
/*  70 */     if (obj == null) {
/*  71 */       throw new NullPointerException(message);
/*     */     }
/*  73 */     return obj;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String stringDefaultValue(String bytes) {
/*  99 */     return new String(bytes.getBytes(ISO_8859_1), UTF_8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ByteString bytesDefaultValue(String bytes) {
/* 110 */     return ByteString.copyFrom(bytes.getBytes(ISO_8859_1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] byteArrayDefaultValue(String bytes) {
/* 118 */     return bytes.getBytes(ISO_8859_1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ByteBuffer byteBufferDefaultValue(String bytes) {
/* 127 */     return ByteBuffer.wrap(byteArrayDefaultValue(bytes));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ByteBuffer copyByteBuffer(ByteBuffer source) {
/* 138 */     ByteBuffer temp = source.duplicate();
/*     */ 
/*     */     
/* 141 */     temp.clear();
/* 142 */     ByteBuffer result = ByteBuffer.allocate(temp.capacity());
/* 143 */     result.put(temp);
/* 144 */     result.clear();
/* 145 */     return result;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isValidUtf8(ByteString byteString) {
/* 175 */     return byteString.isValidUtf8();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isValidUtf8(byte[] byteArray) {
/* 180 */     return Utf8.isValidUtf8(byteArray);
/*     */   }
/*     */ 
/*     */   
/*     */   public static byte[] toByteArray(String value) {
/* 185 */     return value.getBytes(UTF_8);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String toStringUtf8(byte[] bytes) {
/* 190 */     return new String(bytes, UTF_8);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int hashLong(long n) {
/* 224 */     return (int)(n ^ n >>> 32L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int hashBoolean(boolean b) {
/* 233 */     return b ? 1231 : 1237;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int hashEnum(EnumLite e) {
/* 244 */     return e.getNumber();
/*     */   }
/*     */ 
/*     */   
/*     */   public static int hashEnumList(List<? extends EnumLite> list) {
/* 249 */     int hash = 1;
/* 250 */     for (EnumLite e : list) {
/* 251 */       hash = 31 * hash + hashEnum(e);
/*     */     }
/* 253 */     return hash;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean equals(List<byte[]> a, List<byte[]> b) {
/* 258 */     if (a.size() != b.size()) return false; 
/* 259 */     for (int i = 0; i < a.size(); i++) {
/* 260 */       if (!Arrays.equals(a.get(i), b.get(i))) {
/* 261 */         return false;
/*     */       }
/*     */     } 
/* 264 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int hashCode(List<byte[]> list) {
/* 269 */     int hash = 1;
/* 270 */     for (byte[] bytes : list) {
/* 271 */       hash = 31 * hash + hashCode(bytes);
/*     */     }
/* 273 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int hashCode(byte[] bytes) {
/* 282 */     return hashCode(bytes, 0, bytes.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static int hashCode(byte[] bytes, int offset, int length) {
/* 291 */     int h = partialHash(length, bytes, offset, length);
/* 292 */     return (h == 0) ? 1 : h;
/*     */   }
/*     */ 
/*     */   
/*     */   static int partialHash(int h, byte[] bytes, int offset, int length) {
/* 297 */     for (int i = offset; i < offset + length; i++) {
/* 298 */       h = h * 31 + bytes[i];
/*     */     }
/* 300 */     return h;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean equalsByteBuffer(ByteBuffer a, ByteBuffer b) {
/* 305 */     if (a.capacity() != b.capacity()) {
/* 306 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 310 */     return a.duplicate().clear().equals(b.duplicate().clear());
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean equalsByteBuffer(List<ByteBuffer> a, List<ByteBuffer> b) {
/* 315 */     if (a.size() != b.size()) {
/* 316 */       return false;
/*     */     }
/* 318 */     for (int i = 0; i < a.size(); i++) {
/* 319 */       if (!equalsByteBuffer(a.get(i), b.get(i))) {
/* 320 */         return false;
/*     */       }
/*     */     } 
/* 323 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int hashCodeByteBuffer(List<ByteBuffer> list) {
/* 328 */     int hash = 1;
/* 329 */     for (ByteBuffer bytes : list) {
/* 330 */       hash = 31 * hash + hashCodeByteBuffer(bytes);
/*     */     }
/* 332 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int hashCodeByteBuffer(ByteBuffer bytes) {
/* 339 */     if (bytes.hasArray()) {
/*     */       
/* 341 */       int i = partialHash(bytes.capacity(), bytes.array(), bytes.arrayOffset(), bytes.capacity());
/* 342 */       return (i == 0) ? 1 : i;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 347 */     int bufferSize = (bytes.capacity() > 4096) ? 4096 : bytes.capacity();
/* 348 */     byte[] buffer = new byte[bufferSize];
/* 349 */     ByteBuffer duplicated = bytes.duplicate();
/* 350 */     duplicated.clear();
/* 351 */     int h = bytes.capacity();
/* 352 */     while (duplicated.remaining() > 0) {
/*     */       
/* 354 */       int length = (duplicated.remaining() <= bufferSize) ? duplicated.remaining() : bufferSize;
/* 355 */       duplicated.get(buffer, 0, length);
/* 356 */       h = partialHash(h, buffer, 0, length);
/*     */     } 
/* 358 */     return (h == 0) ? 1 : h;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T extends MessageLite> T getDefaultInstance(Class<T> clazz) {
/*     */     try {
/* 365 */       Method method = clazz.getMethod("getDefaultInstance", new Class[0]);
/* 366 */       return (T)method.invoke(method, new Object[0]);
/* 367 */     } catch (Exception e) {
/* 368 */       throw new RuntimeException("Failed to get default instance for " + clazz, e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 374 */   public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
/*     */ 
/*     */   
/* 377 */   public static final ByteBuffer EMPTY_BYTE_BUFFER = ByteBuffer.wrap(EMPTY_BYTE_ARRAY);
/*     */ 
/*     */ 
/*     */   
/* 381 */   public static final CodedInputStream EMPTY_CODED_INPUT_STREAM = CodedInputStream.newInstance(EMPTY_BYTE_ARRAY);
/*     */ 
/*     */ 
/*     */   
/*     */   static Object mergeMessage(Object destination, Object source) {
/* 386 */     return ((MessageLite)destination).toBuilder().mergeFrom((MessageLite)source).buildPartial();
/*     */   }
/*     */   
/*     */   public static interface EnumLite {
/*     */     int getNumber(); }
/*     */   
/*     */   public static interface EnumLiteMap<T extends EnumLite> {
/*     */     T findValueByNumber(int param1Int); }
/*     */   
/*     */   public static interface EnumVerifier {
/*     */     boolean isInRange(int param1Int);
/*     */   }
/*     */   
/*     */   public static class ListAdapter<F, T> extends AbstractList<T> {
/*     */     private final List<F> fromList;
/*     */     private final Converter<F, T> converter;
/*     */     
/*     */     public ListAdapter(List<F> fromList, Converter<F, T> converter) {
/* 404 */       this.fromList = fromList;
/* 405 */       this.converter = converter;
/*     */     }
/*     */ 
/*     */     
/*     */     public T get(int index) {
/* 410 */       return this.converter.convert(this.fromList.get(index));
/*     */     }
/*     */ 
/*     */     
/*     */     public int size() {
/* 415 */       return this.fromList.size();
/*     */     }
/*     */     
/*     */     public static interface Converter<F, T>
/*     */     {
/*     */       T convert(F param2F);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class MapAdapter<K, V, RealValue>
/*     */     extends AbstractMap<K, V> {
/*     */     private final Map<K, RealValue> realMap;
/*     */     private final Converter<RealValue, V> valueConverter;
/*     */     
/*     */     public static <T extends EnumLite> Converter<Integer, T> newEnumConverter(final EnumLiteMap<T> enumMap, final T unrecognizedValue) {
/* 430 */       return new Converter<Integer, T>()
/*     */         {
/*     */           public T doForward(Integer value) {
/* 433 */             T result = (T)enumMap.findValueByNumber(value.intValue());
/* 434 */             return (result == null) ? (T)unrecognizedValue : result;
/*     */           }
/*     */ 
/*     */           
/*     */           public Integer doBackward(T value) {
/* 439 */             return Integer.valueOf(value.getNumber());
/*     */           }
/*     */         };
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public MapAdapter(Map<K, RealValue> realMap, Converter<RealValue, V> valueConverter) {
/* 448 */       this.realMap = realMap;
/* 449 */       this.valueConverter = valueConverter;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public V get(Object key) {
/* 455 */       RealValue result = this.realMap.get(key);
/* 456 */       if (result == null) {
/* 457 */         return null;
/*     */       }
/* 459 */       return this.valueConverter.doForward(result);
/*     */     }
/*     */ 
/*     */     
/*     */     public V put(K key, V value) {
/* 464 */       RealValue oldValue = this.realMap.put(key, this.valueConverter.doBackward(value));
/* 465 */       if (oldValue == null) {
/* 466 */         return null;
/*     */       }
/* 468 */       return this.valueConverter.doForward(oldValue);
/*     */     } public static interface Converter<A, B> {
/*     */       B doForward(A param2A);
/*     */       A doBackward(B param2B); }
/*     */     public Set<Entry<K, V>> entrySet() {
/* 473 */       return new SetAdapter(this.realMap.entrySet());
/*     */     }
/*     */     
/*     */     private class SetAdapter
/*     */       extends AbstractSet<Entry<K, V>>
/*     */     {
/*     */       public SetAdapter(Set<Entry<K, RealValue>> realSet) {
/* 480 */         this.realSet = realSet;
/*     */       }
/*     */       private final Set<Entry<K, RealValue>> realSet;
/*     */       
/*     */       public Iterator<Entry<K, V>> iterator() {
/* 485 */         return new IteratorAdapter(this.realSet.iterator());
/*     */       }
/*     */ 
/*     */       
/*     */       public int size() {
/* 490 */         return this.realSet.size();
/*     */       }
/*     */     }
/*     */     
/*     */     private class IteratorAdapter implements Iterator<Entry<K, V>> {
/*     */       private final Iterator<Entry<K, RealValue>> realIterator;
/*     */       
/*     */       public IteratorAdapter(Iterator<Entry<K, RealValue>> realIterator) {
/* 498 */         this.realIterator = realIterator;
/*     */       }
/*     */ 
/*     */       
/*     */       public boolean hasNext() {
/* 503 */         return this.realIterator.hasNext();
/*     */       }
/*     */ 
/*     */       
/*     */       public Entry<K, V> next() {
/* 508 */         return new EntryAdapter(this.realIterator.next());
/*     */       }
/*     */ 
/*     */       
/*     */       public void remove() {
/* 513 */         this.realIterator.remove();
/*     */       }
/*     */     }
/*     */     
/*     */     private class EntryAdapter implements Entry<K, V> {
/*     */       private final Entry<K, RealValue> realEntry;
/*     */       
/*     */       public EntryAdapter(Entry<K, RealValue> realEntry) {
/* 521 */         this.realEntry = realEntry;
/*     */       }
/*     */ 
/*     */       
/*     */       public K getKey() {
/* 526 */         return this.realEntry.getKey();
/*     */       }
/*     */ 
/*     */       
/*     */       public V getValue() {
/* 531 */         return (V) MapAdapter.this.valueConverter.doForward(this.realEntry.getValue());
/*     */       }
/*     */ 
/*     */       
/*     */       public V setValue(V value) {
/* 536 */         RealValue oldValue = this.realEntry.setValue((RealValue) MapAdapter.this.valueConverter.doBackward(value));
/* 537 */         if (oldValue == null) {
/* 538 */           return null;
/*     */         }
/* 540 */         return (V) MapAdapter.this.valueConverter.doForward(oldValue);
/*     */       }
/*     */ 
/*     */       
/*     */       public boolean equals(Object o) {
/* 545 */         if (o == this) {
/* 546 */           return true;
/*     */         }
/* 548 */         if (!(o instanceof Map.Entry)) {
/* 549 */           return false;
/*     */         }
/*     */         
/* 552 */         Entry<?, ?> other = (Entry<?, ?>)o;
/* 553 */         return (getKey().equals(other.getKey()) && getValue().equals(getValue()));
/*     */       }
/*     */ 
/*     */       
/*     */       public int hashCode() {
/* 558 */         return this.realEntry.hashCode();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface ProtobufList<E> extends List<E>, RandomAccess {
/*     */     void makeImmutable();
/*     */     
/*     */     boolean isModifiable();
/*     */     
/*     */     ProtobufList<E> mutableCopyWithCapacity(int param1Int);
/*     */   }
/*     */   
/*     */   public static interface IntList extends ProtobufList<Integer> {
/*     */     int getInt(int param1Int);
/*     */     
/*     */     void addInt(int param1Int);
/*     */     
/*     */     int setInt(int param1Int1, int param1Int2);
/*     */     
/*     */     IntList mutableCopyWithCapacity(int param1Int);
/*     */   }
/*     */   
/*     */   public static interface BooleanList extends ProtobufList<Boolean> {
/*     */     boolean getBoolean(int param1Int);
/*     */     
/*     */     void addBoolean(boolean param1Boolean);
/*     */     
/*     */     boolean setBoolean(int param1Int, boolean param1Boolean);
/*     */     
/*     */     BooleanList mutableCopyWithCapacity(int param1Int);
/*     */   }
/*     */   
/*     */   public static interface LongList extends ProtobufList<Long> {
/*     */     long getLong(int param1Int);
/*     */     
/*     */     void addLong(long param1Long);
/*     */     
/*     */     long setLong(int param1Int, long param1Long);
/*     */     
/*     */     LongList mutableCopyWithCapacity(int param1Int);
/*     */   }
/*     */   
/*     */   public static interface DoubleList extends ProtobufList<Double> {
/*     */     double getDouble(int param1Int);
/*     */     
/*     */     void addDouble(double param1Double);
/*     */     
/*     */     double setDouble(int param1Int, double param1Double);
/*     */     
/*     */     DoubleList mutableCopyWithCapacity(int param1Int);
/*     */   }
/*     */   
/*     */   public static interface FloatList extends ProtobufList<Float> {
/*     */     float getFloat(int param1Int);
/*     */     
/*     */     void addFloat(float param1Float);
/*     */     
/*     */     float setFloat(int param1Int, float param1Float);
/*     */     
/*     */     FloatList mutableCopyWithCapacity(int param1Int);
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\Internal.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */