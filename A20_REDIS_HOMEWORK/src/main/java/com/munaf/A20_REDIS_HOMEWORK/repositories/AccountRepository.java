package com.munaf.A20_REDIS_HOMEWORK.repositories;

import com.munaf.A20_REDIS_HOMEWORK.entities.Account;
import com.munaf.A20_REDIS_HOMEWORK.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUser(User fromUser);
}
