package com.example.invoiceservice.validator.invoice;

import com.example.invoiceservice.exception.InvalidFileNameException;
import com.example.invoiceservice.validator.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

import static com.example.invoiceservice.constant.Constants.LOG_UPLOAD_REJECTED;
import static com.example.invoiceservice.entity.enums.ValidatorError.INCORRECT_PARTS_ERROR;
import static com.example.invoiceservice.utils.InvoiceFileUtil.fileNameToArray;

@Component
@Slf4j
public class InvoiceFileNameComponentsNumberValidator implements Validator<MultipartFile> {

    @Override
    public Integer priority() {
        return 3;
    }

    @Override
    public void validate(MultipartFile file) {
        var filenameAsArray = fileNameToArray(Objects.requireNonNull(file.getOriginalFilename()));
        if (filenameAsArray.length != 5) {
            log.error(LOG_UPLOAD_REJECTED, file.getOriginalFilename(), INCORRECT_PARTS_ERROR.getMessage());
            throw new InvalidFileNameException(INCORRECT_PARTS_ERROR.getMessage());
        }
    }
}
