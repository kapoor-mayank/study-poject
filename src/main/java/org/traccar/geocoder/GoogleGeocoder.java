/*    */ package org.traccar.geocoder;
/*    */ 
/*    */ import javax.json.JsonArray;
/*    */ import javax.json.JsonObject;
/*    */ import javax.json.JsonString;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GoogleGeocoder
/*    */   extends JsonGeocoder
/*    */ {
/*    */   private static String formatUrl(String key, String language) {
/* 25 */     String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=%f,%f";
/* 26 */     if (key != null) {
/* 27 */       url = url + "&key=" + key;
/*    */     }
/* 29 */     if (language != null) {
/* 30 */       url = url + "&language=" + language;
/*    */     }
/* 32 */     return url;
/*    */   }
/*    */   
/*    */   public GoogleGeocoder(String key, String language, int cacheSize, AddressFormat addressFormat) {
/* 36 */     super(formatUrl(key, language), cacheSize, addressFormat);
/*    */   }
/*    */ 
/*    */   
/*    */   public Address parseAddress(JsonObject json) {
/* 41 */     JsonArray results = json.getJsonArray("results");
/*    */     
/* 43 */     if (!results.isEmpty()) {
/* 44 */       Address address = new Address();
/*    */       
/* 46 */       JsonObject result = (JsonObject)results.get(0);
/* 47 */       JsonArray components = result.getJsonArray("address_components");
/*    */       
/* 49 */       if (result.containsKey("formatted_address")) {
/* 50 */         address.setFormattedAddress(result.getString("formatted_address"));
/*    */       }
/*    */       
/* 53 */       for (JsonObject component : components.getValuesAs(JsonObject.class)) {
/*    */         
/* 55 */         String value = component.getString("short_name");
/*    */         
/* 57 */         for (JsonString type : component.getJsonArray("types").getValuesAs(JsonString.class)) {
/*    */           
/* 59 */           switch (type.getString()) {
/*    */             case "street_number":
/* 61 */               address.setHouse(value);
/*    */               break;
/*    */             case "route":
/* 64 */               address.setStreet(value);
/*    */               break;
/*    */             case "locality":
/* 67 */               address.setSettlement(value);
/*    */               break;
/*    */             case "administrative_area_level_2":
/* 70 */               address.setDistrict(value);
/*    */               break;
/*    */             case "administrative_area_level_1":
/* 73 */               address.setState(value);
/*    */               break;
/*    */             case "country":
/* 76 */               address.setCountry(value);
/*    */               break;
/*    */             case "postal_code":
/* 79 */               address.setPostcode(value);
/*    */           } 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/*    */         } 
/*    */       } 
/* 87 */       return address;
/*    */     } 
/*    */     
/* 90 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   protected String parseError(JsonObject json) {
/* 95 */     return json.getString("error_message");
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\geocoder\GoogleGeocoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */