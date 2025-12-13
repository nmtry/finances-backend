package com.napps.finances_backend.data.dto;

import com.napps.finances_backend.data.enums.AccountPartitionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class AccountPartitionRef {
    private String id;
    private String parentAccountId;
    private AccountPartitionType type;
    private String name;
    private Long balance;
}
