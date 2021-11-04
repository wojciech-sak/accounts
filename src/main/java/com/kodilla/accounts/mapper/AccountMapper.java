package com.kodilla.accounts.mapper;

import com.kodilla.accounts.domain.Account;
import com.kodilla.accounts.dto.AccountDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapper {

    public List<AccountDto> mapToAccountDtoList(List<Account> accounts) {
        return accounts.stream()
                .map(this::mapToAccountDto)
                .collect(Collectors.toList());
    }

    public AccountDto mapToAccountDto(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .nrb(account.getNrb())
                .currency(account.getCurrency())
                .availableFunds(account.getAvailableFunds())
                .build();
    }
}
