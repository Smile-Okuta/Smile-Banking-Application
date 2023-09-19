package com.MyBank.Smile.Bank.Dto;

import com.MyBank.Smile.Bank.Enum.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {
    private Long customerId;
    private AccountType accountType;
//    private String accountNumber;
//    private BigDecimal accountBalance;
//    @OneToMany
//    private List<TransactionModel> transactions;
}
