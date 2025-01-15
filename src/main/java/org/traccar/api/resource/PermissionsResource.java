/*    */ package org.traccar.api.resource;
/*    */ 
/*    */ import java.sql.SQLException;
/*    */ import java.util.LinkedHashMap;
/*    */ import javax.ws.rs.Consumes;
/*    */ import javax.ws.rs.DELETE;
/*    */ import javax.ws.rs.POST;
/*    */ import javax.ws.rs.Path;
/*    */ import javax.ws.rs.Produces;
/*    */ import javax.ws.rs.core.Response;
/*    */ import org.traccar.Context;
/*    */ import org.traccar.api.BaseResource;
/*    */ import org.traccar.helper.LogAction;
/*    */ import org.traccar.model.Device;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ @Path("permissions")
/*    */ @Produces({"application/json"})
/*    */ @Consumes({"application/json"})
/*    */ public class PermissionsResource
/*    */   extends BaseResource
/*    */ {
/*    */   private void checkPermission(Permission permission, boolean link) {
/* 43 */     if (!link && permission.getOwnerClass().equals(User.class) && permission
/* 44 */       .getPropertyClass().equals(Device.class)) {
/* 45 */       if (getUserId() != permission.getOwnerId()) {
/* 46 */         Context.getPermissionsManager().checkUser(getUserId(), permission.getOwnerId());
/*    */       } else {
/* 48 */         Context.getPermissionsManager().checkAdmin(getUserId());
/*    */       } 
/*    */     } else {
/* 51 */       Context.getPermissionsManager().checkPermission(permission
/* 52 */           .getOwnerClass(), getUserId(), permission.getOwnerId());
/*    */     } 
/* 54 */     Context.getPermissionsManager().checkPermission(permission
/* 55 */         .getPropertyClass(), getUserId(), permission.getPropertyId());
/*    */   }
/*    */   
/*    */   @POST
/*    */   public Response add(LinkedHashMap<String, Long> entity) throws SQLException, ClassNotFoundException {
/* 60 */     Context.getPermissionsManager().checkReadonly(getUserId());
/* 61 */     Permission permission = new Permission(entity);
/* 62 */     checkPermission(permission, true);
/* 63 */     Context.getDataManager().linkObject(permission.getOwnerClass(), permission.getOwnerId(), permission
/* 64 */         .getPropertyClass(), permission.getPropertyId(), true);
/* 65 */     LogAction.link(getUserId(), permission.getOwnerClass(), permission.getOwnerId(), permission
/* 66 */         .getPropertyClass(), permission.getPropertyId());
/* 67 */     Context.getPermissionsManager().refreshPermissions(permission);
/* 68 */     return Response.noContent().build();
/*    */   }
/*    */   
/*    */   @DELETE
/*    */   public Response remove(LinkedHashMap<String, Long> entity) throws SQLException, ClassNotFoundException {
/* 73 */     Context.getPermissionsManager().checkReadonly(getUserId());
/* 74 */     Permission permission = new Permission(entity);
/* 75 */     checkPermission(permission, false);
/* 76 */     Context.getDataManager().linkObject(permission.getOwnerClass(), permission.getOwnerId(), permission
/* 77 */         .getPropertyClass(), permission.getPropertyId(), false);
/* 78 */     LogAction.unlink(getUserId(), permission.getOwnerClass(), permission.getOwnerId(), permission
/* 79 */         .getPropertyClass(), permission.getPropertyId());
/* 80 */     Context.getPermissionsManager().refreshPermissions(permission);
/* 81 */     return Response.noContent().build();
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\api\resource\PermissionsResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */