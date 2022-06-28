package com.INetwork.corebanking.dto.transfer;

import com.INetwork.corebanking.enumeration.CurrencyType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
@Data
public class TransferRequestDto {
    @NotNull
    private Long fromAccountNumber;
    @NotNull
    private Long toAccountNumber;
    @NotNull
    private BigDecimal amount;
    @JsonProperty("currency")
    private CurrencyType currencyType;
}
