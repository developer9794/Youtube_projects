package com.Myrestaurant.Myrestaurant.myoreder.controller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Myrestaurant.Myrestaurant.myoreder.Tableorders;

@Repository
public interface TableordersRepository extends JpaRepository<Tableorders, Long> {
    // You can add custom query methods here if needed
}
