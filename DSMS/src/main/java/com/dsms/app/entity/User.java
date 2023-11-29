package com.dsms.app.entity;

import com.dsms.app.constants.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document("user")
public class User {

    @MongoId
    String userId;
    boolean isActive;
    Set<String> roles;
    String userFirstName;
    String userLastName;
    String userMiddleName;
    UserType userType;
    String userMailId;
    String userMobile;
    String userPassword;
    Instant userCreatedDate;
    ShoppingCart cart;
    List<Address> userAddress;
    List<CreditCard> cards;
    List<Order> orders;

}