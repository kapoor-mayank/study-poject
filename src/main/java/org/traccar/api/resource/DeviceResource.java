/*    */ package org.traccar.api.resource;
/*    */ 
/*    */ import java.sql.SQLException;
/*    */ import java.util.Collection;
/*    */ import java.util.HashSet;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import javax.ws.rs.Consumes;
/*    */ import javax.ws.rs.GET;
/*    */ import javax.ws.rs.PUT;
/*    */ import javax.ws.rs.Path;
/*    */ import javax.ws.rs.Produces;
/*    */ import javax.ws.rs.QueryParam;
/*    */ import javax.ws.rs.core.Response;
/*    */ import org.traccar.Context;
/*    */ import org.traccar.api.BaseObjectResource;
/*    */ import org.traccar.database.DeviceManager;
/*    */ import org.traccar.helper.LogAction;
/*    */ import org.traccar.model.Device;
/*    */ import org.traccar.model.DeviceAccumulators;
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
/*    */ @Path("devices")
/*    */ @Produces({"application/json"})
/*    */ @Consumes({"application/json"})
/*    */ public class DeviceResource
/*    */   extends BaseObjectResource<Device>
/*    */ {
/*    */   public DeviceResource() {
/* 46 */     super(Device.class);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @GET
/*    */   public Collection<Device> get(@QueryParam("all") boolean all, @QueryParam("userId") long userId, @QueryParam("uniqueId") List<String> uniqueIds, @QueryParam("id") List<Long> deviceIds) throws SQLException {
/* 54 */     DeviceManager deviceManager = Context.getDeviceManager();
/* 55 */     Set<Long> result = null;
/* 56 */     if (all) {
/* 57 */       if (Context.getPermissionsManager().getUserAdmin(getUserId())) {
/* 58 */         result = deviceManager.getAllItems();
/*    */       } else {
/* 60 */         Context.getPermissionsManager().checkManager(getUserId());
/* 61 */         result = deviceManager.getManagedItems(getUserId());
/*    */       } 
/* 63 */     } else if (uniqueIds.isEmpty() && deviceIds.isEmpty()) {
/* 64 */       if (userId == 0L) {
/* 65 */         userId = getUserId();
/*    */       }
/* 67 */       Context.getPermissionsManager().checkUser(getUserId(), userId);
/* 68 */       if (Context.getPermissionsManager().getUserAdmin(getUserId())) {
/* 69 */         result = deviceManager.getAllUserItems(userId);
/*    */       } else {
/* 71 */         result = deviceManager.getUserItems(userId);
/*    */       } 
/*    */     } else {
/* 74 */       result = new HashSet<>();
/* 75 */       for (String uniqueId : uniqueIds) {
/* 76 */         Device device = deviceManager.getByUniqueId(uniqueId);
/* 77 */         Context.getPermissionsManager().checkDevice(getUserId(), device.getId());
/* 78 */         result.add(Long.valueOf(device.getId()));
/*    */       } 
/* 80 */       for (Long deviceId : deviceIds) {
/* 81 */         Context.getPermissionsManager().checkDevice(getUserId(), deviceId.longValue());
/* 82 */         result.add(deviceId);
/*    */       } 
/*    */     } 
/* 85 */     return deviceManager.getItems(result);
/*    */   }
/*    */   
/*    */   @Path("{id}/accumulators")
/*    */   @PUT
/*    */   public Response updateAccumulators(DeviceAccumulators entity) throws SQLException {
/* 91 */     if (!Context.getPermissionsManager().getUserAdmin(getUserId())) {
/* 92 */       Context.getPermissionsManager().checkManager(getUserId());
/* 93 */       Context.getPermissionsManager().checkPermission(Device.class, getUserId(), entity.getDeviceId());
/*    */     } 
/* 95 */     Context.getDeviceManager().resetDeviceAccumulators(entity);
/* 96 */     LogAction.resetDeviceAccumulators(getUserId(), entity.getDeviceId());
/* 97 */     return Response.noContent().build();
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\api\resource\DeviceResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */