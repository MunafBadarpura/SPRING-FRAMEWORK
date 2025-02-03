package com.munaf.A13_SPRING_SECURITY_1.repositories;

import com.munaf.A13_SPRING_SECURITY_1.entity.Session;
import com.munaf.A13_SPRING_SECURITY_1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByUser(User user);

    Optional<Session> findByRefreshToken(String refreshToken);

    void deleteByRefreshToken(String refreshToken);
}
