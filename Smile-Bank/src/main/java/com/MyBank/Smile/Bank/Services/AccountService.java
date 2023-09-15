package com.MyBank.Smile.Bank.Services;

import com.MyBank.Smile.Bank.Models.AccountModel;
import com.MyBank.Smile.Bank.Models.CustomerModel;

import java.util.List;

public interface AccountService {
    List<AccountModel> createAccount(CustomerModel newCustomer);

}
