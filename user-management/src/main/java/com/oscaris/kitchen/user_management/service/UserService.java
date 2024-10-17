package com.oscaris.kitchen.user_management.service;
/*
*
@author ameda
@project user-management
*
*/

import com.oscaris.kitchen.user_management.model.NewUserRecord;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.RoleRepresentation;

import java.util.List;


public interface UserService {

    void createUser(NewUserRecord newUserRecord);
    void sendVerificationEmail(String id);
    void deleteUser(String id);
    void forgotPassword(String username);
    UserResource getUser(String id);
    List<RoleRepresentation> getUserRoles(String userId);
}
