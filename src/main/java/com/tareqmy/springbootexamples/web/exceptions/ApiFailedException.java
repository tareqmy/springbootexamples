package com.tareqmy.springbootexamples.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by tareqmy at 7/3/22
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ApiFailedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ApiFailedException(String message) {
        super(message);
    }

    public ApiFailedException(Exception e) {
        super(e);
    }
}
