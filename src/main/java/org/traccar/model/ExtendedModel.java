/*     */ package org.traccar.model;
/*     */ 
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExtendedModel
/*     */   extends BaseModel
/*     */ {
/*  23 */   private Map<String, Object> attributes = new LinkedHashMap<>();
/*     */   
/*     */   public Map<String, Object> getAttributes() {
/*  26 */     return this.attributes;
/*     */   }
/*     */   
/*     */   public void setAttributes(Map<String, Object> attributes) {
/*  30 */     this.attributes = attributes;
/*     */   }
/*     */   
/*     */   public void set(String key, Boolean value) {
/*  34 */     if (value != null) {
/*  35 */       this.attributes.put(key, value);
/*     */     }
/*     */   }
/*     */   
/*     */   public void set(String key, Byte value) {
/*  40 */     if (value != null) {
/*  41 */       this.attributes.put(key, Integer.valueOf(value.intValue()));
/*     */     }
/*     */   }
/*     */   
/*     */   public void set(String key, Short value) {
/*  46 */     if (value != null) {
/*  47 */       this.attributes.put(key, Integer.valueOf(value.intValue()));
/*     */     }
/*     */   }
/*     */   
/*     */   public void set(String key, Integer value) {
/*  52 */     if (value != null) {
/*  53 */       this.attributes.put(key, value);
/*     */     }
/*     */   }
/*     */   
/*     */   public void set(String key, Long value) {
/*  58 */     if (value != null) {
/*  59 */       this.attributes.put(key, value);
/*     */     }
/*     */   }
/*     */   
/*     */   public void set(String key, Float value) {
/*  64 */     if (value != null) {
/*  65 */       this.attributes.put(key, Double.valueOf(value.doubleValue()));
/*     */     }
/*     */   }
/*     */   
/*     */   public void set(String key, Double value) {
/*  70 */     if (value != null) {
/*  71 */       this.attributes.put(key, value);
/*     */     }
/*     */   }
/*     */   
/*     */   public void set(String key, String value) {
/*  76 */     if (value != null && !value.isEmpty()) {
/*  77 */       this.attributes.put(key, value);
/*     */     }
/*     */   }
/*     */   
/*     */   public void add(Map.Entry<String, Object> entry) {
/*  82 */     if (entry != null && entry.getValue() != null) {
/*  83 */       this.attributes.put(entry.getKey(), entry.getValue());
/*     */     }
/*     */   }
/*     */   
/*     */   public String getString(String key) {
/*  88 */     if (this.attributes.containsKey(key)) {
/*  89 */       return (String)this.attributes.get(key);
/*     */     }
/*  91 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDouble(String key) {
/*  96 */     if (this.attributes.containsKey(key)) {
/*  97 */       return ((Number)this.attributes.get(key)).doubleValue();
/*     */     }
/*  99 */     return 0.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getBoolean(String key) {
/* 104 */     if (this.attributes.containsKey(key)) {
/* 105 */       return ((Boolean)this.attributes.get(key)).booleanValue();
/*     */     }
/* 107 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInteger(String key) {
/* 112 */     if (this.attributes.containsKey(key)) {
/* 113 */       return ((Number)this.attributes.get(key)).intValue();
/*     */     }
/* 115 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLong(String key) {
/* 120 */     if (this.attributes.containsKey(key)) {
/* 121 */       return ((Number)this.attributes.get(key)).longValue();
/*     */     }
/* 123 */     return 0L;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\model\ExtendedModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */