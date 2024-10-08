package com.tanla.ticket.file_processor.redis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RestController
@RequestMapping("/api/cleanup")
public class CleanUpService {

    @Autowired
    private JedisPool jedisPool;

    @GetMapping("/trigger")
    public String cleanUpRedis() {
        String cleanUpKeys = "CLEAN_UP_KEYS";
        String messages = "Messages";

        try (Jedis jedis = this.jedisPool.getResource()) {
            Long timeStamp = System.currentTimeMillis() / 1000;
            List<String> expiredKeys = jedis.zrangeByScore(cleanUpKeys, 0, timeStamp);
            System.out.println(expiredKeys.toString());
            for (String expiredKey : expiredKeys) {
                jedis.hdel(messages, expiredKey);
                jedis.zrem(cleanUpKeys, expiredKey);
            }
        }
        return "Redis Cleanup function called";
    }

}
