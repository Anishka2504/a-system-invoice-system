package com.example.invoiceservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface InvoiceFileService {

    void uploadFile(MultipartFile file) throws IOException;

    void sendMessage();
}
