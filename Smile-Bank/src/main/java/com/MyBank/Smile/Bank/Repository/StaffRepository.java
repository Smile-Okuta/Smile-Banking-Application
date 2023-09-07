package com.MyBank.Smile.Bank.Repository;

import com.MyBank.Smile.Bank.Models.CustomerModel;
import com.MyBank.Smile.Bank.Models.StaffModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<StaffModel, Long> {
}
