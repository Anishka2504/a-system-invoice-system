package com.example.invoiceservice.validator.invoice;

import com.example.invoiceservice.exception.InvalidFileNameException;
import com.example.invoiceservice.validator.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

import static com.example.invoiceservice.constant.Constants.LOG_UPLOAD_REJECTED;
import static com.example.invoiceservice.entity.enums.ValidatorError.DIGITS_ONLY_ERROR;

@Component
@Slf4j
public class InvoiceFileNameDigitsOnlyValidator implements Validator<MultipartFile> {

    @Override
    public Integer priority() {
        return 2;
    }

    @Override
    public void validate(MultipartFile file) {
        var fileName = Objects.requireNonNull(file.getOriginalFilename())
                .substring(0, file.getOriginalFilename().lastIndexOf('.'))
                .replaceAll("_", "");
        if (!fileName.matches("\\d+")) {
            log.error(LOG_UPLOAD_REJECTED, file.getOriginalFilename(), DIGITS_ONLY_ERROR.getMessage());
            throw new InvalidFileNameException(DIGITS_ONLY_ERROR.getMessage());
        }
    }
}

