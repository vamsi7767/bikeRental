package com.bikerental.bookservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bikerental.bookservice.dto.BookDto;
import com.bikerental.bookservice.model.Book;
import com.bikerental.bookservice.model.BookingStatus;
import com.bikerental.bookservice.repo.BookRepository;
import com.bikerental.bookservice.service.BookingService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
//@CrossOrigin("*")
@RequestMapping("/api/v1/bookings")
public class BookingController {


    @Autowired
    private BookRepository bookRepository;
    private final BookingService bookService;
    public BookingController(BookingService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBookings(){
        return bookService.getAllBooking();
    }

//    @GetMapping("/{bookId}/user/{userId}/car/{carId}")
//    public ResponseEntity<BookDto> getBookCarDto(@PathVariable Long bookId, @PathVariable Long userId, @PathVariable Long carId) {
//        BookDto bookDto = bookService.getBookCarDto(bookId, userId, carId);
//        return ResponseEntity.ok(bookDto);
//    }

    @PostMapping("/bike/book")
    public ResponseEntity<Void> bikeBooking(@RequestBody BookDto bookDto) {
        boolean success = bookService.bookCar(bookDto);
        if (success) return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/bike/{userId}")
    public ResponseEntity<List<Book>> getBookingByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(bookService.getBookingByUserId(userId));
    }

    @GetMapping("/{bookingId}/{status}")
    ResponseEntity<?> changeBookingStatus(@PathVariable("bookingId") Long bookingId, @PathVariable("status") String status){

            Optional<Book> optionalBook = bookRepository.findById(bookingId);
            if (optionalBook.isPresent()){
                Book existingBook = optionalBook.get();
                System.out.println(existingBook);
                if (Objects.equals(status, "APPROVED")){
                    existingBook.setBookingStatus(BookingStatus.APPROVED);
                }
                else{
                    existingBook.setBookingStatus(BookingStatus.REJECTED);
                }
                bookRepository.save(existingBook);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().build();
        }
}
