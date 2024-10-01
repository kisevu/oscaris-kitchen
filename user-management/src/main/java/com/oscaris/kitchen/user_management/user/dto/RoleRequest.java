package com.oscaris.kitchen.user_management.user.dto;
/*
*
@author ameda
@project user-management
*
*/

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleRequest {
    private String roleName;
}
