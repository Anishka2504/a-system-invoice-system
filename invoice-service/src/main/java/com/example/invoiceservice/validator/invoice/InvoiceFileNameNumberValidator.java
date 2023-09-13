package com.example.invoiceservice.validator.invoice;

import com.example.invoiceservice.exception.InvalidFileNameException;
import com.example.invoiceservice.validator.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import static com.example.invoiceservice.constant.Constants.LOG_UPLOAD_REJECTED;
import static com.example.invoiceservice.entity.enums.ValidatorError.INCORRECT_INVOICE_NUMBER_ERROR;
import static com.example.invoiceservice.utils.InvoiceFileUtil.getInvoiceNumber;

@Component
@Slf4j
public class InvoiceFileNameNumberValidator implements Validator<MultipartFile> {


    @Override
    public Integer priority() {
        return 6;
    }

    @Override
    public void validate(MultipartFile file) {
        var invoiceNumber = getInvoiceNumber(file.getOriginalFilename());
        if (invoiceNumber <= 0) {
            log.error(LOG_UPLOAD_REJECTED, file.getOriginalFilename(), INCORRECT_INVOICE_NUMBER_ERROR.getMessage());
            throw new InvalidFileNameException(INCORRECT_INVOICE_NUMBER_ERROR.getMessage());
        }

    }
}
