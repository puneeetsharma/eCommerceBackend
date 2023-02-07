package com.example.MerchantMicroservice.services;

import com.example.MerchantMicroservice.entity.Merchant;

import java.util.List;
import java.util.Optional;

public interface MerchantService {
    public String addMerchant(Merchant merchant);
    public String delMerchant(String merchantId);
    public boolean existsById(String id);

    public List<Merchant> findAll();
    public List<Merchant> deleteById(String merchantId);

    boolean login(String email,String password);
    boolean signup(Merchant user);
    public Optional<Merchant> findBymerchantEmail(String userEmail);
}
