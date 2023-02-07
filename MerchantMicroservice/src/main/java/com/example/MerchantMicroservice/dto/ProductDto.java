package com.example.MerchantMicroservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDto {

    private String productId;
    private String productName;
    private String productDescription;
    private List<String> imageUrl;
    private String productCategory;
    private String merchantId;
    private String merchantName;
    private double productRating;
    private double productPrice;
    private Integer productStock;
    private Integer algoValue;
}
