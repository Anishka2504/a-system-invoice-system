package com.example.invoiceservice.repository;

import com.example.invoiceservice.entity.UploadedFile;
import com.example.invoiceservice.entity.enums.FileStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UploadedFileRepository extends JpaRepository<UploadedFile, Long> {

    Set<UploadedFile> findAllByStatus(FileStatus status);
}
