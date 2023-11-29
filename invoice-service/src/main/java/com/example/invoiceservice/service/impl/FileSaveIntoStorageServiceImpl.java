package com.example.invoiceservice.service.impl;

import com.example.invoiceservice.service.*;
import com.example.invoiceservice.utils.*;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import static com.example.invoiceservice.utils.InvoiceFileUtil.*;


@Service
public class FileSaveIntoStorageServiceImpl implements FileSaveIntoStorageService {

    public static final String INVOICE_SERVICE_DIR = "invoice-service";
    public static final String DIRECTORY_FOR_UPLOADED_FILES = "loaded";

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
