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
/*    */ public class NominatimGeocoder
/*    */   extends JsonGeocoder
/*    */ {
/*    */   private static String formatUrl(String url, String key, String language) {
/* 23 */     if (url == null) {
/* 24 */       url = "https://nominatim.openstreetmap.org/reverse";
/*    */     }
/* 26 */     url = url + "?format=json&lat=%f&lon=%f&zoom=18&addressdetails=1";
/* 27 */     if (key != null) {
/* 28 */       url = url + "&key=" + key;
/*    */     }
/* 30 */     if (language != null) {
/* 31 */       url = url + "&accept-language=" + language;
/*    */     }
/* 33 */     return url;
/*    */   }
/*    */   
/*    */   public NominatimGeocoder(String url, String key, String language, int cacheSize, AddressFormat addressFormat) {
/* 37 */     super(formatUrl(url, key, language), cacheSize, addressFormat);
/*    */   }
/*    */ 
/*    */   
/*    */   public Address parseAddress(JsonObject json) {
/* 42 */     JsonObject result = json.getJsonObject("address");
/*    */     
/* 44 */     if (result != null) {
/* 45 */       Address address = new Address();
/*    */       
/* 47 */       if (json.containsKey("display_name")) {
/* 48 */         address.setFormattedAddress(json.getString("display_name"));
/*    */       }
/*    */       
/* 51 */       if (result.containsKey("house_number")) {
/* 52 */         address.setHouse(result.getString("house_number"));
/*    */       }
/* 54 */       if (result.containsKey("road")) {
/* 55 */         address.setStreet(result.getString("road"));
/*    */       }
/* 57 */       if (result.containsKey("suburb")) {
/* 58 */         address.setSuburb(result.getString("suburb"));
/*    */       }
/*    */       
/* 61 */       if (result.containsKey("village")) {
/* 62 */         address.setSettlement(result.getString("village"));
/* 63 */       } else if (result.containsKey("town")) {
/* 64 */         address.setSettlement(result.getString("town"));
/* 65 */       } else if (result.containsKey("city")) {
/* 66 */         address.setSettlement(result.getString("city"));
/*    */       } 
/*    */       
/* 69 */       if (result.containsKey("state_district")) {
/* 70 */         address.setDistrict(result.getString("state_district"));
/* 71 */       } else if (result.containsKey("region")) {
/* 72 */         address.setDistrict(result.getString("region"));
/*    */       } 
/*    */       
/* 75 */       if (result.containsKey("state")) {
/* 76 */         address.setState(result.getString("state"));
/*    */       }
/* 78 */       if (result.containsKey("country_code")) {
/* 79 */         address.setCountry(result.getString("country_code").toUpperCase());
/*    */       }
/* 81 */       if (result.containsKey("postcode")) {
/* 82 */         address.setPostcode(result.getString("postcode"));
/*    */       }
/*    */       
/* 85 */       return address;
/*    */     } 
/*    */     
/* 88 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\geocoder\NominatimGeocoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */