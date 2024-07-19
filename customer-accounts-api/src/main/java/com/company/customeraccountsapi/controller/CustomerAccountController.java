package com.company.customeraccountsapi.controller;

import com.company.customeraccountsapi.model.dto.CreateAccountRequest;
import com.company.customeraccountsapi.model.dto.CustomerAccountInfoDTO;
import com.company.customeraccountsapi.service.AccountService;
import com.company.customeraccountsapi.service.CustomerAccountInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class CustomerAccountController {
    private final AccountService accountService;
    private final CustomerAccountInfoService customerAccountInfoService;

    @PostMapping
    public ResponseEntity<Void> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        accountService.createAccount(createAccountRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{customerId}/account-info")
    public ResponseEntity<CustomerAccountInfoDTO> getCustomerAccountInfo(@PathVariable Long customerId) {
        CustomerAccountInfoDTO customerAccountInfoDTO = customerAccountInfoService.getCustomerAccountInfo(customerId);
        return new ResponseEntity<>(customerAccountInfoDTO, HttpStatus.OK);
    }
}
