package com.MyBank.Smile.Bank.ServicesImpl;

import com.MyBank.Smile.Bank.Enum.AccountType;
import com.MyBank.Smile.Bank.Models.AccountModel;
import com.MyBank.Smile.Bank.Services.AccountService;
import com.MyBank.Smile.Bank.utils.AccountUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    @Override
    public AccountModel createAccount(AccountType accountType) {
        String accountNumber = AccountUtils.generateAccountNumber();
        AccountModel accountModel = AccountModel.builder()
                .createdAt(LocalDateTime.now())
                .accountNumber(accountNumber)
                .accountType(accountType)
                .build();
        return accountModel;
    }
}
