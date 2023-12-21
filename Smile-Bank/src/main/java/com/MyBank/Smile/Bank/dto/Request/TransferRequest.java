package com.MyBank.Smile.Bank.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {
    private String senderAccountNum;
    private String receiverAccountNum;
    private BigDecimal amount;
}
