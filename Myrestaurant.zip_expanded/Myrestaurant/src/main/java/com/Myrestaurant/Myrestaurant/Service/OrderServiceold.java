package com.Myrestaurant.Myrestaurant.Service;


import com.Myrestaurant.Myrestaurant.Entity.OrderEntity;
import com.Myrestaurant.Myrestaurant.Repositery.OrderRepositoryold;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceold   {

    private final OrderRepositoryold orderRepository;

    @Autowired
    public OrderServiceold(OrderRepositoryold orderRepository) {
        this.orderRepository = orderRepository;
    }

  
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderEntity getOrderById(Long id) {
        Optional<OrderEntity> optionalOrder = orderRepository.findById(id);
        return optionalOrder.orElse(null);
    }

    public OrderEntity createOrder(OrderEntity order) {
        return orderRepository.save(order);
    }

    public OrderEntity updateOrder(Long id, OrderEntity order) {
        if (orderRepository.existsById(id)) {
            order.setOrdId(id); // Ensure the correct ID is set
            return orderRepository.save(order);
        } else {
            return null;
        }
    }


    public boolean deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
