package com.dsms.app.service;

import com.dsms.app.constants.CartItemStatus;
import com.dsms.app.constants.CouponStatus;
import com.dsms.app.constants.OrderStatus;
import com.dsms.app.constants.PickupType;
import com.dsms.app.entity.*;
import com.dsms.app.models.*;
import com.dsms.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.smartcardio.Card;
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

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    CreditCardRepository cardRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public List<DepartmentsResponse> getDepartmentsResponse() {
        List<Department> departments = departmentRepository.getAllDepartments();
        return departments.stream().map(
                department -> new DepartmentsResponse(department, department.getCategories().stream().map(Category::getItems).flatMap(List::stream).collect(Collectors.toList()))).collect(Collectors.toList());
    }

    public List<Department> getDepartments() {
        List<Department> departments = departmentRepository.getAllDepartments();
        return departments;
    }

    public List<Category> getCategories() {
        return categoryRepository.getAllCategories();
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
        return cartItems.stream().filter(item -> item.getStatus().equals(CartItemStatus.ACTIVE)).collect(Collectors.toList());
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
        if(user.getCart().getCartItems() == null) {
            return new ArrayList<String>();
        }
        return user.getCart().getCartItems().stream().filter(i -> i.getStatus().equals(CartItemStatus.ACTIVE)).map(i -> i.getItem().getItemId()).collect(Collectors.toList());
    }

    public Map<String, String> validateCoupon(ValidateCoupon coupon) {

        CouponCode couponCode = couponCodeRepository.getCouponCodeByCode(coupon.getCode());
        if(couponCode != null && couponCode.getExpiryDate().toEpochMilli() > Instant.now().toEpochMilli() && couponCode.getMinimumValue() <= coupon.getAmount()) {
            return Collections.singletonMap("status", "Coupon Code Applied !-" + couponCode.getAmount());
        }
        return Collections.singletonMap("status", "Invalid Coupon Code !");
    }

    public CouponCode getCouponCode(String code) {
        return couponCodeRepository.getCouponCodeByCode(code);
    }

    public Order placeOrder(PlaceOrder placeOrder) {

        Address address = placeOrder.getAddress();
        CreditCard card = placeOrder.getCreditCard();
        Checkout checkout = placeOrder.getCheckout();
        User user = placeOrder.getUser();
        User db_User = userRepository.getUserByUserId(user.getUserId());
        float total_amount = 0.0f;
        if(address.getId().equals("no_address")) {
            address.setId(null);
            addressRepository.save(address);
            db_User.setUserAddress(Arrays.asList(address));
        }
        if(card.getId().equals("no_card")) {
            card.setId(null);
            card.setValidity(AuthService.convertStrToInstant(placeOrder.getCardValidity()));
            cardRepository.save(card);
            db_User.setCards(Arrays.asList(card));
        }
        Order new_order = new Order();
        new_order.setAddress(address);
        new_order.setEmail(user.getUserMailId());
        ShoppingCart existing_cart = shoppingCartRepository.getShoppingCartById(db_User.getCart().getId());
        List<CartItem> cartItems = cartItemRepository.getCartItemsByIdIsIn(existing_cart.getCartItems().stream().filter(ci -> ci.getStatus().equals(CartItemStatus.ACTIVE)).map(ci -> ci.getId()).collect(Collectors.toList()));
        for(CartItem item : cartItems) {
            item.setStatus(CartItemStatus.INACTIVE);
            total_amount += item.getTotal();
            cartItemRepository.save(item);
        }
        existing_cart.setCartItems(cartItems);
        shoppingCartRepository.save(existing_cart);
        db_User.setCart(existing_cart);
        new_order.setItems(cartItems);
        new_order.setStatus(OrderStatus.PLACED);
        if(checkout.getCouponCode() != null) {
            CouponCode code = getCouponCode(checkout.getCouponCode());
            if(code != null) {
                total_amount -= code.getAmount();
                code.setStatus(CouponStatus.INACTIVE);
                couponCodeRepository.save(code);
                new_order.setCode(code);
            }
        }
        new_order.setPickupType(PickupType.STORE);
        if(! checkout.getPickupType().equals("store")) {
            total_amount -= 50.0;
            new_order.setPickupType(PickupType.HOME_DELIVERY);
        }
        Payment payment = new Payment();
        payment.setCard(card);
        payment.setStatus(true);
        payment.setTotalPrice(total_amount);
        paymentRepository.save(payment);
        new_order.setPayment(payment);
        orderRepository.save(new_order);
        if(db_User.getOrders() == null) {
            db_User.setOrders(Arrays.asList(new_order));
        } else {
            List<Order> existingOrders = db_User.getOrders();
            existingOrders.add(new_order);
            db_User.setOrders(existingOrders);
        }
        userRepository.save(db_User);
        return new_order;
    }

    public List<Order> getOrders(User user) {

        return user.getOrders();
    }

    public Order getOrderByOrderId(String orderId) {

        return orderRepository.getOrderById(orderId);
    }
}
