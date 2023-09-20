package com.example.invoiceservice.validator.invoice;

import com.example.invoiceservice.exception.InvalidFileNameException;
import com.example.invoiceservice.validator.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

import static com.example.invoiceservice.constant.Constants.LOG_UPLOAD_REJECTED;
import static com.example.invoiceservice.entity.enums.ValidatorError.INCORRECT_DATE_ERROR;
import static com.example.invoiceservice.utils.InvoiceFileUtil.*;


@Component
@Slf4j
public class InvoiceFileNameDateValidator implements Validator<MultipartFile> {

    @Override
    public Integer priority() {
        return 5;
    }

    @Override
    public void validate(MultipartFile item) {
        var date = getInvoiceDate(item.getOriginalFilename());
        var currentYear = LocalDate.now().getYear();
        if (date.get(YEAR) != currentYear
                || (date.get(MONTH) <= 0 || date.get(MONTH) > 12)
                || (date.get(DAY_OF_MONTH) <= 0 || date.get(DAY_OF_MONTH) > 31)
                || (date.get(MONTH) == 2 & date.get(DAY_OF_MONTH) > 29)) {
            log.error(LOG_UPLOAD_REJECTED, item.getOriginalFilename(), INCORRECT_DATE_ERROR.getMessage());
            throw new InvalidFileNameException(INCORRECT_DATE_ERROR.getMessage());
        }

    }
}
