package com.MyBank.Smile.Bank.Repository;

import com.MyBank.Smile.Bank.Models.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountModel, Long> {
}
