package com.MyBank.Smile.Bank.exception;

public class AlreadyExistException extends  RuntimeException {

    public AlreadyExistException(String message){
        super(message);
    }

}
