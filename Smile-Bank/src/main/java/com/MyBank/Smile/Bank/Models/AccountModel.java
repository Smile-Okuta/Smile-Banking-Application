package com.MyBank.Smile.Bank.Models;

import com.MyBank.Smile.Bank.Enum.AccountEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
//    private String accountFirstName;
//    private String accountLastName;
    @ManyToOne
    private CustomerModel customer;
    private AccountEnum type;
    private String accountNumber;
    private String dateOfCreation;
    private String timeOfCreation;
//    private String dateOfBirth;
    //private Customer BVN;
    private String phoneNumber;
    private String emailAddress;
    @OneToMany
    private List<TransactionModel> transactions;
    private Boolean flag;
}
