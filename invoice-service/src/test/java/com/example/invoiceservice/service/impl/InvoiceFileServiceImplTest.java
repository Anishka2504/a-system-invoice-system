package com.example.invoiceservice.service.impl;

import com.example.invoiceservice.entity.UploadedFile;
import com.example.invoiceservice.exception.InvalidFileNameException;
import com.example.invoiceservice.repository.UploadedFileRepository;
import com.example.invoiceservice.service.FileSaveIntoStorageService;
import com.example.invoiceservice.service.ValidatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class InvoiceFileServiceImplTest {

    @Mock
    private ValidatorService validatorService;
    @Mock
    private FileSaveIntoStorageService fileSaveIntoStorageService;
    @Mock
    private UploadedFileRepository uploadedFileRepository;

    @InjectMocks
    private InvoiceFileServiceImpl invoiceAsMultipartFileService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    public void successfulUploadFileWithValidFilename() throws IOException {
        doNothing().when(validatorService).validateFilename(any(MultipartFile.class));
        doNothing().when(fileSaveIntoStorageService).saveFileIntoStorage(any(MultipartFile.class));

        MultipartFile multipartFileWithValidFilename = new MockMultipartFile("valid", new byte[0]);
        invoiceAsMultipartFileService.uploadFile(multipartFileWithValidFilename);

        verify(validatorService, only()).validateFilename(any(MultipartFile.class));
        verify(fileSaveIntoStorageService, only()).saveFileIntoStorage(any());
        verify(uploadedFileRepository, only()).save(any(UploadedFile.class));
    }

    @Test
    public void throwExceptionWhenUploadFileWithInvalidFilename() throws IOException {
        doThrow(new InvalidFileNameException()).when(validatorService).validateFilename(any(MultipartFile.class));

        MultipartFile multipartFileWithInvalidFilename = new MockMultipartFile("invalid name", new byte[0]);

        Assertions.assertThrows(InvalidFileNameException.class, () ->
                invoiceAsMultipartFileService.uploadFile(multipartFileWithInvalidFilename));

        verify(validatorService, only()).validateFilename(any(MultipartFile.class));

    }

}