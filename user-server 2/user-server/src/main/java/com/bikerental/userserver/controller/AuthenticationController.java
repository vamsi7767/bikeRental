package com.bikerental.userserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.bikerental.userserver.dto.AuthenticationRequest;
import com.bikerental.userserver.dto.AuthenticationResponse;
import com.bikerental.userserver.dto.RegisterRequest;
import com.bikerental.userserver.dto.UserDto;
import com.bikerental.userserver.model.User;
import com.bikerental.userserver.repo.UserRepository;
import com.bikerental.userserver.service.AuthenticationService;
import com.bikerental.userserver.service.UserService;
import com.bikerental.userserver.utils.JwtUtil;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
//@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthenticationController(AuthenticationService authenticationService,
			AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil,
			UserRepository userRepository) {
		
		this.authenticationService = authenticationService;
		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.jwtUtil = jwtUtil;
		this.userRepository = userRepository;
	}

	// register
    @RequestMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody RegisterRequest registerRequest){
        if (authenticationService.hasCustomerWithEmail(registerRequest.getEmail()))
            return new ResponseEntity<>("This Email Already Exist !",HttpStatus.NOT_ACCEPTABLE);
        UserDto createdCustomerDto = authenticationService.createCustomer(registerRequest);
        if (createdCustomerDto == null)
            return new ResponseEntity<>("Customer Not Created, Try Again.", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(createdCustomerDto, HttpStatus.CREATED);
    }

    // login
    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest) throws
            BadCredentialsException,
            DisabledException,
            UsernameNotFoundException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                    (authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException badCredentialsException) {
            throw new BadCredentialsException("Incorrect Email Or Password.");
        }

        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
        Optional<User> optionalUser = userRepository.findByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if (optionalUser.isPresent()) {
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserId(optionalUser.get().getId());
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
        }
        return authenticationResponse;
    }

    @GetMapping("/user/{userId}")
    public User getUserById(@PathVariable("userId") Long userId){
        return userRepository.findById(userId).orElseThrow();
    }

}
