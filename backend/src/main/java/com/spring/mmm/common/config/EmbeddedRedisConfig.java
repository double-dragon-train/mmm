package com.spring.mmm.common.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.embedded.RedisServer;
import redis.embedded.exceptions.EmbeddedRedisException;

import java.io.IOException;

@Slf4j
@Profile({"local", "test"})
@Configuration
public class EmbeddedRedisConfig {
    private RedisServer redisServer;

    public EmbeddedRedisConfig(@Value("${spring.redis.port}") int port) throws IOException {
        this.redisServer = new RedisServer(port);
    }

    @PostConstruct
    public void startRedis() {
        try {
            this.redisServer.start();
        } catch (EmbeddedRedisException e) {
            log.error("error occurred!! : {}", e.getMessage());
        }
    }

    @PreDestroy
    public void stopRedis() {
        this.redisServer.stop();
    }
}
