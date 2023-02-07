package com.example.productService.controller;

import com.example.productService.dto.ProductDto;
import com.example.productService.entity_table.ProductTable;
import com.example.productService.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    MongoTemplate mongoTemplate;

    @PostMapping(value = "/insertProductDetailsByMerchant")
    public ResponseEntity<Object> insertProductDetailsByMerchant(@RequestBody ProductDto productDto)
    {
        ProductTable productTable= new ProductTable();
        BeanUtils.copyProperties(productDto,productTable);
        ProductTable employeeCreated = productService.insertProductDetails(productTable);
        return new ResponseEntity<>( productDto,HttpStatus.OK);
    }

    @GetMapping(value = "/searchItemsByName/{name}")
    public  ResponseEntity<List<ProductTable>> getItemsByName(@PathVariable(value = "name") String name) {

             List<ProductTable> productTables=productService.findByName(name);
     return new ResponseEntity<List<ProductTable>>(productTables, HttpStatus.OK);
    }

    @GetMapping(value = "/getProductById/{productId}")
    public ResponseEntity<Optional<ProductTable>> getItemByProductId(@PathVariable(value = "productId") String productId)
    {
        return new ResponseEntity<>(productService.findByProductId(productId),HttpStatus.OK);
    }

    @GetMapping(value = "/searchItemsByCategoryId/{categoryId}")
    public  ResponseEntity<List<ProductTable>> getItemsByCategory(@PathVariable(value = "categoryId") String categoryId) {

        List<ProductTable> productTables=productService.findByCategory(categoryId);
        return new ResponseEntity<List<ProductTable>>(productTables, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllProducts")
    public  List<ProductTable> getAllProducts() {

       return productService.findAll();
    }
    @GetMapping(value = "/UpdateStockByProductId/{productId}/")
    public boolean UpdateByProductId(String product_Id)
    {
        return productService.updateByProductId(product_Id);
    }


    @GetMapping(value = "/UpdateStock/{productId}/{merchantId}/{stock}")
    public boolean updateStock(@PathVariable("productId") String productId,@PathVariable("merchantId") String merchantId,@PathVariable("stock") int stock)
    {
        return productService.UpdateStock(productId,merchantId,stock);
    }





    @PostMapping(value = "/inputProductDetails/{productId}/{productName}/{productDescription}/{imageUrl}/{productCategory}/{merchantId}/{merchantName}/{productRating}/{productPrice}/{productStock}/{algoValue}")
    public ResponseEntity<Object> inputProductDetails(@PathVariable (value = "productId") String productId,
                                                      @PathVariable(value = "productName") String productName,
                                                      @PathVariable(value = "productDescription") String productDescription,
                                                      @PathVariable(value = "imageUrl") List<String> imageUrl,
                                                      @PathVariable(value = "productCategory") String productCategory,
                                                      @PathVariable(value = "merchantId") String merchantId,
                                                      @PathVariable(value = "merchantName") String merchantName,
                                                      @PathVariable(value = "productRating") double productRating,
                                                      @PathVariable(value = "productPrice") double productPrice,
                                                      @PathVariable(value = "productStock") Integer productStock,
                                                      @PathVariable(value = "algoValue")Integer algoValue)
    {
            ProductDto productDto=new ProductDto();
            productDto.setProductId(productId);
            productDto.setProductName(productName);
            productDto.setProductDescription(productDescription);
            productDto.setImageUrl(imageUrl);
            productDto.setProductCategory(productCategory);
            productDto.setMerchantId(merchantId);
            productDto.setMerchantName(merchantName);
            productDto.setProductRating(productRating);
            productDto.setProductStock(productStock);
            productDto.setAlgoValue(algoValue);
            ProductTable productTable = new ProductTable();
            BeanUtils.copyProperties(productDto,productTable);
            productService.insertProductDetails(productTable);
            return new ResponseEntity<>(productTable,HttpStatus.OK);
    }



//    @GetMapping(value = "/UpdateStocks/{productId}/{stock}")
//    public Optional<ProductTable> updateStocks(@PathVariable("productId") String productId,@PathVariable("stock") int stock)
//    {
//        return productService.FindByProductStock(productId,stock);
//    }

//    @GetMapping(value = "/searchItemsByName/{name}")
//    public  ResponseEntity<List<ProductTable>> getItemsByMerchantId(@PathVariable(value = "merchantId") String merchantId) {
//
//        List<ProductTable> productTables=productService.findByName(merchantId);
//        return new ResponseEntity<List<ProductTable>>(productTables, HttpStatus.OK);
//    }


}
