package com.tareqmy.springbootexamples.web.rest;

import com.tareqmy.springbootexamples.web.payload.response.MessageResponse;
import com.tareqmy.springbootexamples.web.utils.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping(value = {"/", "/index"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> getHome() {
        String message = SecurityUtils.isAnonymousUser() ? "index" : "home";
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

    @GetMapping(value = "/home", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> getUserHome() {
        return new ResponseEntity<>(new MessageResponse("home"), HttpStatus.OK);
    }
}
