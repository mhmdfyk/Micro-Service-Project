package com.INetwork.corebanking.integration;

import com.INetwork.corebanking.dto.TransactionHistory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "REPORT-GENERATION")
public interface ReportGenerationIntegrationService {

    @PostMapping("/api/v1/TransactionHistory/createTransactionHistory")
    ResponseEntity<List<TransactionHistory>> createTransactionHistory(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, @RequestBody ArrayList<TransactionHistory> transactionHistory);
}
