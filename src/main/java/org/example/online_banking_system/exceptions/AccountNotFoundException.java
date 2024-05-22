package org.example.online_banking_system.exceptions;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(Long accountId) {
        super("Account not found with ID: " + accountId);
    }
}
