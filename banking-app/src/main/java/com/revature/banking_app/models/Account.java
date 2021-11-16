package com.revature.banking_app.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Account extends Object{

    private String id;
    private String accountName;
    private String accountType;
    private BigDecimal balance;

    public Account(String accountName, String accountType, BigDecimal balance) {
        this.accountName = accountName;
        this.accountType = accountType;
        this.balance = balance;
    }

    public Account(String id, String accountName, String accountType, BigDecimal balance) {
        this.id = id;
        this.accountName = accountName;
        this.accountType = accountType;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account acc = (Account) o;
        return Objects.equals(id, acc.id) && Objects.equals(accountName, acc.accountName) && Objects.equals(accountType, acc.accountType) && Objects.equals(balance, acc.balance);
    }
}
