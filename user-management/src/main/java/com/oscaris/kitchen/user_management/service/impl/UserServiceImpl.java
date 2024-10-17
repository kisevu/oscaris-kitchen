package com.oscaris.kitchen.user_management.service.impl;
/*
*
@author ameda
@project user-management
*
*/

import com.oscaris.kitchen.user_management.model.NewUserRecord;
import com.oscaris.kitchen.user_management.service.UserService;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final Keycloak keycloak;

    @Value("${app.keycloak.realm}")
    private String realm;

    @Override
    public void createUser(NewUserRecord newUserRecord) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEnabled(true);
        userRepresentation.setFirstName(newUserRecord.firstName());
        userRepresentation.setLastName(newUserRecord.lastName());
        userRepresentation.setUsername(newUserRecord.username());
        userRepresentation.setEmail(newUserRecord.username());
        userRepresentation.setEmailVerified(false);

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue(newUserRecord.password());
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);

        userRepresentation.setCredentials(List.of(credentialRepresentation));

        UsersResource usersResource =  getUsersResource();
        Response response = usersResource.create(userRepresentation);

        log.info("Status code: "+response.getStatus());

        if(!Objects.equals(201,response.getStatus())){
            throw new RuntimeException("Status code: "+response.getStatus());
        }
        log.info("New user has been created");
        /*
        * send verification email to complete account setup
        * */
        List<UserRepresentation> userRepresentations =
                usersResource.searchByUsername(newUserRecord.username(), true);
        UserRepresentation userRepresentation1 = userRepresentations.get(0);
        sendVerificationEmail(userRepresentation1.getId());
    }

    @Override
    public void sendVerificationEmail(String id) {
        UsersResource usersResource = getUsersResource();
        usersResource.get(id).sendVerifyEmail();
    }

    @Override
    public void deleteUser(String id) {
        UsersResource usersResource = getUsersResource();
        usersResource.delete(id);
    }

    @Override
    public void forgotPassword(String username) {
        UsersResource usersResource = getUsersResource();
        List<UserRepresentation> userRepresentations =
                usersResource.searchByUsername(username,true);
        UserRepresentation userRepresentation1 = userRepresentations.get(0);
        UserResource userResource = usersResource.get(userRepresentation1.getId());
        userResource.executeActionsEmail(List.of("UPDATE_PASSWORD"));
    }

    @Override
    public UserResource getUser(String id) {
        UsersResource usersResource = getUsersResource();
        return usersResource.get(id);
    }

    @Override
    public List<RoleRepresentation> getUserRoles(String userId) {
        return getUser(userId).roles().realmLevel().listAll();
    }

    public UsersResource getUsersResource(){
        return keycloak.realm(realm).users();
    }

}
