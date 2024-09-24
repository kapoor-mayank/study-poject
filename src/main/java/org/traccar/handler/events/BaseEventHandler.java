/*    */ package org.traccar.handler.events;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.traccar.BaseDataHandler;
/*    */ import org.traccar.Context;
/*    */ import org.traccar.model.Event;
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
/*    */ public abstract class BaseEventHandler
/*    */   extends BaseDataHandler
/*    */ {
/*    */   protected Position handlePosition(Position position) {
/* 29 */     Map<Event, Position> events = analyzePosition(position);
/* 30 */     if (events != null && Context.getNotificationManager() != null) {
/* 31 */       Context.getNotificationManager().updateEvents(events);
/*    */     }
/* 33 */     return position;
/*    */   }
/*    */   
/*    */   protected abstract Map<Event, Position> analyzePosition(Position paramPosition);
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\handler\events\BaseEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */