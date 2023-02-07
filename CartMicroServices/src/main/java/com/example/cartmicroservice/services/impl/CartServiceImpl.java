package com.example.cartmicroservice.services.impl;

import com.example.cartmicroservice.dto.CartDto;
import com.example.cartmicroservice.entity.Cart;
import com.example.cartmicroservice.repository.mongo.CartRepository;
import com.example.cartmicroservice.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart addToCart(Cart cart){
        cartRepository.save(cart);
        //return "Product added to cart successfully";
        return cartRepository.save(cart);
    }

    @Override
    public String delCart(String cartItemId){
        cartRepository.deleteById(cartItemId);
        return "Product deleted";
    }

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public void deleteById(String userId) {
       cartRepository.deleteByCartItemId(userId);
    }

    @Override
    public List<Cart> findByProductIdUserId(String userId, String productId) {

        List<Cart> cart;
        cart = cartRepository.findByProductIdAndUserId(productId,userId);
        return cart;
    }

    @Override
    public boolean deleteByUserIdAndProductId(String userId, String productId) {
        cartRepository.deleteByUserIdAndProductId(userId,productId);


            return true;

    }

    public List<Cart> findByUserId(String userId){
        return cartRepository.findByUserId(userId);
    }

    @Override
    public Cart findByProductIdAndUserIdAndMerchantId(String productId, String userId, String merchantId) {
        return cartRepository.findByProductIdAndUserIdAndMerchantId(productId,userId,merchantId);
    }

    @Override
    public Cart findByProductIdAndMerchantId(String productId, String merchantId) {
        return cartRepository.findByProductIdAndMerchantId(productId,merchantId);
    }

    @Override
    public Cart findByCartItemId(String cartItemId) {
        return cartRepository.findByCartItemId(cartItemId);
    }

    @Override
    public Cart deleteByCartItemId(String cartItemId) {
        Cart cart = cartRepository.findByCartItemId(cartItemId);
        cart.setQuantity(cart.getQuantity()-1);
        cartRepository.save(cart);
        return cart;

    }


}
