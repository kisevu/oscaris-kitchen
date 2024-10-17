package com.oscaris.kitchen.user_management.api;
/*
*
@author ameda
@project user-management
*
*/

import com.oscaris.kitchen.user_management.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RolesAPI {


    private final RoleService roleService;

    @PutMapping("/assign/users/{userId}")
    public ResponseEntity<?> assignRoles(@PathVariable String userId,
                                         @RequestParam String roleName){
        roleService.assignRole(userId,roleName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/unassign/users/{userId}")
    public ResponseEntity<?> unassignRole(@PathVariable String userId,
                                         @RequestParam String roleName){
        roleService.deleteRoleFromUser(userId,roleName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
