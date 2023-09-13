package com.MyBank.Smile.Bank.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankResponse {
    private String responseCode;
    private String responseMessage;

//    BankResponse class derives capabilities of AccountInfo class
    private AccountInfo accountInfo;
}
