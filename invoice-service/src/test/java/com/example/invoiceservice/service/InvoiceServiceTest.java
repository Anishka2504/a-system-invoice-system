package com.example.invoiceservice.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@SpringBootTest
public class InvoiceServiceTest {

    @Test
    public void parseXlsFileTest() throws IOException {
        String fileName =
                "/home/user/bin/projects/work/a-system-invoice-system/invoice-service/src/test/java/com/example/invoiceservice/test.xls";
        InputStream inputStream = new FileInputStream(fileName);
        Workbook workbook = new HSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(0);
        Cell cell1 = row.getCell(0);
        Assertions.assertEquals(cell1.getCellType(), Cell.CELL_TYPE_STRING);
        Assertions.assertEquals("petya", cell1.getStringCellValue());
        Cell cell2 = row.getCell(1);
        Assertions.assertEquals(cell2.getCellType(), Cell.CELL_TYPE_NUMERIC);
        Assertions.assertEquals(500, cell2.getNumericCellValue());
        inputStream.close();
    }


}
