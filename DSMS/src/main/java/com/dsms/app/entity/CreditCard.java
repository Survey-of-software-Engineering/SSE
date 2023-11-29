package com.dsms.app.entity;

import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;
import java.util.Date;

public class CreditCard {

    @MongoId
    String id;
    String number;
    Date validity;
    String cvv;
    String name;
    Instant createdTime;
    Instant updatedTime;
}
