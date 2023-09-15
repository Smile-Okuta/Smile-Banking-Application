package com.MyBank.Smile.Bank.ServicesImpl;

import com.MyBank.Smile.Bank.Dto.AccountInfo;
import com.MyBank.Smile.Bank.Dto.BankResponse;
import com.MyBank.Smile.Bank.Dto.CustomerRequest;
import com.MyBank.Smile.Bank.Models.AccountModel;
import com.MyBank.Smile.Bank.Models.CustomerModel;
import com.MyBank.Smile.Bank.Repository.AccountRepository;
import com.MyBank.Smile.Bank.Repository.CustomerRepository;
import com.MyBank.Smile.Bank.Services.AccountService;
import com.MyBank.Smile.Bank.Services.CustomerService;
import com.MyBank.Smile.Bank.utils.AccountUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor

public class CustomerServiceImpl implements CustomerService {
@Autowired
    CustomerRepository customerRepository;
@Autowired
AccountService accountService;

    @Override
    public BankResponse createCustomer(CustomerRequest customerRequest) {
//    * Creating an account - saving a new user into the database
//        Check if user already has an account
//        Using the entity class...
        if(customerRepository.existsByNIN(customerRequest.getNIN())){
            BankResponse response = BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_EXIST_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }
        CustomerModel newCustomer = CustomerModel.builder()
                .firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .dateOfBirth(customerRequest.getDateOfBirth())
                .gender(customerRequest.getGender())
                .stateOfOrigin(customerRequest.getStateOfOrigin())
                .mothersMaidenName(customerRequest.getMothersMaidenName())
                .phoneNumber(customerRequest.getPhoneNumber())
                .alternativePhoneNumber(customerRequest.getAlternativePhoneNumber())
                .homeAddress(customerRequest.getHomeAddress())
                .NIN(customerRequest.getNIN())
                .bvn(customerRequest.getBvn())
                .emailAddress(customerRequest.getEmailAddress())
//                I'm bringing in the method "createAccount()" in AccountService into
//                CustomerServiceImpl, so that when a customer is created an account is also created
//                Right now, the Customer class implements the attributes of the AccountClass.
                .accounts(accountService.createAccount(accountRequest))

                .build();

                CustomerModel customer = customerRepository.save(newCustomer);

//                AccountModel account = accountRepository.save(newCustomer.getAccount());
//                            account = accountRepository.save(customer.getAccountBalance());


                return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS_CODE)
                .responseMessage(AccountUtils.ACCOUNT_CREATION_SUCCESS_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountNumber(customer.getAccounts())
                        .accountBalance(customer.getAccountBalance())
                        .accountName(customer.getFirstName() + " " + customer.getLastName())
                        .build())
                .build();

    }
}
