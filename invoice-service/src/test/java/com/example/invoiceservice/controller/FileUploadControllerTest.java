package com.example.invoiceservice.controller;

import com.example.invoiceservice.service.InvoiceFileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.http.HttpStatus.OK;

class FileUploadControllerTest {

    @Mock
    private InvoiceFileService invoiceFileService;

    @InjectMocks
    private FileUploadController fileUploadController;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    public void shouldReturnOkWhenUploadFile() throws IOException {
        doNothing().when(invoiceFileService).uploadFile(any());
        var responseEntity = fileUploadController.uploadFile(any());
        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
        Mockito.verify(invoiceFileService, only()).uploadFile(any());
    }

}