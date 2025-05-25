package com.bikerental.bikeservice.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bikerental.bikeservice.dto.BikeDto;
import com.bikerental.bikeservice.dto.BookDto;
import com.bikerental.bikeservice.dto.SearchDto;
import com.bikerental.bikeservice.service.AdminService;

@RestController
//@CrossOrigin("*")
@RequestMapping("/api/v1/admin")
 
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
		
		this.adminService = adminService;
	}

    
    
    
    
    
	// add car
    @PostMapping("/add-bike")
    public ResponseEntity<?> addBike(@RequestBody BikeDto bikeDto) throws IOException {
        System.out.println("Received BikeDto: " + bikeDto);
        boolean success = adminService.addBike(bikeDto);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }




    // get all cars
    @GetMapping("/bikes")
    public ResponseEntity<?> getAllBikes() {
        return ResponseEntity.ok(adminService.getAllBikes());
    }

    // delete car
    @DeleteMapping("/bike/{id}")
    public ResponseEntity<Void> deleteBike(@PathVariable Long id) {
        adminService.deleteBike(id);
        return ResponseEntity.ok(null);
    }

    // get car by id
    @GetMapping("/bike/{id}")
    public ResponseEntity<BikeDto> getBikeById(@PathVariable Long id) {
        BikeDto bikeDto = adminService.getBikeById(id);
        return ResponseEntity.ok(bikeDto);
    }

    // update car
    @PutMapping("/bike/{bikeId}")
    public ResponseEntity<Void> updateCar(@PathVariable Long bikeId, @ModelAttribute BikeDto bikeDto) throws IOException {
        try {
            boolean success = adminService.updateBike(bikeId, bikeDto);
            if (success) return ResponseEntity.status(HttpStatus.OK).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // get all bookings
    @GetMapping("/bike/bookings")
    public ResponseEntity<List<BookDto>> getBookings(){
        return ResponseEntity.ok(adminService.getBookings());
    }

    @GetMapping("/bike/booking/{bookingId}/{status}")
    public ResponseEntity<?> changeBookingStatus(@PathVariable Long bookingId, @PathVariable String status) {
        boolean success = adminService.changeBookingStatus(bookingId, status);
        if (success) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

    // search for car
    @PostMapping("/bike/search")
    public ResponseEntity<?> search(@RequestBody SearchDto searchDto) {
        return ResponseEntity.ok(adminService.searchBike(searchDto));
    }
}
