package com.munaf.SPRING_SECURITY_PRAC.services;

import com.munaf.SPRING_SECURITY_PRAC.entities.UserEntity;
import com.munaf.SPRING_SECURITY_PRAC.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return userRepo.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found with : " +username));
    }

    public UserEntity getUserById(Long userId) {
            return userRepo.findById(userId)
                    .orElseThrow(() -> new BadCredentialsException("User Not Found with : " +userId));
    }

    public UserEntity getUserByEmail(String userEmail) {
        return userRepo.findByEmail(userEmail).orElse(null);
    }

    public UserEntity save(UserEntity user) {
        return userRepo.save(user);
    }
}
