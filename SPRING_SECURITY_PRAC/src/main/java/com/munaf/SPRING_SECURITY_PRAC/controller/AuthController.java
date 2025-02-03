package com.munaf.SPRING_SECURITY_PRAC.controller;

import com.munaf.SPRING_SECURITY_PRAC.dtos.LoginResponseDTO;
import com.munaf.SPRING_SECURITY_PRAC.entities.UserEntity;
import com.munaf.SPRING_SECURITY_PRAC.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public UserEntity signup(@RequestBody UserEntity userEntity) {
        return authService.signup(userEntity);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody UserEntity userEntity, HttpServletResponse response) {
        return authService.login(userEntity, response);
    }

    @PostMapping("/refresh")
    public LoginResponseDTO refresh(HttpServletRequest request) {
        return authService.refresh(request);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        return ResponseEntity.ok(authService.logout(request));
    }

}
