/*    */ package org.traccar.database;
/*    */ 
/*    */ import com.fasterxml.jackson.core.JsonProcessingException;
/*    */ import com.fasterxml.jackson.databind.ObjectMapper;
/*    */ import java.util.Date;
/*    */ import org.traccar.Context;
/*    */ import org.traccar.model.Device;
/*    */ import org.traccar.model.Position;
/*    */ import redis.clients.jedis.Jedis;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RedisManager
/*    */ {
/* 16 */   private final ObjectMapper objectMapper = new ObjectMapper(); public RedisManager() {
/* 17 */     try (Jedis jedis = new Jedis(Context.getConfig().getString("redis.database"))) {
/* 18 */       for (String key : jedis.keys("connected.*")) {
/* 19 */         jedis.del(key);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public void writePosition(Position position) throws JsonProcessingException {
/* 25 */     String key = "positions." + position.getUniqueId();
/* 26 */     String value = this.objectMapper.writeValueAsString(position);
/* 27 */     try (Jedis jedis = new Jedis(Context.getConfig().getString("redis.database"))) {
/* 28 */       jedis.lpush(key, new String[] { value });
/*    */     } 
/*    */   }
/*    */   
/*    */   public void addDevice(Device device) {
/* 33 */     try (Jedis jedis = new Jedis(Context.getConfig().getString("redis.database"))) {
/* 34 */       jedis.setnx("connected." + device.getUniqueId(), String.valueOf((new Date()).getTime()));
/*    */     } 
/*    */   }
/*    */   
/*    */   public void removeDevice(Device device) {
/* 39 */     try (Jedis jedis = new Jedis(Context.getConfig().getString("redis.database"))) {
/* 40 */       jedis.del("connected." + device.getUniqueId());
/*    */     } 
/*    */   }
/*    */   
/*    */   public String getDeviceModel(String uniqueId) {
/* 45 */     try (Jedis jedis = new Jedis(Context.getConfig().getString("redis.database"))) {
/* 46 */       return jedis.get("model." + uniqueId);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\database\RedisManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */