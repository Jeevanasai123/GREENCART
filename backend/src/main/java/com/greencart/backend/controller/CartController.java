package com.greencart.backend.controller;

import com.greencart.backend.model.CartItem;
import com.greencart.backend.model.Plant;
import com.greencart.backend.model.User;
import com.greencart.backend.repository.CartItemRepository;
import com.greencart.backend.repository.PlantRepository;
import com.greencart.backend.repository.UserRepository;
import com.greencart.backend.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private UserRepository userRepository;

    private User getCurrentUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findById(userDetails.getId()).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @GetMapping
    public List<CartItem> getCartItems() {
        User user = getCurrentUser();
        return cartItemRepository.findByUser(user);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestParam Long plantId, @RequestParam(defaultValue = "1") Integer quantity) {
        User user = getCurrentUser();
        Plant plant = plantRepository.findById(plantId).orElseThrow(() -> new RuntimeException("Plant not found"));

        Optional<CartItem> existingItem = cartItemRepository.findByUserAndPlantId(user, plantId);
        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            cartItemRepository.save(item);
        } else {
            CartItem item = new CartItem();
            item.setUser(user);
            item.setPlant(plant);
            item.setQuantity(quantity);
            cartItemRepository.save(item);
        }
        return ResponseEntity.ok("Added to cart");
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeFromCart(@PathVariable Long id) {
        cartItemRepository.deleteById(id);
        return ResponseEntity.ok("Removed from cart");
    }
}
