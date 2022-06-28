package com.INetwork.corebanking.dto.deposit;

import com.INetwork.corebanking.enumeration.CurrencyType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class DepositRequestDto {
    @NotNull
    private Long accountNumber;
    @NotNull
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private CurrencyType currency;
}
