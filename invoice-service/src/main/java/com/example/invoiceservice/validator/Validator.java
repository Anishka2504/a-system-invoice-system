package com.example.invoiceservice.validator;

public interface Validator<I> {

    Integer priority();

    void validate(I item);
}
