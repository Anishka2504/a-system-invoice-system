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

    public boolean isInvoiceFileNameValid(String fileName) {
        if (!fileName.contains(".")) {
            return false;
        }
        int count = 0;
        for (int i = 0; i < fileName.length(); i++) {
            if (fileName.charAt(i) == '.') {
                count++;
            }
        }
        if (count != 1) {
            return false;
        }
        if (fileNameToArray(fileName).length != 5) {
            return false;
        }
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
        return Integer.parseInt(fileNameToArray(invoiceFileName)[4]);
    }

    private LocalDate getInvoiceDate(String invoiceFileName) {
        int year = Integer.parseInt(fileNameToArray(invoiceFileName)[1]);
        int month = Integer.parseInt(fileNameToArray(invoiceFileName)[2]);
        int day = Integer.parseInt(fileNameToArray(invoiceFileName)[3]);
        return LocalDate.of(year, month, day);
    }

    private Long getCompanyExternalId(String invoiceFileName) {
        return Long.parseLong(fileNameToArray(invoiceFileName)[0]);
    }

    private String[] fileNameToArray(String invoiceFileName) {
        return invoiceFileName.substring(0, invoiceFileName.lastIndexOf('.')).split("_");
    }
}
