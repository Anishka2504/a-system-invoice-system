package com.example.invoiceservice.controller;

import com.example.invoiceservice.service.InvoiceFileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
public class FileUploadController {

    private InvoiceFileService service;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        service.uploadFile(multipartFile);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PostMapping("/sendMessage")
//    public ResponseEntity<?> sendMessage() throws JsonProcessingException {
//        service.sendMessage();
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @PostMapping("/process")
    public ResponseEntity<?> process() {
        service.sendMessage();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
