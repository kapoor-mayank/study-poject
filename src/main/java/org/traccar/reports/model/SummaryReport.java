/*    */ package org.traccar.reports.model;
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
/*    */ public class SummaryReport
/*    */   extends BaseReport
/*    */ {
/*    */   private long engineHours;
/*    */   
/*    */   public long getEngineHours() {
/* 24 */     return this.engineHours;
/*    */   }
/*    */   
/*    */   public void setEngineHours(long engineHours) {
/* 28 */     this.engineHours = engineHours;
/*    */   }
/*    */   
/*    */   public void addEngineHours(long engineHours) {
/* 32 */     this.engineHours += engineHours;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\reports\model\SummaryReport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */