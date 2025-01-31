package com.munaf.SPRING_SECURITY_PRAC.repositories;

import com.munaf.SPRING_SECURITY_PRAC.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {


    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByEmailOrUsername(String email, String username);
}
