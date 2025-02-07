package com.munaf.SPRING_SECURITY_PRAC.controller;

import com.munaf.SPRING_SECURITY_PRAC.dtos.LoginDTO;
import com.munaf.SPRING_SECURITY_PRAC.dtos.LoginResponseDTO;
import com.munaf.SPRING_SECURITY_PRAC.dtos.SignupDTO;
import com.munaf.SPRING_SECURITY_PRAC.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public HashMap<String, Object> signup(@RequestBody SignupDTO signupDTO) {
        return authService.signup(signupDTO);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        return authService.login(loginDTO, response);
    }


    @PostMapping("/refresh")
    public String refresh(HttpServletRequest request) {
        return authService.refresh(request);
    }

}
