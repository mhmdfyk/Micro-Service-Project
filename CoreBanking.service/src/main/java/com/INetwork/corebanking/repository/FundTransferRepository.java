package com.INetwork.corebanking.repository;

import com.INetwork.corebanking.entity.Deposit;
import com.INetwork.corebanking.entity.FundTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface FundTransferRepository extends JpaRepository<FundTransfer, Long> {

}
