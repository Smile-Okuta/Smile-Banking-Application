package com.MyBank.Smile.Bank.exception;

public class InsufficientFundsException extends RuntimeException{
    public InsufficientFundsException(String message){
        super(message);

    }

}
