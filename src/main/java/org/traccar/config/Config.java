/*     */ package org.traccar.config;
/*     */ 
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.InvalidPropertiesFormatException;
/*     */ import java.util.Properties;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Config
/*     */ {
/*  26 */   private final Properties properties = new Properties();
/*     */   
/*     */   private boolean useEnvironmentVariables;
/*     */ 
/*     */   
/*     */   public Config() {}
/*     */   
/*     */   public Config(String file) throws IOException {
/*     */     try {
/*  35 */       Properties mainProperties = new Properties();
/*  36 */       try (InputStream inputStream = new FileInputStream(file)) {
/*  37 */         mainProperties.loadFromXML(inputStream);
/*     */       } 
/*     */       
/*  40 */       String defaultConfigFile = mainProperties.getProperty("config.default");
/*  41 */       if (defaultConfigFile != null) {
/*  42 */         try (InputStream inputStream = new FileInputStream(defaultConfigFile)) {
/*  43 */

                this.properties.loadFromXML(inputStream);
/*     */         } 
/*     */       }
/*     */       
/*  47 */       this.properties.putAll(mainProperties);
/*     */       
/*  49 */       this
/*  50 */         .useEnvironmentVariables = (Boolean.parseBoolean(System.getenv("CONFIG_USE_ENVIRONMENT_VARIABLES")) || Boolean.parseBoolean(this.properties.getProperty("config.useEnvironmentVariables")));
/*  51 */     } catch (InvalidPropertiesFormatException e) {
/*  52 */       throw new RuntimeException("Configuration file is not a valid XML document", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean hasKey(ConfigKey key) {
/*  57 */     return hasKey(key.getKey());
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean hasKey(String key) {
/*  62 */     return ((this.useEnvironmentVariables && System.getenv().containsKey(getEnvironmentVariableName(key))) || this.properties
/*  63 */       .containsKey(key));
/*     */   }
/*     */   
/*     */   public String getString(ConfigKey key) {
/*  67 */     return getString(key.getKey());
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public String getString(String key) {
/*  72 */     if (this.useEnvironmentVariables) {
/*  73 */       String value = System.getenv(getEnvironmentVariableName(key));
/*  74 */       if (value != null && !value.isEmpty()) {
/*  75 */         return value;
/*     */       }
/*     */     } 
/*  78 */     return this.properties.getProperty(key);
/*     */   }
/*     */   
/*     */   public String getString(ConfigKey key, String defaultValue) {
/*  82 */     return getString(key.getKey(), defaultValue);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public String getString(String key, String defaultValue) {
/*  87 */     return hasKey(key) ? getString(key) : defaultValue;
/*     */   }
/*     */   
/*     */   public boolean getBoolean(ConfigKey key) {
/*  91 */     return getBoolean(key.getKey());
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean getBoolean(String key) {
/*  96 */     return Boolean.parseBoolean(getString(key));
/*     */   }
/*     */   
/*     */   public int getInteger(ConfigKey key) {
/* 100 */     return getInteger(key.getKey());
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public int getInteger(String key) {
/* 105 */     return getInteger(key, 0);
/*     */   }
/*     */   
/*     */   public int getInteger(ConfigKey key, int defaultValue) {
/* 109 */     return getInteger(key.getKey(), defaultValue);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public int getInteger(String key, int defaultValue) {
/* 114 */     return hasKey(key) ? Integer.parseInt(getString(key)) : defaultValue;
/*     */   }
/*     */   
/*     */   public long getLong(ConfigKey key) {
/* 118 */     return getLong(key.getKey());
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public long getLong(String key) {
/* 123 */     return getLong(key, 0L);
/*     */   }
/*     */   
/*     */   public long getLong(ConfigKey key, long defaultValue) {
/* 127 */     return getLong(key.getKey(), defaultValue);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public long getLong(String key, long defaultValue) {
/* 132 */     return hasKey(key) ? Long.parseLong(getString(key)) : defaultValue;
/*     */   }
/*     */   
/*     */   public double getDouble(ConfigKey key) {
/* 136 */     return getDouble(key.getKey());
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getDouble(String key) {
/* 141 */     return getDouble(key, 0.0D);
/*     */   }
/*     */   
/*     */   public double getDouble(ConfigKey key, double defaultValue) {
/* 145 */     return getDouble(key.getKey(), defaultValue);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getDouble(String key, double defaultValue) {
/* 150 */     return hasKey(key) ? Double.parseDouble(getString(key)) : defaultValue;
/*     */   }
/*     */   
/*     */   public void setString(ConfigKey key, String value) {
/* 154 */     setString(key.getKey(), value);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setString(String key, String value) {
/* 159 */     this.properties.put(key, value);
/*     */   }
/*     */   
/*     */   static String getEnvironmentVariableName(String key) {
/* 163 */     return key.replaceAll("\\.", "_").replaceAll("(\\p{Lu})", "_$1").toUpperCase();
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\config\Config.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */