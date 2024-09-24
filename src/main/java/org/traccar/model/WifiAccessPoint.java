/*    */ package org.traccar.model;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonInclude;
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
/*    */ @JsonInclude(JsonInclude.Include.NON_NULL)
/*    */ public class WifiAccessPoint
/*    */ {
/*    */   private String macAddress;
/*    */   private Integer signalStrength;
/*    */   private Integer channel;
/*    */   
/*    */   public static WifiAccessPoint from(String macAddress, int signalStrength) {
/* 24 */     WifiAccessPoint wifiAccessPoint = new WifiAccessPoint();
/* 25 */     wifiAccessPoint.setMacAddress(macAddress);
/* 26 */     wifiAccessPoint.setSignalStrength(Integer.valueOf(signalStrength));
/* 27 */     return wifiAccessPoint;
/*    */   }
/*    */   
/*    */   public static WifiAccessPoint from(String macAddress, int signalStrength, int channel) {
/* 31 */     WifiAccessPoint wifiAccessPoint = from(macAddress, signalStrength);
/* 32 */     wifiAccessPoint.setChannel(Integer.valueOf(channel));
/* 33 */     return wifiAccessPoint;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getMacAddress() {
/* 39 */     return this.macAddress;
/*    */   }
/*    */   
/*    */   public void setMacAddress(String macAddress) {
/* 43 */     this.macAddress = macAddress;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Integer getSignalStrength() {
/* 49 */     return this.signalStrength;
/*    */   }
/*    */   
/*    */   public void setSignalStrength(Integer signalStrength) {
/* 53 */     this.signalStrength = signalStrength;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Integer getChannel() {
/* 59 */     return this.channel;
/*    */   }
/*    */   
/*    */   public void setChannel(Integer channel) {
/* 63 */     this.channel = channel;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\model\WifiAccessPoint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */