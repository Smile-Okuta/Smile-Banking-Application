package com.MyBank.Smile.Bank.Dto.Response;

import com.MyBank.Smile.Bank.Models.TransactionModel;
import com.MyBank.Smile.Bank.Models.enums.AccountStatus;
import com.MyBank.Smile.Bank.Models.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankResponse {
    private String responseCode;
    private String responseMessage;

//    BankResponse class derives capabilities of AccountInfo class
    private AccountInfo accountInfo;

    private AccountType accountType;
    private String NIN;
    private String accountNumber;
    private AccountStatus status;
    private BigDecimal accountBalance;
    private List<TransactionModel> transactionModels;
}
