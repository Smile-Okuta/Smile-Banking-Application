package com.MyBank.Smile.Bank.dto.Response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateUserResponse {
    private String message;
    private String accountType;
    private String accountNumber;
    private String firstName;
    private String lastName;
    private String bvn;
}
