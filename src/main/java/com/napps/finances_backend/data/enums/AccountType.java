package com.napps.finances_backend.data.enums;

public enum AccountType {
    CHECKING("Checking"),
    SAVINGS("Savings"),
    RETIREMENT("Retirement"),
    INVESTMENT("Investment"),
    CASH("Cash"),
    CREDIT_CARD("Credit Card"),
    HEALTH_SAVINGS("Health Savings Account"),
    DEPOSIT("Deposit");

    private final String name;

    AccountType(String name) {
        this.name = name;
    }

    public String getName() { return this.name; }
}
