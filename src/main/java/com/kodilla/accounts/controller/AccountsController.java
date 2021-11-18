package com.kodilla.accounts.controller;

import com.kodilla.accounts.controller.response.GetAccountsResponse;
import com.kodilla.accounts.domain.Account;
import com.kodilla.accounts.dto.AccountDto;
import com.kodilla.accounts.mapper.AccountMapper;
import com.kodilla.accounts.service.AccountsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

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

    @GetMapping("/{nrb}")
    public AccountDto getAccountByNrb(@PathVariable String nrb) {
        log.info("Get account with nrb number: {}", nrb);
        return accountMapper.mapToAccountDto(accountsService.getAccountByNrb(nrb));
    }

    @PutMapping("/{nrb}/{amount}")
    public AccountDto updateAccountAvailableFunds(@PathVariable String nrb, @PathVariable BigDecimal amount) {
        log.info("Updating account with nrb number: {}, changing amount by: {}", nrb, amount);
        return accountsService.updateAccountFunds(nrb, amount);
    }
}
