package com.example.MailSender.dto;

import lombok.Data;

@Data
public class MerchantDto {
    String id;;
    String productId;
    double price;
    String merchantId;
    String merchantName;
    Integer stocks;
    Integer algoValue;
}
