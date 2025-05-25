package com.bikerental.bikeservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bikerental.bikeservice.dto.UserDto;


@FeignClient(name = "user-service")
public interface UserServiceClient {
    @GetMapping("/api/v1/auth/user/{userId}")
    UserDto getUserById(@PathVariable("userId") Long userId);
}
