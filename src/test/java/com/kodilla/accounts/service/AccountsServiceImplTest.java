package com.kodilla.accounts.service;

import com.kodilla.accounts.dao.AccountDao;
import com.kodilla.accounts.domain.Account;
import com.kodilla.accounts.dto.AccountDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AccountsServiceImplTest {

    @Autowired
    private AccountsServiceImpl accountsService;

    @Autowired
    private AccountDao accountDao;

    @Test
    void updateAccountFunds() {
        //Given
        Account account1 = new Account("nrb1", "PLN", new BigDecimal(1000));
        Account account2 = new Account("nrb2", "EUR", new BigDecimal(2000));

        accountDao.save(account1);
        accountDao.save(account2);

        //When
        AccountDto updatedAccount1 = accountsService.updateAccountFunds(account1.getNrb(), new BigDecimal("300"));
        AccountDto updatedAccount2 = accountsService.updateAccountFunds(account2.getNrb(), new BigDecimal("-200"));

        //Then
        assertEquals(updatedAccount1.getAvailableFunds(), new BigDecimal("1300"));
        assertEquals(updatedAccount2.getAvailableFunds(), new BigDecimal("1800"));
    }
}