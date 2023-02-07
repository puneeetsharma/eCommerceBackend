package com.example.MerchantMicroservice.controller;

import com.example.MerchantMicroservice.dto.ProductDto;
import com.example.MerchantMicroservice.entity.Merchant;
import com.example.MerchantMicroservice.feign.ProductService;
import com.example.MerchantMicroservice.repository.mongo.MerchantRepository;
import com.example.MerchantMicroservice.services.MerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = "/merchant")
public class MerchantController {
    @Autowired
    private MerchantService merchantService;

    @Autowired
    ProductService productService;
    @Autowired
    private MerchantRepository repo;

    @PostMapping("/addMerchant")
    public String addMerchant(@RequestBody Merchant merchant){
        repo.save(merchant);
        return merchantService.addMerchant(merchant);
    }

    @GetMapping("/findMerchant")
    public List<Merchant> getMerchant(){
        return merchantService.findAll();
    }

    @DeleteMapping(value = "/delete/{merchantId}")
    public String deleteMerchant(@PathVariable String merchantId){
        merchantService.deleteById(merchantId);
        return "Merchant deleted successfully";
    }

    @GetMapping(value = "/getAllProducts")
    public List<ProductDto> getAllProduct(){
        return productService.getAllProducts();
    }

    @GetMapping(value = "/getProductById/{productId}")
    public ProductDto getproductById(@RequestParam String productId)
    {
        return productService.findByProductId(productId);
    }

    @PostMapping(value = "/insertProductDetailsByMerchant")
    public ResponseEntity<Object> insertProductDetailsByMerchant(@RequestBody ProductDto productDto)
    {
        ProductDto productTable= new ProductDto();
        BeanUtils.copyProperties(productDto,productTable);
        ProductDto employeeCreated = productService.insertProductDetails(productTable);
        return new ResponseEntity<>( productDto,HttpStatus.OK);
    }

    @GetMapping("/existById/{id}")
    public ResponseEntity<Boolean> existByIdId(@PathVariable ("id") String id)
    {
        boolean employee = merchantService.existsById(id);

        return new ResponseEntity<Boolean>(employee,HttpStatus.OK);
    }

    @GetMapping(path="/merchantLogin" ,produces = "application/json")
    public ResponseEntity<Boolean> merchantLogin(@RequestParam String email, String password){

        Boolean isTrue = merchantService.login(email,password);
        if(isTrue){

            return new ResponseEntity<Boolean>(isTrue,HttpStatus.OK);

        }else {
            return new ResponseEntity<Boolean>(isTrue ,HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(path="/merchantSignUp" ,produces = "application/json")
    public ResponseEntity<Boolean> mercahntSignUp(@RequestBody Merchant merchant){


        Boolean isTrue = merchantService.signup(merchant);
        if(isTrue){
            return new ResponseEntity<Boolean>(true,HttpStatus.OK);
        }else {
            return new ResponseEntity<Boolean>(isTrue , HttpStatus.BAD_GATEWAY);
        }

    }

    @GetMapping(value = "/findByEmailId/{userEmail}")
    public ResponseEntity<Optional<Merchant>> getMerchantByEmail(@PathVariable(value = "userEmail") String merchantEmail)
    {
        return new ResponseEntity<>(merchantService.findBymerchantEmail(merchantEmail),HttpStatus.OK);
    }





}
