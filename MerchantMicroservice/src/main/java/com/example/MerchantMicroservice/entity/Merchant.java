package com.example.MerchantMicroservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = Merchant.COLLECTION_NAME)
public class Merchant {
    public static final String COLLECTION_NAME="Merchant";
    @Id
    private String merchantId;
    private String merchantName;
    private String merchantEmail;
    private String merchantPassword;


}
