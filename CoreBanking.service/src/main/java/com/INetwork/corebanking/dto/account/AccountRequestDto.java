package com.INetwork.corebanking.dto.account;

import com.INetwork.corebanking.enumeration.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDto {
    @NotNull
    private BigDecimal amount;
    private AccountType type;
}
