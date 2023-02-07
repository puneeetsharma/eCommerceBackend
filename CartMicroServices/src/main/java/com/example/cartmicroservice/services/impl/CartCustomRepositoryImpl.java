package com.example.cartmicroservice.services.impl;

import com.example.cartmicroservice.entity.Cart;
import com.example.cartmicroservice.services.CartCustomRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class CartCustomRepositoryImpl implements CartCustomRepository {
    @Override
    public Cart findByUserIdAndProductIdAndMerchantId(String userId, String productId, String merchantId) {
        Query query =new Query();
        Criteria criteria = new Criteria();
        Cart cart = new Cart();
        return cart;


    }
}
