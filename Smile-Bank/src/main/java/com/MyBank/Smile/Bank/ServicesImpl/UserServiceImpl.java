package com.MyBank.Smile.Bank.ServicesImpl;

import com.MyBank.Smile.Bank.Dto.Request.AccountRequest;
import com.MyBank.Smile.Bank.Dto.Request.NameEnquiryRequest;
import com.MyBank.Smile.Bank.Dto.Request.UserRequest;
import com.MyBank.Smile.Bank.Dto.Response.AccountInfo;
import com.MyBank.Smile.Bank.Dto.Response.BankResponse;
import com.MyBank.Smile.Bank.Dto.Response.EmailDetails;
import com.MyBank.Smile.Bank.Models.UserModel;
import com.MyBank.Smile.Bank.Repository.UserRepository;
import com.MyBank.Smile.Bank.Services.EmailService;
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
    @Autowired
    EmailService emailService;

    AccountServiceImpl accountService;
    public UserServiceImpl(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }




    @Override
    public BankResponse createUser(UserRequest userRequest) {
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
                 .bvn(userRequest.getBvn())
                 .emailAddress(userRequest.getEmailAddress())
                 .accountType(userRequest.getAccountType())
                 .accountBalance(BigDecimal.ZERO)
                 .createdAt(LocalDateTime.now())
                 .accountNumber(AccountUtils.generateAccountNumber())
                 .build();


         UserModel user = userRepository.save(userModel);

//         Automatically create an account for a new user
        accountService.createAccount(AccountRequest.builder()
                .accountType(user.getAccountType())
                .accountNumber(user.getAccountNumber())
                .NIN(user.getNIN())
                .build());


//         Send email alert for creation of account
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(user.getEmailAddress())
                .subject("ACCOUNT CREATION")
                .messageBody("Congratulations! Your Account has been created.\nYour Account Details: \n" +
                        "Account Name: " + user.getFirstName() + " " + user.getLastName() + "\nAccount Number:" + user.getAccountNumber()+ "\nAccount Type:"+user.getAccountType())
                .build();
        emailService.sendEmailAlert(emailDetails);


         return   BankResponse.builder()
                 .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS_CODE)
                 .responseMessage(AccountUtils.ACCOUNT_CREATION_SUCCESS_MESSAGE)
                 .accountInfo(AccountInfo.builder()
                         .accountBalance(user.getAccountBalance())
                         .accountNumber(user.getAccountNumber())
                         .accountName(user.getFirstName() + " " + user.getLastName())
                 .build())

                 .build();


    }



    @Override
    public String nameEnquiry(NameEnquiryRequest request) {
        boolean isAccountExist = userRepository.existsByNIN(request.getNIN());
        if (!isAccountExist){
             return AccountUtils.ACCOUNT_DOES_NOT_EXIST_MESSAGE;
        }
        UserModel foundUser = userRepository.findByNIN(request.getNIN());
        return foundUser.getFirstName() + " " + foundUser.getLastName();
    }


}
