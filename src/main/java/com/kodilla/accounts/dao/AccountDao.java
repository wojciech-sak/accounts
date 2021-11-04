package com.kodilla.accounts.dao;

import com.kodilla.accounts.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface AccountDao extends CrudRepository<Account, Long> {

    List<Account> findAccountsByCustomer_Id(Long id);
}
