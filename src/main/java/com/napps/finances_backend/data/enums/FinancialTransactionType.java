package com.napps.finances_backend.data.enums;

public enum FinancialTransactionType {
    INTERNAL_TRANSFER("Internal Transfer"),
    PAYMENT("Payment"),
    DEPOSIT("Deposit"),
    CREDIT_CARD_BALANCE_PAYMENT("Credit Card Balance Payment");

    private final String name;

    FinancialTransactionType(String name) {
        this.name = name;
    }

    public String getName() { return this.name; }
}
