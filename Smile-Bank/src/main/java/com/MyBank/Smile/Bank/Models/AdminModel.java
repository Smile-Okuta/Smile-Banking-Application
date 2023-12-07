package com.MyBank.Smile.Bank.Models;

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
public class AdminModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fistName;
    private String lastName;
    private String dateOfBirth;
    private String stateOfOrigin;
    private String mothersMaidenName;
    private String phoneNumber;
    private Long bvn;
    private Long nationalIdentificationNumber;

}
