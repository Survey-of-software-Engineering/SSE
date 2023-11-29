package com.dsms.app.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;

@Document
public class CouponCode {

    @MongoId
    String id;
    String code;
    Float minimumValue;
    Float amount;
    Instant validityDate;
    Instant expiryDate;
    Instant createdTime;
    Instant updatedTime;
}
