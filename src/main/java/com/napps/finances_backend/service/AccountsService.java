package com.napps.finances_backend.service;

import com.napps.finances_backend.data.dto.AccountDTO;
import com.napps.finances_backend.data.dto.responses.AccountsResponse;
import com.napps.finances_backend.data.model.Account;
import com.napps.finances_backend.repository.AccountsRepository;
import com.napps.finances_backend.util.ObjectMappers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountsService {
    private final AccountsRepository accountsRepository;

    public AccountsService(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    public ResponseEntity<AccountsResponse> getAccounts() {
        List<Account> accounts = accountsRepository.getAccounts();
        List<AccountDTO> dtos = accounts.stream().map(ObjectMappers::accountToDTO).toList();
        AccountsResponse response = new AccountsResponse(dtos);

        return ResponseEntity.ok(response);
    }
}
