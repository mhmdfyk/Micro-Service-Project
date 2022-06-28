package com.INetwork.corebanking.dto;

import com.INetwork.corebanking.enumeration.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionHistory {

    @NotNull
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @NotNull
    private Long accountNumber;

    @NotNull
    private Long accountId;

    private String customerName;



}
