package com.company.customeraccountsapi.service;

import com.company.customeraccountsapi.model.dto.CreateAccountRequest;

public interface AccountService {
    void createAccount(CreateAccountRequest createAccountRequest);
}
