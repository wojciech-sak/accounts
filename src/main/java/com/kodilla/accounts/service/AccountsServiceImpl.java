package com.kodilla.accounts.service;

import com.kodilla.accounts.dao.AccountDao;
import com.kodilla.accounts.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountsServiceImpl implements AccountsService {

    private final AccountDao accountDao;

    @Autowired
    public AccountsServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> getCustomerAccounts(Long customerId) {
        return accountDao.findAccountsByCustomer_Id(customerId);
    }
}
