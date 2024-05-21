package org.example.online_banking_system.dto;

public class TransferForm {
    /*The Transfer form class is a DTO (Data transfer Object). It is a design pattern used to transfer data between
    software application subsystems or layers. It's essentially a plain Java object containing fields and
    corresponding getters/setters, but it serves a specific purpose: to transfer data between different parts of the application.*/

    private Long fromAccountId;
    private Long toAccountId;
    private Double amount;

    // Getters and Setters
    public Long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


}
