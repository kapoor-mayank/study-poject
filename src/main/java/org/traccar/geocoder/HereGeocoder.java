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
/*    */ public class HereGeocoder
/*    */   extends JsonGeocoder
/*    */ {
/*    */   private static String formatUrl(String id, String key, String language) {
/* 23 */     String url = "https://reverse.geocoder.api.here.com/6.2/reversegeocode.json";
/* 24 */     url = url + "?mode=retrieveAddresses&maxresults=1";
/* 25 */     url = url + "&prox=%f,%f,0";
/* 26 */     url = url + "&app_id=" + id;
/* 27 */     url = url + "&app_code=" + key;
/* 28 */     if (language != null) {
/* 29 */       url = url + "&language=" + language;
/*    */     }
/* 31 */     return url;
/*    */   }
/*    */   
/*    */   public HereGeocoder(String id, String key, String language, int cacheSize, AddressFormat addressFormat) {
/* 35 */     super(formatUrl(id, key, language), cacheSize, addressFormat);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Address parseAddress(JsonObject json) {
/* 47 */     JsonObject result = json.getJsonObject("Response").getJsonArray("View").getJsonObject(0).getJsonArray("Result").getJsonObject(0).getJsonObject("Location").getJsonObject("Address");
/*    */     
/* 49 */     if (result != null) {
/* 50 */       Address address = new Address();
/*    */       
/* 52 */       if (json.containsKey("Label")) {
/* 53 */         address.setFormattedAddress(json.getString("Label"));
/*    */       }
/*    */       
/* 56 */       if (result.containsKey("HouseNumber")) {
/* 57 */         address.setHouse(result.getString("HouseNumber"));
/*    */       }
/* 59 */       if (result.containsKey("Street")) {
/* 60 */         address.setStreet(result.getString("Street"));
/*    */       }
/* 62 */       if (result.containsKey("City")) {
/* 63 */         address.setSettlement(result.getString("City"));
/*    */       }
/* 65 */       if (result.containsKey("District")) {
/* 66 */         address.setDistrict(result.getString("District"));
/*    */       }
/* 68 */       if (result.containsKey("State")) {
/* 69 */         address.setState(result.getString("State"));
/*    */       }
/* 71 */       if (result.containsKey("Country")) {
/* 72 */         address.setCountry(result.getString("Country").toUpperCase());
/*    */       }
/* 74 */       if (result.containsKey("PostalCode")) {
/* 75 */         address.setPostcode(result.getString("PostalCode"));
/*    */       }
/*    */       
/* 78 */       return address;
/*    */     } 
/*    */     
/* 81 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\geocoder\HereGeocoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */