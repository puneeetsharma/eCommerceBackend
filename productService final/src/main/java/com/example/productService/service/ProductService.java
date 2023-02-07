package com.example.productService.service;

import com.example.productService.entity_table.ProductTable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public ProductTable insertProductDetails(ProductTable productTable);

    List<ProductTable> findByName(String name);
    List<ProductTable> findByCategory(String name);
    public Optional<ProductTable> findByProductId(String productId);


    public List<ProductTable> findAll();

    public List<ProductTable> findByMerchantId(String merchantId );

    public boolean updateByProductId(String product_Id);
    public boolean UpdateStock(String product_Id, String merchantId,int Stock);
    //public Optional<ProductTable> FindByProductStock(String product_Id, int Stock);

    public ProductTable inputProductDetails(ProductTable productTable);



    }
