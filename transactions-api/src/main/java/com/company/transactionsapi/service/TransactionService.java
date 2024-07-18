package com.company.transactionsapi.service;

import com.company.transactionsapi.model.dto.CreateTransactionRequest;


public interface TransactionService {
    void createTransaction(CreateTransactionRequest createTransactionRequest);
}
