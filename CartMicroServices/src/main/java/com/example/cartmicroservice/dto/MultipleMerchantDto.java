package com.example.cartmicroservice.dto;

import lombok.Data;

@Data
public class MultipleMerchantDto {

    String id;
    String productId;
    double price;
    String merchantId;
    String merchantName;
    Integer stocks;
    Integer algoValue;
}
