package com.MyBank.Smile.Bank.dto.Request;

import com.MyBank.Smile.Bank.Models.enums.AccountType;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountRequest {
    private String Nin;
    private AccountType accountType;
    private String accountNumber;
}
