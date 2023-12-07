package com.MyBank.Smile.Bank.Services;

import com.MyBank.Smile.Bank.Dto.Request.AccountRequest;
import com.MyBank.Smile.Bank.Dto.Request.TransferRequest;
import com.MyBank.Smile.Bank.Dto.Response.BankResponse;
import com.MyBank.Smile.Bank.Dto.Request.CreditDebitRequest;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    BankResponse createAccount(AccountRequest accountRequest);

    BankResponse balanceEnquiry(AccountRequest accountRequest);

    BankResponse creditAccount(CreditDebitRequest creditRequest);
    BankResponse debitAccount(CreditDebitRequest debitRequest);
    BankResponse transfer (TransferRequest transferRequest);
}
