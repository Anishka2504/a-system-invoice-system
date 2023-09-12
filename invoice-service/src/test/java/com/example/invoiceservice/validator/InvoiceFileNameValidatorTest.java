package com.example.invoiceservice.validator;

import com.example.invoiceservice.entity.Company;
import com.example.invoiceservice.repository.CompanyRepository;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class InvoiceFileNameValidatorTest {

    private static final String TEST_INVOICE_FILE_NAME = "123525_2023_09_06_1.xls";

    @MockBean
    private CompanyRepository repository;

    @Before("")
    public String[] splitFileName() {
        return TEST_INVOICE_FILE_NAME.substring(0, TEST_INVOICE_FILE_NAME
                .lastIndexOf('.')).split("_");
    }

    // TODO: 12.09.2023 think about test algorithm
    @Test
    public void isInvoiceFileNameValidTest() {
        assertTrue(TEST_INVOICE_FILE_NAME.contains("."));
        assertEquals(5, splitFileName().length);
        assertTrue(!TEST_INVOICE_FILE_NAME.contains("[A-Za-z/*-+,()]"));
    }


    @Test
    public void isInvoiceNumberValidTest() {
        int invoiceNumber = Integer.parseInt(splitFileName()[4]);
        assertTrue(invoiceNumber > 0);
    }

    @Test
    public void isDateValidTest() {

        LocalDate negativeDate = LocalDate.of(2021, 12, 2);
        assertNotEquals(negativeDate, LocalDate.now());

        LocalDate positiveData = LocalDate.now();
        Assertions.assertEquals(positiveData, LocalDate.now());
    }

    @Test
    public void isCompanyExternalIdValidTest() {
        Long id = 3L;
        Long externalId = Long.getLong(splitFileName()[1]);
        Company testCompany = new Company(3L, 123525L, "Test Company");
        Mockito.when(repository.findCompanyByExternalId(externalId))
                .thenReturn(testCompany);
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(testCompany));
        Company company = repository.findCompanyByExternalId(externalId);
        Assertions.assertEquals(123525L, company.getExternalId());
        Optional<Company> optionalCompany = repository.findById(company.getId());
        Assertions.assertEquals(testCompany, optionalCompany.get());

    }

    @Test
    public void getInvoiceNumberTest() {
        Assertions.assertEquals(1, Integer.valueOf(splitFileName()[4]));
    }

    @Test
    public void getInvoiceDateTest() {
        String[] array = splitFileName();
        int year = Integer.parseInt(array[1]);
        Assertions.assertEquals(2023, year);
        int month = Integer.parseInt(array[2]);
        Assertions.assertEquals(9, month);
        int day = Integer.parseInt(array[3]);
        Assertions.assertEquals(6, day);
        LocalDate date = LocalDate.of(year, month, day);
        Assertions.assertEquals(LocalDate.of(2023, 9, 6), date);
    }

    @Test
    public void getCompanyExternalIdTest() {
        long externalId = Long.parseLong(splitFileName()[0]);
        Assertions.assertEquals(externalId, 123525);
    }

    @Test
    public void fileNameToArrayTest() {
        String[] array = splitFileName();
        Assertions.assertEquals(5, array.length);
        String[] testArray = Arrays.array("123525", "2023", "09", "06", "1");
        Assertions.assertArrayEquals(testArray, array);
    }

}
