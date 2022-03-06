package com.tareqmy.springbootexamples.web.rest;

import com.tareqmy.springbootexamples.data.entity.User;
import com.tareqmy.springbootexamples.service.AccountService;
import com.tareqmy.springbootexamples.web.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/account/")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public AccountDTO getAccount() {
        User currentLoggedInUser = accountService.getUser();
        return new AccountDTO(currentLoggedInUser);
    }
}
