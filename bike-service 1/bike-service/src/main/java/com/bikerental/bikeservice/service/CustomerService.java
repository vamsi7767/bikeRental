package com.bikerental.bikeservice.service;


import java.util.List;

import com.bikerental.bikeservice.dto.BookDto;
import com.bikerental.bikeservice.dto.BikeDto;
import com.bikerental.bikeservice.dto.BikeListDto;
import com.bikerental.bikeservice.dto.SearchDto;

public interface CustomerService {

    List<BikeDto> getAllBikes();

    boolean bookBike(BookDto bookDto);

  


    BikeListDto searchBike(SearchDto searchDto);

//	List<BikeDto> getAllBikes();
    BikeDto getBikeById(Long bikeId);
}
