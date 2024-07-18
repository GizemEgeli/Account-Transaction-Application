package com.company.customeraccountsapi.controller;

import com.company.customeraccountsapi.model.dto.CreateAccountRequest;
import com.company.customeraccountsapi.model.dto.UserAccountInfoDTO;
import com.company.customeraccountsapi.service.AccountService;
import com.company.customeraccountsapi.service.UserAccountInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class CustomerAccountController {
    private final AccountService accountService;
    private final UserAccountInfoService userAccountInfoService;

    @PostMapping
    public ResponseEntity<Void> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        accountService.createAccount(createAccountRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{customerId}/account-info")
    public ResponseEntity<UserAccountInfoDTO> getUserAccountInfo(@PathVariable Long customerId) {
        UserAccountInfoDTO userAccountInfo = userAccountInfoService.getUserAccountInfo(customerId);
        return new ResponseEntity<>(userAccountInfo, HttpStatus.OK);
    }
}
