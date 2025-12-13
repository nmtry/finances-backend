package com.napps.finances_backend.data.dto;

import com.napps.finances_backend.data.enums.AccountOwnershipType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class AccountOwnershipRef {
    private String id;
    private String accountId;
    private UserRef user;
    private AccountOwnershipType type;
}
