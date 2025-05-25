package com.bikerental.bikeservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bikerental.bikeservice.model.Bike;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {
	
}
