package com.example.cartmicroservice.controller;

import com.example.cartmicroservice.dto.CartDto;
import com.example.cartmicroservice.dto.MultipleMerchantDto;
import com.example.cartmicroservice.dto.ProductDto;
import com.example.cartmicroservice.entity.Cart;
import com.example.cartmicroservice.feign.ProductService;
import com.example.cartmicroservice.repository.mongo.CartRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.cartmicroservice.services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = "/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private CartRepository repo;

    @Autowired
    ProductService productService;

    @PostMapping("/addCart")
    public Cart addToCart(@RequestBody Cart cart){
        return cartService.addToCart(cart);
    }

    @GetMapping("/findCart")
    public List<Cart> getCart(){
        return cartService.findAll();
    }

    @DeleteMapping(value = "/delete/{cartItemId}")
    public String deleteItem(@PathVariable String cartItemId){
        cartService.deleteById(cartItemId);

        return "Product deleted successfully";
    }

    @GetMapping(value = "/getAllProducts")
    public List<ProductDto> getAllProduct(){
        return productService.getAllProducts();
    }

    @GetMapping(value = "/getProductById/{productId}")
    public ProductDto getproduct(@RequestParam String productId)
    {
        return productService.findByProductId(productId);
    }

//    @PostMapping(value = "/saveToCart/{userId}/{productId}")
//    public void saveOrdersDetails(@PathVariable(value = "userId") String userId,@PathVariable(value = "productId") String productId)
//    {
//
//        Cart cart =cartService.findByProductIdUserId(userId,productId);
//        if(cart==null)
//        {
//        ProductDto productDto = productService.findByProductId(productId);
//        CartDto cartDto = new CartDto();
//        cartDto.setProductId(productId);
//        cartDto.setUserId(userId);
//        cartDto.setProductName(productDto.getProductName());
//        cartDto.setImageUrl(productDto.getImageUrl().get(0));
//        cartDto.setPrice(productDto.getProductPrice());
//        cartDto.setRating(productDto.getProductRating());
//
//        cartDto.setQuantity(1);
//            Cart cart1= new Cart();
//            BeanUtils.copyProperties(cartDto,cart1);
//            cartService.addToCart(cart1);
//        }
//        else
//        {
//            CartDto cartDto = new CartDto();
//            cartDto.setCartItemId(cart.getCartItemId());
//            cartDto.setProductId(cart.getProductId());
//            cartDto.setUserId(cart.getUserId());
//            cartDto.setProductName(cart.getProductName());
//            cartDto.setImageUrl(cart.getImageUrl());
//            cartDto.setPrice(cart.getPrice());
//            cartDto.setRating(cart.getRating());
//            cartDto.setQuantity(cart.getQuantity()+1);
//            Cart cart1= new Cart();
//            BeanUtils.copyProperties(cartDto,cart1);
//            cartService.addToCart(cart1);
//
//            //System.out.println(cart.getQuantity());
//        }
//
//
//    }

    //update

//    @PostMapping(value = "/saveToCart/{userId}/{productId}/{merchantId}")
//    public void saveOrdersDetails(@PathVariable(value = "userId") String userId,
//                                  @PathVariable(value = "productId") String productId,
//                                  @PathVariable(value = "merchantId") String merchantId) {
//
//        List<Cart> cart1 = cartService.findByProductIdUserId(userId, productId);
//        Cart cart = cartService.findByProductIdAndMerchantId(productId, merchantId);
//        if (cart1.size() == 0) {
//            Optional<MultipleMerchantDto> multipleMerchantDto = productService.findByProductIdAndMerchantId(merchantId, productId);
//            if (multipleMerchantDto.isPresent()==true) {
//                MultipleMerchantDto multipleMerchantDto1 = multipleMerchantDto.get();
//                ProductDto productDto = productService.findByProductId(productId);
//                CartDto cartDto = new CartDto();
//                cartDto.setProductId(productId);
//                cartDto.setUserId(userId);
//                cartDto.setProductName(productDto.getProductName());
//                cartDto.setImageUrl(productDto.getImageUrl().get(0));
//                cartDto.setPrice(multipleMerchantDto1.getPrice());
//                cartDto.setRating(productDto.getProductRating());
//                cartDto.setMerchantId(multipleMerchantDto1.getMerchantName());
//                cartDto.setMerchantName(multipleMerchantDto1.getMerchantName());
//                cartDto.setQuantity(1);
//                Cart cart2 = new Cart();
//                BeanUtils.copyProperties(cartDto, cart2);
//                cartService.addToCart(cart2);
//            }
//            else {
//                int a=0;
//            }
//
//        } else if (cart == null) {
//            ProductDto productDto = productService.findByProductId(productId);
//            Optional<MultipleMerchantDto> multipleMerchantDto = productService.findByProductIdAndMerchantId(merchantId, productId);
//            if (multipleMerchantDto.isPresent()) {
//                MultipleMerchantDto multipleMerchantDto1 = multipleMerchantDto.get();
//                CartDto cartDto = new CartDto();
//                cartDto.setProductId(productId);
//                cartDto.setUserId(userId);
//                cartDto.setProductName(productDto.getProductName());
//                cartDto.setImageUrl(productDto.getImageUrl().get(0));
//                cartDto.setPrice(multipleMerchantDto1.getPrice());
//                cartDto.setRating(productDto.getProductRating());
//                cartDto.setMerchantId(multipleMerchantDto1.getMerchantId());
//                cartDto.setMerchantName(multipleMerchantDto1.getMerchantName());
//                cartDto.setQuantity(1);
//                Cart cart2 = new Cart();
//                BeanUtils.copyProperties(cartDto, cart2);
//                cartService.addToCart(cart2);
//            } else {
//
//            }
//        }
//        else
//        {
//            Optional<MultipleMerchantDto> multipleMerchantDto = productService.findByProductIdAndMerchantId(merchantId, productId);
//            if (multipleMerchantDto.isPresent()) {
//                MultipleMerchantDto multipleMerchantDto1 = multipleMerchantDto.get();
//                CartDto cartDto = new CartDto();
//                cartDto.setCartItemId(cart.getCartItemId());
//                cartDto.setProductId(cart.getProductId());
//                cartDto.setUserId(cart.getUserId());
//                cartDto.setProductName(cart.getProductName());
//                cartDto.setImageUrl(cart.getImageUrl());
//                cartDto.setPrice(multipleMerchantDto1.getPrice());
//                cartDto.setRating(cart.getRating());
//                cartDto.setQuantity(cart.getQuantity() + 1);
//                cartDto.setMerchantId(merchantId);
//                cartDto.setMerchantName(cartDto.getMerchantName());
//                Cart cart3 = new Cart();
//                BeanUtils.copyProperties(cartDto, cart3);
//                cartService.addToCart(cart3);
//            }
//
//            //System.out.println(cart.getQuantity());
//        }
//
//
//    }

    @PostMapping(value = "/saveToCart/{userId}/{productId}/{merchantId}")
    public void saveOrdersDetails(@PathVariable(value = "userId") String userId,
                                  @PathVariable(value = "productId") String productId,
                                  @PathVariable(value = "merchantId") String merchantId) {

        boolean present=false;
        Cart cart = new Cart();
        MultipleMerchantDto multipleMerchantDto5=new MultipleMerchantDto();
        Optional<MultipleMerchantDto> multipleMerchantDtotop = productService.findByProductIdAndMerchantId(merchantId, productId);
        if (multipleMerchantDtotop.isPresent() == true) {
            multipleMerchantDto5 = multipleMerchantDtotop.get();
        }

        List<Cart> cart1 = cartService.findByProductIdUserId(userId, productId);
        for(int i=0;i<cart1.size();i++)
        {
            if(cart1.get(i).getMerchantId().equals(merchantId))
            {
                present=true;
                cart =cart1.get(i);
                break;
            }
        }
        if (cart1.size() == 0) {
            Optional<MultipleMerchantDto> multipleMerchantDto = productService.findByProductIdAndMerchantId(merchantId, productId);
            if (multipleMerchantDto.isPresent() == true) {
                MultipleMerchantDto multipleMerchantDto1 = multipleMerchantDto.get();
                ProductDto productDto = productService.findByProductId(productId);
                CartDto cartDto = new CartDto();
                cartDto.setProductId(productId);
                cartDto.setUserId(userId);
                cartDto.setProductName(productDto.getProductName());
                cartDto.setImageUrl(productDto.getImageUrl().get(0));
                cartDto.setPrice(multipleMerchantDto1.getPrice());
                cartDto.setRating(productDto.getProductRating());
                cartDto.setMerchantId(multipleMerchantDto1.getMerchantId());
                cartDto.setMerchantName(multipleMerchantDto1.getMerchantName());
                cartDto.setQuantity(1);
                Cart cart2 = new Cart();
                BeanUtils.copyProperties(cartDto, cart2);
                cartService.addToCart(cart2);
            }
        }
        else if (present == false) {
            Optional<MultipleMerchantDto> multipleMerchantDto = productService.findByProductIdAndMerchantId(merchantId, productId);
            if (multipleMerchantDto.isPresent() == true) {
                MultipleMerchantDto multipleMerchantDto1 = multipleMerchantDto.get();
                ProductDto productDto = productService.findByProductId(productId);
                CartDto cartDto = new CartDto();
                cartDto.setProductId(productId);
                cartDto.setUserId(userId);
                cartDto.setProductName(productDto.getProductName());
                cartDto.setImageUrl(productDto.getImageUrl().get(0));
                cartDto.setPrice(multipleMerchantDto1.getPrice());
                cartDto.setRating(productDto.getProductRating());
                cartDto.setMerchantId(merchantId);
                cartDto.setMerchantName(multipleMerchantDto1.getMerchantName());
                cartDto.setQuantity(1);
                Cart cart2 = new Cart();
                BeanUtils.copyProperties(cartDto, cart2);
                cartService.addToCart(cart2);
            }
        }
                else if(multipleMerchantDtotop.isPresent()){
                    //Optional<MultipleMerchantDto> multipleMerchantDto1 = productService.findByProductIdAndMerchantId(merchantId, productId);
                    //if (multipleMerchantDto.isPresent() == true) {
                        //MultipleMerchantDto multipleMerchantDto2 = multipleMerchantDto.get();
                        //ProductDto productDto = productService.findByProductId(productId);
                        CartDto cartDto = new CartDto();
                        cartDto.setCartItemId(cart.getCartItemId());
                        cartDto.setProductId(cart.getProductId());
                        cartDto.setUserId(userId);
                        cartDto.setProductName(cart.getProductName());
                        cartDto.setImageUrl(cart.getImageUrl());
                        cartDto.setPrice(cart.getPrice());
                        cartDto.setRating(cart.getRating());
                        cartDto.setMerchantId(cart.getMerchantId());
                        cartDto.setMerchantName(cart.getMerchantName());
                        if(cart.getQuantity()<multipleMerchantDto5.getStocks()) {
                            cartDto.setQuantity(cart.getQuantity() + 1);
                        }
                        else {
                            cartDto.setQuantity(multipleMerchantDto5.getStocks());
                        }
                        Cart cart3 = new Cart();
                        BeanUtils.copyProperties(cartDto, cart3);
                        cartService.addToCart(cart3);
                   // }
                }
            }




    @GetMapping(value = "/findByProductIdandUserId/{userId}/{productId}")
    public boolean findByProductIdandUserId(@PathVariable(value = "userId") String cartItemId,@PathVariable(value = "productId") String productId)
    {
        return false;
    }

    @GetMapping(value = "/getAllProductsByUserId/{userId}")
    public ResponseEntity<List<Cart>> getproducts(@PathVariable String userId)
    {
        List<Cart> carts= cartService.findByUserId(userId);
        return new ResponseEntity<List<Cart>>(carts,HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteByUserIdAndProductId/{userId}/{productId}")
    public boolean deleteByUserIdAndProductId(@PathVariable(value = "userId") String userId,@PathVariable(value = "productId") String productId)
    {
        if(cartService.deleteByUserIdAndProductId(userId,productId))
        {
            return true;
        }
        return false;
    }

    @GetMapping(value = "/findByProductIdAndMerchantId/{productId}/{merchantId}")
    public boolean findByProductIdAndMerchantId(@PathVariable(value = "productId") String productId,@PathVariable(value = "merchantId") String merchantId)
    {
        cartService.findByProductIdAndMerchantId(productId,merchantId);
        return true;
    }


    @DeleteMapping(value = "/deleteFromCart/{cartItemId}")
    public Cart deleteFromCart(@PathVariable String cartItemId){
        Cart cart=cartService.findByCartItemId(cartItemId);
        if(cart.getQuantity()<=1)
        {
            cartService.deleteById(cartItemId);
        }
        else{
            return cartService.deleteByCartItemId(cartItemId);
        }
        return cart;
    }


}
