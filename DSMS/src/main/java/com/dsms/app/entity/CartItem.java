package com.dsms.app.entity;

import com.dsms.app.constants.CartItemStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
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
