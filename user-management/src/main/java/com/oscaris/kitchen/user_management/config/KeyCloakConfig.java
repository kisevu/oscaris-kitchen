package com.oscaris.kitchen.user_management.config;
/*
*
@author ameda
@project user-management
*
*/


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "jwt.auth.converter")
public class KeyCloakConfig {

    private String resourceId;
    private String principleAttribute;

}
