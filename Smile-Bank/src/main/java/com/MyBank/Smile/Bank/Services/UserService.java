package com.MyBank.Smile.Bank.Services;

import com.MyBank.Smile.Bank.dto.Request.NameEnquiryRequest;
import com.MyBank.Smile.Bank.dto.Request.CreateUserRequest;
import com.MyBank.Smile.Bank.dto.Response.BankResponse;
import com.MyBank.Smile.Bank.dto.Response.CreateUserResponse;

public interface UserService {
//    AccountModel createAccount(AccountType accountType);

    CreateUserResponse createUser(CreateUserRequest createUserRequest);
    String nameEnquiry(NameEnquiryRequest request);


}
