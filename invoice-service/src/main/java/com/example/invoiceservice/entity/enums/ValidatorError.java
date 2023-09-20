package com.example.invoiceservice.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ValidatorError {

    NO_FILE_SELECTED_ERROR("No file selected for upload"),
    DIGITS_ONLY_ERROR("Invalid file name (digits only appropriate)"),
    INCORRECT_PARTS_ERROR("Invalid file name (may consists of company external ID, date of creation and number)"),
    COMPANY_IS_NOT_IN_DATABASE_ERROR("Company is not in database"),
    INCORRECT_DATE_ERROR("Invalid file name (incorrect date)"),

    INCORRECT_INVOICE_NUMBER_ERROR("Invalid file name (incorrect invoice number)");

    private final String message;

}
