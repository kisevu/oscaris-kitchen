package com.oscaris.kitchen.user_management.service.impl;
/*
*
@author ameda
@project user-management
*
*/

import com.oscaris.kitchen.user_management.service.RoleService;
import com.oscaris.kitchen.user_management.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final UserService userService;
    private final Keycloak keycloak;

    @Value("${app.keycloak.realm}")
    private String realm;


    /*
    * We search for the passed role and then assign a representation
    * on keycloak client make sure to add the admin-role role together
    *  with a manage-users role for this to work
    * */
    @Override
    public void assignRole(String userId, String roleName) {
        UserResource user = userService.getUser(userId);
        RolesResource rolesResource = getRolesResource();
        RoleRepresentation representation = rolesResource.get(roleName).toRepresentation();
        user.roles().realmLevel().add(Collections.singletonList(representation));
    }

    @Override
    public void deleteRoleFromUser(String userId, String roleName) {
        UserResource user = userService.getUser(userId);
        RolesResource rolesResource = getRolesResource();
        RoleRepresentation representation = rolesResource.get(roleName).toRepresentation();
        user.roles().realmLevel().remove(Collections.singletonList(representation));
    }


    /*
    * returns a bunch of roles available
    * */
    private RolesResource getRolesResource(){
        return keycloak.realm(realm).roles();
    }
}
