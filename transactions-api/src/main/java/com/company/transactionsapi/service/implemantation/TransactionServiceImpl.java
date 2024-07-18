package com.company.transactionsapi.service.implemantation;

import com.company.transactionsapi.mapper.TransactionMapper;
import com.company.transactionsapi.model.dto.CreateTransactionRequest;
import com.company.transactionsapi.model.dto.TransactionDTO;
import com.company.transactionsapi.model.entity.AccountTransaction;
import com.company.transactionsapi.repository.TransactionRepository;
import com.company.transactionsapi.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public void createTransaction(CreateTransactionRequest createTransactionRequest) {
        AccountTransaction transaction = new AccountTransaction();
        transaction.setAccountId(createTransactionRequest.accountId());
        transaction.setAmount(createTransactionRequest.amount());
        transaction.setReferenceId(UUID.randomUUID().toString());
        transaction.setDescription(createTransactionRequest.description());
        transaction.setTransactionType(createTransactionRequest.transactionType());
        transactionRepository.save(transaction);
    }

    public List<TransactionDTO> getTransactionsByAccountId(Long accountId) {
        List<AccountTransaction> transactions = transactionRepository.findByAccountId(accountId);
        return transactions.stream()
                .map(transactionMapper::toTransactionDTO)
                .toList();
    }
}
