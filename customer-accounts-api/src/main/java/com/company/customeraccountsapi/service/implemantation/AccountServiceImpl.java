package com.company.customeraccountsapi.service.implemantation;

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

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final AccountTransactionClient accountTransactionClient;

    @Override
    public void createAccount(CreateAccountRequest createAccountRequest) {
        CustomerDTO customer = customerService
                .getCustomerById(createAccountRequest.customerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Account account = new Account();
        account.setCustomerId(customer.id());
        account.setBalance(createAccountRequest.initialCredit());
        account = accountRepository.save(account);

        if (createAccountRequest.initialCredit().compareTo(BigDecimal.ZERO) > 0) {
            accountTransactionClient.createTransaction(
                    new CreateTransactionApiRequest(account.getId(),
                            createAccountRequest.initialCredit(),
                            TransactionType.DEPOSIT,
                            "Initial Credit"));
        }
    }
}
