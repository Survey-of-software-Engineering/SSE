package com.dsms.app.entity;

import com.dsms.app.constants.CouponStatus;
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
public class CouponCode {

    @MongoId
    String id;
    String code;
    Float minimumValue;
    Float amount;
    CouponStatus status;
    Instant expiryDate;
    Instant createdTime;
    Instant updatedTime;
}
