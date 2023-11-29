package com.dsms.app.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public class Payment {

    @MongoId
    String id;
    Float totalPrice;
    Boolean status;
    CreditCard card;
}
