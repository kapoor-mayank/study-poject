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
/*    */ public class DeviceState
/*    */ {
/*    */   private Boolean motionState;
/*    */   private Position motionPosition;
/*    */   private Boolean overspeedState;
/*    */   private Position overspeedPosition;
/*    */   private long overspeedGeofenceId;
/*    */   
/*    */   public void setMotionState(boolean motionState) {
/* 24 */     this.motionState = Boolean.valueOf(motionState);
/*    */   }
/*    */   
/*    */   public Boolean getMotionState() {
/* 28 */     return this.motionState;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setMotionPosition(Position motionPosition) {
/* 34 */     this.motionPosition = motionPosition;
/*    */   }
/*    */   
/*    */   public Position getMotionPosition() {
/* 38 */     return this.motionPosition;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setOverspeedState(boolean overspeedState) {
/* 44 */     this.overspeedState = Boolean.valueOf(overspeedState);
/*    */   }
/*    */   
/*    */   public Boolean getOverspeedState() {
/* 48 */     return this.overspeedState;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setOverspeedPosition(Position overspeedPosition) {
/* 54 */     this.overspeedPosition = overspeedPosition;
/*    */   }
/*    */   
/*    */   public Position getOverspeedPosition() {
/* 58 */     return this.overspeedPosition;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setOverspeedGeofenceId(long overspeedGeofenceId) {
/* 64 */     this.overspeedGeofenceId = overspeedGeofenceId;
/*    */   }
/*    */   
/*    */   public long getOverspeedGeofenceId() {
/* 68 */     return this.overspeedGeofenceId;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\model\DeviceState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */