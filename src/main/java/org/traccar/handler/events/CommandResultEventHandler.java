/*    */ package org.traccar.handler.events;
/*    */ 
/*    */ import io.netty.channel.ChannelHandler.Sharable;
/*    */ import java.util.Collections;
/*    */ import java.util.Map;
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
/*    */ @Sharable
/*    */ public class CommandResultEventHandler
/*    */   extends BaseEventHandler
/*    */ {
/*    */   protected Map<Event, Position> analyzePosition(Position position) {
/* 30 */     Object commandResult = position.getAttributes().get("result");
/* 31 */     if (commandResult != null) {
/* 32 */       Event event = new Event("commandResult", position.getDeviceId(), position.getId());
/* 33 */       event.set("result", (String)commandResult);
/* 34 */       return Collections.singletonMap(event, position);
/*    */     } 
/* 36 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\handler\events\CommandResultEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */