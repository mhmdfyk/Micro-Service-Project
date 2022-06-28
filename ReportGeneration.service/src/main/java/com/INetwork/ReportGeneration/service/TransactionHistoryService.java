package com.INetwork.ReportGeneration.service;

import com.INetwork.ReportGeneration.entity.TransactionHistory;
import com.INetwork.ReportGeneration.repository.TransactionHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionHistoryService {


    private final TransactionHistoryRepository transactionHistoryRepository;

    public List<TransactionHistory> saveTransactionHistory(List<TransactionHistory> transactionHistory) {
        log.info("********* Inside saveTransactionHistory********");
        return transactionHistoryRepository.saveAll(transactionHistory);
    }

    public List<TransactionHistory> getALLTransactions() {
        log.info("********* Inside getALLTransactions********");
        return transactionHistoryRepository.findAll();
    }
}
