package com.bikerental.bikeservice.model;

import com.bikerental.bikeservice.dto.BikeDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Table(name = "bikes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String color;
    private String name;
    private String type;
    private String transmission;
    private String description;
    private Long price;
    private String year;
    private String image;

    public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

   

    public Bike() {
        super();
    }

    

    public Bike(Long id, String brand, String color, String name, String type, String transmission, String description,
			Long price, String year, String image) {
		super();
		this.id = id;
		this.brand = brand;
		this.color = color;
		this.name = name;
		this.type = type;
		this.transmission = transmission;
		this.description = description;
		this.price = price;
		this.year = year;
		this.image = image;
	}
    
    

	@Override
	public String toString() {
		return "Bike [id=" + id + ", brand=" + brand + ", color=" + color + ", name=" + name + ", type=" + type
				+ ", transmission=" + transmission + ", description=" + description + ", price=" + price + ", year="
				+ year + ", image=" + image + "]";
	}

	public BikeDto toDto() {
        BikeDto bikeDto = new BikeDto();
        bikeDto.setId(id);
        bikeDto.setName(name);
        bikeDto.setBrand(brand);
        bikeDto.setColor(color);
        bikeDto.setPrice(price);
        bikeDto.setDescription(description);
        bikeDto.setType(type);
        bikeDto.setTransmission(transmission);
        bikeDto.setYear(year);
        bikeDto.setImage(image);
        return bikeDto;
    }
}
