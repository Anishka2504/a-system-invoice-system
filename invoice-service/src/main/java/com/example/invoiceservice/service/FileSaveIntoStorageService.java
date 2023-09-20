package com.example.invoiceservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileSaveIntoStorageService {

    void saveFileIntoStorage(MultipartFile file) throws IOException;
}
