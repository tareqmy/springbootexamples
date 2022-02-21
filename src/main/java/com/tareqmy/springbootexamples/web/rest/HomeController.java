package com.tareqmy.springbootexamples.web.rest;

import com.tareqmy.springbootexamples.data.entity.User;
import com.tareqmy.springbootexamples.service.AccountService;
import com.tareqmy.springbootexamples.web.dto.UserDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
//@SecurityRequirement(name = "${springbootexamples.app.securitySchemeKey}") //NOTE: it doesnt work :(
@SecurityRequirement(name = "tareqmySchemeKey")
public class HomeController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/user")
    public UserDTO getProfile() {
        User currentLoggedInUser = accountService.getUser();
        return new UserDTO(currentLoggedInUser);
    }
}
