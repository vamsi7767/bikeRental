package com.bikerental.bookservice.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date fromDate;
	private Date toDate;
	private Long days;
	private Long price;
	private BookingStatus bookingStatus;
	private Long userId;
	private Long carId;
	private String username;
	private String email;

	public Book() {
		super();
	}

	public Book(Long id, Date fromDate, Date toDate, Long days, Long price, BookingStatus bookingStatus, Long userId,
			Long carId, String username, String email) {
		super();
		this.id = id;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.days = days;
		this.price = price;
		this.bookingStatus = bookingStatus;
		this.userId = userId;
		this.carId = carId;
		this.username = username;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Long getDays() {
		return days;
	}

	public void setDays(Long days) {
		this.days = days;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
