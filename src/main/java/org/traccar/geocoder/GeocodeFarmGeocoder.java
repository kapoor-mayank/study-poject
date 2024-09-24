/*    */ package org.traccar.geocoder;
/*    */ 
/*    */ import javax.json.JsonObject;
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
/*    */ public class GeocodeFarmGeocoder
/*    */   extends JsonGeocoder
/*    */ {
/*    */   private static String formatUrl(String key, String language) {
/* 23 */     String url = "https://www.geocode.farm/v3/json/reverse/";
/* 24 */     url = url + "?lat=%f&lon=%f&country=us&count=1";
/* 25 */     if (key != null) {
/* 26 */       url = url + "&key=" + key;
/*    */     }
/* 28 */     if (language != null) {
/* 29 */       url = url + "&lang=" + language;
/*    */     }
/* 31 */     return url;
/*    */   }
/*    */   public GeocodeFarmGeocoder(String key, String language, int cacheSize, AddressFormat addressFormat) {
/* 34 */     super(formatUrl(key, language), cacheSize, addressFormat);
/*    */   }
/*    */ 
/*    */   
/*    */   public Address parseAddress(JsonObject json) {
/* 39 */     Address address = new Address();
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 44 */     JsonObject result = json.getJsonObject("geocoding_results").getJsonArray("RESULTS").getJsonObject(0);
/*    */     
/* 46 */     JsonObject resultAddress = result.getJsonObject("ADDRESS");
/*    */     
/* 48 */     if (result.containsKey("formatted_address")) {
/* 49 */       address.setFormattedAddress(result.getString("formatted_address"));
/*    */     }
/* 51 */     if (resultAddress.containsKey("street_number")) {
/* 52 */       address.setStreet(resultAddress.getString("street_number"));
/*    */     }
/* 54 */     if (resultAddress.containsKey("street_name")) {
/* 55 */       address.setStreet(resultAddress.getString("street_name"));
/*    */     }
/* 57 */     if (resultAddress.containsKey("locality")) {
/* 58 */       address.setSettlement(resultAddress.getString("locality"));
/*    */     }
/* 60 */     if (resultAddress.containsKey("admin_1")) {
/* 61 */       address.setState(resultAddress.getString("admin_1"));
/*    */     }
/* 63 */     if (resultAddress.containsKey("country")) {
/* 64 */       address.setCountry(resultAddress.getString("country"));
/*    */     }
/*    */     
/* 67 */     return address;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\geocoder\GeocodeFarmGeocoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */