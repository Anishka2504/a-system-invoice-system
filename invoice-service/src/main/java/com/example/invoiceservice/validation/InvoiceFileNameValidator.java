package com.example.invoiceservice.validation;

import com.example.invoiceservice.entity.Company;
import com.example.invoiceservice.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

// TODO: 11.09.2023 validation
@Component
@AllArgsConstructor
public class InvoiceFileNameValidator {

    private final CompanyRepository companyRepository;

    public boolean fileNameValidate(String fileName) {
        return isDateValid(fileName) && isCompanyExternalIdValid(fileName) && isInvoiceNumberValid(fileName);
    }

    private boolean isInvoiceNumberValid(String invoiceFileName) {
        return getInvoiceNumber(invoiceFileName) > 0;
    }

    private boolean isDateValid(String invoiceFileName) {
        return getInvoiceDate(invoiceFileName).equals(LocalDate.now());
    }

    private boolean isCompanyExternalIdValid(String invoiceFileName) {
        Company company = companyRepository.findCompanyByExternalId(getCompanyExternalId(invoiceFileName));
        Optional<Company> optionalControlCompany = companyRepository.findById(company.getId());
        if (optionalControlCompany.isPresent()) {
            Company controlCompany = Company.builder()
                    .id(company.getId())
                    .externalId(company.getExternalId())
                    .companyName(company.getCompanyName())
                    .build();
            return company.equals(controlCompany);
        } else {
            return false;
        }
    }

    private int getInvoiceNumber(String invoiceFileName) {
        return Integer.parseInt(parseFileName(invoiceFileName)[4]);
    }

    private LocalDate getInvoiceDate(String invoiceFileName) {
        int year = Integer.parseInt(parseFileName(invoiceFileName)[1]);
        int month = Integer.parseInt(parseFileName(invoiceFileName)[2]);
        int day = Integer.parseInt(parseFileName(invoiceFileName)[3]);
        return LocalDate.of(year, month, day);
    }

    private Long getCompanyExternalId(String invoiceFileName) {
        return Long.getLong(parseFileName(invoiceFileName)[0]);
    }

    private String[] parseFileName(String invoiceFileName) {
        return invoiceFileName.substring(invoiceFileName.lastIndexOf('.')).split("_");
    }
}
