package org.example.online_banking_system.repository;

import org.example.online_banking_system.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
/**
 * This interface extends JpaRepository provided by Spring Data JPA.
 * JpaRepository provides basic CRUD (Create, Read, Update, Delete) operations
 * for entities.
 */
public interface AccountRepository extends JpaRepository <Account, Long> {

    Account findByAccountNumber(Long accountNumber);
    //Spring Data JPA will automatically generate the query to find an account by its accountNumber.

    /**
     * This method finds an Account entity based on the provided account number.
     * It leverages Spring Data JPA's findBy convention to automatically generate
     * a query based on the method name and parameter.
     *
     * @param accountNumber The unique identifier for the account to be retrieved.
     * @return An Account object if found, null otherwise.
     */


}
