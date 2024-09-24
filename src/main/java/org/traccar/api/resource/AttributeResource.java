/*    */ package org.traccar.api.resource;
/*    */ 
/*    */ import java.sql.SQLException;
/*    */ import javax.ws.rs.Consumes;
/*    */ import javax.ws.rs.DELETE;
/*    */ import javax.ws.rs.POST;
/*    */ import javax.ws.rs.PUT;
/*    */ import javax.ws.rs.Path;
/*    */ import javax.ws.rs.PathParam;
/*    */ import javax.ws.rs.Produces;
/*    */ import javax.ws.rs.QueryParam;
/*    */ import javax.ws.rs.core.Response;
/*    */ import org.traccar.Context;
/*    */ import org.traccar.api.ExtendedObjectResource;
/*    */ import org.traccar.handler.ComputedAttributesHandler;
/*    */ import org.traccar.model.Attribute;
/*    */ import org.traccar.model.BaseModel;
/*    */ import org.traccar.model.Position;
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
/*    */ @Path("attributes/computed")
/*    */ @Produces({"application/json"})
/*    */ @Consumes({"application/json"})
/*    */ public class AttributeResource
/*    */   extends ExtendedObjectResource<Attribute>
/*    */ {
/*    */   public AttributeResource() {
/* 44 */     super(Attribute.class);
/*    */   }
/*    */   
/*    */   @POST
/*    */   @Path("test")
/*    */   public Response test(@QueryParam("deviceId") long deviceId, Attribute entity) {
/* 50 */     Context.getPermissionsManager().checkAdmin(getUserId());
/* 51 */     Context.getPermissionsManager().checkDevice(getUserId(), deviceId);
/* 52 */     Position last = Context.getIdentityManager().getLastPosition(deviceId);
/* 53 */     if (last != null) {
/*    */ 
/*    */ 
/*    */       
/* 57 */       Object result = (new ComputedAttributesHandler(Context.getConfig(), Context.getIdentityManager(), Context.getAttributesManager())).computeAttribute(entity, last);
/* 58 */       if (result != null) {
/* 59 */         Number numberValue; Boolean booleanValue; switch (entity.getType()) {
/*    */           case "number":
/* 61 */             numberValue = (Number)result;
/* 62 */             return Response.ok(numberValue).build();
/*    */           case "boolean":
/* 64 */             booleanValue = (Boolean)result;
/* 65 */             return Response.ok(booleanValue).build();
/*    */         } 
/* 67 */         return Response.ok(result.toString()).build();
/*    */       } 
/*    */       
/* 70 */       return Response.noContent().build();
/*    */     } 
/*    */     
/* 73 */     throw new IllegalArgumentException("Device has no last position");
/*    */   }
/*    */ 
/*    */   
/*    */   @POST
/*    */   public Response add(Attribute entity) throws SQLException {
/* 79 */     Context.getPermissionsManager().checkAdmin(getUserId());
/* 80 */     return super.add((BaseModel)entity);
/*    */   }
/*    */   
/*    */   @Path("{id}")
/*    */   @PUT
/*    */   public Response update(Attribute entity) throws SQLException {
/* 86 */     Context.getPermissionsManager().checkAdmin(getUserId());
/* 87 */     return super.update((BaseModel)entity);
/*    */   }
/*    */   
/*    */   @Path("{id}")
/*    */   @DELETE
/*    */   public Response remove(@PathParam("id") long id) throws SQLException {
/* 93 */     Context.getPermissionsManager().checkAdmin(getUserId());
/* 94 */     return super.remove(id);
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\api\resource\AttributeResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */