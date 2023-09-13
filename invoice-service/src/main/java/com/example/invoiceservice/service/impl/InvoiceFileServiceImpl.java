package com.example.invoiceservice.service.impl;

import com.example.invoiceservice.entity.UploadedFile;
import com.example.invoiceservice.repository.UploadedFileRepository;
import com.example.invoiceservice.service.FileSaveIntoStorageService;
import com.example.invoiceservice.service.InvoiceFileService;
import com.example.invoiceservice.service.ValidatorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

import static com.example.invoiceservice.entity.enums.FileStatus.*;

@Service
@Slf4j
@AllArgsConstructor

public class InvoiceFileServiceImpl implements InvoiceFileService {

    private final ValidatorService validatorService;
    private final FileSaveIntoStorageService fileSaveIntoStorageService;
    private final UploadedFileRepository uploadedFileRepository;

    @Override
    @Transactional
    public void uploadFile(MultipartFile file) throws IOException {
        validatorService.validateFilename(file);
        fileSaveIntoStorageService.saveFileIntoStorage(file);
        UploadedFile uploadedFile = UploadedFile.builder()
                .name(file.getOriginalFilename())
                .dateUpload(LocalDateTime.now())
                .dateModified(LocalDateTime.now())
                .status(NEW)
                .build();
        uploadedFileRepository.save(uploadedFile);
        log.info("File {} is successfully uploaded. Size: {} b",
                file.getOriginalFilename(), file.getSize());
    }
}


