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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BanGeocoder
/*    */   extends JsonGeocoder
/*    */ {
/*    */   public BanGeocoder(int cacheSize, AddressFormat addressFormat) {
/* 29 */     super("https://api-adresse.data.gouv.fr/reverse/?lat=%f&lon=%f", cacheSize, addressFormat);
/*    */   }
/*    */ 
/*    */   
/*    */   public Address parseAddress(JsonObject json) {
/* 34 */     JsonArray result = json.getJsonArray("features");
/*    */     
/* 36 */     if (result != null && !result.isEmpty()) {
/* 37 */       JsonObject location = result.getJsonObject(0).getJsonObject("properties");
/* 38 */       Address address = new Address();
/*    */       
/* 40 */       address.setCountry("FR");
/* 41 */       if (location.containsKey("postcode")) {
/* 42 */         address.setPostcode(location.getString("postcode"));
/*    */       }
/* 44 */       if (location.containsKey("context")) {
/* 45 */         address.setDistrict(location.getString("context"));
/*    */       }
/* 47 */       if (location.containsKey("name")) {
/* 48 */         address.setStreet(location.getString("name"));
/*    */       }
/* 50 */       if (location.containsKey("city")) {
/* 51 */         address.setSettlement(location.getString("city"));
/*    */       }
/* 53 */       if (location.containsKey("housenumber")) {
/* 54 */         address.setHouse(location.getString("housenumber"));
/*    */       }
/* 56 */       if (location.containsKey("label")) {
/* 57 */         address.setFormattedAddress(location.getString("label"));
/*    */       }
/*    */       
/* 60 */       return address;
/*    */     } 
/*    */     
/* 63 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\geocoder\BanGeocoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */