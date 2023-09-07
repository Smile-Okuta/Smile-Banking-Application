package com.MyBank.Smile.Bank.Repository;

import com.MyBank.Smile.Bank.Models.CustomerModel;
import com.MyBank.Smile.Bank.Models.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, Long> {
}
