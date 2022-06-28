package com.INetwork.ReportGeneration.controller;

import com.INetwork.ReportGeneration.entity.TransactionHistory;
import com.INetwork.ReportGeneration.service.TransactionHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/TransactionHistory")
@RequiredArgsConstructor
public class TransactionHistoryController {


    private final TransactionHistoryService transactionHistoryService;

    @PostMapping("/createTransactionHistory")
    public ResponseEntity<List<TransactionHistory>> createTransactionHistory(@RequestBody ArrayList<TransactionHistory> transactionHistory) {
        log.info("********* Inside createTransactionHistory ********");
        return ResponseEntity.ok(transactionHistoryService.saveTransactionHistory(transactionHistory));
    }

    @GetMapping("/generateReport")
    public List<TransactionHistory> generateReport() {
        log.info("********* Inside generate Report ********");
        return transactionHistoryService.getALLTransactions();
    }
}
