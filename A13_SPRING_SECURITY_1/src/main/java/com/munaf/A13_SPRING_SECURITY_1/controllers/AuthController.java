package com.munaf.A13_SPRING_SECURITY_1.controllers;

import com.munaf.A13_SPRING_SECURITY_1.dto.SignupDTO;
import com.munaf.A13_SPRING_SECURITY_1.dto.UserDTO;
import com.munaf.A13_SPRING_SECURITY_1.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService userService) {
        this.authService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signupUser(@RequestBody SignupDTO signupDTO) {
        return ResponseEntity.ok(authService.signupUser(signupDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody SignupDTO loginDTO, HttpServletResponse httpServletResponse) {
        String jwtToken = authService.loginUser(loginDTO);

        Cookie cookie = new Cookie("jwtToken", jwtToken);
        cookie.setHttpOnly(true);
        httpServletResponse.addCookie(cookie);

        return ResponseEntity.ok(jwtToken);
    }
}
