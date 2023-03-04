package com.example.boutiquehotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotSupportedActionException extends RuntimeException {
    public NotSupportedActionException(String message) {
        super(message);
    }
}
