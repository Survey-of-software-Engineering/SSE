package com.dsms.app.repository;

import com.dsms.app.entity.CartItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends MongoRepository<CartItem, String> {

    CartItem getCartItemById(String id);

    List<CartItem> getCartItemsByIdIsIn(List<String> ids);
}