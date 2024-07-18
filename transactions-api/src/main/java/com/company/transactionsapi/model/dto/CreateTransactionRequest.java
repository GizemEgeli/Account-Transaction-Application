package com.company.transactionsapi.model.dto;

import com.company.transactionsapi.model.TransactionType;

import java.math.BigDecimal;

public record CreateTransactionRequest(
        Long accountId,
        BigDecimal amount,
        TransactionType transactionType,
        String description) {
}
