package com.company.transactionsapi.model.dto;

import com.company.transactionsapi.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDTO(
        String referenceId,
        Long accountId,
        BigDecimal amount,
        TransactionType transactionType,
        String description,
        LocalDateTime transactionDate) {
}
