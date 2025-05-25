package com.bikerental.bikeservice.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bikerental.bikeservice.client.BookingServiceClient;
import com.bikerental.bikeservice.dto.BikeDto;
import com.bikerental.bikeservice.dto.BikeListDto;
import com.bikerental.bikeservice.dto.BookDto;
import com.bikerental.bikeservice.dto.SearchDto;
import com.bikerental.bikeservice.model.Bike;
import com.bikerental.bikeservice.repo.BikeRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
    private BikeRepository bikeRepository;
	
	@Autowired
    private BookingServiceClient bookingServiceClient;

	@Override
	public boolean addBike(BikeDto bikeDto) throws IOException {
	    try {
	        Bike bike = new Bike();
	        bike.setName(bikeDto.getName());
	        bike.setBrand(bikeDto.getBrand());
	        bike.setColor(bikeDto.getColor());
	        bike.setPrice(bikeDto.getPrice());
	        bike.setYear(bikeDto.getYear());
	        bike.setType(bikeDto.getType());
	        bike.setDescription(bikeDto.getDescription());
	        bike.setTransmission(bikeDto.getTransmission());
	        bike.setImage(bikeDto.getImage());
	        bikeRepository.save(bike);
	        return true;
	    } catch (Exception exception) {
	        return false;
	    }
	}


    @Override
    public List<BikeDto> getAllBikes() {
        return bikeRepository.findAll().stream().map(Bike::toDto).collect(Collectors.toList());
    }

    @Override
    public void deleteBike(Long id) {
        bikeRepository.deleteById(id);
    }

    @Override
    public BikeDto getBikeById(Long id) {
        Optional<Bike> optionalBike = bikeRepository.findById(id);
        return optionalBike.map(Bike::toDto).orElse(null);
    }

    @Override
    public boolean updateBike(Long bikeId, BikeDto bikeDto) throws IOException {
        Optional<Bike> optionalBike = bikeRepository.findById(bikeId);
        if (optionalBike.isPresent()) {
            Bike existedBike = optionalBike.get();
            if (bikeDto.getImage() != null)
                existedBike.setImage(bikeDto.getImage());
            existedBike.setPrice(bikeDto.getPrice());
            existedBike.setYear(bikeDto.getYear());
            existedBike.setColor(bikeDto.getColor());
            existedBike.setTransmission(bikeDto.getTransmission());
            existedBike.setType(bikeDto.getType());
            existedBike.setDescription(bikeDto.getDescription());
            existedBike.setName(bikeDto.getName());
            existedBike.setBrand(bikeDto.getBrand());
            bikeRepository.save(existedBike);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<BookDto> getBookings() {
        return bookingServiceClient.getAllBookings();
    }

    @Override
    public boolean changeBookingStatus(Long bookingId, String status) {
        ResponseEntity<?> response = bookingServiceClient.changeBookingStatus(bookingId, status);
        return response.getStatusCode() == HttpStatus.OK;
    }

    @Override
    public BikeListDto searchBike(SearchDto searchDto) {
        Bike bike = new Bike();
        bike.setBrand(searchDto.getBrand());
        bike.setType(searchDto.getType());
        bike.setTransmission(searchDto.getTransmission());
        bike.setColor(searchDto.getColor());
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll()
                .withMatcher("brand",ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("type",ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("transmission",ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("color",ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        Example<Bike> bikeExample = Example.of(bike, exampleMatcher);
        List<Bike> bikeList = bikeRepository.findAll(bikeExample);
        BikeListDto bikeListDto = new BikeListDto();
        bikeListDto.setBikeDtoList(bikeList.stream().map(Bike::toDto).collect(Collectors.toList()));
        return bikeListDto;
    }
}
