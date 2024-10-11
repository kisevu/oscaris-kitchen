package com.oscaris.kitchen.user_management.model;
/*
*
@author ameda
@project user-management
*
*/

public record NewUserRecord(String username,
                            String password,
                            String firstName,
                            String lastName) {
}
