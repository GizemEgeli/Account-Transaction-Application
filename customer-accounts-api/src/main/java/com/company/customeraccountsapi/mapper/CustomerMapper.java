package com.company.customeraccountsapi.mapper;

import com.company.customeraccountsapi.model.dto.CustomerDTO;
import com.company.customeraccountsapi.model.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO toCustomerDTO(Customer customer);
}
