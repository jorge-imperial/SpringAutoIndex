package com.ausfaller.examples.springautoindex;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
public class MongoConfig  {
    @Value("${mongodb.uri}")
    private  String uri;
    @Value("${mongodb.database}")
    private  String databaseName;

    public
    @Bean MongoClient mongoClient() {
        return MongoClients.create(uri);
    }

    @Bean
    MongoTemplate MongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, databaseName);
    }
}
