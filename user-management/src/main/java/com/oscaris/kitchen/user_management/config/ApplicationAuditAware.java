package com.oscaris.kitchen.user_management.config;
/*
*
@author ameda
@project user-management
*
*/

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class ApplicationAuditAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        //given that we updated the context holder we're able to get the current auditor
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null || !authentication.isAuthenticated() || authentication instanceof
                AnonymousAuthenticationToken){
            //if authentication object is null which means the user is not authenticated
            // or if we do not know the actual user
            return Optional.empty();
        }
//        User principal = (User) authentication.getPrincipal();
        return Optional.ofNullable(authentication.getName());
    }
}
