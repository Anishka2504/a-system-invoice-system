package com.example.invoiceservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface InvoiceService {

    File uploadFile(MultipartFile file);

    String parseXlsFile(File file);
}
