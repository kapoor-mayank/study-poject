/*     */ package org.traccar.database;
/*     */ 
/*     */ import java.sql.SQLException;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.traccar.model.BaseModel;
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
/*     */ public class BaseObjectManager<T extends BaseModel>
/*     */ {
/*  33 */   private static final Logger LOGGER = LoggerFactory.getLogger(BaseObjectManager.class);
/*     */   
/*     */   private final DataManager dataManager;
/*     */   
/*     */   private Map<Long, T> items;
/*     */   private Class<T> baseClass;
/*     */   
/*     */   protected BaseObjectManager(DataManager dataManager, Class<T> baseClass) {
/*  41 */     this.dataManager = dataManager;
/*  42 */     this.baseClass = baseClass;
/*  43 */     refreshItems();
/*     */   }
/*     */   
/*     */   protected final DataManager getDataManager() {
/*  47 */     return this.dataManager;
/*     */   }
/*     */   
/*     */   protected final Class<T> getBaseClass() {
/*  51 */     return this.baseClass;
/*     */   }
/*     */   
/*     */   public T getById(long itemId) {
/*  55 */     return this.items.get(Long.valueOf(itemId));
/*     */   }
/*     */   
/*     */   public void refreshItems() {
/*  59 */     if (this.dataManager != null) {
/*     */       try {
/*  61 */         Collection<T> databaseItems = this.dataManager.getObjects(this.baseClass);
/*  62 */         if (this.items == null) {
/*  63 */           this.items = new ConcurrentHashMap<>(databaseItems.size());
/*     */         }
/*  65 */         Set<Long> databaseItemIds = new HashSet<>();
/*  66 */         for (BaseModel baseModel : databaseItems) {
/*  67 */           databaseItemIds.add(Long.valueOf(baseModel.getId()));
/*  68 */           if (this.items.containsKey(Long.valueOf(baseModel.getId()))) {
/*  69 */             updateCachedItem((T)baseModel); continue;
/*     */           } 
/*  71 */           addNewItem((T)baseModel);
/*     */         } 
/*     */         
/*  74 */         for (Long cachedItemId : this.items.keySet()) {
/*  75 */           if (!databaseItemIds.contains(cachedItemId)) {
/*  76 */             removeCachedItem(cachedItemId.longValue());
/*     */           }
/*     */         } 
/*  79 */       } catch (SQLException error) {
/*  80 */         LOGGER.warn("Error refreshing items", error);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   protected void addNewItem(T item) {
/*  86 */     this.items.put(Long.valueOf(item.getId()), item);
/*     */   }
/*     */   
/*     */   public void addItem(T item) throws SQLException {
/*  90 */     this.dataManager.addObject((BaseModel)item);
/*  91 */     addNewItem(item);
/*     */   }
/*     */   
/*     */   protected void updateCachedItem(T item) {
/*  95 */     this.items.put(Long.valueOf(item.getId()), item);
/*     */   }
/*     */   
/*     */   public void updateItem(T item) throws SQLException {
/*  99 */     this.dataManager.updateObject((BaseModel)item);
/* 100 */     updateCachedItem(item);
/*     */   }
/*     */   
/*     */   protected void removeCachedItem(long itemId) {
/* 104 */     this.items.remove(Long.valueOf(itemId));
/*     */   }
/*     */   
/*     */   public void removeItem(long itemId) throws SQLException {
/* 108 */     BaseModel item = (BaseModel)getById(itemId);
/* 109 */     if (item != null) {
/* 110 */       this.dataManager.removeObject(this.baseClass, itemId);
/* 111 */       removeCachedItem(itemId);
/*     */     } 
/*     */   }
/*     */   
/*     */   public final Collection<T> getItems(Set<Long> itemIds) {
/* 116 */     Collection<T> result = new LinkedList<>();
/* 117 */     for (Iterator<Long> iterator = itemIds.iterator(); iterator.hasNext(); ) { long itemId = ((Long)iterator.next()).longValue();
/* 118 */       result.add(getById(itemId)); }
/*     */     
/* 120 */     return result;
/*     */   }
/*     */   
/*     */   public Set<Long> getAllItems() {
/* 124 */     return this.items.keySet();
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\database\BaseObjectManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */