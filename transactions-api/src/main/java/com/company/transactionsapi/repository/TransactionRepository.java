package com.company.transactionsapi.repository;

import com.company.transactionsapi.model.entity.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<AccountTransaction, Long> {
}