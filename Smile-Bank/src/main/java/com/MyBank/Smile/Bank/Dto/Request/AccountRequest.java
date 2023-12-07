package com.MyBank.Smile.Bank.Dto.Request;

import com.MyBank.Smile.Bank.Models.enums.AccountType;
import com.MyBank.Smile.Bank.utils.AccountUtils;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountRequest {
    private String NIN;
    private AccountType accountType;
    private String accountNumber;
}
