package com.oscaris.kitchen.user_management.config.converter;
/*
*
@author ameda
@project user-management
*
*/

import com.oscaris.kitchen.user_management.config.KeyCloakConfig;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private KeyCloakConfig properties;
    private JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    public JwtAuthConverter(KeyCloakConfig properties) {
        this.properties = properties;
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = Stream.concat(jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
                        extractRoles(jwt).stream())
                .collect(Collectors.toSet());
        return new JwtAuthenticationToken(jwt, authorities,getPrincipalName(jwt));
    }

    private String getPrincipalName(Jwt jwt){
        String name = JwtClaimNames.SUB;
        if(properties.getPrincipleAttribute()!=null){
            name = properties.getPrincipleAttribute();
        }
        return jwt.getClaim(name);
    }

    //getting roles from token

    private Collection<? extends GrantedAuthority> extractRoles(Jwt jwt){
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        Map<String, Object> resource;
        Collection<String> resourceRoles;

        if(resourceAccess == null
        || (resource = (Map<String,Object>) resourceAccess.get(properties.getResourceId())) == null
        || (resourceRoles  = (Collection<String>) resource.get("roles")) == null){
            return Set.of();
        }

        return resourceRoles.stream()
                .map(r-> new SimpleGrantedAuthority("ROLE_"+r))
                .collect(Collectors.toSet());
    }
}
