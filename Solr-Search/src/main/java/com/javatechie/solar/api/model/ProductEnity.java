package com.javatechie.solar.api.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SolrDocument(collection = "product")
public class ProductEnity {

    @Id
    @Field
    private String productId;
    @Field
    private String productName;
    @Field
    private String productDescription;
    @Field
    private List<String> imageUrl;
    @Field
    private String productCategory;
    @Field
    private String merchantId;
    @Field
    private String merchantName;
    @Field
    private double productRating;
    @Field
    private double productPrice;
    @Field
    private Integer productStock;
    @Field
    private Integer algoValue;
}
