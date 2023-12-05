package com.dsms.app.service;

import com.dsms.app.constants.UserType;
import com.dsms.app.entity.*;
import com.dsms.app.models.RegisterUser;
import com.dsms.app.repository.AddressRepository;
import com.dsms.app.repository.CreditCardRepository;
import com.dsms.app.repository.ShoppingCartRepository;
import com.dsms.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    @Autowired
    PasswordEncoder passwordEncoder;

    public User registerUser(RegisterUser registerUser) {

        User user = userRepository.getUserByUserMailId(registerUser.getUser().getUserMailId());
        if (user == null) {
            User new_user = registerUser.getUser();
            new_user.setUserType(UserType.USER);
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

    public static Instant convertStrToInstant(String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        YearMonth yearMonth = YearMonth.parse(date, formatter);

        return yearMonth.atDay(1).atStartOfDay(ZoneOffset.UTC).toInstant();
    }

    public User newGuestUser() {
        User new_user = new User();
        UUID uuid = UUID.randomUUID();
        String uid = uuid.toString();
        Set<String> roles = new HashSet<>();
        roles.add("USER");
        roles.add("GUEST");
        new_user.setRoles(roles);
        new_user.setUserType(UserType.GUEST);
        new_user.setUserMailId(uid+ "@guest");
        new_user.setUserPassword(passwordEncoder.encode(uid));
        new_user.setUserCreatedDate(Instant.now());
        new_user.setCart(new ShoppingCart());
        userRepository.save(new_user);
        return new_user;
    }
}
