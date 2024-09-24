/*     */ package org.traccar.database;
/*     */ 
/*     */ import java.sql.SQLException;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.atomic.AtomicLong;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.traccar.Context;
/*     */ import org.traccar.model.BaseModel;
/*     */ import org.traccar.model.Group;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GroupsManager
/*     */   extends BaseObjectManager<Group>
/*     */   implements ManagableObjects
/*     */ {
/*  31 */   private static final Logger LOGGER = LoggerFactory.getLogger(GroupsManager.class);
/*     */   
/*  33 */   private AtomicLong groupsLastUpdate = new AtomicLong();
/*     */   private final long dataRefreshDelay;
/*     */   
/*     */   public GroupsManager(DataManager dataManager) {
/*  37 */     super(dataManager, Group.class);
/*  38 */     this.dataRefreshDelay = Context.getConfig().getLong("database.refreshDelay", 300L) * 1000L;
/*     */   }
/*     */ 
/*     */   
/*     */   private void checkGroupCycles(Group group) {
/*  43 */     Set<Long> groups = new HashSet<>();
/*  44 */     while (group != null) {
/*  45 */       if (groups.contains(Long.valueOf(group.getId()))) {
/*  46 */         throw new IllegalArgumentException("Cycle in group hierarchy");
/*     */       }
/*  48 */       groups.add(Long.valueOf(group.getId()));
/*  49 */       group = getById(group.getGroupId());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void updateGroupCache(boolean force) throws SQLException {
/*  54 */     long lastUpdate = this.groupsLastUpdate.get();
/*  55 */     if ((force || System.currentTimeMillis() - lastUpdate > this.dataRefreshDelay) && this.groupsLastUpdate
/*  56 */       .compareAndSet(lastUpdate, System.currentTimeMillis())) {
/*  57 */       refreshItems();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<Long> getAllItems() {
/*  63 */     Set<Long> result = super.getAllItems();
/*  64 */     if (result.isEmpty()) {
/*     */       try {
/*  66 */         updateGroupCache(true);
/*  67 */       } catch (SQLException e) {
/*  68 */         LOGGER.warn("Update group cache error", e);
/*     */       } 
/*  70 */       result = super.getAllItems();
/*     */     } 
/*  72 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addNewItem(Group group) {
/*  77 */     checkGroupCycles(group);
/*  78 */     super.addNewItem(group);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateItem(Group group) throws SQLException {
/*  83 */     checkGroupCycles(group);
/*  84 */     super.updateItem(group);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<Long> getUserItems(long userId) {
/*  89 */     if (Context.getPermissionsManager() != null) {
/*  90 */       return Context.getPermissionsManager().getGroupPermissions(userId);
/*     */     }
/*  92 */     return new HashSet<>();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<Long> getManagedItems(long userId) {
/*  98 */     Set<Long> result = new HashSet<>();
/*  99 */     result.addAll(getUserItems(userId));
/* 100 */     for (Iterator<Long> iterator = Context.getUsersManager().getUserItems(userId).iterator(); iterator.hasNext(); ) { long managedUserId = ((Long)iterator.next()).longValue();
/* 101 */       result.addAll(getUserItems(managedUserId)); }
/*     */     
/* 103 */     return result;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\database\GroupsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */