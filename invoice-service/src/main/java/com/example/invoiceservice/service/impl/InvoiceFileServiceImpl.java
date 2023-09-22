package com.example.invoiceservice.service.impl;

import com.example.invoiceservice.converter.UploadedFileConverter;
import com.example.invoiceservice.dto.UploadedFileDto;
import com.example.invoiceservice.entity.UploadedFile;
import com.example.invoiceservice.entity.enums.FileStatus;
import com.example.invoiceservice.repository.UploadedFileRepository;
import com.example.invoiceservice.service.FileSaveIntoStorageService;
import com.example.invoiceservice.service.InvoiceFileService;
import com.example.invoiceservice.service.ValidatorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.invoiceservice.entity.enums.FileStatus.NEW;

@Service
@Slf4j
@AllArgsConstructor
public class InvoiceFileServiceImpl implements InvoiceFileService {

    private final ValidatorService validatorService;
    private final FileSaveIntoStorageService fileSaveIntoStorageService;
    private final UploadedFileRepository uploadedFileRepository;
    private final KafkaTemplate<Long, Set<UploadedFileDto>> kafkaTemplate;
    @Qualifier("uploadedFileConverter")
    private final UploadedFileConverter converter;

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

    @Override
    public void sendMessage() {
        Set<UploadedFileDto> uploadedFileDtoSet = findAllByStatus(NEW).stream()
                .map(converter::toDto).collect(Collectors.toSet());
        kafkaTemplate.send("uploaded.invoices", uploadedFileDtoSet);
        log.info("=> sending\n{}", uploadedFileDtoSet.stream()
                .sorted(Comparator.comparing(UploadedFileDto::getDateUpload))
                .map(file -> file.getName().concat("\t")
                        .concat("created at: ").concat(file.getDateUpload()).concat("\t\n")
                )
                .collect(Collectors.joining()));
    }

    @Override
    public Set<UploadedFile> findAllByStatus(FileStatus status) {
        return uploadedFileRepository.findAllByStatus(status).stream()
                .sorted(Comparator.comparing(UploadedFile::getDateUpload))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}


