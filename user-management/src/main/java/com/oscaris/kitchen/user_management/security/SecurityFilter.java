package com.oscaris.kitchen.user_management.security;
/*
*
@author ameda
@project user-management
*
*/

import com.oscaris.kitchen.user_management.config.converter.JwtAuthConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityFilter {

    private JwtAuthConverter jwtAuthConverter;

//    @Bean
//    public SecurityFilterChain filter(HttpSecurity httpSecurity) throws Exception{
//        httpSecurity
//                .authorizeHttpRequests()
//                .requestMatchers(HttpMethod.GET,"/api/admin/**").hasRole("admin")
//                .requestMatchers(HttpMethod.GET,"/api/user/**").hasRole("user")
//                .anyRequest()
//                .authenticated();
//        httpSecurity
//                .oauth2ResourceServer()
//                .jwt()
//                .jwtAuthenticationConverter(jwtAuthConverter);
//        httpSecurity
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        return httpSecurity.build();
//    }
//deprecated
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/api/admin").hasRole("admin")
                        .requestMatchers(HttpMethod.GET, "/api/user").hasRole("user")
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter))
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }
}
