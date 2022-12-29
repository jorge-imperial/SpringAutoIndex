package com.ausfaller.examples.springautoindex;


import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Random;

@Document(collection = "products")
@CompoundIndexes({
        @CompoundIndex(name = "index0", def = "{'id' : 1, 'name': 1}",background = true),
        @CompoundIndex(name = "index1", def = "{'name' : 1, 'qty': 1}",background = true),
        @CompoundIndex(name = "index2", def = "{'name' : 1, 'available': 1, 'unavailable': 1}",background = true)
})
public class Product {

    private static final Logger LOG = LoggerFactory.getLogger(Product.class);
    @Id
    private String id;
    private String name;
    private int qty;
    private double price;
    private Date available;
    private Date  unavailable;
    private String skuId;

    public Product(String name, int qty, double price, Date available, Date unavailable, String skuId) {
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.available = available;
        this.unavailable = unavailable;
        this.skuId = skuId;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getAvailable() {
        return available;
    }

    public void setAvailable(Date available) {
        this.available = available;
    }

    public Date getUnavailable() {
        return unavailable;
    }

    public void setUnavailable(Date unavailable) {
        this.unavailable = unavailable;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }
}
