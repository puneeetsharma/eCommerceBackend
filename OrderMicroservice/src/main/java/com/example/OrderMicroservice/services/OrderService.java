package com.example.OrderMicroservice.services;

import com.example.OrderMicroservice.entity.Orders;

import java.util.List;

public interface OrderService {
    public Orders addOrder(Orders orders);
    public String deleteOrder(String orderId);
    public List<Orders> findAll();
    public List<Orders> findByUserId(String userId);
}
