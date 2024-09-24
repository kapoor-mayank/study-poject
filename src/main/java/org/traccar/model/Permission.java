/*    */ package org.traccar.model;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ import org.traccar.database.DataManager;
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
/*    */ public class Permission
/*    */ {
/*    */   private Class<?> ownerClass;
/*    */   private long ownerId;
/*    */   private Class<?> propertyClass;
/*    */   private long propertyId;
/*    */   
/*    */   public Permission(LinkedHashMap<String, Long> permissionMap) throws ClassNotFoundException {
/* 33 */     Iterator<Map.Entry<String, Long>> iterator = permissionMap.entrySet().iterator();
/* 34 */     String owner = (String)((Map.Entry)iterator.next()).getKey();
/* 35 */     this.ownerClass = DataManager.getClassByName(owner);
/* 36 */     String property = (String)((Map.Entry)iterator.next()).getKey();
/* 37 */     this.propertyClass = DataManager.getClassByName(property);
/* 38 */     this.ownerId = ((Long)permissionMap.get(owner)).longValue();
/* 39 */     this.propertyId = ((Long)permissionMap.get(property)).longValue();
/*    */   }
/*    */   
/*    */   public Class<?> getOwnerClass() {
/* 43 */     return this.ownerClass;
/*    */   }
/*    */   
/*    */   public long getOwnerId() {
/* 47 */     return this.ownerId;
/*    */   }
/*    */   
/*    */   public Class<?> getPropertyClass() {
/* 51 */     return this.propertyClass;
/*    */   }
/*    */   
/*    */   public long getPropertyId() {
/* 55 */     return this.propertyId;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\model\Permission.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */