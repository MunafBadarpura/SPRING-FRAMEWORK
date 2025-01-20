package com.munaf.A13_SPRING_SECURITY_1.repositories;

import com.munaf.A13_SPRING_SECURITY_1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
