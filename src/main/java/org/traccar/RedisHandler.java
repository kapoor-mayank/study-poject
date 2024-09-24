/*    */ package org.traccar;
/*    */ 
/*    */ import com.fasterxml.jackson.core.JsonProcessingException;
/*    */ import java.util.HashSet;
/*    */ import java.util.Objects;
/*    */ import java.util.Set;
/*    */ import org.apache.commons.lang3.builder.EqualsBuilder;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.traccar.model.Position;
/*    */ 
/*    */ public class RedisHandler
/*    */   extends BaseDataHandler
/*    */ {
/* 15 */   private static final Logger LOGGER = LoggerFactory.getLogger(RedisHandler.class);
/*    */   
/*    */   private boolean duplicatePositions(Position p1, Position p2) {
/* 18 */     if (EqualsBuilder.reflectionEquals(p1, p2, new String[] { "id", "serverTime", "attributes" })) {
/* 19 */       Set<String> keys = new HashSet<>();
/* 20 */       keys.addAll(p1.getAttributes().keySet());
/* 21 */       keys.addAll(p2.getAttributes().keySet());
/* 22 */       for (String key : keys) {
/* 23 */         if (!key.equals("index") && !key.equals("sequence") && 
/* 24 */           !Objects.equals(p1.getAttributes().get(key), p2.getAttributes().get(key))) {
/* 25 */           return false;
/*    */         }
/*    */       } 
/*    */       
/* 29 */       return true;
/*    */     } 
/* 31 */     return false;
/*    */   }
/*    */   
/*    */   private boolean frequentPositions(Position p1, Position p2) {
/* 35 */     return (p1 != null && p2 != null && 
/* 36 */       Math.abs(p2.getServerTime().getTime() - p1.getServerTime().getTime()) < 60000L);
/*    */   }
/*    */ 
/*    */   
/*    */   protected Position handlePosition(Position position) {
/*    */     try {
/* 42 */       Context.getRedisManager().writePosition(position);
/* 43 */     } catch (JsonProcessingException e) {
/* 44 */       LOGGER.warn("Redis error", (Throwable)e);
/*    */     } 
/* 46 */     return position;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\RedisHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */