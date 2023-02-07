package com.example.OrderMicroservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
