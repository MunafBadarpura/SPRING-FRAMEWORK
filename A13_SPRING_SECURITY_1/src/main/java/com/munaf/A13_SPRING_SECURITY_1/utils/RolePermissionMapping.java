package com.munaf.A13_SPRING_SECURITY_1.utils;

import com.munaf.A13_SPRING_SECURITY_1.entity.enums.Permissions;
import com.munaf.A13_SPRING_SECURITY_1.entity.enums.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.munaf.A13_SPRING_SECURITY_1.entity.enums.Role.*;
import static com.munaf.A13_SPRING_SECURITY_1.entity.enums.Permissions.*;

import java.util.List;
import java.util.Map;


public class RolePermissionMapping {

    private static final Map<Role, List<Permissions>> rolePermissionsMap = Map.of(
            USER, List.of(POST_VIEW, USER_VIEW),
            CREATOR, List.of(POST_CREATE, POST_UPDATE, POST_DELETE),
            ADMIN, List.of(USER_CREATE,USER_UPDATE, USER_DELETE)
    );


    public static List<SimpleGrantedAuthority> getAuthoritiesForRole(Role role) {
        return rolePermissionsMap.get(role)
                .stream()
                .map(permissions -> new SimpleGrantedAuthority(permissions.name()))
                .toList();
    }

}
