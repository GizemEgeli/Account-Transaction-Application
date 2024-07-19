package com.company.customeraccountsapi.service;

import com.company.customeraccountsapi.model.dto.CustomerAccountInfoDTO;

public interface CustomerAccountInfoService {
    CustomerAccountInfoDTO getCustomerAccountInfo(Long customerId);
}
