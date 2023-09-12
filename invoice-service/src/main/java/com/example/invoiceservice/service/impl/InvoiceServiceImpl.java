package com.example.invoiceservice.service.impl;

import com.example.invoiceservice.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Iterator;

import static com.example.invoiceservice.constant.Constants.FILE_PATH;

@Service
@Slf4j
public class InvoiceServiceImpl implements InvoiceService {
    @Override
    public File uploadFile(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String fileName = file.getOriginalFilename();
                File dir = new File(FILE_PATH);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                File uploadedFile = new File(dir.getAbsolutePath() + File.separator + fileName);
                try (OutputStream outputStream = new FileOutputStream(uploadedFile)) {
                    outputStream.write(bytes);
                    log.info("File " + fileName + " was successfully uploaded to " + uploadedFile.getCanonicalPath()
                            + ", size - " + (double) uploadedFile.length() / 1024 + " kb");
                    return uploadedFile;
                } catch (IOException ex) {
                    log.error("Failed to upload file!");
                }
            } catch (IOException ex) {
                log.error("Failed to upload file!");
            }

        } else {
            log.warn("File is not exist or is empty!");
            return null;
        }
        return null;
    }

    @Override
    public String parseXlsFile(File file) {
        StringBuilder result = new StringBuilder();
        String filePath = file.getAbsolutePath();
        try (InputStream inputStream = new FileInputStream(filePath)) {
            Workbook workbook = new HSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {

                        case Cell.CELL_TYPE_STRING -> result.append(cell.getStringCellValue()).append("\t\t");
                        case Cell.CELL_TYPE_NUMERIC, Cell.CELL_TYPE_FORMULA ->
                                result.append(cell.getNumericCellValue());
                    }
                }
                result.append("\n");
            }

        } catch (IOException ex) {

        }
        return result.toString();
    }


}
