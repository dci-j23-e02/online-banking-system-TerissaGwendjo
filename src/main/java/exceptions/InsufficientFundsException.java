package exceptions;

public class InsufficientFundsException extends RuntimeException{
    public InsufficientFundsException(Long accountId) {
        super("Insufficient funds in account with ID: " + accountId);
    }
}
