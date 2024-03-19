package com.Myrestaurant.Myrestaurant.myoreder.controller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Myrestaurant.Myrestaurant.myoreder.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
