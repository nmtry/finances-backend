package com.napps.finances_backend.data.dto;

import com.napps.finances_backend.data.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class AccountRef {
    private String id;
    private AccountType type;
    private String name;
}
