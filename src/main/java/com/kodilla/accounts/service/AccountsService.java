package com.kodilla.accounts.service;

import com.kodilla.accounts.domain.Account;

import java.util.List;

public interface AccountsService {

    List<Account> getCustomerAccounts(Long customerId);
}
