package com.dsms.app.service;

import com.dsms.app.entity.Address;
import com.dsms.app.entity.CreditCard;
import com.dsms.app.entity.ShoppingCart;
import com.dsms.app.entity.User;
import com.dsms.app.models.RegisterUser;
import com.dsms.app.repository.AddressRepository;
import com.dsms.app.repository.CreditCardRepository;
import com.dsms.app.repository.ShoppingCartRepository;
import com.dsms.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    public User registerUser(RegisterUser registerUser) {

        User user = userRepository.getUserByUserMailId(registerUser.getUser().getUserMailId());
        if (user == null) {
            User new_user = registerUser.getUser();
            Address address = registerUser.getAddress();
            address.setCreatedTime(Instant.now());
            address.setUpdatedTime(Instant.now());
            CreditCard card = registerUser.getCard();
            card.setValidity(convertStrToInstant(registerUser.getCardValidity()));
            card.setCreatedTime(Instant.now());
            card.setUpdatedTime(Instant.now());
            addressRepository.save(address);
            creditCardRepository.save(card);
            new_user.setUserAddress(Arrays.asList(address));
            new_user.setCards(Arrays.asList(card));
            ShoppingCart cart = new ShoppingCart();
            shoppingCartRepository.save(cart);
            new_user.setCart(cart);
            userRepository.save(new_user);
        }
        return user;
    }

    public User getCurrentUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return getUserByEmailId(authentication.getName());
    }

    User getUserByEmailId(String mailId) {
        return userRepository.getUserByUserMailId(mailId);
    }

    Instant convertStrToInstant(String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        YearMonth yearMonth = YearMonth.parse(date, formatter);

        return yearMonth.atDay(1).atStartOfDay(ZoneOffset.UTC).toInstant();
    }
}
