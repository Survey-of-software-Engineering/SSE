package com.dsms.app.models;

import com.dsms.app.entity.Address;
import com.dsms.app.entity.CreditCard;
import com.dsms.app.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterUser {

    User user;
    Address address;
    CreditCard card;
    String cardValidity;
}
