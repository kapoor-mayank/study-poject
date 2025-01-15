/*    */ package org.traccar.model;
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
/*    */ public class Maintenance
/*    */   extends ExtendedModel
/*    */ {
/*    */   private String name;
/*    */   private String type;
/*    */   private double start;
/*    */   private double period;
/*    */   
/*    */   public String getName() {
/* 24 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 28 */     this.name = name;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getType() {
/* 34 */     return this.type;
/*    */   }
/*    */   
/*    */   public void setType(String type) {
/* 38 */     this.type = type;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public double getStart() {
/* 44 */     return this.start;
/*    */   }
/*    */   
/*    */   public void setStart(double start) {
/* 48 */     this.start = start;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public double getPeriod() {
/* 54 */     return this.period;
/*    */   }
/*    */   
/*    */   public void setPeriod(double period) {
/* 58 */     this.period = period;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\model\Maintenance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */