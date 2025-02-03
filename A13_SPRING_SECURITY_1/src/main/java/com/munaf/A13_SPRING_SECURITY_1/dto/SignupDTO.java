package com.munaf.A13_SPRING_SECURITY_1.dto;

import com.munaf.A13_SPRING_SECURITY_1.entity.enums.Permissions;
import com.munaf.A13_SPRING_SECURITY_1.entity.enums.Role;
import lombok.Data;

import java.util.List;

@Data
public class SignupDTO {

    private String name;
    private String email;
    private String password;
    private List<Role> roles;
    private List<Permissions> permissions;

}
