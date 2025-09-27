package com.projects.spring_security.controller;

import com.projects.spring_security.CustomerRepository;
import com.projects.spring_security.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Customer customer) {
        try {

            String encodedPassword = passwordEncoder.encode(customer.getPassword());
            customer.setPassword(encodedPassword);
            Customer savedCustomer = customerRepository.save(customer);

            if(savedCustomer.getId() > 0){
                return new ResponseEntity<>("Customer registered successfully", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Registration failed", HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
