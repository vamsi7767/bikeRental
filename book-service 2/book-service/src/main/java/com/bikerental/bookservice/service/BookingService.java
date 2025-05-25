package com.bikerental.bookservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikerental.bookservice.dto.*;
import com.bikerental.bookservice.model.Book;
import com.bikerental.bookservice.model.BookingStatus;
import com.bikerental.bookservice.repo.BookRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class BookingService {

    @Autowired
    private BookRepository bookRepository;
    private final UserServiceFeignClient userServiceFeignClient;
    private final BikeServiceFeignClient bikeServiceFeignClient;

    // Constructor injection
    public BookingService(UserServiceFeignClient userServiceFeignClient, BikeServiceFeignClient bikeServiceFeignClient) {
        this.userServiceFeignClient = userServiceFeignClient;
        this.bikeServiceFeignClient = bikeServiceFeignClient;
    }

    public List<Book> getBookingByUserId(Long userId){
        return bookRepository.findAllByUserId(userId);
    }
    public List<Book> getAllBooking(){
        return bookRepository.findAll();
    }

    public boolean bookCar(BookDto bookDto){
        BikeDto optionalCar = bikeServiceFeignClient.getBikeById(bookDto.getCarId());
        UserDto optionalUser = userServiceFeignClient.getUserById(bookDto.getUserId());



        if (optionalCar!= null && optionalUser!= null) {
            BikeDto existingCar = optionalCar;
            UserDto existingUser = optionalUser;
            Book book = new Book();
            book.setUserId(optionalUser.getId());
            book.setUsername(existingUser.getName());
            book.setEmail(existingUser.getEmail());
            book.setCarId(existingCar.getId());
            book.setBookingStatus(BookingStatus.PENDING);
            long diffInMilliSeconds = bookDto.getToDate().getTime() - bookDto.getFromDate().getTime();
            long days = TimeUnit.MILLISECONDS.toDays(diffInMilliSeconds);
            book.setDays(days);
            book.setPrice(existingCar.getPrice() * days);
            book.setFromDate(bookDto.getFromDate());
            book.setToDate(bookDto.getToDate());
            bookRepository.save(book);
            return true;
        }
        return false;

    }

}
