package com.MyBank.Smile.Bank.dto.Response;

import com.MyBank.Smile.Bank.Models.enums.AccountType;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class CreateAccountResponse {
    private String message;
    private String accountNumber;
    private AccountType accountType;
}
