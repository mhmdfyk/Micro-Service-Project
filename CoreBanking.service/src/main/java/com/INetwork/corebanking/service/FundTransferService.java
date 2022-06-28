package com.INetwork.corebanking.service;

import com.INetwork.corebanking.dto.TransactionHistory;
import com.INetwork.corebanking.dto.UserInfoDto;
import com.INetwork.corebanking.dto.transfer.TransferRequestDto;
import com.INetwork.corebanking.entity.BankAccount;
import com.INetwork.corebanking.entity.Deposit;
import com.INetwork.corebanking.entity.FundTransfer;
import com.INetwork.corebanking.enumeration.TransactionStatus;
import com.INetwork.corebanking.enumeration.TransactionType;
import com.INetwork.corebanking.integration.ReportGenerationIntegrationService;
import com.INetwork.corebanking.repository.BankAccountRepository;
import com.INetwork.corebanking.repository.DepositRepository;
import com.INetwork.corebanking.repository.FundTransferRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FundTransferService {
    private final FundTransferRepository fundTransferRepository;
    private final BankAccountRepository accountRepository;
    private final HttpServletRequest httpServletRequest;
    private final ReportGenerationIntegrationService reportGenerationIntegrationService;

    @Transactional
    public FundTransfer saveFundTransfer(TransferRequestDto fundTransferDto) {
        log.info("********* Inside save FundTransfer ********");
        BankAccount toAccount = accountRepository.findByNumber(fundTransferDto.getToAccountNumber()).orElseThrow(() -> new RuntimeException("[TO Account number] does not exist"));
        BankAccount fromAccount = accountRepository.findByNumber(fundTransferDto.getFromAccountNumber()).orElseThrow(() -> new RuntimeException("[FROM Account number] does not exist"));
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfoDto userInfoDto = ((UserInfoDto) principal);
        FundTransfer fundTransfer = new FundTransfer();
        validateBalance(fromAccount, fundTransferDto.getAmount());
        fromAccount.setBalance(fromAccount.getBalance().subtract(fundTransferDto.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(fundTransferDto.getAmount()));
        fundTransfer.setAmount(fundTransferDto.getAmount());
        fundTransfer.setToAccount(toAccount);
        fundTransfer.setFromAccount(fromAccount);
        fundTransfer.setStatus(TransactionStatus.SUCCESS);
        fundTransfer.setCurrency(fundTransferDto.getCurrencyType());
        String authorization = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        ArrayList<TransactionHistory> transactionHistory = new ArrayList<>();
        transactionHistory.add(createTransactionHistory(
                fundTransferDto.getFromAccountNumber(),
                userInfoDto.getFirstName().concat(" ").concat(userInfoDto.getLastName()),
                fundTransferDto.getAmount(),
                TransactionType.FUND_TRANSFER,
                fromAccount.getId()));
        fundTransferRepository.save(fundTransfer);
        reportGenerationIntegrationService.createTransactionHistory(authorization, transactionHistory);
        return fundTransfer;

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


    private void validateBalance(BankAccount bankAccount, BigDecimal amount) {
        if (bankAccount.getBalance().compareTo(BigDecimal.ZERO) <= 0 || bankAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("insufficient fund to transfer");
        }
    }
}
