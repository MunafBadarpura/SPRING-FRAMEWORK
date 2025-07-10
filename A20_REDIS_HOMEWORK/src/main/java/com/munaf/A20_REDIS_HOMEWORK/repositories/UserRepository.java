package com.munaf.A20_REDIS_HOMEWORK.repositories;

import com.munaf.A20_REDIS_HOMEWORK.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
