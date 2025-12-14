package com.napps.finances_backend.controller;

import com.napps.finances_backend.data.dto.requests.AccountRequest;
import com.napps.finances_backend.data.dto.responses.AccountResponse;
import com.napps.finances_backend.data.dto.responses.AccountsResponse;
import com.napps.finances_backend.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping()
    public ResponseEntity<AccountsResponse> getAccounts() {
        return accountService.getAccounts();
    }

    @PostMapping()
    public ResponseEntity<AccountResponse> postAccount(@RequestBody AccountRequest accountRequest) {
        return accountService.postAccount(accountRequest);
    }
}
