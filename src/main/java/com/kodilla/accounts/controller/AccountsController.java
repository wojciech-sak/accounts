package com.kodilla.accounts.controller;

import com.kodilla.accounts.controller.response.GetAccountsResponse;
import com.kodilla.accounts.mapper.AccountMapper;
import com.kodilla.accounts.service.AccountsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("/v1/accounts")
public class AccountsController {

    @Value("${application.allow-get-accounts}")
    private boolean allowGetAccounts;

    private final AccountsService accountsService;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountsController(AccountsService accountsService, AccountMapper accountMapper) {
        this.accountsService = accountsService;
        this.accountMapper = accountMapper;
    }

    @GetMapping
    public GetAccountsResponse getCustomerAccounts(@RequestParam Long customerId) {
        log.info("Get accounts for customerId: {}", customerId);
        if(!allowGetAccounts) {
            log.info("Getting accounts is disabled");
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Getting accounts is disabled");
        }
        return GetAccountsResponse.of(
                accountMapper.mapToAccountDtoList(accountsService.getCustomerAccounts(customerId)));
    }
}
