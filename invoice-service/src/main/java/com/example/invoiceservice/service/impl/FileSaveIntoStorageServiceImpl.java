package com.example.invoiceservice.service.impl;

import com.example.invoiceservice.service.FileSaveIntoStorageService;
import com.example.invoiceservice.utils.InvoiceFileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.Objects;

import static com.example.invoiceservice.constant.Constants.DIRECTORY_FOR_UPLOADED_FILES;
import static com.example.invoiceservice.constant.Constants.INVOICE_SERVICE_DIR;
import static com.example.invoiceservice.utils.InvoiceFileUtil.*;


@Service
public class FileSaveIntoStorageServiceImpl implements FileSaveIntoStorageService {
    @Override
    public void saveFileIntoStorage(MultipartFile file) throws IOException {
        Path rootDirectory = Path.of(System.getProperty("user.dir") + File.separator
                + INVOICE_SERVICE_DIR + File.separator
                + DIRECTORY_FOR_UPLOADED_FILES + File.separator).normalize();
        if (!Files.exists(rootDirectory)) {
            Files.createDirectory(rootDirectory);
        }
        try (InputStream inputStream = new ByteArrayInputStream(file.getBytes())) {

            String filename = Paths.get(Objects.requireNonNull(file.getOriginalFilename())).toString();
            String companyExternalId = String.valueOf(InvoiceFileUtil.getCompanyExternalId(filename));
            Map<String, Integer> map = InvoiceFileUtil.getInvoiceDate(filename);
            Path directoriesForUploadedFile = Files.createDirectories(
                    rootDirectory
                            .resolve(companyExternalId)
                            .resolve(String.valueOf(map.get(YEAR)))
                            .resolve(String.valueOf(map.get(MONTH)))
                            .resolve(String.valueOf(map.get(DAY_OF_MONTH)))
                            .resolve(String.valueOf(InvoiceFileUtil.getInvoiceNumber(filename))));
            Path path = directoriesForUploadedFile.resolve(filename);
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);

        }
    }
}
