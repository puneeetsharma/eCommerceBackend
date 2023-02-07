package com.example.OrderMicroservice.feign;

import com.example.OrderMicroservice.dto.MerchantDto;
import com.example.OrderMicroservice.dto.ProductDto;
import com.example.OrderMicroservice.entity.Orders;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "ProductService",url = "http://localhost:8090/product")
public interface ProductService {

    @GetMapping(value = "/getAllProducts")
    public List<ProductDto> getAllProducts();

    @GetMapping(value = "/getProductById/{productId}")
    public ProductDto findByProductId(@RequestParam(value = "productId") String productId);


    @GetMapping(value = "/UpdateStockByProductId/{productId}/")
    public boolean UpdateStockByProductId(@PathVariable(value = "productId") String product_Id);

    @PostMapping(value = "/updateStockByProductIdAndMerchantId/{productId}/{merchantId}/{stock}")
    public boolean updateStockByProductIdAndMerchantId(@PathVariable(value = "productId") String productId,
                                                       @PathVariable(value = "merchantId") String merchantId,
                                                       @PathVariable(value = "stock") Integer stock);

    @GetMapping(value = "/findByProductIdAndMerchantId/{productId}/{merchantId}")
    public Optional<MerchantDto> findByProductIdAndMerchantId(@PathVariable(value = "productId") String merchantId,
                                                                   @PathVariable(value = "merchantId") String productId);

    @GetMapping(value = "/UpdateStockByProductId/{productId}/")
    public boolean UpdateByProductId(@PathVariable(value = "productId") String product_Id);

}
