package com.INetwork.corebanking.repository;

import com.INetwork.corebanking.entity.BankAccount;
import com.INetwork.corebanking.entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;


@org.springframework.stereotype.Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {

}
