/*     */ package org.traccar.database;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.ResultSetMetaData;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.sql.DataSource;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.traccar.Context;
/*     */ import org.traccar.model.MiscFormatter;
/*     */ import org.traccar.model.Permission;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class QueryBuilder
/*     */ {
/*  47 */   private static final Logger LOGGER = LoggerFactory.getLogger(QueryBuilder.class);
/*     */   
/*  49 */   private final Map<String, List<Integer>> indexMap = new HashMap<>();
/*     */   private Connection connection;
/*     */   private PreparedStatement statement;
/*     */   private final String query;
/*     */   private final boolean returnGeneratedKeys;
/*     */   
/*     */   private QueryBuilder(DataSource dataSource, String query, boolean returnGeneratedKeys) throws SQLException {
/*  56 */     this.query = query;
/*  57 */     this.returnGeneratedKeys = returnGeneratedKeys;
/*  58 */     if (query != null) {
/*  59 */       this.connection = dataSource.getConnection();
/*  60 */       String parsedQuery = parse(query.trim(), this.indexMap);
/*     */       try {
/*  62 */         if (returnGeneratedKeys) {
/*  63 */           this.statement = this.connection.prepareStatement(parsedQuery, 1);
/*     */         } else {
/*  65 */           this.statement = this.connection.prepareStatement(parsedQuery);
/*     */         } 
/*  67 */       } catch (SQLException error) {
/*  68 */         this.connection.close();
/*  69 */         throw error;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static String parse(String query, Map<String, List<Integer>> paramMap) {
/*  76 */     int length = query.length();
/*  77 */     StringBuilder parsedQuery = new StringBuilder(length);
/*  78 */     boolean inSingleQuote = false;
/*  79 */     boolean inDoubleQuote = false;
/*  80 */     int index = 1;
/*     */     
/*  82 */     for (int i = 0; i < length; i++) {
/*     */       
/*  84 */       char c = query.charAt(i);
/*     */ 
/*     */       
/*  87 */       if (inSingleQuote) {
/*  88 */         if (c == '\'') {
/*  89 */           inSingleQuote = false;
/*     */         }
/*  91 */       } else if (inDoubleQuote) {
/*  92 */         if (c == '"') {
/*  93 */           inDoubleQuote = false;
/*     */         
/*     */         }
/*     */       
/*     */       }
/*  98 */       else if (c == '\'') {
/*  99 */         inSingleQuote = true;
/* 100 */       } else if (c == '"') {
/* 101 */         inDoubleQuote = true;
/* 102 */       } else if (c == ':' && i + 1 < length && 
/* 103 */         Character.isJavaIdentifierStart(query.charAt(i + 1))) {
/*     */ 
/*     */         
/* 106 */         int j = i + 2;
/* 107 */         while (j < length && Character.isJavaIdentifierPart(query.charAt(j))) {
/* 108 */           j++;
/*     */         }
/*     */         
/* 111 */         String name = query.substring(i + 1, j);
/* 112 */         c = '?';
/* 113 */         i += name.length();
/* 114 */         name = name.toLowerCase();
/*     */ 
/*     */         
/* 117 */         List<Integer> indexList = paramMap.get(name);
/* 118 */         if (indexList == null) {
/* 119 */           indexList = new LinkedList<>();
/* 120 */           paramMap.put(name, indexList);
/*     */         } 
/* 122 */         indexList.add(Integer.valueOf(index));
/*     */         
/* 124 */         index++;
/*     */       } 
/*     */ 
/*     */       
/* 128 */       parsedQuery.append(c);
/*     */     } 
/*     */     
/* 131 */     return parsedQuery.toString();
/*     */   }
/*     */   
/*     */   public static QueryBuilder create(DataSource dataSource, String query) throws SQLException {
/* 135 */     return new QueryBuilder(dataSource, query, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static QueryBuilder create(DataSource dataSource, String query, boolean returnGeneratedKeys) throws SQLException {
/* 140 */     return new QueryBuilder(dataSource, query, returnGeneratedKeys);
/*     */   }
/*     */   
/*     */   private List<Integer> indexes(String name) {
/* 144 */     name = name.toLowerCase();
/* 145 */     List<Integer> result = this.indexMap.get(name);
/* 146 */     if (result == null) {
/* 147 */       result = new LinkedList<>();
/*     */     }
/* 149 */     return result;
/*     */   }
/*     */   
/*     */   public QueryBuilder setBoolean(String name, boolean value) throws SQLException {
/* 153 */     for (Iterator<Integer> iterator = indexes(name).iterator(); iterator.hasNext(); ) { int i = ((Integer)iterator.next()).intValue();
/*     */       try {
/* 155 */         this.statement.setBoolean(i, value);
/* 156 */       } catch (SQLException error) {
/* 157 */         this.statement.close();
/* 158 */         this.connection.close();
/* 159 */         throw error;
/*     */       }  }
/*     */     
/* 162 */     return this;
/*     */   }
/*     */   
/*     */   public QueryBuilder setInteger(String name, int value) throws SQLException {
/* 166 */     for (Iterator<Integer> iterator = indexes(name).iterator(); iterator.hasNext(); ) { int i = ((Integer)iterator.next()).intValue();
/*     */       try {
/* 168 */         this.statement.setInt(i, value);
/* 169 */       } catch (SQLException error) {
/* 170 */         this.statement.close();
/* 171 */         this.connection.close();
/* 172 */         throw error;
/*     */       }  }
/*     */     
/* 175 */     return this;
/*     */   }
/*     */   
/*     */   public QueryBuilder setLong(String name, long value) throws SQLException {
/* 179 */     return setLong(name, value, false);
/*     */   }
/*     */   
/*     */   public QueryBuilder setLong(String name, long value, boolean nullIfZero) throws SQLException {
/* 183 */     for (Iterator<Integer> iterator = indexes(name).iterator(); iterator.hasNext(); ) { int i = ((Integer)iterator.next()).intValue();
/*     */       try {
/* 185 */         if (value == 0L && nullIfZero) {
/* 186 */           this.statement.setNull(i, 4); continue;
/*     */         } 
/* 188 */         this.statement.setLong(i, value);
/*     */       }
/* 190 */       catch (SQLException error) {
/* 191 */         this.statement.close();
/* 192 */         this.connection.close();
/* 193 */         throw error;
/*     */       }  }
/*     */     
/* 196 */     return this;
/*     */   }
/*     */   
/*     */   public QueryBuilder setDouble(String name, double value) throws SQLException {
/* 200 */     for (Iterator<Integer> iterator = indexes(name).iterator(); iterator.hasNext(); ) { int i = ((Integer)iterator.next()).intValue();
/*     */       try {
/* 202 */         this.statement.setDouble(i, value);
/* 203 */       } catch (SQLException error) {
/* 204 */         this.statement.close();
/* 205 */         this.connection.close();
/* 206 */         throw error;
/*     */       }  }
/*     */     
/* 209 */     return this;
/*     */   }
/*     */   
/*     */   public QueryBuilder setString(String name, String value) throws SQLException {
/* 213 */     for (Iterator<Integer> iterator = indexes(name).iterator(); iterator.hasNext(); ) { int i = ((Integer)iterator.next()).intValue();
/*     */       try {
/* 215 */         if (value == null) {
/* 216 */           this.statement.setNull(i, 12); continue;
/*     */         } 
/* 218 */         this.statement.setString(i, value);
/*     */       }
/* 220 */       catch (SQLException error) {
/* 221 */         this.statement.close();
/* 222 */         this.connection.close();
/* 223 */         throw error;
/*     */       }  }
/*     */     
/* 226 */     return this;
/*     */   }
/*     */   
/*     */   public QueryBuilder setDate(String name, Date value) throws SQLException {
/* 230 */     for (Iterator<Integer> iterator = indexes(name).iterator(); iterator.hasNext(); ) { int i = ((Integer)iterator.next()).intValue();
/*     */       try {
/* 232 */         if (value == null) {
/* 233 */           this.statement.setNull(i, 93); continue;
/*     */         } 
/* 235 */         this.statement.setTimestamp(i, new Timestamp(value.getTime()));
/*     */       }
/* 237 */       catch (SQLException error) {
/* 238 */         this.statement.close();
/* 239 */         this.connection.close();
/* 240 */         throw error;
/*     */       }  }
/*     */     
/* 243 */     return this;
/*     */   }
/*     */   
/*     */   public QueryBuilder setBlob(String name, byte[] value) throws SQLException {
/* 247 */     for (Iterator<Integer> iterator = indexes(name).iterator(); iterator.hasNext(); ) { int i = ((Integer)iterator.next()).intValue();
/*     */       try {
/* 249 */         if (value == null) {
/* 250 */           this.statement.setNull(i, 2004); continue;
/*     */         } 
/* 252 */         this.statement.setBytes(i, value);
/*     */       }
/* 254 */       catch (SQLException error) {
/* 255 */         this.statement.close();
/* 256 */         this.connection.close();
/* 257 */         throw error;
/*     */       }  }
/*     */     
/* 260 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public QueryBuilder setObject(Object object) throws SQLException {
/* 265 */     Method[] methods = object.getClass().getMethods();
/*     */     
/* 267 */     for (Method method : methods) {
/* 268 */       if (method.getName().startsWith("get") && (method.getParameterTypes()).length == 0 && 
/* 269 */         !method.isAnnotationPresent((Class)QueryIgnore.class)) {
/* 270 */         String name = method.getName().substring(3);
/*     */         try {
/* 272 */           if (method.getReturnType().equals(boolean.class)) {
/* 273 */             setBoolean(name, ((Boolean)method.invoke(object, new Object[0])).booleanValue());
/* 274 */           } else if (method.getReturnType().equals(int.class)) {
/* 275 */             setInteger(name, ((Integer)method.invoke(object, new Object[0])).intValue());
/* 276 */           } else if (method.getReturnType().equals(long.class)) {
/* 277 */             setLong(name, ((Long)method.invoke(object, new Object[0])).longValue(), name.endsWith("Id"));
/* 278 */           } else if (method.getReturnType().equals(double.class)) {
/* 279 */             setDouble(name, ((Double)method.invoke(object, new Object[0])).doubleValue());
/* 280 */           } else if (method.getReturnType().equals(String.class)) {
/* 281 */             setString(name, (String)method.invoke(object, new Object[0]));
/* 282 */           } else if (method.getReturnType().equals(Date.class)) {
/* 283 */             setDate(name, (Date)method.invoke(object, new Object[0]));
/* 284 */           } else if (method.getReturnType().equals(byte[].class)) {
/* 285 */             setBlob(name, (byte[])method.invoke(object, new Object[0]));
/*     */           }
/* 287 */           else if (method.getReturnType().equals(Map.class) && 
/* 288 */             Context.getConfig().getBoolean("database.xml")) {
/* 289 */             setString(name, MiscFormatter.toXmlString((Map)method.invoke(object, new Object[0])));
/*     */           } else {
/* 291 */             setString(name, Context.getObjectMapper().writeValueAsString(method.invoke(object, new Object[0])));
/*     */           }
/*     */         
/* 294 */         } catch (IllegalAccessException|InvocationTargetException|com.fasterxml.jackson.core.JsonProcessingException error) {
/* 295 */           LOGGER.warn("Get property error", error);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 300 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> T executeQuerySingle(Class<T> clazz) throws SQLException {
/* 308 */     Collection<T> result = executeQuery(clazz);
/* 309 */     if (!result.isEmpty()) {
/* 310 */       return result.iterator().next();
/*     */     }
/* 312 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private <T> void addProcessors(List<ResultSetProcessor<T>> processors, final Class<?> parameterType, final Method method, final String name) {
/* 320 */     if (parameterType.equals(boolean.class)) {
/* 321 */       processors.add(new ResultSetProcessor<T>()
/*     */           {
/*     */             public void process(T object, ResultSet resultSet) throws SQLException {
/*     */               try {
/* 325 */                 method.invoke(object, new Object[] { Boolean.valueOf(resultSet.getBoolean(this.val$name)) });
/* 326 */               } catch (IllegalAccessException|InvocationTargetException error) {
/* 327 */                 QueryBuilder.LOGGER.warn("Set property error", error);
/*     */               } 
/*     */             }
/*     */           });
/* 331 */     } else if (parameterType.equals(int.class)) {
/* 332 */       processors.add(new ResultSetProcessor<T>()
/*     */           {
/*     */             public void process(T object, ResultSet resultSet) throws SQLException {
/*     */               try {
/* 336 */                 method.invoke(object, new Object[] { Integer.valueOf(resultSet.getInt(this.val$name)) });
/* 337 */               } catch (IllegalAccessException|InvocationTargetException error) {
/* 338 */                 QueryBuilder.LOGGER.warn("Set property error", error);
/*     */               } 
/*     */             }
/*     */           });
/* 342 */     } else if (parameterType.equals(long.class)) {
/* 343 */       processors.add(new ResultSetProcessor<T>()
/*     */           {
/*     */             public void process(T object, ResultSet resultSet) throws SQLException {
/*     */               try {
/* 347 */                 method.invoke(object, new Object[] { Long.valueOf(resultSet.getLong(this.val$name)) });
/* 348 */               } catch (IllegalAccessException|InvocationTargetException error) {
/* 349 */                 QueryBuilder.LOGGER.warn("Set property error", error);
/*     */               } 
/*     */             }
/*     */           });
/* 353 */     } else if (parameterType.equals(double.class)) {
/* 354 */       processors.add(new ResultSetProcessor<T>()
/*     */           {
/*     */             public void process(T object, ResultSet resultSet) throws SQLException {
/*     */               try {
/* 358 */                 method.invoke(object, new Object[] { Double.valueOf(resultSet.getDouble(this.val$name)) });
/* 359 */               } catch (IllegalAccessException|InvocationTargetException error) {
/* 360 */                 QueryBuilder.LOGGER.warn("Set property error", error);
/*     */               } 
/*     */             }
/*     */           });
/* 364 */     } else if (parameterType.equals(String.class)) {
/* 365 */       processors.add(new ResultSetProcessor<T>()
/*     */           {
/*     */             public void process(T object, ResultSet resultSet) throws SQLException {
/*     */               try {
/* 369 */                 method.invoke(object, new Object[] { resultSet.getString(this.val$name) });
/* 370 */               } catch (IllegalAccessException|InvocationTargetException error) {
/* 371 */                 QueryBuilder.LOGGER.warn("Set property error", error);
/*     */               } 
/*     */             }
/*     */           });
/* 375 */     } else if (parameterType.equals(Date.class)) {
/* 376 */       processors.add(new ResultSetProcessor<T>()
/*     */           {
/*     */             public void process(T object, ResultSet resultSet) throws SQLException {
/*     */               try {
/* 380 */                 Timestamp timestamp = resultSet.getTimestamp(name);
/* 381 */                 if (timestamp != null) {
/* 382 */                   method.invoke(object, new Object[] { new Date(timestamp.getTime()) });
/*     */                 }
/* 384 */               } catch (IllegalAccessException|InvocationTargetException error) {
/* 385 */                 QueryBuilder.LOGGER.warn("Set property error", error);
/*     */               } 
/*     */             }
/*     */           });
/* 389 */     } else if (parameterType.equals(byte[].class)) {
/* 390 */       processors.add(new ResultSetProcessor<T>()
/*     */           {
/*     */             public void process(T object, ResultSet resultSet) throws SQLException {
/*     */               try {
/* 394 */                 method.invoke(object, new Object[] { resultSet.getBytes(this.val$name) });
/* 395 */               } catch (IllegalAccessException|InvocationTargetException error) {
/* 396 */                 QueryBuilder.LOGGER.warn("Set property error", error);
/*     */               } 
/*     */             }
/*     */           });
/*     */     } else {
/* 401 */       processors.add(new ResultSetProcessor<T>()
/*     */           {
/*     */             public void process(T object, ResultSet resultSet) throws SQLException {
/* 404 */               String value = resultSet.getString(name);
/* 405 */               if (value != null && !value.isEmpty()) {
/*     */                 try {
/* 407 */                   method.invoke(object, new Object[] { Context.getObjectMapper().readValue(value, this.val$parameterType) });
/* 408 */                 } catch (InvocationTargetException|IllegalAccessException|java.io.IOException error) {
/* 409 */                   QueryBuilder.LOGGER.warn("Set property error", error);
/*     */                 } 
/*     */               }
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */   
/*     */   public <T> Collection<T> executeQuery(Class<T> clazz) throws SQLException {
/* 418 */     List<T> result = new LinkedList<>();
/*     */     
/* 420 */     if (this.query != null)
/*     */     {
/*     */ 
/*     */       
/* 424 */       try (ResultSet resultSet = this.statement.executeQuery()) {
/*     */         
/* 426 */         ResultSetMetaData resultMetaData = resultSet.getMetaData();
/*     */         
/* 428 */         List<ResultSetProcessor<T>> processors = new LinkedList<>();
/*     */         
/* 430 */         Method[] methods = clazz.getMethods();
/*     */         
/* 432 */         for (Method method : methods) {
/* 433 */           if (method.getName().startsWith("set") && (method.getParameterTypes()).length == 1 && 
/* 434 */             !method.isAnnotationPresent((Class)QueryIgnore.class)) {
/*     */             
/* 436 */             String name = method.getName().substring(3);
/*     */ 
/*     */             
/* 439 */             boolean column = false;
/* 440 */             for (int i = 1; i <= resultMetaData.getColumnCount(); i++) {
/* 441 */               if (name.equalsIgnoreCase(resultMetaData.getColumnLabel(i))) {
/* 442 */                 column = true;
/*     */                 break;
/*     */               } 
/*     */             } 
/* 446 */             if (column)
/*     */             {
/*     */ 
/*     */               
/* 450 */               addProcessors(processors, method.getParameterTypes()[0], method, name);
/*     */             }
/*     */           } 
/*     */         } 
/* 454 */         while (resultSet.next()) {
/*     */           try {
/* 456 */             T object = clazz.newInstance();
/* 457 */             for (ResultSetProcessor<T> processor : processors) {
/* 458 */               processor.process(object, resultSet);
/*     */             }
/* 460 */             result.add(object);
/* 461 */           } catch (InstantiationException|IllegalAccessException e) {
/* 462 */             throw new IllegalArgumentException();
/*     */           }
/*     */         
/*     */         } 
/*     */       } finally {
/*     */         
/* 468 */         this.statement.close();
/* 469 */         this.connection.close();
/*     */       } 
/*     */     }
/*     */     
/* 473 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public long executeUpdate() throws SQLException {
/* 478 */     if (this.query != null) {
/*     */       try {
/* 480 */         this.statement.execute();
/* 481 */         if (this.returnGeneratedKeys) {
/* 482 */           ResultSet resultSet = this.statement.getGeneratedKeys();
/* 483 */           if (resultSet.next()) {
/* 484 */             return resultSet.getLong(1);
/*     */           }
/*     */         } 
/*     */       } finally {
/* 488 */         this.statement.close();
/* 489 */         this.connection.close();
/*     */       } 
/*     */     }
/* 492 */     return 0L;
/*     */   }
/*     */   
/*     */   public Collection<Permission> executePermissionsQuery() throws SQLException, ClassNotFoundException {
/* 496 */     List<Permission> result = new LinkedList<>();
/* 497 */     if (this.query != null)
/*     */     {
/* 499 */       try (ResultSet resultSet = this.statement.executeQuery()) {
/* 500 */         ResultSetMetaData resultMetaData = resultSet.getMetaData();
/* 501 */         while (resultSet.next()) {
/* 502 */           LinkedHashMap<String, Long> map = new LinkedHashMap<>();
/* 503 */           for (int i = 1; i <= resultMetaData.getColumnCount(); i++) {
/* 504 */             String label = resultMetaData.getColumnLabel(i);
/* 505 */             map.put(label, Long.valueOf(resultSet.getLong(label)));
/*     */           } 
/* 507 */           result.add(new Permission(map));
/*     */         } 
/*     */       } finally {
/*     */         
/* 511 */         this.statement.close();
/* 512 */         this.connection.close();
/*     */       } 
/*     */     }
/*     */     
/* 516 */     return result;
/*     */   }
/*     */   
/*     */   private static interface ResultSetProcessor<T> {
/*     */     void process(T param1T, ResultSet param1ResultSet) throws SQLException;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\database\QueryBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */