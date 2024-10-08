package com.tanla.ticket.file_processor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanla.ticket.file_processor.model.Message;
import com.tanla.ticket.file_processor.service.MessageService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/redis")
public class RedisController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private JedisPool jedisPool;

    @GetMapping("/trigger")
    public String callRedisService() throws InterruptedException {
        List<Message> messages = messageService.getAllMessages();

        String cleanupSet = "CLEAN_UP_KEYS";
        String messageHash = "Messages";

        // Insert into Redis
        try (Jedis jedis = this.jedisPool.getResource()) {
            for (int i = 0; i < messages.size(); i++) {
                Thread.sleep(100);
                String text = messages.get(i).toString();
                jedis.hset(messageHash, String.valueOf(messages.get(i).getId()), text);
                jedis.zadd(cleanupSet, System.currentTimeMillis() / 1000 + 60 * i,
                        String.valueOf(messages.get(i).getId()));
            }
        }

        return "Called the Redis Service Function";
    }

    @Scheduled(fixedDelay = 20000, initialDelay = 30000)
    public void scheduledRedisCleanUp() throws InterruptedException {
        System.out.println(this.callRedisService());
    }

}
