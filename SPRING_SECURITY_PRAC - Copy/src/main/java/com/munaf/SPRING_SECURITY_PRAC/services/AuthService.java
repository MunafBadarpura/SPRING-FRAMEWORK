package com.munaf.SPRING_SECURITY_PRAC.services;

import com.munaf.SPRING_SECURITY_PRAC.dtos.LoginDTO;
import com.munaf.SPRING_SECURITY_PRAC.dtos.LoginResponseDTO;
import com.munaf.SPRING_SECURITY_PRAC.dtos.SignupDTO;
import com.munaf.SPRING_SECURITY_PRAC.entities.UserEntity;
import com.munaf.SPRING_SECURITY_PRAC.repositories.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

@RestController
public class AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthService(JwtService jwtService, UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserService userService) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    public HashMap<String, Object> signup(SignupDTO signupDTO) {
        Optional<UserEntity> optionalUser = userRepository.getByEmail(signupDTO.getEmail());
        if (optionalUser.isPresent()) throw new BadCredentialsException("User Already Exists With Email : " + signupDTO.getEmail());

        signupDTO.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        UserEntity savedUser = userRepository.save(signupDTO.signUpDTOtoUserEntity(signupDTO));

        return SignupDTO.userEntityToSignupDTO(savedUser);
    }

    public LoginResponseDTO login(LoginDTO loginDTO, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );

        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(userEntity);
        String refreshToken = jwtService.generateRefreshToken(userEntity);

        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return new LoginResponseDTO(userEntity.getId(), accessToken, refreshToken);
    }

    public String refresh(HttpServletRequest request) {

        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("refreshToken"))
                .map(cookie -> cookie.getValue())
                .findFirst()
                .orElseThrow(() -> new AuthenticationServiceException("Refresh Token Not Found In Cookies"));

        Long userId = jwtService.getUserIdFromToken(refreshToken);
        UserEntity userEntity = userService.getUserById(userId);

        return jwtService.generateAccessToken(userEntity);
    }
}
