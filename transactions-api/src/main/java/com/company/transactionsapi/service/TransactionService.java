package com.company.transactionsapi.service;

import com.company.transactionsapi.model.dto.CreateTransactionRequest;
import com.company.transactionsapi.model.dto.TransactionDTO;

import java.util.List;


public interface TransactionService {
    void createTransaction(CreateTransactionRequest createTransactionRequest);

    List<TransactionDTO> getTransactionsByAccountId(Long accountId);
}
