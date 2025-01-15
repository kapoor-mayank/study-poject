/*    */ package org.traccar.database;
/*    */ 
/*    */ import java.util.Map;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ import org.traccar.model.BaseModel;
/*    */ import org.traccar.model.Driver;
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
/*    */ public class DriversManager
/*    */   extends ExtendedObjectManager<Driver>
/*    */ {
/*    */   private Map<String, Driver> driversByUniqueId;
/*    */   
/*    */   public DriversManager(DataManager dataManager) {
/* 29 */     super(dataManager, Driver.class);
/* 30 */     if (this.driversByUniqueId == null) {
/* 31 */       this.driversByUniqueId = new ConcurrentHashMap<>();
/*    */     }
/*    */   }
/*    */   
/*    */   private void putUniqueDriverId(Driver driver) {
/* 36 */     if (this.driversByUniqueId == null) {
/* 37 */       this.driversByUniqueId = new ConcurrentHashMap<>(getAllItems().size());
/*    */     }
/* 39 */     this.driversByUniqueId.put(driver.getUniqueId(), driver);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void addNewItem(Driver driver) {
/* 44 */     super.addNewItem(driver);
/* 45 */     putUniqueDriverId(driver);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void updateCachedItem(Driver driver) {
/* 50 */     Driver cachedDriver = getById(driver.getId());
/* 51 */     cachedDriver.setName(driver.getName());
/* 52 */     if (!driver.getUniqueId().equals(cachedDriver.getUniqueId())) {
/* 53 */       this.driversByUniqueId.remove(cachedDriver.getUniqueId());
/* 54 */       cachedDriver.setUniqueId(driver.getUniqueId());
/* 55 */       putUniqueDriverId(cachedDriver);
/*    */     } 
/* 57 */     cachedDriver.setAttributes(driver.getAttributes());
/*    */   }
/*    */ 
/*    */   
/*    */   protected void removeCachedItem(long driverId) {
/* 62 */     Driver cachedDriver = getById(driverId);
/* 63 */     if (cachedDriver != null) {
/* 64 */       String driverUniqueId = cachedDriver.getUniqueId();
/* 65 */       super.removeCachedItem(driverId);
/* 66 */       this.driversByUniqueId.remove(driverUniqueId);
/*    */     } 
/*    */   }
/*    */   
/*    */   public Driver getDriverByUniqueId(String uniqueId) {
/* 71 */     return this.driversByUniqueId.get(uniqueId);
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\database\DriversManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */