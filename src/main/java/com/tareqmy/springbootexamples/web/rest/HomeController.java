package com.tareqmy.springbootexamples.web.rest;

import com.tareqmy.springbootexamples.web.payload.response.MessageResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
//@SecurityRequirement(name = "${springbootexamples.app.securitySchemeKey}") //NOTE: it doesnt work :(
@SecurityRequirement(name = "tareqmySchemeKey")
public class HomeController {

    @GetMapping(value = "/home", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> getUserHome() {
        return new ResponseEntity<>(new MessageResponse("home"), HttpStatus.OK);
    }
}
