package com.INetwork.corebanking.entity;

import com.INetwork.corebanking.enumeration.AccountStatus;
import com.INetwork.corebanking.enumeration.AccountType;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private long number;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @NotNull
    @Column(nullable = false)
    private BigDecimal balance;


    @NotNull
    @Column(nullable = false)
    private Long userId;
}
