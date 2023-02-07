package com.example.cartmicroservice.services;

import com.example.cartmicroservice.entity.Cart;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CartService {
    public Cart addToCart(Cart cart);
    public String delCart(String cartItemId);

    public List<Cart> findAll();

    public void deleteById(String cartItemId);

    public List<Cart> findByProductIdUserId(String cartItemId,String productId);

    public boolean deleteByUserIdAndProductId(String userId,String productId);


    public List<Cart> findByUserId(String userId);
    public Cart findByProductIdAndUserIdAndMerchantId(String productId, String userId,String merchantId);
    public Cart findByProductIdAndMerchantId(String productId, String merchantId);
    public Cart findByCartItemId(String cartItemId);
    public Cart deleteByCartItemId(String cartItemId);


    }
