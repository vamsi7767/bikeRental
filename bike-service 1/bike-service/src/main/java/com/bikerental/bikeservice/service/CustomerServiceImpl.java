package com.bikerental.bikeservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.bikerental.bikeservice.dto.BikeDto;
import com.bikerental.bikeservice.dto.BikeListDto;
import com.bikerental.bikeservice.dto.BookDto;
import com.bikerental.bikeservice.dto.SearchDto;
import com.bikerental.bikeservice.model.Bike;
import com.bikerental.bikeservice.repo.BikeRepository;

@Service
//@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	@Autowired
    private BikeRepository bikeRepository;
	
//	@Autowired
//    private UserServiceClient userServiceFeignClient;
//	
//	@Autowired
//    private BookingServiceClient bookServiceFeignClient;

	@Override
	public List<BikeDto> getAllBikes() {
        return bikeRepository.findAll()
                .stream()
                .map(Bike::toDto) // Call toDto() method of Bike entity to convert to BikeDto
                .collect(Collectors.toList());
    }

    @Override
    public boolean bookBike(BookDto bookDto) {

        return false;
    }

    @Override
    public BikeDto getBikeById(Long bikeId) {
        Optional<Bike> optionalBike = bikeRepository.findById(bikeId);
        return optionalBike.map(Bike::toDto).orElse(null);
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

