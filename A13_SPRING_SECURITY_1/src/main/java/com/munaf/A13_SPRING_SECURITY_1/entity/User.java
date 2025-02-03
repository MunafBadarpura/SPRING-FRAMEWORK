package com.munaf.A13_SPRING_SECURITY_1.entity;

import com.munaf.A13_SPRING_SECURITY_1.entity.enums.Permissions;
import com.munaf.A13_SPRING_SECURITY_1.entity.enums.Role;
import com.munaf.A13_SPRING_SECURITY_1.utils.RolePermissionMapping;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @ElementCollection(fetch = FetchType.EAGER) // creates a separate table to store the list of roles
    @Enumerated(EnumType.STRING)
    private List<Role> roles;

//    @ElementCollection(fetch = FetchType.EAGER)
//    @Enumerated(EnumType.STRING)
//    private List<Permissions> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<SimpleGrantedAuthority> simpleGrantedAuthorities =  roles.stream()
//                .map(role -> new SimpleGrantedAuthority(("ROLE_"+role.name())))
//                .toList();
//
//        permissions.forEach(permissions -> simpleGrantedAuthorities.add(new SimpleGrantedAuthority(permissions.name())));
//        return simpleGrantedAuthorities;

        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        roles.forEach(role -> {
            simpleGrantedAuthorities.addAll(RolePermissionMapping.getAuthoritiesForRole(role));
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role.name()));
        });
        return simpleGrantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

}
