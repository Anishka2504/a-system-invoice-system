package com.example.invoiceservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface ValidatorService {

    void validateFilename(MultipartFile file);
}
