package com.example.invoiceservice.validator.invoice;

import com.example.invoiceservice.entity.Company;
import com.example.invoiceservice.exception.InvalidFileNameException;
import com.example.invoiceservice.repository.CompanyRepository;
import com.example.invoiceservice.validator.Validator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static com.example.invoiceservice.constant.Constants.LOG_UPLOAD_REJECTED;
import static com.example.invoiceservice.entity.enums.ValidatorError.COMPANY_IS_NOT_IN_DATABASE_ERROR;
import static com.example.invoiceservice.utils.InvoiceFileUtil.getCompanyExternalId;

@Component
@AllArgsConstructor
@Slf4j
public class InvoiceFileNameCompanyExternalIdValidator implements Validator<MultipartFile> {

    private final CompanyRepository companyRepository;

       @Override
    public Integer priority() {
        return 4;
    }

    @Override
    public void validate(MultipartFile file) {
        var filename = file.getOriginalFilename();
        var companyExternalId = getCompanyExternalId(filename);
        Optional<Company> company = companyRepository.findCompanyByExternalId(companyExternalId);
        if (company.isEmpty()) {
            log.warn(LOG_UPLOAD_REJECTED, file.getOriginalFilename(), COMPANY_IS_NOT_IN_DATABASE_ERROR.getMessage());
            throw new InvalidFileNameException(COMPANY_IS_NOT_IN_DATABASE_ERROR.getMessage());
        }
    }
}
