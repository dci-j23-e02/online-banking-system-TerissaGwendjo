package org.example.online_banking_system.repository;

import org.example.online_banking_system.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AccountRepository extends JpaRepository <Account, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Account a SET a.balance = a.balance + :amount WHERE a.id = :accountId")
    void deposit(@Param("accountId") Long accountId, @Param("amount") Double amount);

    @Modifying
    @Transactional
    @Query("UPDATE Account a SET a.balance = a.balance - :amount WHERE a.id = :accountId")
    void withdraw(@Param("accountId") Long accountId, @Param("amount") Double amount);

    @Query("SELECT a.balance FROM Account a WHERE a.id = :accountId")
    Double findBalanceByAccountId(@Param("accountId") Long accountId);

    Account findByAccountNumber(Long accountNumber);
    //Spring Data JPA will automatically generate the query to find an account by its accountNumber.




}
