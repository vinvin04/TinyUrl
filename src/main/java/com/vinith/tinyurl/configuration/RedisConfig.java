package com.vinith.tinyurl.configuration;

import io.rebloom.client.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

    @Bean
    public Client redisBloomClient() {
        return new Client("localhost", 6379);
    }
}
