package com.example.OrderMicroservice.repository;

import com.example.OrderMicroservice.entity.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Orders, String> {
    public List<Orders> findByUserId(String userId);
}
