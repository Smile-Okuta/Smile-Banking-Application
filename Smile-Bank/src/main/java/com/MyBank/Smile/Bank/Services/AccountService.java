package com.MyBank.Smile.Bank.Services;

import com.MyBank.Smile.Bank.Enum.AccountType;
import com.MyBank.Smile.Bank.Models.AccountModel;

public interface AccountService {

    AccountModel createAccount(AccountType accountType);
}
