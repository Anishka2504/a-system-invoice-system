package com.example.invoiceservice.controller;

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

@Slf4j
@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        if (!multipartFile.isEmpty()) {
            try {
                String fileName = multipartFile.getOriginalFilename();
                byte[] bytes = multipartFile.getBytes();
                File dir = new File(ROOT_PATH + File.separator + DIRECTORY_FOR_UPLOADED_FILES);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                File uploadedFile = new File(dir.getAbsolutePath() + File.separator + fileName);
                try (OutputStream outputStream = new FileOutputStream(uploadedFile)){
                    outputStream.write(bytes);
                    log.info("File " + uploadedFile.getName() + " was successfully uploaded to directory: " + uploadedFile.getCanonicalPath());
                } catch (IOException ex) {
                    log.error("Failed to upload file " + uploadedFile.getName());
                    ex.getCause();
                }

        } catch(Exception ex){
                log.error("Failed to upload file " + multipartFile.getName());
        }
    }
        return new ResponseEntity<>(HttpStatus.OK);

}
}
