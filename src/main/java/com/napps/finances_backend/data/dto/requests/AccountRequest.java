package com.napps.finances_backend.data.dto.requests;

import com.napps.finances_backend.data.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class AccountRequest {
    private AccountType type;
    private String name;
    private String institution;
    private Long creditLimit;
    private String imageUrl;
}
