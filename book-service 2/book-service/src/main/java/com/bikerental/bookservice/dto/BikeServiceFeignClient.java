package com.bikerental.bookservice.dto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "bike-service") // Name of the Car microservice
public interface BikeServiceFeignClient {
    @GetMapping("/api/v1/admin/bike/{carId}")
    BikeDto getBikeById(@PathVariable("carId") Long carId);

//	BikeDto getBikeById(Long carId);
}