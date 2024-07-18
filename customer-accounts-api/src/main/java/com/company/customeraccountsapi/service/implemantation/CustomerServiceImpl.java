package com.company.customeraccountsapi.service.implemantation;

import com.company.customeraccountsapi.mapper.CustomerMapper;
import com.company.customeraccountsapi.model.dto.CustomerDTO;
import com.company.customeraccountsapi.model.entity.Customer;
import com.company.customeraccountsapi.repository.CustomerRepository;
import com.company.customeraccountsapi.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public Optional<CustomerDTO> getCustomerById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        return customerOptional.map(customerMapper::toCustomerDTO);
    }
}
