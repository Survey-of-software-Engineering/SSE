package com.dsms.app.entity;

import com.dsms.app.constants.CartItemStatus;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;

@Document
public class CartItem {

    @MongoId
    String id;
    Item item;
    Float total;
    Integer quantity;
    CartItemStatus status;
    Instant createdTime;
    Instant updatedTime;
}
