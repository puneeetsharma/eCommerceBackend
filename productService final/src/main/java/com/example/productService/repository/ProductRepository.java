package com.example.productService.repository;

import com.example.productService.entity_table.ProductTable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<ProductTable,Integer>,ProductCustomRepository {

    public List<ProductTable> findByproductName(String productName);
    public List<ProductTable> findByproductCategory(String categoryId);
    public List<ProductTable> findByMerchantId(String merchantId );
    public Optional<ProductTable> findByProductId(String productId);
    List<ProductTable> findByMerchantIdOrProductId(String productId,String merchantId);
    //public boolean updateByProductId(String product_Id);
    public boolean UpdateStock(String product_Id, String merchantId,int Stock);
    //public boolean FindByProductStock(String product_Id, int Stock);
    //public ProductTable inputProductDetails(ProductTable productTable);


    }
