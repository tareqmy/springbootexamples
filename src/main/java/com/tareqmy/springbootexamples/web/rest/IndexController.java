package com.tareqmy.springbootexamples.web.rest;

import com.tareqmy.springbootexamples.web.payload.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping(value = {"/"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> getIndex() {
        return new ResponseEntity<>(new MessageResponse("hello-world!"), HttpStatus.OK);
    }
}
