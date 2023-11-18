package com.MyBank.Smile.Bank.Services;

import com.MyBank.Smile.Bank.Dto.AccountRequest;
import com.MyBank.Smile.Bank.Dto.BankResponse;
import com.MyBank.Smile.Bank.Dto.UserRequest;

public interface UserService {

//    AccountModel createAccount(AccountType accountType);

    BankResponse createAccount(UserRequest userRequest);
}
