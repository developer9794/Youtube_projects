package com.Myrestaurant.Myrestaurant.Repositery;

import com.Myrestaurant.Myrestaurant.Entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositoryold extends JpaRepository<OrderEntity, Long> {
    // You can add custom query methods here if needed
}
