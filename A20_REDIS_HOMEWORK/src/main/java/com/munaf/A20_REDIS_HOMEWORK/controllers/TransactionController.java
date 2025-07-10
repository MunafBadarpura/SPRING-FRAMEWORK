package com.munaf.A20_REDIS_HOMEWORK.controllers;

import com.munaf.A20_REDIS_HOMEWORK.entities.Account;
import com.munaf.A20_REDIS_HOMEWORK.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;


    @PostMapping("/from/{fromUserId}/to/{toUserId}/balance/{balance}")
    public Account transferMoney(@PathVariable Long fromUserId, @PathVariable Long toUserId, @PathVariable Long balance) {
        return transactionService.transferMoney(fromUserId, toUserId, balance);
    }

}
