package com.dsms.app.service;

import com.dsms.app.constants.CartItemStatus;
import com.dsms.app.entity.*;
import com.dsms.app.models.AddItemToCart;
import com.dsms.app.models.DepartmentsResponse;
import com.dsms.app.models.UpdateItemQuantity;
import com.dsms.app.models.ValidateCoupon;
import com.dsms.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CouponCodeRepository couponCodeRepository;

    public List<DepartmentsResponse> getDepartmentsResponse() {
        List<Department> departments = departmentRepository.getAllDepartments();
        return departments.stream().map(
                department -> new DepartmentsResponse(department, department.getCategories().stream().map(Category::getItems).flatMap(List::stream).collect(Collectors.toList()))).collect(Collectors.toList());
    }

    public CartItem addItemToShoppingCart(AddItemToCart cartItem, User user) {
        ShoppingCart shoppingCart = user.getCart();
        List<CartItem> existing_items = new ArrayList<>();
        if(shoppingCart.getCartItems() != null) {
            existing_items = shoppingCart.getCartItems();
        }
        CartItem ret_item;
        List<CartItem> cartItems = existing_items.stream().filter(item -> item.getItem().getItemId().equals(cartItem.getItemId()) && item.getStatus().equals(CartItemStatus.ACTIVE)).collect(Collectors.toList());
        if(cartItems.size() == 1) {
            ret_item = cartItems.get(0);
            ret_item.setQuantity(cartItem.getQuantity());
            cartItemRepository.save(ret_item);
        } else {
            Item item = itemRepository.getItemByItemId(cartItem.getItemId());
            CartItem new_item = new CartItem();
            new_item.setQuantity(cartItem.getQuantity());
            new_item.setStatus(CartItemStatus.ACTIVE);
            new_item.setItem(item);
            new_item.setCreatedTime(Instant.now());
            new_item.setUpdatedTime(Instant.now());
            new_item.setTotal(item.getItemPrice() * cartItem.getQuantity());
            cartItemRepository.save(new_item);
            List<CartItem> items_list = new ArrayList<>();
            if(shoppingCart.getCartItems() != null) {
                items_list = shoppingCart.getCartItems();
            }
            items_list.add(new_item);
            shoppingCart.setCartItems(items_list);
            ret_item = new_item;
        }
        shoppingCartRepository.save(shoppingCart);
        user.setCart(shoppingCart);
        userRepository.save(user);
        return ret_item;
    }

    public List<CartItem> getCartItems(User user) {

        ShoppingCart cart = user.getCart();
        List<CartItem> cartItems = cart.getCartItems();
        if(cartItems == null) {
            return new ArrayList<CartItem>();
        }
        return cartItems.stream().filter(item -> item.getStatus() == CartItemStatus.ACTIVE).collect(Collectors.toList());
    }

    public Map<String, String> removeItemFromCart(User user, String cartItemId) {
        try {
            ShoppingCart cart = user.getCart();
            ShoppingCart cart1 = shoppingCartRepository.getShoppingCartById(cart.getId());
            List<CartItem> item = cart1.getCartItems().stream()
                    .filter(i -> i.getStatus().equals(CartItemStatus.ACTIVE) && i.getId().equals(cartItemId))
                    .collect(Collectors.toList());
            List<CartItem> existingItems = cart1.getCartItems();
            cartItemRepository.deleteById(item.get(0).getId());
            existingItems.remove(item.get(0));
            shoppingCartRepository.save(cart1);
            user.setCart(cart1);
            userRepository.save(user);
            return Collections.singletonMap("status", "Success");
        } catch (Exception ex) {
            return Collections.singletonMap("status", ex.getMessage() + ex.getStackTrace().toString());
        }
    }

    public CartItem updateItemQuantity(UpdateItemQuantity item, User user) {

        CartItem cartItem = cartItemRepository.getCartItemById(item.getItemId());
        cartItem.setQuantity(item.getQuantity());
        cartItem.setTotal(cartItem.getItem().getItemPrice() * item.getQuantity());
        cartItemRepository.save(cartItem);
        ShoppingCart cart = user.getCart();
        List<CartItem> cartItems = cart.getCartItems();
        for(CartItem ci : cartItems) {
            if(ci.getId().equals(item.getItemId())) {
                ci.setQuantity(item.getQuantity());
                ci.setTotal(ci.getItem().getItemPrice() * item.getQuantity());
                break;
            }
        }
        shoppingCartRepository.save(cart);
        user.setCart(cart);
        userRepository.save(user);
        return cartItem;
    }

    public List<String> getCartItemsIds(User user) {

        return user.getCart().getCartItems().stream().map(i -> i.getItem().getItemId()).collect(Collectors.toList());
    }

    public Map<String, String> validateCoupon(ValidateCoupon coupon) {

        CouponCode couponCode = couponCodeRepository.getCouponCodeByCode(coupon.getCode());
        if(couponCode != null && couponCode.getExpiryDate().toEpochMilli() > Instant.now().toEpochMilli() && couponCode.getMinimumValue() <= coupon.getAmount()) {
            return Collections.singletonMap("status", "Coupon Code Applied !-" + couponCode.getAmount());
        }
        return Collections.singletonMap("status", "Invalid Coupon Code !");
    }


}
