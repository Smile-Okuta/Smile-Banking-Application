package com.MyBank.Smile.Bank.Models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name="customers")
public class CustomerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String accountType;
    @OneToMany
    private List<AccountModel> accountModels;
    //  @CreationTimestamp: This is an annotation that sets the field value
    // to the current timestamp when the entity is first saved.
    @CreationTimestamp
    private LocalDateTime createdAt;

    // @UpdateTimestamp: It automatically sets the
    // field value to the current timestamp on each entity update
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

}
