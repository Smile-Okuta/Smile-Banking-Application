package com.MyBank.Smile.Bank.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreditDebitRequest {
    private  String accountNumber;
    private BigDecimal amount;
}
