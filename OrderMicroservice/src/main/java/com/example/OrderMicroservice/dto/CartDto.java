package com.example.OrderMicroservice.dto;

import lombok.Data;

@Data
public class CartDto {
    private String cartItemId;
    private String userId;
    private String productId;
    private String productName;
    private String imageUrl;
    private double price;
    private double rating;
    private Integer quantity;


    //update
    private String merchantId;
    private String merchantName;
}
