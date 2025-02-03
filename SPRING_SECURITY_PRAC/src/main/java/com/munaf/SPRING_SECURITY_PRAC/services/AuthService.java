package com.munaf.SPRING_SECURITY_PRAC.services;

import com.munaf.SPRING_SECURITY_PRAC.dtos.LoginResponseDTO;
import com.munaf.SPRING_SECURITY_PRAC.entities.UserEntity;
import com.munaf.SPRING_SECURITY_PRAC.repositories.UserRepo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final SessionService sessionService;


    public UserEntity signup(UserEntity userEntity) {
        Optional<UserEntity> user = userRepo.findByEmailOrUsername(userEntity.getEmail(), userEntity.getUsername());
        if (user.isPresent()) throw new BadCredentialsException("User Already Exist with this email or password");

        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepo.save(userEntity);
    }

    public LoginResponseDTO login(UserEntity userEntity, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userEntity.getUsername(), userEntity.getPassword())
        );

        UserEntity user = (UserEntity) authentication.getPrincipal();
        String accessToken = jwtService.accessToken(user);
        String refreshToken = jwtService.refreshToken(user);
        sessionService.generateSession(user, refreshToken);

        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return new LoginResponseDTO(user.getId(), accessToken, refreshToken);
    }

    public LoginResponseDTO refresh(HttpServletRequest request) {

        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("refreshToken"))
                .map(cookie -> cookie.getValue())
                .findAny().orElseThrow(() -> new AuthenticationServiceException("Refresh Token Is Invalid"));

        sessionService.validateSession(refreshToken);
        Long userId = jwtService.userIdFromJwtToken(refreshToken);
        UserEntity user = userService.getUserById(userId);
        String accessToken = jwtService.accessToken(user);

        return new LoginResponseDTO(user.getId(), accessToken, refreshToken);
    }

    public String logout(HttpServletRequest request) {
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("refreshToken"))
                .map(cookie -> cookie.getValue())
                .findFirst()
                .orElseThrow(() -> new AuthenticationServiceException("RefreshToken Not Valid In Cookies"));

        return sessionService.deleteSession(refreshToken);
    }
}










