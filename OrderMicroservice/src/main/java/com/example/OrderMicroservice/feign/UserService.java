package com.example.OrderMicroservice.feign;

import com.example.OrderMicroservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;


@FeignClient(name = "UserService",url = "http://localhost:8092")
public interface UserService {

    @GetMapping(value = "/getItemByUserId/{userId}")
    public Optional<UserDto> getItemByUserId(@PathVariable(value = "userId") String userId);
}
