/*    */ package org.traccar.geocoder;
/*    */ 
/*    */ import javax.json.JsonArray;
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
/*    */ 
/*    */ public class OpenCageGeocoder
/*    */   extends JsonGeocoder
/*    */ {
/*    */   public OpenCageGeocoder(String url, String key, int cacheSize, AddressFormat addressFormat) {
/* 25 */     super(url + "/json?q=%f,%f&no_annotations=1&key=" + key, cacheSize, addressFormat);
/*    */   }
/*    */ 
/*    */   
/*    */   public Address parseAddress(JsonObject json) {
/* 30 */     JsonArray result = json.getJsonArray("results");
/* 31 */     if (result != null) {
/* 32 */       JsonObject location = result.getJsonObject(0).getJsonObject("components");
/* 33 */       if (location != null) {
/* 34 */         Address address = new Address();
/*    */         
/* 36 */         if (result.getJsonObject(0).containsKey("formatted")) {
/* 37 */           address.setFormattedAddress(result.getJsonObject(0).getString("formatted"));
/*    */         }
/* 39 */         if (location.containsKey("building")) {
/* 40 */           address.setHouse(location.getString("building"));
/*    */         }
/* 42 */         if (location.containsKey("house_number")) {
/* 43 */           address.setHouse(location.getString("house_number"));
/*    */         }
/* 45 */         if (location.containsKey("road")) {
/* 46 */           address.setStreet(location.getString("road"));
/*    */         }
/* 48 */         if (location.containsKey("suburb")) {
/* 49 */           address.setSuburb(location.getString("suburb"));
/*    */         }
/* 51 */         if (location.containsKey("city")) {
/* 52 */           address.setSettlement(location.getString("city"));
/*    */         }
/* 54 */         if (location.containsKey("city_district")) {
/* 55 */           address.setSettlement(location.getString("city_district"));
/*    */         }
/* 57 */         if (location.containsKey("county")) {
/* 58 */           address.setDistrict(location.getString("county"));
/*    */         }
/* 60 */         if (location.containsKey("state")) {
/* 61 */           address.setState(location.getString("state"));
/*    */         }
/* 63 */         if (location.containsKey("country_code")) {
/* 64 */           address.setCountry(location.getString("country_code").toUpperCase());
/*    */         }
/* 66 */         if (location.containsKey("postcode")) {
/* 67 */           address.setPostcode(location.getString("postcode"));
/*    */         }
/*    */         
/* 70 */         return address;
/*    */       } 
/*    */     } 
/* 73 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\geocoder\OpenCageGeocoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */