package com.oscaris.kitchen.user_management.api;

import com.oscaris.kitchen.user_management.model.NewUserRecord;
import com.oscaris.kitchen.user_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
*
@author ameda
@project user-management
*
*/
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserAPI {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody NewUserRecord newUserRecord){
        userService.createUser(newUserRecord);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}/send-verification-email")
    public ResponseEntity<?> sendVerificationEmail(@PathVariable String id){
        userService.sendVerificationEmail(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
