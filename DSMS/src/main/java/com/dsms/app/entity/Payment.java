package com.dsms.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Document
public class Payment {

    @MongoId
    String id;
    Float totalPrice;
    Boolean status;
    CreditCard card;
}
