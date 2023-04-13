package com.ingeniods.databasereader.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
public class RedisConfig {

    @Value("${redis.host}")
    private String redisHost;

    @Bean
    public Jedis jedis() {
        return new Jedis(redisHost);
    }
}
