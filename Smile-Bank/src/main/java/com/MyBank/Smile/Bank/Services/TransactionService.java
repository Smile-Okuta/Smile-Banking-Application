package com.MyBank.Smile.Bank.Services;

import com.MyBank.Smile.Bank.dto.Response.BankResponse;

public interface TransactionService {
    BankResponse creditResponse();
    BankResponse debitResponse();
}
