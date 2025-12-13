package com.napps.finances_backend.data.dto;

import com.napps.finances_backend.data.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class AccountDTO {
    private String id;
    private AccountType type;
    private String name;
    private String institution;
    private Long creditLimit;
    private String imageUrl;
    private List<AccountPartitionRef> partitions;
    private List<AccountOwnershipRef> ownerships;
}
