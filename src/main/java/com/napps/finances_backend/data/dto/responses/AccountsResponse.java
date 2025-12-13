package com.napps.finances_backend.data.dto.responses;

import com.napps.finances_backend.data.dto.AccountDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class AccountsResponse {
    private List<AccountDTO> data;
}
