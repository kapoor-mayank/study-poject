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
/*    */ public class MapmyIndiaGeocoder
/*    */   extends JsonGeocoder
/*    */ {
/*    */   public MapmyIndiaGeocoder(String url, String key, int cacheSize, AddressFormat addressFormat) {
/* 24 */     super(url + "/" + key + "/rev_geocode?lat=%f&lng=%f", cacheSize, addressFormat);
/*    */   }
/*    */ 
/*    */   
/*    */   public Address parseAddress(JsonObject json) {
/* 29 */     JsonArray results = json.getJsonArray("results");
/*    */     
/* 31 */     if (!results.isEmpty()) {
/* 32 */       Address address = new Address();
/*    */       
/* 34 */       JsonObject result = (JsonObject)results.get(0);
/*    */       
/* 36 */       if (result.containsKey("formatted_address")) {
/* 37 */         address.setFormattedAddress(result.getString("formatted_address"));
/*    */       }
/*    */       
/* 40 */       if (result.containsKey("house_number") && !result.getString("house_number").isEmpty()) {
/* 41 */         address.setHouse(result.getString("house_number"));
/* 42 */       } else if (result.containsKey("house_name") && !result.getString("house_name").isEmpty()) {
/* 43 */         address.setHouse(result.getString("house_name"));
/*    */       } 
/*    */       
/* 46 */       if (result.containsKey("street")) {
/* 47 */         address.setStreet(result.getString("street"));
/*    */       }
/*    */       
/* 50 */       if (result.containsKey("locality") && !result.getString("locality").isEmpty()) {
/* 51 */         address.setSuburb(result.getString("locality"));
/* 52 */       } else if (result.containsKey("sublocality") && !result.getString("sublocality").isEmpty()) {
/* 53 */         address.setSuburb(result.getString("sublocality"));
/* 54 */       } else if (result.containsKey("subsublocality") && !result.getString("subsublocality").isEmpty()) {
/* 55 */         address.setSuburb(result.getString("subsublocality"));
/*    */       } 
/*    */       
/* 58 */       if (result.containsKey("city") && !result.getString("city").isEmpty()) {
/* 59 */         address.setSettlement(result.getString("city"));
/* 60 */       } else if (result.containsKey("village") && !result.getString("village").isEmpty()) {
/* 61 */         address.setSettlement(result.getString("village"));
/*    */       } 
/*    */       
/* 64 */       if (result.containsKey("district")) {
/* 65 */         address.setDistrict(result.getString("district"));
/* 66 */       } else if (result.containsKey("subDistrict")) {
/* 67 */         address.setDistrict(result.getString("subDistrict"));
/*    */       } 
/*    */       
/* 70 */       if (result.containsKey("state")) {
/* 71 */         address.setState(result.getString("state"));
/*    */       }
/*    */       
/* 74 */       if (result.containsKey("pincode")) {
/* 75 */         address.setPostcode(result.getString("pincode"));
/*    */       }
/*    */       
/* 78 */       return address;
/*    */     } 
/* 80 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\geocoder\MapmyIndiaGeocoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */