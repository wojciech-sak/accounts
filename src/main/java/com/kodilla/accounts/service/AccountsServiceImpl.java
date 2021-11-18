package com.kodilla.accounts.service;

import com.kodilla.accounts.dao.AccountDao;
import com.kodilla.accounts.domain.Account;
import com.kodilla.accounts.dto.AccountDto;
import com.kodilla.accounts.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountsServiceImpl implements AccountsService {

    private final AccountDao accountDao;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountsServiceImpl(AccountDao accountDao, AccountMapper accountMapper) {
        this.accountDao = accountDao;
        this.accountMapper = accountMapper;
    }

    @Override
    public List<Account> getCustomerAccounts(Long customerId) {
        return accountDao.findAccountsByCustomer_Id(customerId);
    }

    @Override
    public Account getAccountByNrb(String nrb) {
        Account account = accountDao.findAccountByNrb(nrb);
        if (account != null) {
            return accountDao.findAccountByNrb(nrb);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no such account!");
        }
    }

    @Override
    public Account save(Account account) {
        return accountDao.save(account);
    }

    public AccountDto updateAccountFunds(String nrb, BigDecimal amount) {
        Account account = getAccountByNrb(nrb);
        account.setAvailableFunds(account.getAvailableFunds().add(amount));
        return accountMapper.mapToAccountDto(save(account));
    }
}
