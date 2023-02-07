package com.example.OrderMicroservice.feign;

import com.example.OrderMicroservice.dto.CartDto;
import com.example.OrderMicroservice.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CartService", url="http://localhost:8093/cart")
public interface CartService {

    @DeleteMapping(value = "/deleteByUserIdAndProductId/{userId}/{productId}")
    public void deleteByUserIdAndProductId(@PathVariable(value = "userId") String userId, @PathVariable(value = "productId") String productId);

    @GetMapping("/findCart")
    public List<CartDto> getCart();

    @GetMapping(value = "/getAllProductsByUserId/{userId}")
    public List<CartDto> getproducts(@PathVariable(value = "userId") String userId);

}
