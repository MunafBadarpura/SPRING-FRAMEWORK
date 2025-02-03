package com.munaf.SPRING_SECURITY_PRAC.repositories;

import com.munaf.SPRING_SECURITY_PRAC.entities.Session;
import com.munaf.SPRING_SECURITY_PRAC.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SessionRepo extends JpaRepository<Session, Long> {
    List<Session> findByUserEntity(UserEntity userEntity);

    Optional<Session> findByRefreshToken(String refreshToken);
}
