package com.company.customeraccountsapi.model.dto;

import java.util.List;

public record UserAccountInfoDTO(
        String name,
        String surname,
        List<AccountDTO> accounts) {
}
