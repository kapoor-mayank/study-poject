/*    */ package org.traccar.api.resource;
/*    */ 
/*    */ import javax.ws.rs.Consumes;
/*    */ import javax.ws.rs.Path;
/*    */ import javax.ws.rs.Produces;
/*    */ import org.traccar.api.SimpleObjectResource;
/*    */ import org.traccar.model.Calendar;
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
/*    */ @Path("calendars")
/*    */ @Produces({"application/json"})
/*    */ @Consumes({"application/json"})
/*    */ public class CalendarResource
/*    */   extends SimpleObjectResource<Calendar>
/*    */ {
/*    */   public CalendarResource() {
/* 33 */     super(Calendar.class);
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\api\resource\CalendarResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */