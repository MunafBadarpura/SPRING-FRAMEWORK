package com.munaf.SPRING_SECURITY_PRAC.controller;

import com.munaf.SPRING_SECURITY_PRAC.dtos.LoginDTO;
import com.munaf.SPRING_SECURITY_PRAC.dtos.LoginResponseDTO;
import com.munaf.SPRING_SECURITY_PRAC.dtos.SignupDTO;
import com.munaf.SPRING_SECURITY_PRAC.dtos.UserDto;
import com.munaf.SPRING_SECURITY_PRAC.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupDTO signupDTO) {
        return new ResponseEntity<>(authService.signup(signupDTO), HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO, HttpServletResponse httpServletResponse) {
        LoginResponseDTO loginResponseDTO = authService.login(loginDTO);

        // SET COOKIE FOR REFRESH TOKEN
        Cookie refreshTokenCookie = new Cookie("refreshToken", loginResponseDTO.getRefreshToken());
        refreshTokenCookie.setHttpOnly(true);
        httpServletResponse.addCookie(refreshTokenCookie);

        return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
    }


    @PostMapping("refresh")
    public ResponseEntity<LoginResponseDTO> refresh(HttpServletRequest httpServletRequest) {
        Cookie[] cookies = httpServletRequest.getCookies();
        String refreshToken = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals("refreshToken"))
                .map(cookie -> cookie.getValue())
                .findFirst()
                .orElseThrow(() -> new AuthenticationServiceException("Refresh Token Is Invalid"));

        return new ResponseEntity<>(authService.refresh(refreshToken), HttpStatus.OK);
    }

}
