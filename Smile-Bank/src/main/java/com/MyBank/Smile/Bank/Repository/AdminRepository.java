package com.MyBank.Smile.Bank.Repository;

import com.MyBank.Smile.Bank.Models.AdminModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminModel, Long> {
}
