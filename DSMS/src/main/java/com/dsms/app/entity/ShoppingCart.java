package com.dsms.app.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;
import java.util.List;

@Document
public class ShoppingCart {

    @MongoId
    String id;
    List<CartItem> cartItems;
    Instant createdTime;
    Instant updatedTime;

}
