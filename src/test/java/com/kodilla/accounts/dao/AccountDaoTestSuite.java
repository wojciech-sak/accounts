package com.kodilla.accounts.dao;

import com.kodilla.accounts.domain.Account;
import com.kodilla.accounts.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class AccountDaoTestSuite {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private AccountDao accountDao;

    @Test
    void findAccountsByCustomer_Id() {
        //Given
        Account account1 = new Account("nrb1", "PLN", new BigDecimal(1000));
        Account account2 = new Account("nrb2", "EUR", new BigDecimal(2000));

        Customer customer = new Customer();
        account1.setCustomer(customer);
        account2.setCustomer(customer);

        List<Account> accounts = new ArrayList<>(Arrays.asList(account1, account2));
        customer.setAccounts(accounts);

        Customer savedCustomer = customerDao.save(customer);

        //When
        List<Account> retrievedAccounts = accountDao.findAccountsByCustomer_Id(savedCustomer.getId());

        //Then
        assertEquals(retrievedAccounts.size(), 2);
    }
}