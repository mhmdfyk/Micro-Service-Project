package com.INetwork.corebanking.service;

import com.INetwork.corebanking.dto.TransactionHistory;
import com.INetwork.corebanking.dto.UserInfoDto;
import com.INetwork.corebanking.dto.deposit.DepositRequestDto;
import com.INetwork.corebanking.entity.BankAccount;
import com.INetwork.corebanking.entity.Deposit;
import com.INetwork.corebanking.enumeration.TransactionStatus;
import com.INetwork.corebanking.enumeration.TransactionType;
import com.INetwork.corebanking.integration.ReportGenerationIntegrationService;
import com.INetwork.corebanking.repository.BankAccountRepository;
import com.INetwork.corebanking.repository.DepositRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class DepositService {
    private final DepositRepository depositRepository;
    private final BankAccountRepository accountRepository;
    private final HttpServletRequest httpServletRequest;
    private final ReportGenerationIntegrationService reportGenerationIntegrationService;

    @Transactional
    public Deposit saveDeposit(DepositRequestDto depositDto) {
        log.info("********* Inside save Deposit********");
        BankAccount account = accountRepository.findByNumber(depositDto.getAccountNumber()).orElseThrow(()-> new RuntimeException("Account number does not exist"));
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfoDto userInfoDto = ((UserInfoDto) principal);
        Deposit deposit = new Deposit();
        account.setBalance(account.getBalance().add(depositDto.getAmount()));
        deposit.setAccount(account);
        deposit.setAmount(depositDto.getAmount());
        deposit.setCurrency(depositDto.getCurrency());
        deposit.setStatus(TransactionStatus.SUCCESS);
        depositRepository.save(deposit);
        String authorization = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        ArrayList<TransactionHistory> transactionHistory = new ArrayList<>();
        transactionHistory.add(createTransactionHistory(
                depositDto.getAccountNumber(),
                userInfoDto.getFirstName().concat(" ").concat(userInfoDto.getLastName()),
                depositDto.getAmount(),
                TransactionType.DEPOSIT,
                account.getId()
                ));
        reportGenerationIntegrationService.createTransactionHistory(authorization, transactionHistory);

        return deposit;
    }

    private TransactionHistory createTransactionHistory(long AccountNumber, String name, BigDecimal amount, TransactionType transactionType, long accountId) {
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setTransactionType(transactionType);
        transactionHistory.setAmount(amount);
        transactionHistory.setAccountId(accountId);
        transactionHistory.setAccountNumber(AccountNumber);
        transactionHistory.setCustomerName(name);
        return transactionHistory;
    }
}
