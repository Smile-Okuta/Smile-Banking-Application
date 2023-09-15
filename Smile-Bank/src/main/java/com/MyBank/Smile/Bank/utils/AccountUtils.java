package com.MyBank.Smile.Bank.utils;

import java.time.Year;

public class AccountUtils {

    public static final String ACCOUNT_EXIST_CODE = "001";
    public static final String ACCOUNT_EXIST_MESSAGE = "This user already exist";
    public static final String ACCOUNT_CREATION_SUCCESS_CODE = "002";
    public static final String ACCOUNT_CREATION_SUCCESS_MESSAGE = "Account Created Successfully";

    public static String generateAccountNumber(){
        //    Creating a random Account Number using:
//    Current year + randomSixDigits
//    The randomSixDigit will begin from 100,000 and end at 999,999

        Year currentYear = Year.now();
        int min = 100000;
        int max = 999999;

//    generate a random number between min and max
//    Math.random generates numbers between 0 -9

        int randNumber = (int) Math.floor (Math.random() * (max - min + 1) + min);

//  convert the current and randomNumber to Strings, then concatenate them

        String year = String.valueOf(currentYear);
        String randomNumber = String.valueOf(randNumber);
        StringBuilder accountNumber = new StringBuilder();

        return accountNumber.append(year).append(randomNumber).toString();
    }
}
