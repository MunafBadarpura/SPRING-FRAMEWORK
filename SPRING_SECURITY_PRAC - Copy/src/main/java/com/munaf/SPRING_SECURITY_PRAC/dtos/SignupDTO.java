package com.munaf.SPRING_SECURITY_PRAC.dtos;

import com.munaf.SPRING_SECURITY_PRAC.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupDTO {

    private String email;
    private String password;


    public UserEntity signUpDTOtoUserEntity(SignupDTO signupDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(signupDTO.getEmail());
        userEntity.setPassword(signupDTO.getPassword());

        return userEntity;
    }

    public static HashMap<String, Object> userEntityToSignupDTO(UserEntity userEntity) {
        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("Id : ", userEntity.getId());
        hashMap.put("Email : ", userEntity.getEmail());

        return hashMap;
    }

}
