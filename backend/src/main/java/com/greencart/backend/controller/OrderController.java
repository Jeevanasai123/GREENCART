package com.greencart.backend.controller;

import com.greencart.backend.model.CartItem;
import com.greencart.backend.model.Order;
import com.greencart.backend.model.OrderItem;
import com.greencart.backend.model.User;
import com.greencart.backend.repository.CartItemRepository;
import com.greencart.backend.repository.OrderRepository;
import com.greencart.backend.repository.UserRepository;
import com.greencart.backend.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    private User getCurrentUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findById(userDetails.getId()).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PostMapping("/place")
    @Transactional
    public ResponseEntity<?> placeOrder() {
        User user = getCurrentUser();
        List<CartItem> cartItems = cartItemRepository.findByUser(user);

        if (cartItems.isEmpty()) {
            return ResponseEntity.badRequest().body("Cart is empty");
        }

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setStatus("COMPLETED");

        double totalAmount = 0;
        List<OrderItem> orderItems = cartItems.stream().map(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setPlant(cartItem.getPlant());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPlant().getPrice());
            return orderItem;
        }).collect(Collectors.toList());

        for (OrderItem item : orderItems) {
            totalAmount += item.getPrice() * item.getQuantity();
        }

        order.setTotalAmount(totalAmount);
        order.setItems(orderItems);

        orderRepository.save(order);
        cartItemRepository.deleteByUser(user); // Clear cart

        return ResponseEntity.ok("Order placed successfully");
    }

    @GetMapping("/history")
    public List<Order> getOrderHistory() {
        User user = getCurrentUser();
        return orderRepository.findByUserOrderByOrderDateDesc(user);
    }
}
