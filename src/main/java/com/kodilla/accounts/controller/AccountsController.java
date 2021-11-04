package com.kodilla.accounts.controller;

import com.kodilla.accounts.controller.response.GetAccountsResponse;
import com.kodilla.accounts.mapper.AccountMapper;
import com.kodilla.accounts.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/accounts")
public class AccountsController {

    private final AccountsService accountsService;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountsController(AccountsService accountsService, AccountMapper accountMapper) {
        this.accountsService = accountsService;
        this.accountMapper = accountMapper;
    }

    @GetMapping()
    public GetAccountsResponse getCustomerAccounts(@RequestParam Long customerId) {
        return GetAccountsResponse.of(
                accountMapper.mapToAccountDtoList(accountsService.getCustomerAccounts(customerId)));
    }
}
