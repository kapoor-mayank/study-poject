/*    */ package org.traccar.api.resource;
/*    */ 
/*    */ import java.sql.SQLException;
/*    */ import java.util.Collection;
/*    */ import java.util.Date;
/*    */ import java.util.Set;
/*    */ import javax.annotation.security.PermitAll;
/*    */ import javax.ws.rs.Consumes;
/*    */ import javax.ws.rs.GET;
/*    */ import javax.ws.rs.POST;
/*    */ import javax.ws.rs.Path;
/*    */ import javax.ws.rs.Produces;
/*    */ import javax.ws.rs.QueryParam;
/*    */ import javax.ws.rs.core.Response;
/*    */ import org.traccar.Context;
/*    */ import org.traccar.api.BaseObjectResource;
/*    */ import org.traccar.database.UsersManager;
/*    */ import org.traccar.helper.LogAction;
/*    */ import org.traccar.model.BaseModel;
/*    */ import org.traccar.model.ManagedUser;
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
/*    */ @Path("users")
/*    */ @Produces({"application/json"})
/*    */ @Consumes({"application/json"})
/*    */ public class UserResource
/*    */   extends BaseObjectResource<User>
/*    */ {
/*    */   public UserResource() {
/* 45 */     super(User.class);
/*    */   }
/*    */   
/*    */   @GET
/*    */   public Collection<User> get(@QueryParam("userId") long userId) throws SQLException {
/* 50 */     UsersManager usersManager = Context.getUsersManager();
/* 51 */     Set<Long> result = null;
/* 52 */     if (Context.getPermissionsManager().getUserAdmin(getUserId())) {
/* 53 */       if (userId != 0L) {
/* 54 */         result = usersManager.getUserItems(userId);
/*    */       } else {
/* 56 */         result = usersManager.getAllItems();
/*    */       } 
/* 58 */     } else if (Context.getPermissionsManager().getUserManager(getUserId())) {
/* 59 */       result = usersManager.getManagedItems(getUserId());
/*    */     } else {
/* 61 */       throw new SecurityException("Admin or manager access required");
/*    */     } 
/* 63 */     return usersManager.getItems(result);
/*    */   }
/*    */ 
/*    */   
/*    */   @PermitAll
/*    */   @POST
/*    */   public Response add(User entity) throws SQLException {
/* 70 */     if (!Context.getPermissionsManager().getUserAdmin(getUserId())) {
/* 71 */       Context.getPermissionsManager().checkUserUpdate(getUserId(), new User(), entity);
/* 72 */       if (Context.getPermissionsManager().getUserManager(getUserId())) {
/* 73 */         Context.getPermissionsManager().checkUserLimit(getUserId());
/*    */       } else {
/* 75 */         Context.getPermissionsManager().checkRegistration(getUserId());
/* 76 */         entity.setDeviceLimit(Context.getConfig().getInteger("users.defaultDeviceLimit", -1));
/* 77 */         int expirationDays = Context.getConfig().getInteger("users.defaultExpirationDays");
/* 78 */         if (expirationDays > 0) {
/* 79 */           entity.setExpirationTime(new Date(
/* 80 */                 System.currentTimeMillis() + expirationDays * 24L * 3600L * 1000L));
/*    */         }
/*    */       } 
/*    */     } 
/* 84 */     Context.getUsersManager().addItem((User) entity);
/* 85 */     LogAction.create(getUserId(), (BaseModel)entity);
/* 86 */     if (Context.getPermissionsManager().getUserManager(getUserId())) {
/* 87 */       Context.getDataManager().linkObject(User.class, getUserId(), ManagedUser.class, entity.getId(), true);
/* 88 */       LogAction.link(getUserId(), User.class, getUserId(), ManagedUser.class, entity.getId());
/*    */     } 
/* 90 */     Context.getUsersManager().refreshUserItems();
/* 91 */     return Response.ok(entity).build();
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\api\resource\UserResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */