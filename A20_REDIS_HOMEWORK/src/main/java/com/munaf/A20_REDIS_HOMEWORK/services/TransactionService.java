package com.munaf.A20_REDIS_HOMEWORK.services;

import com.munaf.A20_REDIS_HOMEWORK.entities.Account;
import com.munaf.A20_REDIS_HOMEWORK.entities.User;
import com.munaf.A20_REDIS_HOMEWORK.repositories.AccountRepository;
import com.munaf.A20_REDIS_HOMEWORK.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Account transferMoney(Long fromUserId, Long toUserId, Long balance) {
        User fromUser = userRepository.findById(fromUserId).orElse(null);
        User toUser = userRepository.findById(toUserId).orElse(null);

        Account fromAccount = accountRepository.findByUser(fromUser);
        Account toAccount = accountRepository.findByUser(toUser);

        // Make transaction
        Account savedFromAccount = withdrawMoney(fromAccount, balance);
        Account savedToAccount = depositMoney(toAccount, balance);

        return savedFromAccount;
    }


    public Account withdrawMoney(Account fromAccount, Long balance) {
        fromAccount.setAccountBalance(fromAccount.getAccountBalance() - balance);
        return accountRepository.save(fromAccount);
    }


    public Account depositMoney(Account toAccount, Long balance) {
        toAccount.setAccountBalance(toAccount.getAccountBalance() + balance);
        return accountRepository.save(toAccount);
    }
}
