package com.bikerental.bikeservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bikerental.bikeservice.dto.BikeDto;
import com.bikerental.bikeservice.dto.SearchDto;
import com.bikerental.bikeservice.service.CustomerService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/customer")
 
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
		
		this.customerService = customerService;
	}
	// get all cars
    @GetMapping("/bikes")
    public ResponseEntity<List<BikeDto>> getAllBikes(){
        List<BikeDto> bikeDtoList = customerService.getAllBikes();
        return ResponseEntity.ok(bikeDtoList);
    }
    // get car by id
    @GetMapping("/bike/{bikeId}")
    public ResponseEntity<BikeDto> getBikeById(@PathVariable Long bikeId) {
        BikeDto bikeDto = customerService.getBikeById(bikeId);
        if (bikeDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bikeDto);
    }


    // search for car
    @PostMapping("/bike/search")
    public ResponseEntity<?> search(@RequestBody SearchDto searchDto) {
        return ResponseEntity.ok(customerService.searchBike(searchDto));
    }

}
