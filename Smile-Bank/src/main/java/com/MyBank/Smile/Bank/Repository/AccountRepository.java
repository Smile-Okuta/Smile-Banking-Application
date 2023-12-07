package com.MyBank.Smile.Bank.Repository;

import com.MyBank.Smile.Bank.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByAccountNumber(String accountNumber);
    Account findByAccountNumber(String accountRequest);
}
