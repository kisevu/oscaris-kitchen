package com.oscaris.kitchen.user_management.service;
/*
*
@author ameda
@project user-management
*
*/

public interface RoleService {

    void assignRole(String userId, String roleName);
    void deleteRoleFromUser(String userId,String roleName);
}
