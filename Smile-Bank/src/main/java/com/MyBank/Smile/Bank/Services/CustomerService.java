package com.MyBank.Smile.Bank.Services;


import com.MyBank.Smile.Bank.Dto.BankResponse;
import com.MyBank.Smile.Bank.Dto.CustomerRequest;

public interface CustomerService {
 BankResponse createAccount(CustomerRequest customerRequest);
}
