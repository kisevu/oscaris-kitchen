package com.oscaris.kitchen.user_management.api;

import com.oscaris.kitchen.user_management.model.NewUserRecord;
import com.oscaris.kitchen.user_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
