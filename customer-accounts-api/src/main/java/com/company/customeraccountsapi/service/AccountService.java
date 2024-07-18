package com.company.customeraccountsapi.service;

import com.company.customeraccountsapi.model.dto.CreateAccountRequest;
import com.company.customeraccountsapi.model.entity.Account;

import java.util.List;

public interface AccountService {
    void createAccount(CreateAccountRequest createAccountRequest);

    List<Account> getAccountsByCustomerId(Long customerId);
}
