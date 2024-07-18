package com.company.customeraccountsapi.service.implemantation;

import com.company.customeraccountsapi.model.dto.AccountDTO;
import com.company.customeraccountsapi.model.dto.CustomerDTO;
import com.company.customeraccountsapi.model.dto.TransactionDTO;
import com.company.customeraccountsapi.model.dto.UserAccountInfoDTO;
import com.company.customeraccountsapi.service.AccountService;
import com.company.customeraccountsapi.service.CustomerService;
import com.company.customeraccountsapi.service.UserAccountInfoService;
import com.company.customeraccountsapi.service.remote.AccountTransactionClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAccountInfoServiceImpl implements UserAccountInfoService {
    private final CustomerService customerService;
    private final AccountService accountService;
    private final AccountTransactionClient transactionClient;

    @Override
    public UserAccountInfoDTO getUserAccountInfo(Long customerId) {
        CustomerDTO customer = customerService.getCustomerById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        List<AccountDTO> accountDTOList = accountService.getAccountsByCustomerId(customerId).stream()
                .map(account -> {
                    List<TransactionDTO> transactions = transactionClient.getTransactionsByAccountId(account.getId());
                    return new AccountDTO(account.getId(), account.getBalance(), transactions);
                })
                .toList();

        return new UserAccountInfoDTO(customer.name(), customer.surname(), accountDTOList);
    }
}
