package com.INetwork.corebanking.service;

import com.INetwork.corebanking.dto.UserInfoDto;
import com.INetwork.corebanking.dto.account.AccountRequestDto;
import com.INetwork.corebanking.entity.BankAccount;
import com.INetwork.corebanking.enumeration.AccountStatus;
import com.INetwork.corebanking.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class BankAccountService {


    private final BankAccountRepository bankAccountRepository;
    private final BankAccountRepository accountRepository;

    public BankAccount saveBankAccount(AccountRequestDto bankAccount) {
        log.info("********* Inside BankAccount********");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Random rnd = new SecureRandom();
        UserInfoDto userInfoDto = ((UserInfoDto) principal);
        BankAccount account = new BankAccount();
        account.setBalance(bankAccount.getAmount());
        account.setUserId(userInfoDto.getId());
        account.setNumber(rnd.nextInt(999999 - 100000 + 1) + 100000);
        account.setStatus(AccountStatus.ACTIVE);
        account.setType(bankAccount.getType());
        return bankAccountRepository.save(account);
    }

    public BankAccount getBankAccount(long accountNumber){
        return accountRepository.findByNumber(accountNumber).orElseThrow(()-> new RuntimeException("Account number does not exist"));

    }
}
