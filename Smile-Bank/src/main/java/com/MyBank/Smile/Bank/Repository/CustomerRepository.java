package com.MyBank.Smile.Bank.Repository;

import com.MyBank.Smile.Bank.Models.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long>{
}
