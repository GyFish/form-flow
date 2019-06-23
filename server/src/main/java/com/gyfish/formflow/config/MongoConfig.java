package com.gyfish.formflow.config;

import com.mongodb.MongoClientOptions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author geyu
 */
@Configuration
public class MongoConfig {

    @Bean
    public MongoClientOptions mongoOptions() {
        return MongoClientOptions
                .builder()
                .maxConnectionIdleTime(7 * 24 * 60 * 60 * 1000)
                .build();
    }

}
