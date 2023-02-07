package com.example.cartmicroservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Document(collection = Cart.COLLECTION_NAME)
public class Cart {
    public static final String COLLECTION_NAME="Cart";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String cartItemId;
    private String userId;
    private String productId;
    private String productName;
    private String imageUrl;
    private double price;
    private double rating;
    private Integer quantity;
    private String merchantId;
    private String merchantName;
}
