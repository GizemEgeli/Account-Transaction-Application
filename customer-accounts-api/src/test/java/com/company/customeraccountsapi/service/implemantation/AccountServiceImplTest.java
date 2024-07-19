package com.company.customeraccountsapi.service.implemantation;

import com.company.customeraccountsapi.exception.CustomerNotFoundException;
import com.company.customeraccountsapi.model.dto.CreateAccountRequest;
import com.company.customeraccountsapi.model.dto.CustomerDTO;
import com.company.customeraccountsapi.model.entity.Account;
import com.company.customeraccountsapi.model.request.CreateTransactionApiRequest;
import com.company.customeraccountsapi.repository.AccountRepository;
import com.company.customeraccountsapi.service.CustomerService;
import com.company.customeraccountsapi.service.remote.AccountTransactionClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private CustomerService customerService;

    @Mock
    private AccountTransactionClient accountTransactionClient;

    @InjectMocks
    private AccountServiceImpl accountService;


    @Test
    void createAccount_Success() {
        CreateAccountRequest request = new CreateAccountRequest(1L, BigDecimal.valueOf(100));
        CustomerDTO customerDTO = new CustomerDTO(1L, "Gizem", "Egeli");
        Account savedAccount = new Account();
        savedAccount.setId(1L);
        savedAccount.setCustomerId(1L);
        savedAccount.setBalance(BigDecimal.valueOf(100));

        when(customerService.getCustomerById(request.customerId())).thenReturn(Optional.of(customerDTO));
        when(accountRepository.save(any(Account.class))).thenReturn(savedAccount);

        accountService.createAccount(request);

        verify(accountRepository).save(any(Account.class));
        verify(accountTransactionClient).createTransaction(any(CreateTransactionApiRequest.class));

    }

    @Test
    void createAccount_CustomerNotFound() {
        CreateAccountRequest request = new CreateAccountRequest(1L, BigDecimal.valueOf(100));

        when(customerService.getCustomerById(request.customerId())).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> {
            accountService.createAccount(request);
        });

        verify(accountRepository, never()).save(any(Account.class));
        verify(accountTransactionClient, never()).createTransaction(any(CreateTransactionApiRequest.class));
    }

    @Test
    void createAccount_TransactionFailure() {
        CreateAccountRequest request = new CreateAccountRequest(1L, BigDecimal.valueOf(100));
        CustomerDTO customerDTO = new CustomerDTO(1L, "Gizem", "Egeli");
        Account savedAccount = new Account();
        savedAccount.setId(1L);
        savedAccount.setCustomerId(1L);
        savedAccount.setBalance(BigDecimal.valueOf(100));

        when(customerService.getCustomerById(request.customerId())).thenReturn(Optional.of(customerDTO));
        when(accountRepository.save(any(Account.class))).thenReturn(savedAccount);
        doThrow(new RuntimeException("Transaction service unavailable"))
                .when(accountTransactionClient)
                .createTransaction(any(CreateTransactionApiRequest.class));

        assertThrows(RuntimeException.class, () -> {
            accountService.createAccount(request);
        });

        verify(accountRepository).save(any(Account.class));
        verify(accountTransactionClient).createTransaction(any(CreateTransactionApiRequest.class));
    }

    @Test
    void getAccountsByCustomerId_Success() {
        Account account = new Account();
        account.setId(1L);
        account.setCustomerId(1L);
        account.setBalance(BigDecimal.valueOf(100));

        List<Account> accounts = List.of(account);

        when(accountRepository.findByCustomerId(1L)).thenReturn(accounts);

        List<Account> result = accountService.getAccountsByCustomerId(1L);

        assertEquals(1, result.size());
        verify(accountRepository).findByCustomerId(1L);
    }

    @Test
    void getAccountsByCustomerId_InvalidCustomerId() {
        assertThrows(IllegalArgumentException.class, () -> {
            accountService.getAccountsByCustomerId(null);
        });

        verify(accountRepository, never()).findByCustomerId(any(Long.class));
    }
}
