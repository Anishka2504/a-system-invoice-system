package com.example.invoiceservice.validator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Configuration
@AllArgsConstructor
@Getter
public class InvoiceFileNameValidatorChain {

    private final List<Validator<MultipartFile>> validators;
}
