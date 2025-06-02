package com.munaf.SPRING_SECURITY_PRAC.entities;

import com.munaf.SPRING_SECURITY_PRAC.entities.enums.Permissions;
import com.munaf.SPRING_SECURITY_PRAC.entities.enums.Role;
import com.munaf.SPRING_SECURITY_PRAC.utils.RolePermissionMapping;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserEntity implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        roles.forEach(role -> {
            Set<SimpleGrantedAuthority> permissons = RolePermissionMapping.getAuthoritiesForRole(role);
            authorities.addAll(permissons);
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        });

        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
