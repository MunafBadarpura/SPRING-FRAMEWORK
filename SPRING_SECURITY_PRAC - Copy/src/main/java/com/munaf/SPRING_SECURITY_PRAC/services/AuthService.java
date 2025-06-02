package com.munaf.SPRING_SECURITY_PRAC.services;

import com.munaf.SPRING_SECURITY_PRAC.dtos.LoginDTO;
import com.munaf.SPRING_SECURITY_PRAC.dtos.LoginResponseDTO;
import com.munaf.SPRING_SECURITY_PRAC.dtos.SignupDTO;
import com.munaf.SPRING_SECURITY_PRAC.dtos.UserDto;
import com.munaf.SPRING_SECURITY_PRAC.entities.UserEntity;
import com.munaf.SPRING_SECURITY_PRAC.repositories.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final JwtService jwtService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final SessionService sessionService;

    public AuthService(UserRepository userRepository, UserService userService, JwtService jwtService, ModelMapper modelMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, SessionService sessionService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.jwtService = jwtService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.sessionService = sessionService;
    }

    public UserDto signup(SignupDTO signupDTO) {
        boolean isUserAlreadyExists  = userRepository.existsByEmail(signupDTO.getEmail());
        if (isUserAlreadyExists) throw new RuntimeException("user already exists");

        UserEntity userEntity = modelMapper.map(signupDTO, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity = userRepository.save(userEntity);

        return modelMapper.map(userEntity, UserDto.class);
    }

    public LoginResponseDTO login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );

        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(userEntity);
        String refreshToken = jwtService.generateRefreshToken(userEntity);
        sessionService.generateNewSession(userEntity, refreshToken); // CREATE NEW SESSION

        return new LoginResponseDTO(userEntity.getId(), accessToken, refreshToken);
    }

    public LoginResponseDTO refresh(String refreshToken) {
        Long userId = jwtService.getUserIdFromJwtToken(refreshToken); //validate refresh token
        sessionService.validateSession(refreshToken); // validate session

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new BadCredentialsException("User id not found with id : " + userId));

        String accessToken = jwtService.generateAccessToken(user);
        return new LoginResponseDTO(user.getId(), accessToken, refreshToken);
    }
}
