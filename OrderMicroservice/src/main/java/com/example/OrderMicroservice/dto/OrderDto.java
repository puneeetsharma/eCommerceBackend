package com.example.OrderMicroservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private String orderId;
    private String userId;
    private String productId;
    private String productName;
    private String imageUrl;
    private double orderPrice;
    private double rating;
    private String address;
    private Integer quantity;
    private String merchantId;
    private String merchantName;
}
