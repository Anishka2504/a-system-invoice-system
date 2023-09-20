package com.example.invoiceservice.service.impl;

import com.example.invoiceservice.exception.InvalidFileNameException;
import com.example.invoiceservice.validator.InvoiceFileNameValidatorChain;
import com.example.invoiceservice.validator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

import static com.example.invoiceservice.entity.enums.ValidatorError.*;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class ValidatorServiceImplTest {

    @Mock
    private InvoiceFileNameValidatorChain validators;

    @InjectMocks
    private ValidatorServiceImpl validatorService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void validateCorrectFilename() {
        List<Validator<MultipartFile>> list = Collections.emptyList();
        when(validators.getValidators()).thenReturn(list);
        MultipartFile testFile = new MockMultipartFile("valid name", new byte[0]);
        assertThatNoException().isThrownBy(() -> validatorService.validateFilename(testFile));
        verify(validators, times(1)).getValidators();

    }

    @Test
    void validateFilenameWhenFileIsNotSelected() {
        when(validators.getValidators()).thenThrow(new InvalidFileNameException(NO_FILE_SELECTED_ERROR.getMessage()));

        assertThatThrownBy(() -> validatorService.validateFilename(null))
                .isInstanceOfAny(InvalidFileNameException.class);
        assertThatThrownBy(() -> validatorService.validateFilename(null))
                .hasMessage(NO_FILE_SELECTED_ERROR.getMessage());
        Mockito.verify(validators, atLeastOnce()).getValidators();

    }

    @Test
    void validateFilenameThatHasNotOnlyDigits() {
        when(validators.getValidators()).thenThrow(new InvalidFileNameException(DIGITS_ONLY_ERROR.getMessage()));
        MultipartFile fileWithInvalidName = new MockMultipartFile("invalid name", new byte[0]);
        assertThatThrownBy(() -> validatorService.validateFilename(fileWithInvalidName))
                .isInstanceOfAny(InvalidFileNameException.class);
        assertThatThrownBy(() -> validatorService.validateFilename(fileWithInvalidName))
                .hasMessage(DIGITS_ONLY_ERROR.getMessage());
        Mockito.verify(validators, atLeastOnce()).getValidators();

    }

    @Test
    void validateFilenameThatHasTooManyParts() {
        when(validators.getValidators()).thenThrow(new InvalidFileNameException(INCORRECT_PARTS_ERROR.getMessage()));
        MultipartFile fileWithInvalidName = new MockMultipartFile("invalid name", new byte[0]);
        assertThatThrownBy(() -> validatorService.validateFilename(fileWithInvalidName))
                .isInstanceOfAny(InvalidFileNameException.class);
        assertThatThrownBy(() -> validatorService.validateFilename(fileWithInvalidName))
                .hasMessage(INCORRECT_PARTS_ERROR.getMessage());
        Mockito.verify(validators, atLeastOnce()).getValidators();

    }

    @Test
    void validateFilenameThatHasIncorrectExternalCompanyID() {
        when(validators.getValidators()).thenThrow(new InvalidFileNameException(COMPANY_IS_NOT_IN_DATABASE_ERROR.getMessage()));
        MultipartFile fileWithInvalidName = new MockMultipartFile("invalid name", new byte[0]);
        assertThatThrownBy(() -> validatorService.validateFilename(fileWithInvalidName))
                .isInstanceOfAny(InvalidFileNameException.class);
        assertThatThrownBy(() -> validatorService.validateFilename(fileWithInvalidName))
                .hasMessage(COMPANY_IS_NOT_IN_DATABASE_ERROR.getMessage());
        Mockito.verify(validators, atLeastOnce()).getValidators();

    }

    @Test
    void validateFilenameThatHasIncorrectDate() {
        when(validators.getValidators()).thenThrow(new InvalidFileNameException(INCORRECT_DATE_ERROR.getMessage()));
        MultipartFile fileWithInvalidName = new MockMultipartFile("invalid name", new byte[0]);
        assertThatThrownBy(() -> validatorService.validateFilename(fileWithInvalidName))
                .isInstanceOfAny(InvalidFileNameException.class);
        assertThatThrownBy(() -> validatorService.validateFilename(fileWithInvalidName))
                .hasMessage(INCORRECT_DATE_ERROR.getMessage());
        Mockito.verify(validators, atLeastOnce()).getValidators();

    }

    @Test
    void validateFilenameThatHasIncorrectInvoiceNumber() {
        when(validators.getValidators()).thenThrow(new InvalidFileNameException(INCORRECT_INVOICE_NUMBER_ERROR.getMessage()));
        MultipartFile fileWithInvalidName = new MockMultipartFile("invalid name", new byte[0]);
        assertThatThrownBy(() -> validatorService.validateFilename(fileWithInvalidName))
                .isInstanceOfAny(InvalidFileNameException.class);
        assertThatThrownBy(() -> validatorService.validateFilename(fileWithInvalidName))
                .hasMessage(INCORRECT_INVOICE_NUMBER_ERROR.getMessage());
        Mockito.verify(validators, atLeastOnce()).getValidators();

    }

}