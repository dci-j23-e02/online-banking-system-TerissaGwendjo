package org.example.online_banking_system.service;

import org.example.online_banking_system.model.Account;
import org.example.online_banking_system.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // Marks this class as a Spring service bean
public class AccountService {
    @Autowired
    private AccountRepository repository;

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
        //deposit limit set to 1000
        final Double MAX_DEPOSIT_LIMIT = 1000.00;

        Account account = repository.findByAccountNumber(accountNumber);
        if (account != null) {
            if (amount > MAX_DEPOSIT_LIMIT) {
                throw new RuntimeException("Deposit amount exceeds the maximum limit of $" + MAX_DEPOSIT_LIMIT);
            }
            Double currentBalance = account.getBalance();
            Double newBalance = currentBalance + amount;
            account.setBalance(newBalance);
            repository.save(account);
        } else {
            throw new RuntimeException("Account not found for account number: " + accountNumber);
        }
    }

    @Transactional
    public void withdrawMoney(Long accountNumber, Double amount) {
        Account account = repository.findByAccountNumber(accountNumber);
        if (account != null) {
            Double currentBalance = account.getBalance();
            if (currentBalance >= amount) {
                Double newBalance = currentBalance - amount;
                account.setBalance(newBalance);
                repository.save(account);
            } else {
                throw new RuntimeException("Insufficient funds");
            }
        } else {
            throw new RuntimeException("Account not found for account number: " + accountNumber);
        }
    }

    @Transactional
    public void transferFunds(Long fromAccountNumber, Long toAccountNumber, Double amount) {
        Account fromAccount = repository.findByAccountNumber(fromAccountNumber);
        Account toAccount = repository.findByAccountNumber(toAccountNumber);

        if (fromAccount == null) {
            throw new RuntimeException("Source account not found for account number: " + fromAccountNumber);
        }

        if (toAccount == null) {
            throw new RuntimeException("Destination account not found for account number: " + toAccountNumber);
        }

        if (fromAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds in source account");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        repository.save(fromAccount);
        repository.save(toAccount);
    }
}
