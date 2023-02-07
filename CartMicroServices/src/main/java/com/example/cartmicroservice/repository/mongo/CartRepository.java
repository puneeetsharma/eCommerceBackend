package com.example.cartmicroservice.repository.mongo;

import com.example.cartmicroservice.entity.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {
    public List<Cart> findByProductIdAndUserId(String productId, String userId);
    public List<Cart> findByUserId(String userId);
    public void deleteByCartItemId(String cartItemId);
    public void deleteByUserIdAndProductId(String userId,String productId);
    public Cart findByProductIdAndUserIdAndMerchantId(String productId, String userId,String merchantId);
    public Cart findByProductIdAndMerchantId(String productId, String merchantId);
    public Cart findByCartItemId(String cartItemId);
    }
