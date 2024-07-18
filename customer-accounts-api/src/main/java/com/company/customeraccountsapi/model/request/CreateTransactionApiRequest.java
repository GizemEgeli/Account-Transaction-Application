package com.company.customeraccountsapi.model.request;

import com.company.customeraccountsapi.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateTransactionApiRequest {
    private Long accountId;
    private BigDecimal amount;
    private TransactionType transactionType;
    private String description;
}
