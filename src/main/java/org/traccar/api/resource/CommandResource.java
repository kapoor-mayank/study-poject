/*    */ package org.traccar.api.resource;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import javax.ws.rs.Consumes;
/*    */ import javax.ws.rs.GET;
/*    */ import javax.ws.rs.POST;
/*    */ import javax.ws.rs.Path;
/*    */ import javax.ws.rs.Produces;
/*    */ import javax.ws.rs.QueryParam;
/*    */ import javax.ws.rs.core.Response;
/*    */ import org.traccar.Context;
/*    */ import org.traccar.api.ExtendedObjectResource;
/*    */ import org.traccar.database.CommandsManager;
/*    */ import org.traccar.model.Command;
/*    */ import org.traccar.model.Typed;
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
/*    */ 
/*    */ @Path("commands")
/*    */ @Produces({"application/json"})
/*    */ @Consumes({"application/json"})
/*    */ public class CommandResource
/*    */   extends ExtendedObjectResource<Command>
/*    */ {
/*    */   public CommandResource() {
/* 45 */     super(Command.class);
/*    */   }
/*    */   
/*    */   @GET
/*    */   @Path("send")
/*    */   public Collection<Command> get(@QueryParam("deviceId") long deviceId) {
/* 51 */     Context.getPermissionsManager().checkDevice(getUserId(), deviceId);
/* 52 */     CommandsManager commandsManager = Context.getCommandsManager();
/* 53 */     Set<Long> result = new HashSet<>(commandsManager.getUserItems(getUserId()));
/* 54 */     result.retainAll(commandsManager.getSupportedCommands(deviceId));
/* 55 */     return commandsManager.getItems(result);
/*    */   }
/*    */   
/*    */   @POST
/*    */   @Path("send")
/*    */   public Response send(Command entity) throws Exception {
/* 61 */     Context.getPermissionsManager().checkReadonly(getUserId());
/* 62 */     if (entity.getDeviceId() == 0L) {
/* 63 */       entity.setDeviceId(Context.getIdentityManager().getByUniqueId(entity.getUniqueId()).getId());
/*    */     }
/* 65 */     long deviceId = entity.getDeviceId();
/* 66 */     long id = entity.getId();
/* 67 */     Context.getPermissionsManager().checkDevice(getUserId(), deviceId);
/* 68 */     if (id != 0L) {
/* 69 */       Context.getPermissionsManager().checkPermission(Command.class, getUserId(), id);
/* 70 */       Context.getPermissionsManager().checkUserDeviceCommand(getUserId(), deviceId, id);
/*    */     } else {
/* 72 */       Context.getPermissionsManager().checkLimitCommands(getUserId());
/*    */     } 
/* 74 */     if (!Context.getCommandsManager().sendCommand(entity)) {
/* 75 */       return Response.accepted(entity).build();
/*    */     }
/* 77 */     return Response.ok(entity).build();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @GET
/*    */   @Path("types")
/*    */   public Collection<Typed> get(@QueryParam("deviceId") long deviceId, @QueryParam("protocol") String protocol, @QueryParam("textChannel") boolean textChannel) {
/* 86 */     if (deviceId != 0L) {
/* 87 */       Context.getPermissionsManager().checkDevice(getUserId(), deviceId);
/* 88 */       return Context.getCommandsManager().getCommandTypes(deviceId, textChannel);
/* 89 */     }  if (protocol != null) {
/* 90 */       return Context.getCommandsManager().getCommandTypes(protocol, textChannel);
/*    */     }
/* 92 */     return Context.getCommandsManager().getAllCommandTypes();
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\api\resource\CommandResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */