package com.MyBank.Smile.Bank.ServicesImpl;

import com.MyBank.Smile.Bank.Dto.Request.AccountRequest;
import com.MyBank.Smile.Bank.Dto.Request.CreditDebitRequest;
import com.MyBank.Smile.Bank.Dto.Request.TransferRequest;
import com.MyBank.Smile.Bank.Dto.Response.AccountInfo;
import com.MyBank.Smile.Bank.Dto.Response.BankResponse;
import com.MyBank.Smile.Bank.Models.Account;
import com.MyBank.Smile.Bank.Models.UserModel;
import com.MyBank.Smile.Bank.Repository.AccountRepository;
import com.MyBank.Smile.Bank.Repository.UserRepository;
import com.MyBank.Smile.Bank.Services.AccountService;
import com.MyBank.Smile.Bank.exception.InsufficientFundsException;
import com.MyBank.Smile.Bank.exception.NotFoundException;
import com.MyBank.Smile.Bank.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;



    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BankResponse createAccount(AccountRequest accountRequest) {
        boolean isAccountExist = userRepository.existsByNIN(accountRequest.getNIN());
        if (!isAccountExist){
           return BankResponse.builder()
                   .responseMessage(AccountUtils.USER_DOES_NOT_EXIST_MESSAGE)
                   .accountInfo(null)
                   .build();
        }
        Account account = Account.builder()
                .NIN(accountRequest.getNIN())
                .accountType(accountRequest.getAccountType())
                .accountNumber(AccountUtils.generateAccountNumber())
                .build();

        Account newAccount = accountRepository.save(account);

        return BankResponse.builder()
                .NIN(newAccount.getNIN())
                .accountNumber(newAccount.getAccountNumber())
                .accountBalance(BigDecimal.ZERO)
                .accountType(newAccount.getAccountType())
                .build();
    }


    @Override
    public BankResponse balanceEnquiry(AccountRequest accountRequest) {
//        Check if the provided account number exist in the db
           findByAccountNumber(accountRequest);

            Account foundAccount = accountRepository.findByAccountNumber(accountRequest.getAccountNumber());
            UserModel foundUser = userRepository.findByNIN(accountRequest.getNIN());

            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_FOUND_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_FOUND_SUCCESS)
                    .accountInfo(AccountInfo.builder()
                            .accountBalance(foundAccount.getAccountBalance())
                            .accountNumber(foundAccount.getAccountNumber())
                            .accountName(foundUser.getFirstName() + " " + foundUser.getLastName() )
                            .build())
                    .build();
    }



    @Override
    public BankResponse creditAccount(CreditDebitRequest creditRequest) {
        boolean isAccountExist = userRepository.existsByAccountNumber(creditRequest.getAccountNumber());

        if (! isAccountExist){
            throw new NotFoundException("Account Not Found");
        }

        Account accountToCredit = accountRepository.findByAccountNumber(creditRequest.getAccountNumber());
        accountToCredit.setAccountBalance(accountToCredit.getAccountBalance().add(creditRequest.getAmount()));
        accountRepository.save(accountToCredit);

        UserModel foundUser = userRepository.findByAccountNumber(creditRequest.getAccountNumber());

        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREDITED_SUCCESS_CODE)
                .responseMessage(AccountUtils.ACCOUNT_CREDITED_SUCCESS_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(accountToCredit.getAccountBalance())
                        .accountNumber(creditRequest.getAccountNumber())
                        .accountName(foundUser.getFirstName() + " " + foundUser.getLastName() )
                        .build())
                .build();
    }

    @Override
    public BankResponse debitAccount(CreditDebitRequest debitRequest) {
        boolean isAccountExist = userRepository.existsByAccountNumber(debitRequest.getAccountNumber());

        if (! isAccountExist){
            throw new NotFoundException("Account Not Found");
        }

        Account accountToDebit = accountRepository.findByAccountNumber(debitRequest.getAccountNumber());
        double debitAmount = Integer.parseInt(debitRequest.getAmount().toString());
        double availableBalance = Integer.parseInt(accountToDebit.getAccountBalance().toString());
        if (debitAmount >= availableBalance){
            throw new InsufficientFundsException("Insufficient Funds");
        }else {
            accountToDebit.setAccountBalance(accountToDebit.getAccountBalance().subtract(debitRequest.getAmount()));
            accountRepository.save(accountToDebit);

            UserModel foundUser = userRepository.findByAccountNumber(debitRequest.getAccountNumber());

            return BankResponse.builder()
                    .responseMessage(AccountUtils.ACCOUNT_DEBITED_SUCCESS_MESSAGE)
                    .responseCode(AccountUtils.ACCOUNT_DEBITED_SUCCESS_CODE)
                    .accountInfo(AccountInfo.builder()
                            .accountNumber(debitRequest.getAccountNumber())
                            .accountName(foundUser.getFirstName() + " " + foundUser.getLastName())
                            .accountBalance(accountToDebit.getAccountBalance())
                            .build())
                    .build();
        }

    }

    @Override
    public BankResponse transfer(TransferRequest transferRequest) {
        boolean fromAccount = userRepository.existsByAccountNumber(transferRequest.getSenderAccountNum());
        boolean toAccount = userRepository.existsByAccountNumber(transferRequest.getReceiverAccountNum());

        if (!fromAccount){
            throw new NotFoundException("Account not found");
        }
        if (!toAccount){
            throw new NotFoundException("Account Not Found");
        }

        Account newFromAccount = accountRepository.findByAccountNumber(transferRequest.getSenderAccountNum());
        Account newToAccount = accountRepository.findByAccountNumber(transferRequest.getReceiverAccountNum());

        int senderAmount = Integer.parseInt(transferRequest.getAmount().toString());
        int senderBalance = Integer.parseInt(newFromAccount.getAccountBalance().toString());

        if (senderAmount > senderBalance){
            throw new InsufficientFundsException("Insufficient Funds");
        }

       newFromAccount.setAccountBalance(newFromAccount.getAccountBalance().subtract(transferRequest.getAmount()));
       newToAccount.setAccountBalance(newToAccount.getAccountBalance().add(transferRequest.getAmount()));

       accountRepository.save(newFromAccount);
       accountRepository.save(newToAccount);

       return BankResponse.builder()
               .responseCode(AccountUtils.TRANSFER_SUCCESSFUL_CODE)
               .responseMessage(AccountUtils.TRANSFER_SUCCESSFUL_MESSAGE)
               .accountInfo(AccountInfo.builder()
                       .accountBalance(newFromAccount.getAccountBalance())
                       .accountNumber(transferRequest.getSenderAccountNum())
                       .accountBalance(newToAccount.getAccountBalance())
                       .accountNumber(transferRequest.getReceiverAccountNum())
                       .build())

               .build();
    }




    private BankResponse findByAccountNumber(AccountRequest accountRequest){

        return accountRepository.existsByAccountNumber(accountRequest.getAccountNumber()
    }
}
