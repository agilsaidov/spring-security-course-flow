package com.projects.spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
                authorizeHttpRequests(request -> request.
                        requestMatchers("/myAccount","/myBalance","/myCards").authenticated().
                        requestMatchers("/notifications").permitAll());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetails() {
        UserDetails user1 = User.builder()
                .username("aqil")
                .password("{noop}1234")
                .roles("USER").build();

        UserDetails user2 = User.builder()
                .username("max")
                .password("{noop}54321")
                .roles("USER").build();

        UserDetails user3 = User.builder()
                .username("alex")
                .password("{noop}12345")
                .roles("USER").build();

        return new InMemoryUserDetailsManager(user1,user2,user3);

    }
}
