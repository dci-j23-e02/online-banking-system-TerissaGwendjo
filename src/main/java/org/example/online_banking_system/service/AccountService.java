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

  /*  @Transactional
    public Account openNewAccount(Account account) {
        return repository.save(account);
    }*/

    @Transactional
    public void openNewAccount(Long accountNumber, String accountType, Double initialDeposit) {
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setAccountType(accountType);
        account.setBalance(initialDeposit);  //initializes the balance attribute of the Account entity with
        // the value of initialDeposit provided from the form.
        repository.save(account);
        //saves the Account entity to the database, ensuring the balance is initialized with the initialDeposit value.

    }






    @Transactional
    public void depositMoney(Long accountNumber, Double amount) {
        Account account = repository.findByAccountNumber(accountNumber);
        if (account != null) {
            Double currentBalance = account.getBalance();
            Double newBalance = currentBalance + amount;
            account.setBalance(newBalance);
            repository.save(account);
        } else {
            throw new RuntimeException("Account not found for account number: " + accountNumber);
        }
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
