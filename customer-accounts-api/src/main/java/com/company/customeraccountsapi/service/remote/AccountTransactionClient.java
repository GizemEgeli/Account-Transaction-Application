package com.company.customeraccountsapi.service.remote;


import com.company.customeraccountsapi.model.dto.TransactionDTO;
import com.company.customeraccountsapi.model.request.CreateTransactionApiRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "transaction-service", url = "http://localhost:8082/api/v1/account-transactions")
public interface AccountTransactionClient {

    @PostMapping
    void createTransaction(@RequestBody CreateTransactionApiRequest createTransactionApiRequest);

    @GetMapping("/{accountId}")
    List<TransactionDTO> getTransactionsByAccountId(@PathVariable("accountId") Long accountId);
}
