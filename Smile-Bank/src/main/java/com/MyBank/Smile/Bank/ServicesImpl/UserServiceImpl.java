package com.MyBank.Smile.Bank.ServicesImpl;

import com.MyBank.Smile.Bank.Models.Account;
import com.MyBank.Smile.Bank.Repository.AccountRepository;
import com.MyBank.Smile.Bank.dto.Request.AccountRequest;
import com.MyBank.Smile.Bank.dto.Request.NameEnquiryRequest;
import com.MyBank.Smile.Bank.dto.Request.CreateUserRequest;
import com.MyBank.Smile.Bank.dto.Response.CreateUserResponse;
import com.MyBank.Smile.Bank.dto.Response.EmailDetails;
import com.MyBank.Smile.Bank.Models.UserModel;
import com.MyBank.Smile.Bank.Repository.UserRepository;
import com.MyBank.Smile.Bank.Services.EmailService;
import com.MyBank.Smile.Bank.Services.UserService;
import com.MyBank.Smile.Bank.exception.AlreadyExistException;
import com.MyBank.Smile.Bank.exception.NotFoundException;
import com.MyBank.Smile.Bank.utils.AccountUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final AccountRepository accountRepository;


    @Override
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) {
        userExist(createUserRequest);
        UserModel userModel = createNewUser(createUserRequest);
         UserModel user = userRepository.save(userModel);
         Account account = createNewAccount(new AccountRequest());
         Account newAccount = accountRepository.save(account);
         sendEmailEmailAlert(user, newAccount);
         return   CreateUserResponse.builder()
                 .message("user Created Successfully.")
                 .firstName(user.getFirstName())
                 .lastName(user.getLastName())
                 .accountNumber(newAccount.getAccountNumber())
                 .accountType(newAccount.getAccountType().toString())
                 .bvn(user.getBvn())
                 .build();
    }

    private void userExist(CreateUserRequest createUserRequest){
        boolean isUserExist = userRepository.existsByNin(createUserRequest.getNin());
        if (isUserExist){
            throw new AlreadyExistException("User Already Exist");
        }
    }
    private UserModel createNewUser(CreateUserRequest createUserRequest){
        UserModel userModel = new UserModel();
        userModel.setFirstName(createUserRequest.getFirstName());
        userModel.setLastName(createUserRequest.getLastName());
        userModel.setGender(createUserRequest.getGender());
        userModel.setStateOfOrigin(createUserRequest.getStateOfOrigin());
        userModel.setMothersMaidenName(createUserRequest.getMothersMaidenName());
        userModel.setPhoneNumber(createUserRequest.getPhoneNumber());
        userModel.setAlternativePhoneNumber(createUserRequest.getAlternativePhoneNumber());
        userModel.setHomeAddress(createUserRequest.getHomeAddress());
        userModel.setNin(createUserRequest.getNin());
        userModel.setBvn(Bvn(createUserRequest.getBvn()));
        userModel.setEmailAddress(createUserRequest.getEmailAddress());
        userModel.setCreatedAt(LocalDateTime.now());
        LocalDate dateOfBirth = convertDateStringToLocalDate(createUserRequest.getDateOfBirth());
        userModel.setDateOfBirth(dateOfBirth);
        return userModel;
    }

    private String Bvn (String bvn){
        if (bvn == null){
            AccountUtils.generateBvn();
        }
        return bvn;
    }
    private LocalDate convertDateStringToLocalDate(String dateOfBirth) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateOfBirth, dateTimeFormatter);
    }

    private Account createNewAccount(AccountRequest accountRequest) {
        Account account = new Account();
        account.setNin(accountRequest.getNin());
        account.setAccountType(accountRequest.getAccountType());
        account.setAccountNumber(AccountUtils.generateAccountNumber());
        return account;
    }

    private void sendEmailEmailAlert(UserModel user, Account account){
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(user.getEmailAddress())
                .subject(CreateUserResponse.builder()
                        .message("ACCOUNT CREATED SUCCESSFULLY")
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .accountNumber(account.getAccountNumber())
                        .accountType(account.getAccountType().toString())
                        .bvn(user.getBvn())
                        .build())
         .build();
        emailService.sendEmailAlert(emailDetails);
    }



    @Override
    public String nameEnquiry(NameEnquiryRequest request) {
        findUser(request);
        UserModel foundUser = userRepository.findByNIN(request.getNin());
        return foundUser.getFirstName() + " " + foundUser.getLastName();
    }

    private void findUser(NameEnquiryRequest request){
        boolean findUser = userRepository.existsByNin(request.getNin());
        if (!findUser){
            throw new NotFoundException("User not Found");
        }
    }


}
