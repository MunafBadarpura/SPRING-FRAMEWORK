package com.munaf.SPRING_SECURITY_PRAC.utils;

import com.munaf.SPRING_SECURITY_PRAC.entities.enums.Permissions;
import com.munaf.SPRING_SECURITY_PRAC.entities.enums.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.munaf.SPRING_SECURITY_PRAC.entities.enums.Role.*;
import static com.munaf.SPRING_SECURITY_PRAC.entities.enums.Permissions.*;

public class RolePermissionMapping {

    private static final Map<Role, Set<Permissions>> rolePermissionMapping = Map.of(
            USER, Set.of(POST_VIEW),
            CREATOR, Set.of(POST_VIEW, POST_CREATE),
            ADMIN, Set.of(POST_VIEW, POST_CREATE, POST_UPDATE, POST_DELETE)
    );


    public static Set<SimpleGrantedAuthority> getAuthoritiesForRole(Role role) {
        Set<Permissions> permissions = rolePermissionMapping.get(role);

        return permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
    }

}
