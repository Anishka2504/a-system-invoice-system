package com.example.invoiceservice.validator.invoice;

import com.example.invoiceservice.entity.enums.ValidatorError;
import com.example.invoiceservice.exception.InvalidFileNameException;
import com.example.invoiceservice.validator.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import static com.example.invoiceservice.constant.Constants.LOG_UPLOAD_REJECTED;

@Component
@Slf4j
public class InvoiceFileNameIsEmptyValidator implements Validator<MultipartFile> {

    @Override
    public Integer priority() {
        return 1;
    }

    @Override
    public void validate(MultipartFile file) {
        var fileName = file.getOriginalFilename();
        if (fileName == null || fileName.isBlank() || fileName.isEmpty()) {
            log.error(LOG_UPLOAD_REJECTED, fileName, ValidatorError.NO_FILE_SELECTED_ERROR.getMessage());
            throw new InvalidFileNameException(ValidatorError.NO_FILE_SELECTED_ERROR.getMessage());
        }

    }
}
