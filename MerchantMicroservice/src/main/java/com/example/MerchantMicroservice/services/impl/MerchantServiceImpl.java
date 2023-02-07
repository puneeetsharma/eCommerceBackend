package com.example.MerchantMicroservice.services.impl;

import com.example.MerchantMicroservice.entity.Merchant;
import com.example.MerchantMicroservice.repository.mongo.MerchantRepository;
import com.example.MerchantMicroservice.services.MerchantService;
import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    public String addMerchant(Merchant merchant){
        merchantRepository.save(merchant);
        return "Merchant added successfully";
    }

    @Override
    public String delMerchant(String merchantId){
        merchantRepository.deleteById(merchantId);
        return "Merchant deleted";
    }

    @Override
    public boolean login(String email, String password) {

//        List<UserEntity> userEntities = userRepository.findByEmail(email);
//        if (userEntities.size() != 0) {
//            if (userEntities.get(4).getPassword().equals(password)) {
//                return true;
//            }
//        }
        Optional<Merchant> merchant = merchantRepository.findBymerchantEmail(email);
        if(merchant.isPresent())
        {
            Merchant merchant1= new Merchant();
            merchant1= merchant.get();
            if(merchant1.getMerchantEmail().equals(email) && merchant1.getMerchantPassword().equals(password))
            {
                return true;
            }

        }
        return false;
    }

    @Override
    public Optional<Merchant> findBymerchantEmail(String merchantEmail) {
        return merchantRepository.findBymerchantEmail(merchantEmail);
    }

    @Override
    public boolean signup(Merchant merchant) {


        Merchant merchantEntity = new Merchant();
        merchantEntity.setMerchantName(merchant.getMerchantName());
        merchantEntity.setMerchantEmail(merchant.getMerchantEmail());
        merchantEntity.setMerchantId(merchant.getMerchantId());
        merchantEntity.setMerchantPassword(merchant.getMerchantPassword());


        Optional<Merchant> merchantEntities = merchantRepository.findById(merchant.getMerchantId());
        //List<User> userEntities1 = userDetailsRepository.findById(user.getUserName());
        if (merchantEntities.isPresent() == false) {
            merchantRepository.save(merchantEntity);
            return true;
        }
        return false;
    }

    @Override
    public boolean existsById(String id) {
        return merchantRepository.existsById(id);
    }

    @Override
    public List<Merchant> findAll(){
        return merchantRepository.findAll();
    }

    @Override
    public List<Merchant> deleteById(String merchantId){
        return null;
    }
}
