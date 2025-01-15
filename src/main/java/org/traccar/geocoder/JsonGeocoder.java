/*     */ package org.traccar.geocoder;
/*     */ 
/*     */ import java.util.AbstractMap;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import javax.json.JsonObject;
/*     */ import javax.ws.rs.ClientErrorException;
/*     */ import javax.ws.rs.client.Invocation;
/*     */ import javax.ws.rs.client.InvocationCallback;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.traccar.Context;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class JsonGeocoder
/*     */   implements Geocoder
/*     */ {
/*  33 */   private static final Logger LOGGER = LoggerFactory.getLogger(JsonGeocoder.class);
/*     */   
/*     */   private final String url;
/*     */   
/*     */   private final AddressFormat addressFormat;
/*     */   private Map<Map.Entry<Double, Double>, String> cache;
/*     */   
/*     */   public JsonGeocoder(String url, final int cacheSize, AddressFormat addressFormat) {
/*  41 */     this.url = url;
/*  42 */     this.addressFormat = addressFormat;
/*  43 */     if (cacheSize > 0) {
/*  44 */       this.cache = Collections.synchronizedMap(new LinkedHashMap<Map.Entry<Double, Double>, String>()
/*     */           {
/*     */             protected boolean removeEldestEntry(Map.Entry eldest) {
/*  47 */               return (size() > cacheSize);
/*     */             }
/*     */           });
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String handleResponse(double latitude, double longitude, JsonObject json, Geocoder.ReverseGeocoderCallback callback) {
/*  56 */     Address address = parseAddress(json);
/*  57 */     if (address != null) {
/*  58 */       String formattedAddress = this.addressFormat.format(address);
/*  59 */       if (this.cache != null) {
/*  60 */         this.cache.put(new AbstractMap.SimpleImmutableEntry<>(Double.valueOf(latitude), Double.valueOf(longitude)), formattedAddress);
/*     */       }
/*  62 */       if (callback != null) {
/*  63 */         callback.onSuccess(formattedAddress);
/*     */       }
/*  65 */       return formattedAddress;
/*     */     } 
/*  67 */     String msg = "Empty address. Error: " + parseError(json);
/*  68 */     if (callback != null) {
/*  69 */       callback.onFailure(new GeocoderException(msg));
/*     */     } else {
/*  71 */       LOGGER.warn(msg);
/*     */     } 
/*     */     
/*  74 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAddress(final double latitude, final double longitude, final Geocoder.ReverseGeocoderCallback callback) {
/*  81 */     if (this.cache != null) {
/*  82 */       String cachedAddress = this.cache.get(new AbstractMap.SimpleImmutableEntry<>(Double.valueOf(latitude), Double.valueOf(longitude)));
/*  83 */       if (cachedAddress != null) {
/*  84 */         if (callback != null) {
/*  85 */           callback.onSuccess(cachedAddress);
/*     */         }
/*  87 */         return cachedAddress;
/*     */       } 
/*     */     } 
/*     */     
/*  91 */     Invocation.Builder request = Context.getClient().target(String.format(this.url, new Object[] { Double.valueOf(latitude), Double.valueOf(longitude) })).request();
/*     */     
/*  93 */     if (callback != null) {
/*  94 */       request.async().get(new InvocationCallback<JsonObject>()
/*     */           {
/*     */             public void completed(JsonObject json) {
/*  97 */               JsonGeocoder.this.handleResponse(latitude, longitude, json, callback);
/*     */             }
/*     */ 
/*     */             
/*     */             public void failed(Throwable throwable) {
/* 102 */               callback.onFailure(throwable);
/*     */             }
/*     */           });
/*     */     } else {
/*     */       try {
/* 107 */         return handleResponse(latitude, longitude, (JsonObject)request.get(JsonObject.class), null);
/* 108 */       } catch (ClientErrorException e) {
/* 109 */         LOGGER.warn("Geocoder network error", (Throwable)e);
/*     */       } 
/*     */     } 
/* 112 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String parseError(JsonObject json) {
/* 118 */     return null;
/*     */   }
/*     */   
/*     */   public abstract Address parseAddress(JsonObject paramJsonObject);
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\geocoder\JsonGeocoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */