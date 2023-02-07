package com.example.productService.repository.impl;

import com.example.productService.entity_table.ProductTable;
import com.example.productService.repository.ProductCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


import java.util.List;
import java.util.Optional;

public class ProductCustomRepositoryImpl implements ProductCustomRepository {
    @Autowired
    MongoTemplate mongoTemplate;
    public List<ProductTable> UpdateByProductId(String product_Id, String merchantId) {
         Query query =new Query();
        Criteria criteria = new Criteria();

        criteria.andOperator(Criteria.where("productId").is(product_Id),
            Criteria.where("merchantId").is(merchantId));
       query.addCriteria(criteria);
        return mongoTemplate.find(query,ProductTable.class);
    }

    @Override
    public boolean UpdateStock(String product_Id, String merchantId, int Stock) {
        List<ProductTable> productTables= UpdateByProductId(product_Id,merchantId);
        if(productTables.size()==0)
        {
            return false;
        }
        else {
            productTables.get(0).setProductStock(Stock);

            return true;
        }

    }
}
