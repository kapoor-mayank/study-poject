/*    */ package org.traccar.api;
/*    */ 
/*    */ import java.sql.SQLException;
/*    */ import java.util.Collection;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import javax.ws.rs.GET;
/*    */ import javax.ws.rs.QueryParam;
/*    */ import org.traccar.Context;
/*    */ import org.traccar.database.BaseObjectManager;
/*    */ import org.traccar.database.ExtendedObjectManager;
/*    */ import org.traccar.model.BaseModel;
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
/*    */ public class ExtendedObjectResource<T extends BaseModel>
/*    */   extends BaseObjectResource<T>
/*    */ {
/*    */   public ExtendedObjectResource(Class<T> baseClass) {
/* 34 */     super(baseClass);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @GET
/*    */   public Collection<T> get(@QueryParam("all") boolean all, @QueryParam("userId") long userId, @QueryParam("groupId") long groupId, @QueryParam("deviceId") long deviceId, @QueryParam("refresh") boolean refresh) throws SQLException {
/* 42 */     ExtendedObjectManager<T> manager = (ExtendedObjectManager<T>)Context.getManager(getBaseClass());
/* 43 */     if (refresh) {
/* 44 */       manager.refreshItems();
/*    */     }
/*    */     
/* 47 */     Set<Long> result = new HashSet<>(getSimpleManagerItems((BaseObjectManager<T>)manager, all, userId));
/*    */     
/* 49 */     if (groupId != 0L) {
/* 50 */       Context.getPermissionsManager().checkGroup(getUserId(), groupId);
/* 51 */       result.retainAll(manager.getGroupItems(groupId));
/*    */     } 
/*    */     
/* 54 */     if (deviceId != 0L) {
/* 55 */       Context.getPermissionsManager().checkDevice(getUserId(), deviceId);
/* 56 */       result.retainAll(manager.getDeviceItems(deviceId));
/*    */     } 
/* 58 */     return manager.getItems(result);
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\api\ExtendedObjectResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */