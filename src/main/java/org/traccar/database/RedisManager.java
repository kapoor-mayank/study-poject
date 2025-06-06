package org.traccar.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.traccar.Context;
import org.traccar.model.Device;
import org.traccar.model.Position;
import redis.clients.jedis.Jedis;


public class RedisManager {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisManager.class);

    public RedisManager() {
        try (Jedis jedis = new Jedis(Context.getConfig().getString("redis.database"))) {
            for (String key : jedis.keys("connected.*")) {
                jedis.del(key);
            }
        }
    }

    public void writePosition(Position position) throws JsonProcessingException {
//        LOGGER.info("RedisManager writePosition");
        String key = "positions." + position.getUniqueId();
        String value = this.objectMapper.writeValueAsString(position);
        try (Jedis jedis = new Jedis(Context.getConfig().getString("redis.database"))) {
            jedis.lpush(key, new String[]{value});
        }
    }

    public void addDevice(Device device) {
//        LOGGER.info("RedisManager addDevice");
        try (Jedis jedis = new Jedis(Context.getConfig().getString("redis.database"))) {
            jedis.setnx("connected." + device.getUniqueId(), String.valueOf((new Date()).getTime()));
        }
    }

    public void removeDevice(Device device) {
        LOGGER.info("RedisManager removeDevice");
        try (Jedis jedis = new Jedis(Context.getConfig().getString("redis.database"))) {
            jedis.del("connected." + device.getUniqueId());
        }
    }

    public String getDeviceModel(String uniqueId) {
        LOGGER.info("RedisManager getDeviceModel");
        try (Jedis jedis = new Jedis(Context.getConfig().getString("redis.database"))) {
            return jedis.get("model." + uniqueId);
        }
    }
}


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\database\RedisManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */