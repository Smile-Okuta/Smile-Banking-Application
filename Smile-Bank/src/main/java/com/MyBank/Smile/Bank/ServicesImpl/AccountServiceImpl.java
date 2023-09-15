package com.MyBank.Smile.Bank.ServicesImpl;

import com.MyBank.Smile.Bank.Enum.AccountEnum;
import com.MyBank.Smile.Bank.Models.AccountModel;
import com.MyBank.Smile.Bank.Models.CustomerModel;
import com.MyBank.Smile.Bank.Repository.AccountRepository;
import com.MyBank.Smile.Bank.Services.AccountService;
import com.MyBank.Smile.Bank.utils.AccountUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {


    @Autowired
    AccountRepository accountRepository;
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public List<AccountModel> createAccount(CustomerModel newCustomer) {
        AccountModel newAccount = AccountModel.builder()
                .accountType()
                .build();
        AccountUtils.generateAccountNumber();
        return null;
    }
}
