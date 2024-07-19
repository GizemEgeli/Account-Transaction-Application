package com.company.transactionsapi.controller;


import com.company.transactionsapi.model.TransactionType;
import com.company.transactionsapi.model.dto.CreateTransactionRequest;
import com.company.transactionsapi.model.dto.TransactionDTO;
import com.company.transactionsapi.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountTransactionController.class)
class AccountTransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void testCreateTransaction() throws Exception {
        CreateTransactionRequest createTransactionRequest = new CreateTransactionRequest(1L, BigDecimal.valueOf(100), TransactionType.DEPOSIT, "Deposit transaction");

        mockMvc.perform(post("/api/v1/account-transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createTransactionRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetTransactionsByAccountId() throws Exception {
        TransactionDTO transaction1 = new TransactionDTO("ref1", 1L, BigDecimal.valueOf(100), TransactionType.DEPOSIT, "Deposit transaction", null);
        TransactionDTO transaction2 = new TransactionDTO("ref2", 1L, BigDecimal.valueOf(50), TransactionType.WITHDRAWAL, "Withdrawal transaction", null);
        List<TransactionDTO> transactions = Arrays.asList(transaction1, transaction2);

        when(transactionService.getTransactionsByAccountId(1L)).thenReturn(transactions);

        mockMvc.perform(get("/api/v1/account-transactions/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].referenceId", is("ref1")))
                .andExpect(jsonPath("$[0].amount", is(100)))
                .andExpect(jsonPath("$[0].transactionType", is("DEPOSIT")))
                .andExpect(jsonPath("$[1].referenceId", is("ref2")))
                .andExpect(jsonPath("$[1].amount", is(50)))
                .andExpect(jsonPath("$[1].transactionType", is("WITHDRAWAL")));
    }
}
