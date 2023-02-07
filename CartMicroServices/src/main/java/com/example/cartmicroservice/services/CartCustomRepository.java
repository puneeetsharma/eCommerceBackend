package com.example.cartmicroservice.services;

import com.example.cartmicroservice.entity.Cart;

public interface CartCustomRepository {
    public Cart findByUserIdAndProductIdAndMerchantId(String userId,String productId,String merchantId);
}
