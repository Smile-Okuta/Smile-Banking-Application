package com.MyBank.Smile.Bank.Dto;

import com.MyBank.Smile.Bank.Enum.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {
    private String firstName;
    private String lastName;
    private String NIN;
    private AccountType accountType;
    private String accountNumber;
}
