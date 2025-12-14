package com.napps.finances_backend.service;

import com.napps.finances_backend.data.dto.AccountDTO;
import com.napps.finances_backend.data.dto.requests.AccountRequest;
import com.napps.finances_backend.data.dto.responses.AccountResponse;
import com.napps.finances_backend.data.dto.responses.AccountsResponse;
import com.napps.finances_backend.data.model.Account;
import com.napps.finances_backend.repository.AccountRepository;
import com.napps.finances_backend.util.ObjectMappers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public ResponseEntity<AccountsResponse> getAccounts() {
        List<Account> accounts = accountRepository.getAccounts();
        List<AccountDTO> dtos = accounts.stream().map(ObjectMappers::accountToDTO).toList();
        AccountsResponse response = new AccountsResponse(dtos);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<AccountResponse> postAccount(AccountRequest accountRequest) {

        return ResponseEntity.ok(new AccountResponse());
    }
}
