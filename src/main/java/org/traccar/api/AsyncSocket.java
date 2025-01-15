/*    */ package org.traccar.api;
/*    */ 
/*    */ import com.fasterxml.jackson.core.JsonProcessingException;
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.eclipse.jetty.websocket.api.Session;
/*    */ import org.eclipse.jetty.websocket.api.WebSocketAdapter;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.traccar.Context;
/*    */ import org.traccar.database.ConnectionManager;
/*    */ import org.traccar.model.Device;
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
/*    */ public class AsyncSocket
/*    */   extends WebSocketAdapter
/*    */   implements ConnectionManager.UpdateListener
/*    */ {
/* 36 */   private static final Logger LOGGER = LoggerFactory.getLogger(AsyncSocket.class);
/*    */   
/*    */   private static final String KEY_DEVICES = "devices";
/*    */   
/*    */   private static final String KEY_POSITIONS = "positions";
/*    */   private static final String KEY_EVENTS = "events";
/*    */   private long userId;
/*    */   
/*    */   public AsyncSocket(long userId) {
/* 45 */     this.userId = userId;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onWebSocketConnect(Session session) {
/* 50 */     super.onWebSocketConnect(session);
/*    */     
/* 52 */     Map<String, Collection<?>> data = new HashMap<>();
/* 53 */     data.put("positions", Context.getDeviceManager().getInitialState(this.userId));
/* 54 */     sendData(data);
/*    */     
/* 56 */     Context.getConnectionManager().addListener(this.userId, this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onWebSocketClose(int statusCode, String reason) {
/* 61 */     super.onWebSocketClose(statusCode, reason);
/*    */     
/* 63 */     Context.getConnectionManager().removeListener(this.userId, this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdateDevice(Device device) {
/* 68 */     Map<String, Collection<?>> data = new HashMap<>();
/* 69 */     data.put("devices", Collections.singletonList(device));
/* 70 */     sendData(data);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdatePosition(Position position) {
/* 75 */     Map<String, Collection<?>> data = new HashMap<>();
/* 76 */     data.put("positions", Collections.singletonList(position));
/* 77 */     sendData(data);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdateEvent(Event event) {
/* 82 */     Map<String, Collection<?>> data = new HashMap<>();
/* 83 */     data.put("events", Collections.singletonList(event));
/* 84 */     sendData(data);
/*    */   }
/*    */   
/*    */   private void sendData(Map<String, Collection<?>> data) {
/* 88 */     if (!data.isEmpty() && isConnected())
/*    */       try {
/* 90 */         getRemote().sendString(Context.getObjectMapper().writeValueAsString(data), null);
/* 91 */       } catch (JsonProcessingException e) {
/* 92 */         LOGGER.warn("Socket JSON formatting error", (Throwable)e);
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\api\AsyncSocket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */