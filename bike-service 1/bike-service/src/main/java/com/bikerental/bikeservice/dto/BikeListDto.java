package com.bikerental.bikeservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class BikeListDto {

    private List<BikeDto> bikeDtoList;

	public List<BikeDto> getBikeDtoList() {
		return bikeDtoList;
	}

	public void setBikeDtoList(List<BikeDto> bikeDtoList) {
		this.bikeDtoList = bikeDtoList;
	}

}
