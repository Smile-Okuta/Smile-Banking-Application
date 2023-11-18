package com.MyBank.Smile.Bank.ServicesImpl;

import com.MyBank.Smile.Bank.Dto.AccountInfo;
import com.MyBank.Smile.Bank.Dto.BankResponse;
import com.MyBank.Smile.Bank.Dto.UserRequest;
import com.MyBank.Smile.Bank.Models.UserModel;
import com.MyBank.Smile.Bank.Repository.UserRepository;
import com.MyBank.Smile.Bank.Services.UserService;
import com.MyBank.Smile.Bank.utils.AccountUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;


    @Override
    public BankResponse createAccount(UserRequest userRequest) {
         UserModel userModel = UserModel.builder()
                 .firstName(userRequest.getFirstName())
                 .lastName(userRequest.getLastName())
                 .dateOfBirth(userRequest.getDateOfBirth())
                 .gender(userRequest.getGender())
                 .stateOfOrigin(userRequest.getStateOfOrigin())
                 .mothersMaidenName(userRequest.getMothersMaidenName())
                 .phoneNumber(userRequest.getPhoneNumber())
                 .alternativePhoneNumber(userRequest.getAlternativePhoneNumber())
                 .homeAddress(userRequest.getHomeAddress())
                 .NIN(userRequest.getNIN())
                 .bvn(userRequest.getBvn()).emailAddress(userRequest.getEmailAddress())
                 .accountType(userRequest.getAccountType())
                 .createdAt(LocalDateTime.now())
                 .status("ACTIVE")

                 .build();
         UserModel account = userRepository.save(userModel);
         return   BankResponse.builder()
                 .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS_CODE)
                 .responseMessage(AccountUtils.ACCOUNT_CREATION_SUCCESS_MESSAGE)
                 .accountInfo(AccountInfo.builder()
                         .accountBalance(BigDecimal.ZERO)
                         .accountNumber(AccountUtils.generateAccountNumber())
                         .accountName(account.getFirstName() + " " + account.getLastName())
                 .build())

                 .build();

    }

//    private AccountModel findAccountById(Long id){
//     return accountRepository.findById(id)
//             .orElseThrow(() -> new NotFoundException("Customer not found!!" +
//                     "Create new Customer"));
//    }
}
