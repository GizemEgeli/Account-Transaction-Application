package com.company.customeraccountsapi.model.dto;

import java.math.BigDecimal;

public record CreateAccountRequest(Long customerId,
                                   BigDecimal initialCredit) {
}
