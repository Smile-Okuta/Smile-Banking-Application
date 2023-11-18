package com.MyBank.Smile.Bank.Repository;

import com.MyBank.Smile.Bank.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
boolean existByNIN(String userRequest);
}
