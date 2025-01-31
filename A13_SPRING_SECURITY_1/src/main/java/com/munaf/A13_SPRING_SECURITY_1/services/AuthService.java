package com.munaf.A13_SPRING_SECURITY_1.services;

import com.munaf.A13_SPRING_SECURITY_1.dto.LoginResponseDTO;
import com.munaf.A13_SPRING_SECURITY_1.dto.SignupDTO;
import com.munaf.A13_SPRING_SECURITY_1.dto.UserDTO;
import com.munaf.A13_SPRING_SECURITY_1.entity.User;
import com.munaf.A13_SPRING_SECURITY_1.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserDTO signupUser(SignupDTO signupDTO) {
        Optional<User> optionalUser = userRepository.findByEmail(signupDTO.getEmail());
        if (optionalUser.isPresent()) throw new BadCredentialsException("User Already Exists With This Email");

        signupDTO.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        User savedUser = userRepository.save(modelMapper.map(signupDTO, User.class));
        return modelMapper.map(savedUser, UserDTO.class);
    }

    public LoginResponseDTO loginUser(SignupDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        String accessToken =  jwtService.generateAccessToken(user);
        String refreshToken =  jwtService.generateRefreshToken(user);

        return new LoginResponseDTO(user.getId(), accessToken, refreshToken);
    }

    public LoginResponseDTO refresh(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        User user = userService.getUserById(userId);

        String accessToken = jwtService.generateAccessToken(user);
        return new LoginResponseDTO(user.getId(), accessToken, refreshToken);
    }
}
