package com.MyBank.Smile.Bank.Dto;

import com.MyBank.Smile.Bank.Models.TransactionModel;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String phoneNumber;
    private String alternativePhoneNumber;
    private String emailAddress;
    private String accountType;
    private static String accountNumber;
    private BigDecimal accountBalance;
    @OneToMany
    private List<TransactionModel> transactions;
}
