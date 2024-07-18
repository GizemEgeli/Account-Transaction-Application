package com.company.customeraccountsapi.service;

import com.company.customeraccountsapi.model.dto.CustomerDTO;

import java.util.Optional;

public interface CustomerService {
    Optional<CustomerDTO> getCustomerById(Long id);

}
