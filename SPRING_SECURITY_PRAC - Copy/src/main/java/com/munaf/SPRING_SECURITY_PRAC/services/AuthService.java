package com.munaf.SPRING_SECURITY_PRAC.services;

import com.munaf.SPRING_SECURITY_PRAC.dtos.LoginDTO;
import com.munaf.SPRING_SECURITY_PRAC.dtos.SignupDTO;
import com.munaf.SPRING_SECURITY_PRAC.entities.UserEntity;
import com.munaf.SPRING_SECURITY_PRAC.repositories.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

@RestController
public class AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthService(JwtService jwtService, UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public HashMap<String, Object> signup(SignupDTO signupDTO) {
        Optional<UserEntity> optionalUser = userRepository.getByEmail(signupDTO.getEmail());
        if (optionalUser.isPresent()) throw new BadCredentialsException("User Already Exists With Email : " + signupDTO.getEmail());

        signupDTO.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        UserEntity savedUser = userRepository.save(signupDTO.signUpDTOtoUserEntity(signupDTO));

        return SignupDTO.userEntityToSignupDTO(savedUser);
    }

    public String login(LoginDTO loginDTO, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );

        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        String token = jwtService.generateToken(userEntity);

        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);


        return token;
    }
}
