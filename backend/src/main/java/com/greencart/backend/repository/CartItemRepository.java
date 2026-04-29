package com.greencart.backend.repository;

import com.greencart.backend.model.CartItem;
import com.greencart.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
    Optional<CartItem> findByUserAndPlantId(User user, Long plantId);
    void deleteByUser(User user);
}
