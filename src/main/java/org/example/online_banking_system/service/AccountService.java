package org.example.online_banking_system.service;

import exceptions.InsufficientFundsException;
import org.example.online_banking_system.model.Account;
import org.example.online_banking_system.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository repository;

    @Transactional
    public Account openNewAccount(Account account) {
        return repository.save(account);
    }

    @Transactional
    public void depositMoney(Long accountId, Double amount) {
        repository.deposit(accountId, amount);
    }

    @Transactional
    public void withdrawMoney(Long accountId, Double amount) {
        Double balance = repository.findBalanceByAccountId(accountId);
        if (balance == null || balance < amount) {
            throw new InsufficientFundsException(accountId);
        }
        repository.withdraw(accountId, amount);
    }

    @Transactional
    public void transferFunds(Long fromAccountId, Long toAccountId, Double amount) {
        withdrawMoney(fromAccountId, amount);
        depositMoney(toAccountId, amount);
    }
}
