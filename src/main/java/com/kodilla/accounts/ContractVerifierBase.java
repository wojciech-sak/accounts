package com.kodilla.accounts;

import com.kodilla.accounts.dao.AccountDao;
import com.kodilla.accounts.domain.Account;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.Mockito.when;

@Disabled
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ContractVerifierBase {

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private AccountDao repository;

    @BeforeEach
    public void setUp() {
        RestAssuredMockMvc.webAppContextSetup(context);
        when(repository.findAccountsByCustomer_Id(10L)).thenReturn(
                Collections.singletonList(
                        Account.builder()
                                .id(11L)
                                .nrb("08897810189710581776778244")
                                .currency("PLN")
                                .availableFunds(new BigDecimal(1000))
                                .build()
                )
        );
    }

}