package com.MyBank.Smile.Bank.Services;

import com.MyBank.Smile.Bank.dto.Request.AccountRequest;
import com.MyBank.Smile.Bank.dto.Request.BalanceRequest;
import com.MyBank.Smile.Bank.dto.Request.TransferRequest;
import com.MyBank.Smile.Bank.dto.Response.AccountInfo;
import com.MyBank.Smile.Bank.dto.Response.BankResponse;
import com.MyBank.Smile.Bank.dto.Request.CreditDebitRequest;
import com.MyBank.Smile.Bank.dto.Response.CreateAccountResponse;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    CreateAccountResponse createAccount(AccountRequest accountRequest);
    AccountInfo balanceEnquiry (BalanceRequest balanceRequest);
    BankResponse creditAccount(CreditDebitRequest creditRequest);
    BankResponse debitAccount(CreditDebitRequest debitRequest);
    BankResponse transfer (TransferRequest transferRequest);
}
