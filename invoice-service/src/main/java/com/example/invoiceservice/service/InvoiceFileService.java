package com.example.invoiceservice.service;

import com.example.invoiceservice.entity.UploadedFile;
import com.example.invoiceservice.entity.enums.FileStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

public interface InvoiceFileService {
    void uploadFile(MultipartFile file) throws IOException;

    void sendMessage() throws JsonProcessingException;

    Set<UploadedFile> findAllByStatus(FileStatus status);
}
