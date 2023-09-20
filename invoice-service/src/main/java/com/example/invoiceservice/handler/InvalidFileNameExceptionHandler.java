package com.example.invoiceservice.handler;

import com.example.invoiceservice.exception.InvalidFileNameException;
import com.example.invoiceservice.handler.details.ErrorDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import static com.example.invoiceservice.constant.Constants.DATE_TIME_PATTERN;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

@RestControllerAdvice
public class InvalidFileNameExceptionHandler {

    @ExceptionHandler(InvalidFileNameException.class)
    public ErrorDetails handleInvalidFileNameException(InvalidFileNameException exception) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_PATTERN);
                return ErrorDetails.builder()
                .timestamp(format.format(Date.from(Instant.now())))
                .message(exception.getMessage())
                .status(NOT_ACCEPTABLE)
                .statusCode(NOT_ACCEPTABLE.value())
                .build();
    }


}
