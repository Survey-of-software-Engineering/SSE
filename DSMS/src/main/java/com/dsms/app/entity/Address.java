package com.dsms.app.entity;

import com.dsms.app.constants.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import com.dsms.app.constants.State;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Document
public class Address {

    @MongoId
    String id;
    String address;
    String landmark;
    State state;
    Country country;
    String zipCode;
    String mobile;
    Instant createdTime;
    Instant updatedTime;
}
