package com.Myrestaurant.Myrestaurant.Repositery;

import com.Myrestaurant.Myrestaurant.Entity.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<BillEntity, Long> {
    // You can add custom query methods here if needed
}
