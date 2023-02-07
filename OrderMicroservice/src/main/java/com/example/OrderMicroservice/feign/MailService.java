package com.example.OrderMicroservice.feign;

import com.example.OrderMicroservice.dto.MailDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "MailSender",url = "http://localhost:8095")
public interface MailService {

    @PostMapping("/sendMail/{email}/{productId}/{merchantId}")
    String sendSimpleMail(@PathVariable(value = "email") String email,@PathVariable(value = "productId") String productId,
                          @PathVariable(value = "merchantId") String merchantId);
}
