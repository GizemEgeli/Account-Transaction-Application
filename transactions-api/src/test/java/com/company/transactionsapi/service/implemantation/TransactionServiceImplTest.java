package com.company.transactionsapi.service.implemantation;

import com.company.transactionsapi.mapper.TransactionMapper;
import com.company.transactionsapi.model.TransactionType;
import com.company.transactionsapi.model.dto.CreateTransactionRequest;
import com.company.transactionsapi.model.dto.TransactionDTO;
import com.company.transactionsapi.model.entity.AccountTransaction;
import com.company.transactionsapi.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionMapper transactionMapper;

    @InjectMocks
    private TransactionServiceImpl transactionService;


    @Test
    void createTransaction_Success() {
        CreateTransactionRequest request = new CreateTransactionRequest(1L, BigDecimal.valueOf(100), TransactionType.DEPOSIT, "Test transaction");

        transactionService.createTransaction(request);

        verify(transactionRepository).save(any());
    }

    @Test
    void getTransactionsByAccountId_Success() {
        Long accountId = 1L;
        AccountTransaction transaction1 = new AccountTransaction();
        transaction1.setId(1L);
        transaction1.setAccountId(accountId);
        transaction1.setAmount(BigDecimal.valueOf(100));

        AccountTransaction transaction2 = new AccountTransaction();
        transaction2.setId(2L);
        transaction2.setAccountId(accountId);
        transaction2.setAmount(BigDecimal.valueOf(200));

        List<AccountTransaction> transactions = Arrays.asList(transaction1, transaction2);
        when(transactionRepository.findByAccountId(accountId)).thenReturn(transactions);

        TransactionDTO transactionDTO1 = new TransactionDTO(UUID.randomUUID().toString(), accountId, BigDecimal.valueOf(100), TransactionType.DEPOSIT, "Transaction 1", null);
        TransactionDTO transactionDTO2 = new TransactionDTO(UUID.randomUUID().toString(), accountId, BigDecimal.valueOf(200), TransactionType.WITHDRAWAL, "Transaction 2", null);

        when(transactionMapper.toTransactionDTO(transaction1)).thenReturn(transactionDTO1);
        when(transactionMapper.toTransactionDTO(transaction2)).thenReturn(transactionDTO2);

        List<TransactionDTO> result = transactionService.getTransactionsByAccountId(accountId);

        assertEquals(2, result.size());
        assertEquals(transactionDTO1, result.get(0));
        assertEquals(transactionDTO2, result.get(1));
    }

    @Test
    void getTransactionsByAccountId_InvalidAccountId() {

        assertThrows(IllegalArgumentException.class, () -> transactionService.getTransactionsByAccountId(null));

        verify(transactionRepository, never()).findByAccountId(any(Long.class));
    }
}
