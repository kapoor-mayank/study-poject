/*    */ package org.traccar.api.resource;
/*    */ 
/*    */ import java.sql.SQLException;
/*    */ import javax.ws.rs.Consumes;
/*    */ import javax.ws.rs.GET;
/*    */ import javax.ws.rs.Path;
/*    */ import javax.ws.rs.PathParam;
/*    */ import javax.ws.rs.Produces;
/*    */ import org.traccar.Context;
/*    */ import org.traccar.api.BaseResource;
/*    */ import org.traccar.model.Event;
/*    */ import org.traccar.model.Geofence;
/*    */ import org.traccar.model.Maintenance;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Path("events")
/*    */ @Produces({"application/json"})
/*    */ @Consumes({"application/json"})
/*    */ public class EventResource
/*    */   extends BaseResource
/*    */ {
/*    */   @Path("{id}")
/*    */   @GET
/*    */   public Event get(@PathParam("id") long id) throws SQLException {
/* 27 */     Event event = (Event)Context.getDataManager().getObject(Event.class, id);
/* 28 */     Context.getPermissionsManager().checkDevice(getUserId(), event.getDeviceId());
/* 29 */     if (event.getGeofenceId() != 0L) {
/* 30 */       Context.getPermissionsManager().checkPermission(Geofence.class, getUserId(), event.getGeofenceId());
/*    */     }
/* 32 */     if (event.getMaintenanceId() != 0L) {
/* 33 */       Context.getPermissionsManager().checkPermission(Maintenance.class, getUserId(), event.getMaintenanceId());
/*    */     }
/* 35 */     return event;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\api\resource\EventResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */