package com.Myrestaurant.Myrestaurant.Repositery;

import com.Myrestaurant.Myrestaurant.Entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
    // You can add custom query methods here if needed
}
