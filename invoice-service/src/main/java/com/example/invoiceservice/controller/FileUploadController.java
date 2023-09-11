package com.example.invoiceservice.controller;

import com.example.invoiceservice.service.InvoiceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static com.example.invoiceservice.constant.Constants.DIRECTORY_FOR_UPLOADED_FILES;
import static com.example.invoiceservice.constant.Constants.ROOT_PATH;

@RestController
@AllArgsConstructor
public class FileUploadController {

    private InvoiceService invoiceService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        File uploadedFile = invoiceService.uploadFile(multipartFile);
        if (uploadedFile.exists()) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }
}
