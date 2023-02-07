package com.example.MailSender.controller;

import com.example.MailSender.dto.EmailDetails;
import com.example.MailSender.dto.ProductDto;
import com.example.MailSender.feign.ProductService;
import com.example.MailSender.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;
    @Autowired
    ProductService productService;

    @PostMapping("/sendMail/{email}/{productId}/{merchantId}")
    public String sendMail(@PathVariable(value = "email") String email, @PathVariable(value = "productId") String productId,
                           @PathVariable("merchantId") String merchantId)
    {
//        ProductDto productDetails = productService.findByProductId(productId);
        String status = emailService.sendSimpleMail(email,productId,merchantId);

        return status;
    }

//    @PostMapping("/sendMailWithAttachment")
//    public String sendMailWithAttachment(
//            @RequestBody EmailDetails dto)
//    {
//        String status = emailService.sendMailWithAttachment(dto);
//
//        return status;
//    }

//    @PostMapping("/sendMail1/{email}/{productId}/{merchantId}")
//    public String sendMailWithAttachment(@PathVariable(value = "email") String email, @PathVariable(value = "productId") String productId,
//                                         @PathVariable("merchantId") String merchantId)
//    {
//        String status = emailService.sendMailWithAttachment(email,productId,merchantId);
//
//        return status;
//    }


    @GetMapping(value = "/getProductById/{productId}")
    public ProductDto getproduct(@RequestParam String productId)
    {

        return productService.findByProductId(productId);
    }
}
