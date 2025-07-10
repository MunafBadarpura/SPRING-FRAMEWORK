package com.munaf.A20_REDIS_HOMEWORK.services;

import com.munaf.A20_REDIS_HOMEWORK.entities.Account;
import com.munaf.A20_REDIS_HOMEWORK.entities.User;
import com.munaf.A20_REDIS_HOMEWORK.repositories.AccountRepository;
import com.munaf.A20_REDIS_HOMEWORK.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;



    public User signup(User user) {
        // Creating User
        User newUser = User.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .accounts(new ArrayList<>())
                .build();

        newUser = userRepository.save(newUser);

        // Creating Account
        Account account = Account
                .builder()
                .accountBalance(1000L)
                .accountNumber((long) (Math.random() * 9000000000L) + 1000000000L)
                .user(newUser)
                .build();

        account = accountRepository.save(account);
        newUser.getAccounts().add(account);

        return userRepository.save(newUser);
    }
}
