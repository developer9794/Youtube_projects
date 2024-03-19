package com.Myrestaurant.Myrestaurant.myoreder.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.Myrestaurant.Myrestaurant.myoreder.Order;
import com.google.zxing.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            return orderOptional.get();
        } else {
        	  return orderOptional.get();
//            throw new NotFoundException("Order not found with id: " + id);
        }
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        Order existingOrder = getOrderById(id);
        // Update fields of the existingOrder with values from updatedOrder
        // You can implement this logic based on your requirements
        // For example: existingOrder.setMenu(updatedOrder.getMenu());
        return orderRepository.save(existingOrder);
    }

    public boolean deleteOrder(Long id) {
        try {
            orderRepository.deleteById(id);
            return true; // Deletion was successful
        } catch (EmptyResultDataAccessException e) {
            return false; // Order with the given id was not found
        }
    }

}
