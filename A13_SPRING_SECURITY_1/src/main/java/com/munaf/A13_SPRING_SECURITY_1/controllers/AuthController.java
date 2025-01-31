package com.munaf.A13_SPRING_SECURITY_1.controllers;

import com.munaf.A13_SPRING_SECURITY_1.dto.LoginResponseDTO;
import com.munaf.A13_SPRING_SECURITY_1.dto.SignupDTO;
import com.munaf.A13_SPRING_SECURITY_1.dto.UserDTO;
import com.munaf.A13_SPRING_SECURITY_1.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

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
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody SignupDTO loginDTO, HttpServletResponse httpServletResponse) {
        LoginResponseDTO loginResponseDTO = authService.loginUser(loginDTO);

        Cookie cookie = new Cookie("refreshToken", loginResponseDTO.getRefreshToken());
        cookie.setHttpOnly(true);
//        cookie.setSecure(true);
        httpServletResponse.addCookie(cookie);

        return ResponseEntity.ok(loginResponseDTO);
    }

    @PostMapping("refresh")
    public ResponseEntity<LoginResponseDTO> refresh(HttpServletRequest request) {
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("refreshToken"))
                .map(cookie -> cookie.getValue()) // Transforms the Cookie object into its corresponding value
                .findFirst().orElseThrow(() -> new AuthenticationServiceException("Refresh token not valid in Cookies"));

        LoginResponseDTO loginResponseDTO = authService.refresh(refreshToken);
        return ResponseEntity.ok(loginResponseDTO);
    }
}
