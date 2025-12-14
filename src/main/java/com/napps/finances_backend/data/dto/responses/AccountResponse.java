package com.napps.finances_backend.data.dto.responses;

import com.napps.finances_backend.data.dto.AccountDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class AccountResponse {
    private AccountDTO data;
}
