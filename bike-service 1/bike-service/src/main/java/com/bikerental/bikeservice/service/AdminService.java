package com.bikerental.bikeservice.service;


import java.io.IOException;
import java.util.List;

import com.bikerental.bikeservice.dto.BookDto;
import com.bikerental.bikeservice.dto.BikeDto;
import com.bikerental.bikeservice.dto.BikeListDto;
import com.bikerental.bikeservice.dto.SearchDto;

public interface AdminService {

    boolean addBike(BikeDto bikeDto) throws IOException;

    List<BikeDto> getAllBikes();

    void deleteBike(Long id);

    BikeDto getBikeById(Long id);

    boolean updateBike(Long bikeId, BikeDto bikeDto) throws IOException;

    List<BookDto> getBookings();

    boolean changeBookingStatus(Long bookingId, String status);

    BikeListDto searchBike(SearchDto searchDto);

//	Object getAllBikes();

}
