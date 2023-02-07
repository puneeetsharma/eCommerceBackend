package com.example.OrderMicroservice.controller;

import com.example.OrderMicroservice.dto.*;
import com.example.OrderMicroservice.entity.Orders;
import com.example.OrderMicroservice.feign.CartService;
import com.example.OrderMicroservice.feign.MailService;
import com.example.OrderMicroservice.feign.ProductService;
import com.example.OrderMicroservice.feign.UserService;
import com.example.OrderMicroservice.services.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = "/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    MailService mailService;

    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;


    @PostMapping(value = "/insertProductDetailsByMerchant")
    public ResponseEntity<Object> insertProductDetailsByMerchant(@RequestBody OrderDto productDto)
    {
        Orders orders= new Orders();
        BeanUtils.copyProperties(productDto,orders);
        Orders employeeCreated = orderService.addOrder(orders);
        return new ResponseEntity<>( productDto,HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteById/{orderId}")
    public ResponseEntity<String> deleteById(@PathVariable String orderId){
        orderService.deleteOrder(orderId);
        return new ResponseEntity<String>("Deleted",HttpStatus.OK);
    }

    @GetMapping(value = "/getAllOrders")
    public  List<Orders> getAllProducts() {

        return orderService.findAll();
    }

//    @PostMapping("/sendMail/{email}/{productId}")
//    public String sendMail(@PathVariable String email,@PathVariable String productId)
//    {
//        String status = mailService.sendSimpleMail(email, productId);
//
//        return status;
//    }


    @GetMapping(value = "/getAllProducts")
    public List<ProductDto> getAllProduct(){
        return productService.getAllProducts();
    }

    @GetMapping(value = "/getProductById/{productId}")
    public ProductDto getpro(@RequestParam String productId)
    {
        return productService.findByProductId(productId);
    }


//    @PostMapping(value = "/saveOrders/{userId}/{productId}")
//    public boolean saveOrdersDetails(@PathVariable(value = "userId") String userId,@PathVariable(value = "productId") String productId)
//    {
//        cartService.deleteByUserIdAndProductId(userId,productId);
//        ProductDto productDto = productService.findByProductId(productId);
//        OrderDto orderDto = new OrderDto();
//        orderDto.setProductId(productId);
//        orderDto.setUserId(userId);
//        orderDto.setProductName(productDto.getProductName());
//        orderDto.setImageUrl(productDto.getImageUrl().get(0));
//        orderDto.setOrderPrice(productDto.getProductPrice());
//        orderDto.setRating(productDto.getProductRating());
//
//        Orders orders = new Orders();
//        BeanUtils.copyProperties(orderDto,orders);
//        orderService.addOrder(orders);
//        return true;
//    }

    @PostMapping(value = "/saveOrders/{userId}/{productId}/{address}/{merchantId}")
    public boolean saveOrdersDetails(@PathVariable(value = "userId") String userId,
                                     @PathVariable(value = "productId") String productId,
                                     @PathVariable(value = "address") String address,
                                     @PathVariable(value = "merchantId") String merchantId)
    {
        //cartService.deleteByUserIdAndProductId(userId,productId);
        ProductDto productDto = productService.findByProductId(productId);
        //List<CartDto> cartDtos=cartService.getproducts(userId);
        Optional<MerchantDto> merchantDto =productService.findByProductIdAndMerchantId(merchantId,productId);
        MerchantDto merchantDto1=new MerchantDto();
        if(merchantDto.isPresent())
        {
            merchantDto1 =merchantDto.get();
        }

        OrderDto orderDto = new OrderDto();
        orderDto.setProductId(productId);
        orderDto.setUserId(userId);
        orderDto.setProductName(productDto.getProductName());
        orderDto.setImageUrl(productDto.getImageUrl().get(0));
        orderDto.setOrderPrice(merchantDto1.getPrice());
        orderDto.setRating(productDto.getProductRating());
        orderDto.setAddress(address);
        orderDto.setMerchantId(merchantId);
        orderDto.setQuantity(1);
        orderDto.setMerchantName(merchantDto1.getMerchantName());
        Orders orders = new Orders();
        BeanUtils.copyProperties(orderDto,orders);
        orderService.addOrder(orders);
        productService.updateStockByProductIdAndMerchantId(productId,merchantId,1);
        productService.UpdateByProductId(productId);
        Optional<UserDto> userDto = userService.getItemByUserId(userId);
        UserDto userDto1 = new UserDto();
        if (userDto.isPresent())
        {
            userDto1=userDto.get();
        }
        mailService.sendSimpleMail(userDto1.getUserEmail(),productId,merchantId);
        return true;
    }


    @GetMapping(value = "/getOrdersByUserId/{userId}")
    public  List<Orders> getOrdersByUserId(@PathVariable ("userId") String userId) {
        return orderService.findByUserId(userId);
    }


    @GetMapping(value = "/UpdateStockByProductId/{productId}")
    public boolean UpdateStockByProductId(@PathVariable(value = "productId") String productId)
    {
        return productService.UpdateStockByProductId(productId);
    }

    @PostMapping(value = "/saveOrdersCart/{userId}/{address}")
    public boolean saveOrdersDetailsFromCart(@PathVariable(value = "userId") String userId,
                                             @PathVariable(value = "address") String address)
    {
        List<CartDto> cartDtos=cartService.getproducts(userId);
        for(int i=0;i<cartDtos.size();i++) {


            ProductDto productDto = productService.findByProductId(cartDtos.get(i).getProductId());
            OrderDto orderDto = new OrderDto();
            orderDto.setProductId(productDto.getProductId());
            orderDto.setUserId(userId);
            orderDto.setProductName(productDto.getProductName());
            orderDto.setImageUrl(productDto.getImageUrl().get(0));
            orderDto.setOrderPrice(cartDtos.get(i).getPrice());
            orderDto.setRating(productDto.getProductRating());
            orderDto.setAddress(address);
            orderDto.setQuantity(cartDtos.get(i).getQuantity());
            orderDto.setMerchantId(cartDtos.get(i).getMerchantId());
            orderDto.setMerchantName(cartDtos.get(i).getMerchantName());
            Orders orders = new Orders();
            BeanUtils.copyProperties(orderDto, orders);
            orderService.addOrder(orders);
            productService.updateStockByProductIdAndMerchantId(cartDtos.get(i).getProductId(),cartDtos.get(i).getMerchantId(),cartDtos.get(i).getQuantity());
            cartService.deleteByUserIdAndProductId(userId, cartDtos.get(i).getProductId());
            Optional<UserDto> userDto = userService.getItemByUserId(userId);
            UserDto userDto1 = new UserDto();
            if (userDto.isPresent())
            {
                userDto1=userDto.get();
            }
            mailService.sendSimpleMail(userDto1.getUserEmail(),cartDtos.get(i).getProductId(),cartDtos.get(i).getMerchantId());
        }
        return true;
    }

}
