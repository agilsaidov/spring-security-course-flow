package com.projects.spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
                .password("{bcrypt}$2a$12$24BPjnUrPWAAoQEPMXCrweIZEM804gDvSmSGyH/PDvZrtA0SBAOke")
                .roles("USER").build();

        UserDetails user2 = User.builder()
                .username("max")
                .password("{bcrypt}$2a$12$1CurC/69lZVXcJYk3AHA3.JanpCPvX/FlPjVlOAa3Wt3Mav1zU2/e")
                .roles("USER").build();

        UserDetails user3 = User.builder()
                .username("alex")
                .password("{bcrypt}$2a$12$yEul6RTR4b5OQM92Mh220.lOUaJd.OBJcdI9EWdx3tiWhDqb7A2BW")
                .roles("USER").build();

        return new InMemoryUserDetailsManager(user1,user2,user3);

    }

}
