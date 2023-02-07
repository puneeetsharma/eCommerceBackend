package com.example.MerchantMicroservice.repository.mongo;

import com.example.MerchantMicroservice.entity.Merchant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantRepository extends MongoRepository<Merchant, String> {
    boolean existsById(String id);
    public Optional<Merchant> findBymerchantEmail(String merchantEmail);
}
