/*    */ package org.traccar.api.resource;
/*    */ 
/*    */ import java.sql.SQLException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import javax.ws.rs.Consumes;
/*    */ import javax.ws.rs.GET;
/*    */ import javax.ws.rs.Path;
/*    */ import javax.ws.rs.Produces;
/*    */ import javax.ws.rs.QueryParam;
/*    */ import javax.ws.rs.core.Response;
/*    */ import org.traccar.Context;
/*    */ import org.traccar.api.BaseResource;
/*    */ import org.traccar.helper.DateUtil;
/*    */ import org.traccar.model.Position;
/*    */ import org.traccar.web.CsvBuilder;
/*    */ import org.traccar.web.GpxBuilder;
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
/*    */ 
/*    */ 
/*    */ @Path("positions")
/*    */ @Produces({"application/json"})
/*    */ @Consumes({"application/json"})
/*    */ public class PositionResource
/*    */   extends BaseResource
/*    */ {
/*    */   public static final String TEXT_CSV = "text/csv";
/*    */   public static final String CONTENT_DISPOSITION_VALUE_CSV = "attachment; filename=positions.csv";
/*    */   public static final String GPX = "application/gpx+xml";
/*    */   public static final String CONTENT_DISPOSITION_VALUE_GPX = "attachment; filename=positions.gpx";
/*    */   
/*    */   @GET
/*    */   public Collection<Position> getJson(@QueryParam("deviceId") long deviceId, @QueryParam("id") List<Long> positionIds, @QueryParam("from") String from, @QueryParam("to") String to) throws SQLException {
/* 54 */     if (!positionIds.isEmpty()) {
/* 55 */       ArrayList<Position> positions = new ArrayList<>();
/* 56 */       for (Long positionId : positionIds) {
/* 57 */         Position position = (Position)Context.getDataManager().getObject(Position.class, positionId.longValue());
/* 58 */         Context.getPermissionsManager().checkDevice(getUserId(), position.getDeviceId());
/* 59 */         positions.add(position);
/*    */       } 
/* 61 */       return positions;
/* 62 */     }  if (deviceId == 0L) {
/* 63 */       return Context.getDeviceManager().getInitialState(getUserId());
/*    */     }
/* 65 */     Context.getPermissionsManager().checkDevice(getUserId(), deviceId);
/* 66 */     return Context.getDataManager().getPositions(deviceId, 
/* 67 */         DateUtil.parseDate(from), DateUtil.parseDate(to));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @GET
/*    */   @Produces({"text/csv"})
/*    */   public Response getCsv(@QueryParam("deviceId") long deviceId, @QueryParam("from") String from, @QueryParam("to") String to) throws SQLException {
/* 76 */     Context.getPermissionsManager().checkDevice(getUserId(), deviceId);
/* 77 */     CsvBuilder csv = new CsvBuilder();
/* 78 */     csv.addHeaderLine(new Position());
/* 79 */     csv.addArray(Context.getDataManager().getPositions(deviceId, 
/* 80 */           DateUtil.parseDate(from), DateUtil.parseDate(to)));
/* 81 */     return Response.ok(csv.build()).header("Content-Disposition", "attachment; filename=positions.csv").build();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @GET
/*    */   @Produces({"application/gpx+xml"})
/*    */   public Response getGpx(@QueryParam("deviceId") long deviceId, @QueryParam("from") String from, @QueryParam("to") String to) throws SQLException {
/* 89 */     Context.getPermissionsManager().checkDevice(getUserId(), deviceId);
/* 90 */     GpxBuilder gpx = new GpxBuilder(Context.getIdentityManager().getById(deviceId).getName());
/* 91 */     gpx.addPositions(Context.getDataManager().getPositions(deviceId, 
/* 92 */           DateUtil.parseDate(from), DateUtil.parseDate(to)));
/* 93 */     return Response.ok(gpx.build()).header("Content-Disposition", "attachment; filename=positions.gpx").build();
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\api\resource\PositionResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */