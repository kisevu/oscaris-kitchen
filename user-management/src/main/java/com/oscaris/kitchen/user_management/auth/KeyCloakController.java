package com.oscaris.kitchen.user_management.auth;
/*
*
@author ameda
@project user-management
*
*/

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class KeyCloakController {

    @GetMapping("/getAdminDetails")
    public ResponseEntity<String> getAdminDetails(){
        return ResponseEntity.ok("Hello Admin");
    }

    @GetMapping("/getUserDetails")
    public ResponseEntity<String> getUserDetails(){
        return ResponseEntity.ok("Hello User");
    }

}
