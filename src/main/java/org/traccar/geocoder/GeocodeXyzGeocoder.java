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
/*    */ public class GeocodeXyzGeocoder
/*    */   extends JsonGeocoder
/*    */ {
/*    */   private static String formatUrl(String key) {
/* 23 */     String url = "https://geocode.xyz/%f,%f?geoit=JSON";
/* 24 */     if (key != null) {
/* 25 */       url = url + "&key=" + key;
/*    */     }
/* 27 */     return url;
/*    */   }
/*    */   
/*    */   public GeocodeXyzGeocoder(String key, int cacheSize, AddressFormat addressFormat) {
/* 31 */     super(formatUrl(key), cacheSize, addressFormat);
/*    */   }
/*    */ 
/*    */   
/*    */   public Address parseAddress(JsonObject json) {
/* 36 */     Address address = new Address();
/*    */     
/* 38 */     if (json.containsKey("stnumber")) {
/* 39 */       address.setHouse(json.getString("stnumber"));
/*    */     }
/* 41 */     if (json.containsKey("staddress")) {
/* 42 */       address.setStreet(json.getString("staddress"));
/*    */     }
/* 44 */     if (json.containsKey("city")) {
/* 45 */       address.setSettlement(json.getString("city"));
/*    */     }
/* 47 */     if (json.containsKey("region")) {
/* 48 */       address.setState(json.getString("region"));
/*    */     }
/* 50 */     if (json.containsKey("prov")) {
/* 51 */       address.setCountry(json.getString("prov"));
/*    */     }
/* 53 */     if (json.containsKey("postal")) {
/* 54 */       address.setPostcode(json.getString("postal"));
/*    */     }
/*    */     
/* 57 */     return address;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\geocoder\GeocodeXyzGeocoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */