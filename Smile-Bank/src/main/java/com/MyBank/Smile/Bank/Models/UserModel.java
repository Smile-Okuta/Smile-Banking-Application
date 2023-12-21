package com.MyBank.Smile.Bank.Models;

import com.MyBank.Smile.Bank.Models.enums.AccountType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserModel {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String stateOfOrigin;
    private String mothersMaidenName;
    private String phoneNumber;
    private String alternativePhoneNumber;
    private String homeAddress;
    private String nin;
    private String bvn;
    private String emailAddress;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    @OneToMany
    private List<Account> accounts;
}
