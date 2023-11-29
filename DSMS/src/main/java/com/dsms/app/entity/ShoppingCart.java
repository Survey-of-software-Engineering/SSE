package com.dsms.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Document
public class ShoppingCart {

    @MongoId
    String id;
    List<CartItem> cartItems;
    Instant createdTime;
    Instant updatedTime;

}
