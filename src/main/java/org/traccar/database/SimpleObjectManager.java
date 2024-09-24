/*    */ package org.traccar.database;
/*    */ 
/*    */ import java.sql.SQLException;
/*    */ import java.util.HashSet;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.traccar.Context;
/*    */ import org.traccar.model.BaseModel;
/*    */ import org.traccar.model.Permission;
/*    */ import org.traccar.model.User;
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
/*    */ public abstract class SimpleObjectManager<T extends BaseModel>
/*    */   extends BaseObjectManager<T>
/*    */   implements ManagableObjects
/*    */ {
/* 35 */   private static final Logger LOGGER = LoggerFactory.getLogger(SimpleObjectManager.class);
/*    */   
/*    */   private Map<Long, Set<Long>> userItems;
/*    */   
/*    */   protected SimpleObjectManager(DataManager dataManager, Class<T> baseClass) {
/* 40 */     super(dataManager, baseClass);
/*    */   }
/*    */ 
/*    */   
/*    */   public final Set<Long> getUserItems(long userId) {
/* 45 */     if (!this.userItems.containsKey(Long.valueOf(userId))) {
/* 46 */       this.userItems.put(Long.valueOf(userId), new HashSet<>());
/*    */     }
/* 48 */     return this.userItems.get(Long.valueOf(userId));
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<Long> getManagedItems(long userId) {
/* 53 */     Set<Long> result = new HashSet<>();
/* 54 */     result.addAll(getUserItems(userId));
/* 55 */     for (Iterator<Long> iterator = Context.getUsersManager().getUserItems(userId).iterator(); iterator.hasNext(); ) { long managedUserId = ((Long)iterator.next()).longValue();
/* 56 */       result.addAll(getUserItems(managedUserId)); }
/*    */     
/* 58 */     return result;
/*    */   }
/*    */   
/*    */   public final boolean checkItemPermission(long userId, long itemId) {
/* 62 */     return getUserItems(userId).contains(Long.valueOf(itemId));
/*    */   }
/*    */ 
/*    */   
/*    */   public void refreshItems() {
/* 67 */     super.refreshItems();
/* 68 */     refreshUserItems();
/*    */   }
/*    */   
/*    */   public final void refreshUserItems() {
/* 72 */     if (getDataManager() != null) {
/*    */       try {
/* 74 */         if (this.userItems != null) {
/* 75 */           this.userItems.clear();
/*    */         } else {
/* 77 */           this.userItems = new ConcurrentHashMap<>();
/*    */         } 
/* 79 */         for (Permission permission : getDataManager().getPermissions((Class)User.class, getBaseClass())) {
/* 80 */           getUserItems(permission.getOwnerId()).add(Long.valueOf(permission.getPropertyId()));
/*    */         }
/* 82 */       } catch (SQLException|ClassNotFoundException error) {
/* 83 */         LOGGER.warn("Error getting permissions", error);
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void removeItem(long itemId) throws SQLException {
/* 90 */     super.removeItem(itemId);
/* 91 */     refreshUserItems();
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\database\SimpleObjectManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */