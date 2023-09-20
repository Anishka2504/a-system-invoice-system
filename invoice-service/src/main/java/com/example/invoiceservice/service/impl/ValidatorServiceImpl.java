package com.example.invoiceservice.service.impl;

import com.example.invoiceservice.service.ValidatorService;
import com.example.invoiceservice.validator.InvoiceFileNameValidatorChain;
import com.example.invoiceservice.validator.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Comparator;

@Service
@AllArgsConstructor
public class ValidatorServiceImpl implements ValidatorService {

    private final InvoiceFileNameValidatorChain validators;
    @Override
    public void validateFilename(MultipartFile file) {
        validators.getValidators().stream()
                .sorted(Comparator.comparing(Validator::priority))
                .forEach(validator -> validator.validate(file));

    }
}
