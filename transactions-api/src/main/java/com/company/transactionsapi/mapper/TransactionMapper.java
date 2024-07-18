package com.company.transactionsapi.mapper;

import com.company.transactionsapi.model.dto.TransactionDTO;
import com.company.transactionsapi.model.entity.AccountTransaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionDTO toTransactionDTO(AccountTransaction accountTransaction);
}
