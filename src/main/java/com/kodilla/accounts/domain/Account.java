package com.kodilla.accounts.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "ACCOUNT")
@Builder
public class Account {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ACCOUNT_ID", unique = true)
    private Long id;

    private String nrb;

    private String currency;

    private BigDecimal availableFunds;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    public Account(String nrb, String currency, BigDecimal availableFunds) {
        this.nrb = nrb;
        this.currency = currency;
        this.availableFunds = availableFunds;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
