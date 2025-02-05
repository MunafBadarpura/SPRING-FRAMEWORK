package com.munaf.SPRING_SECURITY_PRAC.repositories;

import com.munaf.SPRING_SECURITY_PRAC.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> getByEmail(String username);
}
