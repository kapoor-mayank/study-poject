/*     */ package org.traccar.web;
/*     */ 
/*     */ import com.fasterxml.jackson.core.JsonProcessingException;
/*     */ import java.beans.Introspector;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.Date;
/*     */ import java.util.Map;
/*     */ import java.util.SortedSet;
/*     */ import java.util.TreeSet;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.traccar.Context;
/*     */ import org.traccar.helper.DateUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CsvBuilder
/*     */ {
/*  38 */   private static final Logger LOGGER = LoggerFactory.getLogger(CsvBuilder.class);
/*     */   
/*     */   private static final String LINE_ENDING = "\r\n";
/*     */   
/*     */   private static final String SEPARATOR = ";";
/*  43 */   private StringBuilder builder = new StringBuilder();
/*     */   
/*     */   private void addLineEnding() {
/*  46 */     this.builder.append("\r\n");
/*     */   }
/*     */   private void addSeparator() {
/*  49 */     this.builder.append(";");
/*     */   }
/*     */   
/*     */   private SortedSet<Method> getSortedMethods(Object object) {
/*  53 */     Method[] methodArray = object.getClass().getMethods();
/*  54 */     SortedSet<Method> methods = new TreeSet<>(new Comparator<Method>()
/*     */         {
/*     */           public int compare(Method m1, Method m2) {
/*  57 */             if (m1.getName().equals("getAttributes") && !m1.getName().equals(m2.getName())) {
/*  58 */               return 1;
/*     */             }
/*  60 */             if (m2.getName().equals("getAttributes") && !m1.getName().equals(m2.getName())) {
/*  61 */               return -1;
/*     */             }
/*  63 */             return m1.getName().compareTo(m2.getName());
/*     */           }
/*     */         });
/*  66 */     methods.addAll(Arrays.asList(methodArray));
/*  67 */     return methods;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addLine(Object object) {
/*  72 */     SortedSet<Method> methods = getSortedMethods(object);
/*     */     
/*  74 */     for (Method method : methods) {
/*  75 */       if (method.getName().startsWith("get") && (method.getParameterTypes()).length == 0) {
/*     */         try {
/*  77 */           if (method.getReturnType().equals(boolean.class)) {
/*  78 */             this.builder.append(method.invoke(object, new Object[0]));
/*  79 */             addSeparator(); continue;
/*  80 */           }  if (method.getReturnType().equals(int.class)) {
/*  81 */             this.builder.append(method.invoke(object, new Object[0]));
/*  82 */             addSeparator(); continue;
/*  83 */           }  if (method.getReturnType().equals(long.class)) {
/*  84 */             this.builder.append(method.invoke(object, new Object[0]));
/*  85 */             addSeparator(); continue;
/*  86 */           }  if (method.getReturnType().equals(double.class)) {
/*  87 */             this.builder.append(method.invoke(object, new Object[0]));
/*  88 */             addSeparator(); continue;
/*  89 */           }  if (method.getReturnType().equals(String.class)) {
/*  90 */             this.builder.append((String)method.invoke(object, new Object[0]));
/*  91 */             addSeparator(); continue;
/*  92 */           }  if (method.getReturnType().equals(Date.class)) {
/*  93 */             Date value = (Date)method.invoke(object, new Object[0]);
/*  94 */             this.builder.append(DateUtil.formatDate(value));
/*  95 */             addSeparator(); continue;
/*  96 */           }  if (method.getReturnType().equals(Map.class)) {
/*  97 */             Map value = (Map)method.invoke(object, new Object[0]);
/*  98 */             if (value != null) {
/*     */               try {
/* 100 */                 String map = Context.getObjectMapper().writeValueAsString(value);
/* 101 */                 map = map.replaceAll("[\\{\\}\"]", "");
/* 102 */                 map = map.replaceAll(",", " ");
/* 103 */                 this.builder.append(map);
/* 104 */                 addSeparator();
/* 105 */               } catch (JsonProcessingException e) {
/* 106 */                 LOGGER.warn("Map JSON formatting error", (Throwable)e);
/*     */               } 
/*     */             }
/*     */           } 
/* 110 */         } catch (IllegalAccessException|java.lang.reflect.InvocationTargetException error) {
/* 111 */           LOGGER.warn("Reflection invocation error", error);
/*     */         } 
/*     */       }
/*     */     } 
/* 115 */     addLineEnding();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addHeaderLine(Object object) {
/* 120 */     SortedSet<Method> methods = getSortedMethods(object);
/*     */     
/* 122 */     for (Method method : methods) {
/* 123 */       if (method.getName().startsWith("get") && (method.getParameterTypes()).length == 0) {
/* 124 */         String name = Introspector.decapitalize(method.getName().substring(3));
/* 125 */         if (!name.equals("class")) {
/* 126 */           this.builder.append(name);
/* 127 */           addSeparator();
/*     */         } 
/*     */       } 
/*     */     } 
/* 131 */     addLineEnding();
/*     */   }
/*     */   
/*     */   public void addArray(Collection<?> array) {
/* 135 */     for (Object object : array) {
/* 136 */       switch (object.getClass().getSimpleName().toLowerCase()) {
/*     */         case "string":
/* 138 */           this.builder.append(object.toString());
/* 139 */           addLineEnding();
/*     */           continue;
/*     */         case "long":
/* 142 */           this.builder.append(((Long)object).longValue());
/* 143 */           addLineEnding();
/*     */           continue;
/*     */         case "double":
/* 146 */           this.builder.append(((Double)object).doubleValue());
/* 147 */           addLineEnding();
/*     */           continue;
/*     */         case "boolean":
/* 150 */           this.builder.append(((Boolean)object).booleanValue());
/* 151 */           addLineEnding();
/*     */           continue;
/*     */       } 
/* 154 */       addLine(object);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String build() {
/* 161 */     return this.builder.toString();
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\web\CsvBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */