package com.munaf.A13_SPRING_SECURITY_1.services;


import com.munaf.A13_SPRING_SECURITY_1.entity.User;
import com.munaf.A13_SPRING_SECURITY_1.entity.UserPrincipal;
import com.munaf.A13_SPRING_SECURITY_1.exceptions.ResourceNotFoundException;
import com.munaf.A13_SPRING_SECURITY_1.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
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
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email :  " + username));

        return user;
    }


}
