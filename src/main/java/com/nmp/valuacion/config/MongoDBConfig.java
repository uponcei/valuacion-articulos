package com.nmp.valuacion.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @name MongoDBConfig
 * @description Clase de configuracion de mongodb
 *
 * @author Uriel P. Ibarra
 * @version 0.1
 */

@Configuration
public class MongoDBConfig {

    @Value("${spring.data.mongodb.uri}")
    private String uri;

    @Value("${spring.data.mongodb.database}")
    private String dbName;
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(uri);
    }
    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), getDatabaseName());
    }

    protected String getDatabaseName() {
        return dbName;
    }
}
