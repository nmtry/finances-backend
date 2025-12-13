package com.napps.finances_backend.controller;

import com.napps.finances_backend.data.dto.responses.AccountsResponse;
import com.napps.finances_backend.service.AccountsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/accounts")
public class AccountsController {
    private final AccountsService accountsService;

    public AccountsController(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    @GetMapping()
    public ResponseEntity<AccountsResponse> getAccounts() {
        return accountsService.getAccounts();
    }
}
