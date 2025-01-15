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
/*    */ public class GisgraphyGeocoder
/*    */   extends JsonGeocoder
/*    */ {
/*    */   public GisgraphyGeocoder(AddressFormat addressFormat) {
/* 23 */     this("http://services.gisgraphy.com/reversegeocoding/search", 0, addressFormat);
/*    */   }
/*    */   
/*    */   public GisgraphyGeocoder(String url, int cacheSize, AddressFormat addressFormat) {
/* 27 */     super(url + "?format=json&lat=%f&lng=%f&from=1&to=1", cacheSize, addressFormat);
/*    */   }
/*    */ 
/*    */   
/*    */   public Address parseAddress(JsonObject json) {
/* 32 */     Address address = new Address();
/*    */     
/* 34 */     JsonObject result = json.getJsonArray("result").getJsonObject(0);
/*    */     
/* 36 */     if (result.containsKey("streetName")) {
/* 37 */       address.setStreet(result.getString("streetName"));
/*    */     }
/* 39 */     if (result.containsKey("city")) {
/* 40 */       address.setSettlement(result.getString("city"));
/*    */     }
/* 42 */     if (result.containsKey("state")) {
/* 43 */       address.setState(result.getString("state"));
/*    */     }
/* 45 */     if (result.containsKey("countryCode")) {
/* 46 */       address.setCountry(result.getString("countryCode"));
/*    */     }
/* 48 */     if (result.containsKey("formatedFull")) {
/* 49 */       address.setFormattedAddress(result.getString("formatedFull"));
/*    */     }
/*    */     
/* 52 */     return address;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\geocoder\GisgraphyGeocoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */