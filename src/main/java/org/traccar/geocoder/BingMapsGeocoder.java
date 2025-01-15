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
/*    */ public class BingMapsGeocoder
/*    */   extends JsonGeocoder
/*    */ {
/*    */   public BingMapsGeocoder(String url, String key, int cacheSize, AddressFormat addressFormat) {
/* 25 */     super(url + "/Locations/%f,%f?key=" + key + "&include=ciso2", cacheSize, addressFormat);
/*    */   }
/*    */ 
/*    */   
/*    */   public Address parseAddress(JsonObject json) {
/* 30 */     JsonArray result = json.getJsonArray("resourceSets");
/* 31 */     if (result != null) {
/*    */       
/* 33 */       JsonObject location = result.getJsonObject(0).getJsonArray("resources").getJsonObject(0).getJsonObject("address");
/* 34 */       if (location != null) {
/* 35 */         Address address = new Address();
/* 36 */         if (location.containsKey("addressLine")) {
/* 37 */           address.setStreet(location.getString("addressLine"));
/*    */         }
/* 39 */         if (location.containsKey("locality")) {
/* 40 */           address.setSettlement(location.getString("locality"));
/*    */         }
/* 42 */         if (location.containsKey("adminDistrict2")) {
/* 43 */           address.setDistrict(location.getString("adminDistrict2"));
/*    */         }
/* 45 */         if (location.containsKey("adminDistrict")) {
/* 46 */           address.setState(location.getString("adminDistrict"));
/*    */         }
/* 48 */         if (location.containsKey("countryRegionIso2")) {
/* 49 */           address.setCountry(location.getString("countryRegionIso2").toUpperCase());
/*    */         }
/* 51 */         if (location.containsKey("postalCode")) {
/* 52 */           address.setPostcode(location.getString("postalCode"));
/*    */         }
/* 54 */         if (location.containsKey("formattedAddress")) {
/* 55 */           address.setFormattedAddress(location.getString("formattedAddress"));
/*    */         }
/* 57 */         return address;
/*    */       } 
/*    */     } 
/* 60 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\geocoder\BingMapsGeocoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */