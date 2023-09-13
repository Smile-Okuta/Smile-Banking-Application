package com.MyBank.Smile.Bank.Repository;

import com.MyBank.Smile.Bank.Models.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long>{
   boolean existsByNIN(String nin);

}
