package com.projects.spring_security.config;

import com.projects.spring_security.CustomerRepository;
import com.projects.spring_security.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findCustomerByUsername(username).orElseThrow(() -> new
                UsernameNotFoundException("User details not found for user: " + username));
        List<GrantedAuthority> grantedAuthorities = List.of(new SimpleGrantedAuthority(customer.getRole()));
        return new User(customer.getUsername(),customer.getPassword(),grantedAuthorities);
    }
}
