package com.dsms.app.entity;

import com.dsms.app.constants.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Document
public class Order {

    @MongoId
    String id;
    String email;
    OrderStatus status;
    Payment payment;
    CouponCode code;
    List<CartItem> items;
    Address address;

}