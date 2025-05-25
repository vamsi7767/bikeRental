package com.bikerental.userserver.service;


import com.bikerental.userserver.dto.RegisterRequest;
import com.bikerental.userserver.dto.UserDto;

public interface AuthenticationService {

    UserDto createCustomer(RegisterRequest registerRequest);
    boolean hasCustomerWithEmail(String email);

}
