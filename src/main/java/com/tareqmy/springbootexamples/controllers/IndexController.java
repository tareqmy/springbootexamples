package com.tareqmy.springbootexamples.controllers;

import com.tareqmy.springbootexamples.security.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping(value = {"/", "/index"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getHome() {
        String message = SecurityUtils.isAnonymousUser() ? "index" : "home";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping(value = "/home", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUserHome() {
        return new ResponseEntity<>("home", HttpStatus.OK);
    }
}
