package com.MyBank.Smile.Bank.ServicesImpl;

import com.MyBank.Smile.Bank.Dto.AccountInfo;
import com.MyBank.Smile.Bank.Dto.BankResponse;
import com.MyBank.Smile.Bank.Dto.CustomerRequest;
import com.MyBank.Smile.Bank.Models.AccountModel;
import com.MyBank.Smile.Bank.Models.CustomerModel;
import com.MyBank.Smile.Bank.Repository.CustomerRepository;
import com.MyBank.Smile.Bank.Services.CustomerService;
import com.MyBank.Smile.Bank.utils.AccountUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class CustomerServiceImpl implements CustomerService {
@Autowired
    CustomerRepository customerRepository;
    @Override
    public BankResponse createAccount(CustomerRequest customerRequest) {
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
                .accountNumber(AccountUtils.generateAccountNumber())
                .build();

        CustomerModel customer = customerRepository.save(newCustomer);

        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS_CODE)
                .responseMessage(AccountUtils.ACCOUNT_CREATION_SUCCESS_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(customer.getAccountBalance())
                        .accountNumber((customer.getAccountNumber()))
                        .accountName(customer.getFirstName() + " " + customer.getLastName())
                        .build())
                .build();

    }
}
