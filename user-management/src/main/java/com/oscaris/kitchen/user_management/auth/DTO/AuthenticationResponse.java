package com.oscaris.kitchen.user_management.auth.DTO;
/*
*
@author ameda
@project user-management
*
*/


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationResponse {
    private String token;
}
