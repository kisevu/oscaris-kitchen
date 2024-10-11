package com.oscaris.kitchen.user_management.auth;
/*
*
@author ameda
@project user-management
*
*/

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class KeyCloakController {

    @GetMapping("/admin")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<String> getAdminDetails(){
        return ResponseEntity.ok("Hello Admin");
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('user')")
    public ResponseEntity<String> getUserDetails(){
        return ResponseEntity.ok("Hello User");
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal Jwt jwt){
        String email = jwt.getClaimAsString("email");
        String username = jwt.getClaimAsString("preferred_username");
        UserDTO dto = UserDTO.builder()
                .email(email)
                .username(username)
                .build();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
