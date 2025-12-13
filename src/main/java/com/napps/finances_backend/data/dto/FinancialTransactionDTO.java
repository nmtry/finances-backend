package com.napps.finances_backend.data.dto;

import com.napps.finances_backend.data.enums.FinancialTransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data @AllArgsConstructor @NoArgsConstructor
public class FinancialTransactionDTO {
    private String id;
    private UserRef user;
    private FinancialTransactionType type;
    private Long amount;
    private AccountRef fromAccount;
    private AccountRef toAccount;
    private String fromName;
    private String toName;
    private Instant transactionDate;
}
