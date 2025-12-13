package com.napps.finances_backend.data.dto;

import com.napps.finances_backend.data.enums.AccountOwnershipType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class AccountOwnershipDTO {
    private String id;
    private UserRef user;
    private String accountId;
    private AccountOwnershipType type;
}
