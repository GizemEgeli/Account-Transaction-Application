package com.company.customeraccountsapi.service;

import com.company.customeraccountsapi.model.dto.UserAccountInfoDTO;

public interface UserAccountInfoService {
    UserAccountInfoDTO getUserAccountInfo(Long customerId);
}
