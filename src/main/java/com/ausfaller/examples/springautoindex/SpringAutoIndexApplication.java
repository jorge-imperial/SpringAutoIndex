package com.ausfaller.examples.springautoindex;

import com.github.javafaker.Faker;
import com.mongodb.client.MongoClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

@SpringBootApplication
public class SpringAutoIndexApplication implements CommandLineRunner {

    @Autowired
    private  ProductsRepository repository;

    private static final Logger LOG = LoggerFactory.getLogger(SpringAutoIndexApplication.class);
    public static void main(String[] args) {

        // Drop collection and indexes. Get names from application.properties file.
        try (InputStream input = SpringAutoIndexApplication.class.getClassLoader().getResourceAsStream("application.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                LOG.error("Sorry, unable to find config.properties");
                return;
            }

            //load a properties file from class path, inside static method
            prop.load(input);
            String uri = prop.getProperty("spring.data.mongodb.uri");
            String dbname = prop.getProperty("spring.data.mongodb.database");

            MongoOperations mongoOps = new MongoTemplate(MongoClients.create(uri), dbname);
            mongoOps.dropCollection("products");
            if ( mongoOps.collectionExists("products")) LOG.info("Collection dropped successfully.");

        } catch (IOException ex) {
            ex.printStackTrace();
        }




        SpringApplication.run(SpringAutoIndexApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("Start");

        Faker faker = new Faker();
        Random rand = new Random();

        repository.deleteAll();

        for (int i=0; i<50; ++i) {

            Product p = new Product(  faker.animal().name(),
                    1+rand.nextInt(998),
                    10.0+rand.nextInt(9999),
                    new Date(), new Date(),
                    faker.idNumber().valid());

            repository.save(p);
        }

        Iterable<Product> documents = repository.findAll();
        LOG.info("Document count is ", documents);

        LOG.info("End.");
    }
}
