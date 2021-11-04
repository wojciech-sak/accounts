package com.kodilla.accounts.dao;

import com.kodilla.accounts.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface CustomerDao extends CrudRepository<Customer, Long> {
}
