/*     */ package com.google.protobuf;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.nio.Buffer;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedExceptionAction;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import sun.misc.Unsafe;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class UnsafeUtil
/*     */ {
/*  44 */   private static final Unsafe UNSAFE = getUnsafe();
/*  45 */   private static final Class<?> MEMORY_CLASS = Android.getMemoryClass();
/*  46 */   private static final boolean IS_ANDROID_64 = determineAndroidSupportByAddressSize(long.class);
/*  47 */   private static final boolean IS_ANDROID_32 = determineAndroidSupportByAddressSize(int.class);
/*  48 */   private static final MemoryAccessor MEMORY_ACCESSOR = getMemoryAccessor();
/*     */   
/*  50 */   private static final boolean HAS_UNSAFE_BYTEBUFFER_OPERATIONS = supportsUnsafeByteBufferOperations();
/*  51 */   private static final boolean HAS_UNSAFE_ARRAY_OPERATIONS = supportsUnsafeArrayOperations();
/*     */   
/*  53 */   static final long BYTE_ARRAY_BASE_OFFSET = arrayBaseOffset(byte[].class);
/*     */ 
/*     */ 
/*     */   
/*  57 */   private static final long BOOLEAN_ARRAY_BASE_OFFSET = arrayBaseOffset(boolean[].class);
/*  58 */   private static final long BOOLEAN_ARRAY_INDEX_SCALE = arrayIndexScale(boolean[].class);
/*     */   
/*  60 */   private static final long INT_ARRAY_BASE_OFFSET = arrayBaseOffset(int[].class);
/*  61 */   private static final long INT_ARRAY_INDEX_SCALE = arrayIndexScale(int[].class);
/*     */   
/*  63 */   private static final long LONG_ARRAY_BASE_OFFSET = arrayBaseOffset(long[].class);
/*  64 */   private static final long LONG_ARRAY_INDEX_SCALE = arrayIndexScale(long[].class);
/*     */   
/*  66 */   private static final long FLOAT_ARRAY_BASE_OFFSET = arrayBaseOffset(float[].class);
/*  67 */   private static final long FLOAT_ARRAY_INDEX_SCALE = arrayIndexScale(float[].class);
/*     */   
/*  69 */   private static final long DOUBLE_ARRAY_BASE_OFFSET = arrayBaseOffset(double[].class);
/*  70 */   private static final long DOUBLE_ARRAY_INDEX_SCALE = arrayIndexScale(double[].class);
/*     */   
/*  72 */   private static final long OBJECT_ARRAY_BASE_OFFSET = arrayBaseOffset(Object[].class);
/*  73 */   private static final long OBJECT_ARRAY_INDEX_SCALE = arrayIndexScale(Object[].class);
/*     */   
/*  75 */   private static final long BUFFER_ADDRESS_OFFSET = fieldOffset(bufferAddressField());
/*     */   
/*     */   private static final int STRIDE = 8;
/*     */   private static final int STRIDE_ALIGNMENT_MASK = 7;
/*  79 */   private static final int BYTE_ARRAY_ALIGNMENT = (int)(BYTE_ARRAY_BASE_OFFSET & 0x7L);
/*     */ 
/*     */   
/*  82 */   static final boolean IS_BIG_ENDIAN = (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN);
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean hasUnsafeArrayOperations() {
/*  87 */     return HAS_UNSAFE_ARRAY_OPERATIONS;
/*     */   }
/*     */   
/*     */   static boolean hasUnsafeByteBufferOperations() {
/*  91 */     return HAS_UNSAFE_BYTEBUFFER_OPERATIONS;
/*     */   }
/*     */   
/*     */   static boolean isAndroid64() {
/*  95 */     return IS_ANDROID_64;
/*     */   }
/*     */ 
/*     */   
/*     */   static <T> T allocateInstance(Class<T> clazz) {
/*     */     try {
/* 101 */       return (T)UNSAFE.allocateInstance(clazz);
/* 102 */     } catch (InstantiationException e) {
/* 103 */       throw new IllegalStateException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   static long objectFieldOffset(Field field) {
/* 108 */     return MEMORY_ACCESSOR.objectFieldOffset(field);
/*     */   }
/*     */   
/*     */   private static int arrayBaseOffset(Class<?> clazz) {
/* 112 */     return HAS_UNSAFE_ARRAY_OPERATIONS ? MEMORY_ACCESSOR.arrayBaseOffset(clazz) : -1;
/*     */   }
/*     */   
/*     */   private static int arrayIndexScale(Class<?> clazz) {
/* 116 */     return HAS_UNSAFE_ARRAY_OPERATIONS ? MEMORY_ACCESSOR.arrayIndexScale(clazz) : -1;
/*     */   }
/*     */   
/*     */   static byte getByte(Object target, long offset) {
/* 120 */     return MEMORY_ACCESSOR.getByte(target, offset);
/*     */   }
/*     */   
/*     */   static void putByte(Object target, long offset, byte value) {
/* 124 */     MEMORY_ACCESSOR.putByte(target, offset, value);
/*     */   }
/*     */   
/*     */   static int getInt(Object target, long offset) {
/* 128 */     return MEMORY_ACCESSOR.getInt(target, offset);
/*     */   }
/*     */   
/*     */   static void putInt(Object target, long offset, int value) {
/* 132 */     MEMORY_ACCESSOR.putInt(target, offset, value);
/*     */   }
/*     */   
/*     */   static long getLong(Object target, long offset) {
/* 136 */     return MEMORY_ACCESSOR.getLong(target, offset);
/*     */   }
/*     */   
/*     */   static void putLong(Object target, long offset, long value) {
/* 140 */     MEMORY_ACCESSOR.putLong(target, offset, value);
/*     */   }
/*     */   
/*     */   static boolean getBoolean(Object target, long offset) {
/* 144 */     return MEMORY_ACCESSOR.getBoolean(target, offset);
/*     */   }
/*     */   
/*     */   static void putBoolean(Object target, long offset, boolean value) {
/* 148 */     MEMORY_ACCESSOR.putBoolean(target, offset, value);
/*     */   }
/*     */   
/*     */   static float getFloat(Object target, long offset) {
/* 152 */     return MEMORY_ACCESSOR.getFloat(target, offset);
/*     */   }
/*     */   
/*     */   static void putFloat(Object target, long offset, float value) {
/* 156 */     MEMORY_ACCESSOR.putFloat(target, offset, value);
/*     */   }
/*     */   
/*     */   static double getDouble(Object target, long offset) {
/* 160 */     return MEMORY_ACCESSOR.getDouble(target, offset);
/*     */   }
/*     */   
/*     */   static void putDouble(Object target, long offset, double value) {
/* 164 */     MEMORY_ACCESSOR.putDouble(target, offset, value);
/*     */   }
/*     */   
/*     */   static Object getObject(Object target, long offset) {
/* 168 */     return MEMORY_ACCESSOR.getObject(target, offset);
/*     */   }
/*     */   
/*     */   static void putObject(Object target, long offset, Object value) {
/* 172 */     MEMORY_ACCESSOR.putObject(target, offset, value);
/*     */   }
/*     */   
/*     */   static byte getByte(byte[] target, long index) {
/* 176 */     return MEMORY_ACCESSOR.getByte(target, BYTE_ARRAY_BASE_OFFSET + index);
/*     */   }
/*     */   
/*     */   static void putByte(byte[] target, long index, byte value) {
/* 180 */     MEMORY_ACCESSOR.putByte(target, BYTE_ARRAY_BASE_OFFSET + index, value);
/*     */   }
/*     */   
/*     */   static int getInt(int[] target, long index) {
/* 184 */     return MEMORY_ACCESSOR.getInt(target, INT_ARRAY_BASE_OFFSET + index * INT_ARRAY_INDEX_SCALE);
/*     */   }
/*     */   
/*     */   static void putInt(int[] target, long index, int value) {
/* 188 */     MEMORY_ACCESSOR.putInt(target, INT_ARRAY_BASE_OFFSET + index * INT_ARRAY_INDEX_SCALE, value);
/*     */   }
/*     */   
/*     */   static long getLong(long[] target, long index) {
/* 192 */     return MEMORY_ACCESSOR.getLong(target, LONG_ARRAY_BASE_OFFSET + index * LONG_ARRAY_INDEX_SCALE);
/*     */   }
/*     */ 
/*     */   
/*     */   static void putLong(long[] target, long index, long value) {
/* 197 */     MEMORY_ACCESSOR.putLong(target, LONG_ARRAY_BASE_OFFSET + index * LONG_ARRAY_INDEX_SCALE, value);
/*     */   }
/*     */ 
/*     */   
/*     */   static boolean getBoolean(boolean[] target, long index) {
/* 202 */     return MEMORY_ACCESSOR.getBoolean(target, BOOLEAN_ARRAY_BASE_OFFSET + index * BOOLEAN_ARRAY_INDEX_SCALE);
/*     */   }
/*     */ 
/*     */   
/*     */   static void putBoolean(boolean[] target, long index, boolean value) {
/* 207 */     MEMORY_ACCESSOR.putBoolean(target, BOOLEAN_ARRAY_BASE_OFFSET + index * BOOLEAN_ARRAY_INDEX_SCALE, value);
/*     */   }
/*     */ 
/*     */   
/*     */   static float getFloat(float[] target, long index) {
/* 212 */     return MEMORY_ACCESSOR.getFloat(target, FLOAT_ARRAY_BASE_OFFSET + index * FLOAT_ARRAY_INDEX_SCALE);
/*     */   }
/*     */ 
/*     */   
/*     */   static void putFloat(float[] target, long index, float value) {
/* 217 */     MEMORY_ACCESSOR.putFloat(target, FLOAT_ARRAY_BASE_OFFSET + index * FLOAT_ARRAY_INDEX_SCALE, value);
/*     */   }
/*     */ 
/*     */   
/*     */   static double getDouble(double[] target, long index) {
/* 222 */     return MEMORY_ACCESSOR.getDouble(target, DOUBLE_ARRAY_BASE_OFFSET + index * DOUBLE_ARRAY_INDEX_SCALE);
/*     */   }
/*     */ 
/*     */   
/*     */   static void putDouble(double[] target, long index, double value) {
/* 227 */     MEMORY_ACCESSOR.putDouble(target, DOUBLE_ARRAY_BASE_OFFSET + index * DOUBLE_ARRAY_INDEX_SCALE, value);
/*     */   }
/*     */ 
/*     */   
/*     */   static Object getObject(Object[] target, long index) {
/* 232 */     return MEMORY_ACCESSOR.getObject(target, OBJECT_ARRAY_BASE_OFFSET + index * OBJECT_ARRAY_INDEX_SCALE);
/*     */   }
/*     */ 
/*     */   
/*     */   static void putObject(Object[] target, long index, Object value) {
/* 237 */     MEMORY_ACCESSOR.putObject(target, OBJECT_ARRAY_BASE_OFFSET + index * OBJECT_ARRAY_INDEX_SCALE, value);
/*     */   }
/*     */ 
/*     */   
/*     */   static void copyMemory(byte[] src, long srcIndex, long targetOffset, long length) {
/* 242 */     MEMORY_ACCESSOR.copyMemory(src, srcIndex, targetOffset, length);
/*     */   }
/*     */   
/*     */   static void copyMemory(long srcOffset, byte[] target, long targetIndex, long length) {
/* 246 */     MEMORY_ACCESSOR.copyMemory(srcOffset, target, targetIndex, length);
/*     */   }
/*     */   
/*     */   static void copyMemory(byte[] src, long srcIndex, byte[] target, long targetIndex, long length) {
/* 250 */     System.arraycopy(src, (int)srcIndex, target, (int)targetIndex, (int)length);
/*     */   }
/*     */   
/*     */   static byte getByte(long address) {
/* 254 */     return MEMORY_ACCESSOR.getByte(address);
/*     */   }
/*     */   
/*     */   static void putByte(long address, byte value) {
/* 258 */     MEMORY_ACCESSOR.putByte(address, value);
/*     */   }
/*     */   
/*     */   static int getInt(long address) {
/* 262 */     return MEMORY_ACCESSOR.getInt(address);
/*     */   }
/*     */   
/*     */   static void putInt(long address, int value) {
/* 266 */     MEMORY_ACCESSOR.putInt(address, value);
/*     */   }
/*     */   
/*     */   static long getLong(long address) {
/* 270 */     return MEMORY_ACCESSOR.getLong(address);
/*     */   }
/*     */   
/*     */   static void putLong(long address, long value) {
/* 274 */     MEMORY_ACCESSOR.putLong(address, value);
/*     */   }
/*     */ 
/*     */   
/*     */   static long addressOffset(ByteBuffer buffer) {
/* 279 */     return MEMORY_ACCESSOR.getLong(buffer, BUFFER_ADDRESS_OFFSET);
/*     */   }
/*     */   
/*     */   static Object getStaticObject(Field field) {
/* 283 */     return MEMORY_ACCESSOR.getStaticObject(field);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Unsafe getUnsafe() {
/* 290 */     Unsafe unsafe = null;
/*     */     
/*     */     try {
/* 293 */       unsafe = AccessController.<Unsafe>doPrivileged(new PrivilegedExceptionAction<Unsafe>()
/*     */           {
/*     */             public Unsafe run() throws Exception
/*     */             {
/* 297 */               Class<Unsafe> k = Unsafe.class;
/*     */               
/* 299 */               for (Field f : k.getDeclaredFields()) {
/* 300 */                 f.setAccessible(true);
/* 301 */                 Object x = f.get(null);
/* 302 */                 if (k.isInstance(x)) {
/* 303 */                   return k.cast(x);
/*     */                 }
/*     */               } 
/*     */               
/* 307 */               return null;
/*     */             }
/*     */           });
/* 310 */     } catch (Throwable throwable) {}
/*     */ 
/*     */ 
/*     */     
/* 314 */     return unsafe;
/*     */   }
/*     */ 
/*     */   
/*     */   private static MemoryAccessor getMemoryAccessor() {
/* 319 */     if (UNSAFE == null) {
/* 320 */       return null;
/*     */     }
/* 322 */     if (Android.isOnAndroidDevice()) {
/* 323 */       if (IS_ANDROID_64)
/* 324 */         return new Android64MemoryAccessor(UNSAFE); 
/* 325 */       if (IS_ANDROID_32) {
/* 326 */         return new Android32MemoryAccessor(UNSAFE);
/*     */       }
/* 328 */       return null;
/*     */     } 
/*     */ 
/*     */     
/* 332 */     return new JvmMemoryAccessor(UNSAFE);
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean supportsUnsafeArrayOperations() {
/* 337 */     if (UNSAFE == null) {
/* 338 */       return false;
/*     */     }
/*     */     try {
/* 341 */       Class<?> clazz = UNSAFE.getClass();
/* 342 */       clazz.getMethod("objectFieldOffset", new Class[] { Field.class });
/* 343 */       clazz.getMethod("arrayBaseOffset", new Class[] { Class.class });
/* 344 */       clazz.getMethod("arrayIndexScale", new Class[] { Class.class });
/* 345 */       clazz.getMethod("getInt", new Class[] { Object.class, long.class });
/* 346 */       clazz.getMethod("putInt", new Class[] { Object.class, long.class, int.class });
/* 347 */       clazz.getMethod("getLong", new Class[] { Object.class, long.class });
/* 348 */       clazz.getMethod("putLong", new Class[] { Object.class, long.class, long.class });
/* 349 */       clazz.getMethod("getObject", new Class[] { Object.class, long.class });
/* 350 */       clazz.getMethod("putObject", new Class[] { Object.class, long.class, Object.class });
/* 351 */       if (Android.isOnAndroidDevice()) {
/* 352 */         return true;
/*     */       }
/* 354 */       clazz.getMethod("getByte", new Class[] { Object.class, long.class });
/* 355 */       clazz.getMethod("putByte", new Class[] { Object.class, long.class, byte.class });
/* 356 */       clazz.getMethod("getBoolean", new Class[] { Object.class, long.class });
/* 357 */       clazz.getMethod("putBoolean", new Class[] { Object.class, long.class, boolean.class });
/* 358 */       clazz.getMethod("getFloat", new Class[] { Object.class, long.class });
/* 359 */       clazz.getMethod("putFloat", new Class[] { Object.class, long.class, float.class });
/* 360 */       clazz.getMethod("getDouble", new Class[] { Object.class, long.class });
/* 361 */       clazz.getMethod("putDouble", new Class[] { Object.class, long.class, double.class });
/*     */       
/* 363 */       return true;
/* 364 */     } catch (Throwable e) {
/*     */ 
/*     */ 
/*     */       
/* 368 */       Logger.getLogger(UnsafeUtil.class.getName())
/* 369 */         .log(Level.WARNING, "platform method missing - proto runtime falling back to safer methods: " + e);
/*     */ 
/*     */ 
/*     */       
/* 373 */       return false;
/*     */     } 
/*     */   }
/*     */   private static boolean supportsUnsafeByteBufferOperations() {
/* 377 */     if (UNSAFE == null) {
/* 378 */       return false;
/*     */     }
/*     */     try {
/* 381 */       Class<?> clazz = UNSAFE.getClass();
/*     */       
/* 383 */       clazz.getMethod("objectFieldOffset", new Class[] { Field.class });
/* 384 */       clazz.getMethod("getLong", new Class[] { Object.class, long.class });
/*     */       
/* 386 */       if (bufferAddressField() == null) {
/* 387 */         return false;
/*     */       }
/*     */       
/* 390 */       if (Android.isOnAndroidDevice()) {
/* 391 */         return true;
/*     */       }
/* 393 */       clazz.getMethod("getByte", new Class[] { long.class });
/* 394 */       clazz.getMethod("putByte", new Class[] { long.class, byte.class });
/* 395 */       clazz.getMethod("getInt", new Class[] { long.class });
/* 396 */       clazz.getMethod("putInt", new Class[] { long.class, int.class });
/* 397 */       clazz.getMethod("getLong", new Class[] { long.class });
/* 398 */       clazz.getMethod("putLong", new Class[] { long.class, long.class });
/* 399 */       clazz.getMethod("copyMemory", new Class[] { long.class, long.class, long.class });
/* 400 */       clazz.getMethod("copyMemory", new Class[] { Object.class, long.class, Object.class, long.class, long.class });
/* 401 */       return true;
/* 402 */     } catch (Throwable e) {
/*     */ 
/*     */ 
/*     */       
/* 406 */       Logger.getLogger(UnsafeUtil.class.getName())
/* 407 */         .log(Level.WARNING, "platform method missing - proto runtime falling back to safer methods: " + e);
/*     */ 
/*     */ 
/*     */       
/* 411 */       return false;
/*     */     } 
/*     */   }
/*     */   private static boolean determineAndroidSupportByAddressSize(Class<?> addressClass) {
/* 415 */     if (!Android.isOnAndroidDevice()) {
/* 416 */       return false;
/*     */     }
/*     */     try {
/* 419 */       Class<?> clazz = MEMORY_CLASS;
/* 420 */       clazz.getMethod("peekLong", new Class[] { addressClass, boolean.class });
/* 421 */       clazz.getMethod("pokeLong", new Class[] { addressClass, long.class, boolean.class });
/* 422 */       clazz.getMethod("pokeInt", new Class[] { addressClass, int.class, boolean.class });
/* 423 */       clazz.getMethod("peekInt", new Class[] { addressClass, boolean.class });
/* 424 */       clazz.getMethod("pokeByte", new Class[] { addressClass, byte.class });
/* 425 */       clazz.getMethod("peekByte", new Class[] { addressClass });
/* 426 */       clazz.getMethod("pokeByteArray", new Class[] { addressClass, byte[].class, int.class, int.class });
/* 427 */       clazz.getMethod("peekByteArray", new Class[] { addressClass, byte[].class, int.class, int.class });
/* 428 */       return true;
/* 429 */     } catch (Throwable t) {
/* 430 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static Field bufferAddressField() {
/* 436 */     if (Android.isOnAndroidDevice()) {
/*     */ 
/*     */       
/* 439 */       Field field1 = field(Buffer.class, "effectiveDirectAddress");
/* 440 */       if (field1 != null) {
/* 441 */         return field1;
/*     */       }
/*     */     } 
/* 444 */     Field field = field(Buffer.class, "address");
/* 445 */     return (field != null && field.getType() == long.class) ? field : null;
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
/*     */   private static int firstDifferingByteIndexNativeEndian(long left, long right) {
/* 459 */     int n = IS_BIG_ENDIAN ? Long.numberOfLeadingZeros(left ^ right) : Long.numberOfTrailingZeros(left ^ right);
/* 460 */     return n >> 3;
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
/*     */   static int mismatch(byte[] left, int leftOff, byte[] right, int rightOff, int length) {
/* 472 */     if (leftOff < 0 || rightOff < 0 || length < 0 || leftOff + length > left.length || rightOff + length > right.length)
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 477 */       throw new IndexOutOfBoundsException();
/*     */     }
/*     */     
/* 480 */     int index = 0;
/* 481 */     if (HAS_UNSAFE_ARRAY_OPERATIONS) {
/* 482 */       int leftAlignment = BYTE_ARRAY_ALIGNMENT + leftOff & 0x7;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 489 */       for (; index < length && (leftAlignment & 0x7) != 0; 
/* 490 */         index++, leftAlignment++) {
/* 491 */         if (left[leftOff + index] != right[rightOff + index]) {
/* 492 */           return index;
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 498 */       int strideLength = (length - index & 0xFFFFFFF8) + index;
/*     */ 
/*     */ 
/*     */       
/* 502 */       for (; index < strideLength; index += 8) {
/* 503 */         long leftLongWord = getLong(left, BYTE_ARRAY_BASE_OFFSET + leftOff + index);
/* 504 */         long rightLongWord = getLong(right, BYTE_ARRAY_BASE_OFFSET + rightOff + index);
/* 505 */         if (leftLongWord != rightLongWord)
/*     */         {
/* 507 */           return index + firstDifferingByteIndexNativeEndian(leftLongWord, rightLongWord);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 514 */     for (; index < length; index++) {
/* 515 */       if (left[leftOff + index] != right[rightOff + index]) {
/* 516 */         return index;
/*     */       }
/*     */     } 
/* 519 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static long fieldOffset(Field field) {
/* 527 */     return (field == null || MEMORY_ACCESSOR == null) ? -1L : MEMORY_ACCESSOR.objectFieldOffset(field);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Field field(Class<?> clazz, String fieldName) {
/*     */     Field field;
/*     */     try {
/* 536 */       field = clazz.getDeclaredField(fieldName);
/* 537 */     } catch (Throwable t) {
/*     */       
/* 539 */       field = null;
/*     */     } 
/* 541 */     return field;
/*     */   }
/*     */   
/*     */   private static abstract class MemoryAccessor
/*     */   {
/*     */     Unsafe unsafe;
/*     */     
/*     */     MemoryAccessor(Unsafe unsafe) {
/* 549 */       this.unsafe = unsafe;
/*     */     }
/*     */     
/*     */     public final long objectFieldOffset(Field field) {
/* 553 */       return this.unsafe.objectFieldOffset(field);
/*     */     }
/*     */     
/*     */     public abstract byte getByte(Object param1Object, long param1Long);
/*     */     
/*     */     public abstract void putByte(Object param1Object, long param1Long, byte param1Byte);
/*     */     
/*     */     public final int getInt(Object target, long offset) {
/* 561 */       return this.unsafe.getInt(target, offset);
/*     */     }
/*     */     
/*     */     public final void putInt(Object target, long offset, int value) {
/* 565 */       this.unsafe.putInt(target, offset, value);
/*     */     }
/*     */     
/*     */     public final long getLong(Object target, long offset) {
/* 569 */       return this.unsafe.getLong(target, offset);
/*     */     }
/*     */     
/*     */     public final void putLong(Object target, long offset, long value) {
/* 573 */       this.unsafe.putLong(target, offset, value);
/*     */     }
/*     */     
/*     */     public abstract boolean getBoolean(Object param1Object, long param1Long);
/*     */     
/*     */     public abstract void putBoolean(Object param1Object, long param1Long, boolean param1Boolean);
/*     */     
/*     */     public abstract float getFloat(Object param1Object, long param1Long);
/*     */     
/*     */     public abstract void putFloat(Object param1Object, long param1Long, float param1Float);
/*     */     
/*     */     public abstract double getDouble(Object param1Object, long param1Long);
/*     */     
/*     */     public abstract void putDouble(Object param1Object, long param1Long, double param1Double);
/*     */     
/*     */     public final Object getObject(Object target, long offset) {
/* 589 */       return this.unsafe.getObject(target, offset);
/*     */     }
/*     */     
/*     */     public final void putObject(Object target, long offset, Object value) {
/* 593 */       this.unsafe.putObject(target, offset, value);
/*     */     }
/*     */     
/*     */     public final int arrayBaseOffset(Class<?> clazz) {
/* 597 */       return this.unsafe.arrayBaseOffset(clazz);
/*     */     }
/*     */     
/*     */     public final int arrayIndexScale(Class<?> clazz) {
/* 601 */       return this.unsafe.arrayIndexScale(clazz);
/*     */     }
/*     */     
/*     */     public abstract byte getByte(long param1Long);
/*     */     
/*     */     public abstract void putByte(long param1Long, byte param1Byte);
/*     */     
/*     */     public abstract int getInt(long param1Long);
/*     */     
/*     */     public abstract void putInt(long param1Long, int param1Int);
/*     */     
/*     */     public abstract long getLong(long param1Long);
/*     */     
/*     */     public abstract void putLong(long param1Long1, long param1Long2);
/*     */     
/*     */     public abstract Object getStaticObject(Field param1Field);
/*     */     
/*     */     public abstract void copyMemory(long param1Long1, byte[] param1ArrayOfbyte, long param1Long2, long param1Long3);
/*     */     
/*     */     public abstract void copyMemory(byte[] param1ArrayOfbyte, long param1Long1, long param1Long2, long param1Long3);
/*     */   }
/*     */   
/*     */   private static final class JvmMemoryAccessor
/*     */     extends MemoryAccessor {
/*     */     JvmMemoryAccessor(Unsafe unsafe) {
/* 626 */       super(unsafe);
/*     */     }
/*     */ 
/*     */     
/*     */     public byte getByte(long address) {
/* 631 */       return this.unsafe.getByte(address);
/*     */     }
/*     */ 
/*     */     
/*     */     public void putByte(long address, byte value) {
/* 636 */       this.unsafe.putByte(address, value);
/*     */     }
/*     */ 
/*     */     
/*     */     public int getInt(long address) {
/* 641 */       return this.unsafe.getInt(address);
/*     */     }
/*     */ 
/*     */     
/*     */     public void putInt(long address, int value) {
/* 646 */       this.unsafe.putInt(address, value);
/*     */     }
/*     */ 
/*     */     
/*     */     public long getLong(long address) {
/* 651 */       return this.unsafe.getLong(address);
/*     */     }
/*     */ 
/*     */     
/*     */     public void putLong(long address, long value) {
/* 656 */       this.unsafe.putLong(address, value);
/*     */     }
/*     */ 
/*     */     
/*     */     public byte getByte(Object target, long offset) {
/* 661 */       return this.unsafe.getByte(target, offset);
/*     */     }
/*     */ 
/*     */     
/*     */     public void putByte(Object target, long offset, byte value) {
/* 666 */       this.unsafe.putByte(target, offset, value);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean getBoolean(Object target, long offset) {
/* 671 */       return this.unsafe.getBoolean(target, offset);
/*     */     }
/*     */ 
/*     */     
/*     */     public void putBoolean(Object target, long offset, boolean value) {
/* 676 */       this.unsafe.putBoolean(target, offset, value);
/*     */     }
/*     */ 
/*     */     
/*     */     public float getFloat(Object target, long offset) {
/* 681 */       return this.unsafe.getFloat(target, offset);
/*     */     }
/*     */ 
/*     */     
/*     */     public void putFloat(Object target, long offset, float value) {
/* 686 */       this.unsafe.putFloat(target, offset, value);
/*     */     }
/*     */ 
/*     */     
/*     */     public double getDouble(Object target, long offset) {
/* 691 */       return this.unsafe.getDouble(target, offset);
/*     */     }
/*     */ 
/*     */     
/*     */     public void putDouble(Object target, long offset, double value) {
/* 696 */       this.unsafe.putDouble(target, offset, value);
/*     */     }
/*     */ 
/*     */     
/*     */     public void copyMemory(long srcOffset, byte[] target, long targetIndex, long length) {
/* 701 */       this.unsafe.copyMemory(null, srcOffset, target, UnsafeUtil.BYTE_ARRAY_BASE_OFFSET + targetIndex, length);
/*     */     }
/*     */ 
/*     */     
/*     */     public void copyMemory(byte[] src, long srcIndex, long targetOffset, long length) {
/* 706 */       this.unsafe.copyMemory(src, UnsafeUtil.BYTE_ARRAY_BASE_OFFSET + srcIndex, null, targetOffset, length);
/*     */     }
/*     */ 
/*     */     
/*     */     public Object getStaticObject(Field field) {
/* 711 */       return getObject(this.unsafe.staticFieldBase(field), this.unsafe.staticFieldOffset(field));
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class Android64MemoryAccessor
/*     */     extends MemoryAccessor {
/*     */     Android64MemoryAccessor(Unsafe unsafe) {
/* 718 */       super(unsafe);
/*     */     }
/*     */ 
/*     */     
/*     */     public byte getByte(long address) {
/* 723 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public void putByte(long address, byte value) {
/* 728 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public int getInt(long address) {
/* 733 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public void putInt(long address, int value) {
/* 738 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public long getLong(long address) {
/* 743 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public void putLong(long address, long value) {
/* 748 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public byte getByte(Object target, long offset) {
/* 753 */       if (UnsafeUtil.IS_BIG_ENDIAN) {
/* 754 */         return UnsafeUtil.getByteBigEndian(target, offset);
/*     */       }
/* 756 */       return UnsafeUtil.getByteLittleEndian(target, offset);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void putByte(Object target, long offset, byte value) {
/* 762 */       if (UnsafeUtil.IS_BIG_ENDIAN) {
/* 763 */         UnsafeUtil.putByteBigEndian(target, offset, value);
/*     */       } else {
/* 765 */         UnsafeUtil.putByteLittleEndian(target, offset, value);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean getBoolean(Object target, long offset) {
/* 771 */       if (UnsafeUtil.IS_BIG_ENDIAN) {
/* 772 */         return UnsafeUtil.getBooleanBigEndian(target, offset);
/*     */       }
/* 774 */       return UnsafeUtil.getBooleanLittleEndian(target, offset);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void putBoolean(Object target, long offset, boolean value) {
/* 780 */       if (UnsafeUtil.IS_BIG_ENDIAN) {
/* 781 */         UnsafeUtil.putBooleanBigEndian(target, offset, value);
/*     */       } else {
/* 783 */         UnsafeUtil.putBooleanLittleEndian(target, offset, value);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public float getFloat(Object target, long offset) {
/* 789 */       return Float.intBitsToFloat(getInt(target, offset));
/*     */     }
/*     */ 
/*     */     
/*     */     public void putFloat(Object target, long offset, float value) {
/* 794 */       putInt(target, offset, Float.floatToIntBits(value));
/*     */     }
/*     */ 
/*     */     
/*     */     public double getDouble(Object target, long offset) {
/* 799 */       return Double.longBitsToDouble(getLong(target, offset));
/*     */     }
/*     */ 
/*     */     
/*     */     public void putDouble(Object target, long offset, double value) {
/* 804 */       putLong(target, offset, Double.doubleToLongBits(value));
/*     */     }
/*     */ 
/*     */     
/*     */     public void copyMemory(long srcOffset, byte[] target, long targetIndex, long length) {
/* 809 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public void copyMemory(byte[] src, long srcIndex, long targetOffset, long length) {
/* 814 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public Object getStaticObject(Field field) {
/*     */       try {
/* 820 */         return field.get(null);
/* 821 */       } catch (IllegalAccessException e) {
/* 822 */         return null;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static final class Android32MemoryAccessor
/*     */     extends MemoryAccessor
/*     */   {
/*     */     private static final long SMALL_ADDRESS_MASK = -1L;
/*     */     
/*     */     private static int smallAddress(long address) {
/* 834 */       return (int)(0xFFFFFFFFFFFFFFFFL & address);
/*     */     }
/*     */     
/*     */     Android32MemoryAccessor(Unsafe unsafe) {
/* 838 */       super(unsafe);
/*     */     }
/*     */ 
/*     */     
/*     */     public byte getByte(long address) {
/* 843 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public void putByte(long address, byte value) {
/* 848 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public int getInt(long address) {
/* 853 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public void putInt(long address, int value) {
/* 858 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public long getLong(long address) {
/* 863 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public void putLong(long address, long value) {
/* 868 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public byte getByte(Object target, long offset) {
/* 873 */       if (UnsafeUtil.IS_BIG_ENDIAN) {
/* 874 */         return UnsafeUtil.getByteBigEndian(target, offset);
/*     */       }
/* 876 */       return UnsafeUtil.getByteLittleEndian(target, offset);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void putByte(Object target, long offset, byte value) {
/* 882 */       if (UnsafeUtil.IS_BIG_ENDIAN) {
/* 883 */         UnsafeUtil.putByteBigEndian(target, offset, value);
/*     */       } else {
/* 885 */         UnsafeUtil.putByteLittleEndian(target, offset, value);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean getBoolean(Object target, long offset) {
/* 891 */       if (UnsafeUtil.IS_BIG_ENDIAN) {
/* 892 */         return UnsafeUtil.getBooleanBigEndian(target, offset);
/*     */       }
/* 894 */       return UnsafeUtil.getBooleanLittleEndian(target, offset);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void putBoolean(Object target, long offset, boolean value) {
/* 900 */       if (UnsafeUtil.IS_BIG_ENDIAN) {
/* 901 */         UnsafeUtil.putBooleanBigEndian(target, offset, value);
/*     */       } else {
/* 903 */         UnsafeUtil.putBooleanLittleEndian(target, offset, value);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public float getFloat(Object target, long offset) {
/* 909 */       return Float.intBitsToFloat(getInt(target, offset));
/*     */     }
/*     */ 
/*     */     
/*     */     public void putFloat(Object target, long offset, float value) {
/* 914 */       putInt(target, offset, Float.floatToIntBits(value));
/*     */     }
/*     */ 
/*     */     
/*     */     public double getDouble(Object target, long offset) {
/* 919 */       return Double.longBitsToDouble(getLong(target, offset));
/*     */     }
/*     */ 
/*     */     
/*     */     public void putDouble(Object target, long offset, double value) {
/* 924 */       putLong(target, offset, Double.doubleToLongBits(value));
/*     */     }
/*     */ 
/*     */     
/*     */     public void copyMemory(long srcOffset, byte[] target, long targetIndex, long length) {
/* 929 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public void copyMemory(byte[] src, long srcIndex, long targetOffset, long length) {
/* 934 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public Object getStaticObject(Field field) {
/*     */       try {
/* 940 */         return field.get(null);
/* 941 */       } catch (IllegalAccessException e) {
/* 942 */         return null;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private static byte getByteBigEndian(Object target, long offset) {
/* 948 */     return (byte)(getInt(target, offset & 0xFFFFFFFFFFFFFFFCL) >>> (int)(((offset ^ 0xFFFFFFFFFFFFFFFFL) & 0x3L) << 3L) & 0xFF);
/*     */   }
/*     */   
/*     */   private static byte getByteLittleEndian(Object target, long offset) {
/* 952 */     return (byte)(getInt(target, offset & 0xFFFFFFFFFFFFFFFCL) >>> (int)((offset & 0x3L) << 3L) & 0xFF);
/*     */   }
/*     */   
/*     */   private static void putByteBigEndian(Object target, long offset, byte value) {
/* 956 */     int intValue = getInt(target, offset & 0xFFFFFFFFFFFFFFFCL);
/* 957 */     int shift = (((int)offset ^ 0xFFFFFFFF) & 0x3) << 3;
/* 958 */     int output = intValue & (255 << shift ^ 0xFFFFFFFF) | (0xFF & value) << shift;
/* 959 */     putInt(target, offset & 0xFFFFFFFFFFFFFFFCL, output);
/*     */   }
/*     */   
/*     */   private static void putByteLittleEndian(Object target, long offset, byte value) {
/* 963 */     int intValue = getInt(target, offset & 0xFFFFFFFFFFFFFFFCL);
/* 964 */     int shift = ((int)offset & 0x3) << 3;
/* 965 */     int output = intValue & (255 << shift ^ 0xFFFFFFFF) | (0xFF & value) << shift;
/* 966 */     putInt(target, offset & 0xFFFFFFFFFFFFFFFCL, output);
/*     */   }
/*     */   
/*     */   private static boolean getBooleanBigEndian(Object target, long offset) {
/* 970 */     return (getByteBigEndian(target, offset) != 0);
/*     */   }
/*     */   
/*     */   private static boolean getBooleanLittleEndian(Object target, long offset) {
/* 974 */     return (getByteLittleEndian(target, offset) != 0);
/*     */   }
/*     */   
/*     */   private static void putBooleanBigEndian(Object target, long offset, boolean value) {
/* 978 */     putByteBigEndian(target, offset, (byte)(value ? 1 : 0));
/*     */   }
/*     */   
/*     */   private static void putBooleanLittleEndian(Object target, long offset, boolean value) {
/* 982 */     putByteLittleEndian(target, offset, (byte)(value ? 1 : 0));
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\UnsafeUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */