package com.INetwork.ReportGeneration.repository;

import com.INetwork.ReportGeneration.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long>  {

}
