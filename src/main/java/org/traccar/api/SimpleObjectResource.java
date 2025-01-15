/*    */ package org.traccar.api;
/*    */ 
/*    */ import java.sql.SQLException;
/*    */ import java.util.Collection;
/*    */ import javax.ws.rs.GET;
/*    */ import javax.ws.rs.QueryParam;
/*    */ import org.traccar.Context;
/*    */ import org.traccar.database.BaseObjectManager;
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
/*    */ 
/*    */ public class SimpleObjectResource<T extends BaseModel>
/*    */   extends BaseObjectResource<T>
/*    */ {
/*    */   public SimpleObjectResource(Class<T> baseClass) {
/* 32 */     super(baseClass);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @GET
/*    */   public Collection<T> get(@QueryParam("all") boolean all, @QueryParam("userId") long userId) throws SQLException {
/* 39 */     BaseObjectManager<T> manager = Context.getManager(getBaseClass());
/* 40 */     return manager.getItems(getSimpleManagerItems(manager, all, userId));
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\api\SimpleObjectResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */