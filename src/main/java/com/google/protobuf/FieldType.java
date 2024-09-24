/*     */ package com.google.protobuf;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.ParameterizedType;
/*     */ import java.lang.reflect.Type;
/*     */ import java.lang.reflect.TypeVariable;
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
/*     */ public enum FieldType
/*     */ {
/*  42 */   DOUBLE(0, Collection.SCALAR, JavaType.DOUBLE),
/*  43 */   FLOAT(1, Collection.SCALAR, JavaType.FLOAT),
/*  44 */   INT64(2, Collection.SCALAR, JavaType.LONG),
/*  45 */   UINT64(3, Collection.SCALAR, JavaType.LONG),
/*  46 */   INT32(4, Collection.SCALAR, JavaType.INT),
/*  47 */   FIXED64(5, Collection.SCALAR, JavaType.LONG),
/*  48 */   FIXED32(6, Collection.SCALAR, JavaType.INT),
/*  49 */   BOOL(7, Collection.SCALAR, JavaType.BOOLEAN),
/*  50 */   STRING(8, Collection.SCALAR, JavaType.STRING),
/*  51 */   MESSAGE(9, Collection.SCALAR, JavaType.MESSAGE),
/*  52 */   BYTES(10, Collection.SCALAR, JavaType.BYTE_STRING),
/*  53 */   UINT32(11, Collection.SCALAR, JavaType.INT),
/*  54 */   ENUM(12, Collection.SCALAR, JavaType.ENUM),
/*  55 */   SFIXED32(13, Collection.SCALAR, JavaType.INT),
/*  56 */   SFIXED64(14, Collection.SCALAR, JavaType.LONG),
/*  57 */   SINT32(15, Collection.SCALAR, JavaType.INT),
/*  58 */   SINT64(16, Collection.SCALAR, JavaType.LONG),
/*  59 */   GROUP(17, Collection.SCALAR, JavaType.MESSAGE),
/*  60 */   DOUBLE_LIST(18, Collection.VECTOR, JavaType.DOUBLE),
/*  61 */   FLOAT_LIST(19, Collection.VECTOR, JavaType.FLOAT),
/*  62 */   INT64_LIST(20, Collection.VECTOR, JavaType.LONG),
/*  63 */   UINT64_LIST(21, Collection.VECTOR, JavaType.LONG),
/*  64 */   INT32_LIST(22, Collection.VECTOR, JavaType.INT),
/*  65 */   FIXED64_LIST(23, Collection.VECTOR, JavaType.LONG),
/*  66 */   FIXED32_LIST(24, Collection.VECTOR, JavaType.INT),
/*  67 */   BOOL_LIST(25, Collection.VECTOR, JavaType.BOOLEAN),
/*  68 */   STRING_LIST(26, Collection.VECTOR, JavaType.STRING),
/*  69 */   MESSAGE_LIST(27, Collection.VECTOR, JavaType.MESSAGE),
/*  70 */   BYTES_LIST(28, Collection.VECTOR, JavaType.BYTE_STRING),
/*  71 */   UINT32_LIST(29, Collection.VECTOR, JavaType.INT),
/*  72 */   ENUM_LIST(30, Collection.VECTOR, JavaType.ENUM),
/*  73 */   SFIXED32_LIST(31, Collection.VECTOR, JavaType.INT),
/*  74 */   SFIXED64_LIST(32, Collection.VECTOR, JavaType.LONG),
/*  75 */   SINT32_LIST(33, Collection.VECTOR, JavaType.INT),
/*  76 */   SINT64_LIST(34, Collection.VECTOR, JavaType.LONG),
/*  77 */   DOUBLE_LIST_PACKED(35, Collection.PACKED_VECTOR, JavaType.DOUBLE),
/*  78 */   FLOAT_LIST_PACKED(36, Collection.PACKED_VECTOR, JavaType.FLOAT),
/*  79 */   INT64_LIST_PACKED(37, Collection.PACKED_VECTOR, JavaType.LONG),
/*  80 */   UINT64_LIST_PACKED(38, Collection.PACKED_VECTOR, JavaType.LONG),
/*  81 */   INT32_LIST_PACKED(39, Collection.PACKED_VECTOR, JavaType.INT),
/*  82 */   FIXED64_LIST_PACKED(40, Collection.PACKED_VECTOR, JavaType.LONG),
/*  83 */   FIXED32_LIST_PACKED(41, Collection.PACKED_VECTOR, JavaType.INT),
/*  84 */   BOOL_LIST_PACKED(42, Collection.PACKED_VECTOR, JavaType.BOOLEAN),
/*  85 */   UINT32_LIST_PACKED(43, Collection.PACKED_VECTOR, JavaType.INT),
/*  86 */   ENUM_LIST_PACKED(44, Collection.PACKED_VECTOR, JavaType.ENUM),
/*  87 */   SFIXED32_LIST_PACKED(45, Collection.PACKED_VECTOR, JavaType.INT),
/*  88 */   SFIXED64_LIST_PACKED(46, Collection.PACKED_VECTOR, JavaType.LONG),
/*  89 */   SINT32_LIST_PACKED(47, Collection.PACKED_VECTOR, JavaType.INT),
/*  90 */   SINT64_LIST_PACKED(48, Collection.PACKED_VECTOR, JavaType.LONG),
/*  91 */   GROUP_LIST(49, Collection.VECTOR, JavaType.MESSAGE),
/*  92 */   MAP(50, Collection.MAP, JavaType.VOID);
/*     */   
/*     */   private final JavaType javaType;
/*     */   
/*     */   private final int id;
/*     */   
/*     */   private final Collection collection;
/*     */   
/*     */   FieldType(int id, Collection collection, JavaType javaType) {
/* 101 */     this.id = id;
/* 102 */     this.collection = collection;
/* 103 */     this.javaType = javaType;
/*     */     
/* 105 */     switch (collection) {
/*     */       case BYTE_STRING:
/* 107 */         this.elementType = javaType.getBoxedType();
/*     */         break;
/*     */       case MESSAGE:
/* 110 */         this.elementType = javaType.getBoxedType();
/*     */         break;
/*     */       
/*     */       default:
/* 114 */         this.elementType = null;
/*     */         break;
/*     */     } 
/*     */     
/* 118 */     boolean primitiveScalar = false;
/* 119 */     if (collection == Collection.SCALAR) {
/* 120 */       switch (javaType) {
/*     */         case BYTE_STRING:
/*     */         case MESSAGE:
/*     */         case STRING:
/*     */           break;
/*     */         default:
/* 126 */           primitiveScalar = true;
/*     */           break;
/*     */       } 
/*     */     }
/* 130 */     this.primitiveScalar = primitiveScalar;
/*     */   }
/*     */   private final Class<?> elementType; private final boolean primitiveScalar; private static final FieldType[] VALUES; private static final Type[] EMPTY_TYPES;
/*     */   
/*     */   public int id() {
/* 135 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JavaType getJavaType() {
/* 143 */     return this.javaType;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPacked() {
/* 148 */     return Collection.PACKED_VECTOR.equals(this.collection);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPrimitiveScalar() {
/* 156 */     return this.primitiveScalar;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isScalar() {
/* 161 */     return (this.collection == Collection.SCALAR);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isList() {
/* 166 */     return this.collection.isList();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMap() {
/* 171 */     return (this.collection == Collection.MAP);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidForField(Field field) {
/* 176 */     if (Collection.VECTOR.equals(this.collection)) {
/* 177 */       return isValidForList(field);
/*     */     }
/* 179 */     return this.javaType.getType().isAssignableFrom(field.getType());
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isValidForList(Field field) {
/* 184 */     Class<?> clazz = field.getType();
/* 185 */     if (!this.javaType.getType().isAssignableFrom(clazz))
/*     */     {
/* 187 */       return false;
/*     */     }
/* 189 */     Type[] types = EMPTY_TYPES;
/* 190 */     Type genericType = field.getGenericType();
/* 191 */     if (genericType instanceof ParameterizedType) {
/* 192 */       types = ((ParameterizedType)field.getGenericType()).getActualTypeArguments();
/*     */     }
/* 194 */     Type listParameter = getListParameter(clazz, types);
/* 195 */     if (!(listParameter instanceof Class))
/*     */     {
/* 197 */       return true;
/*     */     }
/* 199 */     return this.elementType.isAssignableFrom((Class)listParameter);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static FieldType forId(int id) {
/* 208 */     if (id < 0 || id >= VALUES.length) {
/* 209 */       return null;
/*     */     }
/* 211 */     return VALUES[id];
/*     */   }
/*     */   
/*     */   static {
/* 215 */     EMPTY_TYPES = new Type[0];
/*     */ 
/*     */     
/* 218 */     FieldType[] values = values();
/* 219 */     VALUES = new FieldType[values.length];
/* 220 */     for (FieldType type : values) {
/* 221 */       VALUES[type.id] = type;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Type getGenericSuperList(Class<?> clazz) {
/* 232 */     Type[] genericInterfaces = clazz.getGenericInterfaces();
/* 233 */     for (Type genericInterface : genericInterfaces) {
/* 234 */       if (genericInterface instanceof ParameterizedType) {
/* 235 */         ParameterizedType parameterizedType = (ParameterizedType)genericInterface;
/* 236 */         Class<?> rawType = (Class)parameterizedType.getRawType();
/* 237 */         if (List.class.isAssignableFrom(rawType)) {
/* 238 */           return genericInterface;
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 244 */     Type type = clazz.getGenericSuperclass();
/* 245 */     if (type instanceof ParameterizedType) {
/* 246 */       ParameterizedType parameterizedType = (ParameterizedType)type;
/* 247 */       Class<?> rawType = (Class)parameterizedType.getRawType();
/* 248 */       if (List.class.isAssignableFrom(rawType)) {
/* 249 */         return type;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 254 */     return null;
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
/*     */   private static Type getListParameter(Class<?> clazz, Type[] realTypes) {
/* 269 */     label41: while (clazz != List.class) {
/*     */       
/* 271 */       Type genericType = getGenericSuperList(clazz);
/* 272 */       if (genericType instanceof ParameterizedType) {
/*     */         
/* 274 */         ParameterizedType parameterizedType = (ParameterizedType)genericType;
/* 275 */         Type[] superArgs = parameterizedType.getActualTypeArguments();
/* 276 */         for (int i = 0; i < superArgs.length; i++) {
/* 277 */           Type superArg = superArgs[i];
/* 278 */           if (superArg instanceof TypeVariable) {
/*     */ 
/*     */             
/* 281 */             TypeVariable[] arrayOfTypeVariable = (TypeVariable[])clazz.getTypeParameters();
/* 282 */             if (realTypes.length != arrayOfTypeVariable.length) {
/* 283 */               throw new RuntimeException("Type array mismatch");
/*     */             }
/*     */ 
/*     */             
/* 287 */             boolean foundReplacement = false;
/* 288 */             for (int j = 0; j < arrayOfTypeVariable.length; j++) {
/* 289 */               if (superArg == arrayOfTypeVariable[j]) {
/* 290 */                 Type realType = realTypes[j];
/* 291 */                 superArgs[i] = realType;
/* 292 */                 foundReplacement = true;
/*     */                 break;
/*     */               } 
/*     */             } 
/* 296 */             if (!foundReplacement) {
/* 297 */               throw new RuntimeException("Unable to find replacement for " + superArg);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         
/* 302 */         Class<?> parent = (Class)parameterizedType.getRawType();
/*     */         
/* 304 */         realTypes = superArgs;
/* 305 */         clazz = parent;
/*     */ 
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 311 */       realTypes = EMPTY_TYPES;
/* 312 */       for (Class<?> iface : clazz.getInterfaces()) {
/* 313 */         if (List.class.isAssignableFrom(iface)) {
/* 314 */           clazz = iface;
/*     */           continue label41;
/*     */         } 
/*     */       } 
/* 318 */       clazz = clazz.getSuperclass();
/*     */     } 
/*     */     
/* 321 */     if (realTypes.length != 1) {
/* 322 */       throw new RuntimeException("Unable to identify parameter type for List<T>");
/*     */     }
/* 324 */     return realTypes[0];
/*     */   }
/*     */   
/*     */   enum Collection {
/* 328 */     SCALAR(false),
/* 329 */     VECTOR(true),
/* 330 */     PACKED_VECTOR(true),
/* 331 */     MAP(false);
/*     */     
/*     */     private final boolean isList;
/*     */     
/*     */     Collection(boolean isList) {
/* 336 */       this.isList = isList;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isList() {
/* 341 */       return this.isList;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\FieldType.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */