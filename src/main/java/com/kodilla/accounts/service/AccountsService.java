package com.kodilla.accounts.service;

import com.kodilla.accounts.domain.Account;
import com.kodilla.accounts.dto.AccountDto;

import java.math.BigDecimal;
import java.util.List;

public interface AccountsService {

    List<Account> getCustomerAccounts(Long customerId);

    Account getAccountByNrb(String nrb);

    Account save(Account account);

    AccountDto updateAccountFunds(String nrb, BigDecimal amount);
}
