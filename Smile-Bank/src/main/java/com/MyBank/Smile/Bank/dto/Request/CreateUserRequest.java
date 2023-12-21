package com.MyBank.Smile.Bank.dto.Request;

import com.MyBank.Smile.Bank.Models.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String stateOfOrigin;
    private String mothersMaidenName;
    private String phoneNumber;
    private String alternativePhoneNumber;
    private String homeAddress;
    private String nin;
    private String bvn;
    private String emailAddress;
    private AccountType accountType;
}
