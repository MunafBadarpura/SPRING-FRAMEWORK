package com.munaf.A13_SPRING_SECURITY_1.services;


import com.munaf.A13_SPRING_SECURITY_1.entity.User;
import com.munaf.A13_SPRING_SECURITY_1.exceptions.ResourceNotFoundException;
import com.munaf.A13_SPRING_SECURITY_1.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new BadCredentialsException("User not found with email :  " + username));

        return user;
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id :  " + userId));
    }

/*
    UserDetailsService interface, which is part of the Spring Security framework.
    It is primary purpose is to load user
    This method is used by Spring Security to fetch user details (such as username, password, and authorities)
    from a database during the authentication process
*/


}
