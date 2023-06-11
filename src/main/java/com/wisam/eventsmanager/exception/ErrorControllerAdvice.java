package com.wisam.eventsmanager.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        // Custom error handling logic
        return "error"; // Return the name of the error view
    }
}
