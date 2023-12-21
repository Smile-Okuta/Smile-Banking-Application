package com.MyBank.Smile.Bank.dto.Response;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountInfo {
    private String accountName;
    private BigDecimal accountBalance;
    private String accountNumber;
}
