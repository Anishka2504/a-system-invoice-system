package com.example.invoiceservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InvalidFileNameException extends RuntimeException {

    public InvalidFileNameException() {
    }

    public InvalidFileNameException(String message) {
        super(message);
    }
}
