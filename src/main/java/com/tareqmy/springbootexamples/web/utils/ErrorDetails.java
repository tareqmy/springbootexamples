package com.tareqmy.springbootexamples.web.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Created by tareqmy at 6/3/22
 */
@Getter
@AllArgsConstructor
public class ErrorDetails {

    private final int status;

    private final LocalDateTime timestamp;

    private final String message;

    private final String details;
}
