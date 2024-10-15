package com.oscaris.kitchen.user_management.api;

import com.oscaris.kitchen.user_management.model.NewUserRecord;
import com.oscaris.kitchen.user_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String username){
        userService.forgotPassword(username);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
