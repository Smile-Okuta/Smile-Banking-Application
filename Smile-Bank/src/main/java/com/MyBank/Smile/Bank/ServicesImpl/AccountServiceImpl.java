package com.MyBank.Smile.Bank.ServicesImpl;

import com.MyBank.Smile.Bank.dto.Request.AccountRequest;
import com.MyBank.Smile.Bank.dto.Request.BalanceRequest;
import com.MyBank.Smile.Bank.dto.Request.CreditDebitRequest;
import com.MyBank.Smile.Bank.dto.Request.TransferRequest;
import com.MyBank.Smile.Bank.dto.Response.AccountInfo;
import com.MyBank.Smile.Bank.dto.Response.BankResponse;
import com.MyBank.Smile.Bank.dto.Response.CreateAccountResponse;
import com.MyBank.Smile.Bank.Models.Account;
import com.MyBank.Smile.Bank.Models.UserModel;
import com.MyBank.Smile.Bank.Repository.AccountRepository;
import com.MyBank.Smile.Bank.Repository.UserRepository;
import com.MyBank.Smile.Bank.Services.AccountService;
import com.MyBank.Smile.Bank.exception.InsufficientFundsException;
import com.MyBank.Smile.Bank.exception.NotFoundException;
import com.MyBank.Smile.Bank.utils.AccountUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Override
    public CreateAccountResponse createAccount(AccountRequest accountRequest) {
        checkUserNotExist(accountRequest);
        Account account = createNewAccount(accountRequest);
        Account newAccount = accountRepository.save(account);
        return CreateAccountResponse.builder()
                .message("Account Created Successfully")
                .accountNumber(newAccount.getAccountNumber())
                .accountType(newAccount.getAccountType())
                .build();
    }
    private void checkUserNotExist(AccountRequest accountRequest) {
        boolean isUserExist = userRepository.existsByNin(accountRequest.getNin());
        if (!isUserExist) {
            CreateAccountResponse.builder()
                    .message("User Does Not Exist, Create a new User")
                    .build();
        }
    }
    private Account createNewAccount(AccountRequest accountRequest) {
        Account account = new Account();
        account.setNin(accountRequest.getNin());
        account.setAccountType(accountRequest.getAccountType());
        account.setAccountNumber(AccountUtils.generateAccountNumber());
        return account;
    }


        @Override
        public AccountInfo balanceEnquiry (BalanceRequest balanceRequest){
            isAccountExist(balanceRequest);
            return AccountInfo.builder()
                    .accountNumber(balanceRequest.getAccountNumber())
                    .accountBalance(balanceRequest.getAccountBalance())
                    .build();
    }

        private void isAccountExist(BalanceRequest balanceRequest) {
            boolean isAccountExist = accountRepository.existsByAccountNumber(balanceRequest.getAccountNumber());
            if (!isAccountExist) {
                throw new NotFoundException("Account Not Found");
            }
    }


        @Override
        public BankResponse creditAccount (CreditDebitRequest creditRequest){
            boolean isAccountExist = userRepository.existsByAccountNumber(creditRequest.getAccountNumber());

            if (!isAccountExist) {
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
                            .accountName(foundUser.getFirstName() + " " + foundUser.getLastName())
                            .build())
                    .build();
        }

        @Override
        public BankResponse debitAccount (CreditDebitRequest debitRequest){
            boolean isAccountExist = userRepository.existsByAccountNumber(debitRequest.getAccountNumber());

            if (!isAccountExist) {
                throw new NotFoundException("Account Not Found");
            }

            Account accountToDebit = accountRepository.findByAccountNumber(debitRequest.getAccountNumber());
            double debitAmount = Integer.parseInt(debitRequest.getAmount().toString());
            double availableBalance = Integer.parseInt(accountToDebit.getAccountBalance().toString());
            if (debitAmount >= availableBalance) {
                throw new InsufficientFundsException("Insufficient Funds");
            } else {
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
        public BankResponse transfer (TransferRequest transferRequest){
            boolean fromAccount = userRepository.existsByAccountNumber(transferRequest.getSenderAccountNum());
            boolean toAccount = userRepository.existsByAccountNumber(transferRequest.getReceiverAccountNum());

            if (!fromAccount) {
                throw new NotFoundException("Account not found");
            }
            if (!toAccount) {
                throw new NotFoundException("Account Not Found");
            }

            Account newFromAccount = accountRepository.findByAccountNumber(transferRequest.getSenderAccountNum());
            Account newToAccount = accountRepository.findByAccountNumber(transferRequest.getReceiverAccountNum());

            int senderAmount = Integer.parseInt(transferRequest.getAmount().toString());
            int senderBalance = Integer.parseInt(newFromAccount.getAccountBalance().toString());

            if (senderAmount > senderBalance) {
                throw new InsufficientFundsException("Insufficient Funds");
            }

            int receiverBalance = Integer.parseInt(newToAccount.getAccountBalance().toString());

            int newFromBalance = senderBalance - senderAmount;
            int newToBalance = receiverBalance + senderAmount;

            newFromAccount.setAccountBalance(BigDecimal.valueOf(newFromBalance));
            newToAccount.setAccountBalance(BigDecimal.valueOf(newToBalance));

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
    }

