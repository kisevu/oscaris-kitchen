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
}
