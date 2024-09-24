/*    */ package org.traccar.reports.model;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
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
/*    */ public class DeviceReport
/*    */ {
/*    */   private String deviceName;
/*    */   
/*    */   public String getDeviceName() {
/* 28 */     return this.deviceName;
/*    */   }
/*    */   
/*    */   public void setDeviceName(String deviceName) {
/* 32 */     this.deviceName = deviceName;
/*    */   }
/*    */   
/* 35 */   private String groupName = "";
/*    */   
/*    */   public String getGroupName() {
/* 38 */     return this.groupName;
/*    */   }
/*    */   private List<?> objects;
/*    */   public void setGroupName(String groupName) {
/* 42 */     this.groupName = groupName;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Collection<?> getObjects() {
/* 48 */     return this.objects;
/*    */   }
/*    */   
/*    */   public void setObjects(Collection<?> objects) {
/* 52 */     this.objects = new ArrayList(objects);
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\reports\model\DeviceReport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */