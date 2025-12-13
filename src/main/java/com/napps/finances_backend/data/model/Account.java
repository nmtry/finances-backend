package com.napps.finances_backend.data.model;

import com.napps.finances_backend.data.dto.AccountOwnershipRef;
import com.napps.finances_backend.data.dto.AccountPartitionRef;
import com.napps.finances_backend.data.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class Account {
    private String id;
    private AccountType type;
    private String name;
    private String institution;
    private Long creditLimit;
    private String imageUrl;
    private Instant dateCreated;
    private Instant dateUpdated;
    private List<AccountPartitionRef> partitions;
    private List<AccountOwnershipRef> ownerships;
}
