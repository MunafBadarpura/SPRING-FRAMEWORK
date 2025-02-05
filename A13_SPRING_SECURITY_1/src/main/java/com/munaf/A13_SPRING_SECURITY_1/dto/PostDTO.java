package com.munaf.A13_SPRING_SECURITY_1.dto;

import com.munaf.A13_SPRING_SECURITY_1.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private Long id;
    private String name;
    private String description;

    private UserDTO author;

}
