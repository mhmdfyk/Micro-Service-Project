package com.INetwork.corebanking.controller;

import com.INetwork.corebanking.dto.UserInfoDto;
import com.INetwork.corebanking.dto.account.AccountRequestDto;
import com.INetwork.corebanking.entity.BankAccount;
import com.INetwork.corebanking.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final BankAccountService bankAccountService;

    @PostMapping("/creatAccount")
    public BankAccount creatAccount(@Valid @RequestBody AccountRequestDto bankAccount) {
        log.info("********* Inside Create Account ********");


        return bankAccountService.saveBankAccount(bankAccount);
    }
    @GetMapping("/{accountNumber}")
    public BankAccount getAccount(@PathVariable long accountNumber) {
        log.info("********* Inside get Account ********");


        return bankAccountService.getBankAccount(accountNumber);
    }
}
