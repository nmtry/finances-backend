package com.napps.finances_backend.data.enums;

public enum AccountPartitionType {
    MAIN("Main"),
    CUSTOM("Custom");

    private final String name;

    AccountPartitionType(String name) {
        this.name = name;
    }

    public String getName() { return this.name; }
}
