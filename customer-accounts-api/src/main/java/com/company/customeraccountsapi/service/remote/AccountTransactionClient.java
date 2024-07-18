package com.company.customeraccountsapi.service.remote;


import com.company.customeraccountsapi.model.request.CreateTransactionApiRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "transaction-service", url = "http://localhost:8082/api/v1/account-transactions")
public interface AccountTransactionClient {

    @PostMapping
    void createTransaction(@RequestBody CreateTransactionApiRequest createTransactionApiRequest);

}
