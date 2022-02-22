package com.tareqmy.springbootexamples.web.rest;

import com.tareqmy.springbootexamples.data.entity.User;
import com.tareqmy.springbootexamples.service.AccountService;
import com.tareqmy.springbootexamples.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class HomeController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/user")
    public UserDTO getUser() {
        User currentLoggedInUser = accountService.getUser();
        return new UserDTO(currentLoggedInUser);
    }
}
