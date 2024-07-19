package com.company.customeraccountsapi.service.implemantation;

import com.company.customeraccountsapi.exception.CustomerNotFoundException;
import com.company.customeraccountsapi.model.TransactionType;
import com.company.customeraccountsapi.model.dto.CreateAccountRequest;
import com.company.customeraccountsapi.model.dto.CustomerDTO;
import com.company.customeraccountsapi.model.entity.Account;
import com.company.customeraccountsapi.model.request.CreateTransactionApiRequest;
import com.company.customeraccountsapi.repository.AccountRepository;
import com.company.customeraccountsapi.service.AccountService;
import com.company.customeraccountsapi.service.CustomerService;
import com.company.customeraccountsapi.service.remote.AccountTransactionClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final AccountTransactionClient accountTransactionClient;

    @Override
    @Transactional
    public void createAccount(CreateAccountRequest createAccountRequest) {
        CustomerDTO customer = customerService.getCustomerById(createAccountRequest.customerId())
                .orElseThrow(() -> {
                    log.error("Customer not found: ID {}", createAccountRequest.customerId());
                    return new CustomerNotFoundException("Customer not found");
                });

        Account account = new Account();
        account.setCustomerId(customer.id());
        account.setBalance(createAccountRequest.initialCredit());
        account = accountRepository.save(account);

        if (createAccountRequest.initialCredit().compareTo(BigDecimal.ZERO) > 0) {
            log.info("Creating initial credit transaction for account ID: {}", account.getId());
            CreateTransactionApiRequest transactionRequest = new CreateTransactionApiRequest(
                    account.getId(),
                    createAccountRequest.initialCredit(),
                    TransactionType.DEPOSIT,
                    "Initial Credit");

            try {
                accountTransactionClient.createTransaction(transactionRequest);
            } catch (Exception e) {
                log.error("Failed to create initial credit transaction for account ID: {}", account.getId(), e);
                throw e;
            }
        }
    }

    @Override
    public List<Account> getAccountsByCustomerId(Long customerId) {
        validateCustomerId(customerId);
        return accountRepository.findByCustomerId(customerId);
    }

    private void validateCustomerId(Long customerId) {
        if (customerId == null || customerId <= 0) {
            log.error("Invalid customer ID: {}", customerId);
            throw new IllegalArgumentException("Customer ID cannot be null");
        }
    }
}
