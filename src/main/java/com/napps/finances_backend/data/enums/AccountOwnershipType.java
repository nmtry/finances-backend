package com.napps.finances_backend.data.enums;

public enum AccountOwnershipType {
    OWNER("OWNER"),
    JOINT_OWNER("Joint Owner"),
    VIEWER("Viewer");

    private final String name;

    AccountOwnershipType(String name) {
        this.name = name;
    }

    public String getName() { return this.name; }
}
