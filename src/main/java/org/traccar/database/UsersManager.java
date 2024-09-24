/*    */ package org.traccar.database;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ import org.traccar.model.BaseModel;
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
/*    */ public class UsersManager
/*    */   extends SimpleObjectManager<User>
/*    */ {
/*    */   private Map<String, User> usersTokens;
/*    */   
/*    */   public UsersManager(DataManager dataManager) {
/* 31 */     super(dataManager, User.class);
/* 32 */     if (this.usersTokens == null) {
/* 33 */       this.usersTokens = new ConcurrentHashMap<>();
/*    */     }
/*    */   }
/*    */   
/*    */   private void putToken(User user) {
/* 38 */     if (this.usersTokens == null) {
/* 39 */       this.usersTokens = new ConcurrentHashMap<>();
/*    */     }
/* 41 */     if (user.getToken() != null) {
/* 42 */       this.usersTokens.put(user.getToken(), user);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected void addNewItem(User user) {
/* 48 */     super.addNewItem(user);
/* 49 */     putToken(user);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void updateCachedItem(User user) {
/* 54 */     User cachedUser = getById(user.getId());
/* 55 */     super.updateCachedItem(user);
/* 56 */     putToken(user);
/* 57 */     if (cachedUser.getToken() != null && !cachedUser.getToken().equals(user.getToken())) {
/* 58 */       this.usersTokens.remove(cachedUser.getToken());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected void removeCachedItem(long userId) {
/* 64 */     User cachedUser = getById(userId);
/* 65 */     if (cachedUser != null) {
/* 66 */       String userToken = cachedUser.getToken();
/* 67 */       super.removeCachedItem(userId);
/* 68 */       if (userToken != null) {
/* 69 */         this.usersTokens.remove(userToken);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<Long> getManagedItems(long userId) {
/* 76 */     Set<Long> result = new HashSet<>();
/* 77 */     result.addAll(getUserItems(userId));
/* 78 */     result.add(Long.valueOf(userId));
/* 79 */     return result;
/*    */   }
/*    */   
/*    */   public User getUserByToken(String token) {
/* 83 */     return this.usersTokens.get(token);
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\database\UsersManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */