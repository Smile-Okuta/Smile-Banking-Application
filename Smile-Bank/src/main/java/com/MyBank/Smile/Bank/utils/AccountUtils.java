package com.MyBank.Smile.Bank.utils;

import java.security.SecureRandom;
import java.time.Year;
import java.util.stream.Collectors;

public class AccountUtils {
    public static final String ACCOUNT_CREDITED_SUCCESS_MESSAGE = "Account Credited successfully";
    public static final String ACCOUNT_CREDITED_SUCCESS_CODE = "006";
    public static final String ACCOUNT_DEBITED_SUCCESS_MESSAGE = "Account Debited Successfully";
    public static final String ACCOUNT_DEBITED_SUCCESS_CODE = "007";
    public static final String TRANSFER_SUCCESSFUL_CODE = "008";

    public static final String TRANSFER_SUCCESSFUL_MESSAGE = "Transfer Successful";



    public static String generateAccountNumber(){
        final SecureRandom secureRandom = new SecureRandom(); //instantiate a secure random class to generate random numbers
        Year currentYear = Year.now(); //returns the current year
        String randomNumbers = secureRandom.ints(8, 0, 10)//generates an 8 digit number starting from 1 to 9 but not including 10
                .mapToObj(String::valueOf) //converts the digits to string
                .collect(Collectors.joining()); //collect the converted string and join them together

        return currentYear.toString().concat(randomNumbers);
    }

    public static String generateBvn(){
        final SecureRandom secureRandom = new SecureRandom();
        int firstNumbers = 2120;
        String randomNumbers = secureRandom.ints(8, 0, 10).toString();

    return firstNumbers + randomNumbers;
    }
}
