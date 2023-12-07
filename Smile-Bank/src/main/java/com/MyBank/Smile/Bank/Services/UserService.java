package com.MyBank.Smile.Bank.Services;

import com.MyBank.Smile.Bank.Dto.Request.NameEnquiryRequest;
import com.MyBank.Smile.Bank.Dto.Request.UserRequest;
import com.MyBank.Smile.Bank.Dto.Response.BankResponse;

public interface UserService {
//    AccountModel createAccount(AccountType accountType);

    BankResponse createUser(UserRequest userRequest);
    String nameEnquiry(NameEnquiryRequest request);


}
