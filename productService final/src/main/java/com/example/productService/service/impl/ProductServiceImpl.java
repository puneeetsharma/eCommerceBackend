package com.example.productService.service.impl;

import com.example.productService.entity_table.ProductTable;
import com.example.productService.repository.ProductRepository;
import com.example.productService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductTable insertProductDetails(ProductTable productTable) {
        return productRepository.save(productTable);
    }

    @Override
    public List<ProductTable> findByName(String name) {
        return productRepository.findByproductName(name);
    }

    @Override
    public List<ProductTable> findByCategory(String categoryId) {
        return productRepository.findByproductCategory(categoryId);
    }

    @Override
    public Optional<ProductTable> findByProductId(String productId){
        return productRepository.findByProductId(productId);
    }

    @Override
    public List<ProductTable> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductTable> findByMerchantId(String merchantId) {
        return productRepository.findByMerchantId(merchantId);
    }

    @Override
    public boolean updateByProductId(String product_Id) {
        Optional<ProductTable> productTable = productRepository.findByProductId(product_Id);
        ProductTable productTable1= new ProductTable();

        if(productTable.isPresent()==true)
        {
            productTable1 = productTable.get();
            int Stock=productTable1.getProductStock()-1;
            productTable1.setProductStock(Stock);
            productRepository.save(productTable1);
            return true;
        }

        return false;
    }

    @Override
    public boolean UpdateStock(String product_Id, String merchantId, int Stock) {

        Optional<ProductTable> productTable = productRepository.findByProductId(product_Id);
        if(productTable.isPresent()==true)
        {
            ProductTable productTable1= new ProductTable();
            productTable1 = productTable.get();
            productTable1.setProductStock(Stock);
            productRepository.save(productTable1);
        }

        return productRepository.UpdateStock(product_Id,merchantId,Stock);
    }

    @Override
    public ProductTable inputProductDetails(ProductTable productTable) {
        return productRepository.save(productTable);
    }

//    @Override
//    public Optional<ProductTable> FindByProductStock(String product_Id, int Stock) {
//        Optional<ProductTable> productTable = productRepository.findByProductId(product_Id);
//        if(productTable.isPresent()==true)
//        {
//            ProductTable productTable1= new ProductTable();
//            productTable1 = productTable.get();
//            productTable1.setProductStock(Stock);
//            return productTable;
//        }
//        return productTable;
//
//    }


}
