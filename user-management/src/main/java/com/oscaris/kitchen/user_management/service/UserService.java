package com.oscaris.kitchen.user_management.service;
/*
*
@author ameda
@project user-management
*
*/

import com.oscaris.kitchen.user_management.model.NewUserRecord;

public interface UserService {

    void createUser(NewUserRecord newUserRecord);
    void sendVerificationEmail(String userId);
    void deleteUser(String userId);
    void forgotPassword(String username);
}
