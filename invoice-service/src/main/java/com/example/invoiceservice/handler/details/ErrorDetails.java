package com.example.invoiceservice.handler.details;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@AllArgsConstructor
@Getter
@Builder
public class ErrorDetails {

    private String timestamp;
    private String message;
    private HttpStatus status;
    private int statusCode;

}
