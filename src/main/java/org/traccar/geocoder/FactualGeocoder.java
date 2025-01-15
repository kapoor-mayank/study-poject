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
/*    */ 
/*    */ public class FactualGeocoder
/*    */   extends JsonGeocoder
/*    */ {
/*    */   public FactualGeocoder(String url, String key, int cacheSize, AddressFormat addressFormat) {
/* 24 */     super(url + "?latitude=%f&longitude=%f&KEY=" + key, cacheSize, addressFormat);
/*    */   }
/*    */ 
/*    */   
/*    */   public Address parseAddress(JsonObject json) {
/* 29 */     JsonObject result = json.getJsonObject("response").getJsonObject("data");
/* 30 */     if (result != null) {
/* 31 */       Address address = new Address();
/* 32 */       if (result.getJsonObject("street_number") != null) {
/* 33 */         address.setHouse(result.getJsonObject("street_number").getString("name"));
/*    */       }
/* 35 */       if (result.getJsonObject("street_name") != null) {
/* 36 */         address.setStreet(result.getJsonObject("street_name").getString("name"));
/*    */       }
/* 38 */       if (result.getJsonObject("locality") != null) {
/* 39 */         address.setSettlement(result.getJsonObject("locality").getString("name"));
/*    */       }
/* 41 */       if (result.getJsonObject("county") != null) {
/* 42 */         address.setDistrict(result.getJsonObject("county").getString("name"));
/*    */       }
/* 44 */       if (result.getJsonObject("region") != null) {
/* 45 */         address.setState(result.getJsonObject("region").getString("name"));
/*    */       }
/* 47 */       if (result.getJsonObject("country") != null) {
/* 48 */         address.setCountry(result.getJsonObject("country").getString("name"));
/*    */       }
/* 50 */       if (result.getJsonObject("postcode") != null) {
/* 51 */         address.setPostcode(result.getJsonObject("postcode").getString("name"));
/*    */       }
/* 53 */       return address;
/*    */     } 
/* 55 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\geocoder\FactualGeocoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */