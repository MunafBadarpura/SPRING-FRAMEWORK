package com.munaf.SPRING_SECURITY_PRAC.utils;

import com.munaf.SPRING_SECURITY_PRAC.entities.enums.Permission;
import com.munaf.SPRING_SECURITY_PRAC.entities.enums.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Map;

import static com.munaf.SPRING_SECURITY_PRAC.entities.enums.Permission.*;
import static com.munaf.SPRING_SECURITY_PRAC.entities.enums.Role.*;

public class RolePermissionMapping {

         private static final Map<Role, List<Permission>> roleMapping = Map.of(
                 USER, List.of(POST_VIEW, USER_VIEW),
                 CREATOR, List.of(POST_CREATE, POST_UPDATE, POST_DELETE),
                 ADMIN, List.of(USER_CREATE, USER_VIEW, USER_UPDATE, USER_DELETE)
         );


        public static List<SimpleGrantedAuthority> getAuthorities(Role role) {
            return roleMapping.get(role)
                    .stream()
                    .map(permission -> new SimpleGrantedAuthority(permission.name()))
                    .toList();
        }

}
