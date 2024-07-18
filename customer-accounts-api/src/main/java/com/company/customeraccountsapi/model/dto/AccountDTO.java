package com.company.customeraccountsapi.model.dto;

import java.math.BigDecimal;
import java.util.List;

public record AccountDTO(Long id,
                         BigDecimal balance,
                         List<TransactionDTO> transactions) {
}
