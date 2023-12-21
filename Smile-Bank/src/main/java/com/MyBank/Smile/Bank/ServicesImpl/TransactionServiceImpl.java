package com.MyBank.Smile.Bank.ServicesImpl;

import com.MyBank.Smile.Bank.dto.Response.BankResponse;
import com.MyBank.Smile.Bank.Services.TransactionService;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Override
    public BankResponse creditResponse() {
        return null;
    }

    @Override
    public BankResponse debitResponse() {
        return null;
    }
}
