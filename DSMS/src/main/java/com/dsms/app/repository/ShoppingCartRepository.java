package com.dsms.app.repository;

import com.dsms.app.entity.ShoppingCart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, String> {

}