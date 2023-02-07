package com.example.productService.repository;

import com.example.productService.entity_table.ProductTable;

import java.util.List;

public interface ProductCustomRepository {
    public List<ProductTable> UpdateByProductId(String product_Id, String merchantId);
    public boolean UpdateStock(String product_Id, String merchantId,int Stock);
    

}
