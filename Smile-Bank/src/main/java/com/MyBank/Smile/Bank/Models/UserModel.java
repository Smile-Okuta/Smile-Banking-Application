package com.MyBank.Smile.Bank.Models;

import com.MyBank.Smile.Bank.Enum.AccountType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name="Account")
public class UserModel {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
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
    private String status;
    @Enumerated(EnumType.STRING)
    @Column(name="Account Type")
    private AccountType accountType;
    private String accountNumber;
    private BigDecimal accountBalance;
    @OneToMany
    private List<TransactionModel> transactions;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
