package com.Myrestaurant.Myrestaurant.Repositery;

import com.Myrestaurant.Myrestaurant.Entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    // You can add custom query methods here if needed
}
