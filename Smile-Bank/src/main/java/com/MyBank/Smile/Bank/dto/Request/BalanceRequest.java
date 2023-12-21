package com.MyBank.Smile.Bank.dto.Request;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class BalanceRequest {
    private String accountNumber;
    private BigDecimal accountBalance;
}
