package com.company.transactionsapi.controller;

import com.company.transactionsapi.model.dto.CreateTransactionRequest;
import com.company.transactionsapi.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account-transactions")
public class AccountTransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Void> createTransaction(@RequestBody CreateTransactionRequest createTransactionRequest) {
        transactionService.createTransaction(createTransactionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
