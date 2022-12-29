package com.ausfaller.examples.springautoindex;

import org.springframework.data.repository.ListCrudRepository;


public interface ProductsRepository extends ListCrudRepository<Product, String> {

}
