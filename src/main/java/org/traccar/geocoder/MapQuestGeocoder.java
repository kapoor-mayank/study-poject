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
/*    */ public class MapQuestGeocoder
/*    */   extends JsonGeocoder
/*    */ {
/*    */   public MapQuestGeocoder(String url, String key, int cacheSize, AddressFormat addressFormat) {
/* 25 */     super(url + "?key=" + key + "&location=%f,%f", cacheSize, addressFormat);
/*    */   }
/*    */ 
/*    */   
/*    */   public Address parseAddress(JsonObject json) {
/* 30 */     JsonArray result = json.getJsonArray("results");
/* 31 */     if (result != null) {
/* 32 */       JsonArray locations = result.getJsonObject(0).getJsonArray("locations");
/* 33 */       if (locations != null) {
/* 34 */         JsonObject location = locations.getJsonObject(0);
/*    */         
/* 36 */         Address address = new Address();
/*    */         
/* 38 */         if (location.containsKey("street")) {
/* 39 */           address.setStreet(location.getString("street"));
/*    */         }
/* 41 */         if (location.containsKey("adminArea5")) {
/* 42 */           address.setSettlement(location.getString("adminArea5"));
/*    */         }
/* 44 */         if (location.containsKey("adminArea4")) {
/* 45 */           address.setDistrict(location.getString("adminArea4"));
/*    */         }
/* 47 */         if (location.containsKey("adminArea3")) {
/* 48 */           address.setState(location.getString("adminArea3"));
/*    */         }
/* 50 */         if (location.containsKey("adminArea1")) {
/* 51 */           address.setCountry(location.getString("adminArea1").toUpperCase());
/*    */         }
/* 53 */         if (location.containsKey("postalCode")) {
/* 54 */           address.setPostcode(location.getString("postalCode"));
/*    */         }
/*    */         
/* 57 */         return address;
/*    */       } 
/*    */     } 
/* 60 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\geocoder\MapQuestGeocoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */