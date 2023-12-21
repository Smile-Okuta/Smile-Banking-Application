package com.MyBank.Smile.Bank.Models;

import com.MyBank.Smile.Bank.Models.enums.AccountStatus;
import com.MyBank.Smile.Bank.Models.enums.AccountType;
import com.MyBank.Smile.Bank.Services.TransactionService;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class  Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @Column(unique = true)
    private String nin;
    @Column(unique = true)
    private String accountNumber;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    private BigDecimal accountBalance;
    @OneToMany
    private List<TransactionModel> transactionService;
}
