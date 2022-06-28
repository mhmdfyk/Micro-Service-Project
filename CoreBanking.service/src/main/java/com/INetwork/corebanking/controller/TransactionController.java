package com.INetwork.corebanking.controller;

import com.INetwork.corebanking.dto.deposit.DepositRequestDto;
import com.INetwork.corebanking.dto.transfer.TransferRequestDto;
import com.INetwork.corebanking.entity.BankAccount;
import com.INetwork.corebanking.entity.Deposit;
import com.INetwork.corebanking.entity.FundTransfer;
import com.INetwork.corebanking.service.BankAccountService;
import com.INetwork.corebanking.service.DepositService;
import com.INetwork.corebanking.service.FundTransferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final DepositService depositService;


    private final FundTransferService fundTransferService;

    @PostMapping("/deposit")
    public Deposit deposit(@RequestBody DepositRequestDto depositData) {
        log.info("********* Inside deposit  ********");
        return depositService.saveDeposit(depositData);
    }

    @PostMapping("/transfer")
    public FundTransfer transfer(@RequestBody TransferRequestDto transferRequestDto) {
        log.info("********* Inside fundTransfer  ********");

        return fundTransferService.saveFundTransfer(transferRequestDto);
    }



}
