package com.MyBank.Smile.Bank.Models;

import com.MyBank.Smile.Bank.Enum.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="transaction")
public class TransactionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name="Transaction Type")
    private TransactionType type;
    private int amount;
    private String accountName;
    private Long accountNumber;
    private String dateOfTransaction;
    private String timeOfTransaction;
    private String description;
    private String thirdPartyAccountName;
    private Long thirdPartyAccountNumber;
    @ManyToOne
    private UserModel account;
}
