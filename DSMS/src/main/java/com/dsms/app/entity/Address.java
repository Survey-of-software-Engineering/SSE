package com.dsms.app.entity;

import com.dsms.app.constants.Country;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import com.dsms.app.constants.State;

import java.time.Instant;

@Document
public class Address {

    @MongoId
    String id;
    String address;
    String landmark;
    State state;
    Country country;
    String zipCode;
    Instant createdTime;
    Instant updatedTime;
}
