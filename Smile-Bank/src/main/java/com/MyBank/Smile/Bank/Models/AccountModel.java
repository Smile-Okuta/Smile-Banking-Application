package com.MyBank.Smile.Bank.Models;

import com.MyBank.Smile.Bank.Enum.AccountEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name="Account Model")
public class  AccountModel {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private CustomerModel customer;
    private AccountEnum accountType;
    private String accountNumber;
    private BigDecimal accountBalance;
    private String dateOfCreation;
    private String timeOfCreation;
    @OneToMany
    private List<TransactionModel> transactions;
    private AccountEnum status;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
