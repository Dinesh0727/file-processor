package com.tanla.ticket.file_processor.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

@Configuration
public class Config {
    @Value("${spring.data.redis.url}")
    String connectionString;

    @Bean
    JedisPool jedisPool() {
        JedisPool jedisPool = new JedisPool(this.connectionString);
        return jedisPool;
    }
}
