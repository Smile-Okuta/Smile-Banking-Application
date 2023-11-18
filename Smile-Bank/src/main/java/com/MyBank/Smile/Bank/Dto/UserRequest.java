package com.MyBank.Smile.Bank.Dto;

import com.MyBank.Smile.Bank.Enum.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String stateOfOrigin;
    private String mothersMaidenName;
    private String phoneNumber;
    private String alternativePhoneNumber;
    private String homeAddress;
    private String NIN;
    private Long bvn;
    private String emailAddress;
    private AccountType accountType;

}
