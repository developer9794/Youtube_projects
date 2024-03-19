package com.Myrestaurant.Myrestaurant.Repositery;

import com.Myrestaurant.Myrestaurant.Entity.CookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CookRepository extends JpaRepository<CookEntity, Long> {
    // You can add custom query methods here if needed
}
