package com.INetwork.corebanking.entity;

import com.INetwork.corebanking.enumeration.CurrencyType;
import com.INetwork.corebanking.enumeration.TransactionStatus;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundTransfer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fromAccountId")
    private BankAccount fromAccount;

    @ManyToOne
    @JoinColumn(name = "toAccountId")
    private BankAccount toAccount;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @Enumerated(EnumType.STRING)
    private CurrencyType currency;
}
