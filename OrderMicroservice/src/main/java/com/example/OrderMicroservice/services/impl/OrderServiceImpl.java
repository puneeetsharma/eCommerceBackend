package com.example.OrderMicroservice.services.impl;

import com.example.OrderMicroservice.repository.OrderRepository;
import com.example.OrderMicroservice.entity.Orders;
import com.example.OrderMicroservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public Orders addOrder(Orders order) {
        return orderRepository.save(order);

    }

    @Override
    public String deleteOrder(String orderId) {
        orderRepository.deleteById(orderId);
        return "Order deleted";
    }

    @Override
    public List<Orders> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Orders> findByUserId(String userId) {

        List<Orders> orderList =orderRepository.findByUserId(userId);

        Collections.reverse(orderList);
        return orderList;
    }

}
