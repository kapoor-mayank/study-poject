/*     */ package org.traccar.database;
/*     */ 
/*     */ import java.sql.SQLException;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.traccar.Context;
/*     */ import org.traccar.model.BaseModel;
/*     */ import org.traccar.model.Device;
/*     */ import org.traccar.model.Group;
/*     */ import org.traccar.model.Permission;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ExtendedObjectManager<T extends BaseModel>
/*     */   extends SimpleObjectManager<T>
/*     */ {
/*  36 */   private static final Logger LOGGER = LoggerFactory.getLogger(ExtendedObjectManager.class);
/*     */   
/*  38 */   private final Map<Long, Set<Long>> deviceItems = new ConcurrentHashMap<>();
/*  39 */   private final Map<Long, Set<Long>> deviceItemsWithGroups = new ConcurrentHashMap<>();
/*  40 */   private final Map<Long, Set<Long>> groupItems = new ConcurrentHashMap<>();
/*     */   
/*     */   protected ExtendedObjectManager(DataManager dataManager, Class<T> baseClass) {
/*  43 */     super(dataManager, baseClass);
/*  44 */     refreshExtendedPermissions();
/*     */   }
/*     */   
/*     */   public final Set<Long> getGroupItems(long groupId) {
/*  48 */     if (!this.groupItems.containsKey(Long.valueOf(groupId))) {
/*  49 */       this.groupItems.put(Long.valueOf(groupId), new HashSet<>());
/*     */     }
/*  51 */     return this.groupItems.get(Long.valueOf(groupId));
/*     */   }
/*     */   
/*     */   public final Set<Long> getDeviceItems(long deviceId) {
/*  55 */     if (!this.deviceItems.containsKey(Long.valueOf(deviceId))) {
/*  56 */       this.deviceItems.put(Long.valueOf(deviceId), new HashSet<>());
/*     */     }
/*  58 */     return this.deviceItems.get(Long.valueOf(deviceId));
/*     */   }
/*     */   
/*     */   public Set<Long> getAllDeviceItems(long deviceId) {
/*  62 */     if (!this.deviceItemsWithGroups.containsKey(Long.valueOf(deviceId))) {
/*  63 */       this.deviceItemsWithGroups.put(Long.valueOf(deviceId), new HashSet<>());
/*     */     }
/*  65 */     return this.deviceItemsWithGroups.get(Long.valueOf(deviceId));
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeItem(long itemId) throws SQLException {
/*  70 */     super.removeItem(itemId);
/*  71 */     refreshExtendedPermissions();
/*     */   }
/*     */   
/*     */   public void refreshExtendedPermissions() {
/*  75 */     if (getDataManager() != null)
/*     */       
/*     */       try {
/*     */         
/*  79 */         Collection<Permission> databaseGroupPermissions = getDataManager().getPermissions((Class)Group.class, getBaseClass());
/*     */         
/*  81 */         this.groupItems.clear();
/*  82 */         for (Permission groupPermission : databaseGroupPermissions) {
/*  83 */           getGroupItems(groupPermission.getOwnerId()).add(Long.valueOf(groupPermission.getPropertyId()));
/*     */         }
/*     */ 
/*     */         
/*  87 */         Collection<Permission> databaseDevicePermissions = getDataManager().getPermissions((Class)Device.class, getBaseClass());
/*     */         
/*  89 */         this.deviceItems.clear();
/*  90 */         this.deviceItemsWithGroups.clear();
/*     */         
/*  92 */         for (Permission devicePermission : databaseDevicePermissions) {
/*  93 */           getDeviceItems(devicePermission.getOwnerId()).add(Long.valueOf(devicePermission.getPropertyId()));
/*  94 */           getAllDeviceItems(devicePermission.getOwnerId()).add(Long.valueOf(devicePermission.getPropertyId()));
/*     */         } 
/*     */         
/*  97 */         for (Device device : Context.getDeviceManager().getAllDevices()) {
/*  98 */           long groupId = device.getGroupId();
/*  99 */           while (groupId != 0L) {
/* 100 */             getAllDeviceItems(device.getId()).addAll(getGroupItems(groupId));
/* 101 */             Group group = Context.getGroupsManager().getById(groupId);
/* 102 */             if (group != null) {
/* 103 */               groupId = group.getGroupId(); continue;
/*     */             } 
/* 105 */             groupId = 0L;
/*     */           }
/*     */         
/*     */         }
/*     */       
/* 110 */       } catch (SQLException|ClassNotFoundException error) {
/* 111 */         LOGGER.warn("Refresh permissions error", error);
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\database\ExtendedObjectManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */